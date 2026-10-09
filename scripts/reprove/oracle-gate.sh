#!/bin/bash
# END-TO-END CHECK of reproving (the metamath-gate law): the found proofs must survive
# every checker that is NOT the prover.
#   1. copy the dataset  <math>/<afile>  ->  <math>/<afile>-reproved  (never touch the original)
#   2. reprove every theorem with the oracle tactic and INSTALL the found proofs (replace=1)
#   3. verify the whole math with the Russell verifier, write the .ru files to the copy
#   4. re-read the copy from disk, export to Metamath, verify with the ORIGINAL metamath binary
#      (translate/ru2mm prints the METAMATH GATE line)
# Usage: scripts/reprove/oracle-gate.sh afile=set-50000 [others=N] [hyps=0|1] [parallel=N]
#        [theorem_time_limit=60s] [time_limit=60m] [mem=16g]
set -e
afile=set-50000; others=0; hyps=1; parallel=0; thl=60s; jobl=60m; mem=16g
for a in "$@"; do case $a in
  afile=*) afile=${a#*=};; others=*) others=${a#*=};; hyps=*) hyps=${a#*=};; parallel=*) parallel=${a#*=};;
  theorem_time_limit=*) thl=${a#*=};; time_limit=*) jobl=${a#*=};; mem=*) mem=${a#*=};;
  *) echo "unknown arg: $a"; exit 2;; esac; done
MATH=${RUSSELL_MATH:?set RUSSELL_MATH}
SRC=$MATH/$afile; DST=$MATH/$afile-reproved
RJ=$(cd "$(dirname "$0")/../.." && pwd)/bin/russellj
[ -d "$SRC" ] || { echo "no dataset $SRC"; exit 2; }
echo "== 1. copy $SRC -> $DST"
rm -rf "$DST"; cp -r "$SRC" "$DST"
sed -i "s|$SRC|$DST|g; s|\"$afile\"|\"$afile-reproved\"|g; s|$afile\_root|$afile-reproved_root|g" "$DST/russell.conf"
mv "$DST/${afile}_root.ru" "$DST/${afile}-reproved_root.ru"
rm -f "$DST"/*.mm
cat "$DST/russell.conf"
cd "$MATH"
echo "== 2-3. reprove with replace=1, verify, write-ru  (others=$others hyps=$hyps parallel=$parallel)"
$RJ mem=$mem no-server=1 "conf-load dir=./$afile-reproved; conf-set gc-after-task=1; read-ru; verify; print (\"before: verified=\" + verify.success + \" disproved=\" + verify.disproved); reprove target=all strict-fail=1 replace=1 parallel=$parallel time-limit=$thl job-time-limit=$jobl tactic=\"oracle(others=$others, hyps=$hyps)\"; print (\"reproved=\" + reprove.reproved-count + \" failed=\" + reprove.failed-count + \" installed proofs differing from the stored ones: \" + reprove.changed-count); verify; print (\"after replace: verified=\" + verify.success + \" disproved=\" + verify.disproved); write-ru; print (\"written: \" + write-ru.size + \" files\");"
echo "== changed .ru files vs the original dataset:"
diff -rq "$SRC" "$DST" 2>/dev/null | grep -c "differ" || true
echo "== 4. re-read from disk, export to Metamath, ORIGINAL metamath verifier"
# translate/ru2mm expects `adir` (the math dir) as a script variable set by its caller, as mm2ru2mm does.
$RJ mem=$mem no-server=1 "adir := cwd(); translate/ru2mm afile=$afile-reproved;" 2>&1 | grep -i "METAMATH GATE\|ru read\|ru translated\|mm has written\|error\|failure" | head -8
