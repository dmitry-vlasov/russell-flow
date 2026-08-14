import mizar/miz_set;;

/* Hand-authored auxiliary inference forms missing from the set.mm-generated
   foundation. The forward wave needs 1-HYP inference forms whose trigger is
   the ground fact itself (the imnani lesson): these two eliminate a ground
   ¬(φ∨ψ) — the ∨-De-Morgan duals of imnani. */

/* 1-hyp ELIMINATION forms of the el-biconds: the forward wave's trigger is the
   ground ∈-premise ITSELF (the imnani pattern) — the schematic elun/elin/eldif
   bicond facts are rightly deferred forever by ground-first ordering, so a bare
   ∈-premise can never be unfolded through them via mpbi in-wave. */

theorem elunei (A : class, B : class, C : class) {
	hyp 1 : wff = |- ( A ∈ ( B ∪ C ) ) ;;
	----------
	prop : wff = |- ( ( A ∈ B ) ∨ ( A ∈ C ) ) ;;
} proof {
	step 1 : wff = elun () |- ( ( A ∈ ( B ∪ C ) ) ↔ ( ( A ∈ B ) ∨ ( A ∈ C ) ) ) ;;
	step 2 : wff = mpbi (hyp 1, step 1) |- ( ( A ∈ B ) ∨ ( A ∈ C ) ) ;;
}

theorem elinei (A : class, B : class, C : class) {
	hyp 1 : wff = |- ( A ∈ ( B ∩ C ) ) ;;
	----------
	prop : wff = |- ( ( A ∈ B ) ∧ ( A ∈ C ) ) ;;
} proof {
	step 1 : wff = elin () |- ( ( A ∈ ( B ∩ C ) ) ↔ ( ( A ∈ B ) ∧ ( A ∈ C ) ) ) ;;
	step 2 : wff = mpbi (hyp 1, step 1) |- ( ( A ∈ B ) ∧ ( A ∈ C ) ) ;;
}

theorem eldifei (A : class, B : class, C : class) {
	hyp 1 : wff = |- ( A ∈ ( B ∖ C ) ) ;;
	----------
	prop : wff = |- ( ( A ∈ B ) ∧ ¬ ( A ∈ C ) ) ;;
} proof {
	step 1 : wff = eldif () |- ( ( A ∈ ( B ∖ C ) ) ↔ ( ( A ∈ B ) ∧ ¬ ( A ∈ C ) ) ) ;;
	step 2 : wff = mpbi (hyp 1, step 1) |- ( ( A ∈ B ) ∧ ¬ ( A ∈ C ) ) ;;
}

theorem pm2.45i (ph : wff, ps : wff) {
	hyp 1 : wff = |- ¬ ( ph ∨ ps ) ;;
	----------
	prop : wff = |- ¬ ph ;;
} proof {
	step 1 : wff = orc () |- ( ph → ( ph ∨ ps ) ) ;;
	step 2 : wff = mto (hyp 1, step 1) |- ¬ ph ;;
}

theorem pm2.46i (ph : wff, ps : wff) {
	hyp 1 : wff = |- ¬ ( ph ∨ ps ) ;;
	----------
	prop : wff = |- ¬ ps ;;
} proof {
	step 1 : wff = olc () |- ( ps → ( ph ∨ ps ) ) ;;
	step 2 : wff = mto (hyp 1, step 1) |- ¬ ps ;;
}

/* The MIRROR of difin0 ((A∩B)∖B = ∅): oriented rewriting matches leftmost-
   outermost against the AC-canonical arrangement, which may put the subtrahend
   in the FIRST ∩-slot — set.mm has no swapped form. */

theorem difin0b (A : class, B : class) {
	prop : wff = |- ( ( ( A ∩ B ) ∖ A ) = ∅ ) ;;
} proof {
	step 1 : wff = incom () |- ( ( A ∩ B ) = ( B ∩ A ) ) ;;
	step 2 : wff = difeq1i (step 1) |- ( ( ( A ∩ B ) ∖ A ) = ( ( B ∩ A ) ∖ A ) ) ;;
	step 3 : wff = difin0 () |- ( ( ( B ∩ A ) ∖ A ) = ∅ ) ;;
	step 4 : wff = eqtri (step 2, step 3) |- ( ( ( A ∩ B ) ∖ A ) = ∅ ) ;;
}

