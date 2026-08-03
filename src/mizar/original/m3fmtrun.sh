#!/bin/bash
# M3 gate 2 — the FORMATS and PRIORITY tables (.frm) loaded article by article.
set -e
SCRIPT_DIR=$( cd "$( dirname "$0" )" && pwd -P )
BASE_DIR=$( cd "$SCRIPT_DIR/../../.." && pwd -P )
MML="${RUSSELL_MATH:-$HOME/dev/math}/MML-test"
ORACLE=$HOME/mizar_oracle/build_m3dbg/wsmparser_dbg
JOBS=8; LIST="$MML/mml.lar"; OUT=$HOME/mizar_runs/m3fmt.txt
if [ "$1" = "--one" ]; then
	a="$2"; out="$3"
	d=$(mktemp -d); trap 'rm -rf "$d"' EXIT
	for e in miz dct frm prf; do cp "$MML/mml/$a.$e" "$d/" 2>/dev/null || true; done
	( cd "$d" && "$ORACLE" -q "$a" >/dev/null 2>&1 || true )
	grep -a '^FMT ' "$d/$a.inf" > "$d/ref.txt" 2>/dev/null || true
	java -Xmx2g -jar "$BASE_DIR/src/mizar/original/test/test_formats.jar" \
		"article=$a" "mml=$MML" 2>/dev/null | grep '^FMT ' > "$d/port.txt" || true
	n=$( wc -l < "$d/ref.txt" )
	if cmp -s "$d/ref.txt" "$d/port.txt"; then line="$a OK formats=$n"
	else line="$a DIFF ref=$n port=$( wc -l < "$d/port.txt" )"; fi
	flock "$out" -c "printf '%s\n' '$line' >> '$out'"
	exit 0
fi
for i in "$@"; do case $i in jobs=*) JOBS="${i#*=}";; list=*) LIST="${i#*=}";; out=*) OUT="${i#*=}";; esac; done
mkdir -p "$( dirname "$OUT" )"; : > "$OUT"
awk 'NF {print $1}' "$LIST" | xargs -P "$JOBS" -I{} "$SCRIPT_DIR/m3fmtrun.sh" --one {} "$OUT"
awk '{ n++; if ($2 == "OK") ok++; else bad[$1]=$0 }
     END { printf "articles  %d, formats-identical %d, differing %d\n", n, ok, n-ok
           for (a in bad) print "  " bad[a] }' "$OUT" | head -20
