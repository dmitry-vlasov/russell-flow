/**
	@help {
		Definitional closure: build a goal-refinement guide and have the engine reconstruct it.

		A composition (not a primitive atom). It tries the refiner guide, falling back to its
		De Morgan (nnf) variant:
		  or-else(
		    replay(refine(strip-forall, rel-intro, eq-rewrite,         <budget>)),
		    replay(refine(strip-forall, rel-intro, eq-rewrite, nnf=on, <budget>)) )
		Each refine builds a candidate proof tree -- strip the leading forall, reduce a subset/= goal
		to a membership subgoal, unfold + canonicalize compound membership atoms -- then closes the
		small residual with a bounded BFS (refine's default closer = limited(bfs, size=max-size,
		depth=max-depth, batch=16)); replay reconstructs the guide through the engine, so the result is
		sound and precedence-checked. The nnf=on alternative is built (by or-else) only if the nnf=off
		one finds no proof. max-depth/max-size/time-limit bound the BUILD-TIME guide construction (a
		wrapping `limited`, which bounds runtime engine steps, cannot reach them).

		NOTE: the refiner list (strip-forall, rel-intro, eq-rewrite) is inlined here; keep it in sync
		with ruRefinerNames() in src/ru/prover/tactics/refiners.flow.
	}
	@arg max-depth  { cap on the guide's tree depth from the root. @defval 6 }
	@arg max-size   { cap on the guide's tree size. @defval 4096 }
	@arg time-limit { per-step build budget, in seconds. @defval 60 }
	@category derived
*/
or-else(
	replay(refine(strip-forall, rel-intro, eq-rewrite,         max-depth=$max-depth, max-size=$max-size, time-limit=$time-limit)),
	replay(refine(strip-forall, rel-intro, eq-rewrite, nnf=on, max-depth=$max-depth, max-size=$max-size, time-limit=$time-limit))
)
