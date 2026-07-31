// HASH COLLISIONS: YES
// timestamp: 1785505924000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_checker {
	public static final Object f_mizAllocInequality(Struct_MizChecker achk, Struct_MizEqualizer aeq_0, Struct at1, Struct at2) {
		final Struct_MizCorrelEnv l0_env = (achk).f_env;
		final int l1_rqEq = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEqualsTo);
		final int l2_i1 = Module_equalizer.f_mizTrmInfoOf(at1);
		final int l3_i2 = Module_equalizer.f_mizTrmInfoOf(at2);
		final Reference<Boolean> l4_dup = ((Reference<Boolean>)(new Reference(false)));
		final Struct_Vector l7_$6 = (aeq_0).f_negBas;
		final Func1<Object,Struct> l8_$5 = (Func1<Object, Struct>)(Struct anf) -> {
			Struct l9__tmp = anf;
			switch (l9__tmp.getTypeId()) {
			case 55/*MizPredFrm*/: {
				final Struct_MizPredFrm l10__tmp = (Struct_MizPredFrm)l9__tmp;
				final int l11_srt = l10__tmp.f_sort;
				final int l12_predNr = l10__tmp.f_predNr;
				final Struct l13_args = l10__tmp.f_args;
				final int l14_srt = (l10__tmp).f_sort;
				final int l15_predNr = (l10__tmp).f_predNr;
				final Struct l16_args = (l10__tmp).f_args;
				final int l17___ = (l10__tmp).f_pattNr;
				if (((l14_srt==Module_lexicon.g_ikFrmPred)&&(l15_predNr==l1_rqEq))) {
					final int l18_l = Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth(l16_args, 0));
					final int l19_r = Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth(l16_args, 1));
					if ((((l18_l==l2_i1)&&(l19_r==l3_i2))||((l18_l==l3_i2)&&(l19_r==l2_i1)))) {
						l4_dup.value = ((Boolean)true);
						return null;
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
		};
		Module_vector.f_iterVector(l7_$6, ((Func1<Object,Object>)(Func1)l8_$5));
		if (!((boolean)l4_dup.value)) {
			Module_vector.f_pushVector((aeq_0).f_negBas, Module_correl.f_mizNewEqFrm(l0_env, at1, at2));
			Module_equalizer.f_mizInsertNonEmptyPub(aeq_0, l2_i1, l3_i2);
			Module_equalizer.f_mizInsertNonEmptyPub(aeq_0, l3_i2, l2_i1);
			Module_equalizer.f_mizInsertNonZeroPub(aeq_0, l2_i1, l3_i2);
			return Module_equalizer.f_mizInsertNonZeroPub(aeq_0, l3_i2, l2_i1);
		} else {
			return null;
		}
	}
	public static final Object f_mizChError(Struct_MizChecker achk, int ae) {
		if (!Module_mobjects.f_mizIntSetIsInSet((achk).f_chErrNr, ae)) {
			final int l0___x = Module_mobjects.f_mizIntSetInsert((achk).f_chErrNr, ae);
			return null;
		} else {
			return null;
		}
	}
	public static final Struct f_mizChkArgs(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 44/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final Struct l2_args = l1__tmp.f_args;
			final int l3___ = (l1__tmp).f_sort;
			final int l4___ = (l1__tmp).f_funcNr;
			final Struct l5_args = (l1__tmp).f_args;
			final int l6___ = (l1__tmp).f_pattNr;
			final int l7___ = (l1__tmp).f_trmInfo;
			return l5_args;
		}
		case 58/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final Struct l8_args = l1__tmp.f_args;
			final int l9___ = (l1__tmp).f_funcNr;
			final Struct l10_args = (l1__tmp).f_args;
			final Struct l11___ = (l1__tmp).f_funcExp;
			final int l12___ = (l1__tmp).f_pattNr;
			final int l13___ = (l1__tmp).f_trmInfo;
			return l10_args;
		}
		default: {
			return ((Struct)SingletonStructs.str_MizTrmNil);
		}
		}
	}
	public static final Struct_MizPredFrm f_mizChkAsPred(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 55/*MizPredFrm*/: {
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
	public static final int f_mizChkFuncNr(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 44/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l2_funcNr = l1__tmp.f_funcNr;
			final int l3___ = (l1__tmp).f_sort;
			final int l4_funcNr = (l1__tmp).f_funcNr;
			final Struct l5___ = (l1__tmp).f_args;
			final int l6___ = (l1__tmp).f_pattNr;
			final int l7___ = (l1__tmp).f_trmInfo;
			return l4_funcNr;
		}
		case 58/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final int l8_funcNr = l1__tmp.f_funcNr;
			final int l9_funcNr = (l1__tmp).f_funcNr;
			final Struct l10___ = (l1__tmp).f_args;
			final Struct l11___ = (l1__tmp).f_funcExp;
			final int l12___ = (l1__tmp).f_pattNr;
			final int l13___ = (l1__tmp).f_trmInfo;
			return l9_funcNr;
		}
		default: {
			return 0;
		}
		}
	}
	public static final boolean f_mizContradictoryAttrs(Struct_MizChecker achk, Struct_MizAttrColl ac1, Struct_MizAttrColl ac2) {
		final Struct_MizCorrelEnv l0_env = (achk).f_env;
		final int l1_n1 = Module_vector.f_sizeVector((ac1).f_items);
		final int l2_n2 = Module_vector.f_sizeVector((ac2).f_items);
		if (((l1_n1==0)||(l2_n2==0))) {
			return false;
		} else {
			final Reference<Boolean> l3_res = ((Reference<Boolean>)(new Reference(false)));
			final Reference<Integer> l4_i = ((Reference<Integer>)(new Reference(0)));
			final Reference<Integer> l5_j = ((Reference<Integer>)(new Reference(0)));
			final Func0<Boolean> l8_$6 = (Func0<Boolean>)() -> {
				return ((Boolean)((!((boolean)l3_res.value)&&(((int)l4_i.value)<l1_n1))&&(((int)l5_j.value)<l2_n2)));
			};
			final Struct_MizAttrColl l10_c1 = ac1;
			final Struct_MizAttrColl l11_c2 = ac2;
			final Func0<Object> l9_$7 = (Func0<Object>)() -> {
				final Struct_MizAttr l10_a = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l10_c1).f_items, ((int)l4_i.value)));
				final Struct_MizAttr l11_b = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l11_c2).f_items, ((int)l5_j.value)));
				final int l12_c = Module_correl.f_mizCompAbsAttr(l0_env, l10_a, l11_b);
				if ((l12_c==(-1))) {
					l4_i.value = ((Integer)(((int)l4_i.value)+1));
					return null;
				} else {
					if ((l12_c==0)) {
						if (((l10_a).f_neg!=(l11_b).f_neg)) {
							l3_res.value = ((Boolean)true);
						} else {
						}
						l4_i.value = ((Integer)(((int)l4_i.value)+1));
					} else {
					}
					l5_j.value = ((Integer)(((int)l5_j.value)+1));
					return null;
				}
			};
			Module_loop.f_while(l8_$6, l9_$7);
			return ((boolean)l3_res.value);
		}
	}
	public static final Object f_mizInferenceChecker(Struct_MizChecker achk, Struct_Vector ainference) {
		final Struct_MizCorrelEnv l0_env = (achk).f_env;
		final Struct_MizIdentify l1_idn = (achk).f_idn;
		Module_mobjects.f_mizSeqClear((achk).f_chErrNr);
		final int l2_infMark = Module_vector.f_sizeVector((l0_env).f_inferConstDef);
		final int l3_fixedMark = (l0_env).f_fixedVarNbr;
		((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
		final Struct_MizPre l4_pre = Module_prechecker.f_mizMakePre(l0_env);
		final Func1<Object,Struct> l6_$5 = (Func1<Object, Struct>)(Struct af) -> {
			return Module_identify.f_mizCollectConstInFrm(l1_idn, af);
		};
		((Field_collectConst)l4_pre).set_collectConst(l6_$5);
		((Field_atomicExpansions)l4_pre).set_atomicExpansions((achk).f_atomicExpansions);
		((Field_itIsChecker)l0_env).set_itIsChecker(true);
		final Struct_MizPreInst l7_normalForm = Module_prechecker.f_mizPreCheck(l4_pre, ainference);
		if ((l4_pre).f_trivialError) {
			Module_checker.f_mizChError(achk, 1);
		} else {
			if ((l4_pre).f_latOvfl) {
				Module_checker.f_mizChError(achk, 8);
			} else {
				final Reference<Boolean> l8_anyTrmOvfl = ((Reference<Boolean>)(new Reference(false)));
				final Reference<Boolean> l9_anyLatOvfl = ((Reference<Boolean>)(new Reference(false)));
				final Struct_Vector l12_$11 = (l7_normalForm).f_evals;
				final Struct_MizChecker l14_chk = achk;
				final Func1<Object,Struct_MizNatFunc> l13_$10 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc aeval) -> {
					((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
					final Struct_MizEqualizer l14_eq_0 = Module_equalizer.f_mizMakeEqualizer(l0_env, l1_idn, (l14_chk).f_clusters, l4_pre);
					Module_equalizer.f_mizEquate(l14_eq_0, aeval);
					if (((l14_eq_0).f_contr==0)) {
						Module_checker.f_mizPreUnification(l14_chk, l14_eq_0);
						if (((l14_eq_0).f_contr==0)) {
							final int l15_uContr = ((int)(l14_chk).f_unification.invoke(l14_chk, l14_eq_0));
							if ((l15_uContr>0)) {
								((Field_contr)l14_eq_0).set_contr(l15_uContr);
							} else {
								Module_checker.f_mizChError(l14_chk, 4);
							}
						} else {
						}
					} else {
					}
					if ((l14_eq_0).f_trmOvfl) {
						l8_anyTrmOvfl.value = ((Boolean)true);
						return null;
					} else {
						return null;
					}
				};
				Module_vector.f_iterVector(l12_$11, ((Func1<Object,Object>)(Func1)l13_$10));
				if ((((boolean)l9_anyLatOvfl.value)||(l4_pre).f_latOvfl)) {
					Module_checker.f_mizChError(achk, 9);
				} else {
				}
				if (((boolean)l8_anyTrmOvfl.value)) {
					Module_checker.f_mizChError(achk, 14);
				} else {
				}
			}
		}
		if ((l4_pre).f_constOvfl) {
			Module_checker.f_mizChError(achk, 11);
		} else {
		}
		((Field_itIsChecker)l0_env).set_itIsChecker(false);
		((Field_fixedVarNbr)l0_env).set_fixedVarNbr(l3_fixedMark);
		return Module_identify.f_mizInferRelease(l1_idn, l2_infMark);
	}
	public static final Struct_MizChecker f_mizMakeChecker(Struct_MizCorrelEnv aenv, Struct_MizIdentify aidn, Struct_MizClusters aclusters) {
		final Struct_MizIntSeq l3_$2 = Module_mobjects.f_mizInitIntSeq(4);
		final Func2<Integer,Struct_MizChecker, Struct_MizEqualizer> l4_$0 = (Func2<Integer, Struct_MizChecker, Struct_MizEqualizer>)(Struct_MizChecker al5_0, Struct_MizEqualizer al6_1) -> {
			return ((Integer)0);
		};
		final Func1<Object[],Struct> l5_$1 = (Func1<Object[], Struct>)(Struct al6_0) -> {
			return SingletonStructs.arr_empty;
		};
		return (new Struct_MizChecker(aenv, aidn, aclusters, l3_$2, l4_$0, l5_$1, false));
	}
	public static final Struct f_mizOneDiffInTrmLists(Struct aa0, Struct ab0) {
		final Reference<Struct> l0_diff = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
		final Reference<Boolean> l1_multi = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Struct> l2_a = ((Reference<Struct>)(new Reference(aa0)));
		final Reference<Struct> l3_b = ((Reference<Struct>)(new Reference(ab0)));
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			boolean l9_$8;
			if (!((boolean)l1_multi.value)) {
				final Struct l10_gsymswitch0 = l2_a.value;
				Struct l11__tmp = l10_gsymswitch0;
				switch (l11__tmp.getTypeId()) {
				case 64/*MizTrmNil*/: {
					l9_$8=false;
					break;
				}
				default: {
					l9_$8=true;
					break;
				}
				}
			} else {
				l9_$8=false;
			}
			return ((Boolean)l9_$8);
		};
		final Func0<Object> l7_$5 = (Func0<Object>)() -> {
			final Struct l8_gsymswitch1 = l2_a.value;
			Struct l9__tmp = l8_gsymswitch1;
			switch (l9__tmp.getTypeId()) {
			case 63/*MizTrmElem*/: {
				final Struct_MizTrmElem l10__tmp = (Struct_MizTrmElem)l9__tmp;
				final Struct l11_ta = l10__tmp.f_trm;
				final Struct l12_na = l10__tmp.f_next;
				final Struct l13_ta = (l10__tmp).f_trm;
				final Struct l14_na = (l10__tmp).f_next;
				final Struct l15_gsymswitch2 = l3_b.value;
				Struct l16__tmp = l15_gsymswitch2;
				switch (l16__tmp.getTypeId()) {
				case 63/*MizTrmElem*/: {
					final Struct_MizTrmElem l17__tmp = (Struct_MizTrmElem)l16__tmp;
					final Struct l18_tb = l17__tmp.f_trm;
					final Struct l19_nb = l17__tmp.f_next;
					final Struct l20_tb = (l17__tmp).f_trm;
					final Struct l21_nb = (l17__tmp).f_next;
					if ((Module_equalizer.f_mizTrmInfoOf(l13_ta)!=Module_equalizer.f_mizTrmInfoOf(l20_tb))) {
						final Struct l22_gsymswitch3 = l0_diff.value;
						Struct l23__tmp = l22_gsymswitch3;
						switch (l23__tmp.getTypeId()) {
						case 83/*Some*/: {
							final Struct_Some l24__tmp = (Struct_Some)l23__tmp;
							final Struct_Pair l25___ = ((Struct_Pair)(l24__tmp).f_value);
							l1_multi.value = ((Boolean)true);
							break;
						}
						case 75/*None*/: {
							((Reference<Struct_Some>)(Reference)l0_diff).value = (new Struct_Some((new Struct_Pair(l13_ta, l20_tb))));
							break;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l23__tmp.getTypeName());
						}
					} else {
					}
					l2_a.value = l14_na;
					l3_b.value = l21_nb;
					return null;
				}
				case 64/*MizTrmNil*/: {
					l1_multi.value = ((Boolean)true);
					return null;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l16__tmp.getTypeName());
				}
			}
			case 64/*MizTrmNil*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l9__tmp.getTypeName());
			}
		};
		Module_loop.f_while(l6_$4, l7_$5);
		if (((boolean)l1_multi.value)) {
			return ((Struct)SingletonStructs.str_None);
		} else {
			return l0_diff.value;
		}
	}
	public static final Object f_mizPreUnification(Struct_MizChecker achk, Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (achk).f_env;
		final int l1_rqEq = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEqualsTo);
		final Struct_Vector l4_$3 = Module_vector.f_copyVector((aeq_0).f_posBas);
		final Struct_MizEqualizer l6_eq_0 = aeq_0;
		final Struct_MizChecker l7_chk = achk;
		final Func1<Object,Struct> l5_$2 = (Func1<Object, Struct>)(Struct apf) -> {
			if (((l6_eq_0).f_contr==0)) {
				Struct l6__tmp = apf;
				switch (l6__tmp.getTypeId()) {
				case 55/*MizPredFrm*/: {
					final Struct_MizPredFrm l7__tmp = (Struct_MizPredFrm)l6__tmp;
					final int l8_srt = l7__tmp.f_sort;
					final int l9_predNr = l7__tmp.f_predNr;
					final Struct l10_args = l7__tmp.f_args;
					final int l11_srt = (l7__tmp).f_sort;
					final int l12_predNr = (l7__tmp).f_predNr;
					final Struct l13_args = (l7__tmp).f_args;
					final int l14___ = (l7__tmp).f_pattNr;
					if ((l11_srt==Module_lexicon.g_ikFrmPred)) {
						final Struct_MizConstr l15_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, l12_predNr);
						if (Module_correl.f_mizHasProperty(l15_c, Module_correl.g_syIrreflexivity)) {
							final Struct l16_l = Module_correl.f_mizTrmListNth(l13_args, ((l15_c).f_firstArg-1));
							final Struct l17_r = Module_correl.f_mizTrmListNth(l13_args, ((l15_c).f_secondArg-1));
							if ((Module_equalizer.f_mizTrmInfoOf(l16_l)==Module_equalizer.f_mizTrmInfoOf(l17_r))) {
								((Field_contr)l6_eq_0).set_contr(50);
								return null;
							} else {
								return Module_checker.f_mizAllocInequality(l7_chk, l6_eq_0, (Module_equalizer.f_mizTrmSAt(l6_eq_0, Module_equalizer.f_mizTrmInfoOf(l16_l))).f_term, (Module_equalizer.f_mizTrmSAt(l6_eq_0, Module_equalizer.f_mizTrmInfoOf(l17_r))).f_term);
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
		Module_vector.f_iterVector(l4_$3, ((Func1<Object,Object>)(Func1)l5_$2));
		if (((aeq_0).f_contr==0)) {
			final Struct_Vector l10_$9 = Module_vector.f_copyVector((aeq_0).f_negBas);
			final Struct_MizEqualizer l12_eq_0 = aeq_0;
			final Struct_MizChecker l13_chk = achk;
			final Func1<Object,Struct> l11_$8 = (Func1<Object, Struct>)(Struct anf) -> {
				if (((l12_eq_0).f_contr==0)) {
					Struct l12__tmp = anf;
					switch (l12__tmp.getTypeId()) {
					case 55/*MizPredFrm*/: {
						final Struct_MizPredFrm l13__tmp = (Struct_MizPredFrm)l12__tmp;
						final int l14_srt = l13__tmp.f_sort;
						final int l15_predNr = l13__tmp.f_predNr;
						final Struct l16_args = l13__tmp.f_args;
						final int l17_srt = (l13__tmp).f_sort;
						final int l18_predNr = (l13__tmp).f_predNr;
						final Struct l19_args = (l13__tmp).f_args;
						final int l20___ = (l13__tmp).f_pattNr;
						if ((l17_srt==Module_lexicon.g_ikFrmPred)) {
							final Struct_MizConstr l21_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, l18_predNr);
							if (Module_correl.f_mizHasProperty(l21_c, Module_correl.g_syReflexivity)) {
								final Struct l22_l = Module_correl.f_mizTrmListNth(l19_args, ((l21_c).f_firstArg-1));
								final Struct l23_r = Module_correl.f_mizTrmListNth(l19_args, ((l21_c).f_secondArg-1));
								if ((Module_equalizer.f_mizTrmInfoOf(l22_l)==Module_equalizer.f_mizTrmInfoOf(l23_r))) {
									((Field_contr)l12_eq_0).set_contr(51);
									return null;
								} else {
									return Module_checker.f_mizAllocInequality(l13_chk, l12_eq_0, (Module_equalizer.f_mizTrmSAt(l12_eq_0, Module_equalizer.f_mizTrmInfoOf(l22_l))).f_term, (Module_equalizer.f_mizTrmSAt(l12_eq_0, Module_equalizer.f_mizTrmInfoOf(l23_r))).f_term);
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
			Module_vector.f_iterVector(l10_$9, ((Func1<Object,Object>)(Func1)l11_$8));
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			final int l14_n = Module_equalizer.f_mizTrmNbr(aeq_0);
			final Struct_MizEqualizer l17_eq_0 = aeq_0;
			final Struct_MizChecker l18_chk = achk;
			final Func1<Object,Integer> l16_$15 = (Func1<Object, Integer>)(Integer aii) -> {
				if (((l17_eq_0).f_contr==0)) {
					final Struct_MizTrmSRec l17_ri = Module_equalizer.f_mizTrmSAt(l17_eq_0, ((int)aii));
					final Struct l18_gsymswitch4 = (l17_ri).f_eqClass;
					boolean l19_liveI;
					Struct l20__tmp = l18_gsymswitch4;
					switch (l20__tmp.getTypeId()) {
					case 64/*MizTrmNil*/: {
						l19_liveI=false;
						break;
					}
					default: {
						l19_liveI=true;
						break;
					}
					}
					if (l19_liveI) {
						final int l24_$23 = (((int)aii)+1);
						final Func1<Object,Integer> l25_$22 = (Func1<Object, Integer>)(Integer ajj) -> {
							if (((l17_eq_0).f_contr==0)) {
								final Struct_MizTrmSRec l26_rj = Module_equalizer.f_mizTrmSAt(l17_eq_0, ((int)ajj));
								final Struct l27_gsymswitch5 = (l26_rj).f_eqClass;
								boolean l28_liveJ;
								Struct l29__tmp = l27_gsymswitch5;
								switch (l29__tmp.getTypeId()) {
								case 64/*MizTrmNil*/: {
									l28_liveJ=false;
									break;
								}
								default: {
									l28_liveJ=true;
									break;
								}
								}
								if ((l28_liveJ&&Module_checker.f_mizContradictoryAttrs(l18_chk, (l17_ri).f_superCluster, (l26_rj).f_superCluster))) {
									return Module_checker.f_mizAllocInequality(l18_chk, l17_eq_0, (l17_ri).f_term, (l26_rj).f_term);
								} else {
									return null;
								}
							} else {
								return null;
							}
						};
						return Module_runtime.f_fori(l24_$23, l14_n, l25_$22);
					} else {
						return null;
					}
				} else {
					return null;
				}
			};
			Module_runtime.f_fori(1, l14_n, l16_$15);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			final Reference<Integer> l19_ii = ((Reference<Integer>)(new Reference(0)));
			final Struct_MizEqualizer l23_eq_0 = aeq_0;
			final Func0<Boolean> l22_$20 = (Func0<Boolean>)() -> {
				return ((Boolean)(((l23_eq_0).f_contr==0)&&(((int)l19_ii.value)<Module_vector.f_sizeVector((l23_eq_0).f_negBas))));
			};
			final Struct_MizEqualizer l25_eq_0 = aeq_0;
			final Struct_MizChecker l26_chk = achk;
			final Func0<Object> l24_$21 = (Func0<Object>)() -> {
				final Struct l25_nf = ((Struct)Module_vector.f_getVectorUnsafe((l25_eq_0).f_negBas, ((int)l19_ii.value)));
				Struct l26__tmp = l25_nf;
				switch (l26__tmp.getTypeId()) {
				case 55/*MizPredFrm*/: {
					final Struct_MizPredFrm l27__tmp = (Struct_MizPredFrm)l26__tmp;
					final int l28_srt = l27__tmp.f_sort;
					final int l29_predNr = l27__tmp.f_predNr;
					final Struct l30_args = l27__tmp.f_args;
					final int l31_srt = (l27__tmp).f_sort;
					final int l32_predNr = (l27__tmp).f_predNr;
					final Struct l33_args = (l27__tmp).f_args;
					final int l34___ = (l27__tmp).f_pattNr;
					if ((l31_srt==Module_lexicon.g_ikFrmPred)) {
						final Struct_MizPredFrm l35_pfN = Module_checker.f_mizChkAsPred(l27__tmp);
						final Struct_Pair l36_pa = Module_correl.f_mizAdjustFrm(l0_env, l35_pfN);
						final Struct_MizConstr l37_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, l32_predNr);
						if (Module_correl.f_mizHasProperty(l37_c, Module_correl.g_syReflexivity)) {
							final Struct l38_l = Module_correl.f_mizTrmListNth(l33_args, ((l37_c).f_firstArg-1));
							final Struct l39_r = Module_correl.f_mizTrmListNth(l33_args, ((l37_c).f_secondArg-1));
							final Reference<Struct> l40_curL = ((Reference<Struct>)(new Reference((Module_equalizer.f_mizTrmSAt(l25_eq_0, Module_equalizer.f_mizTrmInfoOf(l38_l))).f_eqClass)));
							final Func0<Boolean> l43_$41 = (Func0<Boolean>)() -> {
								final Struct l44_gsymswitch7 = l40_curL.value;
								Struct l45__tmp = l44_gsymswitch7;
								switch (l45__tmp.getTypeId()) {
								case 64/*MizTrmNil*/: {
									return ((Boolean)false);
								}
								default: {
									return ((Boolean)true);
								}
								}
							};
							final Func0<Object> l44_$42 = (Func0<Object>)() -> {
								final Struct l45_gsymswitch8 = l40_curL.value;
								Struct l46__tmp = l45_gsymswitch8;
								switch (l46__tmp.getTypeId()) {
								case 63/*MizTrmElem*/: {
									final Struct_MizTrmElem l47__tmp = (Struct_MizTrmElem)l46__tmp;
									final Struct l48_tl = l47__tmp.f_trm;
									final Struct l49_nl = l47__tmp.f_next;
									final Struct l50_tl = (l47__tmp).f_trm;
									final Struct l51_nl = (l47__tmp).f_next;
									final int l52_sl = Module_correl.f_mizTrmSort(l50_tl);
									if ((((((l52_sl==Module_lexicon.g_ikTrmFunctor)||(l52_sl==Module_lexicon.g_ikTrmSchFunc))||(l52_sl==Module_lexicon.g_ikTrmPrivFunc))||(l52_sl==Module_lexicon.g_ikTrmAggreg))||(l52_sl==Module_lexicon.g_ikTrmSelector))) {
										final Reference<Struct> l53_curR = ((Reference<Struct>)(new Reference((Module_equalizer.f_mizTrmSAt(l25_eq_0, Module_equalizer.f_mizTrmInfoOf(l39_r))).f_eqClass)));
										final Func0<Boolean> l56_$54 = (Func0<Boolean>)() -> {
											final Struct l57_gsymswitch9 = l53_curR.value;
											Struct l58__tmp = l57_gsymswitch9;
											switch (l58__tmp.getTypeId()) {
											case 64/*MizTrmNil*/: {
												return ((Boolean)false);
											}
											default: {
												return ((Boolean)true);
											}
											}
										};
										final Func0<Object> l57_$55 = (Func0<Object>)() -> {
											final Struct l58_gsymswitch10 = l53_curR.value;
											Struct l59__tmp = l58_gsymswitch10;
											switch (l59__tmp.getTypeId()) {
											case 63/*MizTrmElem*/: {
												final Struct_MizTrmElem l60__tmp = (Struct_MizTrmElem)l59__tmp;
												final Struct l61_tr = l60__tmp.f_trm;
												final Struct l62_nr = l60__tmp.f_next;
												final Struct l63_tr = (l60__tmp).f_trm;
												final Struct l64_nr = (l60__tmp).f_next;
												if (((Module_correl.f_mizTrmSort(l63_tr)==l52_sl)&&(Module_checker.f_mizChkFuncNr(l50_tl)==Module_checker.f_mizChkFuncNr(l63_tr)))) {
													final Struct l65_gsymswitch11 = Module_checker.f_mizOneDiffInTrmLists(Module_checker.f_mizChkArgs(l50_tl), Module_checker.f_mizChkArgs(l63_tr));
													Struct l66__tmp = l65_gsymswitch11;
													switch (l66__tmp.getTypeId()) {
													case 83/*Some*/: {
														final Struct_Some l67__tmp = (Struct_Some)l66__tmp;
														final Object l68_p = l67__tmp.f_value;
														final Struct_Pair l69_p = ((Struct_Pair)(l67__tmp).f_value);
														Module_checker.f_mizAllocInequality(l26_chk, l25_eq_0, ((Struct)(l69_p).f_first), ((Struct)(l69_p).f_second));
														break;
													}
													case 75/*None*/: {
														break;
													}
													default:
														throw new RuntimeException("Unexpected struct in switch: "+l66__tmp.getTypeName());
													}
												} else {
												}
												l53_curR.value = l64_nr;
												return null;
											}
											case 64/*MizTrmNil*/: {
												return null;
											}
											default:
												throw new RuntimeException("Unexpected struct in switch: "+l59__tmp.getTypeName());
											}
										};
										Module_loop.f_while(l56_$54, l57_$55);
									} else {
									}
									l40_curL.value = l51_nl;
									return null;
								}
								case 64/*MizTrmNil*/: {
									return null;
								}
								default:
									throw new RuntimeException("Unexpected struct in switch: "+l46__tmp.getTypeName());
								}
							};
							Module_loop.f_while(l43_$41, l44_$42);
						} else {
						}
						if ((((int)(l36_pa).f_first)!=Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEqualsTo))) {
							final Struct_Vector l47_$46 = (l25_eq_0).f_posBas;
							final Func1<Object,Struct> l48_$45 = (Func1<Object, Struct>)(Struct apf) -> {
								Struct l49__tmp = apf;
								switch (l49__tmp.getTypeId()) {
								case 55/*MizPredFrm*/: {
									final Struct_MizPredFrm l50__tmp = (Struct_MizPredFrm)l49__tmp;
									final int l51_s2 = l50__tmp.f_sort;
									final int l52_s2 = (l50__tmp).f_sort;
									final int l53___ = (l50__tmp).f_predNr;
									final Struct l54___ = (l50__tmp).f_args;
									final int l55___ = (l50__tmp).f_pattNr;
									if ((l52_s2==Module_lexicon.g_ikFrmPred)) {
										final Struct_MizPredFrm l56_pfP = Module_checker.f_mizChkAsPred(l50__tmp);
										final Struct_Pair l57_pb = Module_correl.f_mizAdjustFrm(l0_env, l56_pfP);
										if ((((int)(l57_pb).f_first)==((int)(l36_pa).f_first))) {
											final Struct l58_gsymswitch12 = Module_checker.f_mizOneDiffInTrmLists(((Struct)(l57_pb).f_second), ((Struct)(l36_pa).f_second));
											Struct l59__tmp = l58_gsymswitch12;
											switch (l59__tmp.getTypeId()) {
											case 83/*Some*/: {
												final Struct_Some l60__tmp = (Struct_Some)l59__tmp;
												final Object l61_p = l60__tmp.f_value;
												final Struct_Pair l62_p = ((Struct_Pair)(l60__tmp).f_value);
												return Module_checker.f_mizAllocInequality(l26_chk, l25_eq_0, ((Struct)(l62_p).f_first), ((Struct)(l62_p).f_second));
											}
											case 75/*None*/: {
												return null;
											}
											default:
												throw new RuntimeException("Unexpected struct in switch: "+l59__tmp.getTypeName());
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
							};
							Module_vector.f_iterVector(l47_$46, ((Func1<Object,Object>)(Func1)l48_$45));
						} else {
						}
					} else {
						final Struct_Vector l51_$50 = (l25_eq_0).f_posBas;
						final Func1<Object,Struct> l52_$49 = (Func1<Object, Struct>)(Struct apf) -> {
							Struct l53__tmp = apf;
							switch (l53__tmp.getTypeId()) {
							case 55/*MizPredFrm*/: {
								final Struct_MizPredFrm l54__tmp = (Struct_MizPredFrm)l53__tmp;
								final int l55_s2 = l54__tmp.f_sort;
								final int l56_p2 = l54__tmp.f_predNr;
								final Struct l57_args2 = l54__tmp.f_args;
								final int l58_s2 = (l54__tmp).f_sort;
								final int l59_p2 = (l54__tmp).f_predNr;
								final Struct l60_args2 = (l54__tmp).f_args;
								final int l61___ = (l54__tmp).f_pattNr;
								if (((l58_s2==l31_srt)&&(l59_p2==l32_predNr))) {
									final Struct l62_gsymswitch6 = Module_checker.f_mizOneDiffInTrmLists(l60_args2, l33_args);
									Struct l63__tmp = l62_gsymswitch6;
									switch (l63__tmp.getTypeId()) {
									case 83/*Some*/: {
										final Struct_Some l64__tmp = (Struct_Some)l63__tmp;
										final Object l65_p = l64__tmp.f_value;
										final Struct_Pair l66_p = ((Struct_Pair)(l64__tmp).f_value);
										return Module_checker.f_mizAllocInequality(l26_chk, l25_eq_0, ((Struct)(l66_p).f_first), ((Struct)(l66_p).f_second));
									}
									case 75/*None*/: {
										return null;
									}
									default:
										throw new RuntimeException("Unexpected struct in switch: "+l63__tmp.getTypeName());
									}
								} else {
									return null;
								}
							}
							default: {
								return null;
							}
							}
						};
						Module_vector.f_iterVector(l51_$50, ((Func1<Object,Object>)(Func1)l52_$49));
					}
					break;
				}
				case 60/*MizQualFrm*/: {
					final Struct_MizQualFrm l27__tmp = (Struct_MizQualFrm)l26__tmp;
					final Struct l53_trm = l27__tmp.f_trm;
					final Struct_MizTyp l54_typ = l27__tmp.f_typ;
					final Struct l55_trm = (l27__tmp).f_trm;
					final Struct_MizTyp l56_typ = (l27__tmp).f_typ;
					final int l57___ = (l27__tmp).f_pattNr;
					final Struct_MizTrmSRec l58_rec = Module_equalizer.f_mizTrmSAt(l25_eq_0, Module_equalizer.f_mizTrmInfoOf(l55_trm));
					final Struct_Vector l61_$60 = (l58_rec).f_xTypClass;
					final Func1<Object,Struct_MizTyp> l62_$59 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
						boolean l67_$66;
						if (((axt).f_sort==(l56_typ).f_sort)) {
							final Struct_MizAttrColl l71_$69 = (l56_typ).f_lower;
							final Struct_MizAttrColl l72_$70 = (l58_rec).f_superCluster;
							final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l73_$68 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
								return ((Boolean)Module_correl_match.f_mizAttrEquals(l0_env, ax, ay));
							};
							l67_$66=Module_correl_inst.f_mizAttrCollIsSubsetOf(l0_env, l71_$69, l72_$70, l73_$68);
						} else {
							l67_$66=false;
						}
						if ((l67_$66&&((axt).f_sort==Module_lexicon.g_ikTypMode))) {
							final Struct_Pair l74_p1 = Module_correl.f_mizAdjustTyp(l0_env, axt);
							final Struct_Pair l75_p2 = Module_correl.f_mizAdjustTyp(l0_env, l56_typ);
							if ((((int)(l74_p1).f_first)==((int)(l75_p2).f_first))) {
								final Struct l76_gsymswitch13 = Module_checker.f_mizOneDiffInTrmLists(((Struct)(l74_p1).f_second), ((Struct)(l75_p2).f_second));
								Struct l77__tmp = l76_gsymswitch13;
								switch (l77__tmp.getTypeId()) {
								case 83/*Some*/: {
									final Struct_Some l78__tmp = (Struct_Some)l77__tmp;
									final Object l79_p = l78__tmp.f_value;
									final Struct_Pair l80_p = ((Struct_Pair)(l78__tmp).f_value);
									return Module_checker.f_mizAllocInequality(l26_chk, l25_eq_0, ((Struct)(l80_p).f_first), ((Struct)(l80_p).f_second));
								}
								case 75/*None*/: {
									return null;
								}
								default:
									throw new RuntimeException("Unexpected struct in switch: "+l77__tmp.getTypeName());
								}
							} else {
								return null;
							}
						} else {
							return null;
						}
					};
					Module_vector.f_iterVector(l61_$60, ((Func1<Object,Object>)(Func1)l62_$59));
					final int l65_$64 = Module_equalizer.f_mizTrmNbr(l25_eq_0);
					final Func1<Object,Integer> l66_$63 = (Func1<Object, Integer>)(Integer aj) -> {
						if ((((int)aj)!=Module_equalizer.f_mizTrmInfoOf(l55_trm))) {
							final Struct_MizTrmSRec l67_rj = Module_equalizer.f_mizTrmSAt(l25_eq_0, ((int)aj));
							final Struct l68_gsymswitch14 = (l67_rj).f_eqClass;
							boolean l69_liveJ;
							Struct l70__tmp = l68_gsymswitch14;
							switch (l70__tmp.getTypeId()) {
							case 64/*MizTrmNil*/: {
								l69_liveJ=false;
								break;
							}
							default: {
								l69_liveJ=true;
								break;
							}
							}
							if (l69_liveJ) {
								final Reference<Boolean> l72_hit = ((Reference<Boolean>)(new Reference(false)));
								final Struct_Vector l75_$74 = (l67_rj).f_xTypClass;
								final Func1<Object,Struct_MizTyp> l76_$73 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
									if ((!((boolean)l72_hit.value)&&Module_correl_inst.f_mizEqRadices(l0_env, l56_typ, axt))) {
										l72_hit.value = ((Boolean)true);
										return null;
									} else {
										return null;
									}
								};
								Module_vector.f_iterVector(l75_$74, ((Func1<Object,Object>)(Func1)l76_$73));
								if (((boolean)l72_hit.value)) {
									return Module_checker.f_mizAllocInequality(l26_chk, l25_eq_0, l55_trm, (l67_rj).f_term);
								} else {
									return null;
								}
							} else {
								return null;
							}
						} else {
							return null;
						}
					};
					Module_runtime.f_fori(1, l65_$64, l66_$63);
					break;
				}
				default: {
					break;
				}
				}
				l19_ii.value = ((Integer)(((int)l19_ii.value)+1));
				return null;
			};
			return Module_loop.f_while(l22_$20, l24_$21);
		} else {
			return null;
		}
	}
}
