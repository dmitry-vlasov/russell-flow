# The Mizar checker in Russell — the whole algorithm, and where we depart from it

STATUS: design map, 2026-07-31. Written against the ORIGINAL sources in `~/dev/system/kernel/`
(Mizar system, GPL). Every claim about Mizar below carries a file:line reference and was read,
not recalled. Every claim about Russell carries a file reference into this repository.

THE STANDING DIRECTIVE this document serves: build the Mizar tactic by TRANSCRIBING the original
algorithm as a whole. When a problem appears, adopt Mizar's own answer to it; do not invent a
mechanism Mizar has no counterpart for. Mizar checks the entire MML in reasonable time, so every
obstacle we meet has a working answer in the original.

---

## 1. What Mizar actually runs, per justification

The unit of work is one justification of one proposition. `analyzer` has already produced the
proposition and its thesis; the checker is called per justification and knows nothing else.

### 1.1 Premise assembly — `mizprep.pas` `ChInferObj.Justify`, `justhan.pas`

For a `by` justification (`ikInfBy`) the premise array is built as:

```
PremNbr := 0
lThesis := ¬ (the proposition being justified)      -- premise #1, ALWAYS
InsertRef(lThesis)
if nLinked then InsertRef(previous proposition)     -- the `then` link
CollectRefs                                          -- the author's `by` list, one per reference
InferenceChecker(Reference, PremNbr, nVarNbr)
```

Reference kinds (`justhan.pas:33-62`): private (a local label), theorem (library), definition
(library). That is the complete premise set: **negated thesis + optional linked proposition +
the cited references. There is no search for premises and no selection among them.** This is
the single most important structural fact about the Mizar checker.

### 1.2 `PreCheck` — `prechecker.pas:1087`

Turns the premise array into a set of ground literal sets (DNF disjuncts):

1. `ExpandAtomicAsTrue` per premise (`prechecker.pas:1029`) — **definitional expansion of atomic
   formulas**: an atom whose predicate/attribute/functor has an expandable definition is replaced
   by its definiens. Definitions therefore enter WITHOUT being cited, but only as expansions of
   atoms that actually occur.
2. `ExpandFlexAsTrue` — flexary (ellipsis `...`) expansion.
3. Conjoin all premises; `DistributeQuantifiers` (`prechecker.pas:576`).
4. `RemoveIntQuantifier` (`prechecker.pas:727`) — internal quantifiers are removed by introducing
   fresh CONSTANTS (`NewConst`, `prechecker.pas:639`). This is Mizar's skolemization; the result
   is quantifier-free except for the universals that survive as `ikFrmUniv` literals.
5. `CollectConstInFrm`, then `NormalizeAsTrue` → `PreInstCollection`, a collection of disjuncts,
   each a map (basic-formula-number → sign) over the `Basic` collection of atoms.
6. `CreateInference` (`prechecker.pas:768`).

### 1.3 The decision loop — `checker.pas:331` `InferenceChecker`

```
PreCheck(...)                          -- 1.2
for each disjunct D of the normal form:
    Equate(D)                          -- 1.4   equalizer.pas
    if Contr = 0:
        PreUnification                 -- 1.5   checker.pas:101
        if Contr = 0:
            InitUnifier; Unification   -- 1.6   unifier.pas:2453
            if Contr = 0: ChError(4)   -- "unsolved inference": the `by` FAILS
```

The justification succeeds iff **every** disjunct is refuted (`Contr > 0`). Other error codes are
resource overflows, not logical failures: `1` trivial, `8`/`9` lattice overflow, `11` constant
overflow, `14` term overflow.

### 1.4 `Equate` — `equalizer.pas`, the Equalizer

Per disjunct, builds the ground structure and looks for a contradiction in it:

- every term is hash-consed into an equality class (`TrmS[i]`, `EqClass`, `YTerm`);
- positive `=` literals union classes; congruence is propagated through per-functor occurrence
  lists (`FuncTrmList`, `UnionTrms`);
- each class carries its TYPE and its attribute cluster (`XTypClass`, `SuperCluster`);
- **rounding-up**: conditional and functorial registrations are fired against the classes
  (`AllowedCCluster`, `AllowedFCluster`; `roundcl.pas` `TryRounding` at line 78 is the rule:
  antecedent attributes ⊆ current cluster, type reachable, loci types check → enlarge the cluster
  by the consequent);
