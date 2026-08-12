# Porting the MML — the general plan

The goal is the whole Mizar Mathematical Library in Russell, with real proofs
that the original Metamath checker verifies. This document fixes the method,
the order of work, the metrics, and a running log of what actually happened.

---

## 1. The principle

**Transcribe Mizar's own algorithms. Never add a rule for a single article.**

Two campaigns have been run. The one that added hand-written cases for the
theorems in front of it reached 344 theorems on five articles and stopped
growing — every new class of theorem cost new code. The one that instrumented
Mizar's checker, recorded its decisions, validated the record offline and
emitted proofs from it produced verified proofs from a general mechanism.

The lesson is not "we spent too long on five articles". It is that coverage
grows with the *generality of the machinery* and not at all with the number of
special cases. Progress under a general method is non-linear: a mechanism that
handles Mizar's type layer moves coverage from ~1% to ~35% at once, because
that is how the library is distributed. Progress under special cases is flat
by construction.

**The proven chain**, to be repeated for every piece of Mizar machinery:

```
instrument the ported Mizar routine
  -> record its decisions per inference
     -> validate the record offline (replay with no prover)
        -> emit Russell proofs from the record
           -> gate with the ORIGINAL Metamath checker
```

Each arrow is separately measurable, which is why the chain is worth keeping:
a failure tells you which link broke.

---

## 2. The map of the library (measured 2026-08-08)

Every MML theorem statement was classified by the machinery needed to write it
down, read from Mizar's own analyzed XML with constructor kinds resolved
through each article's accommodated constructor list. 75,152 real statements
(15,956 placeholder statements from articles analyzed with blocks skipped are
excluded). Scripts: `scripts/analysis/mml_layers/` (see §7).

| support set | theorems | share |
|---|---|---|
| `{}` — `=`, `∈`, `set` only | 873 | 1.2% |
| `+ {attr, mode}` — soft types | 26,786 | 35.6% |
| `+ {struct}` — structures | 52,873 | 70.4% |
| `+ {num}` — numerals/arithmetic | 73,559 | 97.9% |
| `+ {fraenkel}` | 75,141 | 100.0% |
| `+ {choice}` | 75,152 | 100.0% |

Feature definitions (the formal criterion):

- **mode** — a type whose mode constructor is not HIDDEN's `object`/`set`
- **attr** — an adjective in a type, or an attribute constructor
- **struct** — a constructor of kind G/L/U/J (aggregate, structure mode,
  selector, forgetful functor)
- **fraenkel** / **choice** — a Fraenkel or choice term
- **num** — a numeral

A constructor absent from an article's own `.atr` is one the article declares
itself, so a lookup miss means a real soft type, never `set`.

**Consequences that set the order of work.** There is no type-free stratum
worth targeting: it is 1.2%, and the five articles worked on so far live
inside it. The soft-type layer is the entry ticket to 98% of the library.
Structures double the reach again. Numerals take it to 98%. Fraenkel and
choice are rounding error.

---

## 3. Where we stand (2026-08-08)

- Mizar verifier port: **done and exact** on the whole library (M1–M4).
- Checker derivation record (merge log, equality provenance, match
  provenance, class table, resolution): **done**, offline validator replays
  **93%** of refutations with no prover.
- Deterministic by-step emitter (`mizar-to-ru emit=1`): closes 155/241 steps
  on xboole_1, 97/290 on zfmisc_1, 27/130 on subset_1; 48 / 11 / 2 theorems
  fully proved, all verified by the original Metamath checker. It now proves
  and uses the checker's recorded premises (S-B, witness half).
- Translation: all 300 articles of the dependency order translate and parse
  (S-A). 3,422 of their 10,469 theorems are still dropped as unrenderable —
  3,308 numerals, 119 choice terms, 22 flexary formulas.
- Best result ever, by the (now retired) tactic prover: 344 theorems.
- **Coverage of the library: about 0.5%, all of it inside the 1.2% band.**

---

## 4. Stages

Each stage names the Mizar machinery to transcribe, the artifact it produces,
and the gate that closes it. Stages are ordered by the coverage they unlock,
not by difficulty.

### S-A — statements: translation must not lose articles
*Machinery*: none new; this is our own emission discipline.
*Problem*: generated Russell articles fail to parse on name collisions
(a predicate and a functor receiving one name, symbols colliding with
keywords). An earlier survey put the loss near half the theorems in a sample;
the figure predates the in-memory pipeline and must be re-measured.
*Artifact*: a naming rule that is total and collision-free by construction.
*Gate*: percentage of MML articles that translate and parse, measured over a
few hundred articles in dependency order.
*Harness*: `scripts/analysis/mml_translate/` — `dep_order.py` (the topological
order the articles must be translated in), `run_translate.sh N` (translate the
first N and record status + diagnostic per article), `report.py` (status
counts and failure buckets), `name_stability.py` (is a constructor's name a
function of the constructor?).

### S-B — by-steps: consume the record instead of re-deriving
*Machinery*: the checker record that already exists.
*Problem*: the emitter re-derives each step from a fixed lemma catalog and
does not read the recorded witnesses, case split or kill list. The campaign's
central claim is therefore untested.
*Artifact*: record-driven emission — witnesses from `<With>`, the case split
from the disjuncts, kills from `<Cand>`/basis.
*Gate*: by-steps closed on xboole_1/zfmisc_1 from the record, against the
current catalog-driven numbers; and the catalog must shrink, not grow.
*State (2026-08-08)*: the WITNESS half is done — the record is joined to the
proof by matching each recorded instance against the statements the step could
cite (and, for the premises Mizar uses with no citation at all, against every
environment statement with the same skeleton), and the instance is proved by
term-instantiation + sethood + term congruence. Closures barely moved, but the
record was measured to REPLACE the catalog's instance machinery — see the
findings log. The case split and the kill list are still untouched.

### S-C — the type layer (unlocks ~35%)
*Machinery*: the analyzer's type and attribute computation — mode assignment,
adjective sets, cluster registrations (functorial, conditional, existential)
and the rounding-up fixpoint.
*Problem*: Mizar derives type facts silently and uses them as free premises;
Russell must prove each one. We have no record of this computation at all.
*Artifact*: a type-derivation record (which registration fired on which term,
in which order), its offline validator, and emission of the corresponding
Russell proofs against the translated registrations.
*Gate*: subset_1 — 53 theorems, dominated by the type layer, currently 2 —
then a second type-layer article with **no new code**.

### S-D — structures (unlocks ~70%)
*Machinery*: aggregates, selectors, structure modes, forgetful functors;
structural type widening.
*Artifact*: a representation of Mizar structures in the foundation plus the
emission patterns for field access and widening.
*Gate*: a structure-carrying article closed end to end, then a second one
with no new code.

### S-E — numerals and arithmetic (unlocks ~98%)
*Machinery*: Mizar's requirements (BOOLE, SUBSET, NUMERALS, REAL, ARITHM) and
the checker's polynomial evaluator.
*Artifact*: a mapping from each requirement to foundation lemmas, and a
replay of arithmetic evaluation steps.
*Gate*: an arithmetic-carrying article closed end to end.

### S-F — the tail
Fraenkel terms (2%), schemes, choice (11 theorems). Scheme instantiation is
second-order and may need its own record.

---

## 5. Cross-cutting constraints

**Proof economy.** A one-line `by` currently costs a median of 97 emitted
steps with a long tail, and an article runs to megabytes. Size is not a
cosmetic issue: the project's purpose is a readable bridge, and the emitted
text is the deliverable. Remedies, in order: name recurring patterns as
foundation lemmas instead of expanding to primitives; keep sharing proofs as
a DAG; replay rather than search, since the search is what produces the tail.
**Track steps per by-step as a first-class metric.**

**Soundness and what the gate certifies.** Today "proved" means proved from a
generated axiom base — cited library theorems, definitions, registrations,
per-functor sethood, and every still-open theorem turned into an axiom
(hundreds per article). The transitive measure — theorems proved from the
foundation with nothing assumed — is only meaningful once coverage is high,
but it is the number that finally decides whether the port is real. Schedule
it when an article reaches ~95%.

**No per-article patterns.** The health metric of the whole plan: *articles
closed without writing new code*. If it stays at zero, the method has
regressed to the first campaign and no budget will produce the library.

---

## 6. Metrics

| metric | meaning | today |
|---|---|---|
| layer coverage | theorems proved / theorems in the layer | ~0.5% overall |
| new code per new article | lines added to close article N+1 | high — must reach 0 |
| steps per by-step | emitted proof size for one justification | median 97 |
| Metamath gate | verdict + proved count from the original checker | green, 33/10/2 |
| transitive proof | proved from the foundation, nothing assumed | not yet meaningful |

---

## 7. Reproducing the measurements

The layer census reads only Mizar's own files:

- per-article features and dependencies — `.evl` (directives), `.atr`
  (accommodated constructors), `.miz`
- per-theorem statement features — `.xml` (`JustifiedTheorem` →
  `Proposition`), constructor kinds resolved through `.atr`

Placeholder statements (`<Verum/>` bodies from articles analyzed with skipped
blocks) must be excluded or they fake a large type-free core.

---

## 8. Findings log

Append one entry per surprise: what was expected, what happened, what it cost,
and what changed in the plan. This section is the reason the document exists.

### 2026-08-08 — the type-free core is 1.2%, not a viable target
*Expected*: a sizeable set-theoretic core to finish first.
*Found*: 873 theorems, 1.2%. The five articles worked on so far sit inside it.
The first layer with mass is soft types.
*Consequence*: the plan is reordered around the type layer; "finish the core
first" is dropped.

### 2026-08-08 — a large fake core in the census
*Expected*: every analyzed article carries real statements.
*Found*: ~16,000 statements are `Verum` placeholders (`xprimes0/1/2` and
similar generated articles are analyzed with blocks skipped). They inflated
the type-free core from 1.2% to 18.5%.
*Consequence*: the census excludes them; any future statement-level
measurement must do the same.

### 2026-08-08 — article-level dependency closure does not stratify
*Expected*: articles would fall into layers by what they import.
*Found*: 95% of articles transitively declare everything, because environments
are unions over whole articles. Stratification only exists at theorem level.
*Consequence*: layers are defined per theorem statement, not per article.

