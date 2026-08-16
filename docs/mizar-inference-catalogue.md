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

★ CORRECTION (same day, and this is why the caveat was written): the
measurement was mostly an artifact. The record refers to terms by their
equalizer CLASS (<EqClass nr>) and deriv.flow translated those to an unhandled
marker — its own header called that a future slice. The goal's own literals
therefore arrived as markers and shared nothing with anything.

The classes ARE in the record: <Class nr> lists its members, and a member with
no class reference inside it is a surface term. mizDerivClassReps picks that
representative and mizDerivResolveEq rewrites the references before
translation (fuel-bounded, since members may reference other classes).

With that, the recorded refutation reads end to end:

    lit[2]    ( x43 ∈ x4 )
    lit[0]  ¬ ( element x43 x4 )
    instance: ( ( x4 = ∅ ) ∨ ( ( element x43 x4 ) ↔ ( x43 ∈ x4 ) ) )
    premise:  ∀ xd1 ∀ xd2 ( ( xd1 = ∅ ) ∨ ( ( element xd2 xd1 ) ↔ ( xd2 ∈ xd1 ) ) )

and the counts become 52 sharing a goal atom, 25 with a foreign symbol, 387
neither — the 387 being the expanded DEFINITIONS the checker adds as extra
literals, which share symbols with the goal but no atoms, exactly as they
should.

So the honest picture is NOT "the article's vocabulary is gone". It is: the
clause = the goal's literals after skolemization (x43 is the checker's fresh
constant) PLUS the definitional expansions as further literals. The
transcription therefore needs the skolem constants introduced as eigenvariables
and the expansions cited — both of which have machinery already.

CAVEAT on the measurement: record atoms come through mizDerivForm and goal
atoms through miz2ru, so a naming difference between the two dialects could
inflate the "foreign" count. The 0 is what matters and it is too clean to be
naming alone — but the first thing the next unit should do is print one
failing step's goal and clause side by side and confirm the vocabulary gap is
expansion, not spelling.

## Binders apart: the instantiation that was never attempted (2026-08-16)

The unit the previous entry asked for — print one failing step's goal and its
recorded clause side by side — and the answer it gave was not the one the
measurement predicted.

`t41_subset_1`, with the step's own context now printed next to the record:

    ctx-step[0] ( ( x7 ∈ x5 ) → ( { x7 } ⊆ x5 ) )
    ctx-step[1] ( ( x7 ∈ x5 ) → ( { x7 } ∈ 𝒫 x5 ) )
    goal        ( ( x7 ∈ x5 ) → ( element { x7 } 𝒫 x5 ) )
    instance    ( ( 𝒫 x5 = ∅ ) ∨ ( ( element { x7 } 𝒫 x5 ) ↔ ( { x7 } ∈ 𝒫 x5 ) ) )
    premise     ∀ xd1 ∀ xd2 ( ( xd1 = ∅ ) ∨ ( ( element xd2 xd1 ) ↔ ( xd2 ∈ xd1 ) ) )
    wit Xd1 := 𝒫 x5 ; wit Xd2 := { x7 } ; code 45

Nothing here is a vocabulary gap: the context has the membership fact, the
premise is the Element-of definition, and the instance closes the step. The
trace one line above says why it failed anyway:

    INSTIMP-DECLINE binder-in-witness X5
    REC-UNMATCHED cands=d1_subset_1 … match=true
      CAND d1_subset_1 stmt=∀ x5 ∀ xd2 ( ( x5 = ∅ ) ∨ … )

The definition's own bound variable is called `x5`, the step's free variable is
called `x5`, and the witness is `𝒫 x5`. `spcgv` is `disjointed(A x)`, so the
instantiation is refused — correctly, on the spelling, and for two variables
that have nothing to do with each other. The emitter counted **786 such
declines on subset_1 and 761 on xboole_1**.

