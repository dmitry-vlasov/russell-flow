/**
	@help {
		The MEMBERSHIP-UNFOLD refutation as its own pipeline stage (the ∈-class:
		t44-style el-goals). el-scope only: guarded chains under assume-neg's ¬G
		antecedent — the el-biconds (elun/elin/eldif) consumed by syllogism/d-form
		composers (sylnib/sylib/mpi/notnotrd), bare premises unfolded by mpbi at
		bicond expansion, pm2.65i+notnotri collision, bidirectional meet.

		Kept SEPARATE from checker-mizar deliberately (the union-dilution lesson):
		under the wide union the guarded el-chains drown in the other families'
		junk. Compose in an or-else AFTER whole-gap closers.
	}
	@arg max-iters { forward wave iterations. @defval 24 }
	@arg bfs-size  { backward register step: node cap. @defval 256 }
	@arg bfs-depth { backward register step: depth cap. @defval 3 }
	@category derived
*/
scoped(el-scope, instantiate(assume-neg(seq(
	limited(bfs, size=$bfs-size, depth=$bfs-depth, batch=8),
	ordered(loop(forward, $max-iters))))))
