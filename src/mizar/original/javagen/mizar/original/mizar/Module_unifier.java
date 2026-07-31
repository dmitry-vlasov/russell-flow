// HASH COLLISIONS: YES
// timestamp: 1785508389000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_unifier {
	public static int g_expUAggreg;
	public static int g_expUChoice;
	public static int g_expUFraenkel;
	public static int g_expUFunctor;
	public static int g_expUPrivFunc;
	public static int g_expUSchFunc;
	public static int g_expUSelector;
	public static void init() {
		g_expUAggreg=1;
		g_expUChoice=6;
		g_expUFraenkel=5;
		g_expUFunctor=0;
		g_expUPrivFunc=3;
		g_expUSchFunc=4;
		g_expUSelector=2;
	}
	public static final boolean f_mizAttrAreSimilar(Struct_MizAttr aa, Struct_MizAttr ab) {
		return (((aa).f_attrNr==(ab).f_attrNr)&&((aa).f_neg==(ab).f_neg));
	}
	public static final Struct f_mizChReconQualFrm(Struct_MizUnifier au, Struct asubject, int aattrNr, int asign_0, Struct aargs) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizTyp l1_lTyp = Module_correl_inst.f_mizCopyTrmType(l0_env, asubject);
		final Struct_MizAttrColl l2_lCluster = Module_correl.f_mizCopyCluster((l1_lTyp).f_lower);
		Module_correl.f_mizAttrCollInsert(l0_env, l2_lCluster, (new Struct_MizAttr(asign_0, aattrNr, Module_correl.f_mizCopyTrmList(aargs), false, 0)));
		if (!(l2_lCluster).f_consistent) {
			return Module_correl.f_mizNewNeg(Module_correl.f_mizNewVerum());
		} else {
			((Field_lower)l1_lTyp).set_lower(l2_lCluster);
			((Field_upper)l1_lTyp).set_upper(Module_correl.f_mizCopyCluster(l2_lCluster));
			return Module_correl.f_mizNewQualFrm(asubject, l1_lTyp);
		}
	}
	public static final boolean f_mizClustersAreSimilar(Struct_MizAttrColl aa, Struct_MizAttrColl ab) {
		if ((Module_vector.f_sizeVector((aa).f_items)!=Module_vector.f_sizeVector((ab).f_items))) {
			return false;
		} else {
			final Reference<Boolean> l0_ok = ((Reference<Boolean>)(new Reference(true)));
			final int l3_$2 = (Module_vector.f_sizeVector((aa).f_items)-1);
			final Struct_MizAttrColl l5_a = aa;
			final Struct_MizAttrColl l6_b = ab;
			final Func1<Object,Integer> l4_$1 = (Func1<Object, Integer>)(Integer ai) -> {
				if (!Module_unifier.f_mizAttrAreSimilar(((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l5_a).f_items, ((int)ai))), ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l6_b).f_items, ((int)ai))))) {
					l0_ok.value = ((Boolean)false);
					return null;
				} else {
					return null;
				}
			};
			Module_runtime.f_fori(0, l3_$2, l4_$1);
			return ((boolean)l0_ok.value);
		}
	}
	public static final Struct_Vector f_mizCollectComplementaryLiterals(Struct_MizUnifier au, Struct_MizPreInst aall_0) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizPre l1_pre = (au).f_pre;
		final Struct_Vector l2_out = Module_vector.f_makeVector(4);
		final int l3_n = Module_prechecker.f_mizPreInstCount(aall_0);
		final int l6_$5 = (l3_n-1);
		final Struct_MizPreInst l8_all_0 = aall_0;
		final Struct_MizUnifier l9_u = au;
		final Func1<Object,Integer> l7_$4 = (Func1<Object, Integer>)(Integer ai1) -> {
			final Struct_MizNatFunc l8_c1 = ((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe((l8_all_0).f_evals, ((int)ai1)));
			final int l12_$10 = (((int)ai1)+1);
			final int l13_$11 = (l3_n-1);
			final Func1<Object,Integer> l14_$9 = (Func1<Object, Integer>)(Integer ai2) -> {
				final Struct_MizNatFunc l15_c2 = ((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe((l8_all_0).f_evals, ((int)ai2)));
				final int l18_$17 = (Module_mobjects.f_mizNatCount(l8_c1)-1);
				final Func1<Object,Integer> l19_$16 = (Func1<Object, Integer>)(Integer aj1) -> {
					final Struct_MizIntPair l20_a1 = Module_mobjects.f_mizNatItem(l8_c1, ((int)aj1));
					final int l23_$22 = (Module_mobjects.f_mizNatCount(l15_c2)-1);
					final Func1<Object,Integer> l24_$21 = (Func1<Object, Integer>)(Integer aj2) -> {
						final Struct_MizIntPair l25_a2 = Module_mobjects.f_mizNatItem(l15_c2, ((int)aj2));
						if ((((l20_a1).f_y!=(l25_a2).f_y)&&Module_unifier.f_mizFrmsAreSimilar(l0_env, ((Struct)Module_vector.f_getVectorUnsafe((l1_pre).f_basic, (l20_a1).f_x)), ((Struct)Module_vector.f_getVectorUnsafe((l1_pre).f_basic, (l25_a2).f_x))))) {
							final Struct_MizPreInst l26_insts = Module_unifier.f_mizUnifyBasicFrm(l9_u, ((Struct)Module_vector.f_getVectorUnsafe((l1_pre).f_basic, (l20_a1).f_x)), ((Struct)Module_vector.f_getVectorUnsafe((l1_pre).f_basic, (l25_a2).f_x)));
							if (((Module_prechecker.f_mizPreInstCount(l26_insts)!=0)&&!(l26_insts).f_top)) {
								return Module_vector.f_pushVector(l2_out, (new Struct_MizComplPair(((int)ai1), ((int)ai2), ((int)aj1), ((int)aj2), l26_insts)));
							} else {
								return null;
							}
						} else {
							return null;
						}
					};
					return Module_runtime.f_fori(0, l23_$22, l24_$21);
				};
				return Module_runtime.f_fori(0, l18_$17, l19_$16);
			};
			return Module_runtime.f_fori(l12_$10, l13_$11, l14_$9);
		};
		Module_runtime.f_fori(0, l6_$5, l7_$4);
		return l2_out;
	}
	public static final Struct_MizPreInst f_mizCompInstAsFalse(Struct_MizUnifier au, Struct af) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizEqualizer l1_eq_0 = (au).f_eq;
		final Struct_MizPreInst l2_res = Module_unifier.f_mizUniBottom();
		final int l3_srt = Module_correl.f_mizFrmSort(af);
		final Reference<Boolean> l4_handled = ((Reference<Boolean>)(new Reference(false)));
		if ((l3_srt==Module_lexicon.g_ikFrmPred)) {
			Struct_MizPredFrm l5_pf;
			Struct l6__tmp = af;
			switch (l6__tmp.getTypeId()) {
			case 55/*MizPredFrm*/: {
				final Struct_MizPredFrm l7__tmp = (Struct_MizPredFrm)l6__tmp;
				final int l8___ = (l7__tmp).f_sort;
				final int l9___ = (l7__tmp).f_predNr;
				final Struct l10___ = (l7__tmp).f_args;
				final int l11___ = (l7__tmp).f_pattNr;
				l5_pf=l7__tmp;
				break;
			}
			default: {
				l5_pf=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
				break;
			}
			}
			final Struct_Pair l12_pa = Module_correl.f_mizAdjustFrm(l0_env, l5_pf);
			final Struct_MizConstr l13_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, (l5_pf).f_predNr);
			if (Module_correl.f_mizHasProperty(l13_c, Module_correl.g_syReflexivity)) {
				final Struct_MizPreInst l14_l = Module_unifier.f_mizUnifyReflexive(au, af);
				Module_unifier.f_mizUniUnion(au, l2_res, l14_l);
			} else {
			}
			final int l15_rqBel = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqBelongsTo);
			final int l16_rqIncl = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqInclusion);
			final int l17_rqElem = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqElement);
			final int l18_rqPow = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqPowerSet);
			final int l19_rqEmp = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEmpty);
			final int l20_rqEq = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEqualsTo);
			if ((((((int)(l12_pa).f_first)==l15_rqBel)&&(l15_rqBel!=0))&&(l17_rqElem>0))) {
				final Struct l21_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l12_pa).f_second), 0);
				final Struct l22_lRight = Module_correl.f_mizTrmListNth(((Struct)(l12_pa).f_second), 1);
				final int l25_$24 = (au).f_eqClassNbr;
				final Struct_MizUnifier l27_u = au;
				final Func1<Object,Integer> l26_$23 = (Func1<Object, Integer>)(Integer ak) -> {
					final Struct_MizAttrColl l27_sc = ((Struct_MizAttrColl)Module_vector.f_getVectorUnsafe((l27_u).f_eqClassSuper, ((int)ak)));
					final Reference<Boolean> l28_nonEmpty = ((Reference<Boolean>)(new Reference(false)));
					final Struct_Vector l31_$30 = (l27_sc).f_items;
					final Func1<Object,Struct_MizAttr> l32_$29 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aat) -> {
						if (((((aat).f_attrNr==l19_rqEmp)&&(l19_rqEmp!=0))&&((aat).f_neg==0))) {
							l28_nonEmpty.value = ((Boolean)true);
							return null;
						} else {
							return null;
						}
					};
					Module_vector.f_iterVector(l31_$30, ((Func1<Object,Object>)(Func1)l32_$29));
					if (((boolean)l28_nonEmpty.value)) {
						final Struct_MizPreInst l33_l = Module_unifier.f_mizUniTrm(l27_u, l22_lRight, ((Struct)Module_vector.f_getVectorUnsafe((l27_u).f_eTrm, ((int)ak))));
						if ((Module_prechecker.f_mizPreInstCount(l33_l)!=0)) {
							final Struct_MizTyp l34_lTyp = Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), l17_rqElem, Module_correl.f_mizNewTrmList(((Struct)Module_vector.f_getVectorUnsafe((l27_u).f_eTrm, ((int)ak))), ((Struct)SingletonStructs.str_MizTrmNil)));
							final Struct_MizPreInst l35_l1 = Module_unifier.f_mizUniBottom();
							final int l38_$37 = (l27_u).f_eqClassNbr;
							final Func1<Object,Integer> l39_$36 = (Func1<Object, Integer>)(Integer ai) -> {
								final Reference<Boolean> l40_hit = ((Reference<Boolean>)(new Reference(false)));
								final Struct_Vector l43_$42 = ((Struct_Vector)Module_vector.f_getVectorUnsafe((l27_u).f_eqClassType, ((int)ai)));
								final Func1<Object,Struct_MizTyp> l44_$41 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
									if ((!((boolean)l40_hit.value)&&Module_correl_inst.f_mizEqRadices(l0_env, axt, l34_lTyp))) {
										l40_hit.value = ((Boolean)true);
										return null;
									} else {
										return null;
									}
								};
								Module_vector.f_iterVector(l43_$42, ((Func1<Object,Object>)(Func1)l44_$41));
								if (((boolean)l40_hit.value)) {
									final Struct_MizPreInst l45_l2 = Module_unifier.f_mizUniTrm(l27_u, l21_lLeft, ((Struct)Module_vector.f_getVectorUnsafe((l27_u).f_eTrm, ((int)ai))));
									return Module_unifier.f_mizUniUnion(l27_u, l35_l1, l45_l2);
								} else {
									return null;
								}
							};
							Module_runtime.f_fori(1, l38_$37, l39_$36);
							Module_unifier.f_mizUniJoin(l27_u, l33_l, l35_l1);
							return Module_unifier.f_mizUniUnion(l27_u, l2_res, l33_l);
						} else {
							return null;
						}
					} else {
						return null;
					}
				};
				Module_runtime.f_fori(1, l25_$24, l26_$23);
			} else {
				if (((((((int)(l12_pa).f_first)==l16_rqIncl)&&(l16_rqIncl!=0))&&(l17_rqElem>0))&&(l18_rqPow>0))) {
					final Struct l28_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l12_pa).f_second), 0);
					final Struct l29_lRight = Module_correl.f_mizTrmListNth(((Struct)(l12_pa).f_second), 1);
					final int l32_$31 = (au).f_eqClassNbr;
					final Struct_MizUnifier l34_u = au;
					final Func1<Object,Integer> l33_$30 = (Func1<Object, Integer>)(Integer ak) -> {
						final Struct_MizPreInst l34_l = Module_unifier.f_mizUniTrm(l34_u, l29_lRight, ((Struct)Module_vector.f_getVectorUnsafe((l34_u).f_eTrm, ((int)ak))));
						if ((Module_prechecker.f_mizPreInstCount(l34_l)!=0)) {
							final Struct l35_lTrm = Module_correl.f_mizNewFuncTrm(l18_rqPow, Module_correl.f_mizNewTrmList(((Struct)Module_vector.f_getVectorUnsafe((l34_u).f_eTrm, ((int)ak))), ((Struct)SingletonStructs.str_MizTrmNil)));
							final int l36_lEqNr = Module_unifier.f_mizEqClassNr(l34_u, l35_lTrm);
							final Struct_MizPreInst l37_l1 = Module_unifier.f_mizUniBottom();
							if ((l36_lEqNr!=0)) {
								final Struct_MizTyp l38_lTyp = Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), l17_rqElem, Module_correl.f_mizNewTrmList(((Struct)Module_vector.f_getVectorUnsafe((l34_u).f_eTrm, l36_lEqNr)), ((Struct)SingletonStructs.str_MizTrmNil)));
								final int l41_$40 = (l34_u).f_eqClassNbr;
								final Func1<Object,Integer> l42_$39 = (Func1<Object, Integer>)(Integer ai) -> {
									final Reference<Boolean> l43_hit = ((Reference<Boolean>)(new Reference(false)));
									final Struct_Vector l46_$45 = ((Struct_Vector)Module_vector.f_getVectorUnsafe((l34_u).f_eqClassType, ((int)ai)));
									final Func1<Object,Struct_MizTyp> l47_$44 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
										if ((!((boolean)l43_hit.value)&&Module_correl_inst.f_mizEqRadices(l0_env, axt, l38_lTyp))) {
											l43_hit.value = ((Boolean)true);
											return null;
										} else {
											return null;
										}
									};
									Module_vector.f_iterVector(l46_$45, ((Func1<Object,Object>)(Func1)l47_$44));
									if (((boolean)l43_hit.value)) {
										final Struct_MizPreInst l48_l2 = Module_unifier.f_mizUniTrm(l34_u, l28_lLeft, ((Struct)Module_vector.f_getVectorUnsafe((l34_u).f_eTrm, ((int)ai))));
										return Module_unifier.f_mizUniUnion(l34_u, l37_l1, l48_l2);
									} else {
										return null;
									}
								};
								Module_runtime.f_fori(1, l41_$40, l42_$39);
							} else {
							}
							Module_unifier.f_mizUniJoin(l34_u, l34_l, l37_l1);
							return Module_unifier.f_mizUniUnion(l34_u, l2_res, l34_l);
						} else {
							return null;
						}
					};
					Module_runtime.f_fori(1, l32_$31, l33_$30);
				} else {
					if (((((int)(l12_pa).f_first)==l20_rqEq)&&(l20_rqEq!=0))) {
						l4_handled.value = ((Boolean)true);
					} else {
					}
				}
			}
			if (!((boolean)l4_handled.value)) {
				final Struct_Vector l37_$36 = (l1_eq_0).f_posBas;
				final Struct_MizUnifier l39_u = au;
				final Struct l40_f = af;
				final Func1<Object,Struct> l38_$35 = (Func1<Object, Struct>)(Struct apfb) -> {
					final Struct_MizPreInst l39_l = Module_unifier.f_mizUniFrm(l39_u, l40_f, apfb);
					return Module_unifier.f_mizUniUnion(l39_u, l2_res, l39_l);
				};
				Module_vector.f_iterVector(l37_$36, ((Func1<Object,Object>)(Func1)l38_$35));
				l4_handled.value = ((Boolean)true);
			} else {
			}
		} else {
			if ((l3_srt==Module_lexicon.g_ikFrmAttr)) {
				final Struct_MizPreInst l41_l = Module_unifier.f_mizUniAttrFrm(au, af, 1);
				Module_unifier.f_mizUniUnion(au, l2_res, l41_l);
				l4_handled.value = ((Boolean)true);
			} else {
				if ((l3_srt==Module_lexicon.g_ikFrmQual)) {
					Struct_MizQualFrm l42_qf;
					Struct l43__tmp = af;
					switch (l43__tmp.getTypeId()) {
					case 60/*MizQualFrm*/: {
						final Struct_MizQualFrm l44__tmp = (Struct_MizQualFrm)l43__tmp;
						final Struct l45___ = (l44__tmp).f_trm;
						final Struct_MizTyp l46___ = (l44__tmp).f_typ;
						final int l47___ = (l44__tmp).f_pattNr;
						l42_qf=l44__tmp;
						break;
					}
					default: {
						l42_qf=(new Struct_MizQualFrm((new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), Module_correl.f_mizNewIncorTyp(), 0));
						break;
					}
					}
					final int l50_$49 = (au).f_eqClassNbr;
					final Struct_MizUnifier l52_u = au;
					final Struct_MizQualFrm l53_qf = l42_qf;
					final Func1<Object,Integer> l51_$48 = (Func1<Object, Integer>)(Integer ak) -> {
						final Struct_MizPreInst l52_l = Module_unifier.f_mizUniTrm(l52_u, (l53_qf).f_trm, ((Struct)Module_vector.f_getVectorUnsafe((l52_u).f_eTrm, ((int)ak))));
						if ((Module_prechecker.f_mizPreInstCount(l52_l)!=0)) {
							final Struct_MizPreInst l53_l1 = Module_unifier.f_mizUniEqClassTyps(l52_u, ((int)ak), (l53_qf).f_typ);
							Module_unifier.f_mizUniJoin(l52_u, l52_l, l53_l1);
							return Module_unifier.f_mizUniUnion(l52_u, l2_res, l52_l);
						} else {
							return null;
						}
					};
					Module_runtime.f_fori(1, l50_$49, l51_$48);
					l4_handled.value = ((Boolean)true);
				} else {
				}
			}
		}
		if (!((boolean)l4_handled.value)) {
			final Struct_Vector l56_$55 = (l1_eq_0).f_posBas;
			final Struct_MizUnifier l58_u = au;
			final Struct l59_f = af;
			final Func1<Object,Struct> l57_$54 = (Func1<Object, Struct>)(Struct apfb) -> {
				final Struct_MizPreInst l58_l = Module_unifier.f_mizUniFrm(l58_u, l59_f, apfb);
				return Module_unifier.f_mizUniUnion(l58_u, l2_res, l58_l);
			};
			Module_vector.f_iterVector(l56_$55, ((Func1<Object,Object>)(Func1)l57_$54));
		} else {
		}
		return l2_res;
	}
	public static final Struct_MizPreInst f_mizCompInstAsTrue(Struct_MizUnifier au, Struct af) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizEqualizer l1_eq_0 = (au).f_eq;
		final Struct_MizPreInst l2_res = Module_unifier.f_mizUniBottom();
		final int l3_srt = Module_correl.f_mizFrmSort(af);
		final Reference<Boolean> l4_skipNegSweep = ((Reference<Boolean>)(new Reference(false)));
		if ((l3_srt==Module_lexicon.g_ikFrmPred)) {
			Struct_MizPredFrm l5_pf;
			Struct l6__tmp = af;
			switch (l6__tmp.getTypeId()) {
			case 55/*MizPredFrm*/: {
				final Struct_MizPredFrm l7__tmp = (Struct_MizPredFrm)l6__tmp;
				final int l8___ = (l7__tmp).f_sort;
				final int l9___ = (l7__tmp).f_predNr;
				final Struct l10___ = (l7__tmp).f_args;
				final int l11___ = (l7__tmp).f_pattNr;
				l5_pf=l7__tmp;
				break;
			}
			default: {
				l5_pf=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
				break;
			}
			}
			final Struct_Pair l12_pa = Module_correl.f_mizAdjustFrm(l0_env, l5_pf);
			final Struct_MizConstr l13_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, (l5_pf).f_predNr);
			if (Module_correl.f_mizHasProperty(l13_c, Module_correl.g_syIrreflexivity)) {
				final Struct_MizPreInst l14_l = Module_unifier.f_mizUnifyReflexive(au, af);
				Module_unifier.f_mizUniUnion(au, l2_res, l14_l);
			} else {
			}
			final int l15_rqBel = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqBelongsTo);
			final int l16_rqIncl = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqInclusion);
			final int l17_rqElem = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqElement);
			final int l18_rqPow = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqPowerSet);
			final int l19_rqEmp = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEmpty);
			if (((((int)(l12_pa).f_first)==l15_rqBel)&&(l15_rqBel!=0))) {
				final Struct l20_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l12_pa).f_second), 0);
				final Struct l21_lRight = Module_correl.f_mizTrmListNth(((Struct)(l12_pa).f_second), 1);
				final int l24_$23 = (au).f_eqClassNbr;
				final Struct_MizUnifier l26_u = au;
				final Func1<Object,Integer> l25_$22 = (Func1<Object, Integer>)(Integer ak) -> {
					final Struct_MizAttrColl l26_sc = ((Struct_MizAttrColl)Module_vector.f_getVectorUnsafe((l26_u).f_eqClassSuper, ((int)ak)));
					final Reference<Boolean> l27_found = ((Reference<Boolean>)(new Reference(false)));
					final Struct_Vector l30_$29 = (l26_sc).f_items;
					final Func1<Object,Struct_MizAttr> l31_$28 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aat) -> {
						if (((((aat).f_attrNr==l19_rqEmp)&&(l19_rqEmp!=0))&&((aat).f_neg==1))) {
							l27_found.value = ((Boolean)true);
							return null;
						} else {
							return null;
						}
					};
					Module_vector.f_iterVector(l30_$29, ((Func1<Object,Object>)(Func1)l31_$28));
					if (((boolean)l27_found.value)) {
						final Struct_MizPreInst l32_l = Module_unifier.f_mizUniTrm(l26_u, l21_lRight, ((Struct)Module_vector.f_getVectorUnsafe((l26_u).f_eTrm, ((int)ak))));
						return Module_unifier.f_mizUniUnion(l26_u, l2_res, l32_l);
					} else {
						return null;
					}
				};
				Module_runtime.f_fori(1, l24_$23, l25_$22);
				final Struct_Vector l29_$28 = (l1_eq_0).f_negBas;
				final Struct_MizUnifier l31_u = au;
				final Func1<Object,Struct> l30_$27 = (Func1<Object, Struct>)(Struct anf) -> {
					Struct l31__tmp = anf;
					switch (l31__tmp.getTypeId()) {
					case 60/*MizQualFrm*/: {
						final Struct_MizQualFrm l32__tmp = (Struct_MizQualFrm)l31__tmp;
						final Struct l33_qtrm = l32__tmp.f_trm;
						final Struct_MizTyp l34_qtyp = l32__tmp.f_typ;
						final Struct l35_qtrm = (l32__tmp).f_trm;
						final Struct_MizTyp l36_qtyp = (l32__tmp).f_typ;
						final int l37___ = (l32__tmp).f_pattNr;
						if (((l36_qtyp).f_sort==Module_lexicon.g_ikTypMode)) {
							final Struct_Pair l38_pm = Module_correl.f_mizAdjustTyp(l0_env, l36_qtyp);
							if (((((int)(l38_pm).f_first)==l17_rqElem)&&(l17_rqElem!=0))) {
								final Struct_MizPreInst l39_l = Module_unifier.f_mizUniTrm(l31_u, l20_lLeft, l35_qtrm);
								if ((Module_prechecker.f_mizPreInstCount(l39_l)!=0)) {
									final Struct_MizPreInst l40_l1 = Module_unifier.f_mizUniTrm(l31_u, l21_lRight, Module_correl.f_mizTrmListNth(((Struct)(l38_pm).f_second), 0));
									Module_unifier.f_mizUniJoin(l31_u, l39_l, l40_l1);
									return Module_unifier.f_mizUniUnion(l31_u, l2_res, l39_l);
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
				};
				Module_vector.f_iterVector(l29_$28, ((Func1<Object,Object>)(Func1)l30_$27));
			} else {
				if ((((((int)(l12_pa).f_first)==l16_rqIncl)&&(l16_rqIncl!=0))&&(l18_rqPow>0))) {
					final Struct l32_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l12_pa).f_second), 0);
					final Struct l33_lRight = Module_correl.f_mizTrmListNth(((Struct)(l12_pa).f_second), 1);
					final Struct_Vector l36_$35 = (l1_eq_0).f_negBas;
					final Struct_MizUnifier l38_u = au;
					final Func1<Object,Struct> l37_$34 = (Func1<Object, Struct>)(Struct anf) -> {
						Struct l38__tmp = anf;
						switch (l38__tmp.getTypeId()) {
						case 60/*MizQualFrm*/: {
							final Struct_MizQualFrm l39__tmp = (Struct_MizQualFrm)l38__tmp;
							final Struct l40_qtrm = l39__tmp.f_trm;
							final Struct_MizTyp l41_qtyp = l39__tmp.f_typ;
							final Struct l42_qtrm = (l39__tmp).f_trm;
							final Struct_MizTyp l43_qtyp = (l39__tmp).f_typ;
							final int l44___ = (l39__tmp).f_pattNr;
							if (((l43_qtyp).f_sort==Module_lexicon.g_ikTypMode)) {
								final Struct_Pair l45_pm = Module_correl.f_mizAdjustTyp(l0_env, l43_qtyp);
								final int l46_rqElem2 = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqElement);
								if (((((int)(l45_pm).f_first)==l46_rqElem2)&&(l46_rqElem2!=0))) {
									final Struct_MizPreInst l47_l = Module_unifier.f_mizUniTrm(l38_u, l32_lLeft, l42_qtrm);
									if ((Module_prechecker.f_mizPreInstCount(l47_l)!=0)) {
										final Struct l48_lTrm = Module_correl.f_mizNewFuncTrm(l18_rqPow, Module_correl.f_mizNewTrmList(Module_correl.f_mizCopyTrm(l33_lRight), ((Struct)SingletonStructs.str_MizTrmNil)));
										final Struct_MizPreInst l49_l1 = Module_unifier.f_mizUniTrm(l38_u, l48_lTrm, Module_correl.f_mizTrmListNth(((Struct)(l45_pm).f_second), 0));
										Module_unifier.f_mizUniJoin(l38_u, l47_l, l49_l1);
										return Module_unifier.f_mizUniUnion(l38_u, l2_res, l47_l);
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
					};
					Module_vector.f_iterVector(l36_$35, ((Func1<Object,Object>)(Func1)l37_$34));
				} else {
				}
			}
		} else {
			if ((l3_srt==Module_lexicon.g_ikFrmAttr)) {
				final Struct_MizPreInst l39_l = Module_unifier.f_mizUniAttrFrm(au, af, 0);
				Module_unifier.f_mizUniUnion(au, l2_res, l39_l);
				l4_skipNegSweep.value = ((Boolean)true);
			} else {
				if ((l3_srt==Module_lexicon.g_ikFrmQual)) {
					Struct_MizQualFrm l40_qf;
					Struct l41__tmp = af;
					switch (l41__tmp.getTypeId()) {
					case 60/*MizQualFrm*/: {
						final Struct_MizQualFrm l42__tmp = (Struct_MizQualFrm)l41__tmp;
						final Struct l43___ = (l42__tmp).f_trm;
						final Struct_MizTyp l44___ = (l42__tmp).f_typ;
						final int l45___ = (l42__tmp).f_pattNr;
						l40_qf=l42__tmp;
						break;
					}
					default: {
						l40_qf=(new Struct_MizQualFrm((new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), Module_correl.f_mizNewIncorTyp(), 0));
						break;
					}
					}
					final Struct_Vector l48_$47 = (l1_eq_0).f_negBas;
					final Struct_MizUnifier l50_u = au;
					final Struct_MizQualFrm l51_qf = l40_qf;
					final Func1<Object,Struct> l49_$46 = (Func1<Object, Struct>)(Struct anf) -> {
						Struct l50__tmp = anf;
						switch (l50__tmp.getTypeId()) {
						case 60/*MizQualFrm*/: {
							final Struct_MizQualFrm l51__tmp = (Struct_MizQualFrm)l50__tmp;
							final Struct l52_qtrm = l51__tmp.f_trm;
							final Struct_MizTyp l53_qtyp = l51__tmp.f_typ;
							final Struct l54_qtrm = (l51__tmp).f_trm;
							final Struct_MizTyp l55_qtyp = (l51__tmp).f_typ;
							final int l56___ = (l51__tmp).f_pattNr;
							final Struct_MizPreInst l57_l = Module_unifier.f_mizUniTrm(l50_u, (l51_qf).f_trm, l54_qtrm);
							if ((Module_prechecker.f_mizPreInstCount(l57_l)!=0)) {
								final Struct_MizPreInst l58_l1 = Module_unifier.f_mizUniBottom();
								if ((((l55_qtyp).f_sort==Module_lexicon.g_ikTypMode)&&(((l51_qf).f_typ).f_sort==Module_lexicon.g_ikTypMode))) {
									final Struct_Pair l59_pm = Module_correl.f_mizAdjustTyp(l0_env, l55_qtyp);
									final Reference<Struct_MizTyp> l60_cur = ((Reference<Struct_MizTyp>)(new Reference(Wrappers.w_mizCopyTyp.invoke((l51_qf).f_typ))));
									final Reference<Boolean> l61_go = ((Reference<Boolean>)(new Reference(true)));
									final Func0<Boolean> l64_$62 = (Func0<Boolean>)() -> {
										return ((Boolean)((((boolean)l61_go.value)&&((l60_cur.value).f_sort==Module_lexicon.g_ikTypMode))&&((l60_cur.value).f_modNr>=((int)(l59_pm).f_first))));
									};
									final Func0<Object> l65_$63 = (Func0<Object>)() -> {
										final Struct_MizPreInst l66_l2 = Module_unifier.f_mizUniRadices(l50_u, l60_cur.value, l55_qtyp);
										Module_unifier.f_mizUniUnion(l50_u, l58_l1, l66_l2);
										final Struct l67_gsymswitch16 = Module_correl_inst.f_mizWidening(l0_env, l60_cur.value);
										Struct l68__tmp = l67_gsymswitch16;
										switch (l68__tmp.getTypeId()) {
										case 83/*Some*/: {
											final Struct_Some l69__tmp = (Struct_Some)l68__tmp;
											final Object l70_w = l69__tmp.f_value;
											final Struct_MizTyp l71_w = ((Struct_MizTyp)(l69__tmp).f_value);
											l60_cur.value = l71_w;
											return null;
										}
										case 75/*None*/: {
											l61_go.value = ((Boolean)false);
											return null;
										}
										default:
											throw new RuntimeException("Unexpected struct in switch: "+l68__tmp.getTypeName());
										}
									};
									Module_loop.f_while(l64_$62, l65_$63);
								} else {
									if (((l55_qtyp).f_sort==Module_lexicon.g_ikTypStruct)) {
										final Struct l66_gsymswitch15 = Module_correl_inst.f_mizWideningOf(l0_env, l55_qtyp, Wrappers.w_mizCopyTyp.invoke((l51_qf).f_typ));
										Struct l67__tmp = l66_gsymswitch15;
										switch (l67__tmp.getTypeId()) {
										case 83/*Some*/: {
											final Struct_Some l68__tmp = (Struct_Some)l67__tmp;
											final Object l69_w = l68__tmp.f_value;
											final Struct_MizTyp l70_w = ((Struct_MizTyp)(l68__tmp).f_value);
											final Struct_MizPreInst l71_l2 = Module_unifier.f_mizUniRadices(l50_u, l70_w, l55_qtyp);
											Module_unifier.f_mizUniUnion(l50_u, l58_l1, l71_l2);
											break;
										}
										case 75/*None*/: {
											break;
										}
										default:
											throw new RuntimeException("Unexpected struct in switch: "+l67__tmp.getTypeName());
										}
									} else {
									}
								}
								Module_unifier.f_mizUniJoin(l50_u, l57_l, l58_l1);
								return Module_unifier.f_mizUniUnion(l50_u, l2_res, l57_l);
							} else {
								return null;
							}
						}
						default: {
							return null;
						}
						}
					};
					Module_vector.f_iterVector(l48_$47, ((Func1<Object,Object>)(Func1)l49_$46));
					final int l54_$53 = (au).f_eqClassNbr;
					final Struct_MizUnifier l56_u = au;
					final Struct_MizQualFrm l57_qf = l40_qf;
					final Func1<Object,Integer> l55_$52 = (Func1<Object, Integer>)(Integer ak) -> {
						final Struct_MizPreInst l56_l = Module_unifier.f_mizUniTrm(l56_u, (l57_qf).f_trm, ((Struct)Module_vector.f_getVectorUnsafe((l56_u).f_eTrm, ((int)ak))));
						if ((Module_prechecker.f_mizPreInstCount(l56_l)!=0)) {
							final Struct_MizPreInst l57_l1 = Module_unifier.f_mizUniBottom();
							final Struct_Vector l60_$59 = (((l57_qf).f_typ).f_upper).f_items;
							final Func1<Object,Struct_MizAttr> l61_$58 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr ax) -> {
								final Struct_Vector l64_$63 = (((Struct_MizAttrColl)Module_vector.f_getVectorUnsafe((l56_u).f_eqClassSuper, ((int)ak)))).f_items;
								final Func1<Object,Struct_MizAttr> l65_$62 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr av) -> {
									final Struct_MizPreInst l66_ll = Module_unifier.f_mizUniAttr(l56_u, ax, av, false);
									return Module_unifier.f_mizUniUnion(l56_u, l57_l1, l66_ll);
								};
								return Module_vector.f_iterVector(l64_$63, ((Func1<Object,Object>)(Func1)l65_$62));
							};
							Module_vector.f_iterVector(l60_$59, ((Func1<Object,Object>)(Func1)l61_$58));
							Module_unifier.f_mizUniJoin(l56_u, l56_l, l57_l1);
							return Module_unifier.f_mizUniUnion(l56_u, l2_res, l56_l);
						} else {
							return null;
						}
					};
					Module_runtime.f_fori(1, l54_$53, l55_$52);
					l4_skipNegSweep.value = ((Boolean)true);
				} else {
				}
			}
		}
		if (!((boolean)l4_skipNegSweep.value)) {
			final Struct_Vector l60_$59 = (l1_eq_0).f_negBas;
			final Struct_MizUnifier l62_u = au;
			final Struct l63_f = af;
			final Func1<Object,Struct> l61_$58 = (Func1<Object, Struct>)(Struct anf) -> {
				final Struct_MizPreInst l62_l = Module_unifier.f_mizUniFrm(l62_u, l63_f, anf);
				return Module_unifier.f_mizUniUnion(l62_u, l2_res, l62_l);
			};
			Module_vector.f_iterVector(l60_$59, ((Func1<Object,Object>)(Func1)l61_$58));
		} else {
		}
		return l2_res;
	}
	public static final Struct_MizPreInst f_mizCreateClauses(Struct_MizUnifier au, Struct_Vector auniv) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizPre l1_pre = (au).f_pre;
		final Struct_MizPreInst l2_all_0 = Module_prechecker.f_mizPreInstBottom();
		final Struct_MizUnifier l5_u = au;
		final Func1<Object,Struct> l4_$3 = (Func1<Object, Struct>)(Struct af0) -> {
			((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
			final Struct l5_lFrm0 = Module_unifier.f_mizPositivelyStandarized(l5_u, af0);
			((Field_freeVarBase)l5_u).set_freeVarBase(Module_vector.f_sizeVector((l5_u).f_freeVarType));
			final Struct l6_lFrm = Module_unifier.f_mizURemoveExtQuantifier(l5_u, l5_lFrm0);
			((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
			final Struct_MizPreInst l7_nf = Module_prechecker.f_mizNormalizeAsFalse(l1_pre, l6_lFrm);
			return Module_prechecker.f_mizPreInstUnionWith(l1_pre, l2_all_0, l7_nf);
		};
		Module_vector.f_iterVector(auniv, ((Func1<Object,Object>)(Func1)l4_$3));
		return l2_all_0;
	}
	public static final Struct_Pair f_mizCreateClausesDbg(Struct_MizUnifier au, Struct_Vector auniv) {
		final Struct_MizPre l0_pre = (au).f_pre;
		Module_vector.f_clearVector((au).f_freeVarType);
		((Field_unifBase)au).set_unifBase(0);
		((Field_freeVarUnifs)au).set_freeVarUnifs(Module_tree.f_makeTree());
		Module_vector.f_clearVector((l0_pre).f_basic);
		final Struct_MizPreInst l1_all_0 = Module_unifier.f_mizCreateClauses(au, auniv);
		final int l2_n = Module_prechecker.f_mizPreInstCount(l1_all_0);
		int l3_c;
		if ((((l2_n>=2)&&(l2_n<=4))&&!(l1_all_0).f_top)) {
			l3_c=Module_vector.f_sizeVector(Module_unifier.f_mizCollectComplementaryLiterals(au, l1_all_0));
		} else {
			l3_c=(-1);
		}
		return (new Struct_Pair(l1_all_0, l3_c));
	}
	public static final int f_mizEqClassNr(Struct_MizUnifier au, Struct at) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		Struct l1__tmp = at;
		switch (l1__tmp.getTypeId()) {
		case 70/*MizVarTrm*/: {
			final Struct_MizVarTrm l2__tmp = (Struct_MizVarTrm)l1__tmp;
			final int l3_srt = l2__tmp.f_sort;
			final int l4_varNr = l2__tmp.f_varNr;
			final int l5_srt = (l2__tmp).f_sort;
			final int l6_varNr = (l2__tmp).f_varNr;
			final int l7___ = (l2__tmp).f_pattNr;
			final int l8___ = (l2__tmp).f_trmInfo;
			if ((l5_srt==Module_lexicon.g_ikTrmEqConst)) {
				return l6_varNr;
			} else {
				if ((l5_srt==Module_lexicon.g_ikTrmNumeral)) {
					final Reference<Integer> l9_res = ((Reference<Integer>)(new Reference(0)));
					final int l12_$11 = (au).f_eqClassNbr;
					final Struct_MizUnifier l14_u = au;
					final Func1<Object,Integer> l13_$10 = (Func1<Object, Integer>)(Integer ai) -> {
						if ((((int)l9_res.value)==0)) {
							final Struct l14_gsymswitch3 = ((Struct)Module_vector.f_getVectorUnsafe((l14_u).f_eqClassVal, ((int)ai)));
							Struct l15__tmp = l14_gsymswitch3;
							switch (l15__tmp.getTypeId()) {
							case 83/*Some*/: {
								final Struct_Some l16__tmp = (Struct_Some)l15__tmp;
								final Object l17_v = l16__tmp.f_value;
								final Struct_MizComplex l18_v = ((Struct_MizComplex)(l16__tmp).f_value);
								if ((((l18_v).f_re==Module_math.f_i2d(l6_varNr))&&((l18_v).f_im==0.0))) {
									l9_res.value = ai;
									return null;
								} else {
									return null;
								}
							}
							case 75/*None*/: {
								return null;
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l15__tmp.getTypeName());
							}
						} else {
							return null;
						}
					};
					Module_runtime.f_fori(1, l12_$11, l13_$10);
					return ((int)l9_res.value);
				} else {
					if ((l5_srt==Module_lexicon.g_ikTrmInfConst)) {
						if (Module_mobjects.f_mizNatHasInDom((au).f_dConstEqClass, l6_varNr)) {
							return Module_mobjects.f_mizNatValue((au).f_dConstEqClass, l6_varNr);
						} else {
							return 0;
						}
					} else {
						return 0;
					}
				}
			}
		}
		case 44/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l2__tmp = (Struct_MizFuncTrm)l1__tmp;
			final int l15_srt = l2__tmp.f_sort;
			final int l16_funcNr = l2__tmp.f_funcNr;
			final Struct l17_args = l2__tmp.f_args;
			final int l18_srt = (l2__tmp).f_sort;
			final int l19_funcNr = (l2__tmp).f_funcNr;
			final Struct l20_args = (l2__tmp).f_args;
			final int l21___ = (l2__tmp).f_pattNr;
			final int l22___ = (l2__tmp).f_trmInfo;
			final Struct_Vector l23_argNrs = Module_vector.f_makeVector(4);
			final Reference<Boolean> l24_ok = ((Reference<Boolean>)(new Reference(true)));
			final Reference<Struct> l25_cur = ((Reference<Struct>)(new Reference(l20_args)));
			final Func0<Boolean> l28_$26 = (Func0<Boolean>)() -> {
				boolean l31_$30;
				if (((boolean)l24_ok.value)) {
					final Struct l32_gsymswitch4 = l25_cur.value;
					Struct l33__tmp = l32_gsymswitch4;
					switch (l33__tmp.getTypeId()) {
					case 64/*MizTrmNil*/: {
						l31_$30=false;
						break;
					}
					default: {
						l31_$30=true;
						break;
					}
					}
				} else {
					l31_$30=false;
				}
				return ((Boolean)l31_$30);
			};
			final Struct_MizUnifier l30_u = au;
			final Func0<Object> l29_$27 = (Func0<Object>)() -> {
				final Struct l30_gsymswitch5 = l25_cur.value;
				Struct l31__tmp = l30_gsymswitch5;
				switch (l31__tmp.getTypeId()) {
				case 63/*MizTrmElem*/: {
					final Struct_MizTrmElem l32__tmp = (Struct_MizTrmElem)l31__tmp;
					final Struct l33_ta = l32__tmp.f_trm;
					final Struct l34_na = l32__tmp.f_next;
					final Struct l35_ta = (l32__tmp).f_trm;
					final Struct l36_na = (l32__tmp).f_next;
					final int l37_an = Module_unifier.f_mizEqClassNr(l30_u, l35_ta);
					if ((l37_an==0)) {
						l24_ok.value = ((Boolean)false);
					} else {
						Module_vector.f_pushVector(l23_argNrs, l37_an);
					}
					l25_cur.value = l36_na;
					return null;
				}
				case 64/*MizTrmNil*/: {
					return null;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l31__tmp.getTypeName());
				}
			};
			Module_loop.f_while(l28_$26, l29_$27);
			if (!((boolean)l24_ok.value)) {
				return 0;
			} else {
				int l31_kind;
				if ((l18_srt==Module_lexicon.g_ikTrmFunctor)) {
					l31_kind=Module_unifier.g_expUFunctor;
				} else {
					if ((l18_srt==Module_lexicon.g_ikTrmSchFunc)) {
						l31_kind=Module_unifier.g_expUSchFunc;
					} else {
						if ((l18_srt==Module_lexicon.g_ikTrmAggreg)) {
							l31_kind=Module_unifier.g_expUAggreg;
						} else {
							l31_kind=Module_unifier.g_expUSelector;
						}
					}
				}
				final Reference<Integer> l32_res = ((Reference<Integer>)(new Reference(0)));
				final int l35_$34 = (au).f_eqClassNbr;
				final Struct_MizUnifier l37_u = au;
				final int l38_kind = l31_kind;
				final Func1<Object,Integer> l36_$33 = (Func1<Object, Integer>)(Integer aj) -> {
					if ((((int)l32_res.value)==0)) {
						final Struct_Vector l37_members = Module_unifier.f_mizUEqListAt(l37_u, l38_kind, ((int)aj));
						final Func1<Object,Struct> l39_$38 = (Func1<Object, Struct>)(Struct am) -> {
							if (((((int)l32_res.value)==0)&&Module_unifier.f_mizUMemberMatches(l37_u, l2__tmp, am, l23_argNrs, l38_kind))) {
								l32_res.value = aj;
								return null;
							} else {
								return null;
							}
						};
						return Module_vector.f_iterVector(l37_members, ((Func1<Object,Object>)(Func1)l39_$38));
					} else {
						return null;
					}
				};
				Module_runtime.f_fori(1, l35_$34, l36_$33);
				return ((int)l32_res.value);
			}
		}
		case 58/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l2__tmp = (Struct_MizPrivFuncTrm)l1__tmp;
			final int l39___ = (l2__tmp).f_funcNr;
			final Struct l40___ = (l2__tmp).f_args;
			final Struct l41___ = (l2__tmp).f_funcExp;
			final int l42___ = (l2__tmp).f_pattNr;
			final int l43___ = (l2__tmp).f_trmInfo;
			final Struct_Vector l44_argNrs = Module_vector.f_makeVector(4);
			final Reference<Boolean> l45_ok = ((Reference<Boolean>)(new Reference(true)));
			final Reference<Struct> l46_cur = ((Reference<Struct>)(new Reference(Module_unifier.f_mizUArgs(l2__tmp))));
			final Func0<Boolean> l49_$47 = (Func0<Boolean>)() -> {
				boolean l52_$51;
				if (((boolean)l45_ok.value)) {
					final Struct l53_gsymswitch6 = l46_cur.value;
					Struct l54__tmp = l53_gsymswitch6;
					switch (l54__tmp.getTypeId()) {
					case 64/*MizTrmNil*/: {
						l52_$51=false;
						break;
					}
					default: {
						l52_$51=true;
						break;
					}
					}
				} else {
					l52_$51=false;
				}
				return ((Boolean)l52_$51);
			};
			final Struct_MizUnifier l51_u = au;
			final Func0<Object> l50_$48 = (Func0<Object>)() -> {
				final Struct l51_gsymswitch7 = l46_cur.value;
				Struct l52__tmp = l51_gsymswitch7;
				switch (l52__tmp.getTypeId()) {
				case 63/*MizTrmElem*/: {
					final Struct_MizTrmElem l53__tmp = (Struct_MizTrmElem)l52__tmp;
					final Struct l54_ta = l53__tmp.f_trm;
					final Struct l55_na = l53__tmp.f_next;
					final Struct l56_ta = (l53__tmp).f_trm;
					final Struct l57_na = (l53__tmp).f_next;
					final int l58_an = Module_unifier.f_mizEqClassNr(l51_u, l56_ta);
					if ((l58_an==0)) {
						l45_ok.value = ((Boolean)false);
					} else {
						Module_vector.f_pushVector(l44_argNrs, l58_an);
					}
					l46_cur.value = l57_na;
					return null;
				}
				case 64/*MizTrmNil*/: {
					return null;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l52__tmp.getTypeName());
				}
			};
			Module_loop.f_while(l49_$47, l50_$48);
			if (!((boolean)l45_ok.value)) {
				return 0;
			} else {
				final Reference<Integer> l52_res = ((Reference<Integer>)(new Reference(0)));
				final int l55_$54 = (au).f_eqClassNbr;
				final Struct_MizUnifier l57_u = au;
				final Func1<Object,Integer> l56_$53 = (Func1<Object, Integer>)(Integer aj) -> {
					if ((((int)l52_res.value)==0)) {
						final Struct_Vector l59_$58 = Module_unifier.f_mizUEqListAt(l57_u, Module_unifier.g_expUPrivFunc, ((int)aj));
						final Func1<Object,Struct> l60_$57 = (Func1<Object, Struct>)(Struct am) -> {
							if (((((int)l52_res.value)==0)&&Module_unifier.f_mizUMemberMatches(l57_u, l2__tmp, am, l44_argNrs, Module_unifier.g_expUPrivFunc))) {
								l52_res.value = aj;
								return null;
							} else {
								return null;
							}
						};
						return Module_vector.f_iterVector(l59_$58, ((Func1<Object,Object>)(Func1)l60_$57));
					} else {
						return null;
					}
				};
				Module_runtime.f_fori(1, l55_$54, l56_$53);
				return ((int)l52_res.value);
			}
		}
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l2__tmp = (Struct_MizFraenkelTrm)l1__tmp;
			final Struct_Vector l58___ = (l2__tmp).f_lambdaArgs;
			final Struct l59___ = (l2__tmp).f_scope;
			final Struct l60___ = (l2__tmp).f_compr;
			final Struct_MizIntSeq l61___ = (l2__tmp).f_idents;
			final int l62___ = (l2__tmp).f_pattNr;
			final int l63___ = (l2__tmp).f_trmInfo;
			final Reference<Integer> l64_res = ((Reference<Integer>)(new Reference(0)));
			final int l67_$66 = (au).f_eqClassNbr;
			final Struct_MizUnifier l69_u = au;
			final Func1<Object,Integer> l68_$65 = (Func1<Object, Integer>)(Integer aj) -> {
				if ((((int)l64_res.value)==0)) {
					final Struct_Vector l71_$70 = Module_unifier.f_mizUEqListAt(l69_u, Module_unifier.g_expUFraenkel, ((int)aj));
					final Func1<Object,Struct> l72_$69 = (Func1<Object, Struct>)(Struct am) -> {
						if (((((int)l64_res.value)==0)&&Module_unifier.f_mizEqTrmsClassU(l69_u, l2__tmp, am))) {
							l64_res.value = aj;
							return null;
						} else {
							return null;
						}
					};
					return Module_vector.f_iterVector(l71_$70, ((Func1<Object,Object>)(Func1)l72_$69));
				} else {
					return null;
				}
			};
			Module_runtime.f_fori(1, l67_$66, l68_$65);
			return ((int)l64_res.value);
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l2__tmp = (Struct_MizChoiceTrm)l1__tmp;
			final Struct_MizTyp l70___ = (l2__tmp).f_choiceTyp;
			final int l71___ = (l2__tmp).f_pattNr;
			final int l72___ = (l2__tmp).f_trmInfo;
			final Reference<Integer> l73_res = ((Reference<Integer>)(new Reference(0)));
			final int l76_$75 = (au).f_eqClassNbr;
			final Struct_MizUnifier l78_u = au;
			final Func1<Object,Integer> l77_$74 = (Func1<Object, Integer>)(Integer aj) -> {
				if ((((int)l73_res.value)==0)) {
					final Struct_Vector l80_$79 = Module_unifier.f_mizUEqListAt(l78_u, Module_unifier.g_expUChoice, ((int)aj));
					final Func1<Object,Struct> l81_$78 = (Func1<Object, Struct>)(Struct am) -> {
						if (((((int)l73_res.value)==0)&&Module_unifier.f_mizEqTrmsClassU(l78_u, l2__tmp, am))) {
							l73_res.value = aj;
							return null;
						} else {
							return null;
						}
					};
					return Module_vector.f_iterVector(l80_$79, ((Func1<Object,Object>)(Func1)l81_$78));
				} else {
					return null;
				}
			};
			Module_runtime.f_fori(1, l76_$75, l77_$74);
			return ((int)l73_res.value);
		}
		default: {
			return 0;
		}
		}
	}
	public static final boolean f_mizEqClassTypsU(Struct_MizUnifier au, Struct_MizTyp aa, Struct_MizTyp ab) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizUnifier l3_u = au;
		final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l2_$1 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
			final Struct_Pair l3_px = Module_correl.f_mizAdjustAttr(l0_env, ax);
			final Struct_Pair l4_py = Module_correl.f_mizAdjustAttr(l0_env, ay);
			return ((Boolean)(((((int)(l3_px).f_first)==((int)(l4_py).f_first))&&((ax).f_neg==(ay).f_neg))&&Module_unifier.f_mizEqTLClassU(l3_u, ((Struct)(l3_px).f_second), ((Struct)(l4_py).f_second))));
		};
		if (!Module_correl.f_mizEqualClusters(aa, ab, l2_$1)) {
			return false;
		} else {
			if (((aa).f_sort!=(ab).f_sort)) {
				return false;
			} else {
				if (((aa).f_sort==Module_lexicon.g_ikTypMode)) {
					final Struct_Pair l4_pa = Module_correl.f_mizAdjustTyp(l0_env, aa);
					final Struct_Pair l5_pb = Module_correl.f_mizAdjustTyp(l0_env, ab);
					return ((((int)(l4_pa).f_first)==((int)(l5_pb).f_first))&&Module_unifier.f_mizEqTLClassU(au, ((Struct)(l4_pa).f_second), ((Struct)(l5_pb).f_second)));
				} else {
					return (((aa).f_modNr==(ab).f_modNr)&&Module_unifier.f_mizEqTLClassU(au, (aa).f_modArgs, (ab).f_modArgs));
				}
			}
		}
	}
	public static final boolean f_mizEqFrmsClassU(Struct_MizUnifier au, Struct aa, Struct ab) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final int l1_sa = Module_correl.f_mizFrmSort(aa);
		if ((l1_sa!=Module_correl.f_mizFrmSort(ab))) {
			return false;
		} else {
			if ((l1_sa==Module_lexicon.g_ikFrmVerum)) {
				return true;
			} else {
				Struct l2__tmp = aa;
				switch (l2__tmp.getTypeId()) {
				case 50/*MizNegFrm*/: {
					final Struct_MizNegFrm l3__tmp = (Struct_MizNegFrm)l2__tmp;
					final Struct l4_arg = l3__tmp.f_arg;
					final Struct l5_arg = (l3__tmp).f_arg;
					final int l6___ = (l3__tmp).f_pattNr;
					Struct_MizNegFrm l7_nb;
					Struct l8__tmp = ab;
					switch (l8__tmp.getTypeId()) {
					case 50/*MizNegFrm*/: {
						final Struct_MizNegFrm l9__tmp = (Struct_MizNegFrm)l8__tmp;
						final Struct l10___ = (l9__tmp).f_arg;
						final int l11___ = (l9__tmp).f_pattNr;
						l7_nb=l9__tmp;
						break;
					}
					default: {
						l7_nb=(new Struct_MizNegFrm((new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
						break;
					}
					}
					{
						final Struct l12___tmp = l5_arg;
						final Struct l13___tmp = (l7_nb).f_arg;
						aa = l12___tmp;
						ab = l13___tmp;
						continue TAIL_CALL;
					}
				}
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l3__tmp = (Struct_MizConjFrm)l2__tmp;
					final Struct_Vector l14_conjuncts = l3__tmp.f_conjuncts;
					final Struct_Vector l15_conjuncts = (l3__tmp).f_conjuncts;
					final int l16___ = (l3__tmp).f_pattNr;
					Struct_MizConjFrm l17_cb;
					Struct l18__tmp = ab;
					switch (l18__tmp.getTypeId()) {
					case 26/*MizConjFrm*/: {
						final Struct_MizConjFrm l19__tmp = (Struct_MizConjFrm)l18__tmp;
						final Struct_Vector l20___ = (l19__tmp).f_conjuncts;
						final int l21___ = (l19__tmp).f_pattNr;
						l17_cb=l19__tmp;
						break;
					}
					default: {
						l17_cb=(new Struct_MizConjFrm(Module_vector.f_makeVector(0), 0));
						break;
					}
					}
					if ((Module_vector.f_sizeVector(l15_conjuncts)!=Module_vector.f_sizeVector((l17_cb).f_conjuncts))) {
						return false;
					} else {
						final Reference<Boolean> l22_ok = ((Reference<Boolean>)(new Reference(true)));
						final Reference<Integer> l23_i = ((Reference<Integer>)(new Reference(0)));
						final Func0<Boolean> l26_$24 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l23_i.value)<Module_vector.f_sizeVector(l15_conjuncts))&&((boolean)l22_ok.value)));
						};
						final Struct_MizUnifier l28_u = au;
						final Struct_MizConjFrm l29_cb = l17_cb;
						final Func0<Object> l27_$25 = (Func0<Object>)() -> {
							if (!Module_unifier.f_mizEqFrmsClassU(l28_u, ((Struct)Module_vector.f_getVectorUnsafe(l15_conjuncts, ((int)l23_i.value))), ((Struct)Module_vector.f_getVectorUnsafe((l29_cb).f_conjuncts, ((int)l23_i.value))))) {
								l22_ok.value = ((Boolean)false);
							} else {
							}
							l23_i.value = ((Integer)(((int)l23_i.value)+1));
							return null;
						};
						Module_loop.f_while(l26_$24, l27_$25);
						return ((boolean)l22_ok.value);
					}
				}
				case 55/*MizPredFrm*/: {
					final Struct_MizPredFrm l3__tmp = (Struct_MizPredFrm)l2__tmp;
					final int l30_srt = l3__tmp.f_sort;
					final int l31_predNr = l3__tmp.f_predNr;
					final Struct l32_args = l3__tmp.f_args;
					final int l33_srt = (l3__tmp).f_sort;
					final int l34_predNr = (l3__tmp).f_predNr;
					final Struct l35_args = (l3__tmp).f_args;
					final int l36___ = (l3__tmp).f_pattNr;
					Struct_MizPredFrm l37_pb;
					Struct l38__tmp = ab;
					switch (l38__tmp.getTypeId()) {
					case 55/*MizPredFrm*/: {
						final Struct_MizPredFrm l39__tmp = (Struct_MizPredFrm)l38__tmp;
						final int l40___ = (l39__tmp).f_sort;
						final int l41___ = (l39__tmp).f_predNr;
						final Struct l42___ = (l39__tmp).f_args;
						final int l43___ = (l39__tmp).f_pattNr;
						l37_pb=l39__tmp;
						break;
					}
					default: {
						l37_pb=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
						break;
					}
					}
					Struct_MizPredFrm l44_pa2;
					Struct l45__tmp = l3__tmp;
					switch (l45__tmp.getTypeId()) {
					case 55/*MizPredFrm*/: {
						final Struct_MizPredFrm l46__tmp = (Struct_MizPredFrm)l45__tmp;
						final int l47___ = (l46__tmp).f_sort;
						final int l48___ = (l46__tmp).f_predNr;
						final Struct l49___ = (l46__tmp).f_args;
						final int l50___ = (l46__tmp).f_pattNr;
						l44_pa2=l46__tmp;
						break;
					}
					default: {
						l44_pa2=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
						break;
					}
					}
					if ((l33_srt==Module_lexicon.g_ikFrmPred)) {
						final Struct_Pair l51_qa = Module_correl.f_mizAdjustFrm(l0_env, l44_pa2);
						final Struct_Pair l52_qb = Module_correl.f_mizAdjustFrm(l0_env, l37_pb);
						if ((((int)(l51_qa).f_first)!=((int)(l52_qb).f_first))) {
							return false;
						} else {
							if (Module_unifier.f_mizEqTLClassU(au, ((Struct)(l51_qa).f_second), ((Struct)(l52_qb).f_second))) {
								return true;
							} else {
								final Struct_MizConstr l53_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, (l44_pa2).f_predNr);
								if (Module_correl.f_mizHasProperty(l53_c, Module_correl.g_sySymmetry)) {
									final Struct l54_sw = Module_roundcl.f_mizSwapArguments((l44_pa2).f_args, (l53_c).f_firstArg, (l53_c).f_secondArg);
									final Struct l55_swAdj = Module_unifier.f_mizUAdjustArgs(l0_env, (l44_pa2).f_predNr, l54_sw);
									return Module_unifier.f_mizEqTLClassU(au, l55_swAdj, ((Struct)(l52_qb).f_second));
								} else {
									return false;
								}
							}
						}
					} else {
						if ((l33_srt==Module_lexicon.g_ikFrmAttr)) {
							final Struct_Pair l56_qa = Module_correl.f_mizAdjustAttrFrm(l0_env, l44_pa2);
							final Struct_Pair l57_qb = Module_correl.f_mizAdjustAttrFrm(l0_env, l37_pb);
							return ((((int)(l56_qa).f_first)==((int)(l57_qb).f_first))&&Module_unifier.f_mizEqTLClassU(au, ((Struct)(l56_qa).f_second), ((Struct)(l57_qb).f_second)));
						} else {
							return ((l34_predNr==(l37_pb).f_predNr)&&Module_unifier.f_mizEqTLClassU(au, l35_args, (l37_pb).f_args));
						}
					}
				}
				case 48/*MizLocPredFrm*/: {
					final Struct_MizLocPredFrm l3__tmp = (Struct_MizLocPredFrm)l2__tmp;
					final int l58_predNr = l3__tmp.f_predNr;
					final Struct l59_args = l3__tmp.f_args;
					final int l60_predNr = (l3__tmp).f_predNr;
					final Struct l61_args = (l3__tmp).f_args;
					final Struct l62___ = (l3__tmp).f_predExp;
					final int l63___ = (l3__tmp).f_pattNr;
					Struct_MizLocPredFrm l64_lb;
					Struct l65__tmp = ab;
					switch (l65__tmp.getTypeId()) {
					case 48/*MizLocPredFrm*/: {
						final Struct_MizLocPredFrm l66__tmp = (Struct_MizLocPredFrm)l65__tmp;
						final int l67___ = (l66__tmp).f_predNr;
						final Struct l68___ = (l66__tmp).f_args;
						final Struct l69___ = (l66__tmp).f_predExp;
						final int l70___ = (l66__tmp).f_pattNr;
						l64_lb=l66__tmp;
						break;
					}
					default: {
						l64_lb=(new Struct_MizLocPredFrm(0, ((Struct)SingletonStructs.str_MizTrmNil), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
						break;
					}
					}
					return ((l60_predNr==(l64_lb).f_predNr)&&Module_unifier.f_mizEqTLClassU(au, l61_args, (l64_lb).f_args));
				}
				case 69/*MizUnivFrm*/: {
					final Struct_MizUnivFrm l3__tmp = (Struct_MizUnivFrm)l2__tmp;
					final Struct_MizTyp l71_quantified = l3__tmp.f_quantified;
					final Struct l72_scope = l3__tmp.f_scope;
					final int l73___ = (l3__tmp).f_sort;
					final int l74___ = (l3__tmp).f_varId;
					final Struct_MizTyp l75_quantified = (l3__tmp).f_quantified;
					final Struct l76_scope = (l3__tmp).f_scope;
					final int l77___ = (l3__tmp).f_pattNr;
					Struct_MizUnivFrm l78_ub;
					Struct l79__tmp = ab;
					switch (l79__tmp.getTypeId()) {
					case 69/*MizUnivFrm*/: {
						final Struct_MizUnivFrm l80__tmp = (Struct_MizUnivFrm)l79__tmp;
						final int l81___ = (l80__tmp).f_sort;
						final int l82___ = (l80__tmp).f_varId;
						final Struct_MizTyp l83___ = (l80__tmp).f_quantified;
						final Struct l84___ = (l80__tmp).f_scope;
						final int l85___ = (l80__tmp).f_pattNr;
						l78_ub=l80__tmp;
						break;
					}
					default: {
						l78_ub=(new Struct_MizUnivFrm(Module_lexicon.g_ikError, 0, Module_correl.f_mizNewIncorTyp(), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
						break;
					}
					}
					return (Module_unifier.f_mizEqClassTypsU(au, l75_quantified, (l78_ub).f_quantified)&&Module_unifier.f_mizEqFrmsClassU(au, l76_scope, (l78_ub).f_scope));
				}
				case 60/*MizQualFrm*/: {
					final Struct_MizQualFrm l3__tmp = (Struct_MizQualFrm)l2__tmp;
					final Struct l86_trm = l3__tmp.f_trm;
					final Struct_MizTyp l87_typ = l3__tmp.f_typ;
					final Struct l88_trm = (l3__tmp).f_trm;
					final Struct_MizTyp l89_typ = (l3__tmp).f_typ;
					final int l90___ = (l3__tmp).f_pattNr;
					Struct_MizQualFrm l91_qb;
					Struct l92__tmp = ab;
					switch (l92__tmp.getTypeId()) {
					case 60/*MizQualFrm*/: {
						final Struct_MizQualFrm l93__tmp = (Struct_MizQualFrm)l92__tmp;
						final Struct l94___ = (l93__tmp).f_trm;
						final Struct_MizTyp l95___ = (l93__tmp).f_typ;
						final int l96___ = (l93__tmp).f_pattNr;
						l91_qb=l93__tmp;
						break;
					}
					default: {
						l91_qb=(new Struct_MizQualFrm((new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), Module_correl.f_mizNewIncorTyp(), 0));
						break;
					}
					}
					return (Module_unifier.f_mizEqTrmsClassU(au, l88_trm, (l91_qb).f_trm)&&Module_unifier.f_mizEqClassTypsU(au, l89_typ, (l91_qb).f_typ));
				}
				default: {
					return false;
				}
				}
			}
		}
	}
	}
	public static final boolean f_mizEqTLClassU(Struct_MizUnifier au, Struct aa, Struct ab) {
		Struct l0__tmp = aa;
		switch (l0__tmp.getTypeId()) {
		case 64/*MizTrmNil*/: {
			Struct l2__tmp = ab;
			switch (l2__tmp.getTypeId()) {
			case 64/*MizTrmNil*/: {
				return true;
			}
			case 63/*MizTrmElem*/: {
				final Struct_MizTrmElem l3__tmp = (Struct_MizTrmElem)l2__tmp;
				final Struct l4___ = (l3__tmp).f_trm;
				final Struct l5___ = (l3__tmp).f_next;
				return false;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
			}
		}
		case 63/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l6_ta = l1__tmp.f_trm;
			final Struct l7_na = l1__tmp.f_next;
			final Struct l8_ta = (l1__tmp).f_trm;
			final Struct l9_na = (l1__tmp).f_next;
			Struct l10__tmp = ab;
			switch (l10__tmp.getTypeId()) {
			case 64/*MizTrmNil*/: {
				return false;
			}
			case 63/*MizTrmElem*/: {
				final Struct_MizTrmElem l11__tmp = (Struct_MizTrmElem)l10__tmp;
				final Struct l12_tb = l11__tmp.f_trm;
				final Struct l13_nb = l11__tmp.f_next;
				final Struct l14_tb = (l11__tmp).f_trm;
				final Struct l15_nb = (l11__tmp).f_next;
				return (Module_unifier.f_mizEqTrmsClassU(au, l8_ta, l14_tb)&&Module_unifier.f_mizEqTLClassU(au, l9_na, l15_nb));
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final boolean f_mizEqTrmsClassU(Struct_MizUnifier au, Struct aa, Struct ab) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final int l1_sa = Module_correl.f_mizTrmSort(aa);
		final int l2_sb = Module_correl.f_mizTrmSort(ab);
		if ((l1_sa==l2_sb)) {
			Struct l3__tmp = aa;
			switch (l3__tmp.getTypeId()) {
			case 70/*MizVarTrm*/: {
				final Struct_MizVarTrm l4__tmp = (Struct_MizVarTrm)l3__tmp;
				final int l5_varNr = l4__tmp.f_varNr;
				final int l6___ = (l4__tmp).f_sort;
				final int l7_varNr = (l4__tmp).f_varNr;
				final int l8___ = (l4__tmp).f_pattNr;
				final int l9___ = (l4__tmp).f_trmInfo;
				return (l7_varNr==Module_unifier.f_mizUVarNr(ab));
			}
			case 44/*MizFuncTrm*/: {
				final Struct_MizFuncTrm l4__tmp = (Struct_MizFuncTrm)l3__tmp;
				final int l10_srt = l4__tmp.f_sort;
				final int l11_funcNr = l4__tmp.f_funcNr;
				final Struct l12_args = l4__tmp.f_args;
				final int l13_srt = (l4__tmp).f_sort;
				final int l14_funcNr = (l4__tmp).f_funcNr;
				final Struct l15_args = (l4__tmp).f_args;
				final int l16___ = (l4__tmp).f_pattNr;
				final int l17___ = (l4__tmp).f_trmInfo;
				if ((l13_srt==Module_lexicon.g_ikTrmFunctor)) {
					Struct_MizFuncTrm l18_fa;
					Struct l19__tmp = l4__tmp;
					switch (l19__tmp.getTypeId()) {
					case 44/*MizFuncTrm*/: {
						final Struct_MizFuncTrm l20__tmp = (Struct_MizFuncTrm)l19__tmp;
						final int l21___ = (l20__tmp).f_sort;
						final int l22___ = (l20__tmp).f_funcNr;
						final Struct l23___ = (l20__tmp).f_args;
						final int l24___ = (l20__tmp).f_pattNr;
						final int l25___ = (l20__tmp).f_trmInfo;
						l18_fa=l20__tmp;
						break;
					}
					default: {
						l18_fa=(new Struct_MizFuncTrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0, 0));
						break;
					}
					}
					Struct_MizFuncTrm l26_fb;
					Struct l27__tmp = ab;
					switch (l27__tmp.getTypeId()) {
					case 44/*MizFuncTrm*/: {
						final Struct_MizFuncTrm l28__tmp = (Struct_MizFuncTrm)l27__tmp;
						final int l29___ = (l28__tmp).f_sort;
						final int l30___ = (l28__tmp).f_funcNr;
						final Struct l31___ = (l28__tmp).f_args;
						final int l32___ = (l28__tmp).f_pattNr;
						final int l33___ = (l28__tmp).f_trmInfo;
						l26_fb=l28__tmp;
						break;
					}
					default: {
						l26_fb=(new Struct_MizFuncTrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0, 0));
						break;
					}
					}
					final Struct_Pair l34_pa = Module_correl.f_mizAdjustTrm(l0_env, l18_fa);
					final Struct_Pair l35_pb = Module_correl.f_mizAdjustTrm(l0_env, l26_fb);
					return ((((int)(l34_pa).f_first)==((int)(l35_pb).f_first))&&Module_unifier.f_mizEqTLClassU(au, ((Struct)(l34_pa).f_second), ((Struct)(l35_pb).f_second)));
				} else {
					return ((l14_funcNr==Module_unifier.f_mizUFuncNr(ab))&&Module_unifier.f_mizEqTLClassU(au, l15_args, Module_unifier.f_mizUArgs(ab)));
				}
			}
			case 58/*MizPrivFuncTrm*/: {
				final Struct_MizPrivFuncTrm l4__tmp = (Struct_MizPrivFuncTrm)l3__tmp;
				final int l36_funcNr = l4__tmp.f_funcNr;
				final Struct l37_args = l4__tmp.f_args;
				final int l38_funcNr = (l4__tmp).f_funcNr;
				final Struct l39_args = (l4__tmp).f_args;
				final Struct l40___ = (l4__tmp).f_funcExp;
				final int l41___ = (l4__tmp).f_pattNr;
				final int l42___ = (l4__tmp).f_trmInfo;
				return ((l38_funcNr==Module_unifier.f_mizUFuncNr(ab))&&Module_unifier.f_mizEqTLClassU(au, l39_args, Module_unifier.f_mizUArgs(ab)));
			}
			case 42/*MizFraenkelTrm*/: {
				final Struct_MizFraenkelTrm l4__tmp = (Struct_MizFraenkelTrm)l3__tmp;
				final Struct_Vector l43_lambdaArgs = l4__tmp.f_lambdaArgs;
				final Struct l44_scope = l4__tmp.f_scope;
				final Struct l45_compr = l4__tmp.f_compr;
				final Struct_Vector l46_lambdaArgs = (l4__tmp).f_lambdaArgs;
				final Struct l47_scope = (l4__tmp).f_scope;
				final Struct l48_compr = (l4__tmp).f_compr;
				final Struct_MizIntSeq l49___ = (l4__tmp).f_idents;
				final int l50___ = (l4__tmp).f_pattNr;
				final int l51___ = (l4__tmp).f_trmInfo;
				Struct_MizFraenkelTrm l52_kb;
				Struct l53__tmp = ab;
				switch (l53__tmp.getTypeId()) {
				case 42/*MizFraenkelTrm*/: {
					final Struct_MizFraenkelTrm l54__tmp = (Struct_MizFraenkelTrm)l53__tmp;
					final Struct_Vector l55___ = (l54__tmp).f_lambdaArgs;
					final Struct l56___ = (l54__tmp).f_scope;
					final Struct l57___ = (l54__tmp).f_compr;
					final Struct_MizIntSeq l58___ = (l54__tmp).f_idents;
					final int l59___ = (l54__tmp).f_pattNr;
					final int l60___ = (l54__tmp).f_trmInfo;
					l52_kb=l54__tmp;
					break;
				}
				default: {
					l52_kb=(new Struct_MizFraenkelTrm(Module_vector.f_makeVector(0), (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), Module_mobjects.f_mizInitIntSeq(0), 0, 0));
					break;
				}
				}
				if ((Module_vector.f_sizeVector(l46_lambdaArgs)!=Module_vector.f_sizeVector((l52_kb).f_lambdaArgs))) {
					return false;
				} else {
					final Reference<Boolean> l61_ok = ((Reference<Boolean>)(new Reference(true)));
					final Reference<Integer> l62_i = ((Reference<Integer>)(new Reference(0)));
					final Func0<Boolean> l65_$63 = (Func0<Boolean>)() -> {
						return ((Boolean)((((int)l62_i.value)<Module_vector.f_sizeVector(l46_lambdaArgs))&&((boolean)l61_ok.value)));
					};
					final Struct_MizUnifier l67_u = au;
					final Struct_MizFraenkelTrm l68_kb = l52_kb;
					final Func0<Object> l66_$64 = (Func0<Object>)() -> {
						if (!Module_unifier.f_mizEqClassTypsU(l67_u, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l46_lambdaArgs, ((int)l62_i.value))), ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l68_kb).f_lambdaArgs, ((int)l62_i.value))))) {
							l61_ok.value = ((Boolean)false);
						} else {
						}
						l62_i.value = ((Integer)(((int)l62_i.value)+1));
						return null;
					};
					Module_loop.f_while(l65_$63, l66_$64);
					return ((((boolean)l61_ok.value)&&Module_unifier.f_mizEqTrmsClassU(au, l47_scope, (l52_kb).f_scope))&&Module_unifier.f_mizEqFrmsClassU(au, l48_compr, (l52_kb).f_compr));
				}
			}
			case 21/*MizChoiceTrm*/: {
				final Struct_MizChoiceTrm l4__tmp = (Struct_MizChoiceTrm)l3__tmp;
				final Struct_MizTyp l69_typ = l4__tmp.f_choiceTyp;
				final Struct_MizTyp l70_typ = (l4__tmp).f_choiceTyp;
				final int l71___ = (l4__tmp).f_pattNr;
				final int l72___ = (l4__tmp).f_trmInfo;
				Struct_MizChoiceTrm l73_cb;
				Struct l74__tmp = ab;
				switch (l74__tmp.getTypeId()) {
				case 21/*MizChoiceTrm*/: {
					final Struct_MizChoiceTrm l75__tmp = (Struct_MizChoiceTrm)l74__tmp;
					final Struct_MizTyp l76___ = (l75__tmp).f_choiceTyp;
					final int l77___ = (l75__tmp).f_pattNr;
					final int l78___ = (l75__tmp).f_trmInfo;
					l73_cb=l75__tmp;
					break;
				}
				default: {
					l73_cb=(new Struct_MizChoiceTrm(Module_correl.f_mizNewIncorTyp(), 0, 0));
					break;
				}
				}
				return Module_unifier.f_mizEqClassTypsU(au, l70_typ, (l73_cb).f_choiceTyp);
			}
			default: {
				return false;
			}
			}
		} else {
			if ((l2_sb==Module_lexicon.g_ikTrmEqConst)) {
				return (Module_unifier.f_mizEqClassNr(au, aa)==Module_unifier.f_mizUVarNr(ab));
			} else {
				return false;
			}
		}
	}
	public static final Struct f_mizFraenkelFrm(Struct_MizCorrelEnv aenv, Struct at, Struct afr0) {
		final Struct_MizFraenkelTrm l0_fr = Module_unifier.f_mizUFrAsFraenkel(afr0);
		final Struct l1_lEq = Module_correl.f_mizNewEqFrm(aenv, Module_correl.f_mizCopyTrm(at), Module_correl.f_mizCopyTrm((l0_fr).f_scope));
		final Struct l2_base = Module_correl.f_mizNewNeg(Module_correl.f_mizNewConj(l1_lEq, Wrappers.w_mizCopyFrm.invoke((l0_fr).f_compr)));
		final Reference<Struct> l3_res = ((Reference<Struct>)(new Reference(l2_base)));
		final Reference<Integer> l4_i = ((Reference<Integer>)(new Reference((Module_vector.f_sizeVector((l0_fr).f_lambdaArgs)-1))));
		final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
			return (((int)l4_i.value)>=0);
		};
		final Func0<Object> l8_$6 = (Func0<Object>)() -> {
			l3_res.value = Module_correl.f_mizNewUniv(Wrappers.w_mizCopyTyp.invoke(((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l0_fr).f_lambdaArgs, ((int)l4_i.value)))), l3_res.value);
			l4_i.value = ((Integer)(((int)l4_i.value)-1));
			return null;
		};
		Module_loop.f_while(l7_$5, l8_$6);
		return l3_res.value;
	}
	public static final boolean f_mizFrmsAreSimilar(Struct_MizCorrelEnv aenv, Struct aa, Struct ab) {
		TAIL_CALL: for(;;) {
		final int l0_sa = Module_correl.f_mizFrmSort(aa);
		if ((l0_sa!=Module_correl.f_mizFrmSort(ab))) {
			return false;
		} else {
			if (((l0_sa==Module_lexicon.g_ikFrmVerum)||(l0_sa==Module_lexicon.g_ikFrmThesis))) {
				return true;
			} else {
				Struct l1__tmp = aa;
				switch (l1__tmp.getTypeId()) {
				case 50/*MizNegFrm*/: {
					final Struct_MizNegFrm l2__tmp = (Struct_MizNegFrm)l1__tmp;
					final Struct l3_arg = l2__tmp.f_arg;
					final Struct l4_arg = (l2__tmp).f_arg;
					final int l5___ = (l2__tmp).f_pattNr;
					Struct_MizNegFrm l6_nb;
					Struct l7__tmp = ab;
					switch (l7__tmp.getTypeId()) {
					case 50/*MizNegFrm*/: {
						final Struct_MizNegFrm l8__tmp = (Struct_MizNegFrm)l7__tmp;
						final Struct l9___ = (l8__tmp).f_arg;
						final int l10___ = (l8__tmp).f_pattNr;
						l6_nb=l8__tmp;
						break;
					}
					default: {
						l6_nb=(new Struct_MizNegFrm((new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
						break;
					}
					}
					{
						final Struct l11___tmp = l4_arg;
						final Struct l12___tmp = (l6_nb).f_arg;
						aa = l11___tmp;
						ab = l12___tmp;
						continue TAIL_CALL;
					}
				}
				case 60/*MizQualFrm*/: {
					final Struct_MizQualFrm l2__tmp = (Struct_MizQualFrm)l1__tmp;
					final Struct_MizTyp l13_typ = l2__tmp.f_typ;
					final Struct l14___ = (l2__tmp).f_trm;
					final Struct_MizTyp l15_typ = (l2__tmp).f_typ;
					final int l16___ = (l2__tmp).f_pattNr;
					Struct_MizQualFrm l17_qb;
					Struct l18__tmp = ab;
					switch (l18__tmp.getTypeId()) {
					case 60/*MizQualFrm*/: {
						final Struct_MizQualFrm l19__tmp = (Struct_MizQualFrm)l18__tmp;
						final Struct l20___ = (l19__tmp).f_trm;
						final Struct_MizTyp l21___ = (l19__tmp).f_typ;
						final int l22___ = (l19__tmp).f_pattNr;
						l17_qb=l19__tmp;
						break;
					}
					default: {
						l17_qb=(new Struct_MizQualFrm((new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), Module_correl.f_mizNewIncorTyp(), 0));
						break;
					}
					}
					return Module_unifier.f_mizTypsAreSimilar(aenv, l15_typ, (l17_qb).f_typ);
				}
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l2__tmp = (Struct_MizConjFrm)l1__tmp;
					final Struct_Vector l23_conjuncts = l2__tmp.f_conjuncts;
					final Struct_Vector l24_conjuncts = (l2__tmp).f_conjuncts;
					final int l25___ = (l2__tmp).f_pattNr;
					Struct_MizConjFrm l26_cb;
					Struct l27__tmp = ab;
					switch (l27__tmp.getTypeId()) {
					case 26/*MizConjFrm*/: {
						final Struct_MizConjFrm l28__tmp = (Struct_MizConjFrm)l27__tmp;
						final Struct_Vector l29___ = (l28__tmp).f_conjuncts;
						final int l30___ = (l28__tmp).f_pattNr;
						l26_cb=l28__tmp;
						break;
					}
					default: {
						l26_cb=(new Struct_MizConjFrm(Module_vector.f_makeVector(0), 0));
						break;
					}
					}
					if ((Module_vector.f_sizeVector(l24_conjuncts)!=Module_vector.f_sizeVector((l26_cb).f_conjuncts))) {
						return false;
					} else {
						final Reference<Boolean> l31_ok = ((Reference<Boolean>)(new Reference(true)));
						final int l34_$33 = (Module_vector.f_sizeVector(l24_conjuncts)-1);
						final Struct_MizCorrelEnv l36_env = aenv;
						final Struct_MizConjFrm l37_cb = l26_cb;
						final Func1<Object,Integer> l35_$32 = (Func1<Object, Integer>)(Integer ak) -> {
							if (!Module_unifier.f_mizFrmsAreSimilar(l36_env, ((Struct)Module_vector.f_getVectorUnsafe(l24_conjuncts, ((int)ak))), ((Struct)Module_vector.f_getVectorUnsafe((l37_cb).f_conjuncts, ((int)ak))))) {
								l31_ok.value = ((Boolean)false);
								return null;
							} else {
								return null;
							}
						};
						Module_runtime.f_fori(0, l34_$33, l35_$32);
						return ((boolean)l31_ok.value);
					}
				}
				case 55/*MizPredFrm*/: {
					final Struct_MizPredFrm l2__tmp = (Struct_MizPredFrm)l1__tmp;
					final int l38_srt = l2__tmp.f_sort;
					final int l39_predNr = l2__tmp.f_predNr;
					final int l40_srt = (l2__tmp).f_sort;
					final int l41_predNr = (l2__tmp).f_predNr;
					final Struct l42___ = (l2__tmp).f_args;
					final int l43___ = (l2__tmp).f_pattNr;
					Struct_MizPredFrm l44_pa2;
					Struct l45__tmp = l2__tmp;
					switch (l45__tmp.getTypeId()) {
					case 55/*MizPredFrm*/: {
						final Struct_MizPredFrm l46__tmp = (Struct_MizPredFrm)l45__tmp;
						final int l47___ = (l46__tmp).f_sort;
						final int l48___ = (l46__tmp).f_predNr;
						final Struct l49___ = (l46__tmp).f_args;
						final int l50___ = (l46__tmp).f_pattNr;
						l44_pa2=l46__tmp;
						break;
					}
					default: {
						l44_pa2=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
						break;
					}
					}
					Struct_MizPredFrm l51_pb;
					Struct l52__tmp = ab;
					switch (l52__tmp.getTypeId()) {
					case 55/*MizPredFrm*/: {
						final Struct_MizPredFrm l53__tmp = (Struct_MizPredFrm)l52__tmp;
						final int l54___ = (l53__tmp).f_sort;
						final int l55___ = (l53__tmp).f_predNr;
						final Struct l56___ = (l53__tmp).f_args;
						final int l57___ = (l53__tmp).f_pattNr;
						l51_pb=l53__tmp;
						break;
					}
					default: {
						l51_pb=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
						break;
					}
					}
					if ((l40_srt==Module_lexicon.g_ikFrmPred)) {
						return (((int)(Module_correl.f_mizAdjustFrm(aenv, l44_pa2)).f_first)==((int)(Module_correl.f_mizAdjustFrm(aenv, l51_pb)).f_first));
					} else {
						if ((l40_srt==Module_lexicon.g_ikFrmAttr)) {
							return (((int)(Module_correl.f_mizAdjustAttrFrm(aenv, l44_pa2)).f_first)==((int)(Module_correl.f_mizAdjustAttrFrm(aenv, l51_pb)).f_first));
						} else {
							return (l41_predNr==(l51_pb).f_predNr);
						}
					}
				}
				case 48/*MizLocPredFrm*/: {
					final Struct_MizLocPredFrm l2__tmp = (Struct_MizLocPredFrm)l1__tmp;
					final int l58_predNr = l2__tmp.f_predNr;
					final int l59_predNr = (l2__tmp).f_predNr;
					final Struct l60___ = (l2__tmp).f_args;
					final Struct l61___ = (l2__tmp).f_predExp;
					final int l62___ = (l2__tmp).f_pattNr;
					Struct_MizLocPredFrm l63_lb;
					Struct l64__tmp = ab;
					switch (l64__tmp.getTypeId()) {
					case 48/*MizLocPredFrm*/: {
						final Struct_MizLocPredFrm l65__tmp = (Struct_MizLocPredFrm)l64__tmp;
						final int l66___ = (l65__tmp).f_predNr;
						final Struct l67___ = (l65__tmp).f_args;
						final Struct l68___ = (l65__tmp).f_predExp;
						final int l69___ = (l65__tmp).f_pattNr;
						l63_lb=l65__tmp;
						break;
					}
					default: {
						l63_lb=(new Struct_MizLocPredFrm(0, ((Struct)SingletonStructs.str_MizTrmNil), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
						break;
					}
					}
					return (l59_predNr==(l63_lb).f_predNr);
				}
				default: {
					return false;
				}
				}
			}
		}
	}
	}
	public static final Struct_MizUnifier f_mizInitUnifier(Struct_MizChecker achk, Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (achk).f_env;
		final int l1_n = (aeq_0).f_eqClassNbr;
		final Struct_Vector l2_el = Module_vector.f_makeVector(7);
		final Func1<Object,Integer> l4_$3 = (Func1<Object, Integer>)(Integer al5_0) -> {
			final Struct_Vector l6_byClass = Module_vector.f_makeVector((l1_n+1));
			final Func1<Object,Integer> l8_$7 = (Func1<Object, Integer>)(Integer a__u) -> {
				return Module_vector.f_pushVector(l6_byClass, Module_vector.f_makeVector(2));
			};
			Module_runtime.f_fori(0, l1_n, l8_$7);
			return Module_vector.f_pushVector(l2_el, l6_byClass);
		};
		Module_runtime.f_fori(0, 6, l4_$3);
		final Struct_MizNatFunc l5_dConst = Module_mobjects.f_mizInitNatFunc(16);
		final Struct_Vector l6_vals = Module_vector.f_makeVector((l1_n+1));
		final Struct_Vector l7_typs = Module_vector.f_makeVector((l1_n+1));
		final Struct_Vector l8_supers = Module_vector.f_makeVector((l1_n+1));
		final Struct_Vector l9_eTrms = Module_vector.f_makeVector((l1_n+1));
		final Func1<Object,Integer> l11_$10 = (Func1<Object, Integer>)(Integer ai) -> {
			Module_vector.f_pushVector(l6_vals, SingletonStructs.str_None);
			Module_vector.f_pushVector(l7_typs, Module_vector.f_makeVector(2));
			Module_vector.f_pushVector(l8_supers, Module_correl.f_mizNewEmptyCluster());
			final Struct l12_et = Module_correl.f_mizNewVarTrm(Module_lexicon.g_ikTrmEqConst, ((int)ai));
			Module_unifier.f_mizUSetTrmInfo(l12_et, ((int)ai));
			return Module_vector.f_pushVector(l9_eTrms, l12_et);
		};
		Module_runtime.f_fori(0, l1_n, l11_$10);
		final int l14_$13 = Module_equalizer.f_mizTrmNbr(aeq_0);
		final Struct_MizEqualizer l16_eq_0 = aeq_0;
		final Func1<Object,Integer> l15_$12 = (Func1<Object, Integer>)(Integer aii) -> {
			final Struct_MizTrmSRec l16_rec = Module_equalizer.f_mizTrmSAt(l16_eq_0, ((int)aii));
			final Struct l17_gsymswitch0 = (l16_rec).f_eqClass;
			boolean l18_live;
			Struct l19__tmp = l17_gsymswitch0;
			switch (l19__tmp.getTypeId()) {
			case 64/*MizTrmNil*/: {
				l18_live=false;
				break;
			}
			default: {
				l18_live=true;
				break;
			}
			}
			if (l18_live) {
				final int l21_clNr = Module_unifier.f_mizUVarNr((l16_rec).f_term);
				final Reference<Struct> l22_cur = ((Reference<Struct>)(new Reference((l16_rec).f_eqClass)));
				final Func0<Boolean> l25_$23 = (Func0<Boolean>)() -> {
					final Struct l26_gsymswitch1 = l22_cur.value;
					Struct l27__tmp = l26_gsymswitch1;
					switch (l27__tmp.getTypeId()) {
					case 64/*MizTrmNil*/: {
						return ((Boolean)false);
					}
					default: {
						return ((Boolean)true);
					}
					}
				};
				final Func0<Object> l26_$24 = (Func0<Object>)() -> {
					final Struct l27_gsymswitch2 = l22_cur.value;
					Struct l28__tmp = l27_gsymswitch2;
					switch (l28__tmp.getTypeId()) {
					case 63/*MizTrmElem*/: {
						final Struct_MizTrmElem l29__tmp = (Struct_MizTrmElem)l28__tmp;
						final Struct l30_trm = l29__tmp.f_trm;
						final Struct l31_next = l29__tmp.f_next;
						final Struct l32_trm = (l29__tmp).f_trm;
						final Struct l33_next = (l29__tmp).f_next;
						final int l34_srt = Module_correl.f_mizTrmSort(l32_trm);
						if ((l34_srt==Module_lexicon.g_ikTrmInfConst)) {
							Module_mobjects.f_mizNatAssign(l5_dConst, Module_unifier.f_mizUVarNr(l32_trm), l21_clNr);
						} else {
							if ((l34_srt==Module_lexicon.g_ikTrmFunctor)) {
								Module_vector.f_pushVector(((Struct_Vector)Module_vector.f_getVectorUnsafe(((Struct_Vector)Module_vector.f_getVectorUnsafe(l2_el, Module_unifier.g_expUFunctor)), l21_clNr)), l32_trm);
							} else {
								if ((l34_srt==Module_lexicon.g_ikTrmSchFunc)) {
									Module_vector.f_pushVector(((Struct_Vector)Module_vector.f_getVectorUnsafe(((Struct_Vector)Module_vector.f_getVectorUnsafe(l2_el, Module_unifier.g_expUSchFunc)), l21_clNr)), l32_trm);
								} else {
									if ((l34_srt==Module_lexicon.g_ikTrmPrivFunc)) {
										Module_vector.f_pushVector(((Struct_Vector)Module_vector.f_getVectorUnsafe(((Struct_Vector)Module_vector.f_getVectorUnsafe(l2_el, Module_unifier.g_expUPrivFunc)), l21_clNr)), l32_trm);
									} else {
										if ((l34_srt==Module_lexicon.g_ikTrmAggreg)) {
											Module_vector.f_pushVector(((Struct_Vector)Module_vector.f_getVectorUnsafe(((Struct_Vector)Module_vector.f_getVectorUnsafe(l2_el, Module_unifier.g_expUAggreg)), l21_clNr)), l32_trm);
										} else {
											if ((l34_srt==Module_lexicon.g_ikTrmSelector)) {
												Module_vector.f_pushVector(((Struct_Vector)Module_vector.f_getVectorUnsafe(((Struct_Vector)Module_vector.f_getVectorUnsafe(l2_el, Module_unifier.g_expUSelector)), l21_clNr)), l32_trm);
											} else {
												if ((l34_srt==Module_lexicon.g_ikTrmFraenkel)) {
													Module_vector.f_pushVector(((Struct_Vector)Module_vector.f_getVectorUnsafe(((Struct_Vector)Module_vector.f_getVectorUnsafe(l2_el, Module_unifier.g_expUFraenkel)), l21_clNr)), l32_trm);
												} else {
													if ((l34_srt==Module_lexicon.g_ikTrmChoice)) {
														Module_vector.f_pushVector(((Struct_Vector)Module_vector.f_getVectorUnsafe(((Struct_Vector)Module_vector.f_getVectorUnsafe(l2_el, Module_unifier.g_expUChoice)), l21_clNr)), l32_trm);
													} else {
													}
												}
											}
										}
									}
								}
							}
						}
						l22_cur.value = l33_next;
						return null;
					}
					case 64/*MizTrmNil*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l28__tmp.getTypeName());
					}
				};
				Module_loop.f_while(l25_$23, l26_$24);
				if ((l16_rec).f_numDetermined) {
					Module_vector.f_setVectorUnsafe(l6_vals, l21_clNr, (new Struct_Some((l16_rec).f_numValue)));
				} else {
				}
				Module_vector.f_setVectorUnsafe(l7_typs, l21_clNr, (l16_rec).f_xTypClass);
				return Module_vector.f_setVectorUnsafe(l8_supers, l21_clNr, (l16_rec).f_superCluster);
			} else {
				return null;
			}
		};
		Module_runtime.f_fori(1, l14_$13, l15_$12);
		final Struct_MizPre l17_upre = Module_prechecker.f_mizMakePre(l0_env);
		return (new Struct_MizUnifier(l0_env, (achk).f_idn, aeq_0, l1_n, l2_el, l5_dConst, l6_vals, l7_typs, l8_supers, l9_eTrms, Module_vector.f_makeVector(8), 0, 0, Module_tree.f_makeTree(), l17_upre));
	}
	public static final Struct f_mizNegativelyStandarized(Struct_MizUnifier au, Struct af) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l2_conjuncts = l1__tmp.f_conjuncts;
			final Struct_Vector l3_conjuncts = (l1__tmp).f_conjuncts;
			final int l4___ = (l1__tmp).f_pattNr;
			final Struct_MizUnifier l7_u = au;
			final Func1<Struct,Struct> l6_$5 = (Func1<Struct, Struct>)(Struct ag) -> {
				return Module_unifier.f_mizNegativelyStandarized(l7_u, ag);
			};
			return (new Struct_MizConjFrm(Module_vector.f_mapVector(l3_conjuncts, ((Func1<Object,Object>)(Func1)l6_$5)), 0));
		}
		case 69/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l1__tmp = (Struct_MizUnivFrm)l0__tmp;
			final int l8___ = (l1__tmp).f_sort;
			final int l9___ = (l1__tmp).f_varId;
			final Struct_MizTyp l10___ = (l1__tmp).f_quantified;
			final Struct l11___ = (l1__tmp).f_scope;
			final int l12___ = (l1__tmp).f_pattNr;
			return Wrappers.w_mizCopyFrm.invoke(l1__tmp);
		}
		case 60/*MizQualFrm*/: {
			final Struct_MizQualFrm l1__tmp = (Struct_MizQualFrm)l0__tmp;
			final Struct l13_trm = l1__tmp.f_trm;
			final Struct_MizTyp l14_typ = l1__tmp.f_typ;
			final Struct l15_trm = (l1__tmp).f_trm;
			final Struct_MizTyp l16_typ = (l1__tmp).f_typ;
			final int l17___ = (l1__tmp).f_pattNr;
			return (new Struct_MizQualFrm(Module_correl.f_mizCopyTrm(l15_trm), Module_unifier.f_mizStandarizeType(au, l16_typ), 0));
		}
		case 55/*MizPredFrm*/: {
			final Struct_MizPredFrm l1__tmp = (Struct_MizPredFrm)l0__tmp;
			final int l18_srt = l1__tmp.f_sort;
			final int l19_predNr = l1__tmp.f_predNr;
			final Struct l20_args = l1__tmp.f_args;
			final int l21_srt = (l1__tmp).f_sort;
			final int l22_predNr = (l1__tmp).f_predNr;
			final Struct l23_args = (l1__tmp).f_args;
			final int l24___ = (l1__tmp).f_pattNr;
			if ((l21_srt==Module_lexicon.g_ikFrmAttr)) {
				final Struct l25_lTrm = Module_correl.f_mizCopyTrm((Module_correl.f_mizLastElem(l23_args)).f_trm);
				if ((Module_correl.f_mizTrmSort(l25_lTrm)!=Module_lexicon.g_ikTrmEqConst)) {
					final Struct l26_rq = Module_unifier.f_mizChReconQualFrm(au, l25_lTrm, l22_predNr, 1, Module_unifier.f_mizUAllButLast(l23_args));
					{
						final Struct l27___tmp = l26_rq;
						af = l27___tmp;
						continue TAIL_CALL;
					}
				} else {
					return Wrappers.w_mizCopyFrm.invoke(l1__tmp);
				}
			} else {
				return (new Struct_MizPredFrm(l21_srt, l22_predNr, Module_unifier.f_mizStandarizeTrmList(au, l23_args), 0));
			}
		}
		case 48/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l1__tmp = (Struct_MizLocPredFrm)l0__tmp;
			final int l28_predNr = l1__tmp.f_predNr;
			final Struct l29_args = l1__tmp.f_args;
			final Struct l30_expn = l1__tmp.f_predExp;
			final int l31_predNr = (l1__tmp).f_predNr;
			final Struct l32_args = (l1__tmp).f_args;
			final Struct l33_expn = (l1__tmp).f_predExp;
			final int l34___ = (l1__tmp).f_pattNr;
			return (new Struct_MizLocPredFrm(l31_predNr, Module_unifier.f_mizStandarizeTrmList(au, l32_args), Module_correl_inst.f_mizCopyExpFrm(l33_expn), 0));
		}
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l35_arg = l1__tmp.f_arg;
			final Struct l36_arg = (l1__tmp).f_arg;
			final int l37___ = (l1__tmp).f_pattNr;
			return Module_correl.f_mizNewNeg(Module_unifier.f_mizPositivelyStandarized(au, l36_arg));
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l1__tmp = (Struct_MizFlexFrm)l0__tmp;
			final int l38___ = (l1__tmp).f_sort;
			final Struct l39___ = (l1__tmp).f_leftOrig;
			final Struct l40___ = (l1__tmp).f_rightOrig;
			final Struct l41___ = (l1__tmp).f_leftTrm;
			final Struct l42___ = (l1__tmp).f_rightTrm;
			final Struct l43___ = (l1__tmp).f_expansion;
			final int l44___ = (l1__tmp).f_pattNr;
			return Wrappers.w_mizCopyFrm.invoke(l1__tmp);
		}
		case 68/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l1__tmp = (Struct_MizUniqFrm)l0__tmp;
			final int l45_srt = l1__tmp.f_sort;
			final int l46_srt = (l1__tmp).f_sort;
			return (new Struct_MizUniqFrm(l46_srt));
		}
		case 17/*MizBinFrm*/: {
			final Struct_MizBinFrm l1__tmp = (Struct_MizBinFrm)l0__tmp;
			final int l47___ = (l1__tmp).f_sort;
			final Struct l48___ = (l1__tmp).f_left;
			final Struct l49___ = (l1__tmp).f_right;
			final int l50___ = (l1__tmp).f_pattNr;
			Module_errhan.f_mizRunTimeError(2043);
			return l1__tmp;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	}
	public static final Object f_mizNewFreeVar(Struct_MizUnifier au, Struct_MizTyp atyp) {
		final Struct_MizTyp l0_lTyp = Wrappers.w_mizCopyTyp.invoke(atyp);
		final Struct_MizUnifier l3_u = au;
		final Func2<Struct,Struct_MizWithin, Struct> l2_$1 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al3_0, Struct atrm) -> {
			return Module_unifier.f_mizSetFreeVarInTrm(l3_u, atrm);
		};
		final Struct_MizWithin l4_w = Module_correl.f_mizMakeWithin(l2_$1);
		Module_correl.f_mizWithinTyp(l4_w, l0_lTyp);
		return Module_vector.f_pushVector((au).f_freeVarType, l0_lTyp);
	}
	public static final Struct f_mizNewNegDis(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l2_arg = l1__tmp.f_arg;
			final Struct l3_arg = (l1__tmp).f_arg;
			final int l4___ = (l1__tmp).f_pattNr;
			return l3_arg;
		}
		default: {
			return Module_correl.f_mizNewNeg(af);
		}
		}
	}
	public static final Struct f_mizPositivelyStandarized(Struct_MizUnifier au, Struct af) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		Struct l1__tmp = af;
		switch (l1__tmp.getTypeId()) {
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l2__tmp = (Struct_MizConjFrm)l1__tmp;
			final Struct_Vector l3_conjuncts = l2__tmp.f_conjuncts;
			final Struct_Vector l4_conjuncts = (l2__tmp).f_conjuncts;
			final int l5___ = (l2__tmp).f_pattNr;
			final Struct_MizUnifier l8_u = au;
			final Func1<Struct,Struct> l7_$6 = (Func1<Struct, Struct>)(Struct ag) -> {
				return Module_unifier.f_mizPositivelyStandarized(l8_u, ag);
			};
			return (new Struct_MizConjFrm(Module_vector.f_mapVector(l4_conjuncts, ((Func1<Object,Object>)(Func1)l7_$6)), 0));
		}
		case 69/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l2__tmp = (Struct_MizUnivFrm)l1__tmp;
			final Struct_MizTyp l9_quantified = l2__tmp.f_quantified;
			final Struct l10_scope = l2__tmp.f_scope;
			final int l11___ = (l2__tmp).f_sort;
			final int l12___ = (l2__tmp).f_varId;
			final Struct_MizTyp l13_quantified = (l2__tmp).f_quantified;
			final Struct l14_scope = (l2__tmp).f_scope;
			final int l15___ = (l2__tmp).f_pattNr;
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
			final Struct_MizTyp l16_q = Module_unifier.f_mizStandarizeType(au, l13_quantified);
			Module_correl.f_mizSetBoundVar(l0_env, (l0_env).f_boundVarNbr, l16_q);
			final Struct_MizUnivFrm l17_r = (new Struct_MizUnivFrm(Module_lexicon.g_ikFrmUniv, 0, l16_q, Module_unifier.f_mizPositivelyStandarized(au, l14_scope), 0));
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-1));
			return l17_r;
		}
		case 60/*MizQualFrm*/: {
			final Struct_MizQualFrm l2__tmp = (Struct_MizQualFrm)l1__tmp;
			final Struct l18_trm = l2__tmp.f_trm;
			final Struct_MizTyp l19_typ = l2__tmp.f_typ;
			final Struct l20_trm = (l2__tmp).f_trm;
			final Struct_MizTyp l21_typ = (l2__tmp).f_typ;
			final int l22___ = (l2__tmp).f_pattNr;
			return (new Struct_MizQualFrm(Module_correl.f_mizCopyTrm(l20_trm), Module_unifier.f_mizStandarizeType(au, l21_typ), 0));
		}
		case 55/*MizPredFrm*/: {
			final Struct_MizPredFrm l2__tmp = (Struct_MizPredFrm)l1__tmp;
			final int l23_srt = l2__tmp.f_sort;
			final int l24_predNr = l2__tmp.f_predNr;
			final Struct l25_args = l2__tmp.f_args;
			final int l26_srt = (l2__tmp).f_sort;
			final int l27_predNr = (l2__tmp).f_predNr;
			final Struct l28_args = (l2__tmp).f_args;
			final int l29___ = (l2__tmp).f_pattNr;
			if ((l26_srt==Module_lexicon.g_ikFrmAttr)) {
				final Struct l30_lTrm = Module_correl.f_mizCopyTrm((Module_correl.f_mizLastElem(l28_args)).f_trm);
				if ((Module_correl.f_mizTrmSort(l30_lTrm)!=Module_lexicon.g_ikTrmEqConst)) {
					final Struct l31_rq = Module_unifier.f_mizChReconQualFrm(au, l30_lTrm, l27_predNr, 1, Module_unifier.f_mizUAllButLast(l28_args));
					{
						final Struct l32___tmp = l31_rq;
						af = l32___tmp;
						continue TAIL_CALL;
					}
				} else {
					return Wrappers.w_mizCopyFrm.invoke(l2__tmp);
				}
			} else {
				return (new Struct_MizPredFrm(l26_srt, l27_predNr, Module_unifier.f_mizStandarizeTrmList(au, l28_args), 0));
			}
		}
		case 48/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l2__tmp = (Struct_MizLocPredFrm)l1__tmp;
			final int l33_predNr = l2__tmp.f_predNr;
			final Struct l34_args = l2__tmp.f_args;
			final Struct l35_expn = l2__tmp.f_predExp;
			final int l36_predNr = (l2__tmp).f_predNr;
			final Struct l37_args = (l2__tmp).f_args;
			final Struct l38_expn = (l2__tmp).f_predExp;
			final int l39___ = (l2__tmp).f_pattNr;
			return (new Struct_MizLocPredFrm(l36_predNr, Module_unifier.f_mizStandarizeTrmList(au, l37_args), Module_correl_inst.f_mizCopyExpFrm(l38_expn), 0));
		}
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l2__tmp = (Struct_MizNegFrm)l1__tmp;
			final Struct l40_arg = l2__tmp.f_arg;
			final Struct l41_arg = (l2__tmp).f_arg;
			final int l42___ = (l2__tmp).f_pattNr;
			return Module_correl.f_mizNewNeg(Module_unifier.f_mizNegativelyStandarized(au, l41_arg));
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l2__tmp = (Struct_MizFlexFrm)l1__tmp;
			final int l43___ = (l2__tmp).f_sort;
			final Struct l44___ = (l2__tmp).f_leftOrig;
			final Struct l45___ = (l2__tmp).f_rightOrig;
			final Struct l46___ = (l2__tmp).f_leftTrm;
			final Struct l47___ = (l2__tmp).f_rightTrm;
			final Struct l48___ = (l2__tmp).f_expansion;
			final int l49___ = (l2__tmp).f_pattNr;
			return Wrappers.w_mizCopyFrm.invoke(l2__tmp);
		}
		case 68/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l2__tmp = (Struct_MizUniqFrm)l1__tmp;
			final int l50_srt = l2__tmp.f_sort;
			final int l51_srt = (l2__tmp).f_sort;
			return (new Struct_MizUniqFrm(l51_srt));
		}
		case 17/*MizBinFrm*/: {
			final Struct_MizBinFrm l2__tmp = (Struct_MizBinFrm)l1__tmp;
			final int l52___ = (l2__tmp).f_sort;
			final Struct l53___ = (l2__tmp).f_left;
			final Struct l54___ = (l2__tmp).f_right;
			final int l55___ = (l2__tmp).f_pattNr;
			Module_errhan.f_mizRunTimeError(2043);
			return l2__tmp;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l1__tmp.getTypeName());
		}
	}
	}
	public static final boolean f_mizResolventVerify(Struct_MizUnifier au, Struct_MizPreInst aall_0, Struct_Vector acompl) {
		final Struct_MizPre l0_pre = (au).f_pre;
		final Reference<Boolean> l1_res = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l2_i = ((Reference<Integer>)(new Reference(0)));
		final Struct_Vector l6_compl = acompl;
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l1_res.value)&&(((int)l2_i.value)<Module_vector.f_sizeVector(l6_compl))));
		};
		final Struct_Vector l8_compl = acompl;
		final Struct_MizPreInst l9_all_0 = aall_0;
		final Struct_MizUnifier l10_u = au;
		final Func0<Object> l7_$4 = (Func0<Object>)() -> {
			final Struct_MizComplPair l8_cp = ((Struct_MizComplPair)Module_vector.f_getVectorUnsafe(l8_compl, ((int)l2_i.value)));
			final Struct_Vector l9_colls = Module_vector.f_makeVector(8);
			Module_vector.f_pushVector(l9_colls, (l8_cp).f_insts);
			final Reference<Boolean> l10_dead = ((Reference<Boolean>)(new Reference(false)));
			final Func2<Object,Integer, Integer> l11_doClause = (Func2<Object, Integer, Integer>)(Integer aclauseNr, Integer askipAtom) -> {
				final Struct_MizNatFunc l12_cl = ((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe((l9_all_0).f_evals, ((int)aclauseNr)));
				final int l15_$14 = (Module_mobjects.f_mizNatCount(l12_cl)-1);
				final Func1<Object,Integer> l16_$13 = (Func1<Object, Integer>)(Integer aj) -> {
					if ((!((boolean)l10_dead.value)&&(((int)aj)!=((int)askipAtom)))) {
						final Struct_MizIntPair l17_it = Module_mobjects.f_mizNatItem(l12_cl, ((int)aj));
						final Struct l18_bf = ((Struct)Module_vector.f_getVectorUnsafe((l0_pre).f_basic, (l17_it).f_x));
						Struct_MizPreInst l19_lInsts;
						if (((l17_it).f_y==1)) {
							l19_lInsts=Module_unifier.f_mizCompInstAsFalse(l10_u, l18_bf);
						} else {
							l19_lInsts=Module_unifier.f_mizCompInstAsTrue(l10_u, l18_bf);
						}
						if ((Module_prechecker.f_mizPreInstCount(l19_lInsts)==0)) {
							l10_dead.value = ((Boolean)true);
							return null;
						} else {
							if (!(l19_lInsts).f_top) {
								return Module_vector.f_pushVector(l9_colls, l19_lInsts);
							} else {
								return null;
							}
						}
					} else {
						return null;
					}
				};
				return Module_runtime.f_fori(0, l15_$14, l16_$13);
			};
			l11_doClause.invoke(((Integer)(l8_cp).f_clause1), ((Integer)(l8_cp).f_atom1));
			if (!((boolean)l10_dead.value)) {
				l11_doClause.invoke(((Integer)(l8_cp).f_clause2), ((Integer)(l8_cp).f_atom2));
			} else {
			}
			if (!((boolean)l10_dead.value)) {
				final Struct_MizPreInst l12_joined = Module_prechecker.f_mizPreInstJoinList(l0_pre, l9_colls);
				if ((Module_prechecker.f_mizPreInstCount(l12_joined)!=0)) {
					l1_res.value = ((Boolean)true);
				} else {
				}
			} else {
			}
			l2_i.value = ((Integer)(((int)l2_i.value)+1));
			return null;
		};
		Module_loop.f_while(l5_$3, l7_$4);
		return ((boolean)l1_res.value);
	}
	public static final Struct f_mizSetFreeVarInTrm(Struct_MizUnifier au, Struct at) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		Struct l1__tmp = at;
		switch (l1__tmp.getTypeId()) {
		case 70/*MizVarTrm*/: {
			final Struct_MizVarTrm l2__tmp = (Struct_MizVarTrm)l1__tmp;
			final int l3_srt = l2__tmp.f_sort;
			final int l4_varNr = l2__tmp.f_varNr;
			final int l5_srt = (l2__tmp).f_sort;
			final int l6_varNr = (l2__tmp).f_varNr;
			final int l7___ = (l2__tmp).f_pattNr;
			final int l8___ = (l2__tmp).f_trmInfo;
			if ((l5_srt==Module_lexicon.g_ikTrmBound)) {
				final Struct_MizVarTrm l9_vt = l2__tmp;
				if ((l6_varNr>(l0_env).f_boundVarNbr)) {
					((Field_varNr)l9_vt).set_varNr((l6_varNr-(l0_env).f_boundVarNbr));
				} else {
					((Field_sort)l9_vt).set_sort(Module_lexicon.g_ikTrmFreeVar);
					((Field_varNr)l9_vt).set_varNr((l6_varNr+(au).f_freeVarBase));
				}
			} else {
			}
			return l2__tmp;
		}
		default: {
			return at;
		}
		}
	}
	public static final Struct f_mizStandarizeTrmList(Struct_MizUnifier au, Struct al) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 64/*MizTrmNil*/: {
			return al;
		}
		case 63/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			return (new Struct_MizTrmElem(Module_correl.f_mizCopyTrm(l4_trm), Module_unifier.f_mizStandarizeTrmList(au, l5_next)));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct_MizTyp f_mizStandarizeType(Struct_MizUnifier au, Struct_MizTyp at) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct l1_lArgs = Module_unifier.f_mizStandarizeTrmList(au, (at).f_modArgs);
		return (new Struct_MizTyp((at).f_sort, Module_correl_inst.f_mizInstCluster(l0_env, (at).f_lower, l1_lArgs), Module_correl_inst.f_mizInstCluster(l0_env, (at).f_upper, l1_lArgs), (at).f_modNr, l1_lArgs, 0));
	}
	public static final boolean f_mizTypsAreSimilar(Struct_MizCorrelEnv aenv, Struct_MizTyp aa, Struct_MizTyp ab) {
		if ((((aa).f_sort!=(ab).f_sort)||!Module_unifier.f_mizClustersAreSimilar((aa).f_lower, (ab).f_lower))) {
			return false;
		} else {
			if (((aa).f_modNr==(ab).f_modNr)) {
				return true;
			} else {
				if (((aa).f_sort==Module_lexicon.g_ikTypMode)) {
					final Struct_Pair l0_pb = Module_correl.f_mizAdjustTyp(aenv, ab);
					return ((aa).f_modNr==((int)(l0_pb).f_first));
				} else {
					return false;
				}
			}
		}
	}
	public static final Struct f_mizUAdjustArgs(Struct_MizCorrelEnv aenv, int apredNr, Struct aargs) {
		final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coPredicate, apredNr);
		if (((l0_c).f_whichConstrNr!=0)) {
			return Module_correl.f_mizTrmListDrop(aargs, (l0_c).f_superfluous);
		} else {
			return aargs;
		}
	}
	public static final Struct f_mizUAllButLast(Struct al) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 64/*MizTrmNil*/: {
			return al;
		}
		case 63/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			Struct l6__tmp = l5_next;
			switch (l6__tmp.getTypeId()) {
			case 64/*MizTrmNil*/: {
				return ((Struct)SingletonStructs.str_MizTrmNil);
			}
			case 63/*MizTrmElem*/: {
				final Struct_MizTrmElem l7__tmp = (Struct_MizTrmElem)l6__tmp;
				final Struct l8___ = (l7__tmp).f_trm;
				final Struct l9___ = (l7__tmp).f_next;
				return (new Struct_MizTrmElem(l4_trm, Module_unifier.f_mizUAllButLast(l7__tmp)));
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l6__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_mizUArgs(Struct at) {
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
	public static final Struct_Vector f_mizUEqListAt(Struct_MizUnifier au, int akind, int aclassNr) {
		final Struct_Vector l0_byClass = ((Struct_Vector)Module_vector.f_getVectorUnsafe((au).f_eqList, akind));
		if (((aclassNr>=0)&&(aclassNr<Module_vector.f_sizeVector(l0_byClass)))) {
			return ((Struct_Vector)Module_vector.f_getVectorUnsafe(l0_byClass, aclassNr));
		} else {
			return Module_vector.f_makeVector(0);
		}
	}
	public static final Struct_MizFraenkelTrm f_mizUFrAsFraenkel(Struct at) {
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
			return ((Struct_MizFraenkelTrm)Native.failWithError("not a fraenkel trm"));
		}
		}
	}
	public static final Struct_MizPredFrm f_mizUFrAsPred(Struct af) {
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
			return ((Struct_MizPredFrm)Native.failWithError("not a pred frm"));
		}
		}
	}
	public static final int f_mizUFuncNr(Struct at) {
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
	public static final boolean f_mizUMemberMatches(Struct_MizUnifier au, Struct at, Struct am, Struct_Vector aargNrs, int akind) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final int l1_tNr = Module_unifier.f_mizUFuncNr(at);
		final int l2_mNr = Module_unifier.f_mizUFuncNr(am);
		if (((akind==Module_unifier.g_expUFunctor)&&(l1_tNr!=l2_mNr))) {
			Struct_MizFuncTrm l3_tf;
			Struct l4__tmp = at;
			switch (l4__tmp.getTypeId()) {
			case 44/*MizFuncTrm*/: {
				final Struct_MizFuncTrm l5__tmp = (Struct_MizFuncTrm)l4__tmp;
				final int l6___ = (l5__tmp).f_sort;
				final int l7___ = (l5__tmp).f_funcNr;
				final Struct l8___ = (l5__tmp).f_args;
				final int l9___ = (l5__tmp).f_pattNr;
				final int l10___ = (l5__tmp).f_trmInfo;
				l3_tf=l5__tmp;
				break;
			}
			default: {
				l3_tf=(new Struct_MizFuncTrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0, 0));
				break;
			}
			}
			Struct_MizFuncTrm l11_mf;
			Struct l12__tmp = am;
			switch (l12__tmp.getTypeId()) {
			case 44/*MizFuncTrm*/: {
				final Struct_MizFuncTrm l13__tmp = (Struct_MizFuncTrm)l12__tmp;
				final int l14___ = (l13__tmp).f_sort;
				final int l15___ = (l13__tmp).f_funcNr;
				final Struct l16___ = (l13__tmp).f_args;
				final int l17___ = (l13__tmp).f_pattNr;
				final int l18___ = (l13__tmp).f_trmInfo;
				l11_mf=l13__tmp;
				break;
			}
			default: {
				l11_mf=(new Struct_MizFuncTrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0, 0));
				break;
			}
			}
			final Struct_Pair l19_pt = Module_correl.f_mizAdjustTrm(l0_env, l3_tf);
			final Struct_Pair l20_pm = Module_correl.f_mizAdjustTrm(l0_env, l11_mf);
			if ((((int)(l19_pt).f_first)!=((int)(l20_pm).f_first))) {
				return false;
			} else {
				final int l21_skipT = (Module_correl.f_mizTrmListLength((l3_tf).f_args)-Module_correl.f_mizTrmListLength(((Struct)(l19_pt).f_second)));
				final Reference<Boolean> l22_okRef = ((Reference<Boolean>)(new Reference(true)));
				final Reference<Integer> l23_i = ((Reference<Integer>)(new Reference(l21_skipT)));
				final Reference<Struct> l24_cur = ((Reference<Struct>)(new Reference((l20_pm).f_second)));
				final Func0<Boolean> l27_$25 = (Func0<Boolean>)() -> {
					boolean l30_$29;
					if (((boolean)l22_okRef.value)) {
						final Struct l31_gsymswitch10 = l24_cur.value;
						Struct l32__tmp = l31_gsymswitch10;
						switch (l32__tmp.getTypeId()) {
						case 64/*MizTrmNil*/: {
							l30_$29=false;
							break;
						}
						default: {
							l30_$29=true;
							break;
						}
						}
					} else {
						l30_$29=false;
					}
					return ((Boolean)l30_$29);
				};
				final Struct_Vector l29_argNrs = aargNrs;
				final Func0<Object> l28_$26 = (Func0<Object>)() -> {
					final Struct l29_gsymswitch11 = l24_cur.value;
					Struct l30__tmp = l29_gsymswitch11;
					switch (l30__tmp.getTypeId()) {
					case 63/*MizTrmElem*/: {
						final Struct_MizTrmElem l31__tmp = (Struct_MizTrmElem)l30__tmp;
						final Struct l32_tm = l31__tmp.f_trm;
						final Struct l33_nm = l31__tmp.f_next;
						final Struct l34_tm = (l31__tmp).f_trm;
						final Struct l35_nm = (l31__tmp).f_next;
						if (((((int)l23_i.value)>=Module_vector.f_sizeVector(l29_argNrs))||(Module_unifier.f_mizUVarNr(l34_tm)!=((int)Module_vector.f_getVectorUnsafe(l29_argNrs, ((int)l23_i.value)))))) {
							l22_okRef.value = ((Boolean)false);
						} else {
						}
						l23_i.value = ((Integer)(((int)l23_i.value)+1));
						l24_cur.value = l35_nm;
						return null;
					}
					case 64/*MizTrmNil*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l30__tmp.getTypeName());
					}
				};
				Module_loop.f_while(l27_$25, l28_$26);
				return ((boolean)l22_okRef.value);
			}
		} else {
			if ((l1_tNr!=l2_mNr)) {
				return false;
			} else {
				final Reference<Boolean> l30_okRef = ((Reference<Boolean>)(new Reference(true)));
				final Reference<Integer> l31_i = ((Reference<Integer>)(new Reference(0)));
				final Reference<Struct> l32_cur = ((Reference<Struct>)(new Reference(Module_unifier.f_mizUArgs(am))));
				final Func0<Boolean> l35_$33 = (Func0<Boolean>)() -> {
					boolean l38_$37;
					if (((boolean)l30_okRef.value)) {
						final Struct l39_gsymswitch8 = l32_cur.value;
						Struct l40__tmp = l39_gsymswitch8;
						switch (l40__tmp.getTypeId()) {
						case 64/*MizTrmNil*/: {
							l38_$37=false;
							break;
						}
						default: {
							l38_$37=true;
							break;
						}
						}
					} else {
						l38_$37=false;
					}
					return ((Boolean)l38_$37);
				};
				final Struct_Vector l37_argNrs = aargNrs;
				final Func0<Object> l36_$34 = (Func0<Object>)() -> {
					final Struct l37_gsymswitch9 = l32_cur.value;
					Struct l38__tmp = l37_gsymswitch9;
					switch (l38__tmp.getTypeId()) {
					case 63/*MizTrmElem*/: {
						final Struct_MizTrmElem l39__tmp = (Struct_MizTrmElem)l38__tmp;
						final Struct l40_tm = l39__tmp.f_trm;
						final Struct l41_nm = l39__tmp.f_next;
						final Struct l42_tm = (l39__tmp).f_trm;
						final Struct l43_nm = (l39__tmp).f_next;
						if (((((int)l31_i.value)>=Module_vector.f_sizeVector(l37_argNrs))||(Module_unifier.f_mizUVarNr(l42_tm)!=((int)Module_vector.f_getVectorUnsafe(l37_argNrs, ((int)l31_i.value)))))) {
							l30_okRef.value = ((Boolean)false);
						} else {
						}
						l31_i.value = ((Integer)(((int)l31_i.value)+1));
						l32_cur.value = l43_nm;
						return null;
					}
					case 64/*MizTrmNil*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l38__tmp.getTypeName());
					}
				};
				Module_loop.f_while(l35_$33, l36_$34);
				return (((boolean)l30_okRef.value)&&(((int)l31_i.value)==Module_vector.f_sizeVector(aargNrs)));
			}
		}
	}
	public static final Struct f_mizURemoveExtQuantifier(Struct_MizUnifier au, Struct af0) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizPre l1_pre = (au).f_pre;
		Struct l2__tmp = af0;
		switch (l2__tmp.getTypeId()) {
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l3__tmp = (Struct_MizNegFrm)l2__tmp;
			final Struct l4_arg = l3__tmp.f_arg;
			final Struct l5_arg = (l3__tmp).f_arg;
			final int l6___ = (l3__tmp).f_pattNr;
			final Struct l7_na = Module_unifier.f_mizURemoveIntQuantifier(au, l5_arg);
			final Struct_MizNegFrm l8_f1 = l3__tmp;
			((Field_arg)l8_f1).set_arg(l7_na);
			Struct l9__tmp = l7_na;
			switch (l9__tmp.getTypeId()) {
			case 50/*MizNegFrm*/: {
				final Struct_MizNegFrm l10__tmp = (Struct_MizNegFrm)l9__tmp;
				final Struct l11_inner = l10__tmp.f_arg;
				final Struct l12_inner = (l10__tmp).f_arg;
				final int l13___ = (l10__tmp).f_pattNr;
				return l12_inner;
			}
			default: {
				return l3__tmp;
			}
			}
		}
		case 69/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l3__tmp = (Struct_MizUnivFrm)l2__tmp;
			final int l14___ = (l3__tmp).f_sort;
			final int l15___ = (l3__tmp).f_varId;
			final Struct_MizTyp l16___ = (l3__tmp).f_quantified;
			final Struct l17___ = (l3__tmp).f_scope;
			final int l18___ = (l3__tmp).f_pattNr;
			((Field_freeVarBase)au).set_freeVarBase(Module_vector.f_sizeVector((au).f_freeVarType));
			((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
			final Reference<Struct> l19_cur = ((Reference<Struct>)(new Reference(l3__tmp)));
			final Reference<Boolean> l20_cont = ((Reference<Boolean>)(new Reference(true)));
			final Func0<Boolean> l23_$21 = (Func0<Boolean>)() -> {
				return l20_cont.value;
			};
			final Struct_MizUnifier l25_u = au;
			final Func0<Object> l24_$22 = (Func0<Object>)() -> {
				final Struct l25_gsymswitch17 = l19_cur.value;
				Struct l26__tmp = l25_gsymswitch17;
				switch (l26__tmp.getTypeId()) {
				case 69/*MizUnivFrm*/: {
					final Struct_MizUnivFrm l27__tmp = (Struct_MizUnivFrm)l26__tmp;
					final Struct_MizTyp l28_q = l27__tmp.f_quantified;
					final Struct l29_s = l27__tmp.f_scope;
					final int l30___ = (l27__tmp).f_sort;
					final int l31___ = (l27__tmp).f_varId;
					final Struct_MizTyp l32_q = (l27__tmp).f_quantified;
					final Struct l33_s = (l27__tmp).f_scope;
					final int l34___ = (l27__tmp).f_pattNr;
					((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
					Module_unifier.f_mizNewFreeVar(l25_u, l32_q);
					l19_cur.value = l33_s;
					return null;
				}
				default: {
					l20_cont.value = ((Boolean)false);
					return null;
				}
				}
			};
			Module_loop.f_while(l23_$21, l24_$22);
			final Struct l26_body = l19_cur.value;
			final Struct_MizUnifier l29_u = au;
			final Func2<Struct,Struct_MizWithin, Struct> l28_$27 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al29_0, Struct atrm) -> {
				return Module_unifier.f_mizSetFreeVarInTrm(l29_u, atrm);
			};
			final Struct_MizWithin l30_w = Module_correl.f_mizMakeWithin(l28_$27);
			Module_correl.f_mizWithinFrm(l30_w, l26_body);
			{
				final Struct l31___tmp = l26_body;
				af0 = l31___tmp;
				continue TAIL_CALL;
			}
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l3__tmp = (Struct_MizConjFrm)l2__tmp;
			final Struct_Vector l32_conjuncts = l3__tmp.f_conjuncts;
			final Struct_Vector l33_conjuncts = (l3__tmp).f_conjuncts;
			final int l34___ = (l3__tmp).f_pattNr;
			final Struct_Vector l35_out = Module_vector.f_makeVector(Module_vector.f_sizeVector(l33_conjuncts));
			final Struct_MizUnifier l38_u = au;
			final Func1<Object,Struct> l37_$36 = (Func1<Object, Struct>)(Struct ag0) -> {
				final Struct l38_g = Module_unifier.f_mizURemoveExtQuantifier(l38_u, ag0);
				Struct l39__tmp = l38_g;
				switch (l39__tmp.getTypeId()) {
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l40__tmp = (Struct_MizConjFrm)l39__tmp;
					final Struct_Vector l41_inner = l40__tmp.f_conjuncts;
					final Struct_Vector l42_inner = (l40__tmp).f_conjuncts;
					final int l43___ = (l40__tmp).f_pattNr;
					final Func1<Object,Struct> l45_$44 = (Func1<Object, Struct>)(Struct ah) -> {
						return Module_vector.f_pushVector(l35_out, ah);
					};
					return Module_vector.f_iterVector(l42_inner, ((Func1<Object,Object>)(Func1)l45_$44));
				}
				default: {
					return Module_vector.f_pushVector(l35_out, l38_g);
				}
				}
			};
			Module_vector.f_iterVector(l33_conjuncts, ((Func1<Object,Object>)(Func1)l37_$36));
			return (new Struct_MizConjFrm(l35_out, 0));
		}
		default: {
			return af0;
		}
		}
	}
	}
	public static final Struct f_mizURemoveIntQuantifier(Struct_MizUnifier au, Struct af0) {
		Struct l0__tmp = af0;
		switch (l0__tmp.getTypeId()) {
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l2_arg = l1__tmp.f_arg;
			final Struct l3_arg = (l1__tmp).f_arg;
			final int l4___ = (l1__tmp).f_pattNr;
			final Struct l5_na = Module_unifier.f_mizURemoveExtQuantifier(au, l3_arg);
			final Struct_MizNegFrm l6_f1 = l1__tmp;
			((Field_arg)l6_f1).set_arg(l5_na);
			Struct l7__tmp = l5_na;
			switch (l7__tmp.getTypeId()) {
			case 50/*MizNegFrm*/: {
				final Struct_MizNegFrm l8__tmp = (Struct_MizNegFrm)l7__tmp;
				final Struct l9_inner = l8__tmp.f_arg;
				final Struct l10_inner = (l8__tmp).f_arg;
				final int l11___ = (l8__tmp).f_pattNr;
				return l10_inner;
			}
			default: {
				return l1__tmp;
			}
			}
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l12_conjuncts = l1__tmp.f_conjuncts;
			final Struct_Vector l13_conjuncts = (l1__tmp).f_conjuncts;
			final int l14___ = (l1__tmp).f_pattNr;
			final Struct_Vector l15_out = Module_vector.f_makeVector(Module_vector.f_sizeVector(l13_conjuncts));
			final Struct_MizUnifier l18_u = au;
			final Func1<Object,Struct> l17_$16 = (Func1<Object, Struct>)(Struct ag0) -> {
				final Struct l18_g = Module_unifier.f_mizURemoveIntQuantifier(l18_u, ag0);
				Struct l19__tmp = l18_g;
				switch (l19__tmp.getTypeId()) {
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l20__tmp = (Struct_MizConjFrm)l19__tmp;
					final Struct_Vector l21_inner = l20__tmp.f_conjuncts;
					final Struct_Vector l22_inner = (l20__tmp).f_conjuncts;
					final int l23___ = (l20__tmp).f_pattNr;
					final Func1<Object,Struct> l25_$24 = (Func1<Object, Struct>)(Struct ah) -> {
						return Module_vector.f_pushVector(l15_out, ah);
					};
					return Module_vector.f_iterVector(l22_inner, ((Func1<Object,Object>)(Func1)l25_$24));
				}
				default: {
					return Module_vector.f_pushVector(l15_out, l18_g);
				}
				}
			};
			Module_vector.f_iterVector(l13_conjuncts, ((Func1<Object,Object>)(Func1)l17_$16));
			return (new Struct_MizConjFrm(l15_out, 0));
		}
		default: {
			return af0;
		}
		}
	}
	public static final Object f_mizUSetTrmInfo(Struct at, int av) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 70/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2___ = (l1__tmp).f_sort;
			final int l3___ = (l1__tmp).f_varNr;
			final int l4___ = (l1__tmp).f_pattNr;
			final int l5___ = (l1__tmp).f_trmInfo;
			final Struct_MizVarTrm l6_x = l1__tmp;
			((Field_trmInfo)l6_x).set_trmInfo(av);
			return null;
		}
		default: {
			return null;
		}
		}
	}
	public static final int f_mizUVarNr(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 70/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2_varNr = l1__tmp.f_varNr;
			final int l3___ = (l1__tmp).f_sort;
			final int l4_varNr = (l1__tmp).f_varNr;
			final int l5___ = (l1__tmp).f_pattNr;
			final int l6___ = (l1__tmp).f_trmInfo;
			return l4_varNr;
		}
		default: {
			return 0;
		}
		}
	}
	public static final Struct_MizPreInst f_mizUniAttr(Struct_MizUnifier au, Struct_MizAttr aa, Struct_MizAttr ab, boolean asameSign) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_Pair l1_pa = Module_correl.f_mizAdjustAttr(l0_env, aa);
		final Struct_Pair l2_pb = Module_correl.f_mizAdjustAttr(l0_env, ab);
		boolean l3_signOk;
		if (asameSign) {
			l3_signOk=((aa).f_neg==(ab).f_neg);
		} else {
			l3_signOk=((aa).f_neg!=(ab).f_neg);
		}
		if (((((int)(l1_pa).f_first)==((int)(l2_pb).f_first))&&l3_signOk)) {
			return Module_unifier.f_mizUniTrmList(au, ((Struct)(l1_pa).f_second), ((Struct)(l2_pb).f_second));
		} else {
			return Module_unifier.f_mizUniBottom();
		}
	}
	public static final Struct_MizPreInst f_mizUniAttrFrm(Struct_MizUnifier au, Struct af, int asign_0) {
		Struct_MizPredFrm l0_pf;
		Struct l1__tmp = af;
		switch (l1__tmp.getTypeId()) {
		case 55/*MizPredFrm*/: {
			final Struct_MizPredFrm l2__tmp = (Struct_MizPredFrm)l1__tmp;
			final int l3___ = (l2__tmp).f_sort;
			final int l4___ = (l2__tmp).f_predNr;
			final Struct l5___ = (l2__tmp).f_args;
			final int l6___ = (l2__tmp).f_pattNr;
			l0_pf=l2__tmp;
			break;
		}
		default: {
			l0_pf=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
			break;
		}
		}
		final Struct l7_lLeft = (Module_correl.f_mizLastElem((l0_pf).f_args)).f_trm;
		final Struct_MizPreInst l8_res = Module_unifier.f_mizUniBottom();
		if ((Module_correl.f_mizTrmSort(l7_lLeft)==Module_lexicon.g_ikTrmEqConst)) {
			final Struct l9_lAttrArgs = Module_unifier.f_mizUAllButLast((l0_pf).f_args);
			final Struct_MizAttrColl l10_sc = ((Struct_MizAttrColl)Module_vector.f_getVectorUnsafe((au).f_eqClassSuper, Module_unifier.f_mizUVarNr(l7_lLeft)));
			final Struct_Vector l13_$12 = (l10_sc).f_items;
			final Struct_MizPredFrm l15_pf = l0_pf;
			final int l16_sign_0 = asign_0;
			final Struct_MizUnifier l17_u = au;
			final Func1<Object,Struct_MizAttr> l14_$11 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aat) -> {
				if ((((aat).f_attrNr==(l15_pf).f_predNr)&&((aat).f_neg==l16_sign_0))) {
					final Struct_MizPreInst l15_l = Module_unifier.f_mizUniTrmList(l17_u, l9_lAttrArgs, (aat).f_args);
					return Module_unifier.f_mizUniUnion(l17_u, l8_res, l15_l);
				} else {
					return null;
				}
			};
			Module_vector.f_iterVector(l13_$12, ((Func1<Object,Object>)(Func1)l14_$11));
		} else {
		}
		return l8_res;
	}
	public static final Struct_MizPreInst f_mizUniBottom() {
		return Module_prechecker.f_mizPreInstBottom();
	}
	public static final Struct_MizPreInst f_mizUniCopy(Struct_MizPreInst ap) {
		final Struct_MizPreInst l0_r = (new Struct_MizPreInst(Module_vector.f_makeVector(Module_vector.f_sizeVector((ap).f_evals)), (ap).f_top, (ap).f_overflow));
		final Struct_Vector l3_$2 = (ap).f_evals;
		final Func1<Object,Struct_MizNatFunc> l4_$1 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
			return Module_vector.f_pushVector((l0_r).f_evals, Module_mobjects.f_mizCopyNatFunc(ae));
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		return l0_r;
	}
	public static final Struct_MizPreInst f_mizUniEqClassAndFreeVar(Struct_MizUnifier au, int aeqNr, int avarNr) {
		final int l0_key = ((avarNr*Module_limits.g_MaxTrmNbr)+aeqNr);
		final Struct l1_gsymswitch14 = Module_tree.f_lookupTree((au).f_freeVarUnifs, l0_key);
		Struct l2__tmp = l1_gsymswitch14;
		switch (l2__tmp.getTypeId()) {
		case 83/*Some*/: {
			final Struct_Some l3__tmp = (Struct_Some)l2__tmp;
			final Object l4_cached = l3__tmp.f_value;
			final Struct_MizPreInst l5_cached = ((Struct_MizPreInst)(l3__tmp).f_value);
			return Module_unifier.f_mizUniCopy(l5_cached);
		}
		case 75/*None*/: {
			final Struct_MizPreInst l6_base = Module_unifier.f_mizUniEqClassTyps(au, aeqNr, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((au).f_freeVarType, (avarNr-1))));
			Struct_MizPreInst l7_res;
			if ((Module_prechecker.f_mizPreInstCount(l6_base)==0)) {
				l7_res=l6_base;
			} else {
				if ((l6_base).f_top) {
					final Struct_MizNatFunc l8_lInst = Module_mobjects.f_mizInitNatFunc(2);
					Module_mobjects.f_mizNatAssign(l8_lInst, (avarNr-1), aeqNr);
					l7_res=Module_prechecker.f_mizPreInstSingle(l8_lInst);
				} else {
					final Struct_Vector l11_$10 = (l6_base).f_evals;
					final int l13_varNr = avarNr;
					final int l14_eqNr = aeqNr;
					final Func1<Object,Struct_MizNatFunc> l12_$9 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
						return Module_mobjects.f_mizNatAssign(ae, (l13_varNr-1), l14_eqNr);
					};
					Module_vector.f_iterVector(l11_$10, ((Func1<Object,Object>)(Func1)l12_$9));
					l7_res=l6_base;
				}
			}
			((Field_freeVarUnifs)au).set_freeVarUnifs(Module_tree.f_setTree((au).f_freeVarUnifs, l0_key, l7_res));
			return Module_unifier.f_mizUniCopy(l7_res);
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
		}
	}
	public static final Struct_MizPreInst f_mizUniEqClassTyps(Struct_MizUnifier au, int aeqNr, Struct_MizTyp atyp) {
		final Struct_MizPreInst l0_res = Module_unifier.f_mizUniBottom();
		final Struct_Vector l3_$2 = ((Struct_Vector)Module_vector.f_getVectorUnsafe((au).f_eqClassType, aeqNr));
		final Struct_MizUnifier l5_u = au;
		final Struct_MizTyp l6_typ = atyp;
		final Func1<Object,Struct_MizTyp> l4_$1 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
			final Struct_MizPreInst l5_l = Module_unifier.f_mizUniRadices(l5_u, l6_typ, axt);
			return Module_unifier.f_mizUniUnion(l5_u, l0_res, l5_l);
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		final Struct_MizPreInst l7_l2 = Module_unifier.f_mizUniInclClusters(au, (atyp).f_lower, ((Struct_MizAttrColl)Module_vector.f_getVectorUnsafe((au).f_eqClassSuper, aeqNr)), true);
		Module_unifier.f_mizUniJoin(au, l0_res, l7_l2);
		return l0_res;
	}
	public static final Struct_MizPreInst f_mizUniFraenkelTrm(Struct_MizUnifier au, Struct aa, Struct ab) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		Struct_MizFraenkelTrm l1_ka;
		Struct l2__tmp = aa;
		switch (l2__tmp.getTypeId()) {
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l3__tmp = (Struct_MizFraenkelTrm)l2__tmp;
			final Struct_Vector l4___ = (l3__tmp).f_lambdaArgs;
			final Struct l5___ = (l3__tmp).f_scope;
			final Struct l6___ = (l3__tmp).f_compr;
			final Struct_MizIntSeq l7___ = (l3__tmp).f_idents;
			final int l8___ = (l3__tmp).f_pattNr;
			final int l9___ = (l3__tmp).f_trmInfo;
			l1_ka=l3__tmp;
			break;
		}
		default: {
			l1_ka=(new Struct_MizFraenkelTrm(Module_vector.f_makeVector(0), (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), Module_mobjects.f_mizInitIntSeq(0), 0, 0));
			break;
		}
		}
		Struct_MizFraenkelTrm l10_kb;
		Struct l11__tmp = ab;
		switch (l11__tmp.getTypeId()) {
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l12__tmp = (Struct_MizFraenkelTrm)l11__tmp;
			final Struct_Vector l13___ = (l12__tmp).f_lambdaArgs;
			final Struct l14___ = (l12__tmp).f_scope;
			final Struct l15___ = (l12__tmp).f_compr;
			final Struct_MizIntSeq l16___ = (l12__tmp).f_idents;
			final int l17___ = (l12__tmp).f_pattNr;
			final int l18___ = (l12__tmp).f_trmInfo;
			l10_kb=l12__tmp;
			break;
		}
		default: {
			l10_kb=(new Struct_MizFraenkelTrm(Module_vector.f_makeVector(0), (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), Module_mobjects.f_mizInitIntSeq(0), 0, 0));
			break;
		}
		}
		if ((Module_vector.f_sizeVector((l1_ka).f_lambdaArgs)!=Module_vector.f_sizeVector((l10_kb).f_lambdaArgs))) {
			return Module_unifier.f_mizUniBottom();
		} else {
			final int l19_lB = (l0_env).f_boundVarNbr;
			final Struct_MizPreInst l20_res = Module_unifier.f_mizUniTop();
			final int l23_$22 = (Module_vector.f_sizeVector((l1_ka).f_lambdaArgs)-1);
			final Struct_MizUnifier l25_u = au;
			final Struct_MizFraenkelTrm l26_ka = l1_ka;
			final Struct_MizFraenkelTrm l27_kb = l10_kb;
			final Func1<Object,Integer> l24_$21 = (Func1<Object, Integer>)(Integer ai) -> {
				((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
				final Struct_MizPreInst l25_l = Module_unifier.f_mizUniTyp(l25_u, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l26_ka).f_lambdaArgs, ((int)ai))), ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l27_kb).f_lambdaArgs, ((int)ai))));
				return Module_unifier.f_mizUniJoin(l25_u, l20_res, l25_l);
			};
			Module_runtime.f_fori(0, l23_$22, l24_$21);
			if ((Module_prechecker.f_mizPreInstCount(l20_res)!=0)) {
				final Struct_MizPreInst l28_l1 = Module_unifier.f_mizUniTrm(au, (l1_ka).f_scope, (l10_kb).f_scope);
				Module_unifier.f_mizUniJoin(au, l20_res, l28_l1);
			} else {
			}
			if ((Module_prechecker.f_mizPreInstCount(l20_res)!=0)) {
				final Struct_MizPreInst l29_l2 = Module_unifier.f_mizUniFrm(au, (l1_ka).f_compr, (l10_kb).f_compr);
				Module_unifier.f_mizUniJoin(au, l20_res, l29_l2);
			} else {
			}
			((Field_boundVarNbr)l0_env).set_boundVarNbr(l19_lB);
			return l20_res;
		}
	}
	public static final Struct_MizPreInst f_mizUniFrm(Struct_MizUnifier au, Struct aa, Struct ab) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final int l1_sa = Module_correl.f_mizFrmSort(aa);
		if ((l1_sa!=Module_correl.f_mizFrmSort(ab))) {
			return Module_unifier.f_mizUniBottom();
		} else {
			if ((l1_sa==Module_lexicon.g_ikFrmVerum)) {
				return Module_unifier.f_mizUniTop();
			} else {
				Struct l2__tmp = aa;
				switch (l2__tmp.getTypeId()) {
				case 50/*MizNegFrm*/: {
					final Struct_MizNegFrm l3__tmp = (Struct_MizNegFrm)l2__tmp;
					final Struct l4_arg = l3__tmp.f_arg;
					final Struct l5_arg = (l3__tmp).f_arg;
					final int l6___ = (l3__tmp).f_pattNr;
					Struct_MizNegFrm l7_nb;
					Struct l8__tmp = ab;
					switch (l8__tmp.getTypeId()) {
					case 50/*MizNegFrm*/: {
						final Struct_MizNegFrm l9__tmp = (Struct_MizNegFrm)l8__tmp;
						final Struct l10___ = (l9__tmp).f_arg;
						final int l11___ = (l9__tmp).f_pattNr;
						l7_nb=l9__tmp;
						break;
					}
					default: {
						l7_nb=(new Struct_MizNegFrm((new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
						break;
					}
					}
					{
						final Struct l12___tmp = l5_arg;
						final Struct l13___tmp = (l7_nb).f_arg;
						aa = l12___tmp;
						ab = l13___tmp;
						continue TAIL_CALL;
					}
				}
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l3__tmp = (Struct_MizConjFrm)l2__tmp;
					final Struct_Vector l14_conjuncts = l3__tmp.f_conjuncts;
					final Struct_Vector l15_conjuncts = (l3__tmp).f_conjuncts;
					final int l16___ = (l3__tmp).f_pattNr;
					Struct_MizConjFrm l17_cb;
					Struct l18__tmp = ab;
					switch (l18__tmp.getTypeId()) {
					case 26/*MizConjFrm*/: {
						final Struct_MizConjFrm l19__tmp = (Struct_MizConjFrm)l18__tmp;
						final Struct_Vector l20___ = (l19__tmp).f_conjuncts;
						final int l21___ = (l19__tmp).f_pattNr;
						l17_cb=l19__tmp;
						break;
					}
					default: {
						l17_cb=(new Struct_MizConjFrm(Module_vector.f_makeVector(0), 0));
						break;
					}
					}
					if ((Module_vector.f_sizeVector(l15_conjuncts)!=Module_vector.f_sizeVector((l17_cb).f_conjuncts))) {
						return Module_unifier.f_mizUniBottom();
					} else {
						final Struct_MizPreInst l22_res = Module_unifier.f_mizUniTop();
						final Reference<Integer> l23_i = ((Reference<Integer>)(new Reference(0)));
						final Func0<Boolean> l26_$24 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l23_i.value)<Module_vector.f_sizeVector(l15_conjuncts))&&(Module_prechecker.f_mizPreInstCount(l22_res)!=0)));
						};
						final Struct_MizUnifier l28_u = au;
						final Struct_MizConjFrm l29_cb = l17_cb;
						final Func0<Object> l27_$25 = (Func0<Object>)() -> {
							final Struct_MizPreInst l28_l = Module_unifier.f_mizUniFrm(l28_u, ((Struct)Module_vector.f_getVectorUnsafe(l15_conjuncts, ((int)l23_i.value))), ((Struct)Module_vector.f_getVectorUnsafe((l29_cb).f_conjuncts, ((int)l23_i.value))));
							Module_unifier.f_mizUniJoin(l28_u, l22_res, l28_l);
							l23_i.value = ((Integer)(((int)l23_i.value)+1));
							return null;
						};
						Module_loop.f_while(l26_$24, l27_$25);
						return l22_res;
					}
				}
				case 55/*MizPredFrm*/: {
					final Struct_MizPredFrm l3__tmp = (Struct_MizPredFrm)l2__tmp;
					final int l30_srt = l3__tmp.f_sort;
					final int l31_predNr = l3__tmp.f_predNr;
					final Struct l32_args = l3__tmp.f_args;
					final int l33_srt = (l3__tmp).f_sort;
					final int l34_predNr = (l3__tmp).f_predNr;
					final Struct l35_args = (l3__tmp).f_args;
					final int l36___ = (l3__tmp).f_pattNr;
					Struct_MizPredFrm l37_pa2;
					Struct l38__tmp = l3__tmp;
					switch (l38__tmp.getTypeId()) {
					case 55/*MizPredFrm*/: {
						final Struct_MizPredFrm l39__tmp = (Struct_MizPredFrm)l38__tmp;
						final int l40___ = (l39__tmp).f_sort;
						final int l41___ = (l39__tmp).f_predNr;
						final Struct l42___ = (l39__tmp).f_args;
						final int l43___ = (l39__tmp).f_pattNr;
						l37_pa2=l39__tmp;
						break;
					}
					default: {
						l37_pa2=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
						break;
					}
					}
					Struct_MizPredFrm l44_pb;
					Struct l45__tmp = ab;
					switch (l45__tmp.getTypeId()) {
					case 55/*MizPredFrm*/: {
						final Struct_MizPredFrm l46__tmp = (Struct_MizPredFrm)l45__tmp;
						final int l47___ = (l46__tmp).f_sort;
						final int l48___ = (l46__tmp).f_predNr;
						final Struct l49___ = (l46__tmp).f_args;
						final int l50___ = (l46__tmp).f_pattNr;
						l44_pb=l46__tmp;
						break;
					}
					default: {
						l44_pb=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
						break;
					}
					}
					if ((l33_srt==Module_lexicon.g_ikFrmPred)) {
						final Struct_Pair l51_qa = Module_correl.f_mizAdjustFrm(l0_env, l37_pa2);
						final Struct_Pair l52_qb = Module_correl.f_mizAdjustFrm(l0_env, l44_pb);
						if ((((int)(l51_qa).f_first)!=((int)(l52_qb).f_first))) {
							return Module_unifier.f_mizUniBottom();
						} else {
							final Struct_MizPreInst l53_res = Module_unifier.f_mizUniTrmList(au, ((Struct)(l51_qa).f_second), ((Struct)(l52_qb).f_second));
							final Struct_MizConstr l54_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, (l37_pa2).f_predNr);
							if (Module_correl.f_mizHasProperty(l54_c, Module_correl.g_sySymmetry)) {
								final Struct l55_sw = Module_roundcl.f_mizSwapArguments((l37_pa2).f_args, (l54_c).f_firstArg, (l54_c).f_secondArg);
								final Struct l56_swAdj = Module_unifier.f_mizUAdjustArgs(l0_env, (l37_pa2).f_predNr, l55_sw);
								final Struct_MizPreInst l57_l = Module_unifier.f_mizUniTrmList(au, l56_swAdj, ((Struct)(l52_qb).f_second));
								Module_unifier.f_mizUniUnion(au, l53_res, l57_l);
							} else {
								final Struct_MizConstr l58_c2 = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, (l44_pb).f_predNr);
								if (Module_correl.f_mizHasProperty(l58_c2, Module_correl.g_sySymmetry)) {
									final Struct l59_sw = Module_roundcl.f_mizSwapArguments((l44_pb).f_args, (l58_c2).f_firstArg, (l58_c2).f_secondArg);
									final Struct l60_swAdj = Module_unifier.f_mizUAdjustArgs(l0_env, (l44_pb).f_predNr, l59_sw);
									final Struct_MizPreInst l61_l = Module_unifier.f_mizUniTrmList(au, ((Struct)(l51_qa).f_second), l60_swAdj);
									Module_unifier.f_mizUniUnion(au, l53_res, l61_l);
								} else {
								}
							}
							return l53_res;
						}
					} else {
						if ((l33_srt==Module_lexicon.g_ikFrmAttr)) {
							final Struct_Pair l62_qa = Module_correl.f_mizAdjustAttrFrm(l0_env, l37_pa2);
							final Struct_Pair l63_qb = Module_correl.f_mizAdjustAttrFrm(l0_env, l44_pb);
							if ((((int)(l62_qa).f_first)==((int)(l63_qb).f_first))) {
								return Module_unifier.f_mizUniTrmList(au, ((Struct)(l62_qa).f_second), ((Struct)(l63_qb).f_second));
							} else {
								return Module_unifier.f_mizUniBottom();
							}
						} else {
							if ((l34_predNr==(l44_pb).f_predNr)) {
								return Module_unifier.f_mizUniTrmList(au, l35_args, (l44_pb).f_args);
							} else {
								return Module_unifier.f_mizUniBottom();
							}
						}
					}
				}
				case 48/*MizLocPredFrm*/: {
					final Struct_MizLocPredFrm l3__tmp = (Struct_MizLocPredFrm)l2__tmp;
					final int l64_predNr = l3__tmp.f_predNr;
					final Struct l65_args = l3__tmp.f_args;
					final int l66_predNr = (l3__tmp).f_predNr;
					final Struct l67_args = (l3__tmp).f_args;
					final Struct l68___ = (l3__tmp).f_predExp;
					final int l69___ = (l3__tmp).f_pattNr;
					Struct_MizLocPredFrm l70_lb;
					Struct l71__tmp = ab;
					switch (l71__tmp.getTypeId()) {
					case 48/*MizLocPredFrm*/: {
						final Struct_MizLocPredFrm l72__tmp = (Struct_MizLocPredFrm)l71__tmp;
						final int l73___ = (l72__tmp).f_predNr;
						final Struct l74___ = (l72__tmp).f_args;
						final Struct l75___ = (l72__tmp).f_predExp;
						final int l76___ = (l72__tmp).f_pattNr;
						l70_lb=l72__tmp;
						break;
					}
					default: {
						l70_lb=(new Struct_MizLocPredFrm(0, ((Struct)SingletonStructs.str_MizTrmNil), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
						break;
					}
					}
					if ((l66_predNr==(l70_lb).f_predNr)) {
						return Module_unifier.f_mizUniTrmList(au, l67_args, (l70_lb).f_args);
					} else {
						return Module_unifier.f_mizUniBottom();
					}
				}
				case 69/*MizUnivFrm*/: {
					final Struct_MizUnivFrm l3__tmp = (Struct_MizUnivFrm)l2__tmp;
					final Struct_MizTyp l77_quantified = l3__tmp.f_quantified;
					final Struct l78_scope = l3__tmp.f_scope;
					final int l79___ = (l3__tmp).f_sort;
					final int l80___ = (l3__tmp).f_varId;
					final Struct_MizTyp l81_quantified = (l3__tmp).f_quantified;
					final Struct l82_scope = (l3__tmp).f_scope;
					final int l83___ = (l3__tmp).f_pattNr;
					Struct_MizUnivFrm l84_ub;
					Struct l85__tmp = ab;
					switch (l85__tmp.getTypeId()) {
					case 69/*MizUnivFrm*/: {
						final Struct_MizUnivFrm l86__tmp = (Struct_MizUnivFrm)l85__tmp;
						final int l87___ = (l86__tmp).f_sort;
						final int l88___ = (l86__tmp).f_varId;
						final Struct_MizTyp l89___ = (l86__tmp).f_quantified;
						final Struct l90___ = (l86__tmp).f_scope;
						final int l91___ = (l86__tmp).f_pattNr;
						l84_ub=l86__tmp;
						break;
					}
					default: {
						l84_ub=(new Struct_MizUnivFrm(Module_lexicon.g_ikError, 0, Module_correl.f_mizNewIncorTyp(), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
						break;
					}
					}
					final Struct_MizPreInst l92_res = Module_unifier.f_mizUniTyp(au, l81_quantified, (l84_ub).f_quantified);
					if ((Module_prechecker.f_mizPreInstCount(l92_res)!=0)) {
						((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
						Module_correl.f_mizSetBoundVar(l0_env, (l0_env).f_boundVarNbr, (l84_ub).f_quantified);
						final Struct_MizPreInst l93_l = Module_unifier.f_mizUniFrm(au, l82_scope, (l84_ub).f_scope);
						Module_unifier.f_mizUniJoin(au, l92_res, l93_l);
						((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-1));
					} else {
					}
					return l92_res;
				}
				case 60/*MizQualFrm*/: {
					final Struct_MizQualFrm l3__tmp = (Struct_MizQualFrm)l2__tmp;
					final Struct l94_trm = l3__tmp.f_trm;
					final Struct_MizTyp l95_typ = l3__tmp.f_typ;
					final Struct l96_trm = (l3__tmp).f_trm;
					final Struct_MizTyp l97_typ = (l3__tmp).f_typ;
					final int l98___ = (l3__tmp).f_pattNr;
					Struct_MizQualFrm l99_qb;
					Struct l100__tmp = ab;
					switch (l100__tmp.getTypeId()) {
					case 60/*MizQualFrm*/: {
						final Struct_MizQualFrm l101__tmp = (Struct_MizQualFrm)l100__tmp;
						final Struct l102___ = (l101__tmp).f_trm;
						final Struct_MizTyp l103___ = (l101__tmp).f_typ;
						final int l104___ = (l101__tmp).f_pattNr;
						l99_qb=l101__tmp;
						break;
					}
					default: {
						l99_qb=(new Struct_MizQualFrm((new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), Module_correl.f_mizNewIncorTyp(), 0));
						break;
					}
					}
					final Struct_MizPreInst l105_res = Module_unifier.f_mizUniTrm(au, l96_trm, (l99_qb).f_trm);
					if ((Module_prechecker.f_mizPreInstCount(l105_res)!=0)) {
						final Struct_MizPreInst l106_l = Module_unifier.f_mizUniTyp(au, l97_typ, (l99_qb).f_typ);
						Module_unifier.f_mizUniJoin(au, l105_res, l106_l);
					} else {
					}
					return l105_res;
				}
				case 41/*MizFlexFrm*/: {
					final Struct_MizFlexFrm l3__tmp = (Struct_MizFlexFrm)l2__tmp;
					final Struct l107_lo = l3__tmp.f_leftOrig;
					final Struct l108_ro = l3__tmp.f_rightOrig;
					final int l109___ = (l3__tmp).f_sort;
					final Struct l110_lo = (l3__tmp).f_leftOrig;
					final Struct l111_ro = (l3__tmp).f_rightOrig;
					final Struct l112___ = (l3__tmp).f_leftTrm;
					final Struct l113___ = (l3__tmp).f_rightTrm;
					final Struct l114___ = (l3__tmp).f_expansion;
					final int l115___ = (l3__tmp).f_pattNr;
					Struct_MizFlexFrm l116_xb;
					Struct l117__tmp = ab;
					switch (l117__tmp.getTypeId()) {
					case 41/*MizFlexFrm*/: {
						final Struct_MizFlexFrm l118__tmp = (Struct_MizFlexFrm)l117__tmp;
						final int l119___ = (l118__tmp).f_sort;
						final Struct l120___ = (l118__tmp).f_leftOrig;
						final Struct l121___ = (l118__tmp).f_rightOrig;
						final Struct l122___ = (l118__tmp).f_leftTrm;
						final Struct l123___ = (l118__tmp).f_rightTrm;
						final Struct l124___ = (l118__tmp).f_expansion;
						final int l125___ = (l118__tmp).f_pattNr;
						l116_xb=l118__tmp;
						break;
					}
					default: {
						l116_xb=(new Struct_MizFlexFrm(Module_lexicon.g_ikError, (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), (new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
						break;
					}
					}
					final Struct_MizPreInst l126_res = Module_unifier.f_mizUniFrm(au, l110_lo, (l116_xb).f_leftOrig);
					if ((Module_prechecker.f_mizPreInstCount(l126_res)!=0)) {
						final Struct_MizPreInst l127_l = Module_unifier.f_mizUniFrm(au, l111_ro, (l116_xb).f_rightOrig);
						Module_unifier.f_mizUniJoin(au, l126_res, l127_l);
					} else {
					}
					return l126_res;
				}
				default: {
					Module_errhan.f_mizRunTimeError(2034);
					return Module_unifier.f_mizUniBottom();
				}
				}
			}
		}
	}
	}
	public static final Struct_MizPreInst f_mizUniFunc(Struct_MizUnifier au, Struct aa, Struct ab) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		if ((Module_correl.f_mizTrmSort(ab)!=Module_lexicon.g_ikTrmFunctor)) {
			return Module_unifier.f_mizUniBottom();
		} else {
			Struct_MizFuncTrm l1_fa;
			Struct l2__tmp = aa;
			switch (l2__tmp.getTypeId()) {
			case 44/*MizFuncTrm*/: {
				final Struct_MizFuncTrm l3__tmp = (Struct_MizFuncTrm)l2__tmp;
				final int l4___ = (l3__tmp).f_sort;
				final int l5___ = (l3__tmp).f_funcNr;
				final Struct l6___ = (l3__tmp).f_args;
				final int l7___ = (l3__tmp).f_pattNr;
				final int l8___ = (l3__tmp).f_trmInfo;
				l1_fa=l3__tmp;
				break;
			}
			default: {
				l1_fa=(new Struct_MizFuncTrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0, 0));
				break;
			}
			}
			Struct_MizFuncTrm l9_fb;
			Struct l10__tmp = ab;
			switch (l10__tmp.getTypeId()) {
			case 44/*MizFuncTrm*/: {
				final Struct_MizFuncTrm l11__tmp = (Struct_MizFuncTrm)l10__tmp;
				final int l12___ = (l11__tmp).f_sort;
				final int l13___ = (l11__tmp).f_funcNr;
				final Struct l14___ = (l11__tmp).f_args;
				final int l15___ = (l11__tmp).f_pattNr;
				final int l16___ = (l11__tmp).f_trmInfo;
				l9_fb=l11__tmp;
				break;
			}
			default: {
				l9_fb=(new Struct_MizFuncTrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0, 0));
				break;
			}
			}
			if (((l1_fa).f_funcNr==(l9_fb).f_funcNr)) {
				return Module_unifier.f_mizUniTrmList(au, (l1_fa).f_args, (l9_fb).f_args);
			} else {
				final Struct_Pair l17_pa = Module_correl.f_mizAdjustTrm(l0_env, l1_fa);
				final Struct_Pair l18_pb = Module_correl.f_mizAdjustTrm(l0_env, l9_fb);
				if ((((int)(l17_pa).f_first)==((int)(l18_pb).f_first))) {
					return Module_unifier.f_mizUniTrmList(au, ((Struct)(l17_pa).f_second), ((Struct)(l18_pb).f_second));
				} else {
					return Module_unifier.f_mizUniBottom();
				}
			}
		}
	}
	public static final Struct_MizPreInst f_mizUniInclClusters(Struct_MizUnifier au, Struct_MizAttrColl ac1, Struct_MizAttrColl ac2, boolean aargOrd) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizPreInst l1_res = Module_unifier.f_mizUniBottom();
		final Struct_MizPreInst l2_acc = Module_unifier.f_mizUniTop();
		final Reference<Boolean> l3_failed = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l4_j = ((Reference<Integer>)(new Reference(0)));
		final Reference<Integer> l5_i = ((Reference<Integer>)(new Reference(0)));
		final int l6_n1 = Module_vector.f_sizeVector((ac1).f_items);
		final int l7_n2 = Module_vector.f_sizeVector((ac2).f_items);
		final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l3_failed.value)&&(((int)l5_i.value)<l6_n1)));
		};
		final Struct_MizAttrColl l12_c1 = ac1;
		final Struct_MizAttrColl l13_c2 = ac2;
		final boolean l14_argOrd = aargOrd;
		final Struct_MizUnifier l15_u = au;
		final Func0<Object> l11_$9 = (Func0<Object>)() -> {
			final Struct_MizAttr l12_a = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l12_c1).f_items, ((int)l5_i.value)));
			final int l13_lAttrNr = Module_correl.f_mizAdjustedAttrNr(l0_env, l12_a);
			final Func0<Boolean> l16_$14 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l4_j.value)<l7_n2)&&(Module_correl.f_mizAdjustedAttrNr(l0_env, ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l13_c2).f_items, ((int)l4_j.value))))<l13_lAttrNr)));
			};
			final Func0<Object> l17_$15 = (Func0<Object>)() -> {
				l4_j.value = ((Integer)(((int)l4_j.value)+1));
				return null;
			};
			Module_loop.f_while(l16_$14, l17_$15);
			if ((((int)l4_j.value)==l7_n2)) {
				l3_failed.value = ((Boolean)true);
			} else {
				final Struct_MizPreInst l18_l1 = Module_unifier.f_mizUniBottom();
				final Reference<Integer> l19_k = ((Reference<Integer>)(new Reference(l4_j.value)));
				final Reference<Boolean> l20_go = ((Reference<Boolean>)(new Reference(true)));
				final Func0<Boolean> l23_$21 = (Func0<Boolean>)() -> {
					return l20_go.value;
				};
				final Func0<Object> l24_$22 = (Func0<Object>)() -> {
					final Struct_MizAttr l25_b = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l13_c2).f_items, ((int)l19_k.value)));
					Struct_MizPreInst l26_l2;
					if (l14_argOrd) {
						l26_l2=Module_unifier.f_mizUniAttr(l15_u, l12_a, l25_b, true);
					} else {
						l26_l2=Module_unifier.f_mizUniAttr(l15_u, l25_b, l12_a, true);
					}
					Module_unifier.f_mizUniUnion(l15_u, l18_l1, l26_l2);
					l19_k.value = ((Integer)(((int)l19_k.value)+1));
					if (((((int)l19_k.value)>=l7_n2)||(Module_correl.f_mizAdjustedAttrNr(l0_env, ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l13_c2).f_items, ((int)l19_k.value))))>l13_lAttrNr))) {
						l20_go.value = ((Boolean)false);
						return null;
					} else {
						return null;
					}
				};
				Module_loop.f_while(l23_$21, l24_$22);
				Module_unifier.f_mizUniJoin(l15_u, l2_acc, l18_l1);
				if ((Module_prechecker.f_mizPreInstCount(l2_acc)==0)) {
					l3_failed.value = ((Boolean)true);
				} else {
				}
			}
			l5_i.value = ((Integer)(((int)l5_i.value)+1));
			return null;
		};
		Module_loop.f_while(l10_$8, l11_$9);
		if (((boolean)l3_failed.value)) {
			return l1_res;
		} else {
			Module_unifier.f_mizUniUnion(au, l1_res, l2_acc);
			return l1_res;
		}
	}
	public static final Object f_mizUniJoin(Struct_MizUnifier au, Struct_MizPreInst ap, Struct_MizPreInst aq) {
		return Module_prechecker.f_mizPreInstJoinWith((au).f_pre, ap, aq);
	}
	public static final Struct_MizPreInst f_mizUniRadices(Struct_MizUnifier au, Struct_MizTyp aa, Struct_MizTyp ab) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		if (((aa).f_sort!=(ab).f_sort)) {
			return Module_unifier.f_mizUniBottom();
		} else {
			Struct_Pair l1_p;
			if (((aa).f_sort==Module_lexicon.g_ikTypMode)) {
				l1_p=(new Struct_Pair(Module_correl.f_mizAdjustTyp(l0_env, aa), Module_correl.f_mizAdjustTyp(l0_env, ab)));
			} else {
				l1_p=(new Struct_Pair((new Struct_Pair((aa).f_modNr, (aa).f_modArgs)), (new Struct_Pair((ab).f_modNr, (ab).f_modArgs))));
			}
			if ((((int)(((Struct_Pair)(l1_p).f_first)).f_first)==((int)(((Struct_Pair)(l1_p).f_second)).f_first))) {
				return Module_unifier.f_mizUniTrmList(au, ((Struct)(((Struct_Pair)(l1_p).f_first)).f_second), ((Struct)(((Struct_Pair)(l1_p).f_second)).f_second));
			} else {
				return Module_unifier.f_mizUniBottom();
			}
		}
	}
	public static final Struct_MizPreInst f_mizUniTop() {
		return Module_prechecker.f_mizPreInstTop();
	}
	public static final Struct_MizPreInst f_mizUniTrm(Struct_MizUnifier au, Struct aa, Struct ab) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final int l1_sa = Module_correl.f_mizTrmSort(aa);
		if ((l1_sa==Module_lexicon.g_ikTrmFreeVar)) {
			final int l2_lEq = Module_unifier.f_mizEqClassNr(au, ab);
			if ((l2_lEq>0)) {
				return Module_unifier.f_mizUniEqClassAndFreeVar(au, l2_lEq, Module_unifier.f_mizUVarNr(aa));
			} else {
				return Module_unifier.f_mizUniBottom();
			}
		} else {
			if ((l1_sa==Module_lexicon.g_ikTrmBound)) {
				if (((Module_correl.f_mizTrmSort(ab)==Module_lexicon.g_ikTrmBound)&&((Module_unifier.f_mizUVarNr(aa)-(au).f_unifBase)==Module_unifier.f_mizUVarNr(ab)))) {
					return Module_unifier.f_mizUniTop();
				} else {
					return Module_unifier.f_mizUniBottom();
				}
			} else {
				if ((l1_sa==Module_lexicon.g_ikTrmFunctor)) {
					final Struct_MizPreInst l3_res = Module_unifier.f_mizUniFunc(au, aa, ab);
					final int l4_bClass = Module_unifier.f_mizEqClassNr(au, ab);
					if ((l4_bClass>0)) {
						final Struct_Vector l7_$6 = Module_unifier.f_mizUEqListAt(au, Module_unifier.g_expUFunctor, l4_bClass);
						final Struct_MizUnifier l9_u = au;
						final Struct l10_a = aa;
						final Func1<Object,Struct> l8_$5 = (Func1<Object, Struct>)(Struct am) -> {
							final Struct_MizPreInst l9_l = Module_unifier.f_mizUniFunc(l9_u, l10_a, am);
							return Module_unifier.f_mizUniUnion(l9_u, l3_res, l9_l);
						};
						Module_vector.f_iterVector(l7_$6, ((Func1<Object,Object>)(Func1)l8_$5));
					} else {
					}
					return l3_res;
				} else {
					if (((((l1_sa==Module_lexicon.g_ikTrmSchFunc)||(l1_sa==Module_lexicon.g_ikTrmPrivFunc))||(l1_sa==Module_lexicon.g_ikTrmSelector))||(l1_sa==Module_lexicon.g_ikTrmAggreg))) {
						int l11_kind;
						if ((l1_sa==Module_lexicon.g_ikTrmSchFunc)) {
							l11_kind=Module_unifier.g_expUSchFunc;
						} else {
							if ((l1_sa==Module_lexicon.g_ikTrmPrivFunc)) {
								l11_kind=Module_unifier.g_expUPrivFunc;
							} else {
								if ((l1_sa==Module_lexicon.g_ikTrmSelector)) {
									l11_kind=Module_unifier.g_expUSelector;
								} else {
									l11_kind=Module_unifier.g_expUAggreg;
								}
							}
						}
						Struct_MizPreInst l12_res;
						if (((Module_correl.f_mizTrmSort(ab)==l1_sa)&&(Module_unifier.f_mizUFuncNr(ab)==Module_unifier.f_mizUFuncNr(aa)))) {
							l12_res=Module_unifier.f_mizUniTrmList(au, Module_unifier.f_mizUArgs(aa), Module_unifier.f_mizUArgs(ab));
						} else {
							l12_res=Module_unifier.f_mizUniBottom();
						}
						final int l13_bClass = Module_unifier.f_mizEqClassNr(au, ab);
						if ((l13_bClass>0)) {
							final Struct_Vector l16_$15 = Module_unifier.f_mizUEqListAt(au, l11_kind, l13_bClass);
							final Struct l18_a = aa;
							final Struct_MizUnifier l19_u = au;
							final Struct_MizPreInst l20_res = l12_res;
							final Func1<Object,Struct> l17_$14 = (Func1<Object, Struct>)(Struct am) -> {
								if ((Module_unifier.f_mizUFuncNr(am)==Module_unifier.f_mizUFuncNr(l18_a))) {
									final Struct_MizPreInst l18_l = Module_unifier.f_mizUniTrmList(l19_u, Module_unifier.f_mizUArgs(l18_a), Module_unifier.f_mizUArgs(am));
									return Module_unifier.f_mizUniUnion(l19_u, l20_res, l18_l);
								} else {
									return null;
								}
							};
							Module_vector.f_iterVector(l16_$15, ((Func1<Object,Object>)(Func1)l17_$14));
						} else {
						}
						return l12_res;
					} else {
						if ((l1_sa==Module_lexicon.g_ikTrmFraenkel)) {
							Struct_MizPreInst l21_res;
							if ((Module_correl.f_mizTrmSort(ab)==Module_lexicon.g_ikTrmFraenkel)) {
								l21_res=Module_unifier.f_mizUniFraenkelTrm(au, aa, ab);
							} else {
								l21_res=Module_unifier.f_mizUniBottom();
							}
							final int l22_bClass = Module_unifier.f_mizEqClassNr(au, ab);
							if ((l22_bClass>0)) {
								final Struct_Vector l25_$24 = Module_unifier.f_mizUEqListAt(au, Module_unifier.g_expUFraenkel, l22_bClass);
								final Struct_MizUnifier l27_u = au;
								final Struct l28_a = aa;
								final Struct_MizPreInst l29_res = l21_res;
								final Func1<Object,Struct> l26_$23 = (Func1<Object, Struct>)(Struct am) -> {
									final int l27_lUB = (l27_u).f_unifBase;
									((Field_unifBase)l27_u).set_unifBase((l0_env).f_boundVarNbr);
									final Struct_MizPreInst l28_l = Module_unifier.f_mizUniFraenkelTrm(l27_u, l28_a, am);
									Module_unifier.f_mizUniUnion(l27_u, l29_res, l28_l);
									((Field_unifBase)l27_u).set_unifBase(l27_lUB);
									return null;
								};
								Module_vector.f_iterVector(l25_$24, ((Func1<Object,Object>)(Func1)l26_$23));
							} else {
							}
							return l21_res;
						} else {
							if ((l1_sa==Module_lexicon.g_ikTrmChoice)) {
								Struct_MizChoiceTrm l30_ca;
								Struct l31__tmp = aa;
								switch (l31__tmp.getTypeId()) {
								case 21/*MizChoiceTrm*/: {
									final Struct_MizChoiceTrm l32__tmp = (Struct_MizChoiceTrm)l31__tmp;
									final Struct_MizTyp l33___ = (l32__tmp).f_choiceTyp;
									final int l34___ = (l32__tmp).f_pattNr;
									final int l35___ = (l32__tmp).f_trmInfo;
									l30_ca=l32__tmp;
									break;
								}
								default: {
									l30_ca=(new Struct_MizChoiceTrm(Module_correl.f_mizNewIncorTyp(), 0, 0));
									break;
								}
								}
								Struct_MizPreInst l36_res;
								if ((Module_correl.f_mizTrmSort(ab)==Module_lexicon.g_ikTrmChoice)) {
									Struct_MizChoiceTrm l37_cb;
									Struct l38__tmp = ab;
									switch (l38__tmp.getTypeId()) {
									case 21/*MizChoiceTrm*/: {
										final Struct_MizChoiceTrm l39__tmp = (Struct_MizChoiceTrm)l38__tmp;
										final Struct_MizTyp l40___ = (l39__tmp).f_choiceTyp;
										final int l41___ = (l39__tmp).f_pattNr;
										final int l42___ = (l39__tmp).f_trmInfo;
										l37_cb=l39__tmp;
										break;
									}
									default: {
										l37_cb=(new Struct_MizChoiceTrm(Module_correl.f_mizNewIncorTyp(), 0, 0));
										break;
									}
									}
									l36_res=Module_unifier.f_mizUniTyp(au, (l30_ca).f_choiceTyp, (l37_cb).f_choiceTyp);
								} else {
									l36_res=Module_unifier.f_mizUniBottom();
								}
								final int l43_bClass = Module_unifier.f_mizEqClassNr(au, ab);
								if ((l43_bClass>0)) {
									final Struct_Vector l46_$45 = Module_unifier.f_mizUEqListAt(au, Module_unifier.g_expUChoice, l43_bClass);
									final Struct_MizUnifier l48_u = au;
									final Struct_MizChoiceTrm l49_ca = l30_ca;
									final Struct_MizPreInst l50_res = l36_res;
									final Func1<Object,Struct> l47_$44 = (Func1<Object, Struct>)(Struct am) -> {
										Struct_MizChoiceTrm l48_cm;
										Struct l49__tmp = am;
										switch (l49__tmp.getTypeId()) {
										case 21/*MizChoiceTrm*/: {
											final Struct_MizChoiceTrm l50__tmp = (Struct_MizChoiceTrm)l49__tmp;
											final Struct_MizTyp l51___ = (l50__tmp).f_choiceTyp;
											final int l52___ = (l50__tmp).f_pattNr;
											final int l53___ = (l50__tmp).f_trmInfo;
											l48_cm=l50__tmp;
											break;
										}
										default: {
											l48_cm=(new Struct_MizChoiceTrm(Module_correl.f_mizNewIncorTyp(), 0, 0));
											break;
										}
										}
										final Struct_MizPreInst l54_l = Module_unifier.f_mizUniTyp(l48_u, (l49_ca).f_choiceTyp, (l48_cm).f_choiceTyp);
										return Module_unifier.f_mizUniUnion(l48_u, l50_res, l54_l);
									};
									Module_vector.f_iterVector(l46_$45, ((Func1<Object,Object>)(Func1)l47_$44));
								} else {
								}
								return l36_res;
							} else {
								if ((l1_sa==Module_lexicon.g_ikTrmEqConst)) {
									if ((Module_unifier.f_mizUVarNr(aa)==Module_unifier.f_mizEqClassNr(au, ab))) {
										return Module_unifier.f_mizUniTop();
									} else {
										return Module_unifier.f_mizUniBottom();
									}
								} else {
									if ((((l1_sa==Module_lexicon.g_ikTrmNumeral)||(l1_sa==Module_lexicon.g_ikTrmConstant))||(l1_sa==Module_lexicon.g_ikTrmInfConst))) {
										if (((Module_unifier.f_mizEqClassNr(au, aa)!=0)&&(Module_unifier.f_mizEqClassNr(au, aa)==Module_unifier.f_mizEqClassNr(au, ab)))) {
											return Module_unifier.f_mizUniTop();
										} else {
											return Module_unifier.f_mizUniBottom();
										}
									} else {
										Module_errhan.f_mizRunTimeError(2035);
										return Module_unifier.f_mizUniBottom();
									}
								}
							}
						}
					}
				}
			}
		}
	}
	public static final Struct_MizPreInst f_mizUniTrmList(Struct_MizUnifier au, Struct aa, Struct ab) {
		Struct l0__tmp = aa;
		switch (l0__tmp.getTypeId()) {
		case 64/*MizTrmNil*/: {
			Struct l2__tmp = ab;
			switch (l2__tmp.getTypeId()) {
			case 64/*MizTrmNil*/: {
				return Module_unifier.f_mizUniTop();
			}
			case 63/*MizTrmElem*/: {
				final Struct_MizTrmElem l3__tmp = (Struct_MizTrmElem)l2__tmp;
				final Struct l4___ = (l3__tmp).f_trm;
				final Struct l5___ = (l3__tmp).f_next;
				return Module_unifier.f_mizUniBottom();
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
			}
		}
		case 63/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l6_ta = l1__tmp.f_trm;
			final Struct l7_na = l1__tmp.f_next;
			final Struct l8_ta = (l1__tmp).f_trm;
			final Struct l9_na = (l1__tmp).f_next;
			Struct l10__tmp = ab;
			switch (l10__tmp.getTypeId()) {
			case 64/*MizTrmNil*/: {
				return Module_unifier.f_mizUniBottom();
			}
			case 63/*MizTrmElem*/: {
				final Struct_MizTrmElem l11__tmp = (Struct_MizTrmElem)l10__tmp;
				final Struct l12_tb = l11__tmp.f_trm;
				final Struct l13_nb = l11__tmp.f_next;
				final Struct l14_tb = (l11__tmp).f_trm;
				final Struct l15_nb = (l11__tmp).f_next;
				final Struct_MizPreInst l16_res = Module_unifier.f_mizUniTrm(au, l8_ta, l14_tb);
				final Reference<Struct_Pair> l17_cont = ((Reference<Struct_Pair>)(new Reference((new Struct_Pair(l9_na, l15_nb)))));
				final Reference<Boolean> l18_go = ((Reference<Boolean>)(new Reference(true)));
				final Func0<Boolean> l21_$19 = (Func0<Boolean>)() -> {
					return ((Boolean)(((boolean)l18_go.value)&&((Module_vector.f_sizeVector((l16_res).f_evals)+Module_math.f_b2i((l16_res).f_top))>0)));
				};
				final Struct_MizUnifier l23_u = au;
				final Func0<Object> l22_$20 = (Func0<Object>)() -> {
					final Struct_Pair l23_pr = l17_cont.value;
					final Struct l24_gsymswitch12 = ((Struct)(l23_pr).f_first);
					Struct l25__tmp = l24_gsymswitch12;
					switch (l25__tmp.getTypeId()) {
					case 64/*MizTrmNil*/: {
						l18_go.value = ((Boolean)false);
						return null;
					}
					case 63/*MizTrmElem*/: {
						final Struct_MizTrmElem l26__tmp = (Struct_MizTrmElem)l25__tmp;
						final Struct l27_ta2 = l26__tmp.f_trm;
						final Struct l28_na2 = l26__tmp.f_next;
						final Struct l29_ta2 = (l26__tmp).f_trm;
						final Struct l30_na2 = (l26__tmp).f_next;
						final Struct l31_gsymswitch13 = ((Struct)(l23_pr).f_second);
						Struct l32__tmp = l31_gsymswitch13;
						switch (l32__tmp.getTypeId()) {
						case 64/*MizTrmNil*/: {
							Module_vector.f_clearVector((l16_res).f_evals);
							((Field_top)l16_res).set_top(false);
							l18_go.value = ((Boolean)false);
							return null;
						}
						case 63/*MizTrmElem*/: {
							final Struct_MizTrmElem l33__tmp = (Struct_MizTrmElem)l32__tmp;
							final Struct l34_tb2 = l33__tmp.f_trm;
							final Struct l35_nb2 = l33__tmp.f_next;
							final Struct l36_tb2 = (l33__tmp).f_trm;
							final Struct l37_nb2 = (l33__tmp).f_next;
							final Struct_MizPreInst l38_l1 = Module_unifier.f_mizUniTrm(l23_u, l29_ta2, l36_tb2);
							Module_unifier.f_mizUniJoin(l23_u, l16_res, l38_l1);
							l17_cont.value = (new Struct_Pair(l30_na2, l37_nb2));
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l32__tmp.getTypeName());
						}
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l25__tmp.getTypeName());
					}
				};
				Module_loop.f_while(l21_$19, l22_$20);
				return l16_res;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct_MizPreInst f_mizUniTyp(Struct_MizUnifier au, Struct_MizTyp aa, Struct_MizTyp ab) {
		final Struct_MizPreInst l0_res = Module_unifier.f_mizUniBottom();
		if (((aa).f_sort==(ab).f_sort)) {
			final Struct_MizPreInst l1_l = Module_unifier.f_mizUniInclClusters(au, (aa).f_lower, (ab).f_upper, true);
			if ((Module_prechecker.f_mizPreInstCount(l1_l)!=0)) {
				final Struct_MizPreInst l2_l1 = Module_unifier.f_mizUniInclClusters(au, (ab).f_lower, (aa).f_upper, false);
				Module_unifier.f_mizUniJoin(au, l1_l, l2_l1);
				if ((Module_prechecker.f_mizPreInstCount(l1_l)!=0)) {
					final Struct_MizPreInst l3_l2 = Module_unifier.f_mizUniRadices(au, aa, ab);
					Module_unifier.f_mizUniJoin(au, l1_l, l3_l2);
					Module_unifier.f_mizUniUnion(au, l0_res, l1_l);
				} else {
				}
			} else {
			}
		} else {
		}
		return l0_res;
	}
	public static final Object f_mizUniUnion(Struct_MizUnifier au, Struct_MizPreInst ap, Struct_MizPreInst aq) {
		return Module_prechecker.f_mizPreInstUnionWith((au).f_pre, ap, aq);
	}
	public static final boolean f_mizUnifiable(Struct_MizUnifier au, Struct_Vector auniv) {
		final Struct_MizPre l0_pre = (au).f_pre;
		Module_vector.f_clearVector((au).f_freeVarType);
		((Field_unifBase)au).set_unifBase(0);
		((Field_freeVarUnifs)au).set_freeVarUnifs(Module_tree.f_makeTree());
		Module_vector.f_clearVector((l0_pre).f_basic);
		final Struct_MizPreInst l1_all_0 = Module_unifier.f_mizCreateClauses(au, auniv);
		final int l2_n = Module_prechecker.f_mizPreInstCount(l1_all_0);
		if ((((l2_n>=2)&&(l2_n<=4))&&!(l1_all_0).f_top)) {
			final Struct_Vector l3_compl = Module_unifier.f_mizCollectComplementaryLiterals(au, l1_all_0);
			if ((Module_vector.f_sizeVector(l3_compl)==1)) {
				return Module_unifier.f_mizResolventVerify(au, l1_all_0, l3_compl);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public static final int f_mizUnification(Struct_MizChecker achk, Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (achk).f_env;
		final Struct_MizUnifier l1_u = Module_unifier.f_mizInitUnifier(achk, aeq_0);
		((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
		final Struct_Vector l2_univ = Module_vector.f_makeVector(4);
		final Struct_Vector l5_$4 = (aeq_0).f_posBas;
		final Func1<Object,Struct> l6_$3 = (Func1<Object, Struct>)(Struct apf) -> {
			if ((Module_correl.f_mizFrmSort(apf)==Module_lexicon.g_ikFrmUniv)) {
				return Module_vector.f_pushVector(l2_univ, apf);
			} else {
				return null;
			}
		};
		Module_vector.f_iterVector(l5_$4, ((Func1<Object,Object>)(Func1)l6_$3));
		if ((achk).f_debug) {
			Module_runtime.f_println(((((("UNIF: universals="+Module_string.f_i2s(Module_vector.f_sizeVector(l2_univ)))+" posBas=")+Module_string.f_i2s(Module_vector.f_sizeVector((aeq_0).f_posBas)))+" negBas=")+Module_string.f_i2s(Module_vector.f_sizeVector((aeq_0).f_negBas))));
			final Func1<Object,Struct> l8_$7 = (Func1<Object, Struct>)(Struct auf) -> {
				return Module_runtime.f_println(("  UNIV "+Module_print.f_mizFrm2s(auf)));
			};
			Module_vector.f_iterVector(l2_univ, ((Func1<Object,Object>)(Func1)l8_$7));
		} else {
		}
		final Reference<Integer> l9_contr = ((Reference<Integer>)(new Reference(0)));
		final Reference<Integer> l10_i = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l13_$11 = (Func0<Boolean>)() -> {
			return ((Boolean)((((int)l9_contr.value)==0)&&(((int)l10_i.value)<Module_vector.f_sizeVector(l2_univ))));
		};
		final Func0<Object> l14_$12 = (Func0<Object>)() -> {
			if (Module_unifier.f_mizVerify(l1_u, ((Struct)Module_vector.f_getVectorUnsafe(l2_univ, ((int)l10_i.value))))) {
				l9_contr.value = ((Integer)45);
			} else {
			}
			l10_i.value = ((Integer)(((int)l10_i.value)+1));
			return null;
		};
		Module_loop.f_while(l13_$11, l14_$12);
		if ((((int)l9_contr.value)==0)) {
			final Reference<Integer> l15_ii = ((Reference<Integer>)(new Reference(0)));
			final Func0<Boolean> l18_$16 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l9_contr.value)==0)&&(((int)l15_ii.value)<Module_vector.f_sizeVector(l2_univ))));
			};
			final Struct_MizChecker l20_chk = achk;
			final Func0<Object> l19_$17 = (Func0<Object>)() -> {
				final Reference<Integer> l20_jj = ((Reference<Integer>)(new Reference(l15_ii.value)));
				final Func0<Boolean> l23_$21 = (Func0<Boolean>)() -> {
					return ((Boolean)((((int)l9_contr.value)==0)&&(((int)l20_jj.value)<Module_vector.f_sizeVector(l2_univ))));
				};
				final Func0<Object> l24_$22 = (Func0<Object>)() -> {
					final Struct_Vector l25_pair = Module_vector.f_makeVector(2);
					Module_vector.f_pushVector(l25_pair, Module_vector.f_getVectorUnsafe(l2_univ, ((int)l15_ii.value)));
					if ((((int)l20_jj.value)!=((int)l15_ii.value))) {
						Module_vector.f_pushVector(l25_pair, Module_vector.f_getVectorUnsafe(l2_univ, ((int)l20_jj.value)));
					} else {
					}
					if ((l20_chk).f_debug) {
						final Struct_Pair l26_all0 = Module_unifier.f_mizCreateClausesDbg(l1_u, l25_pair);
						Module_runtime.f_println(((((((("  PAIR "+Module_string.f_i2s(((int)l15_ii.value)))+",")+Module_string.f_i2s(((int)l20_jj.value)))+" clauses=")+Module_string.f_i2s(Module_prechecker.f_mizPreInstCount(((Struct_MizPreInst)(l26_all0).f_first))))+" compl=")+Module_string.f_i2s(((int)(l26_all0).f_second))));
					} else {
					}
					if (Module_unifier.f_mizUnifiable(l1_u, l25_pair)) {
						l9_contr.value = ((Integer)47);
					} else {
					}
					l20_jj.value = ((Integer)(((int)l20_jj.value)+1));
					return null;
				};
				Module_loop.f_while(l23_$21, l24_$22);
				l15_ii.value = ((Integer)(((int)l15_ii.value)+1));
				return null;
			};
			Module_loop.f_while(l18_$16, l19_$17);
		} else {
		}
		if ((((int)l9_contr.value)==0)) {
			final Struct_Vector l23_$22 = (aeq_0).f_posBas;
			final Func1<Object,Struct> l24_$21 = (Func1<Object, Struct>)(Struct apf) -> {
				if (((((int)l9_contr.value)==0)&&(Module_correl.f_mizFrmSort(apf)==Module_lexicon.g_ikFrmPred))) {
					final Struct_MizPredFrm l25_pfP = Module_unifier.f_mizUFrAsPred(apf);
					final Struct_Pair l26_pa = Module_correl.f_mizAdjustFrm(l0_env, l25_pfP);
					if ((((int)(l26_pa).f_first)==Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqBelongsTo))) {
						final Struct l27_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l26_pa).f_second), 0);
						final int l28_bClass = Module_unifier.f_mizEqClassNr(l1_u, Module_correl.f_mizTrmListNth(((Struct)(l26_pa).f_second), 1));
						if ((l28_bClass>0)) {
							final Struct_Vector l31_$30 = Module_unifier.f_mizUEqListAt(l1_u, Module_unifier.g_expUFraenkel, l28_bClass);
							final Func1<Object,Struct> l32_$29 = (Func1<Object, Struct>)(Struct am) -> {
								if (((((int)l9_contr.value)==0)&&(Module_correl.f_mizTrmSort(am)==Module_lexicon.g_ikTrmFraenkel))) {
									final Struct l33_lFrm1 = Module_unifier.f_mizFraenkelFrm(l0_env, l27_lLeft, am);
									((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
									final Struct l34_lFrm = Module_unifier.f_mizNewNegDis(Module_prechecker.f_mizDistributeQuantifiers((l1_u).f_pre, l33_lFrm1));
									if (Module_unifier.f_mizVerify(l1_u, l34_lFrm)) {
										l9_contr.value = ((Integer)48);
										return null;
									} else {
										return null;
									}
								} else {
									return null;
								}
							};
							return Module_vector.f_iterVector(l31_$30, ((Func1<Object,Object>)(Func1)l32_$29));
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
			Module_vector.f_iterVector(l23_$22, ((Func1<Object,Object>)(Func1)l24_$21));
		} else {
		}
		if ((((int)l9_contr.value)==0)) {
			final Struct_Vector l27_$26 = (aeq_0).f_negBas;
			final Func1<Object,Struct> l28_$25 = (Func1<Object, Struct>)(Struct anf) -> {
				if (((((int)l9_contr.value)==0)&&(Module_correl.f_mizFrmSort(anf)==Module_lexicon.g_ikFrmPred))) {
					final Struct_MizPredFrm l29_nfP = Module_unifier.f_mizUFrAsPred(anf);
					final Struct_Pair l30_pa = Module_correl.f_mizAdjustFrm(l0_env, l29_nfP);
					if ((((int)(l30_pa).f_first)==Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqBelongsTo))) {
						final Struct l31_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l30_pa).f_second), 0);
						final int l32_bClass = Module_unifier.f_mizEqClassNr(l1_u, Module_correl.f_mizTrmListNth(((Struct)(l30_pa).f_second), 1));
						if ((l32_bClass>0)) {
							final Struct_Vector l35_$34 = Module_unifier.f_mizUEqListAt(l1_u, Module_unifier.g_expUFraenkel, l32_bClass);
							final Func1<Object,Struct> l36_$33 = (Func1<Object, Struct>)(Struct am) -> {
								if (((((int)l9_contr.value)==0)&&(Module_correl.f_mizTrmSort(am)==Module_lexicon.g_ikTrmFraenkel))) {
									((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
									final Struct l37_lFrm = Module_prechecker.f_mizDistributeQuantifiers((l1_u).f_pre, Module_unifier.f_mizFraenkelFrm(l0_env, l31_lLeft, am));
									if (Module_unifier.f_mizVerify(l1_u, l37_lFrm)) {
										l9_contr.value = ((Integer)49);
										return null;
									} else {
										return null;
									}
								} else {
									return null;
								}
							};
							return Module_vector.f_iterVector(l35_$34, ((Func1<Object,Object>)(Func1)l36_$33));
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
			Module_vector.f_iterVector(l27_$26, ((Func1<Object,Object>)(Func1)l28_$25));
		} else {
		}
		return ((int)l9_contr.value);
	}
	public static final Struct_MizPreInst f_mizUnifyAttrs(Struct_MizUnifier au, Struct_MizAttr aa, Struct_MizAttr ab) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_Pair l1_pa = Module_correl.f_mizAdjustAttr(l0_env, aa);
		final Struct_Pair l2_pb = Module_correl.f_mizAdjustAttr(l0_env, ab);
		if (((((int)(l1_pa).f_first)==((int)(l2_pb).f_first))&&((aa).f_neg==(ab).f_neg))) {
			return Module_unifier.f_mizUnifyTrmList(au, ((Struct)(l1_pa).f_second), ((Struct)(l2_pb).f_second));
		} else {
			return Module_unifier.f_mizUniBottom();
		}
	}
	public static final Struct_MizPreInst f_mizUnifyBasicFrm(Struct_MizUnifier au, Struct aa, Struct ab) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final int l1_sa = Module_correl.f_mizFrmSort(aa);
		if ((l1_sa!=Module_correl.f_mizFrmSort(ab))) {
			return Module_unifier.f_mizUniBottom();
		} else {
			Struct l2__tmp = aa;
			switch (l2__tmp.getTypeId()) {
			case 55/*MizPredFrm*/: {
				final Struct_MizPredFrm l3__tmp = (Struct_MizPredFrm)l2__tmp;
				final int l4_srt = l3__tmp.f_sort;
				final int l5_predNr = l3__tmp.f_predNr;
				final Struct l6_args = l3__tmp.f_args;
				final int l7_srt = (l3__tmp).f_sort;
				final int l8_predNr = (l3__tmp).f_predNr;
				final Struct l9_args = (l3__tmp).f_args;
				final int l10___ = (l3__tmp).f_pattNr;
				Struct_MizPredFrm l11_pa2;
				Struct l12__tmp = l3__tmp;
				switch (l12__tmp.getTypeId()) {
				case 55/*MizPredFrm*/: {
					final Struct_MizPredFrm l13__tmp = (Struct_MizPredFrm)l12__tmp;
					final int l14___ = (l13__tmp).f_sort;
					final int l15___ = (l13__tmp).f_predNr;
					final Struct l16___ = (l13__tmp).f_args;
					final int l17___ = (l13__tmp).f_pattNr;
					l11_pa2=l13__tmp;
					break;
				}
				default: {
					l11_pa2=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
					break;
				}
				}
				Struct_MizPredFrm l18_pb;
				Struct l19__tmp = ab;
				switch (l19__tmp.getTypeId()) {
				case 55/*MizPredFrm*/: {
					final Struct_MizPredFrm l20__tmp = (Struct_MizPredFrm)l19__tmp;
					final int l21___ = (l20__tmp).f_sort;
					final int l22___ = (l20__tmp).f_predNr;
					final Struct l23___ = (l20__tmp).f_args;
					final int l24___ = (l20__tmp).f_pattNr;
					l18_pb=l20__tmp;
					break;
				}
				default: {
					l18_pb=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
					break;
				}
				}
				if ((l7_srt==Module_lexicon.g_ikFrmPred)) {
					final Struct_Pair l25_qa = Module_correl.f_mizAdjustFrm(l0_env, l11_pa2);
					final Struct_Pair l26_qb = Module_correl.f_mizAdjustFrm(l0_env, l18_pb);
					if ((((int)(l25_qa).f_first)==((int)(l26_qb).f_first))) {
						return Module_unifier.f_mizUnifyTrmList(au, ((Struct)(l25_qa).f_second), ((Struct)(l26_qb).f_second));
					} else {
						return Module_unifier.f_mizUniBottom();
					}
				} else {
					if ((l7_srt==Module_lexicon.g_ikFrmAttr)) {
						final Struct_Pair l27_qa = Module_correl.f_mizAdjustAttrFrm(l0_env, l11_pa2);
						final Struct_Pair l28_qb = Module_correl.f_mizAdjustAttrFrm(l0_env, l18_pb);
						if ((((int)(l27_qa).f_first)==((int)(l28_qb).f_first))) {
							return Module_unifier.f_mizUnifyTrmList(au, ((Struct)(l27_qa).f_second), ((Struct)(l28_qb).f_second));
						} else {
							return Module_unifier.f_mizUniBottom();
						}
					} else {
						if ((l8_predNr==(l18_pb).f_predNr)) {
							return Module_unifier.f_mizUnifyTrmList(au, l9_args, (l18_pb).f_args);
						} else {
							return Module_unifier.f_mizUniBottom();
						}
					}
				}
			}
			case 48/*MizLocPredFrm*/: {
				final Struct_MizLocPredFrm l3__tmp = (Struct_MizLocPredFrm)l2__tmp;
				final int l29_predNr = l3__tmp.f_predNr;
				final Struct l30_args = l3__tmp.f_args;
				final int l31_predNr = (l3__tmp).f_predNr;
				final Struct l32_args = (l3__tmp).f_args;
				final Struct l33___ = (l3__tmp).f_predExp;
				final int l34___ = (l3__tmp).f_pattNr;
				Struct_MizLocPredFrm l35_lb;
				Struct l36__tmp = ab;
				switch (l36__tmp.getTypeId()) {
				case 48/*MizLocPredFrm*/: {
					final Struct_MizLocPredFrm l37__tmp = (Struct_MizLocPredFrm)l36__tmp;
					final int l38___ = (l37__tmp).f_predNr;
					final Struct l39___ = (l37__tmp).f_args;
					final Struct l40___ = (l37__tmp).f_predExp;
					final int l41___ = (l37__tmp).f_pattNr;
					l35_lb=l37__tmp;
					break;
				}
				default: {
					l35_lb=(new Struct_MizLocPredFrm(0, ((Struct)SingletonStructs.str_MizTrmNil), (new Struct_MizUniqFrm(Module_lexicon.g_ikError)), 0));
					break;
				}
				}
				if ((l31_predNr==(l35_lb).f_predNr)) {
					return Module_unifier.f_mizUnifyTrmList(au, l32_args, (l35_lb).f_args);
				} else {
					return Module_unifier.f_mizUniBottom();
				}
			}
			case 69/*MizUnivFrm*/: {
				final Struct_MizUnivFrm l3__tmp = (Struct_MizUnivFrm)l2__tmp;
				final int l42___ = (l3__tmp).f_sort;
				final int l43___ = (l3__tmp).f_varId;
				final Struct_MizTyp l44___ = (l3__tmp).f_quantified;
				final Struct l45___ = (l3__tmp).f_scope;
				final int l46___ = (l3__tmp).f_pattNr;
				return Module_unifier.f_mizUniBottom();
			}
			case 60/*MizQualFrm*/: {
				final Struct_MizQualFrm l3__tmp = (Struct_MizQualFrm)l2__tmp;
				final Struct l47_trm = l3__tmp.f_trm;
				final Struct_MizTyp l48_typ = l3__tmp.f_typ;
				final Struct l49_trm = (l3__tmp).f_trm;
				final Struct_MizTyp l50_typ = (l3__tmp).f_typ;
				final int l51___ = (l3__tmp).f_pattNr;
				Struct_MizQualFrm l52_qb;
				Struct l53__tmp = ab;
				switch (l53__tmp.getTypeId()) {
				case 60/*MizQualFrm*/: {
					final Struct_MizQualFrm l54__tmp = (Struct_MizQualFrm)l53__tmp;
					final Struct l55___ = (l54__tmp).f_trm;
					final Struct_MizTyp l56___ = (l54__tmp).f_typ;
					final int l57___ = (l54__tmp).f_pattNr;
					l52_qb=l54__tmp;
					break;
				}
				default: {
					l52_qb=(new Struct_MizQualFrm((new Struct_MizBaseTrm(Module_lexicon.g_ikError, 0)), Module_correl.f_mizNewIncorTyp(), 0));
					break;
				}
				}
				final Struct_MizPreInst l58_res = Module_unifier.f_mizUnifyTrmsWithConsts(au, l49_trm, (l52_qb).f_trm);
				if ((Module_prechecker.f_mizPreInstCount(l58_res)!=0)) {
					final Struct_MizPreInst l59_l = Module_unifier.f_mizUnifyTypsWithConsts(au, l50_typ, (l52_qb).f_typ);
					Module_unifier.f_mizUniJoin(au, l58_res, l59_l);
				} else {
				}
				return l58_res;
			}
			default: {
				Module_errhan.f_mizRunTimeError(2734);
				return Module_unifier.f_mizUniBottom();
			}
			}
		}
	}
	public static final Struct_MizPreInst f_mizUnifyInclClusters(Struct_MizUnifier au, Struct_MizAttrColl ac1, Struct_MizAttrColl ac2) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final int l1_n1 = Module_vector.f_sizeVector((ac1).f_items);
		final int l2_n2 = Module_vector.f_sizeVector((ac2).f_items);
		if ((l2_n2>l1_n1)) {
			return Module_unifier.f_mizUniBottom();
		} else {
			if ((l1_n1==0)) {
				return Module_unifier.f_mizUniTop();
			} else {
				final Reference<Struct_MizPreInst> l3_res = ((Reference<Struct_MizPreInst>)(new Reference(Module_unifier.f_mizUniBottom())));
				final Reference<Integer> l4_j = ((Reference<Integer>)(new Reference(0)));
				final Reference<Integer> l5_i = ((Reference<Integer>)(new Reference(0)));
				final Reference<Boolean> l6_dead = ((Reference<Boolean>)(new Reference(false)));
				final Reference<Boolean> l7_first = ((Reference<Boolean>)(new Reference(true)));
				final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
					return ((Boolean)(!((boolean)l6_dead.value)&&(((int)l5_i.value)<l1_n1)));
				};
				final Struct_MizAttrColl l12_c1 = ac1;
				final Struct_MizAttrColl l13_c2 = ac2;
				final Struct_MizUnifier l14_u = au;
				final Func0<Object> l11_$9 = (Func0<Object>)() -> {
					final Struct_MizAttr l12_a = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l12_c1).f_items, ((int)l5_i.value)));
					final int l13_lAttrNr = Module_correl.f_mizAdjustedAttrNr(l0_env, l12_a);
					final Func0<Boolean> l16_$14 = (Func0<Boolean>)() -> {
						return ((Boolean)((((int)l4_j.value)<l2_n2)&&(Module_correl.f_mizAdjustedAttrNr(l0_env, ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l13_c2).f_items, ((int)l4_j.value))))<l13_lAttrNr)));
					};
					final Func0<Object> l17_$15 = (Func0<Object>)() -> {
						l4_j.value = ((Integer)(((int)l4_j.value)+1));
						return null;
					};
					Module_loop.f_while(l16_$14, l17_$15);
					if ((((int)l4_j.value)==l2_n2)) {
						l6_dead.value = ((Boolean)true);
					} else {
						final Struct_MizPreInst l18_l = Module_unifier.f_mizUnifyAttrs(l14_u, l12_a, ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l13_c2).f_items, ((int)l4_j.value))));
						if (((boolean)l7_first.value)) {
							l3_res.value = l18_l;
							l7_first.value = ((Boolean)false);
						} else {
							Module_unifier.f_mizUniJoin(l14_u, l3_res.value, l18_l);
						}
						if ((Module_prechecker.f_mizPreInstCount(l3_res.value)==0)) {
							l6_dead.value = ((Boolean)true);
						} else {
						}
					}
					l5_i.value = ((Integer)(((int)l5_i.value)+1));
					return null;
				};
				Module_loop.f_while(l10_$8, l11_$9);
				if (((boolean)l6_dead.value)) {
					return Module_unifier.f_mizUniBottom();
				} else {
					return l3_res.value;
				}
			}
		}
	}
	public static final Struct_MizPreInst f_mizUnifyReflexive(Struct_MizUnifier au, Struct af) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		Struct_MizPredFrm l1_pf;
		Struct l2__tmp = af;
		switch (l2__tmp.getTypeId()) {
		case 55/*MizPredFrm*/: {
			final Struct_MizPredFrm l3__tmp = (Struct_MizPredFrm)l2__tmp;
			final int l4___ = (l3__tmp).f_sort;
			final int l5___ = (l3__tmp).f_predNr;
			final Struct l6___ = (l3__tmp).f_args;
			final int l7___ = (l3__tmp).f_pattNr;
			l1_pf=l3__tmp;
			break;
		}
		default: {
			l1_pf=(new Struct_MizPredFrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0));
			break;
		}
		}
		final Struct_MizConstr l8_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, (l1_pf).f_predNr);
		final Struct l9_lLeft = Module_correl.f_mizTrmListNth((l1_pf).f_args, ((l8_c).f_firstArg-1));
		final Struct l10_lRight = Module_correl.f_mizTrmListNth((l1_pf).f_args, ((l8_c).f_secondArg-1));
		final Struct_MizPreInst l11_res = Module_unifier.f_mizUniBottom();
		final int l14_$13 = (au).f_eqClassNbr;
		final Struct_MizUnifier l16_u = au;
		final Func1<Object,Integer> l15_$12 = (Func1<Object, Integer>)(Integer ak) -> {
			final Struct l16_et = ((Struct)Module_vector.f_getVectorUnsafe((l16_u).f_eTrm, ((int)ak)));
			final Struct_MizPreInst l17_l = Module_unifier.f_mizUniTrm(l16_u, l9_lLeft, l16_et);
			if ((Module_prechecker.f_mizPreInstCount(l17_l)!=0)) {
				final Struct_MizPreInst l18_l1 = Module_unifier.f_mizUniTrm(l16_u, l10_lRight, l16_et);
				Module_unifier.f_mizUniJoin(l16_u, l17_l, l18_l1);
				return Module_unifier.f_mizUniUnion(l16_u, l11_res, l17_l);
			} else {
				return null;
			}
		};
		Module_runtime.f_fori(1, l14_$13, l15_$12);
		return l11_res;
	}
	public static final Struct_MizPreInst f_mizUnifyTrmList(Struct_MizUnifier au, Struct aa, Struct ab) {
		Struct l0__tmp = aa;
		switch (l0__tmp.getTypeId()) {
		case 64/*MizTrmNil*/: {
			Struct l2__tmp = ab;
			switch (l2__tmp.getTypeId()) {
			case 64/*MizTrmNil*/: {
				return Module_unifier.f_mizUniTop();
			}
			case 63/*MizTrmElem*/: {
				final Struct_MizTrmElem l3__tmp = (Struct_MizTrmElem)l2__tmp;
				final Struct l4___ = (l3__tmp).f_trm;
				final Struct l5___ = (l3__tmp).f_next;
				return Module_unifier.f_mizUniBottom();
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
			}
		}
		case 63/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l6___ = (l1__tmp).f_trm;
			final Struct l7___ = (l1__tmp).f_next;
			Struct l8__tmp = ab;
			switch (l8__tmp.getTypeId()) {
			case 64/*MizTrmNil*/: {
				return Module_unifier.f_mizUniBottom();
			}
			case 63/*MizTrmElem*/: {
				final Struct_MizTrmElem l9__tmp = (Struct_MizTrmElem)l8__tmp;
				final Struct l10___ = (l9__tmp).f_trm;
				final Struct l11___ = (l9__tmp).f_next;
				final Struct_Vector l12_colls = Module_vector.f_makeVector(4);
				final Reference<Boolean> l13_dead = ((Reference<Boolean>)(new Reference(false)));
				final Reference<Struct> l14_ca = ((Reference<Struct>)(new Reference(l1__tmp)));
				final Reference<Struct> l15_cb = ((Reference<Struct>)(new Reference(l9__tmp)));
				final Func0<Boolean> l18_$16 = (Func0<Boolean>)() -> {
					boolean l21_$20;
					if (!((boolean)l13_dead.value)) {
						final Struct l22_gsymswitch18 = l14_ca.value;
						Struct l23__tmp = l22_gsymswitch18;
						switch (l23__tmp.getTypeId()) {
						case 64/*MizTrmNil*/: {
							l21_$20=false;
							break;
						}
						default: {
							l21_$20=true;
							break;
						}
						}
					} else {
						l21_$20=false;
					}
					return ((Boolean)l21_$20);
				};
				final Struct_MizUnifier l20_u = au;
				final Func0<Object> l19_$17 = (Func0<Object>)() -> {
					final Struct l20_gsymswitch19 = l14_ca.value;
					Struct l21__tmp = l20_gsymswitch19;
					switch (l21__tmp.getTypeId()) {
					case 63/*MizTrmElem*/: {
						final Struct_MizTrmElem l22__tmp = (Struct_MizTrmElem)l21__tmp;
						final Struct l23_ta = l22__tmp.f_trm;
						final Struct l24_na = l22__tmp.f_next;
						final Struct l25_ta = (l22__tmp).f_trm;
						final Struct l26_na = (l22__tmp).f_next;
						final Struct l27_gsymswitch20 = l15_cb.value;
						Struct l28__tmp = l27_gsymswitch20;
						switch (l28__tmp.getTypeId()) {
						case 63/*MizTrmElem*/: {
							final Struct_MizTrmElem l29__tmp = (Struct_MizTrmElem)l28__tmp;
							final Struct l30_tb = l29__tmp.f_trm;
							final Struct l31_nb = l29__tmp.f_next;
							final Struct l32_tb = (l29__tmp).f_trm;
							final Struct l33_nb = (l29__tmp).f_next;
							final Struct_MizPreInst l34_l = Module_unifier.f_mizUnifyTrmsWithConsts(l20_u, l25_ta, l32_tb);
							if ((Module_prechecker.f_mizPreInstCount(l34_l)==0)) {
								l13_dead.value = ((Boolean)true);
							} else {
								if (!(l34_l).f_top) {
									Module_vector.f_pushVector(l12_colls, l34_l);
								} else {
								}
							}
							l14_ca.value = l26_na;
							l15_cb.value = l33_nb;
							return null;
						}
						case 64/*MizTrmNil*/: {
							l13_dead.value = ((Boolean)true);
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l28__tmp.getTypeName());
						}
					}
					case 64/*MizTrmNil*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l21__tmp.getTypeName());
					}
				};
				Module_loop.f_while(l18_$16, l19_$17);
				if (((boolean)l13_dead.value)) {
					return Module_unifier.f_mizUniBottom();
				} else {
					return Module_prechecker.f_mizPreInstJoinList((au).f_pre, l12_colls);
				}
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l8__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct_MizPreInst f_mizUnifyTrmsWithConsts(Struct_MizUnifier au, Struct aa, Struct ab) {
		final Struct_MizPreInst l0_res = Module_unifier.f_mizUniBottom();
		final int l3_$2 = (au).f_eqClassNbr;
		final Struct_MizUnifier l5_u = au;
		final Struct l6_a = aa;
		final Struct l7_b = ab;
		final Func1<Object,Integer> l4_$1 = (Func1<Object, Integer>)(Integer ak) -> {
			final Struct l5_et = ((Struct)Module_vector.f_getVectorUnsafe((l5_u).f_eTrm, ((int)ak)));
			final Struct_MizPreInst l6_l = Module_unifier.f_mizUniTrm(l5_u, l6_a, l5_et);
			if ((Module_prechecker.f_mizPreInstCount(l6_l)!=0)) {
				final Struct_MizPreInst l7_l1 = Module_unifier.f_mizUniTrm(l5_u, l7_b, l5_et);
				Module_unifier.f_mizUniJoin(l5_u, l6_l, l7_l1);
				return Module_unifier.f_mizUniUnion(l5_u, l0_res, l6_l);
			} else {
				return null;
			}
		};
		Module_runtime.f_fori(1, l3_$2, l4_$1);
		return l0_res;
	}
	public static final Struct_MizPreInst f_mizUnifyTypsWithConsts(Struct_MizUnifier au, Struct_MizTyp aa, Struct_MizTyp ab) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizPreInst l1_res = Module_unifier.f_mizUniBottom();
		if (((aa).f_sort==(ab).f_sort)) {
			final Struct_MizPreInst l2_l = Module_unifier.f_mizUnifyInclClusters(au, (aa).f_lower, (ab).f_upper);
			if ((Module_prechecker.f_mizPreInstCount(l2_l)!=0)) {
				final Struct_MizPreInst l3_l1 = Module_unifier.f_mizUnifyInclClusters(au, (ab).f_lower, (aa).f_upper);
				Module_unifier.f_mizUniJoin(au, l2_l, l3_l1);
				if ((Module_prechecker.f_mizPreInstCount(l2_l)!=0)) {
					Struct_Pair l4_p;
					if (((aa).f_sort==Module_lexicon.g_ikTypMode)) {
						l4_p=(new Struct_Pair(Module_correl.f_mizAdjustTyp(l0_env, aa), Module_correl.f_mizAdjustTyp(l0_env, ab)));
					} else {
						l4_p=(new Struct_Pair((new Struct_Pair((aa).f_modNr, (aa).f_modArgs)), (new Struct_Pair((ab).f_modNr, (ab).f_modArgs))));
					}
					if ((((int)(((Struct_Pair)(l4_p).f_first)).f_first)==((int)(((Struct_Pair)(l4_p).f_second)).f_first))) {
						final Struct_MizPreInst l5_l2 = Module_unifier.f_mizUnifyTrmList(au, ((Struct)(((Struct_Pair)(l4_p).f_first)).f_second), ((Struct)(((Struct_Pair)(l4_p).f_second)).f_second));
						Module_unifier.f_mizUniJoin(au, l2_l, l5_l2);
					} else {
					}
					Module_unifier.f_mizUniUnion(au, l1_res, l2_l);
				} else {
				}
			} else {
			}
		} else {
		}
		return l1_res;
	}
	public static final boolean f_mizVerify(Struct_MizUnifier au, Struct af0) {
		final Struct_MizCorrelEnv l0_env = (au).f_env;
		final Struct_MizPre l1_pre = (au).f_pre;
		((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
		final Struct l2_lFrm0 = Module_unifier.f_mizPositivelyStandarized(au, af0);
		Module_vector.f_clearVector((au).f_freeVarType);
		((Field_unifBase)au).set_unifBase(0);
		((Field_freeVarUnifs)au).set_freeVarUnifs(Module_tree.f_makeTree());
		final Struct_MizUnifier l5_u = au;
		final Func1<Struct,Struct> l4_$3 = (Func1<Struct, Struct>)(Struct at) -> {
			return Module_unifier.f_mizSetFreeVarInTrm(l5_u, at);
		};
		((Field_setVarInTrm)l1_pre).set_setVarInTrm(l4_$3);
		final Struct_MizUnifier l8_u = au;
		final Func2<Object,Struct_MizPre, Struct_MizTyp> l7_$6 = (Func2<Object, Struct_MizPre, Struct_MizTyp>)(Struct_MizPre al8_0, Struct_MizTyp atyp) -> {
			return Module_unifier.f_mizNewFreeVar(l8_u, atyp);
		};
		((Field_newVariable)l1_pre).set_newVariable(l7_$6);
		((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
		((Field_freeVarBase)au).set_freeVarBase(Module_vector.f_sizeVector((au).f_freeVarType));
		final Struct l9_lFrm = Module_unifier.f_mizURemoveExtQuantifier(au, l2_lFrm0);
		((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
		Module_vector.f_clearVector((l1_pre).f_basic);
		final Struct_MizPreInst l10_normalForm = Module_prechecker.f_mizNormalizeAsFalse(l1_pre, l9_lFrm);
		final Reference<Boolean> l11_verified = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l12_i = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l15_$13 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l11_verified.value)&&(((int)l12_i.value)<Module_prechecker.f_mizPreInstCount(l10_normalForm))));
		};
		final Struct_MizUnifier l17_u = au;
		final Func0<Object> l16_$14 = (Func0<Object>)() -> {
			if ((l10_normalForm).f_top) {
				l11_verified.value = ((Boolean)true);
			} else {
				final Struct_MizNatFunc l17_ev = ((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe((l10_normalForm).f_evals, ((int)l12_i.value)));
				final Struct_Vector l18_colls = Module_vector.f_makeVector(4);
				final Reference<Boolean> l19_dead = ((Reference<Boolean>)(new Reference(false)));
				final Reference<Integer> l20_j = ((Reference<Integer>)(new Reference(0)));
				final Func0<Boolean> l23_$21 = (Func0<Boolean>)() -> {
					return ((Boolean)(!((boolean)l19_dead.value)&&(((int)l20_j.value)<Module_mobjects.f_mizNatCount(l17_ev))));
				};
				final Func0<Object> l24_$22 = (Func0<Object>)() -> {
					final Struct_MizIntPair l25_it = Module_mobjects.f_mizNatItem(l17_ev, ((int)l20_j.value));
					final Struct l26_bf = ((Struct)Module_vector.f_getVectorUnsafe((l1_pre).f_basic, (l25_it).f_x));
					Struct_MizPreInst l27_lInsts;
					if (((l25_it).f_y==1)) {
						l27_lInsts=Module_unifier.f_mizCompInstAsFalse(l17_u, l26_bf);
					} else {
						l27_lInsts=Module_unifier.f_mizCompInstAsTrue(l17_u, l26_bf);
					}
					if ((Module_prechecker.f_mizPreInstCount(l27_lInsts)==0)) {
						l19_dead.value = ((Boolean)true);
					} else {
						if (!(l27_lInsts).f_top) {
							Module_vector.f_pushVector(l18_colls, l27_lInsts);
						} else {
						}
					}
					l20_j.value = ((Integer)(((int)l20_j.value)+1));
					return null;
				};
				Module_loop.f_while(l23_$21, l24_$22);
				if (!((boolean)l19_dead.value)) {
					final Struct_MizPreInst l25_joined = Module_prechecker.f_mizPreInstJoinList(l1_pre, l18_colls);
					if ((Module_prechecker.f_mizPreInstCount(l25_joined)!=0)) {
						l11_verified.value = ((Boolean)true);
					} else {
					}
				} else {
				}
			}
			l12_i.value = ((Integer)(((int)l12_i.value)+1));
			return null;
		};
		Module_loop.f_while(l15_$13, l16_$14);
		return ((boolean)l11_verified.value);
	}
	public static final Object f_mizWireUnifier(Struct_MizChecker achk) {
		final Func2<Integer,Struct_MizChecker, Struct_MizEqualizer> l1_$0 = (Func2<Integer, Struct_MizChecker, Struct_MizEqualizer>)(Struct_MizChecker ac, Struct_MizEqualizer ae) -> {
			return ((Integer)Module_unifier.f_mizUnification(ac, ae));
		};
		((Field_unification)achk).set_unification(l1_$0);
		return null;
	}
}
