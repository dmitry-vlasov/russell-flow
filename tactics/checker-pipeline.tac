/**
	@help {
		THE Mizar article pipeline composition — the exact 4-stage or-else the
		06_mizar_xboole floor test measures (xboole_1 = 116/116 with tl=8s):
		  1. def-close-checker (the A-map auto-prover incl. the Kalmar-free
		     checker) under a 4500 ms slice — the budget hog, needs a virgin PVT;
		  2. checker-mizar (the full refutational composition) under 1500 ms;
		  3. checker-eq (the NARROW Equalizer — equality gaps close whole here
		     where the union scope drowns them) under 1000 ms;
		  4. checker-el (the NARROW membership-unfold refutation) with the rest.
		Stages 2-4 close whole-or-nothing, so they cannot preempt anything (the
		70->51 lesson). The default slices are the measured optimum for tl=8s on
		an otherwise idle machine — see scripts/test/prover/mizar/README.md.
		The slices are WALL-CLOCK milliseconds: on a loaded/throttled machine
		widen them (and tl accordingly), e.g. dc-time=9000 mizar-time=3000
		eq-time=2000 with tl=16s.
	}
	@arg dc-time    { def-close-checker slice, ms @defval 4500 }
	@arg mizar-time { checker-mizar slice, ms @defval 1500 }
	@arg eq-time    { checker-eq slice, ms @defval 1000 }
	@category derived
*/
or-else(limited(def-close-checker, time=$dc-time), limited(checker-mizar, time=$mizar-time), limited(checker-eq, time=$eq-time), checker-el)
