# Unilambda

Unilambda is a small bidirectional, non-deterministic language built on Russell's
kernel. Its central idea: an inference rule is a function whose arguments are
**unified** (not merely substituted) on application, so the same definition can
be run **forward** (compute a result) or **backward** (find inputs that produce a
result).

Two operators name the directions:

- **`eval`** — forward, leaves → root. For a proof term this is *verification* /
  principal-statement inference; for a function it is reduction.
- **`uneval`** — backward, root → leaves. This is *proof search* / narrowing:
  given a goal, find substitutions that make a term reduce to it. It is
  non-deterministic — a query may have many solutions.

Because unilambda terms **are** Russell expressions (`RuExp`), and unification,
matching and substitution are Russell's own order-sorted `ruUnifyExps` /
`ruMatchExp` / `ruApplySubst`, unilambda is best understood as *Russell's kernel
re-presented as a bidirectional, recursive, non-deterministic language* — one
order-sorted term universe shared with the prover and verifier.

There are three ways to use it, in increasing generality.

---

## 1. Proof terms over assertions — `uni-eval`

A **proof term** is an application tree over the names of loaded assertions
(axioms / theorems). `eval` computes the most general statement that term proves
— exactly what the verifier computes, but with unification instead of one-way
matching, so the statement is *inferred* rather than checked.

```
russellj uni-eval term=ax-mp(x,ax-mp(y,z))
  ax-mp(x,ax-mp(y,z))  ==>  \ ph, ps, ( ph -> ( ps -> ch ) ) |- ch        (mp2)
```

A bare identifier that names a loaded assertion is a nullary application (e.g.
`ax-1`); any other identifier is a leaf metavariable standing for a hypothesis.
The result is printed as `\ <hyps> |- <conclusion>`, the most general statement,
up to variable renaming and hypothesis order. More examples:

```
ax-mp(x,ax-1)     ==>  \ ph |- ( ps -> ph )                  (a1i)
ax-mp(x,a2i(y))   ==>  \ ( ph -> ps ), ( ph -> ( ps -> ch ) ) |- ( ph -> ch )   (mpd)
mpd(x,a1i(y))     ==>  \ ( ph -> ps ), ( ps -> ch ) |- ( ph -> ch )             (syl)
```

> Note: arguments on the command line must avoid spaces and shell metacharacters,
> so write `ax-mp(x,ax-1)` rather than `ax-mp(x, ax-1)`.

### Interactive REPL

`server=unilambda` is a stateful REPL: load a math once, then issue many
queries. Lines starting with `eval ` are unilambda proof terms; any other line is
an ordinary Russell command, with state carried into the next prompt.

```
russellj server=unilambda
uni> conf-load dir=./set
uni> read-ru file=set/CLASSICAL_FIRST-ORDER_LOGIC_WITH_EQUALITY/Propositional_calculus/Logical_implication.ru
uni> eval ax-mp(x, ax-1)
ax-mp(x, ax-1)  ==>  \ ph |- ( ps -> ph )
uni> exit
```

---

## 2. The clause language — `uni-run`

A `.uni` program is a table of **clauses** `name = \ args |- body`, with possibly
several clauses per name, plus `eval` / `solve` queries. A symbol with clauses is
a **defined function** (reduced); a symbol with none is a **constructor** (data).
Variables carry a trailing `*`; bare identifiers are constructors / functions;
`#` starts a comment.

```
# Peano naturals
plus = \ x*, 0 |- x*
plus = \ x*, s(y*) |- s(plus(x*, y*))
mult = \ x*, 0 |- 0
mult = \ x*, s(y*) |- plus(x*, mult(x*, y*))

eval plus(s(0), s(s(0)))            # forward: 1 + 2 = 3
solve plus(x*, y*) = s(s(s(0)))     # backward: every (x, y) with x + y = 3
```

```
russellj uni-run file=test/uni/nat.uni
  eval  plus(s(0), s(s(0)))  ==>  s(s(s(0)))
  solve plus(x*, y*) = s(s(s(0)))  ==>  { x* = s(s(s(0))), y* = 0 }; { x* = s(s(0)), y* = s(0) };
                                        { x* = s(0), y* = s(s(0)) }; { x* = 0, y* = s(s(s(0))) }
```

- **`eval <term>`** reduces a term to normal form (call-by-value, first matching
  clause).
- **`solve <goal> = <target>`** narrows the goal backward, enumerating every
  substitution over the goal's variables for which it reduces to the target.