### 2026-08-08 — I measured from articles Russell had already REJECTED
*Expected*: that the emitted articles verify, since `translate` runs `verify`
and prints its verdict.
*Found*: it prints and carries on. The article is written whatever the verdict,
so a rejected article went to disk, through the Metamath gate, and into the
numbers. Three xboole_1 theorems and two of zfmisc_1's did not verify, and the
by-step counts reported for them were worthless. The Metamath gate did not
catch it either: an unverifiable proof can still export to something the
checker accepts, or the theorem is exported as an assumed axiom.
*Cause of the bad proofs*: a citation must state what the cited assertion
actually says, and the emitted shape varies — an env theorem/definition/
registration is an AXIOM over the ∀-closed statement (an instance needs the
prefix eliminated), while a THEOREM's proposition is whatever its skeleton left
(∀-closed for a direct `by`, the open matrix after a `let`) and is instantiated
by Russell when a step names it. Also, spcgv is disjointed(A x, ps x) and only
the witness's variables were being declared, never the instance's.
*Consequence*: `translate.rus` now ASSERTS on the verdict — open `?` steps stay
allowed, a false verdict stops everything. THE ORDER IS: Russell verifies
first, the Metamath checker judges second. Any measurement taken without the
first is not a measurement. Corrected figures: 130/241, 91/289, 27/130
by-steps and 33/116, 10/140, 2/53 theorems on xboole_1/zfmisc_1/subset_1.

### 2026-08-09 — the emitter had not COMPILED for an hour
*Expected*: that `build_java.sh` builds, since I was reading its error lines.
*Found*: it was dying with a StackOverflowError inside the flow typechecker,
which my grep filtered away, so several rounds of "no change" were measured
against a jar that predated the code. Cause: three recursive lambdas written by
self-application (`\self, t -> … self(self, a)`), which the typechecker does
not converge on.
*Consequence*: they are plain top-level functions now. And the rule that
already applies to `verify.success` applies to the build: never read a tool's
output through a filter that can hide its failure.

### 2026-08-09 — S-C: what a type guard needs, exactly
*Expected*: matching definitions better would close subset_1's steps.
*Found*: matching a FUNCTOR definition needs the TERM, not the atom — it says
`f(ā) = …` while the step says something about `f(ā)` — and the record's symbol
is what makes that exact. With it, instantiation rose (subset_1 39 → 47,
relat_1 87 of 327) and the first type guards were discharged (relat_1 7).
subset_1's remain, for two reasons the trace names:
  * `element (k1_subset_1 x4) 𝒫 x4 → …` with an EMPTY context — the guard is a
    type fact from a REGISTRATION, which is the cluster half of the record and
    is still unconsumed;
  * `element x28 𝒫 x4 → …` with an empty context — the guard is the goal's own
    ANTECEDENT, and the reduction has no deduction step: an implication goal
    goes to the certificate whole rather than assuming its antecedent.
*Consequence*: two mechanisms, in that order. Neither is tuning.

### 2026-08-09 — S-C: the machinery is in, the SELECTION is what is missing
*Expected*: recording the functor `equals` expansions and discharging type
guards would close subset_1's steps.
*Found*: both work and neither closes anything. The functor expansions are
recorded (6,167 on subset_1, SUBSET_1 def 2/3/4 among them); a guarded
definition whose guard is in the context is discharged by ax-mp. But the
instances the emitter actually builds are the wrong ones — of 92 definitions
named at subset_1's emitted steps, 39 are instantiated and 0 are guarded ones,
because the selection matches a definition's atom against a step atom and the
generic `A = B iff A ⊆ B ∧ B ⊆ A` matches everything. Ordering by shared
article symbol was not enough.
*Consequence*: the open work is the SELECTION — pick the definition by its
definiendum, which means knowing each definition's defined symbol rather than
guessing it from the statement. The record already names the definition; what
it does not say is which symbol occurrence in the step it was applied to. That
is the next thing to instrument: the expansion record should carry the TERM it
rewrote, not only the definition's number.

### 2026-08-09 — S-C first consumption: right instances, not yet enough
*Expected*: feeding the emitter the definitions the checker expanded would
close subset_1's open steps, since those premises have no citation of their own.
*Found*: the instances are built correctly — 70 of 151 named definitions on
xboole_1, 143 of 242 on zfmisc_1, 39 of 92 on subset_1 — and nothing closed.
(One prerequisite was missing first: an imported DEFINITION is emitted in its
own article as an AXIOM, and only imported THEOREMS were indexed, so every name
the record produced resolved to nothing.)
*Why it is not enough*: subset_1's definitions are TYPE-GUARDED —
`d4_subset_1 : ∀X∀A ( A is Element of 𝒫 X → (X ∖ A) = … )`. Using one means
discharging the guard from the context and then REWRITING with the equation it
yields. That is equality reasoning, and the certificate offered it as a
propositional fact.
*Consequence*: the next mechanism is guarded definitional rewriting, and the
one after it is the cluster half of the record (which supplies the guards that
the context does NOT already carry — the non-emptiness a registration proves).

### 2026-08-09 — the equality family wanted a SMALLER PROOF, not more facts
*Expected*: the theorems that are one step short are short of premises.
*Found*: the certificate never said which limit it hit. Made to say so, it
reports that the ∀-matrix of `x5 ∩ (x7 ∖ x9) = (x5 ∩ x7) ∖ x9` IS decided from
its four unfolds and then runs past 600 emitted steps. Across the three
articles, 48 / 27 / 13 failed steps were provable and rejected only for length.
The cost is driven by the number of BICONDITIONAL FACTS — each doubles the
tableau — not by the three atoms underneath.
*Consequence*: membership between compound terms is now REWRITTEN rather than
decided. Each side goes to a normal form over `v ∈ <variable>` atoms by
chaining the foundation's own elun/elin/eldif through orbi12i/anbi12i/notbii
and bitri, leaving the certificate a propositional residue with no
biconditional facts. xboole_1 136 → 155 steps and 38 → 48 theorems, zfmisc_1
94 → 97 and 11, all Metamath-gated (48 / 11 / 2).
*Open, with a measured price*: imported theorems are still not citable — their
emitted shape is decided in their own article — and that costs relat_1 about 11
by-steps (93 → 82). It is the next thing to fix.

### 2026-08-08 — a proof-only variable was exported as MANDATORY
*Expected*: after proving an iff goal as its two implications closed five more
theorems and the Metamath checker rejected the result, that the defect was in
the export of that proof shape.
*Found* (once the pipeline was made to stop on a failed Russell verify, and the
export made to complain instead of skipping): a theorem declares every variable
it uses, its proof's included, and the export took that declared list as the
theorem's MANDATORY floating hypotheses. A variable a certificate invents then
became mandatory, so every citation of that theorem owed an argument that no
substitution can produce — and the push skipped it silently, shifting the whole
RPN. The checker failed further along, on an unrelated-looking step.
*Consequence*: mandatory now means what Metamath means — the variables the
STATEMENT mentions; everything else declared joins the optional floatings. With
that fixed the iff-split is sound end to end: xboole_1 136 steps / 38 theorems,
zfmisc_1 94 / 11, enumset1 15 / 11. THE ORDER MATTERS: this was findable only
because Russell verifies first and the export refuses to write a proof it
cannot argue.
*Also measured, and rejected*: raising the tableau cap (5000/600 → 40000/3000)
closes more but produces a 113,000-line proof for a single theorem — the blowup
the economy constraint names. The cap stays.

### 2026-08-08 — S-B, second pass: the join was the bottleneck, not the record
*Expected*: after the first pass, that the record simply had little to add.
*Found*: it had plenty; the emitter could not reach it. Three fixes, each
measured on its own:
  * a premise is often used as a PIECE — the checker splits a definitional
    biconditional and takes a conjunct — so the pieces are offered as match
    candidates (biimpi/biimpri/simpl/simpr, each one Hilbert step away);
  * statements must be INDEXED under every piece too, not just under
    themselves: subset_1's meaning of `Element of` is the first conjunct of
    d1_subset_1, never the whole of it;
  * the congruence walk knew only the foundation's ∈ and ⊆, so any premise
    about an article predicate could be matched but never eliminated — the
    translator already emits `pr_<p>_cong<k>d` per slot, which the walk now
    chains with bitrd exactly as the term walk chains eqtrd.
*Result*: recorded premises proved 28 / 61 / 48 of 132 / 169 / 77, by-steps
closed 130 / 93 / 28 (from 128 / 85 / 16). subset_1 — the S-C gate article —
went from 16 to 28 by-steps, and zfmisc_1 from 85 to 93.
*Consequence*: the record pays off in proportion to how well it is joined to
the article's own statements. What remains unreachable needs TWO premises
composed (a type plus a definition), which is where S-C's record comes in.

### 2026-08-08 — S-B: the recorded premises do not move the by-step count
*Expected*: the campaign's central claim — that consuming the checker's record
instead of re-deriving each step raises the number of by-steps closed.
*Found*: after making the record usable (the binder-order fix below took
fact-complete inferences from 53 to 119 on xboole_1) and teaching the emitter to
PROVE a recorded instance from its premise, the closures moved
128 → 130 / 85 → 86 / 16 → 16 on xboole_1 / zfmisc_1 / subset_1. The recorded
premises are largely facts the unfold ladder already had, and each extra fact
also eats the certificate budget.
*But*: with the catalog's own instance machinery switched OFF and only the
record's facts offered, the counts were 129 / 86 / 16 — the record REPRODUCES
what `mizEmitChars` + `mizEmitDerive` derive, within one closure, at a lower
budget cost (skipped-over-budget fell 8 → 4 and 47 → 39). That is the S-B gate
read the other way: the claim to test is not "the record closes more" but "the
record lets the catalog shrink", and that one holds.
*Consequence*: the remaining record content — the case split and the kill list
— is what is still untested. Raising the certificate budget is not the way:
maxFacts 24 did not terminate, as the earlier cost-bomb finding predicted.

### 2026-08-08 — what it costs to USE a Mizar witness
*Expected*: feeding recorded witnesses to the emitter would be plumbing.
*Found*: three pieces of proof machinery were missing, none of them
article-specific. Instantiating at a TERM (Mizar's witnesses are terms, the
ladder could only instantiate at a variable) needs `spcgv`, which needs the
witness's SETHOOD — proved bottom-up from `vex`, the foundation's own lemmas for
reused functors, or the article's `sh_<f>` axiom — and congruence INSIDE a term,
without which `∀A∀B (A ⊆ A ∪ B)` cannot be instantiated at all, since
substituting A must reach into `A ∪ B`.
Two shapes produced proofs Russell accepted and the Metamath checker rejected:
instantiating a binder at ITSELF (spcgv's disjoint-variable condition forbids
it) and `spi`, whose binder occurs only in its hypothesis so the export cannot
determine it. Both now go through the implication form `sp` + ax-mp.
*Consequence*: the Metamath gate is what catches an unsound emission; Russell's
own verify does not. Keep it on every emitter change.

