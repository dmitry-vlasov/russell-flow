---
name: mm-roundtrip-debug
description: Debug failures in the MM↔RU translation pipeline (mm2ru, ru2mm, mm2ru2mm roundtrip)
---

# MM↔RU Roundtrip Debug Playbook

## Pipeline stages
```
.mm file → mm2ru (parse + translate) → RuMath → verify → ru2mm → .mm file
```
Each stage can fail independently. Isolate the stage before touching code.

## Step 1 — Isolate the stage
```bash
# Does mm2ru fail?
bin/russellj mm2ru afile=<dataset>

# Does ru verification fail after translation?
bin/russellj 'mm2ru afile=<dataset>; verify'

# Does ru2mm fail?
bin/russellj ru2mm afile=<dataset>

# Full roundtrip
bin/russellj mm2ru2mm set
```

## Step 2 — Increase verbosity
Add `verb=2` or `verb=3` to see per-file and per-assertion timing and translation details:
```bash
bin/russellj 'conf-set verb=3; mm2ru afile=<dataset>'
```
`verb=3` prints the `MmRuSyntax` (symbol/type mapping) — essential for coercion and symbol issues.

## Step 3 — Locate the failing assertion
The translation processes assertions concurrently (`ruConcurrent` in `mm2ru.flow`).
If an assertion fails silently, narrow to a smaller dataset first (`set-3000`, `set-5000`),
then binary-search by file.

Key files for assertion translation:
- [src/mm/mm2ru.flow](src/mm/mm2ru.flow) — `mmAssertion2ru`, `mm2RuParseAssrtions`
- [src/mm/symbols.flow](src/mm/symbols.flow) — symbol and type mapping (`mmSources2Syntax`)
- [src/mm/parse/](src/mm/parse/) — Metamath parser

## Step 4 — Common failure patterns
- **Symbol not found**: check `mmSources2Syntax` — the `MmRuSyntax` maps MM symbols to RU types
- **Coercion mismatch**: coercion rules are in `MmRuSyntax.coercions`; `verb=3` prints them
- **Substitution tree mismatch**: `mmAssertion2ru` builds `RuParseDecl` — compare with the MM `$p` statement
- **Silent verify failure after roundtrip**: run `verify` explicitly after `mm2ru` to confirm the translated math is valid before blaming `ru2mm`

## Verification command
After any fix, always run the full roundtrip on the standard dataset:
```bash
bin/russellj mm2ru2mm set
```
