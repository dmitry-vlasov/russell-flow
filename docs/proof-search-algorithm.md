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

## Search strategy

The search is controlled by a **strategy** object that determines:

- **Expansion order**: which nodes to expand next (breadth-first, depth-first, heuristic)
- **Termination conditions**: limits on tree size, depth, or time
- **Pruning rules**: when to abandon a branch

The default strategy for `reprove-oracle` uses the existing proof as an **oracle**: it expands nodes in the order suggested by the known proof, dramatically reducing the search space.

### Oracle-guided reproof

The `reprove-oracle` task takes an existing proof tree and uses it as a guide:

1. For each step in the existing proof, the oracle suggests which assertion to try first
2. The prover tries the oracle's suggestion; if it succeeds, it proceeds to the next step
3. If the oracle's suggestion fails (e.g., because an assertion was renamed or its statement changed), the prover falls back to general search

This is used to re-verify proofs after refactoring, or after translating from Metamath.

---

## Implementation

The prover is implemented across several files:

| File | Role |
|------|------|
| `src/ru/prover/task.flow` | `RuProverTask` — the proof goal and premises |
| `src/ru/prover/tree.flow` | `RuProverTree`, PVT nodes, proof linearization |
| `src/ru/prover/env.flow` | `RuProverEnv` — prover configuration and callback functions |
| `src/ru/prover/expand.flow` | Node expansion: assertion and premise unification |
| `src/ru/prover/conf.flow` | `RuProverConf` — time limits and search parameters |
| `src/ru/prover/reprove_oracle.flow` | Oracle-guided reprover |

### Key types

```flow
// The goal: prove `goal` given `premises`
RuProverTask(
    info     : RuDeclInfo,
    strategy : RuProverStrategy,
    header   : string,
    args     : Tree<string, string>,
    premises : [RuPremise],
    goal     : RuStep
);

// A node in the PVT
RuProverExp(
    id       : int,
    stmt     : RuStmt,           // the goal expression
    parent   : RuProverParent,   // parent a-node or root
    children : Vector<RuProverProp>, // candidate a-nodes
    proven   : ref [RuProverProof]
);

// An a-node: one candidate assertion application
RuProverProp(
    id        : int,
    assertion : RuAssertion,
    outer     : RuSubst,         // unifier: assertion conclusion → goal
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