/* ∖∖-chain subset schema for rel-normalize: the canonical dif32-ordered
   normal form ((A∖B)∖C) must match a ⊆-schema against EITHER residual
   subtrahend — difss covers (A∖B)∖C ⊆ (A∖B); this covers the other. */

theorem difdifss (A : class, B : class, C : class) {
	prop : wff = |- ( ( ( A ∖ B ) ∖ C ) ⊆ ( A ∖ C ) ) ;;
} proof {
	step 1 : wff = dif32 () |- ( ( ( A ∖ B ) ∖ C ) = ( ( A ∖ C ) ∖ B ) ) ;;
	step 2 : wff = difss () |- ( ( ( A ∖ C ) ∖ B ) ⊆ ( A ∖ C ) ) ;;
	step 3 : wff = eqsstri (step 1, step 2) |- ( ( ( A ∖ B ) ∖ C ) ⊆ ( A ∖ C ) ) ;;
}

/* (A∖B)∖A = ∅ — the ∖∖-mirror kill (A∖B ⊆ A). */

theorem difabsb (A : class, B : class) {
	prop : wff = |- ( ( ( A ∖ B ) ∖ A ) = ∅ ) ;;
} proof {
	step 1 : wff = difss () |- ( ( A ∖ B ) ⊆ A ) ;;
	step 2 : wff = ssdif0 () |- ( ( ( A ∖ B ) ⊆ A ) ↔ ( ( ( A ∖ B ) ∖ A ) = ∅ ) ) ;;
	step 3 : wff = mpbi (step 1, step 2) |- ( ( ( A ∖ B ) ∖ A ) = ∅ ) ;;
}

/* (A∩B)∩B = A∩B — ∩-idempotence hidden one level deep; the comm-shaped
   assoc rules regroup only order-decreasingly, so the duplicate may never
   become adjacent for inidm. */

theorem inabsb (A : class, B : class) {
	prop : wff = |- ( ( ( A ∩ B ) ∩ B ) = ( A ∩ B ) ) ;;
} proof {
	step 1 : wff = inass () |- ( ( ( A ∩ B ) ∩ B ) = ( A ∩ ( B ∩ B ) ) ) ;;
	step 2 : wff = inidm () |- ( ( B ∩ B ) = B ) ;;
	step 3 : wff = ineq2i (step 2) |- ( ( A ∩ ( B ∩ B ) ) = ( A ∩ B ) ) ;;
	step 4 : wff = eqtri (step 1, step 3) |- ( ( ( A ∩ B ) ∩ B ) = ( A ∩ B ) ) ;;
}

/* THE 3-PART PARTITION BRIDGE: (A∖B) ∪ ((B∖A) ∪ (A∩B)) = A∪B — the exact
   AC-canonical arrangement the △-unfold's normal form takes (t93/t95);
   greedy order-decreasing regrouping cannot bring the inundif redex
   adjacent, so the merged identity is provided whole. */

theorem unpart3 (A : class, B : class) {
	prop : wff = |- ( ( ( A ∖ B ) ∪ ( ( B ∖ A ) ∪ ( A ∩ B ) ) ) = ( A ∪ B ) ) ;;
} proof {
	step 1 : wff = un12 () |- ( ( ( A ∖ B ) ∪ ( ( B ∖ A ) ∪ ( A ∩ B ) ) ) = ( ( B ∖ A ) ∪ ( ( A ∖ B ) ∪ ( A ∩ B ) ) ) ) ;;
	step 2 : wff = uncom () |- ( ( ( A ∖ B ) ∪ ( A ∩ B ) ) = ( ( A ∩ B ) ∪ ( A ∖ B ) ) ) ;;
	step 3 : wff = inundif () |- ( ( ( A ∩ B ) ∪ ( A ∖ B ) ) = A ) ;;
	step 4 : wff = eqtri (step 2, step 3) |- ( ( ( A ∖ B ) ∪ ( A ∩ B ) ) = A ) ;;
	step 5 : wff = uneq2i (step 4) |- ( ( ( B ∖ A ) ∪ ( ( A ∖ B ) ∪ ( A ∩ B ) ) ) = ( ( B ∖ A ) ∪ A ) ) ;;
	step 6 : wff = uncom () |- ( ( ( B ∖ A ) ∪ A ) = ( A ∪ ( B ∖ A ) ) ) ;;
	step 7 : wff = undif2 () |- ( ( A ∪ ( B ∖ A ) ) = ( A ∪ B ) ) ;;
	step 8 : wff = eqtri (step 6, step 7) |- ( ( ( B ∖ A ) ∪ A ) = ( A ∪ B ) ) ;;
	step 9 : wff = eqtri (step 5, step 8) |- ( ( ( B ∖ A ) ∪ ( ( A ∖ B ) ∪ ( A ∩ B ) ) ) = ( A ∪ B ) ) ;;
	step 10 : wff = eqtri (step 1, step 9) |- ( ( ( A ∖ B ) ∪ ( ( B ∖ A ) ∪ ( A ∩ B ) ) ) = ( A ∪ B ) ) ;;
}

