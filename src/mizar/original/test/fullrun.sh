#!/bin/bash
# Whole-MML run of the Flow9 Mizar checker: one line per article
#   <article> checked=<N> accepted=<M>   or   <article> TIMEOUT   or   <article> CRASH rc=<N>
# followed by a tally.
#
#   ./fullrun.sh                      — all articles, 6 workers
#   ./fullrun.sh jobs=12 timeout=300  — more workers, longer cap
#   ./fullrun.sh out=/tmp/run2.txt    — another output file
#   ./fullrun.sh list=slow.txt        — a subset of articles, same MML
#   ./fullrun.sh args=collect-types=1 — extra flags passed to every mizarj run
#   ./fullrun.sh tally                — just re-tally an existing output file
#
# RESUMABLE: articles already present in the output file are skipped, so the
# script can be re-run after a crash or an interrupt.
#
# NOTE: this is a HARNESS, and a temporary one. Each article is a separate JVM
# because the checker is one article per process today. The target shape is a
# single executable that runs the whole MML concurrently in one process, with
# no intermediate files; when that lands, this script goes away.

set -e

SCRIPT_DIR=$( cd "$( dirname "$0" )" && pwd -P )
BASE_DIR=$( cd "$SCRIPT_DIR/../../../.." && pwd -P )   # repository root

# ---- internal worker: check ONE article, append one line under a lock -------
# (handled before the option parsing — its arguments are positional)
if [ "$1" = "--one" ]; then
	article="$2"; out="$3"; timeout_s="$4"; mem="$5"; mml="$6"; extra="$7"
	# Keep the exit code: 124 is the cap, anything else is a crash or an
	# operator kill. Recording those alike once turned a killed run into nine
	# false timeouts.
	rc=0
	o=$( timeout "$timeout_s" "$BASE_DIR/bin/mizarj" "mem=$mem" "mml=$mml" \
		"article=$article" $extra 2>&1 ) || rc=$?
	r=$( printf '%s' "$o" | grep -o 'checked=[0-9]* accepted=[0-9]*' ) || true
	if [ -n "$r" ]; then
		line="$article $r"
	elif [ "$rc" = 124 ]; then
		line="$article TIMEOUT"
	else
		line="$article CRASH rc=$rc"
	fi
	flock "$out" -c "printf '%s\n' '$line' >> '$out'"
	exit 0
fi

MML="${RUSSELL_MATH:-$HOME/dev/math}/MML-test"
JOBS=6
TIMEOUT=300
MEM=4g
OUT="$BASE_DIR/fullrun_out.txt"
LIST=""            # article list; defaults to $MML/mml.lar
EXTRA=""           # extra mizarj flags, appended to every run
TALLY_ONLY=0

for i in "$@"; do
case $i in
	jobs=*)    JOBS="${i#*=}" ;;
	timeout=*) TIMEOUT="${i#*=}" ;;
	mem=*)     MEM="${i#*=}" ;;
	mml=*)     MML="${i#*=}" ;;
	out=*)     OUT="${i#*=}" ;;
	list=*)    LIST="${i#*=}" ;;
	args=*)    EXTRA="$EXTRA ${i#*=}" ;;
	tally)     TALLY_ONLY=1 ;;
	*)
		echo "unknown argument: $i" >&2
		exit 1
	;;
esac
done

# ---- tally -----------------------------------------------------------------
tally() {
	awk '
		/checked=/ {
			split($2, a, "="); split($3, b, "=")
			checked += a[2]; accepted += b[2]; n++
			if (a[2] > 0 && a[2] == b[2]) full++
			gap = a[2] - b[2]
			if (gap > 0) worst[$1] = gap " " b[2] "/" a[2]
		}
		/TIMEOUT|CRASH/ { bad++; badnames = badnames " " $1 " (" $2 ")" }
		END {
			printf "articles      %d run, %d FULL, %d TIMEOUT-OR-CRASH\n", n + bad, full, bad
			if (checked > 0)
				printf "inferences    %d checked, %d accepted = %.2f%%\n", checked, accepted, 100 * accepted / checked
			if (bad > 0) printf "failed       %s\n", badnames
		}
	' "$1"

	echo "worst articles (by missing inferences)"
	awk '/checked=/ {
		split($2, a, "="); split($3, b, "=")
		if (a[2] - b[2] > 0) printf "  %6d  %-10s %d/%d\n", a[2] - b[2], $1, b[2], a[2]
	}' "$1" | sort -rn | head -15
}

if [ "$TALLY_ONLY" = 1 ]; then
	[ -f "$OUT" ] || { echo "no such file: $OUT" >&2; exit 1; }
	tally "$OUT"
	exit 0
fi

# ---- the run ---------------------------------------------------------------
[ -n "$LIST" ] || LIST="$MML/mml.lar"
[ -f "$LIST" ] || { echo "no article list: $LIST" >&2; exit 1; }
[ -f "$BASE_DIR/src/mizar/original/mizar.jar" ] || {
	echo "no mizar.jar — run $SCRIPT_DIR/build.sh first" >&2; exit 1; }

touch "$OUT"

# The resume list is computed ONCE, before any worker writes, so the filter
# never races with the appends.
TODO=$( mktemp )
trap 'rm -f "$TODO"' EXIT
grep -v -F -x -f <( awk 'NF {print $1}' "$OUT" ) <( awk 'NF {print $1}' "$LIST" ) > "$TODO" || true
count=$( wc -l < "$TODO" )

echo "MML       $MML"
echo "list      $LIST"
echo "output    $OUT"
echo "articles  $count to run ($JOBS workers, ${TIMEOUT}s cap, $MEM per worker)"
[ -n "$EXTRA" ] && echo "extra    $EXTRA"
if [ "$count" -eq 0 ]; then tally "$OUT"; exit 0; fi

xargs -a "$TODO" -P "$JOBS" -I{} \
	"$0" --one {} "$OUT" "$TIMEOUT" "$MEM" "$MML" "$EXTRA"

printf '%s\n' "DONE" >> "$OUT"
echo
tally "$OUT"
