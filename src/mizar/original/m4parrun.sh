#!/bin/bash
# M4 gate 1 — Transfer2Analyzer. Byte-compares the port's `.par` against the
# oracle's fresh output in ~/mizar_oracle/m4ref (built by ~/mizar_oracle/m4ref.sh
# with the verifier's `-a` flag). NEVER compare against a `.par` shipped in the
# MML.
#   ./m4parrun.sh [jobs=N] [list=<mml.lar>] [out=<file>]
set -e
SCRIPT_DIR=$( cd "$( dirname "$0" )" && pwd -P )
BASE_DIR=$( cd "$SCRIPT_DIR/../../.." && pwd -P )
MML="${RUSSELL_MATH:-$HOME/dev/math}/MML-test"
REF=$HOME/mizar_oracle/m4ref
JOBS=8; LIST="$MML/mml.lar"; OUT=$HOME/mizar_runs/m4par.txt

if [ "$1" = "--one" ]; then
	a="$2"; out="$3"
	d=$(mktemp -d); trap 'rm -rf "$d"' EXIT
	java -Xss128m -Xmx2g -jar "$BASE_DIR/src/mizar/original/test/test_par.jar" \
		"article=$a" "mml=$MML" "out=$d/port.par" > "$d/log.txt" 2>&1 || true
	if [ ! -s "$REF/$a.par" ]; then line="$a NOREF"
	elif [ ! -s "$d/port.par" ]; then line="$a CRASH"
	elif cmp -s "$d/port.par" "$REF/$a.par"; then line="$a OK"
	else line="$a DIFF $( cmp -l "$d/port.par" "$REF/$a.par" 2>/dev/null | wc -l )"; fi
	flock "$out" -c "printf '%s\n' '$line' >> '$out'"
	exit 0
fi
for i in "$@"; do case $i in jobs=*) JOBS="${i#*=}";; list=*) LIST="${i#*=}";; out=*) OUT="${i#*=}";; esac; done
mkdir -p "$( dirname "$OUT" )"; : > "$OUT"
awk 'NF {print $1}' "$LIST" | xargs -P "$JOBS" -I{} "$SCRIPT_DIR/m4parrun.sh" --one {} "$OUT"
awk '{ n++; if ($2 == "OK") ok++; else bad[$1]=$0 }
     END { printf "articles %d, par-identical %d, differing %d\n", n, ok, n-ok
           for (a in bad) print "  " bad[a] }' "$OUT" | head -40