★ THE FIX IS A RENAMING, AND ITS PLACE IS THE FETCH. An assertion's variables
are metavariables, so a statement with its bound variables renamed is a
substitution instance of the same assertion: the citation stays valid and the
emitted articles stay byte-identical. `folBindersApart` renames every bound
variable into a reserved pool (`Xq<k>`) and `stmtOfAt` applies it to every
statement handed to the emitter. Declines on subset_1: 786 → 0.

TWO RULES THE FIRST VERSION BROKE, both caught by Russell's own verify on
relat_1 before the Metamath half ran:
 * ONE FRESH NAME PER DISTINCT VARIABLE, not per binder occurrence. A statement
   may bind the same variable twice (`d12_xtuple_0`: `∀ x12 … ∃ x12 …`).
   Renaming the two occurrences apart is no longer a substitution instance —
   Russell reported `proposition expression … cannot be matched with` — and
   where it did match it captured (`exbidv`, `∃ xq4 ⟨ xq4 , xq4 ⟩`).
 * A NAME THAT ALSO OCCURS FREE KEEPS ITS SPELLING. An open proposition (what
   a `let` leaves) states the article's own free variables, and the citing step
   must state them too. Only ∀-closed statements are renamed.

MEASURED, full gate, every article Russell-verified and then checked by the
ORIGINAL Metamath checker (all 7 databases success=true, proved == closed):

    tarski 2/3 · xboole_0 3/8 · xboole_1 87/116 · enumset1 66/87
    zfmisc_1 45/140 · subset_1 9/53 · xtuple_0 7/46 · relat_1 25/179
    TOTAL 244 (was 240)

The closed LISTS were diffed, not the counts: +8 (xtuple_0 t10 t11 t31 t35 t39
t43, enumset1 t41, subset_1 t41) and −4 (relat_1 t70 t87 t89 t186).

★ THE LOSS IS THE FACT BUDGET AGAIN, and it is worth stating plainly: the
declined instances were never free. With them relat_1's steps over the tree cap
go 100 → 233, its closed steps 50 → 45, and its emit time 3m05 → 5m02. Every
new fact is offered to every step that can index it, so unblocking an
instantiation is also an enlargement of the search. The four relat_1 theorems
are the price of the eight elsewhere; recovering them is a SELECTION question,
not a renaming one.

Also in this unit: the failing-step diagnostic now prints the step's
hypotheses and prior steps, the recorded witnesses, the kill pairing and the
closing code, so a failing step can be read against what the proof had in hand.

## Why the recorded instance is not there: the three buckets (2026-08-16)

With the binder declines gone, the honest coverage question is what a failing
step still lacks. Measured per failing step whose record HAS the checker's
refutation, does the recorded INSTANCE appear among the facts offered?

    subset_1   49 failing steps, 31 with a refutation, 26 miss their instance
    relat_1   169 failing steps, 64 with a refutation, 64 miss their instance

So the blocker is no longer the propositional endgame — it is that the
instance is never built. Bucketed by cause (per instance, not per step):

    subset_1   9 premise did not translate · 0 no candidate · 37 no match · 2 other
    relat_1    0 premise did not translate · 34 no candidate · 128 no match · 2 other

★ "NO MATCH" IS THE BUCKET, and reading one case named two causes, both in the
matcher and neither in the mathematics.

 1. THE UNIVERSALS BEHIND A GUARD. The checker instantiates every universal of
    its premise, including those an implication hides — `t2_subset_1` is
    `∀A∀B ( B is Subset of A → ∀C ( C is Subset of A → … ) )` and comes back
    instantiated at C with both guards standing. Only the leading prefix was
    eliminable. The walk through the guards already existed for guard-interleaved
    DEFINITIONS (mizEmitPrefixGm + mizEmitRecordDrvG, which eliminates each binder
    under the guards by syl/syl6); it was simply never offered to the record's own
    join. It is now, with the matching side (`mizEmitPrefixGe`/`mizEmitStripGm`)
    mirroring the assembly's own descent rule — a guard is entered only while an
    eliminable binder remains behind it, at most two. The plain walk descends
    through EVERY implication, which on `… → ( ∀xd4 φ → B ⊆ C )` counts a third
    guard and gives up.

 2. ★ A BOUND VARIABLE'S NAME IS NOT PART OF THE STATEMENT. The instance keeps
    the binders it did not eliminate under the CHECKER's names (`∀ xd4 …`) while
    our statement carries ours (`∀ xq4 …`), and `mizEmitMatchF` required
    `vs == vs2`. Every instance with a quantifier left in it was rejected on the
    spelling. The target's binders are now renamed to the pattern's — a renaming,
    refused if one of the pattern's names occurs free in the target's body — and
    the final equality is alpha-tolerant for the same reason.

