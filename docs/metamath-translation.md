# Metamath ↔ Russell Translation

Russell can translate theorem bases between the [Metamath](https://us.metamath.org/) format (`.mm`) and the Russell format (`.ru`). The translation is designed to be round-trip faithful: translating from Metamath to Russell and back produces a Metamath file that is verifiable by the standard Metamath verifier.

---

## Overview

Metamath is a minimal formal system: everything (types, rules, axioms, theorems, proofs) is encoded as labeled statement sequences using only `$c`, `$v`, `$f`, `$e`, `$a`, `$p`, `$d` declarations. Russell provides a higher-level syntax that makes the structure explicit.

The translation maps:

| Metamath | Russell |
|----------|---------|
| `$c` declarations | `constant` declarations |
| `$f` (floating hypothesis) + type | `type`, `rule` |
| `$e` (essential hypothesis) | `hyp` in `axiom`/`theorem` |
| `$a` | `axiom` |
| `$p` | `theorem` with proof |
| `$d` (disjoint variable restriction) | `disjointed(...)` clause |
| Compressed/normal proof | Linear proof steps |
| `$.` (block scoping) | Source file structure |

---

## Translating Metamath → Russell

### Entry point

The translation is invoked via the `mm2ru` script:

```bash
russellj mm2ru afile=set-50000
```

This reads `set-50000.mm`, creates the directory `set-50000/`, and writes the Russell source tree there.

### Pipeline

The translation runs in the following stages:

1. **Read MM** — Parse the `.mm` file into an internal Metamath AST (`read-mm`)
2. **Decompress proofs** — Convert compressed Metamath proof notation to explicit step sequences (`mm-decompress-proofs`)
3. **Verify MM** — Optionally verify the input using the built-in Metamath verifier (`mm-verify`)
4. **Split source** — Partition the monolithic `.mm` file into logical source files organized by Metamath comment markers (`mm-split-source`)
5. **HTML → Markdown** — Convert inline HTML in Metamath comments to Markdown (`mm-html2md`)
6. **Translate** — Convert each Metamath source to Russell (`mm-to-ru`)
7. **Remove trivial sources** — Drop empty or import-only source files
8. **Optimize imports** — Remove redundant imports (run twice for fixpoint)
9. **Verify** — Verify the resulting Russell theorem base
10. **Write** — Write the `.ru` source files

### Source structure determination

The Metamath format is a single flat file. Russell organizes declarations into a source tree. The splitter (`mm-split-source`) uses Metamath comment markers (section dividers like `#*#*#*#*#`) to infer the logical structure and produces a hierarchy of `.ru` files that matches the organization of the original Metamath file.

### Type inference

Metamath has no explicit type declarations — types are encoded as `$f` "floating hypotheses" that bind variables to syntactic categories. The translator:

1. Collects all `$c` declarations as `constant` declarations
2. Identifies `$f` patterns like `wff ph` as variable declarations of type `wff`
3. Infers `type` and `rule` declarations from the `$a` rules that define the grammar

### Assertion-type mapping

The translator needs to know which Metamath assertion type (`|-`, `wff`, etc.) maps to which Russell type. For standard set theory this is configured as:

```json
{"|-": ["wff"]}
```

For matching logic theories with multiple assertion types:

```json
{
    "|-": ["#Pattern"],
    "#Positive": ["#Pattern", "#Pattern"],
    "#Substitution": ["#Pattern", "#Pattern", "#Pattern", "#Variable"]
}
```

This is set via `conf-set mm-stmts='...'` before translation.

---

## Translating Russell → Metamath

### Entry point

```bash
russellj ru2mm afile=set-50000
```

This reads the Russell source tree in `set-50000/` and writes `set-50000_root.mm`.

### Pipeline

1. **Read RU** — Load all `.ru` source files
2. **Comments → ASCII** — Convert Unicode/Markdown back to ASCII-compatible form for Metamath comments (`ru-comments2ascii`)
3. **Translate** — Convert Russell declarations to Metamath statements (`ru-to-mm`)
4. **Extract vars** — Generate `$v` declarations from the inferred free variables
5. **Compress proofs** — Re-compress proof steps into the compact Metamath format (`mm-compress-proofs`)
6. **Write MM** — Write the monolithic `.mm` file (`write-mm`)
7. **Verify** — Verify the output with the external Metamath verifier (`verify-mm`)

### Proof linearization

Russell proofs are stored as trees (the proof tree). For Metamath output they must be linearized into a flat sequence of steps. Russell uses a topological sort of the proof DAG, deduplicating shared subproofs.

### Proof compression

Metamath supports a compressed proof format that represents each step as a reference to the assertion label followed by a compact encoding of which earlier steps were used. Russell implements the standard Metamath compression algorithm to produce compact proofs.

---

## Round-trip properties

The round-trip `MM → RU → MM` is designed to produce a Metamath file that:

1. Passes the standard Metamath verifier
2. Proves exactly the same set of theorems
3. Uses the same axiom base

The translation is not guaranteed to produce an identical `.mm` file (whitespace, comment formatting, and proof ordering may differ), but the mathematical content is preserved.

---

## Running the roundtrip

```bash
# Get a Metamath file
wget https://us.metamath.org/downloads/set.mm

# Take a manageable fragment
head -n 100000 set.mm > set-100000.mm
# (make sure the last theorem is complete — trim at a $. boundary)

# Translate and verify
russellj mm2ru2mm afile=set-100000
```

The script produces diagnostic output including file counts and timing for each stage. A successful run ends with a verification pass from the external Metamath verifier.

---

## Configuration

Key configuration options for translation:

| Option | Description |
|--------|-------------|
| `working-dir` | Directory where the project resides |
| `project` | Project name (base filename without extension) |
| `import-roots` | Directories to search for imported `.ru` files |
| `ru-root` | Root directory for `.ru` output |
| `mm-decompress-proofs` | `0` to skip decompression (for already-expanded proofs) |
| `mm-stmts` | JSON mapping assertion types to Russell types |
| `die-at-verify-failure` | Abort on first verification error |
| `gc-after-task` | Run GC after each translation stage |
