# The Mizar decision procedure as a Russell tactic

Russell carries a transcription of the Mizar checker's decision procedure as
ordinary prover tactics. They work on any loaded Russell goal, not only on
translated Mizar articles: they are the "obvious step" closers — a goal a Mizar
author would justify with a bare `by` is the kind of goal they close.

This document is the usage entry point. The design documents are
[mizar-checker-mapping.md](mizar-checker-mapping.md) and
[mizar-inference-catalogue.md](mizar-inference-catalogue.md); the tactic
language itself is [tactics-language.md](tactics-language.md).

## The tactics

All are file-based tactics under `tactics/` (run `russellj tactic` to list
every component):

| tactic | what it does |
|---|---|
| `def-close` | definitional closure: strip ∀, split conjunctions, reduce ⊆/= goals to membership, unfold compound membership atoms, close the residue with a bounded BFS; retries in NNF form. The strongest single closer on set-theoretic goals. |
| `checker-mizar` | the Mizar `by`-checker as a pure composition: assume the negated thesis, saturate to a clash (Mizar's refutational procedure), with on-engine skolemization and cluster rounding. |
| `checker-el`, `checker-eq`, `checker-bidir`, … | the checker decomposed into orthogonal pieces on the bidirectional engine, for composing with other tactics. |

`def-close` and `checker-mizar` prove DIFFERENT theorems (measured overlap on
the gate articles is well under either count) — trying both is a real
composition, not a retry.

## Closing gaps in any article

The general form — prove the open `?` steps of a loaded target:

    prove target=<module> steps=1 tactic=def-close relevance-depth=0 \
          exclude-disjointed=0 time-limit=5s max-depth=8 max-size=65536 \
          forward-closure-depth=0

For a translated Mizar article there is a packaged script that reads the
foundation and the article, proves the gaps, writes the closures back, and
re-verifies (optionally ending at the external Metamath checker):

    bin/russellj no-server=1 mem=12g translate/mizar/prove \
        module=xboole_1 tac=def-close tl=5s

`translate/mizar/prove_gaps` is the measure-only variant (no write-back).

## The composed pipeline

The MML gate composes the two routes — the record-driven emitter first, the
tactic over what it leaves open — and checks everything with the original
Metamath checker:

    scripts/translate/mizar/gate.sh tac=def-close

Phase order: emit (the Mizar-record emitter closes what it can, leaving `?`
gaps) → tac (`translate/mizar/prove` with the given tactic per article, written
back) → mm (export + the original Metamath checker; the gate law requires
proved == closed per article). Measured on the 8-article gate set, the
composition proves more than either route alone: emitter-only 247 theorems,
def-close-only 149, composed 275 (gate green 2026-08-24, proved == closed on
all 8 Metamath databases; the composed run also beats the 272 paper union of
the two separate runs, because the emitter's partial step-closures shrink the
residues the tactic sees).

## Bounds and gotchas

- Keep the search scoped: `relevance-depth=0` with `def-close` uses a small
  scope-only index; whole-foundation BFS overflows the rule-id space on a big
  article.
- `tl=5s` per step is the measured envelope; raising it buys little
  (the failures are missing machinery, not missing time).
- Under concurrency this power-capped class of machine loses borderline steps
  — commit numbers come from sequential runs (`par=0`, the default).
- Arithmetic is out of scope by design; the procedure covers Mizar's `by`
  fragment (propositional + equational + membership + soft-type reasoning).