MEASURED: no match 37 → 24 (subset_1) and 128 → 120 (relat_1); instances not
offered 26 → 26 and 64 → 62. FULL GATE GREEN at 244 with the closed list
IDENTICAL to the previous round — not one theorem moved, in either direction,
and relat_1's emit time went 5m02 → 4m49.

★ THE HONEST READING: this is a coverage and correctness fix that pays nothing
yet. More of Mizar's own instances are now built, and the steps that receive
them still do not close — which is the same lesson the fact budget keeps
teaching, and it says the next question is not "how do we build more of the
record" but "what does the step do with the record once it has it". The
remaining no-match cases (24 and 120) are the next thing to read, and the
34 relat_1 instances with NO candidate statement are a separate, simpler gap.

## The eigenvariable's own instances (2026-08-16)

Reading t2_subset_1 to its leaf: the ⊆-goal reduction introduces a fresh
variable and reaches `x951 ∈ x29` under the step's antecedents, and the only
fact there was the Element-of definition at the by-step's OWN element `x7`.
The step's universal hypothesis
`∀x7 ( x7 is Element of A → ( x7 ∈ X → x7 ∈ Y ) )` sat over that leaf as one
opaque atom — exactly what the ∀-fact law forbids ("instantiate it at the
goal's element, or drop it"). The facts are built at the by-step level, where
the eigenvariable does not exist yet.

That fresh variable is Mizar's skolem constant for the step and the checker
instantiates its premises at it. `mizEmitEigenInsts` does the same: at the
⊆ / = reduction, every universal hypothesis (and every conjunct of the
assumption) is offered as the instance implication `∀x φ → φ[v]`, which is a
self-contained fact with its own spcgv+sethood proof — so the tableau detaches
the instance from the hypothesis itself and nothing has to be threaded through
the reduction.

The leaf now reads

    F0 ( ∀ x7 ( ( element x7 x4 ) → ( ( x7 ∈ x28 ) → ( x7 ∈ x29 ) ) )
         → ( ( element x951 x4 ) → ( ( x951 ∈ x28 ) → ( x951 ∈ x29 ) ) ) )

GATE GREEN at 244, closed list identical, emit 361s → 385s. So it pays nothing
on its own, and it is committed for what it makes possible: every eigenvariable
proof needs its premises stated about the eigenvariable, and the type rule the
same leaf still waits for is stated about it too.

WHAT THE SAME LEAF STILL LACKS, and it is one rule, not a fact: from
`X is Element of 𝒫A` and `v ∈ X`, conclude `v is Element of A`. That is Mizar's
Element-of type propagation, carried silently by the type system, and it is
four existing lemmas (the Element-of definition at 𝒫A and at A, elpwi, ssel,
and non-emptiness from a member). It cannot be a foundation lemma — `element`
is the article's own definition, not a foundation symbol — so it is either a
derived rule at the eigen seam or an auxiliary lemma the emitter proves once
per article and cites.

MEASURED DEAD END from the same session, recorded so it is not retried: giving
the leaf the statements the SKELETON index offers for the eigenvariable's atoms
builds instances, and the wrong ones — for this leaf the `∈` skeleton returned
`( { x951 } ∪ x28 ) ⊆ x28 → x951 ∈ x28`. Narrowing it to the atoms the eigen
instances introduce produced nothing at all, because the definition the leaf
needs is named by no citation and its `element` skeleton finds no statement the
generic instantiator can bind. Selection by skeleton at a leaf is a lottery, and
this is the third measurement to say so.
