/**
	@help {
		Linear-guided premise-spine replay with a breadth-first fallback.

		A composition: each round, linear-guided replays corpus goal->premise chains; whatever it
		leaves open (off-spine goals) is closed by a bounded BFS. Repeated up to max-iters times,
		capped at total-size nodes:
		  limited(loop(seq(linear-guided(attempts),
		                   limited(bfs, size=max-size, depth=max-depth, batch=16)),
		               max-iters),
		          size=total-size)
		The bounded BFS fallback is REQUIRED -- linear-guided only replays the spine and leaves
		off-spine goals for the next stage. Mirrors the default strategy in
		scripts/reprove/forward-closure.rus. Needs the corpus step index (built automatically because
		the resolved body contains `linear-guided`).
	}
	@arg attempts   { goal->premise chains to try. @defval 3 }
	@arg max-size   { BFS-fallback node cap. @defval 4096 }
	@arg max-depth  { BFS-fallback depth cap. @defval 5 }
	@arg max-iters  { outer loop iterations over linear-guided+bfs (stops early on no progress). @defval 1 }
	@arg total-size { global node-count cap on the whole strategy. @defval 10000 }
	@category presets
*/
limited(
	loop(
		seq(
			linear-guided($attempts),
			limited(bfs, 0, 0, 0, $max-size, $max-depth, 16)
		),
		$max-iters
	),
	0, 0, 0, $total-size
)
