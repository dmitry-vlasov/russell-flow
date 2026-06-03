# The Russell Scripting Language

Russell includes an embedded scripting language used to compose and automate multi-step operations on a theorem base. Scripts are stored in files with the `.rus` extension under `scripts/`, organized into subdirectories, and are run by name (the `.rus` extension is optional):

```
russellj <script> [arg=value ...]
```

Scripts live in a hierarchy and are invoked by their **slash path** (which maps to the file under `scripts/`):

```
russellj translate/mm2ru2mm afile=set-100000   # -> scripts/translate/mm2ru2mm.rus
russellj test/smoke afile=set-3000             # -> scripts/test/smoke.rus
russellj reprove/oracle afile=set-50000        # -> scripts/reprove/oracle.rus
```

The current groups are: `translate/`, `reprove/`, `benchmark/`, `eval/`, `learn/`, `refactor/`, `info/`, `test/`. Run `russellj help scripts` for the full list. Scripts may call other scripts by the same slash path.

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

## Types and operators

Values are one of: **int**, **double**, **bool**, **string**, **array** `[a, b, …]`, **map** `{k = v, …}`, **struct** `Name(a, b, …)`, **lambda** `\x -> e`, or **undef**.

| Group | Operators | Notes |
|-------|-----------|-------|
| Arithmetic | `+` `-` `*` `/` `%` | int and double; `-` is also unary negation |
| Overloaded `+` | `+` | string concatenation (coerces operands to string), array concatenation, map merge |
| Comparison | `<` `<=` `>` `>=` | int, double, string |
| Equality | `==` `!=` | int, double, bool, string, array, map |
| Boolean | `&&` `\|\|` `!` | `!` is unary |
| Conversion | `a2i(x)` `a2d(x)` `a2s(x)` `a2b(x)` | to int / double / string / bool, from any scalar |

Access and construction:

```rust
arr[2]            // array index
m["key"]   m.key  // map lookup (index or field)
p.first           // struct field
(c ? a : b)       // conditional expression
let x = e; body   // local binding (an expression)
{ e1; e2; e3; }   // sequence — evaluates to the last expression
eval(quote(e))    // defer / re-evaluate an expression
```

> **Infix grouping gotcha.** An infix expression `( … )` applies the **first**
> operator to *all* operands, so a single group must use one operator:
> `(1 + 2 + 3)` is fine, but `(1 + 2 * 3)` is **not** `7` — parenthesize mixed
> operators explicitly: `(1 + (2 * 3))`.

These are exhaustively exercised by `test/script-lang` (see [scripts/test/script-lang.rus](../scripts/test/script-lang.rus)).

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

### Command output convention

Commands are **silent on success**. They do not print results or progress
themselves; instead:

- **Results** go into the state under the command's name (`verify.success`,
  `read-mm.size`, `mm-to-ru.time`, `reprove.reproved-count`, …). Scripts read
  these and print whatever they want.
- **Errors** are the *only* thing a command emits directly, via `out.error`
  (a failed verify, a missing target, a write failure, a tactic-DSL error).
- **Progress / streaming info** is delivered through optional lambda callbacks
  the script passes in. A command reads each callback with
  `ruTaskInfoFn2(env, task.args, "<name>")` (in [src/comm.flow](../src/comm.flow)),
  which returns the supplied `(string, string) -> void` lambda or `nop2` when
  absent. The command then calls it **unconditionally** on each event; the
  script chooses the granularity by which callbacks it provides. Commands with
  `info-source` (per file/source) and `info-total` (at completion) callbacks:
  `read-ru`, `read-mm`, `write-ru`, `mm-verify`, `mm-to-ru`, `ru-to-mm`. Example:
  ```
  src_cb := \m, t -> println(("  translated: '" + m + "' in " + t));
  ru-to-mm info-source=src_cb;
  ```
  With no lambda the command is silent. (Note: a lambda body that is a call
  must parenthesise its argument expression — `println((a + b))`, not
  `println(a + b)` — see Gotchas.)
- **Output-intent commands** store their data in state and are fronted by a
  thin wrapper under `scripts/info/` that renders it. There are two shapes:
  - **Structured data** — a command that produces a labelled, fixed-field
    report stores a *mapping* of field → value (numbers/strings), and the
    wrapper assembles the human text. Preferred for tabular/numeric summaries.
    Examples: `stats-math`/`stats-math-1` (`.sources`, `.steps`, …), `info-volume`
    (`.ru_sources`, `.ru_sources_pct`, `.total`, …; wrapper `info/volume`),
    `info-axiomatics` (`.groups`, `.distrib`, `.max_axioms`, …; wrapper
    `info/axiomatics`).
  - **Text** — a command whose output is free-form *content* (source code,
    locations, variable-length listings) that scripts cannot reassemble field
    by field stores it as `<command>.text`. Examples: `conf-show`, `stats-all`,
    `cache-info`, and the lookup wrappers `info/find`, `info/symbol`,
    `info/source`, `info/show`, `info/outline`, `info/math`, `info/vars`,
    `info/latex`, `info/order-sources`. Other wrappers: `info/verify`,
    `info/stats`, `info/conf`, `info/version`, `info/mem-stats`, `info/cache`.

  Note the script language can only evaluate string concatenation (`+`) in
  expressions — the arithmetic operators `*`, `-`, `/` break statement parsing
  — so any derived numbers (e.g. percentages) must be computed in the command
  and stored as fields.

