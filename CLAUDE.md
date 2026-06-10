# Russell Flow

## Stack
- Language: Flow9 (functional, statically typed) → compiled to `src/russell.jar`
- Math formats: Russell `.ru` (native) and Metamath `.mm`
- Grammar: Lingo PEG files (`.lingo`), compiled by `flowc1`
- Scripting: `.rus` scripts compose built-in operations (read, verify, prove, translate, optimize)

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
