// HASH COLLISIONS: YES
// timestamp: 1785506002000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_correl_match {
	public static final boolean f_mizAttrEquals(Struct_MizCorrelEnv aenv, Struct_MizAttr aa, Struct_MizAttr ab) {
		final Struct_Pair l0_pa = Module_correl.f_mizAdjustAttr(aenv, aa);
		final Struct_Pair l1_pb = Module_correl.f_mizAdjustAttr(aenv, ab);
		return ((((int)(l0_pa).f_first)==((int)(l1_pb).f_first))&&((aa).f_neg==(ab).f_neg));
	}
	public static final boolean f_mizCheckLociTypes(Struct_MizCorrelEnv aenv, Struct_Vector alist) {
		final int l0_n = Module_vector.f_sizeVector(alist);
		final Struct_Vector l1_snaps = Module_vector.f_makeVector((l0_n+1));
		final Func1<Object,Integer> l3_$2 = (Func1<Object, Integer>)(Integer al4_0) -> {
			return Module_vector.f_pushVector(l1_snaps, Module_vector.f_makeVector(0));
		};
		Module_runtime.f_fori(0, l0_n, l3_$2);
		final Struct_Vector l4_substTyp = Module_vector.f_makeVector((l0_n+1));
		final Func1<Object,Integer> l6_$5 = (Func1<Object, Integer>)(Integer al7_0) -> {
			return Module_vector.f_pushVector(l4_substTyp, SingletonStructs.str_None);
		};
		Module_runtime.f_fori(0, l0_n, l6_$5);
		final Struct_MizCorrelEnv l8_env = aenv;
		final Func1<Integer,Integer> l7_firstFilled = (Func1<Integer, Integer>)(Integer afrom) -> {
			final Reference<Integer> l8_k = ((Reference<Integer>)(new Reference(afrom)));
			final Func0<Boolean> l11_$9 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l8_k.value)!=0)&&Module_maybe.f_isNone(Module_correl_match.f_mizSubstTrmGet(l8_env, ((int)l8_k.value)))));
			};
			final Func0<Object> l12_$10 = (Func0<Object>)() -> {
				l8_k.value = ((Integer)(((int)l8_k.value)-1));
				return null;
			};
			Module_loop.f_while(l11_$9, l12_$10);
			return l8_k.value;
		};
		final Reference<Integer> l9_locusNr = ((Reference<Integer>)(new Reference(l7_firstFilled.invoke(((Integer)l0_n)))));
		final Reference<Boolean> l10_result = ((Reference<Boolean>)(new Reference(true)));
		final Reference<Boolean> l11_finished = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Struct> l12_pending = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
		final Reference<Integer> l13_mode = ((Reference<Integer>)(new Reference(0)));
		final Struct_Vector l14_valSnaps = Module_vector.f_makeVector((l0_n+1));
		final Func1<Object,Integer> l16_$15 = (Func1<Object, Integer>)(Integer al17_0) -> {
			return Module_vector.f_pushVector(l14_valSnaps, Module_vector.f_makeVector(0));
		};
		Module_runtime.f_fori(0, l0_n, l16_$15);
		final Func0<Boolean> l19_$17 = (Func0<Boolean>)() -> {
			return ((Boolean)!((boolean)l11_finished.value));
		};
		final Struct_MizCorrelEnv l21_env = aenv;
		final Struct_Vector l22_list = alist;
		final Func0<Object> l20_$18 = (Func0<Object>)() -> {
			if ((((int)l13_mode.value)==0)) {
				if ((((int)l9_locusNr.value)==0)) {
					l11_finished.value = ((Boolean)true);
					return null;
				} else {
					Module_vector.f_setVectorUnsafe(l1_snaps, (((int)l9_locusNr.value)-1), Module_correl_match.f_mizSnapshotSubst(l21_env));
					Module_vector.f_setVectorUnsafe(l14_valSnaps, (((int)l9_locusNr.value)-1), Module_vector.f_copyVector((l21_env).f_substTrm));
					final Struct l21_trm = ((Struct)Module_maybe.f_either(Module_correl_match.f_mizSubstTrmGet(l21_env, ((int)l9_locusNr.value)), Module_correl.f_mizNewIncorTrm()));
					Struct_MizTyp l22_wTyp;
					if ((l21_env).f_itIsChecker) {
						l22_wTyp=(l21_env).f_roundUpTrmType.invoke(l21_env, l21_trm);
					} else {
						l22_wTyp=Module_correl_inst.f_mizGetTrmType(l21_env, l21_trm);
					}
					l9_locusNr.value = ((Integer)(((int)l9_locusNr.value)-1));
					Module_correl_match.f_mizRestoreSubst(l21_env, ((Struct_Vector)Module_vector.f_getVectorUnsafe(l14_valSnaps, ((int)l9_locusNr.value))));
					final Struct_MizTyp l23_target = ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l22_list, ((int)l9_locusNr.value)));
					final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l25_$24 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
						return ((Boolean)Module_correl_match.f_mizEsAttrRev(l21_env, ax, ay));
					};
					if (!Module_correl_inst.f_mizDecreasingAttrs(l21_env, l22_wTyp, l23_target, l25_$24)) {
						l13_mode.value = ((Integer)2);
						return null;
					} else {
						((Reference<Struct_Some>)(Reference)l12_pending).value = (new Struct_Some(l22_wTyp));
						l13_mode.value = ((Integer)1);
						return null;
					}
				}
			} else {
				if ((((int)l13_mode.value)==1)) {
					final Struct_MizTyp l26_target = ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l22_list, ((int)l9_locusNr.value)));
					final Struct l27_w = Module_correl_inst.f_mizWideningOf(l21_env, l26_target, ((Struct_MizTyp)Module_maybe.f_either(l12_pending.value, Module_correl.f_mizNewIncorTyp())));
					Struct l28__tmp = l27_w;
					switch (l28__tmp.getTypeId()) {
					case 88/*Some*/: {
						final Struct_Some l29__tmp = (Struct_Some)l28__tmp;
						final Object l30_wt = l29__tmp.f_value;
						final Struct_MizTyp l31_wt = ((Struct_MizTyp)(l29__tmp).f_value);
						Module_vector.f_setVectorUnsafe(l4_substTyp, ((int)l9_locusNr.value), (new Struct_Some(l31_wt)));
						if (Module_correl_match.f_mizCompEsTyp(l21_env, l26_target, l31_wt, false)) {
							l9_locusNr.value = l7_firstFilled.invoke(l9_locusNr.value);
							l13_mode.value = ((Integer)0);
							return null;
						} else {
							Module_correl_match.f_mizResetNewerSubst(l21_env, ((Struct_Vector)Module_vector.f_getVectorUnsafe(l1_snaps, ((int)l9_locusNr.value))));
							l13_mode.value = ((Integer)2);
							return null;
						}
					}
					case 80/*None*/: {
						l13_mode.value = ((Integer)2);
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l28__tmp.getTypeName());
					}
				} else {
					Module_vector.f_setVectorUnsafe(l4_substTyp, ((int)l9_locusNr.value), SingletonStructs.str_None);
					final Reference<Boolean> l32_found = ((Reference<Boolean>)(new Reference(false)));
					final Func0<Boolean> l35_$33 = (Func0<Boolean>)() -> {
						return ((Boolean)(!((boolean)l32_found.value)&&!((boolean)l11_finished.value)));
					};
					final Func0<Object> l36_$34 = (Func0<Object>)() -> {
						final Reference<Integer> l37_ll = ((Reference<Integer>)(new Reference((((int)l9_locusNr.value)+1))));
						final Func0<Boolean> l40_$38 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l37_ll.value)<l0_n)&&Module_maybe.f_isNone(Module_correl_match.f_mizSubstTrmGet(l21_env, (((int)l37_ll.value)+1)))));
						};
						final Func0<Object> l41_$39 = (Func0<Object>)() -> {
							l37_ll.value = ((Integer)(((int)l37_ll.value)+1));
							return null;
						};
						Module_loop.f_while(l40_$38, l41_$39);
						if ((((int)l37_ll.value)>=l0_n)) {
							Module_correl_match.f_mizDisposeSubstTrm(l21_env);
							l10_result.value = ((Boolean)false);
							l11_finished.value = ((Boolean)true);
							return null;
						} else {
							l9_locusNr.value = l37_ll.value;
							final Struct l42_st = ((Struct)Module_vector.f_getVectorUnsafe(l4_substTyp, ((int)l9_locusNr.value)));
							final Struct_MizTyp l43_target2 = ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l22_list, ((int)l9_locusNr.value)));
							final Struct_MizConstr l44_c2 = Module_correl.f_mizConstr(l21_env, Module_correl.g_coMode, (l43_target2).f_modNr);
							boolean l45_okLevel;
							Struct l46__tmp = l42_st;
							switch (l46__tmp.getTypeId()) {
							case 88/*Some*/: {
								final Struct_Some l47__tmp = (Struct_Some)l46__tmp;
								final Object l48_stt = l47__tmp.f_value;
								final Struct_MizTyp l49_stt = ((Struct_MizTyp)(l47__tmp).f_value);
								l45_okLevel=(((((l49_stt).f_sort==Module_lexicon.g_ikTypMode)||((l49_stt).f_modNr!=Module_builtin.f_mizBuiltIn((l21_env).f_builtIn, Module_builtin.g_rqAny)))&&((l43_target2).f_sort==Module_lexicon.g_ikTypMode))&&((l44_c2).f_whichConstrNr==0));
								break;
							}
							case 80/*None*/: {
								l45_okLevel=false;
								break;
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l46__tmp.getTypeName());
							}
							if (l45_okLevel) {
								l32_found.value = ((Boolean)true);
								return null;
							} else {
								return Module_vector.f_setVectorUnsafe(l4_substTyp, ((int)l9_locusNr.value), SingletonStructs.str_None);
							}
						}
					};
					Module_loop.f_while(l35_$33, l36_$34);
					if (!((boolean)l11_finished.value)) {
						final Struct l37_st2 = ((Struct)Module_vector.f_getVectorUnsafe(l4_substTyp, ((int)l9_locusNr.value)));
						Module_vector.f_setVectorUnsafe(l4_substTyp, ((int)l9_locusNr.value), SingletonStructs.str_None);
						Struct l38__tmp = l37_st2;
						switch (l38__tmp.getTypeId()) {
						case 88/*Some*/: {
							final Struct_Some l39__tmp = (Struct_Some)l38__tmp;
							final Object l40_stt2 = l39__tmp.f_value;
							final Struct_MizTyp l41_stt2 = ((Struct_MizTyp)(l39__tmp).f_value);
							final Struct l42_gsymswitch3 = Module_correl_inst.f_mizWidening(l21_env, l41_stt2);
							Struct l43__tmp = l42_gsymswitch3;
							switch (l43__tmp.getTypeId()) {
							case 88/*Some*/: {
								final Struct_Some l44__tmp = (Struct_Some)l43__tmp;
								final Object l45_w2 = l44__tmp.f_value;
								final Struct_MizTyp l46_w2 = ((Struct_MizTyp)(l44__tmp).f_value);
								Module_correl_match.f_mizResetNewerSubst(l21_env, ((Struct_Vector)Module_vector.f_getVectorUnsafe(l1_snaps, ((int)l9_locusNr.value))));
								((Reference<Struct_Some>)(Reference)l12_pending).value = (new Struct_Some(l46_w2));
								l13_mode.value = ((Integer)1);
								return null;
							}
							case 80/*None*/: {
								return null;
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l43__tmp.getTypeName());
							}
						}
						case 80/*None*/: {
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l38__tmp.getTypeName());
						}
					} else {
						return null;
					}
				}
			}
		};
		Module_loop.f_while(l19_$17, l20_$18);
		return ((boolean)l10_result.value);
	}
	public static final boolean f_mizCompEsTyp(Struct_MizCorrelEnv aenv, Struct_MizTyp af, Struct_MizTyp aa, boolean aexactly) {
		if (((af).f_sort!=(aa).f_sort)) {
			return false;
		} else {
			if (((af).f_modNr==(aa).f_modNr)) {
				return Module_correl_match.f_mizEsTrmList(aenv, (af).f_modArgs, (aa).f_modArgs);
			} else {
				if ((((af).f_sort==Module_lexicon.g_ikTypMode)&&!aexactly)) {
					final Struct_Pair l0_pa = Module_correl.f_mizAdjustTyp(aenv, aa);
					return (((af).f_modNr==((int)(l0_pa).f_first))&&Module_correl_match.f_mizEsTrmList(aenv, (af).f_modArgs, ((Struct)(l0_pa).f_second)));
				} else {
					return false;
				}
			}
		}
	}
	public static final Object f_mizDisposeSubstTrm(Struct_MizCorrelEnv aenv) {
		final int l2_$1 = (Module_vector.f_sizeVector((aenv).f_substTrm)-1);
		final Struct_MizCorrelEnv l4_env = aenv;
		final Func1<Object,Integer> l3_$0 = (Func1<Object, Integer>)(Integer ak) -> {
			return Module_vector.f_setVectorUnsafe((l4_env).f_substTrm, ((int)ak), SingletonStructs.str_None);
		};
		return Module_runtime.f_fori(1, l2_$1, l3_$0);
	}
	public static final Struct_MizChoiceTrm f_mizEsAsChoice(Struct at) {
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
	public static final Struct_MizConjFrm f_mizEsAsConj(Struct af) {
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
	public static final Struct_MizFraenkelTrm f_mizEsAsFraenkel(Struct at) {
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
	public static final Struct_MizFuncTrm f_mizEsAsFunc(Struct at) {
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
	public static final Struct_MizLocPredFrm f_mizEsAsLocPred(Struct af) {
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
	public static final Struct_MizNegFrm f_mizEsAsNeg(Struct af) {
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
	public static final Struct_MizPredFrm f_mizEsAsPred(Struct af) {
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
	public static final Struct_MizPrivFuncTrm f_mizEsAsPriv(Struct at) {
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
	public static final Struct_MizQuaTrm f_mizEsAsQua(Struct at) {
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
	public static final Struct_MizQualFrm f_mizEsAsQual(Struct af) {
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
	public static final Struct_MizUnivFrm f_mizEsAsUniv(Struct af) {
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
	public static final Struct_MizVarTrm f_mizEsAsVar(Struct at) {
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
	public static final boolean f_mizEsAttr(Struct_MizCorrelEnv aenv, Struct_MizAttr af, Struct_MizAttr aa) {
		final Struct_Pair l0_pf = Module_correl.f_mizAdjustAttr(aenv, af);
		final Struct_Pair l1_pa = Module_correl.f_mizAdjustAttr(aenv, aa);
		return (((((int)(l0_pf).f_first)==((int)(l1_pa).f_first))&&((af).f_neg==(aa).f_neg))&&Module_correl_match.f_mizEsTrmList(aenv, ((Struct)(l0_pf).f_second), ((Struct)(l1_pa).f_second)));
	}
	public static final boolean f_mizEsAttrRev(Struct_MizCorrelEnv aenv, Struct_MizAttr af, Struct_MizAttr aa) {
		return Module_correl_match.f_mizEsAttr(aenv, aa, af);
	}
	public static final boolean f_mizEsFrm(Struct_MizCorrelEnv aenv, Struct af0, Struct aa0) {
		TAIL_CALL: for(;;) {
		final Struct l0_f = Module_correl.f_mizSkipLocPred(af0);
		final Struct l1_a = Module_correl.f_mizSkipLocPred(aa0);
		final int l2_sf = Module_correl.f_mizFrmSort(l0_f);
		if ((l2_sf!=Module_correl.f_mizFrmSort(l1_a))) {
			return false;
		} else {
			if (((l2_sf==Module_lexicon.g_ikFrmVerum)||(l2_sf==Module_lexicon.g_ikFrmThesis))) {
				return true;
			} else {
				Struct l3__tmp = l0_f;
				switch (l3__tmp.getTypeId()) {
				case 51/*MizNegFrm*/: {
					final Struct_MizNegFrm l4__tmp = (Struct_MizNegFrm)l3__tmp;
					final Struct l5_arg = l4__tmp.f_arg;
					final Struct l6_arg = (l4__tmp).f_arg;
					final int l7___ = (l4__tmp).f_pattNr;
					final Struct_MizNegFrm l8_na = Module_correl_match.f_mizEsAsNeg(l1_a);
					{
						final Struct l9___tmp = l6_arg;
						final Struct l10___tmp = (l8_na).f_arg;
						af0 = l9___tmp;
						aa0 = l10___tmp;
						continue TAIL_CALL;
					}
				}
				case 61/*MizQualFrm*/: {
					final Struct_MizQualFrm l4__tmp = (Struct_MizQualFrm)l3__tmp;
					final Struct l11_trm = l4__tmp.f_trm;
					final Struct_MizTyp l12_typ = l4__tmp.f_typ;
					final Struct l13_trm = (l4__tmp).f_trm;
					final Struct_MizTyp l14_typ = (l4__tmp).f_typ;
					final int l15___ = (l4__tmp).f_pattNr;
					final Struct_MizQualFrm l16_qa = Module_correl_match.f_mizEsAsQual(l1_a);
					return (Module_correl_match.f_mizEsTrm(aenv, l13_trm, (l16_qa).f_trm)&&Module_correl_match.f_mizEsTyp(aenv, l14_typ, (l16_qa).f_typ));
				}
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l4__tmp = (Struct_MizConjFrm)l3__tmp;
					final Struct_Vector l17_conjuncts = l4__tmp.f_conjuncts;
					final Struct_Vector l18_conjuncts = (l4__tmp).f_conjuncts;
					final int l19___ = (l4__tmp).f_pattNr;
					final Struct_MizConjFrm l20_ca = Module_correl_match.f_mizEsAsConj(l1_a);
					if ((Module_vector.f_sizeVector(l18_conjuncts)!=Module_vector.f_sizeVector((l20_ca).f_conjuncts))) {
						return false;
					} else {
						final Reference<Boolean> l21_ok = ((Reference<Boolean>)(new Reference(true)));
						final Reference<Integer> l22_i = ((Reference<Integer>)(new Reference(0)));
						final Func0<Boolean> l25_$23 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l22_i.value)<Module_vector.f_sizeVector(l18_conjuncts))&&((boolean)l21_ok.value)));
						};
						final Struct_MizCorrelEnv l27_env = aenv;
						final Func0<Object> l26_$24 = (Func0<Object>)() -> {
							if (!Module_correl_match.f_mizEsFrm(l27_env, ((Struct)Module_vector.f_getVectorUnsafe(l18_conjuncts, ((int)l22_i.value))), ((Struct)Module_vector.f_getVectorUnsafe((l20_ca).f_conjuncts, ((int)l22_i.value))))) {
								l21_ok.value = ((Boolean)false);
							} else {
							}
							l22_i.value = ((Integer)(((int)l22_i.value)+1));
							return null;
						};
						Module_loop.f_while(l25_$23, l26_$24);
						return ((boolean)l21_ok.value);
					}
				}
				case 56/*MizPredFrm*/: {
					final Struct_MizPredFrm l4__tmp = (Struct_MizPredFrm)l3__tmp;
					final int l28_srt = l4__tmp.f_sort;
					final int l29_predNr = l4__tmp.f_predNr;
					final Struct l30_args = l4__tmp.f_args;
					final int l31_srt = (l4__tmp).f_sort;
					final int l32_predNr = (l4__tmp).f_predNr;
					final Struct l33_args = (l4__tmp).f_args;
					final int l34___ = (l4__tmp).f_pattNr;
					final Struct_MizPredFrm l35_pa = Module_correl_match.f_mizEsAsPred(l1_a);
					if ((l32_predNr==(l35_pa).f_predNr)) {
						return Module_correl_match.f_mizEsTrmList(aenv, l33_args, (l35_pa).f_args);
					} else {
						if ((l31_srt==Module_lexicon.g_ikFrmAttr)) {
							final Struct_Pair l36_q = Module_correl.f_mizAdjustAttrFrm(aenv, l35_pa);
							return ((l32_predNr==((int)(l36_q).f_first))&&Module_correl_match.f_mizEsTrmList(aenv, l33_args, ((Struct)(l36_q).f_second)));
						} else {
							if ((l31_srt==Module_lexicon.g_ikFrmPred)) {
								final Struct_Pair l37_q = Module_correl.f_mizAdjustFrm(aenv, l35_pa);
								return ((l32_predNr==((int)(l37_q).f_first))&&Module_correl_match.f_mizEsTrmList(aenv, l33_args, ((Struct)(l37_q).f_second)));
							} else {
								return false;
							}
						}
					}
				}
				case 49/*MizLocPredFrm*/: {
					final Struct_MizLocPredFrm l4__tmp = (Struct_MizLocPredFrm)l3__tmp;
					final int l38_predNr = l4__tmp.f_predNr;
					final Struct l39_args = l4__tmp.f_args;
					final int l40_predNr = (l4__tmp).f_predNr;
					final Struct l41_args = (l4__tmp).f_args;
					final Struct l42___ = (l4__tmp).f_predExp;
					final int l43___ = (l4__tmp).f_pattNr;
					final Struct_MizLocPredFrm l44_la = Module_correl_match.f_mizEsAsLocPred(l1_a);
					return ((l40_predNr==(l44_la).f_predNr)&&Module_correl_match.f_mizEsTrmList(aenv, l41_args, (l44_la).f_args));
				}
				case 74/*MizUnivFrm*/: {
					final Struct_MizUnivFrm l4__tmp = (Struct_MizUnivFrm)l3__tmp;
					final Struct_MizTyp l45_quantified = l4__tmp.f_quantified;
					final Struct l46_scope = l4__tmp.f_scope;
					final int l47___ = (l4__tmp).f_sort;
					final int l48___ = (l4__tmp).f_varId;
					final Struct_MizTyp l49_quantified = (l4__tmp).f_quantified;
					final Struct l50_scope = (l4__tmp).f_scope;
					final int l51___ = (l4__tmp).f_pattNr;
					final Struct_MizUnivFrm l52_ua = Module_correl_match.f_mizEsAsUniv(l1_a);
					return (Module_correl_match.f_mizEsTyp(aenv, l49_quantified, (l52_ua).f_quantified)&&Module_correl_match.f_mizEsFrm(aenv, l50_scope, (l52_ua).f_scope));
				}
				default: {
					Module_errhan.f_mizRunTimeError(2103);
					return false;
				}
				}
			}
		}
	}
	}
	public static final boolean f_mizEsTrm(Struct_MizCorrelEnv aenv, Struct af0, Struct aa) {
		TAIL_CALL: for(;;) {
		final int l0_sf = Module_correl.f_mizTrmSort(af0);
		if ((l0_sf==Module_lexicon.g_ikTrmLocus)) {
			final Struct_MizVarTrm l1_vf = Module_correl_match.f_mizEsAsVar(af0);
			final Struct l2_gsymswitch2 = Module_correl_match.f_mizSubstTrmGet(aenv, (l1_vf).f_varNr);
			Struct l3__tmp = l2_gsymswitch2;
			switch (l3__tmp.getTypeId()) {
			case 80/*None*/: {
				Module_correl_match.f_mizSubstTrmSet(aenv, (l1_vf).f_varNr, (new Struct_Some(Module_correl.f_mizCopyTrm(aa))));
				return true;
			}
			case 88/*Some*/: {
				final Struct_Some l4__tmp = (Struct_Some)l3__tmp;
				final Object l5_bound = l4__tmp.f_value;
				final Struct l6_bound = ((Struct)(l4__tmp).f_value);
				if ((Module_correl.f_mizTrmSort(l6_bound)==Module_lexicon.g_ikTrmQua)) {
					final Struct_MizQuaTrm l7_qb = Module_correl_match.f_mizEsAsQua(l6_bound);
					return Module_correl.f_mizEqTrm(aenv, aa, (l7_qb).f_trmProper);
				} else {
					return Module_correl.f_mizEqTrm(aenv, aa, l6_bound);
				}
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l3__tmp.getTypeName());
			}
		} else {
			final int l8_sa = Module_correl.f_mizTrmSort(aa);
			if ((l0_sf==l8_sa)) {
				Struct l9__tmp = af0;
				switch (l9__tmp.getTypeId()) {
				case 75/*MizVarTrm*/: {
					final Struct_MizVarTrm l10__tmp = (Struct_MizVarTrm)l9__tmp;
					final int l11_varNr = l10__tmp.f_varNr;
					final int l12___ = (l10__tmp).f_sort;
					final int l13_varNr = (l10__tmp).f_varNr;
					final int l14___ = (l10__tmp).f_pattNr;
					final int l15___ = (l10__tmp).f_trmInfo;
					final Struct_MizVarTrm l16_va = Module_correl_match.f_mizEsAsVar(aa);
					return (l13_varNr==(l16_va).f_varNr);
				}
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l10__tmp = (Struct_MizFuncTrm)l9__tmp;
					final int l17_srt = l10__tmp.f_sort;
					final int l18_funcNr = l10__tmp.f_funcNr;
					final Struct l19_args = l10__tmp.f_args;
					final int l20_srt = (l10__tmp).f_sort;
					final int l21_funcNr = (l10__tmp).f_funcNr;
					final Struct l22_args = (l10__tmp).f_args;
					final int l23___ = (l10__tmp).f_pattNr;
					final int l24___ = (l10__tmp).f_trmInfo;
					final Struct_MizFuncTrm l25_fa = Module_correl_match.f_mizEsAsFunc(aa);
					if ((l20_srt==Module_lexicon.g_ikTrmFunctor)) {
						if ((l21_funcNr==(l25_fa).f_funcNr)) {
							return Module_correl_match.f_mizEsTrmList(aenv, l22_args, (l25_fa).f_args);
						} else {
							final Struct_MizFuncTrm l26_ff = Module_correl_match.f_mizEsAsFunc(l10__tmp);
							final Struct_Pair l27_p1 = Module_correl.f_mizAdjustTrm(aenv, l26_ff);
							final Struct_Pair l28_p2 = Module_correl.f_mizAdjustTrm(aenv, l25_fa);
							return ((((int)(l27_p1).f_first)==((int)(l28_p2).f_first))&&Module_correl_match.f_mizEsTrmList(aenv, ((Struct)(l27_p1).f_second), ((Struct)(l28_p2).f_second)));
						}
					} else {
						return ((l21_funcNr==(l25_fa).f_funcNr)&&Module_correl_match.f_mizEsTrmList(aenv, l22_args, (l25_fa).f_args));
					}
				}
				case 59/*MizPrivFuncTrm*/: {
					final Struct_MizPrivFuncTrm l10__tmp = (Struct_MizPrivFuncTrm)l9__tmp;
					final int l29_funcNr = l10__tmp.f_funcNr;
					final Struct l30_args = l10__tmp.f_args;
					final int l31_funcNr = (l10__tmp).f_funcNr;
					final Struct l32_args = (l10__tmp).f_args;
					final Struct l33___ = (l10__tmp).f_funcExp;
					final int l34___ = (l10__tmp).f_pattNr;
					final int l35___ = (l10__tmp).f_trmInfo;
					final Struct_MizPrivFuncTrm l36_pa = Module_correl_match.f_mizEsAsPriv(aa);
					return ((l31_funcNr==(l36_pa).f_funcNr)&&Module_correl_match.f_mizEsTrmList(aenv, l32_args, (l36_pa).f_args));
				}
				case 42/*MizFraenkelTrm*/: {
					final Struct_MizFraenkelTrm l10__tmp = (Struct_MizFraenkelTrm)l9__tmp;
					final Struct_Vector l37_lambdaArgs = l10__tmp.f_lambdaArgs;
					final Struct l38_scope = l10__tmp.f_scope;
					final Struct l39_compr = l10__tmp.f_compr;
					final Struct_Vector l40_lambdaArgs = (l10__tmp).f_lambdaArgs;
					final Struct l41_scope = (l10__tmp).f_scope;
					final Struct l42_compr = (l10__tmp).f_compr;
					final Struct_MizIntSeq l43___ = (l10__tmp).f_idents;
					final int l44___ = (l10__tmp).f_pattNr;
					final int l45___ = (l10__tmp).f_trmInfo;
					final Struct_MizFraenkelTrm l46_ka = Module_correl_match.f_mizEsAsFraenkel(aa);
					if ((Module_vector.f_sizeVector(l40_lambdaArgs)!=Module_vector.f_sizeVector((l46_ka).f_lambdaArgs))) {
						return false;
					} else {
						final Reference<Boolean> l47_ok = ((Reference<Boolean>)(new Reference(true)));
						final Reference<Integer> l48_i = ((Reference<Integer>)(new Reference(0)));
						final Func0<Boolean> l51_$49 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l48_i.value)<Module_vector.f_sizeVector(l40_lambdaArgs))&&((boolean)l47_ok.value)));
						};
						final Struct_MizCorrelEnv l53_env = aenv;
						final Func0<Object> l52_$50 = (Func0<Object>)() -> {
							if (!Module_correl_match.f_mizEsTyp(l53_env, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l40_lambdaArgs, ((int)l48_i.value))), ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l46_ka).f_lambdaArgs, ((int)l48_i.value))))) {
								l47_ok.value = ((Boolean)false);
							} else {
							}
							l48_i.value = ((Integer)(((int)l48_i.value)+1));
							return null;
						};
						Module_loop.f_while(l51_$49, l52_$50);
						return ((((boolean)l47_ok.value)&&Module_correl_match.f_mizEsTrm(aenv, l41_scope, (l46_ka).f_scope))&&Module_correl_match.f_mizEsFrm(aenv, l42_compr, (l46_ka).f_compr));
					}
				}
				case 21/*MizChoiceTrm*/: {
					final Struct_MizChoiceTrm l10__tmp = (Struct_MizChoiceTrm)l9__tmp;
					final Struct_MizTyp l54_typ = l10__tmp.f_choiceTyp;
					final Struct_MizTyp l55_typ = (l10__tmp).f_choiceTyp;
					final int l56___ = (l10__tmp).f_pattNr;
					final int l57___ = (l10__tmp).f_trmInfo;
					final Struct_MizChoiceTrm l58_ha = Module_correl_match.f_mizEsAsChoice(aa);
					return Module_correl_match.f_mizEsTyp(aenv, l55_typ, (l58_ha).f_choiceTyp);
				}
				case 16/*MizBaseTrm*/: {
					final Struct_MizBaseTrm l10__tmp = (Struct_MizBaseTrm)l9__tmp;
					final int l59_srt = l10__tmp.f_sort;
					final int l60_srt = (l10__tmp).f_sort;
					final int l61___ = (l10__tmp).f_trmInfo;
					return (l60_srt==Module_lexicon.g_ikTrmIt);
				}
				case 60/*MizQuaTrm*/: {
					final Struct_MizQuaTrm l10__tmp = (Struct_MizQuaTrm)l9__tmp;
					final Struct l62___ = (l10__tmp).f_trmProper;
					final Struct_MizTyp l63___ = (l10__tmp).f_qua;
					final int l64___ = (l10__tmp).f_pattNr;
					final int l65___ = (l10__tmp).f_trmInfo;
					return false;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l9__tmp.getTypeName());
				}
			} else {
				if ((l8_sa==Module_lexicon.g_ikTrmInfConst)) {
					final Struct_MizVarTrm l66_va = Module_correl_match.f_mizEsAsVar(aa);
					final Struct_MizConstDef l67_cd = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((aenv).f_inferConstDef, (l66_va).f_varNr));
					{
						final Struct l68___tmp = (l67_cd).f_def;
						aa = l68___tmp;
						continue TAIL_CALL;
					}
				} else {
					if ((l0_sf==Module_lexicon.g_ikTrmInfConst)) {
						final Struct_MizVarTrm l69_vf2 = Module_correl_match.f_mizEsAsVar(af0);
						final Struct_MizConstDef l70_cd2 = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((aenv).f_inferConstDef, (l69_vf2).f_varNr));
						{
							final Struct l71___tmp = (l70_cd2).f_def;
							af0 = l71___tmp;
							continue TAIL_CALL;
						}
					} else {
						if (((l0_sf==Module_lexicon.g_ikTrmPrivFunc)||(l8_sa==Module_lexicon.g_ikTrmPrivFunc))) {
							final Struct l72_f1 = Module_correl.f_mizSkipPrivFunc(af0);
							final Struct l73_a1 = Module_correl.f_mizSkipPrivFunc(aa);
							if (((Module_correl.f_mizTrmSort(l72_f1)==Module_lexicon.g_ikTrmPrivFunc)||(Module_correl.f_mizTrmSort(l73_a1)==Module_lexicon.g_ikTrmPrivFunc))) {
								return false;
							} else {
								{
									final Struct l74___tmp = l72_f1;
									final Struct l75___tmp = l73_a1;
									af0 = l74___tmp;
									aa = l75___tmp;
									continue TAIL_CALL;
								}
							}
						} else {
							return false;
						}
					}
				}
			}
		}
	}
	}
	public static final boolean f_mizEsTrmList(Struct_MizCorrelEnv aenv, Struct af, Struct aa) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			Struct l2__tmp = aa;
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
			final Struct l6_tf = l1__tmp.f_trm;
			final Struct l7_nf = l1__tmp.f_next;
			final Struct l8_tf = (l1__tmp).f_trm;
			final Struct l9_nf = (l1__tmp).f_next;
			Struct l10__tmp = aa;
			switch (l10__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				return false;
			}
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l11__tmp = (Struct_MizTrmElem)l10__tmp;
				final Struct l12_ta = l11__tmp.f_trm;
				final Struct l13_na = l11__tmp.f_next;
				final Struct l14_ta = (l11__tmp).f_trm;
				final Struct l15_na = (l11__tmp).f_next;
				return (Module_correl_match.f_mizEsTrm(aenv, l8_tf, l14_ta)&&Module_correl_match.f_mizEsTrmList(aenv, l9_nf, l15_na));
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final boolean f_mizEsTyp(Struct_MizCorrelEnv aenv, Struct_MizTyp af, Struct_MizTyp aa) {
		if (((af).f_sort!=(aa).f_sort)) {
			return false;
		} else {
			final Struct_MizAttrColl l7_$1 = (af).f_lower;
			final Struct_MizAttrColl l8_$2 = (aa).f_upper;
			final Struct_MizCorrelEnv l10_env = aenv;
			final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l9_$0 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
				return ((Boolean)Module_correl_match.f_mizEsAttrRev(l10_env, ax, ay));
			};
			boolean l11_$6;
			if (!Module_correl_inst.f_mizAttrCollIsSubsetOf(aenv, l7_$1, l8_$2, l9_$0)) {
				l11_$6=true;
			} else {
				final Struct_MizAttrColl l15_$13 = (aa).f_lower;
				final Struct_MizAttrColl l16_$14 = (af).f_upper;
				final Struct_MizCorrelEnv l18_env = aenv;
				final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l17_$12 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
					return ((Boolean)Module_correl_match.f_mizEsAttr(l18_env, ax, ay));
				};
				l11_$6=!Module_correl_inst.f_mizAttrCollIsSubsetOf(aenv, l15_$13, l16_$14, l17_$12);
			}
			if (l11_$6) {
				return false;
			} else {
				if (((af).f_modNr==(aa).f_modNr)) {
					return Module_correl_match.f_mizEsTrmList(aenv, (af).f_modArgs, (aa).f_modArgs);
				} else {
					if (((af).f_sort==Module_lexicon.g_ikTypMode)) {
						final Struct_Pair l19_pa = Module_correl.f_mizAdjustTyp(aenv, aa);
						return (((af).f_modNr==((int)(l19_pa).f_first))&&Module_correl_match.f_mizEsTrmList(aenv, (af).f_modArgs, ((Struct)(l19_pa).f_second)));
					} else {
						return false;
					}
				}
			}
		}
	}
	public static final Object f_mizInstFromSubst(Struct_MizCorrelEnv aenv) {
		Module_vector.f_clearVector((aenv).f_instantiation);
		Module_vector.f_pushVector((aenv).f_instantiation, Module_correl.f_mizNewIncorTrm());
		final Reference<Boolean> l0_brk = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l1_k = ((Reference<Integer>)(new Reference(1)));
		final Struct_MizCorrelEnv l5_env = aenv;
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l0_brk.value)&&(((int)l1_k.value)<Module_vector.f_sizeVector((l5_env).f_substTrm))));
		};
		final Struct_MizCorrelEnv l7_env = aenv;
		final Func0<Object> l6_$3 = (Func0<Object>)() -> {
			final Struct l7_gsymswitch1 = Module_correl_match.f_mizSubstTrmGet(l7_env, ((int)l1_k.value));
			Struct l8__tmp = l7_gsymswitch1;
			switch (l8__tmp.getTypeId()) {
			case 88/*Some*/: {
				final Struct_Some l9__tmp = (Struct_Some)l8__tmp;
				final Object l10_t = l9__tmp.f_value;
				final Struct l11_t = ((Struct)(l9__tmp).f_value);
				Module_vector.f_pushVector((l7_env).f_instantiation, l11_t);
				break;
			}
			case 80/*None*/: {
				l0_brk.value = ((Boolean)true);
				break;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l8__tmp.getTypeName());
			}
			l1_k.value = ((Integer)(((int)l1_k.value)+1));
			return null;
		};
		Module_loop.f_while(l4_$2, l6_$3);
		((Field_instantiated)aenv).set_instantiated(true);
		((Field_boundBase)aenv).set_boundBase((aenv).f_boundVarNbr);
		return null;
	}
	public static final Struct f_mizInstSubstFrm(Struct_MizCorrelEnv aenv, Struct af) {
		Module_correl_match.f_mizInstFromSubst(aenv);
		final Struct l0_res = Module_correl_inst.f_mizInstCopyFrm(aenv, af);
		((Field_instantiated)aenv).set_instantiated(false);
		return l0_res;
	}
	public static final Struct f_mizInstSubstTrm(Struct_MizCorrelEnv aenv, Struct at) {
		Module_correl_match.f_mizInstFromSubst(aenv);
		final Struct l0_res = Module_correl_inst.f_mizInstCopyTrm(aenv, at);
		((Field_instantiated)aenv).set_instantiated(false);
		return l0_res;
	}
	public static final Object f_mizResetNewerSubst(Struct_MizCorrelEnv aenv, Struct_Vector asnap) {
		final int l2_$1 = (Module_vector.f_sizeVector((aenv).f_substTrm)-1);
		final Struct_Vector l4_snap = asnap;
		final Struct_MizCorrelEnv l5_env = aenv;
		final Func1<Object,Integer> l3_$0 = (Func1<Object, Integer>)(Integer ak) -> {
			if (!((boolean)Module_vector.f_getVectorDef(l4_snap, ((int)ak), false))) {
				return Module_vector.f_setVectorUnsafe((l5_env).f_substTrm, ((int)ak), SingletonStructs.str_None);
			} else {
				return null;
			}
		};
		return Module_runtime.f_fori(1, l2_$1, l3_$0);
	}
	public static final Object f_mizRestoreSubst(Struct_MizCorrelEnv aenv, Struct_Vector asnap) {
		Module_vector.f_clearVector((aenv).f_substTrm);
		final Struct_MizCorrelEnv l2_env = aenv;
		final Func1<Object,Struct> l1_$0 = (Func1<Object, Struct>)(Struct am) -> {
			return Module_vector.f_pushVector((l2_env).f_substTrm, am);
		};
		return Module_vector.f_iterVector(asnap, ((Func1<Object,Object>)(Func1)l1_$0));
	}
	public static final Struct_Vector f_mizSnapshotSubst(Struct_MizCorrelEnv aenv) {
		final Struct_Vector l2_$1 = (aenv).f_substTrm;
		final Func1<Boolean,Struct> l3_$0 = (Func1<Boolean, Struct>)(Struct am) -> {
			return ((Boolean)Module_maybe.f_isSome(am));
		};
		return Module_vector.f_mapVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
	}
	public static final Struct f_mizSubstTrmGet(Struct_MizCorrelEnv aenv, int ak) {
		return ((Struct)Module_vector.f_getVectorDef((aenv).f_substTrm, ak, SingletonStructs.str_None));
	}
	public static final Object f_mizSubstTrmSet(Struct_MizCorrelEnv aenv, int ak, Struct at) {
		return Module_vector.f_setVectorUnsafe((aenv).f_substTrm, ak, at);
	}
}
