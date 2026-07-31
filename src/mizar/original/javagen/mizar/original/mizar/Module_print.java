// HASH COLLISIONS: YES
// timestamp: 1785501898000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_print {
	public static final String f_mizFrm2s(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 56/*MizPredFrm*/: {
			final Struct_MizPredFrm l1__tmp = (Struct_MizPredFrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_predNr = l1__tmp.f_predNr;
			final Struct l4_args = l1__tmp.f_args;
			final int l5_srt = (l1__tmp).f_sort;
			final int l6_predNr = (l1__tmp).f_predNr;
			final Struct l7_args = (l1__tmp).f_args;
			final int l8___ = (l1__tmp).f_pattNr;
			String l9_k;
			if ((l5_srt==Module_lexicon.g_ikFrmPred)) {
				l9_k="R";
			} else {
				if ((l5_srt==Module_lexicon.g_ikFrmAttr)) {
					l9_k="V";
				} else {
					if ((l5_srt==Module_lexicon.g_ikFrmSchPred)) {
						l9_k="P";
					} else {
						l9_k="?";
					}
				}
			}
			return ((((l9_k+Module_string.f_i2s(l6_predNr))+"[")+Module_print.f_mizTrmList2s(l7_args))+"]");
		}
		case 49/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l1__tmp = (Struct_MizLocPredFrm)l0__tmp;
			final int l10_predNr = l1__tmp.f_predNr;
			final Struct l11_args = l1__tmp.f_args;
			final int l12_predNr = (l1__tmp).f_predNr;
			final Struct l13_args = (l1__tmp).f_args;
			final Struct l14___ = (l1__tmp).f_predExp;
			final int l15___ = (l1__tmp).f_pattNr;
			return (((("S"+Module_string.f_i2s(l12_predNr))+"[")+Module_print.f_mizTrmList2s(l13_args))+"]");
		}
		case 61/*MizQualFrm*/: {
			final Struct_MizQualFrm l1__tmp = (Struct_MizQualFrm)l0__tmp;
			final Struct l16_trm = l1__tmp.f_trm;
			final Struct_MizTyp l17_typ = l1__tmp.f_typ;
			final Struct l18_trm = (l1__tmp).f_trm;
			final Struct_MizTyp l19_typ = (l1__tmp).f_typ;
			final int l20___ = (l1__tmp).f_pattNr;
			return ((Module_print.f_mizTrm2s(l18_trm)+" is ")+Module_print.f_mizTyp2s(l19_typ));
		}
		case 51/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l21_arg = l1__tmp.f_arg;
			final Struct l22_arg = (l1__tmp).f_arg;
			final int l23___ = (l1__tmp).f_pattNr;
			return ("\u00AC"+Module_print.f_mizFrm2s(l22_arg));
		}
		case 17/*MizBinFrm*/: {
			final Struct_MizBinFrm l1__tmp = (Struct_MizBinFrm)l0__tmp;
			final Struct l24_l = l1__tmp.f_left;
			final Struct l25_r = l1__tmp.f_right;
			final int l26___ = (l1__tmp).f_sort;
			final Struct l27_l = (l1__tmp).f_left;
			final Struct l28_r = (l1__tmp).f_right;
			final int l29___ = (l1__tmp).f_pattNr;
			return (((("("+Module_print.f_mizFrm2s(l27_l))+" ? ")+Module_print.f_mizFrm2s(l28_r))+")");
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l30_conjuncts = l1__tmp.f_conjuncts;
			final Struct_Vector l31_conjuncts = (l1__tmp).f_conjuncts;
			final int l32___ = (l1__tmp).f_pattNr;
			final Func2<String,String, Struct> l34_$33 = (Func2<String, String, Struct>)(String aacc, Struct ag) -> {
				return ((aacc+((aacc).equals("")?"":" \u2227 "))+Module_print.f_mizFrm2s(ag));
			};
			return (("("+((String)Module_vector.f_foldVector(l31_conjuncts, "", ((Func2<Object,Object, Object>)(Func2)l34_$33))))+")");
		}
		case 74/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l1__tmp = (Struct_MizUnivFrm)l0__tmp;
			final Struct_MizTyp l35_quantified = l1__tmp.f_quantified;
			final Struct l36_scope = l1__tmp.f_scope;
			final int l37___ = (l1__tmp).f_sort;
			final int l38___ = (l1__tmp).f_varId;
			final Struct_MizTyp l39_quantified = (l1__tmp).f_quantified;
			final Struct l40_scope = (l1__tmp).f_scope;
			final int l41___ = (l1__tmp).f_pattNr;
			return ((("\u2200["+Module_print.f_mizTyp2s(l39_quantified))+"]")+Module_print.f_mizFrm2s(l40_scope));
		}
		case 73/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l1__tmp = (Struct_MizUniqFrm)l0__tmp;
			final int l42_srt = l1__tmp.f_sort;
			final int l43_srt = (l1__tmp).f_sort;
			if ((l43_srt==Module_lexicon.g_ikFrmVerum)) {
				return "\u22A4";
			} else {
				return "?f";
			}
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l1__tmp = (Struct_MizFlexFrm)l0__tmp;
			final Struct l44_lo = l1__tmp.f_leftOrig;
			final Struct l45_ro = l1__tmp.f_rightOrig;
			final int l46___ = (l1__tmp).f_sort;
			final Struct l47_lo = (l1__tmp).f_leftOrig;
			final Struct l48_ro = (l1__tmp).f_rightOrig;
			final Struct l49___ = (l1__tmp).f_leftTrm;
			final Struct l50___ = (l1__tmp).f_rightTrm;
			final Struct l51___ = (l1__tmp).f_expansion;
			final int l52___ = (l1__tmp).f_pattNr;
			return ((Module_print.f_mizFrm2s(l47_lo)+"...+...")+Module_print.f_mizFrm2s(l48_ro));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final String f_mizTrm2s(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 75/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_varNr = l1__tmp.f_varNr;
			final int l4_trmInfo = l1__tmp.f_trmInfo;
			final int l5_srt = (l1__tmp).f_sort;
			final int l6_varNr = (l1__tmp).f_varNr;
			final int l7___ = (l1__tmp).f_pattNr;
			final int l8_trmInfo = (l1__tmp).f_trmInfo;
			String l9_pfx;
			if ((l5_srt==Module_lexicon.g_ikTrmBound)) {
				l9_pfx="B";
			} else {
				if ((l5_srt==Module_lexicon.g_ikTrmConstant)) {
					l9_pfx="c";
				} else {
					if ((l5_srt==Module_lexicon.g_ikTrmInfConst)) {
						l9_pfx="D";
					} else {
						if ((l5_srt==Module_lexicon.g_ikTrmEqConst)) {
							l9_pfx="E";
						} else {
							if ((l5_srt==Module_lexicon.g_ikTrmFreeVar)) {
								l9_pfx="F";
							} else {
								if ((l5_srt==Module_lexicon.g_ikTrmLocus)) {
									l9_pfx="A";
								} else {
									if ((l5_srt==Module_lexicon.g_ikTrmNumeral)) {
										l9_pfx="#";
									} else {
										l9_pfx=(("v"+Module_string.f_i2s(l5_srt))+"_");
									}
								}
							}
						}
					}
				}
			}
			return (l9_pfx+Module_string.f_i2s(l6_varNr));
		}
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l10_srt = l1__tmp.f_sort;
			final int l11_funcNr = l1__tmp.f_funcNr;
			final Struct l12_args = l1__tmp.f_args;
			final int l13_srt = (l1__tmp).f_sort;
			final int l14_funcNr = (l1__tmp).f_funcNr;
			final Struct l15_args = (l1__tmp).f_args;
			final int l16___ = (l1__tmp).f_pattNr;
			final int l17___ = (l1__tmp).f_trmInfo;
			String l18_k;
			if ((l13_srt==Module_lexicon.g_ikTrmFunctor)) {
				l18_k="K";
			} else {
				if ((l13_srt==Module_lexicon.g_ikTrmSelector)) {
					l18_k="U";
				} else {
					if ((l13_srt==Module_lexicon.g_ikTrmAggreg)) {
						l18_k="G";
					} else {
						l18_k="F";
					}
				}
			}
			return ((((l18_k+Module_string.f_i2s(l14_funcNr))+"(")+Module_print.f_mizTrmList2s(l15_args))+")");
		}
		case 59/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final int l19_funcNr = l1__tmp.f_funcNr;
			final Struct l20_args = l1__tmp.f_args;
			final int l21_funcNr = (l1__tmp).f_funcNr;
			final Struct l22_args = (l1__tmp).f_args;
			final Struct l23___ = (l1__tmp).f_funcExp;
			final int l24___ = (l1__tmp).f_pattNr;
			final int l25___ = (l1__tmp).f_trmInfo;
			return (((("H"+Module_string.f_i2s(l21_funcNr))+"(")+Module_print.f_mizTrmList2s(l22_args))+")");
		}
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l1__tmp = (Struct_MizFraenkelTrm)l0__tmp;
			final Struct l26_scope = l1__tmp.f_scope;
			final Struct_Vector l27___ = (l1__tmp).f_lambdaArgs;
			final Struct l28_scope = (l1__tmp).f_scope;
			final Struct l29___ = (l1__tmp).f_compr;
			final Struct_MizIntSeq l30___ = (l1__tmp).f_idents;
			final int l31___ = (l1__tmp).f_pattNr;
			final int l32___ = (l1__tmp).f_trmInfo;
			return (("{"+Module_print.f_mizTrm2s(l28_scope))+":...}");
		}
		case 60/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
			final Struct l33_trmProper = l1__tmp.f_trmProper;
			final Struct l34_trmProper = (l1__tmp).f_trmProper;
			final Struct_MizTyp l35___ = (l1__tmp).f_qua;
			final int l36___ = (l1__tmp).f_pattNr;
			final int l37___ = (l1__tmp).f_trmInfo;
			return (Module_print.f_mizTrm2s(l34_trmProper)+" qua \u2026");
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
			final Struct_MizTyp l38_typ = l1__tmp.f_choiceTyp;
			final Struct_MizTyp l39_typ = (l1__tmp).f_choiceTyp;
			final int l40___ = (l1__tmp).f_pattNr;
			final int l41___ = (l1__tmp).f_trmInfo;
			return ("the "+Module_print.f_mizTyp2s(l39_typ));
		}
		case 16/*MizBaseTrm*/: {
			final Struct_MizBaseTrm l1__tmp = (Struct_MizBaseTrm)l0__tmp;
			final int l42_srt = l1__tmp.f_sort;
			final int l43_srt = (l1__tmp).f_sort;
			final int l44___ = (l1__tmp).f_trmInfo;
			if ((l43_srt==Module_lexicon.g_ikTrmIt)) {
				return "it";
			} else {
				return "?";
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final String f_mizTrmList2s(Struct al) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			return "";
		}
		case 68/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			final String l6_rest = Module_print.f_mizTrmList2s(l5_next);
			return (Module_print.f_mizTrm2s(l4_trm)+((l6_rest).equals("")?"":(","+l6_rest)));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final String f_mizTyp2s(Struct_MizTyp at) {
		final Struct_Vector l2_$1 = ((at).f_lower).f_items;
		final Func2<String,String, Struct_MizAttr> l3_$0 = (Func2<String, String, Struct_MizAttr>)(String aacc, Struct_MizAttr aa) -> {
			final String l6_$5 = (((aacc+(((aa).f_neg==0)?"non-":""))+"V")+Module_string.f_i2s((aa).f_attrNr));
			final Struct l7_gsymswitch0 = (aa).f_args;
			String l8_$4;
			Struct l9__tmp = l7_gsymswitch0;
			switch (l9__tmp.getTypeId()) {
			case 69/*MizTrmNil*/: {
				l8_$4="";
				break;
			}
			default: {
				l8_$4=(("("+Module_print.f_mizTrmList2s((aa).f_args))+")");
				break;
			}
			}
			return ((l6_$5+l8_$4)+" ");
		};
		final String l4_cl = ((String)Module_vector.f_foldVector(l2_$1, "", ((Func2<Object,Object, Object>)(Func2)l3_$0)));
		String l5_kind;
		if (((at).f_sort==Module_lexicon.g_ikTypMode)) {
			l5_kind="M";
		} else {
			if (((at).f_sort==Module_lexicon.g_ikTypStruct)) {
				l5_kind="L";
			} else {
				l5_kind="?";
			}
		}
		final String l8_$7 = ((l4_cl+l5_kind)+Module_string.f_i2s((at).f_modNr));
		final Struct l9_gsymswitch1 = (at).f_modArgs;
		String l10_$6;
		Struct l11__tmp = l9_gsymswitch1;
		switch (l11__tmp.getTypeId()) {
		case 69/*MizTrmNil*/: {
			l10_$6="";
			break;
		}
		default: {
			l10_$6=(("("+Module_print.f_mizTrmList2s((at).f_modArgs))+")");
			break;
		}
		}
		return (l8_$7+l10_$6);
	}
}
