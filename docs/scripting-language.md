# The Russell Scripting Language

Russell includes an embedded scripting language used to compose and automate multi-step operations on a theorem base. Scripts are stored in files with the `.rus` extension and are run with:

```
russellj <script>.rus [arg=value ...]
```

---

## Overview

A script is a sequence of **statements**. Statements can be control flow (`if`, `while`), assignments, or **task invocations**. A task is any built-in Russell operation (read, verify, translate, prove, etc.).

Variables in scripts hold strings, numbers, arrays, or the results of tasks.

---

## Variables and assignment

```rust
x := 42;
name := "hello";
path := (x + "/" + name);
```

String interpolation uses `$variable` syntax:

```rust
afile := "set-50000";
print "Processing: $afile";
```

Variables defined in the script header via `@arg` become command-line arguments.

---

## Control flow

### If / else

```rust
if (x > 0) {
    print "positive";
} else {
    print "non-positive";
}
```

### While

```rust
i := 0;
while (i < 10) {
    print ("iteration: " + i);
    i := (i + 1);
}
```

---

## Built-in operations (tasks)

Tasks are invoked like function calls. Many accept keyword arguments. After a task runs, its result fields are accessible via `<task-name>.<field>`:

```rust
read-mm set.mm;
print ("mm read: " + read-mm.size + " files in " + time2s(read-mm.time));
```

### I/O operations

| Task | Description |
|------|-------------|
| `read` | Read all `.ru` source files for the current project |
| `read-ru` | Same as `read` |
| `read-mm <file>` | Read a Metamath `.mm` file |
| `write-ru` | Write all `.ru` source files |
| `write-mm file=<f>` | Write a Metamath `.mm` file |
| `cache-load` | Load a binary cache of the theorem base |
| `cache-save` | Save a binary cache |
| `cache-clear` | Clear the in-memory cache |
| `clear-math` | Clear the loaded mathematics |

### Verification

| Task | Description |
|------|-------------|
| `verify` | Verify all loaded Russell theorems |
| `mm-verify` | Verify a Metamath file using the built-in verifier |
| `verify-mm <file>` | Verify a `.mm` file with the external verifier |

### Translation

| Task | Description |
|------|-------------|
| `mm2ru afile=<name>` | Translate Metamath → Russell |
| `ru2mm` | Translate Russell → Metamath |
| `mm-to-ru <file>` | Core MM→RU translation step |
| `ru-to-mm` | Core RU→MM translation step |
| `mm-decompress-proofs` | Decompress compact Metamath proof format |
| `mm-compress-proofs` | Compress proofs to compact Metamath format |

### Proof search

