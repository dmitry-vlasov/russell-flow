/**
	@help {
		def-close ∪ the install `checker` ∪ the DECOMPOSED engine `checker`. Tried in order, first to close
		a gap wins:
		  1. def-close      — structural refiners + a prop-scope bounded BFS (nnf off then on).
		  2. checker        — the install emitter (a STANDALONE off-engine build-and-install tactic; a Law-2
		                      violator). Load-bearing: it closes each gap WHOLE.
		  3. checker-engine — the LAW-ABIDING decomposition of the checker: the same refiners closed over the
		                      FULL checker-scope (prop ∪ refute ∪ exmid ∪ bridge ∪ unfold), reconstructed
		                      through the engine by `replay` — no install, no off-engine proof construction.
		                      A residual pass for gaps the install misses.

		ORDERING (measured on xboole_1, clean pipeline, tl=8s): checker-engine MUST come AFTER the install.
		It closes a gap by REFINING it into sub-goals; if any sub-goal then fails to close, the theorem stays
		open — so running it BEFORE the install preempts the install's whole-gap closure and REGRESSES 70→51.
		Install-first keeps 70 (= the monolith `or-else(def-close, checker)`); checker-engine adds 0 extra on
		these two files (it does not yet subsume the install — that is the open L2 guided-search work, see
		checker-decomposition memory). Retire the install once checker-engine / checker-bidir catch up.
	}
	@category presets
*/
or-else(
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite)),
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite, nnf=on)),
	checker,
	checker-engine
)
