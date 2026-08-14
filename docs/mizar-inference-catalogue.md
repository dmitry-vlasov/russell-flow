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
