#!/usr/bin/env bash
set -euo pipefail

INPUT="$(cat)"
CMD="$(printf '%s' "$INPUT" | jq -r '.tool_input.command // ""')"

# Generic dangerous patterns
if printf '%s' "$CMD" | grep -Eq \
    'rm[[:space:]]+-rf[[:space:]]+/|git[[:space:]]+push[[:space:]]+(-f|--force)|sudo|chmod[[:space:]]+777|curl.*[|].*sh'; then
  echo "BLOCKED: dangerous command: $CMD" >&2
  exit 2
fi

# Protect the math library and theorem caches
if printf '%s' "$CMD" | grep -Eq \
    'rm.*dev/math|rm.*russell\.jar|rm.*db/set'; then
  echo "BLOCKED: attempt to delete protected Russell data: $CMD" >&2
  exit 2
fi
