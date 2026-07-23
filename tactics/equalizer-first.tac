/**
	@help {
		The DIRECT-GENERATOR-FIRST pipeline: try `equalizer-gen` (the Equalizer as a
		deterministic proof generator — decide, assemble, verify, install; silent no-op
		when it does not apply), then fall through to the standard checker-pipeline
		stages. The acceptance harness for the planner-executor campaign: on enumset1
		the generator alone closes every equality theorem, and everything it cannot
		decide takes the ordinary route unchanged.

		Slices are FIXED (4500/1500/1000 ms) because a custom tac= is invoked BARE by
		translate/mizar/pipeline — named args would not bind ($-args here parse to
		nothing and the whole tactic dies: the -28/87 lesson).
	}
*/
or-else(equalizer-gen, limited(def-close-checker, time=4500), limited(checker-mizar, time=1500), limited(checker-eq, time=1000), checker-el)
