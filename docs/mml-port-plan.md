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
- Deterministic by-step emitter (`mizar-to-ru emit=1`): closes 130/241 steps
  on xboole_1, 86/288 on zfmisc_1, 16/129 on subset_1; 33 / 10 / 2 theorems
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
