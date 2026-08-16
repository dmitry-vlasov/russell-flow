# THE INFERENCE CATALOGUE — the revised plan for the MML port

STATUS: adopted 2026-08-14, replacing gap-closing as the working method.

## Why the plan changed

Three designs have now stalled at the same place: a composable tactic, a
monolithic tactic with completers, and a separate emitter. Each gained a
little, then hit a wall, and each wall cost many disproved hypotheses. The
common cause is not the architecture but the TARGET.

Mizar decides a step with procedures — equality classes and congruence
closure, definitional expansion, type and cluster inference, then a
propositional refutation. We emit into set-theoretic Metamath, which has
none of them. So every step is re-derived from primitives. That single
mismatch explains both standing problems: generation is hard because each
step needs a fresh construction instead of an application of a known rule,
and proofs are enormous because a two-atom step expands into thousands of
lines.

`foundation/miz_aux.ru` is the shape of the right answer — a named lemma
cited instead of a re-derivation — arrived at by the wrong method: its
contents were discovered one failure at a time, so it records where we got
stuck, not what Mizar does.

## The plan

**Prove Mizar's inference rules once, as lemmas, and make emission a
citation of them.**

The checker closes each disjunct of a refutation by setting a contradiction
code — the original's `SetContr` argument. That code IS the name of the
inference kind: 1..44 equalizer (attribute clash, class merge, cluster
rounding, complementary literals, type membership), 45..49 unification
(one universal premise instantiated, two unified, Fraenkel membership),
50..51 arithmetic prechecks. The list is finite and already counted by
`MizCensus` (`src/mizar/original/checker.flow`, reported by `census=1`).

So the work is:

1. **Census** — run the ported checker over the whole MML with `census=1`
   and get, for every code, how many disjuncts it closes. This says what
   fraction of the library each kind is worth BEFORE any proof is written.
2. **Rank** — order the codes by share. Take the head of the distribution.
3. **One lemma per kind** — for each code, state the Russell schema that
   licenses that inference and prove it once from the foundation. Not a
   proof pattern in the emitter: a theorem in the library.
4. **Emit by citation** — a by-step becomes: name the kind, supply the
   witnesses the record already carries, cite the lemma.
5. **Gate** — unchanged: the ORIGINAL Metamath checker, `proved == closed`.

## The criterion, fixed in advance

The design is judged by two numbers, both measurable:

* **coverage** — the share of the library's disjuncts closed by codes we
  have lemmas for;
* **size** — emitted bytes per by-step, which must fall, because a citation
  replaces a re-derivation.

If a few kinds cover most disjuncts, the port becomes finite engineering
with a visible end. If the distribution is flat — hundreds of rare kinds —
the port in this form is not viable and we say so instead of grinding.

## What this replaces

No new bespoke closers in the emitter. The library-row table, the chains
and the bridges stay as they are (they already work by citation), but they
stop growing case by case.

---

## THE CENSUS — measured on the whole MML, 2026-08-14

`bin/mizarj article-list=mml.lar jobs=8 census=1`, 1,497 articles, all
verified, 21 minutes. **1,389,675 inferences, 2,203,810 disjuncts.**

### The closing rule

| code | disjuncts | share | cumulative | rule |
|---|---|---|---|---|
| 45 | 1,520,083 | 68.98% | 68.98% | one universal premise instantiated |
| 19 | 163,072 | 7.40% | 76.37% | reflexivity over one class |
| 20 | 75,537 | 3.43% | 79.80% | complementary literal pair |
| 2 | 72,390 | 3.28% | 83.09% | attribute clash at typing |
| 21 | 63,463 | 2.88% | 85.97% | type membership visible in the class |
| 1 | 52,622 | 2.39% | 88.35% | attribute clash at typing |
| 47 | 48,519 | 2.20% | 90.56% | two universal premises unified |
| 11 | 38,542 | 1.75% | 92.31% | attribute clash at cluster rounding |
| 43 | 21,235 | 0.96% | 93.27% | type membership refuted |
| 49 | 20,956 | 0.95% | 94.22% | Fraenkel membership |
| 40 | 18,802 | 0.85% | 95.07% | type membership refuted |

★ **SEVEN codes close 90.6% of the library's disjuncts. Eleven close 95%.**
The distribution is steep — this is the "go" answer the criterion asked for.

### By family, which is how the lemmas group

| family | codes | share |
|---|---|---|
| universal instantiation | 45, 47 | **71.2%** |
| equality and propositional | 19, 20, 25 | **11.2%** |
| soft types — attributes and clusters | 1, 2, 6, 8, 9, 10, 11, 21, 40, 41, 43 | **13.9%** |
| Fraenkel membership | 48, 49 | 1.8% |
| arithmetic and order | 26, 31, 32, 33, 50 | 1.5% |

### The case split is narrow

Split width, disjuncts per inference: **1,029,631 of 1,389,675 inferences
(74%) have exactly ONE disjunct**, and 38,115 more are settled by
normalization alone. So 77% of steps need no case analysis at all — which
is the other half of the size problem, and it says the explosion we see is
ours, not Mizar's.

## What the census decides

1. **Universal instantiation is the whole head of the distribution** and the
   emitter already implements it (∀-elimination at the recorded witnesses,
   sethood-discharged). So the failures we hit are NOT in the closing rules.
   They are in what surrounds them: the premise assembly (definitional
   expansion of atoms), the equality classes, and the type layer.
2. **The soft-type family is 13.9% in ONE coherent mechanism** — attributes,
   adjectives, cluster rounding — and it is not implemented at all. It is
   the largest missing block and the next unit, exactly as the roadmap's
   stage S-C said, but now with a number.
3. **Equality is 11.2%** and the eq-chain builder already covers part of it.
4. Fraenkel, arithmetic and order together are 3.3% — correctly last.

