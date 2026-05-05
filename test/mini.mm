$( Minimal propositional calculus subset of set.mm.
   Used for CI roundtrip testing (mm2ru2mm) without requiring the full
   set.mm download.  Axioms ax-1/ax-2/ax-3/ax-mp plus ~20 theorems are
   sufficient to exercise the MM→RU→MM translation pipeline. $)

$c ( ) -> -. wff |- $.

$v ph ps ch th ta $.

$( $j syntax 'wff'; syntax '|-' as 'wff'; unambiguous 'klr 5'; $)

wph $f wff ph $.
wps $f wff ps $.
wch $f wff ch $.
wth $f wff th $.
wta $f wff ta $.

wn  $a wff -. ph $.
wi  $a wff ( ph -> ps ) $.

${
  min $e |- ph $.
  maj $e |- ( ph -> ps ) $.
  ax-mp $a |- ps $.
$}

ax-1 $a |- ( ph -> ( ps -> ph ) ) $.
ax-2 $a |- ( ( ph -> ( ps -> ch ) ) -> ( ( ph -> ps ) -> ( ph -> ch ) ) ) $.
ax-3 $a |- ( ( -. ph -> -. ps ) -> ( ps -> ph ) ) $.

${
  mp2.1 $e |- ph $.
  mp2.2 $e |- ps $.
  mp2.3 $e |- ( ph -> ( ps -> ch ) ) $.
  mp2 $p |- ch $=
    ( wi ax-mp ) BCEABCGDFHH $.
$}

${
  mp2b.1 $e |- ph $.
  mp2b.2 $e |- ( ph -> ps ) $.
  mp2b.3 $e |- ( ps -> ch ) $.
  mp2b $p |- ch $=
    ( ax-mp ) BCABDEGFG $.
$}

${
  a1i.1 $e |- ph $.
  a1i $p |- ( ps -> ph ) $=
    ( wi ax-1 ax-mp ) ABADCABEF $.
$}

${
  2a1i.1 $e |- ph $.
  2a1i $p |- ( ps -> ( ch -> ph ) ) $=
    ( wi a1i ) CAEBACDFF $.
$}

${
  mp1i.1 $e |- ph $.
  mp1i.2 $e |- ( ph -> ps ) $.
  mp1i $p |- ( ch -> ps ) $=
    ( ax-mp a1i ) BCABDEFG $.
$}

${
  a2i.1 $e |- ( ph -> ( ps -> ch ) ) $.
  a2i $p |- ( ( ph -> ps ) -> ( ph -> ch ) ) $=
    ( wi ax-2 ax-mp ) ABCEEABEACEEDABCFG $.
$}

${
  mpd.1 $e |- ( ph -> ps ) $.
  mpd.2 $e |- ( ph -> ( ps -> ch ) ) $.
  mpd $p |- ( ph -> ch ) $=
    ( wi a2i ax-mp ) ABFACFDABCEGH $.
$}

${
  mpi.1 $e |- ps $.
  mpi.2 $e |- ( ph -> ( ps -> ch ) ) $.
  mpi $p |- ( ph -> ch ) $=
    ( a1i mpd ) ABCBADFEG $.
$}

${
  imim2i.1 $e |- ( ph -> ps ) $.
  imim2i $p |- ( ( ch -> ph ) -> ( ch -> ps ) ) $=
    ( wi a1i a2i ) CABABECDFG $.
$}

${
  syl.1 $e |- ( ph -> ps ) $.
  syl.2 $e |- ( ps -> ch ) $.
  syl $p |- ( ph -> ch ) $=
    ( wi a1i mpd ) ABCDBCFAEGH $.
$}

${
  3syl.1 $e |- ( ph -> ps ) $.
  3syl.2 $e |- ( ps -> ch ) $.
  3syl.3 $e |- ( ch -> th ) $.
  3syl $p |- ( ph -> th ) $=
    ( syl ) ACDABCEFHGH $.
$}

id $p |- ( ph -> ph ) $=
  ( wi ax-1 mpd ) AAABZAAACAECD $.

idd $p |- ( ph -> ( ps -> ps ) ) $=
  ( wi id a1i ) BBCABDE $.

${
  a1d.1 $e |- ( ph -> ps ) $.
  a1d $p |- ( ph -> ( ch -> ps ) ) $=
    ( wi ax-1 syl ) ABCBEDBCFG $.
$}

${
  2a1d.1 $e |- ( ph -> ps ) $.
  2a1d $p |- ( ph -> ( ch -> ( th -> ps ) ) ) $=
    ( wi a1d ) ADBFCABDEGG $.
$}

${
  a2d.1 $e |- ( ph -> ( ps -> ( ch -> th ) ) ) $.
  a2d $p |- ( ph -> ( ( ps -> ch ) -> ( ps -> th ) ) ) $=
    ( wi a2i syl ) ABCGBDGEBCDFHI $.
$}

${
  sylcom.1 $e |- ( ph -> ( ps -> ch ) ) $.
  sylcom.2 $e |- ( ps -> ( ch -> th ) ) $.
  sylcom $p |- ( ph -> ( ps -> th ) ) $=
    ( wi syl com12 ) DBCDABCGFEHI $.
  com12  $p |- ( ps -> ( ph -> ch ) ) $=
    ( wi a1i sylcom ) ABCDECDGBFHI $.
$}

${
  mpcom.1 $e |- ( ps -> ph ) $.
  mpcom.2 $e |- ( ph -> ( ps -> ch ) ) $.
  mpcom $p |- ( ps -> ch ) $=
    ( mpd com12 ) ABCABCDFEG $.
$}

${
  syli.1 $e |- ( ps -> ( ph -> ch ) ) $.
  syli.2 $e |- ( ph -> ( ch -> th ) ) $.
  syli $p |- ( ph -> ( ps -> th ) ) $=
    ( wi com12 sylcom ) ABDEBAFCABDGHI $.
$}

${
  pm2.27.1 $e |- ph $.
  pm2.27 $p |- ( ( ph -> ps ) -> ps ) $=
    ( wi id mpcom ) ABDZABCGEF $.
$}

$( end of mini.mm $)
