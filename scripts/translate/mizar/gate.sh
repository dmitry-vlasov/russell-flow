#!/bin/bash
#
# THE MIZAR GATE, end to end: emit the Russell proofs for the gate articles and
# verify the result with the ORIGINAL Metamath checker. Nothing counts as
# proved until Metamath says so — see the gate law in the project memory.
#
# Usage:
#   scripts/translate/mizar/gate.sh                 both phases
#   scripts/translate/mizar/gate.sh phase=emit      only the emit half
#   scripts/translate/mizar/gate.sh phase=mm        only the Metamath half
#   scripts/translate/mizar/gate.sh width=4         proofs in parallel, N at a time
#   scripts/translate/mizar/gate.sh out=<dir>       where the logs go
#
# ★ RUN IT DETACHED. A full round is tens of minutes and two rounds were lost
#   in one day to a machine restart and an editor crash:
#     setsid nohup scripts/translate/mizar/gate.sh > /tmp/gate.log 2>&1 &
#
# THE EMIT HALF IS TWO PASSES, because a proof needs only the STATEMENTS of the
# articles it depends on, and statements are fixed at translation time:
#   pass 1  every article statement-only, in dependency order, sequential.
#           Seconds, and it produces the snapshot the next pass reads.
#   pass 2  the proofs, one process per article, IN PARALLEL, each reading its
#           own copy of the snapshot and writing its own article. Results are
#           moved into place only after every process has succeeded, so no
#           process ever reads a file another one is rewriting.
# The emit chain used to be sequential because articles depend on each other;
# splitting statements from proofs is what removes that constraint.
#
# THE METAMATH HALF IS MEMORY-BOUND, NOT CPU-BOUND. Each export peaks at
# 5-17 GB (relat_1 16.7, enumset1 15.2, zfmisc_1 12.0, measured 2026-08-13),
# so all six at once needs ~64 GB on a 61 GB machine: the kernel OOM killer
# fires and, because these JVMs run inside the editor's process scope, it
# takes the editor with them. That happened TWICE in one day. MM_WIDTH is 2
# — the two largest together are ~32 GB. Re-measure before raising it; the
# `mem:` line at the end of each export log is the number to look at.
# Each article still needs its own out= directory: concurrent runs in one
# directory race on the shared foundation files.
set -u

BASE_DIR=$( cd "$( dirname "$0" )/../../.." && pwd -P )
MATH="${RUSSELL_MATH:-$HOME/dev/math}"
BASELINE="$MATH/mizar-honest-baseline-20260810.tar.gz"
# the emit chain, in dependency order — pass 1 MUST follow it
ARTS="tarski xboole_0 xboole_1 enumset1 zfmisc_1 subset_1 xtuple_0 relat_1"
# the articles with closed theorems to check; xtuple_0 has none yet
MM_ARTS="tarski xboole_0 xboole_1 enumset1 zfmisc_1 subset_1 relat_1"
PHASE=all
WIDTH=4
MM_WIDTH=2
# NOT under /tmp: that is a 31 GB tmpfs, i.e. RAM. A round writes ~4 GB of
# working copies, and putting them in RAM competes with the exports that
# already peak at 17 GB — one more reason the OOM killer fired.
OUT="$HOME/mizar_runs/gate"

for i in "$@"; do
case $i in
	phase=*) PHASE="${i#*=}" ;;
	width=*) WIDTH="${i#*=}" ;;
	mm-width=*) MM_WIDTH="${i#*=}" ;;
	out=*)   OUT="${i#*=}" ;;
	*) echo "unknown argument: $i" >&2; exit 1 ;;
esac
done

mkdir -p "$OUT"
cd "$BASE_DIR"
T0=$(date +%s)

