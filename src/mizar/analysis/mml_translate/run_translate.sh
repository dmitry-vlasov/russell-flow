#!/bin/bash
# S-A gate: translate MML articles in dependency order and record what happens.
#
#   src/mizar/analysis/mml_translate/run_translate.sh 60 [outdir]
#
# Runs `translate/mizar/translate` on the first N articles of the dependency
# order (dep_order.py) and writes:
#   <outdir>/result.tsv   article <tab> status <tab> first diagnostic line
#   <outdir>/log/<a>.log  the full output of each article
#
# Status is one of: ok | fail | timeout. Nothing is retried and nothing is
# skipped, so the file is a plain census of the translator's reach.
set -u
cd "$(dirname "$0")/../../.." || exit 1

N=${1:-40}
OUT=${2:-/tmp/mml_translate}
TIMEOUT=${TIMEOUT:-300}
MEM=${MEM:-8g}

mkdir -p "$OUT/log"
: > "$OUT/result.tsv"

python3 src/mizar/analysis/mml_translate/dep_order.py "$N" > "$OUT/order.txt"

while read -r a; do
	log="$OUT/log/$a.log"
	# stdin from /dev/null: russellj reads stdin and would otherwise swallow
	# the rest of the article list this loop is reading.
	timeout "$TIMEOUT" bin/russellj no-server=1 mem="$MEM" \
		translate/mizar/translate article="$a" > "$log" 2>&1 < /dev/null
	rc=$?
	if [ $rc -eq 124 ]; then
		status=timeout; msg="after ${TIMEOUT}s"
	elif grep -q "^phase (a) written:" "$log"; then
		status=ok; msg=$(grep -m1 "^mizar-to-ru:" "$log")
	else
		status=fail
		msg=$(grep -m1 -E "Runtime failure:|Error|error:" "$log" | cut -c1-200)
		[ -z "$msg" ] && msg="(no diagnostic)"
	fi
	printf '%s\t%s\t%s\n' "$a" "$status" "$msg" >> "$OUT/result.tsv"
	printf '%-16s %s\n' "$a" "$status"
done < "$OUT/order.txt"

echo "---"
cut -f2 "$OUT/result.tsv" | sort | uniq -c
