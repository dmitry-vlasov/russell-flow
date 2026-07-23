/**
	@help {
		The DIRECT-GENERATOR-FIRST pipeline: try `equalizer-gen` (the Equalizer as a
		deterministic proof generator — decide, assemble, verify, install; silent no-op
		when it does not apply), then fall through to the standard checker-pipeline.
		The acceptance harness for the planner-executor campaign: on enumset1 the
		generator alone closes every equality theorem, and everything it cannot decide
		takes the ordinary route unchanged.
	}
	@arg dc-time    { def-close-checker slice, ms @defval 4500 }
	@arg mizar-time { checker-mizar slice, ms @defval 1500 }
	@arg eq-time    { checker-eq slice, ms @defval 1000 }
*/
or-else(equalizer-gen, limited(def-close-checker, time=$dc-time), limited(checker-mizar, time=$mizar-time), limited(checker-eq, time=$eq-time), checker-el)