if [ "$PHASE" = all ] || [ "$PHASE" = emit ]; then
	# start from the honest baseline: the emitted articles are rewritten in
	# place, so a round must never build on the previous round's output
	[ -f "$BASELINE" ] || { echo "no baseline: $BASELINE" >&2; exit 1; }
	tar -xzf "$BASELINE" -C "$MATH" || { echo "RESTORE FAILED" >&2; exit 1; }

	for a in $ARTS; do
		bin/russellj no-server=1 mem=8g translate/mizar/translate \
			article=$a emit=0 math="$MATH" > "$OUT/p1_$a.log" 2>&1 \
			|| { echo "PASS1 ABORT at $a" >&2; exit 1; }
	done
	T1=$(date +%s); echo "pass 1 (statements) done in $((T1-T0))s"

	# the snapshot and the per-article copies EXCLUDE .cache (296 MB of the
	# 500 MB math dir): it is derived data, each process rebuilds what it needs,
	# and copying it eight times is what filled the disk
	SNAP="$OUT/snap_mizar"
	rm -rf "$SNAP"; mkdir -p "$SNAP"
	find "$MATH/mizar" -maxdepth 1 -mindepth 1 ! -name .cache \
		-exec cp -r {} "$SNAP/" \;

	running=0
	for a in $ARTS; do
		(
			W="$OUT/emit_$a"; rm -rf "$W"; mkdir -p "$W"
			ln -s "$MATH/MML" "$W/MML"
			cp -r "$SNAP" "$W/mizar"    # already cache-free
			RUSSELL_MATH="$W" bin/russellj no-server=1 mem=16g \
				translate/mizar/translate article=$a emit=1 math="$W" \
				> "$OUT/p2_$a.log" 2>&1
		) &
		running=$((running+1))
		if [ $running -ge $WIDTH ]; then wait -n; running=$((running-1)); fi
	done
	wait
	T2=$(date +%s); echo "pass 2 (proofs) done in $((T2-T1))s at width $WIDTH"

	# every article must have finished before anything is moved into place.
	# "written" alone is NOT enough: an article whose emitted text fails to
	# parse still writes, still prints its closed count, and its verify
	# reports vacuous true (caught 2026-08-13: a prefix-rendered ≠ broke the
	# parse and 5 "closed" theorems were text that never loaded)
	for a in $ARTS; do
		grep -q "phase (a) written" "$OUT/p2_$a.log" \
			|| { echo "PASS2 FAIL at $a" >&2; exit 1; }
		grep -q "failed to parse" "$OUT/p2_$a.log" \
			&& { echo "PASS2 PARSE FAIL at $a" >&2; exit 1; }
	done
	total=0
	for a in $ARTS; do
		cp "$OUT/emit_$a/mizar/$a.ru" "$MATH/mizar/$a.ru"
		c=$( grep -o "theorems fully closed [0-9]* / [0-9]*" "$OUT/p2_$a.log" | tail -1 )
		n=$( echo "$c" | awk '{print $4}' )
		total=$(( total + ${n:-0} ))
		printf "  %-10s %s\n" "$a" "$c"
	done
	echo "EMIT DONE in $(( $(date +%s) - T0 ))s — closed total $total"
	# the closed-theorem LIST is the diff unit, not the count: an open step is
	# marked `= ?` in the emitted source
	for a in $ARTS; do
		awk -v art="$a" '/^theorem/ { name=$2 } /= \?/ { open[name]=1 }
			/^theorem/ { seen[name]=1 }
			END { for (t in seen) if (!(t in open)) print art, t }' \
			"$MATH/mizar/$a.ru"
	done | sort > "$OUT/closed.txt"
	echo "closed list: $OUT/closed.txt ($(wc -l < "$OUT/closed.txt") theorems)"
	# the reported total and the marker-extracted list must agree — they
	# diverge exactly when an article's emitted text is not what its counts
	# claim (the 2026-08-13 parse-failure round: total 200, list 195)
	lc=$( wc -l < "$OUT/closed.txt" )
	[ "$lc" -eq "$total" ] || { echo "EMIT COUNT $total != LIST $lc — RED" >&2; exit 1; }
fi

if [ "$PHASE" = all ] || [ "$PHASE" = mm ]; then
	T3=$(date +%s)
	running=0
	for a in $MM_ARTS; do
		# a leftover database from an earlier round makes verify-mm bless a
		# STALE export when write-mm silently produces nothing (caught
		# 2026-08-13: relat_1's read failed, write-mm ran 0 s, and the
		# verdict came from the previous session's file) — delete first,
		# so a failed export is a MISSING file, which verify-mm reports
		( rm -f /tmp/mizar_mm_$a/${a}_root.mm
		  bin/russellj no-server=1 mem=16g translate/mizar/to_mm module=$a \
			math="$MATH" out=/tmp/mizar_mm_$a > "$OUT/mm_$a.log" 2>&1 ) &
		running=$((running+1))
		if [ $running -ge $MM_WIDTH ]; then wait -n; running=$((running-1)); fi
	done
	wait
	echo "=== METAMATH VERDICTS ($(( $(date +%s) - T3 ))s)"
	bad=0
	for a in $MM_ARTS; do
		v=$( grep -h "MM_VERIFY" "$OUT/mm_$a.log" )
		printf "  %-10s %s\n" "$a" "${v:-MM_VERIFY MISSING}"
		echo "$v" | grep -q "success=true" || bad=1
		# a read-ru syntax error silently DROPS a span of declarations and the
		# export can still report success (caught 2026-08-13 on fn_×.3) — a
		# partially-read source is a corrupted measurement, so it is RED
		if grep -q "Syntax error" "$OUT/mm_$a.log"; then
			echo "    ^ SYNTAX ERROR in read-ru — partial source, verdict void" >&2
			bad=1
		fi
		# THE GATE LAW: proved must equal the emit phase's closed count —
		# success=true with proved < closed means closed theorems never
		# reached Metamath (same 2026-08-13 defect: the article's text did
		# not parse, so its "closed" theorems were never exported)
		n=$( grep -c "^$a " "$OUT/closed.txt" 2>/dev/null )
		p=$( echo "$v" | sed -n "s/.*proved($a)=\([0-9]*\).*/\1/p" )
		if [ -n "$p" ] && [ "${n:-0}" != "$p" ]; then
			echo "    ^ proved=$p != closed=${n:-0} — MISMATCH, verdict void" >&2
			bad=1
		fi
	done
	[ $bad -eq 0 ] && echo "GATE GREEN" || { echo "GATE RED"; exit 1; }
fi