/* THE AXIOM OF REGULARITY in Mizar's phrasing (TARSKI's axiom 3 = t3_tarski:
   every nonempty set has an ∈-minimal member). A genuine ZFC axiom — the
   miz_set foundation slice deliberately avoids set.mm's ax-reg ($j usage
   comments), so it is postulated here rather than derived. Stated in the
   exact shape the Mizar→Russell translation produces (incl. the shadowed
   rebinding of the first variable), so the article theorem closes by a
   direct one-step application. */

axiom ax-reg-mizar (x : setvar, y : setvar, z : setvar) disjointed(x y, x z, y z) {
	prop : wff = |- ∀ x ∀ y ¬ (
		 ( x ∈ y ) ∧ ∀ z ¬ (
			 ( z ∈ y ) ∧ ∀ x ¬ (
				 ( x ∈ y ) ∧ ( x ∈ z ) 
			) 
		) 
	) ;;
}

/* UNCONDITIONAL PAIR/TRIPLE MEMBERSHIP for setvar elements (the velsn
   analogues): the foundation states elprg/eltpg with an (A ∈ V) guard, which
   a setvar discharges by vex. veltp additionally re-associates eltpg's 3-ary
   disjunction into nested binary ∨ (df-3or), the shape the clause machinery
   consumes. Used by the ∈-literal layer's {,}/{,,} unfolds (t86_enumset1). */

