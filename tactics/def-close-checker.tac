/**
	@help {
		def-close variant that closes residuals with the Mizar-style Hilbert refutation `checker`
		(equalizer membership-unfold + the fresh Kalmár-free Hilbert certificate emitter) instead of the
		default bounded BFS. Structural refiners strip ∀ and split ∧; `checker` does the relational/
		membership unfold-with-proof and discharges the propositional core by refutation.
	}
	@category presets
*/
or-else(
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite)),
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite, nnf=on)),
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite, closer=checker)),
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite, nnf=on, closer=checker)),
	replay(refine(strip-forall, conj-split, closer=checker))
)
