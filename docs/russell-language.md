# The Russell Language

Russell is a formal language for mathematics inspired by [Metamath](https://us.metamath.org/), extended with a structured syntax for definitions, theorems, and proofs. It is designed to be human-readable while remaining fully machine-verifiable.

## Basics

A Russell source file consists of a series of **declarations** optionally preceded by `import` directives. Declarations are separated by `;;`. Each declaration is one of: `constant`, `type`, `rule`, `axiom`, `definition`, or `theorem`.

### Imports

```russell
import CLASSICAL_FIRST-ORDER_LOGIC_WITH_EQUALITY/Propositional_calculus/Logical_implication;;
import ZF_(ZERMELO-FRAENKEL)_SET_THEORY/ZF_Set_Theory_-_start_with_the_Axiom_of_Extensionality/Classes;;
```

Imports reference other source files by their path relative to the project root. Circular imports are not allowed.

---

## Constants

A `constant` declaration introduces a new atomic symbol into the language:

```russell
constant {
    symbol ¬ ;;
    ascii -. ;;
    latex \lnot ;;
}
```

- `symbol` — the Unicode string used in source files
- `ascii` — an optional ASCII alias (used when Unicode is inconvenient)
- `latex` — the LaTeX rendering

Multiple constants can appear in one block:

```russell
constant {
    symbol → ;;
    ascii -> ;;
    latex \rightarrow ;;
}
```

---

## Types

A `type` declaration introduces a new sort. Types form a hierarchy: sub-types are automatically coerced to super-types when needed.

```russell
type wff ;;
type setvar < wff ;;   // setvar is a sub-type of wff
type class < wff ;;
```

The `<` relation defines the sub-type relation. All later `rule` and `axiom` declarations use these types for their variables.

---

## Rules

A `rule` declaration defines a grammar production — a way to build a term of a given type from sub-terms:

```russell
rule wn (ph : wff) {
    term : wff = ¬ ph ;;
}
```

This rule says: if `ph` is a `wff`, then `¬ ph` is a `wff`. The rule name `wn` is used internally in proofs to reference this production.

More complex rules with multiple sub-terms:

```russell
rule wi (ph : wff, ps : wff) {
    term : wff = ( ph → ps ) ;;
}

rule cv (x : setvar) {
    term : class = x ;;
}

rule cab (x : setvar, ph : wff) {
    term : class = { x ∣ ph } ;;
}
```

---

## Axioms

An `axiom` declaration states an assertion that is accepted without proof:

```russell
axiom ax-mp (ph : wff, ps : wff) {
    hyp 1 : wff = |- ph ;;
    hyp 2 : wff = |- ( ph → ps ) ;;
    ------
    prop : wff = |- ps ;;
}
```

- `hyp` lines declare the hypotheses (premises) of the axiom
- `prop` declares the conclusion
- The variable declarations after the axiom name define the free variables
- `|- ` (turnstile) is typically the "provability" assertion type

```russell
axiom ax-1 (ph : wff, ps : wff) {
    prop : wff = |- ( ph → ( ps → ph ) ) ;;
}

axiom ax-2 (ph : wff, ps : wff, ch : wff) {
    prop : wff = |- ( ( ph → ( ps → ch ) ) → ( ( ph → ps ) → ( ph → ch ) ) ) ;;
}
```

---

## Definitions

A `definition` introduces a new notation as an abbreviation for an existing expression. Unlike axioms, definitions add a propositional equality step automatically:

```russell
definition df-bi (ph : wff, ps : wff) {
    defiendum : wff = ( ph ↔ ps ) ;;
    definiens : wff = ¬ ( ( ph → ps ) → ¬ ( ps → ph ) ) ;;
    ------
    prop : wff = |- ( defiendum ↔ definiens ) ;;
}
```

The `defiendum` is the new notation; the `definiens` is its expansion.

---

## Theorems

A `theorem` is a statement with a machine-checked proof:

```russell
theorem nottru {
    prop : wff = |- ( ¬ ⊤ ↔ F. ) ;;
} proof {
    step 1 : wff = df-fal () |- ( F. ↔ ¬ ⊤ ) ;;
    step 2 : wff = bicomi (step 1) |- ( ¬ ⊤ ↔ F. ) ;;
}
```

Theorems with hypotheses and free variables:

```russell
theorem istos (.<_ : class, B : class, K : class, x : setvar, y : setvar)
    disjointed(.<_ x y, B x y) {
    hyp 1 : wff = |- ( B = ( Base ' K ) ) ;;
    hyp 2 : wff = |- ( .<_ = ( le ' K ) ) ;;
    ----------
    prop : wff = |- (
        ( K ∈ Toset ) ↔ (
            ( K ∈ Poset ) ∧ ∀ x ∈ B ∀ y ∈ B (
                ( x .<_ y ) ∨ ( y .<_ x )
            )
        )
    ) ;;
} proof (b : setvar, f : setvar, r : setvar)
    disjointed(.<_ b f r, B b f r, K b f r) {
    step 1 : wff = fveq2 () |- ( ( f = K ) → ( ( Base ' f ) = ( Base ' K ) ) ) ;;
    step 2 : wff = fveq2 () |- ( ( f = K ) → ( ( le ' f ) = ( le ' K ) ) ) ;;
    ...
    step 12 : wff = df-toset (step 4, step 5, step 6) |- ( K ∈ Toset ↔ ... ) ;;
}
```

### Proof steps

Each proof step has the form:

```
step <n> : <type> = <assertion-name> (<refs>) |- <expression> ;;
```

- `<n>` — step number
- `<type>` — the type of the proved expression (e.g., `wff`)
- `<assertion-name>` — the axiom, definition, or theorem being applied
- `(<refs>)` — references to earlier hypotheses or steps used as premises
- `|- <expression>` — the proved expression

### Disjointness constraints

The `disjointed(...)` clause declares that certain variable pairs must have disjoint ranges when the theorem is applied. This prevents illegal variable captures:

```russell
theorem example (x : setvar, y : setvar, ph : wff) disjointed(x y) {
    ...
}
```

---

## Comments

Russell supports both line and block comments:

```russell
// This is a line comment

/* This is a
   block comment */
```

Documentation comments use `/** ... */` for JSDoc-style documentation:

```russell
/**
 * Modus ponens inference rule.
 */
axiom ax-mp (ph : wff, ps : wff) { ... }
```

---

## Project structure

A Russell project has a root source file that imports (directly or transitively) all other source files. The project configuration is stored in `russell.conf`.

A typical project layout:
```
my-project/
    russell.conf
    my-project_root.ru          ← root file
    LOGIC/
        Propositional_calculus.ru
        Predicate_calculus.ru
    SET_THEORY/
        Axioms.ru
        Ordinals.ru
```

The root file contains import statements that pull in the entire theory:

```russell
import LOGIC/Propositional_calculus;;
import LOGIC/Predicate_calculus;;
import SET_THEORY/Axioms;;
import SET_THEORY/Ordinals;;
```
