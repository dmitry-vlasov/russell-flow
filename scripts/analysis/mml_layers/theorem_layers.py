#!/usr/bin/env python3
"""Per-THEOREM layer census over the whole MML.

The layer of a theorem is decided by its STATEMENT alone — what machinery is
needed to even write it down in Russell. Read from the analyzer's own .xml:

  mode      a type other than HIDDEN's object/set  (Typ kind=M -> aid != HIDDEN)
  attr      an adjective in a type                 (<Adjective)
  struct    a structure constructor                (kind in G/L/U/J)
  fraenkel  a Fraenkel (set-comprehension) term    (<Fraenkel)
  choice    a choice term                          (<Choice)
  num       a numeral                              (<Num)

Constructor kind+nr is resolved to its defining article through the article's
own .atr (the accommodated constructor list), so "is this mode HIDDEN's set or
a real soft type" is answered by Mizar's data, not by a name list.
"""
import os, re, sys, json
from collections import Counter

MML = os.path.expanduser("~/dev/math/MML/mml")
re_ctor = re.compile(r'^<Constructor kind="([A-Z])" nr="(\d+)" aid="([A-Z0-9_]+)"')
re_kind = re.compile(r'kind="([A-Z])" nr="(\d+)"')

STRUCT_KINDS = {'G', 'L', 'U', 'J'}

def ctor_map(path):
    m = {}
    with open(path, encoding='utf-8', errors='replace') as f:
        for line in f:
            g = re_ctor.match(line)
            if g:
                m[(g.group(1), int(g.group(2)))] = g.group(3)
    return m

def scan(article):
    atr = os.path.join(MML, article + '.atr')
    xml = os.path.join(MML, article + '.xml')
    if not (os.path.exists(atr) and os.path.exists(xml)):
        return []
    cm = ctor_map(atr)
    out = []
    verum_only = False
    nlines = 0
    in_thm = False      # inside JustifiedTheorem
    in_prop = False     # inside its statement
    depth = 0
    feats = set()
    with open(xml, encoding='utf-8', errors='replace') as f:
        for line in f:
            if not in_thm:
                if line.startswith('<JustifiedTheorem'):
                    in_thm, in_prop, depth, feats = True, False, 0, set()
                continue
            if not in_prop:
                if line.startswith('<Proposition'):
                    in_prop, depth, verum_only, nlines = True, 1, True, 0
                elif line.startswith('</JustifiedTheorem'):
                    in_thm = False
                continue
            # inside the statement
            if line.startswith('</Proposition'):
                # a Verum-only statement means the article was analyzed with its
                # blocks skipped (huge generated articles) — not a real statement
                out.append(sorted(feats) if not verum_only else ['SKIPPED'])
                in_thm = in_prop = False
                continue
            nlines += 1
            if not line.startswith('<Verum'): verum_only = False
            if line.startswith('<Fraenkel'):  feats.add('fraenkel')
            elif line.startswith('<Choice'):  feats.add('choice')
            elif line.startswith('<Num'):     feats.add('num')
            elif line.startswith('<Adjective'): feats.add('attr')
            g = re_kind.search(line)
            if g:
                k, nr = g.group(1), int(g.group(2))
                if k in STRUCT_KINDS:
                    feats.add('struct')
                elif k == 'M':
                    # a constructor absent from the article's own .atr is one the
                    # article DECLARES itself (only imported ones are listed), so
                    # a miss is a real soft type, never HIDDEN's object/set
                    if cm.get(('M', nr), article.upper()) != 'HIDDEN':
                        feats.add('mode')
                elif k == 'V':
                    feats.add('attr')
    return out

def main():
    arts = sorted(a[:-4] for a in os.listdir(MML) if a.endswith('.miz'))
    res = {}
    for i, a in enumerate(arts):
        if i % 100 == 0:
            print(f"  ...{i}/{len(arts)}", file=sys.stderr, flush=True)
        res[a] = scan(a)
    json.dump(res, open(sys.argv[1], 'w'))
    n = sum(len(v) for v in res.values())
    print(f"wrote {len(res)} articles / {n} theorems", file=sys.stderr)

main()
