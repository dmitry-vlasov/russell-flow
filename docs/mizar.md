# Mizar in Russell

Russell carries two related Mizar subsystems: a complete port of the original
Mizar verifier, and a pipeline that translates MML articles into Russell and
proves them.

## The verifier port (`src/mizar/original/`)

A 1:1 transcription of the original Mizar verifier — parser, MSM, analyzer,
checker, accommodator — from Pascal into Flow9, exact on the whole MML:

* 1497/1497 articles verified, 1,403,754 inferences accepted;
* every analyzer report byte-identical to the original's;
* one jar, one process, all intermediates in memory;
* whole MML in ~21 minutes at `jobs=8`.

```bash
# Build (from src/): one jar, nothing else
cd src && flowc1 jar=1 mizar/original/mizar.flow

# Component test suites (seconds; suites needing MML data skip when absent)
bin/mizarj test=all

# Verify one article (reads $RUSSELL_MATH/MML-test by default; override with mml=)
bin/mizarj article=xboole_1

# Full chain instead of checker-only; byte-compare reports against a reference
bin/mizarj article=xboole_1 full=1 ref=<refdir>

# The whole MML in one process
bin/mizarj mem=16g article-list=$RUSSELL_MATH/MML-test/mml.lar jobs=8 ref=<refdir>
```

Transcription rules and idiom mapping: `src/mizar/original/CONVENTIONS.md`.
The port is a finished dependency of the translation work below.

## The MML → Russell translation (`scripts/translate/mizar/`, `src/mizar/`)

Articles are translated into an A-map foundation derived from set.mm
(`miz_set`), proofs are emitted from the checker's own recorded derivations,
the remaining gaps are closed by the decision-procedure tactics, and the
result is checked by the original Metamath checker — nothing counts as proved
until Metamath says so.

```bash
# The whole pipeline on the 8-article gate set (emit + tactic + Metamath):
scripts/translate/mizar/gate.sh tac=def-close
```

State at the campaign's close (2026-08-24): 275 theorems proved and
Metamath-verified on the gate set — the emitter alone gives 247, the
`def-close` tactic alone 149, and the composition beats both.

* the binding plan, measured findings log, and closing state:
  [mml-port-plan.md](mml-port-plan.md);
* the decision-procedure tactics (`def-close`, `checker-mizar`) usable on any
  Russell goals: [mizar-decision-tactic.md](mizar-decision-tactic.md);
* how the checker's phases map to prover machinery:
  [mizar-checker-mapping.md](mizar-checker-mapping.md);
* the checker's closing rules measured over the whole MML:
  [mizar-inference-catalogue.md](mizar-inference-catalogue.md);
* the per-script reference: `scripts/translate/mizar/README.md`.

The hand-authored foundation lemmas live in `src/mizar/foundation/`
(installed into the math tree by `scripts/translate/mizar/install_foundation.sh`);
the Python census harnesses in `src/mizar/analysis/`.

## Tests and CI

```bash
bin/mizar-ci.sh            # builds mizar.jar, runs the check (~15 s after the build)
```

Two layers, both wired into the GitHub Actions workflow:

1. **Component suites** — `mizarj test=all`: 2293 unit checks of the
   transcribed units (containers, numbers, correlation, rounding, identify,
   equalizer, checker, …). Suites needing the full MML data skip themselves
   where it is absent (257 checks remain data-free).
2. **Full-chain fixture gate** — the bundled `test/mizar-fixture.tar.gz`
   holds the 8 gate articles' Mizar environment files plus the original
   verifier's reference reports; every article runs parser → MSM → analyzer
   → checker in one process, each produced report is byte-compared to the
   reference, and the tally is pinned (8/8 articles, 3037 inferences) so a
   silent under-run fails.

These are separate from `russellj test/all` (the set.mm-side battery): the
Mizar tests live in the mizarj jar, not the russellj one.
