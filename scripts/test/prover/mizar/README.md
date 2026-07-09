# Mizar-solver test suite (mizar-tactic-plan)

Differential, phase-per-file tests for building the complete Mizar `by` decision
procedure as an L2 tactic composition:

```
checker-mizar =
  assume-neg(seq(
    saturate(normalize-scope),           // PreCheck: NNF, skolemize
    loop(or-else(
      saturate(congruence-scope),        // Equalizer
      saturate(cluster-scope),           // rounding-up
      saturate(mp-scope),                // Unifier / forward MP
      reduce(refute-scope),              // SetContr
      reduce(exmid-scope)))))            // DNF: ∨-elim = every clause refuted
```

One new primitive (`ordered` — Knuth-Bendix orientation as L2 selection); all
else is scopes (data) + composition. Zero L1 changes (DV already threaded —
see the mizar-decision-gap memory / forward.flow, proofs.flow:121).

## Discipline

- **Differential**: each phase asserts "with the piece closes / without it fails".
- **Verify always**: soundness by the kernel, never trusted from the engine.
- **Golden floor**: no phase may drop the pinned counts below.
- Run from the math dir, `no-server=1`, ONE instance at a time.
- `.rus` `@help { }` blocks must not contain literal braces — the parser ends the
  block at the first inner `}` and every later `@defval` is silently lost (the
  bare `russellj test/...` invocation then dies at `read-ru`: obligatory `file`
  missing). Write lemma sets as `(a, b)` in help text, never `{a, b}`.

## Pinned golden floors (00_baseline.rus)

Settings: tl=8s, relevance-depth=0, max-depth=8, max-size=65536 — keep STABLE
so floors stay comparable across phases.

| module   | tac               | fully-proved (floor) | pinned      |
|----------|-------------------|----------------------|-------------|
| xboole_0 | def-close-checker | 1/8                  | 2026-07-08  |
| xboole_1 | def-close-checker | 70/117               | 2026-07-08  |

```
russellj no-server=1 mem=12g test/prover/mizar/00_baseline module=xboole_0 tac=def-close-checker floor=1
russellj no-server=1 mem=12g test/prover/mizar/00_baseline module=xboole_1 tac=def-close-checker floor=70
```

## Phases

| file | phase | asserts | status |
|------|-------|---------|--------|
| 00_baseline.rus | P0 | golden floor + verify | PASSES (pinned 2026-07-08) |
| 01_refute_frame.rus | P1 | assume-neg closes frame_test; bare wave fails; proof verifies | PASSES |
| 02_saturate.rus | P2 | `ordered` best-first closes the nn_ladder in 3s; FIFO drowns in 3^w junk | PASSES |
| 03_normalize.rus | P3 | normalize lemmas load-bearing (frame+clash alone fails both goals) | PASSES |
| 04_skolemize.rus | P4 | exists-scope load-bearing; TWO-eigenvar goal; verify checks DV threading | PASSES |
| 05_cluster.rus | P5 | cluster-scope load-bearing (⊆-chain; membership rounding) | PASSES |
| 06_mizar_xboole.rus | P6 | or-else(def-close-checker, checker-mizar) ≥ floor on xboole_1 + verify | PASSES — **79/117** (70 install-era → 73 meet → 75 Equalizer identity base → 79 symbol closure + ∀-strip/ax-gen + AC-permutation family); run with floor=79 |
| 07_orthogonal_only.rus | P7 | corpus closes with install atoms REMOVED | — |
| 08_instantiate.rus | Unifier | `instantiate` closes a one-step ∀-at-a-term instance; control (no frame) fails; verifies | PASSES |
| 09_congruence.rus | Equalizer | congruence-scope + identity-instance seeding close set-algebra equalities (direct + chained + ∀-wrapped + AC-collapse: cong6/cong7 = t92/t100 verbatim); controls fail; verify | PASSES |

## The assembly (tactics/checker-mizar.tac)

