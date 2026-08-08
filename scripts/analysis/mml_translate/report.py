#!/usr/bin/env python3
"""Report of an S-A translation census.

    python3 report.py /tmp/mml_translate/result.tsv

Prints the status counts, the failing articles with their diagnostic, and how
many MML theorem statements the translated articles carry (theorem count comes
from the translator's own line, `mizar-to-ru: 'a' = N theorems`).
"""
import re, sys
from collections import Counter

re_thms = re.compile(r"= (\d+) theorems")


def main(path):
    rows = [l.rstrip("\n").split("\t") for l in open(path) if l.strip()]
    st = Counter(r[1] for r in rows)
    total = len(rows)
    print("articles: %d" % total)
    for k, v in st.most_common():
        print("  %-8s %4d  %5.1f%%" % (k, v, 100.0 * v / total))

    thms = 0
    for r in rows:
        if r[1] == "ok" and len(r) > 2:
            m = re_thms.search(r[2])
            if m:
                thms += int(m.group(1))
    print("theorem statements in translated articles: %d" % thms)

    bad = [r for r in rows if r[1] != "ok"]
    if bad:
        print("\nnot translated:")
        for r in bad:
            print("  %-14s %-8s %s" % (r[0], r[1], r[2] if len(r) > 2 else ""))
        print("\nbuckets:")
        for msg, n in Counter(
                (r[2][:60] if len(r) > 2 else "") for r in bad).most_common():
            print("  %4d  %s" % (n, msg))


if __name__ == "__main__":
    main(sys.argv[1] if len(sys.argv) > 1 else "/tmp/mml_translate/result.tsv")