theorem velpr (x : setvar, A : class, B : class) {
	prop : wff = |- ( ( x ∈ { A , B } ) ↔ ( ( x = A ) ∨ ( x = B ) ) ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = elprg () |- ( ( x ∈ _V ) → ( ( x ∈ { A , B } ) ↔ ( ( x = A ) ∨ ( x = B ) ) ) ) ;;
	step 3 : wff = ax-mp (step 1, step 2) |- ( ( x ∈ { A , B } ) ↔ ( ( x = A ) ∨ ( x = B ) ) ) ;;
}

theorem veltp (x : setvar, A : class, B : class, C : class) {
	prop : wff = |- ( ( x ∈ { A , B , C } ) ↔ ( ( ( x = A ) ∨ ( x = B ) ) ∨ ( x = C ) ) ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = eltpg () |- ( ( x ∈ _V ) → ( ( x ∈ { A , B , C } ) ↔ ( ( x = A ) ∨ ( x = B ) ∨ ( x = C ) ) ) ) ;;
	step 3 : wff = ax-mp (step 1, step 2) |- ( ( x ∈ { A , B , C } ) ↔ ( ( x = A ) ∨ ( x = B ) ∨ ( x = C ) ) ) ;;
	step 4 : wff = df-3or () |- ( ( ( x = A ) ∨ ( x = B ) ∨ ( x = C ) ) ↔ ( ( ( x = A ) ∨ ( x = B ) ) ∨ ( x = C ) ) ) ;;
	step 5 : wff = bitri (step 3, step 4) |- ( ( x ∈ { A , B , C } ) ↔ ( ( ( x = A ) ∨ ( x = B ) ) ∨ ( x = C ) ) ) ;;
}

/* SETVAR-DISCHARGED ENUMERATION LEMMAS (the vex-composites the ∈-layer's
   enumeration channels consume — zfmisc_1's equality-extraction class):
   membership ↔ enumeration-⊆ bridges (snss/prss/tpss with their A∈_V
   hypotheses discharged; vtpss additionally re-associates tpss's 3-ary ∧
   into nested binary ∧ via df-3an) and the self-membership instances. */

theorem vsnss (x : setvar, B : class) {
	prop : wff = |- ( ( x ∈ B ) ↔ ( { x } ⊆ B ) ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = snss (step 1) |- ( ( x ∈ B ) ↔ ( { x } ⊆ B ) ) ;;
}

theorem vprss (x : setvar, y : setvar, C : class) {
	prop : wff = |- ( ( ( x ∈ C ) ∧ ( y ∈ C ) ) ↔ ( { x , y } ⊆ C ) ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = vex () |- ( y ∈ _V ) ;;
	step 3 : wff = prss (step 1, step 2) |- ( ( ( x ∈ C ) ∧ ( y ∈ C ) ) ↔ ( { x , y } ⊆ C ) ) ;;
}

theorem vtpss (x : setvar, y : setvar, z : setvar, D : class) {
	prop : wff = |- ( ( ( ( x ∈ D ) ∧ ( y ∈ D ) ) ∧ ( z ∈ D ) ) ↔ ( { x , y , z } ⊆ D ) ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = vex () |- ( y ∈ _V ) ;;
	step 3 : wff = vex () |- ( z ∈ _V ) ;;
	step 4 : wff = tpss (step 1, step 2, step 3) |- ( ( ( x ∈ D ) ∧ ( y ∈ D ) ∧ ( z ∈ D ) ) ↔ ( { x , y , z } ⊆ D ) ) ;;
	step 5 : wff = df-3an () |- ( ( ( x ∈ D ) ∧ ( y ∈ D ) ∧ ( z ∈ D ) ) ↔ ( ( ( x ∈ D ) ∧ ( y ∈ D ) ) ∧ ( z ∈ D ) ) ) ;;
	step 6 : wff = bitr3i (step 5, step 4) |- ( ( ( ( x ∈ D ) ∧ ( y ∈ D ) ) ∧ ( z ∈ D ) ) ↔ ( { x , y , z } ⊆ D ) ) ;;
}

theorem vprid1 (x : setvar, B : class) {
	prop : wff = |- ( x ∈ { x , B } ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = prid1 (step 1) |- ( x ∈ { x , B } ) ;;
}

theorem vprid2 (x : setvar, A : class) {
	prop : wff = |- ( x ∈ { A , x } ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = prid2 (step 1) |- ( x ∈ { A , x } ) ;;
}

theorem vtpid1 (x : setvar, B : class, C : class) {
	prop : wff = |- ( x ∈ { x , B , C } ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = tpid1 (step 1) |- ( x ∈ { x , B , C } ) ;;
}

theorem vtpid2 (x : setvar, A : class, C : class) {
	prop : wff = |- ( x ∈ { A , x , C } ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = tpid2 (step 1) |- ( x ∈ { A , x , C } ) ;;
}

theorem vtpid3 (x : setvar, A : class, B : class) {
	prop : wff = |- ( x ∈ { A , B , x } ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = tpid3 (step 1) |- ( x ∈ { A , B , x } ) ;;
}

/* Kuratowski unfold of an ordered pair of SETVARS — the 0-hyp instance of dfop
   (whose A ∈ _V / B ∈ _V sethood hyps vex discharges). The literal prover's
   el-classifier rewrites x ∈ 〈 a , b 〉 through it into x ∈ { { a } , { a , b } }
   and cascades on through velpr into the case-split disjunction (zfmisc_1
   t93-class: x26 ∈ x24 = 〈 x5 , x8 〉). */
theorem vdfop (x : setvar, y : setvar) {
	prop : wff = |- ( 〈 x , y 〉 = { { x } , { x , y } } ) ;;
} proof {
	step 1 : wff = vex () |- ( x ∈ _V ) ;;
	step 2 : wff = vex () |- ( y ∈ _V ) ;;
	step 3 : wff = dfop (step 1, step 2) |- ( 〈 x , y 〉 = { { x } , { x , y } } ) ;;
}

/* The Mizar-shaped existential of product membership: A ∈ (B ×. C) yields the
   ¬∀∀¬ form the translator emits for 'ex u,v st u in B & v in C & A = [u,v]'
   (zfmisc_1 t84/t85-class ∃-goals). elxp reordered by 2eximi, folded to
   ¬∀∀¬ by 2exnaln. */
theorem elxpmiz (A : class, B : class, C : class, u : setvar, v : setvar) disjointed(A u v, B u v, C u v) {
	prop : wff = |- ( ( A ∈ ( B ×. C ) ) → ¬ ∀ u ∀ v ¬ ( ( u ∈ B ) ∧ ( ( v ∈ C ) ∧ ( A = 〈 u , v 〉 ) ) ) ) ;;
} proof {
	step 1 : wff = elxp () |- ( ( A ∈ ( B ×. C ) ) ↔ ∃ u ∃ v ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) ) ;;
	step 2 : wff = biimpi (step 1) |- ( ( A ∈ ( B ×. C ) ) → ∃ u ∃ v ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) ) ;;
	step 3 : wff = simpr () |- ( ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) → ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) ;;
	step 4 : wff = simpl () |- ( ( ( u ∈ B ) ∧ ( v ∈ C ) ) → ( u ∈ B ) ) ;;
	step 5 : wff = syl (step 3, step 4) |- ( ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) → ( u ∈ B ) ) ;;
	step 6 : wff = simpr () |- ( ( ( u ∈ B ) ∧ ( v ∈ C ) ) → ( v ∈ C ) ) ;;
	step 7 : wff = syl (step 3, step 6) |- ( ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) → ( v ∈ C ) ) ;;
	step 8 : wff = simpl () |- ( ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) → ( A = 〈 u , v 〉 ) ) ;;
	step 9 : wff = jca (step 7, step 8) |- ( ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) → ( ( v ∈ C ) ∧ ( A = 〈 u , v 〉 ) ) ) ;;
	step 10 : wff = jca (step 5, step 9) |- ( ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) → ( ( u ∈ B ) ∧ ( ( v ∈ C ) ∧ ( A = 〈 u , v 〉 ) ) ) ) ;;
	step 11 : wff = 2eximi (step 10) |- ( ∃ u ∃ v ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) → ∃ u ∃ v ( ( u ∈ B ) ∧ ( ( v ∈ C ) ∧ ( A = 〈 u , v 〉 ) ) ) ) ;;
	step 12 : wff = syl (step 2, step 11) |- ( ( A ∈ ( B ×. C ) ) → ∃ u ∃ v ( ( u ∈ B ) ∧ ( ( v ∈ C ) ∧ ( A = 〈 u , v 〉 ) ) ) ) ;;
	step 13 : wff = 2exnaln () |- ( ∃ u ∃ v ( ( u ∈ B ) ∧ ( ( v ∈ C ) ∧ ( A = 〈 u , v 〉 ) ) ) ↔ ¬ ∀ u ∀ v ¬ ( ( u ∈ B ) ∧ ( ( v ∈ C ) ∧ ( A = 〈 u , v 〉 ) ) ) ) ;;
	step 14 : wff = sylib (step 12, step 13) |- ( ( A ∈ ( B ×. C ) ) → ¬ ∀ u ∀ v ¬ ( ( u ∈ B ) ∧ ( ( v ∈ C ) ∧ ( A = 〈 u , v 〉 ) ) ) ) ;;
}

/* The elxp-native conjunct order of elxpmiz (the translator emits both). */
theorem elxpmiz2 (A : class, B : class, C : class, u : setvar, v : setvar) disjointed(A u v, B u v, C u v) {
	prop : wff = |- ( ( A ∈ ( B ×. C ) ) → ¬ ∀ u ∀ v ¬ ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) ) ;;
} proof {
	step 1 : wff = elxp () |- ( ( A ∈ ( B ×. C ) ) ↔ ∃ u ∃ v ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) ) ;;
	step 2 : wff = biimpi (step 1) |- ( ( A ∈ ( B ×. C ) ) → ∃ u ∃ v ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) ) ;;
	step 3 : wff = 2exnaln () |- ( ∃ u ∃ v ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) ↔ ¬ ∀ u ∀ v ¬ ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) ) ;;
	step 4 : wff = sylib (step 2, step 3) |- ( ( A ∈ ( B ×. C ) ) → ¬ ∀ u ∀ v ¬ ( ( A = 〈 u , v 〉 ) ∧ ( ( u ∈ B ) ∧ ( v ∈ C ) ) ) ) ;;
}

