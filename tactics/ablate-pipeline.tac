/**
	@help {
		ABLATION: checker-pipeline with def-close-nochk (no install `checker`).
	}
	@arg dc-time    { def-close slice, ms @defval 4500 }
	@arg mizar-time { checker-mizar slice, ms @defval 1500 }
	@arg eq-time    { checker-eq slice, ms @defval 1000 }
	@category derived
*/
or-else(limited(def-close-nochk, time=$dc-time), limited(checker-mizar, time=$mizar-time), limited(checker-eq, time=$eq-time), checker-el)
