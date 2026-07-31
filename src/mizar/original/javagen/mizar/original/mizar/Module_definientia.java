// HASH COLLISIONS: YES
// timestamp: 1785502214000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_definientia {
	public static final Object[] f_mizAtomicExpansions(Struct_MizCorrelEnv aenv, Struct_MizDefinientia adefs, Struct af) {
		Struct l0_p;
		Struct l1__tmp = af;
		switch (l1__tmp.getTypeId()) {
		case 56/*MizPredFrm*/: {
			final Struct_MizPredFrm l2__tmp = (Struct_MizPredFrm)l1__tmp;
			final int l3_srt = l2__tmp.f_sort;
			final int l4_predNr = l2__tmp.f_predNr;
			final int l5_srt = (l2__tmp).f_sort;
			final int l6_predNr = (l2__tmp).f_predNr;
			final Struct l7___ = (l2__tmp).f_args;
			final int l8___ = (l2__tmp).f_pattNr;
			final Struct_MizPredFrm l9_pf = l2__tmp;
			if ((l5_srt==Module_lexicon.g_ikFrmPred)) {
				final Struct_Pair l10_q = Module_correl.f_mizAdjustFrm(aenv, l9_pf);
				l0_p=(new Struct_Some((new Struct_Pair((new Struct_Pair(Module_lexicon.g_ikFrmPred, (l10_q).f_first)), (l10_q).f_second))));
			} else {
				if ((l5_srt==Module_lexicon.g_ikFrmAttr)) {
					final Struct_Pair l11_q = Module_correl.f_mizAdjustAttrFrm(aenv, l9_pf);
					l0_p=(new Struct_Some((new Struct_Pair((new Struct_Pair(Module_lexicon.g_ikFrmAttr, (l11_q).f_first)), (l11_q).f_second))));
				} else {
					l0_p=((Struct)SingletonStructs.str_None);
				}
			}
			break;
		}
		default: {
			l0_p=((Struct)SingletonStructs.str_None);
			break;
		}
		}
		Struct l12__tmp = l0_p;
		switch (l12__tmp.getTypeId()) {
		case 80/*None*/: {
			return SingletonStructs.arr_empty;
		}
		case 88/*Some*/: {
			final Struct_Some l13__tmp = (Struct_Some)l12__tmp;
			final Object l14_pk = l13__tmp.f_value;
			final Struct_Pair l15_pk = ((Struct_Pair)(l13__tmp).f_value);
			final Reference<Object[]> l16_out = ((Reference<Object[]>)(new Reference(SingletonStructs.arr_empty)));
			final Struct_Vector l19_$18 = (adefs).f_expansions;
			final Struct_MizCorrelEnv l21_env = aenv;
			final Func1<Object,Struct_MizDefiniens> l20_$17 = (Func1<Object, Struct_MizDefiniens>)(Struct_MizDefiniens ad) -> {
				final Struct l21_gsymswitch4 = (ad).f_def;
				boolean l22_okShape;
				Struct l23__tmp = l21_gsymswitch4;
				switch (l23__tmp.getTypeId()) {
				case 88/*Some*/: {
					final Struct_Some l24__tmp = (Struct_Some)l23__tmp;
					final Object l25_def = l24__tmp.f_value;
					final Struct_MizDef l26_def = ((Struct_MizDef)(l24__tmp).f_value);
					l22_okShape=(((((l26_def).f_sort==109)&&(Module_vector.f_sizeVector((l26_def).f_partials)==0))&&(Module_correl.f_mizFrmSort((ad).f_assumptions)==Module_lexicon.g_ikFrmVerum))&&Module_maybe.f_isSome((l26_def).f_otherwise));
					break;
				}
				case 80/*None*/: {
					l22_okShape=false;
					break;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l23__tmp.getTypeName());
				}
				if ((l22_okShape&&Module_definientia.f_mizMatches(l21_env, ((int)(((Struct_Pair)(l15_pk).f_first)).f_first), ((int)(((Struct_Pair)(l15_pk).f_first)).f_second), ((Struct)(l15_pk).f_second), ad))) {
					final Struct l27_gsymswitch5 = (ad).f_def;
					Struct l28__tmp = l27_gsymswitch5;
					switch (l28__tmp.getTypeId()) {
					case 88/*Some*/: {
						final Struct_Some l29__tmp = (Struct_Some)l28__tmp;
						final Object l30_def = l29__tmp.f_value;
						final Struct_MizDef l31_def = ((Struct_MizDef)(l29__tmp).f_value);
						final Struct l32_gsymswitch6 = (l31_def).f_otherwise;
						Struct l33__tmp = l32_gsymswitch6;
						switch (l33__tmp.getTypeId()) {
						case 88/*Some*/: {
							final Struct_Some l34__tmp = (Struct_Some)l33__tmp;
							final Object l35_body = l34__tmp.f_value;
							final Struct l36_body = ((Struct)(l34__tmp).f_value);
							Struct l37__tmp = l36_body;
							switch (l37__tmp.getTypeId()) {
							case 31/*MizDefFrm*/: {
								final Struct_MizDefFrm l38__tmp = (Struct_MizDefFrm)l37__tmp;
								final Struct l39_df = l38__tmp.f_f;
								final Struct l40_df = (l38__tmp).f_f;
								if ((Module_correl.f_mizFrmSort(l40_df)!=Module_lexicon.g_ikError)) {
									final Struct l41_lFrm1 = Wrappers.w_mizCopyFrm.invoke(l40_df);
									final Func2<Struct,Struct_MizWithin, Struct> l43_$42 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al44_0, Struct atrm) -> {
										Struct l45__tmp = atrm;
										switch (l45__tmp.getTypeId()) {
										case 75/*MizVarTrm*/: {
											final Struct_MizVarTrm l46__tmp = (Struct_MizVarTrm)l45__tmp;
											final int l47_s2 = l46__tmp.f_sort;
											final int l48_v2 = l46__tmp.f_varNr;
											final int l49_s2 = (l46__tmp).f_sort;
											final int l50_v2 = (l46__tmp).f_varNr;
											final int l51___ = (l46__tmp).f_pattNr;
											final int l52___ = (l46__tmp).f_trmInfo;
											if ((l49_s2==Module_lexicon.g_ikTrmBound)) {
												final Struct_MizVarTrm l53_vt = l46__tmp;
												((Field_varNr)l53_vt).set_varNr((l50_v2+(l21_env).f_boundVarNbr));
											} else {
											}
											return l46__tmp;
										}
										default: {
											return atrm;
										}
										}
									};
									final Struct_MizWithin l44_w = Module_correl.f_mizMakeWithin(l43_$42);
									Module_correl.f_mizWithinFrm(l44_w, l41_lFrm1);
									l16_out.value = Module_array.f_arrayPush(l16_out.value, Module_correl_match.f_mizInstSubstFrm(l21_env, l41_lFrm1));
									return null;
								} else {
									return null;
								}
							}
							case 32/*MizDefTrm*/: {
								final Struct_MizDefTrm l38__tmp = (Struct_MizDefTrm)l37__tmp;
								final Struct l45___ = (l38__tmp).f_t;
								return null;
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l37__tmp.getTypeName());
							}
						}
						case 80/*None*/: {
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l33__tmp.getTypeName());
						}
					}
					case 80/*None*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l28__tmp.getTypeName());
					}
				} else {
					return Module_correl_match.f_mizDisposeSubstTrm(l21_env);
				}
			};
			Module_vector.f_iterVector(l19_$18, ((Func1<Object,Object>)(Func1)l20_$17));
			Module_correl_match.f_mizDisposeSubstTrm(aenv);
			return l16_out.value;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l12__tmp.getTypeName());
		}
	}
	public static final Object[] f_mizEqualsExpandHook(Struct_MizDefinientia adefs, Struct_MizIdentify aidn, int afuncNr, Struct aargs) {
		final Struct_MizCorrelEnv l0_env = (aidn).f_env;
		final Struct_MizConstr l1_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coFunctor, afuncNr);
		int l2_adjNr;
		if (((l1_c).f_whichConstrNr!=0)) {
			l2_adjNr=(l1_c).f_whichConstrNr;
		} else {
			l2_adjNr=afuncNr;
		}
		Struct l3_adjArgs;
		if (((l1_c).f_whichConstrNr!=0)) {
			l3_adjArgs=Module_correl.f_mizTrmListDrop(aargs, (l1_c).f_superfluous);
		} else {
			l3_adjArgs=aargs;
		}
		if (((aidn).f_equalsExpansionLevel>=3)) {
			return SingletonStructs.arr_empty;
		} else {
			final Object[] l4_eds = ((Object[])Module_tree.f_lookupTreeDef((adefs).f_equalsBy, l2_adjNr, SingletonStructs.arr_empty));
			final Object[] l7_$6 = SingletonStructs.arr_empty;
			final Struct l9_adjArgs = l3_adjArgs;
			final Struct_MizIdentify l10_idn = aidn;
			final Func2<Object[],Object[], Struct_MizEqualsDef> l8_$5 = (Func2<Object[], Object[], Struct_MizEqualsDef>)(Object[] aacc, Struct_MizEqualsDef aed) -> {
				Module_correl_match.f_mizDisposeSubstTrm(l0_env);
				Module_definientia.f_mizInitEssentialsArgs(l0_env, l9_adjArgs, (aed).f_essentials);
				if (Module_correl_match.f_mizCheckLociTypes(l0_env, (aed).f_primaries)) {
					final Struct l9_lTrm0 = Module_correl_match.f_mizInstSubstTrm(l0_env, (aed).f_expansion);
					Module_correl_match.f_mizDisposeSubstTrm(l0_env);
					((Field_equalsExpansionLevel)l10_idn).set_equalsExpansionLevel(((l10_idn).f_equalsExpansionLevel+1));
					final int l10_bSaved = (l0_env).f_boundVarNbr;
					((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
					final Struct l11_lTrm = Module_identify.f_mizCollectConstInTrm(l10_idn, l9_lTrm0);
					((Field_boundVarNbr)l0_env).set_boundVarNbr(l10_bSaved);
					((Field_equalsExpansionLevel)l10_idn).set_equalsExpansionLevel(((l10_idn).f_equalsExpansionLevel-1));
					Struct l12__tmp = l11_lTrm;
					switch (l12__tmp.getTypeId()) {
					case 75/*MizVarTrm*/: {
						final Struct_MizVarTrm l13__tmp = (Struct_MizVarTrm)l12__tmp;
						final int l14_srt = l13__tmp.f_sort;
						final int l15_varNr = l13__tmp.f_varNr;
						final int l16_srt = (l13__tmp).f_sort;
						final int l17_varNr = (l13__tmp).f_varNr;
						final int l18___ = (l13__tmp).f_pattNr;
						final int l19___ = (l13__tmp).f_trmInfo;
						if ((l16_srt==Module_lexicon.g_ikTrmInfConst)) {
							return Module_array.f_arrayPush(aacc, l17_varNr);
						} else {
							return aacc;
						}
					}
					default: {
						return aacc;
					}
					}
				} else {
					Module_correl_match.f_mizDisposeSubstTrm(l0_env);
					return aacc;
				}
			};
			return ((Object[])Native.fold(l4_eds, l7_$6, ((Func2<Object,Object, Object>)(Func2)l8_$5)));
		}
	}
	public static final Struct f_mizEqualsExpansion(Struct_MizCorrelEnv aenv, Struct_MizDefiniens ad) {
		if (((ad).f_constrKind!=Module_lexicon.g_ikTrmFunctor)) {
			return ((Struct)SingletonStructs.str_None);
		} else {
			if ((Module_correl.f_mizFrmSort((ad).f_assumptions)!=Module_lexicon.g_ikFrmVerum)) {
				return ((Struct)SingletonStructs.str_None);
			} else {
				final Struct l0_gsymswitch0 = (ad).f_def;
				Struct l1__tmp = l0_gsymswitch0;
				switch (l1__tmp.getTypeId()) {
				case 80/*None*/: {
					return ((Struct)SingletonStructs.str_None);
				}
				case 88/*Some*/: {
					final Struct_Some l2__tmp = (Struct_Some)l1__tmp;
					final Object l3_def = l2__tmp.f_value;
					final Struct_MizDef l4_def = ((Struct_MizDef)(l2__tmp).f_value);
					if ((((l4_def).f_sort!=101)||(Module_vector.f_sizeVector((l4_def).f_partials)>0))) {
						return ((Struct)SingletonStructs.str_None);
					} else {
						final Struct l5_gsymswitch1 = (l4_def).f_otherwise;
						Struct l6__tmp = l5_gsymswitch1;
						switch (l6__tmp.getTypeId()) {
						case 80/*None*/: {
							return ((Struct)SingletonStructs.str_None);
						}
						case 88/*Some*/: {
							final Struct_Some l7__tmp = (Struct_Some)l6__tmp;
							final Object l8_body = l7__tmp.f_value;
							final Struct l9_body = ((Struct)(l7__tmp).f_value);
							Struct l10__tmp = l9_body;
							switch (l10__tmp.getTypeId()) {
							case 32/*MizDefTrm*/: {
								final Struct_MizDefTrm l11__tmp = (Struct_MizDefTrm)l10__tmp;
								final Struct l12_t = l11__tmp.f_t;
								final Struct l13_t = (l11__tmp).f_t;
								final Struct_Vector l14_prim = Module_correl.f_mizCopyTypVector((ad).f_primaries);
								if ((Module_vector.f_sizeVector(l14_prim)>0)) {
									Module_vector.f_removeVector(l14_prim, (Module_vector.f_sizeVector(l14_prim)-1));
								} else {
								}
								final Struct_MizIntSeq l15_ess = Module_mobjects.f_mizCopyIntSeq((ad).f_essentials);
								if ((Module_mobjects.f_mizSeqCount(l15_ess)>0)) {
									Module_mobjects.f_mizSeqAtDelete(l15_ess, (Module_mobjects.f_mizSeqCount(l15_ess)-1));
								} else {
								}
								return (new Struct_Some((new Struct_MizEqualsDef(l14_prim, l15_ess, Module_correl.f_mizCopyTrm(l13_t)))));
							}
							case 31/*MizDefFrm*/: {
								final Struct_MizDefFrm l11__tmp = (Struct_MizDefFrm)l10__tmp;
								final Struct l16___ = (l11__tmp).f_f;
								return ((Struct)SingletonStructs.str_None);
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
							}
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l6__tmp.getTypeName());
						}
					}
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l1__tmp.getTypeName());
				}
			}
		}
	}
	public static final Struct f_mizEqualsExpansionPub(Struct_MizCorrelEnv aenv, Struct_MizDefiniens ad) {
		return Module_definientia.f_mizEqualsExpansion(aenv, ad);
	}
	public static final Struct_MizDef f_mizInDef(Struct_MizVrfReader ar) {
		final Struct_MizXmlParser l0_x = (ar).f_x;
		final String l1_kindS = Module_xml_parser.f_mizXmlAttr(l0_x, "kind");
		int l2_srt;
		if ((Native.strlen(l1_kindS)>0)) {
			l2_srt=Native.getCharCodeAt(l1_kindS, 0);
		} else {
			l2_srt=109;
		}
		Module_xml_parser.f_mizXmlNextElementState(l0_x);
		final Struct_Vector l3_partials = Module_vector.f_makeVector(2);
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&((l0_x).f_nElName).equals("PartialDef")));
		};
		final Struct_MizVrfReader l8_r = ar;
		final int l9_srt = l2_srt;
		final Func0<Object> l7_$5 = (Func0<Object>)() -> {
			Module_xml_parser.f_mizXmlNextElementState(l0_x);
			final Struct l8_body = Module_definientia.f_mizInDefBody(l8_r, l9_srt);
			final Struct l9_guard = Module_iocorrel.f_mizInFormula(l8_r);
			Module_vector.f_pushVector(l3_partials, (new Struct_MizPartDef(l8_body, l9_guard)));
			return Module_xml_parser.f_mizXmlNextElementState(l0_x);
		};
		Module_loop.f_while(l6_$4, l7_$5);
		Struct l10_otherwise;
		if ((((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&(Module_iocorrel.f_mizIsFrmEl((l0_x).f_nElName)||Module_iocorrel.f_mizIsTermEl((l0_x).f_nElName)))) {
			l10_otherwise=(new Struct_Some(Module_definientia.f_mizInDefBody(ar, l2_srt)));
		} else {
			l10_otherwise=((Struct)SingletonStructs.str_None);
		}
		Module_xml_parser.f_mizXmlNextElementState(l0_x);
		return (new Struct_MizDef(l2_srt, l3_partials, l10_otherwise));
	}
	public static final Struct f_mizInDefBody(Struct_MizVrfReader ar, int asrt) {
		if ((asrt==109)) {
			return (new Struct_MizDefFrm(Module_iocorrel.f_mizInFormula(ar)));
		} else {
			return (new Struct_MizDefTrm(Module_iocorrel.f_mizInTerm(ar)));
		}
	}
	public static final Struct_MizDefiniens f_mizInDefiniens(Struct_MizVrfReader ar) {
		final Struct_MizXmlParser l0_x = (ar).f_x;
		Module_xml_parser.f_mizXmlAssert(((l0_x).f_nElName).equals("Definiens"));
		final String l1_kindS = Module_xml_parser.f_mizXmlAttr(l0_x, "constrkind");
		int l2_kind;
		if ((Native.strlen(l1_kindS)>0)) {
			l2_kind=Native.getCharCodeAt(l1_kindS, 0);
		} else {
			l2_kind=0;
		}
		final int l3_nr = Module_xml_parser.f_mizXmlIntAttrDef(l0_x, "constrnr", 0);
		final String l4_article = Module_xml_parser.f_mizXmlAttr(l0_x, "aid");
		final int l5_defNr = Module_xml_parser.f_mizXmlIntAttrDef(l0_x, "defnr", 0);
		Module_xml_parser.f_mizXmlNextElementState(l0_x);
		final Struct_Vector l6_primaries = Module_iocorrel.f_mizInTypeColl(ar);
		final Struct_MizIntSeq l7_essentials = Module_definientia.f_mizInIntSeqEl(ar, "Essentials");
		Struct l8_assumptions;
		if ((((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&Module_iocorrel.f_mizIsFrmEl((l0_x).f_nElName))) {
			l8_assumptions=Module_iocorrel.f_mizInFormula(ar);
		} else {
			l8_assumptions=Module_correl.f_mizNewVerum();
		}
		Struct l9_def;
		if ((((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&((l0_x).f_nElName).equals("DefMeaning"))) {
			l9_def=(new Struct_Some(Module_definientia.f_mizInDef(ar)));
		} else {
			l9_def=((Struct)SingletonStructs.str_None);
		}
		Module_xml_parser.f_mizXmlNextElementState(l0_x);
		return (new Struct_MizDefiniens(l2_kind, l3_nr, l4_article, l5_defNr, l6_primaries, l7_essentials, l8_assumptions, l9_def));
	}
	public static final Struct_MizIntSeq f_mizInIntSeqEl(Struct_MizVrfReader ar, String aelName) {
		final Struct_MizXmlParser l0_x = (ar).f_x;
		final Struct_MizIntSeq l1_s = Module_mobjects.f_mizInitIntSeq(4);
		if ((((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&((l0_x).f_nElName).equals(aelName))) {
			Module_xml_parser.f_mizXmlNextElementState(l0_x);
			final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
				return ((l0_x).f_nState==Module_xml_parser.g_MizEStart);
			};
			final Func0<Object> l5_$3 = (Func0<Object>)() -> {
				if (((l0_x).f_nElName).equals("Int")) {
					final int l6___i = Module_mobjects.f_mizSeqInsert(l1_s, Module_xml_parser.f_mizXmlIntAttrDef(l0_x, "x", 0));
				} else {
				}
				return Module_xml_parser.f_mizXmlSkipElement(l0_x);
			};
			Module_loop.f_while(l4_$2, l5_$3);
			Module_xml_parser.f_mizXmlNextElementState(l0_x);
		} else {
		}
		return l1_s;
	}
	public static final Object f_mizInitEssentialsArgs(Struct_MizCorrelEnv aenv, Struct aargs, Struct_MizIntSeq aess) {
		final Reference<Struct> l0_cur = ((Reference<Struct>)(new Reference(aargs)));
		final int l3_$2 = (Module_mobjects.f_mizSeqCount(aess)-1);
		final Struct_MizCorrelEnv l5_env = aenv;
		final Struct_MizIntSeq l6_ess = aess;
		final Func1<Object,Integer> l4_$1 = (Func1<Object, Integer>)(Integer ak) -> {
			final Struct l5_gsymswitch3 = l0_cur.value;
			Struct l6__tmp = l5_gsymswitch3;
			switch (l6__tmp.getTypeId()) {
			case 68/*MizTrmElem*/: {
				final Struct_MizTrmElem l7__tmp = (Struct_MizTrmElem)l6__tmp;
				final Struct l8_trm = l7__tmp.f_trm;
				final Struct l9_next = l7__tmp.f_next;
				final Struct l10_trm = (l7__tmp).f_trm;
				final Struct l11_next = (l7__tmp).f_next;
				Module_correl_match.f_mizSubstTrmSet(l5_env, Module_mobjects.f_mizSeqValue(l6_ess, ((int)ak)), (new Struct_Some(l10_trm)));
				l0_cur.value = l11_next;
				return null;
			}
			case 69/*MizTrmNil*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l6__tmp.getTypeName());
			}
		};
		return Module_runtime.f_fori(0, l3_$2, l4_$1);
	}
	public static final Object f_mizLoadDefinientia(Struct_MizCorrelEnv aenv, Struct_MizDefinientia adefs, String ammlDir, String aarticle) {
		Module_definientia.f_mizLoadDefsFile(aenv, (((ammlDir+"/")+aarticle)+".dfs"), (adefs).f_all);
		Module_definientia.f_mizLoadDefsFile(aenv, (((ammlDir+"/")+aarticle)+".dfx"), (adefs).f_expansions);
		final Struct_Vector l0_eqRaw = Module_vector.f_makeVector(8);
		Module_definientia.f_mizLoadDefsFile(aenv, (((ammlDir+"/")+aarticle)+".dfe"), l0_eqRaw);
		final Struct_MizCorrelEnv l2_env = aenv;
		final Struct_MizDefinientia l3_defs = adefs;
		final Func1<Object,Struct_MizDefiniens> l1_addEq = (Func1<Object, Struct_MizDefiniens>)(Struct_MizDefiniens ad) -> {
			final Struct l2_gsymswitch2 = Module_definientia.f_mizEqualsExpansion(l2_env, ad);
			Struct l3__tmp = l2_gsymswitch2;
			switch (l3__tmp.getTypeId()) {
			case 88/*Some*/: {
				final Struct_Some l4__tmp = (Struct_Some)l3__tmp;
				final Object l5_ed = l4__tmp.f_value;
				final Struct_MizEqualsDef l6_ed = ((Struct_MizEqualsDef)(l4__tmp).f_value);
				final int l7_key = (ad).f_constrNr;
				final Object[] l8_cur = ((Object[])Module_tree.f_lookupTreeDef((l3_defs).f_equalsBy, l7_key, SingletonStructs.arr_empty));
				((Field_equalsBy)l3_defs).set_equalsBy(Module_tree.f_setTree((l3_defs).f_equalsBy, l7_key, Module_array.f_arrayPush(l8_cur, l6_ed)));
				return null;
			}
			case 80/*None*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l3__tmp.getTypeName());
			}
		};
		return Module_vector.f_iterVector(l0_eqRaw, ((Func1<Object,Object>)(Func1)l1_addEq));
	}
	public static final Object f_mizLoadDefsFile(Struct_MizCorrelEnv aenv, String apath, Struct_Vector ainto) {
		if (FlowFileSystem.fileExists(apath)) {
			final Struct_MizVrfReader l0_r = Module_iocorrel.f_mizMakeVrfReader(Module_xml_parser.f_mizXmlInitParsing(Native.getFileContent(apath)), aenv);
			((Field_mml)l0_r).set_mml(true);
			final Struct_MizXmlParser l1_x = (l0_r).f_x;
			Module_xml_parser.f_mizXmlNextElementState(l1_x);
			Module_xml_parser.f_mizXmlAssert(((l1_x).f_nElName).equals("Definientia"));
			Module_xml_parser.f_mizXmlNextElementState(l1_x);
			final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
				return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
			};
			final Struct_Vector l6_into = ainto;
			final Func0<Object> l5_$3 = (Func0<Object>)() -> {
				if (((l1_x).f_nElName).equals("Definiens")) {
					return Module_vector.f_pushVector(l6_into, Module_definientia.f_mizInDefiniens(l0_r));
				} else {
					return Module_xml_parser.f_mizXmlSkipElement(l1_x);
				}
			};
			return Module_loop.f_while(l4_$2, l5_$3);
		} else {
			return null;
		}
	}
	public static final Struct_MizDefinientia f_mizMakeDefinientia() {
		return (new Struct_MizDefinientia(Module_vector.f_makeVector(16), Module_vector.f_makeVector(16), Module_tree.f_makeTree()));
	}
	public static final boolean f_mizMatches(Struct_MizCorrelEnv aenv, int akind, int anr, Struct aargs, Struct_MizDefiniens ad) {
		Module_correl_match.f_mizDisposeSubstTrm(aenv);
		if (((akind!=(ad).f_constrKind)||(anr!=(ad).f_constrNr))) {
			return false;
		} else {
			if ((Module_mobjects.f_mizSeqCount((ad).f_essentials)==0)) {
				return true;
			} else {
				Module_definientia.f_mizInitEssentialsArgs(aenv, aargs, (ad).f_essentials);
				return Module_correl_match.f_mizCheckLociTypes(aenv, (ad).f_primaries);
			}
		}
	}
}
