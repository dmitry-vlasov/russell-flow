// HASH COLLISIONS: YES
// timestamp: 1785502339000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_correl_inst {
	public static final Struct_MizVarTrm f_mizAsVarTrmL(Struct at) {
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
			Module_errhan.f_mizRunTimeError(2901);
			return (new Struct_MizVarTrm(Module_lexicon.g_ikError, 0, 0, 0));
		}
		}
	}
	public static final boolean f_mizAttrCollIsSubsetOf(Struct_MizCorrelEnv aenv, Struct_MizAttrColl ac, Struct_MizAttrColl aother, Func2<Boolean,Struct_MizAttr, Struct_MizAttr> aeqAttr) {
		final int l0_n = Module_vector.f_sizeVector((ac).f_items);
		final int l1_m = Module_vector.f_sizeVector((aother).f_items);
		if ((l1_m<l0_n)) {
			return false;
		} else {
			final Reference<Boolean> l2_res = ((Reference<Boolean>)(new Reference(true)));
			final Reference<Integer> l3_j = ((Reference<Integer>)(new Reference(0)));
			final Reference<Integer> l4_i = ((Reference<Integer>)(new Reference(0)));
			final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l4_i.value)<l0_n)&&((boolean)l2_res.value)));
			};
			final Struct_MizAttrColl l9_c = ac;
			final Struct_MizCorrelEnv l10_env = aenv;
			final Struct_MizAttrColl l11_other = aother;
			final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l12_eqAttr = aeqAttr;
			final Func0<Object> l8_$6 = (Func0<Object>)() -> {
				final Struct_MizAttr l9_a = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l9_c).f_items, ((int)l4_i.value)));
				final int l10_lAttrNr = Module_correl.f_mizAdjustedAttrNr(l10_env, l9_a);
				final Func0<Boolean> l13_$11 = (Func0<Boolean>)() -> {
					return ((Boolean)((((int)l3_j.value)<l1_m)&&(Module_correl.f_mizAdjustedAttrNr(l10_env, ((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l11_other).f_items, ((int)l3_j.value))))<l10_lAttrNr)));
				};
				final Func0<Object> l14_$12 = (Func0<Object>)() -> {
					l3_j.value = ((Integer)(((int)l3_j.value)+1));
					return null;
				};
				Module_loop.f_while(l13_$11, l14_$12);
				if (((((int)l3_j.value)==l1_m)||!((boolean)l12_eqAttr.invoke(((Struct_MizAttr)Module_vector.f_getVectorUnsafe((l11_other).f_items, ((int)l3_j.value))), l9_a)))) {
					l2_res.value = ((Boolean)false);
				} else {
				}
				l4_i.value = ((Integer)(((int)l4_i.value)+1));
				return null;
			};
			Module_loop.f_while(l7_$5, l8_$6);
			return ((boolean)l2_res.value);
		}
	}
	public static final Struct_MizAttrColl f_mizCopyAllowed(Struct_MizCorrelEnv aenv, Struct_MizTyp atyp, Struct_MizAttrColl aorigin) {
		final Struct_MizAttrColl l0_res = Module_correl.f_mizNewEmptyCluster();
		Module_errhan.f_mizAssert(2599, ((atyp).f_sort==Module_lexicon.g_ikTypMode));
		final Struct_Pair l1_pm = Module_correl.f_mizAdjustTyp(aenv, atyp);
		final int l2_lModNr = ((int)(l1_pm).f_first);
		final Struct_Vector l5_$4 = (aorigin).f_items;
		final Struct_MizCorrelEnv l7_env = aenv;
		final Func1<Object,Struct_MizAttr> l6_$3 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			final int l7_lAttrNr = Module_correl.f_mizAdjustedAttrNr(l7_env, aa);
			final Struct_MizConstr l8_ca = Module_correl.f_mizConstr(l7_env, Module_correl.g_coAttribute, l7_lAttrNr);
			final Struct l9_gsymswitch4 = (l8_ca).f_typ;
			Struct l10__tmp = l9_gsymswitch4;
			switch (l10__tmp.getTypeId()) {
			case 90/*Some*/: {
				final Struct_Some l11__tmp = (Struct_Some)l10__tmp;
				final Object l12_ct = l11__tmp.f_value;
				final Struct_MizTyp l13_ct = ((Struct_MizTyp)(l11__tmp).f_value);
				if (((l13_ct).f_sort==Module_lexicon.g_ikTypMode)) {
					if ((l2_lModNr!=(l13_ct).f_modNr)) {
						return Module_correl.f_mizAttrCollInsert(l7_env, l0_res, Wrappers.w_mizCopyAttr.invoke(aa));
					} else {
						return null;
					}
				} else {
					if (((l13_ct).f_sort==Module_lexicon.g_ikTypStruct)) {
						return Module_correl.f_mizAttrCollInsert(l7_env, l0_res, Wrappers.w_mizCopyAttr.invoke(aa));
					} else {
						return null;
					}
				}
			}
			case 82/*None*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
			}
		};
		Module_vector.f_iterVector(l5_$4, ((Func1<Object,Object>)(Func1)l6_$3));
		return l0_res;
	}
	public static final Struct_MizAttrColl f_mizCopyExpCluster(Struct_MizAttrColl ac) {
		final Struct_Vector l2_$1 = (ac).f_items;
		final Func1<Struct_MizAttr,Struct_MizAttr> l3_$0 = (Func1<Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			return (new Struct_MizAttr((aa).f_neg, (aa).f_attrNr, Module_correl_inst.f_mizCopyExpTrmList((aa).f_args), false, 0));
		};
		return (new Struct_MizAttrColl(Module_vector.f_mapVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0)), (ac).f_consistent));
	}
	public static final Struct f_mizCopyExpFrm(Struct af) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l2_conjuncts = l1__tmp.f_conjuncts;
			final Struct_Vector l3_conjuncts = (l1__tmp).f_conjuncts;
			final int l4___ = (l1__tmp).f_pattNr;
			final Struct_Vector l5_out = Module_vector.f_makeVector(Module_vector.f_sizeVector(l3_conjuncts));
			final Func1<Object,Struct> l7_$6 = (Func1<Object, Struct>)(Struct ag) -> {
				final Struct l8_cg = Module_correl_inst.f_mizCopyExpFrm(ag);
				Struct l9__tmp = l8_cg;
				switch (l9__tmp.getTypeId()) {
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l10__tmp = (Struct_MizConjFrm)l9__tmp;
					final Struct_Vector l11_inner = l10__tmp.f_conjuncts;
					final Struct_Vector l12_inner = (l10__tmp).f_conjuncts;
					final int l13___ = (l10__tmp).f_pattNr;
					final Func1<Object,Struct> l15_$14 = (Func1<Object, Struct>)(Struct ah) -> {
						return Module_vector.f_pushVector(l5_out, ah);
					};
					return Module_vector.f_iterVector(l12_inner, ((Func1<Object,Object>)(Func1)l15_$14));
				}
				default: {
					return Module_vector.f_pushVector(l5_out, l8_cg);
				}
				}
			};
			Module_vector.f_iterVector(l3_conjuncts, ((Func1<Object,Object>)(Func1)l7_$6));
			return (new Struct_MizConjFrm(l5_out, 0));
		}
		case 76/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l1__tmp = (Struct_MizUnivFrm)l0__tmp;
			final int l8_srt = l1__tmp.f_sort;
			final int l9_varId = l1__tmp.f_varId;
			final Struct_MizTyp l10_quantified = l1__tmp.f_quantified;
			final Struct l11_scope = l1__tmp.f_scope;
			final int l12_srt = (l1__tmp).f_sort;
			final int l13_varId = (l1__tmp).f_varId;
			final Struct_MizTyp l14_quantified = (l1__tmp).f_quantified;
			final Struct l15_scope = (l1__tmp).f_scope;
			final int l16___ = (l1__tmp).f_pattNr;
			return (new Struct_MizUnivFrm(l12_srt, l13_varId, Module_correl_inst.f_mizCopyExpTyp(l14_quantified), Module_correl_inst.f_mizCopyExpFrm(l15_scope), 0));
		}
		case 63/*MizQualFrm*/: {
			final Struct_MizQualFrm l1__tmp = (Struct_MizQualFrm)l0__tmp;
			final Struct l17_trm = l1__tmp.f_trm;
			final Struct_MizTyp l18_typ = l1__tmp.f_typ;
			final Struct l19_trm = (l1__tmp).f_trm;
			final Struct_MizTyp l20_typ = (l1__tmp).f_typ;
			final int l21___ = (l1__tmp).f_pattNr;
			return Module_correl.f_mizNewQualFrm(Module_correl_inst.f_mizCopyExpTrm(l19_trm), Module_correl_inst.f_mizCopyExpTyp(l20_typ));
		}
		case 58/*MizPredFrm*/: {
			final Struct_MizPredFrm l1__tmp = (Struct_MizPredFrm)l0__tmp;
			final int l22_srt = l1__tmp.f_sort;
			final int l23_predNr = l1__tmp.f_predNr;
			final Struct l24_args = l1__tmp.f_args;
			final int l25_srt = (l1__tmp).f_sort;
			final int l26_predNr = (l1__tmp).f_predNr;
			final Struct l27_args = (l1__tmp).f_args;
			final int l28___ = (l1__tmp).f_pattNr;
			return (new Struct_MizPredFrm(l25_srt, l26_predNr, Module_correl_inst.f_mizCopyExpTrmList(l27_args), 0));
		}
		case 49/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l1__tmp = (Struct_MizLocPredFrm)l0__tmp;
			final int l29_predNr = l1__tmp.f_predNr;
			final Struct l30_args = l1__tmp.f_args;
			final Struct l31_expn = l1__tmp.f_predExp;
			final int l32_predNr = (l1__tmp).f_predNr;
			final Struct l33_args = (l1__tmp).f_args;
			final Struct l34_expn = (l1__tmp).f_predExp;
			final int l35___ = (l1__tmp).f_pattNr;
			if ((Module_correl.f_mizFrmSort(l34_expn)!=Module_lexicon.g_ikError)) {
				{
					final Struct l36___tmp = l34_expn;
					af = l36___tmp;
					continue TAIL_CALL;
				}
			} else {
				return (new Struct_MizLocPredFrm(l32_predNr, Module_correl_inst.f_mizCopyExpTrmList(l33_args), Module_correl_inst.f_mizCopyExpFrm(l34_expn), 0));
			}
		}
		case 52/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l37_arg = l1__tmp.f_arg;
			final Struct l38_arg = (l1__tmp).f_arg;
			final int l39___ = (l1__tmp).f_pattNr;
			return Module_correl.f_mizNewNeg(Module_correl_inst.f_mizCopyExpFrm(l38_arg));
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l1__tmp = (Struct_MizFlexFrm)l0__tmp;
			final Struct l40_lo = l1__tmp.f_leftOrig;
			final Struct l41_ro = l1__tmp.f_rightOrig;
			final Struct l42_lt = l1__tmp.f_leftTrm;
			final Struct l43_rt = l1__tmp.f_rightTrm;
			final Struct l44_expn = l1__tmp.f_expansion;
			final int l45___ = (l1__tmp).f_sort;
			final Struct l46_lo = (l1__tmp).f_leftOrig;
			final Struct l47_ro = (l1__tmp).f_rightOrig;
			final Struct l48_lt = (l1__tmp).f_leftTrm;
			final Struct l49_rt = (l1__tmp).f_rightTrm;
			final Struct l50_expn = (l1__tmp).f_expansion;
			final int l51___ = (l1__tmp).f_pattNr;
			return (new Struct_MizFlexFrm(Module_lexicon.g_ikFrmFlexConj, Module_correl_inst.f_mizCopyExpFrm(l46_lo), Module_correl_inst.f_mizCopyExpFrm(l47_ro), Module_correl_inst.f_mizCopyExpTrm(l48_lt), Module_correl_inst.f_mizCopyExpTrm(l49_rt), Module_correl_inst.f_mizCopyExpFrm(l50_expn), 0));
		}
		case 75/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l1__tmp = (Struct_MizUniqFrm)l0__tmp;
			final int l52_srt = l1__tmp.f_sort;
			final int l53_srt = (l1__tmp).f_sort;
			return (new Struct_MizUniqFrm(l53_srt));
		}
		case 17/*MizBinFrm*/: {
			final Struct_MizBinFrm l1__tmp = (Struct_MizBinFrm)l0__tmp;
			final int l54___ = (l1__tmp).f_sort;
			final Struct l55___ = (l1__tmp).f_left;
			final Struct l56___ = (l1__tmp).f_right;
			final int l57___ = (l1__tmp).f_pattNr;
			Module_errhan.f_mizRunTimeError(2643);
			return l1__tmp;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	}
	public static final Struct f_mizCopyExpTrm(Struct at) {
		TAIL_CALL: for(;;) {
		if ((Module_correl.f_mizTrmSort(at)==Module_lexicon.g_ikTrmEqConst)) {
			return at;
		} else {
			Struct l0__tmp = at;
			switch (l0__tmp.getTypeId()) {
			case 77/*MizVarTrm*/: {
				final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
				final int l2_srt = l1__tmp.f_sort;
				final int l3_varNr = l1__tmp.f_varNr;
				final int l4_srt = (l1__tmp).f_sort;
				final int l5_varNr = (l1__tmp).f_varNr;
				final int l6___ = (l1__tmp).f_pattNr;
				final int l7___ = (l1__tmp).f_trmInfo;
				return Module_correl.f_mizNewVarTrm(l4_srt, l5_varNr);
			}
			case 45/*MizFuncTrm*/: {
				final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
				final int l8_srt = l1__tmp.f_sort;
				final int l9_funcNr = l1__tmp.f_funcNr;
				final Struct l10_args = l1__tmp.f_args;
				final int l11_srt = (l1__tmp).f_sort;
				final int l12_funcNr = (l1__tmp).f_funcNr;
				final Struct l13_args = (l1__tmp).f_args;
				final int l14___ = (l1__tmp).f_pattNr;
				final int l15___ = (l1__tmp).f_trmInfo;
				return Module_correl.f_mizNewLocFuncTrm(l11_srt, l12_funcNr, Module_correl_inst.f_mizCopyExpTrmList(l13_args));
			}
			case 61/*MizPrivFuncTrm*/: {
				final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
				final int l16_funcNr = l1__tmp.f_funcNr;
				final Struct l17_args = l1__tmp.f_args;
				final Struct l18_funcExp = l1__tmp.f_funcExp;
				final int l19_funcNr = (l1__tmp).f_funcNr;
				final Struct l20_args = (l1__tmp).f_args;
				final Struct l21_funcExp = (l1__tmp).f_funcExp;
				final int l22___ = (l1__tmp).f_pattNr;
				final int l23___ = (l1__tmp).f_trmInfo;
				if ((Module_correl.f_mizTrmSort(l21_funcExp)!=Module_lexicon.g_ikError)) {
					{
						final Struct l24___tmp = l21_funcExp;
						at = l24___tmp;
						continue TAIL_CALL;
					}
				} else {
					return Module_correl.f_mizNewPrivFuncTrm(l19_funcNr, Module_correl_inst.f_mizCopyExpTrmList(l20_args), Module_correl_inst.f_mizCopyExpTrm(l21_funcExp));
				}
			}
			case 42/*MizFraenkelTrm*/: {
				final Struct_MizFraenkelTrm l1__tmp = (Struct_MizFraenkelTrm)l0__tmp;
				final Struct_Vector l25_lambdaArgs = l1__tmp.f_lambdaArgs;
				final Struct l26_scope = l1__tmp.f_scope;
				final Struct l27_compr = l1__tmp.f_compr;
				final Struct_MizIntSeq l28_idents = l1__tmp.f_idents;
				final Struct_Vector l29_lambdaArgs = (l1__tmp).f_lambdaArgs;
				final Struct l30_scope = (l1__tmp).f_scope;
				final Struct l31_compr = (l1__tmp).f_compr;
				final Struct_MizIntSeq l32_idents = (l1__tmp).f_idents;
				final int l33___ = (l1__tmp).f_pattNr;
				final int l34___ = (l1__tmp).f_trmInfo;
				return (new Struct_MizFraenkelTrm(Module_vector.f_mapVector(l29_lambdaArgs, ((Func1<Object,Object>)(Func1)Wrappers.w_mizCopyExpTyp)), Module_correl_inst.f_mizCopyExpTrm(l30_scope), Module_correl_inst.f_mizCopyExpFrm(l31_compr), Module_mobjects.f_mizCopyIntSeq(l32_idents), 0, 0));
			}
			case 21/*MizChoiceTrm*/: {
				final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
				final Struct_MizTyp l35_typ = l1__tmp.f_choiceTyp;
				final Struct_MizTyp l36_typ = (l1__tmp).f_choiceTyp;
				final int l37___ = (l1__tmp).f_pattNr;
				final int l38___ = (l1__tmp).f_trmInfo;
				return Module_correl.f_mizNewChoiceTrm(Wrappers.w_mizCopyExpTyp.invoke(l36_typ));
			}
			case 62/*MizQuaTrm*/: {
				final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
				final Struct l39_trmProper = l1__tmp.f_trmProper;
				final Struct_MizTyp l40_qua = l1__tmp.f_qua;
				final Struct l41_trmProper = (l1__tmp).f_trmProper;
				final Struct_MizTyp l42_qua = (l1__tmp).f_qua;
				final int l43___ = (l1__tmp).f_pattNr;
				final int l44___ = (l1__tmp).f_trmInfo;
				return Module_correl.f_mizNewQuaTrm(Module_correl_inst.f_mizCopyExpTrm(l41_trmProper), Wrappers.w_mizCopyExpTyp.invoke(l42_qua));
			}
			case 16/*MizBaseTrm*/: {
				final Struct_MizBaseTrm l1__tmp = (Struct_MizBaseTrm)l0__tmp;
				final int l45_srt = l1__tmp.f_sort;
				final int l46_srt = (l1__tmp).f_sort;
				final int l47___ = (l1__tmp).f_trmInfo;
				return (new Struct_MizBaseTrm(l46_srt, 0));
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
			}
		}
	}
	}
	public static final Struct f_mizCopyExpTrmList(Struct al) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			return al;
		}
		case 70/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			return (new Struct_MizTrmElem(Module_correl_inst.f_mizCopyExpTrm(l4_trm), Module_correl_inst.f_mizCopyExpTrmList(l5_next)));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct_MizTyp f_mizCopyExpTyp(Struct_MizTyp at) {
		return (new Struct_MizTyp((at).f_sort, Module_correl_inst.f_mizCopyExpCluster((at).f_lower), Module_correl_inst.f_mizCopyExpCluster((at).f_upper), (at).f_modNr, Module_correl_inst.f_mizCopyExpTrmList((at).f_modArgs), 0));
	}
	public static final Struct_MizTyp f_mizCopyTrmType(Struct_MizCorrelEnv aenv, Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 77/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_varNr = l1__tmp.f_varNr;
			final int l4_srt = (l1__tmp).f_sort;
			final int l5_varNr = (l1__tmp).f_varNr;
			final int l6___ = (l1__tmp).f_pattNr;
			final int l7___ = (l1__tmp).f_trmInfo;
			if ((l4_srt==Module_lexicon.g_ikTrmBound)) {
				return Wrappers.w_mizCopyTyp.invoke(Module_correl.f_mizBoundVar(aenv, l5_varNr));
			} else {
				if ((l4_srt==Module_lexicon.g_ikTrmConstant)) {
					final Struct_MizFixedVar l8_fv = ((Struct_MizFixedVar)Module_vector.f_getVectorUnsafe((aenv).f_fixedVar, l5_varNr));
					final Struct_MizTyp l9_typ = Wrappers.w_mizCopyTyp.invoke((l8_fv).f_typ);
					final Struct_MizCorrelEnv l12_env = aenv;
					final Func2<Struct,Struct_MizWithin, Struct> l11_$10 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al12_0, Struct atrm) -> {
						Struct l13__tmp = atrm;
						switch (l13__tmp.getTypeId()) {
						case 77/*MizVarTrm*/: {
							final Struct_MizVarTrm l14__tmp = (Struct_MizVarTrm)l13__tmp;
							final int l15_s2 = l14__tmp.f_sort;
							final int l16_v2 = l14__tmp.f_varNr;
							final int l17_s2 = (l14__tmp).f_sort;
							final int l18_v2 = (l14__tmp).f_varNr;
							final int l19___ = (l14__tmp).f_pattNr;
							final int l20___ = (l14__tmp).f_trmInfo;
							if ((l17_s2==Module_lexicon.g_ikTrmBound)) {
								final Struct_MizVarTrm l21_vt = Module_correl_inst.f_mizAsVarTrmL(l14__tmp);
								((Field_varNr)l21_vt).set_varNr((l18_v2+(l12_env).f_boundVarNbr));
							} else {
							}
							return l14__tmp;
						}
						default: {
							return atrm;
						}
						}
					};
					final Struct_MizWithin l13_w = Module_correl.f_mizMakeWithin(l11_$10);
					Module_correl.f_mizWithinTyp(l13_w, l9_typ);
					return l9_typ;
				} else {
					if ((l4_srt==Module_lexicon.g_ikTrmInfConst)) {
						final Struct_MizConstDef l14_cd = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((aenv).f_inferConstDef, l5_varNr));
						return Wrappers.w_mizCopyTyp.invoke((l14_cd).f_typ);
					} else {
						if ((l4_srt==Module_lexicon.g_ikTrmNumeral)) {
							return Wrappers.w_mizCopyTyp.invoke((aenv).f_nonZeroTyp);
						} else {
							if ((l4_srt==Module_lexicon.g_ikTrmLocus)) {
								return Wrappers.w_mizCopyTyp.invoke(((Struct_MizTyp)Module_vector.f_getVectorUnsafe((aenv).f_locArgTyp, l5_varNr)));
							} else {
								Module_errhan.f_mizRunTimeError(2054);
								return Module_correl.f_mizNewIncorTyp();
							}
						}
					}
				}
			}
		}
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l15_srt = l1__tmp.f_sort;
			final int l16_funcNr = l1__tmp.f_funcNr;
			final Struct l17_args = l1__tmp.f_args;
			final int l18_srt = (l1__tmp).f_sort;
			final int l19_funcNr = (l1__tmp).f_funcNr;
			final Struct l20_args = (l1__tmp).f_args;
			final int l21___ = (l1__tmp).f_pattNr;
			final int l22___ = (l1__tmp).f_trmInfo;
			if ((l18_srt==Module_lexicon.g_ikTrmSchFunc)) {
				return Wrappers.w_mizCopyTyp.invoke(((Struct_MizTyp)Module_vector.f_getVectorUnsafe((aenv).f_curSchFuncTyp, (l19_funcNr-1))));
			} else {
				int l23_kind;
				if ((l18_srt==Module_lexicon.g_ikTrmFunctor)) {
					l23_kind=Module_correl.g_coFunctor;
				} else {
					if ((l18_srt==Module_lexicon.g_ikTrmSelector)) {
						l23_kind=Module_correl.g_coSelector;
					} else {
						l23_kind=Module_correl.g_coAggregate;
					}
				}
				final Struct_MizConstr l24_c = Module_correl.f_mizConstr(aenv, l23_kind, l19_funcNr);
				final Struct l25_gsymswitch2 = (l24_c).f_typ;
				Struct l26__tmp = l25_gsymswitch2;
				switch (l26__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l27__tmp = (Struct_Some)l26__tmp;
					final Object l28_ct = l27__tmp.f_value;
					final Struct_MizTyp l29_ct = ((Struct_MizTyp)(l27__tmp).f_value);
					return Module_correl_inst.f_mizInstTyp(aenv, l29_ct, l20_args);
				}
				case 82/*None*/: {
					Module_errhan.f_mizRunTimeError(2054);
					return Module_correl.f_mizNewIncorTyp();
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l26__tmp.getTypeName());
				}
			}
		}
		case 61/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final int l30_funcNr = l1__tmp.f_funcNr;
			final Struct l31_args = l1__tmp.f_args;
			final int l32_funcNr = (l1__tmp).f_funcNr;
			final Struct l33_args = (l1__tmp).f_args;
			final Struct l34___ = (l1__tmp).f_funcExp;
			final int l35___ = (l1__tmp).f_pattNr;
			final int l36___ = (l1__tmp).f_trmInfo;
			final Struct_MizFuncDef l37_fd = ((Struct_MizFuncDef)Module_vector.f_getVectorUnsafe((aenv).f_locFuncDef, (l32_funcNr-1)));
			return Module_correl_inst.f_mizInstTyp(aenv, (l37_fd).f_funcTyp, l33_args);
		}
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l1__tmp = (Struct_MizFraenkelTrm)l0__tmp;
			final Struct_Vector l38___ = (l1__tmp).f_lambdaArgs;
			final Struct l39___ = (l1__tmp).f_scope;
			final Struct l40___ = (l1__tmp).f_compr;
			final Struct_MizIntSeq l41___ = (l1__tmp).f_idents;
			final int l42___ = (l1__tmp).f_pattNr;
			final int l43___ = (l1__tmp).f_trmInfo;
			return Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqSetMode), ((Struct)SingletonStructs.str_MizTrmNil));
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
			final Struct_MizTyp l44_typ = l1__tmp.f_choiceTyp;
			final Struct_MizTyp l45_typ = (l1__tmp).f_choiceTyp;
			final int l46___ = (l1__tmp).f_pattNr;
			final int l47___ = (l1__tmp).f_trmInfo;
			return Wrappers.w_mizCopyTyp.invoke(l45_typ);
		}
		case 62/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
			final Struct_MizTyp l48_qua = l1__tmp.f_qua;
			final Struct l49___ = (l1__tmp).f_trmProper;
			final Struct_MizTyp l50_qua = (l1__tmp).f_qua;
			final int l51___ = (l1__tmp).f_pattNr;
			final int l52___ = (l1__tmp).f_trmInfo;
			return Wrappers.w_mizCopyTyp.invoke(l50_qua);
		}
		case 16/*MizBaseTrm*/: {
			final Struct_MizBaseTrm l1__tmp = (Struct_MizBaseTrm)l0__tmp;
			final int l53_srt = l1__tmp.f_sort;
			final int l54_srt = (l1__tmp).f_sort;
			final int l55___ = (l1__tmp).f_trmInfo;
			if ((l54_srt==Module_lexicon.g_ikTrmIt)) {
				return Wrappers.w_mizCopyTyp.invoke((aenv).f_itTyp);
			} else {
				return Module_correl.f_mizNewIncorTyp();
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final boolean f_mizDecreasingAttrs(Struct_MizCorrelEnv aenv, Struct_MizTyp asource, Struct_MizTyp atarget, Func2<Boolean,Struct_MizAttr, Struct_MizAttr> aeqAttr) {
		if ((Module_vector.f_sizeVector(((atarget).f_lower).f_items)==0)) {
			return true;
		} else {
			return Module_correl_inst.f_mizAttrCollIsSubsetOf(aenv, (atarget).f_lower, (asource).f_upper, aeqAttr);
		}
	}
	public static final boolean f_mizEqRadices(Struct_MizCorrelEnv aenv, Struct_MizTyp aa, Struct_MizTyp ab) {
		if (((aa).f_sort!=(ab).f_sort)) {
			return false;
		} else {
			if (((aa).f_sort==Module_lexicon.g_ikTypMode)) {
				final Struct_Pair l0_pa = Module_correl.f_mizAdjustTyp(aenv, aa);
				final Struct_Pair l1_pb = Module_correl.f_mizAdjustTyp(aenv, ab);
				return ((((int)(l0_pa).f_first)==((int)(l1_pb).f_first))&&Module_correl.f_mizEqTrmList(aenv, ((Struct)(l0_pa).f_second), ((Struct)(l1_pb).f_second)));
			} else {
				if (((aa).f_sort==Module_lexicon.g_ikTypStruct)) {
					return (((aa).f_modNr==(ab).f_modNr)&&Module_correl.f_mizEqTrmList(aenv, (aa).f_modArgs, (ab).f_modArgs));
				} else {
					Module_errhan.f_mizRunTimeError(2051);
					return false;
				}
			}
		}
	}
	public static final Struct_MizTyp f_mizGetTrmType(Struct_MizCorrelEnv aenv, Struct at) {
		return Module_correl_inst.f_mizCopyTrmType(aenv, at);
	}
	public static final Struct_MizAttrColl f_mizInstCluster(Struct_MizCorrelEnv aenv, Struct_MizAttrColl aclu, Struct aargs) {
		Module_correl_inst.f_mizSetInstantiation(aenv, aargs);
		final Struct_MizAttrColl l0_res = Module_correl_inst.f_mizInstCopyCluster(aenv, aclu);
		Module_correl_inst.f_mizStopInst(aenv);
		return l0_res;
	}
	public static final Struct_MizAttr f_mizInstCopyAttr(Struct_MizCorrelEnv aenv, Struct_MizAttr aa) {
		return (new Struct_MizAttr((aa).f_neg, (aa).f_attrNr, Module_correl_inst.f_mizInstCopyTrmList(aenv, (aa).f_args), false, (aa).f_pattNr));
	}
	public static final Struct_MizAttrColl f_mizInstCopyCluster(Struct_MizCorrelEnv aenv, Struct_MizAttrColl ac) {
		final Struct_Vector l2_$1 = (ac).f_items;
		final Struct_MizCorrelEnv l4_env = aenv;
		final Func1<Struct_MizAttr,Struct_MizAttr> l3_$0 = (Func1<Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			return Module_correl_inst.f_mizInstCopyAttr(l4_env, aa);
		};
		return (new Struct_MizAttrColl(Module_vector.f_mapVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0)), (ac).f_consistent));
	}
	public static final Struct f_mizInstCopyFrm(Struct_MizCorrelEnv aenv, Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 58/*MizPredFrm*/: {
			final Struct_MizPredFrm l1__tmp = (Struct_MizPredFrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_predNr = l1__tmp.f_predNr;
			final Struct l4_args = l1__tmp.f_args;
			final int l5_pattNr = l1__tmp.f_pattNr;
			final int l6_srt = (l1__tmp).f_sort;
			final int l7_predNr = (l1__tmp).f_predNr;
			final Struct l8_args = (l1__tmp).f_args;
			final int l9_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizPredFrm(l6_srt, l7_predNr, Module_correl_inst.f_mizInstCopyTrmList(aenv, l8_args), l9_pattNr));
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
			return (new Struct_MizLocPredFrm(l14_predNr, Module_correl_inst.f_mizInstCopyTrmList(aenv, l15_args), Module_correl_inst.f_mizInstCopyFrm(aenv, l16_expn), l17_pattNr));
		}
		case 63/*MizQualFrm*/: {
			final Struct_MizQualFrm l1__tmp = (Struct_MizQualFrm)l0__tmp;
			final Struct l18_trm = l1__tmp.f_trm;
			final Struct_MizTyp l19_typ = l1__tmp.f_typ;
			final int l20_pattNr = l1__tmp.f_pattNr;
			final Struct l21_trm = (l1__tmp).f_trm;
			final Struct_MizTyp l22_typ = (l1__tmp).f_typ;
			final int l23_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizQualFrm(Module_correl_inst.f_mizInstCopyTrm(aenv, l21_trm), Module_correl_inst.f_mizInstCopyTyp(aenv, l22_typ), l23_pattNr));
		}
		case 52/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l24_arg = l1__tmp.f_arg;
			final int l25_pattNr = l1__tmp.f_pattNr;
			final Struct l26_arg = (l1__tmp).f_arg;
			final int l27_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizNegFrm(Module_correl_inst.f_mizInstCopyFrm(aenv, l26_arg), l27_pattNr));
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
			return (new Struct_MizBinFrm(l32_srt, Module_correl_inst.f_mizInstCopyFrm(aenv, l33_l), Module_correl_inst.f_mizInstCopyFrm(aenv, l34_r), l35_pattNr));
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l36_conjuncts = l1__tmp.f_conjuncts;
			final int l37_pattNr = l1__tmp.f_pattNr;
			final Struct_Vector l38_conjuncts = (l1__tmp).f_conjuncts;
			final int l39_pattNr = (l1__tmp).f_pattNr;
			final Struct_MizCorrelEnv l42_env = aenv;
			final Func1<Struct,Struct> l41_$40 = (Func1<Struct, Struct>)(Struct ag) -> {
				return Module_correl_inst.f_mizInstCopyFrm(l42_env, ag);
			};
			return (new Struct_MizConjFrm(Module_vector.f_mapVector(l38_conjuncts, ((Func1<Object,Object>)(Func1)l41_$40)), l39_pattNr));
		}
		case 76/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l1__tmp = (Struct_MizUnivFrm)l0__tmp;
			final int l43_srt = l1__tmp.f_sort;
			final int l44_varId = l1__tmp.f_varId;
			final Struct_MizTyp l45_quantified = l1__tmp.f_quantified;
			final Struct l46_scope = l1__tmp.f_scope;
			final int l47_pattNr = l1__tmp.f_pattNr;
			final int l48_srt = (l1__tmp).f_sort;
			final int l49_varId = (l1__tmp).f_varId;
			final Struct_MizTyp l50_quantified = (l1__tmp).f_quantified;
			final Struct l51_scope = (l1__tmp).f_scope;
			final int l52_pattNr = (l1__tmp).f_pattNr;
			final Struct_MizTyp l53_q = Module_correl_inst.f_mizInstCopyTyp(aenv, l50_quantified);
			((Field_boundVarNbr)aenv).set_boundVarNbr(((aenv).f_boundVarNbr+1));
			final Struct l54_s = Module_correl_inst.f_mizInstCopyFrm(aenv, l51_scope);
			((Field_boundVarNbr)aenv).set_boundVarNbr(((aenv).f_boundVarNbr-1));
			return (new Struct_MizUnivFrm(l48_srt, l49_varId, l53_q, l54_s, l52_pattNr));
		}
		case 75/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l1__tmp = (Struct_MizUniqFrm)l0__tmp;
			final int l55_srt = l1__tmp.f_sort;
			final int l56_srt = (l1__tmp).f_sort;
			return (new Struct_MizUniqFrm(l56_srt));
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l1__tmp = (Struct_MizFlexFrm)l0__tmp;
			final int l57_srt = l1__tmp.f_sort;
			final Struct l58_lo = l1__tmp.f_leftOrig;
			final Struct l59_ro = l1__tmp.f_rightOrig;
			final Struct l60_lt = l1__tmp.f_leftTrm;
			final Struct l61_rt = l1__tmp.f_rightTrm;
			final Struct l62_expn = l1__tmp.f_expansion;
			final int l63_pattNr = l1__tmp.f_pattNr;
			final int l64_srt = (l1__tmp).f_sort;
			final Struct l65_lo = (l1__tmp).f_leftOrig;
			final Struct l66_ro = (l1__tmp).f_rightOrig;
			final Struct l67_lt = (l1__tmp).f_leftTrm;
			final Struct l68_rt = (l1__tmp).f_rightTrm;
			final Struct l69_expn = (l1__tmp).f_expansion;
			final int l70_pattNr = (l1__tmp).f_pattNr;
			return (new Struct_MizFlexFrm(l64_srt, Module_correl_inst.f_mizInstCopyFrm(aenv, l65_lo), Module_correl_inst.f_mizInstCopyFrm(aenv, l66_ro), Module_correl_inst.f_mizInstCopyTrm(aenv, l67_lt), Module_correl_inst.f_mizInstCopyTrm(aenv, l68_rt), Module_correl_inst.f_mizInstCopyFrm(aenv, l69_expn), l70_pattNr));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_mizInstCopyTrm(Struct_MizCorrelEnv aenv, Struct at) {
		final int l0_srt0 = Module_correl.f_mizTrmSort(at);
		if ((l0_srt0==Module_lexicon.g_ikTrmEqConst)) {
			return at;
		} else {
			if (((aenv).f_instantiated&&(l0_srt0==Module_lexicon.g_ikTrmLocus))) {
				final Struct_MizVarTrm l1_vt = Module_correl_inst.f_mizAsVarTrmL(at);
				if (((l1_vt).f_varNr>=Module_vector.f_sizeVector((aenv).f_instantiation))) {
					return Module_correl.f_mizNewIncorTrm();
				} else {
					((Field_instantiated)aenv).set_instantiated(false);
					final Struct l2_lTrm = Module_correl_inst.f_mizInstCopyTrm(aenv, ((Struct)Module_vector.f_getVectorUnsafe((aenv).f_instantiation, (l1_vt).f_varNr)));
					((Field_instantiated)aenv).set_instantiated(true);
					return Module_correl_inst.f_mizRenBound(aenv, l2_lTrm);
				}
			} else {
				Struct l3__tmp = at;
				switch (l3__tmp.getTypeId()) {
				case 77/*MizVarTrm*/: {
					final Struct_MizVarTrm l4__tmp = (Struct_MizVarTrm)l3__tmp;
					final int l5_srt = l4__tmp.f_sort;
					final int l6_varNr = l4__tmp.f_varNr;
					final int l7_pattNr = l4__tmp.f_pattNr;
					final int l8_srt = (l4__tmp).f_sort;
					final int l9_varNr = (l4__tmp).f_varNr;
					final int l10_pattNr = (l4__tmp).f_pattNr;
					final int l11___ = (l4__tmp).f_trmInfo;
					return (new Struct_MizVarTrm(l8_srt, l9_varNr, l10_pattNr, 0));
				}
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l4__tmp = (Struct_MizFuncTrm)l3__tmp;
					final int l12_srt = l4__tmp.f_sort;
					final int l13_funcNr = l4__tmp.f_funcNr;
					final Struct l14_args = l4__tmp.f_args;
					final int l15_pattNr = l4__tmp.f_pattNr;
					final int l16_srt = (l4__tmp).f_sort;
					final int l17_funcNr = (l4__tmp).f_funcNr;
					final Struct l18_args = (l4__tmp).f_args;
					final int l19_pattNr = (l4__tmp).f_pattNr;
					final int l20___ = (l4__tmp).f_trmInfo;
					return (new Struct_MizFuncTrm(l16_srt, l17_funcNr, Module_correl_inst.f_mizInstCopyTrmList(aenv, l18_args), l19_pattNr, 0));
				}
				case 61/*MizPrivFuncTrm*/: {
					final Struct_MizPrivFuncTrm l4__tmp = (Struct_MizPrivFuncTrm)l3__tmp;
					final int l21_funcNr = l4__tmp.f_funcNr;
					final Struct l22_args = l4__tmp.f_args;
					final Struct l23_funcExp = l4__tmp.f_funcExp;
					final int l24_pattNr = l4__tmp.f_pattNr;
					final int l25_funcNr = (l4__tmp).f_funcNr;
					final Struct l26_args = (l4__tmp).f_args;
					final Struct l27_funcExp = (l4__tmp).f_funcExp;
					final int l28_pattNr = (l4__tmp).f_pattNr;
					final int l29___ = (l4__tmp).f_trmInfo;
					return (new Struct_MizPrivFuncTrm(l25_funcNr, Module_correl_inst.f_mizInstCopyTrmList(aenv, l26_args), Module_correl_inst.f_mizInstCopyTrm(aenv, l27_funcExp), l28_pattNr, 0));
				}
				case 42/*MizFraenkelTrm*/: {
					final Struct_MizFraenkelTrm l4__tmp = (Struct_MizFraenkelTrm)l3__tmp;
					final Struct_Vector l30_lambdaArgs = l4__tmp.f_lambdaArgs;
					final Struct l31_scope = l4__tmp.f_scope;
					final Struct l32_compr = l4__tmp.f_compr;
					final Struct_MizIntSeq l33_idents = l4__tmp.f_idents;
					final int l34_pattNr = l4__tmp.f_pattNr;
					final Struct_Vector l35_lambdaArgs = (l4__tmp).f_lambdaArgs;
					final Struct l36_scope = (l4__tmp).f_scope;
					final Struct l37_compr = (l4__tmp).f_compr;
					final Struct_MizIntSeq l38_idents = (l4__tmp).f_idents;
					final int l39_pattNr = (l4__tmp).f_pattNr;
					final int l40___ = (l4__tmp).f_trmInfo;
					final int l41_i0 = (aenv).f_boundVarNbr;
					final Struct_Vector l42_la = Module_vector.f_makeVector(Module_vector.f_sizeVector(l35_lambdaArgs));
					final Struct_MizCorrelEnv l45_env = aenv;
					final Func1<Object,Struct_MizTyp> l44_$43 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp atyp) -> {
						((Field_boundVarNbr)l45_env).set_boundVarNbr(((l45_env).f_boundVarNbr+1));
						return Module_vector.f_pushVector(l42_la, Module_correl_inst.f_mizInstCopyTyp(l45_env, atyp));
					};
					Module_vector.f_iterVector(l35_lambdaArgs, ((Func1<Object,Object>)(Func1)l44_$43));
					final Struct l46_sc = Module_correl_inst.f_mizInstCopyTrm(aenv, l36_scope);
					final Struct l47_cp = Module_correl_inst.f_mizInstCopyFrm(aenv, l37_compr);
					((Field_boundVarNbr)aenv).set_boundVarNbr(l41_i0);
					return (new Struct_MizFraenkelTrm(l42_la, l46_sc, l47_cp, Module_mobjects.f_mizCopyIntSeq(l38_idents), l39_pattNr, 0));
				}
				case 21/*MizChoiceTrm*/: {
					final Struct_MizChoiceTrm l4__tmp = (Struct_MizChoiceTrm)l3__tmp;
					final Struct_MizTyp l48_typ = l4__tmp.f_choiceTyp;
					final int l49_pattNr = l4__tmp.f_pattNr;
					final Struct_MizTyp l50_typ = (l4__tmp).f_choiceTyp;
					final int l51_pattNr = (l4__tmp).f_pattNr;
					final int l52___ = (l4__tmp).f_trmInfo;
					return (new Struct_MizChoiceTrm(Module_correl_inst.f_mizInstCopyTyp(aenv, l50_typ), l51_pattNr, 0));
				}
				case 62/*MizQuaTrm*/: {
					final Struct_MizQuaTrm l4__tmp = (Struct_MizQuaTrm)l3__tmp;
					final Struct l53_trmProper = l4__tmp.f_trmProper;
					final Struct_MizTyp l54_qua = l4__tmp.f_qua;
					final int l55_pattNr = l4__tmp.f_pattNr;
					final Struct l56_trmProper = (l4__tmp).f_trmProper;
					final Struct_MizTyp l57_qua = (l4__tmp).f_qua;
					final int l58_pattNr = (l4__tmp).f_pattNr;
					final int l59___ = (l4__tmp).f_trmInfo;
					return (new Struct_MizQuaTrm(Module_correl_inst.f_mizInstCopyTrm(aenv, l56_trmProper), Module_correl_inst.f_mizInstCopyTyp(aenv, l57_qua), l58_pattNr, 0));
				}
				case 16/*MizBaseTrm*/: {
					final Struct_MizBaseTrm l4__tmp = (Struct_MizBaseTrm)l3__tmp;
					final int l60_srt = l4__tmp.f_sort;
					final int l61_srt = (l4__tmp).f_sort;
					final int l62___ = (l4__tmp).f_trmInfo;
					return (new Struct_MizBaseTrm(l61_srt, 0));
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l3__tmp.getTypeName());
				}
			}
		}
	}
	public static final Struct f_mizInstCopyTrmList(Struct_MizCorrelEnv aenv, Struct al) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			return al;
		}
		case 70/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			return (new Struct_MizTrmElem(Module_correl_inst.f_mizInstCopyTrm(aenv, l4_trm), Module_correl_inst.f_mizInstCopyTrmList(aenv, l5_next)));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct_MizTyp f_mizInstCopyTyp(Struct_MizCorrelEnv aenv, Struct_MizTyp at) {
		return (new Struct_MizTyp((at).f_sort, Module_correl_inst.f_mizInstCopyCluster(aenv, (at).f_lower), Module_correl_inst.f_mizInstCopyCluster(aenv, (at).f_upper), (at).f_modNr, Module_correl_inst.f_mizInstCopyTrmList(aenv, (at).f_modArgs), (at).f_pattNr));
	}
	public static final Struct_MizTyp f_mizInstTyp(Struct_MizCorrelEnv aenv, Struct_MizTyp atyp, Struct aargs) {
		Module_correl_inst.f_mizSetInstantiation(aenv, aargs);
		final Struct_MizTyp l0_res = Module_correl_inst.f_mizInstCopyTyp(aenv, atyp);
		Module_correl_inst.f_mizStopInst(aenv);
		return l0_res;
	}
	public static final boolean f_mizIsWiderThan(Struct_MizCorrelEnv aenv, Struct_MizTyp awider, Struct_MizTyp anarrower0) {
		final Struct_MizCorrelEnv l2_env = aenv;
		final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l1_$0 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
			return ((Boolean)Module_correl.f_mizEqAttr(l2_env, ax, ay));
		};
		if (!Module_correl_inst.f_mizDecreasingAttrs(aenv, anarrower0, awider, l1_$0)) {
			return false;
		} else {
			if (((awider).f_sort==Module_lexicon.g_ikTypMode)) {
				final Struct_Pair l3_pw = Module_correl.f_mizAdjustTyp(aenv, awider);
				final int l4_lModNr = ((int)(l3_pw).f_first);
				final Reference<Struct_MizTyp> l5_cur = ((Reference<Struct_MizTyp>)(new Reference(anarrower0)));
				final Reference<Boolean> l6_res = ((Reference<Boolean>)(new Reference(false)));
				final Reference<Boolean> l7_brk = ((Reference<Boolean>)(new Reference(false)));
				final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
					return ((Boolean)((!((boolean)l7_brk.value)&&((l5_cur.value).f_sort==Module_lexicon.g_ikTypMode))&&((l5_cur.value).f_modNr>=l4_lModNr)));
				};
				final Struct_MizCorrelEnv l12_env = aenv;
				final Struct_MizTyp l13_wider = awider;
				final Func0<Object> l11_$9 = (Func0<Object>)() -> {
					if (Module_correl_inst.f_mizEqRadices(l12_env, l13_wider, l5_cur.value)) {
						l6_res.value = ((Boolean)true);
						l7_brk.value = ((Boolean)true);
						return null;
					} else {
						final Struct l12_gsymswitch10 = Module_correl_inst.f_mizWidening(l12_env, l5_cur.value);
						Struct l13__tmp = l12_gsymswitch10;
						switch (l13__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l14__tmp = (Struct_Some)l13__tmp;
							final Object l15_w = l14__tmp.f_value;
							final Struct_MizTyp l16_w = ((Struct_MizTyp)(l14__tmp).f_value);
							l5_cur.value = l16_w;
							return null;
						}
						case 82/*None*/: {
							l7_brk.value = ((Boolean)true);
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l13__tmp.getTypeName());
						}
					}
				};
				Module_loop.f_while(l10_$8, l11_$9);
				if ((!((boolean)l6_res.value)&&((l5_cur.value).f_sort==Module_lexicon.g_ikTypStruct))) {
					if (((l4_lModNr==Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqSetMode))||(l4_lModNr==Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqAny)))) {
						l6_res.value = ((Boolean)true);
					} else {
					}
				} else {
				}
				return ((boolean)l6_res.value);
			} else {
				if (((awider).f_sort==Module_lexicon.g_ikTypStruct)) {
					final Struct l14_gsymswitch9 = Module_correl_inst.f_mizWideningOf(aenv, awider, anarrower0);
					Struct l15__tmp = l14_gsymswitch9;
					switch (l15__tmp.getTypeId()) {
					case 82/*None*/: {
						return false;
					}
					case 90/*Some*/: {
						final Struct_Some l16__tmp = (Struct_Some)l15__tmp;
						final Object l17_w = l16__tmp.f_value;
						final Struct_MizTyp l18_w = ((Struct_MizTyp)(l16__tmp).f_value);
						return Module_correl_inst.f_mizEqRadices(aenv, awider, l18_w);
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l15__tmp.getTypeName());
					}
				} else {
					return false;
				}
			}
		}
	}
	public static final Struct f_mizNewDisj(Struct aa, Struct ab) {
		return Module_correl.f_mizNewNeg(Module_correl.f_mizNewConj(Module_correl.f_mizNewNeg(aa), Module_correl.f_mizNewNeg(ab)));
	}
	public static final Struct f_mizRenBound(Struct_MizCorrelEnv aenv, Struct at) {
		final Struct_MizCorrelEnv l2_env = aenv;
		final Func2<Struct,Struct_MizWithin, Struct> l1_$0 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al2_0, Struct atrm) -> {
			Struct l3__tmp = atrm;
			switch (l3__tmp.getTypeId()) {
			case 77/*MizVarTrm*/: {
				final Struct_MizVarTrm l4__tmp = (Struct_MizVarTrm)l3__tmp;
				final int l5_srt = l4__tmp.f_sort;
				final int l6_varNr = l4__tmp.f_varNr;
				final int l7_srt = (l4__tmp).f_sort;
				final int l8_varNr = (l4__tmp).f_varNr;
				final int l9___ = (l4__tmp).f_pattNr;
				final int l10___ = (l4__tmp).f_trmInfo;
				if (((l7_srt==Module_lexicon.g_ikTrmBound)&&(l8_varNr>(l2_env).f_boundBase))) {
					final Struct_MizVarTrm l11_vt = Module_correl_inst.f_mizAsVarTrmL(l4__tmp);
					((Field_varNr)l11_vt).set_varNr((l8_varNr+((l2_env).f_boundVarNbr-(l2_env).f_boundBase)));
				} else {
				}
				return l4__tmp;
			}
			default: {
				return atrm;
			}
			}
		};
		final Struct_MizWithin l3_w = Module_correl.f_mizMakeWithin(l1_$0);
		return Module_correl.f_mizWithinTrm(l3_w, at);
	}
	public static final Object f_mizSetInstantiation(Struct_MizCorrelEnv aenv, Struct aargs) {
		Module_vector.f_clearVector((aenv).f_instantiation);
		Module_vector.f_pushVector((aenv).f_instantiation, Module_correl.f_mizNewIncorTrm());
		final Reference<Struct> l0_cur = ((Reference<Struct>)(new Reference(aargs)));
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			final Struct l4_gsymswitch0 = l0_cur.value;
			Struct l5__tmp = l4_gsymswitch0;
			switch (l5__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				return ((Boolean)false);
			}
			default: {
				return ((Boolean)true);
			}
			}
		};
		final Struct_MizCorrelEnv l5_env = aenv;
		final Func0<Object> l4_$2 = (Func0<Object>)() -> {
			final Struct l5_gsymswitch1 = l0_cur.value;
			Struct l6__tmp = l5_gsymswitch1;
			switch (l6__tmp.getTypeId()) {
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l7__tmp = (Struct_MizTrmElem)l6__tmp;
				final Struct l8_trm = l7__tmp.f_trm;
				final Struct l9_next = l7__tmp.f_next;
				final Struct l10_trm = (l7__tmp).f_trm;
				final Struct l11_next = (l7__tmp).f_next;
				Module_vector.f_pushVector((l5_env).f_instantiation, l10_trm);
				l0_cur.value = l11_next;
				return null;
			}
			case 71/*MizTrmNil*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l6__tmp.getTypeName());
			}
		};
		Module_loop.f_while(l3_$1, l4_$2);
		((Field_instantiated)aenv).set_instantiated(true);
		((Field_boundBase)aenv).set_boundBase((aenv).f_boundVarNbr);
		return null;
	}
	public static final Object f_mizStopInst(Struct_MizCorrelEnv aenv) {
		((Field_instantiated)aenv).set_instantiated(false);
		return null;
	}
	public static final boolean f_mizTypReachable(Struct_MizCorrelEnv aenv, Struct_MizTyp awider, Struct_MizTyp anarrower) {
		if ((((awider).f_sort!=Module_lexicon.g_ikTypMode)||((anarrower).f_sort!=Module_lexicon.g_ikTypMode))) {
			return true;
		} else {
			if (((awider).f_modNr==Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqAny))) {
				return true;
			} else {
				final int l0_lModNr0 = (awider).f_modNr;
				final Struct_MizConstr l1_cw = Module_correl.f_mizConstr(aenv, Module_correl.g_coMode, l0_lModNr0);
				int l2_lModNr;
				if (((l1_cw).f_whichConstrNr!=0)) {
					l2_lModNr=(l1_cw).f_whichConstrNr;
				} else {
					l2_lModNr=l0_lModNr0;
				}
				final Reference<Struct_MizTyp> l3_cur = ((Reference<Struct_MizTyp>)(new Reference(anarrower)));
				final Reference<Boolean> l4_res = ((Reference<Boolean>)(new Reference(false)));
				final Reference<Boolean> l5_brk = ((Reference<Boolean>)(new Reference(false)));
				final int l9_lModNr = l2_lModNr;
				final Func0<Boolean> l8_$6 = (Func0<Boolean>)() -> {
					return ((Boolean)((!((boolean)l5_brk.value)&&((l3_cur.value).f_sort==Module_lexicon.g_ikTypMode))&&((l3_cur.value).f_modNr>=l9_lModNr)));
				};
				final Struct_MizCorrelEnv l11_env = aenv;
				final int l12_lModNr = l2_lModNr;
				final Func0<Object> l10_$7 = (Func0<Object>)() -> {
					final Struct_MizConstr l11_cn = Module_correl.f_mizConstr(l11_env, Module_correl.g_coMode, (l3_cur.value).f_modNr);
					final int l12_orig = (l11_cn).f_whichConstrNr;
					if ((((l3_cur.value).f_modNr==l12_lModNr)||(l12_orig==l12_lModNr))) {
						l4_res.value = ((Boolean)true);
						l5_brk.value = ((Boolean)true);
						return null;
					} else {
						final Struct l13_gsymswitch3 = (l11_cn).f_typ;
						Struct l14__tmp = l13_gsymswitch3;
						switch (l14__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l15__tmp = (Struct_Some)l14__tmp;
							final Object l16_ct = l15__tmp.f_value;
							final Struct_MizTyp l17_ct = ((Struct_MizTyp)(l15__tmp).f_value);
							l3_cur.value = l17_ct;
							return null;
						}
						case 82/*None*/: {
							l5_brk.value = ((Boolean)true);
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l14__tmp.getTypeName());
						}
					}
				};
				Module_loop.f_while(l8_$6, l10_$7);
				return ((boolean)l4_res.value);
			}
		}
	}
	public static final Struct f_mizWidenToStruct(Struct_MizCorrelEnv aenv, Struct_MizTyp at) {
		final Reference<Struct_MizTyp> l0_cur = ((Reference<Struct_MizTyp>)(new Reference(Wrappers.w_mizCopyTyp.invoke(at))));
		final Reference<Struct> l1_res = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
		final Reference<Boolean> l2_brk = ((Reference<Boolean>)(new Reference(false)));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return ((Boolean)!((boolean)l2_brk.value));
		};
		final Struct_MizCorrelEnv l7_env = aenv;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			if (((l0_cur.value).f_sort==Module_lexicon.g_ikTypMode)) {
				final Struct l7_gsymswitch6 = Module_correl_inst.f_mizWidening(l7_env, l0_cur.value);
				Struct l8__tmp = l7_gsymswitch6;
				switch (l8__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l9__tmp = (Struct_Some)l8__tmp;
					final Object l10_w = l9__tmp.f_value;
					final Struct_MizTyp l11_w = ((Struct_MizTyp)(l9__tmp).f_value);
					l0_cur.value = l11_w;
					return null;
				}
				case 82/*None*/: {
					l2_brk.value = ((Boolean)true);
					return null;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l8__tmp.getTypeName());
				}
			} else {
				((Reference<Struct_Some>)(Reference)l1_res).value = (new Struct_Some(l0_cur.value));
				l2_brk.value = ((Boolean)true);
				return null;
			}
		};
		Module_loop.f_while(l5_$3, l6_$4);
		return l1_res.value;
	}
	public static final Struct f_mizWidening(Struct_MizCorrelEnv aenv, Struct_MizTyp at) {
		if (((at).f_sort==Module_lexicon.g_ikTypMode)) {
			if (((at).f_modNr==Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqAny))) {
				return ((Struct)SingletonStructs.str_None);
			} else {
				final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coMode, (at).f_modNr);
				final Struct l1_gsymswitch5 = (l0_c).f_typ;
				Struct l2__tmp = l1_gsymswitch5;
				switch (l2__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l3__tmp = (Struct_Some)l2__tmp;
					final Object l4_ct = l3__tmp.f_value;
					final Struct_MizTyp l5_ct = ((Struct_MizTyp)(l3__tmp).f_value);
					final Struct_MizTyp l6_lTyp = Module_correl_inst.f_mizInstTyp(aenv, l5_ct, (at).f_modArgs);
					((Field_upper)l6_lTyp).set_upper(Module_correl_inst.f_mizCopyAllowed(aenv, at, (at).f_upper));
					return (new Struct_Some(l6_lTyp));
				}
				case 82/*None*/: {
					return ((Struct)SingletonStructs.str_None);
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
				}
			}
		} else {
			if (((at).f_sort==Module_lexicon.g_ikTypStruct)) {
				return (new Struct_Some(Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqSetMode), ((Struct)SingletonStructs.str_MizTrmNil))));
			} else {
				Module_errhan.f_mizRunTimeError(2223);
				return ((Struct)SingletonStructs.str_None);
			}
		}
	}
	public static final Struct f_mizWideningOf(Struct_MizCorrelEnv aenv, Struct_MizTyp atarget, Struct_MizTyp asource) {
		if (((atarget).f_sort==Module_lexicon.g_ikTypMode)) {
			final Struct_Pair l0_pt = Module_correl.f_mizAdjustTyp(aenv, atarget);
			final int l1_lModNr = ((int)(l0_pt).f_first);
			final Reference<Struct_MizTyp> l2_cur = ((Reference<Struct_MizTyp>)(new Reference(asource)));
			final Reference<Struct> l3_res = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
			final Reference<Boolean> l4_brk = ((Reference<Boolean>)(new Reference(false)));
			final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
				return ((Boolean)((!((boolean)l4_brk.value)&&((l2_cur.value).f_sort==Module_lexicon.g_ikTypMode))&&((l2_cur.value).f_modNr>=l1_lModNr)));
			};
			final Struct_MizCorrelEnv l9_env = aenv;
			final Func0<Object> l8_$6 = (Func0<Object>)() -> {
				final Struct_MizConstr l9_cm = Module_correl.f_mizConstr(l9_env, Module_correl.g_coMode, (l2_cur.value).f_modNr);
				if ((((l2_cur.value).f_modNr==l1_lModNr)||((l9_cm).f_whichConstrNr==l1_lModNr))) {
					((Reference<Struct_Some>)(Reference)l3_res).value = (new Struct_Some(l2_cur.value));
					l4_brk.value = ((Boolean)true);
					return null;
				} else {
					final Struct l10_gsymswitch8 = Module_correl_inst.f_mizWidening(l9_env, l2_cur.value);
					Struct l11__tmp = l10_gsymswitch8;
					switch (l11__tmp.getTypeId()) {
					case 90/*Some*/: {
						final Struct_Some l12__tmp = (Struct_Some)l11__tmp;
						final Object l13_w = l12__tmp.f_value;
						final Struct_MizTyp l14_w = ((Struct_MizTyp)(l12__tmp).f_value);
						l2_cur.value = l14_w;
						return null;
					}
					case 82/*None*/: {
						l4_brk.value = ((Boolean)true);
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l11__tmp.getTypeName());
					}
				}
			};
			Module_loop.f_while(l7_$5, l8_$6);
			if ((Module_maybe.f_isNone(l3_res.value)&&((l2_cur.value).f_sort==Module_lexicon.g_ikTypStruct))) {
				if ((l1_lModNr==Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqSetMode))) {
					((Reference<Struct_Some>)(Reference)l3_res).value = (new Struct_Some((new Struct_MizTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqSetMode), ((Struct)SingletonStructs.str_MizTrmNil), 0))));
				} else {
					if ((l1_lModNr==Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqAny))) {
						((Reference<Struct_Some>)(Reference)l3_res).value = (new Struct_Some((new Struct_MizTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), Module_builtin.f_mizBuiltIn((aenv).f_builtIn, Module_builtin.g_rqAny), ((Struct)SingletonStructs.str_MizTrmNil), 0))));
					} else {
					}
				}
			} else {
			}
			return l3_res.value;
		} else {
			if (((atarget).f_sort==Module_lexicon.g_ikTypStruct)) {
				final Struct l10_gsymswitch7 = Module_correl_inst.f_mizWidenToStruct(aenv, asource);
				Struct l11__tmp = l10_gsymswitch7;
				switch (l11__tmp.getTypeId()) {
				case 82/*None*/: {
					return ((Struct)SingletonStructs.str_None);
				}
				case 90/*Some*/: {
					final Struct_Some l12__tmp = (Struct_Some)l11__tmp;
					final Object l13_st0 = l12__tmp.f_value;
					final Struct_MizTyp l14_st0 = ((Struct_MizTyp)(l12__tmp).f_value);
					if (((atarget).f_modNr==(l14_st0).f_modNr)) {
						return (new Struct_Some(l14_st0));
					} else {
						final Struct_MizConstr l15_cs = Module_correl.f_mizConstr(aenv, Module_correl.g_coStructMode, (atarget).f_modNr);
						final Struct_MizConstr l16_co = Module_correl.f_mizConstr(aenv, Module_correl.g_coStructMode, (l14_st0).f_modNr);
						if (((Module_mobjects.f_mizNatCount((l15_cs).f_fields)==0)||!Module_mobjects.f_mizNatIsSubsetOf((l15_cs).f_fields, (l16_co).f_fields))) {
							return ((Struct)SingletonStructs.str_None);
						} else {
							final Struct_Vector l17_path = Module_vector.f_makeVector(4);
							final boolean l18_found = Module_correl_inst.f_mizWidenningPath(aenv, (l14_st0).f_modNr, (atarget).f_modNr, l17_path);
							if (l18_found) {
								final Reference<Struct_MizTyp> l19_cur2 = ((Reference<Struct_MizTyp>)(new Reference(l14_st0)));
								final Struct_MizCorrelEnv l22_env = aenv;
								final Func1<Object,Struct_MizTyp> l21_$20 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp apref) -> {
									l19_cur2.value = Module_correl_inst.f_mizInstTyp(l22_env, apref, (l19_cur2.value).f_modArgs);
									return null;
								};
								Module_vector.f_iterVector(l17_path, ((Func1<Object,Object>)(Func1)l21_$20));
								return (new Struct_Some(l19_cur2.value));
							} else {
								return ((Struct)SingletonStructs.str_None);
							}
						}
					}
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l11__tmp.getTypeName());
				}
			} else {
				return ((Struct)SingletonStructs.str_None);
			}
		}
	}
	public static final boolean f_mizWidenningPath(Struct_MizCorrelEnv aenv, int afrom, int atarget, Struct_Vector apath) {
		final Struct_MizConstr l0_cs = Module_correl.f_mizConstr(aenv, Module_correl.g_coStructMode, afrom);
		final Reference<Boolean> l1_found = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l2_i = ((Reference<Integer>)(new Reference(0)));
		final Struct_Vector l3_prefs = Module_correl.f_mizConstrPrefixes(aenv, afrom);
		final int l4_n = Module_vector.f_sizeVector(l3_prefs);
		final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l1_found.value)&&(((int)l2_i.value)<l4_n)));
		};
		final int l9_target = atarget;
		final Struct_Vector l10_path = apath;
		final Struct_MizCorrelEnv l11_env = aenv;
		final Func0<Object> l8_$6 = (Func0<Object>)() -> {
			final Struct_MizTyp l9_p = ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l3_prefs, ((int)l2_i.value)));
			if (((l9_p).f_modNr==l9_target)) {
				Module_vector.f_pushVector(l10_path, l9_p);
				l1_found.value = ((Boolean)true);
			} else {
				if (Module_correl_inst.f_mizWidenningPath(l11_env, (l9_p).f_modNr, l9_target, l10_path)) {
					Module_mobjects.f_mizVectorAtInsert(l10_path, 0, l9_p);
					l1_found.value = ((Boolean)true);
				} else {
				}
			}
			l2_i.value = ((Integer)(((int)l2_i.value)+1));
			return null;
		};
		Module_loop.f_while(l7_$5, l8_$6);
		return ((boolean)l1_found.value);
	}
}
