# The Russell Tactics Language

Russell's proof-search engine is driven by **tactics** — small composable strategies that decide which leaves of the proof tree to expand next, when to stop, and when to hand off to a different sub-strategy. Tactics can be built in code, but the easiest way to experiment with them is the **tactic DSL**, a small string-based language passed via the `tactic=` argument of the `reprove` command (and a few other commands that wrap the prover).

---

## Why a DSL

The proof-search engine knows only one type: `RuProverTactic`. Earlier, complex strategies were assembled in Flow code by chaining combinators (`ruSequenceTactic`, `ruLoopWhileProgressTactic`, `ruSubproofReplayTactic`, …). That required recompiling for every experiment. The DSL exposes the same combinators and atomic tactics as a string syntax, so a strategy can be tuned per-invocation:

```
reprove target=all tactic="seq(
    uq(10, 2, 32),
    loop(
        seq(spr(3, 4096), bounded-bfs(5, 4096)),
        3
    )
)"
```

---

## Grammar

```
tactic   ::= name ( '(' arg ( ',' arg )* ')' )?
arg      ::= name '=' value         (named)
           | value                  (positional)
value    ::= number-or-duration
           | tactic
literal  ::= '-'? digits ( 's' | 'ms' | 'm' | 'h' )?
```

- Names use letters, digits, underscores and hyphens (so `bounded-bfs`, `max-size` are valid identifiers).
- Whitespace (spaces, tabs, newlines) is freely interleaved between tokens — multi-line tactics with indentation work the same as single-line ones.
- A literal is a non-negative or negative integer optionally followed by a duration suffix (`60s`, `5m`, `1500ms`).
- A bare identifier without parentheses is a zero-arg tactic (`bfs`).
- Inside a `.rus` script, the `tactic=` value is normally double-quoted. The DSL parser strips one pair of surrounding quotes.

### Substitution

Russell `.rus` scripts substitute `$varname` inside argument values before they reach the DSL parser. So:

```
@arg attempts { @defval 1 }
@arg max-size { @defval 4096 }
...
reprove tactic="spr($attempts, $max-size)";
```

works as long as `attempts` and `max-size` are in `state.vars` (either passed on the CLI when invoking `run-script`, or set by earlier statements). `@arg` defaults are *not* auto-populated into vars — you still have to pass them on the command line, or default them in script logic.

---

## Atomic tactics

These are the leaves of any tactic expression. Each lists positional parameters in order; all parameters can also be passed by name.

### Generic search

| Atom | Positional params | Description |
|------|-------------------|-------------|
| `bfs` | (none) | Breadth-first expansion of every open leaf, no resource limits. Use only inside `limited(...)`. |
| `done`, `giveup` | (none) | Sentinel: terminate immediately. |
| `bounded-bfs(max-depth, max-size)` | depth, size | BFS bounded by tree depth and total node count. |
| `top-n-bfs(n, max-depth, max-size)` | n, depth, size | BFS but expanding only the first `n` leaves per round. |
| `unif-quality(rank, max-depth, max-size)` (alias `uq`) | rank, depth, size | Beam over the most-specific leaves first (specificity is encoded as insertion order, set in `expand.flow`). When `rank <= 0` returns `Done` immediately, so `seq()` skips it cleanly. |

### Corpus-guided

| Atom | Positional params | Needs in context |
|------|-------------------|------------------|
| `spr(attempts, max-size)` | attempts per leaf, follow-proof budget | Step index (built automatically by `reprove` when the tactic string mentions `spr`). Subsumes the removed `fragment-replay` atom. |
| `linear-guided(max-size, attempts)` | follow-proof budget, attempts | Step index (built automatically when the tactic string mentions `linear-guided`). Replays corpus goal→premise spines. |
| `follow-proof(theorem)` | theorem name | The named theorem must exist in the corpus and its proof tree must not introduce new hyps (so this is mostly useful for SPR-style sub-proofs). |

### Heuristic / specialised

| Atom | Positional params | Needs in context |
|------|-------------------|------------------|
| `ml(top-k, max-depth, max-size)` | top-k, depth, size | The ML selector — enabled with `load-ml=1` on the command. |
| `oracle(max-true, max-false, max-variants, max-proofs)` | as named | Mutates `penv.fns` to install oracle-aware unifiers, then drives the search by the original proof tree of the theorem being proved. Used by the round-trip reprovability test. |

---

## Combinators

| Form | Meaning |
|------|---------|
| `seq(t1, t2, …)` | Run `t1` to completion, then `t2`, then `t3`, ... When a sub-tactic returns `Done`, the sequence advances; when all are done, the whole `seq` is done. |
| `loop(body, max-iters)` | Each iteration calls `body` as a *fresh* tactic (so state-carrying tactics start over), runs it to completion, and checks whether the tree grew. Re-runs until no progress, `max-iters` reached, or a proof is found. |
| `limited(inner, time, applied, produced)` | Wraps `inner` with cutoffs: wall-clock time, number of `step` invocations, total leaves produced. A limit of `0` disables that particular check. |

