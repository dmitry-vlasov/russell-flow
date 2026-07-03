/**
	@help {
		The BIDIRECTIONAL engine `checker` (checker-decomposition, experimental): a scoped meeting of forward
		saturation and backward search — the law-abiding realization of the Mizar `by`-checker's forward
		saturate-to-contradiction procedure, entirely on-engine.

		Under the full `checker-scope` (prop ∪ refute ∪ exmid ∪ bridge ∪ unfold), it loops `or-else(forward,
		bfs)`: `forward` seeds the front from the premises + the scope's 0-hypothesis axioms (exmid, pm3.24,
		noel, …) and advances it one wave — deriving consequences that JOIN the backward subgoals `bfs`
		opens; `bfs` closes structurally. Forward runs first each round so the forward front is installed
		before backward subgoals are created (they register for the reverse join). All proofs are engine-built
		(ruAddProof) — no install, no off-engine construction.

		NOTE: unguided, the forward saturation + cut can explode; this is the open L2 guided-search problem.
		Keep it behind the refine-based `checker-engine` / install `checker` in an or-else so its cost is
		only paid on gaps the cheaper paths miss. Bounded by max-size / max-depth / max-iters.
	}
	@arg max-depth  { cap on backward tree depth from the root. @defval 6 }
	@arg max-size   { cap on backward nodes added per bfs pass. @defval 8192 }
	@arg max-iters  { forward/backward alternation rounds. @defval 3 }
	@category derived
*/
scoped(checker-scope,
	loop(
		or-else(forward, limited(bfs, size=$max-size, depth=$max-depth)),
		max-iters=$max-iters))
