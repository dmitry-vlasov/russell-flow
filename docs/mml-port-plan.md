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