### Horn logic embeds for free

A Horn clause *is* a unilambda clause and SLD resolution *is* `uneval`, so a
classic logic program runs with no new machinery — `append/3` forward and
backward (`test/uni/horn.uni`):

```
append = \ nil, ys* |- ys*
append = \ cons(x*, xs*), ys* |- cons(x*, append(xs*, ys*))

eval  append(cons(a, cons(b, nil)), cons(c, nil))        # [a,b] ++ [c] = [a,b,c]
solve append(xs*, ys*) = cons(a, cons(b, cons(c, nil)))  # every split of [a,b,c]
```

---

## 3. Bound to Russell — `uni-brun`

A **bound** `.uni` program manipulates *real Russell formulas*. Variables are
declared with `var name : type`; a formula written in backticks is parsed by the
loaded grammar into a real order-sorted `RuExp`. The clause engine is unchanged —
it already runs on `RuExp` — so the same `eval` / `solve` operate over genuine
`wff` / `class` / `set` terms (`test/uni/wff.uni`):

```
var ph : wff
var ps : wff

ante = \ `( ph -> ps )` |- `ph`            # antecedent of an implication
swap = \ `( ph -> ps )` |- `( ps -> ph )`  # flip an implication

eval ante(`( ( a -> b ) -> c )`)           # => ( a -> b )
eval swap(`( a -> b )`)                    # => ( b -> a )
solve swap(x*) = `( a -> b )`              # backward => x* = ( b -> a )
```

This needs a loaded math (run after `conf-load` / `read-ru`); see
`scripts/test/wffbind.rus`. Russell's concrete syntax applies inside backticks —
e.g. propositional implication is the Unicode `→`, not ASCII `->`.

---

## Theory and prior art

Each operator coincides with an established notion:

- **`eval`** is forward unification / principal-statement computation; its
  machinery is that of Hindley–Milner type inference (Algorithm W), except the
  computed object is the statement (a *value*), not a *type* — there is no
  term/type stratification, so this is **not** Curry–Howard.
- **`uneval`** is backward chaining / **narrowing** (functional-logic
  programming). It is not SLD resolution intrinsically — unilambda has no literal
  / atom / negation layer; resolution reappears only under a meta-encoding that
  wraps each statement in a `Provable(·)` predicate.

Stripped of those decorations, unilambda is *bare unify-and-substitute over a
single term universe, with multiple non-deterministic clauses* — the operational
core of definite logic programming / narrowing, here over Russell's binder-free,
**first-order, order-sorted** signature (which keeps unification decidable and
sidesteps the higher-order-unification undecidability that forces λProlog/Isabelle
into semi-decidable search).

`uneval`/`solve` search is bounded by a `fuel` depth limit: undirected narrowing
is only semi-decidable, so deeper solutions beyond the bound are not found.
Target-directed (needed) narrowing would tame this; it is future work.

---

## Implementation

All under [`src/uni/`](../src/uni/), reusing [`src/ru/unify/`](../src/ru/unify/)
and [`src/ru/subst.flow`](../src/ru/subst.flow):

| File | Role |
|------|------|
| `eval.flow` | proof-term `eval` over assertions (generalizes the verifier's `ruMatchStep`) |
| `run.flow` | the `RuExp`-native clause engine: `uniReduce` (forward), `uniSolve` (backward narrowing), `.uni` parser |
| `bound.flow` | bound mode: `var` decls + backtick formulas parsed via the grammar |
| `ast.flow`, `parse.flow` | proof-term AST and parser |
| `comms.flow` | the `uni-eval` / `uni-run` / `uni-brun` commands |

Key reused facts: a `RuRuleNode`'s `len` field is its children's node count (so a
flat-RPN `RuExp` decomposes into head + arguments by `subrange`), and
`ruUnifyExps` consults the language only for order-sorted *subtype* checks (so
same-sort terms unify with an empty math, and real math plugs in for the
multi-sort wff/class/set case).

---

## Running the tests

```bash
cd ~/dev/math
russellj test/uni no-server=1                 # comprehensive: all three engines, eval + uneval
russellj test/uni afile=set-3000 no-server=1  # against a smaller translated prefix
```

The clause-only fixtures need no math and run from the repo:

```bash
russellj uni-run file=scripts/test/uni/nat.uni  no-server=1
russellj uni-run file=scripts/test/uni/horn.uni no-server=1
```

`test/uni` is also step 3 of the full battery (`scripts/test/all.rus`) and of the
CI workflow.