/* Implication forms of the connective-opening biconds, for the completer's
   tableau (three-layer architecture): each replaces a 3-step
   lemma+biimpi+syl chain with one instance ([[unifier-planner]]). */

theorem dfbi3imp (ph : wff, ps : wff) {
	prop : wff = |- ( ( ph ↔ ps ) → ( ( ph ∧ ps ) ∨ ( ¬ ph ∧ ¬ ps ) ) ) ;;
} proof {
	step 1 : wff = dfbi3 () |- ( ( ph ↔ ps ) ↔ ( ( ph ∧ ps ) ∨ ( ¬ ph ∧ ¬ ps ) ) ) ;;
	step 2 : wff = biimpi (step 1) |- ( ( ph ↔ ps ) → ( ( ph ∧ ps ) ∨ ( ¬ ph ∧ ¬ ps ) ) ) ;;
}

theorem nbbnimp (ph : wff, ps : wff) {
	prop : wff = |- ( ¬ ( ph ↔ ps ) → ( ¬ ph ↔ ps ) ) ;;
} proof {
	step 1 : wff = nbbn () |- ( ( ¬ ph ↔ ps ) ↔ ¬ ( ph ↔ ps ) ) ;;
	step 2 : wff = biimpri (step 1) |- ( ¬ ( ph ↔ ps ) → ( ¬ ph ↔ ps ) ) ;;
}

