#!/bin/bash
# M4 gate 2 — the ANALYZER. Runs the whole chain (.miz -> .xml) and compares the
# report against the oracle's own in ~/mizar_oracle/m4ref.
#
# While the analyzer is incomplete the port's report is a PREFIX of the
# reference, so the interesting number is how many leading bytes agree: it only
# ever goes up. Once every item kind lands, "prefix" becomes "identical" and the
# tally line is the real gate.
#   ./m4xmlrun.sh [jobs=N] [list=<mml.lar>] [out=<file>]
set -e
SCRIPT_DIR=$( cd "$( dirname "$0" )" && pwd -P )
BASE_DIR=$( cd "$SCRIPT_DIR/../../.." && pwd -P )
MML="${RUSSELL_MATH:-$HOME/dev/math}/MML-test"
REF=$HOME/mizar_oracle/m4ref
JOBS=8; LIST="$MML/mml.lar"; OUT=$HOME/mizar_runs/m4xml.txt

if [ "$1" = "--one" ]; then
	a="$2"; out="$3"
	d=$(mktemp -d); trap 'rm -rf "$d"' EXIT
	msg=$( java -Xss128m -Xmx3g -jar "$BASE_DIR/src/mizar/original/test/test_xml.jar" \
		"article=$a" "mml=$MML" "out=$d/port.xml" 2>&1 | head -1 )
	if [ ! -s "$REF/$a.xml" ]; then line="$a NOREF"
	elif [ ! -s "$d/port.xml" ]; then line="$a CRASH $msg"
	elif cmp -s "$d/port.xml" "$REF/$a.xml"; then line="$a OK"
	else
		# first differing byte offset (cmp prints "... differ: byte N, line M")
		b=$( cmp "$d/port.xml" "$REF/$a.xml" 2>/dev/null | sed -n 's/.*byte \([0-9]*\),.*/\1/p' )
		[ -n "$b" ] || b=$( wc -c < "$d/port.xml" )
		tot=$( wc -c < "$REF/$a.xml" )
		line="$a PREFIX $b/$tot ${msg:-}"
	fi
	flock "$out" -c "printf '%s\n' '$line' >> '$out'"
	exit 0
fi
for i in "$@"; do case $i in jobs=*) JOBS="${i#*=}";; list=*) LIST="${i#*=}";; out=*) OUT="${i#*=}";; esac; done
mkdir -p "$( dirname "$OUT" )"; : > "$OUT"
awk 'NF {print $1}' "$LIST" | xargs -P "$JOBS" -I{} "$SCRIPT_DIR/m4xmlrun.sh" --one {} "$OUT"
awk '{ n++; if ($2 == "OK") ok++; else if ($2 == "PREFIX") pre++; else bad++ }
     END { printf "articles %d, xml-identical %d, prefix %d, crash/noref %d\n",
                  n, ok, pre, bad }' "$OUT"
