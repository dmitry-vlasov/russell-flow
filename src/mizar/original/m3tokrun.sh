#!/bin/bash
# M3 gate 1 — the TOKENISER, article by article.
#
# Reference: the ORIGINAL parser built with -dMDEBUG dumps every token it takes
# from the scanner (the tap in mscanner.pas ReadToken) into <article>.inf.
# Port:      src/mizar/original/test/test_scanner.jar prints the same shape.
# The two streams must be identical.
#
#   ./m3tokrun.sh [jobs=N] [list=<file>] [out=<file>]
set -e

SCRIPT_DIR=$( cd "$( dirname "$0" )" && pwd -P )
BASE_DIR=$( cd "$SCRIPT_DIR/../../.." && pwd -P )
MML="${RUSSELL_MATH:-$HOME/dev/math}/MML-test"
ORACLE=$HOME/mizar_oracle/build_m3dbg/wsmparser_dbg
JOBS=8
LIST="$MML/mml.lar"
OUT=$HOME/mizar_runs/m3tok.txt

if [ "$1" = "--one" ]; then
	a="$2"; out="$3"
	d=$(mktemp -d); trap 'rm -rf "$d"' EXIT
	for e in miz dct frm prf; do cp "$MML/mml/$a.$e" "$d/" 2>/dev/null || true; done
	( cd "$d" && "$ORACLE" -q "$a" >/dev/null 2>&1 || true )
	if [ ! -f "$d/$a.inf" ]; then
		flock "$out" -c "echo '$a NOREF' >> '$out'"; exit 0
	fi
	grep -a '^TOK ' "$d/$a.inf" > "$d/ref.txt" || true
	java -Xss128m -Xmx2g -jar "$BASE_DIR/src/mizar/original/test/test_scanner.jar" \
		"article=$a" "mml=$MML" > "$d/port.txt" 2>&1 || true
	nref=$( wc -l < "$d/ref.txt" )
	nport=$( grep -c '^TOK ' "$d/port.txt" || true )
	if cmp -s "$d/ref.txt" <( grep -a '^TOK ' "$d/port.txt" ); then
		line="$a OK tokens=$nref"
	else
		first=$( diff "$d/ref.txt" <( grep -a '^TOK ' "$d/port.txt" ) | head -1 | tr -d "'" )
		line="$a DIFF ref=$nref port=$nport at=$first"
	fi
	flock "$out" -c "printf '%s\n' '$line' >> '$out'"
	exit 0
fi

for i in "$@"; do
case $i in
	jobs=*) JOBS="${i#*=}" ;;
	list=*) LIST="${i#*=}" ;;
	out=*)  OUT="${i#*=}" ;;
esac
done

[ -x "$ORACLE" ] || { echo "no oracle parser: $ORACLE" >&2; exit 1; }
[ -f "$BASE_DIR/src/mizar/original/test/test_scanner.jar" ] || {
	echo "build first: flowc1 jar=1 mizar/original/test/test_scanner.flow" >&2; exit 1; }

mkdir -p "$( dirname "$OUT" )"
: > "$OUT"
awk 'NF {print $1}' "$LIST" | xargs -P "$JOBS" -I{} "$SCRIPT_DIR/m3tokrun.sh" --one {} "$OUT"

echo
awk '{ n++; if ($2 == "OK") ok++; else bad[$1] = $0 }
     END { printf "articles  %d, token-identical %d, differing %d\n", n, ok, n - ok
           for (a in bad) print "  " bad[a] }' "$OUT" | head -30
