# The Russell Tactics Language

Russell's proof-search engine is driven by **tactics** — small composable strategies that decide which leaves of the proof tree to expand next, when to stop, and when to hand off to a different sub-strategy. Tactics can be built in code, but the easiest way to experiment with them is the **tactic DSL**, a small string-based language passed via the `tactic=` argument of `reprove` / `prove` (and a few other commands that wrap the prover).

> The authoritative, always-current list of tactic components is the `tactic` command:
> `russellj tactic` lists every component (grouped by category) plus the file-based derived tactics;
> `russellj tactic name=<x>` shows one component's full description and parameters. This document explains
> the model; when in doubt, trust `tactic`.

---

## Why a DSL

The proof-search engine knows only one type: `RuProverTactic`. Earlier, complex strategies were assembled in Flow code by chaining combinators (`ruSequenceTactic`, `ruLoopWhileProgressTactic`, `ruSubproofReplayTactic`, …). That required recompiling for every experiment. The DSL exposes the same combinators and atomic tactics as a string syntax, so a strategy can be tuned per-invocation:

```
reprove target=all tactic="loop(seq(limited(spr(3), size=4096),
                                     limited(bfs, size=4096, depth=5, batch=16)), 3)"
```

And recurring strategies can be saved as **derived tactics** (`.tac` files) and referenced by name — the
example above is exactly the shipped `spr-bfs` tactic, so the same run is just `tactic="spr-bfs(3)"`.

---

## Grammar

```
tactic   ::= name ( '(' arg ( ',' arg )* ')' )?
arg      ::= name '=' value         (named)
           | value                  (positional)
value    ::= literal
           | tactic
literal  ::= '-'? digits ( 's' | 'ms' | 'm' | 'h' )?   (an int, optionally a duration)
```

- Names use letters, digits, underscores, hyphens and dots (so `linear-guided`, `max-size`, `pm3.2i` are valid identifiers).
- Whitespace (spaces, tabs, newlines) is freely interleaved — multi-line, indented tactics work the same as single-line ones.
- A bare identifier without parentheses is a zero-arg tactic (`bfs`).
- Every parameter can be passed positionally or by name (`limited(bfs, size=4096)` ≡ `limited(bfs, 0, 4096)`); named args are clearer and order-independent.
- An argument value can itself be a tactic (nesting): `limited(bfs, …)`, `seq(spr(3), bfs)`.
- Inside a `.rus` script the `tactic=` value is normally double-quoted; the DSL parser strips one pair of surrounding quotes.

### Substitution

Russell `.rus` scripts substitute `$varname` inside argument values before they reach the DSL parser:

```
@arg attempts { @defval 3 }
@arg max-size { @defval 4096 }
...
reprove tactic="limited(spr($attempts), size=$max-size)";
```

works as long as `attempts` / `max-size` are in `state.vars` (passed on the CLI, or set by earlier statements). Note: a `.rus` command-arg value **may contain `=`** (so named-arg tactic strings work as `tactic="…"`); historically it could not, which is why older scripts used positional tactics.

---

## Tactic components

Components are grouped into categories (the same grouping `tactic` prints). Below is the model; see
`tactic name=<x>` for exact parameters.

### Atoms — the irreducible leaves

| Atom | Meaning |
|------|---------|
| `bfs` | Breadth-first expansion of the whole open frontier. Complete but unbounded — wrap in `limited`. |
| `done` / `giveup` | Sentinel: terminate immediately. |
| `spr(attempts)` | Sub-proof replay: replay up to `attempts` held-out corpus sub-proofs as guides. Unbounded; wrap in `limited`. Needs the corpus step index (built automatically — see below). |
| `linear-guided(attempts)` | Replay corpus goal→premise spines (`attempts` chains). Only replays the spine; off-spine goals fall to the next stage (so it needs a BFS fallback, e.g. `seq(linear-guided(3), limited(bfs,…))`). Needs the step index. |
| `ml` | Expand the frontier in ML-ranked (best-first) order — the ML analogue of `bfs`. Needs an ML selector (`load-ml=1`). Bound/beam via `limited(ml, batch=k, size=, depth=)`. |
| `ap(a, child, …)` | Replay a positional proof **tree**: assertion `a` applied at the goal, each child proving a premise (a nested `ap(…)` or the leaf `prem`). Deterministic; the structure-carrying atom. |
| `oracle(max-true-props, max-false-props, max-variants, max-proofs)` | Drive the search by the theorem's own stored proof tree (oracle stubs map proof nodes onto prover props). Used by the round-trip reprovability test. |
| `follow-proof(theorem)` | Replay a named theorem's stored proof tree (diagnostic). |
| `kalmar-closer` | Propositional closer: unfold membership atoms + a Kalmár certificate. `[PRUNE-CANDIDATE]` — no active workflow invokes it. |

