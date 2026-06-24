/**
	@help {
		Sub-proof replay with a breadth-first fallback (the canonical corpus-fragment strategy).

		A composition: each round, spr replays held-out corpus sub-proofs as guides; whatever it
		leaves open is closed by a bounded BFS. The round is repeated up to max-iters times, and the
		whole search is capped at total-size nodes:
		  limited(loop(seq(limited(spr(attempts), size=max-size),
		                   limited(bfs, size=max-size, depth=max-depth, batch=16)),
		               max-iters),
		          size=total-size)
		Replaces the inlined strategy in scripts/reprove/spr.rus. Needs the corpus step index (built
		automatically because the resolved body contains `spr`).
	}
	@arg attempts   { held-out corpus sub-proofs to try as guides per leaf. @defval 3 }
	@arg max-size   { per-stage node cap (spr replay and the BFS fallback). @defval 4096 }
	@arg max-depth  { BFS-fallback depth cap. @defval 5 }
	@arg max-iters  { outer loop iterations over spr+bfs (stops early on no progress). @defval 1 }
	@arg total-size { global node-count cap on the whole strategy. @defval 10000 }
	@category derived
*/
limited(
	loop(
		seq(
			limited(spr($attempts), 0, 0, 0, $max-size, 0, 0),
			limited(bfs, 0, 0, 0, $max-size, $max-depth, 16)
		),
		$max-iters
	),
	0, 0, 0, $total-size
)
