#!/usr/bin/env python3
"""From the per-article feature census: the observed feature-set order, its
Hasse structure, and article/theorem mass per layer.

Two readings per article:
  OWN     — the machinery this article's own text needs
  CLOSURE — OWN unioned over everything it transitively depends on: what a
            from-scratch replay of this article must support
"""
import json, sys
from collections import defaultdict, Counter

FEATS = ['f_attr', 'f_mode', 'f_fraenkel', 'f_scheme', 'f_struct', 'f_arith']
SHORT = {'f_attr':'attr', 'f_mode':'mode', 'f_fraenkel':'fraenkel',
         'f_scheme':'scheme', 'f_struct':'struct', 'f_arith':'arith'}

d = json.load(open(sys.argv[1]))
arts = sorted(d)
lower = {a.upper(): a for a in arts}

def own(a):
    return frozenset(f for f in FEATS if d[a][f])

# transitive closure over declared dependencies
memo = {}
def clos(a, stack=()):
    if a in memo: return memo[a]
    if a in stack: return own(a)          # cycle guard (should not happen)
    s = set(own(a))
    for dep in d[a]['deps']:
        da = lower.get(dep)
        if da and da in d:
            s |= clos(da, stack + (a,))
    r = frozenset(s)
    memo[a] = r
    return r

def name(fs):
    return '{' + ','.join(SHORT[f] for f in FEATS if f in fs) + '}' if fs else '{}'

ownC, cloC = Counter(), Counter()
ownT, cloT = Counter(), Counter()
for a in arts:
    o, c = own(a), clos(a)
    ownC[o] += 1;  ownT[o] += d[a]['theorems']
    cloC[c] += 1;  cloT[c] += d[a]['theorems']

TOT_A = len(arts); TOT_T = sum(d[a]['theorems'] for a in arts)
print(f"MML: {TOT_A} articles, {TOT_T} theorems\n")

print("=== per-feature mass (CLOSURE reading: needed to replay from scratch) ===")
for f in FEATS:
    na = sum(1 for a in arts if f in clos(a))
    nt = sum(d[a]['theorems'] for a in arts if f in clos(a))
    print(f"  {SHORT[f]:9s} articles {na:5d} ({100*na/TOT_A:5.1f}%)   theorems {nt:6d} ({100*nt/TOT_T:5.1f}%)")

print("\n=== observed CLOSURE feature-sets (the layers that actually occur) ===")
for fs, n in sorted(cloC.items(), key=lambda kv: (len(kv[0]), -kv[1])):
    print(f"  {name(fs):45s} articles {n:5d}  theorems {cloT[fs]:6d}")

print("\n=== observed OWN feature-sets (top 12) ===")
for fs, n in ownC.most_common(12):
    print(f"  {name(fs):45s} articles {n:5d}  theorems {ownT[fs]:6d}")

# cumulative: how much of the MML is reachable if we support exactly a set S
print("\n=== cumulative reach: support S  =>  articles/theorems whose CLOSURE fits in S ===")
import itertools
cands = []
for r in range(len(FEATS)+1):
    for combo in itertools.combinations(FEATS, r):
        S = frozenset(combo)
        na = sum(1 for a in arts if clos(a) <= S)
        nt = sum(d[a]['theorems'] for a in arts if clos(a) <= S)
        cands.append((nt, na, S))
cands.sort(key=lambda x: (len(x[2]), -x[0]))
seen = set()
for nt, na, S in cands:
    key = (len(S), nt)
    if nt == 0 and len(S) > 0: continue
    if key in seen: continue
    seen.add(key)
    print(f"  support {name(S):45s} -> articles {na:5d} ({100*na/TOT_A:5.1f}%)  theorems {nt:6d} ({100*nt/TOT_T:5.1f}%)")

# the frontier articles of the smallest layers
print("\n=== articles in the smallest closure layers ===")
for fs, n in sorted(cloC.items(), key=lambda kv: len(kv[0]))[:4]:
    names = [a for a in arts if clos(a) == fs]
    print(f"  {name(fs)}: {len(names)} -> {', '.join(names[:14])}{' ...' if len(names)>14 else ''}")