`scoped(normalize-scope, exists-scope, cluster-scope, clash-scope,
assume-neg(ordered(loop(forward, N))))` — ONE composition closes every corpus
theorem of 01-05 (frame, norm×2, skolem×2, cluster×2) with no per-test tuning.
Design facts found during assembly:
- the forward wave must get ONLY the forward families; the wide checker-scope
  (prop/bridge/unfold) explodes the fill side (1 expansion → 135 facts) and the
  refute-scope contraposition family (con*i, notnoti, mto/mt*) is a GROUND-junk
  factory ground-first cannot separate — hence `clash-scope` = {pm2.65i,
  notnotri} only: Mizar's Equate DETECTS the collision, it does not saturate
  contrapositives;
- inference forms are the forward-triggerable ones: notnotri (not notnotr+ax-mp),
  spi (not sp) — a 0-hyp schematic fact expands once and is then trigger-blind;
- `scoped()` now UNIONS named scopes in multi-name lists (dsl.flow fix);
- flowc silently reuses the last good javagen on compile errors — always check
  build output for "Error", not just "done".

## Test corpora (<math>/rus-test/)

- `frame_test.ru` (P1): premises ps, ¬ps; goal ph — closes ONLY via the
  assume-neg seeds (raw premises trigger nothing in {pm2.65i, notnotri}).
- `nn_ladder.ru` (P2): 16 ¬'s; 8 notnotri rungs, each triggered by the previous
  rung's output = a true 8-WAVE ladder. NOTE: equality/implication CHAINS cannot
  test ordering — the live-index fill side cascades any all-seeds-present chain
  to closure inside wave 1; only 1-hyp trigger ladders enforce wave depth.
- `norm_test.ru` (P3): two 0-premise goals (Clavius; nested ∧/→) whose only seed
  under assume-neg is ¬G→¬G — closable only by decomposing ¬G. Neither statement
  equals a scope lemma (no trivial root-join). 0-hyp theorems have NO `----------`
  separator (that line is hyp-block syntax).
- `skolem_test.ru` (P4, set-20000): one- and TWO-witness ∃-chains; the theorems
  declare the disjointed() the exlimiv eigenvariables need — verify proves the
  DV rides the whole wave+join path (the zero-L1-change skolemization claim).
- `cluster_test.ru` (P5, set-50000): ⊆-chain + membership rounding-up. sseli in
  this corpus is the 1-HYP form (A⊆B ⊢ (C∈A)→(C∈B)) — composers must ride along.

## Engine findings recorded while building this suite

- ROOT JOIN (forward.flow ruReverseJoin): a forward fact matching the root
  statement now completes the proof via ruBuildRootProofs. Before, only
  Prop-created subgoal E-nodes were joinable (expand.flow:124) — a pure-forward
  tactic could derive the goal yet never conclude.
- `ordered` = BEST-FIRST waves with ≺ = (metavar count, term size) — GROUND-first,
  then smallest; defer the rest. Not per-wave sorting (still expands the whole
  junk front each wave), and not size-only (the useful ¬G facts are LARGE while
  schematic junk is small). Ground-first also keeps seeded 0-hyp lemma-facts
  FILL-only: their syl/mpd self-compositions would regenerate the whole theory.
- Lemma FORMS matter for the wave: simprim (⊢¬(φ→¬ψ)→ψ) cannot strip an ATOMIC
  consequent — the ¬(a→b)→¬b decomposition needs conax1. Check the statement,
  not the name.
- `ruMaybeBuildForwardFront` pre-build is now timer-bounded (it was unbudgeted:
  ~85s/theorem against a 5s reprove budget — the forward-bidir gate ran 50+ min;
  now 57s at stride=100 tms=5000 forward-search-size=256).
- `scoped(a, b, …, t)` gotcha: with MULTIPLE names each is an ASSERTION name;
  named scopes (refute-scope …) are NOT expanded inside a list.
