#!/usr/bin/env python3
"""MML feature census: what machinery does each article need, and how do the
feature sets nest? Everything is read from Mizar's own per-article files —
no hand-picked seed list.

Per article:
  .evl  — the declared environment: requirements, schemes/registrations
          directives, and the article dependency set (all Ident names)
  .atr  — the ACCOMMODATED constructor environment: constructor kinds with
          their defining article (kind M = mode, V = attribute, G = aggregate,
          L = selector, K = functor, R = predicate)
  .miz  — surface features Mizar has no constructor for: Fraenkel terms
          ({ .. where .. }), structure definitions
"""
import os, re, sys, json
from collections import defaultdict

MML = os.path.expanduser("~/dev/math/MML/mml")

re_dir   = re.compile(r'<Directive name="([a-z]+)" argnr="(\d+)"')
re_ident = re.compile(r'<Ident name="([A-Z0-9_]+)"')
re_ctor  = re.compile(r'<Constructor kind="([A-Z])" nr="\d+" aid="([A-Z0-9_]+)"')
re_thm   = re.compile(r'^\s*theorem\b', re.M)
re_where = re.compile(r'\bwhere\b')
re_struct= re.compile(r'\bstruct\b')

def parse_evl(path):
    """-> (dict directive -> [idents], set deps)"""
    txt = open(path, encoding='utf-8', errors='replace').read()
    out, deps = {}, set()
    cur = None
    for line in txt.split('\n'):
        m = re_dir.search(line)
        if m:
            cur = m.group(1); out[cur] = []
            continue
        m = re_ident.search(line)
        if m and cur:
            out[cur].append(m.group(1))
            deps.add(m.group(1))
    return out, deps

def parse_atr(path):
    """-> set of (kind, aid) constructor declarations"""
    s = set()
    with open(path, encoding='utf-8', errors='replace') as f:
        for line in f:
            m = re_ctor.match(line.strip())
            if m:
                s.add((m.group(1), m.group(2)))
    return s

def main():
    arts = sorted(a[:-4] for a in os.listdir(MML) if a.endswith('.miz'))
    data = {}
    for i, a in enumerate(arts):
        if i % 200 == 0:
            print(f"  ...{i}/{len(arts)}", file=sys.stderr)
        evl = os.path.join(MML, a + '.evl')
        atr = os.path.join(MML, a + '.atr')
        miz = os.path.join(MML, a + '.miz')
        if not (os.path.exists(evl) and os.path.exists(atr)):
            continue
        dirs, deps = parse_evl(evl)
        ctors = parse_atr(atr)
        mtxt = open(miz, encoding='utf-8', errors='replace').read()
        AID = a.upper()
        deps.discard('HIDDEN'); deps.discard(AID)

        kinds = defaultdict(set)
        for k, aid in ctors:
            kinds[k].add(aid)
        # HIDDEN gives object/set (M) and =/in (R); anything else is real machinery
        soft_modes = kinds['M'] - {'HIDDEN'}
        attrs      = kinds['V'] - {'HIDDEN'}
        structs    = (kinds['G'] | kinds['L'] | kinds['U'])

        reqs = set(dirs.get('requirements', []))
        data[a] = dict(
            deps       = sorted(deps),
            theorems   = len(re_thm.findall(mtxt)),
            f_mode     = bool(soft_modes),
            f_attr     = bool(attrs) or bool(dirs.get('registrations')),
            f_struct   = bool(structs) or bool(re_struct.search(mtxt)),
            f_scheme   = bool(dirs.get('schemes')),
            f_fraenkel = bool(re_where.search(mtxt)),
            f_arith    = bool(reqs & {'NUMERALS', 'REAL', 'ARITHM'}),
            reqs       = sorted(reqs),
        )
    json.dump(data, open(sys.argv[1], 'w'))
    print(f"wrote {len(data)} articles", file=sys.stderr)

main()
