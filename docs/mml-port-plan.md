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
- Deterministic by-step emitter (`mizar-to-ru emit=1`): closes 128/242 steps
  on xboole_1, 85/291 on zfmisc_1, 16/130 on subset_1; 33 / 10 / 2 theorems
  fully proved, all verified by the original Metamath checker.
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

### S-B — by-steps: consume the record instead of re-deriving
*Machinery*: the checker record that already exists.
*Problem*: the emitter re-derives each step from a fixed lemma catalog and
does not read the recorded witnesses, case split or kill list. The campaign's
central claim is therefore untested.
*Artifact*: record-driven emission — witnesses from `<With>`, the case split
from the disjuncts, kills from `<Cand>`/basis.
*Gate*: by-steps closed on xboole_1/zfmisc_1 from the record, against the
current catalog-driven numbers; and the catalog must shrink, not grow.

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

### 2026-08-08 — the phantom import cycle
*Expected*: "cyclic imports" meant a real cycle.
*Found*: a relative `import-roots` in the library config does not resolve from
another working directory, so file-read sources keep their full path as module
id while importing articles name them `mizar/<a>`; the toposort then hangs on
an id mismatch. Only articles importing a sibling exposed it, which is why one
article passed and the rest failed.
*Consequence*: scripts pin absolute import roots; the Metamath gate now
reaches every article.
