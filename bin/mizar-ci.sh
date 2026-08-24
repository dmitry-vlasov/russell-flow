#!/bin/bash
#
# Mizar port CI: the fast integration check, self-contained.
#
#   bin/mizar-ci.sh [build=0]
#
# 1. Builds src/mizar/original/mizar.jar (skip with build=0 if it is fresh).
# 2. Runs the component suites (mizarj test=all). Suites needing the full
#    MML data skip themselves where it is absent; any FAILED test is fatal.
# 3. Unpacks the bundled article fixture (test/mizar-fixture.tar.gz: the
#    8 gate articles' Mizar environment files + the original verifier's
#    reference reports) and verifies every article through the FULL chain
#    (parser -> MSM -> analyzer -> checker) in one process, byte-comparing
#    each produced report against the reference. The checker tally is
#    pinned: 8 articles, 3037 inferences — a silent under-run is a failure.
#
# Runs in well under a minute after the jar build (the fixture pass itself
# is ~2 s).
set -e

BASE_DIR=$( cd "$( dirname "$0" )/.." && pwd -P )
BUILD=1
for i in "$@"; do
case $i in
	build=*) BUILD="${i#*=}" ;;
	*) echo "unknown argument: $i" >&2; exit 1 ;;
esac
done

if [ "$BUILD" != 0 ]; then
	( cd "$BASE_DIR/src" && flowc1 jar=1 mizar/original/mizar.flow )
	[ -f "$BASE_DIR/src/mizar/original/mizar.jar" ] \
		|| { echo "mizar.jar was not produced" >&2; exit 1; }
fi

echo "=== component suites"
"$BASE_DIR/bin/mizarj" test=all

echo "=== full-chain fixture gate"
WORK=$(mktemp -d)
trap 'rm -rf "$WORK"' EXIT
tar -xzf "$BASE_DIR/test/mizar-fixture.tar.gz" -C "$WORK"
OUT=$("$BASE_DIR/bin/mizarj" article-list="$WORK/fixture.lar" \
	mml="$WORK" full=1 ref="$WORK/ref" jobs=2)
echo "$OUT"
echo "$OUT" | grep -q "Total articles: 8, verified: 8, failed: 0, inferences: 3037," \
	|| { echo "FIXTURE GATE RED: tally is not the pinned 8/8 @ 3037" >&2; exit 1; }
echo "MIZAR CI GREEN"