### 2026-08-08 — S-A gate: 300 / 300 articles translate and parse
*Expected*: about half the theorems lost to naming collisions, per the earlier
survey.
*Found*: after the naming work below, all 300 articles of the dependency order
translate and verify (open steps allowed) — 15,432 theorem statements. The
survey figure is retired: articles are not lost to collisions.
*What IS lost*: 3,422 of 10,469 theorems inside those articles are dropped
because the translator cannot render a construct — 3,308 numerals, 119 choice
terms, 22 flexary formulas. That is S-E and S-F work, not a naming problem, and
the translator now reports it per article ("NOT translated: N of M theorems —
Num 43").
*Method note*: a dependency-ordered census must start from an EMPTY `mizar/`
directory. Leftover `.ru` files from a run with different naming are read as
dependencies and produce failures that belong to no version of the code.

### 2026-08-08 — the Russell name of a constructor depended on the article
*Expected*: a constructor keeps one name across the library.
*Found*: the contest for a notation word was decided inside the environment of
the article being translated. The word `real` is the notation of NUMBERS'
functor AND of XREAL_0's attribute: an article importing only the first names
it `real`, one importing both drops the name from both and falls back to
`k1_numbers`. So the same constructor has two names in two articles; they no
longer link, and importing both raises "decl 'k1_numbers' is already added" —
which is exactly how the first 300-article census died (square_1, nat_1, int_1,
membered, xxreal_1). Measured over 200 articles: 26% of the constructors with
a readable name were readable in one article and mechanical in another.
Two joins were also wrong underneath it: a `.eno` <Pattern> names its
constructor by the ENVIRONMENT index (the `.atr` `relnr`, 81..84 for TARSKI's
functors in numbers.atr), not by the number inside its defining article, and a
format of kind K is a LEFT BRACKET whose symbol lives under `.dcx` kind K —
reading it as an "O" functor symbol returned an unrelated word.
*Consequence*: the contest is decided ONCE over the whole library. Every
article's notations are read, each word goes to the claimant whose defining
article comes first in MML canonical order, everyone else keeps the mechanical
name — in every article alike. The table is cached as `<mml>/russell_names.tsv`
(word, owning constructor); build takes ~15 s, delete the file to rebuild.
Gate unchanged: xboole_1 still emits 128/242 steps and the original Metamath
checker still proves 33.

### 2026-08-08 — one constructor, one name: the four defects behind it
*Expected*: making the name-contest global would be the whole fix.
*Found*: three more, each of which alone reintroduced the duplicate-declaration
crash.
  1. The name was attached to a table KEY, not to a constructor. One
     constructor sits under several environment keys (a redefinition aliases
     HIDDEN's ∈ into TARSKI's entry) and only one carries a pattern, so an
     article used both `∈` and `r2_hidden` for one relation.
  2. An article cannot see the notation of its OWN constructors — a
     constructor's word lives in the `.eno` of the articles that IMPORT it.
     So funct_1 called its attribute `v3_funct_1` while every importer called
     it `constant_`. The name table is therefore read backwards too
     (constructor → word), which is what finally made the name global.
  3. An ANTONYMIC pattern spells the NEGATION (`nin` for ∈, `<>` for =).
     Adopting it named a relation `nin` in three articles.
  4. Sibling articles legitimately declare the same imported symbol, so an
     identical re-declaration is now a no-op in all three loader paths
     (`ruDeclsAddDecl`, `ruLangAddDecl`, `ruAssertionsAddDecl`); anything that
     differs still stops the load.
  5. Foundation words (∈/∪/⊆/…) were left OUT of the table, because it was
     built from `.dcx` clean words and could not see bracket or operator
     spellings. They therefore kept drifting: `newton`, whose environment also
     holds SUBSET_1's `In` functor as a third claimant for `in`, wrote
     membership as `r2_hidden` while `rvsum_2` wrote `∈`, and 31 of 300
     articles failed. The table now renders the full spelling from `.frm` +
     `.dcx` — a format with a `rightsymbolnr` is a bracket whose halves are a
     `K` and an `L` symbol — and runs it through the same `mizSymbolName` the
     `.msx` path uses, so operators are covered and decided globally. Bracket
     NAMES (singleton/pair/triple/opair/×.) remain excluded by name: bracket
     symbols are in no `.dcx` at all (`{`/`}` come with the language, not with
     a vocabulary), so any claimant the table finds for one is a coincidence —
     `pair` went to XTUPLE_0's `is pair` attribute and took the bracket term's
     grammar rule.
*Consequence*: 300 / 300. The lesson for the port: a name
that depends
on the reading context is not a name, and each of these defects was invisible
until articles were translated in bulk, in dependency order.

### 2026-08-08 — the phantom import cycle
*Expected*: "cyclic imports" meant a real cycle.
*Found*: a relative `import-roots` in the library config does not resolve from
another working directory, so file-read sources keep their full path as module
id while importing articles name them `mizar/<a>`; the toposort then hangs on
an id mismatch. Only articles importing a sibling exposed it, which is why one
article passed and the rest failed.
*Consequence*: scripts pin absolute import roots; the Metamath gate now
reaches every article.

### 2026-08-09 — the emitted article was WRONG in three ways, and the counts hid it
*Expected*: that the numbers in the plan described articles on disk.
*Found*: the emitter prints its counts BEFORE the generated text is parsed and
verified, so an article Russell rejected still reported by-step and theorem
figures. subset_1 did not parse at all and zfmisc_1 was disproved; both had
been reported as results. Three separate defects:
  1. an OPEN step's variables were declared nowhere — the header carries only
     statement variables and the proof block only certificate variables, and a
     step left open belongs to neither (t47_subset_1 binds x901 in an open step
     that no hypothesis and no conclusion mentions);
  2. a cited theorem was stated as the PIECE of it the recorded fact came from,
     so the step read `¬(A ∧ B)` while t56_zfmisc_1 proves `¬(A∧B) ∧ ¬(C∧D)`;
  3. an emitted theorem stated LESS than the Mizar theorem: its prop is the
     last skeleton step, and a walk that misses the closing inference ends
     short — t40_xboole_1 came out as ONE inclusion of `(X ∪ Y) ∖ Y = X ∖ Y`,
     which is true, verifies, and is not XBOOLE_1:40. zfmisc_1 then cited it
     with the equation and Russell disproved zfmisc_1.
*Consequence*: the third is the one to remember — a theorem that states less
than it should still verifies, so only its CITERS can detect it, one article
later. The emitted prop is now the statement (appended as an open step when the
skeleton falls short) whenever the skeleton has no let/assume hypotheses.
Corrected figures, every article Russell-verified: xboole_1 186/268 by-steps
48/116 theorems, zfmisc_1 132/318 11/140, subset_1 29/128 2/53, enumset1 18/95
11/87, relat_1 89/405 1/175.

### 2026-08-09 — a constructor has a TYPE, and the article did not say so
*Expected*: the type guards were unreachable because the record's cluster half
was unconsumed.
*Found*: one half of every Mizar definition was never translated at all.
`func {}E -> Subset of E equals {}` states an equation AND a type; the article
had `k1_subset_1 E = ∅` and nothing anywhere said `{}E is Element of bool E`.
That type fact is what the checker uses without citing it, and it is the guard
of every type-conditioned definition. It is now emitted per constructor as
`∀L̄ ( argGuards(L̄) → typGuards(resultTyp, f(L̄)) )` — the FCluster shape with
the constructor's own type — and the discharge derives a guard it cannot find
in the context, joining a conjunction of guards with pm3.2i and recursing when
a guard is itself a type fact. A guarded statement is also indexed by what it
CONCLUDES, since the axiom giving `element (k4 …) 𝒫 X` lives under
`imp(and(…),…)`.
*Consequence*: the facts are correct and present; they closed one more step.
Of subset_1's five guard requests exactly one is a constructor type. The other
four are the GOAL'S OWN ANTECEDENT.

### 2026-08-09 — the deduction step works and closes nothing
*Expected*: assuming the antecedent would unlock the type-guarded definitions.
*Found*: it fires and it is neutral — all seven gate articles closed exactly
the same steps as before. The certificate can now be told that one fact is
ASSUMED and concludes `A → goal` instead of `goal` (the refutation is
unchanged; only the final discharge differs: a1i/id, jca, syl), and
`A → ∀x C` is swapped to `∀x (A → C)` by 19.21v so the peel reaches every
antecedent under the binders. What blocks the payoff is composition: the
deduction is a LEAF — once the antecedent is assumed the consequent goes to ONE
certificate — and t21_subset_1's consequent is a `⊆` goal wanting the df-ss
characterization and membership rewriting, which live ABOVE the certificate.
*Consequence*: the next piece is named exactly. Reducing UNDER an assumption
means lifting every combinator into deduction form (ax-gen, mpbir, impbii,
pm3.2i, spei), i.e. the reduction returns `A → X` rather than `X` while an
assumption is in scope. Nothing else in S-C is blocked on facts any more.

### 2026-08-09 — reducing UNDER the assumption is what paid
*Expected*: assuming the antecedent at the certificate would be enough.
*Found*: it was worth nothing until the REDUCTION itself ran under the
assumption. As a leaf the deduction handed the whole consequent to one
certificate, and a `⊆` consequent wants df-ss and membership rewriting, which
happen above it. With every combinator given its deduction twin — ax-gen ->
alrimiv, mpbir -> sylibr, pm3.2i -> jca, impbii -> impbid, and a second
antecedent assumed as the CONJUNCTION and re-curried with `ex` — xboole_1 went
48 -> 55 theorems and 186 -> 194 by-steps, relat_1 89 -> 94 steps, and the
original Metamath checker proves all 55 (zfmisc_1 11, subset_1 2, relat_1 1,
all four databases verified clean).
*Consequence*: the pattern to reuse — a reduction rule is only as useful as its
deduction twin, and the twins are all single lemmas that already exist in the
foundation. subset_1's own type-guard steps remain open, and the reason has
moved: t21 now reaches `x ∈ k3(X, k4(X,A,B)) → x ∈ k3(X,A)` under both
assumptions with 8 facts, and what it lacks is the two functor definitions at
that step, which its expansion record does not name. That is a RECORD question
(which definitions the checker expanded where), not a reduction question.

### 2026-08-09 — offering a step the definitions of its own symbols: no
*Expected*: the steps that still fail lack the meaning of their functors,
because the expansion record only names definitions where the checker recorded
an inference. So: index every definition and constructor type by the symbols it
mentions, and let a step with no record ask for the definitions of what it is
about.
*Found*: it closes nothing. subset_1 29, zfmisc_1 132, relat_1 94 by-steps —
identical, either way it is offered. Up front it also pushed 29 of subset_1's
128 steps over the certificate's 16-fact budget and LOST 3 that had been
closing; as a retry after a step fails it cost about 50% more wall clock for no
gain. Reverted; what survives is the soundness guard it exposed — spcgv is
disjointed(A x), so a witness that MENTIONS the binder (`∀x5 φ` at `𝒫 x5`) is
not an instance, and t1_subset_1 was disproved by exactly such a step.
*Consequence*: on these articles the binding constraint is the fact BUDGET, not
the supply of facts. More facts per attempt is the wrong direction; what pays is
a reduction that needs fewer of them (the deduction twins) or a selection that
picks the right two. Do not retry this without a budget answer first.

### 2026-08-09 — the one-step-short buckets, and what each of them wanted
*Method*: bucket the theorems that are ONE step short by the skeleton of the
step that fails (47 xboole_1 + 69 zfmisc_1 + 86 relat_1). One class held 31 of
them: an equality as the QED step.
*Found — two one-line answers*: the membership-unfold table had no entry for
SYMMETRIC DIFFERENCE and none for an ORDERED PAIR in a cartesian product,
though the foundation has both lemmas (elsymdif, opelxp). t101_xboole_1's trace
showed the reduction doing everything right and then stopping with
`x ∈ (X △ Y)` opaque. Adding the two entries: xboole_1 55 -> 63 theorems
(194 -> 209 steps), zfmisc_1 11 -> 16 (132 -> 139), all Metamath-gated.
*Found — one wall*: the next bucket is enumset1's 75, every enumerated set past
a triple. These are MEANS-definitions (`func {x1..x5} means x ∈ it iff …`), and
they never instantiated because Mizar's `it` occurs in no definiendum term, so
matching left it unbound. Binding it to the term the definition defines (the
left side becomes `t = t`, discharged by eqid) makes all 104 of enumset1's
definitions instantiate — and closes NOTHING. The instances are large and each
is a k-way case split with k up to 8; offering them put 43 of 95 steps over the
fact budget, and raising the budget (emf=24 ems=800) ran the JVM out of memory,
which is the same exponential the earlier budget experiments hit.
*Consequence*: enumset1 is not short of facts, it is short of a CHEAPER form of
this fact. What that family needs is a normal form for k-way membership (the
definition's two halves offered as separate pieces, or an n-ary
`x ∈ {a₁..aₙ} ↔ ⋁ x = aᵢ` unfold with its own lemma), not a bigger tableau. The
means-instantiation code is reverted with the rest — it is neutral where the
record does name definitions (relat_1 94, subset_1 29 unchanged).

### 2026-08-09 — the budget bound, so I stopped throwing steps away
*Expected*: after the last finding ("the fact budget binds"), that the answer
was to need fewer facts.
*Found*: half the answer. The other half was what the code DID when the budget
bound — it skipped the attempt, and the step was never tried: 47 of zfmisc_1's
319 steps, 9 of xboole_1's. Ranking the relevant facts (most goal atoms shared
first, smaller first among equals) and attempting with the best two thirds of
the budget and a third of the tree closes more AND runs faster: xboole_1 84 s
against 103, zfmisc_1 70 against 114, relat_1 29 against 49. Nothing is skipped
over budget in any article now.
Two pieces pull the same way: a ∀-quantified derived fact is instantiated at
the goal's element and replaced by the instance (dropped if it cannot be
instantiated — to the tableau it was one opaque atom that matched nothing), and
the checker's normalized iff is re-stated as the equivalence it means, which
the certificate proves for itself (269 facts across four articles).
*Consequence*: three lessons about this tableau, in order of how much they were
worth: never abandon an attempt you can make smaller; state a fact as what it
means, not as what the normalizer left; and a fact the tableau cannot use is
worse than no fact. The scoreboard: xboole_1 64/116 theorems, zfmisc_1 16/140,
enumset1 11/87, relat_1 2/175, subset_1 2/53, all Metamath-gated.

