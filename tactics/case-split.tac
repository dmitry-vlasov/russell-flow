/**
	@help {
		The `case-split` decomposition atom: close a goal by classical excluded middle — backward search
		SCOPED to `exmid-scope` (exmid, pm2.61i/pm2.61d, ∨-elim jaoi/mpjaodan, pm3.24). The law-abiding,
		engine-driven slice of the Mizar `by`-checker's case-analysis: the engine applies the excluded-middle
		/ ∨-elimination rules under the scope and builds the proof itself (no bespoke off-engine case decider).

		A thin, orthogonal piece — compose it (or-else) with `refute`, `forward` and `bfs`. Bounded by
		max-depth / max-size (case-splits nest, so keep these small).
	}
	@arg max-depth  { cap on tree depth from the root. @defval 4 }
	@arg max-size   { cap on nodes added. @defval 4096 }
	@category derived
*/
scoped(exmid-scope, limited(bfs, size=$max-size, depth=$max-depth))
