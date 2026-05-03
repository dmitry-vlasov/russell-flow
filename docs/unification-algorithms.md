# Unification Algorithms

Unification is the engine of Russell's proof search. Given a goal expression and an assertion, the prover must find a substitution that maps the assertion's conclusion onto the goal. This document describes the unification algorithms used in Russell.

---

## Unification semantics

Russell uses **one-sided unification** (also called matching): given a *template* `e1` and a *target* `e2`, find a substitution σ such that:

```
σ(e1) = e2
```

Only variables in `e1` are bound. Variables in `e2` are treated as constants. This is more restricted than standard unification but sufficient for proof search (the template is always the assertion's conclusion and the target is always the current goal).

### Uniqueness

If a matcher exists, it is unique: there is at most one most-general matcher. This simplifies the algorithm — there is no need to track multiple candidate substitutions.

---

## Expression representation

Expressions are represented as flat arrays of **nodes**. Each node is one of:

```flow
RuVar(id : int, type : int)      // a variable of a given sort
RuConstRef(id : int)             // a constant symbol
RuRuleNode(rule : int, type : int, arity : int, len : int)
                                  // an application of a grammar rule
```

The `RuRuleNode` encodes:
- `rule` — the rule being applied
- `type` — the result type of the rule
- `arity` — number of direct children
- `len` — total number of nodes in the subtree (for O(1) skipping)

This flat representation enables efficient tree traversal without pointer chasing.

---

## Basic unification: `ruMatchExp`

The core matching function takes a template expression, a target expression, and returns an optional substitution:

```flow
ruMatchExp(templ : RuExp, target : RuExp, math : RuMath, env : RuEnv) -> Maybe<RuSubst>
```

The algorithm walks both expressions in parallel:

1. If the template node is a **variable**, bind it to the corresponding subtree in the target
   - If the variable is already bound, check that the new binding is consistent
2. If both nodes are **rule nodes** with the same rule, recurse into their children
3. If both nodes are the **same constant**, continue
4. Otherwise, unification fails

### Type coercion

Russell has a type hierarchy where sub-types can be coerced to super-types via coercion rules. When a template variable of type `T` is matched against a target expression of sub-type `S ⊂ T`, the coercion rule is inserted automatically.

---

## Index-based unification: `ruUnifyIndex`

Proof search requires unifying a goal against potentially thousands of assertions. A naive O(n) scan would be too slow. Russell uses a **trie-based index** (`RuIndex`) that allows O(log n) lookup.

### Index structure

```flow
RuIndex<V>(
    data  : Maybe<V>,            // value at this node (if any)
    rule  : Tree<int, RuIndex<V>>,  // children by rule ID
    var   : Tree<int, RuIndex<V>>   // children by variable type
);
```

Each expression is stored in the index by its structure: at each node position, the index branches on the rule being applied (for rule nodes) or on the variable type (for variable nodes). This allows the unifier to skip entire subtrees of assertions that cannot possibly match.

### Lookup algorithm: `ruDoUnifyIndex`

Given a target expression `e` and a substitution `s` accumulated so far:

1. If the current index has a value, record a match with substitution `s`
2. Branch on the current node of `e`:
   - **Rule node**: follow the `rule` branch for this rule ID; recurse into sub-expressions
   - **Variable node** (from target): this position in `e` is a variable — it can match any stored expression of the correct type; follow all `var` branches for compatible types; also try the `rule` branches (the variable in `e` acts as a wildcard)
   - **Constant**: treat as a zero-arity rule node

### Variable type compatibility

When the target has a variable, all index branches for types that are sub-types of the variable's type are explored. This is computed once and cached in `RuSubst`'s type information.

### Substitution merging

Each branch of the index exploration may produce a different partial substitution. When two branches are combined (e.g., when a variable in the template is matched at different positions), the substitutions must be merged:

```flow
ruMergeSubst(s1 : RuSubst, s2 : RuSubst) -> Maybe<RuSubst>
```

This returns `None()` if the two substitutions conflict (bind the same variable to different expressions).

---

## Multi-index unification: `ruUnifyIndexes`

The prover must simultaneously unify a goal against multiple indexes (one for assertions, one for premises). The function:

```flow
ruUnifyIndexes(
    exps     : [RuExp],
    indexes  : [RuIndex<?>],
    subst    : RuSubst,
    math     : RuMath,
    env      : RuEnv
) -> [RuSubst]
```

This finds all substitutions that simultaneously unify `exps[i]` against `indexes[i]` for all `i`.

### Variable unification across indexes

When an index contains a variable node (i.e., the stored expression has a variable), the algorithm must handle the case where two indexes assign different expressions to the same variable. The function `ruUnifyIndexesStepVar` handles this by:

1. Collecting all possible bindings from each index independently
2. Merging the bindings pair-wise and keeping only consistent combinations

This ensures that shared variables between different hypotheses are bound consistently.

---

## Substitution operations

### Application: `ruApplySubst`

```flow
ruApplySubst(e : RuExp, subst : RuSubst) -> RuExp
```

Replaces each variable in `e` with its image in `subst`. The result is a ground expression (no free variables from the domain of `subst`).

### Composition: `ruUnsafeCompose`

```flow
ruUnsafeCompose(s1 : RuSubst, s2 : RuSubst) -> RuSubst
```

Composes two substitutions: `(s1 ∘ s2)(x) = s2(s1(x))`. Called "unsafe" because it does not check for variable capture — it is used in contexts where capture is not possible by construction.

### Merging: `ruMergeSubst`

```flow
ruMergeSubst(s1 : RuSubst, s2 : RuSubst) -> Maybe<RuSubst>
```

Merges two substitutions, returning `None()` if they are inconsistent.

---

## Constant environments

To avoid variable capture during proof search, Russell **replaces variables with fresh constants** before searching. This is done via `ruMakeConstEnv`:

1. For each variable `v` in the theorem, create a unique constant `_v`
2. Add grammar rules for each new constant (so the type system still accepts them)
3. The `doconst` function maps each `v` → `_v`
4. The `unconst` function maps each `_v` → `v` (used to recover the final proof)

After proof search, the proof steps are un-constantified to restore the original variables.

---

## Performance characteristics

| Operation | Complexity |
|-----------|------------|
| `ruMatchExp` | O(|e1| + |e2|) |
| `ruSetIndex` | O(|e| · log n) |
| `ruUnifyIndex` | O(|e| · k) where k = number of matches |
| `ruMergeSubst` | O(|domain| · log |domain|) |

The trie index eliminates most non-matching candidates early: an assertion whose conclusion starts with rule `r1` is never visited when the goal starts with rule `r2 ≠ r1`. In practice, the index reduces the effective search space by 2–3 orders of magnitude compared to linear scan.

---

## Key source files

| File | Contents |
|------|----------|
| `src/ru/unify/index.flow` | `RuIndex`, `ruUnifyIndex`, `ruDoUnifyIndex` |
| `src/ru/unify/indexes.flow` | `ruUnifyIndexes`, multi-index unification |
| `src/ru/unify/unified.flow` | `RuUnified`, unified result type |
| `src/ru/subst.flow` | `RuSubst`, `ruApplySubst`, `ruMergeSubst`, `ruUnsafeCompose` |
| `src/ru/index.flow` | `ruSetIndex`, `ruIndexValues`, `ruFoldIndex` |
