/**
	@help {
		The BIDIRECTIONAL engine `checker` (checker-decomposition, experimental): the law-abiding
		decomposition's refiner front (as in `checker-engine`) LAYERED with an on-engine forward
		saturation fallback — a step toward the Mizar `by`-checker's forward saturate-to-contradiction
		procedure, entirely on-engine.

		Structure (an or-else, first to close a gap wins):
		  1. refine(strip-forall, conj-split, rel-intro, eq-rewrite) over the FULL checker-scope, replayed
		     through the engine (nnf off, then on) — the same front that gives `checker-engine` its closes.
		  2. scoped(checker-scope, forward) — seed the forward front from the premises + the scope's
		     0-hypothesis axioms (exmid, pm3.24, noel, …) and saturate; derived facts JOIN the backward
		     subgoals via ruReverseJoin. All proofs engine-built (ruAddProof) — no install, no off-engine
		     construction. Its marginal contribution is small today (forward alone rarely closes a whole
		     gap — the open L2 guided-search problem); it is scaffolding for the assume-¬G refutation
		     seeding (checker-decomposition candidates A/B).

		BOUNDED so it COMPOSES: an unguided forward wave under the wide checker-scope can, from a SINGLE
		fact, trigger dozens of assertions and produce thousands of facts in one call — seconds of work
		that, in a sequential prove run, consumes the whole command budget and STARVES the sibling
		theorems (they never start). The `forward` atom now bounds each wave / each fact expansion by
		forward-search-size (facts) and the per-goal timer — a LIMIT, completeness-preserving (never MISS
		a reachable proof within the bound). See ruRunForwardWave / ruExpandForwardProved in
		src/ru/prover/core/forward.flow.
	}
	@arg max-depth  { cap on the refiner guide's tree depth from the root. @defval 8 }
	@arg max-size   { cap on the refiner guide's tree size. @defval 8192 }
	@category derived
*/
or-else(
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite,         scope=checker-scope, max-depth=$max-depth, max-size=$max-size)),
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite, nnf=on, scope=checker-scope, max-depth=$max-depth, max-size=$max-size)),
	scoped(checker-scope, forward))
