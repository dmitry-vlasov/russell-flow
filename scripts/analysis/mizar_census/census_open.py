#!/usr/bin/env python3
"""Census the OPEN goals (`= ?` steps) of an emitted article.

Prints each open goal's full text (joined to one line), sorted by length,
with a count of identical shapes.
"""
import sys, re, collections

path = sys.argv[1]
maxlen = int(sys.argv[2]) if len(sys.argv) > 2 else 400

text = open(path).read()
lines = text.split('\n')
goals = []
i = 0
while i < len(lines):
    if '= ?' in lines[i] and 'step' in lines[i]:
        buf = []
        j = i
        while j < len(lines):
            buf.append(lines[j].strip())
            if ';;' in lines[j]:
                break
            j += 1
        whole = ' '.join(buf)
        whole = re.sub(r'\s+', ' ', whole)
        goals.append(whole)
        i = j + 1
    else:
        i += 1

# strip the "step N : wff = ? by(...) |-" prefix
def body(g):
    k = g.find('|-')
    return g[k+2:].strip() if k >= 0 else g

short = [(len(body(g)), body(g), g) for g in goals]
short.sort()
print(f"# {len(goals)} open goals in {path}")
cnt = collections.Counter(b for _, b, _ in short)
for ln, b, g in short:
    if ln <= maxlen:
        cited = re.search(r'by\(([^)]*)\)', g)
        print(f"[{ln:4d}] {b[:maxlen]}   << {cited.group(1) if cited else ''}")
