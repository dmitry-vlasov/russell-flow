#!/bin/bash
# Build the Flow9 Mizar verifier (src/mizar/original).
#   ./build.sh        — build mizar.jar (the verifier)
#   ./build.sh test   — build + run the full integration test suite

set -e

SCRIPT_DIR=$( cd "$( dirname "$0" )" && pwd -P )
SRC_DIR=$( cd "$SCRIPT_DIR/../.." && pwd -P )   # …/src

cd "$SRC_DIR"

flowc1 jar=1 mizar/original/mizar.flow
echo "built: $SRC_DIR/mizar/original/mizar.jar"

if [ "$1" = "test" ]; then
	flowc1 jar=1 mizar/original/test/test_all.flow
	MML="${RUSSELL_MATH:-$HOME/dev/math}/MML-test"
	java -Xss128m -Xmx4g -jar mizar/original/test/test_all.jar "mml=$MML"
fi
