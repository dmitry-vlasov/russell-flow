# Proof Search Algorithm

Russell implements a backward-chaining proof search algorithm based on the theoretical framework described in the paper:

> **"Proof Search Algorithm in Pure Logical Framework"** by Dmitry Vlasov  
> See [`proof_search_algo.pdf`](proof_search_algo.pdf) for the full theoretical treatment.

---

## Deductive system

### Grammar

The underlying logic is typed. Terms are built from:

- **Variables** of each sort (type)
- **Rules** (grammar productions) that combine sub-terms into terms of a new sort

A **substitution** σ maps variables to terms. The application σ(e) replaces each variable in expression `e` by its image under σ.

### Unification

Unification in Russell is **non-symmetric**: given a *template* expression `e1` and a *target* expression `e2`, the unifier σ satisfies:

```
σ(e1) = e2
```

Only variables in `e1` can be bound; `e2` is treated as ground. If a unifier exists, it is unique (there is at most one most-general unifier for a given template/target pair). See [unification-algorithms.md](unification-algorithms.md) for implementation details.

### Inference

An assertion (axiom or theorem) has the form:

```
H1, H2, ..., Hk ⊢ C
```

where `H1..Hk` are hypotheses and `C` is the conclusion. To apply an assertion to prove a goal `G`:

1. Find a substitution σ such that σ(C) = G
2. Generate subgoals σ(H1), ..., σ(Hk)

---

## Proof Variant Tree (PVT)

The central data structure is the **Proof Variant Tree** (PVT). It represents all partial proof attempts simultaneously, without committing to a single path prematurely.

The PVT alternates between two node types:

### E-nodes (expression nodes)

An e-node represents a **goal expression** to be proved. It stores:
- The goal expression
- All **a-nodes** that could prove this goal (one per matching assertion)

### A-nodes (assertion nodes)

An a-node represents a **candidate assertion application**. It stores:
- The assertion being applied
- The substitution that unifies the assertion's conclusion with the goal
- Child e-nodes for each hypothesis of the assertion (after substitution)

When an a-node has all its children resolved, the corresponding proof variant is complete.

### Growth of the PVT

