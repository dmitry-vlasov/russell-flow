#!/bin/bash
# Build the Flow9 Mizar verifier (src/mizar/original).
#   ./build.sh        — build mizar.jar (the verifier)
#   ./build.sh test   — build + run the component test suite
#
# EVERYTHING ELSE UNDER TEST IS IN test/ — see test/test.sh:
#   test/test.sh              the component suites
#   test/test.sh <unit>       one of them
#   test/test.sh verifier     the whole MML through the whole verifier
# and the per-stage gates (test/m3*.sh, test/m4*.sh, test/fullrun.sh) for
# localizing a regression to one pass.

set -e

SCRIPT_DIR=$( cd "$( dirname "$0" )" && pwd -P )
SRC_DIR=$( cd "$SCRIPT_DIR/../.." && pwd -P )   # …/src

cd "$SRC_DIR"

flowc1 jar=1 mizar/original/mizar.flow
echo "built: $SRC_DIR/mizar/original/mizar.jar"

if [ "$1" = "test" ]; then
	exec "$SCRIPT_DIR/test/test.sh"
fi
