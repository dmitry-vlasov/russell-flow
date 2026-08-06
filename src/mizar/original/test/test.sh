#!/bin/bash
# THE test entry point for src/mizar/original. Everything under test/ runs
# from here — there is no other command to remember.
#
#   ./test.sh                    the component suite (test_all: fast, seconds)
#   ./test.sh <unit>             one component suite, e.g. ./test.sh correl
#   ./test.sh verifier [args]    THE INTEGRATION TEST: the whole MML through
#                                the whole verifier, in ONE process
#   ./test.sh verifier only=xboole_1,friends1     ... just these (the smoke)
#   ./test.sh list               what can be run
#
# Extra arguments are passed through to the program, so:
#   ./test.sh verifier jobs=8 out=/tmp/run.txt quiet=1
#
# WHY A JAR AND NOT `flowc1 run=`: the port is built on flow9's Vector, whose
# natives exist ONLY in the java runtime (ds/vector.flow:10). `flowc1 run=`
# uses the bytecode runner, which hangs on the first makeVector — so every
# test here has to be compiled to a jar first. This script does that for you
# and keeps the jars out of the source tree (see JARS below).
set -e

TEST_DIR=$( cd "$( dirname "$0" )" && pwd -P )
SRC_DIR=$( cd "$TEST_DIR/../../.." && pwd -P )      # …/src
MML="${RUSSELL_MATH:-$HOME/dev/math}/MML-test"
# jars are BUILD ARTIFACTS: they live outside the source tree
JARS="${MIZAR_TEST_JARS:-$SRC_DIR/../build/mizar-test}"

usage() {
	echo "usage: ./test.sh [<unit>|verifier|list] [args...]"
	echo
	echo "  units:    $( cd "$TEST_DIR" && ls test_*.flow | sed 's/^test_//; s/\.flow$//' | grep -vE '^(utils|all|verifier)$' | tr '\n' ' ' )"
	echo "  verifier: the whole MML in one process (see the header of test_verifier.flow)"
}

case "${1:-all}" in
	list|-h|--help|help) usage; exit 0 ;;
esac

what="${1:-all}"; shift || true

# Every component suite lives in ONE program (test_all.flow, selected with
# suite=<name>); the integration test and the standalone stage gates are
# programs of their own.
case "$what" in
	all)      prog=test_all;      sel=() ;;
	verifier) prog=test_verifier; sel=() ;;
	*)
		if [ -f "$TEST_DIR/test_$what.flow" ] && grep -q '^main()' "$TEST_DIR/test_$what.flow"; then
			prog="test_$what"; sel=()            # a program with its own main
		else
			prog=test_all; sel=("suite=$what")   # a component suite
		fi
	;;
esac

mkdir -p "$JARS"
cd "$SRC_DIR"
flowc1 "mizar/original/test/$prog.flow" "jar=$JARS/$prog.jar" > /dev/null

# the integration test holds `jobs` articles in one heap at once
if [ "$what" = "verifier" ]; then MEM=16g; else MEM=4g; fi
exec java -Xss128m -Xmx$MEM -jar "$JARS/$prog.jar" "mml=$MML" "home=$HOME" "${sel[@]}" "$@"
