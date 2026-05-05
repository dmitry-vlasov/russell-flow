---
name: benchmark
description: Run and interpret performance benchmarks across dataset sizes for proof compression and reprove
---

# Benchmark Playbook

## Dataset sizes (in ~/dev/math)
| Dataset | Theorems (approx) | Use for |
|---------|-------------------|---------|
| `set-3000` | ~3k | quick smoke test |
| `set-5000` | ~5k | fast iteration |
| `set-10000` | ~10k | representative sample |
| `set-30000` | ~30k | medium load |
| `set-50000` | ~50k | standard benchmark |
| `set-100000` | ~100k | full stress test |

Start with `set-5000` or `set-10000`. Only move to `set-50000`+ when the behaviour is confirmed.

## Proof compression benchmark
Uses a pre-selected subset of theorems from `benchmark.json` in the dataset directory:
```bash
bin/russellj benchmark-compress.rus afile=set-50000
# optional overrides:
bin/russellj benchmark-compress.rus afile=set-50000 step_time_limit=5.0 max_depth=3 max_size=2048
```
Reads `benchmark.json` → runs `reprove-compress` on each theorem → prints per-theorem stats.

## Reprove-oracle benchmark
```bash
bin/russellj 'conf-load dir=./set-50000; conf-set gc-after-task=1 verb=1 time-limit=5m;
  read-ru; verify; reprove-oracle target=all max-true-props=-1 max-false-props=-1'
```

## Memory snapshot
```bash
bin/russellj output_mem_stats.rus          # prints used/free/total heap
bin/russellj 'conf-load dir=./set-50000; read-ru; stats-mem'
```
Output format: `memory used: Xmb, free: Ymb, total: Zmb`

## Reading the stats output
After `reprove-compress` or `reprove-oracle`, run:
```bash
# prints table with per-theorem compression results
bin/russellj 'stats-all table=reprove-compress'
```
Columns: theorem name, original steps, compressed steps, ratio, time.

## Establishing a baseline
Before any algorithmic change, record the baseline:
```bash
bin/russellj benchmark-compress.rus afile=set-10000 2>&1 | tee baseline.txt
```
After the change, diff against it:
```bash
bin/russellj benchmark-compress.rus afile=set-10000 2>&1 | diff baseline.txt -
```

## Key scripts
- [scripts/benchmark-compress.rus](scripts/benchmark-compress.rus) — compression benchmark
- [scripts/run-reprove-compress.rus](scripts/run-reprove-compress.rus) — full reprove-compress run
- [scripts/run-benchmark-select.rus](scripts/run-benchmark-select.rus) — select theorems for benchmark.json
- [scripts/output_mem_stats.rus](scripts/output_mem_stats.rus) — memory snapshot
