#!/bin/bash
#
# Try to PROVE each leftover env axiom of a translated A-map article from the
# foundation alone — i.e. find which of them are actually redundant (derivable),
# as opposed to genuinely new facts.
#
# For every `axiom` in <math>/mizar/<module>.ru it builds a tiny probe source
# that imports ONLY the foundation (miz_set) + the article's own signature
# (constants/rules/congruences), restates that one axiom as an OPEN theorem
# (`step 1 : wff = ? |- <stmt>`), and runs `prove` on it in isolation. Isolation
# matters: each probe contains a single theorem, so the prover can never lean on
# another still-unproved env axiom — a success means "provable from the
# foundation", full stop.
#
# Usage:
#   scripts/translate/mizar/prove_env_axioms.sh [module] [time-limit] [relevance-depth]
#   scripts/translate/mizar/prove_env_axioms.sh xb1 60s 1
#
# Search budget (BFS depth/size + forward-closure) is intentionally generous —
# these statements need real search, not the small default. Tune the MD/MS/FC
# env vars if you want, e.g.  MD=12 FC=3 scripts/translate/mizar/prove_env_axioms.sh
#
# Probes are left in /tmp/prove_env_<module>/ so you can inspect or re-run a
# single one by hand, e.g.:
#   russellj translate/mizar/prove_probe file=/tmp/prove_env_xb1/t1_xboole_0.ru target=t1_xboole_0 tl=120s md=12

set -e
MODULE="${1:-xb1}"
TLIMIT="${2:-60s}"
RELDEPTH="${3:-1}"
MD="${MD:-10}"          # BFS max depth
MS="${MS:-1000000}"     # BFS max size
FC="${FC:-2}"           # forward-closure depth
MATH="${RUSSELL_MATH:-$HOME/dev/math}"
ART="$MATH/mizar/$MODULE.ru"
WORK="/tmp/prove_env_$MODULE"

command -v russellj >/dev/null 2>&1 && RJ="russellj" || RJ="$(cd "$(dirname "$0")/../../.." && pwd)/bin/russellj"
[ -f "$ART" ] || { echo "no article: $ART (run translate/mizar/translate first)"; exit 1; }
rm -rf "$WORK"; mkdir -p "$WORK"

# 1. Generate one isolated probe (.ru) per env axiom.
NAMES=$(python3 - "$ART" "$WORK" <<'PY'
import sys, os
art, work = sys.argv[1], sys.argv[2]
lines = open(art).read().split('\n')
# The generated file lists all env axioms before the article's own theorems, so
# everything up to the first `theorem ` is import + signature + the axioms.
ti = next((i for i, l in enumerate(lines) if l.startswith('theorem ')), len(lines))
prefix = lines[:ti]
imp = next(l for l in prefix if l.startswith('import '))

# Walk top-level blocks. Block braces close at column 0; expression brackets
# ({ } singleton/pair) are always indented, so a line starting with '}' ends a block.
sig, axioms, i = [], [], 0
while i < len(prefix):
    l = prefix[i]
    kw = l.split(' ', 1)[0] if l.strip() else ''
    if kw in ('constant', 'rule', 'congruence', 'axiom'):
        block = [l]; i += 1
        while not prefix[i].startswith('}'):
            block.append(prefix[i]); i += 1
        block.append(prefix[i]); i += 1
        text = '\n'.join(block)
        if kw == 'axiom':
            name = l.split('(')[0].split(' ', 1)[1].strip()
            a = text.index('prop : wff = ') + len('prop : wff = ')
            proprhs = text[a: text.rindex(';;') + 2]          # `|- <stmt> ;;`
            thm = 'theorem' + text[len('axiom'):]             # same header + body
            thm = thm[:thm.rindex('}')] + '} proof {\n\tstep 1 : wff = ? ' + proprhs + '\n}'
            axioms.append((name, thm))
        else:
            sig.append(text)
    else:
        i += 1

header = imp + '\n\n' + '\n'.join(sig) + '\n'
for name, thm in axioms:
    open(os.path.join(work, name + '.ru'), 'w').write(header + '\n' + thm + '\n')
print(' '.join(n for n, _ in axioms))
PY
)

# 2. Prove each probe in isolation; report provable vs not.
echo "=== ${MODULE}: proving env axioms as open theorems from the foundation"
echo "    (time-limit=$TLIMIT relevance-depth=$RELDEPTH max-depth=$MD max-size=$MS forward-closure=$FC) ==="
prov=0; tot=0
for n in $NAMES; do
    tot=$((tot + 1))
    got=$("$RJ" no-server=1 translate/mizar/prove_probe \
            file="$WORK/$n.ru" target="$n" rel="$RELDEPTH" tl="$TLIMIT" md="$MD" ms="$MS" fc="$FC" \
          2>/dev/null | grep -oE "PROBE_RESULT $n proved=[0-9]+" | grep -oE "[0-9]+$" || echo 0)
    if [ "${got:-0}" -ge 1 ]; then printf "  PROVABLE      %s\n" "$n"; prov=$((prov + 1));
    else                          printf "  not provable  %s\n" "$n"; fi
done
echo "=== $prov / $tot provable from the foundation (probes in $WORK/) ==="
