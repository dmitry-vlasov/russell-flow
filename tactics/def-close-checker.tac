/**
	@help {
		def-close ∪ the Mizar-style Hilbert refutation `checker`. First try def-close (structural refiners +
		bounded BFS); if it can't close the gap, `checker` (a STANDALONE install tactic: strip ∀, prove the
		body with the fresh Kalmár-free Hilbert emitter — refutation + ⊆/=/∈ bridge + membership unfold —
		re-quantify with ax-gen, install directly). checker installs its proof rather than going through
		replay, which can't reconstruct the emitter's ssrdv/deneg steps.
	}
	@category presets
*/
or-else(
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite)),
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite, nnf=on)),
	checker
)
