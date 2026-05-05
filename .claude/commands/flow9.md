# Flow9 Language Reference

You are working with **flow9**, a statically typed functional programming language in the ML family with TypeScript-like syntax. Use this reference when reading, writing, or reasoning about flow9 code.

## Core Principles

- **Expression-based**: Everything is an expression. No statements, no `return` keyword. The last expression in a block is its value.
- **Immutable by default**: All values and data structures are immutable unless explicitly using `ref` or `mutable`.
- **No classes**: Only structs (product types) and unions (sum types). No inheritance, no methods.
- **Global namespace**: All top-level names share a single namespace across all modules. No module-qualified names.
- **No exceptions**: Error handling via `Maybe<T>`, default values, and callbacks.

## Syntax Quick Reference

### Types and Values

```flow
// Primitives
b : bool = true;
i : int = 42;              // 32-bit signed, hex: 0xff
d : double = 3.14;         // 64-bit IEEE
s : string = "hello\n";    // UTF-16, escapes: \n \t \\ \" \xHH \uHHHH

// Arrays (immutable, 0-indexed)
a : [int] = [1, 2, 3];
nested : [[string]] = [["a", "b"], ["c"]];

// No null, no undefined. Use Maybe for optional values.
```

### Structs and Unions

```flow
// Struct: product type. Name starts with uppercase.
Point(x : double, y : double);

// Union: sum type (algebraic data type).
Shape ::= Circle, Rect;
    Circle(center : Point, radius : double);
    Rect(topLeft : Point, bottomRight : Point);

// Parameterized (generic) types use ?, ??, ??? as type variables
Maybe<?> ::= None, Some<?>;
    None();
    Some(value : ?);

Tree<?, ??> ::= TreeEmpty, TreeNode<?, ??>;
    TreeEmpty();
    TreeNode(key : ?, value : ??, left : Tree<?, ??>, right : Tree<?, ??>, depth : int);
```

### Field Access and Struct Update

```flow
p = Point(1.0, 2.0);
x = p.x;                          // field access with dot

// Struct update with `with` keyword:
p2 = Point(p with x = 5.0);       // creates new Point, only x changed
p3 = Point(p with x = 5.0, y = 3.0);
```

### Functions

```flow
// Top-level function. No "function" keyword.
factorial(n : int) -> int {
    if (n <= 1) 1
    else n * factorial(n - 1);
}

// Polymorphic function
max(a : ?, b : ?) -> ? {
    if (a < b) b else a
}

// Lambda
double = \x -> x * 2;
add = \x, y -> x + y;

// Function types
apply(f : (int) -> int, x : int) -> int { f(x) }
```

### Control Flow

```flow
// if is an expression (returns a value)
sign = if (x > 0) "positive" else if (x < 0) "negative" else "zero";

// Blocks: semicolons separate expressions, last one is the result
result = {
    a = compute();
    b = transform(a);
    combine(a, b);    // this is the return value
};

// switch: pattern matching on unions. NOT usable on int/string.
describe(s : Shape) -> string {
    switch (s) {
        Circle(c, r): "circle at " + d2s(c.x) + " r=" + d2s(r);
        Rect(tl, br): "rect from " + d2s(tl.x) + " to " + d2s(br.x);
    }
}

// ?? operator: sugar for Maybe pattern matching
name = maybeName ?? {
    // `value` is bound to the Some contents
    value
} : {
    // None case
    "anonymous"
};
```

### Sequences and Side Effects

```flow
// Semicolons separate expressions in a sequence
{
    println("step 1");
    x = doSomething();
    println("step 2");
    x + 1;  // return value of the block
}

// void: use {} for "no value"
// Functions returning void end with {} or a void expression
logAndReturn(x : int) -> int {
    println(i2s(x));
    x;
}
```

## Module System

```flow
// Importing modules (paths relative to lib/ or project root)
import runtime;
import ds/array;
import ds/tree;
import math/math;

// Exporting declarations
export {
    // Functions
    myFunction(x : int) -> string;

    // Types
    MyStruct(field1 : int, field2 : string);

    MyUnion ::= CaseA, CaseB;
        CaseA(value : int);
        CaseB(text : string);

    // Variables
    myConstant : int;
}

// forbid: prevent transitive dependency on a module
forbid ds/array;
// After this, neither this module nor anything importing it can use ds/array
```

All paths are case-sensitive. Use forward slashes. Full path required even for files in the same directory.

## Mutable State

