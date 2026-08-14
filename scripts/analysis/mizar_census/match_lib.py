#!/usr/bin/env python3
"""Match open goals against the library, exactly and modulo commutativity.

Same as match_lib.py, plus a second index keyed on a canonical form that
sorts the arguments of the commutative operators. Those hits are CANDIDATES
(the verifier decides), not proofs — a commuted row needs its bridge.

  usage: match_lib2.py <emitted.ru> [miz_set.ru]
"""
import sys, re, os, collections

art = sys.argv[1]
lib = sys.argv[2] if len(sys.argv) > 2 else os.path.expanduser('~/dev/math/mizar/miz_set.ru')
TOK = re.compile(r'\s+')
COMM = ('∪', '∩', '△', '=', '↔', '∧', '∨')


def toks(s):
    return TOK.sub(' ', s).strip()


def parse(s):
    """a very small reader: nested lists of tokens"""
    out, stack = [], []
    for t in toks(s).split(' '):
        if t == '(':
            stack.append(out)
            out = []
        elif t == ')':
            done, out = out, stack.pop()
            out.append(done)
        else:
            out.append(t)
    return out


def unparse(x):
    if isinstance(x, str):
        return x
    return '( ' + ' '.join(unparse(y) for y in x) + ' )'


def commsort(x):
    if isinstance(x, str):
        return x
    ys = [commsort(y) for y in x]
    if len(ys) == 3 and isinstance(ys[1], str) and ys[1] in COMM:
        a, b = unparse(ys[0]), unparse(ys[2])
        if b < a:
            ys = [ys[2], ys[1], ys[0]]
    return ys


def canon(s, varpat, comm):
    x = parse(s)
    if comm:
        x = commsort(x)
    out, seen = [], {}
    for t in unparse(x).split(' '):
        if varpat.fullmatch(t):
            if t not in seen:
                seen[t] = 'v%d' % len(seen)
            out.append(seen[t])
        else:
            out.append(t)
    return ' '.join(out)


libvar = re.compile(r'[A-Z]|ph|ps|ch|th|[a-z]')
text = open(lib).read()
exact, approx = collections.defaultdict(list), collections.defaultdict(list)
for m in re.finditer(r'^theorem (\S+)[^\n]*\{\n(.*?)^\}', text, re.S | re.M):
    name, body = m.group(1), m.group(2)
    if 'hyp ' in body:
        continue
    pm = re.search(r'prop : wff = \|-(.*?);;', body, re.S)
    if not pm:
        continue
    st = toks(pm.group(1))
    forms = [(st, '')]
    em = re.fullmatch(r'\( (.*) ↔ (.*) \)', st)
    if em:
        forms.append(('( %s → %s )' % (em.group(1), em.group(2)), 'biimpi'))
        forms.append(('( %s → %s )' % (em.group(2), em.group(1)), 'biimpri'))
    for f, tag in forms:
        try:
            exact[canon(f, libvar, False)].append((tag, name))
            approx[canon(f, libvar, True)].append((tag, name))
        except IndexError:
            pass

gvar = re.compile(r'x\d+')
lines = open(art).read().split('\n')
goals, i = [], 0
while i < len(lines):
    if '= ?' in lines[i] and 'step' in lines[i]:
        buf, j = [], i
        while j < len(lines):
            buf.append(lines[j].strip())
            if ';;' in lines[j]:
                break
            j += 1
        g = toks(' '.join(buf))
        k = g.find('|-')
        goals.append(g[k + 2:].replace(';;', '').strip())
        i = j + 1
    else:
        i += 1


def strip_forall(g):
    while True:
        m = re.match(r'∀ x\d+ (.*)', g)
        if not m:
            return g
        g = m.group(1)


def split_top(s):
    if not (s.startswith('( ') and s.endswith(' )')):
        return None
    d, parts, cur = 0, [], []
    for t in s[2:-2].split(' '):
        if t == '(':
            d += 1
        elif t == ')':
            d -= 1
        if d == 0 and t in ('∧', '∨', '→', '↔'):
            parts.append((' '.join(cur), t))
            cur = []
        else:
            cur.append(t)
    if len(parts) != 1:
        return None
    return (parts[0][0], parts[0][1], ' '.join(cur))


def neg_to_imp(g):
    m = re.fullmatch(r'¬ (\(.*\))', g)
    if not m:
        return None
    cs, cur = [], m.group(1)
    while True:
        sp = split_top(cur)
        if sp and sp[1] == '∧':
            cs.append(sp[0])
            cur = sp[2]
        else:
            cs.append(cur)
            break
    if len(cs) < 2:
        return None
    p = cs[0]
    for q in cs[1:-1]:
        p = '( %s ∧ %s )' % (p, q)
    nm = re.fullmatch(r'¬ (.*)', cs[-1])
    c = nm.group(1) if nm else '¬ %s' % cs[-1]
    return '( %s → %s )' % (p, c)


nex = nap = 0
for g in goals:
    g0 = strip_forall(g)
    shown = False
    for form, how in ((g0, 'direct'), (neg_to_imp(g0), 'neg-bridge')):
        if not form or shown:
            continue
        for idx, kind, ctr in ((exact, 'EXACT', 'e'), (approx, 'comm', 'a')):
            try:
                rows = idx.get(canon(form, gvar, kind == 'comm'))
            except IndexError:
                rows = None
            if rows:
                shown = True
                if kind == 'EXACT':
                    nex += 1
                else:
                    nap += 1
                tags = ', '.join('%s%s' % (n, '/' + t if t else '') for t, n in rows[:4])
                print('%-5s %-10s %s\n                << %s' % (kind, how, g0[:130], tags))
                break
print('# %d exact, %d commuted, of %d open goals' % (nex, nap, len(goals)))
