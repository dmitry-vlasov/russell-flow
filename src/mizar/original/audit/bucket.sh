#!/bin/bash
# For one article: first differing line, port side vs reference side.
a="$1"
MML=$HOME/dev/math/MML-test
REF=$HOME/mizar_oracle/m4ref
d=$(mktemp -d); trap 'rm -rf "$d"' EXIT
timeout 280 java -Xss128m -Xmx3g -jar $HOME/dev/russell-flow/src/mizar/original/test/test_xml.jar \
  article=$a mml=$MML out=$d/p.xml >/dev/null 2>&1
[ -s "$d/p.xml" ] || { echo "$a CRASH"; exit; }
L=$(cmp "$d/p.xml" "$REF/$a.xml" 2>/dev/null | sed -n 's/.*line \([0-9]*\).*/\1/p')
[ -n "$L" ] || { echo "$a OK"; exit; }
pe=$(sed -n "${L}p" "$d/p.xml" | grep -o '^<[/A-Za-z-]*' | head -1)
re=$(sed -n "${L}p" "$REF/$a.xml" | grep -o '^<[/A-Za-z-]*' | head -1)
echo "$a PORT=${pe:-?} REF=${re:-?}"
