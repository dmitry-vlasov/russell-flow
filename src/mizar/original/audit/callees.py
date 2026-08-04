import re,sys
# names defined in the shared units (correl, identify, roundcl, iocorrel, schemes)
shared={}
for u in ['correl.pas','identify.pas','roundcl.pas','iocorrel.pas','schemes.pas','ellipses.pas']:
    for m in re.finditer(r'^(?:procedure|function)\s+([A-Za-z_0-9]+)\s*[\(;:]', open(u).read(), re.M):
        shared.setdefault(m.group(1),u)
# names called from the analyzer-only units
called=set()
for u in ['generato.pas','analyzer.pas']:
    txt=open(u).read()
    for n in shared:
        if re.search(r'\b'+n+r'\s*[\(;]', txt): called.add(n)
print(len(shared),"shared routines,",len(called),"reached from generato/analyzer")
open('/tmp/analyzer_callees.txt','w').write('\n'.join(sorted(called)))
