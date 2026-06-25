# Mizar → A-map Russell pipeline

Scripts for importing Mizar (analyzed XML) into the A-map Russell foundation
(`miz_set`, set.mm's native class layer) and filling proof gaps. All operate on
`$RUSSELL_MATH/mizar/` (the A-map work area — kept separate from `$RUSSELL_MATH/set/`,
the canonical set.mm library). `math` is supplied automatically by `bin/russellj`
from `$RUSSELL_MATH`, so you never pass `math=`.

## Permanent scripts

- `pipeline.rus` — **one-shot**: translate + prove + verify for ONE article in a
  single launch (skips the manual phase (b); a command-level tactic proves every
  gap). This is the easiest way to reproduce the whole pipeline:
    `russellj no-server=1 mem=16g translate/mizar/pipeline article=xboole_1`
  Requires `mizar/miz_set.ru`, the article's already-translated deps in `mizar/`,
  and the analyzed `MML/mml/<article>.{xml,atr,eth}`. Default tactic is
  `def-close-checker` (the A-map auto-prover incl. the Kalmár-free checker). The
  per-article result = the article's theorems absent from the `not proved:` list.
- `regen_foundation.rus` — rebuild `mizar/miz_set.ru` from the set.mm library
  (run from `$RUSSELL_MATH/set/`).
- `translate.rus` — (phase a) Mizar article → A-map skeleton `mizar/<module>.ru`
  with open `?` steps.
- `prove.rus` — (phase c) prove the inline per-step tactics added in phase (b),
  write completed proofs back, re-verify.
- `translate_flat.rus` — legacy: translate against the FLAT `miz_logic` foundation
  (pre-A-map). Kept for comparison.

The three explicit phases (translate → manual phase (b) edit → prove) are still
available separately; `pipeline.rus` just chains (a) and (c) for convenience.
Phase (b) — adding an inline tactic to each `?` step — is a manual edit of the
`.ru` file (no script).

## Dependency order

An article translates against its already-translated deps, so translate in
dependency order, e.g.:
```
for a in tarski xboole_0 xboole_1; do
  russellj no-server=1 mem=16g translate/mizar/pipeline article=$a
done
```

## Analysis tools

- `prove_env_axioms.sh` — turn each leftover env `axiom` of `mizar/<module>.ru`
  into an OPEN theorem and try to prove it from the foundation alone (each in
  isolation), reporting which are redundant (derivable) vs genuinely new. Uses a
  generous BFS budget (tunable via `MD`/`MS`/`FC` env vars). Probes are left in
  `/tmp/prove_env_<module>/` for manual re-runs.
    `scripts/translate/mizar/prove_env_axioms.sh xb1 60s 1`
- `prove_probe.rus` — helper invoked by the above: read one probe `.ru` and prove
  one target (also usable directly to retry a single axiom with custom settings).

## Temporary / debug scripts

Put throwaway debugging scripts in `tmp/` — it is git-ignored, so they are easy to
spot and never get committed. Do not scatter `_`-prefixed scratch scripts in the
main directory.
