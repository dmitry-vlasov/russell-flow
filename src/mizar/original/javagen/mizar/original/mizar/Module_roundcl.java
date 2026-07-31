// HASH COLLISIONS: YES
// timestamp: 1785496853000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_roundcl {
	public static final int f_mizCmpFuncTrm(Struct_MizCorrelEnv aenv, Struct aa, Struct ab) {
		final boolean l0_fa = (Module_correl.f_mizTrmSort(aa)==Module_lexicon.g_ikTrmFunctor);
		final boolean l1_fb = (Module_correl.f_mizTrmSort(ab)==Module_lexicon.g_ikTrmFunctor);
		if (!l0_fa) {
			if (!l1_fb) {
				return 0;
			} else {
				return (-1);
			}
		} else {
			if (!l1_fb) {
				return 1;
			} else {
				int l2_na;
				Struct l3__tmp = aa;
				switch (l3__tmp.getTypeId()) {
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l4__tmp = (Struct_MizFuncTrm)l3__tmp;
					final int l5_funcNr = l4__tmp.f_funcNr;
					final int l6___ = (l4__tmp).f_sort;
					final int l7_funcNr = (l4__tmp).f_funcNr;
					final Struct l8___ = (l4__tmp).f_args;
					final int l9___ = (l4__tmp).f_pattNr;
					final int l10___ = (l4__tmp).f_trmInfo;
					l2_na=l7_funcNr;
					break;
				}
				default: {
					l2_na=0;
					break;
				}
				}
				int l11_nb;
				Struct l12__tmp = ab;
				switch (l12__tmp.getTypeId()) {
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l13__tmp = (Struct_MizFuncTrm)l12__tmp;
					final int l14_funcNr = l13__tmp.f_funcNr;
					final int l15___ = (l13__tmp).f_sort;
					final int l16_funcNr = (l13__tmp).f_funcNr;
					final Struct l17___ = (l13__tmp).f_args;
					final int l18___ = (l13__tmp).f_pattNr;
					final int l19___ = (l13__tmp).f_trmInfo;
					l11_nb=l16_funcNr;
					break;
				}
				default: {
					l11_nb=0;
					break;
				}
				}
				return Module_correl.f_mizCmpInt(Module_roundcl.f_mizFuncRedefNr(aenv, l2_na), Module_roundcl.f_mizFuncRedefNr(aenv, l11_nb));
			}
		}
	}
	public static final Struct_Vector f_mizCondIndex(Struct_MizClusters acl, int aneg) {
		if ((aneg==0)) {
			return (acl).f_condIndexNeg;
		} else {
			return (acl).f_condIndexPos;
		}
	}
	public static final Struct_MizNatFunc f_mizCondIndexAt(Struct_MizClusters acl, int aneg, int aadjNr) {
		final Struct_Vector l0_idx = Module_roundcl.f_mizCondIndex(acl, aneg);
		final int l4_adjNr = aadjNr;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector(l0_idx)<=l4_adjNr);
		};
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			return Module_vector.f_pushVector(l0_idx, Module_mobjects.f_mizInitNatSet(4));
		};
		Module_loop.f_while(l3_$1, l5_$2);
		return ((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe(l0_idx, aadjNr));
	}
	public static final Object f_mizEnlargeByInst(Struct_MizCorrelEnv aenv, Struct_MizAttrColl atarget, Struct_MizAttrColl asrc) {
		Module_roundcl.f_mizInstFromSubstLocal(aenv);
		final Struct_Vector l2_$1 = (asrc).f_items;
		final Struct_MizAttrColl l4_target = atarget;
		final Struct_MizCorrelEnv l5_env = aenv;
		final Func1<Object,Struct_MizAttr> l3_$0 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			if ((l4_target).f_consistent) {
				final Struct_MizAttr l4_copied = (new Struct_MizAttr((aa).f_neg, (aa).f_attrNr, Module_correl_inst.f_mizInstCopyTrmList(l5_env, (aa).f_args), false, (aa).f_pattNr));
				return Module_correl.f_mizAttrCollInsert(l5_env, l4_target, l4_copied);
			} else {
				return null;
			}
		};
		Module_vector.f_iterVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
		((Field_instantiated)aenv).set_instantiated(false);
		return null;
	}
	public static final Struct f_mizFClusterInterval(Struct_MizCorrelEnv aenv, Struct_MizClusters acl, Struct at) {
		final int l0_n = Module_vector.f_sizeVector((acl).f_functor);
		final Reference<Integer> l1_left = ((Reference<Integer>)(new Reference((-1))));
		final int l4_$3 = (l0_n-1);
		final Struct_MizCorrelEnv l6_env = aenv;
		final Struct_MizClusters l7_cl = acl;
		final Struct l8_t = at;
		final Func1<Object,Integer> l5_$2 = (Func1<Object, Integer>)(Integer ai) -> {
			if (((((int)l1_left.value)<0)&&(Module_roundcl.f_mizCmpFuncTrm(l6_env, (((Struct_MizFCluster)Module_vector.f_getVectorUnsafe((l7_cl).f_functor, ((int)ai)))).f_clusterTerm, l8_t)==0))) {
				l1_left.value = ai;
				return null;
			} else {
				return null;
			}
		};
		Module_runtime.f_fori(0, l4_$3, l5_$2);
		if ((((int)l1_left.value)<0)) {
			return ((Struct)SingletonStructs.str_None);
		} else {
			final Reference<Integer> l9_right = ((Reference<Integer>)(new Reference(l1_left.value)));
			final Struct_MizCorrelEnv l13_env = aenv;
			final Struct_MizClusters l14_cl = acl;
			final Struct l15_t = at;
			final Func0<Boolean> l12_$10 = (Func0<Boolean>)() -> {
				return ((Boolean)(((((int)l9_right.value)+1)<l0_n)&&(Module_roundcl.f_mizCmpFuncTrm(l13_env, (((Struct_MizFCluster)Module_vector.f_getVectorUnsafe((l14_cl).f_functor, (((int)l9_right.value)+1)))).f_clusterTerm, l15_t)==0)));
			};
			final Func0<Object> l16_$11 = (Func0<Object>)() -> {
				l9_right.value = ((Integer)(((int)l9_right.value)+1));
				return null;
			};
			Module_loop.f_while(l12_$10, l16_$11);
			return (new Struct_Some((new Struct_Pair(l1_left.value, l9_right.value))));
		}
	}
	public static final boolean f_mizFClusterRoundUpWith(Struct_MizCorrelEnv aenv, Struct_MizFCluster afc, Struct at, Struct_MizTyp atyp, Struct_MizAttrColl acluster) {
		Module_correl_match.f_mizDisposeSubstTrm(aenv);
		final Struct_Vector l2_$1 = (aenv).f_substTrm;
		final Func1<Boolean,Struct> l3_$0 = (Func1<Boolean, Struct>)(Struct am) -> {
			return ((Boolean)Module_maybe.f_isSome(am));
		};
		final Struct_Vector l4_snap = Module_vector.f_mapVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
		final boolean l5_lEq0 = (Module_correl_match.f_mizEsTrm(aenv, (afc).f_clusterTerm, at)&&Module_correl_match.f_mizCheckLociTypes(aenv, (afc).f_primaries));
		boolean l6_lEq;
		if ((!l5_lEq0&&(Module_correl.f_mizTrmSort(at)==Module_lexicon.g_ikTrmFunctor))) {
			Struct_MizFuncTrm l7_ft;
			Struct l8__tmp = at;
			switch (l8__tmp.getTypeId()) {
			case 45/*MizFuncTrm*/: {
				final Struct_MizFuncTrm l9__tmp = (Struct_MizFuncTrm)l8__tmp;
				final int l10___ = (l9__tmp).f_sort;
				final int l11___ = (l9__tmp).f_funcNr;
				final Struct l12___ = (l9__tmp).f_args;
				final int l13___ = (l9__tmp).f_pattNr;
				final int l14___ = (l9__tmp).f_trmInfo;
				l7_ft=l9__tmp;
				break;
			}
			default: {
				l7_ft=(new Struct_MizFuncTrm(Module_lexicon.g_ikError, 0, ((Struct)SingletonStructs.str_MizTrmNil), 0, 0));
				break;
			}
			}
			final Struct_MizConstr l15_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coFunctor, (l7_ft).f_funcNr);
			if (Module_correl.f_mizHasProperty(l15_c, Module_correl.g_syCommutativity)) {
				final Struct l16_lTrm = Module_correl.f_mizNewFuncTrm((l7_ft).f_funcNr, Module_roundcl.f_mizSwapArguments((l7_ft).f_args, (l15_c).f_firstArg, (l15_c).f_secondArg));
				Module_roundcl.f_mizResetSubstToSnap(aenv, l4_snap);
				l6_lEq=(Module_correl_match.f_mizEsTrm(aenv, (afc).f_clusterTerm, l16_lTrm)&&Module_correl_match.f_mizCheckLociTypes(aenv, (afc).f_primaries));
			} else {
				l6_lEq=false;
			}
		} else {
			l6_lEq=l5_lEq0;
		}
		boolean l17_res;
		if (l6_lEq) {
			final Struct l18_gsymswitch2 = (afc).f_clusterType;
			boolean l19_typOk;
			Struct l20__tmp = l18_gsymswitch2;
			switch (l20__tmp.getTypeId()) {
			case 88/*Some*/: {
				final Struct_Some l21__tmp = (Struct_Some)l20__tmp;
				final Object l22_ct = l21__tmp.f_value;
				final Struct_MizTyp l23_ct = ((Struct_MizTyp)(l21__tmp).f_value);
				final Struct l24_gsymswitch3 = Module_correl_inst.f_mizWideningOf(aenv, l23_ct, Wrappers.w_mizCopyTyp.invoke(atyp));
				Struct l25__tmp = l24_gsymswitch3;
				switch (l25__tmp.getTypeId()) {
				case 88/*Some*/: {
					final Struct_Some l26__tmp = (Struct_Some)l25__tmp;
					final Object l27_lTyp = l26__tmp.f_value;
					final Struct_MizTyp l28_lTyp = ((Struct_MizTyp)(l26__tmp).f_value);
					boolean l33_$32;
					if (Module_correl_match.f_mizCompEsTyp(aenv, l23_ct, l28_lTyp, false)) {
						final Struct_MizAttrColl l37_$35 = (l23_ct).f_lower;
						final Struct_MizAttrColl l38_$36 = (l28_lTyp).f_upper;
						final Struct_MizCorrelEnv l40_env = aenv;
						final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l39_$34 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
							return ((Boolean)Module_correl_match.f_mizEsAttrRev(l40_env, ax, ay));
						};
						l33_$32=Module_correl_inst.f_mizAttrCollIsSubsetOf(aenv, l37_$35, l38_$36, l39_$34);
					} else {
						l33_$32=false;
					}
					l19_typOk=(l33_$32&&Module_correl_match.f_mizCheckLociTypes(aenv, (afc).f_primaries));
					break;
				}
				case 80/*None*/: {
					l19_typOk=false;
					break;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l25__tmp.getTypeName());
				}
				break;
			}
			case 80/*None*/: {
				l19_typOk=true;
				break;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l20__tmp.getTypeName());
			}
			if (l19_typOk) {
				Module_roundcl.f_mizEnlargeByInst(aenv, acluster, ((afc).f_consequent).f_upper);
				l17_res=true;
			} else {
				l17_res=false;
			}
		} else {
			l17_res=false;
		}
		Module_correl_match.f_mizDisposeSubstTrm(aenv);
		return l17_res;
	}
	public static final int f_mizFuncRedefNr(Struct_MizCorrelEnv aenv, int afuncNr) {
		final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coFunctor, afuncNr);
		if (((l0_c).f_whichConstrNr!=0)) {
			return (l0_c).f_whichConstrNr;
		} else {
			return afuncNr;
		}
	}
	public static final Object f_mizInsertCCluster(Struct_MizCorrelEnv aenv, Struct_MizClusters acl, Struct_MizCCluster ac) {
		Module_vector.f_pushVector((acl).f_conditional, ac);
		final int l0_nr = (Module_vector.f_sizeVector((acl).f_conditional)-1);
		final Struct_Vector l3_$2 = ((ac).f_antecedent).f_items;
		final Struct_MizClusters l5_cl = acl;
		final Struct_MizCorrelEnv l6_env = aenv;
		final Func1<Object,Struct_MizAttr> l4_$1 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			return Module_mobjects.f_mizNatInsertElem(Module_roundcl.f_mizCondIndexAt(l5_cl, (aa).f_neg, Module_correl.f_mizAdjustedAttrNr(l6_env, aa)), l0_nr);
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		Module_mobjects.f_mizSeqInsert((acl).f_initClFire, Module_vector.f_sizeVector(((ac).f_antecedent).f_items));
		return null;
	}
	public static final Object f_mizInsertFCluster(Struct_MizCorrelEnv aenv, Struct_MizClusters acl, Struct_MizFCluster af) {
		final Reference<Integer> l0_pos = ((Reference<Integer>)(new Reference(Module_vector.f_sizeVector((acl).f_functor))));
		final Struct_MizCorrelEnv l4_env = aenv;
		final Struct_MizClusters l5_cl = acl;
		final Struct_MizFCluster l6_f = af;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return ((Boolean)((((int)l0_pos.value)>0)&&(Module_roundcl.f_mizCmpFuncTrm(l4_env, (((Struct_MizFCluster)Module_vector.f_getVectorUnsafe((l5_cl).f_functor, (((int)l0_pos.value)-1)))).f_clusterTerm, (l6_f).f_clusterTerm)>0)));
		};
		final Func0<Object> l7_$2 = (Func0<Object>)() -> {
			l0_pos.value = ((Integer)(((int)l0_pos.value)-1));
			return null;
		};
		Module_loop.f_while(l3_$1, l7_$2);
		return Module_mobjects.f_mizVectorAtInsert((acl).f_functor, ((int)l0_pos.value), af);
	}
	public static final Object f_mizInsertRCluster(Struct_MizClusters acl, Struct_MizRCluster ar) {
		return Module_vector.f_pushVector((acl).f_registered, ar);
	}
	public static final Object f_mizInstFromSubstLocal(Struct_MizCorrelEnv aenv) {
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
			final Struct l7_gsymswitch0 = Module_correl_match.f_mizSubstTrmGet(l7_env, ((int)l1_k.value));
			Struct l8__tmp = l7_gsymswitch0;
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
	public static final Struct_MizClusters f_mizMakeClusters() {
		return (new Struct_MizClusters(Module_vector.f_makeVector(16), Module_vector.f_makeVector(16), Module_vector.f_makeVector(16), Module_vector.f_makeVector(64), Module_vector.f_makeVector(64), Module_mobjects.f_mizInitIntSeq(16)));
	}
	public static final Object f_mizResetSubstToSnap(Struct_MizCorrelEnv aenv, Struct_Vector asnap) {
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
	public static final Object f_mizRoundUpCluster(Struct_MizCorrelEnv aenv, Struct_MizClusters acl, Struct_MizAttrColl acluster, Struct_MizTyp atyp) {
		final int l0_nCond = Module_vector.f_sizeVector((acl).f_conditional);
		if ((l0_nCond>0)) {
			final Struct_Vector l1_clFire = Module_vector.f_makeVector(l0_nCond);
			final int l4_$3 = (l0_nCond-1);
			final Struct_MizClusters l6_cl = acl;
			final Func1<Object,Integer> l5_$2 = (Func1<Object, Integer>)(Integer aj) -> {
				return Module_vector.f_pushVector(l1_clFire, Module_mobjects.f_mizSeqValue((l6_cl).f_initClFire, ((int)aj)));
			};
			Module_runtime.f_fori(0, l4_$3, l5_$2);
			final Struct_MizNatFunc l7_jobs = Module_mobjects.f_mizInitNatSet(32);
			final Struct_MizNatFunc l8_oldPos = Module_mobjects.f_mizInitNatFunc(32);
			final Struct_MizNatFunc l9_oldNeg = Module_mobjects.f_mizInitNatFunc(32);
			final Struct_MizCorrelEnv l11_env = aenv;
			final Func1<Struct_Pair,Struct_MizAttrColl> l10_countAdj = (Func1<Struct_Pair, Struct_MizAttrColl>)(Struct_MizAttrColl ac) -> {
				final Struct_MizNatFunc l11_np = Module_mobjects.f_mizInitNatFunc(32);
				final Struct_MizNatFunc l12_nn = Module_mobjects.f_mizInitNatFunc(32);
				final Struct_Vector l15_$14 = (ac).f_items;
				final Func1<Object,Struct_MizAttr> l16_$13 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
					if (((aa).f_neg==1)) {
						return Module_mobjects.f_mizNatUp(l11_np, Module_correl.f_mizAdjustedAttrNr(l11_env, aa));
					} else {
						return Module_mobjects.f_mizNatUp(l12_nn, Module_correl.f_mizAdjustedAttrNr(l11_env, aa));
					}
				};
				Module_vector.f_iterVector(l15_$14, ((Func1<Object,Object>)(Func1)l16_$13));
				return (new Struct_Pair(l11_np, l12_nn));
			};
			final Struct_MizAttrColl l13_cluster = acluster;
			final Struct_MizClusters l14_cl = acl;
			final Func2<Struct_Pair,Struct_MizNatFunc, Struct_MizNatFunc> l12_handleUsageAndFire = (Func2<Struct_Pair, Struct_MizNatFunc, Struct_MizNatFunc>)(Struct_MizNatFunc aoldP, Struct_MizNatFunc aoldN) -> {
				final Struct_Pair l13_pq = l10_countAdj.invoke(l13_cluster);
				final Func3<Object,Struct_MizNatFunc, Struct_MizNatFunc, Integer> l14_doSide = (Func3<Object, Struct_MizNatFunc, Struct_MizNatFunc, Integer>)(Struct_MizNatFunc anewF, Struct_MizNatFunc aoldF, Integer aneg) -> {
					final int l17_$16 = (Module_mobjects.f_mizNatCount(anewF)-1);
					final Func1<Object,Integer> l18_$15 = (Func1<Object, Integer>)(Integer ai) -> {
						final Struct_MizIntPair l19_it = Module_mobjects.f_mizNatItem(anewF, ((int)ai));
						int l20_oldVal;
						if (Module_mobjects.f_mizNatHasInDom(aoldF, (l19_it).f_x)) {
							l20_oldVal=Module_mobjects.f_mizNatValue(aoldF, (l19_it).f_x);
						} else {
							l20_oldVal=(-1);
						}
						if ((l20_oldVal<(l19_it).f_y)) {
							final Struct_MizNatFunc l21_idxSet = Module_roundcl.f_mizCondIndexAt(l14_cl, ((int)aneg), (l19_it).f_x);
							final int l24_$23 = (Module_mobjects.f_mizNatCount(l21_idxSet)-1);
							final Func1<Object,Integer> l25_$22 = (Func1<Object, Integer>)(Integer ak) -> {
								final int l26_clNr = (Module_mobjects.f_mizNatItem(l21_idxSet, ((int)ak))).f_x;
								Module_vector.f_setVectorUnsafe(l1_clFire, l26_clNr, (((int)Module_vector.f_getVectorUnsafe(l1_clFire, l26_clNr))-1));
								if ((((int)Module_vector.f_getVectorUnsafe(l1_clFire, l26_clNr))<=0)) {
									return Module_mobjects.f_mizNatInsertElem(l7_jobs, l26_clNr);
								} else {
									return null;
								}
							};
							return Module_runtime.f_fori(0, l24_$23, l25_$22);
						} else {
							return null;
						}
					};
					return Module_runtime.f_fori(0, l17_$16, l18_$15);
				};
				l14_doSide.invoke(((Struct_MizNatFunc)(l13_pq).f_first), aoldP, ((Integer)1));
				l14_doSide.invoke(((Struct_MizNatFunc)(l13_pq).f_second), aoldN, ((Integer)0));
				return l13_pq;
			};
			final int l17_$16 = (l0_nCond-1);
			final Func1<Object,Integer> l18_$15 = (Func1<Object, Integer>)(Integer aj) -> {
				if ((((int)Module_vector.f_getVectorUnsafe(l1_clFire, ((int)aj)))==0)) {
					return Module_mobjects.f_mizNatInsertElem(l7_jobs, ((int)aj));
				} else {
					return null;
				}
			};
			Module_runtime.f_fori(0, l17_$16, l18_$15);
			final Reference<Struct_Pair> l19_cur = ((Reference<Struct_Pair>)(new Reference(l12_handleUsageAndFire.invoke(l8_oldPos, l9_oldNeg))));
			final Struct_MizAttrColl l23_cluster = acluster;
			final Func0<Boolean> l22_$20 = (Func0<Boolean>)() -> {
				return ((Boolean)((l23_cluster).f_consistent&&(Module_mobjects.f_mizNatCount(l7_jobs)>0)));
			};
			final Struct_MizCorrelEnv l25_env = aenv;
			final Struct_MizClusters l26_cl = acl;
			final Struct_MizTyp l27_typ = atyp;
			final Struct_MizAttrColl l28_cluster = acluster;
			final Func0<Object> l24_$21 = (Func0<Object>)() -> {
				final int l25_last = (Module_mobjects.f_mizNatItem(l7_jobs, (Module_mobjects.f_mizNatCount(l7_jobs)-1))).f_x;
				Module_mobjects.f_mizNatDeleteElem(l7_jobs, l25_last);
				if (Module_roundcl.f_mizTryRounding(l25_env, ((Struct_MizCCluster)Module_vector.f_getVectorUnsafe((l26_cl).f_conditional, l25_last)), l27_typ, l28_cluster)) {
					l19_cur.value = l12_handleUsageAndFire.invoke(((Struct_MizNatFunc)(l19_cur.value).f_first), ((Struct_MizNatFunc)(l19_cur.value).f_second));
					return null;
				} else {
					return null;
				}
			};
			return Module_loop.f_while(l22_$20, l24_$21);
		} else {
			return null;
		}
	}
	public static final Struct_MizTyp f_mizRoundUpTrmType(Struct_MizCorrelEnv aenv, Struct_MizClusters acl, Struct at0) {
		final Struct l0_t = Module_correl.f_mizSkipPrivFunc(at0);
		return Module_roundcl.f_mizRoundUpTrmTypeWithType(aenv, acl, Module_correl_inst.f_mizCopyTrmType(aenv, l0_t), l0_t);
	}
	public static final Struct_MizTyp f_mizRoundUpTrmTypeWithType(Struct_MizCorrelEnv aenv, Struct_MizClusters acl, Struct_MizTyp atyp0, Struct at) {
		final int l0_srt = Module_correl.f_mizTrmSort(at);
		final Struct_MizTyp l1_typ = atyp0;
		if ((((l0_srt==Module_lexicon.g_ikTrmFunctor)||(l0_srt==Module_lexicon.g_ikTrmSelector))||(l0_srt==Module_lexicon.g_ikTrmAggreg))) {
			final Struct l2_gsymswitch4 = Module_roundcl.f_mizFClusterInterval(aenv, acl, at);
			Struct l3__tmp = l2_gsymswitch4;
			switch (l3__tmp.getTypeId()) {
			case 80/*None*/: {
				break;
			}
			case 88/*Some*/: {
				final Struct_Some l4__tmp = (Struct_Some)l3__tmp;
				final Object l5_iv = l4__tmp.f_value;
				final Struct_Pair l6_iv = ((Struct_Pair)(l4__tmp).f_value);
				final Struct_MizIntSeq l7_used = Module_mobjects.f_mizInitIntSeq(4);
				final Reference<Struct_MizAttrColl> l8_cluster = ((Reference<Struct_MizAttrColl>)(new Reference(Module_correl.f_mizCopyCluster((l1_typ).f_upper))));
				final Reference<Integer> l9_i = ((Reference<Integer>)(new Reference((l6_iv).f_first)));
				final Reference<Boolean> l10_inconsistent = ((Reference<Boolean>)(new Reference(false)));
				final Func0<Boolean> l13_$11 = (Func0<Boolean>)() -> {
					return ((Boolean)(!((boolean)l10_inconsistent.value)&&(((int)l9_i.value)<=((int)(l6_iv).f_second))));
				};
				final Struct_MizClusters l15_cl = acl;
				final Struct_MizCorrelEnv l16_env = aenv;
				final Struct l17_t = at;
				final Func0<Object> l14_$12 = (Func0<Object>)() -> {
					if (Module_mobjects.f_mizIntSetIsInSet(l7_used, ((int)l9_i.value))) {
						l9_i.value = ((Integer)(((int)l9_i.value)+1));
						return null;
					} else {
						final Struct_MizFCluster l15_fc = ((Struct_MizFCluster)Module_vector.f_getVectorUnsafe((l15_cl).f_functor, ((int)l9_i.value)));
						if (Module_roundcl.f_mizFClusterRoundUpWith(l16_env, l15_fc, l17_t, l1_typ, l8_cluster.value)) {
							Module_roundcl.f_mizRoundUpCluster(l16_env, l15_cl, l8_cluster.value, l1_typ);
							((Field_upper)l1_typ).set_upper(l8_cluster.value);
							if (!(l8_cluster.value).f_consistent) {
								l10_inconsistent.value = ((Boolean)true);
								return null;
							} else {
								l8_cluster.value = Module_correl.f_mizCopyCluster((l1_typ).f_upper);
								final int l16___u2 = Module_mobjects.f_mizIntSetInsert(l7_used, ((int)l9_i.value));
								if (Module_maybe.f_isSome((l15_fc).f_clusterType)) {
									l9_i.value = ((Integer)(l6_iv).f_first);
									return null;
								} else {
									l9_i.value = ((Integer)(((int)l9_i.value)+1));
									return null;
								}
							}
						} else {
							if (Module_maybe.f_isNone((l15_fc).f_clusterType)) {
								final int l17___u = Module_mobjects.f_mizIntSetInsert(l7_used, ((int)l9_i.value));
							} else {
							}
							l9_i.value = ((Integer)(((int)l9_i.value)+1));
							return null;
						}
					}
				};
				Module_loop.f_while(l13_$11, l14_$12);
				break;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l3__tmp.getTypeName());
			}
		} else {
		}
		return l1_typ;
	}
	public static final Struct f_mizSwapArguments(Struct al, int afirst, int asecond) {
		final Struct_Vector l0_v = Module_correl.f_mizTrmList2Vector(al);
		final int l1_n = Module_vector.f_sizeVector(l0_v);
		if (((((afirst>=1)&&(afirst<=l1_n))&&(asecond>=1))&&(asecond<=l1_n))) {
			final Struct l2_a = ((Struct)Module_vector.f_getVectorUnsafe(l0_v, (afirst-1)));
			final Struct l3_b = ((Struct)Module_vector.f_getVectorUnsafe(l0_v, (asecond-1)));
			Module_vector.f_setVectorUnsafe(l0_v, (afirst-1), l3_b);
			Module_vector.f_setVectorUnsafe(l0_v, (asecond-1), l2_a);
		} else {
		}
		return Module_correl.f_mizVector2TrmList(l0_v);
	}
	public static final boolean f_mizTryRounding(Struct_MizCorrelEnv aenv, Struct_MizCCluster acond, Struct_MizTyp atyp, Struct_MizAttrColl acluster) {
		if (!Module_correl_inst.f_mizTypReachable(aenv, (acond).f_clusterType, atyp)) {
			return false;
		} else {
			Module_correl_match.f_mizDisposeSubstTrm(aenv);
			final Struct_MizAttrColl l5_$1 = (acond).f_antecedent;
			final Struct_MizCorrelEnv l7_env = aenv;
			final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l6_$0 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
				return ((Boolean)Module_correl_match.f_mizEsAttrRev(l7_env, ax, ay));
			};
			boolean l8_$4;
			if (Module_correl_inst.f_mizAttrCollIsSubsetOf(aenv, l5_$1, acluster, l6_$0)) {
				final Struct_MizAttrColl l11_$10 = ((acond).f_consequent).f_upper;
				final Struct_MizCorrelEnv l13_env = aenv;
				final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l12_$9 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
					return ((Boolean)Module_correl_match.f_mizAttrEquals(l13_env, ax, ay));
				};
				l8_$4=!Module_correl_inst.f_mizAttrCollIsSubsetOf(aenv, l11_$10, acluster, l12_$9);
			} else {
				l8_$4=false;
			}
			final boolean l14_ok = l8_$4;
			boolean l15_res;
			if (l14_ok) {
				final Struct l16_gsymswitch1 = Module_correl_inst.f_mizWideningOf(aenv, (acond).f_clusterType, Wrappers.w_mizCopyTyp.invoke(atyp));
				Struct l17__tmp = l16_gsymswitch1;
				switch (l17__tmp.getTypeId()) {
				case 88/*Some*/: {
					final Struct_Some l18__tmp = (Struct_Some)l17__tmp;
					final Object l19_lTyp = l18__tmp.f_value;
					final Struct_MizTyp l20_lTyp = ((Struct_MizTyp)(l18__tmp).f_value);
					boolean l25_$24;
					if (Module_correl_match.f_mizCompEsTyp(aenv, (acond).f_clusterType, l20_lTyp, false)) {
						final Struct_MizAttrColl l29_$27 = ((acond).f_clusterType).f_lower;
						final Struct_MizAttrColl l30_$28 = (l20_lTyp).f_upper;
						final Struct_MizCorrelEnv l32_env = aenv;
						final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l31_$26 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
							return ((Boolean)Module_correl_match.f_mizEsAttrRev(l32_env, ax, ay));
						};
						l25_$24=Module_correl_inst.f_mizAttrCollIsSubsetOf(aenv, l29_$27, l30_$28, l31_$26);
					} else {
						l25_$24=false;
					}
					if ((l25_$24&&Module_correl_match.f_mizCheckLociTypes(aenv, (acond).f_primaries))) {
						Module_roundcl.f_mizEnlargeByInst(aenv, acluster, ((acond).f_consequent).f_upper);
						l15_res=true;
					} else {
						l15_res=false;
					}
					break;
				}
				case 80/*None*/: {
					l15_res=false;
					break;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l17__tmp.getTypeName());
				}
			} else {
				l15_res=false;
			}
			Module_correl_match.f_mizDisposeSubstTrm(aenv);
			return l15_res;
		}
	}
	public static final Object f_mizWireRoundcl(Struct_MizCorrelEnv aenv, Struct_MizClusters acl) {
		final Struct_MizClusters l2_cl = acl;
		final Func2<Struct_MizTyp,Struct_MizCorrelEnv, Struct> l1_$0 = (Func2<Struct_MizTyp, Struct_MizCorrelEnv, Struct>)(Struct_MizCorrelEnv ae, Struct at) -> {
			return Module_roundcl.f_mizRoundUpTrmType(ae, l2_cl, at);
		};
		((Field_roundUpTrmType)aenv).set_roundUpTrmType(l1_$0);
		final Struct_MizClusters l5_cl = acl;
		final Func3<Object,Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp> l4_$3 = (Func3<Object, Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp>)(Struct_MizCorrelEnv ae, Struct_MizAttrColl ac, Struct_MizTyp aty) -> {
			return Module_roundcl.f_mizRoundUpCluster(ae, l5_cl, ac, aty);
		};
		((Field_roundUpCluster)aenv).set_roundUpCluster(l4_$3);
		return null;
	}
}