### Refiners — goal reducers (composed inside `refine(…)`, not runnable standalone)

| Refiner | Meaning |
|---------|---------|
| `strip-forall` | Strip the leading ∀-chain; the matrix proof is re-quantified with `ax-gen`. |
| `rel-intro` | Reduce a `⊆` / set-`=` goal to a fresh-setvar membership subgoal (via `ssriv`/`eqriv`). |
| `eq-rewrite` | Unfold compound membership atoms + canonicalize by biconditional congruence to a residual. |

### Guides — a value yielding a proof guide (consumed by a realizer)

| Guide | Meaning |
|-------|---------|
| `refine(r1, …, nnf=on, closer=<t>)` | Compose goal refiners + a residual closer (default: a bounded BFS) into a candidate proof tree. `nnf=on` canonicalizes to negation-normal-form. |
| `stored` | The theorem's own stored proof tree (`replay(stored)` reconstructs an existing proof). |

### Realizers — run a guide through the engine

| Realizer | Meaning |
|----------|---------|
| `replay(<guide>)` | Reconstruct the guide through the engine (track + replay selector). Valid by construction — sound and precedence-checked. |

### Combinators

| Form | Meaning |
|------|---------|
| `seq(t1, t2, …)` | Run `t1`; on `Done` without a proof, fall through to `t2`, … (first that closes wins). Built eagerly; threads env stage→stage. |
| `or-else(t1, t2, …)` | Lazy alternation: like `seq`, but each alternative is **built** only when the previous finishes without a proof — so an expensive guide build is skipped when an earlier one closes. |
| `loop(body, max-iters=N)` | Each iteration runs a **fresh** `body` to completion; repeats while the tree grows, up to `N` iters (or until proved). |
| `limited(t, time=, size=, depth=, batch=)` | Wrap `t` with cutoffs (omit/`0` disables each): `time` wall-clock, `size` nodes added since the tactic started (a *delta*), `depth` absolute tree depth. `batch` paces expansion to N leafs/round — a non-zero `batch` is what keeps a `size` cap **strict**. |
| `scoped(scope, t)` | Restrict candidate rules to `<scope>` (a named scope or an inline assertion list), then run `t`. |
| `focus(selector, t)` | Restrict `t` to the subtrees rooted at the selector's seeds. |
| `track(selector, t)` | Restrict `t` to exactly the selector's props each step (e.g. replay a guide). |

### Selectors — pick props inside `focus(…)` / `track(…)`

`all` (every open leaf), `frontier` (where the guide diverges from the tree), `leaf(id)` (one prop), `assertion(name)` (all props applying a named assertion).