So a one-off `russellj verify afile=set` prints nothing on success; use
`russellj info/verify afile=set` to see a summary. This keeps machine-readable
state separate from human-readable formatting (which lives in scripts).

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
| `mm-to-ru <file>` | Core MM→RU translation step (command) |
| `ru-to-mm` | Core RU→MM translation step (command) |
| `translate/mm2ru afile=<name>` | Script: full Metamath → Russell translation (wraps `mm-to-ru`) |
| `translate/ru2mm afile=<name>` | Script: full Russell → Metamath translation (wraps `ru-to-mm`) |
| `translate/mm2ru2mm afile=<name>` | Script: MM → RU → MM round-trip with re-verification |
| `mm-decompress-proofs` | Decompress compact Metamath proof format |
| `mm-compress-proofs` | Compress proofs to compact Metamath format |

### Proof search

| Task | Description |
|------|-------------|
| `reprove [target=...] [tactic="..."] [...]` | Universal reprove command — runs a tactic supplied via the DSL (default: bounded BFS). See [tactics-language.md](tactics-language.md). |
| `compress [target=...] [...]` | Per-step proof compression (replaces a step's proof with a shorter one when found). |

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
| `load-ml` | `0` | If `1`, load the ML selector — enables the `ml` atom. |
| `min-index-rule-nodes` | `1` | Min rule-node count for step-index entries (only used when the tactic mentions `spr`/`linear-guided`). |
| `strict-fail` | `0` | If `1`, crash if any theorem failed to reprove (CI mode). |

The earlier `reprove-dumb`, `reprove-fragments`, `reprove-ml`, `reprove-linear`, and `reprove-oracle` commands have been removed; each is now a one-line `reprove tactic="…"` invocation (see [tactics-language.md](tactics-language.md) for the mapping).

### Optimization

| Task | Description |
|------|-------------|
| `optimize-imports-ru` | Remove redundant imports (command) |
| `remove-trivial-theorems` | Remove theorems that are trivial reformulations (command) |
| `ru-remove-trivial-sources` | Remove source files with no content (command) |
| `shorten-proofs` | Find shorter proofs (command) |
| `refactor/optimize-hyps` | Script: iterate remove-duplicate/unused steps + hyps to a fixpoint |
| `refactor/optimize-shorten` | Script: shorten proofs globally and write the result |
| `refactor/optimize` | Script: full optimization loop (hyps + shorten + remove-trivial) |

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
| `info-volume` | Print volume information |
| `run-gc` | Run garbage collection |
| `info/math-stats` | Script: print theorem-base size statistics |
| `info/mem-stats` | Script: print memory statistics |

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

### Calling Flow9 library functions

Beyond the built-ins above, scripts can call **Flow9 runtime library functions
directly**, passing script lambdas as the higher-order arguments:

```rust
squares  := map([1, 2, 3], \x -> (x * x));            // [1, 4, 9]
total    := fold([1, 2, 3, 4], 0, \acc, x -> (acc + x)); // 10
evens    := filter(xs, \x -> ((x % 2) == 0));
hit      := find(xs, \x -> (x > 2));                   // a Maybe — use hit.value
iter(xs, \x -> println(x));                           // (inside an expression)
```

Verified working with script lambdas: `map`, `mapi`, `fold`, `foldi`, `filter`,
`filtermap`, `exists`, `forall`, `find`, `iter`, `iteri`, plus non-higher-order
helpers like `length`, `concat`, `enumFromTo`, `arrayPush`, `strlen`. (Not every
runtime symbol is exposed — an unavailable one yields `undefined var`.)

> **`print` vs `println`.** `print` is a *statement* keyword and cannot appear
> inside an expression (e.g. a lambda body); use the `println(...)` host function
> there. Also note a bare call in statement position is parsed as a task — call
> void functions like `iter` from inside an expression (e.g. an assignment).

---

## Lambda expressions

Scripts support anonymous functions (lambdas):

```rust
info_total := \size, time -> println(("mm read: " + size + " files in " + time));
info_source := \src, time -> println(("\tmm read: " + src + " file in " + time));

read-mm $afile.mm info-total=info_total info-source=info_source;
```

Lambdas are real **closures**: they capture the variables in scope at the point
of definition (including enclosing lambda parameters), so currying, nesting, and
higher-order use all work:

```rust
curry_add := \a -> \b -> (a + b);
add5      := curry_add(5);
println(add5(10));                 // 15

compose := \f, g -> (\x -> f(g(x)));
twice   := \g, v -> g(g(v));
```

A closure captures a **snapshot** of the scope at definition time. A lambda can
therefore call any variable/lambda already defined above it, but **not itself**
— a self-recursive lambda (`fact := \n -> … fact(n - 1) …`) sees no `fact` yet
and fails with `undefined var`. Use a `while` loop for iteration instead.

The full lambda surface (currying, multi-level nested closures, capture inside
call/index arguments, lambdas in arrays/maps, host higher-order calls) is
exercised by `test/script-lang`.

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

translate/mm2ru afile=$afile;

conf-clear;
clear-math;
cache-clear;
conf-set working-dir=$wd;

translate/ru2mm afile=$afile;

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
    refactor/optimize-hyps;
    refactor/optimize-shorten time_limit=$time_limit;
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