```flow
// ref: mutable cell (use sparingly)
counter : ref int = ref 0;
current = ^counter;       // dereference with ^
counter := current + 1;   // update with :=

// mutable struct fields (rare, for performance-critical code)
DLink(value : ?, mutable prev : DLink<?>, mutable next : DLink<?>);
node.prev ::= otherNode;  // update mutable field with ::=
```

**Prefer immutable patterns**: use `fold`, pass data as function parameters, return new values instead of mutating.

## Standard Library Essentials

### Arrays (import ds/array)

```flow
length(a : [?]) -> int
map(a : [?], fn : (?) -> ??) -> [??]
mapi(a : [?], fn : (int, ?) -> ??) -> [??]        // with index
fold(a : [?], init : ??, fn : (??, ?) -> ??) -> ??
foldi(a : [?], init : ??, fn : (int, ??, ?) -> ??) -> ??
filter(a : [?], fn : (?) -> bool) -> [?]
find(a : [?], fn : (?) -> bool) -> Maybe<?>
findi(a : [?], fn : (?) -> bool) -> Maybe<int>     // returns index
exists(a : [?], fn : (?) -> bool) -> bool
forall(a : [?], fn : (?) -> bool) -> bool
concat(a : [?], b : [?]) -> [?]
arrayPush(a : [?], x : ?) -> [?]                   // append one element
replace(a : [?], i : int, x : ?) -> [?]            // immutable update at index
subrange(a : [?], start : int, len : int) -> [?]
tail(a : [?]) -> [?]                               // all but first
sort(a : [?], cmp : (?, ?) -> int) -> [?]
filtermap(a : [?], fn : (?) -> Maybe<??>) -> [??]  // filter + map in one
```

### Trees / Maps (import ds/tree)

```flow
makeTree() -> Tree<?, ??>                           // empty tree
setTree(t : Tree<?, ??>, key : ?, val : ??) -> Tree<?, ??>
lookupTree(t : Tree<?, ??>, key : ?) -> Maybe<??>
lookupTreeDef(t : Tree<?, ??>, key : ?, def : ??) -> ??  // with default
containsKeyTree(t : Tree<?, ??>, key : ?) -> bool
removeFromTree(t : Tree<?, ??>, key : ?) -> Tree<?, ??>
foldTree(t : Tree<?, ??>, acc : ???, fn : (?, ??, ???) -> ???) -> ???
mapTree(t : Tree<?, ??>, fn : (??) -> ???) -> Tree<?, ???>
mergeTree(a : Tree<?, ??>, b : Tree<?, ??>) -> Tree<?, ??>  // b overrides a
tree2pairs(t : Tree<?, ??>) -> [Pair<?, ??>]
pairs2tree(p : [Pair<?, ??>]) -> Tree<?, ??>
sizeTree(t : Tree<?, ??>) -> int
```

### Sets (import ds/set)

```flow
makeSet() -> Set<?>
insertSet(s : Set<?>, val : ?) -> Set<?>
containsSet(s : Set<?>, val : ?) -> bool
buildSet(a : [?]) -> Set<?>
set2array(s : Set<?>) -> [?]
mergeSets(a : Set<?>, b : Set<?>) -> Set<?>
differenceSets(a : Set<?>, b : Set<?>) -> Set<?>
intersectSets(a : Set<?>, b : Set<?>) -> Set<?>
```

### Strings (import string)

```flow
strlen(s : string) -> int
substring(s : string, start : int, len : int) -> string
strContains(str : string, sub : string) -> bool
strIndexOf(str : string, sub : string) -> int          // -1 if not found
strSplit(str : string, sep : string) -> [string]
strGlue(strs : [string], sep : string) -> string
concatStrings(strs : [string]) -> string               // efficient multi-concat
trim(s : string) -> string
toLowerCase(s : string) -> string
toUpperCase(s : string) -> string
startsWith(s : string, prefix : string) -> bool
endsWith(s : string, suffix : string) -> bool
superglue(xs : [?], fn : (?) -> string, sep : string) -> string
```

### Conversions

```flow
i2s(int) -> string          i2d(int) -> double
s2i(string) -> int          d2i(double) -> int
d2s(double) -> string       s2d(string) -> double
b2s(bool) -> string
```

### Maybe (import maybe)

```flow
either(m : Maybe<?>, default : ?) -> ?
eitherMap(m : Maybe<?>, fn : (?) -> ??, default : ??) -> ??
eitherFn(m : Maybe<?>, someFn : (?) -> ??, noneFn : () -> ??) -> ??
isNone(m : Maybe<?>) -> bool
isSome(m : Maybe<?>) -> bool
maybeApply(m : Maybe<?>, fn : (?) -> void) -> void
```

