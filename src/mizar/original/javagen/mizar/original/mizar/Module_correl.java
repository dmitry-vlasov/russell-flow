// HASH COLLISIONS: YES
// timestamp: 1785497684000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_correl {
	public static int g_coAggregate;
	public static int g_coAttribute;
	public static int g_coFunctor;
	public static int g_coLAST;
	public static int g_coMode;
	public static int g_coPredicate;
	public static int g_coSelector;
	public static int g_coStructMode;
	public static Struct g_mizNil;
	public static int g_syAbstractness;
	public static int g_syAssociativity;
	public static int g_syAsymmetry;
	public static int g_syCommutativity;
	public static int g_syConnectedness;
	public static int g_syIdempotence;
	public static int g_syInvolutiveness;
	public static int g_syIrreflexivity;
	public static int g_syProjectivity;
	public static int g_syReflexivity;
	public static int g_sySethood;
	public static int g_sySymmetry;
	public static int g_syTransitivity;
	public static void init() {
		g_coAggregate=6;
		g_coAttribute=2;
		g_coFunctor=4;
		g_coLAST=6;
		g_coMode=0;
		g_coPredicate=3;
		g_coSelector=5;
		g_coStructMode=1;
		g_mizNil=((Struct)SingletonStructs.str_MizTrmNil);
		g_syAbstractness=13;
		g_syAssociativity=4;
		g_syAsymmetry=8;
		g_syCommutativity=6;
		g_syConnectedness=7;
		g_syIdempotence=9;
		g_syInvolutiveness=10;
		g_syIrreflexivity=3;
		g_syProjectivity=11;
		g_syReflexivity=2;
		g_sySethood=12;
		g_sySymmetry=1;
		g_syTransitivity=5;
	}
	public static final Struct_Pair f_mizAdjustAttr(Struct_MizCorrelEnv aenv, Struct_MizAttr aa) {
		final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coAttribute, (aa).f_attrNr);
		if (((l0_c).f_whichConstrNr!=0)) {
			return (new Struct_Pair((l0_c).f_whichConstrNr, Module_correl.f_mizTrmListDrop((aa).f_args, (l0_c).f_superfluous)));
		} else {
			return (new Struct_Pair((aa).f_attrNr, (aa).f_args));
		}
	}
	public static final Struct_Pair f_mizAdjustAttrFrm(Struct_MizCorrelEnv aenv, Struct_MizPredFrm af) {
		final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coAttribute, (af).f_predNr);
		if (((l0_c).f_whichConstrNr!=0)) {
			return (new Struct_Pair((l0_c).f_whichConstrNr, Module_correl.f_mizTrmListDrop((af).f_args, (l0_c).f_superfluous)));
		} else {
			return (new Struct_Pair((af).f_predNr, (af).f_args));
		}
	}
	public static final Struct_Pair f_mizAdjustFrm(Struct_MizCorrelEnv aenv, Struct_MizPredFrm af) {
		final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coPredicate, (af).f_predNr);
		if (((l0_c).f_whichConstrNr!=0)) {
			return (new Struct_Pair((l0_c).f_whichConstrNr, Module_correl.f_mizTrmListDrop((af).f_args, (l0_c).f_superfluous)));
		} else {
			return (new Struct_Pair((af).f_predNr, (af).f_args));
		}
	}
	public static final Struct_Pair f_mizAdjustTrm(Struct_MizCorrelEnv aenv, Struct_MizFuncTrm at) {
		final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coFunctor, (at).f_funcNr);
		if (((l0_c).f_whichConstrNr!=0)) {
			return (new Struct_Pair((l0_c).f_whichConstrNr, Module_correl.f_mizTrmListDrop((at).f_args, (l0_c).f_superfluous)));
		} else {
			return (new Struct_Pair((at).f_funcNr, (at).f_args));
		}
	}
	public static final Struct_Pair f_mizAdjustTyp(Struct_MizCorrelEnv aenv, Struct_MizTyp at) {
		Module_errhan.f_mizAssert(2148, (((at).f_sort==Module_lexicon.g_ikTypStruct)||((at).f_sort==Module_lexicon.g_ikTypMode)));
		if (((at).f_sort==Module_lexicon.g_ikTypStruct)) {
			return (new Struct_Pair((at).f_modNr, (at).f_modArgs));
		} else {
			final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coMode, (at).f_modNr);
			if (((l0_c).f_whichConstrNr!=0)) {
				return (new Struct_Pair((l0_c).f_whichConstrNr, Module_correl.f_mizTrmListDrop((at).f_modArgs, (l0_c).f_superfluous)));
			} else {
				return (new Struct_Pair((at).f_modNr, (at).f_modArgs));
			}
		}
	}
	public static final int f_mizAdjustedAttrNr(Struct_MizCorrelEnv aenv, Struct_MizAttr aa) {
		final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coAttribute, (aa).f_attrNr);
		if (((l0_c).f_whichConstrNr!=0)) {
			return (l0_c).f_whichConstrNr;
		} else {
			return (aa).f_attrNr;
		}
	}
	public static final Struct_MizChoiceTrm f_mizAsChoiceTrm(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
			final Struct_MizTyp l2___ = (l1__tmp).f_choiceTyp;
			final int l3___ = (l1__tmp).f_pattNr;
			final int l4___ = (l1__tmp).f_trmInfo;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2906);
			return (new Struct_MizChoiceTrm(Module_correl.f_mizNewIncorTyp(), 0, 0));
		}
		}
	}
	public static final Struct_MizChoiceTrm f_mizAsChoiceTrm2(Struct at) {
		return Module_correl.f_mizAsChoiceTrm(at);
	}
	public static final Struct_MizConjFrm f_mizAsConjFrm(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l2___ = (l1__tmp).f_conjuncts;
			final int l3___ = (l1__tmp).f_pattNr;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2912);
			return (new Struct_MizConjFrm(Module_vector.f_makeVector(0), 0));
		}
		}
	}
	public static final Struct_MizFlexFrm f_mizAsFlexFrm(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l1__tmp = (Struct_MizFlexFrm)l0__tmp;
			final int l2___ = (l1__tmp).f_sort;
			final Struct l3___ = (l1__tmp).f_leftOrig;
			final Struct l4___ = (l1__tmp).f_rightOrig;
			final Struct l5___ = (l1__tmp).f_leftTrm;
			final Struct l6___ = (l1__tmp).f_rightTrm;
			final Struct l7___ = (l1__tmp).f_expansion;
			final int l8___ = (l1__tmp).f_pattNr;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2914);
			return (new Struct_MizFlexFrm(Module_lexicon.g_ikError, (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
		}
		}
	}
	public static final Struct_MizFraenkelTrm f_mizAsFraenkelTrm(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l1__tmp = (Struct_MizFraenkelTrm)l0__tmp;
			final Struct_Vector l2___ = (l1__tmp).f_lambdaArgs;
			final Struct l3___ = (l1__tmp).f_scope;
			final Struct l4___ = (l1__tmp).f_compr;
			final Struct_MizIntSeq l5___ = (l1__tmp).f_idents;
			final int l6___ = (l1__tmp).f_pattNr;
			final int l7___ = (l1__tmp).f_trmInfo;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2904);
			return (new Struct_MizFraenkelTrm(Module_vector.f_makeVector(0), (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), Module_mobjects.f_mizInitIntSeq(0), 0, 0));
		}
		}
	}
	public static final Struct_MizFraenkelTrm f_mizAsFraenkelTrm2(Struct at) {
		return Module_correl.f_mizAsFraenkelTrm(at);
	}
	public static final Struct_MizFuncTrm f_mizAsFuncTrm(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l2___ = (l1__tmp).f_sort;
			final int l3___ = (l1__tmp).f_funcNr;
			final Struct l4___ = (l1__tmp).f_args;
			final int l5___ = (l1__tmp).f_pattNr;
			final int l6___ = (l1__tmp).f_trmInfo;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2902);
			return (new Struct_MizFuncTrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0, 0));
		}
		}
	}
	public static final Struct_MizFuncTrm f_mizAsFuncTrm2(Struct at) {
		return Module_correl.f_mizAsFuncTrm(at);
	}
	public static final Struct_MizLocPredFrm f_mizAsLocPredFrm(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 49/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l1__tmp = (Struct_MizLocPredFrm)l0__tmp;
			final int l2___ = (l1__tmp).f_predNr;
			final Struct l3___ = (l1__tmp).f_args;
			final Struct l4___ = (l1__tmp).f_predExp;
			final int l5___ = (l1__tmp).f_pattNr;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2909);
			return (new Struct_MizLocPredFrm(0, ((Struct)SingletonStructs.str_MizTrmNil), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
		}
		}
	}
	public static final Struct_MizNegFrm f_mizAsNegFrm(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 51/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l2___ = (l1__tmp).f_arg;
			final int l3___ = (l1__tmp).f_pattNr;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2911);
			return (new Struct_MizNegFrm((new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
		}
		}
	}
	public static final Struct_MizPredFrm f_mizAsPredFrm(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 56/*MizPredFrm*/: {
			final Struct_MizPredFrm l1__tmp = (Struct_MizPredFrm)l0__tmp;
			final int l2___ = (l1__tmp).f_sort;
			final int l3___ = (l1__tmp).f_predNr;
			final Struct l4___ = (l1__tmp).f_args;
			final int l5___ = (l1__tmp).f_pattNr;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2908);
			return (new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
		}
		}
	}
	public static final Struct_MizPrivFuncTrm f_mizAsPrivFuncTrm(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 59/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final int l2___ = (l1__tmp).f_funcNr;
			final Struct l3___ = (l1__tmp).f_args;
			final Struct l4___ = (l1__tmp).f_funcExp;
			final int l5___ = (l1__tmp).f_pattNr;
			final int l6___ = (l1__tmp).f_trmInfo;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2903);
			return (new Struct_MizPrivFuncTrm(0, ((Struct)SingletonStructs.str_MizTrmNil), (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), 0, 0));
		}
		}
	}
	public static final Struct_MizPrivFuncTrm f_mizAsPrivFuncTrm2(Struct at) {
		return Module_correl.f_mizAsPrivFuncTrm(at);
	}
	public static final Struct_MizQuaTrm f_mizAsQuaTrm(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 60/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
			final Struct l2___ = (l1__tmp).f_trmProper;
			final Struct_MizTyp l3___ = (l1__tmp).f_qua;
			final int l4___ = (l1__tmp).f_pattNr;
			final int l5___ = (l1__tmp).f_trmInfo;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2905);
			return (new Struct_MizQuaTrm((new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), Module_correl.f_mizNewIncorTyp(), 0, 0));
		}
		}
	}
	public static final Struct_MizQuaTrm f_mizAsQuaTrm2(Struct at) {
		return Module_correl.f_mizAsQuaTrm(at);
	}
	public static final Struct_MizQualFrm f_mizAsQualFrm(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 61/*MizQualFrm*/: {
			final Struct_MizQualFrm l1__tmp = (Struct_MizQualFrm)l0__tmp;
			final Struct l2___ = (l1__tmp).f_trm;
			final Struct_MizTyp l3___ = (l1__tmp).f_typ;
			final int l4___ = (l1__tmp).f_pattNr;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2910);
			return (new Struct_MizQualFrm((new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), Module_correl.f_mizNewIncorTyp(), 0));
		}
		}
	}
	public static final Struct_MizTrmElem f_mizAsTrmElem(Struct al) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2___ = (l1__tmp).f_trm;
			final Struct l3___ = (l1__tmp).f_next;
			return l1__tmp;
		}
		case 69/*MizTrmNil*/: {
			Module_errhan.f_mizRunTimeError(2907);
			return (new Struct_MizTrmElem((new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), ((Struct)SingletonStructs.str_MizTrmNil)));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct_MizUnivFrm f_mizAsUnivFrm(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 74/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l1__tmp = (Struct_MizUnivFrm)l0__tmp;
			final int l2___ = (l1__tmp).f_sort;
			final int l3___ = (l1__tmp).f_varId;
			final Struct_MizTyp l4___ = (l1__tmp).f_quantified;
			final Struct l5___ = (l1__tmp).f_scope;
			final int l6___ = (l1__tmp).f_pattNr;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2913);
			return (new Struct_MizUnivFrm(Module_lexicon.g_ikError, 0, Module_correl.f_mizNewIncorTyp(), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
		}
		}
	}
	public static final Struct_MizVarTrm f_mizAsVarTrm(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 75/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2___ = (l1__tmp).f_sort;
			final int l3___ = (l1__tmp).f_varNr;
			final int l4___ = (l1__tmp).f_pattNr;
			final int l5___ = (l1__tmp).f_trmInfo;
			return l1__tmp;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2901);
			return (new Struct_MizVarTrm(Module_lexicon.g_ikError, 0, 0, 0));
		}
		}
	}
	public static final Struct_MizVarTrm f_mizAsVarTrm2(Struct at) {
		return Module_correl.f_mizAsVarTrm(at);
	}
	public static final Object f_mizAttrCollInsert(Struct_MizCorrelEnv aenv, Struct_MizAttrColl acoll, Struct_MizAttr aa) {
		if ((acoll).f_consistent) {
			final Struct_Pair l0_p = Module_correl.f_mizAttrCollSearch(aenv, acoll, aa);
			if (((boolean)(l0_p).f_first)) {
				final Struct_MizAttr l1_ex = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((acoll).f_items, ((int)(l0_p).f_second)));
				if (((l1_ex).f_neg!=(aa).f_neg)) {
					Module_vector.f_clearVector((acoll).f_items);
					((Field_consistent)acoll).set_consistent(false);
					return null;
				} else {
					return null;
				}
			} else {
				return Module_mobjects.f_mizVectorAtInsert((acoll).f_items, ((int)(l0_p).f_second), aa);
			}
		} else {
			return null;
		}
	}
	public static final Struct_Pair f_mizAttrCollSearch(Struct_MizCorrelEnv aenv, Struct_MizAttrColl acoll, Struct_MizAttr aa) {
		final Reference<Boolean> l0_found = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l1_l = ((Reference<Integer>)(new Reference(0)));
		final Reference<Integer> l2_h = ((Reference<Integer>)(new Reference((Module_vector.f_sizeVector((acoll).f_items)-1))));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return (((int)l1_l.value)<=((int)l2_h.value));
		};
		final Struct_MizCorrelEnv l7_env = aenv;
		final Struct_MizAttrColl l8_coll = acoll;
		final Struct_MizAttr l9_a = aa;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			final int l7_i = ((((int)l1_l.value)+((int)l2_h.value))/2);
			final int l8_c = Module_correl.f_mizCompAbsAttr(l7_env, ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l8_coll).f_items, l7_i)), l9_a);
			if ((l8_c<0)) {
				l1_l.value = ((Integer)(l7_i+1));
				return null;
			} else {
				l2_h.value = ((Integer)(l7_i-1));
				if ((l8_c==0)) {
					l0_found.value = ((Boolean)true);
					l1_l.value = ((Integer)l7_i);
					l2_h.value = ((Integer)(l7_i-1));
					return null;
				} else {
					return null;
				}
			}
		};
		Module_loop.f_while(l5_$3, l6_$4);
		return (new Struct_Pair(l0_found.value, l1_l.value));
	}
	public static final Struct_MizTyp f_mizBoundVar(Struct_MizCorrelEnv aenv, int an) {
		return ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((aenv).f_boundVar, an));
	}
	public static final int f_mizCmpInt(int aa, int ab) {
		if ((aa<ab)) {
			return (-1);
		} else {
			if ((aa>ab)) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	public static final int f_mizCompAbsAttr(Struct_MizCorrelEnv aenv, Struct_MizAttr aa, Struct_MizAttr ab) {
		final Struct_Pair l0_pa = Module_correl.f_mizAdjustAttr(aenv, aa);
		final Struct_Pair l1_pb = Module_correl.f_mizAdjustAttr(aenv, ab);
		final int l2_c = Module_correl.f_mizCmpInt(((int)(l0_pa).f_first), ((int)(l1_pb).f_first));
		if ((l2_c!=0)) {
			return l2_c;
		} else {
			return Module_correl.f_mizCompTrmLists(aenv, ((Struct)(l0_pa).f_second), ((Struct)(l1_pb).f_second));
		}
	}
	public static final int f_mizCompTrmLists(Struct_MizCorrelEnv aenv, Struct aa, Struct ab) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = aa;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			Struct l2__tmp = ab;
			switch (l2__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return 0;
			}
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l3__tmp = (Struct_MizTrmElem)l2__tmp;
				final Struct l4___ = (l3__tmp).f_trm;
				final Struct l5___ = (l3__tmp).f_next;
				return (-1);
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
			}
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l6_ta = l1__tmp.f_trm;
			final Struct l7_na = l1__tmp.f_next;
			final Struct l8_ta = (l1__tmp).f_trm;
			final Struct l9_na = (l1__tmp).f_next;
			Struct l10__tmp = ab;
			switch (l10__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return 1;
			}
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l11__tmp = (Struct_MizTrmElem)l10__tmp;
				final Struct l12_tb = l11__tmp.f_trm;
				final Struct l13_nb = l11__tmp.f_next;
				final Struct l14_tb = (l11__tmp).f_trm;
				final Struct l15_nb = (l11__tmp).f_next;
				final int l16_c = Module_correl.f_mizCompTrms(aenv, l8_ta, l14_tb);
				if ((l16_c!=0)) {
					return l16_c;
				} else {
					{
						final Struct l17___tmp = l9_na;
						final Struct l18___tmp = l15_nb;
						aa = l17___tmp;
						ab = l18___tmp;
						continue TAIL_CALL;
					}
				}
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	}
	public static final int f_mizCompTrms(Struct_MizCorrelEnv aenv, Struct ata, Struct atb) {
		Struct l0_a;
		if ((aenv).f_strictCompare) {
			l0_a=ata;
		} else {
			l0_a=Module_correl.f_mizSkipPrivFunc(ata);
		}
		Struct l1_b;
		if ((aenv).f_strictCompare) {
			l1_b=atb;
		} else {
			l1_b=Module_correl.f_mizSkipPrivFunc(atb);
		}
		final int l2_c0 = Module_correl.f_mizCmpInt(Module_correl.f_mizTrmSort(l0_a), Module_correl.f_mizTrmSort(l1_b));
		if ((l2_c0!=0)) {
			return l2_c0;
		} else {
			Struct l3__tmp = l0_a;
			switch (l3__tmp.getTypeId()) {
			case 75/*MizVarTrm*/: {
				final Struct_MizVarTrm l4__tmp = (Struct_MizVarTrm)l3__tmp;
				final int l5_varNr = l4__tmp.f_varNr;
				final int l6___ = (l4__tmp).f_sort;
				final int l7_varNr = (l4__tmp).f_varNr;
				final int l8___ = (l4__tmp).f_pattNr;
				final int l9___ = (l4__tmp).f_trmInfo;
				final Struct_MizVarTrm l10_vb = Module_correl.f_mizAsVarTrm2(l1_b);
				return Module_correl.f_mizCmpInt(l7_varNr, (l10_vb).f_varNr);
			}
			case 45/*MizFuncTrm*/: {
				final Struct_MizFuncTrm l4__tmp = (Struct_MizFuncTrm)l3__tmp;
				final int l11_srt = l4__tmp.f_sort;
				final int l12_funcNr = l4__tmp.f_funcNr;
				final Struct l13_args = l4__tmp.f_args;
				final int l14_srt = (l4__tmp).f_sort;
				final int l15_funcNr = (l4__tmp).f_funcNr;
				final Struct l16_args = (l4__tmp).f_args;
				final int l17___ = (l4__tmp).f_pattNr;
				final int l18___ = (l4__tmp).f_trmInfo;
				final Struct_MizFuncTrm l19_fb = Module_correl.f_mizAsFuncTrm2(l1_b);
				if (((l14_srt!=Module_lexicon.g_ikTrmFunctor)||(aenv).f_strictCompare)) {
					final int l20_c = Module_correl.f_mizCmpInt(l15_funcNr, (l19_fb).f_funcNr);
					if ((l20_c!=0)) {
						return l20_c;
					} else {
						return Module_correl.f_mizCompTrmLists(aenv, l16_args, (l19_fb).f_args);
					}
				} else {
					final Struct_MizFuncTrm l21_fa2 = Module_correl.f_mizAsFuncTrm2(l4__tmp);
					final Struct_Pair l22_pa = Module_correl.f_mizAdjustTrm(aenv, l21_fa2);
					final Struct_Pair l23_pb = Module_correl.f_mizAdjustTrm(aenv, l19_fb);
					final int l24_c = Module_correl.f_mizCmpInt(((int)(l22_pa).f_first), ((int)(l23_pb).f_first));
					if ((l24_c!=0)) {
						return l24_c;
					} else {
						return Module_correl.f_mizCompTrmLists(aenv, ((Struct)(l22_pa).f_second), ((Struct)(l23_pb).f_second));
					}
				}
			}
			case 59/*MizPrivFuncTrm*/: {
				final Struct_MizPrivFuncTrm l4__tmp = (Struct_MizPrivFuncTrm)l3__tmp;
				final int l25_funcNr = l4__tmp.f_funcNr;
				final Struct l26_args = l4__tmp.f_args;
				final int l27_funcNr = (l4__tmp).f_funcNr;
				final Struct l28_args = (l4__tmp).f_args;
				final Struct l29___ = (l4__tmp).f_funcExp;
				final int l30___ = (l4__tmp).f_pattNr;
				final int l31___ = (l4__tmp).f_trmInfo;
				final Struct_MizPrivFuncTrm l32_pb2 = Module_correl.f_mizAsPrivFuncTrm2(l1_b);
				final int l33_c = Module_correl.f_mizCmpInt(l27_funcNr, (l32_pb2).f_funcNr);
				if ((l33_c!=0)) {
					return l33_c;
				} else {
					return Module_correl.f_mizCompTrmLists(aenv, l28_args, (l32_pb2).f_args);
				}
			}
			case 42/*MizFraenkelTrm*/: {
				final Struct_MizFraenkelTrm l4__tmp = (Struct_MizFraenkelTrm)l3__tmp;
				final Struct_Vector l34_lambdaArgs = l4__tmp.f_lambdaArgs;
				final Struct l35_scope = l4__tmp.f_scope;
				final Struct l36_compr = l4__tmp.f_compr;
				final Struct_Vector l37_lambdaArgs = (l4__tmp).f_lambdaArgs;
				final Struct l38_scope = (l4__tmp).f_scope;
				final Struct l39_compr = (l4__tmp).f_compr;
				final Struct_MizIntSeq l40___ = (l4__tmp).f_idents;
				final int l41___ = (l4__tmp).f_pattNr;
				final int l42___ = (l4__tmp).f_trmInfo;
				final Struct_MizFraenkelTrm l43_kb = Module_correl.f_mizAsFraenkelTrm2(l1_b);
				final int l44_c = Module_correl.f_mizCompTrms(aenv, l38_scope, (l43_kb).f_scope);
				if ((l44_c!=0)) {
					return l44_c;
				} else {
					final int l45_c2 = Module_correl.f_mizCompareFrms(l39_compr, (l43_kb).f_compr);
					if ((l45_c2!=0)) {
						return l45_c2;
					} else {
						final int l46_c3 = Module_correl.f_mizCmpInt(Module_vector.f_sizeVector(l37_lambdaArgs), Module_vector.f_sizeVector((l43_kb).f_lambdaArgs));
						if ((l46_c3!=0)) {
							return l46_c3;
						} else {
							final Reference<Integer> l47_res = ((Reference<Integer>)(new Reference(0)));
							final Reference<Integer> l48_i = ((Reference<Integer>)(new Reference(0)));
							final Func0<Boolean> l51_$49 = (Func0<Boolean>)() -> {
								return ((Boolean)((((int)l48_i.value)<Module_vector.f_sizeVector(l37_lambdaArgs))&&(((int)l47_res.value)==0)));
							};
							final Func0<Object> l52_$50 = (Func0<Object>)() -> {
								l47_res.value = ((Integer)Module_correl.f_mizCompareTyps(((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l37_lambdaArgs, ((int)l48_i.value))), ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l43_kb).f_lambdaArgs, ((int)l48_i.value)))));
								l48_i.value = ((Integer)(((int)l48_i.value)+1));
								return null;
							};
							Module_loop.f_while(l51_$49, l52_$50);
							return ((int)l47_res.value);
						}
					}
				}
			}
			case 21/*MizChoiceTrm*/: {
				final Struct_MizChoiceTrm l4__tmp = (Struct_MizChoiceTrm)l3__tmp;
				final Struct_MizTyp l53_typ = l4__tmp.f_choiceTyp;
				final Struct_MizTyp l54_typ = (l4__tmp).f_choiceTyp;
				final int l55___ = (l4__tmp).f_pattNr;
				final int l56___ = (l4__tmp).f_trmInfo;
				final Struct_MizChoiceTrm l57_hb = Module_correl.f_mizAsChoiceTrm2(l1_b);
				return Module_correl.f_mizCompareTyps(l54_typ, (l57_hb).f_choiceTyp);
			}
			case 60/*MizQuaTrm*/: {
				final Struct_MizQuaTrm l4__tmp = (Struct_MizQuaTrm)l3__tmp;
				final Struct l58_trmProper = l4__tmp.f_trmProper;
				final Struct_MizTyp l59_qua = l4__tmp.f_qua;
				final Struct l60_trmProper = (l4__tmp).f_trmProper;
				final Struct_MizTyp l61_qua = (l4__tmp).f_qua;
				final int l62___ = (l4__tmp).f_pattNr;
				final int l63___ = (l4__tmp).f_trmInfo;
				final Struct_MizQuaTrm l64_qb = Module_correl.f_mizAsQuaTrm2(l1_b);
				final int l65_c = Module_correl.f_mizCompTrms(aenv, l60_trmProper, (l64_qb).f_trmProper);
				if ((l65_c!=0)) {
					return l65_c;
				} else {
					return Module_correl.f_mizCompareTyps(l61_qua, (l64_qb).f_qua);
				}
			}
			case 16/*MizBaseTrm*/: {
				final Struct_MizBaseTrm l4__tmp = (Struct_MizBaseTrm)l3__tmp;
				final int l66_srt = l4__tmp.f_sort;
				final int l67_srt = (l4__tmp).f_sort;
				final int l68___ = (l4__tmp).f_trmInfo;
				return 0;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l3__tmp.getTypeName());
			}
		}
	}
	public static final int f_mizCompareClusters(Struct_MizAttrColl aa, Struct_MizAttrColl ab) {
		final int l0_c = Module_correl.f_mizCmpInt(Module_vector.f_sizeVector((aa).f_items), Module_vector.f_sizeVector((ab).f_items));
		if ((l0_c!=0)) {
			return l0_c;
		} else {
			final Reference<Integer> l1_res = ((Reference<Integer>)(new Reference(0)));
			final Reference<Integer> l2_i = ((Reference<Integer>)(new Reference(0)));
			final int l3_n = Module_vector.f_sizeVector((aa).f_items);
			final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l2_i.value)<l3_n)&&(((int)l1_res.value)==0)));
			};
			final Struct_MizAttrColl l8_a = aa;
			final Struct_MizAttrColl l9_b = ab;
			final Func0<Object> l7_$5 = (Func0<Object>)() -> {
				final Struct_MizAttr l8_x = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l8_a).f_items, ((int)l2_i.value)));
				final Struct_MizAttr l9_y = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l9_b).f_items, ((int)l2_i.value)));
				final int l10_c1 = Module_correl.f_mizCmpInt((l8_x).f_attrNr, (l9_y).f_attrNr);
				if ((l10_c1!=0)) {
					l1_res.value = ((Integer)l10_c1);
				} else {
					final int l11_c2 = Module_correl.f_mizCmpInt((l8_x).f_neg, (l9_y).f_neg);
					if ((l11_c2!=0)) {
						l1_res.value = ((Integer)l11_c2);
					} else {
						l1_res.value = ((Integer)Module_correl.f_mizCompareTrmLists((l8_x).f_args, (l9_y).f_args));
					}
				}
				l2_i.value = ((Integer)(((int)l2_i.value)+1));
				return null;
			};
			Module_loop.f_while(l6_$4, l7_$5);
			return ((int)l1_res.value);
		}
	}
	public static final int f_mizCompareFrms(Struct afa, Struct afb) {
		TAIL_CALL: for(;;) {
		final Struct l0_a = Module_correl.f_mizSkipLocPred(afa);
		final int l1_c0 = Module_correl.f_mizCmpInt(Module_correl.f_mizFrmSort(l0_a), Module_correl.f_mizFrmSort(afb));
		if ((l1_c0!=0)) {
			return l1_c0;
		} else {
			Struct l2__tmp = l0_a;
			switch (l2__tmp.getTypeId()) {
			case 73/*MizUniqFrm*/: {
				final Struct_MizUniqFrm l3__tmp = (Struct_MizUniqFrm)l2__tmp;
				final int l4___ = (l3__tmp).f_sort;
				return 0;
			}
			case 51/*MizNegFrm*/: {
				final Struct_MizNegFrm l3__tmp = (Struct_MizNegFrm)l2__tmp;
				final Struct l5_arg = l3__tmp.f_arg;
				final Struct l6_arg = (l3__tmp).f_arg;
				final int l7___ = (l3__tmp).f_pattNr;
				final Struct_MizNegFrm l8_nb = Module_correl.f_mizAsNegFrm(afb);
				{
					final Struct l9___tmp = l6_arg;
					final Struct l10___tmp = (l8_nb).f_arg;
					afa = l9___tmp;
					afb = l10___tmp;
					continue TAIL_CALL;
				}
			}
			case 61/*MizQualFrm*/: {
				final Struct_MizQualFrm l3__tmp = (Struct_MizQualFrm)l2__tmp;
				final Struct l11_trm = l3__tmp.f_trm;
				final Struct_MizTyp l12_typ = l3__tmp.f_typ;
				final Struct l13_trm = (l3__tmp).f_trm;
				final Struct_MizTyp l14_typ = (l3__tmp).f_typ;
				final int l15___ = (l3__tmp).f_pattNr;
				final Struct_MizQualFrm l16_qb = Module_correl.f_mizAsQualFrm(afb);
				final int l17_c = Module_correl.f_mizCompareTrms(l13_trm, (l16_qb).f_trm);
				if ((l17_c!=0)) {
					return l17_c;
				} else {
					return Module_correl.f_mizCompareTyps(l14_typ, (l16_qb).f_typ);
				}
			}
			case 26/*MizConjFrm*/: {
				final Struct_MizConjFrm l3__tmp = (Struct_MizConjFrm)l2__tmp;
				final Struct_Vector l18_conjuncts = l3__tmp.f_conjuncts;
				final Struct_Vector l19_conjuncts = (l3__tmp).f_conjuncts;
				final int l20___ = (l3__tmp).f_pattNr;
				final Struct_MizConjFrm l21_cb = Module_correl.f_mizAsConjFrm(afb);
				final int l22_c = Module_correl.f_mizCmpInt(Module_vector.f_sizeVector(l19_conjuncts), Module_vector.f_sizeVector((l21_cb).f_conjuncts));
				if ((l22_c!=0)) {
					return l22_c;
				} else {
					final Reference<Integer> l23_res = ((Reference<Integer>)(new Reference(0)));
					final Reference<Integer> l24_i = ((Reference<Integer>)(new Reference(0)));
					final Func0<Boolean> l27_$25 = (Func0<Boolean>)() -> {
						return ((Boolean)((((int)l24_i.value)<Module_vector.f_sizeVector(l19_conjuncts))&&(((int)l23_res.value)==0)));
					};
					final Func0<Object> l28_$26 = (Func0<Object>)() -> {
						l23_res.value = ((Integer)Module_correl.f_mizCompareFrms(((Struct)Module_vector.f_getVectorUnsafe(l19_conjuncts, ((int)l24_i.value))), ((Struct)Module_vector.f_getVectorUnsafe((l21_cb).f_conjuncts, ((int)l24_i.value)))));
						l24_i.value = ((Integer)(((int)l24_i.value)+1));
						return null;
					};
					Module_loop.f_while(l27_$25, l28_$26);
					return ((int)l23_res.value);
				}
			}
			case 56/*MizPredFrm*/: {
				final Struct_MizPredFrm l3__tmp = (Struct_MizPredFrm)l2__tmp;
				final int l29_predNr = l3__tmp.f_predNr;
				final Struct l30_args = l3__tmp.f_args;
				final int l31___ = (l3__tmp).f_sort;
				final int l32_predNr = (l3__tmp).f_predNr;
				final Struct l33_args = (l3__tmp).f_args;
				final int l34___ = (l3__tmp).f_pattNr;
				final Struct_MizPredFrm l35_pb = Module_correl.f_mizAsPredFrm(afb);
				final int l36_c = Module_correl.f_mizCmpInt(l32_predNr, (l35_pb).f_predNr);
				if ((l36_c!=0)) {
					return l36_c;
				} else {
					return Module_correl.f_mizCompareTrmLists(l33_args, (l35_pb).f_args);
				}
			}
			case 74/*MizUnivFrm*/: {
				final Struct_MizUnivFrm l3__tmp = (Struct_MizUnivFrm)l2__tmp;
				final Struct_MizTyp l37_quantified = l3__tmp.f_quantified;
				final Struct l38_scope = l3__tmp.f_scope;
				final int l39___ = (l3__tmp).f_sort;
				final int l40___ = (l3__tmp).f_varId;
				final Struct_MizTyp l41_quantified = (l3__tmp).f_quantified;
				final Struct l42_scope = (l3__tmp).f_scope;
				final int l43___ = (l3__tmp).f_pattNr;
				final Struct_MizUnivFrm l44_ub = Module_correl.f_mizAsUnivFrm(afb);
				final int l45_c = Module_correl.f_mizCompareTyps(l41_quantified, (l44_ub).f_quantified);
				if ((l45_c!=0)) {
					return l45_c;
				} else {
					{
						final Struct l46___tmp = l42_scope;
						final Struct l47___tmp = (l44_ub).f_scope;
						afa = l46___tmp;
						afb = l47___tmp;
						continue TAIL_CALL;
					}
				}
			}
			default: {
				Module_errhan.f_mizRunTimeError(2049);
				return 0;
			}
			}
		}
	}
	}
	public static final int f_mizCompareTrmLists(Struct aa, Struct ab) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = aa;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			Struct l2__tmp = ab;
			switch (l2__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return 0;
			}
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l3__tmp = (Struct_MizTrmElem)l2__tmp;
				final Struct l4___ = (l3__tmp).f_trm;
				final Struct l5___ = (l3__tmp).f_next;
				return (-1);
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
			}
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l6_ta = l1__tmp.f_trm;
			final Struct l7_na = l1__tmp.f_next;
			final Struct l8_ta = (l1__tmp).f_trm;
			final Struct l9_na = (l1__tmp).f_next;
			Struct l10__tmp = ab;
			switch (l10__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return 1;
			}
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l11__tmp = (Struct_MizTrmElem)l10__tmp;
				final Struct l12_tb = l11__tmp.f_trm;
				final Struct l13_nb = l11__tmp.f_next;
				final Struct l14_tb = (l11__tmp).f_trm;
				final Struct l15_nb = (l11__tmp).f_next;
				final int l16_c = Module_correl.f_mizCompareTrms(l8_ta, l14_tb);
				if ((l16_c!=0)) {
					return l16_c;
				} else {
					{
						final Struct l17___tmp = l9_na;
						final Struct l18___tmp = l15_nb;
						aa = l17___tmp;
						ab = l18___tmp;
						continue TAIL_CALL;
					}
				}
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	}
	public static final int f_mizCompareTrms(Struct ata, Struct atb) {
		final int l0_c0 = Module_correl.f_mizCmpInt(Module_correl.f_mizSizeOfTrm(ata), Module_correl.f_mizSizeOfTrm(atb));
		if ((l0_c0!=0)) {
			return l0_c0;
		} else {
			final Struct l1_a = Module_correl.f_mizSkipPrivFunc(ata);
			final Struct l2_b = Module_correl.f_mizSkipPrivFunc(atb);
			final int l3_c1 = Module_correl.f_mizCmpInt(Module_correl.f_mizTrmSort(l1_a), Module_correl.f_mizTrmSort(l2_b));
			if ((l3_c1!=0)) {
				return l3_c1;
			} else {
				Struct l4__tmp = l1_a;
				switch (l4__tmp.getTypeId()) {
				case 75/*MizVarTrm*/: {
					final Struct_MizVarTrm l5__tmp = (Struct_MizVarTrm)l4__tmp;
					final int l6_varNr = l5__tmp.f_varNr;
					final int l7___ = (l5__tmp).f_sort;
					final int l8_varNr = (l5__tmp).f_varNr;
					final int l9___ = (l5__tmp).f_pattNr;
					final int l10___ = (l5__tmp).f_trmInfo;
					final Struct_MizVarTrm l11_vb = Module_correl.f_mizAsVarTrm(l2_b);
					return Module_correl.f_mizCmpInt(l8_varNr, (l11_vb).f_varNr);
				}
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l5__tmp = (Struct_MizFuncTrm)l4__tmp;
					final int l12_funcNr = l5__tmp.f_funcNr;
					final Struct l13_args = l5__tmp.f_args;
					final int l14___ = (l5__tmp).f_sort;
					final int l15_funcNr = (l5__tmp).f_funcNr;
					final Struct l16_args = (l5__tmp).f_args;
					final int l17___ = (l5__tmp).f_pattNr;
					final int l18___ = (l5__tmp).f_trmInfo;
					final Struct_MizFuncTrm l19_fb = Module_correl.f_mizAsFuncTrm(l2_b);
					final int l20_c = Module_correl.f_mizCmpInt(l15_funcNr, (l19_fb).f_funcNr);
					if ((l20_c!=0)) {
						return l20_c;
					} else {
						return Module_correl.f_mizCompareTrmLists(l16_args, (l19_fb).f_args);
					}
				}
				case 59/*MizPrivFuncTrm*/: {
					final Struct_MizPrivFuncTrm l5__tmp = (Struct_MizPrivFuncTrm)l4__tmp;
					final int l21_funcNr = l5__tmp.f_funcNr;
					final Struct l22_args = l5__tmp.f_args;
					final int l23_funcNr = (l5__tmp).f_funcNr;
					final Struct l24_args = (l5__tmp).f_args;
					final Struct l25___ = (l5__tmp).f_funcExp;
					final int l26___ = (l5__tmp).f_pattNr;
					final int l27___ = (l5__tmp).f_trmInfo;
					final Struct_MizPrivFuncTrm l28_pb = Module_correl.f_mizAsPrivFuncTrm(l2_b);
					final int l29_c = Module_correl.f_mizCmpInt(l23_funcNr, (l28_pb).f_funcNr);
					if ((l29_c!=0)) {
						return l29_c;
					} else {
						return Module_correl.f_mizCompareTrmLists(l24_args, (l28_pb).f_args);
					}
				}
				case 42/*MizFraenkelTrm*/: {
					final Struct_MizFraenkelTrm l5__tmp = (Struct_MizFraenkelTrm)l4__tmp;
					final Struct_Vector l30_lambdaArgs = l5__tmp.f_lambdaArgs;
					final Struct l31_scope = l5__tmp.f_scope;
					final Struct l32_compr = l5__tmp.f_compr;
					final Struct_Vector l33_lambdaArgs = (l5__tmp).f_lambdaArgs;
					final Struct l34_scope = (l5__tmp).f_scope;
					final Struct l35_compr = (l5__tmp).f_compr;
					final Struct_MizIntSeq l36___ = (l5__tmp).f_idents;
					final int l37___ = (l5__tmp).f_pattNr;
					final int l38___ = (l5__tmp).f_trmInfo;
					final Struct_MizFraenkelTrm l39_kb = Module_correl.f_mizAsFraenkelTrm(l2_b);
					final int l40_c = Module_correl.f_mizCompareTrms(l34_scope, (l39_kb).f_scope);
					if ((l40_c!=0)) {
						return l40_c;
					} else {
						final int l41_c2 = Module_correl.f_mizCompareFrms(l35_compr, (l39_kb).f_compr);
						if ((l41_c2!=0)) {
							return l41_c2;
						} else {
							return Module_correl.f_mizCompareTypVectors(l33_lambdaArgs, (l39_kb).f_lambdaArgs);
						}
					}
				}
				case 21/*MizChoiceTrm*/: {
					final Struct_MizChoiceTrm l5__tmp = (Struct_MizChoiceTrm)l4__tmp;
					final Struct_MizTyp l42_typ = l5__tmp.f_choiceTyp;
					final Struct_MizTyp l43_typ = (l5__tmp).f_choiceTyp;
					final int l44___ = (l5__tmp).f_pattNr;
					final int l45___ = (l5__tmp).f_trmInfo;
					final Struct_MizChoiceTrm l46_hb = Module_correl.f_mizAsChoiceTrm(l2_b);
					return Module_correl.f_mizCompareTyps(l43_typ, (l46_hb).f_choiceTyp);
				}
				case 60/*MizQuaTrm*/: {
					final Struct_MizQuaTrm l5__tmp = (Struct_MizQuaTrm)l4__tmp;
					final Struct l47_trmProper = l5__tmp.f_trmProper;
					final Struct_MizTyp l48_qua = l5__tmp.f_qua;
					final Struct l49_trmProper = (l5__tmp).f_trmProper;
					final Struct_MizTyp l50_qua = (l5__tmp).f_qua;
					final int l51___ = (l5__tmp).f_pattNr;
					final int l52___ = (l5__tmp).f_trmInfo;
					final Struct_MizQuaTrm l53_qb = Module_correl.f_mizAsQuaTrm(l2_b);
					final int l54_c = Module_correl.f_mizCompareTrms(l49_trmProper, (l53_qb).f_trmProper);
					if ((l54_c!=0)) {
						return l54_c;
					} else {
						return Module_correl.f_mizCompareTyps(l50_qua, (l53_qb).f_qua);
					}
				}
				case 16/*MizBaseTrm*/: {
					final Struct_MizBaseTrm l5__tmp = (Struct_MizBaseTrm)l4__tmp;
					final int l55_srt = l5__tmp.f_sort;
					final int l56_srt = (l5__tmp).f_sort;
					final int l57___ = (l5__tmp).f_trmInfo;
					if ((l56_srt==Module_lexicon.g_ikTrmIt)) {
						return 0;
					} else {
						Module_errhan.f_mizRunTimeError(2341);
						return 0;
					}
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l4__tmp.getTypeName());
				}
			}
		}
	}
	public static final int f_mizCompareTypVectors(Struct_Vector aa, Struct_Vector ab) {
		final int l0_c = Module_correl.f_mizCmpInt(Module_vector.f_sizeVector(aa), Module_vector.f_sizeVector(ab));
		if ((l0_c!=0)) {
			return l0_c;
		} else {
			final Reference<Integer> l1_res = ((Reference<Integer>)(new Reference(0)));
			final Reference<Integer> l2_i = ((Reference<Integer>)(new Reference(0)));
			final Struct_Vector l6_a = aa;
			final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l2_i.value)<Module_vector.f_sizeVector(l6_a))&&(((int)l1_res.value)==0)));
			};
			final Struct_Vector l8_a = aa;
			final Struct_Vector l9_b = ab;
			final Func0<Object> l7_$4 = (Func0<Object>)() -> {
				l1_res.value = ((Integer)Module_correl.f_mizCompareTyps(((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l8_a, ((int)l2_i.value))), ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l9_b, ((int)l2_i.value)))));
				l2_i.value = ((Integer)(((int)l2_i.value)+1));
				return null;
			};
			Module_loop.f_while(l5_$3, l7_$4);
			return ((int)l1_res.value);
		}
	}
	public static final int f_mizCompareTyps(Struct_MizTyp aa, Struct_MizTyp ab) {
		final int l0_c1 = Module_correl.f_mizCmpInt((aa).f_sort, (ab).f_sort);
		if ((l0_c1!=0)) {
			return l0_c1;
		} else {
			final int l1_c2 = Module_correl.f_mizCmpInt((aa).f_modNr, (ab).f_modNr);
			if ((l1_c2!=0)) {
				return l1_c2;
			} else {
				final int l2_c3 = Module_correl.f_mizCompareClusters((aa).f_lower, (ab).f_lower);
				if ((l2_c3!=0)) {
					return l2_c3;
				} else {
					return Module_correl.f_mizCompareTrmLists((aa).f_modArgs, (ab).f_modArgs);
				}
			}
		}
	}
	public static final Struct_MizConstr f_mizConstr(Struct_MizCorrelEnv aenv, int akind, int anr) {
		return ((Struct_MizConstr)Module_vector.f_getVectorUnsafe(((Struct_Vector)((aenv).f_constrs[akind])), anr));
	}
	public static final int f_mizConstrCount(Struct_MizCorrelEnv aenv, int akind) {
		return (Module_vector.f_sizeVector(((Struct_Vector)((aenv).f_constrs[akind])))-1);
	}
	public static final Struct_Vector f_mizConstrPrefixes(Struct_MizCorrelEnv aenv, int astructNr) {
		return (Module_correl.f_mizConstr(aenv, Module_correl.g_coStructMode, astructNr)).f_prefixes;
	}
	public static final Struct_MizAttr f_mizCopyAttr(Struct_MizAttr aa) {
		return (new Struct_MizAttr((aa).f_neg, (aa).f_attrNr, Module_correl.f_mizCopyTrmList((aa).f_args), (aa).f_collected, (aa).f_pattNr));
	}
	public static final Struct_MizAttrColl f_mizCopyCluster(Struct_MizAttrColl ac) {
		return (new Struct_MizAttrColl(Module_vector.f_mapVector((ac).f_items, ((Func1<Object,Object>)(Func1)Wrappers.w_mizCopyAttr)), (ac).f_consistent));
	}
	public static final Struct f_mizCopyFrm(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 56/*MizPredFrm*/: {
			final Struct_MizPredFrm l1__tmp = (Struct_MizPredFrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_predNr = l1__tmp.f_predNr;
			final Struct l4_args = l1__tmp.f_args;
			final int l5_pattNr = l1__tmp.f_pattNr;
			final int l6_srt = (l1__tmp).f_sort;
			final int l7_predNr = (l1__tmp).f_predNr;
			final Struct l8_args = (l1__tmp).f_args;
			final int l9_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizPredFrm(l6_srt, l7_predNr, Module_correl.f_mizCopyTrmList(l8_args), l9_pattNr));
		}
		case 49/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l1__tmp = (Struct_MizLocPredFrm)l0__tmp;
			final int l10_predNr = l1__tmp.f_predNr;
			final Struct l11_args = l1__tmp.f_args;
			final Struct l12_expn = l1__tmp.f_predExp;
			final int l13_pattNr = l1__tmp.f_pattNr;
			final int l14_predNr = (l1__tmp).f_predNr;
			final Struct l15_args = (l1__tmp).f_args;
			final Struct l16_expn = (l1__tmp).f_predExp;
			final int l17_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizLocPredFrm(l14_predNr, Module_correl.f_mizCopyTrmList(l15_args), Module_correl.f_mizCopyFrm(l16_expn), l17_pattNr));
		}
		case 61/*MizQualFrm*/: {
			final Struct_MizQualFrm l1__tmp = (Struct_MizQualFrm)l0__tmp;
			final Struct l18_trm = l1__tmp.f_trm;
			final Struct_MizTyp l19_typ = l1__tmp.f_typ;
			final int l20_pattNr = l1__tmp.f_pattNr;
			final Struct l21_trm = (l1__tmp).f_trm;
			final Struct_MizTyp l22_typ = (l1__tmp).f_typ;
			final int l23_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizQualFrm(Module_correl.f_mizCopyTrm(l21_trm), Module_correl.f_mizCopyTyp(l22_typ), l23_pattNr));
		}
		case 51/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l24_arg = l1__tmp.f_arg;
			final int l25_pattNr = l1__tmp.f_pattNr;
			final Struct l26_arg = (l1__tmp).f_arg;
			final int l27_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizNegFrm(Module_correl.f_mizCopyFrm(l26_arg), l27_pattNr));
		}
		case 17/*MizBinFrm*/: {
			final Struct_MizBinFrm l1__tmp = (Struct_MizBinFrm)l0__tmp;
			final int l28_srt = l1__tmp.f_sort;
			final Struct l29_l = l1__tmp.f_left;
			final Struct l30_r = l1__tmp.f_right;
			final int l31_pattNr = l1__tmp.f_pattNr;
			final int l32_srt = (l1__tmp).f_sort;
			final Struct l33_l = (l1__tmp).f_left;
			final Struct l34_r = (l1__tmp).f_right;
			final int l35_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizBinFrm(l32_srt, Module_correl.f_mizCopyFrm(l33_l), Module_correl.f_mizCopyFrm(l34_r), l35_pattNr));
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l36_conjuncts = l1__tmp.f_conjuncts;
			final int l37_pattNr = l1__tmp.f_pattNr;
			final Struct_Vector l38_conjuncts = (l1__tmp).f_conjuncts;
			final int l39_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizConjFrm(Module_vector.f_mapVector(l38_conjuncts, ((Func1<Object,Object>)(Func1)Wrappers.w_mizCopyFrm)), l39_pattNr));
		}
		case 74/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l1__tmp = (Struct_MizUnivFrm)l0__tmp;
			final int l40_srt = l1__tmp.f_sort;
			final int l41_varId = l1__tmp.f_varId;
			final Struct_MizTyp l42_quantified = l1__tmp.f_quantified;
			final Struct l43_scope = l1__tmp.f_scope;
			final int l44_pattNr = l1__tmp.f_pattNr;
			final int l45_srt = (l1__tmp).f_sort;
			final int l46_varId = (l1__tmp).f_varId;
			final Struct_MizTyp l47_quantified = (l1__tmp).f_quantified;
			final Struct l48_scope = (l1__tmp).f_scope;
			final int l49_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizUnivFrm(l45_srt, l46_varId, Module_correl.f_mizCopyTyp(l47_quantified), Wrappers.w_mizCopyFrm.invoke(l48_scope), l49_pattNr));
		}
		case 73/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l1__tmp = (Struct_MizUniqFrm)l0__tmp;
			final int l50_srt = l1__tmp.f_sort;
			final int l51_srt = (l1__tmp).f_sort;
			return (new Struct_MizUniqFrm(l51_srt));
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l1__tmp = (Struct_MizFlexFrm)l0__tmp;
			final int l52_srt = l1__tmp.f_sort;
			final Struct l53_lo = l1__tmp.f_leftOrig;
			final Struct l54_ro = l1__tmp.f_rightOrig;
			final Struct l55_lt = l1__tmp.f_leftTrm;
			final Struct l56_rt = l1__tmp.f_rightTrm;
			final Struct l57_expn = l1__tmp.f_expansion;
			final int l58_pattNr = l1__tmp.f_pattNr;
			final int l59_srt = (l1__tmp).f_sort;
			final Struct l60_lo = (l1__tmp).f_leftOrig;
			final Struct l61_ro = (l1__tmp).f_rightOrig;
			final Struct l62_lt = (l1__tmp).f_leftTrm;
			final Struct l63_rt = (l1__tmp).f_rightTrm;
			final Struct l64_expn = (l1__tmp).f_expansion;
			final int l65_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizFlexFrm(l59_srt, Wrappers.w_mizCopyFrm.invoke(l60_lo), Wrappers.w_mizCopyFrm.invoke(l61_ro), Module_correl.f_mizCopyTrm(l62_lt), Module_correl.f_mizCopyTrm(l63_rt), Wrappers.w_mizCopyFrm.invoke(l64_expn), l65_pattNr));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_mizCopyTrm(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 75/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_varNr = l1__tmp.f_varNr;
			final int l4_pattNr = l1__tmp.f_pattNr;
			final int l5_srt = (l1__tmp).f_sort;
			final int l6_varNr = (l1__tmp).f_varNr;
			final int l7_pattNr = (l1__tmp).f_pattNr;
			final int l8___ = (l1__tmp).f_trmInfo;
			if ((l5_srt==Module_lexicon.g_ikTrmEqConst)) {
				return l1__tmp;
			} else {
				return (new Struct_MizVarTrm(l5_srt, l6_varNr, l7_pattNr, 0));
			}
		}
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l9_srt = l1__tmp.f_sort;
			final int l10_funcNr = l1__tmp.f_funcNr;
			final Struct l11_args = l1__tmp.f_args;
			final int l12_pattNr = l1__tmp.f_pattNr;
			final int l13_srt = (l1__tmp).f_sort;
			final int l14_funcNr = (l1__tmp).f_funcNr;
			final Struct l15_args = (l1__tmp).f_args;
			final int l16_pattNr = (l1__tmp).f_pattNr;
			final int l17___ = (l1__tmp).f_trmInfo;
			return (new Struct_MizFuncTrm(l13_srt, l14_funcNr, Module_correl.f_mizCopyTrmList(l15_args), l16_pattNr, 0));
		}
		case 59/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final int l18_funcNr = l1__tmp.f_funcNr;
			final Struct l19_args = l1__tmp.f_args;
			final Struct l20_funcExp = l1__tmp.f_funcExp;
			final int l21_pattNr = l1__tmp.f_pattNr;
			final int l22_funcNr = (l1__tmp).f_funcNr;
			final Struct l23_args = (l1__tmp).f_args;
			final Struct l24_funcExp = (l1__tmp).f_funcExp;
			final int l25_pattNr = (l1__tmp).f_pattNr;
			final int l26___ = (l1__tmp).f_trmInfo;
			return (new Struct_MizPrivFuncTrm(l22_funcNr, Module_correl.f_mizCopyTrmList(l23_args), Module_correl.f_mizCopyTrm(l24_funcExp), l25_pattNr, 0));
		}
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l1__tmp = (Struct_MizFraenkelTrm)l0__tmp;
			final Struct_Vector l27_lambdaArgs = l1__tmp.f_lambdaArgs;
			final Struct l28_scope = l1__tmp.f_scope;
			final Struct l29_compr = l1__tmp.f_compr;
			final Struct_MizIntSeq l30_idents = l1__tmp.f_idents;
			final int l31_pattNr = l1__tmp.f_pattNr;
			final Struct_Vector l32_lambdaArgs = (l1__tmp).f_lambdaArgs;
			final Struct l33_scope = (l1__tmp).f_scope;
			final Struct l34_compr = (l1__tmp).f_compr;
			final Struct_MizIntSeq l35_idents = (l1__tmp).f_idents;
			final int l36_pattNr = (l1__tmp).f_pattNr;
			final int l37___ = (l1__tmp).f_trmInfo;
			return (new Struct_MizFraenkelTrm(Module_correl.f_mizCopyTypVector(l32_lambdaArgs), Module_correl.f_mizCopyTrm(l33_scope), Wrappers.w_mizCopyFrm.invoke(l34_compr), Module_mobjects.f_mizCopyIntSeq(l35_idents), l36_pattNr, 0));
		}
		case 60/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
			final Struct l38_trmProper = l1__tmp.f_trmProper;
			final Struct_MizTyp l39_qua = l1__tmp.f_qua;
			final int l40_pattNr = l1__tmp.f_pattNr;
			final Struct l41_trmProper = (l1__tmp).f_trmProper;
			final Struct_MizTyp l42_qua = (l1__tmp).f_qua;
			final int l43_pattNr = (l1__tmp).f_pattNr;
			final int l44___ = (l1__tmp).f_trmInfo;
			return (new Struct_MizQuaTrm(Module_correl.f_mizCopyTrm(l41_trmProper), Module_correl.f_mizCopyTyp(l42_qua), l43_pattNr, 0));
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
			final Struct_MizTyp l45_typ = l1__tmp.f_choiceTyp;
			final int l46_pattNr = l1__tmp.f_pattNr;
			final Struct_MizTyp l47_typ = (l1__tmp).f_choiceTyp;
			final int l48_pattNr = (l1__tmp).f_pattNr;
			final int l49___ = (l1__tmp).f_trmInfo;
			return (new Struct_MizChoiceTrm(Module_correl.f_mizCopyTyp(l47_typ), l48_pattNr, 0));
		}
		case 16/*MizBaseTrm*/: {
			final Struct_MizBaseTrm l1__tmp = (Struct_MizBaseTrm)l0__tmp;
			final int l50_srt = l1__tmp.f_sort;
			final int l51_srt = (l1__tmp).f_sort;
			final int l52___ = (l1__tmp).f_trmInfo;
			return (new Struct_MizBaseTrm(l51_srt, 0));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_mizCopyTrmList(Struct al) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			return al;
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			return (new Struct_MizTrmElem(Module_correl.f_mizCopyTrm(l4_trm), Module_correl.f_mizCopyTrmList(l5_next)));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct_MizTyp f_mizCopyTyp(Struct_MizTyp at) {
		return (new Struct_MizTyp((at).f_sort, Module_correl.f_mizCopyCluster((at).f_lower), Module_correl.f_mizCopyCluster((at).f_upper), (at).f_modNr, Module_correl.f_mizCopyTrmList((at).f_modArgs), (at).f_pattNr));
	}
	public static final Struct_Vector f_mizCopyTypVector(Struct_Vector av) {
		return Module_vector.f_mapVector(av, ((Func1<Object,Object>)(Func1)Wrappers.w_mizCopyTyp));
	}
	public static final boolean f_mizEqAttr(Struct_MizCorrelEnv aenv, Struct_MizAttr aa, Struct_MizAttr ab) {
		final Struct_Pair l0_pa = Module_correl.f_mizAdjustAttr(aenv, aa);
		final Struct_Pair l1_pb = Module_correl.f_mizAdjustAttr(aenv, ab);
		return (((((int)(l0_pa).f_first)==((int)(l1_pb).f_first))&&((aa).f_neg==(ab).f_neg))&&Module_correl.f_mizEqTrmList(aenv, ((Struct)(l0_pa).f_second), ((Struct)(l1_pb).f_second)));
	}
	public static final boolean f_mizEqCluIsSubsetOf(Struct_MizAttrColl ac, Struct_MizAttrColl aother, Func2<Boolean,Struct_MizAttr, Struct_MizAttr> aeqAttr) {
		final int l0_n = Module_vector.f_sizeVector((ac).f_items);
		final int l1_m = Module_vector.f_sizeVector((aother).f_items);
		if ((l1_m<l0_n)) {
			return false;
		} else {
			final Reference<Boolean> l2_res = ((Reference<Boolean>)(new Reference(true)));
			final Reference<Integer> l3_i = ((Reference<Integer>)(new Reference(0)));
			final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l3_i.value)<l0_n)&&((boolean)l2_res.value)));
			};
			final Struct_MizAttrColl l8_c = ac;
			final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l9_eqAttr = aeqAttr;
			final Struct_MizAttrColl l10_other = aother;
			final Func0<Object> l7_$5 = (Func0<Object>)() -> {
				final Struct_MizAttr l8_a = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l8_c).f_items, ((int)l3_i.value)));
				final Reference<Boolean> l9_found = ((Reference<Boolean>)(new Reference(false)));
				final Reference<Integer> l10_j = ((Reference<Integer>)(new Reference(0)));
				final Func0<Boolean> l13_$11 = (Func0<Boolean>)() -> {
					return ((Boolean)((((int)l10_j.value)<l1_m)&&!((boolean)l9_found.value)));
				};
				final Func0<Object> l14_$12 = (Func0<Object>)() -> {
					if (((boolean)l9_eqAttr.invoke(((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l10_other).f_items, ((int)l10_j.value))), l8_a))) {
						l9_found.value = ((Boolean)true);
					} else {
					}
					l10_j.value = ((Integer)(((int)l10_j.value)+1));
					return null;
				};
				Module_loop.f_while(l13_$11, l14_$12);
				if (!((boolean)l9_found.value)) {
					l2_res.value = ((Boolean)false);
				} else {
				}
				l3_i.value = ((Integer)(((int)l3_i.value)+1));
				return null;
			};
			Module_loop.f_while(l6_$4, l7_$5);
			return ((boolean)l2_res.value);
		}
	}
	public static final boolean f_mizEqFrm(Struct_MizCorrelEnv aenv, Struct afa, Struct afb) {
		TAIL_CALL: for(;;) {
		final Struct l0_a = Module_correl.f_mizSkipLocPred(afa);
		final Struct l1_b = Module_correl.f_mizSkipLocPred(afb);
		final int l2_sa = Module_correl.f_mizFrmSort(l0_a);
		final int l3_sb = Module_correl.f_mizFrmSort(l1_b);
		if ((l2_sa!=l3_sb)) {
			return false;
		} else {
			if ((((l2_sa==Module_lexicon.g_ikFrmVerum)||(l2_sa==Module_lexicon.g_ikFrmThesis))||(l2_sa==Module_lexicon.g_ikError))) {
				return true;
			} else {
				Struct l4__tmp = l0_a;
				switch (l4__tmp.getTypeId()) {
				case 51/*MizNegFrm*/: {
					final Struct_MizNegFrm l5__tmp = (Struct_MizNegFrm)l4__tmp;
					final Struct l6_arg = l5__tmp.f_arg;
					final Struct l7_arg = (l5__tmp).f_arg;
					final int l8___ = (l5__tmp).f_pattNr;
					final Struct_MizNegFrm l9_nb = Module_correl.f_mizAsNegFrm(l1_b);
					{
						final Struct l10___tmp = l7_arg;
						final Struct l11___tmp = (l9_nb).f_arg;
						afa = l10___tmp;
						afb = l11___tmp;
						continue TAIL_CALL;
					}
				}
				case 61/*MizQualFrm*/: {
					final Struct_MizQualFrm l5__tmp = (Struct_MizQualFrm)l4__tmp;
					final Struct l12_trm = l5__tmp.f_trm;
					final Struct_MizTyp l13_typ = l5__tmp.f_typ;
					final Struct l14_trm = (l5__tmp).f_trm;
					final Struct_MizTyp l15_typ = (l5__tmp).f_typ;
					final int l16___ = (l5__tmp).f_pattNr;
					final Struct_MizQualFrm l17_qb = Module_correl.f_mizAsQualFrm(l1_b);
					return (Module_correl.f_mizEqTrm(aenv, l14_trm, (l17_qb).f_trm)&&Module_correl.f_mizEqTyp(aenv, l15_typ, (l17_qb).f_typ));
				}
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l5__tmp = (Struct_MizConjFrm)l4__tmp;
					final Struct_Vector l18_conjuncts = l5__tmp.f_conjuncts;
					final Struct_Vector l19_conjuncts = (l5__tmp).f_conjuncts;
					final int l20___ = (l5__tmp).f_pattNr;
					final Struct_MizConjFrm l21_cb = Module_correl.f_mizAsConjFrm(l1_b);
					final Struct_MizCorrelEnv l23_env = aenv;
					final Func2<Boolean,Struct_Vector, Struct_Vector> l22_eqVec = (Func2<Boolean, Struct_Vector, Struct_Vector>)(Struct_Vector ava, Struct_Vector avb) -> {
						if ((Module_vector.f_sizeVector(ava)!=Module_vector.f_sizeVector(avb))) {
							return ((Boolean)false);
						} else {
							final Reference<Boolean> l23_ok = ((Reference<Boolean>)(new Reference(true)));
							final Reference<Integer> l24_i = ((Reference<Integer>)(new Reference(0)));
							final Func0<Boolean> l27_$25 = (Func0<Boolean>)() -> {
								return ((Boolean)((((int)l24_i.value)<Module_vector.f_sizeVector(ava))&&((boolean)l23_ok.value)));
							};
							final Func0<Object> l28_$26 = (Func0<Object>)() -> {
								if (!Module_correl.f_mizEqFrm(l23_env, ((Struct)Module_vector.f_getVectorUnsafe(ava, ((int)l24_i.value))), ((Struct)Module_vector.f_getVectorUnsafe(avb, ((int)l24_i.value))))) {
									l23_ok.value = ((Boolean)false);
								} else {
								}
								l24_i.value = ((Integer)(((int)l24_i.value)+1));
								return null;
							};
							Module_loop.f_while(l27_$25, l28_$26);
							return l23_ok.value;
						}
					};
					if ((Module_vector.f_sizeVector(l19_conjuncts)==Module_vector.f_sizeVector((l21_cb).f_conjuncts))) {
						return ((boolean)l22_eqVec.invoke(l19_conjuncts, (l21_cb).f_conjuncts));
					} else {
						return ((boolean)l22_eqVec.invoke(Module_correl.f_mizFlattenConjuncts(l19_conjuncts), Module_correl.f_mizFlattenConjuncts((l21_cb).f_conjuncts)));
					}
				}
				case 56/*MizPredFrm*/: {
					final Struct_MizPredFrm l5__tmp = (Struct_MizPredFrm)l4__tmp;
					final int l24_srt = l5__tmp.f_sort;
					final int l25_predNr = l5__tmp.f_predNr;
					final Struct l26_args = l5__tmp.f_args;
					final int l27_srt = (l5__tmp).f_sort;
					final int l28_predNr = (l5__tmp).f_predNr;
					final Struct l29_args = (l5__tmp).f_args;
					final int l30___ = (l5__tmp).f_pattNr;
					final Struct_MizPredFrm l31_pb = Module_correl.f_mizAsPredFrm(l1_b);
					final Struct_MizPredFrm l32_pa2 = Module_correl.f_mizAsPredFrm(l5__tmp);
					if ((l27_srt==Module_lexicon.g_ikFrmSchPred)) {
						return ((l28_predNr==(l31_pb).f_predNr)&&Module_correl.f_mizEqTrmList(aenv, l29_args, (l31_pb).f_args));
					} else {
						if ((l27_srt==Module_lexicon.g_ikFrmAttr)) {
							final Struct_Pair l33_qa = Module_correl.f_mizAdjustAttrFrm(aenv, l32_pa2);
							final Struct_Pair l34_qb = Module_correl.f_mizAdjustAttrFrm(aenv, l31_pb);
							return ((((int)(l33_qa).f_first)==((int)(l34_qb).f_first))&&Module_correl.f_mizEqTrmList(aenv, ((Struct)(l33_qa).f_second), ((Struct)(l34_qb).f_second)));
						} else {
							final Struct_Pair l35_qa = Module_correl.f_mizAdjustFrm(aenv, l32_pa2);
							final Struct_Pair l36_qb = Module_correl.f_mizAdjustFrm(aenv, l31_pb);
							if ((((int)(l35_qa).f_first)!=((int)(l36_qb).f_first))) {
								return false;
							} else {
								if ((((int)(l35_qa).f_first)==Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqEqualsTo))) {
									final Struct l37_l1 = Module_correl.f_mizTrmListNth(((Struct)(l35_qa).f_second), 0);
									final Struct l38_r1 = Module_correl.f_mizTrmListNth(((Struct)(l35_qa).f_second), 1);
									final Struct l39_l2 = Module_correl.f_mizTrmListNth(((Struct)(l36_qb).f_second), 0);
									final Struct l40_r2 = Module_correl.f_mizTrmListNth(((Struct)(l36_qb).f_second), 1);
									return ((Module_correl.f_mizEqTrm(aenv, l37_l1, l39_l2)&&Module_correl.f_mizEqTrm(aenv, l38_r1, l40_r2))||(Module_correl.f_mizEqTrm(aenv, l38_r1, l39_l2)&&Module_correl.f_mizEqTrm(aenv, l37_l1, l40_r2)));
								} else {
									return Module_correl.f_mizEqTrmList(aenv, ((Struct)(l35_qa).f_second), ((Struct)(l36_qb).f_second));
								}
							}
						}
					}
				}
				case 49/*MizLocPredFrm*/: {
					final Struct_MizLocPredFrm l5__tmp = (Struct_MizLocPredFrm)l4__tmp;
					final int l41_predNr = l5__tmp.f_predNr;
					final Struct l42_args = l5__tmp.f_args;
					final int l43_predNr = (l5__tmp).f_predNr;
					final Struct l44_args = (l5__tmp).f_args;
					final Struct l45___ = (l5__tmp).f_predExp;
					final int l46___ = (l5__tmp).f_pattNr;
					final Struct_MizLocPredFrm l47_lb = Module_correl.f_mizAsLocPredFrm(l1_b);
					return ((l43_predNr==(l47_lb).f_predNr)&&Module_correl.f_mizEqTrmList(aenv, l44_args, (l47_lb).f_args));
				}
				case 74/*MizUnivFrm*/: {
					final Struct_MizUnivFrm l5__tmp = (Struct_MizUnivFrm)l4__tmp;
					final Struct_MizTyp l48_quantified = l5__tmp.f_quantified;
					final Struct l49_scope = l5__tmp.f_scope;
					final int l50___ = (l5__tmp).f_sort;
					final int l51___ = (l5__tmp).f_varId;
					final Struct_MizTyp l52_quantified = (l5__tmp).f_quantified;
					final Struct l53_scope = (l5__tmp).f_scope;
					final int l54___ = (l5__tmp).f_pattNr;
					final Struct_MizUnivFrm l55_ub = Module_correl.f_mizAsUnivFrm(l1_b);
					return (Module_correl.f_mizEqTyp(aenv, l52_quantified, (l55_ub).f_quantified)&&Module_correl.f_mizEqFrm(aenv, l53_scope, (l55_ub).f_scope));
				}
				case 41/*MizFlexFrm*/: {
					final Struct_MizFlexFrm l5__tmp = (Struct_MizFlexFrm)l4__tmp;
					final Struct l56_lo = l5__tmp.f_leftOrig;
					final Struct l57_ro = l5__tmp.f_rightOrig;
					final Struct l58_expn = l5__tmp.f_expansion;
					final int l59___ = (l5__tmp).f_sort;
					final Struct l60_lo = (l5__tmp).f_leftOrig;
					final Struct l61_ro = (l5__tmp).f_rightOrig;
					final Struct l62___ = (l5__tmp).f_leftTrm;
					final Struct l63___ = (l5__tmp).f_rightTrm;
					final Struct l64_expn = (l5__tmp).f_expansion;
					final int l65___ = (l5__tmp).f_pattNr;
					final Struct_MizFlexFrm l66_xb = Module_correl.f_mizAsFlexFrm(l1_b);
					return ((Module_correl.f_mizEqFrm(aenv, l60_lo, (l66_xb).f_leftOrig)&&Module_correl.f_mizEqFrm(aenv, l61_ro, (l66_xb).f_rightOrig))&&Module_correl.f_mizEqFrm(aenv, l64_expn, (l66_xb).f_expansion));
				}
				default: {
					Module_errhan.f_mizRunTimeError(2049);
					return false;
				}
				}
			}
		}
	}
	}
	public static final boolean f_mizEqTrm(Struct_MizCorrelEnv aenv, Struct ata, Struct atb) {
		TAIL_CALL: for(;;) {
		final Struct l0_a = Module_correl.f_mizSkipPrivFunc(ata);
		final Struct l1_b = Module_correl.f_mizSkipPrivFunc(atb);
		final int l2_sa = Module_correl.f_mizTrmSort(l0_a);
		final int l3_sb = Module_correl.f_mizTrmSort(l1_b);
		if ((l2_sa!=l3_sb)) {
			return false;
		} else {
			if ((l2_sa==Module_lexicon.g_ikTrmInfConst)) {
				final Struct_MizVarTrm l4_va = Module_correl.f_mizAsVarTrm(l0_a);
				final Struct_MizVarTrm l5_vb = Module_correl.f_mizAsVarTrm(l1_b);
				if (((l4_va).f_varNr==(l5_vb).f_varNr)) {
					return true;
				} else {
					final Struct_MizConstDef l6_da = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((aenv).f_inferConstDef, (l4_va).f_varNr));
					final Struct_MizConstDef l7_db = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((aenv).f_inferConstDef, (l5_vb).f_varNr));
					{
						final Struct l8___tmp = (l6_da).f_def;
						final Struct l9___tmp = (l7_db).f_def;
						ata = l8___tmp;
						atb = l9___tmp;
						continue TAIL_CALL;
					}
				}
			} else {
				Struct l10__tmp = l0_a;
				switch (l10__tmp.getTypeId()) {
				case 75/*MizVarTrm*/: {
					final Struct_MizVarTrm l11__tmp = (Struct_MizVarTrm)l10__tmp;
					final int l12_varNr = l11__tmp.f_varNr;
					final int l13___ = (l11__tmp).f_sort;
					final int l14_varNr = (l11__tmp).f_varNr;
					final int l15___ = (l11__tmp).f_pattNr;
					final int l16___ = (l11__tmp).f_trmInfo;
					final Struct_MizVarTrm l17_vb2 = Module_correl.f_mizAsVarTrm(l1_b);
					return (l14_varNr==(l17_vb2).f_varNr);
				}
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l11__tmp = (Struct_MizFuncTrm)l10__tmp;
					final int l18_srt = l11__tmp.f_sort;
					final int l19_funcNr = l11__tmp.f_funcNr;
					final Struct l20_args = l11__tmp.f_args;
					final int l21_srt = (l11__tmp).f_sort;
					final int l22_funcNr = (l11__tmp).f_funcNr;
					final Struct l23_args = (l11__tmp).f_args;
					final int l24___ = (l11__tmp).f_pattNr;
					final int l25___ = (l11__tmp).f_trmInfo;
					final Struct_MizFuncTrm l26_fb = Module_correl.f_mizAsFuncTrm(l1_b);
					if ((l21_srt==Module_lexicon.g_ikTrmFunctor)) {
						final Struct_MizFuncTrm l27_fa2 = Module_correl.f_mizAsFuncTrm(l11__tmp);
						final Struct_Pair l28_pa = Module_correl.f_mizAdjustTrm(aenv, l27_fa2);
						final Struct_Pair l29_pb = Module_correl.f_mizAdjustTrm(aenv, l26_fb);
						return ((((int)(l28_pa).f_first)==((int)(l29_pb).f_first))&&Module_correl.f_mizEqTrmList(aenv, ((Struct)(l28_pa).f_second), ((Struct)(l29_pb).f_second)));
					} else {
						return ((l22_funcNr==(l26_fb).f_funcNr)&&Module_correl.f_mizEqTrmList(aenv, l23_args, (l26_fb).f_args));
					}
				}
				case 59/*MizPrivFuncTrm*/: {
					final Struct_MizPrivFuncTrm l11__tmp = (Struct_MizPrivFuncTrm)l10__tmp;
					final int l30_funcNr = l11__tmp.f_funcNr;
					final Struct l31_args = l11__tmp.f_args;
					final int l32_funcNr = (l11__tmp).f_funcNr;
					final Struct l33_args = (l11__tmp).f_args;
					final Struct l34___ = (l11__tmp).f_funcExp;
					final int l35___ = (l11__tmp).f_pattNr;
					final int l36___ = (l11__tmp).f_trmInfo;
					final Struct_MizPrivFuncTrm l37_pb2 = Module_correl.f_mizAsPrivFuncTrm(l1_b);
					return ((l32_funcNr==(l37_pb2).f_funcNr)&&Module_correl.f_mizEqTrmList(aenv, l33_args, (l37_pb2).f_args));
				}
				case 42/*MizFraenkelTrm*/: {
					final Struct_MizFraenkelTrm l11__tmp = (Struct_MizFraenkelTrm)l10__tmp;
					final Struct_Vector l38_lambdaArgs = l11__tmp.f_lambdaArgs;
					final Struct l39_scope = l11__tmp.f_scope;
					final Struct l40_compr = l11__tmp.f_compr;
					final Struct_Vector l41_lambdaArgs = (l11__tmp).f_lambdaArgs;
					final Struct l42_scope = (l11__tmp).f_scope;
					final Struct l43_compr = (l11__tmp).f_compr;
					final Struct_MizIntSeq l44___ = (l11__tmp).f_idents;
					final int l45___ = (l11__tmp).f_pattNr;
					final int l46___ = (l11__tmp).f_trmInfo;
					final Struct_MizFraenkelTrm l47_kb = Module_correl.f_mizAsFraenkelTrm(l1_b);
					if ((Module_vector.f_sizeVector(l41_lambdaArgs)!=Module_vector.f_sizeVector((l47_kb).f_lambdaArgs))) {
						return false;
					} else {
						final Reference<Boolean> l48_ok = ((Reference<Boolean>)(new Reference(true)));
						final Reference<Integer> l49_i = ((Reference<Integer>)(new Reference(0)));
						final Func0<Boolean> l52_$50 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l49_i.value)<Module_vector.f_sizeVector(l41_lambdaArgs))&&((boolean)l48_ok.value)));
						};
						final Struct_MizCorrelEnv l54_env = aenv;
						final Func0<Object> l53_$51 = (Func0<Object>)() -> {
							if (!Module_correl.f_mizEqTyp(l54_env, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l41_lambdaArgs, ((int)l49_i.value))), ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l47_kb).f_lambdaArgs, ((int)l49_i.value))))) {
								l48_ok.value = ((Boolean)false);
							} else {
							}
							l49_i.value = ((Integer)(((int)l49_i.value)+1));
							return null;
						};
						Module_loop.f_while(l52_$50, l53_$51);
						return ((((boolean)l48_ok.value)&&Module_correl.f_mizEqTrm(aenv, l42_scope, (l47_kb).f_scope))&&Module_correl.f_mizEqFrm(aenv, l43_compr, (l47_kb).f_compr));
					}
				}
				case 21/*MizChoiceTrm*/: {
					final Struct_MizChoiceTrm l11__tmp = (Struct_MizChoiceTrm)l10__tmp;
					final Struct_MizTyp l55_typ = l11__tmp.f_choiceTyp;
					final Struct_MizTyp l56_typ = (l11__tmp).f_choiceTyp;
					final int l57___ = (l11__tmp).f_pattNr;
					final int l58___ = (l11__tmp).f_trmInfo;
					final Struct_MizChoiceTrm l59_hb = Module_correl.f_mizAsChoiceTrm(l1_b);
					return Module_correl.f_mizEqTyp(aenv, l56_typ, (l59_hb).f_choiceTyp);
				}
				case 60/*MizQuaTrm*/: {
					final Struct_MizQuaTrm l11__tmp = (Struct_MizQuaTrm)l10__tmp;
					final Struct l60_trmProper = l11__tmp.f_trmProper;
					final Struct_MizTyp l61_qua = l11__tmp.f_qua;
					final Struct l62_trmProper = (l11__tmp).f_trmProper;
					final Struct_MizTyp l63_qua = (l11__tmp).f_qua;
					final int l64___ = (l11__tmp).f_pattNr;
					final int l65___ = (l11__tmp).f_trmInfo;
					final Struct_MizQuaTrm l66_qb = Module_correl.f_mizAsQuaTrm(l1_b);
					return (Module_correl.f_mizEqTrm(aenv, l62_trmProper, (l66_qb).f_trmProper)&&Module_correl.f_mizEqTyp(aenv, l63_qua, (l66_qb).f_qua));
				}
				case 16/*MizBaseTrm*/: {
					final Struct_MizBaseTrm l11__tmp = (Struct_MizBaseTrm)l10__tmp;
					final int l67_srt = l11__tmp.f_sort;
					final int l68_srt = (l11__tmp).f_sort;
					final int l69___ = (l11__tmp).f_trmInfo;
					return ((l68_srt==Module_lexicon.g_ikTrmIt)||(l68_srt==Module_lexicon.g_ikError));
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
				}
			}
		}
	}
	}
	public static final boolean f_mizEqTrmList(Struct_MizCorrelEnv aenv, Struct aa, Struct ab) {
		Struct l0__tmp = aa;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			Struct l2__tmp = ab;
			switch (l2__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return true;
			}
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l3__tmp = (Struct_MizTrmElem)l2__tmp;
				final Struct l4___ = (l3__tmp).f_trm;
				final Struct l5___ = (l3__tmp).f_next;
				return false;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
			}
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l6_ta = l1__tmp.f_trm;
			final Struct l7_na = l1__tmp.f_next;
			final Struct l8_ta = (l1__tmp).f_trm;
			final Struct l9_na = (l1__tmp).f_next;
			Struct l10__tmp = ab;
			switch (l10__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return false;
			}
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l11__tmp = (Struct_MizTrmElem)l10__tmp;
				final Struct l12_tb = l11__tmp.f_trm;
				final Struct l13_nb = l11__tmp.f_next;
				final Struct l14_tb = (l11__tmp).f_trm;
				final Struct l15_nb = (l11__tmp).f_next;
				return (Module_correl.f_mizEqTrm(aenv, l8_ta, l14_tb)&&Module_correl.f_mizEqTrmList(aenv, l9_na, l15_nb));
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final boolean f_mizEqTyp(Struct_MizCorrelEnv aenv, Struct_MizTyp aa, Struct_MizTyp ab) {
		if (((aa).f_sort!=(ab).f_sort)) {
			return false;
		} else {
			if (((aa).f_sort==Module_lexicon.g_ikTypMode)) {
				final Struct_MizCorrelEnv l2_env = aenv;
				final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l1_$0 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
					return ((Boolean)Module_correl.f_mizEqAttr(l2_env, ax, ay));
				};
				if (Module_correl.f_mizEqualClusters(aa, ab, l1_$0)) {
					final Struct_Pair l3_pa = Module_correl.f_mizAdjustTyp(aenv, aa);
					final Struct_Pair l4_pb = Module_correl.f_mizAdjustTyp(aenv, ab);
					return ((((int)(l3_pa).f_first)==((int)(l4_pb).f_first))&&Module_correl.f_mizEqTrmList(aenv, ((Struct)(l3_pa).f_second), ((Struct)(l4_pb).f_second)));
				} else {
					return false;
				}
			} else {
				if (((aa).f_sort==Module_lexicon.g_ikTypStruct)) {
					final Struct_MizCorrelEnv l7_env = aenv;
					final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l6_$5 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
						return ((Boolean)Module_correl.f_mizEqAttr(l7_env, ax, ay));
					};
					return ((Module_correl.f_mizEqualClusters(aa, ab, l6_$5)&&((aa).f_modNr==(ab).f_modNr))&&Module_correl.f_mizEqTrmList(aenv, (aa).f_modArgs, (ab).f_modArgs));
				} else {
					if (((aa).f_sort==Module_lexicon.g_ikError)) {
						return true;
					} else {
						Module_errhan.f_mizRunTimeError(2048);
						return false;
					}
				}
			}
		}
	}
	public static final boolean f_mizEqualClusters(Struct_MizTyp aa, Struct_MizTyp ab, Func2<Boolean,Struct_MizAttr, Struct_MizAttr> aeqAttr) {
		return (Module_correl.f_mizEqCluIsSubsetOf((aa).f_lower, (ab).f_upper, aeqAttr)&&Module_correl.f_mizEqCluIsSubsetOf((ab).f_lower, (aa).f_upper, aeqAttr));
	}
	public static final Struct_Vector f_mizFlattenConjuncts(Struct_Vector av) {
		final Struct_Vector l0_out = Module_vector.f_makeVector(Module_vector.f_sizeVector(av));
		final Func1<Object,Struct> l2_$1 = (Func1<Object, Struct>)(Struct af0) -> {
			final Struct l3_f = Module_correl.f_mizSkipLocPred(af0);
			Struct l4__tmp = l3_f;
			switch (l4__tmp.getTypeId()) {
			case 26/*MizConjFrm*/: {
				final Struct_MizConjFrm l5__tmp = (Struct_MizConjFrm)l4__tmp;
				final Struct_Vector l6_inner = l5__tmp.f_conjuncts;
				final Struct_Vector l7_inner = (l5__tmp).f_conjuncts;
				final int l8___ = (l5__tmp).f_pattNr;
				final Func1<Object,Struct> l10_$9 = (Func1<Object, Struct>)(Struct ag) -> {
					return Module_vector.f_pushVector(l0_out, ag);
				};
				return Module_vector.f_iterVector(l7_inner, ((Func1<Object,Object>)(Func1)l10_$9));
			}
			default: {
				return Module_vector.f_pushVector(l0_out, l3_f);
			}
			}
		};
		Module_vector.f_iterVector(av, ((Func1<Object,Object>)(Func1)l2_$1));
		return l0_out;
	}
	public static final int f_mizFrmSort(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 56/*MizPredFrm*/: {
			final Struct_MizPredFrm l1__tmp = (Struct_MizPredFrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_srt = (l1__tmp).f_sort;
			final int l4___ = (l1__tmp).f_predNr;
			final Struct l5___ = (l1__tmp).f_args;
			final int l6___ = (l1__tmp).f_pattNr;
			return l3_srt;
		}
		case 49/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l1__tmp = (Struct_MizLocPredFrm)l0__tmp;
			final int l7___ = (l1__tmp).f_predNr;
			final Struct l8___ = (l1__tmp).f_args;
			final Struct l9___ = (l1__tmp).f_predExp;
			final int l10___ = (l1__tmp).f_pattNr;
			return Module_lexicon.g_ikFrmPrivPred;
		}
		case 61/*MizQualFrm*/: {
			final Struct_MizQualFrm l1__tmp = (Struct_MizQualFrm)l0__tmp;
			final Struct l11___ = (l1__tmp).f_trm;
			final Struct_MizTyp l12___ = (l1__tmp).f_typ;
			final int l13___ = (l1__tmp).f_pattNr;
			return Module_lexicon.g_ikFrmQual;
		}
		case 51/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l14___ = (l1__tmp).f_arg;
			final int l15___ = (l1__tmp).f_pattNr;
			return Module_lexicon.g_ikFrmNeg;
		}
		case 17/*MizBinFrm*/: {
			final Struct_MizBinFrm l1__tmp = (Struct_MizBinFrm)l0__tmp;
			final int l16_srt = l1__tmp.f_sort;
			final int l17_srt = (l1__tmp).f_sort;
			final Struct l18___ = (l1__tmp).f_left;
			final Struct l19___ = (l1__tmp).f_right;
			final int l20___ = (l1__tmp).f_pattNr;
			return l17_srt;
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l21___ = (l1__tmp).f_conjuncts;
			final int l22___ = (l1__tmp).f_pattNr;
			return Module_lexicon.g_ikFrmConj;
		}
		case 74/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l1__tmp = (Struct_MizUnivFrm)l0__tmp;
			final int l23_srt = l1__tmp.f_sort;
			final int l24_srt = (l1__tmp).f_sort;
			final int l25___ = (l1__tmp).f_varId;
			final Struct_MizTyp l26___ = (l1__tmp).f_quantified;
			final Struct l27___ = (l1__tmp).f_scope;
			final int l28___ = (l1__tmp).f_pattNr;
			return l24_srt;
		}
		case 73/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l1__tmp = (Struct_MizUniqFrm)l0__tmp;
			final int l29_srt = l1__tmp.f_sort;
			final int l30_srt = (l1__tmp).f_sort;
			return l30_srt;
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l1__tmp = (Struct_MizFlexFrm)l0__tmp;
			final int l31_srt = l1__tmp.f_sort;
			final int l32_srt = (l1__tmp).f_sort;
			final Struct l33___ = (l1__tmp).f_leftOrig;
			final Struct l34___ = (l1__tmp).f_rightOrig;
			final Struct l35___ = (l1__tmp).f_leftTrm;
			final Struct l36___ = (l1__tmp).f_rightTrm;
			final Struct l37___ = (l1__tmp).f_expansion;
			final int l38___ = (l1__tmp).f_pattNr;
			return l32_srt;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final boolean f_mizHasProperty(Struct_MizConstr ac, int aprop) {
		return (Native.bitAnd((ac).f_properties, Native.bitShl(1, aprop))!=0);
	}
	public static final Struct_MizTrmElem f_mizLastElem(Struct al) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			Module_errhan.f_mizRunTimeError(2102);
			return (new Struct_MizTrmElem(Module_correl.f_mizNewIncorTrm(), ((Struct)SingletonStructs.str_MizTrmNil)));
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_next = l1__tmp.f_next;
			final Struct l3___ = (l1__tmp).f_trm;
			final Struct l4_next = (l1__tmp).f_next;
			Struct l5__tmp = l4_next;
			switch (l5__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return l1__tmp;
			}
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l6__tmp = (Struct_MizTrmElem)l5__tmp;
				final Struct l7___ = (l6__tmp).f_trm;
				final Struct l8___ = (l6__tmp).f_next;
				{
					final Struct l9___tmp = l6__tmp;
					al = l9___tmp;
					continue TAIL_CALL;
				}
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l5__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	}
	public static final Struct_Vector f_mizMake1BasedTyps() {
		final Struct_Vector l0_v = Module_vector.f_makeVector(64);
		Module_vector.f_pushVector(l0_v, Module_correl.f_mizNewIncorTyp());
		return l0_v;
	}
	public static final Struct_MizConstr f_mizMakeConstr(int akind, String aarticle, int aabsNr) {
		return (new Struct_MizConstr(akind, aarticle, aabsNr, Module_vector.f_makeVector(4), 0, 0, 0, 0, 0, ((Struct)SingletonStructs.str_None), 0, 0, Module_mobjects.f_mizInitNatSet(0), Module_vector.f_makeVector(2)));
	}
	public static final Struct_MizCorrelEnv f_mizMakeCorrelEnv() {
		final Object[] l17_$1 = Native.enumFromTo(0, Module_correl.g_coLAST);
		final Func1<Struct_Vector,Integer> l18_$0 = (Func1<Struct_Vector, Integer>)(Integer ak) -> {
			final Struct_Vector l19_v = Module_vector.f_makeVector(64);
			Module_vector.f_pushVector(l19_v, Module_correl.f_mizMakeConstr(((int)ak), "", 0));
			return l19_v;
		};
		final Object[] l19_$7 = Native.map(l17_$1, ((Func1<Object,Object>)(Func1)l18_$0));
		final Struct_MizBuiltIn l20_$8 = Module_builtin.f_mizMakeBuiltIn();
		final Struct_Vector l21_$9 = Module_vector.f_makeVector(64);
		final Struct_MizIntSeq l22_$10 = Module_mobjects.f_mizInitIntSeq(16);
		final Struct_Vector l23_$11 = Module_correl.f_mizMake1BasedTyps();
		final Struct_Vector l24_iv = Module_vector.f_makeVector(32);
		Module_vector.f_pushVector(l24_iv, Module_correl.f_mizNewIncorTrm());
		final Struct_Vector l25_$2 = l24_iv;
		final Struct_Vector l26_fv = Module_vector.f_makeVector(64);
		Module_vector.f_pushVector(l26_fv, (new Struct_MizFixedVar(0, Module_correl.f_mizNewIncorTyp(), false, ((Struct)SingletonStructs.str_None), 0)));
		final Struct_Vector l27_$3 = l26_fv;
		final Struct_Vector l28_$12 = Module_correl.f_mizMake1BasedTyps();
		final Struct_Vector l29_$13 = Module_vector.f_makeVector(8);
		final Struct_Vector l30_$14 = Module_vector.f_makeVector(8);
		final Struct_MizTyp l31_$15 = Module_correl.f_mizNewIncorTyp();
		final Struct_MizTyp l32_$16 = Module_correl.f_mizNewIncorTyp();
		final Struct_Vector l33_st = Module_vector.f_makeVector(28);
		final Func1<Object,Integer> l35_$34 = (Func1<Object, Integer>)(Integer al36_0) -> {
			return Module_vector.f_pushVector(l33_st, SingletonStructs.str_None);
		};
		Module_runtime.f_fori(0, 26, l35_$34);
		final Struct_Vector l36_$4 = l33_st;
		final Func2<Struct_MizTyp,Struct_MizCorrelEnv, Struct> l37_$5 = (Func2<Struct_MizTyp, Struct_MizCorrelEnv, Struct>)(Struct_MizCorrelEnv ae, Struct al38_1) -> {
			return Module_correl.f_mizNewIncorTyp();
		};
		final Func3<Object,Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp> l38_$6 = (Func3<Object, Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp>)(Struct_MizCorrelEnv al39_0, Struct_MizAttrColl al40_1, Struct_MizTyp al41_2) -> {
			return null;
		};
		return (new Struct_MizCorrelEnv(l19_$7, l20_$8, l21_$9, l22_$10, true, 0, l23_$11, false, l25_$2, 0, l27_$3, 0, l28_$12, l29_$13, l30_$14, l31_$15, l32_$16, l36_$4, false, l37_$5, l38_$6));
	}
	public static final Struct_MizWithin f_mizMakeWithin(Func2<Struct,Struct_MizWithin, Struct> ap) {
		return (new Struct_MizWithin(ap, false, 0));
	}
	public static final Struct f_mizNewChoiceTrm(Struct_MizTyp atyp) {
		return (new Struct_MizChoiceTrm(atyp, 0, 0));
	}
	public static final Struct f_mizNewConj(Struct aa, Struct ab) {
		final int l0_sa = Module_correl.f_mizFrmSort(aa);
		final int l1_sb = Module_correl.f_mizFrmSort(ab);
		if (((l0_sa==Module_lexicon.g_ikError)||(l1_sb==Module_lexicon.g_ikError))) {
			return Module_correl.f_mizNewIncorFrm();
		} else {
			if ((l0_sa==Module_lexicon.g_ikFrmVerum)) {
				return ab;
			} else {
				if ((l0_sa==Module_lexicon.g_ikFrmConj)) {
					final Struct_MizConjFrm l2_ca = Module_correl.f_mizAsConjFrm(aa);
					if ((l1_sb==Module_lexicon.g_ikFrmConj)) {
						final Struct_MizConjFrm l3_cb = Module_correl.f_mizAsConjFrm(ab);
						final Struct_Vector l6_$5 = (l3_cb).f_conjuncts;
						final Func1<Object,Struct> l7_$4 = (Func1<Object, Struct>)(Struct af) -> {
							return Module_vector.f_pushVector((l2_ca).f_conjuncts, af);
						};
						Module_vector.f_iterVector(l6_$5, ((Func1<Object,Object>)(Func1)l7_$4));
					} else {
						if ((l1_sb!=Module_lexicon.g_ikFrmVerum)) {
							Module_vector.f_pushVector((l2_ca).f_conjuncts, ab);
						} else {
						}
					}
					return aa;
				} else {
					if ((l1_sb==Module_lexicon.g_ikFrmVerum)) {
						return aa;
					} else {
						if ((l1_sb==Module_lexicon.g_ikFrmConj)) {
							final Struct_MizConjFrm l8_cb2 = Module_correl.f_mizAsConjFrm(ab);
							Module_mobjects.f_mizVectorAtInsert((l8_cb2).f_conjuncts, 0, aa);
							return ab;
						} else {
							final Struct_Vector l9_v = Module_vector.f_makeVector(2);
							Module_vector.f_pushVector(l9_v, aa);
							Module_vector.f_pushVector(l9_v, ab);
							return Module_correl.f_mizNewConjFrm(l9_v);
						}
					}
				}
			}
		}
	}
	public static final Struct f_mizNewConjFrm(Struct_Vector alist) {
		final int l0_n = Module_vector.f_sizeVector(alist);
		if ((l0_n==0)) {
			return Module_correl.f_mizNewVerum();
		} else {
			if ((l0_n==1)) {
				return ((Struct)Module_vector.f_getVectorUnsafe(alist, 0));
			} else {
				return (new Struct_MizConjFrm(alist, 0));
			}
		}
	}
	public static final Struct_MizAttrColl f_mizNewEmptyCluster() {
		return (new Struct_MizAttrColl(Module_vector.f_makeVector(4), true));
	}
	public static final Struct f_mizNewEqFrm(Struct_MizCorrelEnv aenv, Struct aleft, Struct aright) {
		if (((Module_correl.f_mizTrmSort(aleft)==Module_lexicon.g_ikError)||(Module_correl.f_mizTrmSort(aright)==Module_lexicon.g_ikError))) {
			return Module_correl.f_mizNewIncorFrm();
		} else {
			return (new Struct_MizPredFrm(Module_lexicon.g_ikFrmPred, Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqEqualsTo), Module_correl.f_mizNewTrmList(aleft, Module_correl.f_mizNewTrmList(aright, ((Struct)SingletonStructs.str_MizTrmNil))), 0));
		}
	}
	public static final Struct f_mizNewFuncTrm(int afuncNr, Struct aargs) {
		if ((afuncNr==0)) {
			return Module_correl.f_mizNewIncorTrm();
		} else {
			return (new Struct_MizFuncTrm(Module_lexicon.g_ikTrmFunctor, afuncNr, aargs, 0, 0));
		}
	}
	public static final Struct f_mizNewIncorFrm() {
		return (new Struct_MizUniqFrm(Module_lexicon.g_ikError));
	}
	public static final Struct f_mizNewIncorTrm() {
		return (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0));
	}
	public static final Struct_MizTyp f_mizNewIncorTyp() {
		return (new Struct_MizTyp(Module_lexicon.g_ikError, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
	}
	public static final Struct f_mizNewItTrm() {
		return (new Struct_MizBaseTrm(Module_lexicon.g_ikTrmIt, 0));
	}
	public static final Struct f_mizNewLocFuncTrm(int asrt, int afuncNr, Struct aargs) {
		return (new Struct_MizFuncTrm(asrt, afuncNr, aargs, 0, 0));
	}
	public static final Struct f_mizNewLocPredFrm(int apredNr, Struct aargs, Struct aexpn) {
		return (new Struct_MizLocPredFrm(apredNr, aargs, aexpn, 0));
	}
	public static final Struct f_mizNewNeg(Struct aarg) {
		Struct l0__tmp = aarg;
		switch (l0__tmp.getTypeId()) {
		case 51/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l2_inner = l1__tmp.f_arg;
			final Struct l3_inner = (l1__tmp).f_arg;
			final int l4___ = (l1__tmp).f_pattNr;
			return l3_inner;
		}
		default: {
			if ((Module_correl.f_mizFrmSort(aarg)==Module_lexicon.g_ikError)) {
				return aarg;
			} else {
				return (new Struct_MizNegFrm(aarg, 0));
			}
		}
		}
	}
	public static final Struct f_mizNewPredFrm(int asrt, int apredNr, Struct aargs, int apattNr) {
		return (new Struct_MizPredFrm(asrt, apredNr, aargs, apattNr));
	}
	public static final Struct f_mizNewPrivFuncTrm(int afuncNr, Struct aargs, Struct aexpn) {
		return (new Struct_MizPrivFuncTrm(afuncNr, aargs, aexpn, 0, 0));
	}
	public static final Struct f_mizNewQuaTrm(Struct atrmProper, Struct_MizTyp atyp) {
		return (new Struct_MizQuaTrm(atrmProper, atyp, 0, 0));
	}
	public static final Struct f_mizNewQualFrm(Struct atrm, Struct_MizTyp atyp) {
		if ((((atyp).f_sort==Module_lexicon.g_ikError)||(Module_correl.f_mizTrmSort(atrm)==Module_lexicon.g_ikError))) {
			return Module_correl.f_mizNewIncorFrm();
		} else {
			return (new Struct_MizQualFrm(atrm, atyp, 0));
		}
	}
	public static final Struct_MizTyp f_mizNewStandardTyp(int asrt, Struct_MizAttrColl alower, Struct_MizAttrColl aupper, int amodNr, Struct aargs) {
		if ((amodNr!=0)) {
			return (new Struct_MizTyp(asrt, alower, aupper, amodNr, aargs, 0));
		} else {
			return Module_correl.f_mizNewIncorTyp();
		}
	}
	public static final Struct f_mizNewTrmList(Struct at, Struct arest) {
		if ((Module_correl.f_mizTrmSort(at)==Module_lexicon.g_ikError)) {
			return (new Struct_MizTrmElem(Module_correl.f_mizNewIncorTrm(), ((Struct)SingletonStructs.str_MizTrmNil)));
		} else {
			return (new Struct_MizTrmElem(at, arest));
		}
	}
	public static final Struct f_mizNewUniv(Struct_MizTyp aquant, Struct ascope) {
		if ((((aquant).f_sort==Module_lexicon.g_ikError)||(Module_correl.f_mizFrmSort(ascope)==Module_lexicon.g_ikError))) {
			return Module_correl.f_mizNewIncorFrm();
		} else {
			return (new Struct_MizUnivFrm(Module_lexicon.g_ikFrmUniv, 0, aquant, ascope, 0));
		}
	}
	public static final Struct f_mizNewVarTrm(int asrt, int anr) {
		return (new Struct_MizVarTrm(asrt, anr, 0, 0));
	}
	public static final Struct f_mizNewVerum() {
		return (new Struct_MizUniqFrm(Module_lexicon.g_ikFrmVerum));
	}
	public static final int f_mizPushConstr(Struct_MizCorrelEnv aenv, Struct_MizConstr ac) {
		Module_vector.f_pushVector(((Struct_Vector)((aenv).f_constrs[(ac).f_kind])), ac);
		return (Module_vector.f_sizeVector(((Struct_Vector)((aenv).f_constrs[(ac).f_kind])))-1);
	}
	public static final Object f_mizSetBoundVar(Struct_MizCorrelEnv aenv, int an, Struct_MizTyp atyp) {
		final Struct_MizCorrelEnv l3_env = aenv;
		final int l4_n = an;
		final Func0<Boolean> l2_$0 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector((l3_env).f_boundVar)<=l4_n);
		};
		final Struct_MizCorrelEnv l6_env = aenv;
		final Func0<Object> l5_$1 = (Func0<Object>)() -> {
			return Module_vector.f_pushVector((l6_env).f_boundVar, Module_correl.f_mizNewIncorTyp());
		};
		Module_loop.f_while(l2_$0, l5_$1);
		return Module_vector.f_setVectorUnsafe((aenv).f_boundVar, an, atyp);
	}
	public static final int f_mizSizeOfTrm(Struct at) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final Struct l2_args = l1__tmp.f_args;
			final int l3___ = (l1__tmp).f_sort;
			final int l4___ = (l1__tmp).f_funcNr;
			final Struct l5_args = (l1__tmp).f_args;
			final int l6___ = (l1__tmp).f_pattNr;
			final int l7___ = (l1__tmp).f_trmInfo;
			return (Module_correl.f_mizSizeOfTrmList(l5_args)+1);
		}
		case 59/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final Struct l8_funcExp = l1__tmp.f_funcExp;
			final int l9___ = (l1__tmp).f_funcNr;
			final Struct l10___ = (l1__tmp).f_args;
			final Struct l11_funcExp = (l1__tmp).f_funcExp;
			final int l12___ = (l1__tmp).f_pattNr;
			final int l13___ = (l1__tmp).f_trmInfo;
			{
				final Struct l14___tmp = l11_funcExp;
				at = l14___tmp;
				continue TAIL_CALL;
			}
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
			final Struct_MizTyp l15_typ = l1__tmp.f_choiceTyp;
			final Struct_MizTyp l16_typ = (l1__tmp).f_choiceTyp;
			final int l17___ = (l1__tmp).f_pattNr;
			final int l18___ = (l1__tmp).f_trmInfo;
			return Module_correl.f_mizSizeOfTyp(l16_typ);
		}
		case 60/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
			final Struct l19_trmProper = l1__tmp.f_trmProper;
			final Struct_MizTyp l20_qua = l1__tmp.f_qua;
			final Struct l21_trmProper = (l1__tmp).f_trmProper;
			final Struct_MizTyp l22_qua = (l1__tmp).f_qua;
			final int l23___ = (l1__tmp).f_pattNr;
			final int l24___ = (l1__tmp).f_trmInfo;
			return (Module_correl.f_mizSizeOfTrm(l21_trmProper)+Module_correl.f_mizSizeOfTyp(l22_qua));
		}
		default: {
			return 1;
		}
		}
	}
	}
	public static final int f_mizSizeOfTrmList(Struct al) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			return 0;
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			return (Module_correl.f_mizSizeOfTrm(l4_trm)+Module_correl.f_mizSizeOfTrmList(l5_next));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final int f_mizSizeOfTyp(Struct_MizTyp at) {
		if ((((at).f_sort==Module_lexicon.g_ikTypMode)||((at).f_sort==Module_lexicon.g_ikTypStruct))) {
			return (Module_correl.f_mizSizeOfTrmList((at).f_modArgs)+1);
		} else {
			return 1;
		}
	}
	public static final Struct f_mizSkipLocPred(Struct af0) {
		final Reference<Struct> l0_f = ((Reference<Struct>)(new Reference(af0)));
		final Reference<Boolean> l1_go = ((Reference<Boolean>)(new Reference(true)));
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return l1_go.value;
		};
		final Func0<Object> l5_$3 = (Func0<Object>)() -> {
			l1_go.value = ((Boolean)false);
			final Reference<Boolean> l6_stop = ((Reference<Boolean>)(new Reference(false)));
			final Func0<Boolean> l9_$7 = (Func0<Boolean>)() -> {
				return ((Boolean)(!((boolean)l6_stop.value)&&(Module_correl.f_mizFrmSort(l0_f.value)==Module_lexicon.g_ikFrmPrivPred)));
			};
			final Func0<Object> l10_$8 = (Func0<Object>)() -> {
				final Struct_MizLocPredFrm l11_lp = Module_correl.f_mizAsLocPredFrm(l0_f.value);
				if ((Module_correl.f_mizFrmSort((l11_lp).f_predExp)==Module_lexicon.g_ikError)) {
					l6_stop.value = ((Boolean)true);
					return null;
				} else {
					l0_f.value = (l11_lp).f_predExp;
					return null;
				}
			};
			Module_loop.f_while(l9_$7, l10_$8);
			if (!((boolean)l6_stop.value)) {
				final Struct l11_gsymswitch2 = l0_f.value;
				Struct l12__tmp = l11_gsymswitch2;
				switch (l12__tmp.getTypeId()) {
				case 51/*MizNegFrm*/: {
					final Struct_MizNegFrm l13__tmp = (Struct_MizNegFrm)l12__tmp;
					final Struct l14_arg = l13__tmp.f_arg;
					final Struct l15_arg = (l13__tmp).f_arg;
					final int l16___ = (l13__tmp).f_pattNr;
					if ((Module_correl.f_mizFrmSort(l15_arg)==Module_lexicon.g_ikFrmPrivPred)) {
						final Reference<Struct> l17_l = ((Reference<Struct>)(new Reference((Module_correl.f_mizAsLocPredFrm(l15_arg)).f_predExp)));
						if ((Module_correl.f_mizFrmSort(l17_l.value)!=Module_lexicon.g_ikError)) {
							final Func0<Boolean> l20_$18 = (Func0<Boolean>)() -> {
								return (Module_correl.f_mizFrmSort(l17_l.value)==Module_lexicon.g_ikFrmPrivPred);
							};
							final Func0<Object> l21_$19 = (Func0<Object>)() -> {
								l17_l.value = (Module_correl.f_mizAsLocPredFrm(l17_l.value)).f_predExp;
								return null;
							};
							Module_loop.f_while(l20_$18, l21_$19);
							if ((Module_correl.f_mizFrmSort(l17_l.value)==Module_lexicon.g_ikFrmNeg)) {
								l0_f.value = (Module_correl.f_mizAsNegFrm(l17_l.value)).f_arg;
								l1_go.value = (Module_correl.f_mizFrmSort(l0_f.value)==Module_lexicon.g_ikFrmPrivPred);
								return null;
							} else {
								return null;
							}
						} else {
							return null;
						}
					} else {
						return null;
					}
				}
				default: {
					return null;
				}
				}
			} else {
				return null;
			}
		};
		Module_loop.f_while(l4_$2, l5_$3);
		return l0_f.value;
	}
	public static final Struct f_mizSkipPrivFunc(Struct at) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 59/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final Struct l2_funcExp = l1__tmp.f_funcExp;
			final int l3___ = (l1__tmp).f_funcNr;
			final Struct l4___ = (l1__tmp).f_args;
			final Struct l5_funcExp = (l1__tmp).f_funcExp;
			final int l6___ = (l1__tmp).f_pattNr;
			final int l7___ = (l1__tmp).f_trmInfo;
			if ((Module_correl.f_mizTrmSort(l5_funcExp)==Module_lexicon.g_ikError)) {
				return l1__tmp;
			} else {
				{
					final Struct l8___tmp = l5_funcExp;
					at = l8___tmp;
					continue TAIL_CALL;
				}
			}
		}
		default: {
			return at;
		}
		}
	}
	}
	public static final Struct_Vector f_mizTrmList2Vector(Struct al) {
		final Struct_Vector l0_v = Module_vector.f_makeVector(8);
		final Reference<Struct> l1_cur = ((Reference<Struct>)(new Reference(al)));
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			final Struct l5_gsymswitch0 = l1_cur.value;
			Struct l6__tmp = l5_gsymswitch0;
			switch (l6__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return ((Boolean)false);
			}
			default: {
				return ((Boolean)true);
			}
			}
		};
		final Func0<Object> l5_$3 = (Func0<Object>)() -> {
			final Struct l6_gsymswitch1 = l1_cur.value;
			Struct l7__tmp = l6_gsymswitch1;
			switch (l7__tmp.getTypeId()) {
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l8__tmp = (Struct_MizTrmElem)l7__tmp;
				final Struct l9_trm = l8__tmp.f_trm;
				final Struct l10_next = l8__tmp.f_next;
				final Struct l11_trm = (l8__tmp).f_trm;
				final Struct l12_next = (l8__tmp).f_next;
				Module_vector.f_pushVector(l0_v, l11_trm);
				l1_cur.value = l12_next;
				return null;
			}
			case 69/*MizTrmNil*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l7__tmp.getTypeName());
			}
		};
		Module_loop.f_while(l4_$2, l5_$3);
		return l0_v;
	}
	public static final Struct f_mizTrmListDrop(Struct al, int an) {
		TAIL_CALL: for(;;) {
		if ((an<=0)) {
			return al;
		} else {
			Struct l0__tmp = al;
			switch (l0__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return al;
			}
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
				final Struct l2_next = l1__tmp.f_next;
				final Struct l3___ = (l1__tmp).f_trm;
				final Struct l4_next = (l1__tmp).f_next;
				{
					final Struct l5___tmp = l4_next;
					final int l6___tmp = (an-1);
					al = l5___tmp;
					an = l6___tmp;
					continue TAIL_CALL;
				}
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
			}
		}
	}
	}
	public static final int f_mizTrmListLength(Struct al) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			return 0;
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_next = l1__tmp.f_next;
			final Struct l3___ = (l1__tmp).f_trm;
			final Struct l4_next = (l1__tmp).f_next;
			return (1+Module_correl.f_mizTrmListLength(l4_next));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_mizTrmListNth(Struct al, int ai) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			Module_errhan.f_mizRunTimeError(2103);
			return Module_correl.f_mizNewIncorTrm();
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			if ((ai==0)) {
				return l4_trm;
			} else {
				{
					final Struct l6___tmp = l5_next;
					final int l7___tmp = (ai-1);
					al = l6___tmp;
					ai = l7___tmp;
					continue TAIL_CALL;
				}
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	}
	public static final int f_mizTrmSort(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 75/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_srt = (l1__tmp).f_sort;
			final int l4___ = (l1__tmp).f_varNr;
			final int l5___ = (l1__tmp).f_pattNr;
			final int l6___ = (l1__tmp).f_trmInfo;
			return l3_srt;
		}
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l7_srt = l1__tmp.f_sort;
			final int l8_srt = (l1__tmp).f_sort;
			final int l9___ = (l1__tmp).f_funcNr;
			final Struct l10___ = (l1__tmp).f_args;
			final int l11___ = (l1__tmp).f_pattNr;
			final int l12___ = (l1__tmp).f_trmInfo;
			return l8_srt;
		}
		case 59/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final int l13___ = (l1__tmp).f_funcNr;
			final Struct l14___ = (l1__tmp).f_args;
			final Struct l15___ = (l1__tmp).f_funcExp;
			final int l16___ = (l1__tmp).f_pattNr;
			final int l17___ = (l1__tmp).f_trmInfo;
			return Module_lexicon.g_ikTrmPrivFunc;
		}
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l1__tmp = (Struct_MizFraenkelTrm)l0__tmp;
			final Struct_Vector l18___ = (l1__tmp).f_lambdaArgs;
			final Struct l19___ = (l1__tmp).f_scope;
			final Struct l20___ = (l1__tmp).f_compr;
			final Struct_MizIntSeq l21___ = (l1__tmp).f_idents;
			final int l22___ = (l1__tmp).f_pattNr;
			final int l23___ = (l1__tmp).f_trmInfo;
			return Module_lexicon.g_ikTrmFraenkel;
		}
		case 60/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
			final Struct l24___ = (l1__tmp).f_trmProper;
			final Struct_MizTyp l25___ = (l1__tmp).f_qua;
			final int l26___ = (l1__tmp).f_pattNr;
			final int l27___ = (l1__tmp).f_trmInfo;
			return Module_lexicon.g_ikTrmQua;
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
			final Struct_MizTyp l28___ = (l1__tmp).f_choiceTyp;
			final int l29___ = (l1__tmp).f_pattNr;
			final int l30___ = (l1__tmp).f_trmInfo;
			return Module_lexicon.g_ikTrmChoice;
		}
		case 16/*MizBaseTrm*/: {
			final Struct_MizBaseTrm l1__tmp = (Struct_MizBaseTrm)l0__tmp;
			final int l31_srt = l1__tmp.f_sort;
			final int l32_srt = (l1__tmp).f_sort;
			final int l33___ = (l1__tmp).f_trmInfo;
			return l32_srt;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_mizVector2TrmList(Struct_Vector av) {
		final Reference<Struct> l0_r = ((Reference<Struct>)(new Reference(SingletonStructs.str_MizTrmNil)));
		final Reference<Integer> l1_i = ((Reference<Integer>)(new Reference((Module_vector.f_sizeVector(av)-1))));
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return (((int)l1_i.value)>=0);
		};
		final Struct_Vector l6_v = av;
		final Func0<Object> l5_$3 = (Func0<Object>)() -> {
			((Reference<Struct_MizTrmElem>)(Reference)l0_r).value = (new Struct_MizTrmElem(((Struct)Module_vector.f_getVectorUnsafe(l6_v, ((int)l1_i.value))), l0_r.value));
			l1_i.value = ((Integer)(((int)l1_i.value)-1));
			return null;
		};
		Module_loop.f_while(l4_$2, l5_$3);
		return l0_r.value;
	}
	public static final Object f_mizWithinCluster(Struct_MizWithin aw, Struct_MizAttrColl ac) {
		final Struct_Vector l2_$1 = (ac).f_items;
		final Struct_MizWithin l4_w = aw;
		final Func1<Object,Struct_MizAttr> l3_$0 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			return Module_correl.f_mizWithinTrmList(l4_w, (aa).f_args);
		};
		return Module_vector.f_iterVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
	}
	public static final Object f_mizWithinFrm(Struct_MizWithin aw, Struct af) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 56/*MizPredFrm*/: {
			final Struct_MizPredFrm l1__tmp = (Struct_MizPredFrm)l0__tmp;
			final Struct l2_args = l1__tmp.f_args;
			final int l3___ = (l1__tmp).f_sort;
			final int l4___ = (l1__tmp).f_predNr;
			final Struct l5_args = (l1__tmp).f_args;
			final int l6___ = (l1__tmp).f_pattNr;
			return Module_correl.f_mizWithinTrmList(aw, l5_args);
		}
		case 49/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l1__tmp = (Struct_MizLocPredFrm)l0__tmp;
			final Struct l7_args = l1__tmp.f_args;
			final Struct l8_expn = l1__tmp.f_predExp;
			final int l9___ = (l1__tmp).f_predNr;
			final Struct l10_args = (l1__tmp).f_args;
			final Struct l11_expn = (l1__tmp).f_predExp;
			final int l12___ = (l1__tmp).f_pattNr;
			Module_correl.f_mizWithinTrmList(aw, l10_args);
			{
				final Struct l13___tmp = l11_expn;
				af = l13___tmp;
				continue TAIL_CALL;
			}
		}
		case 61/*MizQualFrm*/: {
			final Struct_MizQualFrm l1__tmp = (Struct_MizQualFrm)l0__tmp;
			final Struct l14_trm = l1__tmp.f_trm;
			final Struct_MizTyp l15_typ = l1__tmp.f_typ;
			final Struct l16_trm = (l1__tmp).f_trm;
			final Struct_MizTyp l17_typ = (l1__tmp).f_typ;
			final int l18___ = (l1__tmp).f_pattNr;
			final Struct_MizQualFrm l19_f2 = Module_correl.f_mizAsQualFrm(l1__tmp);
			((Field_trm)l19_f2).set_trm(Module_correl.f_mizWithinTrm(aw, l16_trm));
			return Module_correl.f_mizWithinTyp(aw, l17_typ);
		}
		case 51/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l20_arg = l1__tmp.f_arg;
			final Struct l21_arg = (l1__tmp).f_arg;
			final int l22___ = (l1__tmp).f_pattNr;
			{
				final Struct l23___tmp = l21_arg;
				af = l23___tmp;
				continue TAIL_CALL;
			}
		}
		case 17/*MizBinFrm*/: {
			final Struct_MizBinFrm l1__tmp = (Struct_MizBinFrm)l0__tmp;
			final Struct l24_l = l1__tmp.f_left;
			final Struct l25_r = l1__tmp.f_right;
			final int l26___ = (l1__tmp).f_sort;
			final Struct l27_l = (l1__tmp).f_left;
			final Struct l28_r = (l1__tmp).f_right;
			final int l29___ = (l1__tmp).f_pattNr;
			Module_correl.f_mizWithinFrm(aw, l27_l);
			{
				final Struct l30___tmp = l28_r;
				af = l30___tmp;
				continue TAIL_CALL;
			}
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l31_conjuncts = l1__tmp.f_conjuncts;
			final Struct_Vector l32_conjuncts = (l1__tmp).f_conjuncts;
			final int l33___ = (l1__tmp).f_pattNr;
			final Struct_MizWithin l36_w = aw;
			final Func1<Object,Struct> l35_$34 = (Func1<Object, Struct>)(Struct ag) -> {
				return Module_correl.f_mizWithinFrm(l36_w, ag);
			};
			return Module_vector.f_iterVector(l32_conjuncts, ((Func1<Object,Object>)(Func1)l35_$34));
		}
		case 74/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l1__tmp = (Struct_MizUnivFrm)l0__tmp;
			final Struct_MizTyp l37_quantified = l1__tmp.f_quantified;
			final Struct l38_scope = l1__tmp.f_scope;
			final int l39___ = (l1__tmp).f_sort;
			final int l40___ = (l1__tmp).f_varId;
			final Struct_MizTyp l41_quantified = (l1__tmp).f_quantified;
			final Struct l42_scope = (l1__tmp).f_scope;
			final int l43___ = (l1__tmp).f_pattNr;
			if ((aw).f_incBound) {
				((Field_boundVarNbr)aw).set_boundVarNbr(((aw).f_boundVarNbr+1));
			} else {
			}
			Module_correl.f_mizWithinTyp(aw, l41_quantified);
			Module_correl.f_mizWithinFrm(aw, l42_scope);
			if ((aw).f_incBound) {
				((Field_boundVarNbr)aw).set_boundVarNbr(((aw).f_boundVarNbr-1));
				return null;
			} else {
				return null;
			}
		}
		case 73/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l1__tmp = (Struct_MizUniqFrm)l0__tmp;
			final int l44___ = (l1__tmp).f_sort;
			return null;
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l1__tmp = (Struct_MizFlexFrm)l0__tmp;
			final Struct l45_lo = l1__tmp.f_leftOrig;
			final Struct l46_ro = l1__tmp.f_rightOrig;
			final Struct l47_lt = l1__tmp.f_leftTrm;
			final Struct l48_rt = l1__tmp.f_rightTrm;
			final Struct l49_expn = l1__tmp.f_expansion;
			final int l50___ = (l1__tmp).f_sort;
			final Struct l51_lo = (l1__tmp).f_leftOrig;
			final Struct l52_ro = (l1__tmp).f_rightOrig;
			final Struct l53_lt = (l1__tmp).f_leftTrm;
			final Struct l54_rt = (l1__tmp).f_rightTrm;
			final Struct l55_expn = (l1__tmp).f_expansion;
			final int l56___ = (l1__tmp).f_pattNr;
			Module_correl.f_mizWithinFrm(aw, l51_lo);
			Module_correl.f_mizWithinFrm(aw, l52_ro);
			((Field_leftTrm)l1__tmp).set_leftTrm(Module_correl.f_mizWithinTrm(aw, l53_lt));
			((Field_rightTrm)l1__tmp).set_rightTrm(Module_correl.f_mizWithinTrm(aw, l54_rt));
			{
				final Struct l57___tmp = l55_expn;
				af = l57___tmp;
				continue TAIL_CALL;
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	}
	public static final Struct f_mizWithinTrm(Struct_MizWithin aw, Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 75/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_srt = (l1__tmp).f_sort;
			final int l4___ = (l1__tmp).f_varNr;
			final int l5___ = (l1__tmp).f_pattNr;
			final int l6___ = (l1__tmp).f_trmInfo;
			if ((l3_srt==Module_lexicon.g_ikTrmNumeral)) {
				return l1__tmp;
			} else {
				return (aw).f_p.invoke(aw, l1__tmp);
			}
		}
		case 16/*MizBaseTrm*/: {
			final Struct_MizBaseTrm l1__tmp = (Struct_MizBaseTrm)l0__tmp;
			final int l7_srt = l1__tmp.f_sort;
			final int l8_srt = (l1__tmp).f_sort;
			final int l9___ = (l1__tmp).f_trmInfo;
			if ((l8_srt==Module_lexicon.g_ikTrmIt)) {
				return (aw).f_p.invoke(aw, l1__tmp);
			} else {
				return l1__tmp;
			}
		}
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final Struct l10_args = l1__tmp.f_args;
			final int l11___ = (l1__tmp).f_sort;
			final int l12___ = (l1__tmp).f_funcNr;
			final Struct l13_args = (l1__tmp).f_args;
			final int l14___ = (l1__tmp).f_pattNr;
			final int l15___ = (l1__tmp).f_trmInfo;
			Module_correl.f_mizWithinTrmList(aw, l13_args);
			return l1__tmp;
		}
		case 59/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final Struct l16_args = l1__tmp.f_args;
			final Struct l17_funcExp = l1__tmp.f_funcExp;
			final int l18___ = (l1__tmp).f_funcNr;
			final Struct l19_args = (l1__tmp).f_args;
			final Struct l20_funcExp = (l1__tmp).f_funcExp;
			final int l21___ = (l1__tmp).f_pattNr;
			final int l22___ = (l1__tmp).f_trmInfo;
			Module_correl.f_mizWithinTrmList(aw, l19_args);
			final Struct_MizPrivFuncTrm l23_t2 = Module_correl.f_mizAsPrivFuncTrm(l1__tmp);
			((Field_funcExp)l23_t2).set_funcExp(Module_correl.f_mizWithinTrm(aw, l20_funcExp));
			return l1__tmp;
		}
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l1__tmp = (Struct_MizFraenkelTrm)l0__tmp;
			final Struct_Vector l24_lambdaArgs = l1__tmp.f_lambdaArgs;
			final Struct l25_scope = l1__tmp.f_scope;
			final Struct l26_compr = l1__tmp.f_compr;
			final Struct_Vector l27_lambdaArgs = (l1__tmp).f_lambdaArgs;
			final Struct l28_scope = (l1__tmp).f_scope;
			final Struct l29_compr = (l1__tmp).f_compr;
			final Struct_MizIntSeq l30___ = (l1__tmp).f_idents;
			final int l31___ = (l1__tmp).f_pattNr;
			final int l32___ = (l1__tmp).f_trmInfo;
			final Struct_MizWithin l35_w = aw;
			final Func1<Object,Struct_MizTyp> l34_$33 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp atyp) -> {
				if ((l35_w).f_incBound) {
					((Field_boundVarNbr)l35_w).set_boundVarNbr(((l35_w).f_boundVarNbr+1));
				} else {
				}
				return Module_correl.f_mizWithinTyp(l35_w, atyp);
			};
			Module_vector.f_iterVector(l27_lambdaArgs, ((Func1<Object,Object>)(Func1)l34_$33));
			final Struct_MizFraenkelTrm l36_t2 = Module_correl.f_mizAsFraenkelTrm(l1__tmp);
			((Field_scope)l36_t2).set_scope(Module_correl.f_mizWithinTrm(aw, l28_scope));
			Module_correl.f_mizWithinFrm(aw, l29_compr);
			if ((aw).f_incBound) {
				((Field_boundVarNbr)aw).set_boundVarNbr(((aw).f_boundVarNbr-Module_vector.f_sizeVector(l27_lambdaArgs)));
			} else {
			}
			return l1__tmp;
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
			final Struct_MizTyp l37_typ = l1__tmp.f_choiceTyp;
			final Struct_MizTyp l38_typ = (l1__tmp).f_choiceTyp;
			final int l39___ = (l1__tmp).f_pattNr;
			final int l40___ = (l1__tmp).f_trmInfo;
			Module_correl.f_mizWithinTyp(aw, l38_typ);
			return l1__tmp;
		}
		case 60/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
			final Struct l41_trmProper = l1__tmp.f_trmProper;
			final Struct_MizTyp l42_qua = l1__tmp.f_qua;
			final Struct l43_trmProper = (l1__tmp).f_trmProper;
			final Struct_MizTyp l44_qua = (l1__tmp).f_qua;
			final int l45___ = (l1__tmp).f_pattNr;
			final int l46___ = (l1__tmp).f_trmInfo;
			final Struct_MizQuaTrm l47_t2 = Module_correl.f_mizAsQuaTrm(l1__tmp);
			((Field_trmProper)l47_t2).set_trmProper(Module_correl.f_mizWithinTrm(aw, l43_trmProper));
			Module_correl.f_mizWithinTyp(aw, l44_qua);
			return l1__tmp;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Object f_mizWithinTrmList(Struct_MizWithin aw, Struct al) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			return null;
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			final Struct_MizTrmElem l6_e = Module_correl.f_mizAsTrmElem(l1__tmp);
			((Field_trm)l6_e).set_trm(Module_correl.f_mizWithinTrm(aw, l4_trm));
			{
				final Struct l7___tmp = l5_next;
				al = l7___tmp;
				continue TAIL_CALL;
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	}
	public static final Object f_mizWithinTyp(Struct_MizWithin aw, Struct_MizTyp at) {
		Module_correl.f_mizWithinCluster(aw, (at).f_lower);
		Module_correl.f_mizWithinCluster(aw, (at).f_upper);
		return Module_correl.f_mizWithinTrmList(aw, (at).f_modArgs);
	}
	public static final Object f_mizWithinTypVector(Struct_MizWithin aw, Struct_Vector av) {
		final Struct_MizWithin l2_w = aw;
		final Func1<Object,Struct_MizTyp> l1_$0 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp at) -> {
			return Module_correl.f_mizWithinTyp(l2_w, at);
		};
		return Module_vector.f_iterVector(av, ((Func1<Object,Object>)(Func1)l1_$0));
	}
}
