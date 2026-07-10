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
		70->51 lesson). Stage order and slices are the measured optimum — see
		scripts/test/prover/mizar/README.md and the budget-discipline notes.
	}
	@category derived
*/
or-else(limited(def-close-checker, time=4500), limited(checker-mizar, time=1500), limited(checker-eq, time=1000), checker-el)
