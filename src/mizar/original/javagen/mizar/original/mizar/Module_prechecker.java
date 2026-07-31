// HASH COLLISIONS: YES
// timestamp: 1785496853000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_prechecker {
	public static final Struct f_mizApplySetVar(Struct_MizPre apre, Struct af) {
		final Struct_MizPre l2_pre = apre;
		final Func2<Struct,Struct_MizWithin, Struct> l1_$0 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al2_0, Struct atrm) -> {
			return (l2_pre).f_setVarInTrm.invoke(atrm);
		};
		final Struct_MizWithin l3_w = Module_correl.f_mizMakeWithin(l1_$0);
		Module_correl.f_mizWithinFrm(l3_w, af);
		return af;
	}
	public static final Struct_Vector f_mizAsConjuncts(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l2_conjuncts = l1__tmp.f_conjuncts;
			final Struct_Vector l3_conjuncts = (l1__tmp).f_conjuncts;
			final int l4___ = (l1__tmp).f_pattNr;
			return l3_conjuncts;
		}
		default: {
			Module_errhan.f_mizRunTimeError(2912);
			return Module_vector.f_makeVector(0);
		}
		}
	}
	public static final Struct f_mizAsNegArg(Struct af) {
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
			Module_errhan.f_mizRunTimeError(2911);
			return af;
		}
		}
	}
	public static final Struct_MizQualFrm f_mizAsQualFrmP(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 60/*MizQualFrm*/: {
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
	public static final Struct_MizVarTrm f_mizAsVarTrmB(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 70/*MizVarTrm*/: {
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
	public static final int f_mizBasicFrmNr(Struct_MizPre apre, Struct af) {
		final int l0_n = Module_vector.f_sizeVector((apre).f_basic);
		final Reference<Integer> l1_res = ((Reference<Integer>)(new Reference((-1))));
		final Reference<Integer> l2_i = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return ((Boolean)((((int)l2_i.value)<l0_n)&&(((int)l1_res.value)<0)));
		};
		final Struct_MizPre l7_pre = apre;
		final Struct l8_f = af;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			if (Module_correl.f_mizEqFrm((l7_pre).f_env, l8_f, ((Struct)Module_vector.f_getVectorUnsafe((l7_pre).f_basic, ((int)l2_i.value))))) {
				l1_res.value = l2_i.value;
			} else {
			}
			l2_i.value = ((Integer)(((int)l2_i.value)+1));
			return null;
		};
		Module_loop.f_while(l5_$3, l6_$4);
		return ((int)l1_res.value);
	}
	public static final Object f_mizCreateInference(Struct_MizPre apre, Struct_MizPreInst anormalForm) {
		final Struct_MizCorrelEnv l0_env = (apre).f_env;
		final Reference<Integer> l1_z = ((Reference<Integer>)(new Reference(0)));
		final Reference<Integer> l2_zCount = ((Reference<Integer>)(new Reference(Module_vector.f_sizeVector((anormalForm).f_evals))));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return (((int)l1_z.value)<((int)l2_zCount.value));
		};
		final Struct_MizPreInst l7_normalForm = anormalForm;
		final Struct_MizPre l8_pre = apre;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			final Struct_MizNatFunc l7_this = ((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe((l7_normalForm).f_evals, ((int)l1_z.value)));
			final Struct_Vector l8_lQual = Module_vector.f_makeVector(4);
			final Struct_MizNatFunc l9_lEval = Module_mobjects.f_mizCopyNatFunc(l7_this);
			final int l12_$11 = (Module_vector.f_sizeVector((l8_pre).f_basic)-1);
			final Func1<Object,Integer> l13_$10 = (Func1<Object, Integer>)(Integer ai) -> {
				if (((Module_mobjects.f_mizNatHasInDom(l7_this, ((int)ai))&&(Module_mobjects.f_mizNatValue(l7_this, ((int)ai))==0))&&(Module_correl.f_mizFrmSort(((Struct)Module_vector.f_getVectorUnsafe((l8_pre).f_basic, ((int)ai))))==Module_lexicon.g_ikFrmQual))) {
					Module_mobjects.f_mizNatDeleteElem(l9_lEval, ((int)ai));
					return Module_vector.f_pushVector(l8_lQual, Module_vector.f_getVectorUnsafe((l8_pre).f_basic, ((int)ai)));
				} else {
					return null;
				}
			};
			Module_runtime.f_fori(0, l12_$11, l13_$10);
			if ((Module_vector.f_sizeVector(l8_lQual)>0)) {
				final Struct_MizPreInst l14_lNormalForm = Module_prechecker.f_mizPreInstSingle(Module_mobjects.f_mizCopyNatFunc(l9_lEval));
				final Func1<Object,Struct> l16_$15 = (Func1<Object, Struct>)(Struct alFrm) -> {
					final Struct_MizQualFrm l17_qf = Module_prechecker.f_mizAsQualFrmP(alFrm);
					final Struct l18_lSubject = Module_correl.f_mizCopyTrm((l17_qf).f_trm);
					final Struct_MizTyp l19_lQualification = Wrappers.w_mizCopyTyp.invoke((l17_qf).f_typ);
					((Field_lower)l19_lQualification).set_lower(Module_correl.f_mizNewEmptyCluster());
					((Field_upper)l19_lQualification).set_upper(Module_correl.f_mizNewEmptyCluster());
					final int l20_lQualified = Module_prechecker.f_mizNewBasic(l8_pre, Module_correl.f_mizNewQualFrm(Module_correl.f_mizCopyTrm(l18_lSubject), Wrappers.w_mizCopyTyp.invoke(l19_lQualification)));
					final Struct_MizPreInst l21_lEvalList = Module_prechecker.f_mizPreInstBottom();
					final Struct_Vector l24_$23 = (((l17_qf).f_typ).f_lower).f_items;
					final Func1<Object,Struct_MizAttr> l25_$22 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aattr) -> {
						final Struct_MizTyp l26_lQualTyp = Wrappers.w_mizCopyTyp.invoke(l19_lQualification);
						final Struct_MizConstr l27_ca = Module_correl.f_mizConstr(l0_env, Module_correl.g_coAttribute, (aattr).f_attrNr);
						final Struct l28_gsymswitch4 = (l27_ca).f_typ;
						Struct_MizAttrColl l29_lClusterPtr;
						Struct l30__tmp = l28_gsymswitch4;
						switch (l30__tmp.getTypeId()) {
						case 83/*Some*/: {
							final Struct_Some l31__tmp = (Struct_Some)l30__tmp;
							final Object l32_ct = l31__tmp.f_value;
							final Struct_MizTyp l33_ct = ((Struct_MizTyp)(l31__tmp).f_value);
							l29_lClusterPtr=Module_correl_inst.f_mizInstCluster(l0_env, (l33_ct).f_upper, (aattr).f_args);
							break;
						}
						case 75/*None*/: {
							l29_lClusterPtr=Module_correl.f_mizNewEmptyCluster();
							break;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l30__tmp.getTypeName());
						}
						int l34_counterNeg;
						if (((aattr).f_neg==0)) {
							l34_counterNeg=1;
						} else {
							l34_counterNeg=0;
						}
						Module_correl.f_mizAttrCollInsert(l0_env, l29_lClusterPtr, (new Struct_MizAttr(l34_counterNeg, (aattr).f_attrNr, Module_correl.f_mizCopyTrmList((aattr).f_args), false, 0)));
						((Field_lower)l26_lQualTyp).set_lower(l29_lClusterPtr);
						((Field_upper)l26_lQualTyp).set_upper(Module_correl.f_mizCopyCluster(l29_lClusterPtr));
						final Struct_MizNatFunc l35_llEval = Module_mobjects.f_mizInitNatFunc(2);
						Module_mobjects.f_mizNatAssign(l35_llEval, Module_prechecker.f_mizNewBasic(l8_pre, Module_correl.f_mizNewQualFrm(Module_correl.f_mizCopyTrm(l18_lSubject), l26_lQualTyp)), 1);
						return Module_prechecker.f_mizPreInstInsertAndAbsorb(l21_lEvalList, l35_llEval);
					};
					Module_vector.f_iterVector(l24_$23, ((Func1<Object,Object>)(Func1)l25_$22));
					final Struct_MizNatFunc l26_lNegBas = Module_mobjects.f_mizInitNatFunc(2);
					Module_mobjects.f_mizNatAssign(l26_lNegBas, l20_lQualified, 0);
					final Struct_MizPreInst l27_lEvalList1 = Module_prechecker.f_mizPreInstSingle(l26_lNegBas);
					Module_prechecker.f_mizPreInstUnionWith(l8_pre, l21_lEvalList, l27_lEvalList1);
					return Module_prechecker.f_mizPreInstJoinWith(l8_pre, l14_lNormalForm, l21_lEvalList);
				};
				Module_vector.f_iterVector(l8_lQual, ((Func1<Object,Object>)(Func1)l16_$15));
				Module_vector.f_removeVector((l7_normalForm).f_evals, ((int)l1_z.value));
				l2_zCount.value = ((Integer)(((int)l2_zCount.value)-1));
				final Struct_Vector l19_$18 = (l14_lNormalForm).f_evals;
				final Func1<Object,Struct_MizNatFunc> l20_$17 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
					return Module_vector.f_pushVector((l7_normalForm).f_evals, ae);
				};
				return Module_vector.f_iterVector(l19_$18, ((Func1<Object,Object>)(Func1)l20_$17));
			} else {
				l1_z.value = ((Integer)(((int)l1_z.value)+1));
				return null;
			}
		};
		return Module_loop.f_while(l5_$3, l6_$4);
	}
	public static final Struct f_mizDistributeQuantifiers(Struct_MizPre apre, Struct af) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (apre).f_env;
		Struct l1__tmp = af;
		switch (l1__tmp.getTypeId()) {
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l2__tmp = (Struct_MizNegFrm)l1__tmp;
			final Struct l3_arg = l2__tmp.f_arg;
			final Struct l4_arg = (l2__tmp).f_arg;
			final int l5___ = (l2__tmp).f_pattNr;
			return Module_correl.f_mizNewNeg(Module_prechecker.f_mizDistributeQuantifiers(apre, l4_arg));
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l2__tmp = (Struct_MizConjFrm)l1__tmp;
			final Struct_Vector l6_conjuncts = l2__tmp.f_conjuncts;
			final Struct_Vector l7_conjuncts = (l2__tmp).f_conjuncts;
			final int l8___ = (l2__tmp).f_pattNr;
			final Reference<Struct> l9_res = ((Reference<Struct>)(new Reference(Module_correl.f_mizNewVerum())));
			final Struct_MizPre l12_pre = apre;
			final Func1<Object,Struct> l11_$10 = (Func1<Object, Struct>)(Struct ag) -> {
				l9_res.value = Module_correl.f_mizNewConj(l9_res.value, Module_prechecker.f_mizDistributeQuantifiers(l12_pre, ag));
				return null;
			};
			Module_vector.f_iterVector(l7_conjuncts, ((Func1<Object,Object>)(Func1)l11_$10));
			return l9_res.value;
		}
		case 69/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l2__tmp = (Struct_MizUnivFrm)l1__tmp;
			final Struct_MizTyp l13_quantified = l2__tmp.f_quantified;
			final Struct l14_scope = l2__tmp.f_scope;
			final int l15___ = (l2__tmp).f_sort;
			final int l16___ = (l2__tmp).f_varId;
			final Struct_MizTyp l17_quantified = (l2__tmp).f_quantified;
			final Struct l18_scope = (l2__tmp).f_scope;
			final int l19___ = (l2__tmp).f_pattNr;
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
			final Struct l20_l = Module_prechecker.f_mizDistributeQuantifiers(apre, l18_scope);
			Struct l21_res;
			Struct l22__tmp = l20_l;
			switch (l22__tmp.getTypeId()) {
			case 26/*MizConjFrm*/: {
				final Struct_MizConjFrm l23__tmp = (Struct_MizConjFrm)l22__tmp;
				final Struct_Vector l24_lc = l23__tmp.f_conjuncts;
				final Struct_Vector l25_lc = (l23__tmp).f_conjuncts;
				final int l26___ = (l23__tmp).f_pattNr;
				final int l29_$28 = (Module_vector.f_sizeVector(l25_lc)-1);
				final Func1<Object,Integer> l30_$27 = (Func1<Object, Integer>)(Integer ai) -> {
					final Struct l31_g = ((Struct)Module_vector.f_getVectorUnsafe(l25_lc, ((int)ai)));
					final Reference<Boolean> l32_nonFict = ((Reference<Boolean>)(new Reference(false)));
					final Func2<Struct,Struct_MizWithin, Struct> l34_$33 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al35_0, Struct atrm) -> {
						Struct l36__tmp = atrm;
						switch (l36__tmp.getTypeId()) {
						case 70/*MizVarTrm*/: {
							final Struct_MizVarTrm l37__tmp = (Struct_MizVarTrm)l36__tmp;
							final int l38_s2 = l37__tmp.f_sort;
							final int l39_v2 = l37__tmp.f_varNr;
							final int l40_s2 = (l37__tmp).f_sort;
							final int l41_v2 = (l37__tmp).f_varNr;
							final int l42___ = (l37__tmp).f_pattNr;
							final int l43___ = (l37__tmp).f_trmInfo;
							if (((l40_s2==Module_lexicon.g_ikTrmBound)&&(l41_v2==(l0_env).f_boundVarNbr))) {
								l32_nonFict.value = ((Boolean)true);
							} else {
							}
							return l37__tmp;
						}
						default: {
							return atrm;
						}
						}
					};
					final Struct_MizWithin l35_wc = Module_correl.f_mizMakeWithin(l34_$33);
					Module_correl.f_mizWithinFrm(l35_wc, l31_g);
					if (((boolean)l32_nonFict.value)) {
						return Module_vector.f_setVectorUnsafe(l25_lc, ((int)ai), Module_correl.f_mizNewUniv(Wrappers.w_mizCopyExpTyp.invoke(l17_quantified), l31_g));
					} else {
						final Func2<Struct,Struct_MizWithin, Struct> l37_$36 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al38_0, Struct atrm) -> {
							Struct l39__tmp = atrm;
							switch (l39__tmp.getTypeId()) {
							case 70/*MizVarTrm*/: {
								final Struct_MizVarTrm l40__tmp = (Struct_MizVarTrm)l39__tmp;
								final int l41_s2 = l40__tmp.f_sort;
								final int l42_v2 = l40__tmp.f_varNr;
								final int l43_s2 = (l40__tmp).f_sort;
								final int l44_v2 = (l40__tmp).f_varNr;
								final int l45___ = (l40__tmp).f_pattNr;
								final int l46___ = (l40__tmp).f_trmInfo;
								if (((l43_s2==Module_lexicon.g_ikTrmBound)&&(l44_v2>(l0_env).f_boundVarNbr))) {
									final Struct_MizVarTrm l47_vt = Module_prechecker.f_mizAsVarTrmB(l40__tmp);
									((Field_varNr)l47_vt).set_varNr((l44_v2-1));
								} else {
								}
								return l40__tmp;
							}
							default: {
								return atrm;
							}
							}
						};
						final Struct_MizWithin l38_wr = Module_correl.f_mizMakeWithin(l37_$36);
						return Module_correl.f_mizWithinFrm(l38_wr, l31_g);
					}
				};
				Module_runtime.f_fori(0, l29_$28, l30_$27);
				l21_res=l23__tmp;
				break;
			}
			default: {
				l21_res=Module_correl.f_mizNewUniv(Wrappers.w_mizCopyExpTyp.invoke(l17_quantified), l20_l);
				break;
			}
			}
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-1));
			return l21_res;
		}
		case 48/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l2__tmp = (Struct_MizLocPredFrm)l1__tmp;
			final Struct l31_expn = l2__tmp.f_predExp;
			final int l32___ = (l2__tmp).f_predNr;
			final Struct l33___ = (l2__tmp).f_args;
			final Struct l34_expn = (l2__tmp).f_predExp;
			final int l35___ = (l2__tmp).f_pattNr;
			if ((Module_correl.f_mizFrmSort(l34_expn)!=Module_lexicon.g_ikError)) {
				{
					final Struct l36___tmp = l34_expn;
					af = l36___tmp;
					continue TAIL_CALL;
				}
			} else {
				return Module_correl_inst.f_mizCopyExpFrm(l2__tmp);
			}
		}
		default: {
			return Module_correl_inst.f_mizCopyExpFrm(af);
		}
		}
	}
	}
	public static final Struct f_mizExpandAtomicAsFalse(Struct_MizPre apre, Struct af) {
		final Struct_MizCorrelEnv l0_env = (apre).f_env;
		Struct l1__tmp = af;
		switch (l1__tmp.getTypeId()) {
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l2__tmp = (Struct_MizNegFrm)l1__tmp;
			final Struct l3_arg = l2__tmp.f_arg;
			final Struct l4_arg = (l2__tmp).f_arg;
			final int l5___ = (l2__tmp).f_pattNr;
			return Module_correl.f_mizNewNeg(Module_prechecker.f_mizExpandAtomicAsTrue(apre, l4_arg));
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l2__tmp = (Struct_MizConjFrm)l1__tmp;
			final Struct_Vector l6_conjuncts = l2__tmp.f_conjuncts;
			final Struct_Vector l7_conjuncts = (l2__tmp).f_conjuncts;
			final int l8___ = (l2__tmp).f_pattNr;
			final Reference<Struct> l9_res = ((Reference<Struct>)(new Reference(Module_correl.f_mizNewVerum())));
			final Struct_MizPre l12_pre = apre;
			final Func1<Object,Struct> l11_$10 = (Func1<Object, Struct>)(Struct ag) -> {
				l9_res.value = Module_correl.f_mizNewConj(l9_res.value, Module_prechecker.f_mizExpandAtomicAsFalse(l12_pre, ag));
				return null;
			};
			Module_vector.f_iterVector(l7_conjuncts, ((Func1<Object,Object>)(Func1)l11_$10));
			return l9_res.value;
		}
		case 55/*MizPredFrm*/: {
			final Struct_MizPredFrm l2__tmp = (Struct_MizPredFrm)l1__tmp;
			final int l13_srt = l2__tmp.f_sort;
			final int l14_srt = (l2__tmp).f_sort;
			final int l15___ = (l2__tmp).f_predNr;
			final Struct l16___ = (l2__tmp).f_args;
			final int l17___ = (l2__tmp).f_pattNr;
			if (((l14_srt==Module_lexicon.g_ikFrmPred)||(l14_srt==Module_lexicon.g_ikFrmAttr))) {
				final Reference<Struct> l18_res = ((Reference<Struct>)(new Reference(Module_correl_inst.f_mizCopyExpFrm(l2__tmp))));
				final Object[] l19_exps = (apre).f_atomicExpansions.invoke(l2__tmp);
				final Func1<Object,Struct> l21_$20 = (Func1<Object, Struct>)(Struct ae) -> {
					l18_res.value = Module_correl_inst.f_mizNewDisj(l18_res.value, ae);
					return null;
				};
				Native.iter(l19_exps, ((Func1<Object,Object>)(Func1)l21_$20));
				return l18_res.value;
			} else {
				return Module_correl_inst.f_mizCopyExpFrm(l2__tmp);
			}
		}
		case 69/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l2__tmp = (Struct_MizUnivFrm)l1__tmp;
			final Struct_MizTyp l22_quantified = l2__tmp.f_quantified;
			final Struct l23_scope = l2__tmp.f_scope;
			final int l24___ = (l2__tmp).f_sort;
			final int l25___ = (l2__tmp).f_varId;
			final Struct_MizTyp l26_quantified = (l2__tmp).f_quantified;
			final Struct l27_scope = (l2__tmp).f_scope;
			final int l28___ = (l2__tmp).f_pattNr;
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
			Module_correl.f_mizSetBoundVar(l0_env, (l0_env).f_boundVarNbr, l26_quantified);
			final Struct l29_res = Module_correl.f_mizNewUniv(Wrappers.w_mizCopyExpTyp.invoke(l26_quantified), Module_prechecker.f_mizExpandAtomicAsFalse(apre, l27_scope));
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-1));
			return l29_res;
		}
		default: {
			return Module_correl_inst.f_mizCopyExpFrm(af);
		}
		}
	}
	public static final Struct f_mizExpandAtomicAsTrue(Struct_MizPre apre, Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l2_arg = l1__tmp.f_arg;
			final Struct l3_arg = (l1__tmp).f_arg;
			final int l4___ = (l1__tmp).f_pattNr;
			return Module_correl.f_mizNewNeg(Module_prechecker.f_mizExpandAtomicAsFalse(apre, l3_arg));
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l1__tmp = (Struct_MizConjFrm)l0__tmp;
			final Struct_Vector l5_conjuncts = l1__tmp.f_conjuncts;
			final Struct_Vector l6_conjuncts = (l1__tmp).f_conjuncts;
			final int l7___ = (l1__tmp).f_pattNr;
			final Reference<Struct> l8_res = ((Reference<Struct>)(new Reference(Module_correl.f_mizNewVerum())));
			final Struct_MizPre l11_pre = apre;
			final Func1<Object,Struct> l10_$9 = (Func1<Object, Struct>)(Struct ag) -> {
				l8_res.value = Module_correl.f_mizNewConj(l8_res.value, Module_prechecker.f_mizExpandAtomicAsTrue(l11_pre, ag));
				return null;
			};
			Module_vector.f_iterVector(l6_conjuncts, ((Func1<Object,Object>)(Func1)l10_$9));
			return l8_res.value;
		}
		case 55/*MizPredFrm*/: {
			final Struct_MizPredFrm l1__tmp = (Struct_MizPredFrm)l0__tmp;
			final int l12_srt = l1__tmp.f_sort;
			final int l13_srt = (l1__tmp).f_sort;
			final int l14___ = (l1__tmp).f_predNr;
			final Struct l15___ = (l1__tmp).f_args;
			final int l16___ = (l1__tmp).f_pattNr;
			if (((l13_srt==Module_lexicon.g_ikFrmPred)||(l13_srt==Module_lexicon.g_ikFrmAttr))) {
				final Reference<Struct> l17_res = ((Reference<Struct>)(new Reference(Module_correl_inst.f_mizCopyExpFrm(l1__tmp))));
				final Object[] l18_exps = (apre).f_atomicExpansions.invoke(l1__tmp);
				final Func1<Object,Struct> l20_$19 = (Func1<Object, Struct>)(Struct ae) -> {
					l17_res.value = Module_correl.f_mizNewConj(l17_res.value, ae);
					return null;
				};
				Native.iter(l18_exps, ((Func1<Object,Object>)(Func1)l20_$19));
				return l17_res.value;
			} else {
				return Module_correl_inst.f_mizCopyExpFrm(l1__tmp);
			}
		}
		default: {
			return Module_correl_inst.f_mizCopyExpFrm(af);
		}
		}
	}
	public static final Struct_MizPre f_mizMakePre(Struct_MizCorrelEnv aenv) {
		final Struct_Vector l5_$4 = Module_vector.f_makeVector(16);
		final Func1<Struct,Struct> l6_$0 = (Func1<Struct, Struct>)(Struct at) -> {
			return at;
		};
		final Func2<Object,Struct_MizPre, Struct_MizTyp> l7_$1 = (Func2<Object, Struct_MizPre, Struct_MizTyp>)(Struct_MizPre ap, Struct_MizTyp atyp) -> {
			return Module_prechecker.f_mizNewConst(ap, atyp);
		};
		final Func1<Object[],Struct> l8_$2 = (Func1<Object[], Struct>)(Struct al9_0) -> {
			return SingletonStructs.arr_empty;
		};
		final Func1<Object,Struct> l9_$3 = (Func1<Object, Struct>)(Struct al10_0) -> {
			return null;
		};
		return (new Struct_MizPre(aenv, l5_$4, 0, false, false, false, l6_$0, l7_$1, l8_$2, l9_$3));
	}
	public static final int f_mizNewBasic(Struct_MizPre apre, Struct af) {
		final int l0_i = Module_prechecker.f_mizBasicFrmNr(apre, af);
		if ((l0_i>=0)) {
			return l0_i;
		} else {
			Module_vector.f_pushVector((apre).f_basic, af);
			return (Module_vector.f_sizeVector((apre).f_basic)-1);
		}
	}
	public static final Object f_mizNewConst(Struct_MizPre apre, Struct_MizTyp atyp) {
		final Struct_MizCorrelEnv l0_env = (apre).f_env;
		if (((l0_env).f_fixedVarNbr>=Module_limits.g_MaxVarNbr)) {
			((Field_constOvfl)apre).set_constOvfl(true);
			return null;
		} else {
			final Struct_MizTyp l1_lTyp = Wrappers.w_mizCopyTyp.invoke(atyp);
			final Struct_MizPre l4_pre = apre;
			final Func2<Struct,Struct_MizWithin, Struct> l3_$2 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al4_0, Struct atrm) -> {
				return Module_prechecker.f_mizSetConstInTrm(l4_pre, atrm);
			};
			final Struct_MizWithin l5_w = Module_correl.f_mizMakeWithin(l3_$2);
			Module_correl.f_mizWithinTyp(l5_w, l1_lTyp);
			((Field_fixedVarNbr)l0_env).set_fixedVarNbr(((l0_env).f_fixedVarNbr+1));
			final Func0<Boolean> l8_$6 = (Func0<Boolean>)() -> {
				return (Module_vector.f_sizeVector((l0_env).f_fixedVar)<=(l0_env).f_fixedVarNbr);
			};
			final Func0<Object> l9_$7 = (Func0<Object>)() -> {
				return Module_vector.f_pushVector((l0_env).f_fixedVar, (new Struct_MizFixedVar(0, Module_correl.f_mizNewIncorTyp(), false, ((Struct)SingletonStructs.str_None), 0)));
			};
			Module_loop.f_while(l8_$6, l9_$7);
			final Struct_MizFixedVar l10_fv = ((Struct_MizFixedVar)Module_vector.f_getVectorUnsafe((l0_env).f_fixedVar, (l0_env).f_fixedVarNbr));
			((Field_typ)l10_fv).set_typ(l1_lTyp);
			((Field_def)l10_fv).set_def(SingletonStructs.str_None);
			return null;
		}
	}
	public static final Struct_MizPreInst f_mizNormalizeAsFalse(Struct_MizPre apre, Struct af) {
		final int l0_srt = Module_correl.f_mizFrmSort(af);
		if ((l0_srt==Module_lexicon.g_ikFrmNeg)) {
			final Struct l1_nf = Module_prechecker.f_mizAsNegArg(af);
			return Module_prechecker.f_mizNormalizeAsTrue(apre, l1_nf);
		} else {
			if ((l0_srt==Module_lexicon.g_ikFrmConj)) {
				final Struct_MizPreInst l2_res = Module_prechecker.f_mizPreInstBottom();
				final Struct_Vector l3_conj = Module_prechecker.f_mizAsConjuncts(af);
				final Struct_MizPre l6_pre = apre;
				final Func1<Object,Struct> l5_$4 = (Func1<Object, Struct>)(Struct ag) -> {
					final Struct_MizPreInst l6_l = Module_prechecker.f_mizNormalizeAsFalse(l6_pre, ag);
					return Module_prechecker.f_mizPreInstUnionWith(l6_pre, l2_res, l6_l);
				};
				Module_vector.f_iterVector(l3_conj, ((Func1<Object,Object>)(Func1)l5_$4));
				return l2_res;
			} else {
				if ((l0_srt==Module_lexicon.g_ikFrmVerum)) {
					return Module_prechecker.f_mizPreInstBottom();
				} else {
					final Struct l7_lsnt = Module_prechecker.f_mizApplySetVar(apre, Wrappers.w_mizCopyFrm.invoke(af));
					final Struct_MizNatFunc l8_atom = Module_mobjects.f_mizInitNatFunc(2);
					Module_mobjects.f_mizNatAssign(l8_atom, Module_prechecker.f_mizNewBasic(apre, l7_lsnt), 0);
					return Module_prechecker.f_mizPreInstSingle(l8_atom);
				}
			}
		}
	}
	public static final Struct_MizPreInst f_mizNormalizeAsTrue(Struct_MizPre apre, Struct af) {
		final int l0_srt = Module_correl.f_mizFrmSort(af);
		if ((l0_srt==Module_lexicon.g_ikFrmNeg)) {
			final Struct l1_nf = Module_prechecker.f_mizAsNegArg(af);
			return Module_prechecker.f_mizNormalizeAsFalse(apre, l1_nf);
		} else {
			if ((l0_srt==Module_lexicon.g_ikFrmConj)) {
				final Struct_MizPreInst l2_res = Module_prechecker.f_mizPreInstTop();
				final Struct_Vector l3_conj = Module_prechecker.f_mizAsConjuncts(af);
				final Struct_MizPre l6_pre = apre;
				final Func1<Object,Struct> l5_$4 = (Func1<Object, Struct>)(Struct ag) -> {
					final Struct_MizPreInst l6_l = Module_prechecker.f_mizNormalizeAsTrue(l6_pre, ag);
					return Module_prechecker.f_mizPreInstJoinWith(l6_pre, l2_res, l6_l);
				};
				Module_vector.f_iterVector(l3_conj, ((Func1<Object,Object>)(Func1)l5_$4));
				return l2_res;
			} else {
				if ((l0_srt==Module_lexicon.g_ikFrmVerum)) {
					return Module_prechecker.f_mizPreInstTop();
				} else {
					final Struct l7_lsnt = Module_prechecker.f_mizApplySetVar(apre, Wrappers.w_mizCopyFrm.invoke(af));
					final Struct_MizNatFunc l8_atom = Module_mobjects.f_mizInitNatFunc(2);
					Module_mobjects.f_mizNatAssign(l8_atom, Module_prechecker.f_mizNewBasic(apre, l7_lsnt), 1);
					return Module_prechecker.f_mizPreInstSingle(l8_atom);
				}
			}
		}
	}
	public static final Struct_MizPreInst f_mizPreCheck(Struct_MizPre apre, Struct_Vector ainference) {
		final Struct_MizCorrelEnv l0_env = (apre).f_env;
		((Field_constBase)apre).set_constBase((l0_env).f_fixedVarNbr);
		((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
		final Struct_MizPre l3_pre = apre;
		final Func1<Struct,Struct> l2_$1 = (Func1<Struct, Struct>)(Struct ag) -> {
			return Module_prechecker.f_mizExpandAtomicAsTrue(l3_pre, ag);
		};
		final Struct_Vector l4_lInference = Module_vector.f_mapVector(ainference, ((Func1<Object,Object>)(Func1)l2_$1));
		final Reference<Struct> l5_lChkFrm = ((Reference<Struct>)(new Reference(Module_correl.f_mizNewVerum())));
		final Struct_MizPre l8_pre = apre;
		final Func1<Object,Struct> l7_$6 = (Func1<Object, Struct>)(Struct ag) -> {
			l5_lChkFrm.value = Module_correl.f_mizNewConj(l5_lChkFrm.value, Module_prechecker.f_mizDistributeQuantifiers(l8_pre, ag));
			return null;
		};
		Module_vector.f_iterVector(l4_lInference, ((Func1<Object,Object>)(Func1)l7_$6));
		final Struct_MizPre l11_pre = apre;
		final Func1<Struct,Struct> l10_$9 = (Func1<Struct, Struct>)(Struct at) -> {
			return Module_prechecker.f_mizSetConstInTrm(l11_pre, at);
		};
		((Field_setVarInTrm)apre).set_setVarInTrm(l10_$9);
		final Func2<Object,Struct_MizPre, Struct_MizTyp> l13_$12 = (Func2<Object, Struct_MizPre, Struct_MizTyp>)(Struct_MizPre ap, Struct_MizTyp atyp) -> {
			return Module_prechecker.f_mizNewConst(ap, atyp);
		};
		((Field_newVariable)apre).set_newVariable(l13_$12);
		final Struct l14_body = Module_prechecker.f_mizRemoveIntQuantifier(apre, l5_lChkFrm.value);
		((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
		(apre).f_collectConst.invoke(l14_body);
		((Field_constBase)apre).set_constBase((l0_env).f_fixedVarNbr);
		Module_vector.f_clearVector((apre).f_basic);
		((Field_trivialError)apre).set_trivialError(false);
		final Struct_MizPreInst l15_normalForm = Module_prechecker.f_mizNormalizeAsTrue(apre, l14_body);
		if ((l15_normalForm).f_top) {
			((Field_trivialError)apre).set_trivialError(true);
			return l15_normalForm;
		} else {
			Module_prechecker.f_mizCreateInference(apre, l15_normalForm);
			return l15_normalForm;
		}
	}
	public static final Struct_MizPreInst f_mizPreInstBottom() {
		return (new Struct_MizPreInst(Module_vector.f_makeVector(4), false, false));
	}
	public static final int f_mizPreInstCount(Struct_MizPreInst ap) {
		if ((ap).f_top) {
			return 1;
		} else {
			return Module_vector.f_sizeVector((ap).f_evals);
		}
	}
	public static final Object f_mizPreInstInsertAndAbsorb(Struct_MizPreInst ap, Struct_MizNatFunc aelem) {
		final Reference<Integer> l0_i = ((Reference<Integer>)(new Reference(0)));
		final Reference<Boolean> l1_redundant = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Boolean> l2_replaced = ((Reference<Boolean>)(new Reference(false)));
		final Struct_MizPreInst l6_p = ap;
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return ((Boolean)((!((boolean)l1_redundant.value)&&!((boolean)l2_replaced.value))&&(((int)l0_i.value)<Module_vector.f_sizeVector((l6_p).f_evals))));
		};
		final Struct_MizPreInst l8_p = ap;
		final Struct_MizNatFunc l9_elem = aelem;
		final Func0<Object> l7_$4 = (Func0<Object>)() -> {
			final Struct_MizNatFunc l8_lAtom = ((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe((l8_p).f_evals, ((int)l0_i.value)));
			final int l9_c = Module_mobjects.f_mizNatCompareWith(l8_lAtom, l9_elem);
			if ((l9_c==0)) {
				l0_i.value = ((Integer)(((int)l0_i.value)+1));
				return null;
			} else {
				if ((l9_c==(-1))) {
					l1_redundant.value = ((Boolean)true);
					return null;
				} else {
					l2_replaced.value = ((Boolean)true);
					return null;
				}
			}
		};
		Module_loop.f_while(l5_$3, l7_$4);
		if (((boolean)l1_redundant.value)) {
			return null;
		} else {
			if (((boolean)l2_replaced.value)) {
				Module_vector.f_setVectorUnsafe((ap).f_evals, ((int)l0_i.value), aelem);
				l0_i.value = ((Integer)(((int)l0_i.value)+1));
				final Struct_MizPreInst l13_p = ap;
				final Func0<Boolean> l12_$10 = (Func0<Boolean>)() -> {
					return (((int)l0_i.value)<Module_vector.f_sizeVector((l13_p).f_evals));
				};
				final Struct_MizPreInst l15_p = ap;
				final Struct_MizNatFunc l16_elem = aelem;
				final Func0<Object> l14_$11 = (Func0<Object>)() -> {
					final Struct_MizNatFunc l15_lAtom = ((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe((l15_p).f_evals, ((int)l0_i.value)));
					if (Module_mobjects.f_mizNatWeakerThan(l16_elem, l15_lAtom)) {
						return Module_vector.f_removeVector((l15_p).f_evals, ((int)l0_i.value));
					} else {
						l0_i.value = ((Integer)(((int)l0_i.value)+1));
						return null;
					}
				};
				return Module_loop.f_while(l12_$10, l14_$11);
			} else {
				return Module_vector.f_pushVector((ap).f_evals, aelem);
			}
		}
	}
	public static final Struct_MizPreInst f_mizPreInstJoinList(Struct_MizPre apre, Struct_Vector acolls) {
		if ((Module_vector.f_sizeVector(acolls)==0)) {
			return Module_prechecker.f_mizPreInstTop();
		} else {
			final Struct_Vector l0_work = Module_vector.f_copyVector(acolls);
			final int l1_n = Module_vector.f_sizeVector(l0_work);
			final int l4_$3 = (l1_n-1);
			final Func1<Object,Integer> l5_$2 = (Func1<Object, Integer>)(Integer ai) -> {
				final Reference<Integer> l6_j = ((Reference<Integer>)(new Reference(ai)));
				final Func0<Boolean> l9_$7 = (Func0<Boolean>)() -> {
					return ((Boolean)((((int)l6_j.value)>0)&&(Module_prechecker.f_mizPreInstCount(((Struct_MizPreInst)Module_vector.f_getVectorUnsafe(l0_work, (((int)l6_j.value)-1))))<Module_prechecker.f_mizPreInstCount(((Struct_MizPreInst)Module_vector.f_getVectorUnsafe(l0_work, ((int)l6_j.value)))))));
				};
				final Func0<Object> l10_$8 = (Func0<Object>)() -> {
					final Struct_MizPreInst l11_tmp = ((Struct_MizPreInst)Module_vector.f_getVectorUnsafe(l0_work, ((int)l6_j.value)));
					Module_vector.f_setVectorUnsafe(l0_work, ((int)l6_j.value), Module_vector.f_getVectorUnsafe(l0_work, (((int)l6_j.value)-1)));
					Module_vector.f_setVectorUnsafe(l0_work, (((int)l6_j.value)-1), l11_tmp);
					l6_j.value = ((Integer)(((int)l6_j.value)-1));
					return null;
				};
				return Module_loop.f_while(l9_$7, l10_$8);
			};
			Module_runtime.f_fori(1, l4_$3, l5_$2);
			final Reference<Struct> l6_res = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
			final Func0<Boolean> l9_$7 = (Func0<Boolean>)() -> {
				return ((Boolean)((Module_vector.f_sizeVector(l0_work)>1)&&Module_maybe.f_isNone(l6_res.value)));
			};
			final Struct_MizPre l11_pre = apre;
			final Func0<Object> l10_$8 = (Func0<Object>)() -> {
				final Struct_MizPreInst l11_lInsts = ((Struct_MizPreInst)Module_vector.f_getVectorUnsafe(l0_work, (Module_vector.f_sizeVector(l0_work)-1)));
				Module_vector.f_removeVector(l0_work, (Module_vector.f_sizeVector(l0_work)-1));
				if (((Module_prechecker.f_mizPreInstCount(l11_lInsts)==1)&&!(l11_lInsts).f_top)) {
					final Struct_MizNatFunc l12_lObj = ((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe((l11_lInsts).f_evals, 0));
					final int l15_$14 = (Module_vector.f_sizeVector(l0_work)-1);
					final Func1<Object,Integer> l16_$13 = (Func1<Object, Integer>)(Integer az) -> {
						return Module_vector.f_setVectorUnsafe(l0_work, ((int)az), Module_prechecker.f_mizPreInstJoinWithAtom(l12_lObj, ((Struct_MizPreInst)Module_vector.f_getVectorUnsafe(l0_work, ((int)az)))));
					};
					return Module_runtime.f_fori(0, l15_$14, l16_$13);
				} else {
					final Struct_MizPreInst l17_lInsts1 = ((Struct_MizPreInst)Module_vector.f_getVectorUnsafe(l0_work, (Module_vector.f_sizeVector(l0_work)-1)));
					Module_vector.f_removeVector(l0_work, (Module_vector.f_sizeVector(l0_work)-1));
					Module_prechecker.f_mizPreInstJoinWith(l11_pre, l17_lInsts1, l11_lInsts);
					if ((l17_lInsts1).f_overflow) {
						final Struct_MizPreInst l18_bot = Module_prechecker.f_mizPreInstBottom();
						((Field_overflow)l18_bot).set_overflow(true);
						((Reference<Struct_Some>)(Reference)l6_res).value = (new Struct_Some(l18_bot));
						return null;
					} else {
						final Reference<Integer> l19_pos = ((Reference<Integer>)(new Reference(Module_vector.f_sizeVector(l0_work))));
						final Func0<Boolean> l22_$20 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l19_pos.value)>0)&&(Module_prechecker.f_mizPreInstCount(((Struct_MizPreInst)Module_vector.f_getVectorUnsafe(l0_work, (((int)l19_pos.value)-1))))<Module_prechecker.f_mizPreInstCount(l17_lInsts1))));
						};
						final Func0<Object> l23_$21 = (Func0<Object>)() -> {
							l19_pos.value = ((Integer)(((int)l19_pos.value)-1));
							return null;
						};
						Module_loop.f_while(l22_$20, l23_$21);
						final Struct_Vector l24_v2 = Module_vector.f_makeVector((Module_vector.f_sizeVector(l0_work)+1));
						final int l27_$26 = (((int)l19_pos.value)-1);
						final Func1<Object,Integer> l28_$25 = (Func1<Object, Integer>)(Integer ak) -> {
							return Module_vector.f_pushVector(l24_v2, Module_vector.f_getVectorUnsafe(l0_work, ((int)ak)));
						};
						Module_runtime.f_fori(0, l27_$26, l28_$25);
						Module_vector.f_pushVector(l24_v2, l17_lInsts1);
						final int l32_$30 = ((int)l19_pos.value);
						final int l33_$31 = (Module_vector.f_sizeVector(l0_work)-1);
						final Func1<Object,Integer> l34_$29 = (Func1<Object, Integer>)(Integer ak) -> {
							return Module_vector.f_pushVector(l24_v2, Module_vector.f_getVectorUnsafe(l0_work, ((int)ak)));
						};
						Module_runtime.f_fori(l32_$30, l33_$31, l34_$29);
						Module_vector.f_clearVector(l0_work);
						final Func1<Object,Struct_MizPreInst> l36_$35 = (Func1<Object, Struct_MizPreInst>)(Struct_MizPreInst ac) -> {
							return Module_vector.f_pushVector(l0_work, ac);
						};
						return Module_vector.f_iterVector(l24_v2, ((Func1<Object,Object>)(Func1)l36_$35));
					}
				}
			};
			Module_loop.f_while(l9_$7, l10_$8);
			final Struct l12_gsymswitch2 = l6_res.value;
			Struct l13__tmp = l12_gsymswitch2;
			switch (l13__tmp.getTypeId()) {
			case 83/*Some*/: {
				final Struct_Some l14__tmp = (Struct_Some)l13__tmp;
				final Object l15_r = l14__tmp.f_value;
				final Struct_MizPreInst l16_r = ((Struct_MizPreInst)(l14__tmp).f_value);
				return l16_r;
			}
			case 75/*None*/: {
				return ((Struct_MizPreInst)Module_vector.f_getVectorUnsafe(l0_work, 0));
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l13__tmp.getTypeName());
			}
		}
	}
	public static final Object f_mizPreInstJoinWith(Struct_MizPre apre, Struct_MizPreInst ap, Struct_MizPreInst aq) {
		if ((ap).f_top) {
			((Field_top)ap).set_top((aq).f_top);
			Module_vector.f_clearVector((ap).f_evals);
			final Struct_Vector l2_$1 = (aq).f_evals;
			final Struct_MizPreInst l4_p = ap;
			final Func1<Object,Struct_MizNatFunc> l3_$0 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
				return Module_vector.f_pushVector((l4_p).f_evals, ae);
			};
			Module_vector.f_iterVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
			((Field_overflow)ap).set_overflow((aq).f_overflow);
			return null;
		} else {
			if ((aq).f_top) {
				return null;
			} else {
				if ((Module_vector.f_sizeVector((aq).f_evals)==0)) {
					Module_vector.f_clearVector((ap).f_evals);
					((Field_top)ap).set_top(false);
					return null;
				} else {
					if ((Module_vector.f_sizeVector((ap).f_evals)==0)) {
						return null;
					} else {
						final int l5_newSize0 = (Module_vector.f_sizeVector((ap).f_evals)*Module_vector.f_sizeVector((aq).f_evals));
						final int l6_newSize = ((int)Module_runtime.f_min(l5_newSize0, Module_limits.g_MaxInstNbr));
						final Struct_Vector l7_old = Module_vector.f_copyVector((ap).f_evals);
						Module_vector.f_clearVector((ap).f_evals);
						final Struct_Vector l10_$9 = (aq).f_evals;
						final Struct_MizPreInst l12_p = ap;
						final Struct_MizPre l13_pre = apre;
						final Func1<Object,Struct_MizNatFunc> l11_$8 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
							final Func1<Object,Struct_MizNatFunc> l13_$12 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc aa) -> {
								if (!(l12_p).f_overflow) {
									final Struct l14_gsymswitch0 = Module_mobjects.f_mizNatJoinAtom(aa, ae);
									Struct l15__tmp = l14_gsymswitch0;
									switch (l15__tmp.getTypeId()) {
									case 83/*Some*/: {
										final Struct_Some l16__tmp = (Struct_Some)l15__tmp;
										final Object l17_j = l16__tmp.f_value;
										final Struct_MizNatFunc l18_j = ((Struct_MizNatFunc)(l16__tmp).f_value);
										if ((Module_vector.f_sizeVector((l12_p).f_evals)>=l6_newSize)) {
											Module_vector.f_clearVector((l12_p).f_evals);
											((Field_overflow)l12_p).set_overflow(true);
											((Field_latOvfl)l13_pre).set_latOvfl(true);
											return null;
										} else {
											return Module_prechecker.f_mizPreInstInsertAndAbsorb(l12_p, l18_j);
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
							return Module_vector.f_iterVector(l7_old, ((Func1<Object,Object>)(Func1)l13_$12));
						};
						return Module_vector.f_iterVector(l10_$9, ((Func1<Object,Object>)(Func1)l11_$8));
					}
				}
			}
		}
	}
	public static final Struct_MizPreInst f_mizPreInstJoinWithAtom(Struct_MizNatFunc aelem, Struct_MizPreInst acoll) {
		final Struct_MizPreInst l0_res = Module_prechecker.f_mizPreInstBottom();
		final Struct_Vector l3_$2 = (acoll).f_evals;
		final Struct_MizNatFunc l5_elem = aelem;
		final Func1<Object,Struct_MizNatFunc> l4_$1 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc aa) -> {
			final Struct l5_gsymswitch1 = Module_mobjects.f_mizNatJoinAtom(aa, l5_elem);
			Struct l6__tmp = l5_gsymswitch1;
			switch (l6__tmp.getTypeId()) {
			case 83/*Some*/: {
				final Struct_Some l7__tmp = (Struct_Some)l6__tmp;
				final Object l8_j = l7__tmp.f_value;
				final Struct_MizNatFunc l9_j = ((Struct_MizNatFunc)(l7__tmp).f_value);
				return Module_vector.f_pushVector((l0_res).f_evals, l9_j);
			}
			case 75/*None*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l6__tmp.getTypeName());
			}
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		return l0_res;
	}
	public static final Struct_MizPreInst f_mizPreInstSingle(Struct_MizNatFunc aatom) {
		final Struct_Vector l0_v = Module_vector.f_makeVector(1);
		Module_vector.f_pushVector(l0_v, aatom);
		return (new Struct_MizPreInst(l0_v, false, false));
	}
	public static final Struct_MizPreInst f_mizPreInstTop() {
		return (new Struct_MizPreInst(Module_vector.f_makeVector(1), true, false));
	}
	public static final Object f_mizPreInstUnionWith(Struct_MizPre apre, Struct_MizPreInst ap, Struct_MizPreInst aq) {
		if ((ap).f_overflow) {
			return null;
		} else {
			if ((aq).f_overflow) {
				Module_vector.f_clearVector((ap).f_evals);
				((Field_top)ap).set_top(false);
				((Field_overflow)ap).set_overflow(true);
				return null;
			} else {
				if ((!(ap).f_top&&(Module_vector.f_sizeVector((ap).f_evals)==0))) {
					((Field_top)ap).set_top((aq).f_top);
					final Struct_Vector l2_$1 = (aq).f_evals;
					final Struct_MizPreInst l4_p = ap;
					final Func1<Object,Struct_MizNatFunc> l3_$0 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
						return Module_vector.f_pushVector((l4_p).f_evals, ae);
					};
					return Module_vector.f_iterVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
				} else {
					if ((ap).f_top) {
						return null;
					} else {
						if ((!(aq).f_top&&(Module_vector.f_sizeVector((aq).f_evals)==0))) {
							return null;
						} else {
							if ((aq).f_top) {
								Module_vector.f_clearVector((ap).f_evals);
								((Field_top)ap).set_top(true);
								return null;
							} else {
								final Struct_Vector l7_$6 = (aq).f_evals;
								final Struct_MizPreInst l9_p = ap;
								final Func1<Object,Struct_MizNatFunc> l8_$5 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
									if (!(l9_p).f_overflow) {
										return Module_prechecker.f_mizPreInstInsertAndAbsorb(l9_p, ae);
									} else {
										return null;
									}
								};
								return Module_vector.f_iterVector(l7_$6, ((Func1<Object,Object>)(Func1)l8_$5));
							}
						}
					}
				}
			}
		}
	}
	public static final Struct f_mizRemoveExtQuantifier(Struct_MizPre apre, Struct af0) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (apre).f_env;
		Struct l1__tmp = af0;
		switch (l1__tmp.getTypeId()) {
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l2__tmp = (Struct_MizNegFrm)l1__tmp;
			final Struct l3_arg = l2__tmp.f_arg;
			final Struct l4_arg = (l2__tmp).f_arg;
			final int l5___ = (l2__tmp).f_pattNr;
			final Struct l6_na = Module_prechecker.f_mizRemoveIntQuantifier(apre, l4_arg);
			final Struct_MizNegFrm l7_f1 = l2__tmp;
			((Field_arg)l7_f1).set_arg(l6_na);
			Struct l8__tmp = l6_na;
			switch (l8__tmp.getTypeId()) {
			case 50/*MizNegFrm*/: {
				final Struct_MizNegFrm l9__tmp = (Struct_MizNegFrm)l8__tmp;
				final Struct l10_inner = l9__tmp.f_arg;
				final Struct l11_inner = (l9__tmp).f_arg;
				final int l12___ = (l9__tmp).f_pattNr;
				return l11_inner;
			}
			default: {
				return l2__tmp;
			}
			}
		}
		case 69/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l2__tmp = (Struct_MizUnivFrm)l1__tmp;
			final int l13___ = (l2__tmp).f_sort;
			final int l14___ = (l2__tmp).f_varId;
			final Struct_MizTyp l15___ = (l2__tmp).f_quantified;
			final Struct l16___ = (l2__tmp).f_scope;
			final int l17___ = (l2__tmp).f_pattNr;
			((Field_constBase)apre).set_constBase((l0_env).f_fixedVarNbr);
			((Field_boundVarNbr)l0_env).set_boundVarNbr(0);
			final Reference<Struct> l18_cur = ((Reference<Struct>)(new Reference(l2__tmp)));
			final Reference<Boolean> l19_cont = ((Reference<Boolean>)(new Reference(true)));
			final Func0<Boolean> l22_$20 = (Func0<Boolean>)() -> {
				return l19_cont.value;
			};
			final Struct_MizPre l24_pre = apre;
			final Func0<Object> l23_$21 = (Func0<Object>)() -> {
				final Struct l24_gsymswitch3 = l18_cur.value;
				Struct l25__tmp = l24_gsymswitch3;
				switch (l25__tmp.getTypeId()) {
				case 69/*MizUnivFrm*/: {
					final Struct_MizUnivFrm l26__tmp = (Struct_MizUnivFrm)l25__tmp;
					final Struct_MizTyp l27_q = l26__tmp.f_quantified;
					final Struct l28_s = l26__tmp.f_scope;
					final int l29___ = (l26__tmp).f_sort;
					final int l30___ = (l26__tmp).f_varId;
					final Struct_MizTyp l31_q = (l26__tmp).f_quantified;
					final Struct l32_s = (l26__tmp).f_scope;
					final int l33___ = (l26__tmp).f_pattNr;
					((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
					(l24_pre).f_newVariable.invoke(l24_pre, Wrappers.w_mizCopyTyp.invoke(l31_q));
					l18_cur.value = l32_s;
					return null;
				}
				default: {
					l19_cont.value = ((Boolean)false);
					return null;
				}
				}
			};
			Module_loop.f_while(l22_$20, l23_$21);
			final Struct l25_body = Module_prechecker.f_mizApplySetVar(apre, l18_cur.value);
			{
				final Struct l26___tmp = l25_body;
				af0 = l26___tmp;
				continue TAIL_CALL;
			}
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l2__tmp = (Struct_MizConjFrm)l1__tmp;
			final Struct_Vector l27_conjuncts = l2__tmp.f_conjuncts;
			final Struct_Vector l28_conjuncts = (l2__tmp).f_conjuncts;
			final int l29___ = (l2__tmp).f_pattNr;
			final Struct_Vector l30_out = Module_vector.f_makeVector(Module_vector.f_sizeVector(l28_conjuncts));
			final Struct_MizPre l33_pre = apre;
			final Func1<Object,Struct> l32_$31 = (Func1<Object, Struct>)(Struct ag0) -> {
				final Struct l33_g = Module_prechecker.f_mizRemoveExtQuantifier(l33_pre, ag0);
				Struct l34__tmp = l33_g;
				switch (l34__tmp.getTypeId()) {
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l35__tmp = (Struct_MizConjFrm)l34__tmp;
					final Struct_Vector l36_inner = l35__tmp.f_conjuncts;
					final Struct_Vector l37_inner = (l35__tmp).f_conjuncts;
					final int l38___ = (l35__tmp).f_pattNr;
					final Func1<Object,Struct> l40_$39 = (Func1<Object, Struct>)(Struct ah) -> {
						return Module_vector.f_pushVector(l30_out, ah);
					};
					return Module_vector.f_iterVector(l37_inner, ((Func1<Object,Object>)(Func1)l40_$39));
				}
				default: {
					return Module_vector.f_pushVector(l30_out, l33_g);
				}
				}
			};
			Module_vector.f_iterVector(l28_conjuncts, ((Func1<Object,Object>)(Func1)l32_$31));
			return (new Struct_MizConjFrm(l30_out, 0));
		}
		default: {
			return af0;
		}
		}
	}
	}
	public static final Struct f_mizRemoveIntQuantifier(Struct_MizPre apre, Struct af0) {
		Struct l0__tmp = af0;
		switch (l0__tmp.getTypeId()) {
		case 50/*MizNegFrm*/: {
			final Struct_MizNegFrm l1__tmp = (Struct_MizNegFrm)l0__tmp;
			final Struct l2_arg = l1__tmp.f_arg;
			final Struct l3_arg = (l1__tmp).f_arg;
			final int l4___ = (l1__tmp).f_pattNr;
			final Struct l5_na = Module_prechecker.f_mizRemoveExtQuantifier(apre, l3_arg);
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
			final Struct_MizPre l18_pre = apre;
			final Func1<Object,Struct> l17_$16 = (Func1<Object, Struct>)(Struct ag0) -> {
				final Struct l18_g = Module_prechecker.f_mizRemoveIntQuantifier(l18_pre, ag0);
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
	public static final Struct f_mizSetConstInTrm(Struct_MizPre apre, Struct at) {
		final Struct_MizCorrelEnv l0_env = (apre).f_env;
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
				final Struct_MizVarTrm l9_vt = Module_prechecker.f_mizAsVarTrmB(l2__tmp);
				if ((l6_varNr>(l0_env).f_boundVarNbr)) {
					((Field_varNr)l9_vt).set_varNr((l6_varNr-(l0_env).f_boundVarNbr));
				} else {
					((Field_sort)l9_vt).set_sort(Module_lexicon.g_ikTrmConstant);
					((Field_varNr)l9_vt).set_varNr((l6_varNr+(apre).f_constBase));
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
}
