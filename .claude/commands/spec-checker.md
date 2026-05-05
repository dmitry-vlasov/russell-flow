# Spec-Checker: AI Guidance Map

You are working in a project that uses **spec-checker** — a structural and behavioral specification system. The `specs/` directory contains `.spec.yaml` files that describe every module's purpose, contracts, dependencies, and invariants.

**Use specs as your primary reasoning layer.** Before reading source code, consult specs first. They are a compressed semantic model of the codebase — much cheaper to read and reason about than raw source.

## Arguments

$ARGUMENTS — A mode followed by optional arguments. Modes: `help`, `orient`, `ask <question>`, `plan <feature>`, `guard`, `impact <module>`, `check`, `bootstrap`. If omitted, defaults to `orient`.

## Layered resolution principle

Always resolve information in this order, stopping as soon as you have enough:

1. **Spec descriptions + dependency graph** (~1500 tokens for a whole project) — module purposes, who depends on whom
2. **Contracts** — requires/ensures/modifies/invariants of specific entities
3. **Source code** — only when specs are insufficient for the task at hand

This mirrors how an architect navigates a large codebase: consult the architecture first, then drill into specifics.

## Modes

### help

Show available modes. Print this summary:

```
Spec-Checker AI Guidance Skill — available modes:

  /spec-checker help                 Show this help
  /spec-checker orient               Build mental model of the codebase from specs
  /spec-checker ask <question>       Answer a question using specs first, source if needed
  /spec-checker plan <feature>       Spec-first feature development: design specs, then implement
  /spec-checker guard                Guard a refactoring against semantic drift
  /spec-checker impact <module>      Show blast radius of changes to a module
  /spec-checker check                Run spec-checker and interpret the results
  /spec-checker bootstrap            One-command setup for a new project

Principle: always consult specs before source code.
Resolution order: spec descriptions → contracts → source code.
```

### orient

Build a mental model of the codebase from specs alone. This should be your **first step** before any code task in a spec-checked project.