Two notes on `seq`:

- A sub-tactic's `keep_expanding` predicate is what the engine checks per-leaf during a *batch* expansion. `seq` delegates to its currently-active sub-tactic; when the sub-tactic returns `Done` and `seq` switches to the next, the new sub-tactic's `keep_expanding` takes over.
- Sub-tactics that modify the prover env (`linear-guided`, `oracle`) thread their env changes through `seq`: each sub-tactic builder is invoked with the env produced by the previous one.

`loop` rebuilds its `body` on every iteration. That means `loop(seq(spr(3, 4096), bounded-bfs(5, 4096)), 3)` constructs three brand-new `spr` + BFS instances, each with fresh `attempts_remaining` and `pending_steps` refs — the iteration counter is the only state that persists.

---

## How `reprove` uses the DSL

The universal `reprove` command:

1. Parses `tactic="…"` (if non-empty) into a `RuTacticBuilder` — a closure `(RuProverEnv) -> (RuProverEnv, RuProverTactic)`.
2. Constructs the per-theorem prover env with a placeholder tactic.
3. Calls `builder.build(penv)` to get both the real tactic and an (optionally modified) env back.
4. Stores the tactic on the env and runs the search.

Atoms that need state from the env (`linear-guided`, `oracle`) close over the result of step 2 inside their builder; atoms that don't (everything else) are produced statically. The combinators (`seq`, `loop`, `limited`) thread builders so env modifications propagate correctly through chains.

The `step_index` (used by `spr` and `linear-guided`) is built lazily — only when the substring `spr` or `linear-guided` appears in the tactic string — because on the full `set` corpus it takes seconds and gigabytes of RAM.

### Default

When `tactic=""` (the default), `reprove` runs `bounded-bfs(max-depth, max-size)` with the values from the command's own `max-depth` / `max-size` args. This makes `reprove` a drop-in replacement for the old `reprove-dumb` command.

### Context flags

These `reprove` arguments enable atoms that need extra data:

| Argument | Effect |
|----------|--------|
| `load-ml=1` | Load the per-assertion ML selector → enables `ml`. |
| `strict-fail=1` | After the run, `ruCrash` if any theorem was not reproved. Used by the CI round-trip test. |

The `step_index` for `spr`/`linear-guided` is detected automatically by substring; nothing else is needed.

---

## Examples

### Old commands as DSL strings

| Removed command | Equivalent `reprove` invocation |
|-----------------|--------------------------------|
| `reprove-dumb` | `reprove tactic="bounded-bfs(5, 4096)"` (or just omit `tactic=`) |
| `reprove-fragments` | `reprove tactic="spr(3, 4096)"` |
| `reprove-ml` | `reprove load-ml=1 tactic="ml(5, 7, 10000)"` |
| `reprove-linear` | `reprove tactic="linear-guided(4096, 3)"` |
| `reprove-oracle` | `reprove strict-fail=1 tactic="oracle(-1, -1)"` |

### Combined SPR + BFS

The strategy that earlier required Flow-level code (`ruCombinedStrategy`):

```
reprove
    target=all
    max-depth=5
    max-size=4096
    attempts=3
    time-limit=60s
    tactic="seq(
        uq(10, 2, 32),
        loop(
            seq(
                spr(3, 4096),
                bounded-bfs(5, 4096)
            ),
            3
        )
    )";
```

### Time-bounded BFS for compression

`limited` is the standard way to attach a budget to any inner tactic:

```
reprove tactic="limited(bounded-bfs(5, 4096), 5s, 0, 0)"
```

---

## Adding a new atom

The DSL atom registry is the `if/else` chain in `ruBuildTacticFromAst` (in `src/ru/prover/tactics/dsl.flow`). To add a new atom:

1. Pick a name (e.g. `myatom`) and a `ruDslBuildMyAtom(ast, ctx)` helper that returns `Maybe<RuTacticBuilder>`.
2. In the helper, extract args with `ruDslGetInt` / `ruDslGetDuration` / `ruDslGetTacticBuilder`.
3. Build either a static tactic (`ruStaticTacticBuilder(t)`) or, if you need the env, a deferred builder (`RuTacticBuilder(\penv -> Pair(penv, t))`).
4. Add `else if (n == "myatom") ruDslBuildMyAtom(ast, ctx)` to the dispatch chain.

If the atom needs ambient data not already in `RuTacticContext`, extend that record (and update the call sites in `reprove.flow` and the other prover commands that build a `RuTacticContext`, e.g. `prove.flow` / `annotate_proof.flow`).