- numerals and polynomial values (`polynom.pas`) evaluate arithmetic;
- contradiction sources set `Contr` (`SetContr`): reflexivity/irreflexivity of a class, a negated
  equality inside one class, contradictory attributes, requirement facts (empty/non-empty,
  zero/non-zero via `builtin.pas` `gBuiltIn[rq…]`), and more.

### 1.5 `PreUnification` — `checker.pas:101`

Between Equalizer and Unifier, Mizar derives INEQUALITIES that the Unifier will need:

- a positive literal whose predicate has the `irreflexivity` property, and a negative one with
  `reflexivity`, force the two argument classes apart (`AllocInequality`, `checker.pas:58`);
- contradictory attribute clusters between two classes force them apart;
- **the one-difference rule** (`OneDiffInTrmLists`, `checker.pas:79`): if a negative literal and a
  positive literal have the same head and their argument lists differ in exactly ONE position,
  those two arguments must be unequal; likewise for two terms of the same functor inside classes
  related by a reflexive predicate, and for qualifications (type membership) differing in one
  argument.

### 1.6 `Unification` — `unifier.pas:2453`

The only phase that instantiates universal statements:

```
UNIV := the positive universal literals of the disjunct (ikFrmUniv)
for each U in UNIV:                 if Verify(U) then Contr := 45
for each pair (U_i, U_j), i ≤ j:    if Unifiable({U_i,U_j}) then Contr := 47
Fraenkel-term rules for ∈ literals: Contr := 48 / 49
```

`Verify(U)` (`unifier.pas:2364`): standardize U, replace its external quantifiers by FREE
VARIABLES (`RemoveExtQuantifier` + `NewFreeVar`), normalize the body as false into clauses, and for
each literal compute the set of substitutions (free variable → equality class) under which it
contradicts the basis (`COMPInstAsFalse` / `COMPInstAsTrue`); intersect those sets across the
clause (`JoinInstList`, the InstCollection substitution lattice). Non-empty intersection = the
universal can be instantiated into a contradiction.

`Unifiable` (`unifier.pas:2265`) with `CreateClauses` (2160), `CollectComplementaryLiterals` (2219)
and `ResolventVerify` (2039) is resolution restricted to AT MOST TWO universal statements.

**Note the shape of the whole thing: no unbounded search anywhere.** Instantiation ranges over
equality classes, resolution over pairs, and everything is bounded by structural constants in
`limits.pas` (`MaxInstTrmNbr = 26`, `MaxInstNbr = 6000`, `MaxArgNbr = 13`, …). There is no time
budget in the algorithm at all.

### 1.7 Scheme justification — `mizprep.pas` `SchemeInfer`, `schemes.pas`

`from` justifications are a different inference: the cited scheme's premises are matched against
the collected references and the scheme's conclusion against the current proposition
(`Schematize`). Frequency in the MML sample: `subset_1` 2, `setfam_1` 5, `relat_1` 8, `funct_1` 18,
`ordinal1` 18, `classes1` 40 — against several hundred `by` each. Small but not negligible.

### 1.8 The ambient data the checker relies on

None of this is cited; all of it is part of the environment the checker is handed:

| data | Mizar source | role |
|---|---|---|
| types of terms + attribute clusters | analyzer, `roundcl.pas` | every equality class carries one |
| registrations (functorial / conditional / existential) | `roundcl.pas`, `AllowedCCluster/AllowedFCluster` | rounding-up |
| definitional expansions | `prechecker.pas:877-1052` | atoms replaced by definiens |
| constructor properties (reflexivity, irreflexivity, symmetry, projectivity, involutiveness) | `ConstrPtr.fProperties` | `PreUnification` |
| requirements (built-in constructors: `=`, `∈`, empty, zero, …) | `builtin.pas`, `req_info.pas` | Equalizer special rules |
| identifications | `identify.pas` | term identification |
| numerals / polynomials | `polynom.pas` | arithmetic |

---

## 2. What Russell must add: proof objects

Mizar answers yes/no. Russell must produce a kernel-checkable proof. So each Mizar phase gets a
proof obligation, and this is the ONLY place where we are allowed to have machinery Mizar lacks:

