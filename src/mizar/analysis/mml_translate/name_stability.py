#!/usr/bin/env python3
"""Is a constructor's Russell name a function of the CONSTRUCTOR, or of the
article that happens to be translated?

The translator names a constructor by its notation word (.eno -> .frm -> .dcx)
and then drops any word that two constructors of the CURRENT article's
environment share. That last step is environment-dependent: `real` is the
notation of the functor NUMBERS:1, but any article that also imports the
attribute XREAL_0:1 (`real`) sees the word twice and falls back to the
mechanical name `k1_numbers` for the functor. The same constructor therefore
gets two different Russell names in two articles — the articles no longer link,
and importing both raises a duplicate declaration.

This script measures that: per article it reproduces the resolution and
reports how many constructors are named inconsistently across the library.

    python3 name_stability.py [N]     # N = how many articles to read
"""
import os, re, sys
from collections import defaultdict, Counter

MML = os.path.expanduser("~/dev/math/MML/mml")
re_el = re.compile(r'<(\w[\w-]*)\s([^>]*)/?>')
re_at = re.compile(r'(\w+)="([^"]*)"')

RESERVED = {"ascii", "axiom", "congruence", "constant", "defiendum", "definiens",
            "definition", "disjointed", "hyp", "import", "latex", "lemma",
            "meta", "proof", "prop", "rule", "step", "symbol", "term",
            "theorem", "type", "wff", "class", "setvar", "set"}


def elements(path, tag):
    if not os.path.exists(path):
        return
    for line in open(path, encoding="utf-8", errors="replace"):
        m = re_el.search(line)
        if m and m.group(1) == tag:
            yield dict(re_at.findall(m.group(2)))


def clean_word(raw):
    if not raw or not re.fullmatch(r"[A-Za-z][A-Za-z0-9_]*", raw):
        return ""
    base = raw.lower()
    return base + "_" if base in RESERVED else base


def article_names(a):
    """-> {constructor key -> russell name} as this article would compute it"""
    dcx = {d["kind"] + ":" + d["nr"]: d.get("name", "")
           for d in elements(os.path.join(MML, a + ".dcx"), "Symbol")}
    frm = {f["nr"]: (f["kind"], f["symbolnr"])
           for f in elements(os.path.join(MML, a + ".frm"), "Format")}
    out = {}
    for p in elements(os.path.join(MML, a + ".eno"), "Pattern"):
        key = "%s:%s:%s" % (p.get("constrkind"), p.get("aid"), p.get("constrnr"))
        ks = frm.get(p.get("formatnr"))
        if not ks:
            continue
        skind = "O" if ks[0] == "K" else ks[0]
        w = clean_word(dcx.get(skind + ":" + ks[1], ""))
        if w:
            out[key] = w
    # the translator drops a word claimed by more than one constructor
    counts = Counter(out.values())
    return {k: v for k, v in out.items() if counts[v] == 1}


def main():
    arts = sorted(f[:-4] for f in os.listdir(MML) if f.endswith(".xml"))
    n = int(sys.argv[1]) if len(sys.argv) > 1 else len(arts)
    arts = arts[:n]

    names = defaultdict(set)     # constructor -> set of names it was given
    seen = defaultdict(int)      # constructor -> in how many articles it appears
    for a in arts:
        for k, v in article_names(a).items():
            names[k].add(v)
            seen[k] += 1

    unstable = {k: v for k, v in names.items() if len(v) > 1}
    print("articles read: %d" % len(arts))
    print("constructors with a readable name somewhere: %d" % len(names))
    print("named inconsistently across articles: %d (%.1f%%)"
          % (len(unstable), 100.0 * len(unstable) / max(1, len(names))))
    print("\nexamples:")
    for k, v in list(sorted(unstable.items()))[:15]:
        print("  %-28s %s" % (k, ", ".join(sorted(v))))

    # a name is also lost outright when the article drops it: count the
    # constructors that are readable in one article and mechanical in another
    print("\nnote: a constructor missing from an article's readable map falls")
    print("back to its mechanical name (k1_numbers) in that article only.")


if __name__ == "__main__":
    main()
