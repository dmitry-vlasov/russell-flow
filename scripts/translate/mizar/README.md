# Mizar → A-map Russell pipeline

Scripts for importing Mizar (analyzed XML) into the A-map Russell foundation
(`miz_set`, set.mm's native class layer) and filling proof gaps. All operate on
`$RUSSELL_MATH/mizar/` (the A-map work area — kept separate from `$RUSSELL_MATH/set/`,
the canonical set.mm library). `math` is supplied automatically by `bin/russellj`
from `$RUSSELL_MATH`, so you never pass `math=`.

## Permanent scripts

- `regen_foundation.rus` — rebuild `mizar/miz_set.ru` from the set.mm library
  (run from `$RUSSELL_MATH/set/`).
- `translate.rus` — (phase a) Mizar article → A-map skeleton `mizar/<module>.ru`
  with open `?` steps.
- `prove.rus` — (phase c) prove the inline per-step tactics added in phase (b),
  write completed proofs back, re-verify.
- `translate_flat.rus` — legacy: translate against the FLAT `miz_logic` foundation
  (pre-A-map). Kept for comparison.

Phase (b) — adding an inline tactic to each `?` step — is a manual edit of the
`.ru` file (no script).

## Temporary / debug scripts

Put throwaway debugging scripts in `tmp/` — it is git-ignored, so they are easy to
spot and never get committed. Do not scatter `_`-prefixed scratch scripts in the
main directory.
