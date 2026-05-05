#!/usr/bin/env bash
set -euo pipefail

QUESTION="$1"
CONTEXT_DIR="$(pwd)"
RECENT_GIT="$(git log --oneline -5 2>/dev/null || echo 'no git history')"
CHANGED_FILES="$(git diff --name-only 2>/dev/null || true)"
GIT_STATUS="$(git status --short 2>/dev/null || true)"
OUTDIR="${TMPDIR:-/tmp}/council-$(date +%Y%m%d-%H%M%S)"
mkdir -p "$OUTDIR"

PROMPT="You are reviewing a bug or design problem in Russell Flow, a formal mathematics
proof assistant written in Flow9 (a statically-typed functional language that compiles
to a Java JAR). The system bridges two proof formats: Russell (.ru, native) and
Metamath (.mm), supporting bidirectional translation, proof verification, oracle-guided
proof search, and proof base optimization.

Key architecture facts:
- RuEnv: owns config, I/O, thread pool — immutable during a task
- RuState: carries the theorem base (RuMath) — threaded through tasks as a return value
- RuMath: holds consts, types, rules, axioms, defs, theorems — the full proof base
- Unification uses trie-indexed pattern matching (src/ru/unify/)
- Proof search is oracle-guided backward chaining (src/ru/prover/)
- MM↔RU translation runs concurrently per assertion (src/mm/mm2ru.flow)
- Scripts (.rus) compose operations: read, verify, prove, translate, optimize
- Config is loaded from russell.conf, found by walking up from CWD
- Math libraries live in ~/dev/math, passed as import-roots via \$RUSSELL_MATH

Working directory: $CONTEXT_DIR
Recent commits:
$RECENT_GIT

Changed files:
$CHANGED_FILES

Git status:
$GIT_STATUS

Problem: $QUESTION

Give a second opinion for a senior engineer working on this codebase.
Return exactly:
1. Most likely root cause (be specific to Flow9/Russell architecture)
2. Files and functions to inspect first
3. Concrete fix plan
4. One thing the primary engineer may be missing or overlooking

Be direct. No disclaimers. No generic advice."

# Run Codex and Gemini in parallel
codex exec "$PROMPT" > "$OUTDIR/codex.out" 2>&1 &
CODEX_PID=$!

gemini -p "$PROMPT" > "$OUTDIR/gemini.out" 2>&1 &
GEMINI_PID=$!

wait $CODEX_PID 2>/dev/null || echo "codex failed or not installed" > "$OUTDIR/codex.out"
wait $GEMINI_PID 2>/dev/null || echo "gemini failed or not installed" > "$OUTDIR/gemini.out"

echo "===== CODEX (GPT) ====="
cat "$OUTDIR/codex.out"
echo ""
echo "===== GEMINI ====="
cat "$OUTDIR/gemini.out"
echo ""
echo "===== SAVED TO $OUTDIR ====="
echo "===== END OF COUNCIL ====="
