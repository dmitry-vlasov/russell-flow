#!/usr/bin/env python3
"""Topological order of the MML articles.

Each article's `.evl` lists the articles named in its environment directives.
The semantic dependencies are the ones that import content: notations,
constructors, registrations, definitions, theorems, schemes, equalities,
expansions. `vocabularies` and `requirements` are excluded — a vocabulary is a
symbol file and may be written after the article that reads it (relat_1 reads
VALUED_1), so including it makes the graph cyclic.

Printing the articles in topological order gives the order in which they must
be translated: an article translates against its translated dependencies.

    python3 dep_order.py            # every article, one per line
    python3 dep_order.py 50         # the first 50 in the order
"""
import os, re, sys
from collections import defaultdict

MML = os.path.expanduser("~/dev/math/MML/mml")
re_ident = re.compile(r'<Ident name="([A-Z0-9_]+)"')
re_dir = re.compile(r'<Directive name="([a-z]+)"')
CONTENT_DIRECTIVES = {"notations", "constructors", "registrations",
                      "definitions", "theorems", "schemes", "equalities",
                      "expansions"}


def deps_of(article):
    path = os.path.join(MML, article + ".evl")
    if not os.path.exists(path):
        return set()
    d, cur = set(), None
    for line in open(path, encoding="utf-8", errors="replace"):
        m = re_dir.search(line)
        if m:
            cur = m.group(1)
            continue
        m = re_ident.search(line)
        if m and cur in CONTENT_DIRECTIVES:
            d.add(m.group(1).lower())
    d.discard("hidden")
    d.discard(article)
    return d


def main():
    arts = sorted(f[:-4] for f in os.listdir(MML) if f.endswith(".xml"))
    known = set(arts)
    deps = {a: deps_of(a) & known for a in arts}

    # Kahn, breaking ties by (fewest deps, name) so the order is stable.
    indeg = {a: len(deps[a]) for a in arts}
    users = defaultdict(set)
    for a, ds in deps.items():
        for d in ds:
            users[d].add(a)
    ready = sorted(a for a in arts if indeg[a] == 0)
    order, done = [], set()
    while ready:
        a = ready.pop(0)
        order.append(a)
        done.add(a)
        for u in sorted(users[a]):
            indeg[u] -= 1
            if indeg[u] == 0:
                ready.append(u)
        ready.sort(key=lambda x: (len(deps[x]), x))
    if len(order) < len(arts):
        rest = [a for a in arts if a not in done]
        print("# WARNING: %d articles in cycles, appended by dep count"
              % len(rest), file=sys.stderr)
        order += sorted(rest, key=lambda x: (len(deps[x]), x))

    n = int(sys.argv[1]) if len(sys.argv) > 1 else len(order)
    for a in order[:n]:
        print(a)


if __name__ == "__main__":
    main()