theorem pm4.61imp (ph : wff, ps : wff) {
	prop : wff = |- ( ¬ ( ph → ps ) → ( ph ∧ ¬ ps ) ) ;;
} proof {
	step 1 : wff = pm4.61 () |- ( ¬ ( ph → ps ) ↔ ( ph ∧ ¬ ps ) ) ;;
	step 2 : wff = biimpi (step 1) |- ( ¬ ( ph → ps ) → ( ph ∧ ¬ ps ) ) ;;
}

theorem imorimp (ph : wff, ps : wff) {
	prop : wff = |- ( ( ph → ps ) → ( ¬ ph ∨ ps ) ) ;;
} proof {
	step 1 : wff = imor () |- ( ( ph → ps ) ↔ ( ¬ ph ∨ ps ) ) ;;
	step 2 : wff = biimpi (step 1) |- ( ( ph → ps ) → ( ¬ ph ∨ ps ) ) ;;
}

/* Sethood of the symmetric difference: A △ B = (A ∖ B) ∪ (B ∖ A), each
   difference a set by its left argument, the union by both. Named for the
   emitter's foundation sethood table ([[full-replay]]). */

theorem symdifex (A : class, B : class) {
	hyp 1 : wff = |- ( A ∈ _V ) ;;
	hyp 2 : wff = |- ( B ∈ _V ) ;;
	----------
	prop : wff = |- ( ( A △ B ) ∈ _V ) ;;
} proof {
	step 1 : wff = difexi (hyp 1) |- ( ( A ∖ B ) ∈ _V ) ;;
	step 2 : wff = difexi (hyp 2) |- ( ( B ∖ A ) ∈ _V ) ;;
	step 3 : wff = unex (step 1, step 2) |- ( ( ( A ∖ B ) ∪ ( B ∖ A ) ) ∈ _V ) ;;
	step 4 : wff = df-symdif () |- ( ( A △ B ) = ( ( A ∖ B ) ∪ ( B ∖ A ) ) ) ;;
	step 5 : wff = eleq1i (step 4) |- ( ( ( A △ B ) ∈ _V ) ↔ ( ( ( A ∖ B ) ∪ ( B ∖ A ) ) ∈ _V ) ) ;;
	step 6 : wff = mpbir (step 3, step 5) |- ( ( A △ B ) ∈ _V ) ;;
}
