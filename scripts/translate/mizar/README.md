# Mizar → Russell translation pipeline

Scripts for importing Mizar (analyzed XML) into the A-map Russell foundation
(`miz_set`, set.mm's native class layer) and closing the proofs. All operate on
`$RUSSELL_MATH/mizar/` (the work area — kept separate from `$RUSSELL_MATH/set/`,
the canonical set.mm library). `math` is supplied automatically by `bin/russellj`
from `$RUSSELL_MATH`, so you never pass `math=`.

## The gate (the main entry point)

- `gate.sh` — the whole pipeline end to end on the 8-article gate set, ending
  at the ORIGINAL Metamath checker (nothing counts as proved until Metamath
  says so). Phases: emit (the record-driven emitter closes what it can,
  leaving `?` gaps) → tac (optional: a tactic over the remaining gaps) → mm
  (export + Metamath check; the gate law requires proved == closed per
  article).

      scripts/translate/mizar/gate.sh                # emit + mm
      scripts/translate/mizar/gate.sh tac=def-close  # THE COMPOSITION
                                                     # (gate green 275, 2026-08-24)

  Run it detached (a round is ~35 min); see the header comment for the
  resource facts (export memory peaks, /tmp is RAM).

## Per-article scripts

- `pipeline.rus` — one-shot for ONE article: translate + prove + verify in a
  single launch. Default tactic `generators-first`.
    `russellj no-server=1 mem=16g translate/mizar/pipeline article=xboole_1`
  Requires `mizar/miz_set.ru`, the article's already-translated deps in
  `mizar/`, and the analyzed `MML/mml/<article>.{xml,atr,eth}`.
- `translate.rus` — translate one article; `emit=0` statements only (skeleton
  with open `?` steps), `emit=1` also emit the record-driven proofs.
- `prove.rus` — prove an article's remaining `?` gaps with a tactic
  (default `def-close`), write the closures back, re-verify; `mm=1` also
  exports and runs the Metamath checker. This is what the gate's tac phase
  calls.
- `prove_gaps.rus` — measure-only variant of the above (no write-back).
- `to_mm.rus` — export one article to Metamath and verify it with the
  external checker (the gate's mm phase).
- `verify.rus` — read + verify one article.
- `regen_foundation.rus` — rebuild `mizar/miz_set.ru` from the set.mm library
  (run from `$RUSSELL_MATH/set/`).
- `install_foundation.sh` — install the hand-authored `foundation/miz_aux.ru`
  into the math tree (see `foundation/README.md`).
- `translate_flat.rus` — legacy: translate against the FLAT `miz_logic`
  foundation (pre-A-map). Kept for comparison.

See `docs/mizar-decision-tactic.md` for using the gap-closing tactics on
their own, and `docs/mml-port-plan.md` for the campaign's plan, findings log
and closing state.

## Dependency order

An article translates against its already-translated deps, so translate in
dependency order (gate.sh does this for the gate set):
```
for a in tarski xboole_0 xboole_1; do
  russellj no-server=1 mem=16g translate/mizar/pipeline article=$a
done
```

## Analysis tools

- `prove_env_axioms.sh` — turn each leftover env `axiom` of `mizar/<module>.ru`
  into an OPEN theorem and try to prove it from the foundation alone (each in
  isolation), reporting which are redundant (derivable) vs genuinely new.
    `scripts/translate/mizar/prove_env_axioms.sh xb1 60s 1`
- `prove_probe.rus` — helper invoked by the above (also usable directly).
- The Python census/report harnesses live in `src/mizar/analysis/`
  (open-goal census, MML layer map, whole-MML translation harness).

## Temporary / debug scripts

Put throwaway debugging scripts in `tmp/` — it is git-ignored, so they are easy
to spot and never get committed. Do not scatter `_`-prefixed scratch scripts in
the main directory.
