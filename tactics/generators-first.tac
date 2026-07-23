/**
	@help {
		BOTH direct generators first (the planner-executor campaign, stage two): try
		`equalizer-gen` (equality goals), then `unifier-gen` (non-equality goals —
		the Mizar Unification generator), then fall through to the standard
		checker-pipeline stages. Each generator is a silent no-op outside its
		fragment, so the composition costs at most the two budgeted decision
		attempts per step.

		Slices are FIXED (4500/1500/1000 ms) because a custom tac= is invoked BARE
		by translate/mizar/pipeline — named args would not bind ($-args here parse
		to nothing and the whole tactic dies: the -28/87 lesson).
	}
*/
or-else(equalizer-gen, unifier-gen, limited(def-close-checker, time=4500), limited(checker-mizar, time=1500), limited(checker-eq, time=1000), checker-el)
