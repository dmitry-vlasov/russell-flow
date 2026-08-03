#!/bin/bash
# M3 gate 4 — the MSM step. Byte-compares the port's .wsx against the oracle's
# fresh output in ~/mizar_oracle/m3ref (NEVER the .wsx shipped in the MML: its
# <Link labelnr=".."> holds an uninitialised value).
#   ./m3msmrun.sh [jobs=N] [list=<mml.lar>] [out=<file>]
set -e
SCRIPT_DIR=$( cd "$( dirname "$0" )" && pwd -P )
BASE_DIR=$( cd "$SCRIPT_DIR/../../.." && pwd -P )
MML="${RUSSELL_MATH:-$HOME/dev/math}/MML-test"
REF=$HOME/mizar_oracle/m3ref
JOBS=8; LIST="$MML/mml.lar"; OUT=$HOME/mizar_runs/m3parse.txt

if [ "$1" = "--one" ]; then
	a="$2"; out="$3"
	d=$(mktemp -d); trap 'rm -rf "$d"' EXIT
	java -Xss128m -Xmx2g -jar "$BASE_DIR/src/mizar/original/test/test_msm.jar" \
		"article=$a" "mml=$MML" > "$d/port.msx" 2>"$d/err.txt" || true
	if [ ! -s "$REF/$a.msx" ]; then line="$a NOREF"
	elif cmp -s "$d/port.msx" "$REF/$a.msx"; then line="$a OK"
	else line="$a DIFF $( diff "$d/port.msx" "$REF/$a.msx" | grep -c '^[<>]' )"; fi
	flock "$out" -c "printf '%s\n' '$line' >> '$out'"
	exit 0
fi
for i in "$@"; do case $i in jobs=*) JOBS="${i#*=}";; list=*) LIST="${i#*=}";; out=*) OUT="${i#*=}";; esac; done
mkdir -p "$( dirname "$OUT" )"; : > "$OUT"
awk 'NF {print $1}' "$LIST" | xargs -P "$JOBS" -I{} "$SCRIPT_DIR/m3msmrun.sh" --one {} "$OUT"
awk '{ n++; if ($2 == "OK") ok++; else bad[$1]=$0 }
     END { printf "articles %d, msx-identical %d, differing %d\n", n, ok, n-ok
           for (a in bad) print "  " bad[a] }' "$OUT" | head -40
