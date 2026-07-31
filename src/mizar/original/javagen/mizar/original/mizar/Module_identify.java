// HASH COLLISIONS: YES
// timestamp: 1785510018000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_identify {
	public static final Struct_MizComplex f_mizCInv(Struct_MizComplex av) {
		final double l0_d = (((av).f_re*(av).f_re)+((av).f_im*(av).f_im));
		return (new Struct_MizComplex(((av).f_re/l0_d), ((-(av).f_im)/l0_d)));
	}
	public static final Struct_MizAttrColl f_mizCollectConstInCluster(Struct_MizIdentify aidn, Struct_MizAttrColl ac) {
		final Struct_MizAttrColl l0_res = Module_correl.f_mizNewEmptyCluster();
		final Struct_Vector l3_$2 = (ac).f_items;
		final Struct_MizIdentify l5_idn = aidn;
		final Func1<Object,Struct_MizAttr> l4_$1 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			final Struct_MizAttr l5_na = (new Struct_MizAttr((aa).f_neg, (aa).f_attrNr, Module_correl.f_mizCopyTrmList((aa).f_args), false, (aa).f_pattNr));
			Module_identify.f_mizCollectConstInTrmList(l5_idn, (l5_na).f_args);
			return Module_correl.f_mizAttrCollInsert((l5_idn).f_env, l0_res, l5_na);
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		((Field_consistent)l0_res).set_consistent(((l0_res).f_consistent&&(ac).f_consistent));
		return l0_res;
	}
	public static final Object f_mizCollectConstInFrm(Struct_MizIdentify aidn, Struct af) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (aidn).f_env;
		Struct l1__tmp = af;
		switch (l1__tmp.getTypeId()) {
		case 56/*MizPredFrm*/: {
			final Struct_MizPredFrm l2__tmp = (Struct_MizPredFrm)l1__tmp;
			final Struct l3_args = l2__tmp.f_args;
			final int l4___ = (l2__tmp).f_sort;
			final int l5___ = (l2__tmp).f_predNr;
			final Struct l6_args = (l2__tmp).f_args;
			final int l7___ = (l2__tmp).f_pattNr;
			return Module_identify.f_mizCollectConstInTrmList(aidn, l6_args);
		}
		case 49/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l2__tmp = (Struct_MizLocPredFrm)l1__tmp;
			final Struct l8_args = l2__tmp.f_args;
			final Struct l9_expn = l2__tmp.f_predExp;
			final int l10___ = (l2__tmp).f_predNr;
			final Struct l11_args = (l2__tmp).f_args;
			final Struct l12_expn = (l2__tmp).f_predExp;
			final int l13___ = (l2__tmp).f_pattNr;
			Module_identify.f_mizCollectConstInTrmList(aidn, l11_args);
			{
				final Struct l14___tmp = l12_expn;
				af = l14___tmp;
				continue TAIL_CALL;
			}
		}
		case 61/*MizQualFrm*/: {
			final Struct_MizQualFrm l2__tmp = (Struct_MizQualFrm)l1__tmp;
			final Struct l15_trm = l2__tmp.f_trm;
			final Struct_MizTyp l16_typ = l2__tmp.f_typ;
			final Struct l17_trm = (l2__tmp).f_trm;
			final Struct_MizTyp l18_typ = (l2__tmp).f_typ;
			final int l19___ = (l2__tmp).f_pattNr;
			final Struct_MizQualFrm l20_qf = l2__tmp;
			((Field_trm)l20_qf).set_trm(Module_identify.f_mizCollectConstInTrm(aidn, l17_trm));
			return Module_identify.f_mizCollectConstInTyp(aidn, l18_typ);
		}
		case 51/*MizNegFrm*/: {
			final Struct_MizNegFrm l2__tmp = (Struct_MizNegFrm)l1__tmp;
			final Struct l21_arg = l2__tmp.f_arg;
			final Struct l22_arg = (l2__tmp).f_arg;
			final int l23___ = (l2__tmp).f_pattNr;
			{
				final Struct l24___tmp = l22_arg;
				af = l24___tmp;
				continue TAIL_CALL;
			}
		}
		case 17/*MizBinFrm*/: {
			final Struct_MizBinFrm l2__tmp = (Struct_MizBinFrm)l1__tmp;
			final Struct l25_l = l2__tmp.f_left;
			final Struct l26_r = l2__tmp.f_right;
			final int l27___ = (l2__tmp).f_sort;
			final Struct l28_l = (l2__tmp).f_left;
			final Struct l29_r = (l2__tmp).f_right;
			final int l30___ = (l2__tmp).f_pattNr;
			Module_identify.f_mizCollectConstInFrm(aidn, l28_l);
			{
				final Struct l31___tmp = l29_r;
				af = l31___tmp;
				continue TAIL_CALL;
			}
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l2__tmp = (Struct_MizConjFrm)l1__tmp;
			final Struct_Vector l32_conjuncts = l2__tmp.f_conjuncts;
			final Struct_Vector l33_conjuncts = (l2__tmp).f_conjuncts;
			final int l34___ = (l2__tmp).f_pattNr;
			final Struct_MizIdentify l37_idn = aidn;
			final Func1<Object,Struct> l36_$35 = (Func1<Object, Struct>)(Struct ag) -> {
				return Module_identify.f_mizCollectConstInFrm(l37_idn, ag);
			};
			return Module_vector.f_iterVector(l33_conjuncts, ((Func1<Object,Object>)(Func1)l36_$35));
		}
		case 74/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l2__tmp = (Struct_MizUnivFrm)l1__tmp;
			final Struct_MizTyp l38_quantified = l2__tmp.f_quantified;
			final Struct l39_scope = l2__tmp.f_scope;
			final int l40___ = (l2__tmp).f_sort;
			final int l41___ = (l2__tmp).f_varId;
			final Struct_MizTyp l42_quantified = (l2__tmp).f_quantified;
			final Struct l43_scope = (l2__tmp).f_scope;
			final int l44___ = (l2__tmp).f_pattNr;
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
			Module_identify.f_mizCollectConstInTyp(aidn, l42_quantified);
			Module_identify.f_mizCollectConstInFrm(aidn, l43_scope);
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-1));
			return null;
		}
		case 73/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l2__tmp = (Struct_MizUniqFrm)l1__tmp;
			final int l45___ = (l2__tmp).f_sort;
			return null;
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l2__tmp = (Struct_MizFlexFrm)l1__tmp;
			final Struct l46_lo = l2__tmp.f_leftOrig;
			final Struct l47_ro = l2__tmp.f_rightOrig;
			final Struct l48_expn = l2__tmp.f_expansion;
			final int l49___ = (l2__tmp).f_sort;
			final Struct l50_lo = (l2__tmp).f_leftOrig;
			final Struct l51_ro = (l2__tmp).f_rightOrig;
			final Struct l52___ = (l2__tmp).f_leftTrm;
			final Struct l53___ = (l2__tmp).f_rightTrm;
			final Struct l54_expn = (l2__tmp).f_expansion;
			final int l55___ = (l2__tmp).f_pattNr;
			Module_identify.f_mizCollectConstInFrm(aidn, l50_lo);
			Module_identify.f_mizCollectConstInFrm(aidn, l51_ro);
			{
				final Struct l56___tmp = l54_expn;
				af = l56___tmp;
				continue TAIL_CALL;
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l1__tmp.getTypeName());
		}
	}
	}
	public static final Struct f_mizCollectConstInTrm(Struct_MizIdentify aidn, Struct at) {
		final Struct_MizCorrelEnv l0_env = (aidn).f_env;
		final boolean l1_lOnly = (aidn).f_onlyConstants;
		((Field_onlyConstants)aidn).set_onlyConstants(true);
		final int l2_lLevel = (aidn).f_equalsExpansionLevel;
		((Field_equalsExpansionLevel)aidn).set_equalsExpansionLevel(0);
		Struct l3_res;
		Struct l4__tmp = at;
		switch (l4__tmp.getTypeId()) {
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l5__tmp = (Struct_MizFuncTrm)l4__tmp;
			final int l6_srt = l5__tmp.f_sort;
			final int l7_funcNr = l5__tmp.f_funcNr;
			final Struct l8_args = l5__tmp.f_args;
			final int l9_srt = (l5__tmp).f_sort;
			final int l10_funcNr = (l5__tmp).f_funcNr;
			final Struct l11_args = (l5__tmp).f_args;
			final int l12___ = (l5__tmp).f_pattNr;
			final int l13___ = (l5__tmp).f_trmInfo;
			Module_identify.f_mizCollectConstInTrmList(aidn, l11_args);
			if ((l9_srt==Module_lexicon.g_ikTrmFunctor)) {
				if ((aidn).f_onlyConstants) {
					final Struct_Pair l14_p = Module_identify.f_mizInferSearch(aidn, l5__tmp);
					if ((((int)(l14_p).f_first)>=0)) {
						l3_res=Module_correl.f_mizNewVarTrm(Module_lexicon.g_ikTrmInfConst, ((int)(l14_p).f_first));
					} else {
						final Struct l15_it = Module_identify.f_mizCollectInferConst(aidn, l5__tmp);
						((Field_equalsExpansionLevel)aidn).set_equalsExpansionLevel(l2_lLevel);
						Struct l16__tmp = l15_it;
						switch (l16__tmp.getTypeId()) {
						case 75/*MizVarTrm*/: {
							final Struct_MizVarTrm l17__tmp = (Struct_MizVarTrm)l16__tmp;
							final int l18_varNr = l17__tmp.f_varNr;
							final int l19___ = (l17__tmp).f_sort;
							final int l20_varNr = (l17__tmp).f_varNr;
							final int l21___ = (l17__tmp).f_pattNr;
							final int l22___ = (l17__tmp).f_trmInfo;
							final Struct_MizConstDef l23_cd = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((l0_env).f_inferConstDef, l20_varNr));
							final Object[] l24_eqs = (aidn).f_equalsExpand.invoke(aidn, ((Integer)l10_funcNr), l11_args);
							final Func1<Object,Integer> l26_$25 = (Func1<Object, Integer>)(Integer ae) -> {
								return Module_mobjects.f_mizNatInsertElem((l23_cd).f_eqConst, ((int)ae));
							};
							Native.iter(l24_eqs, ((Func1<Object,Object>)(Func1)l26_$25));
							final Object[] l27_ids = Module_identify.f_mizIdentifyEqConsts(aidn, l5__tmp);
							final Func1<Object,Integer> l29_$28 = (Func1<Object, Integer>)(Integer ae) -> {
								return Module_mobjects.f_mizNatInsertElem((l23_cd).f_eqConst, ((int)ae));
							};
							Native.iter(l27_ids, ((Func1<Object,Object>)(Func1)l29_$28));
							break;
						}
						default: {
							break;
						}
						}
						l3_res=l15_it;
					}
				} else {
					l3_res=l5__tmp;
				}
			} else {
				l3_res=Module_identify.f_mizCollectInferConst(aidn, l5__tmp);
			}
			break;
		}
		case 59/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l5__tmp = (Struct_MizPrivFuncTrm)l4__tmp;
			final Struct l30_args = l5__tmp.f_args;
			final Struct l31_funcExp = l5__tmp.f_funcExp;
			final int l32___ = (l5__tmp).f_funcNr;
			final Struct l33_args = (l5__tmp).f_args;
			final Struct l34_funcExp = (l5__tmp).f_funcExp;
			final int l35___ = (l5__tmp).f_pattNr;
			final int l36___ = (l5__tmp).f_trmInfo;
			Module_identify.f_mizCollectConstInTrmList(aidn, l33_args);
			final Struct_MizPrivFuncTrm l37_pt = l5__tmp;
			((Field_funcExp)l37_pt).set_funcExp(Module_identify.f_mizCollectConstInTrm(aidn, l34_funcExp));
			l3_res=l5__tmp;
			break;
		}
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l5__tmp = (Struct_MizFraenkelTrm)l4__tmp;
			final Struct_Vector l38_lambdaArgs = l5__tmp.f_lambdaArgs;
			final Struct l39_scope = l5__tmp.f_scope;
			final Struct l40_compr = l5__tmp.f_compr;
			final Struct_Vector l41_lambdaArgs = (l5__tmp).f_lambdaArgs;
			final Struct l42_scope = (l5__tmp).f_scope;
			final Struct l43_compr = (l5__tmp).f_compr;
			final Struct_MizIntSeq l44___ = (l5__tmp).f_idents;
			final int l45___ = (l5__tmp).f_pattNr;
			final int l46___ = (l5__tmp).f_trmInfo;
			final Struct_MizIdentify l49_idn = aidn;
			final Func1<Object,Struct_MizTyp> l48_$47 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp atyp) -> {
				((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
				return Module_identify.f_mizCollectConstInTyp(l49_idn, atyp);
			};
			Module_vector.f_iterVector(l41_lambdaArgs, ((Func1<Object,Object>)(Func1)l48_$47));
			final Struct_MizFraenkelTrm l50_ft = l5__tmp;
			((Field_scope)l50_ft).set_scope(Module_identify.f_mizCollectConstInTrm(aidn, l42_scope));
			Module_identify.f_mizCollectConstInFrm(aidn, l43_compr);
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-Module_vector.f_sizeVector(l41_lambdaArgs)));
			final Reference<Boolean> l51_thereAreBound = ((Reference<Boolean>)(new Reference(false)));
			final Func2<Struct,Struct_MizWithin, Struct> l53_$52 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al54_0, Struct atrm) -> {
				Struct l55__tmp = atrm;
				switch (l55__tmp.getTypeId()) {
				case 75/*MizVarTrm*/: {
					final Struct_MizVarTrm l56__tmp = (Struct_MizVarTrm)l55__tmp;
					final int l57_s2 = l56__tmp.f_sort;
					final int l58_v2 = l56__tmp.f_varNr;
					final int l59_s2 = (l56__tmp).f_sort;
					final int l60_v2 = (l56__tmp).f_varNr;
					final int l61___ = (l56__tmp).f_pattNr;
					final int l62___ = (l56__tmp).f_trmInfo;
					if (((l59_s2==Module_lexicon.g_ikTrmBound)&&(l60_v2<=(l0_env).f_boundVarNbr))) {
						l51_thereAreBound.value = ((Boolean)true);
					} else {
					}
					return l56__tmp;
				}
				default: {
					return atrm;
				}
				}
			};
			final Struct_MizWithin l54_w = Module_correl.f_mizMakeWithin(l53_$52);
			final Struct l55___t2 = Module_correl.f_mizWithinTrm(l54_w, l5__tmp);
			((Field_onlyConstants)aidn).set_onlyConstants(!((boolean)l51_thereAreBound.value));
			if ((aidn).f_onlyConstants) {
				final Func2<Struct,Struct_MizWithin, Struct> l57_$56 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al58_0, Struct atrm) -> {
					Struct l59__tmp = atrm;
					switch (l59__tmp.getTypeId()) {
					case 75/*MizVarTrm*/: {
						final Struct_MizVarTrm l60__tmp = (Struct_MizVarTrm)l59__tmp;
						final int l61_s2 = l60__tmp.f_sort;
						final int l62_v2 = l60__tmp.f_varNr;
						final int l63_s2 = (l60__tmp).f_sort;
						final int l64_v2 = (l60__tmp).f_varNr;
						final int l65___ = (l60__tmp).f_pattNr;
						final int l66___ = (l60__tmp).f_trmInfo;
						if (((l63_s2==Module_lexicon.g_ikTrmBound)&&(l64_v2>(l0_env).f_boundVarNbr))) {
							final Struct_MizVarTrm l67_vt = Module_identify.f_mizIdAsVar(l60__tmp);
							((Field_varNr)l67_vt).set_varNr((l64_v2-(l0_env).f_boundVarNbr));
						} else {
						}
						return l60__tmp;
					}
					default: {
						return atrm;
					}
					}
				};
				final Struct_MizWithin l58_w2 = Module_correl.f_mizMakeWithin(l57_$56);
				final Struct l59___t3 = Module_correl.f_mizWithinTrm(l58_w2, l5__tmp);
				l3_res=Module_identify.f_mizCollectInferConst(aidn, l5__tmp);
			} else {
				l3_res=l5__tmp;
			}
			break;
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l5__tmp = (Struct_MizChoiceTrm)l4__tmp;
			final Struct_MizTyp l60_typ = l5__tmp.f_choiceTyp;
			final Struct_MizTyp l61_typ = (l5__tmp).f_choiceTyp;
			final int l62___ = (l5__tmp).f_pattNr;
			final int l63___ = (l5__tmp).f_trmInfo;
			Module_identify.f_mizCollectConstInTyp(aidn, l61_typ);
			l3_res=Module_identify.f_mizCollectInferConst(aidn, l5__tmp);
			break;
		}
		case 75/*MizVarTrm*/: {
			final Struct_MizVarTrm l5__tmp = (Struct_MizVarTrm)l4__tmp;
			final int l64_srt = l5__tmp.f_sort;
			final int l65_varNr = l5__tmp.f_varNr;
			final int l66_srt = (l5__tmp).f_sort;
			final int l67_varNr = (l5__tmp).f_varNr;
			final int l68___ = (l5__tmp).f_pattNr;
			final int l69___ = (l5__tmp).f_trmInfo;
			if ((l66_srt==Module_lexicon.g_ikTrmNumeral)) {
				l3_res=Module_identify.f_mizCollectInferConst(aidn, l5__tmp);
			} else {
				if ((l66_srt==Module_lexicon.g_ikTrmConstant)) {
					final Struct_MizNatFunc l70_eqSeeds = Module_mobjects.f_mizInitNatSet(4);
					final Struct_MizFixedVar l71_fv = ((Struct_MizFixedVar)Module_vector.f_getVectorUnsafe((l0_env).f_fixedVar, l67_varNr));
					final Struct l72_gsymswitch1 = (l71_fv).f_def;
					Struct l73__tmp = l72_gsymswitch1;
					switch (l73__tmp.getTypeId()) {
					case 88/*Some*/: {
						final Struct_Some l74__tmp = (Struct_Some)l73__tmp;
						final Object l75_d = l74__tmp.f_value;
						final Struct l76_d = ((Struct)(l74__tmp).f_value);
						if ((Module_correl.f_mizTrmSort(l76_d)!=Module_lexicon.g_ikError)) {
							final Struct l77_lTrm0 = Module_correl_inst.f_mizCopyExpTrm(l76_d);
							final Struct l78_lTrm = Module_identify.f_mizCollectConstInTrm(aidn, l77_lTrm0);
							if ((aidn).f_onlyConstants) {
								Module_errhan.f_mizAssert(4382, (Module_correl.f_mizTrmSort(l78_lTrm)==Module_lexicon.g_ikTrmInfConst));
								Struct l79__tmp = l78_lTrm;
								switch (l79__tmp.getTypeId()) {
								case 75/*MizVarTrm*/: {
									final Struct_MizVarTrm l80__tmp = (Struct_MizVarTrm)l79__tmp;
									final int l81_dn = l80__tmp.f_varNr;
									final int l82___ = (l80__tmp).f_sort;
									final int l83_dn = (l80__tmp).f_varNr;
									final int l84___ = (l80__tmp).f_pattNr;
									final int l85___ = (l80__tmp).f_trmInfo;
									Module_mobjects.f_mizNatInsertElem(l70_eqSeeds, l83_dn);
									break;
								}
								default: {
									break;
								}
								}
							} else {
							}
							((Field_onlyConstants)aidn).set_onlyConstants(true);
						} else {
						}
						break;
					}
					case 80/*None*/: {
						break;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l73__tmp.getTypeName());
					}
					final Struct l86_it = Module_identify.f_mizCollectInferConst(aidn, l5__tmp);
					Struct l87__tmp = l86_it;
					switch (l87__tmp.getTypeId()) {
					case 75/*MizVarTrm*/: {
						final Struct_MizVarTrm l88__tmp = (Struct_MizVarTrm)l87__tmp;
						final int l89_s3 = l88__tmp.f_sort;
						final int l90_nr3 = l88__tmp.f_varNr;
						final int l91_s3 = (l88__tmp).f_sort;
						final int l92_nr3 = (l88__tmp).f_varNr;
						final int l93___ = (l88__tmp).f_pattNr;
						final int l94___ = (l88__tmp).f_trmInfo;
						if ((l91_s3==Module_lexicon.g_ikTrmInfConst)) {
							final Struct_MizConstDef l95_cd = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((l0_env).f_inferConstDef, l92_nr3));
							Module_mobjects.f_mizNatEnlargeBy((l95_cd).f_eqConst, l70_eqSeeds);
							if ((Module_mobjects.f_mizNatCount(l70_eqSeeds)>0)) {
								((Field_setting)l95_cd).set_setting((Module_mobjects.f_mizNatItem(l70_eqSeeds, 0)).f_x);
							} else {
							}
						} else {
						}
						break;
					}
					default: {
						break;
					}
					}
					l3_res=l86_it;
				} else {
					if (((((l66_srt==Module_lexicon.g_ikTrmLocus)||(l66_srt==Module_lexicon.g_ikTrmFreeVar))||(l66_srt==Module_lexicon.g_ikTrmLambdaVar))||(l66_srt==Module_lexicon.g_ikTrmBound))) {
						((Field_onlyConstants)aidn).set_onlyConstants(false);
						l3_res=l5__tmp;
					} else {
						l3_res=l5__tmp;
					}
				}
			}
			break;
		}
		case 60/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l5__tmp = (Struct_MizQuaTrm)l4__tmp;
			final Struct l96___ = (l5__tmp).f_trmProper;
			final Struct_MizTyp l97___ = (l5__tmp).f_qua;
			final int l98___ = (l5__tmp).f_pattNr;
			final int l99___ = (l5__tmp).f_trmInfo;
			Module_errhan.f_mizRunTimeError(2845);
			l3_res=l5__tmp;
			break;
		}
		case 16/*MizBaseTrm*/: {
			final Struct_MizBaseTrm l5__tmp = (Struct_MizBaseTrm)l4__tmp;
			final int l100_srt = l5__tmp.f_sort;
			final int l101_srt = (l5__tmp).f_sort;
			final int l102___ = (l5__tmp).f_trmInfo;
			if ((l101_srt==Module_lexicon.g_ikError)) {
				((Field_onlyConstants)aidn).set_onlyConstants(false);
				l3_res=l5__tmp;
			} else {
				Module_errhan.f_mizRunTimeError(2845);
				l3_res=l5__tmp;
			}
			break;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l4__tmp.getTypeName());
		}
		((Field_onlyConstants)aidn).set_onlyConstants((l1_lOnly&&(aidn).f_onlyConstants));
		((Field_equalsExpansionLevel)aidn).set_equalsExpansionLevel(l2_lLevel);
		return l3_res;
	}
	public static final Object f_mizCollectConstInTrmList(Struct_MizIdentify aidn, Struct al) {
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
			final Struct_MizTrmElem l6_e = l1__tmp;
			((Field_trm)l6_e).set_trm(Module_identify.f_mizCollectConstInTrm(aidn, l4_trm));
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
	public static final Object f_mizCollectConstInTyp(Struct_MizIdentify aidn, Struct_MizTyp atyp) {
		if (((atyp).f_sort!=Module_lexicon.g_ikError)) {
			((Field_lower)atyp).set_lower(Module_identify.f_mizCollectConstInCluster(aidn, (atyp).f_lower));
			((Field_upper)atyp).set_upper(Module_identify.f_mizCollectConstInCluster(aidn, (atyp).f_upper));
			return Module_identify.f_mizCollectConstInTrmList(aidn, (atyp).f_modArgs);
		} else {
			return null;
		}
	}
	public static final Struct f_mizCollectInferConst(Struct_MizIdentify aidn, Struct at) {
		final Struct_MizCorrelEnv l0_env = (aidn).f_env;
		if (!(aidn).f_onlyConstants) {
			return at;
		} else {
			final Struct_Pair l1_p = Module_identify.f_mizInferSearch(aidn, at);
			if ((((int)(l1_p).f_first)>=0)) {
				return Module_correl.f_mizNewVarTrm(Module_lexicon.g_ikTrmInfConst, ((int)(l1_p).f_first));
			} else {
				final Struct_MizTyp l2_lTyp = (l0_env).f_roundUpTrmType.invoke(l0_env, at);
				final int l3_nr = Module_vector.f_sizeVector((l0_env).f_inferConstDef);
				final Struct_MizAttrColl l4_lClusterPtr = Module_correl.f_mizCopyCluster((l2_lTyp).f_upper);
				(l0_env).f_roundUpCluster.invoke(l0_env, l4_lClusterPtr, l2_lTyp);
				((Field_upper)l2_lTyp).set_upper(l4_lClusterPtr);
				final Struct_MizConstDef l5_cd = (new Struct_MizConstDef(at, Module_mobjects.f_mizInitNatSet(4), (-1), l2_lTyp, false));
				Module_vector.f_pushVector((l0_env).f_inferConstDef, l5_cd);
				Module_mobjects.f_mizVectorAtInsert((aidn).f_sortedIdx, ((int)(l1_p).f_second), l3_nr);
				Struct l6_numeric;
				Struct l7__tmp = at;
				switch (l7__tmp.getTypeId()) {
				case 75/*MizVarTrm*/: {
					final Struct_MizVarTrm l8__tmp = (Struct_MizVarTrm)l7__tmp;
					final int l9_srt = l8__tmp.f_sort;
					final int l10_varNr = l8__tmp.f_varNr;
					final int l11_srt = (l8__tmp).f_sort;
					final int l12_varNr = (l8__tmp).f_varNr;
					final int l13___ = (l8__tmp).f_pattNr;
					final int l14___ = (l8__tmp).f_trmInfo;
					if ((l11_srt==Module_lexicon.g_ikTrmNumeral)) {
						l6_numeric=(new Struct_Some((new Struct_MizComplex(Module_math.f_i2d(l12_varNr), 0.0))));
					} else {
						l6_numeric=((Struct)SingletonStructs.str_None);
					}
					break;
				}
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l8__tmp = (Struct_MizFuncTrm)l7__tmp;
					final int l15_srt = l8__tmp.f_sort;
					final int l16_funcNr0 = l8__tmp.f_funcNr;
					final Struct l17_args0 = l8__tmp.f_args;
					final int l18_srt = (l8__tmp).f_sort;
					final int l19_funcNr0 = (l8__tmp).f_funcNr;
					final Struct l20_args0 = (l8__tmp).f_args;
					final int l21___ = (l8__tmp).f_pattNr;
					final int l22___ = (l8__tmp).f_trmInfo;
					if ((l18_srt==Module_lexicon.g_ikTrmFunctor)) {
						final Struct_MizFuncTrm l23_ft = Module_identify.f_mizIdAsFunc(l8__tmp);
						final Struct_Pair l24_pa = Module_correl.f_mizAdjustTrm(l0_env, l23_ft);
						final int l25_rq = Module_identify.f_mizRevReq(aidn, ((int)(l24_pa).f_first));
						if ((l25_rq==Module_builtin.g_rqImaginaryUnit)) {
							l6_numeric=(new Struct_Some((new Struct_MizComplex(0.0, 1.0))));
						} else {
							if (((((l25_rq==Module_builtin.g_rqRealAdd)||(l25_rq==Module_builtin.g_rqRealMult))||(l25_rq==Module_builtin.g_rqRealDiff))||(l25_rq==Module_builtin.g_rqRealDiv))) {
								final Struct l26_a1 = Module_identify.f_mizInfNumArg(aidn, Module_correl.f_mizTrmListNth(((Struct)(l24_pa).f_second), 0));
								final Struct l27_a2 = Module_identify.f_mizInfNumArg(aidn, Module_correl.f_mizTrmListNth(((Struct)(l24_pa).f_second), 1));
								Struct l28__tmp = l26_a1;
								switch (l28__tmp.getTypeId()) {
								case 88/*Some*/: {
									final Struct_Some l29__tmp = (Struct_Some)l28__tmp;
									final Object l30_v1 = l29__tmp.f_value;
									final Struct_MizComplex l31_v1 = ((Struct_MizComplex)(l29__tmp).f_value);
									Struct l32__tmp = l27_a2;
									switch (l32__tmp.getTypeId()) {
									case 88/*Some*/: {
										final Struct_Some l33__tmp = (Struct_Some)l32__tmp;
										final Object l34_v2 = l33__tmp.f_value;
										final Struct_MizComplex l35_v2 = ((Struct_MizComplex)(l33__tmp).f_value);
										l6_numeric=Module_identify.f_mizComplexOp(l25_rq, l31_v1, l35_v2);
										break;
									}
									case 80/*None*/: {
										l6_numeric=((Struct)SingletonStructs.str_None);
										break;
									}
									default:
										throw new RuntimeException("Unexpected struct in switch: "+l32__tmp.getTypeName());
									}
									break;
								}
								case 80/*None*/: {
									l6_numeric=((Struct)SingletonStructs.str_None);
									break;
								}
								default:
									throw new RuntimeException("Unexpected struct in switch: "+l28__tmp.getTypeName());
								}
							} else {
								if (((l25_rq==Module_builtin.g_rqRealNeg)||(l25_rq==Module_builtin.g_rqRealInv))) {
									final Struct l36_gsymswitch0 = Module_identify.f_mizInfNumArg(aidn, Module_correl.f_mizTrmListNth(((Struct)(l24_pa).f_second), 0));
									Struct l37__tmp = l36_gsymswitch0;
									switch (l37__tmp.getTypeId()) {
									case 88/*Some*/: {
										final Struct_Some l38__tmp = (Struct_Some)l37__tmp;
										final Object l39_v1 = l38__tmp.f_value;
										final Struct_MizComplex l40_v1 = ((Struct_MizComplex)(l38__tmp).f_value);
										if ((l25_rq==Module_builtin.g_rqRealNeg)) {
											l6_numeric=(new Struct_Some((new Struct_MizComplex((-(l40_v1).f_re), (-(l40_v1).f_im)))));
										} else {
											if ((((l40_v1).f_re!=0.0)||((l40_v1).f_im!=0.0))) {
												l6_numeric=(new Struct_Some(Module_identify.f_mizCInv(l40_v1)));
											} else {
												l6_numeric=((Struct)SingletonStructs.str_None);
											}
										}
										break;
									}
									case 80/*None*/: {
										l6_numeric=((Struct)SingletonStructs.str_None);
										break;
									}
									default:
										throw new RuntimeException("Unexpected struct in switch: "+l37__tmp.getTypeName());
									}
								} else {
									l6_numeric=((Struct)SingletonStructs.str_None);
								}
							}
						}
					} else {
						l6_numeric=((Struct)SingletonStructs.str_None);
					}
					break;
				}
				default: {
					l6_numeric=((Struct)SingletonStructs.str_None);
					break;
				}
				}
				((Field_determined)l5_cd).set_determined(Module_maybe.f_isSome(l6_numeric));
				Module_identify.f_mizSetNumeric(aidn, l3_nr, l6_numeric);
				Module_identify.f_mizCollectConstInTyp(aidn, (l5_cd).f_typ);
				return Module_correl.f_mizNewVarTrm(Module_lexicon.g_ikTrmInfConst, l3_nr);
			}
		}
	}
	public static final int f_mizCompRdTrms(Struct_MizCorrelEnv aenv, Struct aa, Struct ab) {
		final boolean l0_saved = (aenv).f_strictCompare;
		((Field_strictCompare)aenv).set_strictCompare(false);
		final int l1_c = Module_correl.f_mizCompTrms(aenv, aa, ab);
		((Field_strictCompare)aenv).set_strictCompare(l0_saved);
		return l1_c;
	}
	public static final Struct f_mizComplexOp(int arq, Struct_MizComplex aa, Struct_MizComplex ab) {
		if ((arq==Module_builtin.g_rqRealAdd)) {
			return (new Struct_Some((new Struct_MizComplex(((aa).f_re+(ab).f_re), ((aa).f_im+(ab).f_im)))));
		} else {
			if ((arq==Module_builtin.g_rqRealMult)) {
				return (new Struct_Some((new Struct_MizComplex((((aa).f_re*(ab).f_re)-((aa).f_im*(ab).f_im)), (((aa).f_re*(ab).f_im)+((aa).f_im*(ab).f_re))))));
			} else {
				if ((arq==Module_builtin.g_rqRealDiff)) {
					return (new Struct_Some((new Struct_MizComplex(((aa).f_re-(ab).f_re), ((aa).f_im-(ab).f_im)))));
				} else {
					if ((arq==Module_builtin.g_rqRealDiv)) {
						if ((((ab).f_re==0.0)&&((ab).f_im==0.0))) {
							return ((Struct)SingletonStructs.str_None);
						} else {
							final double l0_d = (((ab).f_re*(ab).f_re)+((ab).f_im*(ab).f_im));
							return (new Struct_Some((new Struct_MizComplex(((((aa).f_re*(ab).f_re)+((aa).f_im*(ab).f_im))/l0_d), ((((aa).f_im*(ab).f_re)-((aa).f_re*(ab).f_im))/l0_d)))));
						}
					} else {
						return ((Struct)SingletonStructs.str_None);
					}
				}
			}
		}
	}
	public static final Struct_MizFuncTrm f_mizIdAsFunc(Struct at) {
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
	public static final Struct_MizVarTrm f_mizIdAsVar(Struct at) {
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
	public static final Object[] f_mizIdentifyEqConsts(Struct_MizIdentify aidn, Struct at) {
		final Struct_MizCorrelEnv l0_env = (aidn).f_env;
		if ((((aidn).f_equalsExpansionLevel>=3)||(Module_vector.f_sizeVector((aidn).f_identifications)==0))) {
			return SingletonStructs.arr_empty;
		} else {
			final Struct_MizFuncTrm l1_ft = Module_identify.f_mizIdAsFunc(at);
			final Struct_Pair l2_pa = Module_correl.f_mizAdjustTrm(l0_env, l1_ft);
			final Reference<Object[]> l3_res = ((Reference<Object[]>)(new Reference(SingletonStructs.arr_empty)));
			final Struct_Vector l6_$5 = (aidn).f_identifications;
			final Struct l8_t = at;
			final Struct_MizIdentify l9_idn = aidn;
			final Func1<Object,Struct_MizFuncIdent> l7_$4 = (Func1<Object, Struct_MizFuncIdent>)(Struct_MizFuncIdent aidf) -> {
				final Struct l8_gsymswitch2 = (aidf).f_pattern0;
				boolean l9_headOk;
				Struct l10__tmp = l8_gsymswitch2;
				switch (l10__tmp.getTypeId()) {
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l11__tmp = (Struct_MizFuncTrm)l10__tmp;
					final int l12_s0 = l11__tmp.f_sort;
					final int l13_s0 = (l11__tmp).f_sort;
					final int l14___ = (l11__tmp).f_funcNr;
					final Struct l15___ = (l11__tmp).f_args;
					final int l16___ = (l11__tmp).f_pattNr;
					final int l17___ = (l11__tmp).f_trmInfo;
					if ((l13_s0==Module_lexicon.g_ikTrmFunctor)) {
						final Struct_MizFuncTrm l18_f0 = Module_identify.f_mizIdAsFunc((aidf).f_pattern0);
						final Struct_Pair l19_p0 = Module_correl.f_mizAdjustTrm(l0_env, l18_f0);
						l9_headOk=(((int)(l19_p0).f_first)==((int)(l2_pa).f_first));
					} else {
						l9_headOk=false;
					}
					break;
				}
				default: {
					l9_headOk=false;
					break;
				}
				}
				if (l9_headOk) {
					final Reference<Integer> l20_k = ((Reference<Integer>)(new Reference(1)));
					final Func0<Boolean> l23_$21 = (Func0<Boolean>)() -> {
						return (((int)l20_k.value)<Module_vector.f_sizeVector((l0_env).f_substTrm));
					};
					final Func0<Object> l24_$22 = (Func0<Object>)() -> {
						Module_vector.f_setVectorUnsafe((l0_env).f_substTrm, ((int)l20_k.value), SingletonStructs.str_None);
						l20_k.value = ((Integer)(((int)l20_k.value)+1));
						return null;
					};
					Module_loop.f_while(l23_$21, l24_$22);
					if ((Module_correl_match.f_mizEsTrm(l0_env, (aidf).f_pattern0, l8_t)&&Module_correl_match.f_mizCheckLociTypes(l0_env, (aidf).f_primaries))) {
						final Reference<Boolean> l25_ok = ((Reference<Boolean>)(new Reference(true)));
						final Object[] l28_$27 = (aidf).f_eqArgs;
						final Func1<Object,Struct_MizIntPair> l29_$26 = (Func1<Object, Struct_MizIntPair>)(Struct_MizIntPair apxy) -> {
							if (((boolean)l25_ok.value)) {
								if (!Module_correl_inst.f_mizIsWiderThan(l0_env, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((aidf).f_primaries, ((apxy).f_y-1))), Wrappers.w_mizCopyTyp.invoke(((Struct_MizTyp)Module_vector.f_getVectorUnsafe((aidf).f_primaries, ((apxy).f_x-1)))))) {
									l25_ok.value = ((Boolean)false);
									return null;
								} else {
									final Struct_Vector l33_$31 = (l0_env).f_substTrm;
									final int l34_$32 = (apxy).f_y;
									final Struct l35_gsymswitch3 = ((Struct)Module_vector.f_getVectorDef((l0_env).f_substTrm, (apxy).f_x, SingletonStructs.str_None));
									Struct l36_$30;
									Struct l37__tmp = l35_gsymswitch3;
									switch (l37__tmp.getTypeId()) {
									case 88/*Some*/: {
										final Struct_Some l38__tmp = (Struct_Some)l37__tmp;
										final Object l39_st = l38__tmp.f_value;
										final Struct l40_st = ((Struct)(l38__tmp).f_value);
										l36_$30=(new Struct_Some(Module_correl.f_mizCopyTrm(l40_st)));
										break;
									}
									case 80/*None*/: {
										l36_$30=((Struct)SingletonStructs.str_None);
										break;
									}
									default:
										throw new RuntimeException("Unexpected struct in switch: "+l37__tmp.getTypeName());
									}
									return Module_vector.f_setVectorUnsafe(l33_$31, l34_$32, l36_$30);
								}
							} else {
								return null;
							}
						};
						Native.iter(l28_$27, ((Func1<Object,Object>)(Func1)l29_$26));
						if (((boolean)l25_ok.value)) {
							final Struct l30_lTrm = Module_correl_match.f_mizInstSubstTrm(l0_env, (aidf).f_pattern1);
							((Field_equalsExpansionLevel)l9_idn).set_equalsExpansionLevel(((l9_idn).f_equalsExpansionLevel+1));
							final int l31_lB = (l0_env).f_boundVarNbr;
							((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
							final Struct l32_ct = Module_identify.f_mizCollectConstInTrm(l9_idn, l30_lTrm);
							((Field_boundVarNbr)l0_env).set_boundVarNbr(l31_lB);
							((Field_equalsExpansionLevel)l9_idn).set_equalsExpansionLevel(((l9_idn).f_equalsExpansionLevel-1));
							Struct l33__tmp = l32_ct;
							switch (l33__tmp.getTypeId()) {
							case 75/*MizVarTrm*/: {
								final Struct_MizVarTrm l34__tmp = (Struct_MizVarTrm)l33__tmp;
								final int l35_s4 = l34__tmp.f_sort;
								final int l36_nr4 = l34__tmp.f_varNr;
								final int l37_s4 = (l34__tmp).f_sort;
								final int l38_nr4 = (l34__tmp).f_varNr;
								final int l39___ = (l34__tmp).f_pattNr;
								final int l40___ = (l34__tmp).f_trmInfo;
								if ((l37_s4==Module_lexicon.g_ikTrmInfConst)) {
									l3_res.value = Module_array.f_arrayPush(l3_res.value, l38_nr4);
									return null;
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
					} else {
						return null;
					}
				} else {
					return null;
				}
			};
			Module_vector.f_iterVector(l6_$5, ((Func1<Object,Object>)(Func1)l7_$4));
			return l3_res.value;
		}
	}
	public static final Struct f_mizInfNumArg(Struct_MizIdentify aidn, Struct at0) {
		final Struct l0_t = Module_correl.f_mizSkipPrivFunc(at0);
		Struct l1__tmp = l0_t;
		switch (l1__tmp.getTypeId()) {
		case 75/*MizVarTrm*/: {
			final Struct_MizVarTrm l2__tmp = (Struct_MizVarTrm)l1__tmp;
			final int l3_srt = l2__tmp.f_sort;
			final int l4_varNr = l2__tmp.f_varNr;
			final int l5_srt = (l2__tmp).f_sort;
			final int l6_varNr = (l2__tmp).f_varNr;
			final int l7___ = (l2__tmp).f_pattNr;
			final int l8___ = (l2__tmp).f_trmInfo;
			if ((l5_srt==Module_lexicon.g_ikTrmInfConst)) {
				return Module_identify.f_mizNumericValue(aidn, l6_varNr);
			} else {
				return ((Struct)SingletonStructs.str_None);
			}
		}
		default: {
			return ((Struct)SingletonStructs.str_None);
		}
		}
	}
	public static final Object f_mizInferRelease(Struct_MizIdentify aidn, int amark) {
		final Struct_MizCorrelEnv l0_env = (aidn).f_env;
		final int l4_mark = amark;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector((l0_env).f_inferConstDef)>l4_mark);
		};
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			return Module_vector.f_removeVector((l0_env).f_inferConstDef, (Module_vector.f_sizeVector((l0_env).f_inferConstDef)-1));
		};
		Module_loop.f_while(l3_$1, l5_$2);
		final Reference<Integer> l6_i = ((Reference<Integer>)(new Reference(0)));
		final Struct_MizIdentify l10_idn = aidn;
		final Func0<Boolean> l9_$7 = (Func0<Boolean>)() -> {
			return (((int)l6_i.value)<Module_vector.f_sizeVector((l10_idn).f_sortedIdx));
		};
		final Struct_MizIdentify l12_idn = aidn;
		final int l13_mark = amark;
		final Func0<Object> l11_$8 = (Func0<Object>)() -> {
			if ((((int)Module_vector.f_getVectorUnsafe((l12_idn).f_sortedIdx, ((int)l6_i.value)))>=l13_mark)) {
				return Module_vector.f_removeVector((l12_idn).f_sortedIdx, ((int)l6_i.value));
			} else {
				l6_i.value = ((Integer)(((int)l6_i.value)+1));
				return null;
			}
		};
		Module_loop.f_while(l9_$7, l11_$8);
		final Struct_MizIdentify l17_idn = aidn;
		final int l18_mark = amark;
		final Func0<Boolean> l16_$14 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector((l17_idn).f_numeric)>l18_mark);
		};
		final Struct_MizIdentify l20_idn = aidn;
		final Func0<Object> l19_$15 = (Func0<Object>)() -> {
			return Module_vector.f_removeVector((l20_idn).f_numeric, (Module_vector.f_sizeVector((l20_idn).f_numeric)-1));
		};
		return Module_loop.f_while(l16_$14, l19_$15);
	}
	public static final Struct_Pair f_mizInferSearch(Struct_MizIdentify aidn, Struct at) {
		final Struct_MizCorrelEnv l0_env = (aidn).f_env;
		final Reference<Integer> l1_l = ((Reference<Integer>)(new Reference(0)));
		final Reference<Integer> l2_h = ((Reference<Integer>)(new Reference((Module_vector.f_sizeVector((aidn).f_sortedIdx)-1))));
		final Reference<Integer> l3_found = ((Reference<Integer>)(new Reference((-1))));
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			return ((Boolean)((((int)l1_l.value)<=((int)l2_h.value))&&(((int)l3_found.value)<0)));
		};
		final Struct_MizIdentify l8_idn = aidn;
		final Struct l9_t = at;
		final Func0<Object> l7_$5 = (Func0<Object>)() -> {
			final int l8_m = ((((int)l1_l.value)+((int)l2_h.value))/2);
			final int l9_nr = ((int)Module_vector.f_getVectorUnsafe((l8_idn).f_sortedIdx, l8_m));
			final Struct_MizConstDef l10_cd = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((l0_env).f_inferConstDef, l9_nr));
			final int l11_c = Module_identify.f_mizCompRdTrms(l0_env, (l10_cd).f_def, l9_t);
			if ((l11_c<0)) {
				l1_l.value = ((Integer)(l8_m+1));
				return null;
			} else {
				if ((l11_c>0)) {
					l2_h.value = ((Integer)(l8_m-1));
					return null;
				} else {
					l3_found.value = ((Integer)l9_nr);
					return null;
				}
			}
		};
		Module_loop.f_while(l6_$4, l7_$5);
		return (new Struct_Pair(l3_found.value, l1_l.value));
	}
	public static final Object f_mizInitRevReq(Struct_MizIdentify aidn) {
		Module_vector.f_clearVector((aidn).f_revReq);
		final int l0_n = Module_correl.f_mizConstrCount((aidn).f_env, Module_correl.g_coFunctor);
		final Struct_MizIdentify l3_idn = aidn;
		final Func1<Object,Integer> l2_$1 = (Func1<Object, Integer>)(Integer al3_0) -> {
			return Module_vector.f_pushVector((l3_idn).f_revReq, Module_builtin.g_rqNone);
		};
		Module_runtime.f_fori(0, l0_n, l2_$1);
		Object[] l4_frqs;
		l4_frqs=(new Object[] { Module_builtin.g_rqEmptySet, Module_builtin.g_rqRealAdd, Module_builtin.g_rqRealMult, Module_builtin.g_rqSucc, Module_builtin.g_rqUnion, Module_builtin.g_rqIntersection, Module_builtin.g_rqSubtraction, Module_builtin.g_rqSymmetricDifference, Module_builtin.g_rqRealNeg, Module_builtin.g_rqRealInv, Module_builtin.g_rqRealDiff, Module_builtin.g_rqRealDiv, Module_builtin.g_rqImaginaryUnit, Module_builtin.g_rqOmega, Module_builtin.g_rqZeroNumber, Module_builtin.g_rqPowerSet, Module_builtin.g_rqDiv, Module_builtin.g_rqMod, Module_builtin.g_rqLCM, Module_builtin.g_rqGCD });
		final Struct_MizIdentify l7_idn = aidn;
		final Func1<Object,Integer> l6_$5 = (Func1<Object, Integer>)(Integer ar) -> {
			final int l7_nr = Module_builtin.f_mizBuiltIn(((l7_idn).f_env).f_builtIn, ((int)ar));
			if (((l7_nr>0)&&(l7_nr<Module_vector.f_sizeVector((l7_idn).f_revReq)))) {
				return Module_vector.f_setVectorUnsafe((l7_idn).f_revReq, l7_nr, ar);
			} else {
				return null;
			}
		};
		return Native.iter(l4_frqs, ((Func1<Object,Object>)(Func1)l6_$5));
	}
	public static final Struct_MizIdentify f_mizMakeIdentify(Struct_MizCorrelEnv aenv) {
		final Struct_Vector l0_rv = Module_vector.f_makeVector(64);
		final Struct_Vector l4_$2 = Module_vector.f_makeVector(64);
		final Struct_Vector l5_$3 = Module_vector.f_makeVector(64);
		final Func3<Object[],Struct_MizIdentify, Integer, Struct> l6_$1 = (Func3<Object[], Struct_MizIdentify, Integer, Struct>)(Struct_MizIdentify al7_0, Integer al8_1, Struct al9_2) -> {
			return SingletonStructs.arr_empty;
		};
		return (new Struct_MizIdentify(aenv, l4_$2, l5_$3, true, 0, l0_rv, l6_$1, Module_vector.f_makeVector(4), Module_vector.f_makeVector(4)));
	}
	public static final Struct f_mizNumericValue(Struct_MizIdentify aidn, int anr) {
		return ((Struct)Module_vector.f_getVectorDef((aidn).f_numeric, anr, SingletonStructs.str_None));
	}
	public static final int f_mizRevReq(Struct_MizIdentify aidn, int afuncNr) {
		return ((int)Module_vector.f_getVectorDef((aidn).f_revReq, afuncNr, Module_builtin.g_rqNone));
	}
	public static final Object f_mizSetNumeric(Struct_MizIdentify aidn, int anr, Struct av) {
		final Struct_MizIdentify l3_idn = aidn;
		final int l4_nr = anr;
		final Func0<Boolean> l2_$0 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector((l3_idn).f_numeric)<=l4_nr);
		};
		final Struct_MizIdentify l6_idn = aidn;
		final Func0<Object> l5_$1 = (Func0<Object>)() -> {
			return Module_vector.f_pushVector((l6_idn).f_numeric, SingletonStructs.str_None);
		};
		Module_loop.f_while(l2_$0, l5_$1);
		return Module_vector.f_setVectorUnsafe((aidn).f_numeric, anr, av);
	}
}
