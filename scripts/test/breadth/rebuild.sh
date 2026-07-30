#!/usr/bin/env bash
# BREADTH REBUILD — translate the first N articles of the MML in canonical order into a
# disposable library, then CHECK THAT THE LIBRARY LOADS.
#
#   scripts/test/breadth/rebuild.sh [N] [outdir]      # default N=40, outdir=/tmp/miz-rebuild
#
# Why it exists: the breadth campaign's number is "how many MML theorems does the translator
# actually produce", and it must be measured on a library built in canonical order from the
# foundation up — not on whatever happens to sit in $RUSSELL_MATH. Translation only: no proving,
# so a full pass is minutes, not hours ([[test-time-budget]]).
#
# TWO RULES THIS SCRIPT ENCODES, both learned the hard way:
#  1. ALWAYS END WITH A LOAD CHECK. An earlier version checked only that each article
#     TRANSLATED; the import graph had gone cyclic and every article "passed" while the library
#     could not be read at all. Translation success is not library health.
#  2. SUBTRACT CANCELED SLOTS BEFORE QUOTING A RATE. `<Proposition><Verum/>` blocks are reserved
#     theorem numbers Mizar keeps after a theorem is withdrawn (relat_2 has 20 of 31). They are
#     not theorems; counting them as losses understates the translator by several percent.
set -euo pipefail

N="${1:-40}"
OUT="${2:-/tmp/miz-rebuild}"
RUSSELL_MATH="${RUSSELL_MATH:-$HOME/dev/math}"
BIN="$(cd "$(dirname "${BASH_SOURCE[0]}")/../../.." && pwd)/bin/russellj"
LAR="$RUSSELL_MATH/MML/mml.lar"

[ -f "$LAR" ] || { echo "no mml.lar at $LAR"; exit 1; }

rm -rf "$OUT"
mkdir -p "$OUT/mizar"
cp "$RUSSELL_MATH/mizar/miz_set.ru" "$OUT/mizar/"
cp "$RUSSELL_MATH/mizar/miz_aux.ru" "$OUT/mizar/" 2>/dev/null || true
cp "$RUSSELL_MATH/mizar/mizar_root.ru" "$OUT/mizar/" 2>/dev/null || true
# the project conf MUST come along: without it `import-roots` is empty and every article in the
# rebuilt library fails to resolve its imports — the library translates fine and then cannot be read
cp "$RUSSELL_MATH/mizar/russell.conf" "$OUT/mizar/" 2>/dev/null || true
ln -sfn "$RUSSELL_MATH/MML" "$OUT/MML"

articles=$(head -n "$N" "$LAR" | tr -d '\r' | awk 'NF')
ok=0; fail=0; total_xml=0; total_ru=0
: > "$OUT/rebuild.log"

for a in $articles; do
	xml="$RUSSELL_MATH/MML/mml/$a.xml"
	# raw <JustifiedTheorem> minus the canceled (bare <Verum/>) slots = the REAL denominator
	if [ -f "$xml" ]; then
		raw=$(grep -c "<JustifiedTheorem" "$xml" || true)
		raw=${raw:-0}
		# canceled slot = a <JustifiedTheorem> whose proposition is a bare <Verum/>
		# NB `set -o pipefail` + a grep that matches nothing kills the script — hence `|| true`
		canceled=$( { tr '\n' ' ' < "$xml" | grep -o "<JustifiedTheorem[^>]*>[[:space:]]*<Proposition[^>]*>[[:space:]]*<Verum/>" || true; } | wc -l)
	else
		raw=0; canceled=0
	fi
	real=$(( raw - canceled ))
	out="$(RUSSELL_MATH="$OUT" "$BIN" no-server=1 mem=16g \
		translate/mizar/translate article="$a" module="$a" math="$OUT" v=0 2>&1 || true)"
	printf '%s\n' "=== $a" "$out" >> "$OUT/rebuild.log"
	if [ -f "$OUT/mizar/$a.ru" ]; then
		n=$(grep -c "^theorem " "$OUT/mizar/$a.ru" || true); n=${n:-0}
		ok=$((ok+1)); total_ru=$((total_ru+n)); total_xml=$((total_xml+real))
		printf '  OK   %-14s %4d / %4d theorems (raw %d, canceled %d)\n' "$a" "$n" "$real" "$raw" "$canceled"
	else
		fail=$((fail+1)); total_xml=$((total_xml+real))
		printf '  FAIL %-14s (%d real theorems lost)\n' "$a" "$real"
	fi
done

echo
echo "ARTICLES: $ok ok / $fail failed of $((ok+fail))"
if [ "$total_xml" -gt 0 ]; then
	echo "THEOREMS: $total_ru of $total_xml real (canceled slots already subtracted) = $((100*total_ru/total_xml))%"
fi

# RULE 1: the library must LOAD, not merely translate.
echo
echo "LOAD CHECK:"
last=$(echo "$articles" | tail -1)
if [ -f "$OUT/mizar/$last.ru" ]; then
	if RUSSELL_MATH="$OUT" "$BIN" no-server=1 mem=16g read file="$OUT/mizar/$last.ru" > "$OUT/load.log" 2>&1; then
		echo "  library loads (read $last ok)"
	else
		echo "  LOAD FAILED — see $OUT/load.log"
		grep -iE "cyclic|error" "$OUT/load.log" | head -5
		exit 1
	fi
else
	echo "  skipped: $last did not translate"
fi
