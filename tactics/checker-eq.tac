/**
	@help {
		The EQUALIZER as its own pipeline stage (Mizar's Equate phase,
		mizar-tactic-plan): the NARROW congruence scope only — identity-instance
		seeding + eq-normalize (directed rewriting of both sides of an = goal to
		canonical form, seeded with the constructed proof) + the ground equality
		wave (eqtri/eqcomi/per-op congruence inferences), best-first ordered.

		Kept SEPARATE from checker-mizar deliberately: under the wide union scope
		the equality chains drown in the other families' junk (measured: t90
		closes here in seconds and fails under the union). Compose in an or-else
		AFTER whole-gap closers: it closes an equality gap whole or not at all —
		it never refines, so it cannot preempt anything (the 70->51 lesson).
	}
	@arg max-iters { forward wave iterations. @defval 32 }
	@category derived
*/
scoped(congruence-scope, relnorm-scope, instantiate(ordered(loop(forward, $max-iters))))
