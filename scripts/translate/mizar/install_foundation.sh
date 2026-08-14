#!/bin/bash
# Copy the hand-authored foundation lemmas into the math tree, where the
# translator expects them ($RUSSELL_MATH/mizar/miz_aux.ru). Everything else in
# that directory is generated; this file is not, so its master copy lives in
# the repository under foundation/ — see foundation/README.md.
set -eu
BASE_DIR=$( cd "$( dirname "$0" )/../../.." && pwd -P )
MATH="${RUSSELL_MATH:-$HOME/dev/math}"
install -D -m 644 "$BASE_DIR/foundation/miz_aux.ru" "$MATH/mizar/miz_aux.ru"
echo "installed $MATH/mizar/miz_aux.ru from $BASE_DIR/foundation/miz_aux.ru"
