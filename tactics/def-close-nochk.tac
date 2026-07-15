/**
	@help {
		ABLATION variant of def-close-checker WITHOUT the install `checker` atom
		(the Law-2 violator): the general refine passes + the law-abiding engine
		decomposition only. Measures what the install still uniquely closes.
	}
	@category presets
*/
or-else(
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite)),
	replay(refine(strip-forall, conj-split, rel-intro, eq-rewrite, nnf=on)),
	checker-engine
)
