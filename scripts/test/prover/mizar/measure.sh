#!/usr/bin/env bash
# HERMETIC MIZAR MEASUREMENT.
#
#   scripts/test/prover/mizar/measure.sh <article> [off] [tl] [par]  # one article
#   scripts/test/prover/mizar/measure.sh --chain    [off] [tl] [par] # the whole dependency chain
#
#   par=N (N>1)      prove up to N theorems concurrently — FAST SMOKE ONLY (~2.6x at N=8, but
#                    the power cap devalues wall-clock slices: +-5 theorems noise; see README).
#                    Floors and commit numbers are ALWAYS par=0 (sequential, count-exact).
#   scripts/test/prover/mizar/measure.sh --probe <article> <theorem> [off] [v]
#
#   off=1 (default)  the GENERAL-PATH metric: hand-written literal closers disabled
#   off=0            the full stack
#
# WHY THIS EXISTS. The pipeline WRITES each proved article back into the math library, and the
# articles form a dependency chain (tarski -> xboole_0 -> xboole_1 -> enumset1 -> zfmisc_1). So a
# measurement with a weaker configuration silently degrades the library that the NEXT measurement of
# a dependent article reads — a general-only run leaves a weaker xboole_1.ru behind, and the next
# full-stack zfmisc_1 run then loses theorems with NO code change. That produced three phantom
# regressions on 2026-07-12 and cost two correct changes, reverted on bad evidence.
#
# The output path of a generated article is derived from its module id resolved against import-roots,
# so it cannot be redirected by a flag. Instead this harness gives every run a DISPOSABLE MATH ROOT:
# a fresh copy of the golden mizar/ library plus a symlink to the read-only MML XML. The run
# regenerates its target from XML, proves it, and writes its results into the copy, which is then
# thrown away. The golden library under $RUSSELL_MATH is never touched, so runs are reproducible and
# independent of each other and of their order.
#
# Dependencies always come from the golden library (proved with the full stack, whatever `off` says):
# `off` decides what proves THIS article, not what proved the library it stands on.
#
# NEVER run two measurements at once — the checker-pipeline stage slices are fixed WALL-CLOCK ms, so
# CPU contention alone loses theorems.
set -euo pipefail

RUSSELL_MATH="${RUSSELL_MATH:-$HOME/dev/math}"
RUSSELL_BIN="$(cd "$(dirname "${BASH_SOURCE[0]}")/../../../.." && pwd)/bin/russellj"
SANDBOX="${SANDBOX:-/tmp/russell-measure-math}"
CHAIN=(tarski xboole_0 xboole_1 enumset1 zfmisc_1)
declare -A TOTALS=([tarski]=3 [xboole_0]=8 [xboole_1]=116 [enumset1]=87 [zfmisc_1]=140)

setup_sandbox() {
	rm -rf "$SANDBOX"
	mkdir -p "$SANDBOX"
	cp -r "$RUSSELL_MATH/mizar" "$SANDBOX/mizar"      # a disposable copy of the golden library
	ln -s "$RUSSELL_MATH/MML" "$SANDBOX/MML"          # the Mizar XML, read-only
}

run_article() {   # <article> <off> <tl> [par]   (stage slices via env: DCMS/MZMS/EQMS)
	local article="$1" off="$2" tl="$3" par="${4:-0}"
	local run_t0=$(date +%s)
	# PREFLIGHT: the stage slices are wall-clock, so outer CPU load silently
	# eats theorems (a loaded run reads as a tail of unattempted theorems).
	# Record the environment INTO the result so every number carries its
	# context; warn loudly when contention is visible. Warn, never refuse.
	local loadavg other_java
	loadavg="$(cut -d' ' -f1 /proc/loadavg)"
	other_java="$(pgrep -x java | while read -r p; do
		grep -qE 'flowc_lsp|server=lsp' "/proc/$p/cmdline" 2>/dev/null || echo "$p"; done | wc -l)"
	echo "PREFLIGHT [$article]: loadavg=$loadavg other-jvm=$other_java"
	if awk "BEGIN{exit !($loadavg > 2.0)}" || [ "$other_java" -gt 0 ]; then
		echo "PREFLIGHT WARNING [$article]: contended environment — numbers from this run are NOT quotable as floors"
	fi
	setup_sandbox
	local out
	out="$(RUSSELL_MATH="$SANDBOX" "$RUSSELL_BIN" no-server=1 mem=16g \
		test/prover/mizar/general_only article="$article" off="$off" tl="$tl" par="$par" \
		tac="${TAC:-generators-first}" cl="${CL:-0}" \
		dcms="${DCMS:-4500}" mzms="${MZMS:-1500}" eqms="${EQMS:-1000}" 2>&1)"
	if [ -n "${MLOG:-}" ]; then printf '%s\n' "$out" > "$MLOG"; fi
	echo "DURATION [$article off=$off cl=${CL:-0}]: $(( $(date +%s) - run_t0 )) s"
	local open verify total
	open="$(grep -oP 'open theorems -> axioms: \K[0-9]+' <<<"$out" || echo '?')"
	verify="$(grep -oP 'Russell verify \(all proofs valid\): \K\w+' <<<"$out" || echo '?')"
	# the article's theorem count comes from the SKELETON (the optimize pass drops trivial theorems
	# from the written file, so counting there under-reports the denominator)
	total="${TOTALS[$article]:-?}"
	echo "$article: proved=$(( ${total:-0} - ${open:-0} ))/$total  open=$open  verify=$verify"
}

case "${1:-}" in
	--chain)
		off="${2:-1}"; tl="${3:-10s}"; par="${4:-0}"
		echo "== hermetic measurement, off=$off (1 = general path only), tl=$tl, par=$par"
		for a in "${CHAIN[@]}"; do run_article "$a" "$off" "$tl" "$par"; done
		;;
	--subset)
		# FAST TIER: prove a fixed theorem subset (one probe each, ~12-25s/theorem)
		# — the iteration gate; the full article gate is the COMMIT gate only.
		article="${2:?article}"; thlist="${3:?comma-separated theorems}"; off="${4:-1}"
		sub_t0=$(date +%s)
		ok=0; n=0
		for th in $(echo "$thlist" | tr ',' ' '); do
			n=$((n+1))
			setup_sandbox
			r="$(RUSSELL_MATH="$SANDBOX" "$RUSSELL_BIN" no-server=1 mem=16g \
				test/prover/mizar/general_probe module="$article" target="$th" off="$off" v=1 thr=0 cl="${CL:-0}" 2>&1 \
				| grep -c '^prove: 1/1' || true)"
			if [ "$r" = "1" ]; then ok=$((ok+1)); echo "  + $th"; else echo "  - $th"; fi
		done
		echo "SUBSET [$article off=$off cl=${CL:-0}]: $ok/$n proved"
		echo "DURATION [subset $article]: $(( $(date +%s) - sub_t0 )) s"
		;;
	--probe)
		article="${2:?article}"; theorem="${3:?theorem}"; off="${4:-1}"; v="${5:-1}"; thr="${6:-0}"
		setup_sandbox
		RUSSELL_MATH="$SANDBOX" "$RUSSELL_BIN" no-server=1 mem=16g \
			test/prover/mizar/general_probe module="$article" target="$theorem" off="$off" v="$v" thr="$thr" cl="${CL:-0}" tl="${TL:-10s}"
		;;
	"" | -h | --help)
		sed -n '2,30p' "${BASH_SOURCE[0]}"
		;;
	*)
		run_article "$1" "${2:-1}" "${3:-10s}" "${4:-0}"
		;;
esac
