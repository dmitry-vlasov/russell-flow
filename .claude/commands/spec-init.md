# Spec Init

You are tasked with creating enriched, intent-focused specifications for a project that uses **spec-checker**. Your main job is to **read source code and write meaningful specs** — not just generate mechanical skeletons.

## Arguments

$ARGUMENTS — Optional: a source file or directory path. If omitted, process the entire project.

## Step 1: Generate skeletons (if needed)

If specs don't exist yet, run `spec-checker init .` to create mechanical skeletons. If specs already exist, skip this step.

## Step 2: Enrich each spec (THIS IS THE MAIN WORK)

Get processing order: `spec-checker toposort ./specs`

Then for EACH spec file, in dependency order:

1. **Read the source file** (from `source_path` in the spec)
2. **Read the current spec**
3. **Check if enrichment is needed**: A spec is NOT enriched if it lacks a `description:` field at module level, or if its `exposes` entries have no `description:` fields. A skeleton with just `kind: function` is NOT enriched — it MUST be rewritten. **WARNING**: A matching `source_hash` does NOT mean the spec is enriched — it only means the skeleton was generated from the current source. You must still add descriptions, layer, and forbidden_deps.
4. **Rewrite the spec** by adding all enrichment fields (see below). You MUST add `description`, `layer`, entity descriptions, and `forbidden_deps`.
5. **Update source_hash**: `sha256sum <source-file> | cut -c1-16`
6. **Write the enriched spec** back
7. **Report progress**

### What to add

**Module description** — One sentence about what this module is FOR (purpose/role), not what it contains.

**API curation** — Review `exposes`. Remove internal helpers that happen to be public. Keep only the intended public API. Add a one-line `description` to each retained entity.

**Forbidden dependencies** — Add `forbidden_deps` for clear architectural violations:
```yaml
forbidden_deps:
  - clap       # CLI concerns belong in main
  - syn        # AST parsing belongs in extractors
```

**Layer** — Set to `infrastructure` (I/O, persistence), `domain` (core logic), `application` (orchestration), or `interface` (user-facing).

### Before/after example

**BEFORE** (mechanical skeleton — NOT acceptable as final output):
```yaml
module: cache
language: rust
source_path: src/cache.rs
source_hash: a1b2c3d4
exposes:
  CacheEntry:
    kind: type
  Cache:
    kind: type
  Cache.new:
    kind: function
  Cache.get:
    kind: function
  Cache.set:
    kind: function
  Cache.invalidate:
    kind: function
  compute_key:
    kind: function
  format_entry:
    kind: function
```

**AFTER** (enriched — THIS is what you should produce):
```yaml
module: cache
description: "Content-addressed cache with SHA256 keys and TTL-based expiration"
language: rust
source_path: src/cache.rs
source_hash: a1b2c3d4
layer: infrastructure
exposes:
  Cache:
    kind: type
    description: "Thread-safe LRU cache with configurable size limit and TTL"
  Cache.new:
    kind: function
    description: "Creates a cache with the given capacity and TTL settings"
  Cache.get:
    kind: function
    description: "Retrieves a cached value by key, returning None if expired or missing"
  Cache.set:
    kind: function
    description: "Stores a value with automatic key computation and timestamp"
  Cache.invalidate:
    kind: function
    description: "Removes all entries matching a predicate"
forbidden_deps:
  - cli        # cache is infrastructure, not user-facing
  - checker    # cache should not depend on application logic
```

Note what changed: `compute_key` and `format_entry` removed (internal helpers). `CacheEntry` removed (implementation detail). Descriptions added to every entity. Layer and forbidden_deps set.

## What NOT to do

- Do NOT leave specs as bare skeletons (just kind + name) — that's the BEFORE, not the AFTER
- Do NOT add `type_constraints` mirroring struct fields or function signatures
- Do NOT add `depends_on` or `external_deps` — derivable from source
- Do NOT add `invariants`, `requires`, `ensures`, `modifies` — those are for `/spec-refine`

## Step 3: Validate

Run `spec-checker check .` and fix any errors.

## Quality guidelines

- **Intent over structure** — specs describe what the module is FOR, not what it contains
- **Curate aggressively** — 5 well-chosen API entities beat 20 that mirror public items
- **Be specific** — "Orchestrates verification passes against specs" beats "Main checker module"
- **Use domain language** — match terminology from the code
- **Every spec must have**: description, layer, and at least one curated expose with description
- **Verify your own work**: after writing a spec, re-read it and confirm it has a `description:` field at module level AND `description:` on each exposed entity. If not, you haven't finished that spec.