Notes:
- `seq` delegates `keep_expanding` to its currently-active sub-tactic, and threads env modifications (`linear-guided`, `oracle` close over the previous stage's env).
- `loop` rebuilds its `body` each iteration, so state-carrying tactics (`spr`'s `attempts_remaining`, etc.) reset — only the iteration counter persists.

---

## Derived tactics (`.tac`)

Named *compositions* of the primitives above live as files under the top-level `tactics/` directory, with
the extension `.tac` — the tactic analogue of `.rus` scripts. A `.tac` file is a metadata comment block
(the **same** `@help` / `@arg` / `@defval` / `@category` syntax scripts use) followed by a tactic-DSL body
with `$arg` placeholders:

```
/**
    @help { Sub-proof replay with a BFS fallback. ...full description... }
    @arg attempts   { corpus sub-proofs to try per leaf. @defval 3 }
    @arg max-size   { per-stage node cap. @defval 4096 }
    @category derived
*/
limited(loop(seq(limited(spr($attempts), size=$max-size),
                 limited(bfs, size=$max-size, depth=$max-depth, batch=16)), $max-iters),
        size=$total-size)
```

**Referencing them.** Anywhere a tactic string is accepted (`prove`/`reprove` `tactic=`), you may pass
either an inline DSL body (as before) **or** a `.tac` reference — a relative path under `tactics/`
(extension omitted), optionally with arguments: `tactic=spr-bfs`, `tactic=spr-bfs(5)`,
`tactic=spr-bfs(attempts=5, max-iters=3)`, `tactic=subdir/my-tactic`.

**Resolution** (`ruResolveTacticSource` in `dsl.flow`): the head name is looked up; a **built-in**
atom/combinator is used inline (built-ins always win over a same-named file), otherwise — if the name
contains `/` or resolves to a `tactics/<name>.tac` file — the file is loaded, its `@arg`s are bound from
the invocation (named-or-positional, `@defval` fallback), `$arg` placeholders are substituted (to a
fixpoint, so a default may reference another arg), and the resulting DSL body is parsed and built. The
expansion happens *before* the `spr`/`linear-guided` step-index decision, so a `.tac` whose body uses them
still gets the corpus index built. Files are re-read each use (no caching), matching `.rus` scripts.

> Caveat: a `@defval` value cannot contain spaces (the doc-comment grammar captures one whitespace-
> delimited token) — keep defaults single-token, e.g. `@defval limited(bfs,size=4096)` (no spaces).

**Discovery.** `tactic` lists the file tactics under a "Derived tactics" heading; `tactic name=<x>` prints a
`.tac` file's `@help` + `@arg` table; `tactic derived` lists the `derived` category. Shipped derived
tactics: `def-close`, `spr-bfs`, `linear-bfs`.

To add a composition, drop a new `.tac` file under `tactics/` — no rebuild needed (the body is parsed at
runtime). Add a new *primitive* in code instead (see "Adding a new atom") only when it cannot be expressed
as a composition.

---

## How `reprove` / `prove` use the DSL

The prover commands:

1. Expand any `.tac` reference and parse `tactic="…"` (if non-empty) into a `RuTacticBuilder` — a closure `(RuProverEnv) -> (RuProverEnv, RuProverTactic)`.
2. Construct the per-theorem prover env with a placeholder tactic.
3. Call `builder.build(penv)` to get both the real tactic and an (optionally modified) env back.
4. Store the tactic on the env and run the search.

Atoms that need state from the env (`linear-guided`, `oracle`) close over the result of step 2 inside their builder; the rest are produced statically. The combinators thread builders so env modifications propagate through chains.

The `step_index` (used by `spr` / `linear-guided`) is built lazily — only when the *resolved* tactic source contains `spr` or `linear-guided` — because on the full `set` corpus it takes seconds and gigabytes of RAM. Resolution happens before this check, so a `.tac` whose body uses them still triggers it.

### Default

When `tactic=""` (the default), the prover runs a strict bounded BFS — `limited(bfs, size=max-size, depth=max-depth, batch=16)` — with the values from the command's own `max-depth` / `max-size` args.

### Context flags

| `reprove` argument | Effect |
|----------|--------|
| `load-ml=1` | Load the per-assertion ML selector → enables the `ml` atom. |
| `strict-fail=1` | After the run, `ruCrash` if any theorem was not reproved (CI / regression mode). |
| `coverage=1` | Measure proof-step coverage from the search tree inline. |

---

## Examples

```
# strict bounded BFS (the default; explicit form)
reprove tactic="limited(bfs, size=4096, depth=5, batch=16)"

# sub-proof replay with a BFS fallback — inline, then via the derived tactic
reprove tactic="loop(seq(limited(spr(3), size=4096), limited(bfs, size=4096, depth=5, batch=16)), 3)"
reprove tactic="spr-bfs(3)"

# premise-spine replay with a BFS fallback (derived)
reprove tactic="linear-bfs(attempts=3)"

# ML-ranked beam
reprove load-ml=1 tactic="limited(ml, batch=5, size=10000, depth=7)"

# drive the search by each theorem's own proof (round-trip test)
reprove strict-fail=1 tactic="oracle(-1, -1)"

# definitional closure (the Mizar-import workhorse; a derived tactic)
prove target=all steps=1 tactic="def-close"

# time-bounded BFS for compression
reprove tactic="limited(bfs, time=5s, size=4096, depth=5, batch=16)"
```

---

## Adding a new atom

The DSL atom registry is the `if/else` chain in `ruBuildTacticFromAst` (in `src/ru/prover/tactics/dsl.flow`). To add a new atom:

1. Pick a name (e.g. `myatom`) and a `ruDslBuildMyAtom(ast, ctx)` helper returning `Maybe<RuTacticBuilder>`.
2. In the helper, extract args with `ruDslGetInt` / `ruDslGetDuration` / `ruDslGetTacticBuilder` (named-or-positional via `ruDslGetArg`).
3. Build either a static tactic (`ruStaticTacticBuilder(t)`) or, if you need the env, a deferred builder (`RuTacticBuilder(\penv -> Pair(penv, t))`).
4. Add `else if (n == "myatom") ruDslBuildMyAtom(ast, ctx)` to the dispatch chain, and a `ruTacticDocs()` entry so `tactic` documents it. If it should win over a same-named `.tac` file, add it to `ruBuiltinTacticNames`.

If the atom needs ambient data not in `RuTacticContext`, extend that record (and the call sites in `reprove.flow` / `prove.flow` / `annotate_proof.flow`). **Prefer a derived `.tac` tactic** (no code, no rebuild) whenever the new tactic is a *composition* of existing primitives.