| Mizar phase | Russell must additionally emit |
|---|---|
| premise assembly | the premise leaves: hypothesis references, and for a cited assertion its application step |
| definitional expansion | the biconditional instance used, with its own proof (definition applied at the occurrence) |
| `RemoveIntQuantifier` | the ∀-instantiation / ∃-elimination steps (spcv chain, exlimiv discharge) |
| DNF normalization | the propositional glue that recombines per-disjunct proofs into one proof of the thesis |
| `Equate` | the congruence-closure proof (already proof-producing: `congruence.flow`) |
| rounding-up | each fired registration is an ordinary implication — its application step |
| `PreUnification` | the derivation of each allocated inequality from the property/one-difference lemma |
| `Verify` / `Unifiable` | the instantiated premise with its substitution proof, and the propositional refutation as glue |
| verdict | assembly of the per-disjunct refutations into `⊢ thesis` |

This is the "planner → completer → executor" split already agreed: the planner decides (Mizar's
algorithm, no proofs), the completer turns each decided step into a fixed proof pattern, the
executor replays and the kernel verifies.

---

## 3. Where Russell stands today

We have TWO implementations running side by side. The default tactic is
`tactics/generators-first.tac`:

```
or-else(equalizer-gen, unifier-gen, def-close-checker, checker-mizar, checker-eq, checker-el)
```

**(A) The transcription** — `equalizer_plan.flow` / `equalizer_gen.flow` (Equate) and
`unifier_plan.flow` / `unifier_gen.flow` (PreCheck DNF + InstCollection lattice + the pairwise
`Contr 47` step). These follow §1 and are documented against it. They run first and decline
outside their fragment.

**(B) The invention** — everything behind them: `refiners.flow` (`ruCheckerResidual*`),
`hilbert.flow` (a propositional certificate), `literal_closers.flow`, the `checker-*` scoped
searches, plus the type layer in `type_table.flow`. This carries most of today's coverage and is
NOT a transcription of anything.

Phase-by-phase status:

| Mizar phase | Russell (A) transcription | Russell (B) invention | gap |
|---|---|---|---|
| premise assembly | `by.refs` on the board, read by both generators | ignored; premises = hyps + saturation + **relevance heuristic** | ★ the deviation, §4.1 |
| definitional expansion | partial (unfold tables) | `unfold.flow` scans, `type_table.flow` behind `ensure-type=1`, OFF by default | mandatory in Mizar, optional here |
| `RemoveIntQuantifier` | `unifier_plan.flow:117` | ad-hoc skolemization in `hilbert.flow` | duplicated |
| DNF per disjunct | `unifier_plan.flow` | absent — one propositional refutation over selected literals | structural |
| `Equate` | `equalizer_plan.flow` + `congruence.flow` | eq-rewrite + congruence fallback | duplicated |
| rounding-up | — | `ruTyEnvironment` (flag-gated) | not a transcription |
| `PreUnification` | — | absent | ★ missing entirely |
| `Verify` / `Unifiable` | `unifier_plan.flow` | BFS + certificates | duplicated |
| scheme (`from`) | — | — | ★ missing entirely; translator drops scheme refs (`miz2ru.flow:27`) |
| properties, requirements, identifications | — | — | ★ missing; not carried by the translator |
| numerals / arithmetic | — | — | out of scope by decision |
| bounds | — | wall-clock budgets everywhere | ★ Mizar uses structural limits only |

---

## 4. The deviations, and the Mizar answer to each

### 4.1 Premise selection (the one that provoked this document)

WE DO: build a large fact pool (all hypotheses + saturation over everything in scope), then filter
it by atom connectivity before the decision procedure, because that procedure is exponential in
atom count (`refiners.flow`, `ruResidualFallback`). Measured: 33 401 `certificate/relevance`
refusals in one `subset_1` run — the largest failure class by an order of magnitude.

MIZAR DOES: no selection. The premise set IS ¬thesis + linked + cited (§1.1), and it is small
because the author made it small.

THE CORRECTION: on a step carrying a `by` list, the premise set is exactly that list plus the
negated thesis plus the linked proposition; the relevance filter is deleted, not tuned. The
pool grows only by the mechanisms Mizar itself has: definitional expansion of occurring atoms,
rounding-up, and Unifier instantiation.

