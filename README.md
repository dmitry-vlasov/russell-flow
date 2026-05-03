# Russell

Russell is a formal mathematics system: a language for writing machine-verified proofs, a toolchain for building and checking theorem bases, and a bridge to the [Metamath](https://us.metamath.org/) ecosystem. The name honours [Bertrand Russell](https://en.wikipedia.org/wiki/Bertrand_Russell), the British logician and philosopher who co-authored *Principia Mathematica* and laid foundational groundwork for mathematical logic.

The implementation is written in [Flow9](https://github.com/area9innovation/flow9) and compiles to a Java JAR.

---

## Documentation

| Document | Description |
|----------|-------------|
| [Russell Language](docs/russell-language.md) | Language reference: constants, types, rules, axioms, definitions, theorems, proofs |
| [Scripting Language](docs/scripting-language.md) | Built-in scripting language for automating operations on theorem bases |
| [Metamath Translation](docs/metamath-translation.md) | Translating between Metamath (`.mm`) and Russell (`.ru`) formats |
| [Proof Search Algorithm](docs/proof-search-algorithm.md) | Backward-chaining proof search, Proof Variant Trees, oracle-guided reproof |
| [Unification Algorithms](docs/unification-algorithms.md) | Trie-indexed matching, multi-index unification, substitution composition |
| [Algorithm Paper](docs/proof_search_algo.pdf) | Theoretical basis: "Proof Search Algorithm in Pure Logical Framework" |

---

## Prerequisites

- **Java Runtime (JRE)** — to run the pre-built JAR
- **JDK 11+** — to compile from source
- **Flow9** — to recompile the implementation from Flow9 source

---

## Quick start

Add the `bin/` directory to your `PATH`:

```bash
export PATH="$PATH:/path/to/russell-flow/bin"
```

The main executable is `russellj` (Java-backed). A native binary `russell` is also available if compiled.

---

## Running the Metamath roundtrip

The canonical demo: translate a fragment of the Metamath set theory library to Russell and back, then verify with the Metamath verifier.

```bash
# Download set.mm (the main Metamath theorem base)
wget https://us.metamath.org/downloads/set.mm

# Take a manageable fragment (~100k lines)
head -n 100000 set.mm > set-100000.mm
# Make sure the fragment ends at a complete theorem boundary (ends with $.)

cd /directory/containing/set-100000.mm

# Translate MM → RU → MM and verify
russellj mm2ru2mm afile=set-100000
```

---

## Building from source

### Java (recommended)

```bash
./build_java.sh
```

This runs `flowc1 jar=1 russell` in the `src/` directory and produces `src/russell.jar`. Copy or link it to `bin/`.

### Native binary (optional)

```bash
./build_binary.sh
```

Uses `clang++` with mimalloc; requires Flow9's C++ backend.

---

## VSCode extension

A VSCode extension for Russell syntax highlighting and language server features is in `resources/vscode/`. Install the `.vsix` file directly in VSCode:

```
Extensions → ··· → Install from VSIX → resources/russell.vsix
```

---

## Common commands

```bash
# Read and verify a Russell theorem base
russellj verify

# Re-prove all theorems using oracle guidance
russellj reprove-with-oracle afile=set-50000

# Optimize a theorem base (shorten proofs, minimize hypotheses)
russellj optimize

# Run an arbitrary script
russellj scripts/mm2ru.rus afile=set-100000
```

---

## Project layout

```
bin/            Executable scripts (russellj, russell_lsp, etc.)
docs/           Documentation
resources/      VSCode extension
scripts/        .rus automation scripts (mm2ru, optimize, reprove, etc.)
src/            Flow9 source code
  ru/           Russell language implementation
  mm/           Metamath parser and translator
  script/       Scripting language implementation
  base/         Utilities
platforms/      Java platform runtime files
```
