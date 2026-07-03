/**
	@help {
		The `refute` decomposition atom: close a goal by the classical refutation lemmas — backward search
		SCOPED to `refute-scope` (¬¬-elim notnotr, reductio pm2.65i/mto, ex-falso pm2.21, falsum falim,
		deneg iman/imnan). This is the law-abiding, engine-driven slice of the Mizar `by`-checker's
		refutational core: instead of a bespoke off-engine "assume ¬G, saturate to ⊥" decider, the engine
		applies the refutation rules under the scope and builds the proof itself.

		A thin, orthogonal piece — compose it (seq / or-else) with `forward` (saturation) and `bfs`
		(structural close) for the full refutational checker. Bounded by max-depth / max-size.
	}
	@arg max-depth  { cap on tree depth from the root. @defval 6 }
	@arg max-size   { cap on nodes added. @defval 4096 }
	@category derived
*/
scoped(refute-scope, limited(bfs, size=$max-size, depth=$max-depth))