| Task | Description |
|------|-------------|
| `reprove [target=...] [tactic="..."] [...]` | Universal reprove command — runs a tactic supplied via the DSL (default: bounded BFS). See [tactics-language.md](tactics-language.md). |
| `compress [target=...] [...]` | Per-step proof compression (replaces a step's proof with a shorter one when found). |
| `autoprove [target=...]` | Attempt fully automated proof search. |

The `target` parameter can be:
- `all` — all theorems in the project
- `all-seq` — all theorems, sequentially (no parallelism)
- `stride-N` — every Nth theorem (stratified sample)
- `file:<name>` — theorems listed in `<name>` (e.g. a benchmark JSON)
- A specific theorem name
- A source file name

Common `reprove` arguments:

| Argument | Default | Effect |
|----------|---------|--------|
| `tactic` | `""` (bounded-bfs) | Tactic DSL expression. See [tactics-language.md](tactics-language.md). |
| `max-depth`, `max-size` | `5`, `4096` | Bounds for the default tactic. |
| `time-limit` | `60s` | Per-theorem wall-clock limit. |
| `job-time-limit` | `""` | Total wall-clock cap (default: `n*time-limit/p`). |
| `fragment-depth` | `0` | If `>0`, build the fragment index — enables `fragment-replay` and `linear-guided` atoms. |
| `load-ml` | `0` | If `1`, load the ML selector — enables the `ml` atom. |
| `strict-fail` | `0` | If `1`, crash if any theorem failed to reprove (CI mode). |

The earlier `reprove-dumb`, `reprove-fragments`, `reprove-ml`, `reprove-linear`, and `reprove-oracle` commands have been removed; each is now a one-line `reprove tactic="…"` invocation (see [tactics-language.md](tactics-language.md) for the mapping).

### Optimization

| Task | Description |
|------|-------------|
| `optimize-imports-ru` | Remove redundant imports |
| `optimize_hyps` | Minimize the hypotheses of each theorem |
| `optimize_shorten` | Find shorter proofs |
| `remove-trivial-theorems` | Remove theorems that are trivial reformulations |
| `ru-remove-trivial-sources` | Remove source files with no content |

### Configuration

| Task | Description |
|------|-------------|
| `conf-set key=value ...` | Set configuration options |
| `conf-load dir=<dir>` | Load `russell.conf` from a directory |
| `conf-save dir=<dir>` | Save configuration to a directory |
| `conf-push / conf-pop` | Save/restore the current configuration stack |
| `conf-clear` | Clear non-essential configuration |

Common configuration keys:

| Key | Description |
|-----|-------------|
| `working-dir` | Working directory |
| `project` | Project name |
| `import-roots` | Colon-separated list of import root directories |
| `verbose` / `verb` | Verbosity level (0–3) |
| `time-limit` | Default time limit for proving (e.g., `10m`, `30s`) |
| `gc-after-task` | Run garbage collection after each task (`0` or `1`) |

### Statistics and diagnostics

| Task | Description |
|------|-------------|
| `stats-all` | Print all statistics |
| `stats-mem` | Print memory usage |
| `output_math_stats` | Print theorem base size statistics |
| `info-volume` | Print volume information |
| `run-gc` | Run garbage collection |

---

## Script metadata (doc comments)

Scripts can include structured documentation in their leading comment:

```rust
/**
    @help { Translates $afile.mm to Russell and back }
    @defarg afile
    @category translate

    @arg afile {
        @defval set-50000
        input metamath file without .mm extension
    }
*/
```

- `@help` — brief description shown in `russellj --help`
- `@defarg` — the default positional argument
- `@arg <name>` — argument documentation
- `@defval` — default value for the argument
- `@category` — groups the script in help output

---

## Built-in functions

Scripts have access to built-in functions usable in expressions:

| Function | Description |
|----------|-------------|
| `time()` | Current time as a number |
| `time2s(t)` | Format a time duration as a human-readable string |
| `cwd()` | Current working directory |
| `startsWith(s, prefix)` | String prefix test |
| `println(s)` | Print a line |
| `a2i(s)` | Parse string to integer |
| `i2s(n)` | Convert integer to string |

---

## Lambda expressions

Scripts support anonymous functions (lambdas):

```rust
info_total := \size, time -> println(("mm read: " + size + " files in " + time));
info_source := \src, time -> println(("\tmm read: " + src + " file in " + time));

read-mm $afile.mm info-total=info_total info-source=info_source;
```

---

## Complete example: mm2ru2mm roundtrip

This script translates a Metamath file to Russell and back, verifying correctness:

```rust
/**
    @help { Testing of a there-and-back translation from Metamath to Russell and back }
    @defarg afile
    @arg afile { input metamath file without .mm extension @defval set }
*/

wd := conf.workingDir;
conf-push;
conf-set working-dir=$wd project=$afile;
conf-set gc-after-task=1;

mm2ru afile=$afile;

conf-clear;
clear-math;
cache-clear;
conf-set working-dir=$wd;

ru2mm afile=$afile;

conf-pop;
conf-clear;
clear-math;
cache-clear;
clear-ids;
run-gc;
```

## Complete example: optimization loop

```rust
/**
    @help { Optimizes all math sources. }
    @arg time_limit { Global time limit, sec. @defval 900s }
    @arg iter_limit { Max iterations. @defval 10 }
*/
read;
optimized := 1;
iteration := 1;

while ((optimized > 0) && (iteration <= a2i(iter_limit))) {
    optimize_hyps;
    optimize_shorten time_limit=$time_limit;
    remove-trivial-theorems;
    verify;
    stats-math-1;
    steps_after := stats-math-1.steps;
    optimized := (steps_before - steps_after);
    steps_before := steps_after;
    iteration := (1 + iteration);
}

if (optimized > 0) {
    write-ru;
}
```