### 2026-08-10 — the contradiction goal, and where the remaining walls are
*Found — one cheap defect*: the QED step of a Mizar proof by contradiction
states `¬ ⊤`, which has no atoms, so the relevance filter kept NO facts and the
refutation ran empty-handed. Keeping all facts when the goal is atomless (the
ranking still caps the count) closed 8 such theorems: xboole_1 66/116,
zfmisc_1 17/140, subset_1 30/132 by-steps, all Metamath-gated.
*Found — where the three walls actually are, each checked this round*:
  1. the CLUSTER RECORD is thin: 46 <Round> per article, conditional clusters
     only, attributed to no inference (rounding runs during analysis, outside
     the checker window); F-cluster firings are not recorded at all. The
     "consume the cluster half" plan step dissolves into the constructor-type
     axioms that already exist. If a firing is ever needed per-step, the
     RECORDING side must be extended first (roundcl.flow, the FIRED branch).
  2. enumset1 (75 theorems) = the foundation regeneration with enum4-8
     membership lemmas; nothing inside the emitter reaches it.
  3. relat_1's imp(v1_relat_1, …) family (~38 theorems) = ∃-elimination with
     eigenvariables: the reduction is correct down to the membership matrix
     and then needs `every element of a relation is a pair` APPLIED, which no
     propositional certificate can do. This is the single biggest lever left
     in the gate set, and it is a reduction feature, not a fact feature.
*Consequence*: the S-C backlog is now exactly three named items, ordered by
size of the prize: eigenvariable ∃-elimination (relat_1 + zfmisc_1's t83/t86/
t127 class), foundation enum lemmas (enumset1), numerals (S-E, out of scope
here). The scoreboard: 96 -> 98 theorems Metamath-proved on the gate set.

### 2026-08-10 — what the ranked cut cannot see
*Expected*: t74_xboole_1's class (a `consider` witness in the hyps, goal a
negated equality) closes once the facts are right — everything needed exists.
*Found*: the dbg-facts trace shows the ranked cut keeping two degenerate
`A ↔ A` bridge instances — small, all goal atoms — while cutting the elin
unfold the proof needs, which shares NO atom with the goal directly (it
connects through a bridge, two hops out). Filtering the degenerate instances
is free and done. Re-ranking by closure wave (hop distance) fixes t74's class
in principle but LOSES 9 zfmisc_1 by-steps; combining wave with atom count
still loses 5. The two signals disagree about which steps they save, and the
atom count wins on net, so it stays.
*Consequence*: a static rank cannot know that a fact two hops out is
load-bearing. That knowledge exists — it is the proof structure the
certificate discovers when it succeeds — so the honest next form is either a
RETRY that swaps the cut when the first attempt fails on `unprovable` (cost:
one more certificate call per failed step), or the eigenvariable reduction,
which shrinks these problems below the budget instead of selecting harder.
Both belong to the same frontier item; the backlog stays at three.

### 2026-08-10 — ∃-elimination, first slice: the hypothesis case
*Built*: a `∃x φ(x)` HYPOTHESIS is now usable. The deduction reduction proves
`φ(x) → goal` with the binder as the temporary name, exlimiv turns it into
`∃x φ → goal` (its disjointness condition is the no-escape rule), ax-mp
closes. Tried last, per ∃ hypothesis; a failed attempt keeps the step's
original status (its overruns must not re-label an attempted step "skipped" —
the first version did, and the counts moved without any closure changing).
*Measured*: relat_1 95 -> 97 by-steps, all else exactly unchanged, gates
green. Modest as expected: a `consider` step's witnessed instance was already
in scope, so only the lone-∃ steps gain.
*Consequence*: the eigenvariable wall now lacks only the CHAIN: definition
instantiated at an element → its ∃ consequent eliminated (the piece built
here) → the witnessed equation `x = ⟨a,b⟩` used to REWRITE the goal's atoms
(the congruence walk exists). The next slice is wiring these three existing
pieces into one reduction case for `x ∈ X` facts where X is a relation.

### 2026-08-10 — ∃-elimination generalized; the wall wants the deduction twin
*Built*: many-binder elimination (an exlimiv chain, innermost first) and
DERIVED existential candidates — a definition instance `x ∈ X → ∃ā ψ` whose
antecedent is a context fact yields the ∃ by one ax-mp, then eliminates.
*Measured*: all five gate articles exactly unchanged. The trace on
t8_relat_1 names the reason precisely: the theorem is `∀X (X is Relation →
…)`, so the relation-hood assumption AND the element antecedent both live
INSIDE the deduction reduction, and the elimination fires only at top level,
where neither fact exists yet.
*Consequence*: the next slice is single and named — ∃-elimination UNDER an
assumption, discharged by exlimdv (`(φ → (ψ → χ)) ⊢ (φ → (∃x ψ → χ))`, in
the foundation), the deduction twin of exlimiv. It slots into mizEmitReduceA
the way jca/alrimiv/sylibr did into their cases; the candidate machinery
(context + derived ∃, multi-binder chains) is already built and waiting.
Note the pattern now repeated three times: every reduction rule's deduction
twin is ONE existing foundation lemma. The twin table so far: ax-gen→alrimiv,
mpbir→sylibr, pm3.2i→jca, impbii→impbid, exlimiv→exlimdv.

### 2026-08-10 — exlimdv lands; the chain fails at its FIRST link, not the middle
*Built*: ∃-elimination under the assumption, inside the deduction reduction:
`(asm ∧ ψ) → goal`, curry (ex), exlimdv per binder, and the existential
supplied by mpi (context fact / standalone block) or by projection + syl +
mpd when its antecedent is a conjunct of the assumption.
*Measured*: everything exactly unchanged, and the t8_relat_1 fact dump names
the cause: NO definition instance of v1_relat_1 reaches the step. The record
never names d1_relat_1 there — the checker gets relation-hood through the
TYPE channel (analyzer rounding), which is not attributed per inference. The
middle of the chain is built and idle; the first link is missing.
*Consequence*: the next move is to offer the definition OF THE ASSUMPTION'S
HEAD PREDICATE — asm `v1_relat_1 X` selects d1_relat_1 instantiated at X.
This selection is by the head symbol of a fact IN SCOPE, which is exactly
what distinguishes it from the measured dead end (definitions of every
symbol the step mentions, which blew the budget and closed nothing). It
needs the defs-by-symbol index restored, consumed only inside the
∃-elimination. The twin table stands at six rows and has not missed yet.

