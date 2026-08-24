# The open-goal census

Two scripts that turn an emitted article into a work list. Both read only
the emitted `.ru` and the foundation `miz_set.ru` — seconds, no jar.

    census_open.py <emitted.ru> [maxlen]
        every OPEN goal (`= ?` step) of the article, shortest first,
        with the citations the record gave that step.

    match_lib.py <emitted.ru> [miz_set.ru]
        the same goals, looked up in the library BY STATEMENT: each goal
        is canonicalised (variables renamed in order of first
        appearance) and matched against every hypothesis-free theorem of
        miz_set, as stated and as either half of an equivalence. A goal
        in the checker's denied-conjunction spelling is also matched
        against its implication form (`neg-bridge`). A second index
        sorts the arguments of the commutative operators, so a row that
        differs only by commutativity still shows up — as a CANDIDATE:
        such a row needs its own bridge, and the verifier decides.

A hit means the by-step is a library lemma the proof was re-deriving:
add the row to mizEmitFoundIffTable / mizEmitFoundGuardedTable in
`src/mizar/emit.flow`, copied VERBATIM as miz_set states it.
