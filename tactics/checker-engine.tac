/**
	@help {
		The LAW-ABIDING engine `checker` — the decomposition of the install `hilbert`/`checker` emitter
		into a pure composition of engine-driven atoms + named lemma-set scopes (checker-decomposition).

		Where the install `checker` BUILDS a proof off-engine (a bespoke natural-deduction emitter) and
		INSTALLS it — a Law-2 violation (the engine must build every proof) — this reconstructs the same
		class of proofs THROUGH the engine: it runs the def-close goal-refiners (strip the leading ∀, split
		a conjunction goal, reduce a ⊆/= goal to a membership subgoal, unfold + canonicalize compound
		membership atoms) and closes the residual with a bounded BFS scoped to the FULL checker rule set
		`checker-scope` (prop ∪ refute ∪ exmid ∪ bridge ∪ unfold — the same lemma families the emitter uses).
		`replay` reconstructs the guide via the engine, so the result is sound and precedence-checked.

		The wider scope (vs def-close's prop-only closer) is what lets the residual BFS apply the refutation
		(pm2.65i/notnotr/pm2.21), excluded-middle (exmid/pm2.61i) and ⊆/=/∈ bridge lemmas — closing gaps
		def-close cannot, with no install and no off-engine proof construction.

		Bounding the deeper search: max-depth / max-size / time-limit are the BUILD-TIME guide-construction
		caps (a wrapping `limited` bounds runtime engine steps but cannot reach these).
	}
	@arg max-depth  { cap on the guide's tree depth from the root. @defval 8 }
	@arg max-size   { cap on the guide's tree size. @defval 8192 }
	@arg time-limit { per-step build budget, in seconds. @defval 60 }
	@category derived
*/
or-else(
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite,         scope=checker-scope, max-depth=$max-depth, max-size=$max-size, time-limit=$time-limit)),
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite, nnf=on, scope=checker-scope, max-depth=$max-depth, max-size=$max-size, time-limit=$time-limit))
)