So the order of work is: the type layer (13.9%), then the equality
completion (11.2%), then Fraenkel (1.8%). Nothing else is worth touching
until those are done.

## Type layer, step 1: the record (done, 2026-08-14)

The type family (13.9% of all disjuncts) could not be emitted because the
record was 6% complete: `RoundUpCluster` wrote its conditional firings
(`<Round kind="C">`), while the FUNCTOR registrations — the ones that give a
term its attributes in the first place — were a debug print. Both halves are
now recorded, and the term is serialized structurally, in the dialect
`deriv.flow` already parses:

    <Round kind="F" article="ZFMISC_1" nr="1" trm="K1(A1)" typ="..."
           before="-V1 ()" after="-V1 ()V2 ()">
      <Func kind="K" nr="1"><LocusVar nr="1"/></Func>
    </Round>

subset_1: 46 lines -> 725. The verifier is unchanged (recording is off unless
asked for; 25 articles re-verified).

Two findings that decide how the emitter consumes it:

1. **The registrations are already citable.** The translator emits them as
   `fc<N>_<article>` / `rc<N>_<article>` axioms (e.g. `fc2_xboole_0`:
   `∀l1 ¬({l1} = ∅)`), so the record's `article`/`nr` join them directly. A
   type fact is therefore an INSTANCE of an existing axiom — the same
   ∀-elimination the emitter already performs — not a new proof shape.
2. **The firings are mostly on concrete terms, not on patterns.** Of
   subset_1's 706 F firings: 448 ground, 180 with a constant, 27 with a
   variable, and only 24 on locus patterns. So the record names the actual
   terms of the article and can be consumed as a table: term -> the
   registration that gave it its attributes.

★ NEXT (the emitter half): for each recorded firing at a by-step's terms,
offer `fc<N>_<article>` instantiated at the term's arguments as a fact, and
index those facts by the attribute they conclude — the type guards the
discharge currently fails to find. The gate is subset_1 (7/53 today) and
relat_1 (27/179).

## The transcription, step 1 (2026-08-16): the record is not a fact supply

Measured on subset_1: **50 failing steps, and 32 of them (64%) have the
checker's own refutation in the record** — a single disjunct, the clause, the
universal premise, its instance, and the `<PreLit anr=>` pairing that says
which clause atom each instance literal kills. Exactly the single-disjunct
code-45 share.

★ AND SUPPLYING THAT INSTANCE AS A FACT CHANGES NOTHING. `recOf` had been
dropping any record instance that was not fully instantiated; the clause
record keeps them, they were added to the step's fact pool, and subset_1
stayed at 8/53 with the same 50 failures. Rung 2R already tried the record's
facts wholesale before that.

So the blocker is NOT premise selection — Mizar's own premise is present and
the proof still is not found. What differs is the PROPOSITIONAL ENDGAME: the
checker refuted its clause, and our tableau works on its own normal form of
¬goal, in which the recorded kill pairing does not exist. The two normalizers
do not agree, so the instance cannot be used the way Mizar used it.

★ THE CONSEQUENCE FOR THE PLAN: transcription has to include the
normalization. The proof to emit is
  1. assume ¬goal;
  2. derive the clause literals L1..Ln from it — Mizar's normal form, proved,
     not re-derived by search (mizEmitQNF is the proof-producing normalizer
     that exists);
  3. derive the instance from the premise at the recorded witnesses;
  4. kill literal by literal in the recorded order, closing by the code.
Steps 3 and 4 are already data (mizDerivPosClauses). Step 2 is the missing
piece and it is the whole difficulty: our ¬goal must be brought to Mizar's
clause with a proof.

What this rules out, on evidence rather than opinion: any further work on
selecting, ranking or supplying facts. The fact side is solved; the normal
form is not.

## Why the two normal forms do not meet (2026-08-16)

Measured on subset_1's failing steps, over the 464 clause literals the record
carries for them:

    0    share an atom with the step's goal
    346  carry a symbol the goal never mentions
    118  share symbols but no atom

So Mizar's clause is not a rearrangement of our negated goal. It is the goal
after the checker's own PreCheck: `ExpandAtomicAsTrue` replaces every atom
whose predicate/attribute/functor has an expandable definition by its
definiens, and `RemoveIntQuantifier` replaces inner quantifiers by fresh
CONSTANTS. By the time the clause exists, the goal's own vocabulary is gone.

★ THIS IS THE ANSWER TO THE THREE STALLED DESIGNS. Our tableau reasons in the
article's vocabulary; the checker reasons in the expanded, skolemized one.
Every fact we hand it is stated in the wrong language, which is why supplying
Mizar's own premise changed nothing, why fact selection never mattered, and
why the failing goal shapes are flat while Mizar's rules are steep.

★ AND IT NAMES THE PIPELINE, in Mizar's own order (prechecker.pas 1087):
  1. expand the goal's atoms by the definitions the checker used — RECORDED,
     as <Expand article nr> (164 of them in subset_1), and a definition's
     expansion is an iff, i.e. a citation;
  2. skolemize the inner quantifiers — the eigenvariable machinery the
     emitter already has in part (exlimiv / exlimdv chains);
  3. the clause literals now exist in our proof, and the recorded kills apply
     verbatim.
Nothing in that chain is invented; every stage is Mizar's, and stages 1 and 3
are already in the record.

CAVEAT on the measurement: record atoms come through mizDerivForm and goal
atoms through miz2ru, so a naming difference between the two dialects could
inflate the "foreign" count. The 0 is what matters and it is too clean to be
naming alone — but the first thing the next unit should do is print one
failing step's goal and clause side by side and confirm the vocabulary gap is
expansion, not spelling.
