# MML layer census

Which Mizar machinery each theorem needs, read from Mizar's own per-article
files. Feeds `docs/mml-port-plan.md` (the map that fixes the order of work).

    python3 theorem_layers.py out.json     # per-THEOREM statement features
    python3 article_features.py arts.json  # per-ARTICLE features + deps
    python3 report_articles.py arts.json   # article-level report

`theorem_layers.py` is the one that matters: it classifies every statement in
the library by the machinery needed to write it down (mode / attr / struct /
fraenkel / choice / num), resolving constructor kinds through each article's
accommodated constructor list (`.atr`). Statements whose body is `<Verum/>`
are reported as SKIPPED — those articles were analyzed with their blocks
skipped, and counting them fakes a large type-free core.

`article_features.py` + `report_articles.py` record the negative result that
article-level dependency closure does NOT stratify the library: 95% of
articles transitively declare every feature, so layers only exist per
theorem statement.

Reads `~/dev/math/MML/mml`. Runtime: seconds for articles, ~15s for theorems.
