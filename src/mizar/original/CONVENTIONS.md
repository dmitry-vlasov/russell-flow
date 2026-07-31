# Pascal → Flow9 transcription conventions

This directory is a 1:1 transcription of the original Mizar verifier
(`~/dev/system`, GPL) into Flow9. One `.flow` file per Pascal unit, same name,
same procedure names (prefixed `miz` only where Flow9 requires a unique global
name). Deviations from the original are marked `// DEVIATION:` in code.
Every file states the Pascal source it transcribes at the top.

## Idiom mapping (fixed at M0 — do not improvise later)

| Pascal | Flow9 |
|---|---|
| unit-level `var` globals | fields of ONE state struct per subsystem, `mutable`, created per article. No true globals — this keeps article-level parallelism possible. |
| `object` with fields | struct with `mutable` fields, passed explicitly (the `self` argument comes first) |
| virtual method overridden in descendants | a function field in the struct (assigned at construction), or a `kind` switch when the override set is closed |
| `PFoo = ^TFoo`, `new`/`dispose` | the struct value itself (heap in JVM); `dispose` = drop the reference (no-op) |
| variant record / tagged pointer cast (`UnivFrmPtr(f)^`) | union + `switch`; the discriminant (`FrmSort` char) becomes the struct name |
| growable `MCollection` | `Vector<?>` (`ds/vector`, Java-native, O(1) push) |
| fixed `array[1..Max…]` | `Vector` pre-sized, or plain arrays; keep the `limits.flow` constant and the explicit bound CHECK (see overflow below) |
| Pascal 1-based string indexing | keep indices 1-based in transcribed code; use `mizCharAt1(s, i)` = code of char at 1-based i. Never silently shift to 0-based — off-by-one drift is the main fidelity risk. |
| `set of` small enum | bit mask int, or `[bool]`; per-case choice, documented at the site |
| `goto label` out of loops | rewrite as helper function + early return, marked `// goto:` |
| `RunError` via `RunTimeError(code)` / `Mizassert` | `fail("RTE " + code)` — aborts like the original. Caught at the per-article boundary later if needed. |
| graceful overflow (`ListError(coOverflow)` → `nOverflow`/`LatOvfl`) | explicit flag on the collection's owner struct, checked where the original checks it. NOT `fail`. |
| `text` file line I/O | read whole file (`getFileContent`), split to lines, scan by index |
| `write`/console drawing (`mconsole`, `monitor`) | dropped; `println` on verbose only |

## State threading

The original keeps per-article state in unit-level globals. Here each unit's
globals become one struct (e.g. `MizErrHan`, `MizXmlParser`), and structs
aggregate into `MizArticle` as units get ported. One `MizArticle` per article,
no shared mutable state between articles.

## Errors

`errhan.pas`: `Error(pos, nr)` records to the `.err`-equivalent list and counts
`errorNbr`; error NUMBERS are part of observed behavior (checker: 1 trivial,
4 unsolved, 8/9/11/14 overflows) and must match the original exactly.
`RunTimeError` = internal can't-happen, aborts (`fail`).

## Testing

- Component tests: `test/test_<unit>.flow`, plain `main()` printing
  `PASS <name>` / `FAIL <name>: <detail>`, exit code 1 on any failure.
- Integration: `test/test_all.flow` runs every component test's entry function.
- Data: tests read the WORKING COPY `$RUSSELL_MATH/MML-test` (never the pristine
  `$RUSSELL_MATH/MML`, which is the untouched reference — restore the copy from
  it if a file gets spoiled). `mml/` has every Mizar artifact per article:
  `.xml`, `.eno`, `.ecl`, `.epr`, `.eid`, `.erd`, `.ere`, `.dfs`, `.frm`,
  `.err`, …. Mizar itself is the oracle: diff our verdicts against the shipped
  `.err` files.
- If a whole-MML sweep is too slow, test with a stride (every N-th article of
  `mml.lar`), as the Russell prover tests do.

## Build

`./build.sh` in this directory compiles `mizar.flow` (the entry point) to
`mizar.jar` via `flowc1 jar=1`. Wrapper: `bin/mizarj` (mirrors `bin/russellj`).
