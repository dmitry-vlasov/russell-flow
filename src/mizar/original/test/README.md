# Tests for the Mizar verifier port

Everything that tests `src/mizar/original` lives here. One entry point:

    ./test.sh                  every component suite      (seconds)
    ./test.sh correl           one suite
    ./test.sh list             what can be run
    ./test.sh verifier         THE INTEGRATION TEST: the whole MML through
                               the whole verifier, in ONE process   (~1 h)
    ./test.sh verifier only=xboole_1,friends1     ... just these  (seconds)

Arguments pass through, so `./test.sh verifier jobs=8 out=/tmp/run.txt quiet=1`
works.

## The two levels

| | what it checks | where |
|---|---|---|
| component suites | one unit against hand-written expectations | `test_*.flow`, driven by `test_all.flow` |
| integration | all 1497 MML articles, both ends: the analyzer report byte-for-byte against the oracle's, and every inference accepted | `test_verifier.flow` |

The integration test is a Flow9 program, not a shell loop: it runs the
articles concurrently on a thread pool **inside one JVM**. Articles are
independent — each builds its own `MizCorrelEnv`, and the port has no shared
mutable state beyond idempotent debug-flag caches.

## Why jars, and where they go

The port is built on flow9's `Vector`, whose natives exist **only in the java
runtime** (`ds/vector.flow:10`). `flowc1 run=` uses the bytecode runner, which
hangs on the first `makeVector`, so every test here must be compiled to a jar.
`test.sh` does that for you and puts the jars in `build/mizar-test/` — never
in the source tree. Override with `MIZAR_TEST_JARS=<dir>`.

## The per-stage gates

`m3tokrun.sh` (tokeniser), `m3fmtrun.sh` (formats), `m3parserun.sh` (parser
`.wsx`), `m3msmrun.sh` (MSM `.msx`), `m4parrun.sh` (`.par`), `m4xmlrun.sh`
(analyzer `.xml`), `fullrun.sh` (checker over the reference `.xml`),
`verifierrun.sh` (the whole chain, one JVM per article).

`./test.sh verifier` subsumes them for the pass/fail question. They are kept
because they **localize** a regression to one pass: if the integration test
fails, they say which stage stopped matching.

They compare against reference output produced by a patched build of the
original Pascal verifier in `~/mizar_oracle/` — so they are developer gates,
not CI-ready as they stand.