### 2026-08-10 — the full first-link design, traced to the lemma level
Before implementing, the whole t8_relat_1 chain was walked by hand against
the existing machinery. The candidate the ∃-elimination needs has the shape
  d1_relat_1 at X:   P ↔ ∀x (B(x) → ∃a∃b ψ)      P = v1_relat_1 X
with P a conjunct of the assumption and B(e) another conjunct (e = the
element the reduction introduced). The assembly, all under `asm`:
  1. the instance block (standalone axiom instance, mizEmitDefInstance with
     sym = the HEAD PREDICATE of the assumption's atom — restore the
     defs-by-symbol index, consumed only here);
  2. biimpi → `P → ∀x(B → ∃ψ)`; projTo(asm, P) + syl → `asm → ∀x(B → ∃ψ)`;
  3. the ∀ eliminated at e UNDER asm: mizEmitElimAt already builds the
     needed implication `∀x φ → φ[e]` as its intermediate step (m1) — it
     needs a variant that RETURNS that implication instead of consuming a
     source fact; then syl → `asm → (B(e) → ∃ψ[e])`;
  4. projTo(asm, B(e)) + mpd → `asm → ∃ψ[e]`;
  5. the existing exlimdv elimination consumes it (mpd at the end).
Every lemma is already in the foundation; steps 1, 2, 4, 5 exist as code.
The ONE new piece is the ElimAt variant in step 3 (implication out, no
source), plus the index restore. Nothing else in the chain is new.

### 2026-08-10 — the chain passes every link; one sub-proof left
*Built*: QNF applied to every derived fact; ∃ goals under an assumption
(mizEmitExGoalA: 19.8a + syl for the binder witness, walk + spcegv + vex +
syl for a term witness); goal-operator definitions and assumption-element
instantiation reaching the chain's sub-proof.
*Measured*: everything unchanged, verify green, and the CHAIN-FAIL diagnostic
puts t8_relat_1's failure at exactly one point: the final sub-proof
`(asm ∧ x = ⟨a,b⟩) → x ∈ (X ∩ (proj1 X ×. proj2 X))`. Every earlier link —
candidate, QNF, shaping, element match, instance implication, projections —
holds.
*Consequence*: the sub-proof needs `⟨a,b⟩ ∈ X` (eleq1 + mpbi under asm) and
`a ∈ proj1 X` / `b ∈ proj2 X` (normalized projection definition + spcegv at
the other component) — each derivable UNDER the assumption with machinery
that now exists, but arising inside the tableau where no ∃ step can happen.
Next slice: STRENGTHEN THE ASSUMPTION — derive these consequences first and
run the sub-proof from `asm ∧ ⟨a,b⟩∈X ∧ a∈proj1X ∧ b∈proj2X`. That is an
assembly job over existing parts (the walk, spcegv, projTo, mpbi are all in
place); no new lemma and no new machinery family is required.

### 2026-08-10 — strengthening works; the index looks in the wrong place
*Built*: the sub-proof of the elimination chain runs from a STRENGTHENED
assumption. Round 1 (equation into membership: eleq1+syl+mpbid) fires on
t8_relat_1 — `⟨a,b⟩ ∈ X` is derived and handed over. Round 2 (membership by
an ∃-bodied definition + the ∃-goal machinery) is built and finds nothing.
*Found*: the reason is the INDEX, not the machinery. proj1's definition is
d12_xtuple_0 — an imported article's means-definition — and the .eth
environment carries only what the article's proofs CITE. A definition the
checker expands silently is absent, so defs-by-symbol, built from the
environment, sees only the two relat_1 definitions that mention proj1.
Also fixed on the way: the normalizer emits nested single-binder ∃, which
must be flattened before elimination (round 1 was blocked on exactly this).
*Consequence*: ONE job left on this wall, and it is mechanical: build
defs-by-symbol from the DEPENDENCY ARTICLES' own definition tables
(mizArticleDefs over each imported article's XML with that article's sig,
cached per dep), not from the .eth. Every consumer downstream is in place
and traced working. Note the session's recurring shape: each "wall" turned
into a chain of ordinary defects, and every defect was found by a trace that
names its exact point — never by speculation.

