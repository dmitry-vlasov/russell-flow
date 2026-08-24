# Hand-authored foundation lemmas

Everything under `$RUSSELL_MATH/mizar/` is GENERATED — 300 translated MML
articles, plus `miz_set.ru` (translated from set.mm) and `mizar_root.ru`.
`miz_aux.ru` is the exception: it is written by hand, it is the only file of
the foundation that a person edits, and it was living among the generated
articles where nothing kept it under version control.

The authoritative copy lives HERE. Install it into the math tree with

    scripts/translate/mizar/install_foundation.sh

which copies it to `$RUSSELL_MATH/mizar/miz_aux.ru`, where the translator
expects it (the emitter imports it as a standing dependency `mizar/miz_aux`,
see `mizEnvAids`/`depAids0` in `src/mizar/comms.flow`).

## What is in it

Lemmas for the proof paths the Mizar checker takes and the set.mm-derived
foundation does not state in that shape:

* one-hypothesis elimination forms of the membership biconditionals
  (`elunei`, `elinei`, `eldifei`, `pm2.45i`, `pm2.46i`) — the forward wave is
  triggered by the ground fact itself, so a bare `∈` premise cannot be
  unfolded through a schematic biconditional;
* set identities in the orientation the checker's normal form produces
  (`difin0b`, `difdifss`, `difabsb`, `inabsb`, `unpart3`);
* `ax-reg-mizar` — the axiom of regularity in Mizar's phrasing (Tarski's
  axiom 3); the miz_set slice deliberately omits set.mm's `ax-reg`;
* enumeration lemmas with the sethood guard already discharged (`velpr`,
  `veltp`, `vsnss`, `vprss`, `vtpss`, `vprid1/2`, `vtpid1/2/3`, `vdfop`);
* product membership in Mizar's shape (`elxpmiz`, `elxpmiz2`);
* propositional shapes of the checker's normal form (`dfbi3imp`, `nbbnimp`,
  `pm4.61imp`, `imorimp`);
* `symdifex` — sethood of symmetric difference.

Each lemma carries a comment naming the proof path that needed it.
