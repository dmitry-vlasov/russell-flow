/**
	@help {
		def-close ∪ the DECOMPOSED engine `checker` ∪ the install `checker` fallback. Tried in order, first
		to close a gap wins:
		  1. def-close      — structural refiners + a prop-scope bounded BFS (nnf off then on).
		  2. checker-engine — the LAW-ABIDING decomposition of the checker: the same refiners closed over the
		                      FULL checker-scope (prop ∪ refute ∪ exmid ∪ bridge ∪ unfold), reconstructed
		                      through the engine by `replay` — no install, no off-engine proof construction.
		                      Closes refutation / case-split / ⊆-bridge gaps def-close's prop-only closer
		                      cannot.
		  3. checker        — the install emitter (a STANDALONE off-engine build-and-install tactic; a Law-2
		                      violator). Kept as the FINAL fallback so no coverage is lost while the engine
		                      decomposition's L2 guided search matures; retire it once checker-engine (and
		                      checker-bidir) subsume its coverage.
	}
	@category presets
*/
or-else(
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite)),
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite, nnf=on)),
	checker-engine,
	checker
)
