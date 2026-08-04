# The port audit tools

Two scripts. They exist because the M4 campaign was slowed by fixing byte
diffs one article at a time instead of auditing the Pascal the analyzer calls.

## `callees.py` — what the analyzer reaches that the checker never did

    cd ~/dev/system/kernel && python3 <this>/callees.py

Lists every routine of the SHARED units (`correl`, `identify`, `roundcl`,
`iocorrel`, `schemes`, `ellipses`) that `generato.pas` or `analyzer.pas` calls,
into `/tmp/analyzer_callees.txt`.

Measured 2026-08-04: 245 shared routines, **78 reached from the analyzer**.
All 78 already exist in the port — the apparent misses are renames
(`CopyTerm`→`mizCopyTrm`, `WithinFormula`→`mizWithinFrm`,
`MarkTermsInTTColl`→`mizTTCollMark`) plus the `Dispose*` family that GC makes
moot.

So the job is never "find the missing function". It is "check those 78 BODIES
against the Pascal". Both bugs of that class so far had the same shape:

  * `FrOpVarTypeOK` — Pascal is `A or B`, the port had only `A`
  * `CompareFrms`  — Pascal case list, the port omitted `ikFrmPrivPred`

## `bucket.sh` — turn N byte diffs into a handful of causes

    xargs -a <articles> -P 8 -I{} ./bucket.sh {} > /tmp/buckets.txt
    awk '{print $2, $3}' /tmp/buckets.txt | sort | uniq -c | sort -rn

Prints `<article> PORT=<elem> REF=<elem>` — the XML element on each side at
the first differing line. Articles cluster by cause, not by article.

Measured 2026-08-04 on an 80-article sample, all classified:

    26  Adjective vs Adjective     cluster contents / order
    19  Pred      vs Pred
    10  Func      vs Func
    17  ErrorFrm/ErrorTrm vs a real node   (an analysis silently failed)
     8  everything else

## The rule these tools serve

A discrepancy is a question about the ORIGINAL's logic, not about the port's.
Open the Pascal procedure that emits the differing element, read it, and only
then look at the port. Every fix commit in this campaign cites the
`file.pas:line` it transcribes — if a commit cannot cite one, it is a guess and
does not belong in the tree.