PREREQUISITE, measured: six of the 32 names `subset_1` cites resolve to nothing today, because the
translator drops Mizar definitions of foundation-provided operators (`d1_zfmisc_1`, cited 18
times). Keeping them, measured, costs coverage while the heuristic pool is still there
(16/53 → 11/53) — because it makes the pool larger. Under 4.1 it is the whole pool, which is the
opposite situation. The two changes must land together.

### 4.2 Search where Mizar has none

WE DO: bounded BFS over lemma scopes, or-else cascades with wall-clock slices, certificate
retries, case-split budgets.

MIZAR DOES: three bounded phases, no search, structural limits from `limits.pas`.

THE CORRECTION: the decision must be a transcription (path A) end to end. Search-based closers are
legitimate only as the COMPLETER's fixed patterns (§2), never as the decision.

### 4.3 Optional type layer

WE DO: `ensure-type=1`, off by default; guard discharge, functor definitions and sethood are
opt-in extras.

MIZAR DOES: types and clusters are not optional — every equality class carries its type and its
rounded-up cluster, and the Equalizer's contradiction rules read them.

THE CORRECTION: the type environment is part of Equate, unconditional, inside the transcription.

### 4.4 Missing phases

`PreUnification` (§1.5) does not exist on our side in any form — no one-difference rule, no
property-driven inequalities. Constructor properties, requirements and identifications are not
carried by the translator at all. Scheme justifications are dropped. These are not optimisations;
they are parts of the algorithm, and each missing part shows up as unexplained failures.

### 4.5 Budgets

WE DO: wall-clock slices per stage (and they are the documented cause of measurement noise and of
"borderliner" theorems).

MIZAR DOES: structural constants only (`limits.pas`).

THE CORRECTION: already the agreed budget doctrine — Mizar-family atoms get Mizar's structural
caps, one outer time limit for the whole step, no inner wall-clock.

---

## 5. Target design

Stages, in the user's numbering, with Mizar's phases inside them:

**Stage 1 — preparation** (per justification)
- 1a assemble premises: ¬thesis, linked proposition, cited assertions (`by` list). NOTHING else.
- 1b definitional expansion of the atoms occurring in them.
- 1c quantifier handling: `DistributeQuantifiers`, `RemoveIntQuantifier` with fresh constants.
- 1d normalize to DNF; the result is a list of disjuncts, each a ground literal set.
- artifacts: for each expansion and each instantiation, the proof step that licenses it.

**Stage 2 — decision** (per disjunct, must refute every one)
- 2a `Equate`: equality classes, congruence closure, types + clusters, rounding-up, requirements.
- 2b `PreUnification`: property-driven and one-difference inequalities.
- 2c `Unification`: `Verify` per universal, then pairs.
- artifacts: the refutation certificate per disjunct, in the form the completer consumes.

**Stage 3 — proof construction** (Russell's addition)
- one fixed pattern per decided step kind; no search. The catalogue is finite because stage 2's
  step kinds are finite.

**Stage 4 — embedding**
- splice into the PVT, kernel-verify, write back. Unchanged.

**Composability** (secondary, but the shape is natural): each of 1a–1d, 2a–2c is a separate atom
over a shared board artifact (the disjunct set, the eq-class structure, the substitution lattice),
so the whole is a composition, not a monolith. Decide this only after the unit works.

---

## 6. Work order

1. Carry the missing environment data through the translator: constructor properties, requirements,
   identifications; keep definitions of foundation-provided operators so citations resolve.
2. Stage 1 as a transcription, replacing the current premise assembly (4.1 + 4.3).
3. `PreUnification` (4.4) — it is small and self-contained.
4. Complete stage 2 by extending the two existing generators to the full disjunct loop.
5. Retire the (B) battery family by family, measuring after each removal.
6. Scheme justifications.
7. Replace wall-clock budgets with structural limits.

Each step is measured on the standard gates before the next begins.

## 7. What is NOT yet read

Honest boundary of this document: `Equate` (`equalizer.pas`, 4868 lines) was read structurally,
not line by line — its contradiction rules are enumerated from `SetContr` sites, not from a full
reading. `schemes.pas`, `identify.pas`, `polynom.pas` were not read. `analyzer.pas` (thesis
transformation, which our translator replaces) was not read. These must be read before the
corresponding work item, not before this map is agreed.