1. Find all spec files: `find specs/ -name '*.spec.yaml'`
2. Read every spec file (they're small — typically ~50-150 lines each)
3. Build a mental map:
   - What modules exist and what each one does (from `description`)
   - The dependency graph (from `depends_on`)
   - Architectural layers and boundaries (from `layer`, `context`, `forbidden_deps`)
   - Key invariants and contracts
4. Check for staleness: `spec-checker check .` — look for "specs may be stale" warnings
5. If there are stale specs, tell the user which modules are stale and offer to refresh them

Output a brief summary of the project architecture as you understand it from the specs.

### ask <question>

Answer a question about the codebase using specs first, falling through to source only if needed.

1. **Read relevant specs** — identify which modules are likely relevant to the question from their descriptions and dependency graph
2. **Try to answer from specs alone** — use descriptions, contracts (requires/ensures), invariants, and type constraints
3. **If specs are insufficient**, identify the specific source files to read (guided by `source_path` in the spec) and read only those
4. **If specs are stale** for the relevant modules, warn the user before answering — the answer may not reflect current code

Always cite which spec(s) you used to form your answer. If you had to fall through to source, note what was missing from the specs.

### plan <feature-description>

Spec-first feature development: design the feature in specs before writing any code.

1. **Orient** — read all specs to understand current architecture
2. **Identify affected modules** — which existing modules need changes? Any new modules needed?
3. **Draft spec changes** — for each affected module:
   - New entities in `exposes` with their contracts (requires/ensures/modifies)
   - Updated invariants if the feature introduces new guarantees
   - New dependencies if needed
   - Updated protocol transitions if the feature changes call sequences
4. **Draft new specs** — for any new modules, write complete spec files
5. **Check consistency** — verify that:
   - Dependencies don't violate layer/context boundaries
   - No forbidden deps are introduced
   - Event emit/subscribe pairs are matched
   - New contracts are compatible with existing callers
6. **Present the plan** — show the user the proposed spec changes and get approval before implementation
7. **After approval** — implement the code to satisfy the new specs, then run `spec-checker check .` to verify

### guard

Guard a refactoring against semantic drift. Run this before and after refactoring.

**Before refactoring:**
1. Run `spec-checker check .` to establish a baseline — all checks should pass
2. Read specs of modules you plan to refactor
3. Snapshot the key contracts: list all `ensures`, `requires`, `invariants` for affected modules
4. Present the snapshot to the user: "These are the contracts I'll preserve"

**After refactoring:**
1. Run `spec-checker check .` — compare results to baseline
2. For any new failures, determine if the refactoring broke a contract or if the spec needs updating
3. If specs need updating (intentional semantic change), update them and explain why
4. Update `source_hash` for any modified source files by running:
   ```bash
   sha256sum <source-file> | cut -c1-16
   ```
   Then set `source_hash: <hash>` in the corresponding spec YAML.
5. Run `spec-checker check .` again to confirm clean

### impact <module-or-entity>

Trace the dependency graph to show the blast radius of changes to a module or entity.

1. Read all spec files
2. Build the dependency graph from `depends_on` fields
3. Find all **direct dependents** — modules whose `depends_on` includes the target
4. Find all **transitive dependents** — recursively follow the graph
5. For each dependent, show:
   - Which contracts reference the target (in requires/ensures)
   - Which type constraints reference types from the target
   - Whether the dependent subscribes to events the target emits
6. Summarize: "Changing X directly affects N modules, transitively affects M modules"
7. Highlight high-risk dependents: those with strict contracts that reference the target

### check

Run spec-checker and interpret the results.

1. Run `spec-checker check .`
2. Parse the output, grouping by module
3. For each issue:
   - **Structural errors** (missing functions, forbidden deps) — these are definite problems
   - **Staleness warnings** — source changed since specs were written
   - **Type formula failures** — type constraint violations
4. For staleness warnings, offer to run `/fill-behavioral-specs` for the stale modules
5. Present a summary: N modules checked, X errors, Y warnings, Z stale

### bootstrap

Set up spec-checker for a new project. This is a one-time setup.

1. Check if `spec-checker` is installed: `which spec-checker || cargo install --path .`
2. Check if specs already exist: `ls specs/` — if yes, ask if user wants to reinitialize
3. Run `spec-checker init src/` (or the appropriate source directory)
4. Count generated spec files and estimate token cost for behavioral fill:
   - For each source file referenced in specs, count bytes
   - Estimate: `total_source_bytes / 4 * 2` tokens (read source + write spec)
   - Display: "Generated N spec files. Estimated token usage for behavioral specs: ~Xk tokens."
5. Prompt: "Fill behavioral specs now? [Y/n]"
6. On confirmation, run `/fill-behavioral-specs` to populate behavioral specs for all modules
7. Run `spec-checker check .` to validate the result
8. Report: "Bootstrapping complete. N modules specified, X checks passing."

## Staleness handling

When you encounter stale specs (source_hash mismatch):

- **Single stale file**: Offer to run `/fill-behavioral-specs <source-file>` for just that file
- **Multiple stale files**: List them all and offer to run `/fill-behavioral-specs` for all mismatched files
- **During orient/ask**: Warn the user but still use the specs (with caveat). Stale specs are better than no specs — the structural parts are still valid, only behavioral parts may be outdated.

## Important principles

- **Specs are a semantic index, not documentation** — they describe what code DOES and GUARANTEES, not how it works internally
- **Trust specs, verify when needed** — if a spec says `ensures: returns sorted list`, trust it unless the spec is stale
- **Spec changes are design decisions** — changing a spec's contract is an architectural decision, not a casual edit. Always highlight spec changes to the user.
- **Layered cost** — reading all specs is ~1500 tokens. Reading one source file can be 500-2000 tokens. Always try specs first.
- **Backward compatible** — specs without `source_hash` are treated as "freshness unknown" — no warning, but also no guarantee