Starting from the root e-node (the theorem's conclusion):

1. **Expand** an e-node: find all assertions whose conclusion unifies with the goal; create one a-node per match
2. **Expand** an a-node: create child e-nodes for each hypothesis
3. **Resolve** a leaf e-node against the known premises (hypotheses) of the theorem

The tree grows by repeatedly expanding unresolved nodes.

---

## Substitution Proof Trees (SPT)

Each a-node carries a **Substitution Proof Tree** (SPT) that encodes the set of partial proof variants reaching this node, together with their accumulated substitutions.

When an assertion is applied with substitution σ at an a-node `a`:

- The parent e-node propagates its current substitution set to `a`
- `a` combines the parent's substitutions with σ using substitution composition

### Unification of substitution sets

A key operation is the **most general unification of a set of substitutions** Ξ = {σ1, ..., σn}. This computes the most general substitution that is consistent with all σi simultaneously. If any pair of substitutions conflicts on a variable, the set has no unifier and the corresponding proof branch is pruned.

This operation is what allows the PVT to propagate constraints *upward* through the tree: a binding discovered deep in the tree (at a leaf) must be consistent with all bindings along the path to the root.

---

## Search tactics

The search is controlled by a **tactic** — a small object that on each step tells the engine:

- which leaves to expand next (`RuTacticExpand([…])`);
- when to hand off to a different tactic (`RuTacticSwitch(next)`);
- when to stop entirely (`RuTacticDone`).

```flow
RuProverTactic(
    description    : string,
    step           : (RuProverTree) -> RuProverTacticStep,
    keep_expanding : (RuProverTree) -> bool   // pure predicate
)
```

The unified type subsumes what was earlier split into `RuProverTactic` (single-step) and `RuProverStrategy` (sequencer over tactics). Tactic transitions are explicit and visible — `Switch` makes the next tactic obvious in the description chain — and combinators are pure functions over tactics.

The companion `keep_expanding` predicate is checked **per-prop during parallel batch expansion** so a tactic's budget (tree size, depth, time, proved) can short-circuit a large batch the moment it's exceeded, without waiting for the next `step` call.

### Primitives

The tactic library in [`src/ru/prover/tactics/`](../src/ru/prover/tactics/) contains a flat catalogue of building blocks: breadth-first search (`bfs`, `bounded-bfs`, `top-n-bfs`, `unif-quality`), follow-proof replay (`follow_proof`), corpus-guided fragment lookup (`subproof_replay`, `fragment_replay`), oracle-guided search (`oracle`), ML-guided premise selection (`ml`), and linear-guided search over fragment-backed completions (`linear-guided`).

### Combinators

Three combinators ([`combinators.flow`](../src/ru/prover/tactics/combinators.flow)) compose tactics into larger strategies:

- `ruSequenceTactic([t1, t2, …])` — runs each tactic in turn; advances on `Done`, swaps in place on `Switch`.
- `ruIterateTactic(driver)` — calls `driver(tree)` to produce a fresh sub-tactic on every iteration; re-enters when the sub-tactic finishes, which is how SPR properly exhausts its attempts/pending queue.
- `ruLoopWhileProgressTactic(builder, max_iters)` — rebuilds the inner tactic on each iteration (so state-carrying tactics reset) and re-runs while the proof tree keeps growing.

A `ruLimitedTactic(inner, time, applied, produced)` wrapper attaches per-tactic budget limits.

### DSL

The full tactic library is also exposed through a small string DSL — see [`tactics-language.md`](tactics-language.md) for the grammar, atoms and combinators. The universal `reprove` command takes a `tactic="…"` argument that the DSL parser turns into a `RuProverTactic` for the search.

### Oracle-guided reproof

The `oracle` tactic uses the existing proof tree as a guide:

1. For each step in the existing proof, the oracle suggests which assertion to try first.
2. The prover tries the oracle's suggestion; if it succeeds, it proceeds to the next step.
3. If the oracle's suggestion fails (e.g. because an assertion was renamed or its statement changed), the prover falls back to general search.

This is used to re-verify proofs after refactoring, or after translating from Metamath. The roundtrip test invokes `reprove strict-fail=1 tactic="oracle(-1, -1)"`.

---

## Implementation

The prover is split between the engine (in [`src/ru/prover/core/`](../src/ru/prover/core/)) and the tactic library (in [`src/ru/prover/tactics/`](../src/ru/prover/tactics/)).

### Engine

| File | Role |
|------|------|
| `src/ru/prover/core/controls.flow` | `RuProverTactic`, `RuProverTacticStep` ADT, `ruDoneTactic` sentinel |
| `src/ru/prover/core/task.flow` | `RuProverTask` — the proof goal and premises |
| `src/ru/prover/core/tree.flow` | `RuProverTree`, PVT nodes, proof linearisation |
| `src/ru/prover/core/env.flow` | `RuProverEnv` — prover state, callback functions, fragment trees |
| `src/ru/prover/core/expand.flow` | Node expansion: assertion and premise unification |
| `src/ru/prover/core/conf.flow` | `RuProverConf` — time limits and search parameters |
| `src/ru/prover/core/prove.flow` | The search loop: polls the active tactic, applies its decision |

### Tactic library

| File | Role |
|------|------|
| `src/ru/prover/tactics/breadth_first.flow` | BFS and its bounded / top-N / unif-quality variants |
| `src/ru/prover/tactics/follow_proof.flow` | Replay a known proof sub-tree |
| `src/ru/prover/tactics/linear_guided.flow` | Fragment-completion linear search |
| `src/ru/prover/tactics/subproof_replay.flow` | Corpus-guided proof-step replay (SPR) |
| `src/ru/prover/tactics/fragment_replay.flow` | Corpus-guided fragment replay |
| `src/ru/prover/tactics/oracle.flow` | Oracle-guided reprover; overrides `penv.fns` on init |
| `src/ru/prover/tactics/combinators.flow` | `seq`, `iterate`, `loop`, `limited` |
| `src/ru/prover/tactics/combined.flow` | Convenience composition of unif-quality + SPR + BFS |
| `src/ru/prover/tactics/dsl.flow` | Parser and builder for the [tactics DSL](tactics-language.md) |

### Key types

```flow
// The goal: prove `goal` given `premises`
RuProverTask(
    info     : RuDeclInfo,
    tactic   : RuProverTactic,         // placeholder; the real one is installed
                                       // by the DSL builder after env init
    header   : string,
    args     : Tree<string, flow>,
    premises : [RuPremise],
    goal     : RuStep
);

// A tactic's per-step decision
RuProverTacticStep ::= RuTacticExpand, RuTacticSwitch, RuTacticDone;
RuTacticExpand(leafs : [RuProverProp]);
RuTacticSwitch(next : RuProverTactic);
RuTacticDone();

// A node in the PVT
RuProverExp(
    id       : int,
    stmt     : RuStmt,                   // the goal expression
    parent   : RuProverParent,           // parent a-node or root
    children : Vector<RuProverProp>,     // candidate a-nodes
    proven   : ref [RuProverProof]
);

// An a-node: one candidate assertion application
RuProverProp(
    id        : int,
    assertion : RuAssertion,
    outer     : RuSubst,                 // unifier: assertion conclusion → goal
    parent    : RuProverExp,
    children  : Vector<RuProverHyp>
);
```

### Proof extraction

Once a complete proof is found, the PVT is linearized into a flat `[RuStep]` sequence:

1. `ruMakeLinearProof` traverses the proof tree depth-first
2. Each a-node becomes a `RuStep` with the assertion name and references to child steps
3. The step's statement is computed by applying the accumulated substitution to the assertion's conclusion
4. The resulting steps are verified against the theorem's conclusion

---

## Correctness properties

The algorithm satisfies three key properties (proved in the paper):

1. **Correctness**: every proof found by the algorithm is a valid proof in the deductive system
2. **Generality**: the algorithm finds the most general proof — it does not commit to specific variable bindings prematurely
3. **Completeness**: if a proof exists within the search bound, the algorithm finds it

The completeness guarantee requires that the PVT is expanded sufficiently — in practice, resource limits (time, memory) bound the search.

---

## See also

- [Unilambda](unilambda.md) — the same backward direction presented as `uneval`: narrowing over clause-shaped rules, with the dual forward `eval`.
