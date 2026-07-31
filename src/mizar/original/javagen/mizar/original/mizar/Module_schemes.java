// HASH COLLISIONS: YES
// timestamp: 1785514111000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_schemes {
	public static final Struct_MizSchemes f_mizMakeSchemes() {
		return (new Struct_MizSchemes(Module_vector.f_makeVector(8)));
	}
	public static final Struct_MizFuncTrm f_mizSchAsFunc(Struct at) {
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
			return ((Struct_MizFuncTrm)Native.failWithError("not a func trm"));
		}
		}
	}
	public static final Struct_MizVarTrm f_mizSchAsVar(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 77/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2___ = (l1__tmp).f_sort;
			final int l3___ = (l1__tmp).f_varNr;
			final int l4___ = (l1__tmp).f_pattNr;
			final int l5___ = (l1__tmp).f_trmInfo;
			return l1__tmp;
		}
		default: {
			return ((Struct_MizVarTrm)Native.failWithError("not a var trm"));
		}
		}
	}
	public static final Object f_mizSchChkBound(Struct_MizSchState as, Struct at) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		final Struct_MizSchState l3_s = as;
		final Func2<Struct,Struct_MizWithin, Struct> l2_$1 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al3_0, Struct atrm) -> {
			Struct l4__tmp = atrm;
			switch (l4__tmp.getTypeId()) {
			case 77/*MizVarTrm*/: {
				final Struct_MizVarTrm l5__tmp = (Struct_MizVarTrm)l4__tmp;
				final int l6_srt = l5__tmp.f_sort;
				final int l7_v = l5__tmp.f_varNr;
				final int l8_srt = (l5__tmp).f_sort;
				final int l9_v = (l5__tmp).f_varNr;
				final int l10___ = (l5__tmp).f_pattNr;
				final int l11___ = (l5__tmp).f_trmInfo;
				if (((l8_srt==Module_lexicon.g_ikTrmBound)&&(l9_v<=(l0_env).f_boundVarNbr))) {
					((Field_thereAreBound)l3_s).set_thereAreBound(true);
				} else {
				}
				return l5__tmp;
			}
			default: {
				return atrm;
			}
			}
		};
		final Struct_MizWithin l4_w = Module_correl.f_mizMakeWithin(l2_$1);
		final Struct l5___t = Module_correl.f_mizWithinTrm(l4_w, at);
		return null;
	}
	public static final boolean f_mizSchCompTyp(Struct_MizSchState as, Struct_MizTyp at1, Struct_MizTyp at2) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		if (((at1).f_sort!=(at2).f_sort)) {
			return false;
		} else {
			if (((at1).f_sort==Module_lexicon.g_ikTypMode)) {
				final Struct_Pair l1_p1 = Module_correl.f_mizAdjustTyp(l0_env, at1);
				final Struct_Pair l2_p2 = Module_correl.f_mizAdjustTyp(l0_env, at2);
				return ((((int)(l1_p1).f_first)==((int)(l2_p2).f_first))&&Module_schemes.f_mizSchEqTrmList(as, ((Struct)(l1_p1).f_second), ((Struct)(l2_p2).f_second)));
			} else {
				if (((at1).f_sort==Module_lexicon.g_ikTypStruct)) {
					return (((at1).f_modNr==(at2).f_modNr)&&Module_schemes.f_mizSchEqTrmList(as, (at1).f_modArgs, (at2).f_modArgs));
				} else {
					return ((at1).f_sort==Module_lexicon.g_ikError);
				}
			}
		}
	}
	public static final boolean f_mizSchEqAttr(Struct_MizSchState as, Struct_MizAttr aa1, Struct_MizAttr aa2) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		final Struct_Pair l1_p1 = Module_correl.f_mizAdjustAttr(l0_env, aa1);
		final Struct_Pair l2_p2 = Module_correl.f_mizAdjustAttr(l0_env, aa2);
		return (((((int)(l1_p1).f_first)==((int)(l2_p2).f_first))&&((aa1).f_neg==(aa2).f_neg))&&Module_schemes.f_mizSchEqTrmList(as, ((Struct)(l1_p1).f_second), ((Struct)(l2_p2).f_second)));
	}
	public static final boolean f_mizSchEqFrm(Struct_MizSchState as, Struct afFrm10, Struct afFrm20) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		final boolean l1_lNeg = (((Module_correl.f_mizFrmSort(afFrm10)==Module_lexicon.g_ikFrmNeg)&&(Module_correl.f_mizFrmSort(afFrm20)!=Module_lexicon.g_ikFrmNeg))||((Module_correl.f_mizFrmSort(afFrm20)==Module_lexicon.g_ikFrmNeg)&&(Module_correl.f_mizFrmSort(afFrm10)!=Module_lexicon.g_ikFrmNeg)));
		Struct l2_fFrm1;
		if (((Module_correl.f_mizFrmSort(afFrm10)==Module_lexicon.g_ikFrmNeg)&&l1_lNeg)) {
			Struct l3__tmp = afFrm10;
			switch (l3__tmp.getTypeId()) {
			case 52/*MizNegFrm*/: {
				final Struct_MizNegFrm l4__tmp = (Struct_MizNegFrm)l3__tmp;
				final Struct l5_a = l4__tmp.f_arg;
				final Struct l6_a = (l4__tmp).f_arg;
				final int l7___ = (l4__tmp).f_pattNr;
				l2_fFrm1=l6_a;
				break;
			}
			default: {
				l2_fFrm1=afFrm10;
				break;
			}
			}
		} else {
			l2_fFrm1=afFrm10;
		}
		Struct l8_fFrm2;
		if (((Module_correl.f_mizFrmSort(afFrm20)==Module_lexicon.g_ikFrmNeg)&&l1_lNeg)) {
			Struct l9__tmp = afFrm20;
			switch (l9__tmp.getTypeId()) {
			case 52/*MizNegFrm*/: {
				final Struct_MizNegFrm l10__tmp = (Struct_MizNegFrm)l9__tmp;
				final Struct l11_a = l10__tmp.f_arg;
				final Struct l12_a = (l10__tmp).f_arg;
				final int l13___ = (l10__tmp).f_pattNr;
				l8_fFrm2=l12_a;
				break;
			}
			default: {
				l8_fFrm2=afFrm20;
				break;
			}
			}
		} else {
			l8_fFrm2=afFrm20;
		}
		final int l14_sf1 = Module_correl.f_mizFrmSort(l2_fFrm1);
		final int l15_sf2 = Module_correl.f_mizFrmSort(l8_fFrm2);
		if ((l14_sf1==Module_lexicon.g_ikFrmSchPred)) {
			Struct_MizPredFrm l16_p1;
			Struct l17__tmp = l2_fFrm1;
			switch (l17__tmp.getTypeId()) {
			case 58/*MizPredFrm*/: {
				final Struct_MizPredFrm l18__tmp = (Struct_MizPredFrm)l17__tmp;
				final int l19___ = (l18__tmp).f_sort;
				final int l20___ = (l18__tmp).f_predNr;
				final Struct l21___ = (l18__tmp).f_args;
				final int l22___ = (l18__tmp).f_pattNr;
				l16_p1=l18__tmp;
				break;
			}
			default: {
				l16_p1=((Struct_MizPredFrm)Native.failWithError("sp"));
				break;
			}
			}
			if ((((l15_sf2==Module_lexicon.g_ikFrmPred)||(l15_sf2==Module_lexicon.g_ikFrmPrivPred))||(l15_sf2==Module_lexicon.g_ikFrmSchPred))) {
				int l23_p2nr;
				Struct l24__tmp = l8_fFrm2;
				switch (l24__tmp.getTypeId()) {
				case 58/*MizPredFrm*/: {
					final Struct_MizPredFrm l25__tmp = (Struct_MizPredFrm)l24__tmp;
					final int l26_nr = l25__tmp.f_predNr;
					final int l27___ = (l25__tmp).f_sort;
					final int l28_nr = (l25__tmp).f_predNr;
					final Struct l29___ = (l25__tmp).f_args;
					final int l30___ = (l25__tmp).f_pattNr;
					l23_p2nr=l28_nr;
					break;
				}
				case 49/*MizLocPredFrm*/: {
					final Struct_MizLocPredFrm l25__tmp = (Struct_MizLocPredFrm)l24__tmp;
					final int l31_nr = l25__tmp.f_predNr;
					final int l32_nr = (l25__tmp).f_predNr;
					final Struct l33___ = (l25__tmp).f_args;
					final Struct l34___ = (l25__tmp).f_predExp;
					final int l35___ = (l25__tmp).f_pattNr;
					l23_p2nr=l32_nr;
					break;
				}
				default: {
					l23_p2nr=0;
					break;
				}
				}
				Struct l36_p2args;
				Struct l37__tmp = l8_fFrm2;
				switch (l37__tmp.getTypeId()) {
				case 58/*MizPredFrm*/: {
					final Struct_MizPredFrm l38__tmp = (Struct_MizPredFrm)l37__tmp;
					final Struct l39_args = l38__tmp.f_args;
					final int l40___ = (l38__tmp).f_sort;
					final int l41___ = (l38__tmp).f_predNr;
					final Struct l42_args = (l38__tmp).f_args;
					final int l43___ = (l38__tmp).f_pattNr;
					l36_p2args=l42_args;
					break;
				}
				case 49/*MizLocPredFrm*/: {
					final Struct_MizLocPredFrm l38__tmp = (Struct_MizLocPredFrm)l37__tmp;
					final Struct l44_args = l38__tmp.f_args;
					final int l45___ = (l38__tmp).f_predNr;
					final Struct l46_args = (l38__tmp).f_args;
					final Struct l47___ = (l38__tmp).f_predExp;
					final int l48___ = (l38__tmp).f_pattNr;
					l36_p2args=l46_args;
					break;
				}
				default: {
					l36_p2args=((Struct)SingletonStructs.str_MizTrmNil);
					break;
				}
				}
				final int l49_key = Module_schemes.f_skKey(l15_sf2, (l16_p1).f_predNr);
				final Struct l50_gsymswitch11 = Module_tree.f_lookupTree((as).f_predSubst, l49_key);
				Struct l51__tmp = l50_gsymswitch11;
				switch (l51__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l52__tmp = (Struct_Some)l51__tmp;
					final Object l53_v0 = l52__tmp.f_value;
					final int l54_v0 = ((int)(l52__tmp).f_value);
					int l55_v;
					if (l1_lNeg) {
						l55_v=(-l54_v0);
					} else {
						l55_v=l54_v0;
					}
					if ((l55_v==l23_p2nr)) {
						if (Module_schemes.f_mizSchEqTrmList(as, (l16_p1).f_args, l36_p2args)) {
							return true;
						} else {
							((Field_err)as).set_err(23);
							return false;
						}
					} else {
						((Field_err)as).set_err(24);
						return false;
					}
				}
				case 82/*None*/: {
					final Object[] l58_$57 = (new Object[] { Module_lexicon.g_ikFrmPred, Module_lexicon.g_ikFrmPrivPred, Module_lexicon.g_ikFrmSchPred });
					final Func1<Boolean,Integer> l59_$56 = (Func1<Boolean, Integer>)(Integer ak) -> {
						return (((int)ak)!=l15_sf2);
					};
					final Object[] l60_others = Native.filter(l58_$57, ((Func1<Boolean,Object>)(Func1)l59_$56));
					final Struct_MizSchState l63_s = as;
					final Struct_MizPredFrm l64_p1 = l16_p1;
					final Func1<Boolean,Integer> l62_$61 = (Func1<Boolean, Integer>)(Integer ak) -> {
						return ((Boolean)Module_maybe.f_isSome(Module_tree.f_lookupTree((l63_s).f_predSubst, Module_schemes.f_skKey(((int)ak), (l64_p1).f_predNr))));
					};
					final boolean l65_conflict = Native.exists(l60_others, ((Func1<Boolean,Object>)(Func1)l62_$61));
					if (l65_conflict) {
						((Field_err)as).set_err(31);
						return false;
					} else {
						((Field_predSubst)as).set_predSubst(Module_tree.f_setTree((as).f_predSubst, l49_key, l23_p2nr));
						if (Module_schemes.f_mizSchEqTrmList(as, (l16_p1).f_args, l36_p2args)) {
							return true;
						} else {
							((Field_err)as).set_err(23);
							return false;
						}
					}
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l51__tmp.getTypeName());
				}
			} else {
				((Field_err)as).set_err(22);
				return false;
			}
		} else {
			if (((l14_sf1==l15_sf2)&&!l1_lNeg)) {
				if (((l14_sf1==Module_lexicon.g_ikFrmVerum)||(l14_sf1==Module_lexicon.g_ikFrmThesis))) {
					return true;
				} else {
					if ((l14_sf1==Module_lexicon.g_ikFrmNeg)) {
						Struct l66_a1;
						Struct l67__tmp = l2_fFrm1;
						switch (l67__tmp.getTypeId()) {
						case 52/*MizNegFrm*/: {
							final Struct_MizNegFrm l68__tmp = (Struct_MizNegFrm)l67__tmp;
							final Struct l69_a = l68__tmp.f_arg;
							final Struct l70_a = (l68__tmp).f_arg;
							final int l71___ = (l68__tmp).f_pattNr;
							l66_a1=l70_a;
							break;
						}
						default: {
							l66_a1=l2_fFrm1;
							break;
						}
						}
						Struct l72_a2;
						Struct l73__tmp = l8_fFrm2;
						switch (l73__tmp.getTypeId()) {
						case 52/*MizNegFrm*/: {
							final Struct_MizNegFrm l74__tmp = (Struct_MizNegFrm)l73__tmp;
							final Struct l75_a = l74__tmp.f_arg;
							final Struct l76_a = (l74__tmp).f_arg;
							final int l77___ = (l74__tmp).f_pattNr;
							l72_a2=l76_a;
							break;
						}
						default: {
							l72_a2=l8_fFrm2;
							break;
						}
						}
						{
							final Struct l78___tmp = l66_a1;
							final Struct l79___tmp = l72_a2;
							afFrm10 = l78___tmp;
							afFrm20 = l79___tmp;
							continue TAIL_CALL;
						}
					} else {
						if ((l14_sf1==Module_lexicon.g_ikFrmQual)) {
							Struct_MizQualFrm l80_q1;
							Struct l81__tmp = l2_fFrm1;
							switch (l81__tmp.getTypeId()) {
							case 63/*MizQualFrm*/: {
								final Struct_MizQualFrm l82__tmp = (Struct_MizQualFrm)l81__tmp;
								final Struct l83___ = (l82__tmp).f_trm;
								final Struct_MizTyp l84___ = (l82__tmp).f_typ;
								final int l85___ = (l82__tmp).f_pattNr;
								l80_q1=l82__tmp;
								break;
							}
							default: {
								l80_q1=((Struct_MizQualFrm)Native.failWithError("q"));
								break;
							}
							}
							Struct_MizQualFrm l86_q2;
							Struct l87__tmp = l8_fFrm2;
							switch (l87__tmp.getTypeId()) {
							case 63/*MizQualFrm*/: {
								final Struct_MizQualFrm l88__tmp = (Struct_MizQualFrm)l87__tmp;
								final Struct l89___ = (l88__tmp).f_trm;
								final Struct_MizTyp l90___ = (l88__tmp).f_typ;
								final int l91___ = (l88__tmp).f_pattNr;
								l86_q2=l88__tmp;
								break;
							}
							default: {
								l86_q2=((Struct_MizQualFrm)Native.failWithError("q"));
								break;
							}
							}
							return (Module_schemes.f_mizSchEqTrm(as, (l80_q1).f_trm, (l86_q2).f_trm)&&Module_schemes.f_mizSchEqTyp(as, (l80_q1).f_typ, (l86_q2).f_typ));
						} else {
							if ((l14_sf1==Module_lexicon.g_ikFrmConj)) {
								Struct_MizConjFrm l92_c1;
								Struct l93__tmp = l2_fFrm1;
								switch (l93__tmp.getTypeId()) {
								case 26/*MizConjFrm*/: {
									final Struct_MizConjFrm l94__tmp = (Struct_MizConjFrm)l93__tmp;
									final Struct_Vector l95___ = (l94__tmp).f_conjuncts;
									final int l96___ = (l94__tmp).f_pattNr;
									l92_c1=l94__tmp;
									break;
								}
								default: {
									l92_c1=((Struct_MizConjFrm)Native.failWithError("c"));
									break;
								}
								}
								Struct_MizConjFrm l97_c2;
								Struct l98__tmp = l8_fFrm2;
								switch (l98__tmp.getTypeId()) {
								case 26/*MizConjFrm*/: {
									final Struct_MizConjFrm l99__tmp = (Struct_MizConjFrm)l98__tmp;
									final Struct_Vector l100___ = (l99__tmp).f_conjuncts;
									final int l101___ = (l99__tmp).f_pattNr;
									l97_c2=l99__tmp;
									break;
								}
								default: {
									l97_c2=((Struct_MizConjFrm)Native.failWithError("c"));
									break;
								}
								}
								if ((Module_vector.f_sizeVector((l92_c1).f_conjuncts)!=Module_vector.f_sizeVector((l97_c2).f_conjuncts))) {
									return false;
								} else {
									final Reference<Boolean> l102_ok = ((Reference<Boolean>)(new Reference(true)));
									final Reference<Integer> l103_i = ((Reference<Integer>)(new Reference(0)));
									final Struct_MizConjFrm l107_c1 = l92_c1;
									final Func0<Boolean> l106_$104 = (Func0<Boolean>)() -> {
										return ((Boolean)(((boolean)l102_ok.value)&&(((int)l103_i.value)<Module_vector.f_sizeVector((l107_c1).f_conjuncts))));
									};
									final Struct_MizSchState l109_s = as;
									final Struct_MizConjFrm l110_c1 = l92_c1;
									final Struct_MizConjFrm l111_c2 = l97_c2;
									final Func0<Object> l108_$105 = (Func0<Object>)() -> {
										if (!Module_schemes.f_mizSchEqFrm(l109_s, ((Struct)Module_vector.f_getVectorUnsafe((l110_c1).f_conjuncts, ((int)l103_i.value))), ((Struct)Module_vector.f_getVectorUnsafe((l111_c2).f_conjuncts, ((int)l103_i.value))))) {
											l102_ok.value = ((Boolean)false);
										} else {
										}
										l103_i.value = ((Integer)(((int)l103_i.value)+1));
										return null;
									};
									Module_loop.f_while(l106_$104, l108_$105);
									return ((boolean)l102_ok.value);
								}
							} else {
								if ((l14_sf1==Module_lexicon.g_ikFrmAttr)) {
									Struct_MizPredFrm l112_p1;
									Struct l113__tmp = l2_fFrm1;
									switch (l113__tmp.getTypeId()) {
									case 58/*MizPredFrm*/: {
										final Struct_MizPredFrm l114__tmp = (Struct_MizPredFrm)l113__tmp;
										final int l115___ = (l114__tmp).f_sort;
										final int l116___ = (l114__tmp).f_predNr;
										final Struct l117___ = (l114__tmp).f_args;
										final int l118___ = (l114__tmp).f_pattNr;
										l112_p1=l114__tmp;
										break;
									}
									default: {
										l112_p1=((Struct_MizPredFrm)Native.failWithError("a"));
										break;
									}
									}
									Struct_MizPredFrm l119_p2;
									Struct l120__tmp = l8_fFrm2;
									switch (l120__tmp.getTypeId()) {
									case 58/*MizPredFrm*/: {
										final Struct_MizPredFrm l121__tmp = (Struct_MizPredFrm)l120__tmp;
										final int l122___ = (l121__tmp).f_sort;
										final int l123___ = (l121__tmp).f_predNr;
										final Struct l124___ = (l121__tmp).f_args;
										final int l125___ = (l121__tmp).f_pattNr;
										l119_p2=l121__tmp;
										break;
									}
									default: {
										l119_p2=((Struct_MizPredFrm)Native.failWithError("a"));
										break;
									}
									}
									final Struct_Pair l126_q1 = Module_correl.f_mizAdjustAttrFrm(l0_env, l112_p1);
									final Struct_Pair l127_q2 = Module_correl.f_mizAdjustAttrFrm(l0_env, l119_p2);
									return ((((int)(l126_q1).f_first)==((int)(l127_q2).f_first))&&Module_schemes.f_mizSchEqTrmList(as, ((Struct)(l126_q1).f_second), ((Struct)(l127_q2).f_second)));
								} else {
									if ((l14_sf1==Module_lexicon.g_ikFrmPred)) {
										Struct_MizPredFrm l128_p1;
										Struct l129__tmp = l2_fFrm1;
										switch (l129__tmp.getTypeId()) {
										case 58/*MizPredFrm*/: {
											final Struct_MizPredFrm l130__tmp = (Struct_MizPredFrm)l129__tmp;
											final int l131___ = (l130__tmp).f_sort;
											final int l132___ = (l130__tmp).f_predNr;
											final Struct l133___ = (l130__tmp).f_args;
											final int l134___ = (l130__tmp).f_pattNr;
											l128_p1=l130__tmp;
											break;
										}
										default: {
											l128_p1=((Struct_MizPredFrm)Native.failWithError("p"));
											break;
										}
										}
										Struct_MizPredFrm l135_p2;
										Struct l136__tmp = l8_fFrm2;
										switch (l136__tmp.getTypeId()) {
										case 58/*MizPredFrm*/: {
											final Struct_MizPredFrm l137__tmp = (Struct_MizPredFrm)l136__tmp;
											final int l138___ = (l137__tmp).f_sort;
											final int l139___ = (l137__tmp).f_predNr;
											final Struct l140___ = (l137__tmp).f_args;
											final int l141___ = (l137__tmp).f_pattNr;
											l135_p2=l137__tmp;
											break;
										}
										default: {
											l135_p2=((Struct_MizPredFrm)Native.failWithError("p"));
											break;
										}
										}
										final Struct_Pair l142_q1 = Module_correl.f_mizAdjustFrm(l0_env, l128_p1);
										final Struct_Pair l143_q2 = Module_correl.f_mizAdjustFrm(l0_env, l135_p2);
										if ((((int)(l142_q1).f_first)!=((int)(l143_q2).f_first))) {
											return false;
										} else {
											if ((((int)(l142_q1).f_first)==Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEqualsTo))) {
												return (Module_schemes.f_mizSchEqTrm(as, Module_correl.f_mizTrmListNth(((Struct)(l142_q1).f_second), 0), Module_correl.f_mizTrmListNth(((Struct)(l143_q2).f_second), 0))&&Module_schemes.f_mizSchEqTrm(as, Module_correl.f_mizTrmListNth(((Struct)(l142_q1).f_second), 1), Module_correl.f_mizTrmListNth(((Struct)(l143_q2).f_second), 1)));
											} else {
												return Module_schemes.f_mizSchEqTrmList(as, ((Struct)(l142_q1).f_second), ((Struct)(l143_q2).f_second));
											}
										}
									} else {
										if ((l14_sf1==Module_lexicon.g_ikFrmUniv)) {
											Struct_MizUnivFrm l144_u1;
											Struct l145__tmp = l2_fFrm1;
											switch (l145__tmp.getTypeId()) {
											case 76/*MizUnivFrm*/: {
												final Struct_MizUnivFrm l146__tmp = (Struct_MizUnivFrm)l145__tmp;
												final int l147___ = (l146__tmp).f_sort;
												final int l148___ = (l146__tmp).f_varId;
												final Struct_MizTyp l149___ = (l146__tmp).f_quantified;
												final Struct l150___ = (l146__tmp).f_scope;
												final int l151___ = (l146__tmp).f_pattNr;
												l144_u1=l146__tmp;
												break;
											}
											default: {
												l144_u1=((Struct_MizUnivFrm)Native.failWithError("u"));
												break;
											}
											}
											Struct_MizUnivFrm l152_u2;
											Struct l153__tmp = l8_fFrm2;
											switch (l153__tmp.getTypeId()) {
											case 76/*MizUnivFrm*/: {
												final Struct_MizUnivFrm l154__tmp = (Struct_MizUnivFrm)l153__tmp;
												final int l155___ = (l154__tmp).f_sort;
												final int l156___ = (l154__tmp).f_varId;
												final Struct_MizTyp l157___ = (l154__tmp).f_quantified;
												final Struct l158___ = (l154__tmp).f_scope;
												final int l159___ = (l154__tmp).f_pattNr;
												l152_u2=l154__tmp;
												break;
											}
											default: {
												l152_u2=((Struct_MizUnivFrm)Native.failWithError("u"));
												break;
											}
											}
											((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
											boolean l160_r;
											if (Module_schemes.f_mizSchEqTyp(as, (l144_u1).f_quantified, (l152_u2).f_quantified)) {
												Module_correl.f_mizSetBoundVar(l0_env, (l0_env).f_boundVarNbr, (l152_u2).f_quantified);
												l160_r=Module_schemes.f_mizSchEqFrm(as, (l144_u1).f_scope, (l152_u2).f_scope);
											} else {
												l160_r=false;
											}
											((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-1));
											return l160_r;
										} else {
											if ((l14_sf1==Module_lexicon.g_ikFrmFlexConj)) {
												Struct_MizFlexFrm l161_x1;
												Struct l162__tmp = l2_fFrm1;
												switch (l162__tmp.getTypeId()) {
												case 41/*MizFlexFrm*/: {
													final Struct_MizFlexFrm l163__tmp = (Struct_MizFlexFrm)l162__tmp;
													final int l164___ = (l163__tmp).f_sort;
													final Struct l165___ = (l163__tmp).f_leftOrig;
													final Struct l166___ = (l163__tmp).f_rightOrig;
													final Struct l167___ = (l163__tmp).f_leftTrm;
													final Struct l168___ = (l163__tmp).f_rightTrm;
													final Struct l169___ = (l163__tmp).f_expansion;
													final int l170___ = (l163__tmp).f_pattNr;
													l161_x1=l163__tmp;
													break;
												}
												default: {
													l161_x1=((Struct_MizFlexFrm)Native.failWithError("x"));
													break;
												}
												}
												Struct_MizFlexFrm l171_x2;
												Struct l172__tmp = l8_fFrm2;
												switch (l172__tmp.getTypeId()) {
												case 41/*MizFlexFrm*/: {
													final Struct_MizFlexFrm l173__tmp = (Struct_MizFlexFrm)l172__tmp;
													final int l174___ = (l173__tmp).f_sort;
													final Struct l175___ = (l173__tmp).f_leftOrig;
													final Struct l176___ = (l173__tmp).f_rightOrig;
													final Struct l177___ = (l173__tmp).f_leftTrm;
													final Struct l178___ = (l173__tmp).f_rightTrm;
													final Struct l179___ = (l173__tmp).f_expansion;
													final int l180___ = (l173__tmp).f_pattNr;
													l171_x2=l173__tmp;
													break;
												}
												default: {
													l171_x2=((Struct_MizFlexFrm)Native.failWithError("x"));
													break;
												}
												}
												return (Module_schemes.f_mizSchEqFrm(as, (l161_x1).f_leftOrig, (l171_x2).f_leftOrig)&&Module_schemes.f_mizSchEqFrm(as, (l161_x1).f_rightOrig, (l171_x2).f_rightOrig));
											} else {
												return (l14_sf1==Module_lexicon.g_ikError);
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				if ((l15_sf2==Module_lexicon.g_ikFrmPrivPred)) {
					Struct_MizLocPredFrm l181_l2;
					Struct l182__tmp = l8_fFrm2;
					switch (l182__tmp.getTypeId()) {
					case 49/*MizLocPredFrm*/: {
						final Struct_MizLocPredFrm l183__tmp = (Struct_MizLocPredFrm)l182__tmp;
						final int l184___ = (l183__tmp).f_predNr;
						final Struct l185___ = (l183__tmp).f_args;
						final Struct l186___ = (l183__tmp).f_predExp;
						final int l187___ = (l183__tmp).f_pattNr;
						l181_l2=l183__tmp;
						break;
					}
					default: {
						l181_l2=((Struct_MizLocPredFrm)Native.failWithError("l"));
						break;
					}
					}
					{
						final Struct l188___tmp = (l181_l2).f_predExp;
						afFrm20 = l188___tmp;
						continue TAIL_CALL;
					}
				} else {
					return false;
				}
			}
		}
	}
	}
	public static final boolean f_mizSchEqTrm(Struct_MizSchState as, Struct at1, Struct at2) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		final int l1_s1 = Module_correl.f_mizTrmSort(at1);
		final int l2_s2 = Module_correl.f_mizTrmSort(at2);
		if ((l1_s1==Module_lexicon.g_ikTrmSchFunc)) {
			final Struct_MizFuncTrm l3_f1 = Module_schemes.f_mizSchAsFunc(at1);
			final Struct l4_gsymswitch8 = (l3_f1).f_args;
			boolean l5_noArgs;
			Struct l6__tmp = l4_gsymswitch8;
			switch (l6__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				l5_noArgs=true;
				break;
			}
			default: {
				l5_noArgs=false;
				break;
			}
			}
			if (l5_noArgs) {
				final Struct l8_lTrm = Module_correl.f_mizCopyTrm(at2);
				((Field_thereAreBound)as).set_thereAreBound(false);
				Module_schemes.f_mizSchRenBound(as, l8_lTrm);
				final Struct l9_gsymswitch10 = Module_schemes.f_schConstAt(as, (l3_f1).f_funcNr);
				Struct l10__tmp = l9_gsymswitch10;
				switch (l10__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l11__tmp = (Struct_Some)l10__tmp;
					final Object l12_prev = l11__tmp.f_value;
					final Struct l13_prev = ((Struct)(l11__tmp).f_value);
					if (Module_correl.f_mizEqTrm(l0_env, l13_prev, l8_lTrm)) {
						return true;
					} else {
						((Field_err)as).set_err(25);
						return false;
					}
				}
				case 82/*None*/: {
					if ((as).f_thereAreBound) {
						((Field_err)as).set_err(27);
						return false;
					} else {
						final Struct_MizTyp l14_wTyp = Module_schemes.f_mizSchGetTrmType(as, l8_lTrm);
						if (Module_schemes.f_mizSchWidennings(as, l14_wTyp, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((as).f_schTypes, ((l3_f1).f_funcNr-1))))) {
							Module_schemes.f_schConstSet(as, (l3_f1).f_funcNr, l8_lTrm);
							return true;
						} else {
							((Field_err)as).set_err(26);
							if ((Module_url_parameter.f_getUrlParameterDef("dbg-sch", "")).equals("1")) {
								Module_runtime.f_println(((((((("SCH26 F"+Module_string.f_i2s((l3_f1).f_funcNr))+" want=")+Module_print.f_mizTyp2s(((Struct_MizTyp)Module_vector.f_getVectorUnsafe((as).f_schTypes, ((l3_f1).f_funcNr-1)))))+" got=")+Module_print.f_mizTyp2s(l14_wTyp))+" term=")+Module_print.f_mizTrm2s(l8_lTrm)));
							} else {
							}
							return false;
						}
					}
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
				}
			} else {
				if ((l2_s2==Module_lexicon.g_ikTrmInfConst)) {
					final Struct_MizVarTrm l15_v2 = Module_schemes.f_mizSchAsVar(at2);
					{
						final Struct l16___tmp = Module_schemes.f_mizSchExpandInfConst(as, (l15_v2).f_varNr);
						at2 = l16___tmp;
						continue TAIL_CALL;
					}
				} else {
					if ((((l2_s2==Module_lexicon.g_ikTrmFunctor)||(l2_s2==Module_lexicon.g_ikTrmPrivFunc))||(l2_s2==Module_lexicon.g_ikTrmSchFunc))) {
						int l17_f2nr;
						Struct l18__tmp = at2;
						switch (l18__tmp.getTypeId()) {
						case 45/*MizFuncTrm*/: {
							final Struct_MizFuncTrm l19__tmp = (Struct_MizFuncTrm)l18__tmp;
							final int l20_fn = l19__tmp.f_funcNr;
							final int l21___ = (l19__tmp).f_sort;
							final int l22_fn = (l19__tmp).f_funcNr;
							final Struct l23___ = (l19__tmp).f_args;
							final int l24___ = (l19__tmp).f_pattNr;
							final int l25___ = (l19__tmp).f_trmInfo;
							l17_f2nr=l22_fn;
							break;
						}
						case 61/*MizPrivFuncTrm*/: {
							final Struct_MizPrivFuncTrm l19__tmp = (Struct_MizPrivFuncTrm)l18__tmp;
							final int l26_fn = l19__tmp.f_funcNr;
							final int l27_fn = (l19__tmp).f_funcNr;
							final Struct l28___ = (l19__tmp).f_args;
							final Struct l29___ = (l19__tmp).f_funcExp;
							final int l30___ = (l19__tmp).f_pattNr;
							final int l31___ = (l19__tmp).f_trmInfo;
							l17_f2nr=l27_fn;
							break;
						}
						default: {
							l17_f2nr=0;
							break;
						}
						}
						Struct l32_f2args;
						Struct l33__tmp = at2;
						switch (l33__tmp.getTypeId()) {
						case 45/*MizFuncTrm*/: {
							final Struct_MizFuncTrm l34__tmp = (Struct_MizFuncTrm)l33__tmp;
							final Struct l35_aa = l34__tmp.f_args;
							final int l36___ = (l34__tmp).f_sort;
							final int l37___ = (l34__tmp).f_funcNr;
							final Struct l38_aa = (l34__tmp).f_args;
							final int l39___ = (l34__tmp).f_pattNr;
							final int l40___ = (l34__tmp).f_trmInfo;
							l32_f2args=l38_aa;
							break;
						}
						case 61/*MizPrivFuncTrm*/: {
							final Struct_MizPrivFuncTrm l34__tmp = (Struct_MizPrivFuncTrm)l33__tmp;
							final Struct l41_aa = l34__tmp.f_args;
							final int l42___ = (l34__tmp).f_funcNr;
							final Struct l43_aa = (l34__tmp).f_args;
							final Struct l44___ = (l34__tmp).f_funcExp;
							final int l45___ = (l34__tmp).f_pattNr;
							final int l46___ = (l34__tmp).f_trmInfo;
							l32_f2args=l43_aa;
							break;
						}
						default: {
							l32_f2args=((Struct)SingletonStructs.str_MizTrmNil);
							break;
						}
						}
						((Field_thereAreBound)as).set_thereAreBound(false);
						Module_schemes.f_mizSchChkBound(as, at2);
						final int l47_key = Module_schemes.f_skKey(l2_s2, (l3_f1).f_funcNr);
						final Struct l48_gsymswitch9 = Module_tree.f_lookupTree((as).f_funcSubst, l47_key);
						Struct l49__tmp = l48_gsymswitch9;
						switch (l49__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l50__tmp = (Struct_Some)l49__tmp;
							final Object l51_v = l50__tmp.f_value;
							final int l52_v = ((int)(l50__tmp).f_value);
							if ((l52_v==l17_f2nr)) {
								if (Module_schemes.f_mizSchEqTrmList(as, (l3_f1).f_args, l32_f2args)) {
									return true;
								} else {
									((Field_err)as).set_err(28);
									return false;
								}
							} else {
								((Field_err)as).set_err(29);
								return false;
							}
						}
						case 82/*None*/: {
							final Struct_MizTyp l53_wTyp = Module_schemes.f_mizSchGetTrmType(as, at2);
							if (Module_schemes.f_mizSchWidennings(as, l53_wTyp, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((as).f_schTypes, ((l3_f1).f_funcNr-1))))) {
								final Object[] l56_$55 = (new Object[] { Module_lexicon.g_ikTrmFunctor, Module_lexicon.g_ikTrmPrivFunc, Module_lexicon.g_ikTrmSchFunc });
								final Func1<Boolean,Integer> l57_$54 = (Func1<Boolean, Integer>)(Integer ak) -> {
									return (((int)ak)!=l2_s2);
								};
								final Object[] l58_others = Native.filter(l56_$55, ((Func1<Boolean,Object>)(Func1)l57_$54));
								final Struct_MizSchState l61_s = as;
								final Func1<Boolean,Integer> l60_$59 = (Func1<Boolean, Integer>)(Integer ak) -> {
									return ((Boolean)Module_maybe.f_isSome(Module_tree.f_lookupTree((l61_s).f_funcSubst, Module_schemes.f_skKey(((int)ak), (l3_f1).f_funcNr))));
								};
								final boolean l62_conflict = Native.exists(l58_others, ((Func1<Boolean,Object>)(Func1)l60_$59));
								if (l62_conflict) {
									((Field_err)as).set_err(32);
									return false;
								} else {
									((Field_funcSubst)as).set_funcSubst(Module_tree.f_setTree((as).f_funcSubst, l47_key, l17_f2nr));
									if (Module_schemes.f_mizSchEqTrmList(as, (l3_f1).f_args, l32_f2args)) {
										return true;
									} else {
										((Field_err)as).set_err(28);
										return false;
									}
								}
							} else {
								((Field_err)as).set_err(30);
								return false;
							}
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l49__tmp.getTypeName());
						}
					} else {
						((Field_err)as).set_err(21);
						return false;
					}
				}
			}
		} else {
			if ((l1_s1==l2_s2)) {
				if ((((((((l1_s1==Module_lexicon.g_ikTrmLocus)||(l1_s1==Module_lexicon.g_ikTrmBound))||(l1_s1==Module_lexicon.g_ikTrmConstant))||(l1_s1==Module_lexicon.g_ikTrmEqConst))||(l1_s1==Module_lexicon.g_ikTrmFreeVar))||(l1_s1==Module_lexicon.g_ikTrmLambdaVar))||(l1_s1==Module_lexicon.g_ikTrmNumeral))) {
					final Struct_MizVarTrm l63_v1 = Module_schemes.f_mizSchAsVar(at1);
					final Struct_MizVarTrm l64_v2 = Module_schemes.f_mizSchAsVar(at2);
					return ((l63_v1).f_varNr==(l64_v2).f_varNr);
				} else {
					if ((l1_s1==Module_lexicon.g_ikTrmInfConst)) {
						final Struct_MizVarTrm l65_v1 = Module_schemes.f_mizSchAsVar(at1);
						final Struct_MizVarTrm l66_v2 = Module_schemes.f_mizSchAsVar(at2);
						if (((l65_v1).f_varNr==(l66_v2).f_varNr)) {
							return true;
						} else {
							final Struct_MizConstDef l67_cd1 = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((l0_env).f_inferConstDef, (l65_v1).f_varNr));
							final Struct_MizConstDef l68_cd2 = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((l0_env).f_inferConstDef, (l66_v2).f_varNr));
							{
								final Struct l69___tmp = (l67_cd1).f_def;
								final Struct l70___tmp = (l68_cd2).f_def;
								at1 = l69___tmp;
								at2 = l70___tmp;
								continue TAIL_CALL;
							}
						}
					} else {
						if ((l1_s1==Module_lexicon.g_ikTrmFunctor)) {
							final Struct_MizFuncTrm l71_ff1 = Module_schemes.f_mizSchAsFunc(at1);
							final Struct_MizFuncTrm l72_ff2 = Module_schemes.f_mizSchAsFunc(at2);
							final Struct_Pair l73_p1 = Module_correl.f_mizAdjustTrm(l0_env, l71_ff1);
							final Struct_Pair l74_p2 = Module_correl.f_mizAdjustTrm(l0_env, l72_ff2);
							return ((((int)(l73_p1).f_first)==((int)(l74_p2).f_first))&&Module_schemes.f_mizSchEqTrmList(as, ((Struct)(l73_p1).f_second), ((Struct)(l74_p2).f_second)));
						} else {
							if (((l1_s1==Module_lexicon.g_ikTrmAggreg)||(l1_s1==Module_lexicon.g_ikTrmSelector))) {
								final Struct_MizFuncTrm l75_ff1 = Module_schemes.f_mizSchAsFunc(at1);
								final Struct_MizFuncTrm l76_ff2 = Module_schemes.f_mizSchAsFunc(at2);
								return (((l75_ff1).f_funcNr==(l76_ff2).f_funcNr)&&Module_schemes.f_mizSchEqTrmList(as, (l75_ff1).f_args, (l76_ff2).f_args));
							} else {
								if ((l1_s1==Module_lexicon.g_ikTrmFraenkel)) {
									Struct_MizFraenkelTrm l77_k1;
									Struct l78__tmp = at1;
									switch (l78__tmp.getTypeId()) {
									case 42/*MizFraenkelTrm*/: {
										final Struct_MizFraenkelTrm l79__tmp = (Struct_MizFraenkelTrm)l78__tmp;
										final Struct_Vector l80___ = (l79__tmp).f_lambdaArgs;
										final Struct l81___ = (l79__tmp).f_scope;
										final Struct l82___ = (l79__tmp).f_compr;
										final Struct_MizIntSeq l83___ = (l79__tmp).f_idents;
										final int l84___ = (l79__tmp).f_pattNr;
										final int l85___ = (l79__tmp).f_trmInfo;
										l77_k1=l79__tmp;
										break;
									}
									default: {
										l77_k1=((Struct_MizFraenkelTrm)Native.failWithError("fr"));
										break;
									}
									}
									Struct_MizFraenkelTrm l86_k2;
									Struct l87__tmp = at2;
									switch (l87__tmp.getTypeId()) {
									case 42/*MizFraenkelTrm*/: {
										final Struct_MizFraenkelTrm l88__tmp = (Struct_MizFraenkelTrm)l87__tmp;
										final Struct_Vector l89___ = (l88__tmp).f_lambdaArgs;
										final Struct l90___ = (l88__tmp).f_scope;
										final Struct l91___ = (l88__tmp).f_compr;
										final Struct_MizIntSeq l92___ = (l88__tmp).f_idents;
										final int l93___ = (l88__tmp).f_pattNr;
										final int l94___ = (l88__tmp).f_trmInfo;
										l86_k2=l88__tmp;
										break;
									}
									default: {
										l86_k2=((Struct_MizFraenkelTrm)Native.failWithError("fr"));
										break;
									}
									}
									if ((Module_vector.f_sizeVector((l77_k1).f_lambdaArgs)!=Module_vector.f_sizeVector((l86_k2).f_lambdaArgs))) {
										return false;
									} else {
										final int l95_lB = (l0_env).f_boundVarNbr;
										final Reference<Boolean> l96_ok = ((Reference<Boolean>)(new Reference(true)));
										final Reference<Integer> l97_i = ((Reference<Integer>)(new Reference(0)));
										final Struct_MizFraenkelTrm l101_k1 = l77_k1;
										final Func0<Boolean> l100_$98 = (Func0<Boolean>)() -> {
											return ((Boolean)(((boolean)l96_ok.value)&&(((int)l97_i.value)<Module_vector.f_sizeVector((l101_k1).f_lambdaArgs))));
										};
										final Struct_MizSchState l103_s = as;
										final Struct_MizFraenkelTrm l104_k1 = l77_k1;
										final Struct_MizFraenkelTrm l105_k2 = l86_k2;
										final Func0<Object> l102_$99 = (Func0<Object>)() -> {
											((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
											if (!Module_schemes.f_mizSchEqTyp(l103_s, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l104_k1).f_lambdaArgs, ((int)l97_i.value))), ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l105_k2).f_lambdaArgs, ((int)l97_i.value))))) {
												l96_ok.value = ((Boolean)false);
											} else {
												Module_correl.f_mizSetBoundVar(l0_env, (l0_env).f_boundVarNbr, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l105_k2).f_lambdaArgs, ((int)l97_i.value))));
											}
											l97_i.value = ((Integer)(((int)l97_i.value)+1));
											return null;
										};
										Module_loop.f_while(l100_$98, l102_$99);
										final boolean l106_r = ((((boolean)l96_ok.value)&&Module_schemes.f_mizSchEqTrm(as, (l77_k1).f_scope, (l86_k2).f_scope))&&Module_schemes.f_mizSchEqFrm(as, (l77_k1).f_compr, (l86_k2).f_compr));
										((Field_boundVarNbr)l0_env).set_boundVarNbr(l95_lB);
										return l106_r;
									}
								} else {
									if ((l1_s1==Module_lexicon.g_ikTrmChoice)) {
										Struct_MizChoiceTrm l107_c1;
										Struct l108__tmp = at1;
										switch (l108__tmp.getTypeId()) {
										case 21/*MizChoiceTrm*/: {
											final Struct_MizChoiceTrm l109__tmp = (Struct_MizChoiceTrm)l108__tmp;
											final Struct_MizTyp l110___ = (l109__tmp).f_choiceTyp;
											final int l111___ = (l109__tmp).f_pattNr;
											final int l112___ = (l109__tmp).f_trmInfo;
											l107_c1=l109__tmp;
											break;
										}
										default: {
											l107_c1=((Struct_MizChoiceTrm)Native.failWithError("ch"));
											break;
										}
										}
										Struct_MizChoiceTrm l113_c2;
										Struct l114__tmp = at2;
										switch (l114__tmp.getTypeId()) {
										case 21/*MizChoiceTrm*/: {
											final Struct_MizChoiceTrm l115__tmp = (Struct_MizChoiceTrm)l114__tmp;
											final Struct_MizTyp l116___ = (l115__tmp).f_choiceTyp;
											final int l117___ = (l115__tmp).f_pattNr;
											final int l118___ = (l115__tmp).f_trmInfo;
											l113_c2=l115__tmp;
											break;
										}
										default: {
											l113_c2=((Struct_MizChoiceTrm)Native.failWithError("ch"));
											break;
										}
										}
										return Module_schemes.f_mizSchEqTyp(as, (l107_c1).f_choiceTyp, (l113_c2).f_choiceTyp);
									} else {
										return ((l1_s1==Module_lexicon.g_ikTrmIt)||(l1_s1==Module_lexicon.g_ikError));
									}
								}
							}
						}
					}
				}
			} else {
				if ((l1_s1==Module_lexicon.g_ikTrmInfConst)) {
					final Struct_MizVarTrm l119_v1 = Module_schemes.f_mizSchAsVar(at1);
					{
						final Struct l120___tmp = Module_schemes.f_mizSchExpandInfConst(as, (l119_v1).f_varNr);
						at1 = l120___tmp;
						continue TAIL_CALL;
					}
				} else {
					if ((l2_s2==Module_lexicon.g_ikTrmInfConst)) {
						final Struct_MizVarTrm l121_v2 = Module_schemes.f_mizSchAsVar(at2);
						{
							final Struct l122___tmp = Module_schemes.f_mizSchExpandInfConst(as, (l121_v2).f_varNr);
							at2 = l122___tmp;
							continue TAIL_CALL;
						}
					} else {
						return false;
					}
				}
			}
		}
	}
	}
	public static final boolean f_mizSchEqTrmList(Struct_MizSchState as, Struct al1, Struct al2) {
		final Reference<Struct> l0_c1 = ((Reference<Struct>)(new Reference(al1)));
		final Reference<Struct> l1_c2 = ((Reference<Struct>)(new Reference(al2)));
		final Reference<Boolean> l2_ok = ((Reference<Boolean>)(new Reference(true)));
		final Reference<Boolean> l3_go = ((Reference<Boolean>)(new Reference(true)));
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			return l3_go.value;
		};
		final Struct_MizSchState l8_s = as;
		final Func0<Object> l7_$5 = (Func0<Object>)() -> {
			final Struct l8_gsymswitch5 = l0_c1.value;
			Struct l9__tmp = l8_gsymswitch5;
			switch (l9__tmp.getTypeId()) {
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l10__tmp = (Struct_MizTrmElem)l9__tmp;
				final Struct l11_t1 = l10__tmp.f_trm;
				final Struct l12_n1 = l10__tmp.f_next;
				final Struct l13_t1 = (l10__tmp).f_trm;
				final Struct l14_n1 = (l10__tmp).f_next;
				final Struct l15_gsymswitch6 = l1_c2.value;
				Struct l16__tmp = l15_gsymswitch6;
				switch (l16__tmp.getTypeId()) {
				case 70/*MizTrmElem*/: {
					final Struct_MizTrmElem l17__tmp = (Struct_MizTrmElem)l16__tmp;
					final Struct l18_t2 = l17__tmp.f_trm;
					final Struct l19_n2 = l17__tmp.f_next;
					final Struct l20_t2 = (l17__tmp).f_trm;
					final Struct l21_n2 = (l17__tmp).f_next;
					if (Module_schemes.f_mizSchEqTrm(l8_s, l13_t1, l20_t2)) {
						l0_c1.value = l14_n1;
						l1_c2.value = l21_n2;
						return null;
					} else {
						l2_ok.value = ((Boolean)false);
						l3_go.value = ((Boolean)false);
						return null;
					}
				}
				case 71/*MizTrmNil*/: {
					l2_ok.value = ((Boolean)false);
					l3_go.value = ((Boolean)false);
					return null;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l16__tmp.getTypeName());
				}
			}
			case 71/*MizTrmNil*/: {
				final Struct l22_gsymswitch7 = l1_c2.value;
				Struct l23__tmp = l22_gsymswitch7;
				switch (l23__tmp.getTypeId()) {
				case 70/*MizTrmElem*/: {
					final Struct_MizTrmElem l24__tmp = (Struct_MizTrmElem)l23__tmp;
					final Struct l25___ = (l24__tmp).f_trm;
					final Struct l26___ = (l24__tmp).f_next;
					l2_ok.value = ((Boolean)false);
					break;
				}
				case 71/*MizTrmNil*/: {
					break;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l23__tmp.getTypeName());
				}
				l3_go.value = ((Boolean)false);
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l9__tmp.getTypeName());
			}
		};
		Module_loop.f_while(l6_$4, l7_$5);
		return ((boolean)l2_ok.value);
	}
	public static final boolean f_mizSchEqTyp(Struct_MizSchState as, Struct_MizTyp at1, Struct_MizTyp at2) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		if (((at1).f_sort!=(at2).f_sort)) {
			return false;
		} else {
			if (((at1).f_sort==Module_lexicon.g_ikTypMode)) {
				if (Module_schemes.f_mizSchEqualClusters(as, at1, at2)) {
					final Struct_Pair l1_p1 = Module_correl.f_mizAdjustTyp(l0_env, at1);
					final Struct_Pair l2_p2 = Module_correl.f_mizAdjustTyp(l0_env, at2);
					return ((((int)(l1_p1).f_first)==((int)(l2_p2).f_first))&&Module_schemes.f_mizSchEqTrmList(as, ((Struct)(l1_p1).f_second), ((Struct)(l2_p2).f_second)));
				} else {
					return false;
				}
			} else {
				if (((at1).f_sort==Module_lexicon.g_ikTypStruct)) {
					return ((Module_schemes.f_mizSchEqualClusters(as, at1, at2)&&((at1).f_modNr==(at2).f_modNr))&&Module_schemes.f_mizSchEqTrmList(as, (at1).f_modArgs, (at2).f_modArgs));
				} else {
					return ((at1).f_sort==Module_lexicon.g_ikError);
				}
			}
		}
	}
	public static final boolean f_mizSchEqualClusters(Struct_MizSchState as, Struct_MizTyp at1, Struct_MizTyp at2) {
		return (Module_schemes.f_mizSchIsSubsetOf(as, (at1).f_lower, (at2).f_upper)&&Module_schemes.f_mizSchIsSupersetOf(as, (at1).f_upper, (at2).f_lower));
	}
	public static final Struct f_mizSchExpandInfConst(Struct_MizSchState as, int anr) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		final Struct_MizConstDef l1_cd = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((l0_env).f_inferConstDef, anr));
		final Struct l2_lTrm = Module_correl.f_mizCopyTrm((l1_cd).f_def);
		Module_schemes.f_mizSchFrRenBound(as, l2_lTrm);
		return l2_lTrm;
	}
	public static final Object f_mizSchFrRenBound(Struct_MizSchState as, Struct at) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		if (((l0_env).f_boundVarNbr>0)) {
			final Func2<Struct,Struct_MizWithin, Struct> l2_$1 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al3_0, Struct atrm) -> {
				Struct l4__tmp = atrm;
				switch (l4__tmp.getTypeId()) {
				case 77/*MizVarTrm*/: {
					final Struct_MizVarTrm l5__tmp = (Struct_MizVarTrm)l4__tmp;
					final int l6_srt = l5__tmp.f_sort;
					final int l7_v = l5__tmp.f_varNr;
					final int l8_srt = (l5__tmp).f_sort;
					final int l9_v = (l5__tmp).f_varNr;
					final int l10___ = (l5__tmp).f_pattNr;
					final int l11___ = (l5__tmp).f_trmInfo;
					if ((l8_srt==Module_lexicon.g_ikTrmBound)) {
						final Struct_MizVarTrm l12_vt = Module_schemes.f_mizSchAsVar(l5__tmp);
						((Field_varNr)l12_vt).set_varNr((l9_v+(l0_env).f_boundVarNbr));
					} else {
					}
					return l5__tmp;
				}
				default: {
					return atrm;
				}
				}
			};
			final Struct_MizWithin l3_w = Module_correl.f_mizMakeWithin(l2_$1);
			final Struct l4___t = Module_correl.f_mizWithinTrm(l3_w, at);
			return null;
		} else {
			return null;
		}
	}
	public static final Struct_MizTyp f_mizSchGetTrmType(Struct_MizSchState as, Struct at) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		final int l1_srt = Module_correl.f_mizTrmSort(at);
		if ((((l1_srt==Module_lexicon.g_ikTrmFunctor)||(l1_srt==Module_lexicon.g_ikTrmSelector))||(l1_srt==Module_lexicon.g_ikTrmAggreg))) {
			final Struct_MizTyp l2_lTyp = (l0_env).f_roundUpTrmType.invoke(l0_env, at);
			final Struct_MizAttrColl l3_lCl = Module_correl.f_mizCopyCluster((l2_lTyp).f_upper);
			(l0_env).f_roundUpCluster.invoke(l0_env, l3_lCl, l2_lTyp);
			((Field_upper)l2_lTyp).set_upper(l3_lCl);
			return l2_lTyp;
		} else {
			return Module_correl_inst.f_mizGetTrmType(l0_env, at);
		}
	}
	public static final boolean f_mizSchIsSubsetOf(Struct_MizSchState as, Struct_MizAttrColl afClu, Struct_MizAttrColl aaClu) {
		if ((Module_vector.f_sizeVector((aaClu).f_items)<Module_vector.f_sizeVector((afClu).f_items))) {
			return false;
		} else {
			final Reference<Integer> l0_matched = ((Reference<Integer>)(new Reference(0)));
			final Struct_Vector l3_$2 = (afClu).f_items;
			final Struct_MizAttrColl l5_aClu = aaClu;
			final Struct_MizSchState l6_s = as;
			final Func1<Object,Struct_MizAttr> l4_$1 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa1) -> {
				final Reference<Boolean> l5_hit = ((Reference<Boolean>)(new Reference(false)));
				final Struct_Vector l8_$7 = (l5_aClu).f_items;
				final Func1<Object,Struct_MizAttr> l9_$6 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa2) -> {
					if (!((boolean)l5_hit.value)) {
						final Struct_MizSchSnap l10_sn = Module_schemes.f_schSnap(l6_s);
						if (Module_schemes.f_mizSchEqAttr(l6_s, aa1, aa2)) {
							l5_hit.value = ((Boolean)true);
							l0_matched.value = ((Integer)(((int)l0_matched.value)+1));
							return null;
						} else {
							return Module_schemes.f_schRestore(l6_s, l10_sn);
						}
					} else {
						return null;
					}
				};
				return Module_vector.f_iterVector(l8_$7, ((Func1<Object,Object>)(Func1)l9_$6));
			};
			Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
			return (((int)l0_matched.value)==Module_vector.f_sizeVector((afClu).f_items));
		}
	}
	public static final boolean f_mizSchIsSupersetOf(Struct_MizSchState as, Struct_MizAttrColl afClu, Struct_MizAttrColl aaClu) {
		if ((Module_vector.f_sizeVector((aaClu).f_items)>Module_vector.f_sizeVector((afClu).f_items))) {
			return false;
		} else {
			final Reference<Integer> l0_matched = ((Reference<Integer>)(new Reference(0)));
			final Struct_Vector l3_$2 = (aaClu).f_items;
			final Struct_MizAttrColl l5_fClu = afClu;
			final Struct_MizSchState l6_s = as;
			final Func1<Object,Struct_MizAttr> l4_$1 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa2) -> {
				final Reference<Boolean> l5_hit = ((Reference<Boolean>)(new Reference(false)));
				final Struct_Vector l8_$7 = (l5_fClu).f_items;
				final Func1<Object,Struct_MizAttr> l9_$6 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa1) -> {
					if (!((boolean)l5_hit.value)) {
						final Struct_MizSchSnap l10_sn = Module_schemes.f_schSnap(l6_s);
						if (Module_schemes.f_mizSchEqAttr(l6_s, aa1, aa2)) {
							l5_hit.value = ((Boolean)true);
							l0_matched.value = ((Integer)(((int)l0_matched.value)+1));
							return null;
						} else {
							return Module_schemes.f_schRestore(l6_s, l10_sn);
						}
					} else {
						return null;
					}
				};
				return Module_vector.f_iterVector(l8_$7, ((Func1<Object,Object>)(Func1)l9_$6));
			};
			Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
			return (((int)l0_matched.value)==Module_vector.f_sizeVector((aaClu).f_items));
		}
	}
	public static final Object f_mizSchRenBound(Struct_MizSchState as, Struct at) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		final Struct_MizSchState l3_s = as;
		final Func2<Struct,Struct_MizWithin, Struct> l2_$1 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al3_0, Struct atrm) -> {
			Struct l4__tmp = atrm;
			switch (l4__tmp.getTypeId()) {
			case 77/*MizVarTrm*/: {
				final Struct_MizVarTrm l5__tmp = (Struct_MizVarTrm)l4__tmp;
				final int l6_srt = l5__tmp.f_sort;
				final int l7_v = l5__tmp.f_varNr;
				final int l8_srt = (l5__tmp).f_sort;
				final int l9_v = (l5__tmp).f_varNr;
				final int l10___ = (l5__tmp).f_pattNr;
				final int l11___ = (l5__tmp).f_trmInfo;
				if ((l8_srt==Module_lexicon.g_ikTrmBound)) {
					if ((l9_v>(l0_env).f_boundVarNbr)) {
						final Struct_MizVarTrm l12_vt = Module_schemes.f_mizSchAsVar(l5__tmp);
						((Field_varNr)l12_vt).set_varNr((l9_v-(l0_env).f_boundVarNbr));
					} else {
						((Field_thereAreBound)l3_s).set_thereAreBound(true);
					}
				} else {
				}
				return l5__tmp;
			}
			default: {
				return atrm;
			}
			}
		};
		final Struct_MizWithin l4_w = Module_correl.f_mizMakeWithin(l2_$1);
		final Struct l5___t = Module_correl.f_mizWithinTrm(l4_w, at);
		return null;
	}
	public static final boolean f_mizSchWidennings(Struct_MizSchState as, Struct_MizTyp aaSource, Struct_MizTyp afTarget) {
		final Struct_MizCorrelEnv l0_env = (as).f_env;
		if (!Module_schemes.f_mizSchIsSubsetOf(as, (afTarget).f_lower, (aaSource).f_upper)) {
			return false;
		} else {
			final Struct_MizSchSnap l1_sn = Module_schemes.f_schSnap(as);
			if (Module_schemes.f_mizSchCompTyp(as, afTarget, aaSource)) {
				return true;
			} else {
				if ((((afTarget).f_sort==(aaSource).f_sort)&&((afTarget).f_modNr==(aaSource).f_modNr))) {
					return false;
				} else {
					Module_schemes.f_schRestore(as, l1_sn);
					if (((afTarget).f_sort==Module_lexicon.g_ikTypMode)) {
						final Struct l2_gsymswitch1 = Module_correl_inst.f_mizWidening(l0_env, aaSource);
						Struct l3__tmp = l2_gsymswitch1;
						switch (l3__tmp.getTypeId()) {
						case 82/*None*/: {
							return false;
						}
						case 90/*Some*/: {
							final Struct_Some l4__tmp = (Struct_Some)l3__tmp;
							final Object l5_lTyp0 = l4__tmp.f_value;
							final Struct_MizTyp l6_lTyp0 = ((Struct_MizTyp)(l4__tmp).f_value);
							final Struct l7_gsymswitch2 = Module_correl_inst.f_mizWideningOf(l0_env, afTarget, l6_lTyp0);
							Struct l8__tmp = l7_gsymswitch2;
							switch (l8__tmp.getTypeId()) {
							case 82/*None*/: {
								return false;
							}
							case 90/*Some*/: {
								final Struct_Some l9__tmp = (Struct_Some)l8__tmp;
								final Object l10_wTyp0 = l9__tmp.f_value;
								final Struct_MizTyp l11_wTyp0 = ((Struct_MizTyp)(l9__tmp).f_value);
								Module_schemes.f_schRestore(as, l1_sn);
								if (Module_schemes.f_mizSchCompTyp(as, afTarget, l11_wTyp0)) {
									return true;
								} else {
									if (((Module_correl.f_mizConstr(l0_env, Module_correl.g_coMode, (afTarget).f_modNr)).f_whichConstrNr==0)) {
										final Reference<Boolean> l12_res = ((Reference<Boolean>)(new Reference(false)));
										final Reference<Boolean> l13_stop = ((Reference<Boolean>)(new Reference(false)));
										final Reference<Struct_MizTyp> l14_wTyp = ((Reference<Struct_MizTyp>)(new Reference(l11_wTyp0)));
										final Func0<Boolean> l17_$15 = (Func0<Boolean>)() -> {
											return ((Boolean)!((boolean)l13_stop.value));
										};
										final Struct_MizTyp l19_fTarget = afTarget;
										final Struct_MizSchState l20_s = as;
										final Func0<Object> l18_$16 = (Func0<Object>)() -> {
											final boolean l19_isAny = (((l14_wTyp.value).f_sort==Module_lexicon.g_ikTypMode)&&((l14_wTyp.value).f_modNr==Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqAny)));
											if (l19_isAny) {
												l13_stop.value = ((Boolean)true);
												return null;
											} else {
												final Struct l20_gsymswitch3 = Module_correl_inst.f_mizWidening(l0_env, l14_wTyp.value);
												Struct l21__tmp = l20_gsymswitch3;
												switch (l21__tmp.getTypeId()) {
												case 82/*None*/: {
													l13_stop.value = ((Boolean)true);
													return null;
												}
												case 90/*Some*/: {
													final Struct_Some l22__tmp = (Struct_Some)l21__tmp;
													final Object l23_lTyp = l22__tmp.f_value;
													final Struct_MizTyp l24_lTyp = ((Struct_MizTyp)(l22__tmp).f_value);
													final Struct l25_gsymswitch4 = Module_correl_inst.f_mizWideningOf(l0_env, l19_fTarget, l24_lTyp);
													Struct l26__tmp = l25_gsymswitch4;
													switch (l26__tmp.getTypeId()) {
													case 82/*None*/: {
														l13_stop.value = ((Boolean)true);
														return null;
													}
													case 90/*Some*/: {
														final Struct_Some l27__tmp = (Struct_Some)l26__tmp;
														final Object l28_w2 = l27__tmp.f_value;
														final Struct_MizTyp l29_w2 = ((Struct_MizTyp)(l27__tmp).f_value);
														l14_wTyp.value = l29_w2;
														Module_schemes.f_schRestore(l20_s, l1_sn);
														if (Module_schemes.f_mizSchCompTyp(l20_s, l19_fTarget, l29_w2)) {
															l12_res.value = ((Boolean)true);
															l13_stop.value = ((Boolean)true);
															return null;
														} else {
															return null;
														}
													}
													default:
														throw new RuntimeException("Unexpected struct in switch: "+l26__tmp.getTypeName());
													}
												}
												default:
													throw new RuntimeException("Unexpected struct in switch: "+l21__tmp.getTypeName());
												}
											}
										};
										Module_loop.f_while(l17_$15, l18_$16);
										return ((boolean)l12_res.value);
									} else {
										return false;
									}
								}
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l8__tmp.getTypeName());
							}
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l3__tmp.getTypeName());
						}
					} else {
						if (((afTarget).f_sort==Module_lexicon.g_ikTypStruct)) {
							final Struct l21_gsymswitch0 = Module_correl_inst.f_mizWidenToStruct(l0_env, aaSource);
							Struct l22__tmp = l21_gsymswitch0;
							switch (l22__tmp.getTypeId()) {
							case 82/*None*/: {
								return false;
							}
							case 90/*Some*/: {
								final Struct_Some l23__tmp = (Struct_Some)l22__tmp;
								final Object l24_lTyp = l23__tmp.f_value;
								final Struct_MizTyp l25_lTyp = ((Struct_MizTyp)(l23__tmp).f_value);
								if (((afTarget).f_modNr==(l25_lTyp).f_modNr)) {
									return Module_schemes.f_mizSchCompTyp(as, afTarget, l25_lTyp);
								} else {
									return false;
								}
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l22__tmp.getTypeName());
							}
						} else {
							return false;
						}
					}
				}
			}
		}
	}
	public static final Struct_Pair f_mizSchematizeErr(Struct_MizCorrelEnv aenv, Struct_MizSchemeDef asch, Object[] asnts) {
		if ((!(asch).f_valid||(Native.length((asch).f_schProps)!=Native.length(asnts)))) {
			return (new Struct_Pair(20, (-1)));
		} else {
			((Field_boundVarNbr)aenv).set_boundVarNbr(0);
			final boolean l0_lChk = (aenv).f_itIsChecker;
			((Field_itIsChecker)aenv).set_itIsChecker((Module_url_parameter.f_getUrlParameterDef("sch-round", "1")).equals("1"));
			final Struct_MizSchState l1_s = (new Struct_MizSchState(aenv, (asch).f_schTypes, Module_vector.f_makeVector(8), Module_tree.f_makeTree(), Module_tree.f_makeTree(), false, 20));
			final Reference<Integer> l2_bad = ((Reference<Integer>)(new Reference((-1))));
			final Reference<Integer> l3_i = ((Reference<Integer>)(new Reference(0)));
			final Struct_MizSchemeDef l7_sch = asch;
			final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l2_bad.value)<0)&&(((int)l3_i.value)<Native.length((l7_sch).f_schProps))));
			};
			final Struct_MizSchemeDef l9_sch = asch;
			final Object[] l10_snts = asnts;
			final Func0<Object> l8_$5 = (Func0<Object>)() -> {
				if (!Module_schemes.f_mizSchEqFrm(l1_s, ((Struct)((l9_sch).f_schProps[((int)l3_i.value)])), ((Struct)(l10_snts[((int)l3_i.value)])))) {
					l2_bad.value = l3_i.value;
				} else {
				}
				l3_i.value = ((Integer)(((int)l3_i.value)+1));
				return null;
			};
			Module_loop.f_while(l6_$4, l8_$5);
			((Field_itIsChecker)aenv).set_itIsChecker(l0_lChk);
			if ((((int)l2_bad.value)<0)) {
				return (new Struct_Pair(0, (-1)));
			} else {
				return (new Struct_Pair((l1_s).f_err, l2_bad.value));
			}
		}
	}
	public static final Struct f_mizSchemeFind(Struct_MizSchemes as, int aarticleNr, int anr) {
		final Reference<Struct> l0_res = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
		final Struct_Vector l3_$2 = (as).f_items;
		final int l5_articleNr = aarticleNr;
		final int l6_nr = anr;
		final Func1<Object,Struct_MizSchemeDef> l4_$1 = (Func1<Object, Struct_MizSchemeDef>)(Struct_MizSchemeDef ad) -> {
			if ((((ad).f_articleNr==l5_articleNr)&&((ad).f_nr==l6_nr))) {
				((Reference<Struct_Some>)(Reference)l0_res).value = (new Struct_Some(ad));
				return null;
			} else {
				return null;
			}
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		return l0_res.value;
	}
	public static final Struct f_schConstAt(Struct_MizSchState as, int anr) {
		return ((Struct)Module_vector.f_getVectorDef((as).f_constSubst, anr, SingletonStructs.str_None));
	}
	public static final Object f_schConstSet(Struct_MizSchState as, int anr, Struct at) {
		final Struct_MizSchState l3_s = as;
		final int l4_nr = anr;
		final Func0<Boolean> l2_$0 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector((l3_s).f_constSubst)<=l4_nr);
		};
		final Struct_MizSchState l6_s = as;
		final Func0<Object> l5_$1 = (Func0<Object>)() -> {
			return Module_vector.f_pushVector((l6_s).f_constSubst, SingletonStructs.str_None);
		};
		Module_loop.f_while(l2_$0, l5_$1);
		return Module_vector.f_setVectorUnsafe((as).f_constSubst, anr, (new Struct_Some(at)));
	}
	public static final Object f_schRestore(Struct_MizSchState as, Struct_MizSchSnap asn) {
		Module_vector.f_clearVector((as).f_constSubst);
		final Object[] l2_$1 = (asn).f_consts;
		final Struct_MizSchState l4_s = as;
		final Func1<Object,Struct> l3_$0 = (Func1<Object, Struct>)(Struct ac) -> {
			return Module_vector.f_pushVector((l4_s).f_constSubst, ac);
		};
		Native.iter(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
		((Field_funcSubst)as).set_funcSubst((asn).f_funcs);
		((Field_predSubst)as).set_predSubst((asn).f_preds);
		return null;
	}
	public static final Struct_MizSchSnap f_schSnap(Struct_MizSchState as) {
		return (new Struct_MizSchSnap(Module_vector.f_vector2array((as).f_constSubst), (as).f_funcSubst, (as).f_predSubst));
	}
	public static final int f_skKey(int asort_0, int anr) {
		return ((asort_0*1000000)+anr);
	}
}
