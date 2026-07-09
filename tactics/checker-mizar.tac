/**
	@help {
		The MIZAR `by`-checker as a pure L2 composition (mizar-tactic-plan P6) — the
		refutational decision procedure of the Mizar checker (~/dev/system/kernel/:
		negate the thesis, saturate the fact set to a contradiction), built entirely
		from the plan's atoms and lemma-set scopes, zero off-engine construction:

		  assume-neg        — the pivot: seed ¬G→¬G (id) and ¬G→hyp_i (a1i); the wave
		                      saturates UNDER ¬G, a clash (pm2.65i → ¬¬G, notnotri → G)
		                      concludes G, the ROOT JOIN completes the proof.
		  ordered           — ground-first best-first waves (≺ = metavar count, size):
		                      the ¬G deductions and premises advance; schematic 0-hyp
		                      lemma-facts stay fill-only (their syl/mpd self-compositions
		                      would regenerate the whole theory as junk).
		  the union scope   — checker-scope (prop∪refute∪exmid∪bridge∪unfold: the clash,
		                      case-split and ⊆/=/∈ machinery) ∪ normalize-scope (forward
		                      NNF of ¬G: simplim/conax1/simpl/simpr/pm2.45-46 + syl/mpd
		                      = Mizar's PreCheck) ∪ exists-scope (on-engine skolemization:
		                      sp/exlimiv/eximi, eigenvar DV rides ordinary application)
		                      ∪ cluster-scope (rounding-up: sstri/sseli).

		The BIDIRECTIONAL MEET: a bounded backward bfs runs FIRST and registers
		its subgoals in backwardFront (e.g. the ¬¬G rung under notnotri); the
		forward saturation then joins them AT FACT CREATION (ruReverseJoin) — the
		large late rungs of a clash chain never need their own expansion turn.
		`instantiate` installs the front at build time so the bfs subgoals
		register; `ordered` also sets the join-admission policy (structured
		subgoals only — bare cut-slots stay for the backward search).

		One saturation over the union — the same shape as Mizar's per-clause Equate/
		Unification run (the per-family staging of the plan sketch collapses anyway:
		the forward front's assertion index is frozen at seeding). Each family's
		load-bearing role is proven by its own differential test
		(scripts/test/prover/mizar/01-05). Bounded by the per-goal timer and
		forward-search-size — LIMITS, never variant discards.
	}
	@arg max-iters { forward wave iterations. @defval 24 }
	@arg bfs-size  { backward register step: node cap. @defval 256 }
	@arg bfs-depth { backward register step: depth cap. @defval 3 }
	@category derived
*/
scoped(normalize-scope, exists-scope, cluster-scope, clash-scope, congruence-scope,
	instantiate(assume-neg(seq(
		limited(bfs, size=$bfs-size, depth=$bfs-depth, batch=8),
		ordered(loop(forward, $max-iters))))))