### IO (import runtime)

```flow
println(value : flow) -> void
timestamp() -> double                  // Unix timestamp in seconds
timer(ms : int, fn : () -> void) -> void
random() -> double                     // [0, 1)
```

## Gotchas

1. **No return keyword**: The last expression in a function body is the result. Don't write `return x`.

2. **No let/const/var keywords**: Variables are declared by assignment: `x = 5;` not `let x = 5;`

3. **No function keyword**: Functions are declared directly: `foo(x : int) -> int { ... }` not `function foo(...)`.

4. **switch only works on unions/structs**: You cannot switch on int, string, or bool. Use if-else chains instead.

5. **Semicolons are separators, not terminators**: In a block `{ a; b; c }`, the last expression `c` has no trailing semicolon (though flow9 tolerates it).

6. **Lambda greediness**: Lambda `\x -> ...` consumes everything to the right. Parenthesize in pipes:
   ```flow
   // Wrong:
   arr |> \x -> map(x, fn) |> \y -> filter(y, pred)
   // Correct:
   arr |> (\x -> map(x, fn)) |> (\y -> filter(y, pred))
   ```

7. **String concatenation in if**: The parser eagerly consumes strings:
   ```flow
   // Wrong: "true" becomes part of else branch
   if (x) "" else "not " + "true"
   // Correct:
   (if (x) "" else "not ") + "true"
   ```

8. **fold, not loops**: There are no while/for loops. Use `fold`, `map`, `iter`, or recursion.

9. **Recursive lambdas impossible**: Local lambdas cannot reference themselves. Use top-level functions for recursion.

10. **Global namespace collisions**: All exported names are global. Prefix with module context if naming conflicts arise.

11. **Array indexing crashes on out-of-bounds**: `a[i]` will crash at runtime if `i >= length(a)`. Always check bounds or use `elementAt(a, i, default)`.

12. **Immutable arrays**: `replace(a, i, v)` returns a new array. `arrayPush(a, v)` returns a new array. The original is unchanged.

## Common Idioms

### Building collections incrementally

```flow
// Bad: O(n^2) due to repeated arrayPush
fold(items, [], \acc, item -> arrayPush(acc, transform(item)))

// Good: use map directly
map(items, transform)

// Good: build as List, convert once at the end
list2array(fold(items, makeList(), \acc, item -> Cons(transform(item), acc)))
```

### Dictionary/map operations

```flow
// Build a lookup table
nameMap = fold(users, makeTree(), \acc, user -> setTree(acc, user.id, user.name));

// Lookup with default
name = lookupTreeDef(nameMap, userId, "unknown");

// Lookup with Maybe
switch (lookupTree(nameMap, userId)) {
    Some(n): processName(n);
    None(): handleMissing();
}
```

### API structs (simulating interfaces)

```flow
// Define an "interface" as a struct of functions
Logger(
    log : (string) -> void,
    level : () -> int
);

// Implement by constructing the struct
consoleLogger() -> Logger {
    Logger(
        \msg -> println(msg),
        \ -> 0
    )
}
```

### Error handling

```flow
// Return Maybe for fallible operations
parseNumber(s : string) -> Maybe<int> {
    n = s2i(s);
    if (n == 0 && s != "0") None()
    else Some(n);
}

// Chain with eitherMap
result = eitherMap(parseNumber(input), \n -> n * 2, -1);
```

### Async with callbacks

```flow
// Success/error callback pattern
loadData(
    url : string,
    onSuccess : (Data) -> void,
    onError : (string) -> void
) -> void;

// Sequential async with nesting
loadConfig(\config -> {
    loadData(config.url, \data -> {
        processData(data);
    }, \err -> println("data error: " + err));
}, \err -> println("config error: " + err));
```

## Behaviours (Functional Reactive Programming)

```flow
import ds/behaviour;

// Create a mutable observable value
counter : DynamicBehaviour<int> = make(0);

// Read current value
current = getValue(counter);

// Update
next(counter, current + 1);

// Subscribe to changes (returns unsubscriber)
unsub = subscribe(counter, \val -> println("counter: " + i2s(val)));

// Derived behaviours
doubled = select(counter, \v -> v * 2);

// Always unsubscribe when done to avoid memory leaks
unsub();
```

## File Structure Convention

```
project/
  lib/                    # standard library and shared code
  src/
    module.flow           # lowercase filenames
    subdir/
      helper.flow
  tools/                  # build tools, compiler utilities
```

- One module per file, filename matches module name
- Shared types extracted to `*_types.flow` to break circular deps
- Exported declarations at the top of the file for quick scanning
