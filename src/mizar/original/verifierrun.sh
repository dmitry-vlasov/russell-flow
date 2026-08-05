#!/bin/bash
# THE ONE-PROCESS GATE — the whole verifier chain per article:
#   .miz --parse--> --MSM--> --analyze--> .xml --check--> verdict
# in a single JVM (mizarj full=1), with BOTH ends compared:
#   * the analyzer report against the oracle's own ~/mizar_oracle/m4ref
#   * the checker tally (checked=N accepted=M) recorded per article
#
#   ./verifierrun.sh [jobs=N] [timeout=S] [list=<mml.lar>] [out=<file>]
#
# One line per article:
#   <art> OK checked=N accepted=M     xml byte-identical, all inferences ran
#   <art> XMLDIFF <byte>/<total> ...  the report differs from the reference
#   <art> TIMEOUT | CRASH rc=<n>
# RESUMABLE: articles already present in the output file are skipped.
set -e
SCRIPT_DIR=$( cd "$( dirname "$0" )" && pwd -P )
BASE_DIR=$( cd "$SCRIPT_DIR/../../.." && pwd -P )
MML="${RUSSELL_MATH:-$HOME/dev/math}/MML-test"
REF=$HOME/mizar_oracle/m4ref
JOBS=6; TIMEOUT=600; LIST="$MML/mml.lar"; OUT=$HOME/mizar_runs/verifier.txt

if [ "$1" = "--one" ]; then
	a="$2"; out="$3"; timeout_s="$4"
	grep -q "^$a " "$out" 2>/dev/null && exit 0
	d=$(mktemp -d); trap 'rm -rf "$d"' EXIT
	msg=$( timeout "$timeout_s" java -Xss128m -Xmx4g \
		-jar "$BASE_DIR/src/mizar/original/mizar.jar" \
		"article=$a" "mml=$MML" full=1 "out=$d/port.xml" 2>&1 | tail -1 )
	rc=$?
	if [ $rc -eq 124 ]; then line="$a TIMEOUT"
	elif [ ! -s "$d/port.xml" ]; then line="$a CRASH rc=$rc $msg"
	elif ! cmp -s "$d/port.xml" "$REF/$a.xml"; then
		b=$( cmp "$d/port.xml" "$REF/$a.xml" 2>/dev/null | sed -n 's/.*byte \([0-9]*\),.*/\1/p' )
		tot=$( wc -c < "$REF/$a.xml" )
		line="$a XMLDIFF ${b:-?}/$tot $msg"
	else
		line="$a OK $( printf '%s' "$msg" | sed -n 's/^[a-z0-9_]*: \(checked=[0-9]* accepted=[0-9]*\).*/\1/p' )"
	fi
	flock "$out" -c "printf '%s\n' '$line' >> '$out'"
	exit 0
fi
for i in "$@"; do case $i in jobs=*) JOBS="${i#*=}";; timeout=*) TIMEOUT="${i#*=}";; list=*) LIST="${i#*=}";; out=*) OUT="${i#*=}";; esac; done
mkdir -p "$( dirname "$OUT" )"; touch "$OUT"
awk 'NF {print $1}' "$LIST" | xargs -P "$JOBS" -I{} "$SCRIPT_DIR/verifierrun.sh" --one {} "$OUT" "$TIMEOUT"
awk '{ n++; if ($2 == "OK") { ok++;
         split($3,c,"="); split($4,ac,"="); chk+=c[2]; acc+=ac[2] }
       else if ($2 == "XMLDIFF") xd++; else bad++ }
     END { printf "articles %d, verified %d, xmldiff %d, timeout/crash %d\n", n, ok, xd, bad;
           printf "inferences %d checked, %d accepted\n", chk, acc }' "$OUT"
