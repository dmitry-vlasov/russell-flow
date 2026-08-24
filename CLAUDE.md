# Russell Flow

## Communication
- Keep answers brief and clear.
- Use simple words and plain vocabulary.
- No idioms.

## Stack
- Language: Flow9 (functional, statically typed) → compiled to `src/russell.jar`
- Math formats: Russell `.ru` (native) and Metamath `.mm`
- Grammar: Lingo PEG files (`.lingo`), compiled by `flowc1`
- Scripting: `.rus` scripts compose built-in operations (read, verify, prove, translate, optimize)

## Mizar verifier commands
- Build: `flowc1 jar=1 mizar/original/mizar.flow` (from `src/`) — ONE jar, no other
- Verify one article: `bin/mizarj article=xboole_1`
- Verify the whole MML in one process: `bin/mizarj mem=16g article-list=$RUSSELL_MATH/MML-test/mml.lar jobs=8 ref=~/mizar_oracle/m4ref`
- Component suites: `bin/mizarj test=all` (or `test=<suite>`, `test=list`)
- Fast CI check (suites + full-chain 8-article fixture vs reference): `bin/mizar-ci.sh`
- Intermediates stay in MEMORY; `dump-xml=` / `dump-par=` are the only writers
- MML→Russell gate (emit + tactic + Metamath): `scripts/translate/mizar/gate.sh tac=def-close` — see docs/mml-port-plan.md §9 (campaign closed at 275)

## Commands
- Build: `./build_java.sh`
- Roundtrip test (CI): `bin/russellj translate/mm2ru2mm set`
- Verify: `bin/russellj verify afile=set-50000`
- Reprove: `bin/russellj reprove/oracle afile=set-50000`
- REPL: `bin/russellj server=console` (or `server=unilambda` for the unilambda REPL)
- Unilambda: `bin/russellj test/uni` (eval+uneval suite); `uni-eval` / `uni-run` / `uni-brun` commands
- Memory override: `bin/russellj mem=16g ...` (default 10g)

## Mathematics Libraries
Math libraries live in `~/dev/math` (`set/`, `mm-theory/`, `set-3000.mm` … `set-100000.mm`).
`bin/russellj` passes `$RUSSELL_MATH` as `import-roots` automatically — no CLI flag needed.
Never hardcode absolute paths to math libraries in `russell.conf` files.

## Architecture
- `RuEnv` owns config, I/O, thread pool — never modify inside a task
- `RuState` owns the theorem base (`RuMath`) — threaded through tasks as return value, never mutated
- Scripts (`ScComm`) own sequencing — business logic lives in tasks, not the script engine
- `russell.conf` is found by walking up from CWD — run `russellj` from inside the project tree

## Key Files
| File | Purpose |
|------|---------|
| [src/russell.flow](src/russell.flow) | Entry point — dispatches HTTP / LSP / console / one-shot |
| [src/base/conf.flow](src/base/conf.flow) | `RuConf` — config loading and merging |
| [src/ru/src.flow](src/ru/src.flow) | Core AST: `RuSource`, `RuDecl`, `RuAssertion`, `RuMath` |
| [src/ru/prover/](src/ru/prover/) | Proof search: oracle-guided backward chaining |
| [src/ru/unify/](src/ru/unify/) | Unification: trie-indexed pattern matching |
| [src/uni/](src/uni/) | Unilambda: bidirectional `eval`/`uneval` on the RuExp kernel — see [docs/unilambda.md](docs/unilambda.md) |
| [src/mm/mm2ru.flow](src/mm/mm2ru.flow) | Metamath → Russell translator |
| [docs/](docs/) | Architecture docs: language, algorithms, translation |

## Flow9 Notes
- Records (`RuFoo`), dispatched with `switch` / `cast` — no classes, no mutation
- State threading is explicit: functions take and return `RuState`
- Imports are path-based: `import ru/src;` → `src/ru/src.flow`

## Gotchas
- `afile` is the base name without extension or import-root prefix (`afile=set-50000`, not a full path)
- `import-roots` from `russell.conf` and CLI are merged, not overridden — both always apply
- JSON caches in `db/` can go stale after source changes; delete the `.json` to force a rebuild
- JVM stack is 128m (`-Xss128m`) for deep proof-search recursion — don't reduce it
- `switch` in Flow9 must be exhaustive or have a `default` branch; for datasets outside the project run from the math dir — `mm2ru.rus` uses CWD as import root

## Mizar port (`src/mizar/original/`) — HARD RULES
This tree is a TRANSCRIPTION of `~/dev/system/{base,kernel}/*.pas`. It is not
our design, and a discrepancy is a question about the ORIGINAL's logic.

1. **Pascal first.** Before editing anything here, open the Pascal procedure
   that produces the differing output and read it. Do not reason about the
   Flow9 code to explain a diff.
2. **Cite it.** Every fix commit names the `file.pas:line` it transcribes. A
   fix that cannot cite one is a guess — do not commit it.
3. **Bucket before fixing.** Never chase byte diffs one article at a time.
   Classify the failures first — by the element the diff lands on, or by the
   Pascal routine that writes it; they cluster into a few causes.
4. **Suspect the shared units, not the new code.** The analyzer calls 78 of
   the 245 routines in the shared units (correl, identify, roundcl, iocorrel,
   schemes, ellipses). They were ported for the checker, are exact on 1.4M
   checker inferences, and were still half-implemented for the analyzer's
   inputs. Both defects of that class had the same shape: a Pascal `A or B`
   with only `A` ported, and a case list with a variant omitted.
5. **Port-only machinery is a bug source.** Caches, memos and bounds the
   original does not have (rounded-type memo, growable arrays where Pascal has
   fixed ones) diverge silently. Mark them, and turn them off when in doubt.

## Compact Instructions
Use `/compact` manually at ~65% context fill — don't wait for auto-compaction.
When compacting, preserve the handoff strictly in this format:

```
Goal: one line describing the current task.
Changed files: path -> what changed.
Decisions: options rejected and why.
Current failure: command run, full error summary, hypothesis.
Verification: commands already run and their results.
Next step: one next command or one next file to inspect.
```

Remove: style chatter, failed prompt drafts, generic reasoning.

## When to /clear
If the same correction fails twice, context is poisoned — `/compact` preserves the failed approaches too.
Try `/council` first; if that doesn't unblock, use `/clear` with a rewritten prompt.
The rewritten prompt must add verification: a test command, expected output, or validator to run after the fix.
Verification beats explanation.
