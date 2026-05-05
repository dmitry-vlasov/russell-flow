---
name: proof-search-debug
description: Debug reprove-oracle failures — silent failures, wrong proofs, timeouts, backward chaining issues
---

# Proof Search Debug Playbook

## Key parameters
| Parameter | Meaning | Default |
|-----------|---------|---------|
| `target` | theorem name, `all`, or `file:path` | `all` |
| `max-true-props` | max hypotheses chosen as true per step; `-1` = unlimited | `1` |
| `max-false-props` | max hypotheses chosen as false per step; `-1` = unlimited | `0` |
| `max-proofs` | max proof variants in tree; `-1` = unlimited | `-1` |
| `max-variants` | max assertion choice variants; `-1` = unlimited | `-1` |
| `proving-trace` | print step-by-step trace (`1` = on) | off |
| `time-limit` | abort after this duration (e.g. `60s`, `10m`, `no`) | — |

## Step 1 — Narrow to a single theorem
Never debug `target=all` first. Pick the failing theorem by name:
```bash
bin/russellj 'conf-load dir=./<dataset>; read-ru; verify; reprove-oracle target=<theorem-name> max-true-props=-1 max-false-props=-1'
```

## Step 2 — Enable the proving trace
```bash
bin/russellj 'conf-load dir=./<dataset>; read-ru; verify;
  conf-set verb=2 proving-trace=1 time-limit=60s;
  reprove-oracle target=<theorem> max-true-props=-1 max-false-props=-1'
```
The trace prints each backward-chaining step: which assertion is tried, which hypotheses
are unified, and why a branch is pruned.

## Step 3 — Interpret the trace
Key files:
- [src/ru/prover/reprove_oracle.flow](src/ru/prover/reprove_oracle.flow) — oracle tactic: maps proof tree nodes to `queue` and `leafs`
- [src/ru/prover/tree.flow](src/ru/prover/tree.flow) — `RuProverNode`, proof variant tree structure
- [src/ru/prover/prove.flow](src/ru/prover/prove.flow) — backward chaining loop
- [src/ru/unify/](src/ru/unify/) — unification; failed unification = branch pruned

## Step 4 — Common failure patterns
- **Silent failure (no proof found)**: the oracle exhausts `max-true-props`/`max-false-props` — relax both to `-1`
- **Wrong proof produced**: oracle mapping (`ruReproveOracleTactic`) misaligns proof tree indices — check `s.children[i]` bounds in `reprove_oracle.flow`
- **Timeout**: reduce target scope; increase `time-limit`; check if unification index is warm (`read-ru` must precede `reprove-oracle`)
- **Crash with `ruCrash`**: index mismatch between prover node children and proof step children — the assertion arity changed

## Verification
After a fix, confirm the theorem still verifies:
```bash
bin/russellj 'conf-load dir=./<dataset>; read-ru; verify'
```
