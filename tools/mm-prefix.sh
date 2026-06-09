#!/bin/bash
#
# mm-prefix.sh — extract a VALID Metamath prefix of a .mm file.
#
# A naive `head -n N set.mm` is almost never valid Metamath: it usually cuts
# inside a `${ ... $}` scope (leaving it unclosed) or mid-statement, and the
# Russell translator then rejects it ("Syntax error: <<ERROR>>${", 0 theorems).
#
# This tool scans tokens while tracking comment state (`$( ... $)`) and scope
# depth (`${` / `$}`), then stops at the first point PAST the target line where
# scope depth is 0, we are not inside a comment, and the last token completed a
# statement (`$.`) or closed a scope (`$}`) — i.e. a safe between-statements
# boundary. The result parses and verifies as a self-contained theory.
#
# Usage:   tools/mm-prefix.sh <input.mm> <target-lines> <output.mm>
# Example: tools/mm-prefix.sh set.mm 100000 set-100000.mm
#
# Note: the cut tracks upstream set.mm content, so the exact set of theorems in
# the prefix shifts as set.mm evolves. Tests that pin specific (theorem, tactic)
# pairs against this prefix (see scripts/test-tactic-semantics.rus) may need
# re-pinning if upstream drift changes those theorems' proofs or corpus.
set -euo pipefail

if [ "$#" -ne 3 ]; then
	echo "usage: $0 <input.mm> <target-lines> <output.mm>" >&2
	exit 2
fi

INPUT="$1"
TARGET="$2"
OUTPUT="$3"

awk -v target="$TARGET" '
BEGIN { depth = 0; incomment = 0; last = "" }
{
	n = split($0, t, /[ \t]+/)
	for (i = 1; i <= n; i++) {
		tok = t[i]
		if (tok == "") continue
		if (incomment) { if (tok == "$)") incomment = 0; continue }
		if (tok == "$(") { incomment = 1; continue }
		if (tok == "${")      { depth++; last = "${" }
		else if (tok == "$}") { depth--; last = "$}" }
		else                    last = tok
	}
	print
	if (NR >= target && depth == 0 && incomment == 0 && (last == "$." || last == "$}")) exit
}
' "$INPUT" > "$OUTPUT"

# Always include the `$t` typesetting block (htmldef/latexdef/althtmldef). It lives
# far down set.mm, so short prefixes would otherwise omit it and the translator
# would emit bare ASCII symbols (e.g. `/\` instead of `∧`). That makes a prefix
# CONTRADICT full set.mm — the same grammar rule (`wa`) gets different symbols —
# which corrupts the shared, cross-project caches of a long-lived Russell server.
# Appending `$t` (a position-independent comment) keeps every prefix's symbol names
# identical to set.mm. Skip if the prefix already contains it.
if ! grep -q '[$]( [$]t' "$OUTPUT"; then
	awk '
		/[$]\( [$]t/ { cap = 1 }
		cap          { print }
		cap && /[$]\)/ { exit }
	' "$INPUT" >> "$OUTPUT"
	echo "mm-prefix: appended \$t typesetting block"
fi

echo "mm-prefix: wrote $(wc -l < "$OUTPUT") lines to $OUTPUT (target $TARGET)"