### 2026-08-10 — the strengthener derives everything; one unfold short
*Built*: the defs-by-symbol index reads the dependency articles' own
definition tables. Seven defects on the way, each named by a trace: names
carried by constructor identity (a dep's .eno gives mechanical names); inline
constructors merged from the dep XML; the STATIC reuse check for the dep
parse (emitD's counts every loaded import symbol as provided); the DEFINES
filter and round 2 accept the normalizer's ∧-of-implications beside the iff;
filter before capping; ∃-witnesses from inside terms; the eleq1 bridge
unfolds the memberships it introduces.
*State*: STRENGTHEN on t8_relat_1 derives the exact predicted list —
`⟨a,b⟩∈X ; a∈proj1 X ; b∈proj2 X` — and the sub-proof fact dump (dbg-facts)
shows every instance present EXCEPT the opelxp unfold of
`⟨a,b⟩ ∈ (proj1 X ×. proj2 X)`. That one instance is the whole remaining
distance on this theorem. Suspects, in order: the memGen recursion from the
eleq1-introduced membership stops before the product term; the pair's
FolApp name differs where that particular recursion runs; the relevance cut.
All articles unchanged and verified; nothing regressed.

### 2026-08-10 — the "one opelxp instance" was three defects in fact selection
*Found*: the instance was GENERATED all along (a memGen trace proved it) and
lost downstream, behind three stacked defects:
  1. THE RANKED-RETRY DEATH SPIRAL. The assumption is mandatory and never
     dropped, but it COUNTED against `maxFacts` — so the over-budget retry of
     a deduction step always had kept = cap, facts = cap + 1, re-entered the
     over-budget branch, and recursed 10 → 6 → 4 → … → 0 without ever
     attempting a certificate. Every assumption-carrying attempt was silently
     falling through to the order-dependent 1-hop pass, which kept the elin
     that links opelxp to the goal but not opelxp itself.
  2. GOAL-ONLY RANKING. A fact whose atoms live in the ASSUMPTION (the
     opelxp unfold of a strengthened conjunct) could never win a slot. The
     rank now scores against goal ∪ assumption atoms, and the cut is a
     GREEDY FRONTIER: each kept fact's atoms join the scoring set before the
     next pick, so an unfold chain (eleq1 introduces the membership elin
     defines, whose parts opelxp defines) is kept whole. This is the answer
     to the 08-10 "what the ranked cut cannot see" entry: not a different
     static signal, but a rank that grows with what it keeps.
  3. BLOWUP AS VERDICT. A steps/nodes overflow now retries once with the top
     half of the greedy set (deterministic descending ladder), and pcert
     distinguishes a translator failure (PCERT_*) from a genuine length
     overflow — they were both reported "steps", which hid one behind a cap.
*Measured* (A/B, 8 articles dep order, pristine restore between rounds, all
verify green, MM gate green with proved == closed): by-steps 149 → 156
zfmisc_1, 212 → 223 xboole_1, 30 → 33 subset_1, 97 → 104 relat_1, 11 → 12
xboole_0; theorems fully closed 100 → 110 on the gate set (xboole_1 66 → 72,
zfmisc_1 17 → 19, subset_1 2 → 4). ZERO losses anywhere — the greedy frontier
does not repeat the hop-distance experiment's zfmisc_1 regression.
*t8_relat_1 itself*: closes end-to-end ONLY at a 2400-step certificate cap
(the strengthened assumption is a deep conjunction and every frame lift pays
it). At 2400 the gate gains more (xboole_1 80, xboole_0 5 theorems) but the
emitted mass explodes 4× (xboole_1.ru 38 MB → 172 MB) and READING it as a
dependency kills the next article's run — the cap stays 600, and t8 is
parked behind the named backlog item: certificate size, shared sub-lemmas
per theorem. The eigenvariable chain itself is DONE and proved working —
candidate, QNF, strengthening, elimination all hold on the trace; size is
the only wall left.

### 2026-08-10 — the ascending fact ladder, and where the mass actually is
*Built*: attempts now go SMALL FIRST — greedy-4 and greedy-8 before the full
relevance set — so a big context is paid only when a small one cannot close
the step. A failed small attempt is cheap (its G3cp tree is bounded by its
own fact count).
*Measured at cap 600* (full 8-article round, verify + MM green, proved ==
closed): zfmisc_1 by-steps 156 → 162, everything else exactly held; emitted
mass DOWN — xboole_1 41.7 → 37.4 MB, relat_1 26.1 → 23.8 MB.
*Measured at cap 2400 with the ladder* (the parked prize, re-checked): the
counts are the best ever seen — xboole_1 82/116 theorems, xboole_0 5/8,
zfmisc_1 23/140, by-steps 235/17/184 — but the mass still explodes (zfmisc_1
229 MB) and the NEXT article's dependency read dies even at mem=16g. Memory
cannot buy it: the flood is not oversized fact sets (the ladder fixed those)
but the genuinely-long certificates themselves.
*Census of the 155 MB xboole_1*: 80,230 proof steps; syl 27.5k + mpd 11.9k +
simpr 9.7k + simpl 9.7k = 73% of ALL steps are the natural-deduction
context-lift machinery, each restating the full context conjunction — the
text is steps × context, quadratic in practice. Top theorems ~4.8k steps.
*Consequence*: the 2400-cap prize (+12 theorems on xboole_1+xboole_0 alone,
+4 more on zfmisc_1) is measured and waiting behind ONE named item:
certificate text compression — the lift chains are the target (share them as
named sub-lemmas, or a discharge shape that does not restate the context).
Proofless dependency loading (read-ru proofless=1 exists; mizar-to-ru's
ruMizLoadDeps loads with proofs) would unblock the translate chain but NOT
the to_mm gate, which rightly reads dependency proofs — so compression is
the real gate, not loading.

### 2026-08-10 — the dynamic context: certificates stop restating their facts
*Built*: the G3cp→Hilbert translator's context conjunction now holds only the
DYNAMIC part — the assumption (if any) and ¬goal, extended by branch pushes.
The facts stay out of C entirely: a fact enters a branch as ONE a1i step over
its `|- f` reference, cached per branch context by the existing step memo,
and the closing pm3.2i/jca conjunction assembly disappears (the discharge is
con1i or ex+con1d+mpi over a one- or two-element context). Statements now
scale with the branch stack, not the fact count. FolPDer grew a `fact` field;
everything else in the translation is unchanged, including the two-pass
used-fact rebuild. The test-pcert battery emits 370 steps where the old form
needed 1426, and verifies against miz_set.
*Measured — the parked 2400-cap prize collected*: the certificate step cap
goes 600 → 2400, and the full 8-article chain now survives it end to end
(zfmisc_1.ru peaks at 88 MB, down from 229 MB; mem=16g for the emit and
to_mm runs). Verify green everywhere, Metamath gate green with proved ==
closed on every article:
  theorems fully closed: xboole_0 5/8, xboole_1 82/116, enumset1 11/87,
  zfmisc_1 23/140, subset_1 5/53, relat_1 3/175 (t8 in), tarski 1/3
  — THE GATE SET GOES 110 → 130;
  by-steps: xboole_1 235/277, zfmisc_1 184/366, relat_1 110/411,
  subset_1 37/132, xtuple_0 51/133, xboole_0 17/20.
*Consequence*: the eigenvariable wall is now fully paid — t8_relat_1 closes
end-to-end and is verified by the original checker. Emitted mass is still
the scaling concern (88 MB peak; the census's next target is the branch-
stack lifts and cross-step sharing), but it no longer blocks the pipeline.
The relat_1 imp(v1_relat_1, …) family (~38 theorems) is now unblocked for
the same machinery; enumset1 stays behind the foundation enum lemmas.

### 2026-08-10 — goal-operator definitions in the ladder; the eq-family's trail found
*Built*, tracing t21_relat_1 (`field X = field (X~)` by t20): two widenings,
both places where a selection looked only at the surface of the step:
  1. rung 3 now offers the GOAL'S OWN OPERATOR DEFINITIONS (field,
     k2_relat_1, …) as derived instances — they existed only inside the
     ∃-chain path; and a definition instantiates at EVERY occurrence term
     (capped 3), not the first match — t21 needs `field` defined at both
     `field X` and `field (X~)`;
  2. mizEmitDerive's instantiation candidates are the step's FREE VARIABLES,
     not only variables standing as a WHOLE side of an atom — an equational
     step between compound terms has no bare-variable side at all.
*Measured* (8 articles, verify + MM green, proved == closed): xtuple_0 0 → 2
theorems (its FIRST closures), subset_1 +1 by-step, relat_1 +1; everything
else exactly held. THE GATE SET GOES 130 → 132.
*Found — where the imp(v1_relat_1, =) family actually is* (12+9+6+ theorems,
the largest one-step-short bucket): t21's QED position has NO record facts at
all. These equational steps are closed by the checker's EQUALIZER — their
evidence is the MERGE LOG (S1/S2: equations, congruence closure, ConstEq/
DefEq provenance), and mizDerivPosFacts extracts only the Verify family's
premise instances. The 11-of-76 citation match rate is the same story. The
named next unit: REPLAY THE MERGE LOG — translate a disjunct's equation
chain (each merge cites its `=` premise) into eqtr/eqeq/congruence bridges,
the S4c catalog's "eqmixed tail". The record already carries everything
(gated 1497/1497 since S2); only the emitter-side consumer is missing.

### 2026-08-10 — the stale-jar scar; the cite experiment reverted; 101/175 dishonest shapes
*The scar, recorded so it cannot repeat*: a debug print referencing an
unexported name made `build_java.sh` FAIL SILENTLY in the middle of the
session — the jar on disk stayed one edit behind, and one commit
(6012b34b, "cited statements as rung-3 facts") was gated on a jar that did
not contain its code. The honest re-gate on a from-source build showed the
change DISPROVED theorems (a cite step stated t27_xtuple_0's .eth ∀-closed
statement while the emitted t27 states one inclusion) and had
variable-capture defects in its instantiation chain (albidv/spv
disjointedness violations). REVERTED; the census helpers and the build fix
stay. 92911010's gate was re-validated exactly (132 theorems, all counts
reproduced, verify green) on a strict build. RULE: check the builder's exit
code, never its last line.
*The census* (dbg-shape=1): relat_1 has 101 of 175 theorems whose
hypothetical emission is DISHONEST — hyps that are not the statement's own
antecedents (nested-block let/assume leaked to theorem level) or a last
step that is not the statement's matrix. t20_relat_1 states `x46 ∈ proj1
x26` under four proof-local hyps where RELAT_1:20 should stand. Cross-
article citation of such theorems is impossible by construction, and the
verifier rightly disproves any attempt to cite them by their .eth text.
*Consequence*: the THEOREM-SHAPE unit is confirmed as the gate to the
imp(v1_relat_1,…) family and to cross-article citability generally:
theorems must be emitted stating their ∀-closed Mizar statement, with the
let/assume/consider skeleton inside the proof (the deduction-reduction
machinery that now exists is exactly what proves such shapes). Then the
cite channel can return, matching against emitted text only. Merge-log
replay stays queued behind it.

### 2026-08-10 — THE HONESTY GATE: every emitted theorem states its Mizar theorem
*Built*: the hypothetical emission `hyps ⊢ last-step` passes only when the
hyps are the statement's own antecedents and the last step is its matrix;
a dishonest shape (census: 270 of ~630 hypothetical theorems across the
gate set — 101 in relat_1 alone) falls back to the single-step form: no
hyps, one open step stating the ∀-closed Mizar statement, the proof's
whole citation set as its premise pool. Honest, citable, and exactly the
shape the deduction reduction proves.
*Measured* (8 articles, verify green, MM green with proved == closed):
  the headline count moves 132 → 112, and that is a CORRECTION, not a
  loss — 55 of the old 132 were proof FRAGMENTS bearing Mizar ref names
  (subset_1's five and xtuple_0's two were ALL fragments; their honest
  count is zero). Against the honest baseline of 77, the fallback +
  reduction RE-CLOSED 35 theorems as their real ∀-closed statements:
  xboole_1 47 → 73 (26 of its 35 fragments re-close through the same
  machinery that closed t8), zfmisc_1 15 → 18, relat_1 1 → 6, xboole_0
  2 → 3. relat_1 also now emits 179 theorems (4 previously dropped for
  Choice markers in their PROOFS return — the statement-only form does
  not carry them).
*Consequence*: the scoreboard is honest for the first time since the
hypothetical form was introduced, and every closed theorem is citable by
dependents at its true statement. The "one step short" metric is now
diluted (a single-step open theorem is trivially one short) — read
by-steps instead. NEXT: the single-step ∀-closed theorems are exactly the
reduction's workload — the eigenvariable chain, merge-log replay, and the
per-piece skeletons (thus-chains as ∧-splits) are the three levers over
the 167 open relat_1 statements.

### 2026-08-10 — the cite rung: citations return, soundly, and relat_1 doubles
*Built*: the cited-statements channel returns as RUNG 4 of the ladder,
rebuilt on the honesty gate with every defect from the reverted attempt
fixed by name:
  - TRUST: only own-article theorems (their table entry IS the emitted
    ∀-closed statement now) and env axioms are citable — an imported
    theorem's .eth text may differ from its emitted shape;
  - CAPTURE: a witness must be the binder itself (spi) or a name foreign
    to the statement — and BOTH comparisons are case-normalized (the AST
    binder is "X5", the rendered free var "x5"; the mismatch sent identity
    instantiations into spv and violated its own disjointedness);
  - SCOPE: instances only at the goal's/assumption's free variables, two
    per citation — instantiating at every variable of every form put 77
    candidates against a 10-slot ranked cut and cost t2_xboole_0;
  - ORDER: a separate rung, fired only when rung 3 without citations
    fails — as ordinary candidates the citations crowded working sets.
*Measured* (8 articles, verify green, MM green, proved == closed):
  relat_1 6 → 10 theorems, zfmisc_1 18 → 19, all floors held.
  THE HONEST GATE SET GOES 112 → 117.
*Consequence*: the theorem-shape fix is now PAYING — the citation economy
works across an article's own theorems. The remaining relat_1 tail wants
imported citations (blocked on the .eth-vs-emitted text mismatch — the
next translation slice is emitting dependency articles' theorems in the
same honest shape, which makes their loaded text THE text) and the
merge-log replay for the equalizer-closed equational steps.

### 2026-08-10 — the honest baseline regenerated end to end
*Done*: the whole gate chain re-translated in dependency order with the
honesty-gate jar — every stored article skeleton now carries honest
theorem shapes (t20_relat_1 states RELAT_1:20). Backup:
`~/dev/math/mizar-honest-baseline-20260810.tar.gz`. The emit gate on the
new baseline reproduces 117 exactly (verify green, MM spot-checks green,
proved == closed) — no .eth-vs-emitted render mismatch exists: the
constructor-identity naming makes the two texts equal for single-step
theorems, so IMPORTED single-step theorems are now citable through the
existing isAxiomName dispatch with no code change.
*Observed*: no imported-citation gains yet — the remaining open steps
fail on EVIDENCE (the equalizer's equation chains), not on citability.
t21_relat_1 has its citation (t20, honest, instantiable) and still needs
the ∪-congruence + commutativity chain the merge log records.
*Consequence*: the citability infrastructure is complete and sound; the
frontier is now purely the MERGE-LOG REPLAY (equation chains into
eqtr/congruence-walk bridges) plus the enumset1 foundation lemmas. Both
were named before; nothing else is in front of them.

### 2026-08-11 — the def-instance chain unblocked; the flat certificate's limit named
*Built*, tracing t21_relat_1 one layer deeper (each defect by its own
diagnostic print, on a strictly-checked build):
  1. THE BINDER-COLLISION TRAP at the rung-3 definition site — d6's locus
     renders as the theorem's own x26, so every compound witness "contains
     the binder" and every compound instance was refused (GDEF-DECLINE
     named it). Fix = alpha-rename the statement's bound variables before
     matching, the documented remedy at every other application site.
     mizFreshenBound exported for it.
  2. GUARD DERIVATION at the rungs: a guarded instance (`Relation (X~) →
     field (X~) = …`) is inert until its guard is a fact, and the guard's
     source — a cluster registration — is cited by nobody. The rungs now
     derive guards by skeleton, exactly as the top-level channel does.
  3. A greedy-16 rung between greedy-8 and the ranked cut.
*Measured*: the whole gate set exactly held (117, verify green, MM
spot-checks green) — infrastructure, not yet coverage.
*The limit, stated honestly*: t21's chain is now fully GENERATED — both
definition instances, the ct4 guard bridge, t20's citation instance — and
still does not close: ~14 exact facts must survive a 16-slot cut against
~40 candidates, and flat selection at this density is the wrong tool. This
is the boundary of the pile-of-facts certificate. Mizar's own answer is
the EQUALIZER: the merge log hands over the exact equations and their
order, no selection involved. The next unit is unchanged — MERGE-LOG
REPLAY — and now it is known to be necessary, not merely queued: no
selection tuning can substitute for it on the equational family.

### 2026-08-11 — the record channel opened wide: 117 → 126
*Built*, one chain of defects at a time, each named by its own diagnostic:
  1. WHOLE-PROOF POSITIONS: both single-step fallbacks (dishonest shape,
     no-steps walk — t21's IterEquality class) carried pos = "" and got
     NOTHING from the record; they now carry every inference position of
     their proof comma-joined, and the record lookups split and union.
     Citation-proved facts on relat_1 alone went 11 → 68.
  2. MERGE-LOG SEEDS (mizDerivPosEqs): the <Equation basic=j> provenance
     resolved to its disjunct's <Literal> formula, per position — the
     checker's own "which equalities matter here"; they and the record
     instances' equation atoms seed the relevance closure and the greedy.
  3. PIECES UNDER AN ANTECEDENT (simpld/simprd): the checker splits
     `v1 X → (eq ∧ eq)` into guarded premises; the citation matcher and
     the op applier now reach them. Plus the sp identity case compared
     case-insensitively (the AST binder "X26" vs the rendered "x26").
  4. THE RECORD WHOLESALE (rung 2R): when the record supplies facts, an
     attempt runs on THAT pool and its own bridges before any speculative
     candidate joins; plus the circular filter (a derived instance whose
     equation IS the step's own goal equation proves nothing and crowded
     the cut).
  5. TWO SOUNDNESS GUARDS the verifier demanded: a foundation operator
     absent from the sethood table DECLINES (guessing sh_symdiff disproved
     xboole_1); and a citation of an assertion with declared
     disjointedness maps the requirement through its substitution — or
     declines (t58_enumset1's inherited-restrictions rejection) — via a
     registry of built theorems' disjointedness groups.
*Measured* (8 articles, verify green, MM green, proved == closed):
  enumset1 11 → 22 (the record finally reaches its enum steps),
  zfmisc_1 19 → 20, xboole_1 73 → 70 (the widened def instances displace
  three borderliners — a real cost, recorded), others held.
  THE HONEST GATE SET GOES 117 → 126.
*Open*: t21_relat_1's chain now has its record equations as facts and
still wants d6@X~ + ct4 + two eluns together — the full merge-log REPLAY
(chain emission, not seeding) remains the equational family's answer.
enumset1's remaining 9 declined-citation theorems want instance-level
disjointedness declared on the CITING theorem instead of declining.

### 2026-08-11 — MERGE-LOG REPLAY: the equation-chain builder; 126 → 144

*What*: an equation goal's proof is now BUILT, not selected
(`mizEmitEqChain`, emit.flow, hooked at ladder rung 3). A proof-producing
congruence closure runs over the candidate equations the channels already
generate — record instances, def instances, cite instances, guard
bridges, context equations. Commutativity twins (uncom / incom /
symdifcom / prcom) and slot congruence supply the edges the equalizer
bakes into its classes with no merge-log entry; extraction emits
eqtrd / eqcomd / syl chains, with guards discharged through the same pool
(id / a1i / syl, depth 6). Deterministic — the 16-slot ranked cut that
structurally could not hold t21_relat_1's ~14-fact chain is bypassed
entirely for the equational family.

*The unlocking fix beside it*: `bySkeletonAt` returned [] for any
skeleton shared by more than 32 statements — and the guard channel needs
exactly the crowded ones (`X is Relation` names half of relat_1's env).
It now falls back to the AXIOM subset (cap 96); matching still decides.
That is what let `Relation (X~)` derive from ct4 and unlock d6@(X~).

*Two soundness scars, both caught by the verifier as disproofs
(t6/t14_enumset1), both general*:
  1. in the chain's path fold, `acc == None` meant both "first edge" and
     "an earlier edge failed" — after a mid-path failure the next success
     re-seeded the chain and silently dropped the unproven prefix. A
     failed edge must abort the whole chain.
  2. a congruence edge's extraction bound must be the edge COUNT at
     creation, not the closure pass number: two edges of one pass may
     depend on each other's merges, and the pass-number bound refused at
     extraction what the closure had legitimately used.

*Measured* (8 articles dep order, verify green, MM green,
proved == closed): tarski 1, xboole_0 3, xboole_1 70, enumset1 22 → 34,
zfmisc_1 20 → 23, subset_1 0, xtuple_0 0, relat_1 10 → 13.
THE HONEST GATE SET GOES 126 → 144. t21_relat_1 closes end to end.

*Open next*: enumset1's 9 declined citations (instance disjointedness
declared on the citing theorem); symdifex + "△" sethood (xb1's three
displaced borderliners); subset_1 = the S-C type layer; chain extensions
only if traces ask (⊆-goals via the closure, multi-guard payloads).

### 2026-08-11 (later) — three units on top of the chain builder; 144 → 158

*Neutral, sound, committed @8995bb80*: citation disjointedness now maps a
compound-substituted group member to ALL its variables (Metamath's DV
semantics, the spv path's own rule) instead of declining; symdifex (△
sethood) authored in miz_aux.ru and added to the emitter's sethood table.
Both gated neutral at 144 — the enumset1 declines and xboole_1's three
displaced borderliners were not the binding constraint.

*The enum wall fell to a ROUTING fix, not a foundation regen
(@21d47df5)*: the goal-def channel had been building the d2..d6_enumset1
means instances all along — but only the record channel's facts went
through the drv ∀-elimination. A means instance keeps its inner
∀x characterization, and as one opaque atom relevance dropped it unseen.
Routing the def instances through the same elimination (instantiated at
the goal's element) closes t4/t5/t7/t8 and the k2/k3 family:
enumset1 34 → 48, every other floor held, verify + MM green,
proved == closed = 158.
★ THE LESSON: before regenerating a foundation, check whether the fact
is already GENERATED and merely dropped between channels — the whole
"enum4-8 regen campaign" prize was one plumbing defect. (What remains
open in enumset1 — t41/t49/t50/t61-65, the k5/k6 identities and
permutation family — may want the wider lemmas still, or deeper chains.)
★ THE COST, recorded honestly: enumset1's emit+verify now runs ~10 min
(was 40 s); the k-way case-split certificates are heavy. Budgets were
not raised. CERTIFICATE SHARING across steps is the named follow-up
before this scales to more enum-citing articles.

### 2026-08-11 (evening) — the size census: sharing is NOT the lever

*Done (@252162bd, gated exact at 158)*: cross-block certificate step
dedup in folProofTheoremE — identical (assertion, resolved refs,
statement) steps across one theorem's by-step blocks collapse to the
first occurrence.

*The census that matters*: enumset1's emitted mass is 97 MB / 76k steps;
63% is syl / mpd / simpl / simpr — INTRA-certificate context lifting,
every form mentioning its own certificate's context. Cross-block sharing
therefore recovers only ~7% of steps (76k → 70k) and ~1.5% of text; the
536 MB Metamath export does not move at all. So the case-split family's
mass will not fall to sharing at any granularity above the certificate.

*The named next form (design, not yet built)*: prove the k-way
membership matrix `x ∈ k(n)(ā) ↔ x ∈ <compound>` as a BICONDITIONAL
CHAIN instead of a G3cp refutation — both sides normalize to ∨-trees
over the same `x = aᵢ` atoms, and ∨-tree equality modulo assoc/comm is
a bitri / orbi12i / orass / orcom walk: linear in the tree, no case
explosion, no context to restate. mizEmitMemRewrite already proves this
shape for the foundation operators (elun/elin/eldif route); the enum
operators need the INSTANTIATED definition iff (gDefsI has it) consulted
as an unfold rule in the membership-NF table. That one unit should
replace the heaviest certificates outright — the 10-minute enumset1 run
and the half-gigabyte export are its measured prize.

### 2026-08-11 (night) — the membership NF opens up; 158 → 170; TWO USER DIRECTIVES

*Done (@91aae30d, verify + MM green, proved == closed)*: mizEmitMemNF
unfolds △ (elsymdif, structural), the var-element leaves (velsn / velpr /
veltp / velpw), and ARTICLE operators through their own definition — the
instantiated def iff spliced as a proof block and cited like a lemma;
mizEmitMemPair admits these as compound, so the mem-rewrite route now
fires on the enum matrices. xboole_1 70 → 73 (the displaced △
borderliners return), enumset1 48 → 54, zfmisc_1 23 → 26 — gate set 170.
Emitted mass unchanged: the residue still case-splits in G3cp.

*USER DIRECTIVE 1 — proofs must cite, not trace.* The 3-orders size gap
against the old tactic prover is structural: the tactic composed
CITATIONS of a 10k-lemma library (the library carries the mass); the
certificate is a decision-procedure TRACE over ~20 propositional lemmas.
Direction set: the record/replay stays the PLANNER, but closing becomes
citation — match residues against existing library lemmas before
refuting, and name recurring residue shapes as foundation lemmas. The
eq-chain builder is the model (small eqtrd/uncom chains). This
supersedes trace-compression as the size campaign.

*USER DIRECTIVE 2 — parallelize the gates.* to_mm is per-article
independent (own out= dirs mandatory — shared dirs race on the
foundation .mm files): concurrency 3 at mem=16g fits 64 GB and roughly
halves a round. The emit phase stays sequential (dependency chain);
per-theorem parallelism inside one article is the next lever.

### 2026-08-12 — THE ∨-TREE WALK: the citation completer's first unit; 170 → 181

*Done (@d2c2855e, verify + MM green, proved == closed)*: k-way membership
residues close by CANONICAL FORM, not refutation. mizEmitOrCanon proves
`f ↔ C` (right-nested, key-sorted, deduplicated ∨) as an insertion sort of
foundation citations — orass/orcom/or12/oridm lifted by orbi1i/orbi2i,
glued by bitri, with the checker's dialect opened on the way (imor, ianor,
notnotb). The eliminated def instance becomes `mem ↔ C` by a fixed
assembly over both checker pair-shapes (simpl/simpr + pm2.53 +
sylib/sylbi + impbii) — CleanIff's capped internal certificate is out of
that path. Membership pairs rewrite FIRST in the reduction.

*Measured on enumset1*: 54 → 65 theorems; 88 MB → 6.8 MB; 64,651 →
17,608 steps; 11 min → 28 s. The permutation family (t61–t74) closes in
~100-step citation chains instead of 5,250-step case splits. THIS is the
trace-vs-citation gap closing exactly as the directive predicted.

*Two scars*: notnotb is stated `φ ↔ ¬¬φ` — orientation matters, the
verifier caught 32 disproofs from the flipped citation; and a DETACHED
dbg probe overwrote the gate's freshly emitted article minutes later, so
the MM gate read a stale file (proved=54 vs closed=65, caught by the
proved==closed law) — "one writer at a time" applies to probes too.

*Standing*: honest gate set 181 (tarski 1/3, xb0 3/8, xb1 73/116,
enumset1 65/87, zfmisc 26/140, subset_1 0/53, xtuple_0 0/46, relat_1
13/179). Next: the same citation treatment for the remaining heavy
families (relat_1's export is still 600+ MB — its certificates are the
next census target), then the S-C type layer for subset_1.

### 2026-08-12 — THE SUBSET CLOSURE: relat_1's mass falls, 181 → 189

*The census (@c866ad19)*: three relat_1 theorems (t8/t68/t94) carried
89% of the article's 26.7 MB — each a ~2,780-step G3cp refutation whose
content is a small ⊆-chain (`X ⊆ proj1 X ×. proj2 X`, ×.-monotonicity,
intersection absorption). 64% of all steps were syl/mpd/simpl/simpr
context lifts.

*Done*: mizEmitSsChain — a directed subset closure next to the eq-chain.
Edges: the candidate pool's ⊆ atoms (through conjunction PIECES and one
guard), the structural inclusions (inss1/inss2/difss/ssun1/ssun2), and
the equation closure (piece-buried equations join as kind-3 edges).
Composition sstri/sseqtri/eqsstri on a BFS over eq-classes;
introduction ssin/unss on the compound side; an equation goal is
antisymmetry (eqssi). Full deduction twins (d-forms, jca, sylib, a1i
lifts); the ASSUMPTION's conjuncts are piece sources; and
mizEqcProveG projects a guard that is a conjunct of the assumption
(id + simpld/simprd) — the plain chain closed t94/t68 but the ONELEFT
imp(v1_relat_1,…) family's guards are asm conjuncts, and eq/ss edges
never materialized until the guard machinery saw them (relat_1 14 → 20
came from THAT one fix, not from more edges).

*Measured, all Metamath-gated (proved == closed = 189)*: xboole_1
73 → 74, relat_1 13 → 20, all other floors held. relat_1.ru 26.7 MB →
2.2 MB; its MM export 600+ MB → 193 MB — the residual mass is the
DEPENDENCY articles' certificates (zfmisc_1.ru 58 MB, xboole_1.ru
41 MB), which are now the census target.

*Standing*: honest gate set 189 (tarski 1/3, xb0 3/8, xb1 74/116,
enumset1 65/87, zfmisc 26/140, subset_1 0/53, xtuple_0 0/46, relat_1
20/179). Next: census zfmisc_1/xboole_1 the same way (their heavy
certificates hold the remaining export mass); the relat_1 open set
(155 one-step-short) for what the chain still lacks; then S-C.

### 2026-08-12 (later) — FOUNDATION REWRITE TWINS; 189 → 190; the pointwise lesson

*Done (@417b7c70, verify + MM green, proved == closed = 190)*:
mizEqcRewriteTwins — the foundation's distribution/absorption identities
(xpundir family, difundir family, indi/indir/undi/undir, the ∅ and
idempotence rows; every row the miz_set statement verbatim) join both
chain builders as the commutativity twins do: matching term → twin term
+ kind-4 edge citing the lemma. zfmisc_1 26 → 27, floors held.

*THE LESSON that redirects the size campaign*: zfmisc_1's heavy
certificates (t120/t99/t97/t57 — 29 of its 58 MB) do NOT pass through
the equation chain. Their Mizar proofs are POINTWISE: the by-steps are
pair-membership matrices (`⟨a,b⟩ ∈ (X∪Y)×.Z ↔ …`) whose residue after
the membership NF is an ∧/∨ DISTRIBUTION, and that closes in G3cp — the
equation form never appears as a step goal. The next size unit is a
DNF residue canonicalizer next to the ∨-tree walk (andir/andi + orbi
congruence, insertion-sorted like mizEmitOrCanon), which would also
serve xboole_1's t88/t25 class.

### 2026-08-12 (later) — THE DNF WALK: the pointwise family closes by citation

*Done (@9137245d, verify + MM green, all closures held at 190)*: three
pieces, one family. mizEmitDnfCanon extends the ∨-walk's insertion sort
to ∧/∨ trees (canonical form = right-nested key-sorted ∨ of right-nested
key-sorted ∧; andir/andi distribution, anass/ancom/an12/anidm, ioran —
the ∨-level machinery reused unchanged with whole conjunctions as
leaves). opelxp joins the membership normal form (an explicit pair in a
product opens into the component memberships). mizEmitIffNFWalk at the
reduction entry normalizes BOTH sides of any iff goal and joins them by
the walks; a miss falls through to the old path untouched.

*Measured*: zfmisc_1.ru 58 → 36.5 MB (t97/t99/t102 collapse),
xboole_1.ru 41 → 30 MB, relat_1's MM export 193 → 166 MB. The remaining
heavy pair — zfmisc t120 (8 MB) and t57 (7 MB) — is a CASE-ANALYSIS
shape (a disjunction with an equation limb, `x∈A ∨ x∈A ∨ A = A∖{x,y}`),
not a pointwise iff; it needs either the proof-by-cases route or an
equation-under-disjunction treatment, and is the natural next census
read.

### 2026-08-12 (later) — THE CHARACTERIZATION TABLE: 190 → 191; zfmisc under 1 MB/theorem

*Done (@985420d8, verify + MM green, proved == closed = 191)*:
mizEmitFoundIffGoal — a by-step goal is matched against a small table of
foundation characterization lemmas in Fol form (disj3, dfss2, ssequn1,
disjdif), directly and through the orientation bridges (bicomi for the
iff, eqcom per equation limb, bibi12i + mpbi to reassemble; a1i lift
under an assumption). The `(X∖{a,b} = X) ↔ (X∩{a,b} = ∅)` steps of
t120/t57_zfmisc_1 — 4,300-step flat proofs, 15 MB together — are now
four-citation proofs. xboole_1 74 → 75; zfmisc_1.ru 36.5 → 18.3 MB, no
theorem above 1 MB left in it.

*The table is the seed of the general residue-vs-library matcher*: rows
are added when a census names a shape; the matching and bridge machinery
is shape-independent. The day's arc: 181 → 191 theorems; zfmisc_1.ru
58 → 18.3 MB, xboole_1.ru 41 → 30 MB, relat_1.ru 26.7 → 2.2 MB.

### 2026-08-12 (evening) — ABSORPTION + IFF-OPENING: xboole_1 30 → 18.3 MB

*Done (@HEAD, verify + MM green, proved == closed = 191, floors held)*:
the xboole_1 census named two DNF-walk gaps and both closed. ABSORPTION:
mizEmitOrPrune drops a conjunct whose atom set strictly contains
another's and bridges the pruned tree by two implication proofs (orc/olc
injections, simpl/simpr/jca projections onto the absorber, jaoi folds,
impbii) — t25's lattice identity collapses. IFF-OPENING: dfbi3 opens a
biconditional into its two-case DNF, xor3 turns a negated one first —
the shape the △ membership unfold leaves (t102 collapses). The join
retries pruned forms when the raw DNFs differ; step cap 1200.

*Remaining xboole_1 tail*: t32 (2.5 MB, `X∖Y = Y∖X ⊢ X = Y`) and
~1 MB friends — hypothesis-driven reasoning, not identities; a
different family for a later read.

### 2026-08-12 (late) — relat_1's ONELEFT buckets read; the two blockers NAMED

*The census*: 30× `v1 X → (eq)`, 18× `v1 X → (⊆)` — 48 theorems, the
whole top of relat_1's open set. One trace (t52: `id(proj1 X) ∘ X = X`)
names the exact blockers, verified by hand against t50/t51:

1. COMPOUND-WITNESS CITATION: t52 needs t50 at `x7 := proj1 x26`. The
   cite channel instantiates only at the goal's plain variables
   (spi/spv); a compound witness needs the spcgv/sethood route —
   mizEmitElimAt already does it, chained from a step citing the
   ∀-closed statement (the sethood-axiom instantiation is the model).
   Witness pool = the goal/asm's compound subterms, capped.
2. HYPOTHETICAL CITATION: t51 is emitted in honest hypothetical form
   (hyps `v1 X`, `proj1 X ⊆ x7`) and is EXCLUDED from the citable
   statement table (stmtTbl filters hyps == []). Citing it means
   providing refs proving each substituted hypothesis — here the
   context's own `v1 x26` and `proj1 x26 ⊆ proj1 x26` (ssid). The
   discharge kinds needed: a context fact, the assumption/its conjunct,
   ssid/eqid trivials.

Both are general (every article's guarded families look like this);
together they unblock the 48. This is the next session's first unit.

### 2026-08-12 (night) — TERM-WITNESS CITATION: blocker (a) paid; 191 → 194

*Done (@HEAD, verify + MM green, proved == closed = 194)*:
mizEmitInstCiteT — a cited ∀-closed theorem is eliminated binder by
binder through the spcgv + sethood chain at TERM witnesses drawn from
the goal/assumption's compound subterms (t50 @ `x7 := proj1 x26`), the
theorem's declared disjointedness mapped through the witnesses. Tuples:
compound subterms (cap 3) × free variables, at least one compound
element, cap 6 per citation; the plain-variable route unchanged.
relat_1 20 → 23, all floors held.

*Emit-gate parallelism, asked and answered*: within the 8-article gate
set the import graph is a TOTAL CHAIN (tarski → xboole_0 → xboole_1 →
enumset1 → xtuple_0 → zfmisc_1 → subset_1 → relat_1) — each article
reads its dependencies' freshly emitted files, so no two can run
together; article-level parallelism only pays at MML scale where levels
are wide. The lever for the gate set is PER-THEOREM parallelism inside
one article's emit (theorems need earlier theorems' statements, not
their proofs) — the recorded next lever. to_mm already runs 3-wide.

*Blocker (b) — hypothetical citation — remains*: t51-shaped theorems
(hyps = own antecedents) are outside the citable table; citing one
means proving each substituted hypothesis (ctx fact / ssid / eqid) as
refs. The remaining v1→eq/⊆ family members that still fail want it.
