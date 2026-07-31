// HASH COLLISIONS: YES
// timestamp: 1785524846000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_equalizer {
	public static int g_expAggreg;
	public static int g_expFunctor;
	public static int g_expPrivFunc;
	public static int g_expSchFunc;
	public static int g_expSelector;
	public static void init() {
		g_expAggreg=1;
		g_expFunctor=0;
		g_expPrivFunc=3;
		g_expSchFunc=4;
		g_expSelector=2;
	}
	public static final Struct_Pair f_mizANAdjustedArgs(Struct_MizCorrelEnv aenv, Struct at) {
		final Struct_MizFuncTrm l0_ft = Module_equalizer.f_mizEqAsFunc(at);
		if (((l0_ft).f_sort==Module_lexicon.g_ikTrmFunctor)) {
			return Module_correl.f_mizAdjustTrm(aenv, l0_ft);
		} else {
			return (new Struct_Pair((l0_ft).f_funcNr, (l0_ft).f_args));
		}
	}
	public static final Struct_MizAttrColl f_mizANAllowedAttrsIn(Struct_MizEqualizer aeq_0, Struct_MizAttrColl acluster) {
		final Struct_MizAttrColl l0_r = Module_correl.f_mizNewEmptyCluster();
		final Struct_Vector l3_$2 = (acluster).f_items;
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func1<Object,Struct_MizAttr> l4_$1 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			if (!Module_equalizer.f_mizANAttrHasForeignDConst(l5_eq_0, aa)) {
				return Module_correl.f_mizAttrCollInsert((l5_eq_0).f_env, l0_r, Wrappers.w_mizCopyAttr.invoke(aa));
			} else {
				return null;
			}
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		return l0_r;
	}
	public static final boolean f_mizANAttrHasForeignDConst(Struct_MizEqualizer aeq_0, Struct_MizAttr aa) {
		final Reference<Boolean> l0_found = ((Reference<Boolean>)(new Reference(false)));
		final Struct_MizEqualizer l3_eq_0 = aeq_0;
		final Func2<Struct,Struct_MizWithin, Struct> l2_$1 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al3_0, Struct at) -> {
			Struct l4__tmp = at;
			switch (l4__tmp.getTypeId()) {
			case 77/*MizVarTrm*/: {
				final Struct_MizVarTrm l5__tmp = (Struct_MizVarTrm)l4__tmp;
				final int l6_srt = l5__tmp.f_sort;
				final int l7_varNr = l5__tmp.f_varNr;
				final int l8_srt = (l5__tmp).f_sort;
				final int l9_varNr = (l5__tmp).f_varNr;
				final int l10___ = (l5__tmp).f_pattNr;
				final int l11___ = (l5__tmp).f_trmInfo;
				if (((l8_srt==Module_lexicon.g_ikTrmInfConst)&&!((boolean)l0_found.value))) {
					boolean l14_$13;
					if ((l9_varNr<Module_vector.f_sizeVector((l3_eq_0).f_dTrm))) {
						final Struct l15_gsymswitch37 = ((Struct)Module_vector.f_getVectorUnsafe((l3_eq_0).f_dTrm, l9_varNr));
						Struct l16__tmp = l15_gsymswitch37;
						switch (l16__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l17__tmp = (Struct_Some)l16__tmp;
							final Struct l18___ = ((Struct)(l17__tmp).f_value);
							l14_$13=true;
							break;
						}
						case 82/*None*/: {
							l14_$13=false;
							break;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l16__tmp.getTypeName());
						}
					} else {
						l14_$13=false;
					}
					final boolean l19_avail = l14_$13;
					if (!l19_avail) {
						l0_found.value = ((Boolean)true);
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
			return at;
		};
		final Struct_MizWithin l4_w = Module_correl.f_mizMakeWithin(l2_$1);
		Module_correl.f_mizWithinTrmList(l4_w, (aa).f_args);
		return ((boolean)l0_found.value);
	}
	public static final Struct f_mizANDependentClasses(Struct_MizEqualizer aeq_0, int aenr) {
		final Struct_MizTrmSRec l0_rec = Module_equalizer.f_mizTrmSAt(aeq_0, aenr);
		final Struct l1_gsymswitch42 = (l0_rec).f_eqClass;
		boolean l2_live;
		Struct l3__tmp = l1_gsymswitch42;
		switch (l3__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			l2_live=false;
			break;
		}
		default: {
			l2_live=true;
			break;
		}
		}
		if (l2_live) {
			final Struct_MizNatFunc l5_r = Module_mobjects.f_mizInitNatSet(10);
			final Reference<Integer> l6_i = ((Reference<Integer>)(new Reference(1)));
			final Struct_MizEqualizer l10_eq_0 = aeq_0;
			final Func0<Boolean> l9_$7 = (Func0<Boolean>)() -> {
				return (((int)l6_i.value)<=Module_equalizer.f_mizTrmNbr(l10_eq_0));
			};
			final Struct_MizEqualizer l12_eq_0 = aeq_0;
			final int l13_enr = aenr;
			final Func0<Object> l11_$8 = (Func0<Object>)() -> {
				if (Module_equalizer.f_mizANDependsOn(l12_eq_0, ((int)l6_i.value), l13_enr)) {
					Module_mobjects.f_mizNatInsertElem(l5_r, ((int)l6_i.value));
				} else {
				}
				l6_i.value = ((Integer)(((int)l6_i.value)+1));
				return null;
			};
			Module_loop.f_while(l9_$7, l11_$8);
			return (new Struct_Some(l5_r));
		} else {
			return ((Struct)SingletonStructs.str_None);
		}
	}
	public static final boolean f_mizANDependsOn(Struct_MizEqualizer aeq_0, int ax, int ay) {
		final Struct_MizTrmSRec l0_rx = Module_equalizer.f_mizTrmSAt(aeq_0, ax);
		final Struct_MizTrmSRec l1_ry = Module_equalizer.f_mizTrmSAt(aeq_0, ay);
		final Struct l2_gsymswitch38 = (l0_rx).f_eqClass;
		boolean l3_liveX;
		Struct l4__tmp = l2_gsymswitch38;
		switch (l4__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			l3_liveX=false;
			break;
		}
		default: {
			l3_liveX=true;
			break;
		}
		}
		final Struct l6_gsymswitch39 = (l1_ry).f_eqClass;
		boolean l7_liveY;
		Struct l8__tmp = l6_gsymswitch39;
		switch (l8__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			l7_liveY=false;
			break;
		}
		default: {
			l7_liveY=true;
			break;
		}
		}
		if ((l3_liveX&&l7_liveY)) {
			final Reference<Boolean> l10_found = ((Reference<Boolean>)(new Reference(false)));
			final Struct_MizEqualizer l13_eq_0 = aeq_0;
			final int l14_y = ay;
			final Func2<Struct,Struct_MizWithin, Struct> l12_$11 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al13_0, Struct at) -> {
				Struct l14__tmp = at;
				switch (l14__tmp.getTypeId()) {
				case 77/*MizVarTrm*/: {
					final Struct_MizVarTrm l15__tmp = (Struct_MizVarTrm)l14__tmp;
					final int l16_srt = l15__tmp.f_sort;
					final int l17_info = l15__tmp.f_trmInfo;
					final int l18_srt = (l15__tmp).f_sort;
					final int l19___ = (l15__tmp).f_varNr;
					final int l20___ = (l15__tmp).f_pattNr;
					final int l21_info = (l15__tmp).f_trmInfo;
					if ((((l18_srt==Module_lexicon.g_ikTrmEqConst)&&!((boolean)l10_found.value))&&(Module_equalizer.f_mizClassOf(l13_eq_0, l21_info)==l14_y))) {
						l10_found.value = ((Boolean)true);
					} else {
					}
					break;
				}
				default: {
					break;
				}
				}
				return at;
			};
			final Struct_MizWithin l15_w = Module_correl.f_mizMakeWithin(l12_$11);
			final Reference<Struct> l16_cur = ((Reference<Struct>)(new Reference((l0_rx).f_eqClass)));
			final Func0<Boolean> l19_$17 = (Func0<Boolean>)() -> {
				boolean l22_$21;
				if (!((boolean)l10_found.value)) {
					final Struct l23_gsymswitch40 = l16_cur.value;
					Struct l24__tmp = l23_gsymswitch40;
					switch (l24__tmp.getTypeId()) {
					case 71/*MizTrmNil*/: {
						l22_$21=false;
						break;
					}
					default: {
						l22_$21=true;
						break;
					}
					}
				} else {
					l22_$21=false;
				}
				return ((Boolean)l22_$21);
			};
			final Func0<Object> l20_$18 = (Func0<Object>)() -> {
				final Struct l21_gsymswitch41 = l16_cur.value;
				Struct l22__tmp = l21_gsymswitch41;
				switch (l22__tmp.getTypeId()) {
				case 70/*MizTrmElem*/: {
					final Struct_MizTrmElem l23__tmp = (Struct_MizTrmElem)l22__tmp;
					final Struct l24_trm = l23__tmp.f_trm;
					final Struct l25_next = l23__tmp.f_next;
					final Struct l26_trm = (l23__tmp).f_trm;
					final Struct l27_next = (l23__tmp).f_next;
					Module_correl.f_mizWithinTrm(l15_w, l26_trm);
					l16_cur.value = l27_next;
					return null;
				}
				case 71/*MizTrmNil*/: {
					return null;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l22__tmp.getTypeName());
				}
			};
			Module_loop.f_while(l19_$17, l20_$18);
			if (!((boolean)l10_found.value)) {
				Module_correl.f_mizWithinTypVector(l15_w, (l0_rx).f_xTypClass);
			} else {
			}
			if (!((boolean)l10_found.value)) {
				Module_correl.f_mizWithinCluster(l15_w, (l0_rx).f_superCluster);
			} else {
			}
			return ((boolean)l10_found.value);
		} else {
			return false;
		}
	}
	public static final boolean f_mizANEnlarge(Struct_MizEqualizer aeq_0, Struct_MizAttrColl asc, Struct_MizAttrColl alc) {
		final int l0_c0 = Module_vector.f_sizeVector((asc).f_items);
		final Struct_Vector l3_$2 = (alc).f_items;
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Struct_MizAttrColl l6_sc = asc;
		final Func1<Object,Struct_MizAttr> l4_$1 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			return Module_correl.f_mizAttrCollInsert((l5_eq_0).f_env, l6_sc, Wrappers.w_mizCopyAttr.invoke(aa));
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		return (Module_vector.f_sizeVector((asc).f_items)!=l0_c0);
	}
	public static final Struct_MizPreInst f_mizANInstAttr(Struct_MizEqualizer aeq_0, Struct_Vector aprim, int aenr, Struct_MizAttr aaa) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final Struct_Pair l1_ap = Module_correl.f_mizAdjustAttr(l0_env, aaa);
		final Struct_MizTrmSRec l2_rec = Module_equalizer.f_mizTrmSAt(aeq_0, aenr);
		final Struct_MizPreInst l3_r = Module_prechecker.f_mizPreInstBottom();
		final Reference<Boolean> l4_stop = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l5_i = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l8_$6 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l4_stop.value)&&(((int)l5_i.value)<Module_vector.f_sizeVector(((l2_rec).f_superCluster).f_items))));
		};
		final Struct_MizAttr l10_aa = aaa;
		final Struct_MizEqualizer l11_eq_0 = aeq_0;
		final Struct_Vector l12_prim = aprim;
		final Func0<Object> l9_$7 = (Func0<Object>)() -> {
			final Struct_MizAttr l10_sa = ((Struct_MizAttr)Module_vector.f_getVectorUnsafe(((l2_rec).f_superCluster).f_items, ((int)l5_i.value)));
			final Struct_Pair l11_sp = Module_correl.f_mizAdjustAttr(l0_env, l10_sa);
			if (((((int)(l11_sp).f_first)==((int)(l1_ap).f_first))&&((l10_sa).f_neg==(l10_aa).f_neg))) {
				final Struct l12_gsymswitch51 = ((Struct)(l1_ap).f_second);
				boolean l13_noArgs;
				Struct l14__tmp = l12_gsymswitch51;
				switch (l14__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l13_noArgs=true;
					break;
				}
				default: {
					l13_noArgs=false;
					break;
				}
				}
				if (l13_noArgs) {
					((Field_top)l3_r).set_top(true);
					Module_vector.f_clearVector((l3_r).f_evals);
					l4_stop.value = ((Boolean)true);
				} else {
					final Struct_MizPreInst l16_v = Module_equalizer.f_mizANJoinArgs(l11_eq_0, l12_prim, ((Struct)(l11_sp).f_second), ((Struct)(l1_ap).f_second));
					Module_equalizer.f_mizANUnionWith(l3_r, l16_v);
				}
			} else {
			}
			l5_i.value = ((Integer)(((int)l5_i.value)+1));
			return null;
		};
		Module_loop.f_while(l8_$6, l9_$7);
		return l3_r;
	}
	public static final Struct_MizPreInst f_mizANInstAttrs(Struct_MizEqualizer aeq_0, Struct_Vector aprim, int aenr, Struct_MizAttrColl aalc) {
		final Struct_MizPreInst l0_r = Module_prechecker.f_mizPreInstTop();
		final Struct_Vector l3_$2 = (aalc).f_items;
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Struct_Vector l6_prim = aprim;
		final int l7_enr = aenr;
		final Func1<Object,Struct_MizAttr> l4_$1 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			return Module_equalizer.f_mizANJoinWith(l0_r, Module_equalizer.f_mizANInstAttr(l5_eq_0, l6_prim, l7_enr, aa));
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		return l0_r;
	}
	public static final Struct_MizPreInst f_mizANInstCCluster(Struct_MizEqualizer aeq_0, Struct_MizCCluster acc, int aenr) {
		final Struct_MizPreInst l0_r = Module_equalizer.f_mizANInstType(aeq_0, (acc).f_primaries, aenr, (acc).f_clusterType);
		final boolean l1_dbg = (Module_url_parameter.f_getUrlParameterDef("dbg-rus2", "")).equals(Module_string.f_i2s(aenr));
		if (l1_dbg) {
			Module_runtime.f_println(((((((("  ICC "+(acc).f_article)+":")+Module_string.f_i2s((acc).f_absNr))+" typM=")+Module_string.f_i2s(((acc).f_clusterType).f_modNr))+" instType -> ")+((l0_r).f_top?"top":Module_string.f_i2s(Module_prechecker.f_mizPreInstCount(l0_r)))));
		} else {
		}
		final Struct_MizPreInst l2_a = Module_equalizer.f_mizANInstAttrs(aeq_0, (acc).f_primaries, aenr, (acc).f_antecedent);
		if (l1_dbg) {
			Module_runtime.f_println(("  ICC ante -> "+((l2_a).f_top?"top":Module_string.f_i2s(Module_prechecker.f_mizPreInstCount(l2_a)))));
		} else {
		}
		Module_equalizer.f_mizANJoinWith(l0_r, l2_a);
		return l0_r;
	}
	public static final Struct_MizPreInst f_mizANInstFCluster(Struct_MizEqualizer aeq_0, Struct_MizFCluster afc, int aenr) {
		final Struct_MizPreInst l0_r = Module_equalizer.f_mizANInstTerm(aeq_0, (afc).f_primaries, (Module_equalizer.f_mizTrmSAt(aeq_0, aenr)).f_term, (afc).f_clusterTerm);
		final Struct l1_gsymswitch52 = (afc).f_clusterType;
		Struct l2__tmp = l1_gsymswitch52;
		switch (l2__tmp.getTypeId()) {
		case 90/*Some*/: {
			final Struct_Some l3__tmp = (Struct_Some)l2__tmp;
			final Object l4_ct = l3__tmp.f_value;
			final Struct_MizTyp l5_ct = ((Struct_MizTyp)(l3__tmp).f_value);
			Module_equalizer.f_mizANJoinWith(l0_r, Module_equalizer.f_mizANInstType(aeq_0, (afc).f_primaries, aenr, l5_ct));
			break;
		}
		case 82/*None*/: {
			break;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
		}
		return l0_r;
	}
	public static final Struct_MizPreInst f_mizANInstRadixType(Struct_MizEqualizer aeq_0, Struct_Vector aprim, int aenr0, Struct_MizTyp aatyp) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_enr = Module_equalizer.f_mizClassOf(aeq_0, aenr0);
		final Struct_Pair l2_ap = Module_correl.f_mizAdjustTyp(l0_env, aatyp);
		final Struct_MizTrmSRec l3_rec = Module_equalizer.f_mizTrmSAt(aeq_0, l1_enr);
		final Struct_MizPreInst l4_r = Module_prechecker.f_mizPreInstBottom();
		final boolean l5_dbgR = (Module_url_parameter.f_getUrlParameterDef("dbg-rus2", "")).equals(Module_string.f_i2s(aenr0));
		final Reference<Boolean> l6_stop = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l7_i = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l6_stop.value)&&(((int)l7_i.value)<Module_vector.f_sizeVector((l3_rec).f_xTypClass))));
		};
		final Struct_MizTyp l12_atyp = aatyp;
		final Struct_MizEqualizer l13_eq_0 = aeq_0;
		final Struct_Vector l14_prim = aprim;
		final Func0<Object> l11_$9 = (Func0<Object>)() -> {
			final Struct_MizTyp l12_xt = ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l3_rec).f_xTypClass, ((int)l7_i.value)));
			final Struct_Pair l13_mp = Module_correl.f_mizAdjustTyp(l0_env, l12_xt);
			if (l5_dbgR) {
				Module_runtime.f_println(((((((("    IRT xt.sort="+Module_string.f_i2s((l12_xt).f_sort))+"/")+Module_string.f_i2s((l12_atyp).f_sort))+" nr=")+Module_string.f_i2s(((int)(l13_mp).f_first)))+"/")+Module_string.f_i2s(((int)(l2_ap).f_first))));
			} else {
			}
			if ((((l12_xt).f_sort==(l12_atyp).f_sort)&&(((int)(l13_mp).f_first)==((int)(l2_ap).f_first)))) {
				final Struct l14_gsymswitch50 = ((Struct)(l2_ap).f_second);
				boolean l15_noArgs;
				Struct l16__tmp = l14_gsymswitch50;
				switch (l16__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l15_noArgs=true;
					break;
				}
				default: {
					l15_noArgs=false;
					break;
				}
				}
				if (l15_noArgs) {
					((Field_top)l4_r).set_top(true);
					Module_vector.f_clearVector((l4_r).f_evals);
					l6_stop.value = ((Boolean)true);
				} else {
					final Struct_MizPreInst l18_z = Module_equalizer.f_mizANJoinArgs(l13_eq_0, l14_prim, ((Struct)(l13_mp).f_second), ((Struct)(l2_ap).f_second));
					Module_equalizer.f_mizANUnionWith(l4_r, l18_z);
				}
			} else {
			}
			l7_i.value = ((Integer)(((int)l7_i.value)+1));
			return null;
		};
		Module_loop.f_while(l10_$8, l11_$9);
		return l4_r;
	}
	public static final Struct_MizPreInst f_mizANInstTerm(Struct_MizEqualizer aeq_0, Struct_Vector aprim, Struct aeTrm, Struct aaTrm) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		if ((Module_url_parameter.f_getUrlParameterDef("dbg-rus3", "")).equals("2")) {
			Module_runtime.f_println(((((("       AIT e="+Module_string.f_i2s(Module_correl.f_mizTrmSort(aeTrm)))+"/")+Module_string.f_i2s(Module_equalizer.f_mizTrmInfoOf(aeTrm)))+" a=")+Module_string.f_i2s(Module_correl.f_mizTrmSort(aaTrm))));
		} else {
		}
		final int l1_eSort = Module_correl.f_mizTrmSort(aeTrm);
		if ((l1_eSort!=Module_lexicon.g_ikTrmEqConst)) {
			if ((l1_eSort==Module_lexicon.g_ikTrmNumeral)) {
				final Struct_MizVarTrm l2_ev = Module_equalizer.f_mizEqAsVar(aeTrm);
				boolean l5_$4;
				if ((Module_correl.f_mizTrmSort(aaTrm)==Module_lexicon.g_ikTrmNumeral)) {
					final Struct_MizVarTrm l6_av = Module_equalizer.f_mizEqAsVar(aaTrm);
					l5_$4=((l6_av).f_varNr==(l2_ev).f_varNr);
				} else {
					l5_$4=false;
				}
				final boolean l7_same = l5_$4;
				if (l7_same) {
					return Module_prechecker.f_mizPreInstTop();
				} else {
					return Module_prechecker.f_mizPreInstBottom();
				}
			} else {
				if ((l1_eSort==Module_correl.f_mizTrmSort(aaTrm))) {
					final Struct_Pair l8_ep = Module_equalizer.f_mizANAdjustedArgs(l0_env, aeTrm);
					final Struct_Pair l9_ap = Module_equalizer.f_mizANAdjustedArgs(l0_env, aaTrm);
					if ((((int)(l8_ep).f_first)==((int)(l9_ap).f_first))) {
						return Module_equalizer.f_mizANJoinArgs(aeq_0, aprim, ((Struct)(l8_ep).f_second), ((Struct)(l9_ap).f_second));
					} else {
						return Module_equalizer.f_mizANInstTermClass(aeq_0, aprim, Module_equalizer.f_mizClassOf(aeq_0, Module_equalizer.f_mizTrmInfoOf(aeTrm)), aaTrm);
					}
				} else {
					return Module_prechecker.f_mizPreInstBottom();
				}
			}
		} else {
			return Module_equalizer.f_mizANInstTermClass(aeq_0, aprim, Module_equalizer.f_mizClassOf(aeq_0, Module_equalizer.f_mizTrmInfoOf(aeTrm)), aaTrm);
		}
	}
	public static final Struct_MizPreInst f_mizANInstTermClass(Struct_MizEqualizer aeq_0, Struct_Vector aprim, int aenr, Struct aaTrm) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_aSort = Module_correl.f_mizTrmSort(aaTrm);
		if ((l1_aSort==Module_lexicon.g_ikTrmLocus)) {
			final Struct_MizVarTrm l2_av = Module_equalizer.f_mizEqAsVar(aaTrm);
			final Struct_MizTyp l3_t1 = ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(aprim, ((l2_av).f_varNr-1)));
			final Struct_MizPreInst l4_z = Module_equalizer.f_mizANInstType(aeq_0, aprim, aenr, l3_t1);
			if ((Module_prechecker.f_mizPreInstCount(l4_z)==0)) {
				return l4_z;
			} else {
				final Struct_MizVarTrm l5_rep = Module_equalizer.f_mizEqAsVar((Module_equalizer.f_mizTrmSAt(aeq_0, aenr)).f_term);
				final Struct_MizNatFunc l6_n = Module_mobjects.f_mizInitNatFunc(1);
				Module_mobjects.f_mizNatAssign(l6_n, (l2_av).f_varNr, (l5_rep).f_varNr);
				Module_equalizer.f_mizANJoinWith(l4_z, Module_prechecker.f_mizPreInstSingle(l6_n));
				return l4_z;
			}
		} else {
			if ((l1_aSort==Module_lexicon.g_ikTrmNumeral)) {
				final Struct_MizVarTrm l7_av = Module_equalizer.f_mizEqAsVar(aaTrm);
				final Struct_MizTrmSRec l8_rec = Module_equalizer.f_mizTrmSAt(aeq_0, aenr);
				if ((((l8_rec).f_numDetermined&&(((l8_rec).f_numValue).f_re==Module_math.f_i2d((l7_av).f_varNr)))&&(((l8_rec).f_numValue).f_im==0.0))) {
					return Module_prechecker.f_mizPreInstTop();
				} else {
					return Module_prechecker.f_mizPreInstBottom();
				}
			} else {
				final Struct_Pair l9_ap = Module_equalizer.f_mizANAdjustedArgs(l0_env, aaTrm);
				final Struct_MizPreInst l10_r = Module_prechecker.f_mizPreInstBottom();
				if ((!(Module_url_parameter.f_getUrlParameterDef("dbg-rus3", "")).equals(""))) {
					final Reference<String> l11_mems3 = ((Reference<String>)(new Reference("")));
					final Reference<Struct> l12_cur3 = ((Reference<Struct>)(new Reference((Module_equalizer.f_mizTrmSAt(aeq_0, aenr)).f_eqClass)));
					final Func0<Boolean> l15_$13 = (Func0<Boolean>)() -> {
						final Struct l16_gsymswitch46 = l12_cur3.value;
						Struct l17__tmp = l16_gsymswitch46;
						switch (l17__tmp.getTypeId()) {
						case 71/*MizTrmNil*/: {
							return ((Boolean)false);
						}
						default: {
							return ((Boolean)true);
						}
						}
					};
					final Func0<Object> l16_$14 = (Func0<Object>)() -> {
						final Struct l17_gsymswitch47 = l12_cur3.value;
						Struct l18__tmp = l17_gsymswitch47;
						switch (l18__tmp.getTypeId()) {
						case 70/*MizTrmElem*/: {
							final Struct_MizTrmElem l19__tmp = (Struct_MizTrmElem)l18__tmp;
							final Struct l20_m3 = l19__tmp.f_trm;
							final Struct l21_n3 = l19__tmp.f_next;
							final Struct l22_m3 = (l19__tmp).f_trm;
							final Struct l23_n3 = (l19__tmp).f_next;
							final String l26_$25 = ((l11_mems3.value+" ")+Module_string.f_i2s(Module_correl.f_mizTrmSort(l22_m3)));
							String l27_$24;
							Struct l28__tmp = l22_m3;
							switch (l28__tmp.getTypeId()) {
							case 45/*MizFuncTrm*/: {
								final Struct_MizFuncTrm l29__tmp = (Struct_MizFuncTrm)l28__tmp;
								final int l30_fn3 = l29__tmp.f_funcNr;
								final int l31___ = (l29__tmp).f_sort;
								final int l32_fn3 = (l29__tmp).f_funcNr;
								final Struct l33___ = (l29__tmp).f_args;
								final int l34___ = (l29__tmp).f_pattNr;
								final int l35___ = (l29__tmp).f_trmInfo;
								l27_$24=(":"+Module_string.f_i2s(l32_fn3));
								break;
							}
							case 77/*MizVarTrm*/: {
								final Struct_MizVarTrm l29__tmp = (Struct_MizVarTrm)l28__tmp;
								final int l36_vn3 = l29__tmp.f_varNr;
								final int l37___ = (l29__tmp).f_sort;
								final int l38_vn3 = (l29__tmp).f_varNr;
								final int l39___ = (l29__tmp).f_pattNr;
								final int l40___ = (l29__tmp).f_trmInfo;
								l27_$24=("#"+Module_string.f_i2s(l38_vn3));
								break;
							}
							default: {
								l27_$24="";
								break;
							}
							}
							l11_mems3.value = (l26_$25+l27_$24);
							l12_cur3.value = l23_n3;
							return null;
						}
						case 71/*MizTrmNil*/: {
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l18__tmp.getTypeName());
						}
					};
					Module_loop.f_while(l15_$13, l16_$14);
					Module_runtime.f_println(((((((("      ITC enr="+Module_string.f_i2s(aenr))+" aSort=")+Module_string.f_i2s(l1_aSort))+" aNr=")+Module_string.f_i2s(((int)(l9_ap).f_first)))+" mems:")+l11_mems3.value));
				} else {
				}
				final Reference<Struct> l17_cur = ((Reference<Struct>)(new Reference((Module_equalizer.f_mizTrmSAt(aeq_0, aenr)).f_eqClass)));
				final Func0<Boolean> l20_$18 = (Func0<Boolean>)() -> {
					final Struct l21_gsymswitch48 = l17_cur.value;
					Struct l22__tmp = l21_gsymswitch48;
					switch (l22__tmp.getTypeId()) {
					case 71/*MizTrmNil*/: {
						return ((Boolean)false);
					}
					default: {
						return ((Boolean)true);
					}
					}
				};
				final Struct_MizEqualizer l22_eq_0 = aeq_0;
				final Struct_Vector l23_prim = aprim;
				final Func0<Object> l21_$19 = (Func0<Object>)() -> {
					final Struct l22_gsymswitch49 = l17_cur.value;
					Struct l23__tmp = l22_gsymswitch49;
					switch (l23__tmp.getTypeId()) {
					case 70/*MizTrmElem*/: {
						final Struct_MizTrmElem l24__tmp = (Struct_MizTrmElem)l23__tmp;
						final Struct l25_mem = l24__tmp.f_trm;
						final Struct l26_next = l24__tmp.f_next;
						final Struct l27_mem = (l24__tmp).f_trm;
						final Struct l28_next = (l24__tmp).f_next;
						if ((Module_correl.f_mizTrmSort(l27_mem)==l1_aSort)) {
							final Struct_Pair l29_mp = Module_equalizer.f_mizANAdjustedArgs(l0_env, l27_mem);
							if ((((int)(l29_mp).f_first)==((int)(l9_ap).f_first))) {
								final Struct_MizPreInst l30_z = Module_equalizer.f_mizANJoinArgs(l22_eq_0, l23_prim, ((Struct)(l29_mp).f_second), ((Struct)(l9_ap).f_second));
								Module_equalizer.f_mizANUnionWith(l10_r, l30_z);
							} else {
							}
						} else {
						}
						l17_cur.value = l28_next;
						return null;
					}
					case 71/*MizTrmNil*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l23__tmp.getTypeName());
					}
				};
				Module_loop.f_while(l20_$18, l21_$19);
				return l10_r;
			}
		}
	}
	public static final Struct_MizPreInst f_mizANInstType(Struct_MizEqualizer aeq_0, Struct_Vector aprim, int aenr, Struct_MizTyp aatyp) {
		final Struct_MizPreInst l0_r = Module_equalizer.f_mizANInstRadixType(aeq_0, aprim, aenr, aatyp);
		if ((Module_url_parameter.f_getUrlParameterDef("dbg-rus2", "")).equals(Module_string.f_i2s(aenr))) {
			Module_runtime.f_println(((("     ITY radix -> "+((l0_r).f_top?"top":Module_string.f_i2s(Module_prechecker.f_mizPreInstCount(l0_r))))+" lowerN=")+Module_string.f_i2s(Module_vector.f_sizeVector(((aatyp).f_lower).f_items))));
		} else {
		}
		Module_equalizer.f_mizANJoinWith(l0_r, Module_equalizer.f_mizANInstAttrs(aeq_0, aprim, aenr, (aatyp).f_lower));
		return l0_r;
	}
	public static final Struct_MizPreInst f_mizANJoinArgs(Struct_MizEqualizer aeq_0, Struct_Vector aprim, Struct ael0, Struct aal0) {
		final Struct_MizPreInst l0_r = Module_prechecker.f_mizPreInstTop();
		final Reference<Struct> l1_el = ((Reference<Struct>)(new Reference(ael0)));
		final Reference<Struct> l2_al = ((Reference<Struct>)(new Reference(aal0)));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			final Struct l6_gsymswitch43 = l2_al.value;
			Struct l7__tmp = l6_gsymswitch43;
			switch (l7__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				return ((Boolean)false);
			}
			default: {
				return ((Boolean)true);
			}
			}
		};
		final Struct_MizEqualizer l7_eq_0 = aeq_0;
		final Struct_Vector l8_prim = aprim;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			final Struct l7_gsymswitch44 = l2_al.value;
			Struct l8__tmp = l7_gsymswitch44;
			switch (l8__tmp.getTypeId()) {
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l9__tmp = (Struct_MizTrmElem)l8__tmp;
				final Struct l10_at = l9__tmp.f_trm;
				final Struct l11_an = l9__tmp.f_next;
				final Struct l12_at = (l9__tmp).f_trm;
				final Struct l13_an = (l9__tmp).f_next;
				final Struct l14_gsymswitch45 = l1_el.value;
				Struct l15__tmp = l14_gsymswitch45;
				switch (l15__tmp.getTypeId()) {
				case 70/*MizTrmElem*/: {
					final Struct_MizTrmElem l16__tmp = (Struct_MizTrmElem)l15__tmp;
					final Struct l17_et = l16__tmp.f_trm;
					final Struct l18_en = l16__tmp.f_next;
					final Struct l19_et = (l16__tmp).f_trm;
					final Struct l20_en = (l16__tmp).f_next;
					Module_equalizer.f_mizANJoinWith(l0_r, Module_equalizer.f_mizANInstTerm(l7_eq_0, l8_prim, l19_et, l12_at));
					l1_el.value = l20_en;
					break;
				}
				case 71/*MizTrmNil*/: {
					break;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l15__tmp.getTypeName());
				}
				l2_al.value = l13_an;
				return null;
			}
			case 71/*MizTrmNil*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l8__tmp.getTypeName());
			}
		};
		Module_loop.f_while(l5_$3, l6_$4);
		return l0_r;
	}
	public static final Object f_mizANJoinWith(Struct_MizPreInst ap, Struct_MizPreInst aq) {
		if ((aq).f_top) {
			return null;
		} else {
			if ((ap).f_top) {
				((Field_top)ap).set_top(false);
				Module_vector.f_clearVector((ap).f_evals);
				final Struct_Vector l2_$1 = (aq).f_evals;
				final Struct_MizPreInst l4_p = ap;
				final Func1<Object,Struct_MizNatFunc> l3_$0 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
					return Module_vector.f_pushVector((l4_p).f_evals, ae);
				};
				return Module_vector.f_iterVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
			} else {
				if ((Module_vector.f_sizeVector((aq).f_evals)==0)) {
					return Module_vector.f_clearVector((ap).f_evals);
				} else {
					if ((Module_vector.f_sizeVector((ap).f_evals)==0)) {
						return null;
					} else {
						final Struct_Vector l5_tmp = Module_vector.f_makeVector((Module_vector.f_sizeVector((ap).f_evals)*Module_vector.f_sizeVector((aq).f_evals)));
						final Struct_Vector l8_$7 = (ap).f_evals;
						final Struct_MizPreInst l10_q = aq;
						final Func1<Object,Struct_MizNatFunc> l9_$6 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc aa) -> {
							final Struct_Vector l12_$11 = (l10_q).f_evals;
							final Func1<Object,Struct_MizNatFunc> l13_$10 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ab) -> {
								final Struct l14_gsymswitch36 = Module_mobjects.f_mizNatJoinAtom(aa, ab);
								Struct l15__tmp = l14_gsymswitch36;
								switch (l15__tmp.getTypeId()) {
								case 90/*Some*/: {
									final Struct_Some l16__tmp = (Struct_Some)l15__tmp;
									final Object l17_j = l16__tmp.f_value;
									final Struct_MizNatFunc l18_j = ((Struct_MizNatFunc)(l16__tmp).f_value);
									return Module_vector.f_pushVector(l5_tmp, l18_j);
								}
								case 82/*None*/: {
									return null;
								}
								default:
									throw new RuntimeException("Unexpected struct in switch: "+l15__tmp.getTypeName());
								}
							};
							return Module_vector.f_iterVector(l12_$11, ((Func1<Object,Object>)(Func1)l13_$10));
						};
						Module_vector.f_iterVector(l8_$7, ((Func1<Object,Object>)(Func1)l9_$6));
						Module_vector.f_clearVector((ap).f_evals);
						final Struct_MizPreInst l13_p = ap;
						final Func1<Object,Struct_MizNatFunc> l12_$11 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
							return Module_vector.f_pushVector((l13_p).f_evals, ae);
						};
						return Module_vector.f_iterVector(l5_tmp, ((Func1<Object,Object>)(Func1)l12_$11));
					}
				}
			}
		}
	}
	public static final Struct f_mizANLocateTerm(Struct_MizEqualizer aeq_0, Struct aaTrm) {
		final int l0_aSort = Module_correl.f_mizTrmSort(aaTrm);
		if ((l0_aSort==Module_lexicon.g_ikTrmLocus)) {
			final Struct_MizVarTrm l1_av = Module_equalizer.f_mizEqAsVar(aaTrm);
			if (Module_mobjects.f_mizNatHasInDom((aeq_0).f_anInst, (l1_av).f_varNr)) {
				final int l2_g = Module_mobjects.f_mizNatValue((aeq_0).f_anInst, (l1_av).f_varNr);
				final Reference<Struct> l3_found = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
				final Reference<Integer> l4_i = ((Reference<Integer>)(new Reference(1)));
				final Struct_MizEqualizer l8_eq_0 = aeq_0;
				final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
					return ((Boolean)((((int)l4_i.value)<=Module_equalizer.f_mizTrmNbr(l8_eq_0))&&((Struct)l3_found.value).getTypeId() == 82));
				};
				final Struct_MizEqualizer l10_eq_0 = aeq_0;
				final Func0<Object> l9_$6 = (Func0<Object>)() -> {
					final Struct_MizVarTrm l10_rep = Module_equalizer.f_mizEqAsVar((Module_equalizer.f_mizTrmSAt(l10_eq_0, ((int)l4_i.value))).f_term);
					if (((l10_rep).f_varNr==l2_g)) {
						((Reference<Struct_Some>)(Reference)l3_found).value = (new Struct_Some((Module_equalizer.f_mizTrmSAt(l10_eq_0, ((int)l4_i.value))).f_term));
					} else {
					}
					l4_i.value = ((Integer)(((int)l4_i.value)+1));
					return null;
				};
				Module_loop.f_while(l7_$5, l9_$6);
				return l3_found.value;
			} else {
				return ((Struct)SingletonStructs.str_None);
			}
		} else {
			if ((l0_aSort==Module_lexicon.g_ikTrmInfConst)) {
				final Struct_MizVarTrm l11_av = Module_equalizer.f_mizEqAsVar(aaTrm);
				final Reference<Struct> l12_found = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
				final Reference<Integer> l13_i = ((Reference<Integer>)(new Reference(1)));
				final Struct_MizEqualizer l17_eq_0 = aeq_0;
				final Func0<Boolean> l16_$14 = (Func0<Boolean>)() -> {
					return ((Boolean)((((int)l13_i.value)<=Module_equalizer.f_mizTrmNbr(l17_eq_0))&&((Struct)l12_found.value).getTypeId() == 82));
				};
				final Struct_MizEqualizer l19_eq_0 = aeq_0;
				final Func0<Object> l18_$15 = (Func0<Object>)() -> {
					final Reference<Struct> l19_cur = ((Reference<Struct>)(new Reference((Module_equalizer.f_mizTrmSAt(l19_eq_0, ((int)l13_i.value))).f_eqClass)));
					final Func0<Boolean> l22_$20 = (Func0<Boolean>)() -> {
						boolean l25_$24;
						if (((Struct)l12_found.value).getTypeId() == 82) {
							final Struct l26_gsymswitch61 = l19_cur.value;
							Struct l27__tmp = l26_gsymswitch61;
							switch (l27__tmp.getTypeId()) {
							case 71/*MizTrmNil*/: {
								l25_$24=false;
								break;
							}
							default: {
								l25_$24=true;
								break;
							}
							}
						} else {
							l25_$24=false;
						}
						return ((Boolean)l25_$24);
					};
					final Func0<Object> l23_$21 = (Func0<Object>)() -> {
						final Struct l24_gsymswitch62 = l19_cur.value;
						Struct l25__tmp = l24_gsymswitch62;
						switch (l25__tmp.getTypeId()) {
						case 70/*MizTrmElem*/: {
							final Struct_MizTrmElem l26__tmp = (Struct_MizTrmElem)l25__tmp;
							final Struct l27_mem = l26__tmp.f_trm;
							final Struct l28_next = l26__tmp.f_next;
							final Struct l29_mem = (l26__tmp).f_trm;
							final Struct l30_next = (l26__tmp).f_next;
							boolean l31_hit;
							Struct l32__tmp = l29_mem;
							switch (l32__tmp.getTypeId()) {
							case 77/*MizVarTrm*/: {
								final Struct_MizVarTrm l33__tmp = (Struct_MizVarTrm)l32__tmp;
								final int l34_srt = l33__tmp.f_sort;
								final int l35_varNr = l33__tmp.f_varNr;
								final int l36_srt = (l33__tmp).f_sort;
								final int l37_varNr = (l33__tmp).f_varNr;
								final int l38___ = (l33__tmp).f_pattNr;
								final int l39___ = (l33__tmp).f_trmInfo;
								l31_hit=((l36_srt==Module_lexicon.g_ikTrmInfConst)&&(l37_varNr==(l11_av).f_varNr));
								break;
							}
							default: {
								l31_hit=false;
								break;
							}
							}
							if (l31_hit) {
								((Reference<Struct_Some>)(Reference)l12_found).value = (new Struct_Some((Module_equalizer.f_mizTrmSAt(l19_eq_0, ((int)l13_i.value))).f_term));
							} else {
							}
							l19_cur.value = l30_next;
							return null;
						}
						case 71/*MizTrmNil*/: {
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l25__tmp.getTypeName());
						}
					};
					Module_loop.f_while(l22_$20, l23_$21);
					l13_i.value = ((Integer)(((int)l13_i.value)+1));
					return null;
				};
				Module_loop.f_while(l16_$14, l18_$15);
				return l12_found.value;
			} else {
				if ((l0_aSort==Module_lexicon.g_ikTrmNumeral)) {
					final Struct_MizVarTrm l20_av = Module_equalizer.f_mizEqAsVar(aaTrm);
					final Reference<Struct> l21_found = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
					final Reference<Integer> l22_i = ((Reference<Integer>)(new Reference(1)));
					final Struct_MizEqualizer l26_eq_0 = aeq_0;
					final Func0<Boolean> l25_$23 = (Func0<Boolean>)() -> {
						return ((Boolean)((((int)l22_i.value)<=Module_equalizer.f_mizTrmNbr(l26_eq_0))&&((Struct)l21_found.value).getTypeId() == 82));
					};
					final Struct_MizEqualizer l28_eq_0 = aeq_0;
					final Func0<Object> l27_$24 = (Func0<Object>)() -> {
						final Struct_MizTrmSRec l28_rec = Module_equalizer.f_mizTrmSAt(l28_eq_0, ((int)l22_i.value));
						final Struct l29_gsymswitch60 = (l28_rec).f_eqClass;
						boolean l30_live;
						Struct l31__tmp = l29_gsymswitch60;
						switch (l31__tmp.getTypeId()) {
						case 71/*MizTrmNil*/: {
							l30_live=false;
							break;
						}
						default: {
							l30_live=true;
							break;
						}
						}
						if ((((l30_live&&(l28_rec).f_numDetermined)&&(((l28_rec).f_numValue).f_re==Module_math.f_i2d((l20_av).f_varNr)))&&(((l28_rec).f_numValue).f_im==0.0))) {
							((Reference<Struct_Some>)(Reference)l21_found).value = (new Struct_Some((l28_rec).f_term));
						} else {
						}
						l22_i.value = ((Integer)(((int)l22_i.value)+1));
						return null;
					};
					Module_loop.f_while(l25_$23, l27_$24);
					return l21_found.value;
				} else {
					if ((((l0_aSort==Module_lexicon.g_ikTrmFunctor)||(l0_aSort==Module_lexicon.g_ikTrmSelector))||(l0_aSort==Module_lexicon.g_ikTrmAggreg))) {
						final Struct_MizFuncTrm l29_af = Module_equalizer.f_mizEqAsFunc(aaTrm);
						final Reference<Struct> l30_found = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
						final Reference<Integer> l31_i = ((Reference<Integer>)(new Reference(1)));
						final Struct_MizEqualizer l35_eq_0 = aeq_0;
						final Func0<Boolean> l34_$32 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l31_i.value)<=Module_equalizer.f_mizTrmNbr(l35_eq_0))&&((Struct)l30_found.value).getTypeId() == 82));
						};
						final Struct_MizEqualizer l37_eq_0 = aeq_0;
						final Func0<Object> l36_$33 = (Func0<Object>)() -> {
							final Struct_MizTrmSRec l37_rec = Module_equalizer.f_mizTrmSAt(l37_eq_0, ((int)l31_i.value));
							final Reference<Struct> l38_cur = ((Reference<Struct>)(new Reference((l37_rec).f_eqClass)));
							final Func0<Boolean> l41_$39 = (Func0<Boolean>)() -> {
								boolean l44_$43;
								if (((Struct)l30_found.value).getTypeId() == 82) {
									final Struct l45_gsymswitch56 = l38_cur.value;
									Struct l46__tmp = l45_gsymswitch56;
									switch (l46__tmp.getTypeId()) {
									case 71/*MizTrmNil*/: {
										l44_$43=false;
										break;
									}
									default: {
										l44_$43=true;
										break;
									}
									}
								} else {
									l44_$43=false;
								}
								return ((Boolean)l44_$43);
							};
							final Func0<Object> l42_$40 = (Func0<Object>)() -> {
								final Struct l43_gsymswitch57 = l38_cur.value;
								Struct l44__tmp = l43_gsymswitch57;
								switch (l44__tmp.getTypeId()) {
								case 70/*MizTrmElem*/: {
									final Struct_MizTrmElem l45__tmp = (Struct_MizTrmElem)l44__tmp;
									final Struct l46_mem = l45__tmp.f_trm;
									final Struct l47_next = l45__tmp.f_next;
									final Struct l48_mem = (l45__tmp).f_trm;
									final Struct l49_next = (l45__tmp).f_next;
									if ((Module_correl.f_mizTrmSort(l48_mem)==l0_aSort)) {
										final Struct_MizFuncTrm l50_mf = Module_equalizer.f_mizEqAsFunc(l48_mem);
										if (((l50_mf).f_funcNr==(l29_af).f_funcNr)) {
											final Struct l51_gsymswitch58 = (l50_mf).f_args;
											boolean l52_noArgs;
											Struct l53__tmp = l51_gsymswitch58;
											switch (l53__tmp.getTypeId()) {
											case 71/*MizTrmNil*/: {
												l52_noArgs=true;
												break;
											}
											default: {
												l52_noArgs=false;
												break;
											}
											}
											if (l52_noArgs) {
												((Reference<Struct_Some>)(Reference)l30_found).value = (new Struct_Some((l37_rec).f_term));
											} else {
												final Struct l55_gsymswitch59 = Module_equalizer.f_mizANLocateTermList(l37_eq_0, (l29_af).f_args);
												Struct l56__tmp = l55_gsymswitch59;
												switch (l56__tmp.getTypeId()) {
												case 90/*Some*/: {
													final Struct_Some l57__tmp = (Struct_Some)l56__tmp;
													final Object l58_la = l57__tmp.f_value;
													final Struct l59_la = ((Struct)(l57__tmp).f_value);
													if (Module_equalizer.f_mizEqTrmListsE(l37_eq_0, l59_la, (l50_mf).f_args)) {
														((Reference<Struct_Some>)(Reference)l30_found).value = (new Struct_Some((l37_rec).f_term));
													} else {
													}
													break;
												}
												case 82/*None*/: {
													break;
												}
												default:
													throw new RuntimeException("Unexpected struct in switch: "+l56__tmp.getTypeName());
												}
											}
										} else {
										}
									} else {
									}
									l38_cur.value = l49_next;
									return null;
								}
								case 71/*MizTrmNil*/: {
									return null;
								}
								default:
									throw new RuntimeException("Unexpected struct in switch: "+l44__tmp.getTypeName());
								}
							};
							Module_loop.f_while(l41_$39, l42_$40);
							l31_i.value = ((Integer)(((int)l31_i.value)+1));
							return null;
						};
						Module_loop.f_while(l34_$32, l36_$33);
						return l30_found.value;
					} else {
						return ((Struct)SingletonStructs.str_None);
					}
				}
			}
		}
	}
	public static final Struct f_mizANLocateTermList(Struct_MizEqualizer aeq_0, Struct al0) {
		final Reference<Struct> l0_res = ((Reference<Struct>)(new Reference(Module_correl.g_mizNil)));
		final Reference<Boolean> l1_ok = ((Reference<Boolean>)(new Reference(true)));
		final Reference<Struct> l2_l = ((Reference<Struct>)(new Reference(al0)));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			boolean l8_$7;
			if (((boolean)l1_ok.value)) {
				final Struct l9_gsymswitch53 = l2_l.value;
				Struct l10__tmp = l9_gsymswitch53;
				switch (l10__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l8_$7=false;
					break;
				}
				default: {
					l8_$7=true;
					break;
				}
				}
			} else {
				l8_$7=false;
			}
			return ((Boolean)l8_$7);
		};
		final Struct_MizEqualizer l7_eq_0 = aeq_0;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			final Struct l7_gsymswitch54 = l2_l.value;
			Struct l8__tmp = l7_gsymswitch54;
			switch (l8__tmp.getTypeId()) {
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l9__tmp = (Struct_MizTrmElem)l8__tmp;
				final Struct l10_t = l9__tmp.f_trm;
				final Struct l11_next = l9__tmp.f_next;
				final Struct l12_t = (l9__tmp).f_trm;
				final Struct l13_next = (l9__tmp).f_next;
				final Struct l14_gsymswitch55 = Module_equalizer.f_mizANLocateTerm(l7_eq_0, l12_t);
				Struct l15__tmp = l14_gsymswitch55;
				switch (l15__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l16__tmp = (Struct_Some)l15__tmp;
					final Object l17_lt = l16__tmp.f_value;
					final Struct l18_lt = ((Struct)(l16__tmp).f_value);
					l0_res.value = Module_equalizer.f_mizAppendTrm(l0_res.value, l18_lt);
					break;
				}
				case 82/*None*/: {
					l1_ok.value = ((Boolean)false);
					break;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l15__tmp.getTypeName());
				}
				l2_l.value = l13_next;
				return null;
			}
			case 71/*MizTrmNil*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l8__tmp.getTypeName());
			}
		};
		Module_loop.f_while(l5_$3, l6_$4);
		if (((boolean)l1_ok.value)) {
			return (new Struct_Some(l0_res.value));
		} else {
			return ((Struct)SingletonStructs.str_None);
		}
	}
	public static final Struct f_mizANLocatedAttr(Struct_MizEqualizer aeq_0, Struct_MizAttr aa) {
		final Struct l0_gsymswitch63 = (aa).f_args;
		boolean l1_noArgs;
		Struct l2__tmp = l0_gsymswitch63;
		switch (l2__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			l1_noArgs=true;
			break;
		}
		default: {
			l1_noArgs=false;
			break;
		}
		}
		if (l1_noArgs) {
			return (new Struct_Some(Wrappers.w_mizCopyAttr.invoke(aa)));
		} else {
			final Struct l4_gsymswitch64 = Module_equalizer.f_mizANLocateTermList(aeq_0, (aa).f_args);
			Struct l5__tmp = l4_gsymswitch64;
			switch (l5__tmp.getTypeId()) {
			case 90/*Some*/: {
				final Struct_Some l6__tmp = (Struct_Some)l5__tmp;
				final Object l7_la = l6__tmp.f_value;
				final Struct l8_la = ((Struct)(l6__tmp).f_value);
				return (new Struct_Some((new Struct_MizAttr((aa).f_neg, (aa).f_attrNr, l8_la, (aa).f_collected, (aa).f_pattNr))));
			}
			case 82/*None*/: {
				return ((Struct)SingletonStructs.str_None);
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l5__tmp.getTypeName());
			}
		}
	}
	public static final Struct_MizAttrColl f_mizANLocatedCluster(Struct_MizEqualizer aeq_0, Struct_MizAttrColl aaCluster) {
		final Struct_MizAttrColl l0_r = Module_correl.f_mizNewEmptyCluster();
		final Struct_Vector l3_$2 = (aaCluster).f_items;
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func1<Object,Struct_MizAttr> l4_$1 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			final Struct l5_gsymswitch65 = Module_equalizer.f_mizANLocatedAttr(l5_eq_0, aa);
			Struct l6__tmp = l5_gsymswitch65;
			switch (l6__tmp.getTypeId()) {
			case 90/*Some*/: {
				final Struct_Some l7__tmp = (Struct_Some)l6__tmp;
				final Object l8_la = l7__tmp.f_value;
				final Struct_MizAttr l9_la = ((Struct_MizAttr)(l7__tmp).f_value);
				return Module_correl.f_mizAttrCollInsert((l5_eq_0).f_env, l0_r, l9_la);
			}
			case 82/*None*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l6__tmp.getTypeName());
			}
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		return l0_r;
	}
	public static final Object f_mizANUnionWith(Struct_MizPreInst ap, Struct_MizPreInst aq) {
		if ((ap).f_top) {
			return null;
		} else {
			if ((aq).f_top) {
				Module_vector.f_clearVector((ap).f_evals);
				((Field_top)ap).set_top(true);
				return null;
			} else {
				final Struct_Vector l2_$1 = (aq).f_evals;
				final Struct_MizPreInst l4_p = ap;
				final Func1<Object,Struct_MizNatFunc> l3_$0 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc ae) -> {
					return Module_prechecker.f_mizPreInstInsertAndAbsorb(l4_p, ae);
				};
				return Module_vector.f_iterVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
			}
		}
	}
	public static final Object f_mizAddEquality(Struct_Vector apend, int ax, int ay) {
		final Reference<Boolean> l0_dup = ((Reference<Boolean>)(new Reference(false)));
		final int l3_x = ax;
		final int l4_y = ay;
		final Func1<Object,Struct_MizIntPair> l2_$1 = (Func1<Object, Struct_MizIntPair>)(Struct_MizIntPair ap) -> {
			if ((((ap).f_x==l3_x)&&((ap).f_y==l4_y))) {
				l0_dup.value = ((Boolean)true);
				return null;
			} else {
				return null;
			}
		};
		Module_vector.f_iterVector(apend, ((Func1<Object,Object>)(Func1)l2_$1));
		if (!((boolean)l0_dup.value)) {
			return Module_vector.f_pushVector(apend, (new Struct_MizIntPair(ax, ay)));
		} else {
			return null;
		}
	}
	public static final Struct f_mizAdjustPredArgs(Struct_MizCorrelEnv aenv, int apredNr, Struct aargs) {
		final Struct_MizConstr l0_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coPredicate, apredNr);
		if (((l0_c).f_whichConstrNr!=0)) {
			return Module_correl.f_mizTrmListDrop(aargs, (l0_c).f_superfluous);
		} else {
			return aargs;
		}
	}
	public static final int f_mizAdjustedFuncNrE(Struct_MizCorrelEnv aenv, Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l2_srt = l1__tmp.f_sort;
			final int l3_funcNr = l1__tmp.f_funcNr;
			final int l4_srt = (l1__tmp).f_sort;
			final int l5_funcNr = (l1__tmp).f_funcNr;
			final Struct l6___ = (l1__tmp).f_args;
			final int l7___ = (l1__tmp).f_pattNr;
			final int l8___ = (l1__tmp).f_trmInfo;
			if ((l4_srt==Module_lexicon.g_ikTrmFunctor)) {
				final Struct_MizConstr l9_c = Module_correl.f_mizConstr(aenv, Module_correl.g_coFunctor, l5_funcNr);
				if (((l9_c).f_whichConstrNr==0)) {
					return l5_funcNr;
				} else {
					return (l9_c).f_whichConstrNr;
				}
			} else {
				return l5_funcNr;
			}
		}
		default: {
			return 0;
		}
		}
	}
	public static final Struct f_mizAllButLast(Struct al) {
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
			Struct l6__tmp = l5_next;
			switch (l6__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				return ((Struct)SingletonStructs.str_MizTrmNil);
			}
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l7__tmp = (Struct_MizTrmElem)l6__tmp;
				final Struct l8___ = (l7__tmp).f_trm;
				final Struct l9___ = (l7__tmp).f_next;
				return (new Struct_MizTrmElem(l4_trm, Module_equalizer.f_mizAllButLast(l7__tmp)));
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l6__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Object f_mizAllocByTerm(Struct_MizEqualizer aeq_0, int akind, Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l2_funcNr = l1__tmp.f_funcNr;
			final int l3___ = (l1__tmp).f_sort;
			final int l4_funcNr = (l1__tmp).f_funcNr;
			final Struct l5___ = (l1__tmp).f_args;
			final int l6___ = (l1__tmp).f_pattNr;
			final int l7___ = (l1__tmp).f_trmInfo;
			return Module_equalizer.f_mizFuncIdxAlloc(aeq_0, akind, l4_funcNr, l1__tmp);
		}
		case 61/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final int l8_funcNr = l1__tmp.f_funcNr;
			final int l9_funcNr = (l1__tmp).f_funcNr;
			final Struct l10___ = (l1__tmp).f_args;
			final Struct l11___ = (l1__tmp).f_funcExp;
			final int l12___ = (l1__tmp).f_pattNr;
			final int l13___ = (l1__tmp).f_trmInfo;
			return Module_equalizer.f_mizFuncIdxAlloc(aeq_0, akind, l9_funcNr, l1__tmp);
		}
		default: {
			return null;
		}
		}
	}
	public static final Struct f_mizAppendTrm(Struct al, Struct at) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			return (new Struct_MizTrmElem(at, ((Struct)SingletonStructs.str_MizTrmNil)));
		}
		case 70/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2___ = (l1__tmp).f_trm;
			final Struct l3___ = (l1__tmp).f_next;
			final Struct_MizTrmElem l4_last = Module_correl.f_mizLastElem(l1__tmp);
			((Field_next)l4_last).set_next((new Struct_MizTrmElem(at, ((Struct)SingletonStructs.str_MizTrmNil))));
			return l1__tmp;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_mizArgsOfE(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final Struct l2_args = l1__tmp.f_args;
			final int l3___ = (l1__tmp).f_sort;
			final int l4___ = (l1__tmp).f_funcNr;
			final Struct l5_args = (l1__tmp).f_args;
			final int l6___ = (l1__tmp).f_pattNr;
			final int l7___ = (l1__tmp).f_trmInfo;
			return l5_args;
		}
		case 61/*MizPrivFuncTrm*/: {
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
	public static final Struct_MizComplex f_mizCInvE(Struct_MizComplex aa) {
		final double l0_d = (((aa).f_re*(aa).f_re)+((aa).f_im*(aa).f_im));
		return (new Struct_MizComplex(((aa).f_re/l0_d), ((-(aa).f_im)/l0_d)));
	}
	public static final Struct_MizComplex f_mizCMul(Struct_MizComplex aa, Struct_MizComplex ab) {
		return (new Struct_MizComplex((((aa).f_re*(ab).f_re)-((aa).f_im*(ab).f_im)), (((aa).f_re*(ab).f_im)+((aa).f_im*(ab).f_re))));
	}
	public static final Object f_mizChChangeBoundE(Struct_MizEqualizer aeq_0, Struct at) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		if (((l0_env).f_boundVarNbr>0)) {
			final Func2<Struct,Struct_MizWithin, Struct> l2_$1 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al3_0, Struct atrm) -> {
				Struct l4__tmp = atrm;
				switch (l4__tmp.getTypeId()) {
				case 77/*MizVarTrm*/: {
					final Struct_MizVarTrm l5__tmp = (Struct_MizVarTrm)l4__tmp;
					final int l6_s2 = l5__tmp.f_sort;
					final int l7_v2 = l5__tmp.f_varNr;
					final int l8_s2 = (l5__tmp).f_sort;
					final int l9_v2 = (l5__tmp).f_varNr;
					final int l10___ = (l5__tmp).f_pattNr;
					final int l11___ = (l5__tmp).f_trmInfo;
					if (((l8_s2==Module_lexicon.g_ikTrmBound)&&(l9_v2>(l0_env).f_boundVarNbr))) {
						final Struct_MizVarTrm l12_vt = Module_equalizer.f_mizEqAsVar(l5__tmp);
						((Field_varNr)l12_vt).set_varNr((l9_v2-(l0_env).f_boundVarNbr));
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
	public static final int f_mizClassOf(Struct_MizEqualizer aeq_0, int ai) {
		return Module_equalizer.f_mizTrmInfoOf((Module_equalizer.f_mizTrmSAt(aeq_0, ai)).f_term);
	}
	public static final Object f_mizClearPolynomialValues(Struct_MizEqualizer aeq_0) {
		final Reference<Integer> l0_t = ((Reference<Integer>)(new Reference(1)));
		final Struct_MizEqualizer l4_eq_0 = aeq_0;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return (((int)l0_t.value)<=Module_equalizer.f_mizTrmNbr(l4_eq_0));
		};
		final Struct_MizEqualizer l6_eq_0 = aeq_0;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			final Struct_MizTrmSRec l6_rec = Module_equalizer.f_mizTrmSAt(l6_eq_0, ((int)l0_t.value));
			final int l7_repNr = Module_equalizer.f_mizClassOf(l6_eq_0, ((int)l0_t.value));
			if (((l7_repNr!=((int)l0_t.value))&&(Module_vector.f_sizeVector((Module_equalizer.f_mizTrmSAt(l6_eq_0, l7_repNr)).f_polynomialValues)>0))) {
				Module_equalizer.f_mizSubstVarP(l6_eq_0, ((int)l0_t.value), l7_repNr);
			} else {
			}
			l0_t.value = ((Integer)(((int)l0_t.value)+1));
			return null;
		};
		return Module_loop.f_while(l3_$1, l5_$2);
	}
	public static final int f_mizCompRdTrmsPub(Struct_MizCorrelEnv aenv, Struct aa, Struct ab) {
		final boolean l0_saved = (aenv).f_strictCompare;
		((Field_strictCompare)aenv).set_strictCompare(false);
		final int l1_c = Module_correl.f_mizCompTrms(aenv, aa, ab);
		((Field_strictCompare)aenv).set_strictCompare(l0_saved);
		return l1_c;
	}
	public static final Struct f_mizConcatTrmLists(Struct aa, Struct ab) {
		Struct l0__tmp = aa;
		switch (l0__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			return ab;
		}
		case 70/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2___ = (l1__tmp).f_trm;
			final Struct l3___ = (l1__tmp).f_next;
			final Struct_MizTrmElem l4_last = Module_correl.f_mizLastElem(l1__tmp);
			((Field_next)l4_last).set_next(ab);
			return l1__tmp;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Object f_mizContradictionVerify(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final Reference<Integer> l1_i = ((Reference<Integer>)(new Reference(0)));
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l5_eq_0).f_contr==0)&&(((int)l1_i.value)<Module_vector.f_sizeVector((l5_eq_0).f_negBas))));
		};
		final Struct_MizEqualizer l7_eq_0 = aeq_0;
		final Func0<Object> l6_$3 = (Func0<Object>)() -> {
			final Struct l7_nf = ((Struct)Module_vector.f_getVectorUnsafe((l7_eq_0).f_negBas, ((int)l1_i.value)));
			Struct l8__tmp = l7_nf;
			switch (l8__tmp.getTypeId()) {
			case 58/*MizPredFrm*/: {
				final Struct_MizPredFrm l9__tmp = (Struct_MizPredFrm)l8__tmp;
				final int l10_srt = l9__tmp.f_sort;
				final int l11_predNr = l9__tmp.f_predNr;
				final Struct l12_args = l9__tmp.f_args;
				final int l13_srt = (l9__tmp).f_sort;
				final int l14_predNr = (l9__tmp).f_predNr;
				final Struct l15_args = (l9__tmp).f_args;
				final int l16___ = (l9__tmp).f_pattNr;
				if ((l13_srt==Module_lexicon.g_ikFrmAttr)) {
					final Struct l17_la = Module_equalizer.f_mizGetAttr(l7_eq_0, (Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_equalizer.f_mizLastArgE(l15_args)))).f_superCluster, l14_predNr, Module_equalizer.f_mizAllButLast(l15_args));
					Struct l18__tmp = l17_la;
					switch (l18__tmp.getTypeId()) {
					case 90/*Some*/: {
						final Struct_Some l19__tmp = (Struct_Some)l18__tmp;
						final Object l20_at = l19__tmp.f_value;
						final Struct_MizAttr l21_at = ((Struct_MizAttr)(l19__tmp).f_value);
						if (((l21_at).f_neg==1)) {
							Module_equalizer.f_mizSetContr(l7_eq_0, 18);
						} else {
						}
						break;
					}
					case 82/*None*/: {
						break;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l18__tmp.getTypeName());
					}
				} else {
					if ((l13_srt==Module_lexicon.g_ikFrmPred)) {
						final Struct_MizConstr l22_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, l14_predNr);
						if (Module_correl.f_mizHasProperty(l22_c, Module_correl.g_syReflexivity)) {
							final Struct_Pair l23_p2 = Module_equalizer.f_mizGetArgs2(l15_args, (l22_c).f_firstArg, (l22_c).f_secondArg);
							if ((Module_equalizer.f_mizTrmInfoOf(((Struct)(l23_p2).f_first))==Module_equalizer.f_mizTrmInfoOf(((Struct)(l23_p2).f_second)))) {
								Module_equalizer.f_mizSetContr(l7_eq_0, 19);
							} else {
							}
						} else {
						}
					} else {
					}
				}
				if (((l7_eq_0).f_contr==0)) {
					final Struct_Vector l26_$25 = (l7_eq_0).f_posBas;
					final Func1<Object,Struct> l27_$24 = (Func1<Object, Struct>)(Struct apf) -> {
						if ((((l7_eq_0).f_contr==0)&&Module_equalizer.f_mizEqFrmsE(l7_eq_0, apf, l9__tmp))) {
							return Module_equalizer.f_mizSetContr(l7_eq_0, 20);
						} else {
							return null;
						}
					};
					Module_vector.f_iterVector(l26_$25, ((Func1<Object,Object>)(Func1)l27_$24));
				} else {
				}
				break;
			}
			case 63/*MizQualFrm*/: {
				final Struct_MizQualFrm l9__tmp = (Struct_MizQualFrm)l8__tmp;
				final Struct l28_trm = l9__tmp.f_trm;
				final Struct_MizTyp l29_typ = l9__tmp.f_typ;
				final Struct l30_trm = (l9__tmp).f_trm;
				final Struct_MizTyp l31_typ = (l9__tmp).f_typ;
				final int l32___ = (l9__tmp).f_pattNr;
				final Struct_MizTrmSRec l33_rec = Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l30_trm));
				final Struct_Vector l36_$35 = (l33_rec).f_xTypClass;
				final Func1<Object,Struct_MizTyp> l37_$34 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
					if ((((l7_eq_0).f_contr==0)&&Module_correl_inst.f_mizEqRadices(l0_env, l31_typ, axt))) {
						return Module_equalizer.f_mizSetContr(l7_eq_0, 21);
					} else {
						return null;
					}
				};
				Module_vector.f_iterVector(l36_$35, ((Func1<Object,Object>)(Func1)l37_$34));
				break;
			}
			default: {
				break;
			}
			}
			l1_i.value = ((Integer)(((int)l1_i.value)+1));
			return null;
		};
		return Module_loop.f_while(l4_$2, l6_$3);
	}
	public static final Struct_MizChoiceTrm f_mizEqAsChoice(Struct at) {
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
	public static final Struct_MizConjFrm f_mizEqAsConj(Struct af) {
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
	public static final Struct_MizFraenkelTrm f_mizEqAsFraenkel(Struct at) {
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
	public static final Struct_MizFuncTrm f_mizEqAsFunc(Struct at) {
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
	public static final Struct_MizLocPredFrm f_mizEqAsLocPred(Struct af) {
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
	public static final Struct_MizNegFrm f_mizEqAsNeg(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 52/*MizNegFrm*/: {
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
	public static final Struct_MizPredFrm f_mizEqAsPred(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 58/*MizPredFrm*/: {
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
	public static final Struct_MizPrivFuncTrm f_mizEqAsPriv(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 61/*MizPrivFuncTrm*/: {
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
	public static final Struct_MizQualFrm f_mizEqAsQual(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 63/*MizQualFrm*/: {
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
	public static final Struct_MizUnivFrm f_mizEqAsUniv(Struct af) {
		Struct l0__tmp = af;
		switch (l0__tmp.getTypeId()) {
		case 76/*MizUnivFrm*/: {
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
	public static final Struct_MizVarTrm f_mizEqAsVar(Struct at) {
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
	public static final boolean f_mizEqAttrsE(Struct_MizEqualizer aeq_0, Struct_MizAttr aa, Struct_MizAttr ab) {
		final Struct_Pair l0_pa = Module_correl.f_mizAdjustAttr((aeq_0).f_env, aa);
		final Struct_Pair l1_pb = Module_correl.f_mizAdjustAttr((aeq_0).f_env, ab);
		return (((((int)(l0_pa).f_first)==((int)(l1_pb).f_first))&&((aa).f_neg==(ab).f_neg))&&Module_equalizer.f_mizEqTrmListsE(aeq_0, ((Struct)(l0_pa).f_second), ((Struct)(l1_pb).f_second)));
	}
	public static final boolean f_mizEqButLast(Struct_MizEqualizer aeq_0, Struct aa0, Struct ab0) {
		Struct l0__tmp = aa0;
		switch (l0__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			return false;
		}
		case 70/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_ta = l1__tmp.f_trm;
			final Struct l3_na = l1__tmp.f_next;
			final Struct l4_ta = (l1__tmp).f_trm;
			final Struct l5_na = (l1__tmp).f_next;
			Struct l6__tmp = ab0;
			switch (l6__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				return false;
			}
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l7__tmp = (Struct_MizTrmElem)l6__tmp;
				final Struct l8_tb = l7__tmp.f_trm;
				final Struct l9_nb = l7__tmp.f_next;
				final Struct l10_tb = (l7__tmp).f_trm;
				final Struct l11_nb = (l7__tmp).f_next;
				boolean l12_lastA;
				Struct l13__tmp = l5_na;
				switch (l13__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l12_lastA=true;
					break;
				}
				default: {
					l12_lastA=false;
					break;
				}
				}
				boolean l15_lastB;
				Struct l16__tmp = l11_nb;
				switch (l16__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l15_lastB=true;
					break;
				}
				default: {
					l15_lastB=false;
					break;
				}
				}
				if ((l12_lastA||l15_lastB)) {
					return (l12_lastA&&l15_lastB);
				} else {
					return (Module_equalizer.f_mizEqTrms(aeq_0, l4_ta, l10_tb)&&Module_equalizer.f_mizEqButLast(aeq_0, l5_na, l11_nb));
				}
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l6__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final boolean f_mizEqFrmsE(Struct_MizEqualizer aeq_0, Struct aa, Struct ab) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_sa = Module_correl.f_mizFrmSort(aa);
		if ((l1_sa!=Module_correl.f_mizFrmSort(ab))) {
			return false;
		} else {
			if ((((l1_sa==Module_lexicon.g_ikFrmVerum)||(l1_sa==Module_lexicon.g_ikFrmThesis))||(l1_sa==Module_lexicon.g_ikError))) {
				return true;
			} else {
				Struct l2__tmp = aa;
				switch (l2__tmp.getTypeId()) {
				case 52/*MizNegFrm*/: {
					final Struct_MizNegFrm l3__tmp = (Struct_MizNegFrm)l2__tmp;
					final Struct l4_arg = l3__tmp.f_arg;
					final Struct l5_arg = (l3__tmp).f_arg;
					final int l6___ = (l3__tmp).f_pattNr;
					final Struct_MizNegFrm l7_nb = Module_equalizer.f_mizEqAsNeg(ab);
					{
						final Struct l8___tmp = l5_arg;
						final Struct l9___tmp = (l7_nb).f_arg;
						aa = l8___tmp;
						ab = l9___tmp;
						continue TAIL_CALL;
					}
				}
				case 63/*MizQualFrm*/: {
					final Struct_MizQualFrm l3__tmp = (Struct_MizQualFrm)l2__tmp;
					final Struct l10_trm = l3__tmp.f_trm;
					final Struct_MizTyp l11_typ = l3__tmp.f_typ;
					final Struct l12_trm = (l3__tmp).f_trm;
					final Struct_MizTyp l13_typ = (l3__tmp).f_typ;
					final int l14___ = (l3__tmp).f_pattNr;
					final Struct_MizQualFrm l15_qb = Module_equalizer.f_mizEqAsQual(ab);
					return (Module_equalizer.f_mizEqTrms(aeq_0, l12_trm, (l15_qb).f_trm)&&Module_equalizer.f_mizEqTypsE(aeq_0, l13_typ, (l15_qb).f_typ));
				}
				case 26/*MizConjFrm*/: {
					final Struct_MizConjFrm l3__tmp = (Struct_MizConjFrm)l2__tmp;
					final Struct_Vector l16_conjuncts = l3__tmp.f_conjuncts;
					final Struct_Vector l17_conjuncts = (l3__tmp).f_conjuncts;
					final int l18___ = (l3__tmp).f_pattNr;
					final Struct_MizConjFrm l19_cb = Module_equalizer.f_mizEqAsConj(ab);
					if ((Module_vector.f_sizeVector(l17_conjuncts)!=Module_vector.f_sizeVector((l19_cb).f_conjuncts))) {
						return false;
					} else {
						final Reference<Boolean> l20_ok = ((Reference<Boolean>)(new Reference(true)));
						final Reference<Integer> l21_i = ((Reference<Integer>)(new Reference(0)));
						final Func0<Boolean> l24_$22 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l21_i.value)<Module_vector.f_sizeVector(l17_conjuncts))&&((boolean)l20_ok.value)));
						};
						final Struct_MizEqualizer l26_eq_0 = aeq_0;
						final Func0<Object> l25_$23 = (Func0<Object>)() -> {
							if (!Module_equalizer.f_mizEqFrmsE(l26_eq_0, ((Struct)Module_vector.f_getVectorUnsafe(l17_conjuncts, ((int)l21_i.value))), ((Struct)Module_vector.f_getVectorUnsafe((l19_cb).f_conjuncts, ((int)l21_i.value))))) {
								l20_ok.value = ((Boolean)false);
							} else {
							}
							l21_i.value = ((Integer)(((int)l21_i.value)+1));
							return null;
						};
						Module_loop.f_while(l24_$22, l25_$23);
						return ((boolean)l20_ok.value);
					}
				}
				case 58/*MizPredFrm*/: {
					final Struct_MizPredFrm l3__tmp = (Struct_MizPredFrm)l2__tmp;
					final int l27_srt = l3__tmp.f_sort;
					final int l28_predNr = l3__tmp.f_predNr;
					final Struct l29_args = l3__tmp.f_args;
					final int l30_srt = (l3__tmp).f_sort;
					final int l31_predNr = (l3__tmp).f_predNr;
					final Struct l32_args = (l3__tmp).f_args;
					final int l33___ = (l3__tmp).f_pattNr;
					final Struct_MizPredFrm l34_pb = Module_equalizer.f_mizEqAsPred(ab);
					if ((l30_srt==Module_lexicon.g_ikFrmSchPred)) {
						return ((l31_predNr==(l34_pb).f_predNr)&&Module_equalizer.f_mizEqTrmListsE(aeq_0, l32_args, (l34_pb).f_args));
					} else {
						if ((l30_srt==Module_lexicon.g_ikFrmAttr)) {
							final Struct_MizPredFrm l35_pa2 = Module_equalizer.f_mizEqAsPred(l3__tmp);
							final Struct_Pair l36_qa = Module_correl.f_mizAdjustAttrFrm(l0_env, l35_pa2);
							final Struct_Pair l37_qb = Module_correl.f_mizAdjustAttrFrm(l0_env, l34_pb);
							return ((((int)(l36_qa).f_first)==((int)(l37_qb).f_first))&&Module_equalizer.f_mizEqTrmListsE(aeq_0, ((Struct)(l36_qa).f_second), ((Struct)(l37_qb).f_second)));
						} else {
							final Struct_MizPredFrm l38_pa3 = Module_equalizer.f_mizEqAsPred(l3__tmp);
							final Struct_Pair l39_qa = Module_correl.f_mizAdjustFrm(l0_env, l38_pa3);
							final Struct_Pair l40_qb = Module_correl.f_mizAdjustFrm(l0_env, l34_pb);
							if ((((int)(l39_qa).f_first)!=((int)(l40_qb).f_first))) {
								return false;
							} else {
								if (Module_equalizer.f_mizEqTrmListsE(aeq_0, ((Struct)(l39_qa).f_second), ((Struct)(l40_qb).f_second))) {
									return true;
								} else {
									final Struct_MizConstr l41_c1 = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, (l38_pa3).f_predNr);
									if (Module_correl.f_mizHasProperty(l41_c1, Module_correl.g_sySymmetry)) {
										final Struct l42_sw = Module_roundcl.f_mizSwapArguments((l38_pa3).f_args, (l41_c1).f_firstArg, (l41_c1).f_secondArg);
										final Struct l43_swAdj = Module_equalizer.f_mizAdjustPredArgs(l0_env, (l38_pa3).f_predNr, l42_sw);
										return Module_equalizer.f_mizEqTrmListsE(aeq_0, l43_swAdj, ((Struct)(l40_qb).f_second));
									} else {
										final Struct_MizConstr l44_c2 = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, (l34_pb).f_predNr);
										if (Module_correl.f_mizHasProperty(l44_c2, Module_correl.g_sySymmetry)) {
											final Struct l45_sw = Module_roundcl.f_mizSwapArguments((l34_pb).f_args, (l44_c2).f_firstArg, (l44_c2).f_secondArg);
											final Struct l46_swAdj = Module_equalizer.f_mizAdjustPredArgs(l0_env, (l34_pb).f_predNr, l45_sw);
											return Module_equalizer.f_mizEqTrmListsE(aeq_0, ((Struct)(l39_qa).f_second), l46_swAdj);
										} else {
											return false;
										}
									}
								}
							}
						}
					}
				}
				case 49/*MizLocPredFrm*/: {
					final Struct_MizLocPredFrm l3__tmp = (Struct_MizLocPredFrm)l2__tmp;
					final int l47_predNr = l3__tmp.f_predNr;
					final Struct l48_args = l3__tmp.f_args;
					final int l49_predNr = (l3__tmp).f_predNr;
					final Struct l50_args = (l3__tmp).f_args;
					final Struct l51___ = (l3__tmp).f_predExp;
					final int l52___ = (l3__tmp).f_pattNr;
					final Struct_MizLocPredFrm l53_lb = Module_equalizer.f_mizEqAsLocPred(ab);
					return ((l49_predNr==(l53_lb).f_predNr)&&Module_equalizer.f_mizEqTrmListsE(aeq_0, l50_args, (l53_lb).f_args));
				}
				case 76/*MizUnivFrm*/: {
					final Struct_MizUnivFrm l3__tmp = (Struct_MizUnivFrm)l2__tmp;
					final Struct_MizTyp l54_quantified = l3__tmp.f_quantified;
					final Struct l55_scope = l3__tmp.f_scope;
					final int l56___ = (l3__tmp).f_sort;
					final int l57___ = (l3__tmp).f_varId;
					final Struct_MizTyp l58_quantified = (l3__tmp).f_quantified;
					final Struct l59_scope = (l3__tmp).f_scope;
					final int l60___ = (l3__tmp).f_pattNr;
					final Struct_MizUnivFrm l61_ub = Module_equalizer.f_mizEqAsUniv(ab);
					return (Module_equalizer.f_mizEqTypsE(aeq_0, l58_quantified, (l61_ub).f_quantified)&&Module_equalizer.f_mizEqFrmsE(aeq_0, l59_scope, (l61_ub).f_scope));
				}
				default: {
					Module_errhan.f_mizRunTimeError(2021);
					return false;
				}
				}
			}
		}
	}
	}
	public static final boolean f_mizEqTrmListsE(Struct_MizEqualizer aeq_0, Struct aa, Struct ab) {
		Struct l0__tmp = aa;
		switch (l0__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			Struct l2__tmp = ab;
			switch (l2__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				return true;
			}
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l3__tmp = (Struct_MizTrmElem)l2__tmp;
				final Struct l4___ = (l3__tmp).f_trm;
				final Struct l5___ = (l3__tmp).f_next;
				return false;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
			}
		}
		case 70/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l6_ta = l1__tmp.f_trm;
			final Struct l7_na = l1__tmp.f_next;
			final Struct l8_ta = (l1__tmp).f_trm;
			final Struct l9_na = (l1__tmp).f_next;
			Struct l10__tmp = ab;
			switch (l10__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				return false;
			}
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l11__tmp = (Struct_MizTrmElem)l10__tmp;
				final Struct l12_tb = l11__tmp.f_trm;
				final Struct l13_nb = l11__tmp.f_next;
				final Struct l14_tb = (l11__tmp).f_trm;
				final Struct l15_nb = (l11__tmp).f_next;
				return (Module_equalizer.f_mizEqTrms(aeq_0, l8_ta, l14_tb)&&Module_equalizer.f_mizEqTrmListsE(aeq_0, l9_na, l15_nb));
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final boolean f_mizEqTrms(Struct_MizEqualizer aeq_0, Struct aa, Struct ab) {
		final int l0_ia = Module_equalizer.f_mizTrmInfoOf(aa);
		if ((l0_ia!=0)) {
			return (l0_ia==Module_equalizer.f_mizTrmInfoOf(ab));
		} else {
			if ((Module_correl.f_mizTrmSort(aa)!=Module_correl.f_mizTrmSort(ab))) {
				return false;
			} else {
				Struct l1__tmp = aa;
				switch (l1__tmp.getTypeId()) {
				case 77/*MizVarTrm*/: {
					final Struct_MizVarTrm l2__tmp = (Struct_MizVarTrm)l1__tmp;
					final int l3_varNr = l2__tmp.f_varNr;
					final int l4___ = (l2__tmp).f_sort;
					final int l5_varNr = (l2__tmp).f_varNr;
					final int l6___ = (l2__tmp).f_pattNr;
					final int l7___ = (l2__tmp).f_trmInfo;
					final Struct_MizVarTrm l8_vb = Module_equalizer.f_mizEqAsVar(ab);
					return (l5_varNr==(l8_vb).f_varNr);
				}
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l2__tmp = (Struct_MizFuncTrm)l1__tmp;
					final int l9_srt = l2__tmp.f_sort;
					final int l10_funcNr = l2__tmp.f_funcNr;
					final Struct l11_args = l2__tmp.f_args;
					final int l12_srt = (l2__tmp).f_sort;
					final int l13_funcNr = (l2__tmp).f_funcNr;
					final Struct l14_args = (l2__tmp).f_args;
					final int l15___ = (l2__tmp).f_pattNr;
					final int l16___ = (l2__tmp).f_trmInfo;
					final Struct_MizFuncTrm l17_fb = Module_equalizer.f_mizEqAsFunc(ab);
					if ((l12_srt==Module_lexicon.g_ikTrmFunctor)) {
						final Struct_MizFuncTrm l18_fa = Module_equalizer.f_mizEqAsFunc(l2__tmp);
						final Struct_Pair l19_pa = Module_correl.f_mizAdjustTrm((aeq_0).f_env, l18_fa);
						final Struct_Pair l20_pb = Module_correl.f_mizAdjustTrm((aeq_0).f_env, l17_fb);
						return ((((int)(l19_pa).f_first)==((int)(l20_pb).f_first))&&Module_equalizer.f_mizEqTrmListsE(aeq_0, ((Struct)(l19_pa).f_second), ((Struct)(l20_pb).f_second)));
					} else {
						return ((l13_funcNr==(l17_fb).f_funcNr)&&Module_equalizer.f_mizEqTrmListsE(aeq_0, l14_args, (l17_fb).f_args));
					}
				}
				case 61/*MizPrivFuncTrm*/: {
					final Struct_MizPrivFuncTrm l2__tmp = (Struct_MizPrivFuncTrm)l1__tmp;
					final int l21_funcNr = l2__tmp.f_funcNr;
					final Struct l22_args = l2__tmp.f_args;
					final int l23_funcNr = (l2__tmp).f_funcNr;
					final Struct l24_args = (l2__tmp).f_args;
					final Struct l25___ = (l2__tmp).f_funcExp;
					final int l26___ = (l2__tmp).f_pattNr;
					final int l27___ = (l2__tmp).f_trmInfo;
					final Struct_MizPrivFuncTrm l28_pb = Module_equalizer.f_mizEqAsPriv(ab);
					return ((l23_funcNr==(l28_pb).f_funcNr)&&Module_equalizer.f_mizEqTrmListsE(aeq_0, l24_args, (l28_pb).f_args));
				}
				case 42/*MizFraenkelTrm*/: {
					final Struct_MizFraenkelTrm l2__tmp = (Struct_MizFraenkelTrm)l1__tmp;
					final Struct_Vector l29_lambdaArgs = l2__tmp.f_lambdaArgs;
					final Struct l30_scope = l2__tmp.f_scope;
					final Struct l31_compr = l2__tmp.f_compr;
					final Struct_Vector l32_lambdaArgs = (l2__tmp).f_lambdaArgs;
					final Struct l33_scope = (l2__tmp).f_scope;
					final Struct l34_compr = (l2__tmp).f_compr;
					final Struct_MizIntSeq l35___ = (l2__tmp).f_idents;
					final int l36___ = (l2__tmp).f_pattNr;
					final int l37___ = (l2__tmp).f_trmInfo;
					final Struct_MizFraenkelTrm l38_kb = Module_equalizer.f_mizEqAsFraenkel(ab);
					if ((Module_vector.f_sizeVector(l32_lambdaArgs)!=Module_vector.f_sizeVector((l38_kb).f_lambdaArgs))) {
						return false;
					} else {
						final Reference<Boolean> l39_ok = ((Reference<Boolean>)(new Reference(true)));
						final Reference<Integer> l40_i = ((Reference<Integer>)(new Reference(0)));
						final Func0<Boolean> l43_$41 = (Func0<Boolean>)() -> {
							return ((Boolean)((((int)l40_i.value)<Module_vector.f_sizeVector(l32_lambdaArgs))&&((boolean)l39_ok.value)));
						};
						final Struct_MizEqualizer l45_eq_0 = aeq_0;
						final Func0<Object> l44_$42 = (Func0<Object>)() -> {
							if (!Module_equalizer.f_mizEqTypsE(l45_eq_0, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l32_lambdaArgs, ((int)l40_i.value))), ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l38_kb).f_lambdaArgs, ((int)l40_i.value))))) {
								l39_ok.value = ((Boolean)false);
							} else {
							}
							l40_i.value = ((Integer)(((int)l40_i.value)+1));
							return null;
						};
						Module_loop.f_while(l43_$41, l44_$42);
						return ((((boolean)l39_ok.value)&&Module_equalizer.f_mizEqTrms(aeq_0, l33_scope, (l38_kb).f_scope))&&Module_equalizer.f_mizEqFrmsE(aeq_0, l34_compr, (l38_kb).f_compr));
					}
				}
				case 21/*MizChoiceTrm*/: {
					final Struct_MizChoiceTrm l2__tmp = (Struct_MizChoiceTrm)l1__tmp;
					final Struct_MizTyp l46_typ = l2__tmp.f_choiceTyp;
					final Struct_MizTyp l47_typ = (l2__tmp).f_choiceTyp;
					final int l48___ = (l2__tmp).f_pattNr;
					final int l49___ = (l2__tmp).f_trmInfo;
					final Struct_MizChoiceTrm l50_hb = Module_equalizer.f_mizEqAsChoice(ab);
					return Module_equalizer.f_mizEqTypsE(aeq_0, l47_typ, (l50_hb).f_choiceTyp);
				}
				case 16/*MizBaseTrm*/: {
					final Struct_MizBaseTrm l2__tmp = (Struct_MizBaseTrm)l1__tmp;
					final int l51_srt = l2__tmp.f_sort;
					final int l52_srt = (l2__tmp).f_sort;
					final int l53___ = (l2__tmp).f_trmInfo;
					return (l52_srt==Module_lexicon.g_ikError);
				}
				case 62/*MizQuaTrm*/: {
					final Struct_MizQuaTrm l2__tmp = (Struct_MizQuaTrm)l1__tmp;
					final Struct l54___ = (l2__tmp).f_trmProper;
					final Struct_MizTyp l55___ = (l2__tmp).f_qua;
					final int l56___ = (l2__tmp).f_pattNr;
					final int l57___ = (l2__tmp).f_trmInfo;
					return false;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l1__tmp.getTypeName());
				}
			}
		}
	}
	public static final boolean f_mizEqTypsE(Struct_MizEqualizer aeq_0, Struct_MizTyp aa, Struct_MizTyp ab) {
		if (((aa).f_sort!=(ab).f_sort)) {
			return false;
		} else {
			if (((aa).f_sort==Module_lexicon.g_ikTypMode)) {
				final Struct_Pair l0_pa = Module_correl.f_mizAdjustTyp((aeq_0).f_env, aa);
				final Struct_Pair l1_pb = Module_correl.f_mizAdjustTyp((aeq_0).f_env, ab);
				boolean l4_$3;
				if ((((int)(l0_pa).f_first)==((int)(l1_pb).f_first))) {
					final Struct_MizEqualizer l7_eq_0 = aeq_0;
					final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l6_$5 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
						return ((Boolean)Module_equalizer.f_mizEqAttrsE(l7_eq_0, ax, ay));
					};
					l4_$3=Module_correl.f_mizEqualClusters(aa, ab, l6_$5);
				} else {
					l4_$3=false;
				}
				return (l4_$3&&Module_equalizer.f_mizEqTrmListsE(aeq_0, ((Struct)(l0_pa).f_second), ((Struct)(l1_pb).f_second)));
			} else {
				if (((aa).f_sort==Module_lexicon.g_ikTypStruct)) {
					boolean l10_$9;
					if (((aa).f_modNr==(ab).f_modNr)) {
						final Struct_MizEqualizer l13_eq_0 = aeq_0;
						final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l12_$11 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
							return ((Boolean)Module_equalizer.f_mizEqAttrsE(l13_eq_0, ax, ay));
						};
						l10_$9=Module_correl.f_mizEqualClusters(aa, ab, l12_$11);
					} else {
						l10_$9=false;
					}
					return (l10_$9&&Module_equalizer.f_mizEqTrmListsE(aeq_0, (aa).f_modArgs, (ab).f_modArgs));
				} else {
					Module_errhan.f_mizRunTimeError(2020);
					return false;
				}
			}
		}
	}
	public static final Object f_mizEquate(Struct_MizEqualizer aeq0, Struct_MizNatFunc aeval) {
		final Struct_MizEqualizer l0_eq_0 = aeq0;
		final Struct_MizCorrelEnv l1_env = (l0_eq_0).f_env;
		final Struct_MizPre l2_pre = (l0_eq_0).f_pre;
		final Struct_Vector l3_basic = (l2_pre).f_basic;
		final Reference<Integer> l4_i = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l0_eq_0).f_contr==0)&&(((int)l4_i.value)<Module_vector.f_sizeVector(l3_basic))));
		};
		final Struct_MizNatFunc l9_eval = aeval;
		final Func0<Object> l8_$6 = (Func0<Object>)() -> {
			final int l9_j = ((int)l4_i.value);
			if ((Module_mobjects.f_mizNatHasInDom(l9_eval, l9_j)&&(Module_mobjects.f_mizNatValue(l9_eval, l9_j)==1))) {
				final Struct l10_bf = ((Struct)Module_vector.f_getVectorUnsafe(l3_basic, l9_j));
				Struct l11__tmp = l10_bf;
				switch (l11__tmp.getTypeId()) {
				case 63/*MizQualFrm*/: {
					final Struct_MizQualFrm l12__tmp = (Struct_MizQualFrm)l11__tmp;
					final Struct l13_trm = l12__tmp.f_trm;
					final Struct_MizTyp l14_typ = l12__tmp.f_typ;
					final Struct l15_trm = (l12__tmp).f_trm;
					final Struct_MizTyp l16_typ = (l12__tmp).f_typ;
					final int l17___ = (l12__tmp).f_pattNr;
					final Struct_MizTyp l18_xType = Wrappers.w_mizCopyTyp.invoke(l16_typ);
					final Struct l19_xTerm = Module_correl.f_mizCopyTrm(l15_trm);
					Module_equalizer.f_mizYType(l0_eq_0, l18_xType);
					final Struct l20_xTerm2 = Module_equalizer.f_mizYTerm(l0_eq_0, l19_xTerm);
					if ((Module_url_parameter.f_getUrlParameterDef("dbg-qual", "")).equals("1")) {
						Module_runtime.f_println(("QUAL insert cls="+Module_string.f_i2s(Module_equalizer.f_mizTrmInfoOf(l20_xTerm2))));
					} else {
					}
					Module_equalizer.f_mizInsertType(l0_eq_0, l18_xType, Module_equalizer.f_mizTrmInfoOf(l20_xTerm2));
					break;
				}
				case 58/*MizPredFrm*/: {
					final Struct_MizPredFrm l12__tmp = (Struct_MizPredFrm)l11__tmp;
					final int l21_srt = l12__tmp.f_sort;
					final int l22_predNr = l12__tmp.f_predNr;
					final Struct l23_args = l12__tmp.f_args;
					final int l24_srt = (l12__tmp).f_sort;
					final int l25_predNr = (l12__tmp).f_predNr;
					final Struct l26_args = (l12__tmp).f_args;
					final int l27___ = (l12__tmp).f_pattNr;
					if ((l24_srt==Module_lexicon.g_ikFrmAttr)) {
						final Struct l28_lTrmList = Module_correl.f_mizCopyTrmList(Module_equalizer.f_mizAllButLast(l26_args));
						final Struct l29_lTrm = Module_correl.f_mizCopyTrm(Module_equalizer.f_mizLastArgE(l26_args));
						Module_equalizer.f_mizYTermList(l0_eq_0, l28_lTrmList);
						final Struct l30_lTrm2 = Module_equalizer.f_mizYTerm(l0_eq_0, l29_lTrm);
						final int l31_cls = Module_equalizer.f_mizClassOf(l0_eq_0, Module_equalizer.f_mizTrmInfoOf(l30_lTrm2));
						Module_equalizer.f_mizInsertAttrE(l0_eq_0, (Module_equalizer.f_mizTrmSAt(l0_eq_0, l31_cls)).f_superCluster, l25_predNr, 1, l28_lTrmList);
						if (!((Module_equalizer.f_mizTrmSAt(l0_eq_0, l31_cls)).f_superCluster).f_consistent) {
							Module_equalizer.f_mizSetContr(l0_eq_0, 24);
						} else {
						}
					} else {
						if ((l24_srt==Module_lexicon.g_ikFrmPred)) {
							final Struct_MizPredFrm l32_pfE = Module_equalizer.f_mizEqAsPred(l12__tmp);
							final Struct_Pair l33_pa = Module_correl.f_mizAdjustFrm(l1_env, l32_pfE);
							if ((((int)(l33_pa).f_first)==Module_builtin.f_mizBuiltIn((l1_env).f_builtIn, Module_builtin.g_rqEqualsTo))) {
								final Struct l34_xT = Module_equalizer.f_mizYTerm(l0_eq_0, Module_correl.f_mizCopyTrm(Module_correl.f_mizTrmListNth(((Struct)(l33_pa).f_second), 0)));
								final Struct l35_xT1 = Module_equalizer.f_mizYTerm(l0_eq_0, Module_correl.f_mizCopyTrm(Module_correl.f_mizTrmListNth(((Struct)(l33_pa).f_second), 1)));
								if ((Module_equalizer.f_mizTrmInfoOf(l34_xT)!=Module_equalizer.f_mizTrmInfoOf(l35_xT1))) {
									Module_equalizer.f_mizAddEquality((l0_eq_0).f_eqPend, Module_equalizer.f_mizTrmInfoOf(l34_xT), Module_equalizer.f_mizTrmInfoOf(l35_xT1));
								} else {
								}
							} else {
								final Struct l36_lFrm = Wrappers.w_mizCopyFrm.invoke(l12__tmp);
								Module_equalizer.f_mizYFormula(l0_eq_0, l36_lFrm);
								Module_vector.f_pushVector((l0_eq_0).f_posBas, l36_lFrm);
							}
						} else {
							final Struct l37_lFrm = Wrappers.w_mizCopyFrm.invoke(l12__tmp);
							Module_equalizer.f_mizYFormula(l0_eq_0, l37_lFrm);
							Module_vector.f_pushVector((l0_eq_0).f_posBas, l37_lFrm);
						}
					}
					break;
				}
				default: {
					final Struct l38_lFrm = Wrappers.w_mizCopyFrm.invoke(l10_bf);
					Module_equalizer.f_mizYFormula(l0_eq_0, l38_lFrm);
					Module_vector.f_pushVector((l0_eq_0).f_posBas, l38_lFrm);
					break;
				}
				}
			} else {
			}
			l4_i.value = ((Integer)(((int)l4_i.value)+1));
			return null;
		};
		Module_loop.f_while(l7_$5, l8_$6);
		if (((l0_eq_0).f_contr==0)) {
			final Reference<Integer> l10_i2 = ((Reference<Integer>)(new Reference(0)));
			final Func0<Boolean> l13_$11 = (Func0<Boolean>)() -> {
				return ((Boolean)(((l0_eq_0).f_contr==0)&&(((int)l10_i2.value)<Module_vector.f_sizeVector(l3_basic))));
			};
			final Struct_MizNatFunc l15_eval = aeval;
			final Func0<Object> l14_$12 = (Func0<Object>)() -> {
				final int l15_j = ((int)l10_i2.value);
				if ((Module_mobjects.f_mizNatHasInDom(l15_eval, l15_j)&&(Module_mobjects.f_mizNatValue(l15_eval, l15_j)==0))) {
					final Struct l16_bf = ((Struct)Module_vector.f_getVectorUnsafe(l3_basic, l15_j));
					boolean l17_isNegAttr;
					Struct l18__tmp = l16_bf;
					switch (l18__tmp.getTypeId()) {
					case 58/*MizPredFrm*/: {
						final Struct_MizPredFrm l19__tmp = (Struct_MizPredFrm)l18__tmp;
						final int l20_srt = l19__tmp.f_sort;
						final int l21_srt = (l19__tmp).f_sort;
						final int l22___ = (l19__tmp).f_predNr;
						final Struct l23___ = (l19__tmp).f_args;
						final int l24___ = (l19__tmp).f_pattNr;
						l17_isNegAttr=(l21_srt==Module_lexicon.g_ikFrmAttr);
						break;
					}
					default: {
						l17_isNegAttr=false;
						break;
					}
					}
					if (l17_isNegAttr) {
						final Struct_MizPredFrm l25_pf = Module_equalizer.f_mizEqAsPred(l16_bf);
						final Struct l26_lTrmList = Module_correl.f_mizCopyTrmList(Module_equalizer.f_mizAllButLast((l25_pf).f_args));
						final Struct l27_lTrm = Module_correl.f_mizCopyTrm(Module_equalizer.f_mizLastArgE((l25_pf).f_args));
						Module_equalizer.f_mizYTermList(l0_eq_0, l26_lTrmList);
						final Struct l28_lTrm2 = Module_equalizer.f_mizYTerm(l0_eq_0, l27_lTrm);
						final int l29_cls = Module_equalizer.f_mizClassOf(l0_eq_0, Module_equalizer.f_mizTrmInfoOf(l28_lTrm2));
						Module_equalizer.f_mizInsertAttrE(l0_eq_0, (Module_equalizer.f_mizTrmSAt(l0_eq_0, l29_cls)).f_superCluster, (l25_pf).f_predNr, 0, l26_lTrmList);
						if (!((Module_equalizer.f_mizTrmSAt(l0_eq_0, l29_cls)).f_superCluster).f_consistent) {
							Module_equalizer.f_mizSetContr(l0_eq_0, 25);
						} else {
						}
					} else {
						final Struct l30_lFrm = Wrappers.w_mizCopyFrm.invoke(l16_bf);
						Module_equalizer.f_mizYFormula(l0_eq_0, l30_lFrm);
						Module_vector.f_pushVector((l0_eq_0).f_negBas, l30_lFrm);
					}
				} else {
				}
				l10_i2.value = ((Integer)(((int)l10_i2.value)+1));
				return null;
			};
			Module_loop.f_while(l13_$11, l14_$12);
		} else {
		}
		if (((l0_eq_0).f_contr==0)) {
			return Module_equalizer.f_mizEquatePhase2(l0_eq_0);
		} else {
			return null;
		}
	}
	public static final Object f_mizEquateComplexValue(Struct_MizEqualizer aeq_0, int aaTrmInfo, Struct_MizComplex av) {
		final Struct_MizTrmSRec l0_rec = Module_equalizer.f_mizTrmSAt(aeq_0, aaTrmInfo);
		if ((l0_rec).f_numDetermined) {
			if ((!FlowRuntime.compareEqual((l0_rec).f_numValue,av))) {
				return Module_equalizer.f_mizSetContr(aeq_0, 14);
			} else {
				return null;
			}
		} else {
			final Reference<Boolean> l1_merged = ((Reference<Boolean>)(new Reference(false)));
			final Reference<Integer> l2_i = ((Reference<Integer>)(new Reference(1)));
			final Struct_MizEqualizer l6_eq_0 = aeq_0;
			final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
				return ((Boolean)((((l6_eq_0).f_contr==0)&&!((boolean)l1_merged.value))&&(((int)l2_i.value)<=Module_equalizer.f_mizTrmNbr(l6_eq_0))));
			};
			final Struct_MizEqualizer l8_eq_0 = aeq_0;
			final Struct_MizComplex l9_v = av;
			final int l10_aTrmInfo = aaTrmInfo;
			final Func0<Object> l7_$4 = (Func0<Object>)() -> {
				final Struct_MizTrmSRec l8_ri = Module_equalizer.f_mizTrmSAt(l8_eq_0, ((int)l2_i.value));
				final Struct l9_gsymswitch35 = (l8_ri).f_eqClass;
				boolean l10_live;
				Struct l11__tmp = l9_gsymswitch35;
				switch (l11__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l10_live=false;
					break;
				}
				default: {
					l10_live=true;
					break;
				}
				}
				if (((l10_live&&(l8_ri).f_numDetermined)&&FlowRuntime.compareEqual((l8_ri).f_numValue,l9_v))) {
					Module_equalizer.f_mizUnionTrms(l8_eq_0, l10_aTrmInfo, ((int)l2_i.value));
					l1_merged.value = ((Boolean)true);
				} else {
				}
				l2_i.value = ((Integer)(((int)l2_i.value)+1));
				return null;
			};
			Module_loop.f_while(l5_$3, l7_$4);
			if (((aeq_0).f_contr==0)) {
				((Field_clash)aeq_0).set_clash(true);
				((Field_numDetermined)l0_rec).set_numDetermined(true);
				((Field_numValue)l0_rec).set_numValue(av);
				return null;
			} else {
				return null;
			}
		}
	}
	public static final Object f_mizEquatePhase2(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		Module_equalizer.f_mizInitAllowedClusters(aeq_0);
		final Struct_Vector l3_$2 = Module_vector.f_copyVector((aeq_0).f_posBas);
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func1<Object,Struct> l4_$1 = (Func1<Object, Struct>)(Struct apf) -> {
			Struct l5__tmp = apf;
			switch (l5__tmp.getTypeId()) {
			case 58/*MizPredFrm*/: {
				final Struct_MizPredFrm l6__tmp = (Struct_MizPredFrm)l5__tmp;
				final int l7_srt = l6__tmp.f_sort;
				final int l8_predNr = l6__tmp.f_predNr;
				final Struct l9_args = l6__tmp.f_args;
				final int l10_srt = (l6__tmp).f_sort;
				final int l11_predNr = (l6__tmp).f_predNr;
				final Struct l12_args = (l6__tmp).f_args;
				final int l13___ = (l6__tmp).f_pattNr;
				if ((l10_srt==Module_lexicon.g_ikFrmPred)) {
					final Struct_MizConstr l14_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, l11_predNr);
					if (Module_correl.f_mizHasProperty(l14_c, Module_correl.g_syAsymmetry)) {
						final Reference<Boolean> l15_there = ((Reference<Boolean>)(new Reference(false)));
						final Struct_Vector l18_$17 = (l5_eq_0).f_negBas;
						final Func1<Object,Struct> l19_$16 = (Func1<Object, Struct>)(Struct ag) -> {
							if (Module_equalizer.f_mizEqFrmsE(l5_eq_0, l6__tmp, ag)) {
								l15_there.value = ((Boolean)true);
								return null;
							} else {
								return null;
							}
						};
						Module_vector.f_iterVector(l18_$17, ((Func1<Object,Object>)(Func1)l19_$16));
						if (!((boolean)l15_there.value)) {
							return Module_vector.f_pushVector((l5_eq_0).f_negBas, Module_correl.f_mizNewPredFrm(l10_srt, l11_predNr, Module_roundcl.f_mizSwapArguments(l12_args, (l14_c).f_firstArg, (l14_c).f_secondArg), 0));
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
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		final Struct_Vector l8_$7 = Module_vector.f_copyVector((aeq_0).f_negBas);
		final Struct_MizEqualizer l10_eq_0 = aeq_0;
		final Func1<Object,Struct> l9_$6 = (Func1<Object, Struct>)(Struct anf) -> {
			Struct l10__tmp = anf;
			switch (l10__tmp.getTypeId()) {
			case 58/*MizPredFrm*/: {
				final Struct_MizPredFrm l11__tmp = (Struct_MizPredFrm)l10__tmp;
				final int l12_srt = l11__tmp.f_sort;
				final int l13_predNr = l11__tmp.f_predNr;
				final Struct l14_args = l11__tmp.f_args;
				final int l15_srt = (l11__tmp).f_sort;
				final int l16_predNr = (l11__tmp).f_predNr;
				final Struct l17_args = (l11__tmp).f_args;
				final int l18___ = (l11__tmp).f_pattNr;
				if ((l15_srt==Module_lexicon.g_ikFrmPred)) {
					final Struct_MizConstr l19_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, l16_predNr);
					if (Module_correl.f_mizHasProperty(l19_c, Module_correl.g_syConnectedness)) {
						final Reference<Boolean> l20_there = ((Reference<Boolean>)(new Reference(false)));
						final Struct_Vector l23_$22 = (l10_eq_0).f_posBas;
						final Func1<Object,Struct> l24_$21 = (Func1<Object, Struct>)(Struct ag) -> {
							if (Module_equalizer.f_mizEqFrmsE(l10_eq_0, l11__tmp, ag)) {
								l20_there.value = ((Boolean)true);
								return null;
							} else {
								return null;
							}
						};
						Module_vector.f_iterVector(l23_$22, ((Func1<Object,Object>)(Func1)l24_$21));
						if (!((boolean)l20_there.value)) {
							return Module_vector.f_pushVector((l10_eq_0).f_posBas, Module_correl.f_mizNewPredFrm(l15_srt, l16_predNr, Module_roundcl.f_mizSwapArguments(l17_args, (l19_c).f_firstArg, (l19_c).f_secondArg), 0));
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
		Module_vector.f_iterVector(l8_$7, ((Func1<Object,Object>)(Func1)l9_$6));
		final Reference<Integer> l11_ii = ((Reference<Integer>)(new Reference(1)));
		final Struct_MizEqualizer l15_eq_0 = aeq_0;
		final Func0<Boolean> l14_$12 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l15_eq_0).f_contr==0)&&(((int)l11_ii.value)<=Module_equalizer.f_mizTrmNbr(l15_eq_0))));
		};
		final Struct_MizEqualizer l17_eq_0 = aeq_0;
		final Func0<Object> l16_$13 = (Func0<Object>)() -> {
			final Struct_MizTrmSRec l17_rec = Module_equalizer.f_mizTrmSAt(l17_eq_0, ((int)l11_ii.value));
			final Reference<Struct> l18_cur = ((Reference<Struct>)(new Reference((l17_rec).f_eqClass)));
			final Func0<Boolean> l21_$19 = (Func0<Boolean>)() -> {
				final Struct l22_gsymswitch21 = l18_cur.value;
				Struct l23__tmp = l22_gsymswitch21;
				switch (l23__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					return ((Boolean)false);
				}
				default: {
					return ((Boolean)true);
				}
				}
			};
			final Func0<Object> l22_$20 = (Func0<Object>)() -> {
				final Struct l23_gsymswitch22 = l18_cur.value;
				Struct l24__tmp = l23_gsymswitch22;
				switch (l24__tmp.getTypeId()) {
				case 70/*MizTrmElem*/: {
					final Struct_MizTrmElem l25__tmp = (Struct_MizTrmElem)l24__tmp;
					final Struct l26_trm = l25__tmp.f_trm;
					final Struct l27_next = l25__tmp.f_next;
					final Struct l28_trm = (l25__tmp).f_trm;
					final Struct l29_next = (l25__tmp).f_next;
					Struct l30__tmp = l28_trm;
					switch (l30__tmp.getTypeId()) {
					case 77/*MizVarTrm*/: {
						final Struct_MizVarTrm l31__tmp = (Struct_MizVarTrm)l30__tmp;
						final int l32_srt = l31__tmp.f_sort;
						final int l33_varNr = l31__tmp.f_varNr;
						final int l34_srt = (l31__tmp).f_sort;
						final int l35_varNr = (l31__tmp).f_varNr;
						final int l36___ = (l31__tmp).f_pattNr;
						final int l37___ = (l31__tmp).f_trmInfo;
						if ((l34_srt==Module_lexicon.g_ikTrmInfConst)) {
							final Struct_MizConstDef l38_cd = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((l0_env).f_inferConstDef, l35_varNr));
							final int l41_$40 = (Module_mobjects.f_mizNatCount((l38_cd).f_eqConst)-1);
							final Func1<Object,Integer> l42_$39 = (Func1<Object, Integer>)(Integer az) -> {
								final int l43_other = (Module_mobjects.f_mizNatItem((l38_cd).f_eqConst, ((int)az))).f_x;
								final Struct l44_lTrm = Module_equalizer.f_mizYTerm(l17_eq_0, Module_correl.f_mizNewVarTrm(Module_lexicon.g_ikTrmInfConst, l43_other));
								if ((((int)l11_ii.value)!=Module_equalizer.f_mizTrmInfoOf(l44_lTrm))) {
									return Module_equalizer.f_mizAddEquality((l17_eq_0).f_eqPend, ((int)l11_ii.value), Module_equalizer.f_mizTrmInfoOf(l44_lTrm));
								} else {
									return null;
								}
							};
							Module_runtime.f_fori(0, l41_$40, l42_$39);
							final Struct l43_dTrm = Module_correl_inst.f_mizCopyExpTrm((l38_cd).f_def);
							final int l44_xi = Module_equalizer.f_mizYYTerm(l17_eq_0, l43_dTrm, ((int)l11_ii.value));
							if ((((int)l11_ii.value)!=l44_xi)) {
								Module_equalizer.f_mizAddEquality((l17_eq_0).f_settings, ((int)l11_ii.value), l44_xi);
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
					l18_cur.value = l29_next;
					return null;
				}
				case 71/*MizTrmNil*/: {
					return null;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l24__tmp.getTypeName());
				}
			};
			Module_loop.f_while(l21_$19, l22_$20);
			l11_ii.value = ((Integer)(((int)l11_ii.value)+1));
			return null;
		};
		Module_loop.f_while(l14_$12, l16_$13);
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizInitEmptyInEqClass(aeq_0);
		} else {
		}
		if ((((aeq_0).f_contr==0)&&!(aeq_0).f_trmOvfl)) {
			Module_equalizer.f_mizInitStructuresInEqClass(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizProcessReductions(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizInitSuperClusterForComplex(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizUnionEqualsForNonComplex(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizInitPolynomialValues(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizSubstituteSettings(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizClearPolynomialValues(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizEquatePolynomialValues(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizEquatePolynomials(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizClearPolynomialValues(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			final Struct_Vector l20_$19 = (aeq_0).f_eqPend;
			final Struct_MizEqualizer l22_eq_0 = aeq_0;
			final Func1<Object,Struct_MizIntPair> l21_$18 = (Func1<Object, Struct_MizIntPair>)(Struct_MizIntPair ap) -> {
				if (((l22_eq_0).f_contr==0)) {
					return Module_equalizer.f_mizUnionTrms(l22_eq_0, (ap).f_x, (ap).f_y);
				} else {
					return null;
				}
			};
			Module_vector.f_iterVector(l20_$19, ((Func1<Object,Object>)(Func1)l21_$18));
			Module_vector.f_clearVector((aeq_0).f_eqPend);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizEquatePolynomials(aeq_0);
		} else {
		}
		final Reference<Boolean> l23_outerCl = ((Reference<Boolean>)(new Reference(true)));
		final Struct_MizEqualizer l27_eq_0 = aeq_0;
		final Func0<Boolean> l26_$24 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l27_eq_0).f_contr==0)&&((boolean)l23_outerCl.value)));
		};
		final Struct_MizEqualizer l29_eq_0 = aeq_0;
		final Func0<Object> l28_$25 = (Func0<Object>)() -> {
			Module_equalizer.f_mizClearPolynomialValues(l29_eq_0);
			if (((l29_eq_0).f_contr==0)) {
				Module_equalizer.f_mizIdentities(l29_eq_0);
			} else {
			}
			if (((l29_eq_0).f_contr==0)) {
				final boolean l29_savedClash = (l29_eq_0).f_clash;
				((Field_clash)l29_eq_0).set_clash(false);
				Module_equalizer.f_mizEquatePolynomials(l29_eq_0);
				l23_outerCl.value = ((Boolean)(l29_eq_0).f_clash);
				((Field_clash)l29_eq_0).set_clash(l29_savedClash);
				return null;
			} else {
				l23_outerCl.value = ((Boolean)false);
				return null;
			}
		};
		Module_loop.f_while(l26_$24, l28_$25);
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizRenumEqClasses(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizContradictionVerify(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			final Struct_Vector l32_$31 = (aeq_0).f_negBas;
			final Struct_MizEqualizer l34_eq_0 = aeq_0;
			final Func1<Object,Struct> l33_$30 = (Func1<Object, Struct>)(Struct anf) -> {
				if (((l34_eq_0).f_contr==0)) {
					Struct l34__tmp = anf;
					switch (l34__tmp.getTypeId()) {
					case 58/*MizPredFrm*/: {
						final Struct_MizPredFrm l35__tmp = (Struct_MizPredFrm)l34__tmp;
						final int l36_srt = l35__tmp.f_sort;
						final int l37_predNr = l35__tmp.f_predNr;
						final Struct l38_args = l35__tmp.f_args;
						final int l39_srt = (l35__tmp).f_sort;
						final int l40_predNr = (l35__tmp).f_predNr;
						final Struct l41_args = (l35__tmp).f_args;
						final int l42___ = (l35__tmp).f_pattNr;
						if ((l39_srt==Module_lexicon.g_ikFrmPred)) {
							final Struct_MizConstr l43_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coPredicate, l40_predNr);
							if (Module_correl.f_mizHasProperty(l43_c, Module_correl.g_syReflexivity)) {
								final Struct_Pair l44_p2 = Module_equalizer.f_mizGetArgs2(l41_args, (l43_c).f_firstArg, (l43_c).f_secondArg);
								final int l45_i1 = Module_equalizer.f_mizTrmInfoOf(((Struct)(l44_p2).f_first));
								final int l46_i2 = Module_equalizer.f_mizTrmInfoOf(((Struct)(l44_p2).f_second));
								Module_equalizer.f_mizInsertNonEmpty(l34_eq_0, l45_i1, l46_i2);
								Module_equalizer.f_mizInsertNonEmpty(l34_eq_0, l46_i2, l45_i1);
								Module_equalizer.f_mizInsertNonZero(l34_eq_0, l45_i1, l46_i2);
								return Module_equalizer.f_mizInsertNonZero(l34_eq_0, l46_i2, l45_i1);
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
			Module_vector.f_iterVector(l32_$31, ((Func1<Object,Object>)(Func1)l33_$30));
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizPositiveRequirementRules(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizPowersetRule(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			Module_equalizer.f_mizNegativeSweep(aeq_0);
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			return Module_equalizer.f_mizSuperClusterRoundLoop(aeq_0);
		} else {
			return null;
		}
	}
	public static final Object f_mizEquatePolynomialValues(Struct_MizEqualizer aeq_0) {
		if ((Module_builtin.f_mizBuiltIn(((aeq_0).f_env).f_builtIn, Module_builtin.g_rqComplex)!=0)) {
			final Struct_MizNatFunc l0_pend = Module_mobjects.f_mizInitNatSet(8);
			final Reference<Boolean> l1_loop1 = ((Reference<Boolean>)(new Reference(true)));
			final Struct_MizEqualizer l5_eq_0 = aeq_0;
			final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
				return ((Boolean)(((l5_eq_0).f_contr==0)&&((boolean)l1_loop1.value)));
			};
			final Struct_MizEqualizer l7_eq_0 = aeq_0;
			final Func0<Object> l6_$3 = (Func0<Object>)() -> {
				final Reference<Integer> l7_t = ((Reference<Integer>)(new Reference(1)));
				final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
					return ((Boolean)(((l7_eq_0).f_contr==0)&&(((int)l7_t.value)<=Module_equalizer.f_mizTrmNbr(l7_eq_0))));
				};
				final Func0<Object> l11_$9 = (Func0<Object>)() -> {
					final Struct_MizTrmSRec l12_rec = Module_equalizer.f_mizTrmSAt(l7_eq_0, ((int)l7_t.value));
					final Struct l13_gsymswitch92 = (l12_rec).f_eqClass;
					boolean l14_live;
					Struct l15__tmp = l13_gsymswitch92;
					switch (l15__tmp.getTypeId()) {
					case 71/*MizTrmNil*/: {
						l14_live=false;
						break;
					}
					default: {
						l14_live=true;
						break;
					}
					}
					if ((l14_live&&(Module_vector.f_sizeVector((l12_rec).f_polynomialValues)>0))) {
						final int l17_iV = Module_polynom.f_mizPolyIsVariable(((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l12_rec).f_polynomialValues, 0)));
						if (((l17_iV>0)&&(l17_iV!=((int)l7_t.value)))) {
							final Struct_MizTrmSRec l18_ri = Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, l17_iV));
							if ((Module_vector.f_sizeVector((l18_ri).f_polynomialValues)>0)) {
								final int l19_jV = Module_polynom.f_mizPolyIsVariable(((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l18_ri).f_polynomialValues, 0)));
								if ((l19_jV==l17_iV)) {
									if ((((int)l7_t.value)>l17_iV)) {
										final boolean l20___s1 = Module_equalizer.f_mizSubstituteVariableP(l7_eq_0, ((int)l7_t.value), ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l18_ri).f_polynomialValues, 0)), l0_pend);
									} else {
										final Struct_MizPolynomial l21_lp = Module_polynom.f_mizPolyWithMonomial((new Struct_MizComplex(1.0, 0.0)), ((int)l7_t.value));
										final boolean l22___s2 = Module_equalizer.f_mizSubstituteVariableP(l7_eq_0, l17_iV, l21_lp, l0_pend);
									}
									Module_equalizer.f_mizUnionTrms(l7_eq_0, ((int)l7_t.value), l17_iV);
								} else {
								}
							} else {
							}
						} else {
						}
					} else {
					}
					l7_t.value = ((Integer)(((int)l7_t.value)+1));
					return null;
				};
				Module_loop.f_while(l10_$8, l11_$9);
				Module_equalizer.f_mizSubstitutePendingVars(l7_eq_0, l0_pend);
				final Reference<Integer> l12_i = ((Reference<Integer>)(new Reference(0)));
				final Func0<Boolean> l15_$13 = (Func0<Boolean>)() -> {
					return ((Boolean)(((l7_eq_0).f_contr==0)&&(((int)l12_i.value)<Module_vector.f_sizeVector((l7_eq_0).f_eqPend))));
				};
				final Func0<Object> l16_$14 = (Func0<Object>)() -> {
					final Struct_MizIntPair l17_p = ((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((l7_eq_0).f_eqPend, ((int)l12_i.value)));
					final Struct_MizTrmSRec l18_rx = Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, (l17_p).f_x));
					final Struct_MizTrmSRec l19_ry = Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, (l17_p).f_y));
					final Reference<Boolean> l20_matched = ((Reference<Boolean>)(new Reference(false)));
					if (((Module_vector.f_sizeVector((l18_rx).f_polynomialValues)>0)&&(Module_vector.f_sizeVector((l19_ry).f_polynomialValues)>0))) {
						final Struct_Vector l23_$22 = (l18_rx).f_polynomialValues;
						final Func1<Object,Struct_MizPolynomial> l24_$21 = (Func1<Object, Struct_MizPolynomial>)(Struct_MizPolynomial ap1) -> {
							final Struct_Vector l27_$26 = (l19_ry).f_polynomialValues;
							final Func1<Object,Struct_MizPolynomial> l28_$25 = (Func1<Object, Struct_MizPolynomial>)(Struct_MizPolynomial ap2) -> {
								if ((!((boolean)l20_matched.value)&&(Module_polynom.f_mizComparePolynomials(ap1, ap2)==0))) {
									l20_matched.value = ((Boolean)true);
									return null;
								} else {
									return null;
								}
							};
							return Module_vector.f_iterVector(l27_$26, ((Func1<Object,Object>)(Func1)l28_$25));
						};
						Module_vector.f_iterVector(l23_$22, ((Func1<Object,Object>)(Func1)l24_$21));
						if (((boolean)l20_matched.value)) {
							Module_equalizer.f_mizUnionTrms(l7_eq_0, (l17_p).f_x, (l17_p).f_y);
							Module_vector.f_removeVector((l7_eq_0).f_eqPend, ((int)l12_i.value));
						} else {
							final int l25_lVar1 = Module_polynom.f_mizPolyIsUniVariantVariable(((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l18_rx).f_polynomialValues, 0)));
							final int l26_lVar2 = Module_polynom.f_mizPolyIsUniVariantVariable(((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l19_ry).f_polynomialValues, 0)));
							if ((l25_lVar1==Module_equalizer.f_mizClassOf(l7_eq_0, (l17_p).f_x))) {
								if ((l26_lVar2==Module_equalizer.f_mizClassOf(l7_eq_0, (l17_p).f_y))) {
									Module_equalizer.f_mizUnionTrms(l7_eq_0, (l17_p).f_x, (l17_p).f_y);
									Module_vector.f_removeVector((l7_eq_0).f_eqPend, ((int)l12_i.value));
									l20_matched.value = ((Boolean)true);
								} else {
									if (((l19_ry).f_numDetermined&&(Module_vector.f_sizeVector((l19_ry).f_polynomialValues)==1))) {
										final boolean l27___s3 = Module_equalizer.f_mizSubstituteVariableP(l7_eq_0, (l17_p).f_x, ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l19_ry).f_polynomialValues, 0)), l0_pend);
										Module_equalizer.f_mizUnionTrms(l7_eq_0, (l17_p).f_x, (l17_p).f_y);
										Module_vector.f_removeVector((l7_eq_0).f_eqPend, ((int)l12_i.value));
										l20_matched.value = ((Boolean)true);
									} else {
									}
								}
							} else {
								if (((l18_rx).f_numDetermined&&(Module_vector.f_sizeVector((l18_rx).f_polynomialValues)==1))) {
									if ((l26_lVar2==Module_equalizer.f_mizClassOf(l7_eq_0, (l17_p).f_y))) {
										final boolean l28___s4 = Module_equalizer.f_mizSubstituteVariableP(l7_eq_0, (l17_p).f_y, ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l18_rx).f_polynomialValues, 0)), l0_pend);
										Module_equalizer.f_mizUnionTrms(l7_eq_0, (l17_p).f_x, (l17_p).f_y);
										Module_vector.f_removeVector((l7_eq_0).f_eqPend, ((int)l12_i.value));
										l20_matched.value = ((Boolean)true);
									} else {
										if (Module_polynom.f_mizPolyIsNumeric(((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l19_ry).f_polynomialValues, 0)))) {
											Module_equalizer.f_mizSetContr(l7_eq_0, 16);
										} else {
										}
									}
								} else {
								}
							}
						}
					} else {
					}
					if (!((boolean)l20_matched.value)) {
						l12_i.value = ((Integer)(((int)l12_i.value)+1));
						return null;
					} else {
						return null;
					}
				};
				Module_loop.f_while(l15_$13, l16_$14);
				l1_loop1.value = (Module_mobjects.f_mizNatCount(l0_pend)!=0);
				return null;
			};
			return Module_loop.f_while(l4_$2, l6_$3);
		} else {
			return null;
		}
	}
	public static final Object f_mizEquatePolynomials(Struct_MizEqualizer aeq_0) {
		final Reference<Integer> l0_i1 = ((Reference<Integer>)(new Reference(1)));
		final Struct_MizEqualizer l4_eq_0 = aeq_0;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l4_eq_0).f_contr==0)&&(((int)l0_i1.value)<=Module_equalizer.f_mizTrmNbr(l4_eq_0))));
		};
		final Struct_MizEqualizer l6_eq_0 = aeq_0;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			final Struct_MizTrmSRec l6_r1 = Module_equalizer.f_mizTrmSAt(l6_eq_0, ((int)l0_i1.value));
			final Struct l7_gsymswitch93 = (l6_r1).f_eqClass;
			boolean l8_live1;
			Struct l9__tmp = l7_gsymswitch93;
			switch (l9__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				l8_live1=false;
				break;
			}
			default: {
				l8_live1=true;
				break;
			}
			}
			if ((l8_live1&&(Module_vector.f_sizeVector((l6_r1).f_polynomialValues)>0))) {
				final Reference<Integer> l11_i2 = ((Reference<Integer>)(new Reference((((int)l0_i1.value)+1))));
				final Func0<Boolean> l14_$12 = (Func0<Boolean>)() -> {
					return ((Boolean)(((l6_eq_0).f_contr==0)&&(((int)l11_i2.value)<=Module_equalizer.f_mizTrmNbr(l6_eq_0))));
				};
				final Func0<Object> l15_$13 = (Func0<Object>)() -> {
					final Struct_MizTrmSRec l16_r2 = Module_equalizer.f_mizTrmSAt(l6_eq_0, ((int)l11_i2.value));
					final Struct l17_gsymswitch94 = (l16_r2).f_eqClass;
					boolean l18_live2;
					Struct l19__tmp = l17_gsymswitch94;
					switch (l19__tmp.getTypeId()) {
					case 71/*MizTrmNil*/: {
						l18_live2=false;
						break;
					}
					default: {
						l18_live2=true;
						break;
					}
					}
					if ((l18_live2&&(Module_vector.f_sizeVector((l16_r2).f_polynomialValues)>0))) {
						final Reference<Boolean> l21_hit = ((Reference<Boolean>)(new Reference(false)));
						final Struct_Vector l24_$23 = (l6_r1).f_polynomialValues;
						final Func1<Object,Struct_MizPolynomial> l25_$22 = (Func1<Object, Struct_MizPolynomial>)(Struct_MizPolynomial ap1) -> {
							final Struct_Vector l28_$27 = (l16_r2).f_polynomialValues;
							final Func1<Object,Struct_MizPolynomial> l29_$26 = (Func1<Object, Struct_MizPolynomial>)(Struct_MizPolynomial ap2) -> {
								if ((!((boolean)l21_hit.value)&&(Module_polynom.f_mizComparePolynomials(ap1, ap2)==0))) {
									l21_hit.value = ((Boolean)true);
									return null;
								} else {
									return null;
								}
							};
							return Module_vector.f_iterVector(l28_$27, ((Func1<Object,Object>)(Func1)l29_$26));
						};
						Module_vector.f_iterVector(l24_$23, ((Func1<Object,Object>)(Func1)l25_$22));
						if (((boolean)l21_hit.value)) {
							Module_equalizer.f_mizUnionTrms(l6_eq_0, ((int)l0_i1.value), ((int)l11_i2.value));
						} else {
						}
					} else {
					}
					l11_i2.value = ((Integer)(((int)l11_i2.value)+1));
					return null;
				};
				Module_loop.f_while(l14_$12, l15_$13);
			} else {
			}
			l0_i1.value = ((Integer)(((int)l0_i1.value)+1));
			return null;
		};
		return Module_loop.f_while(l3_$1, l5_$2);
	}
	public static final Object f_mizEvalComplexReq(Struct_MizEqualizer aeq_0, int arq, Struct at) {
		final Struct l0_args = Module_equalizer.f_mizArgsOfE(at);
		final int l1_ti = Module_equalizer.f_mizTrmInfoOf(at);
		final int l2_lTrmInfo = Module_equalizer.f_mizClassOf(aeq_0, l1_ti);
		final Struct_MizEqualizer l4_eq_0 = aeq_0;
		final Func1<Struct,Struct> l3_valOf = (Func1<Struct, Struct>)(Struct aa) -> {
			final Struct_MizTrmSRec l4_ra = Module_equalizer.f_mizTrmSAt(l4_eq_0, Module_equalizer.f_mizClassOf(l4_eq_0, Module_equalizer.f_mizTrmInfoOf(aa)));
			if ((l4_ra).f_numDetermined) {
				return (new Struct_Some((l4_ra).f_numValue));
			} else {
				return ((Struct)SingletonStructs.str_None);
			}
		};
		final Func2<Boolean,Struct_MizComplex, Integer> l5_isInt = (Func2<Boolean, Struct_MizComplex, Integer>)(Struct_MizComplex av, Integer an) -> {
			return ((Boolean)(((av).f_im==0.0)&&((av).f_re==Module_math.f_i2d(((int)an)))));
		};
		if ((arq==Module_builtin.g_rqRealAdd)) {
			final Struct l6_lt1 = Module_correl.f_mizTrmListNth(l0_args, 0);
			final Struct l7_lt2 = Module_correl.f_mizTrmListNth(l0_args, 1);
			final Struct l8_gsymswitch81 = l3_valOf.invoke(l6_lt1);
			Struct l9__tmp = l8_gsymswitch81;
			switch (l9__tmp.getTypeId()) {
			case 90/*Some*/: {
				final Struct_Some l10__tmp = (Struct_Some)l9__tmp;
				final Object l11_v1 = l10__tmp.f_value;
				final Struct_MizComplex l12_v1 = ((Struct_MizComplex)(l10__tmp).f_value);
				if (((boolean)l5_isInt.invoke(l12_v1, ((Integer)0)))) {
					Module_equalizer.f_mizUnionTrms(aeq_0, l2_lTrmInfo, Module_equalizer.f_mizTrmInfoOf(l7_lt2));
				} else {
				}
				if (((aeq_0).f_contr==0)) {
					final Struct l13_gsymswitch82 = l3_valOf.invoke(l7_lt2);
					Struct l14__tmp = l13_gsymswitch82;
					switch (l14__tmp.getTypeId()) {
					case 90/*Some*/: {
						final Struct_Some l15__tmp = (Struct_Some)l14__tmp;
						final Object l16_v2 = l15__tmp.f_value;
						final Struct_MizComplex l17_v2 = ((Struct_MizComplex)(l15__tmp).f_value);
						return Module_equalizer.f_mizEquateComplexValue(aeq_0, Module_equalizer.f_mizClassOf(aeq_0, l1_ti), (new Struct_MizComplex(((l12_v1).f_re+(l17_v2).f_re), ((l12_v1).f_im+(l17_v2).f_im))));
					}
					case 82/*None*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l14__tmp.getTypeName());
					}
				} else {
					return null;
				}
			}
			case 82/*None*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l9__tmp.getTypeName());
			}
		} else {
			if ((arq==Module_builtin.g_rqRealMult)) {
				final Struct l18_lt1 = Module_correl.f_mizTrmListNth(l0_args, 0);
				final Struct l19_lt2 = Module_correl.f_mizTrmListNth(l0_args, 1);
				final Struct l20_gsymswitch79 = l3_valOf.invoke(l18_lt1);
				Struct l21__tmp = l20_gsymswitch79;
				switch (l21__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l22__tmp = (Struct_Some)l21__tmp;
					final Object l23_v1 = l22__tmp.f_value;
					final Struct_MizComplex l24_v1 = ((Struct_MizComplex)(l22__tmp).f_value);
					if (((boolean)l5_isInt.invoke(l24_v1, ((Integer)0)))) {
						Module_equalizer.f_mizUnionTrms(aeq_0, l1_ti, Module_equalizer.f_mizTrmInfoOf(l18_lt1));
					} else {
						if (((boolean)l5_isInt.invoke(l24_v1, ((Integer)1)))) {
							Module_equalizer.f_mizUnionTrms(aeq_0, l1_ti, Module_equalizer.f_mizTrmInfoOf(l19_lt2));
						} else {
						}
					}
					if (((aeq_0).f_contr==0)) {
						final Struct l25_gsymswitch80 = l3_valOf.invoke(l19_lt2);
						Struct l26__tmp = l25_gsymswitch80;
						switch (l26__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l27__tmp = (Struct_Some)l26__tmp;
							final Object l28_v2 = l27__tmp.f_value;
							final Struct_MizComplex l29_v2 = ((Struct_MizComplex)(l27__tmp).f_value);
							return Module_equalizer.f_mizEquateComplexValue(aeq_0, Module_equalizer.f_mizClassOf(aeq_0, l1_ti), Module_equalizer.f_mizCMul(l24_v1, l29_v2));
						}
						case 82/*None*/: {
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l26__tmp.getTypeName());
						}
					} else {
						return null;
					}
				}
				case 82/*None*/: {
					return null;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l21__tmp.getTypeName());
				}
			} else {
				if ((arq==Module_builtin.g_rqRealNeg)) {
					final Struct l30_lt1 = Module_correl.f_mizTrmListNth(l0_args, 0);
					final Struct l31_gsymswitch78 = l3_valOf.invoke(l30_lt1);
					Struct l32__tmp = l31_gsymswitch78;
					switch (l32__tmp.getTypeId()) {
					case 90/*Some*/: {
						final Struct_Some l33__tmp = (Struct_Some)l32__tmp;
						final Object l34_v1 = l33__tmp.f_value;
						final Struct_MizComplex l35_v1 = ((Struct_MizComplex)(l33__tmp).f_value);
						return Module_equalizer.f_mizEquateComplexValue(aeq_0, l2_lTrmInfo, (new Struct_MizComplex((-(l35_v1).f_re), (-(l35_v1).f_im))));
					}
					case 82/*None*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l32__tmp.getTypeName());
					}
				} else {
					if ((arq==Module_builtin.g_rqRealInv)) {
						final Struct l36_lt1 = Module_correl.f_mizTrmListNth(l0_args, 0);
						final Struct l37_gsymswitch77 = l3_valOf.invoke(l36_lt1);
						Struct l38__tmp = l37_gsymswitch77;
						switch (l38__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l39__tmp = (Struct_Some)l38__tmp;
							final Object l40_v1 = l39__tmp.f_value;
							final Struct_MizComplex l41_v1 = ((Struct_MizComplex)(l39__tmp).f_value);
							if ((((l41_v1).f_re!=0.0)||((l41_v1).f_im!=0.0))) {
								return Module_equalizer.f_mizEquateComplexValue(aeq_0, l2_lTrmInfo, Module_equalizer.f_mizCInvE(l41_v1));
							} else {
								return null;
							}
						}
						case 82/*None*/: {
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l38__tmp.getTypeName());
						}
					} else {
						if ((arq==Module_builtin.g_rqRealDiff)) {
							final Struct l42_lt1 = Module_correl.f_mizTrmListNth(l0_args, 0);
							final Struct l43_lt2 = Module_correl.f_mizTrmListNth(l0_args, 1);
							final Struct l44_gsymswitch75 = l3_valOf.invoke(l43_lt2);
							Struct l45__tmp = l44_gsymswitch75;
							switch (l45__tmp.getTypeId()) {
							case 90/*Some*/: {
								final Struct_Some l46__tmp = (Struct_Some)l45__tmp;
								final Object l47_v2 = l46__tmp.f_value;
								final Struct_MizComplex l48_v2 = ((Struct_MizComplex)(l46__tmp).f_value);
								if (((boolean)l5_isInt.invoke(l48_v2, ((Integer)0)))) {
									Module_equalizer.f_mizUnionTrms(aeq_0, l2_lTrmInfo, Module_equalizer.f_mizTrmInfoOf(l42_lt1));
								} else {
								}
								if (((aeq_0).f_contr==0)) {
									final Struct l49_gsymswitch76 = l3_valOf.invoke(l42_lt1);
									Struct l50__tmp = l49_gsymswitch76;
									switch (l50__tmp.getTypeId()) {
									case 90/*Some*/: {
										final Struct_Some l51__tmp = (Struct_Some)l50__tmp;
										final Object l52_v1 = l51__tmp.f_value;
										final Struct_MizComplex l53_v1 = ((Struct_MizComplex)(l51__tmp).f_value);
										return Module_equalizer.f_mizEquateComplexValue(aeq_0, Module_equalizer.f_mizClassOf(aeq_0, l1_ti), (new Struct_MizComplex(((l53_v1).f_re-(l48_v2).f_re), ((l53_v1).f_im-(l48_v2).f_im))));
									}
									case 82/*None*/: {
										return null;
									}
									default:
										throw new RuntimeException("Unexpected struct in switch: "+l50__tmp.getTypeName());
									}
								} else {
									return null;
								}
							}
							case 82/*None*/: {
								return null;
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l45__tmp.getTypeName());
							}
						} else {
							if ((arq==Module_builtin.g_rqRealDiv)) {
								final Struct l54_lt1 = Module_correl.f_mizTrmListNth(l0_args, 0);
								final Struct l55_lt2 = Module_correl.f_mizTrmListNth(l0_args, 1);
								final Struct l56_gsymswitch72 = l3_valOf.invoke(l54_lt1);
								Struct l57__tmp = l56_gsymswitch72;
								switch (l57__tmp.getTypeId()) {
								case 90/*Some*/: {
									final Struct_Some l58__tmp = (Struct_Some)l57__tmp;
									final Object l59_v1 = l58__tmp.f_value;
									final Struct_MizComplex l60_v1 = ((Struct_MizComplex)(l58__tmp).f_value);
									if (((boolean)l5_isInt.invoke(l60_v1, ((Integer)0)))) {
										Module_equalizer.f_mizUnionTrms(aeq_0, l1_ti, Module_equalizer.f_mizTrmInfoOf(l54_lt1));
									} else {
									}
									break;
								}
								case 82/*None*/: {
									break;
								}
								default:
									throw new RuntimeException("Unexpected struct in switch: "+l57__tmp.getTypeName());
								}
								if (((aeq_0).f_contr==0)) {
									final Struct l61_gsymswitch73 = l3_valOf.invoke(l55_lt2);
									Struct l62__tmp = l61_gsymswitch73;
									switch (l62__tmp.getTypeId()) {
									case 90/*Some*/: {
										final Struct_Some l63__tmp = (Struct_Some)l62__tmp;
										final Object l64_v2 = l63__tmp.f_value;
										final Struct_MizComplex l65_v2 = ((Struct_MizComplex)(l63__tmp).f_value);
										if ((((l65_v2).f_re!=0.0)||((l65_v2).f_im!=0.0))) {
											final Struct_MizComplex l66_inv = Module_equalizer.f_mizCInvE(l65_v2);
											if (((boolean)l5_isInt.invoke(l66_inv, ((Integer)1)))) {
												Module_equalizer.f_mizUnionTrms(aeq_0, l1_ti, Module_equalizer.f_mizTrmInfoOf(l54_lt1));
											} else {
											}
											if (((aeq_0).f_contr==0)) {
												final Struct l67_gsymswitch74 = l3_valOf.invoke(l54_lt1);
												Struct l68__tmp = l67_gsymswitch74;
												switch (l68__tmp.getTypeId()) {
												case 90/*Some*/: {
													final Struct_Some l69__tmp = (Struct_Some)l68__tmp;
													final Object l70_v1 = l69__tmp.f_value;
													final Struct_MizComplex l71_v1 = ((Struct_MizComplex)(l69__tmp).f_value);
													return Module_equalizer.f_mizEquateComplexValue(aeq_0, Module_equalizer.f_mizClassOf(aeq_0, l1_ti), Module_equalizer.f_mizCMul(l71_v1, l66_inv));
												}
												case 82/*None*/: {
													return null;
												}
												default:
													throw new RuntimeException("Unexpected struct in switch: "+l68__tmp.getTypeName());
												}
											} else {
												return null;
											}
										} else {
											return null;
										}
									}
									case 82/*None*/: {
										return null;
									}
									default:
										throw new RuntimeException("Unexpected struct in switch: "+l62__tmp.getTypeName());
									}
								} else {
									return null;
								}
							} else {
								return null;
							}
						}
					}
				}
			}
		}
	}
	public static final Object f_mizExecuteReduction(Struct_MizEqualizer aeq_0, int aenr, Struct_MizReduction ared) {
		final Struct l0_t1 = (ared).f_t1;
		Struct l1_target;
		if ((Module_correl.f_mizTrmSort(l0_t1)==Module_lexicon.g_ikTrmFunctor)) {
			final Struct_MizFuncTrm l2_f1 = Module_equalizer.f_mizEqAsFunc(l0_t1);
			final Struct_Pair l3_pa = Module_correl.f_mizAdjustTrm((aeq_0).f_env, l2_f1);
			l1_target=Module_equalizer.f_mizANLocateTerm(aeq_0, Module_correl.f_mizNewFuncTrm(((int)(l3_pa).f_first), ((Struct)(l3_pa).f_second)));
		} else {
			l1_target=Module_equalizer.f_mizANLocateTerm(aeq_0, l0_t1);
		}
		Struct l4__tmp = l1_target;
		switch (l4__tmp.getTypeId()) {
		case 90/*Some*/: {
			final Struct_Some l5__tmp = (Struct_Some)l4__tmp;
			final Object l6_t = l5__tmp.f_value;
			final Struct l7_t = ((Struct)(l5__tmp).f_value);
			return Module_equalizer.f_mizUnionTrms(aeq_0, aenr, Module_equalizer.f_mizTrmInfoOf(l7_t));
		}
		case 82/*None*/: {
			return null;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l4__tmp.getTypeName());
		}
	}
	public static final Struct f_mizFindEqArgsTerm(Struct_MizEqualizer aeq_0, int akind, int afuncNr, Struct aargs) {
		final Struct l0_gsymswitch4 = Module_equalizer.f_mizFuncIdxFind(aeq_0, akind, afuncNr);
		Struct l1__tmp = l0_gsymswitch4;
		switch (l1__tmp.getTypeId()) {
		case 82/*None*/: {
			return ((Struct)SingletonStructs.str_None);
		}
		case 90/*Some*/: {
			final Struct_Some l2__tmp = (Struct_Some)l1__tmp;
			final Object l3_v = l2__tmp.f_value;
			final Struct_Vector l4_v = ((Struct_Vector)(l2__tmp).f_value);
			final Reference<Struct> l5_res = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
			final Reference<Integer> l6_i = ((Reference<Integer>)(new Reference(0)));
			final Func0<Boolean> l9_$7 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l6_i.value)<Module_vector.f_sizeVector(l4_v))&&Module_maybe.f_isNone(l5_res.value)));
			};
			final Struct_MizEqualizer l11_eq_0 = aeq_0;
			final Struct l12_args = aargs;
			final Func0<Object> l10_$8 = (Func0<Object>)() -> {
				final Struct l11_cand = ((Struct)Module_vector.f_getVectorUnsafe(l4_v, ((int)l6_i.value)));
				final Struct l12_cargs = Module_equalizer.f_mizArgsOfE(l11_cand);
				if (Module_equalizer.f_mizEqTrmListsE(l11_eq_0, l12_args, l12_cargs)) {
					((Reference<Struct_Some>)(Reference)l5_res).value = (new Struct_Some(l11_cand));
				} else {
				}
				l6_i.value = ((Integer)(((int)l6_i.value)+1));
				return null;
			};
			Module_loop.f_while(l9_$7, l10_$8);
			return l5_res.value;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l1__tmp.getTypeName());
		}
	}
	public static final Object f_mizFuncIdxAlloc(Struct_MizEqualizer aeq_0, int akind, int afuncNr, Struct at) {
		final Struct_Vector l0_idx = ((Struct_Vector)Module_vector.f_getVectorUnsafe((aeq_0).f_funcTrmIdx, akind));
		final Struct l1_gsymswitch3 = Module_equalizer.f_mizFuncIdxFind(aeq_0, akind, afuncNr);
		Struct l2__tmp = l1_gsymswitch3;
		switch (l2__tmp.getTypeId()) {
		case 90/*Some*/: {
			final Struct_Some l3__tmp = (Struct_Some)l2__tmp;
			final Object l4_v = l3__tmp.f_value;
			final Struct_Vector l5_v = ((Struct_Vector)(l3__tmp).f_value);
			return Module_vector.f_pushVector(l5_v, at);
		}
		case 82/*None*/: {
			final Struct_Vector l6_v = Module_vector.f_makeVector(4);
			Module_vector.f_pushVector(l6_v, at);
			return Module_vector.f_pushVector(l0_idx, (new Struct_Pair(afuncNr, l6_v)));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
		}
	}
	public static final Object f_mizFuncIdxAllocT(Struct_MizEqualizer aeq_0, int akind, Struct at) {
		return Module_equalizer.f_mizAllocByTerm(aeq_0, akind, at);
	}
	public static final Struct f_mizFuncIdxFind(Struct_MizEqualizer aeq_0, int akind, int afuncNr) {
		final Struct_Vector l0_idx = ((Struct_Vector)Module_vector.f_getVectorUnsafe((aeq_0).f_funcTrmIdx, akind));
		final Reference<Struct> l1_res = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
		final int l4_funcNr = afuncNr;
		final Func1<Object,Struct_Pair> l3_$2 = (Func1<Object, Struct_Pair>)(Struct_Pair ap) -> {
			if ((((int)(ap).f_first)==l4_funcNr)) {
				((Reference<Struct_Some>)(Reference)l1_res).value = (new Struct_Some((ap).f_second));
				return null;
			} else {
				return null;
			}
		};
		Module_vector.f_iterVector(l0_idx, ((Func1<Object,Object>)(Func1)l3_$2));
		return l1_res.value;
	}
	public static final int f_mizFuncNrOfE(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l2_funcNr = l1__tmp.f_funcNr;
			final int l3___ = (l1__tmp).f_sort;
			final int l4_funcNr = (l1__tmp).f_funcNr;
			final Struct l5___ = (l1__tmp).f_args;
			final int l6___ = (l1__tmp).f_pattNr;
			final int l7___ = (l1__tmp).f_trmInfo;
			return l4_funcNr;
		}
		case 61/*MizPrivFuncTrm*/: {
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
	public static final Struct_Pair f_mizGetArgs2(Struct al, int afirst, int asecond) {
		return (new Struct_Pair(Module_correl.f_mizTrmListNth(al, (afirst-1)), Module_correl.f_mizTrmListNth(al, (asecond-1))));
	}
	public static final Struct f_mizGetAttr(Struct_MizEqualizer aeq_0, Struct_MizAttrColl ac, int aattrNr, Struct aargs) {
		final Struct_MizAttr l0_probe = (new Struct_MizAttr(0, aattrNr, aargs, false, 0));
		final Struct_Pair l1_p = Module_correl.f_mizAttrCollSearch((aeq_0).f_env, ac, l0_probe);
		if (((boolean)(l1_p).f_first)) {
			return (new Struct_Some(Module_vector.f_getVectorUnsafe((ac).f_items, ((int)(l1_p).f_second))));
		} else {
			return ((Struct)SingletonStructs.str_None);
		}
	}
	public static final boolean f_mizHasBoundE(Struct_MizEqualizer aeq_0, Struct at) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final Reference<Boolean> l1_found = ((Reference<Boolean>)(new Reference(false)));
		final Func2<Struct,Struct_MizWithin, Struct> l3_$2 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al4_0, Struct atrm) -> {
			Struct l5__tmp = atrm;
			switch (l5__tmp.getTypeId()) {
			case 77/*MizVarTrm*/: {
				final Struct_MizVarTrm l6__tmp = (Struct_MizVarTrm)l5__tmp;
				final int l7_s2 = l6__tmp.f_sort;
				final int l8_v2 = l6__tmp.f_varNr;
				final int l9_s2 = (l6__tmp).f_sort;
				final int l10_v2 = (l6__tmp).f_varNr;
				final int l11___ = (l6__tmp).f_pattNr;
				final int l12___ = (l6__tmp).f_trmInfo;
				if (((l9_s2==Module_lexicon.g_ikTrmBound)&&(l10_v2<=(l0_env).f_boundVarNbr))) {
					l1_found.value = ((Boolean)true);
				} else {
				}
				return l6__tmp;
			}
			default: {
				return atrm;
			}
			}
		};
		final Struct_MizWithin l4_w = Module_correl.f_mizMakeWithin(l3_$2);
		final Struct l5___x = Module_correl.f_mizWithinTrm(l4_w, at);
		return ((boolean)l1_found.value);
	}
	public static final Object f_mizIdentities(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final Reference<Boolean> l1_go = ((Reference<Boolean>)(new Reference(true)));
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l5_eq_0).f_contr==0)&&((boolean)l1_go.value)));
		};
		final Struct_MizEqualizer l7_eq_0 = aeq_0;
		final Func0<Object> l6_$3 = (Func0<Object>)() -> {
			final boolean l7_clashBefore = (l7_eq_0).f_clash;
			((Field_clash)l7_eq_0).set_clash(false);
			final Func1<Object,Integer> l9_$8 = (Func1<Object, Integer>)(Integer akind) -> {
				if (((l7_eq_0).f_contr==0)) {
					final Struct_Vector l10_idx = ((Struct_Vector)Module_vector.f_getVectorUnsafe((l7_eq_0).f_funcTrmIdx, ((int)akind)));
					final Func1<Object,Struct_Pair> l12_$11 = (Func1<Object, Struct_Pair>)(Struct_Pair abucket) -> {
						final Struct_Vector l13_terms = ((Struct_Vector)(abucket).f_second);
						final int l14_n = Module_vector.f_sizeVector(l13_terms);
						final int l17_$16 = (l14_n-2);
						final Func1<Object,Integer> l18_$15 = (Func1<Object, Integer>)(Integer al1) -> {
							if (((l7_eq_0).f_contr==0)) {
								final Struct l19_t1 = ((Struct)Module_vector.f_getVectorUnsafe(l13_terms, ((int)al1)));
								final int l23_$21 = (((int)al1)+1);
								final int l24_$22 = (l14_n-1);
								final Func1<Object,Integer> l25_$20 = (Func1<Object, Integer>)(Integer al2) -> {
									if (((l7_eq_0).f_contr==0)) {
										final Struct l26_t2 = ((Struct)Module_vector.f_getVectorUnsafe(l13_terms, ((int)al2)));
										final int l27_c1 = Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l19_t1));
										final int l28_c2 = Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l26_t2));
										if ((l27_c1!=l28_c2)) {
											boolean l29_same;
											if ((((int)akind)==Module_equalizer.g_expAggreg)) {
												final int l30_fn = Module_equalizer.f_mizFuncNrOfE(l26_t2);
												final Struct_MizConstr l31_ca = Module_correl.f_mizConstr(l0_env, Module_correl.g_coAggregate, l30_fn);
												l29_same=Module_equalizer.f_mizEqTrmListsE(l7_eq_0, Module_correl.f_mizTrmListDrop(Module_equalizer.f_mizArgsOfE(l19_t1), (l31_ca).f_aggregBase), Module_correl.f_mizTrmListDrop(Module_equalizer.f_mizArgsOfE(l26_t2), (l31_ca).f_aggregBase));
											} else {
												if ((((int)akind)==Module_equalizer.g_expSelector)) {
													l29_same=Module_equalizer.f_mizEqTrms(l7_eq_0, Module_equalizer.f_mizLastArgE(Module_equalizer.f_mizArgsOfE(l19_t1)), Module_equalizer.f_mizLastArgE(Module_equalizer.f_mizArgsOfE(l26_t2)));
												} else {
													l29_same=Module_equalizer.f_mizEqTrmListsE(l7_eq_0, Module_equalizer.f_mizArgsOfE(l19_t1), Module_equalizer.f_mizArgsOfE(l26_t2));
												}
											}
											if (l29_same) {
												return Module_equalizer.f_mizUnionTrms(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l19_t1), Module_equalizer.f_mizTrmInfoOf(l26_t2));
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
								return Module_runtime.f_fori(l23_$21, l24_$22, l25_$20);
							} else {
								return null;
							}
						};
						return Module_runtime.f_fori(0, l17_$16, l18_$15);
					};
					return Module_vector.f_iterVector(l10_idx, ((Func1<Object,Object>)(Func1)l12_$11));
				} else {
					return null;
				}
			};
			Module_runtime.f_fori(0, 4, l9_$8);
			final Func2<Boolean,Struct, Struct> l10_samePartsE = (Func2<Boolean, Struct, Struct>)(Struct at1, Struct at2) -> {
				Struct l11__tmp = at1;
				switch (l11__tmp.getTypeId()) {
				case 21/*MizChoiceTrm*/: {
					final Struct_MizChoiceTrm l12__tmp = (Struct_MizChoiceTrm)l11__tmp;
					final Struct_MizTyp l13_ct1 = l12__tmp.f_choiceTyp;
					final Struct_MizTyp l14_ct1 = (l12__tmp).f_choiceTyp;
					final int l15___ = (l12__tmp).f_pattNr;
					final int l16___ = (l12__tmp).f_trmInfo;
					Struct l17__tmp = at2;
					switch (l17__tmp.getTypeId()) {
					case 21/*MizChoiceTrm*/: {
						final Struct_MizChoiceTrm l18__tmp = (Struct_MizChoiceTrm)l17__tmp;
						final Struct_MizTyp l19_ct2 = l18__tmp.f_choiceTyp;
						final Struct_MizTyp l20_ct2 = (l18__tmp).f_choiceTyp;
						final int l21___ = (l18__tmp).f_pattNr;
						final int l22___ = (l18__tmp).f_trmInfo;
						return ((Boolean)Module_equalizer.f_mizEqTypsE(l7_eq_0, l14_ct1, l20_ct2));
					}
					default: {
						return ((Boolean)false);
					}
					}
				}
				case 42/*MizFraenkelTrm*/: {
					final Struct_MizFraenkelTrm l12__tmp = (Struct_MizFraenkelTrm)l11__tmp;
					final Struct_Vector l23_la1 = l12__tmp.f_lambdaArgs;
					final Struct l24_sc1 = l12__tmp.f_scope;
					final Struct l25_co1 = l12__tmp.f_compr;
					final Struct_Vector l26_la1 = (l12__tmp).f_lambdaArgs;
					final Struct l27_sc1 = (l12__tmp).f_scope;
					final Struct l28_co1 = (l12__tmp).f_compr;
					final Struct_MizIntSeq l29___ = (l12__tmp).f_idents;
					final int l30___ = (l12__tmp).f_pattNr;
					final int l31___ = (l12__tmp).f_trmInfo;
					Struct l32__tmp = at2;
					switch (l32__tmp.getTypeId()) {
					case 42/*MizFraenkelTrm*/: {
						final Struct_MizFraenkelTrm l33__tmp = (Struct_MizFraenkelTrm)l32__tmp;
						final Struct_Vector l34_la2 = l33__tmp.f_lambdaArgs;
						final Struct l35_sc2 = l33__tmp.f_scope;
						final Struct l36_co2 = l33__tmp.f_compr;
						final Struct_Vector l37_la2 = (l33__tmp).f_lambdaArgs;
						final Struct l38_sc2 = (l33__tmp).f_scope;
						final Struct l39_co2 = (l33__tmp).f_compr;
						final Struct_MizIntSeq l40___ = (l33__tmp).f_idents;
						final int l41___ = (l33__tmp).f_pattNr;
						final int l42___ = (l33__tmp).f_trmInfo;
						if ((Module_vector.f_sizeVector(l26_la1)!=Module_vector.f_sizeVector(l37_la2))) {
							return ((Boolean)false);
						} else {
							final Reference<Boolean> l43_ok2 = ((Reference<Boolean>)(new Reference(true)));
							final Reference<Integer> l44_i2b_0 = ((Reference<Integer>)(new Reference(0)));
							final Func0<Boolean> l47_$45 = (Func0<Boolean>)() -> {
								return ((Boolean)(((boolean)l43_ok2.value)&&(((int)l44_i2b_0.value)<Module_vector.f_sizeVector(l26_la1))));
							};
							final Func0<Object> l48_$46 = (Func0<Object>)() -> {
								if (!Module_equalizer.f_mizEqTypsE(l7_eq_0, ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l26_la1, ((int)l44_i2b_0.value))), ((Struct_MizTyp)Module_vector.f_getVectorUnsafe(l37_la2, ((int)l44_i2b_0.value))))) {
									l43_ok2.value = ((Boolean)false);
								} else {
								}
								l44_i2b_0.value = ((Integer)(((int)l44_i2b_0.value)+1));
								return null;
							};
							Module_loop.f_while(l47_$45, l48_$46);
							return ((Boolean)((((boolean)l43_ok2.value)&&Module_equalizer.f_mizEqTrms(l7_eq_0, l27_sc1, l38_sc2))&&Module_equalizer.f_mizEqFrmsE(l7_eq_0, l28_co1, l39_co2)));
						}
					}
					default: {
						return ((Boolean)false);
					}
					}
				}
				default: {
					return ((Boolean)false);
				}
				}
			};
			final Func1<Object,Struct_Vector> l11_doList = (Func1<Object, Struct_Vector>)(Struct_Vector av) -> {
				final int l12_n = Module_vector.f_sizeVector(av);
				final int l15_$14 = (l12_n-2);
				final Func1<Object,Integer> l16_$13 = (Func1<Object, Integer>)(Integer al1) -> {
					if (((l7_eq_0).f_contr==0)) {
						final Struct l17_t1 = ((Struct)Module_vector.f_getVectorUnsafe(av, ((int)al1)));
						final int l21_$19 = (((int)al1)+1);
						final int l22_$20 = (l12_n-1);
						final Func1<Object,Integer> l23_$18 = (Func1<Object, Integer>)(Integer al2) -> {
							if (((l7_eq_0).f_contr==0)) {
								final Struct l24_t2 = ((Struct)Module_vector.f_getVectorUnsafe(av, ((int)al2)));
								if (((Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l17_t1))!=Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l24_t2)))&&((boolean)l10_samePartsE.invoke(l17_t1, l24_t2)))) {
									return Module_equalizer.f_mizUnionTrms(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l17_t1), Module_equalizer.f_mizTrmInfoOf(l24_t2));
								} else {
									return null;
								}
							} else {
								return null;
							}
						};
						return Module_runtime.f_fori(l21_$19, l22_$20, l23_$18);
					} else {
						return null;
					}
				};
				return Module_runtime.f_fori(0, l15_$14, l16_$13);
			};
			if (((l7_eq_0).f_contr==0)) {
				l11_doList.invoke((l7_eq_0).f_frOper);
			} else {
			}
			if (((l7_eq_0).f_contr==0)) {
				l11_doList.invoke((l7_eq_0).f_choiceTerm);
			} else {
			}
			if (((l7_eq_0).f_contr==0)) {
				final Struct_Vector l12_idxF = ((Struct_Vector)Module_vector.f_getVectorUnsafe((l7_eq_0).f_funcTrmIdx, Module_equalizer.g_expFunctor));
				final Func1<Object,Struct_Pair> l14_$13 = (Func1<Object, Struct_Pair>)(Struct_Pair abucket) -> {
					if (((l7_eq_0).f_contr==0)) {
						final int l15_fn = ((int)(abucket).f_first);
						final Struct_MizConstr l16_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coFunctor, l15_fn);
						final Struct_Vector l17_terms = ((Struct_Vector)(abucket).f_second);
						if (Module_correl.f_mizHasProperty(l16_c, Module_correl.g_syIdempotence)) {
							final Func1<Object,Struct> l19_$18 = (Func1<Object, Struct>)(Struct at) -> {
								if (((l7_eq_0).f_contr==0)) {
									final Struct_Pair l20_p2 = Module_equalizer.f_mizGetArgs2(Module_equalizer.f_mizArgsOfE(at), (l16_c).f_firstArg, (l16_c).f_secondArg);
									if ((Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(((Struct)(l20_p2).f_first)))==Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(((Struct)(l20_p2).f_second))))) {
										return Module_equalizer.f_mizUnionTrms(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(at), Module_equalizer.f_mizTrmInfoOf(((Struct)(l20_p2).f_first)));
									} else {
										return null;
									}
								} else {
									return null;
								}
							};
							Module_vector.f_iterVector(l17_terms, ((Func1<Object,Object>)(Func1)l19_$18));
						} else {
						}
						final Func1<Object,Boolean> l20_doInvProj = (Func1<Object, Boolean>)(Boolean aproj) -> {
							final Func1<Object,Struct> l22_$21 = (Func1<Object, Struct>)(Struct at) -> {
								if (((l7_eq_0).f_contr==0)) {
									final Struct l23_arg1 = Module_correl.f_mizTrmListNth(Module_equalizer.f_mizArgsOfE(at), ((l16_c).f_firstArg-1));
									final int l24_argClass = Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l23_arg1));
									final Reference<Struct> l25_cur = ((Reference<Struct>)(new Reference((Module_equalizer.f_mizTrmSAt(l7_eq_0, l24_argClass)).f_eqClass)));
									final Func0<Boolean> l28_$26 = (Func0<Boolean>)() -> {
										boolean l31_$30;
										if (((l7_eq_0).f_contr==0)) {
											final Struct l32_gsymswitch33 = l25_cur.value;
											Struct l33__tmp = l32_gsymswitch33;
											switch (l33__tmp.getTypeId()) {
											case 71/*MizTrmNil*/: {
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
									final Func0<Object> l29_$27 = (Func0<Object>)() -> {
										final Struct l30_gsymswitch34 = l25_cur.value;
										Struct l31__tmp = l30_gsymswitch34;
										switch (l31__tmp.getTypeId()) {
										case 70/*MizTrmElem*/: {
											final Struct_MizTrmElem l32__tmp = (Struct_MizTrmElem)l31__tmp;
											final Struct l33_member = l32__tmp.f_trm;
											final Struct l34_next = l32__tmp.f_next;
											final Struct l35_member = (l32__tmp).f_trm;
											final Struct l36_next = (l32__tmp).f_next;
											boolean l37_hit;
											Struct l38__tmp = l35_member;
											switch (l38__tmp.getTypeId()) {
											case 45/*MizFuncTrm*/: {
												final Struct_MizFuncTrm l39__tmp = (Struct_MizFuncTrm)l38__tmp;
												final int l40_s2 = l39__tmp.f_sort;
												final int l41_fn2 = l39__tmp.f_funcNr;
												final int l42_s2 = (l39__tmp).f_sort;
												final int l43_fn2 = (l39__tmp).f_funcNr;
												final Struct l44___ = (l39__tmp).f_args;
												final int l45___ = (l39__tmp).f_pattNr;
												final int l46___ = (l39__tmp).f_trmInfo;
												l37_hit=((l42_s2==Module_lexicon.g_ikTrmFunctor)&&(l43_fn2==l15_fn));
												break;
											}
											default: {
												l37_hit=false;
												break;
											}
											}
											if ((l37_hit&&Module_equalizer.f_mizEqButLast(l7_eq_0, Module_equalizer.f_mizArgsOfE(at), Module_equalizer.f_mizArgsOfE(l35_member)))) {
												if (((boolean)aproj)) {
													Module_equalizer.f_mizUnionTrms(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(at), Module_equalizer.f_mizTrmInfoOf(l23_arg1));
												} else {
													final Struct l47_inner = Module_correl.f_mizTrmListNth(Module_equalizer.f_mizArgsOfE(l35_member), ((l16_c).f_firstArg-1));
													Module_equalizer.f_mizUnionTrms(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(at), Module_equalizer.f_mizTrmInfoOf(l47_inner));
												}
											} else {
											}
											l25_cur.value = l36_next;
											return null;
										}
										case 71/*MizTrmNil*/: {
											return null;
										}
										default:
											throw new RuntimeException("Unexpected struct in switch: "+l31__tmp.getTypeName());
										}
									};
									return Module_loop.f_while(l28_$26, l29_$27);
								} else {
									return null;
								}
							};
							return Module_vector.f_iterVector(l17_terms, ((Func1<Object,Object>)(Func1)l22_$21));
						};
						if (Module_correl.f_mizHasProperty(l16_c, Module_correl.g_syInvolutiveness)) {
							l20_doInvProj.invoke(((Boolean)false));
						} else {
						}
						if ((((l7_eq_0).f_contr==0)&&Module_correl.f_mizHasProperty(l16_c, Module_correl.g_syProjectivity))) {
							l20_doInvProj.invoke(((Boolean)true));
						} else {
						}
						final int l21_rq = Module_equalizer.f_mizRevReqPub((l7_eq_0).f_idn, l15_fn);
						if ((((l7_eq_0).f_contr==0)&&((((l21_rq==Module_builtin.g_rqUnion)||(l21_rq==Module_builtin.g_rqIntersection))||(l21_rq==Module_builtin.g_rqSubtraction))||(l21_rq==Module_builtin.g_rqSymmetricDifference)))) {
							final Func1<Object,Struct> l23_$22 = (Func1<Object, Struct>)(Struct at) -> {
								if (((l7_eq_0).f_contr==0)) {
									final Struct l24_args = Module_equalizer.f_mizArgsOfE(at);
									final Struct l25_lt1 = Module_correl.f_mizTrmListNth(l24_args, 0);
									final Struct l26_lt2 = Module_correl.f_mizTrmListNth(l24_args, 1);
									final int l27_ti = Module_equalizer.f_mizTrmInfoOf(at);
									final boolean l28_e1 = Module_equalizer.f_mizIsItEmptySet(l7_eq_0, (Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l25_lt1)))).f_eqClass);
									final boolean l29_e2 = Module_equalizer.f_mizIsItEmptySet(l7_eq_0, (Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l26_lt2)))).f_eqClass);
									if ((l21_rq==Module_builtin.g_rqUnion)) {
										if (l28_e1) {
											return Module_equalizer.f_mizUnionTrms(l7_eq_0, l27_ti, Module_equalizer.f_mizTrmInfoOf(l26_lt2));
										} else {
											return null;
										}
									} else {
										if ((l21_rq==Module_builtin.g_rqIntersection)) {
											if (l28_e1) {
												return Module_equalizer.f_mizUnionTrms(l7_eq_0, l27_ti, Module_equalizer.f_mizTrmInfoOf(l25_lt1));
											} else {
												return null;
											}
										} else {
											if ((l21_rq==Module_builtin.g_rqSubtraction)) {
												if (l28_e1) {
													Module_equalizer.f_mizUnionTrms(l7_eq_0, l27_ti, Module_equalizer.f_mizTrmInfoOf(l25_lt1));
												} else {
												}
												if ((((l7_eq_0).f_contr==0)&&l29_e2)) {
													return Module_equalizer.f_mizUnionTrms(l7_eq_0, l27_ti, Module_equalizer.f_mizTrmInfoOf(l25_lt1));
												} else {
													return null;
												}
											} else {
												if (l29_e2) {
													return Module_equalizer.f_mizUnionTrms(l7_eq_0, l27_ti, Module_equalizer.f_mizTrmInfoOf(l25_lt1));
												} else {
													return null;
												}
											}
										}
									}
								} else {
									return null;
								}
							};
							Module_vector.f_iterVector(l17_terms, ((Func1<Object,Object>)(Func1)l23_$22));
						} else {
						}
						if ((((l7_eq_0).f_contr==0)&&(l21_rq==Module_builtin.g_rqSucc))) {
							final Func1<Object,Struct> l25_$24 = (Func1<Object, Struct>)(Struct at) -> {
								if (((l7_eq_0).f_contr==0)) {
									final Struct l26_lt1 = Module_correl.f_mizTrmListNth(Module_equalizer.f_mizArgsOfE(at), 0);
									final Struct_MizTrmSRec l27_ra = Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(l26_lt1)));
									if (((l27_ra).f_numDetermined&&(((l27_ra).f_numValue).f_im==0.0))) {
										return Module_equalizer.f_mizEquateComplexValue(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(at)), (new Struct_MizComplex((((l27_ra).f_numValue).f_re+1.0), 0.0)));
									} else {
										return null;
									}
								} else {
									return null;
								}
							};
							Module_vector.f_iterVector(l17_terms, ((Func1<Object,Object>)(Func1)l25_$24));
						} else {
						}
						if ((((l7_eq_0).f_contr==0)&&((((((l21_rq==Module_builtin.g_rqRealAdd)||(l21_rq==Module_builtin.g_rqRealMult))||(l21_rq==Module_builtin.g_rqRealNeg))||(l21_rq==Module_builtin.g_rqRealInv))||(l21_rq==Module_builtin.g_rqRealDiff))||(l21_rq==Module_builtin.g_rqRealDiv)))) {
							final Func1<Object,Struct> l27_$26 = (Func1<Object, Struct>)(Struct at) -> {
								if (((l7_eq_0).f_contr==0)) {
									return Module_equalizer.f_mizEvalComplexReq(l7_eq_0, l21_rq, at);
								} else {
									return null;
								}
							};
							return Module_vector.f_iterVector(l17_terms, ((Func1<Object,Object>)(Func1)l27_$26));
						} else {
							return null;
						}
					} else {
						return null;
					}
				};
				Module_vector.f_iterVector(l12_idxF, ((Func1<Object,Object>)(Func1)l14_$13));
			} else {
			}
			if (((l7_eq_0).f_contr==0)) {
				Module_equalizer.f_mizProcessReductions(l7_eq_0);
			} else {
			}
			l1_go.value = ((Boolean)(l7_eq_0).f_clash);
			if (!((boolean)l1_go.value)) {
				((Field_clash)l7_eq_0).set_clash(l7_clashBefore);
				return null;
			} else {
				return null;
			}
		};
		return Module_loop.f_while(l4_$2, l6_$3);
	}
	public static final Object f_mizInitAllowedClusters(Struct_MizEqualizer aeq_0) {
		Module_vector.f_clearVector((aeq_0).f_allowedC);
		Module_vector.f_clearVector((aeq_0).f_allowedF);
		final Reference<Integer> l0_i = ((Reference<Integer>)(new Reference(0)));
		final Struct_MizEqualizer l4_eq_0 = aeq_0;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return (((int)l0_i.value)<Module_vector.f_sizeVector(((l4_eq_0).f_clusters).f_conditional));
		};
		final Struct_MizEqualizer l6_eq_0 = aeq_0;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			final Struct_MizCCluster l6_cc = ((Struct_MizCCluster)Module_vector.f_getVectorUnsafe(((l6_eq_0).f_clusters).f_conditional, ((int)l0_i.value)));
			final Struct_MizAttrColl l7_lC = Module_equalizer.f_mizANAllowedAttrsIn(l6_eq_0, ((l6_cc).f_consequent).f_upper);
			if ((Module_vector.f_sizeVector((l7_lC).f_items)>0)) {
				Module_vector.f_pushVector((l6_eq_0).f_allowedC, (new Struct_Pair(l0_i.value, l7_lC)));
			} else {
			}
			l0_i.value = ((Integer)(((int)l0_i.value)+1));
			return null;
		};
		Module_loop.f_while(l3_$1, l5_$2);
		final Reference<Integer> l7_j = ((Reference<Integer>)(new Reference(0)));
		final Struct_MizEqualizer l11_eq_0 = aeq_0;
		final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
			return (((int)l7_j.value)<Module_vector.f_sizeVector(((l11_eq_0).f_clusters).f_functor));
		};
		final Struct_MizEqualizer l13_eq_0 = aeq_0;
		final Func0<Object> l12_$9 = (Func0<Object>)() -> {
			final Struct_MizFCluster l13_fc = ((Struct_MizFCluster)Module_vector.f_getVectorUnsafe(((l13_eq_0).f_clusters).f_functor, ((int)l7_j.value)));
			final Struct_MizAttrColl l14_lF = Module_equalizer.f_mizANAllowedAttrsIn(l13_eq_0, ((l13_fc).f_consequent).f_upper);
			if ((Module_vector.f_sizeVector((l14_lF).f_items)>0)) {
				Module_vector.f_pushVector((l13_eq_0).f_allowedF, (new Struct_Pair(l7_j.value, l14_lF)));
			} else {
			}
			l7_j.value = ((Integer)(((int)l7_j.value)+1));
			return null;
		};
		return Module_loop.f_while(l10_$8, l12_$9);
	}
	public static final Object f_mizInitEmptyInEqClass(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final Struct_MizEqualizer l2_eq_0 = aeq_0;
		final Func2<Object,Integer, Integer> l1_doOne = (Func2<Object, Integer, Integer>)(Integer aattrRq, Integer afuncRq) -> {
			final int l2_fRq = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, ((int)afuncRq));
			final int l3_aRq = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, ((int)aattrRq));
			if ((l2_fRq!=0)) {
				final Reference<Integer> l4_ii = ((Reference<Integer>)(new Reference(1)));
				final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
					return (((int)l4_ii.value)<=Module_equalizer.f_mizTrmNbr(l2_eq_0));
				};
				final Func0<Object> l8_$6 = (Func0<Object>)() -> {
					final Struct_MizTrmSRec l9_rec = Module_equalizer.f_mizTrmSAt(l2_eq_0, ((int)l4_ii.value));
					final Struct l10_gsymswitch23 = (l9_rec).f_eqClass;
					boolean l11_live;
					Struct l12__tmp = l10_gsymswitch23;
					switch (l12__tmp.getTypeId()) {
					case 71/*MizTrmNil*/: {
						l11_live=false;
						break;
					}
					default: {
						l11_live=true;
						break;
					}
					}
					if (l11_live) {
						final Struct l14_gsymswitch24 = Module_equalizer.f_mizGetAttr(l2_eq_0, (l9_rec).f_superCluster, l3_aRq, ((Struct)SingletonStructs.str_MizTrmNil));
						Struct l15__tmp = l14_gsymswitch24;
						switch (l15__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l16__tmp = (Struct_Some)l15__tmp;
							final Object l17_a = l16__tmp.f_value;
							final Struct_MizAttr l18_a = ((Struct_MizAttr)(l16__tmp).f_value);
							if (((l18_a).f_neg==1)) {
								final Struct l19_lUnique = Module_equalizer.f_mizYTerm(l2_eq_0, Module_correl.f_mizNewFuncTrm(l2_fRq, ((Struct)SingletonStructs.str_MizTrmNil)));
								if ((((int)l4_ii.value)!=Module_equalizer.f_mizTrmInfoOf(l19_lUnique))) {
									Module_equalizer.f_mizAddEquality((l2_eq_0).f_eqPend, ((int)l4_ii.value), Module_equalizer.f_mizTrmInfoOf(l19_lUnique));
								} else {
								}
							} else {
							}
							break;
						}
						case 82/*None*/: {
							break;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l15__tmp.getTypeName());
						}
					} else {
					}
					l4_ii.value = ((Integer)(((int)l4_ii.value)+1));
					return null;
				};
				return Module_loop.f_while(l7_$5, l8_$6);
			} else {
				return null;
			}
		};
		l1_doOne.invoke(((Integer)Module_builtin.g_rqEmpty), ((Integer)Module_builtin.g_rqEmptySet));
		return l1_doOne.invoke(((Integer)Module_builtin.g_rqZero), ((Integer)Module_builtin.g_rqZeroNumber));
	}
	public static final Object f_mizInitPolynomialValues(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		if ((Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqComplex)!=0)) {
			final Struct_MizNatFunc l1_pend = Module_mobjects.f_mizInitNatSet(8);
			final Struct_MizComplex l2_cOne = (new Struct_MizComplex(1.0, 0.0));
			final Reference<Integer> l3_t = ((Reference<Integer>)(new Reference(1)));
			final Struct_MizEqualizer l7_eq_0 = aeq_0;
			final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
				return (((int)l3_t.value)<=Module_equalizer.f_mizTrmNbr(l7_eq_0));
			};
			final Struct_MizEqualizer l9_eq_0 = aeq_0;
			final Func0<Object> l8_$5 = (Func0<Object>)() -> {
				final Struct_MizTrmSRec l9_rec = Module_equalizer.f_mizTrmSAt(l9_eq_0, ((int)l3_t.value));
				final Struct l10_gsymswitch89 = (l9_rec).f_eqClass;
				boolean l11_live;
				Struct l12__tmp = l10_gsymswitch89;
				switch (l12__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l11_live=false;
					break;
				}
				default: {
					l11_live=true;
					break;
				}
				}
				if (l11_live) {
					final int l14_tt = ((int)l3_t.value);
					final Reference<Struct> l15_cur = ((Reference<Struct>)(new Reference((l9_rec).f_eqClass)));
					final Func0<Boolean> l18_$16 = (Func0<Boolean>)() -> {
						final Struct l19_gsymswitch90 = l15_cur.value;
						Struct l20__tmp = l19_gsymswitch90;
						switch (l20__tmp.getTypeId()) {
						case 71/*MizTrmNil*/: {
							return ((Boolean)false);
						}
						default: {
							return ((Boolean)true);
						}
						}
					};
					final Func0<Object> l19_$17 = (Func0<Object>)() -> {
						final Struct l20_gsymswitch91 = l15_cur.value;
						Struct l21__tmp = l20_gsymswitch91;
						switch (l21__tmp.getTypeId()) {
						case 70/*MizTrmElem*/: {
							final Struct_MizTrmElem l22__tmp = (Struct_MizTrmElem)l21__tmp;
							final Struct l23_trm = l22__tmp.f_trm;
							final Struct l24_next = l22__tmp.f_next;
							final Struct l25_trm = (l22__tmp).f_trm;
							final Struct l26_next = (l22__tmp).f_next;
							final int l27_srt = Module_correl.f_mizTrmSort(l25_trm);
							if (((((((l27_srt==Module_lexicon.g_ikTrmSchFunc)||(l27_srt==Module_lexicon.g_ikTrmPrivFunc))||(l27_srt==Module_lexicon.g_ikTrmAggreg))||(l27_srt==Module_lexicon.g_ikTrmSelector))||(l27_srt==Module_lexicon.g_ikTrmFraenkel))||(l27_srt==Module_lexicon.g_ikTrmEqConst))) {
								if (Module_equalizer.f_mizIsComplexNumberE(l9_eq_0, (l9_rec).f_superCluster)) {
									Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizPolyWithMonomial(l2_cOne, l14_tt));
								} else {
								}
							} else {
								if ((l27_srt==Module_lexicon.g_ikTrmInfConst)) {
									if ((l9_rec).f_numDetermined) {
										Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizPolyWithNumeric((l9_rec).f_numValue));
										Module_mobjects.f_mizNatInsertElem(l1_pend, l14_tt);
									} else {
										if (Module_equalizer.f_mizIsComplexNumberE(l9_eq_0, (l9_rec).f_superCluster)) {
											if ((Module_vector.f_sizeVector((l9_rec).f_polynomialValues)==0)) {
												Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizPolyWithMonomial(l2_cOne, l14_tt));
											} else {
											}
										} else {
										}
									}
								} else {
									if ((l27_srt==Module_lexicon.g_ikTrmFunctor)) {
										final Struct_MizFuncTrm l28_ft = Module_equalizer.f_mizEqAsFunc(l25_trm);
										final int l29_rq = Module_equalizer.f_mizRevReqPub((l9_eq_0).f_idn, (l28_ft).f_funcNr);
										if ((l29_rq==Module_builtin.g_rqImaginaryUnit)) {
											Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizPolyWithNumeric((new Struct_MizComplex(0.0, 1.0))));
											Module_mobjects.f_mizNatInsertElem(l1_pend, l14_tt);
										} else {
											if ((((l29_rq==Module_builtin.g_rqRealAdd)||(l29_rq==Module_builtin.g_rqRealMult))||(l29_rq==Module_builtin.g_rqRealDiff))) {
												final int l30_a1 = Module_equalizer.f_mizClassOf(l9_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth((l28_ft).f_args, 0)));
												final int l31_a2 = Module_equalizer.f_mizClassOf(l9_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth((l28_ft).f_args, 1)));
												final Struct_MizPolynomial l32_p1 = Module_polynom.f_mizPolyWithMonomial(l2_cOne, l30_a1);
												final Struct_MizPolynomial l33_p2 = Module_polynom.f_mizPolyWithMonomial(l2_cOne, l31_a2);
												Struct_MizPolynomial l34_pv;
												if ((l29_rq==Module_builtin.g_rqRealAdd)) {
													l34_pv=Module_polynom.f_mizAddPolynomials(l32_p1, l33_p2);
												} else {
													if ((l29_rq==Module_builtin.g_rqRealMult)) {
														l34_pv=Module_polynom.f_mizMultPolynomials(l32_p1, l33_p2);
													} else {
														l34_pv=Module_polynom.f_mizDiffPolynomials(l32_p1, l33_p2);
													}
												}
												Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, l34_pv);
												Module_mobjects.f_mizNatInsertElem(l1_pend, l14_tt);
											} else {
												if ((l29_rq==Module_builtin.g_rqRealNeg)) {
													final int l35_a1 = Module_equalizer.f_mizClassOf(l9_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth((l28_ft).f_args, 0)));
													final Struct_MizPolynomial l36_p1 = Module_polynom.f_mizPolyWithMonomial(l2_cOne, l35_a1);
													Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizNMultPolynomial((new Struct_MizComplex((-1.0), 0.0)), l36_p1));
													Module_mobjects.f_mizNatInsertElem(l1_pend, l14_tt);
												} else {
													if ((l29_rq==Module_builtin.g_rqRealInv)) {
														Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizPolyWithMonomial(l2_cOne, l14_tt));
													} else {
														if ((l29_rq==Module_builtin.g_rqRealDiv)) {
															final int l37_a1 = Module_equalizer.f_mizClassOf(l9_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth((l28_ft).f_args, 0)));
															final Struct_MizTrmSRec l38_a2r = Module_equalizer.f_mizTrmSAt(l9_eq_0, Module_equalizer.f_mizClassOf(l9_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth((l28_ft).f_args, 1))));
															Struct l39_inv;
															if (((l38_a2r).f_numDetermined&&!((((l38_a2r).f_numValue).f_re==0.0)&&(((l38_a2r).f_numValue).f_im==0.0)))) {
																final double l40_d = ((((l38_a2r).f_numValue).f_re*((l38_a2r).f_numValue).f_re)+(((l38_a2r).f_numValue).f_im*((l38_a2r).f_numValue).f_im));
																l39_inv=(new Struct_Some((new Struct_MizComplex((((l38_a2r).f_numValue).f_re/l40_d), ((-((l38_a2r).f_numValue).f_im)/l40_d)))));
															} else {
																l39_inv=((Struct)SingletonStructs.str_None);
															}
															Struct l41__tmp = l39_inv;
															switch (l41__tmp.getTypeId()) {
															case 90/*Some*/: {
																final Struct_Some l42__tmp = (Struct_Some)l41__tmp;
																final Object l43_iv = l42__tmp.f_value;
																final Struct_MizComplex l44_iv = ((Struct_MizComplex)(l42__tmp).f_value);
																final Struct_MizPolynomial l45_p1 = Module_polynom.f_mizPolyWithMonomial(l2_cOne, l37_a1);
																Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizNMultPolynomial(l44_iv, l45_p1));
																Module_mobjects.f_mizNatInsertElem(l1_pend, l14_tt);
																break;
															}
															case 82/*None*/: {
																Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizPolyWithMonomial(l2_cOne, l14_tt));
																break;
															}
															default:
																throw new RuntimeException("Unexpected struct in switch: "+l41__tmp.getTypeName());
															}
														} else {
															if ((l9_rec).f_numDetermined) {
																Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizPolyWithNumeric((l9_rec).f_numValue));
																Module_mobjects.f_mizNatInsertElem(l1_pend, l14_tt);
															} else {
																if (Module_equalizer.f_mizIsComplexNumberE(l9_eq_0, (l9_rec).f_superCluster)) {
																	Module_equalizer.f_mizPolyValInsert((l9_rec).f_polynomialValues, Module_polynom.f_mizPolyWithMonomial(l2_cOne, l14_tt));
																} else {
																}
															}
														}
													}
												}
											}
										}
									} else {
									}
								}
							}
							l15_cur.value = l26_next;
							return null;
						}
						case 71/*MizTrmNil*/: {
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l21__tmp.getTypeName());
						}
					};
					Module_loop.f_while(l18_$16, l19_$17);
					if ((Module_vector.f_sizeVector((l9_rec).f_polynomialValues)>1)) {
						final Reference<Integer> l20_k = ((Reference<Integer>)(new Reference(0)));
						final Reference<Boolean> l21_done1 = ((Reference<Boolean>)(new Reference(false)));
						final Func0<Boolean> l24_$22 = (Func0<Boolean>)() -> {
							return ((Boolean)(!((boolean)l21_done1.value)&&(((int)l20_k.value)<Module_vector.f_sizeVector((l9_rec).f_polynomialValues))));
						};
						final Func0<Object> l25_$23 = (Func0<Object>)() -> {
							if ((Module_polynom.f_mizPolyIsVariable(((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l9_rec).f_polynomialValues, ((int)l20_k.value))))==l14_tt)) {
								Module_vector.f_removeVector((l9_rec).f_polynomialValues, ((int)l20_k.value));
								l21_done1.value = ((Boolean)true);
								return null;
							} else {
								l20_k.value = ((Integer)(((int)l20_k.value)+1));
								return null;
							}
						};
						Module_loop.f_while(l24_$22, l25_$23);
					} else {
					}
				} else {
				}
				l3_t.value = ((Integer)(((int)l3_t.value)+1));
				return null;
			};
			Module_loop.f_while(l6_$4, l8_$5);
			return Module_equalizer.f_mizSubstitutePendingVars(aeq_0, l1_pend);
		} else {
			return null;
		}
	}
	public static final Object f_mizInitStructuresInEqClass(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final Reference<Integer> l1_ii = ((Reference<Integer>)(new Reference(1)));
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l5_eq_0).f_contr==0)&&(((int)l1_ii.value)<=Module_equalizer.f_mizTrmNbr(l5_eq_0))));
		};
		final Struct_MizEqualizer l7_eq_0 = aeq_0;
		final Func0<Object> l6_$3 = (Func0<Object>)() -> {
			final Struct_MizTrmSRec l7_rec = Module_equalizer.f_mizTrmSAt(l7_eq_0, ((int)l1_ii.value));
			final Struct l8_gsymswitch25 = (l7_rec).f_eqClass;
			boolean l9_live;
			Struct l10__tmp = l8_gsymswitch25;
			switch (l10__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				l9_live=false;
				break;
			}
			default: {
				l9_live=true;
				break;
			}
			}
			if (l9_live) {
				final Reference<Integer> l12_strictStruct = ((Reference<Integer>)(new Reference(0)));
				final Struct_Vector l15_$14 = ((l7_rec).f_superCluster).f_items;
				final Func1<Object,Struct_MizAttr> l16_$13 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
					final Struct_MizConstr l17_ca = Module_correl.f_mizConstr(l0_env, Module_correl.g_coAttribute, (aa).f_attrNr);
					final boolean l18_isStrict = (Module_correl.f_mizHasProperty(l17_ca, Module_correl.g_syAbstractness)&&((aa).f_neg==1));
					if ((l18_isStrict&&((l7_eq_0).f_contr==0))) {
						final Struct l19_gsymswitch26 = (l17_ca).f_typ;
						Struct l20__tmp = l19_gsymswitch26;
						switch (l20__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l21__tmp = (Struct_Some)l20__tmp;
							final Object l22_ct = l21__tmp.f_value;
							final Struct_MizTyp l23_ct = ((Struct_MizTyp)(l21__tmp).f_value);
							if ((((int)l12_strictStruct.value)==0)) {
								l12_strictStruct.value = ((Integer)(l23_ct).f_modNr);
								return null;
							} else {
								if (((l23_ct).f_modNr!=((int)l12_strictStruct.value))) {
									return Module_equalizer.f_mizSetContr(l7_eq_0, 12);
								} else {
									return null;
								}
							}
						}
						case 82/*None*/: {
							return null;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l20__tmp.getTypeName());
						}
					} else {
						return null;
					}
				};
				Module_vector.f_iterVector(l15_$14, ((Func1<Object,Object>)(Func1)l16_$13));
			} else {
			}
			l1_ii.value = ((Integer)(((int)l1_ii.value)+1));
			return null;
		};
		return Module_loop.f_while(l4_$2, l6_$3);
	}
	public static final Object f_mizInitSuperClusterForComplex(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		if ((Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqComplex)!=0)) {
			final Reference<Integer> l1_ii = ((Reference<Integer>)(new Reference(1)));
			final Struct_MizEqualizer l5_eq_0 = aeq_0;
			final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
				return ((Boolean)(((l5_eq_0).f_contr==0)&&(((int)l1_ii.value)<=Module_equalizer.f_mizTrmNbr(l5_eq_0))));
			};
			final Struct_MizEqualizer l7_eq_0 = aeq_0;
			final Func0<Object> l6_$3 = (Func0<Object>)() -> {
				final Struct_MizTrmSRec l7_rec = Module_equalizer.f_mizTrmSAt(l7_eq_0, ((int)l1_ii.value));
				final Reference<Struct> l8_cur = ((Reference<Struct>)(new Reference((l7_rec).f_eqClass)));
				final Func0<Boolean> l11_$9 = (Func0<Boolean>)() -> {
					boolean l14_$13;
					if (((l7_eq_0).f_contr==0)) {
						final Struct l15_gsymswitch84 = l8_cur.value;
						Struct l16__tmp = l15_gsymswitch84;
						switch (l16__tmp.getTypeId()) {
						case 71/*MizTrmNil*/: {
							l14_$13=false;
							break;
						}
						default: {
							l14_$13=true;
							break;
						}
						}
					} else {
						l14_$13=false;
					}
					return ((Boolean)l14_$13);
				};
				final Func0<Object> l12_$10 = (Func0<Object>)() -> {
					final Struct l13_gsymswitch85 = l8_cur.value;
					Struct l14__tmp = l13_gsymswitch85;
					switch (l14__tmp.getTypeId()) {
					case 70/*MizTrmElem*/: {
						final Struct_MizTrmElem l15__tmp = (Struct_MizTrmElem)l14__tmp;
						final Struct l16_trm = l15__tmp.f_trm;
						final Struct l17_next = l15__tmp.f_next;
						final Struct l18_trm = (l15__tmp).f_trm;
						final Struct l19_next = (l15__tmp).f_next;
						Struct l20__tmp = l18_trm;
						switch (l20__tmp.getTypeId()) {
						case 77/*MizVarTrm*/: {
							final Struct_MizVarTrm l21__tmp = (Struct_MizVarTrm)l20__tmp;
							final int l22_srt = l21__tmp.f_sort;
							final int l23_srt = (l21__tmp).f_sort;
							final int l24___ = (l21__tmp).f_varNr;
							final int l25___ = (l21__tmp).f_pattNr;
							final int l26___ = (l21__tmp).f_trmInfo;
							if (((l23_srt==Module_lexicon.g_ikTrmInfConst)&&(l7_rec).f_numDetermined)) {
								Module_equalizer.f_mizInsertComplexE(l7_eq_0, ((int)l1_ii.value));
							} else {
							}
							break;
						}
						case 45/*MizFuncTrm*/: {
							final Struct_MizFuncTrm l21__tmp = (Struct_MizFuncTrm)l20__tmp;
							final int l27_srt = l21__tmp.f_sort;
							final int l28_funcNr = l21__tmp.f_funcNr;
							final Struct l29_args = l21__tmp.f_args;
							final int l30_srt = (l21__tmp).f_sort;
							final int l31_funcNr = (l21__tmp).f_funcNr;
							final Struct l32_args = (l21__tmp).f_args;
							final int l33___ = (l21__tmp).f_pattNr;
							final int l34___ = (l21__tmp).f_trmInfo;
							if ((l30_srt==Module_lexicon.g_ikTrmFunctor)) {
								final int l35_rq = Module_equalizer.f_mizRevReqPub((l7_eq_0).f_idn, l31_funcNr);
								if ((l35_rq==Module_builtin.g_rqImaginaryUnit)) {
									Module_equalizer.f_mizInsertComplexE(l7_eq_0, ((int)l1_ii.value));
								} else {
									if (((((l35_rq==Module_builtin.g_rqRealAdd)||(l35_rq==Module_builtin.g_rqRealMult))||(l35_rq==Module_builtin.g_rqRealDiff))||(l35_rq==Module_builtin.g_rqRealDiv))) {
										Module_equalizer.f_mizInsertComplexE(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth(l32_args, 0))));
										if (((l7_eq_0).f_contr==0)) {
											Module_equalizer.f_mizInsertComplexE(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth(l32_args, 1))));
										} else {
										}
										if (((l7_eq_0).f_contr==0)) {
											Module_equalizer.f_mizInsertComplexE(l7_eq_0, ((int)l1_ii.value));
										} else {
										}
									} else {
										if (((l35_rq==Module_builtin.g_rqRealNeg)||(l35_rq==Module_builtin.g_rqRealInv))) {
											Module_equalizer.f_mizInsertComplexE(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_correl.f_mizTrmListNth(l32_args, 0))));
											if (((l7_eq_0).f_contr==0)) {
												Module_equalizer.f_mizInsertComplexE(l7_eq_0, ((int)l1_ii.value));
											} else {
											}
										} else {
										}
									}
								}
							} else {
							}
							break;
						}
						default: {
							break;
						}
						}
						l8_cur.value = l19_next;
						return null;
					}
					case 71/*MizTrmNil*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l14__tmp.getTypeName());
					}
				};
				Module_loop.f_while(l11_$9, l12_$10);
				l1_ii.value = ((Integer)(((int)l1_ii.value)+1));
				return null;
			};
			return Module_loop.f_while(l4_$2, l6_$3);
		} else {
			return null;
		}
	}
	public static final Object f_mizInsertAttrE(Struct_MizEqualizer aeq_0, Struct_MizAttrColl ac, int aattrNr, int aneg, Struct aargs) {
		return Module_correl.f_mizAttrCollInsert((aeq_0).f_env, ac, (new Struct_MizAttr(aneg, aattrNr, aargs, false, 0)));
	}
	public static final Object f_mizInsertComplexE(Struct_MizEqualizer aeq_0, int anr) {
		final int l0_rqC = Module_builtin.f_mizBuiltIn(((aeq_0).f_env).f_builtIn, Module_builtin.g_rqComplex);
		final Struct_MizTrmSRec l1_rec = Module_equalizer.f_mizTrmSAt(aeq_0, Module_equalizer.f_mizClassOf(aeq_0, anr));
		if (!Module_equalizer.f_mizIsComplexNumberE(aeq_0, (l1_rec).f_superCluster)) {
			Module_equalizer.f_mizInsertAttrE(aeq_0, (l1_rec).f_superCluster, l0_rqC, 1, ((Struct)SingletonStructs.str_MizTrmNil));
			if (!((l1_rec).f_superCluster).f_consistent) {
				return Module_equalizer.f_mizSetContr(aeq_0, 13);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	public static final Object f_mizInsertNonEmpty(Struct_MizEqualizer aeq_0, int ai1, int ai2) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_rqE = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEmpty);
		if ((l1_rqE>0)) {
			final Struct l2_gsymswitch19 = Module_equalizer.f_mizGetAttr(aeq_0, (Module_equalizer.f_mizTrmSAt(aeq_0, ai1)).f_superCluster, l1_rqE, ((Struct)SingletonStructs.str_MizTrmNil));
			Struct l3__tmp = l2_gsymswitch19;
			switch (l3__tmp.getTypeId()) {
			case 90/*Some*/: {
				final Struct_Some l4__tmp = (Struct_Some)l3__tmp;
				final Object l5_a = l4__tmp.f_value;
				final Struct_MizAttr l6_a = ((Struct_MizAttr)(l4__tmp).f_value);
				if (((l6_a).f_neg==1)) {
					Module_equalizer.f_mizInsertAttrE(aeq_0, (Module_equalizer.f_mizTrmSAt(aeq_0, ai2)).f_superCluster, l1_rqE, 0, ((Struct)SingletonStructs.str_MizTrmNil));
					if (!((Module_equalizer.f_mizTrmSAt(aeq_0, ai2)).f_superCluster).f_consistent) {
						return Module_equalizer.f_mizSetContr(aeq_0, 22);
					} else {
						return null;
					}
				} else {
					return null;
				}
			}
			case 82/*None*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l3__tmp.getTypeName());
			}
		} else {
			return null;
		}
	}
	public static final Object f_mizInsertNonEmptyPub(Struct_MizEqualizer aeq_0, int ai1, int ai2) {
		return Module_equalizer.f_mizInsertNonEmpty(aeq_0, ai1, ai2);
	}
	public static final Object f_mizInsertNonZero(Struct_MizEqualizer aeq_0, int ai1, int ai2) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_rqZ = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqZero);
		if ((l1_rqZ>0)) {
			final Struct l2_gsymswitch20 = Module_equalizer.f_mizGetAttr(aeq_0, (Module_equalizer.f_mizTrmSAt(aeq_0, ai1)).f_superCluster, l1_rqZ, ((Struct)SingletonStructs.str_MizTrmNil));
			Struct l3__tmp = l2_gsymswitch20;
			switch (l3__tmp.getTypeId()) {
			case 90/*Some*/: {
				final Struct_Some l4__tmp = (Struct_Some)l3__tmp;
				final Object l5_a = l4__tmp.f_value;
				final Struct_MizAttr l6_a = ((Struct_MizAttr)(l4__tmp).f_value);
				if (((l6_a).f_neg==1)) {
					Module_equalizer.f_mizInsertAttrE(aeq_0, (Module_equalizer.f_mizTrmSAt(aeq_0, ai2)).f_superCluster, l1_rqZ, 0, ((Struct)SingletonStructs.str_MizTrmNil));
					if (!((Module_equalizer.f_mizTrmSAt(aeq_0, ai2)).f_superCluster).f_consistent) {
						return Module_equalizer.f_mizSetContr(aeq_0, 23);
					} else {
						return null;
					}
				} else {
					return null;
				}
			}
			case 82/*None*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l3__tmp.getTypeName());
			}
		} else {
			return null;
		}
	}
	public static final Object f_mizInsertNonZeroPub(Struct_MizEqualizer aeq_0, int ai1, int ai2) {
		return Module_equalizer.f_mizInsertNonZero(aeq_0, ai1, ai2);
	}
	public static final Object f_mizInsertType(Struct_MizEqualizer aeq_0, Struct_MizTyp afTyp0, int aaTrmNr) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final Struct_MizTrmSRec l1_rec = Module_equalizer.f_mizTrmSAt(aeq_0, aaTrmNr);
		final Reference<Struct_MizTyp> l2_fTyp = ((Reference<Struct_MizTyp>)(new Reference(afTyp0)));
		final Reference<Boolean> l3_stop = ((Reference<Boolean>)(new Reference(false)));
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			return ((Boolean)!((boolean)l3_stop.value));
		};
		final Struct_MizEqualizer l8_eq_0 = aeq_0;
		final Func0<Object> l7_$5 = (Func0<Object>)() -> {
			final Reference<Boolean> l8_found = ((Reference<Boolean>)(new Reference(false)));
			final Struct_Vector l11_$10 = (l1_rec).f_xTypClass;
			final Func1<Object,Struct_MizTyp> l12_$9 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
				if ((((!((boolean)l8_found.value)&&((axt).f_sort==(l2_fTyp.value).f_sort))&&((axt).f_modNr==(l2_fTyp.value).f_modNr))&&Module_equalizer.f_mizEqTrmListsE(l8_eq_0, (axt).f_modArgs, (l2_fTyp.value).f_modArgs))) {
					l8_found.value = ((Boolean)true);
					final Struct_MizAttrColl l16_$14 = (l2_fTyp.value).f_upper;
					final Struct_MizAttrColl l17_$15 = (l1_rec).f_superCluster;
					final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l18_$13 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
						return ((Boolean)Module_equalizer.f_mizEqAttrsE(l8_eq_0, ax, ay));
					};
					if (!Module_correl_inst.f_mizAttrCollIsSubsetOf(l0_env, l16_$14, l17_$15, l18_$13)) {
						final Struct_Vector l21_$20 = ((l2_fTyp.value).f_upper).f_items;
						final Func1<Object,Struct_MizAttr> l22_$19 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
							return Module_correl.f_mizAttrCollInsert(l0_env, (l1_rec).f_superCluster, Wrappers.w_mizCopyAttr.invoke(aa));
						};
						Module_vector.f_iterVector(l21_$20, ((Func1<Object,Object>)(Func1)l22_$19));
						if (!((l1_rec).f_superCluster).f_consistent) {
							return Module_equalizer.f_mizSetContr(l8_eq_0, 1);
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
			Module_vector.f_iterVector(l11_$10, ((Func1<Object,Object>)(Func1)l12_$9));
			if (((boolean)l8_found.value)) {
				l3_stop.value = ((Boolean)true);
				return null;
			} else {
				Module_equalizer.f_mizYType(l8_eq_0, l2_fTyp.value);
				final Struct_Vector l15_$14 = ((l2_fTyp.value).f_upper).f_items;
				final Func1<Object,Struct_MizAttr> l16_$13 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
					return Module_correl.f_mizAttrCollInsert(l0_env, (l1_rec).f_superCluster, Wrappers.w_mizCopyAttr.invoke(aa));
				};
				Module_vector.f_iterVector(l15_$14, ((Func1<Object,Object>)(Func1)l16_$13));
				if (!((l1_rec).f_superCluster).f_consistent) {
					Module_equalizer.f_mizSetContr(l8_eq_0, 2);
				} else {
				}
				Module_vector.f_pushVector((l1_rec).f_xTypClass, l2_fTyp.value);
				if ((((l2_fTyp.value).f_sort!=Module_lexicon.g_ikTypMode)||((l2_fTyp.value).f_modNr==1))) {
					l3_stop.value = ((Boolean)true);
					return null;
				} else {
					final Struct l17_gsymswitch0 = Module_correl_inst.f_mizWidening(l0_env, l2_fTyp.value);
					Struct l18__tmp = l17_gsymswitch0;
					switch (l18__tmp.getTypeId()) {
					case 90/*Some*/: {
						final Struct_Some l19__tmp = (Struct_Some)l18__tmp;
						final Object l20_w = l19__tmp.f_value;
						final Struct_MizTyp l21_w = ((Struct_MizTyp)(l19__tmp).f_value);
						l2_fTyp.value = l21_w;
						return null;
					}
					case 82/*None*/: {
						l3_stop.value = ((Boolean)true);
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l18__tmp.getTypeName());
					}
				}
			}
		};
		Module_loop.f_while(l6_$4, l7_$5);
		final Reference<Integer> l9_last = ((Reference<Integer>)(new Reference(Module_vector.f_sizeVector((l1_rec).f_xTypClass))));
		final Reference<Struct_MizTyp> l10_cur = ((Reference<Struct_MizTyp>)(new Reference(l2_fTyp.value)));
		final Reference<Boolean> l11_structGo = ((Reference<Boolean>)(new Reference(((l10_cur.value).f_sort==Module_lexicon.g_ikTypStruct))));
		final Func0<Boolean> l14_$12 = (Func0<Boolean>)() -> {
			return l11_structGo.value;
		};
		final Struct_MizEqualizer l16_eq_0 = aeq_0;
		final Func0<Object> l15_$13 = (Func0<Object>)() -> {
			final Struct_Vector l16_prefs = Module_correl.f_mizConstrPrefixes(l0_env, (l10_cur.value).f_modNr);
			final Func1<Object,Struct_MizTyp> l18_$17 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp ap) -> {
				final Struct_MizTyp l19_lTypPtr = Module_correl_inst.f_mizInstTyp(l0_env, ap, (l10_cur.value).f_modArgs);
				Module_equalizer.f_mizYType(l16_eq_0, l19_lTypPtr);
				final Reference<Boolean> l20_dup = ((Reference<Boolean>)(new Reference(false)));
				final Struct_Vector l23_$22 = (l1_rec).f_xTypClass;
				final Func1<Object,Struct_MizTyp> l24_$21 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
					if (((((axt).f_sort==(l19_lTypPtr).f_sort)&&((axt).f_modNr==(l19_lTypPtr).f_modNr))&&Module_equalizer.f_mizEqTrmListsE(l16_eq_0, (axt).f_modArgs, (l19_lTypPtr).f_modArgs))) {
						l20_dup.value = ((Boolean)true);
						return null;
					} else {
						return null;
					}
				};
				Module_vector.f_iterVector(l23_$22, ((Func1<Object,Object>)(Func1)l24_$21));
				if (!((boolean)l20_dup.value)) {
					return Module_vector.f_pushVector((l1_rec).f_xTypClass, l19_lTypPtr);
				} else {
					return null;
				}
			};
			Module_vector.f_iterVector(l16_prefs, ((Func1<Object,Object>)(Func1)l18_$17));
			if ((((int)l9_last.value)>=Module_vector.f_sizeVector((l1_rec).f_xTypClass))) {
				l11_structGo.value = ((Boolean)false);
				return null;
			} else {
				l10_cur.value = ((Struct_MizTyp)Module_vector.f_getVectorUnsafe((l1_rec).f_xTypClass, ((int)l9_last.value)));
				l9_last.value = ((Integer)(((int)l9_last.value)+1));
				l11_structGo.value = ((Boolean)true);
				return null;
			}
		};
		return Module_loop.f_while(l14_$12, l15_$13);
	}
	public static final boolean f_mizIsComplexNumberE(Struct_MizEqualizer aeq_0, Struct_MizAttrColl asc) {
		final int l0_rqC = Module_builtin.f_mizBuiltIn(((aeq_0).f_env).f_builtIn, Module_builtin.g_rqComplex);
		final Struct l1_gsymswitch83 = Module_equalizer.f_mizGetAttr(aeq_0, asc, l0_rqC, ((Struct)SingletonStructs.str_MizTrmNil));
		Struct l2__tmp = l1_gsymswitch83;
		switch (l2__tmp.getTypeId()) {
		case 90/*Some*/: {
			final Struct_Some l3__tmp = (Struct_Some)l2__tmp;
			final Object l4_a = l3__tmp.f_value;
			final Struct_MizAttr l5_a = ((Struct_MizAttr)(l3__tmp).f_value);
			return ((l5_a).f_neg==1);
		}
		case 82/*None*/: {
			return false;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
		}
	}
	public static final boolean f_mizIsItEmptySet(Struct_MizEqualizer aeq_0, Struct acls) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_rqES = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEmptySet);
		final Reference<Boolean> l2_found = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Struct> l3_cur = ((Reference<Struct>)(new Reference(acls)));
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			boolean l9_$8;
			if (!((boolean)l2_found.value)) {
				final Struct l10_gsymswitch31 = l3_cur.value;
				Struct l11__tmp = l10_gsymswitch31;
				switch (l11__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
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
			final Struct l8_gsymswitch32 = l3_cur.value;
			Struct l9__tmp = l8_gsymswitch32;
			switch (l9__tmp.getTypeId()) {
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l10__tmp = (Struct_MizTrmElem)l9__tmp;
				final Struct l11_trm = l10__tmp.f_trm;
				final Struct l12_next = l10__tmp.f_next;
				final Struct l13_trm = (l10__tmp).f_trm;
				final Struct l14_next = (l10__tmp).f_next;
				Struct l15__tmp = l13_trm;
				switch (l15__tmp.getTypeId()) {
				case 45/*MizFuncTrm*/: {
					final Struct_MizFuncTrm l16__tmp = (Struct_MizFuncTrm)l15__tmp;
					final int l17_srt = l16__tmp.f_sort;
					final int l18_funcNr = l16__tmp.f_funcNr;
					final int l19_srt = (l16__tmp).f_sort;
					final int l20_funcNr = (l16__tmp).f_funcNr;
					final Struct l21___ = (l16__tmp).f_args;
					final int l22___ = (l16__tmp).f_pattNr;
					final int l23___ = (l16__tmp).f_trmInfo;
					if ((((l19_srt==Module_lexicon.g_ikTrmFunctor)&&(l1_rqES!=0))&&(l20_funcNr==l1_rqES))) {
						l2_found.value = ((Boolean)true);
					} else {
					}
					break;
				}
				default: {
					break;
				}
				}
				l3_cur.value = l14_next;
				return null;
			}
			case 71/*MizTrmNil*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l9__tmp.getTypeName());
			}
		};
		Module_loop.f_while(l6_$4, l7_$5);
		return ((boolean)l2_found.value);
	}
	public static final Object f_mizLESignRules(Struct_MizEqualizer aeq_0, Struct aargs, Reference<Boolean> aadded, boolean apositive) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_rqPos = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqPositive);
		final int l2_rqNeg = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqNegative);
		if (((l1_rqPos>0)&&(l2_rqNeg>0))) {
			final Struct l3_lLeft = Module_correl.f_mizTrmListNth(aargs, 0);
			final Struct l4_lRight = Module_correl.f_mizTrmListNth(aargs, 1);
			final Struct_MizAttrColl l5_scL = (Module_equalizer.f_mizTrmSAt(aeq_0, Module_equalizer.f_mizTrmInfoOf(l3_lLeft))).f_superCluster;
			final Struct_MizAttrColl l6_scR = (Module_equalizer.f_mizTrmSAt(aeq_0, Module_equalizer.f_mizTrmInfoOf(l4_lRight))).f_superCluster;
			final Struct_MizEqualizer l8_eq_0 = aeq_0;
			final Func2<Struct,Struct_MizAttrColl, Integer> l7_att = (Func2<Struct, Struct_MizAttrColl, Integer>)(Struct_MizAttrColl asc, Integer anr) -> {
				return Module_equalizer.f_mizGetAttr(l8_eq_0, asc, ((int)anr), ((Struct)SingletonStructs.str_MizTrmNil));
			};
			final Func1<Integer,Struct> l9_negOf = (Func1<Integer, Struct>)(Struct am) -> {
				Struct l10__tmp = am;
				switch (l10__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l11__tmp = (Struct_Some)l10__tmp;
					final Object l12_a = l11__tmp.f_value;
					final Struct_MizAttr l13_a = ((Struct_MizAttr)(l11__tmp).f_value);
					return ((Integer)(l13_a).f_neg);
				}
				case 82/*None*/: {
					return ((Integer)(-1));
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
				}
			};
			final Struct_MizEqualizer l11_eq_0 = aeq_0;
			final Reference<Boolean> l12_added = aadded;
			final Func4<Object,Struct_MizAttrColl, Integer, Integer, Integer> l10_ins = (Func4<Object, Struct_MizAttrColl, Integer, Integer, Integer>)(Struct_MizAttrColl asc, Integer anr, Integer aneg, Integer acode) -> {
				if (Module_maybe.f_isNone(Module_equalizer.f_mizGetAttr(l11_eq_0, asc, ((int)anr), ((Struct)SingletonStructs.str_MizTrmNil)))) {
					l12_added.value = ((Boolean)true);
				} else {
				}
				Module_equalizer.f_mizInsertAttrE(l11_eq_0, asc, ((int)anr), ((int)aneg), ((Struct)SingletonStructs.str_MizTrmNil));
				if (!(asc).f_consistent) {
					return Module_equalizer.f_mizSetContr(l11_eq_0, ((int)acode));
				} else {
					return null;
				}
			};
			if (apositive) {
				if ((((int)l9_negOf.invoke(l7_att.invoke(l5_scL, ((Integer)l1_rqPos))))==1)) {
					l10_ins.invoke(l6_scR, ((Integer)l1_rqPos), ((Integer)1), ((Integer)26));
				} else {
				}
				if ((((aeq_0).f_contr==0)&&(((int)l9_negOf.invoke(l7_att.invoke(l6_scR, ((Integer)l2_rqNeg))))==1))) {
					l10_ins.invoke(l5_scL, ((Integer)l2_rqNeg), ((Integer)1), ((Integer)27));
				} else {
				}
				if ((((aeq_0).f_contr==0)&&(((int)l9_negOf.invoke(l7_att.invoke(l5_scL, ((Integer)l2_rqNeg))))==0))) {
					l10_ins.invoke(l6_scR, ((Integer)l2_rqNeg), ((Integer)0), ((Integer)28));
				} else {
				}
				if ((((aeq_0).f_contr==0)&&(((int)l9_negOf.invoke(l7_att.invoke(l6_scR, ((Integer)l1_rqPos))))==0))) {
					l10_ins.invoke(l5_scL, ((Integer)l1_rqPos), ((Integer)0), ((Integer)29));
				} else {
				}
			} else {
				if ((((int)l9_negOf.invoke(l7_att.invoke(l5_scL, ((Integer)l1_rqPos))))==0)) {
					l10_ins.invoke(l6_scR, ((Integer)l2_rqNeg), ((Integer)1), ((Integer)37));
				} else {
				}
				if ((((aeq_0).f_contr==0)&&(((int)l9_negOf.invoke(l7_att.invoke(l6_scR, ((Integer)l2_rqNeg))))==0))) {
					l10_ins.invoke(l5_scL, ((Integer)l1_rqPos), ((Integer)1), ((Integer)38));
				} else {
				}
			}
		} else {
		}
		if (((aeq_0).f_contr==0)) {
			final Struct l13_lLeft2 = Module_correl.f_mizTrmListNth(aargs, 0);
			final Struct l14_lRight2 = Module_correl.f_mizTrmListNth(aargs, 1);
			final Struct_MizTrmSRec l15_rl = Module_equalizer.f_mizTrmSAt(aeq_0, Module_equalizer.f_mizClassOf(aeq_0, Module_equalizer.f_mizTrmInfoOf(l13_lLeft2)));
			final Struct_MizTrmSRec l16_rr = Module_equalizer.f_mizTrmSAt(aeq_0, Module_equalizer.f_mizClassOf(aeq_0, Module_equalizer.f_mizTrmInfoOf(l14_lRight2)));
			if (((((l15_rl).f_numDetermined&&(l16_rr).f_numDetermined)&&(((l15_rl).f_numValue).f_im==0.0))&&(((l16_rr).f_numValue).f_im==0.0))) {
				if (apositive) {
					if ((((l15_rl).f_numValue).f_re>((l16_rr).f_numValue).f_re)) {
						return Module_equalizer.f_mizSetContr(aeq_0, 32);
					} else {
						return null;
					}
				} else {
					if ((((l15_rl).f_numValue).f_re<=((l16_rr).f_numValue).f_re)) {
						return Module_equalizer.f_mizSetContr(aeq_0, 39);
					} else {
						return null;
					}
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	public static final Struct f_mizLastArgE(Struct al) {
		return (Module_correl.f_mizLastElem(al)).f_trm;
	}
	public static final Struct_MizEqualizer f_mizMakeEqualizer(Struct_MizCorrelEnv aenv, Struct_MizIdentify aidn, Struct_MizClusters aclusters, Struct_MizPre apre) {
		final Struct_Vector l0_fti = Module_vector.f_makeVector(5);
		final Func1<Object,Integer> l2_$1 = (Func1<Object, Integer>)(Integer al3_0) -> {
			return Module_vector.f_pushVector(l0_fti, Module_vector.f_makeVector(8));
		};
		Module_runtime.f_fori(0, 4, l2_$1);
		final Struct_Vector l3_ts = Module_vector.f_makeVector(64);
		Module_vector.f_pushVector(l3_ts, (new Struct_MizTrmSRec(Module_correl.f_mizNewIncorTrm(), false, (new Struct_MizComplex(0.0, 0.0)), ((Struct)SingletonStructs.str_MizTrmNil), Module_vector.f_makeVector(0), Module_correl.f_mizNewEmptyCluster(), Module_vector.f_makeVector(2))));
		return (new Struct_MizEqualizer(aenv, aidn, aclusters, apre, l3_ts, false, 0, 0, Module_vector.f_makeVector(8), Module_vector.f_makeVector(8), Module_vector.f_makeVector(16), l0_fti, Module_vector.f_makeVector(4), Module_vector.f_makeVector(4), false, Module_vector.f_makeVector(8), Module_vector.f_makeVector(8), Module_vector.f_makeVector(4), Module_vector.f_makeVector(4), Module_vector.f_makeVector(16), Module_mobjects.f_mizInitNatSet(16), Module_mobjects.f_mizInitNatFunc(1)));
	}
	public static final Object f_mizNegPairSweep(Struct_MizEqualizer aeq_0, Struct anf, int acode) {
		final Struct_Vector l2_$1 = (aeq_0).f_posBas;
		final Struct_MizEqualizer l4_eq_0 = aeq_0;
		final Struct l5_nf = anf;
		final int l6_code = acode;
		final Func1<Object,Struct> l3_$0 = (Func1<Object, Struct>)(Struct apf) -> {
			if ((((l4_eq_0).f_contr==0)&&Module_equalizer.f_mizEqFrmsE(l4_eq_0, apf, l5_nf))) {
				return Module_equalizer.f_mizSetContr(l4_eq_0, l6_code);
			} else {
				return null;
			}
		};
		return Module_vector.f_iterVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
	}
	public static final Object f_mizNegativeSweep(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_rqLE = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqLessOrEqual);
		final int l2_rqBel = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqBelongsTo);
		final int l3_rqIncl = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqInclusion);
		final int l4_rqElem = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqElement);
		final int l5_rqPow = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqPowerSet);
		final int l6_rqEmp = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEmpty);
		final Reference<Boolean> l7_added = ((Reference<Boolean>)(new Reference(true)));
		final Struct_MizEqualizer l11_eq_0 = aeq_0;
		final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l11_eq_0).f_contr==0)&&((boolean)l7_added.value)));
		};
		final Struct_MizEqualizer l13_eq_0 = aeq_0;
		final Func0<Object> l12_$9 = (Func0<Object>)() -> {
			l7_added.value = ((Boolean)false);
			final Reference<Integer> l13_ii = ((Reference<Integer>)(new Reference(0)));
			final Func0<Boolean> l16_$14 = (Func0<Boolean>)() -> {
				return ((Boolean)(((l13_eq_0).f_contr==0)&&(((int)l13_ii.value)<Module_vector.f_sizeVector((l13_eq_0).f_negBas))));
			};
			final Func0<Object> l17_$15 = (Func0<Object>)() -> {
				final Struct l18_nf = ((Struct)Module_vector.f_getVectorUnsafe((l13_eq_0).f_negBas, ((int)l13_ii.value)));
				Struct l19__tmp = l18_nf;
				switch (l19__tmp.getTypeId()) {
				case 58/*MizPredFrm*/: {
					final Struct_MizPredFrm l20__tmp = (Struct_MizPredFrm)l19__tmp;
					final int l21_srt = l20__tmp.f_sort;
					final int l22_predNr = l20__tmp.f_predNr;
					final Struct l23_args = l20__tmp.f_args;
					final int l24_srt = (l20__tmp).f_sort;
					final int l25_predNr = (l20__tmp).f_predNr;
					final Struct l26_args = (l20__tmp).f_args;
					final int l27___ = (l20__tmp).f_pattNr;
					if ((l24_srt==Module_lexicon.g_ikFrmAttr)) {
						final Struct l28_la = Module_equalizer.f_mizGetAttr(l13_eq_0, (Module_equalizer.f_mizTrmSAt(l13_eq_0, Module_equalizer.f_mizTrmInfoOf(Module_equalizer.f_mizLastArgE(l26_args)))).f_superCluster, l25_predNr, Module_equalizer.f_mizAllButLast(l26_args));
						Struct l29__tmp = l28_la;
						switch (l29__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l30__tmp = (Struct_Some)l29__tmp;
							final Object l31_at = l30__tmp.f_value;
							final Struct_MizAttr l32_at = ((Struct_MizAttr)(l30__tmp).f_value);
							if (((l32_at).f_neg==1)) {
								Module_equalizer.f_mizSetContr(l13_eq_0, 35);
							} else {
							}
							break;
						}
						case 82/*None*/: {
							break;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l29__tmp.getTypeName());
						}
						if (((l13_eq_0).f_contr==0)) {
							Module_equalizer.f_mizNegPairSweep(l13_eq_0, l20__tmp, 36);
						} else {
						}
					} else {
						if (((l24_srt==Module_lexicon.g_ikFrmSchPred)||(l24_srt==Module_lexicon.g_ikFrmPrivPred))) {
							Module_equalizer.f_mizNegPairSweep(l13_eq_0, l20__tmp, 36);
						} else {
							if ((l24_srt==Module_lexicon.g_ikFrmPred)) {
								final Struct_MizPredFrm l33_pfN = Module_equalizer.f_mizEqAsPred(l20__tmp);
								final Struct_Pair l34_pa = Module_correl.f_mizAdjustFrm(l0_env, l33_pfN);
								final int l35_lPred = ((int)(l34_pa).f_first);
								if (((l35_lPred==l1_rqLE)&&(l35_lPred!=0))) {
									Module_equalizer.f_mizLESignRules(l13_eq_0, ((Struct)(l34_pa).f_second), l7_added, false);
								} else {
									if ((((l35_lPred==l2_rqBel)&&(l35_lPred!=0))&&(l4_rqElem>0))) {
										final Struct l36_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l34_pa).f_second), 0);
										final Struct l37_lRight = Module_correl.f_mizTrmListNth(((Struct)(l34_pa).f_second), 1);
										if ((Module_url_parameter.f_getUrlParameterDef("dbg-neg", "")).equals("1")) {
											final String l40_$39 = (("NEG-BEL rhs-cls="+Module_string.f_i2s(Module_equalizer.f_mizClassOf(l13_eq_0, Module_equalizer.f_mizTrmInfoOf(l37_lRight))))+" empty-attr=");
											final Struct l41_gsymswitch29 = Module_equalizer.f_mizGetAttr(l13_eq_0, (Module_equalizer.f_mizTrmSAt(l13_eq_0, Module_equalizer.f_mizTrmInfoOf(l37_lRight))).f_superCluster, l6_rqEmp, ((Struct)SingletonStructs.str_MizTrmNil));
											String l42_$38;
											Struct l43__tmp = l41_gsymswitch29;
											switch (l43__tmp.getTypeId()) {
											case 90/*Some*/: {
												final Struct_Some l44__tmp = (Struct_Some)l43__tmp;
												final Object l45_a0 = l44__tmp.f_value;
												final Struct_MizAttr l46_a0 = ((Struct_MizAttr)(l44__tmp).f_value);
												l42_$38=Module_string.f_i2s((l46_a0).f_neg);
												break;
											}
											case 82/*None*/: {
												l42_$38="none";
												break;
											}
											default:
												throw new RuntimeException("Unexpected struct in switch: "+l43__tmp.getTypeName());
											}
											Module_runtime.f_println((l40_$39+l42_$38));
										} else {
										}
										final Struct l47_gsymswitch30 = Module_equalizer.f_mizGetAttr(l13_eq_0, (Module_equalizer.f_mizTrmSAt(l13_eq_0, Module_equalizer.f_mizTrmInfoOf(l37_lRight))).f_superCluster, l6_rqEmp, ((Struct)SingletonStructs.str_MizTrmNil));
										Struct l48__tmp = l47_gsymswitch30;
										switch (l48__tmp.getTypeId()) {
										case 90/*Some*/: {
											final Struct_Some l49__tmp = (Struct_Some)l48__tmp;
											final Object l50_a = l49__tmp.f_value;
											final Struct_MizAttr l51_a = ((Struct_MizAttr)(l49__tmp).f_value);
											if (((l51_a).f_neg==0)) {
												final Struct_MizTyp l52_lTyp = Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), l4_rqElem, Module_correl.f_mizNewTrmList(Module_correl.f_mizCopyTrm(l37_lRight), ((Struct)SingletonStructs.str_MizTrmNil)));
												final Struct_Vector l55_$54 = (Module_equalizer.f_mizTrmSAt(l13_eq_0, Module_equalizer.f_mizTrmInfoOf(l36_lLeft))).f_xTypClass;
												final Func1<Object,Struct_MizTyp> l56_$53 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
													boolean l59_$58;
													if (((l13_eq_0).f_contr==0)) {
														final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l61_$60 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
															return ((Boolean)Module_equalizer.f_mizEqAttrsE(l13_eq_0, ax, ay));
														};
														l59_$58=Module_correl_inst.f_mizDecreasingAttrs(l0_env, axt, l52_lTyp, l61_$60);
													} else {
														l59_$58=false;
													}
													if ((l59_$58&&Module_correl_inst.f_mizEqRadices(l0_env, l52_lTyp, axt))) {
														return Module_equalizer.f_mizSetContr(l13_eq_0, 40);
													} else {
														return null;
													}
												};
												Module_vector.f_iterVector(l55_$54, ((Func1<Object,Object>)(Func1)l56_$53));
											} else {
											}
											break;
										}
										case 82/*None*/: {
											break;
										}
										default:
											throw new RuntimeException("Unexpected struct in switch: "+l48__tmp.getTypeName());
										}
									} else {
										if (((((l35_lPred==l3_rqIncl)&&(l35_lPred!=0))&&(l4_rqElem>0))&&(l5_rqPow>0))) {
											final Struct l57_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l34_pa).f_second), 0);
											final Struct l58_lRight = Module_correl.f_mizTrmListNth(((Struct)(l34_pa).f_second), 1);
											final Struct l59_lRight2 = Module_equalizer.f_mizYTerm(l13_eq_0, Module_correl.f_mizNewFuncTrm(l5_rqPow, Module_correl.f_mizNewTrmList(Module_correl.f_mizCopyTrm(l58_lRight), ((Struct)SingletonStructs.str_MizTrmNil))));
											final Struct_MizTyp l60_lTyp = Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), l4_rqElem, Module_correl.f_mizNewTrmList(l59_lRight2, ((Struct)SingletonStructs.str_MizTrmNil)));
											final Struct_Vector l63_$62 = (Module_equalizer.f_mizTrmSAt(l13_eq_0, Module_equalizer.f_mizTrmInfoOf(l57_lLeft))).f_xTypClass;
											final Func1<Object,Struct_MizTyp> l64_$61 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
												boolean l67_$66;
												if (((l13_eq_0).f_contr==0)) {
													final Func2<Boolean,Struct_MizAttr, Struct_MizAttr> l69_$68 = (Func2<Boolean, Struct_MizAttr, Struct_MizAttr>)(Struct_MizAttr ax, Struct_MizAttr ay) -> {
														return ((Boolean)Module_equalizer.f_mizEqAttrsE(l13_eq_0, ax, ay));
													};
													l67_$66=Module_correl_inst.f_mizDecreasingAttrs(l0_env, axt, l60_lTyp, l69_$68);
												} else {
													l67_$66=false;
												}
												if ((l67_$66&&Module_correl_inst.f_mizEqRadices(l0_env, l60_lTyp, axt))) {
													return Module_equalizer.f_mizSetContr(l13_eq_0, 41);
												} else {
													return null;
												}
											};
											Module_vector.f_iterVector(l63_$62, ((Func1<Object,Object>)(Func1)l64_$61));
										} else {
										}
									}
								}
								if (((l13_eq_0).f_contr==0)) {
									Module_equalizer.f_mizNegPairSweep(l13_eq_0, l20__tmp, 42);
								} else {
								}
							} else {
							}
						}
					}
					break;
				}
				case 63/*MizQualFrm*/: {
					final Struct_MizQualFrm l20__tmp = (Struct_MizQualFrm)l19__tmp;
					final Struct l65_trm = l20__tmp.f_trm;
					final Struct_MizTyp l66_typ = l20__tmp.f_typ;
					final Struct l67_trm = (l20__tmp).f_trm;
					final Struct_MizTyp l68_typ = (l20__tmp).f_typ;
					final int l69___ = (l20__tmp).f_pattNr;
					final Struct_MizTrmSRec l70_rec = Module_equalizer.f_mizTrmSAt(l13_eq_0, Module_equalizer.f_mizTrmInfoOf(l67_trm));
					final Struct_Vector l73_$72 = (l70_rec).f_xTypClass;
					final Func1<Object,Struct_MizTyp> l74_$71 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
						if ((((l13_eq_0).f_contr==0)&&Module_correl_inst.f_mizEqRadices(l0_env, l68_typ, axt))) {
							return Module_equalizer.f_mizSetContr(l13_eq_0, 43);
						} else {
							return null;
						}
					};
					Module_vector.f_iterVector(l73_$72, ((Func1<Object,Object>)(Func1)l74_$71));
					break;
				}
				default: {
					break;
				}
				}
				l13_ii.value = ((Integer)(((int)l13_ii.value)+1));
				return null;
			};
			return Module_loop.f_while(l16_$14, l17_$15);
		};
		return Module_loop.f_while(l10_$8, l12_$9);
	}
	public static final Object f_mizPolyValInsert(Struct_Vector av, Struct_MizPolynomial ap) {
		final int l0_n = Module_vector.f_sizeVector(av);
		final Reference<Integer> l1_pos = ((Reference<Integer>)(new Reference(0)));
		final Reference<Boolean> l2_dup = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Boolean> l3_brk = ((Reference<Boolean>)(new Reference(false)));
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l3_brk.value)&&(((int)l1_pos.value)<l0_n)));
		};
		final Struct_Vector l8_v = av;
		final Struct_MizPolynomial l9_p = ap;
		final Func0<Object> l7_$5 = (Func0<Object>)() -> {
			final int l8_c = Module_polynom.f_mizStdComparePolynomials(((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe(l8_v, ((int)l1_pos.value))), l9_p);
			if ((l8_c==0)) {
				l2_dup.value = ((Boolean)true);
				l3_brk.value = ((Boolean)true);
				return null;
			} else {
				if ((l8_c>0)) {
					l3_brk.value = ((Boolean)true);
					return null;
				} else {
					l1_pos.value = ((Integer)(((int)l1_pos.value)+1));
					return null;
				}
			}
		};
		Module_loop.f_while(l6_$4, l7_$5);
		if (!((boolean)l2_dup.value)) {
			Module_vector.f_pushVector(av, ap);
			final Reference<Integer> l10_i = ((Reference<Integer>)(new Reference((Module_vector.f_sizeVector(av)-1))));
			final Func0<Boolean> l13_$11 = (Func0<Boolean>)() -> {
				return (((int)l10_i.value)>((int)l1_pos.value));
			};
			final Struct_Vector l15_v = av;
			final Func0<Object> l14_$12 = (Func0<Object>)() -> {
				Module_vector.f_setVectorUnsafe(l15_v, ((int)l10_i.value), Module_vector.f_getVectorUnsafe(l15_v, (((int)l10_i.value)-1)));
				l10_i.value = ((Integer)(((int)l10_i.value)-1));
				return null;
			};
			Module_loop.f_while(l13_$11, l14_$12);
			return Module_vector.f_setVectorUnsafe(av, ((int)l1_pos.value), ap);
		} else {
			return null;
		}
	}
	public static final Object f_mizPositiveRequirementRules(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_rqLE = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqLessOrEqual);
		final int l2_rqBel = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqBelongsTo);
		final int l3_rqIncl = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqInclusion);
		final int l4_rqElem = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqElement);
		final int l5_rqPow = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqPowerSet);
		final int l6_rqEmp = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEmpty);
		final Reference<Boolean> l7_added = ((Reference<Boolean>)(new Reference(true)));
		final Struct_MizEqualizer l11_eq_0 = aeq_0;
		final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l11_eq_0).f_contr==0)&&((boolean)l7_added.value)));
		};
		final Struct_MizEqualizer l13_eq_0 = aeq_0;
		final Func0<Object> l12_$9 = (Func0<Object>)() -> {
			l7_added.value = ((Boolean)false);
			final Struct_Vector l15_$14 = (l13_eq_0).f_posBas;
			final Func1<Object,Struct> l16_$13 = (Func1<Object, Struct>)(Struct apf) -> {
				if (((l13_eq_0).f_contr==0)) {
					Struct l17__tmp = apf;
					switch (l17__tmp.getTypeId()) {
					case 58/*MizPredFrm*/: {
						final Struct_MizPredFrm l18__tmp = (Struct_MizPredFrm)l17__tmp;
						final int l19_srt = l18__tmp.f_sort;
						final int l20_srt = (l18__tmp).f_sort;
						final int l21___ = (l18__tmp).f_predNr;
						final Struct l22___ = (l18__tmp).f_args;
						final int l23___ = (l18__tmp).f_pattNr;
						if ((l20_srt==Module_lexicon.g_ikFrmPred)) {
							final Struct_MizPredFrm l24_pfP = Module_equalizer.f_mizEqAsPred(l18__tmp);
							final Struct_Pair l25_pa = Module_correl.f_mizAdjustFrm(l0_env, l24_pfP);
							final int l26_lPred = ((int)(l25_pa).f_first);
							if (((l26_lPred==l2_rqBel)&&(l26_lPred!=0))) {
								final Struct l27_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l25_pa).f_second), 0);
								final Struct l28_lRight = Module_correl.f_mizTrmListNth(((Struct)(l25_pa).f_second), 1);
								if ((l6_rqEmp>0)) {
									Module_equalizer.f_mizInsertAttrE(l13_eq_0, (Module_equalizer.f_mizTrmSAt(l13_eq_0, Module_equalizer.f_mizTrmInfoOf(l28_lRight))).f_superCluster, l6_rqEmp, 0, ((Struct)SingletonStructs.str_MizTrmNil));
									if (!((Module_equalizer.f_mizTrmSAt(l13_eq_0, Module_equalizer.f_mizTrmInfoOf(l28_lRight))).f_superCluster).f_consistent) {
										Module_equalizer.f_mizSetContr(l13_eq_0, 33);
									} else {
									}
								} else {
								}
								if ((((l13_eq_0).f_contr==0)&&(l4_rqElem>0))) {
									final Struct_MizTyp l29_lInsTyp = Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), l4_rqElem, Module_correl.f_mizNewTrmList(l28_lRight, ((Struct)SingletonStructs.str_MizTrmNil)));
									return Module_equalizer.f_mizInsertType(l13_eq_0, l29_lInsTyp, Module_equalizer.f_mizTrmInfoOf(l27_lLeft));
								} else {
									return null;
								}
							} else {
								if (((((l26_lPred==l3_rqIncl)&&(l26_lPred!=0))&&(l4_rqElem>0))&&(l5_rqPow>0))) {
									final Struct l30_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l25_pa).f_second), 0);
									final Struct l31_lRight = Module_correl.f_mizTrmListNth(((Struct)(l25_pa).f_second), 1);
									final Struct l32_lRight2 = Module_equalizer.f_mizYTerm(l13_eq_0, Module_correl.f_mizNewFuncTrm(l5_rqPow, Module_correl.f_mizNewTrmList(Module_correl.f_mizCopyTrm(l31_lRight), ((Struct)SingletonStructs.str_MizTrmNil))));
									final Struct_MizTyp l33_lInsTyp = Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), l4_rqElem, Module_correl.f_mizNewTrmList(l32_lRight2, ((Struct)SingletonStructs.str_MizTrmNil)));
									return Module_equalizer.f_mizInsertType(l13_eq_0, l33_lInsTyp, Module_equalizer.f_mizTrmInfoOf(l30_lLeft));
								} else {
									if (((l26_lPred==l1_rqLE)&&(l26_lPred!=0))) {
										return Module_equalizer.f_mizLESignRules(l13_eq_0, ((Struct)(l25_pa).f_second), l7_added, true);
									} else {
										return null;
									}
								}
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
			return Module_vector.f_iterVector(l15_$14, ((Func1<Object,Object>)(Func1)l16_$13));
		};
		return Module_loop.f_while(l10_$8, l12_$9);
	}
	public static final Object f_mizPowersetRule(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_rqBel = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqBelongsTo);
		final int l2_rqElem = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqElement);
		final int l3_rqPow = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqPowerSet);
		final int l4_rqEmp = Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqEmpty);
		if ((((l1_rqBel>0)&&(l2_rqElem>0))&&(l3_rqPow>0))) {
			final Reference<Integer> l5_b11 = ((Reference<Integer>)(new Reference(0)));
			final Struct_MizEqualizer l9_eq_0 = aeq_0;
			final Func0<Boolean> l8_$6 = (Func0<Boolean>)() -> {
				return ((Boolean)(((l9_eq_0).f_contr==0)&&(((int)l5_b11.value)<Module_vector.f_sizeVector((l9_eq_0).f_posBas))));
			};
			final Struct_MizEqualizer l11_eq_0 = aeq_0;
			final Func0<Object> l10_$7 = (Func0<Object>)() -> {
				l5_b11.value = ((Integer)(((int)l5_b11.value)+1));
				final Struct_Vector l13_$12 = Module_vector.f_copyVector((l11_eq_0).f_posBas);
				final Func1<Object,Struct> l14_$11 = (Func1<Object, Struct>)(Struct apf) -> {
					if (((l11_eq_0).f_contr==0)) {
						Struct l15__tmp = apf;
						switch (l15__tmp.getTypeId()) {
						case 58/*MizPredFrm*/: {
							final Struct_MizPredFrm l16__tmp = (Struct_MizPredFrm)l15__tmp;
							final int l17_srt = l16__tmp.f_sort;
							final int l18_srt = (l16__tmp).f_sort;
							final int l19___ = (l16__tmp).f_predNr;
							final Struct l20___ = (l16__tmp).f_args;
							final int l21___ = (l16__tmp).f_pattNr;
							if ((l18_srt==Module_lexicon.g_ikFrmPred)) {
								final Struct_MizPredFrm l22_pfP = Module_equalizer.f_mizEqAsPred(l16__tmp);
								final Struct_Pair l23_pa = Module_correl.f_mizAdjustFrm(l0_env, l22_pfP);
								if ((((int)(l23_pa).f_first)==l1_rqBel)) {
									final Struct l24_lLeft = Module_correl.f_mizTrmListNth(((Struct)(l23_pa).f_second), 0);
									final Struct l25_lRight = Module_correl.f_mizTrmListNth(((Struct)(l23_pa).f_second), 1);
									final Struct_Vector l26_newTyps = Module_vector.f_makeVector(2);
									final Struct_Vector l29_$28 = (Module_equalizer.f_mizTrmSAt(l11_eq_0, Module_equalizer.f_mizTrmInfoOf(l25_lRight))).f_xTypClass;
									final Func1<Object,Struct_MizTyp> l30_$27 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp axt) -> {
										final Struct_Pair l31_pt = Module_correl.f_mizAdjustTyp(l0_env, axt);
										if ((((axt).f_sort==Module_lexicon.g_ikTypMode)&&(((int)(l31_pt).f_first)==l2_rqElem))) {
											final Struct l32_arg0 = Module_correl.f_mizTrmListNth(((Struct)(l31_pt).f_second), 0);
											final Reference<Struct> l33_cur = ((Reference<Struct>)(new Reference((Module_equalizer.f_mizTrmSAt(l11_eq_0, Module_equalizer.f_mizTrmInfoOf(l32_arg0))).f_eqClass)));
											final Func0<Boolean> l36_$34 = (Func0<Boolean>)() -> {
												final Struct l37_gsymswitch27 = l33_cur.value;
												Struct l38__tmp = l37_gsymswitch27;
												switch (l38__tmp.getTypeId()) {
												case 71/*MizTrmNil*/: {
													return ((Boolean)false);
												}
												default: {
													return ((Boolean)true);
												}
												}
											};
											final Func0<Object> l37_$35 = (Func0<Object>)() -> {
												final Struct l38_gsymswitch28 = l33_cur.value;
												Struct l39__tmp = l38_gsymswitch28;
												switch (l39__tmp.getTypeId()) {
												case 70/*MizTrmElem*/: {
													final Struct_MizTrmElem l40__tmp = (Struct_MizTrmElem)l39__tmp;
													final Struct l41_trm = l40__tmp.f_trm;
													final Struct l42_next = l40__tmp.f_next;
													final Struct l43_trm = (l40__tmp).f_trm;
													final Struct l44_next = (l40__tmp).f_next;
													boolean l45_powHit;
													Struct l46__tmp = l43_trm;
													switch (l46__tmp.getTypeId()) {
													case 45/*MizFuncTrm*/: {
														final Struct_MizFuncTrm l47__tmp = (Struct_MizFuncTrm)l46__tmp;
														final int l48_s2 = l47__tmp.f_sort;
														final int l49_fn2 = l47__tmp.f_funcNr;
														final int l50_s2 = (l47__tmp).f_sort;
														final int l51_fn2 = (l47__tmp).f_funcNr;
														final Struct l52___ = (l47__tmp).f_args;
														final int l53___ = (l47__tmp).f_pattNr;
														final int l54___ = (l47__tmp).f_trmInfo;
														l45_powHit=((l50_s2==Module_lexicon.g_ikTrmFunctor)&&(l51_fn2==l3_rqPow));
														break;
													}
													default: {
														l45_powHit=false;
														break;
													}
													}
													if (l45_powHit) {
														final Struct l55_pargs = Module_equalizer.f_mizArgsOfE(l43_trm);
														final Struct l56_inner = Module_correl.f_mizTrmListNth(l55_pargs, 0);
														final int l57_innerInfo = Module_equalizer.f_mizTrmInfoOf(l56_inner);
														if ((l4_rqEmp>0)) {
															Module_equalizer.f_mizInsertAttrE(l11_eq_0, (Module_equalizer.f_mizTrmSAt(l11_eq_0, l57_innerInfo)).f_superCluster, l4_rqEmp, 0, ((Struct)SingletonStructs.str_MizTrmNil));
															if (!((Module_equalizer.f_mizTrmSAt(l11_eq_0, l57_innerInfo)).f_superCluster).f_consistent) {
																Module_equalizer.f_mizSetContr(l11_eq_0, 34);
															} else {
															}
														} else {
														}
														Module_vector.f_pushVector(l26_newTyps, Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), l2_rqElem, Module_correl.f_mizNewTrmList(l56_inner, ((Struct)SingletonStructs.str_MizTrmNil))));
													} else {
													}
													l33_cur.value = l44_next;
													return null;
												}
												case 71/*MizTrmNil*/: {
													return null;
												}
												default:
													throw new RuntimeException("Unexpected struct in switch: "+l39__tmp.getTypeName());
												}
											};
											return Module_loop.f_while(l36_$34, l37_$35);
										} else {
											return null;
										}
									};
									Module_vector.f_iterVector(l29_$28, ((Func1<Object,Object>)(Func1)l30_$27));
									if (((l11_eq_0).f_contr==0)) {
										final Func1<Object,Struct_MizTyp> l32_$31 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp ant) -> {
											return Module_equalizer.f_mizInsertType(l11_eq_0, ant, Module_equalizer.f_mizTrmInfoOf(l24_lLeft));
										};
										return Module_vector.f_iterVector(l26_newTyps, ((Func1<Object,Object>)(Func1)l32_$31));
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
					} else {
						return null;
					}
				};
				return Module_vector.f_iterVector(l13_$12, ((Func1<Object,Object>)(Func1)l14_$11));
			};
			return Module_loop.f_while(l8_$6, l10_$7);
		} else {
			return null;
		}
	}
	public static final Object f_mizProcessReductions(Struct_MizEqualizer aeq_0) {
		if (((Module_vector.f_sizeVector(((aeq_0).f_idn).f_reductions)>0)&&(Module_equalizer.f_mizTrmNbr(aeq_0)>0))) {
			final Reference<Integer> l0_k = ((Reference<Integer>)(new Reference(0)));
			final Struct_MizEqualizer l4_eq_0 = aeq_0;
			final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
				return ((Boolean)(((l4_eq_0).f_contr==0)&&(((int)l0_k.value)<Module_vector.f_sizeVector((l4_eq_0).f_dTrm))));
			};
			final Struct_MizEqualizer l6_eq_0 = aeq_0;
			final Func0<Object> l5_$2 = (Func0<Object>)() -> {
				final Struct l6_gsymswitch71 = ((Struct)Module_vector.f_getVectorUnsafe((l6_eq_0).f_dTrm, ((int)l0_k.value)));
				Struct l7__tmp = l6_gsymswitch71;
				switch (l7__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l8__tmp = (Struct_Some)l7__tmp;
					final Object l9_t = l8__tmp.f_value;
					final Struct l10_t = ((Struct)(l8__tmp).f_value);
					Module_equalizer.f_mizProcessReductionsAN(l6_eq_0, Module_equalizer.f_mizTrmInfoOf(l10_t));
					break;
				}
				case 82/*None*/: {
					break;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l7__tmp.getTypeName());
				}
				l0_k.value = ((Integer)(((int)l0_k.value)+1));
				return null;
			};
			return Module_loop.f_while(l3_$1, l5_$2);
		} else {
			return null;
		}
	}
	public static final Object f_mizProcessReductionsAN(Struct_MizEqualizer aeq_0, int aenr) {
		final Struct_MizTrmSRec l0_rec = Module_equalizer.f_mizTrmSAt(aeq_0, aenr);
		final Struct l1_gsymswitch70 = (l0_rec).f_eqClass;
		boolean l2_live;
		Struct l3__tmp = l1_gsymswitch70;
		switch (l3__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			l2_live=false;
			break;
		}
		default: {
			l2_live=true;
			break;
		}
		}
		if (l2_live) {
			final Struct_Vector l7_$6 = ((aeq_0).f_idn).f_reductions;
			final Struct_MizEqualizer l9_eq_0 = aeq_0;
			final int l10_enr = aenr;
			final Func1<Object,Struct_MizReduction> l8_$5 = (Func1<Object, Struct_MizReduction>)(Struct_MizReduction ared) -> {
				if (((l9_eq_0).f_contr==0)) {
					final Struct_MizPreInst l9_inst0 = Module_equalizer.f_mizANInstTerm(l9_eq_0, (ared).f_primaries, (l0_rec).f_term, (ared).f_t0);
					if ((l9_inst0).f_top) {
						return Module_equalizer.f_mizExecuteReduction(l9_eq_0, l10_enr, ared);
					} else {
						if ((Module_vector.f_sizeVector((l9_inst0).f_evals)>0)) {
							((Field_anInst)l9_eq_0).set_anInst(((Struct_MizNatFunc)Module_vector.f_getVectorUnsafe((l9_inst0).f_evals, 0)));
							return Module_equalizer.f_mizExecuteReduction(l9_eq_0, l10_enr, ared);
						} else {
							return null;
						}
					}
				} else {
					return null;
				}
			};
			return Module_vector.f_iterVector(l7_$6, ((Func1<Object,Object>)(Func1)l8_$5));
		} else {
			return null;
		}
	}
	public static final Object f_mizRenumEqClasses(Struct_MizEqualizer aeq_0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		((Field_eqClassNbr)aeq_0).set_eqClassNbr(0);
		final int l3_$2 = Module_equalizer.f_mizTrmNbr(aeq_0);
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func1<Object,Integer> l4_$1 = (Func1<Object, Integer>)(Integer aii) -> {
			final Struct_MizTrmSRec l5_rec = Module_equalizer.f_mizTrmSAt(l5_eq_0, ((int)aii));
			final Struct l6_gsymswitch17 = (l5_rec).f_eqClass;
			boolean l7_live;
			Struct l8__tmp = l6_gsymswitch17;
			switch (l8__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				l7_live=false;
				break;
			}
			default: {
				l7_live=true;
				break;
			}
			}
			if (l7_live) {
				((Field_eqClassNbr)l5_eq_0).set_eqClassNbr(((l5_eq_0).f_eqClassNbr+1));
				final Struct_MizVarTrm l10_vt = Module_equalizer.f_mizEqAsVar((l5_rec).f_term);
				((Field_varNr)l10_vt).set_varNr((l5_eq_0).f_eqClassNbr);
				return null;
			} else {
				return null;
			}
		};
		Module_runtime.f_fori(1, l3_$2, l4_$1);
		final int l8_$7 = Module_equalizer.f_mizTrmNbr(aeq_0);
		final Struct_MizEqualizer l10_eq_0 = aeq_0;
		final Func1<Object,Integer> l9_$6 = (Func1<Object, Integer>)(Integer aii) -> {
			final Struct_MizTrmSRec l10_rec = Module_equalizer.f_mizTrmSAt(l10_eq_0, ((int)aii));
			final int l11_repClass = Module_equalizer.f_mizTrmInfoOf((l10_rec).f_term);
			if (((l11_repClass>0)&&(l11_repClass<=Module_equalizer.f_mizTrmNbr(l10_eq_0)))) {
				final Struct_MizVarTrm l12_vRep = Module_equalizer.f_mizEqAsVar((Module_equalizer.f_mizTrmSAt(l10_eq_0, l11_repClass)).f_term);
				final Struct_MizVarTrm l13_vt2 = Module_equalizer.f_mizEqAsVar((l10_rec).f_term);
				((Field_varNr)l13_vt2).set_varNr((l12_vRep).f_varNr);
				return null;
			} else {
				return null;
			}
		};
		Module_runtime.f_fori(1, l8_$7, l9_$6);
		final int l13_$12 = Module_equalizer.f_mizTrmNbr(aeq_0);
		final Struct_MizEqualizer l15_eq_0 = aeq_0;
		final Func1<Object,Integer> l14_$11 = (Func1<Object, Integer>)(Integer aii) -> {
			final Struct_MizTrmSRec l15_rec = Module_equalizer.f_mizTrmSAt(l15_eq_0, ((int)aii));
			final Struct l16_gsymswitch18 = (l15_rec).f_eqClass;
			boolean l17_live;
			Struct l18__tmp = l16_gsymswitch18;
			switch (l18__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				l17_live=false;
				break;
			}
			default: {
				l17_live=true;
				break;
			}
			}
			if ((l17_live&&((l15_eq_0).f_contr==0))) {
				final Struct_MizAttrColl l20_fresh = Module_correl.f_mizNewEmptyCluster();
				final Struct_Vector l23_$22 = ((l15_rec).f_superCluster).f_items;
				final Func1<Object,Struct_MizAttr> l24_$21 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr ax) -> {
					return Module_correl.f_mizAttrCollInsert(l0_env, l20_fresh, Wrappers.w_mizCopyAttr.invoke(ax));
				};
				Module_vector.f_iterVector(l23_$22, ((Func1<Object,Object>)(Func1)l24_$21));
				if (!(l20_fresh).f_consistent) {
					Module_equalizer.f_mizSetContr(l15_eq_0, 17);
				} else {
				}
				((Field_superCluster)l15_rec).set_superCluster(l20_fresh);
				return null;
			} else {
				return null;
			}
		};
		return Module_runtime.f_fori(1, l13_$12, l14_$11);
	}
	public static final int f_mizRevReqPub(Struct_MizIdentify aidn, int afuncNr) {
		if (((afuncNr>=0)&&(afuncNr<Module_vector.f_sizeVector((aidn).f_revReq)))) {
			return ((int)Module_vector.f_getVectorUnsafe((aidn).f_revReq, afuncNr));
		} else {
			return Module_builtin.g_rqNone;
		}
	}
	public static final boolean f_mizRoundUpSuperCluster(Struct_MizEqualizer aeq_0, int aenr) {
		final Struct_MizTrmSRec l0_rec = Module_equalizer.f_mizTrmSAt(aeq_0, aenr);
		final Struct l1_gsymswitch66 = (l0_rec).f_eqClass;
		boolean l2_live;
		Struct l3__tmp = l1_gsymswitch66;
		switch (l3__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			l2_live=false;
			break;
		}
		default: {
			l2_live=true;
			break;
		}
		}
		if (!l2_live) {
			return false;
		} else {
			final Reference<Boolean> l5_r = ((Reference<Boolean>)(new Reference(false)));
			final Reference<Boolean> l6_stop = ((Reference<Boolean>)(new Reference(false)));
			final Reference<Boolean> l7_added = ((Reference<Boolean>)(new Reference(true)));
			final Struct_MizEqualizer l9_eq_0 = aeq_0;
			final Func4<Object,Struct_MizAttrColl, Integer, Integer, Struct_MizPreInst> l8_applyOne = (Func4<Object, Struct_MizAttrColl, Integer, Integer, Struct_MizPreInst>)(Struct_MizAttrColl aallowed, Integer acontrTop, Integer acontrInst, Struct_MizPreInst ainst) -> {
				if ((ainst).f_top) {
					final Struct_MizAttrColl l9_lC = Module_equalizer.f_mizANLocatedCluster(l9_eq_0, aallowed);
					if (Module_equalizer.f_mizANEnlarge(l9_eq_0, (l0_rec).f_superCluster, l9_lC)) {
						l5_r.value = ((Boolean)true);
						l7_added.value = ((Boolean)true);
					} else {
					}
					if (!((l0_rec).f_superCluster).f_consistent) {
						Module_equalizer.f_mizSetContr(l9_eq_0, ((int)acontrTop));
						l5_r.value = ((Boolean)true);
						l6_stop.value = ((Boolean)true);
						return null;
					} else {
						return null;
					}
				} else {
					final Struct_Vector l12_$11 = (ainst).f_evals;
					final Func1<Object,Struct_MizNatFunc> l13_$10 = (Func1<Object, Struct_MizNatFunc>)(Struct_MizNatFunc aatom) -> {
						if (!((boolean)l6_stop.value)) {
							((Field_anInst)l9_eq_0).set_anInst(aatom);
							final Struct_MizAttrColl l14_lC = Module_equalizer.f_mizANLocatedCluster(l9_eq_0, aallowed);
							if (Module_equalizer.f_mizANEnlarge(l9_eq_0, (l0_rec).f_superCluster, l14_lC)) {
								l5_r.value = ((Boolean)true);
								l7_added.value = ((Boolean)true);
							} else {
							}
							if (!((l0_rec).f_superCluster).f_consistent) {
								Module_equalizer.f_mizSetContr(l9_eq_0, ((int)acontrInst));
								l5_r.value = ((Boolean)true);
								l6_stop.value = ((Boolean)true);
								return null;
							} else {
								return null;
							}
						} else {
							return null;
						}
					};
					return Module_vector.f_iterVector(l12_$11, ((Func1<Object,Object>)(Func1)l13_$10));
				}
			};
			final Func0<Boolean> l12_$10 = (Func0<Boolean>)() -> {
				return ((Boolean)(((boolean)l7_added.value)&&!((boolean)l6_stop.value)));
			};
			final Struct_MizEqualizer l14_eq_0 = aeq_0;
			final int l15_enr = aenr;
			final Func0<Object> l13_$11 = (Func0<Object>)() -> {
				l7_added.value = ((Boolean)false);
				final Struct_Vector l16_$15 = (l14_eq_0).f_allowedC;
				final Func1<Object,Struct_Pair> l17_$14 = (Func1<Object, Struct_Pair>)(Struct_Pair apc) -> {
					if (!((boolean)l6_stop.value)) {
						final Struct_MizCCluster l18_cc = ((Struct_MizCCluster)Module_vector.f_getVectorUnsafe(((l14_eq_0).f_clusters).f_conditional, ((int)(apc).f_first)));
						final Struct_MizPreInst l19_instC = Module_equalizer.f_mizANInstCCluster(l14_eq_0, l18_cc, l15_enr);
						if ((Module_url_parameter.f_getUrlParameterDef("dbg-rus", "")).equals(Module_string.f_i2s(l15_enr))) {
							Module_runtime.f_println(((((((("RUS cls "+Module_string.f_i2s(l15_enr))+" CC ")+(l18_cc).f_article)+":")+Module_string.f_i2s((l18_cc).f_absNr))+" -> ")+((l19_instC).f_top?"top":Module_string.f_i2s(Module_prechecker.f_mizPreInstCount(l19_instC)))));
						} else {
						}
						return l8_applyOne.invoke(((Struct_MizAttrColl)(apc).f_second), ((Integer)8), ((Integer)9), l19_instC);
					} else {
						return null;
					}
				};
				Module_vector.f_iterVector(l16_$15, ((Func1<Object,Object>)(Func1)l17_$14));
				final Struct_Vector l20_$19 = (l14_eq_0).f_allowedF;
				final Func1<Object,Struct_Pair> l21_$18 = (Func1<Object, Struct_Pair>)(Struct_Pair apf) -> {
					if (!((boolean)l6_stop.value)) {
						final Struct_MizFCluster l22_fc = ((Struct_MizFCluster)Module_vector.f_getVectorUnsafe(((l14_eq_0).f_clusters).f_functor, ((int)(apf).f_first)));
						return l8_applyOne.invoke(((Struct_MizAttrColl)(apf).f_second), ((Integer)10), ((Integer)11), Module_equalizer.f_mizANInstFCluster(l14_eq_0, l22_fc, l15_enr));
					} else {
						return null;
					}
				};
				return Module_vector.f_iterVector(l20_$19, ((Func1<Object,Object>)(Func1)l21_$18));
			};
			Module_loop.f_while(l12_$10, l13_$11);
			return ((boolean)l5_r.value);
		}
	}
	public static final Object f_mizSetContr(Struct_MizEqualizer aeq_0, int ai) {
		((Field_contr)aeq_0).set_contr(ai);
		return null;
	}
	public static final Object f_mizSetTrmInfo(Struct at, int av) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 77/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2___ = (l1__tmp).f_sort;
			final int l3___ = (l1__tmp).f_varNr;
			final int l4___ = (l1__tmp).f_pattNr;
			final int l5___ = (l1__tmp).f_trmInfo;
			final Struct_MizVarTrm l6_x = l1__tmp;
			((Field_trmInfo)l6_x).set_trmInfo(av);
			return null;
		}
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l7___ = (l1__tmp).f_sort;
			final int l8___ = (l1__tmp).f_funcNr;
			final Struct l9___ = (l1__tmp).f_args;
			final int l10___ = (l1__tmp).f_pattNr;
			final int l11___ = (l1__tmp).f_trmInfo;
			final Struct_MizFuncTrm l12_x = l1__tmp;
			((Field_trmInfo)l12_x).set_trmInfo(av);
			return null;
		}
		case 61/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final int l13___ = (l1__tmp).f_funcNr;
			final Struct l14___ = (l1__tmp).f_args;
			final Struct l15___ = (l1__tmp).f_funcExp;
			final int l16___ = (l1__tmp).f_pattNr;
			final int l17___ = (l1__tmp).f_trmInfo;
			final Struct_MizPrivFuncTrm l18_x = l1__tmp;
			((Field_trmInfo)l18_x).set_trmInfo(av);
			return null;
		}
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l1__tmp = (Struct_MizFraenkelTrm)l0__tmp;
			final Struct_Vector l19___ = (l1__tmp).f_lambdaArgs;
			final Struct l20___ = (l1__tmp).f_scope;
			final Struct l21___ = (l1__tmp).f_compr;
			final Struct_MizIntSeq l22___ = (l1__tmp).f_idents;
			final int l23___ = (l1__tmp).f_pattNr;
			final int l24___ = (l1__tmp).f_trmInfo;
			final Struct_MizFraenkelTrm l25_x = l1__tmp;
			((Field_trmInfo)l25_x).set_trmInfo(av);
			return null;
		}
		case 62/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
			final Struct l26___ = (l1__tmp).f_trmProper;
			final Struct_MizTyp l27___ = (l1__tmp).f_qua;
			final int l28___ = (l1__tmp).f_pattNr;
			final int l29___ = (l1__tmp).f_trmInfo;
			final Struct_MizQuaTrm l30_x = l1__tmp;
			((Field_trmInfo)l30_x).set_trmInfo(av);
			return null;
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
			final Struct_MizTyp l31___ = (l1__tmp).f_choiceTyp;
			final int l32___ = (l1__tmp).f_pattNr;
			final int l33___ = (l1__tmp).f_trmInfo;
			final Struct_MizChoiceTrm l34_x = l1__tmp;
			((Field_trmInfo)l34_x).set_trmInfo(av);
			return null;
		}
		case 16/*MizBaseTrm*/: {
			final Struct_MizBaseTrm l1__tmp = (Struct_MizBaseTrm)l0__tmp;
			final int l35___ = (l1__tmp).f_sort;
			final int l36___ = (l1__tmp).f_trmInfo;
			final Struct_MizBaseTrm l37_x = l1__tmp;
			((Field_trmInfo)l37_x).set_trmInfo(av);
			return null;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_mizSortedFind(Struct_MizEqualizer aeq_0, Struct_Vector av, Struct at) {
		final Reference<Struct> l0_res = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
		final Reference<Integer> l1_i = ((Reference<Integer>)(new Reference(0)));
		final Struct_Vector l5_v = av;
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((Boolean)((((int)l1_i.value)<Module_vector.f_sizeVector(l5_v))&&Module_maybe.f_isNone(l0_res.value)));
		};
		final Struct_Vector l7_v = av;
		final Struct_MizEqualizer l8_eq_0 = aeq_0;
		final Struct l9_t = at;
		final Func0<Object> l6_$3 = (Func0<Object>)() -> {
			final Struct l7_cand = ((Struct)Module_vector.f_getVectorUnsafe(l7_v, ((int)l1_i.value)));
			if ((Module_equalizer.f_mizCompRdTrmsPub((l8_eq_0).f_env, l7_cand, l9_t)==0)) {
				((Reference<Struct_Some>)(Reference)l0_res).value = (new Struct_Some(l7_cand));
			} else {
			}
			l1_i.value = ((Integer)(((int)l1_i.value)+1));
			return null;
		};
		Module_loop.f_while(l4_$2, l6_$3);
		return l0_res.value;
	}
	public static final Object f_mizSubstVarP(Struct_MizEqualizer aeq_0, int alVar, int asVar) {
		final Struct_MizPolynomial l0_lPoly = Module_polynom.f_mizPolyWithMonomial((new Struct_MizComplex(1.0, 0.0)), asVar);
		final Reference<Integer> l1_t = ((Reference<Integer>)(new Reference(1)));
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return (((int)l1_t.value)<=Module_equalizer.f_mizTrmNbr(l5_eq_0));
		};
		final Struct_MizEqualizer l7_eq_0 = aeq_0;
		final int l8_lVar = alVar;
		final Func0<Object> l6_$3 = (Func0<Object>)() -> {
			final Struct_MizTrmSRec l7_rec = Module_equalizer.f_mizTrmSAt(l7_eq_0, ((int)l1_t.value));
			final Struct l8_gsymswitch87 = (l7_rec).f_eqClass;
			boolean l9_live;
			Struct l10__tmp = l8_gsymswitch87;
			switch (l10__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				l9_live=false;
				break;
			}
			default: {
				l9_live=true;
				break;
			}
			}
			if (((((int)l1_t.value)!=l8_lVar)&&l9_live)) {
				final Struct_Vector l12_changed = Module_vector.f_makeVector(2);
				final Reference<Integer> l13_i = ((Reference<Integer>)(new Reference(0)));
				final Func0<Boolean> l16_$14 = (Func0<Boolean>)() -> {
					return (((int)l13_i.value)<Module_vector.f_sizeVector((l7_rec).f_polynomialValues));
				};
				final Func0<Object> l17_$15 = (Func0<Object>)() -> {
					final Struct_MizPolynomial l18_pv = ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l7_rec).f_polynomialValues, ((int)l13_i.value)));
					if (Module_polynom.f_mizPolyHasTheVariable(l18_pv, l8_lVar)) {
						Module_polynom.f_mizPolyInsertValue(l18_pv, l8_lVar, l0_lPoly);
						if (Module_polynom.f_mizPolyIsNumeric(l18_pv)) {
							if (!(l7_rec).f_numDetermined) {
								((Field_numDetermined)l7_rec).set_numDetermined(true);
								((Field_numValue)l7_rec).set_numValue(Module_polynom.f_mizPolyGetNumeric(l18_pv));
							} else {
								if (!Module_polynom.f_mizPolyIsNumericEqualWith(l18_pv, (l7_rec).f_numValue)) {
									Module_equalizer.f_mizSetContr(l7_eq_0, 15);
								} else {
								}
							}
						} else {
						}
						Module_vector.f_pushVector(l12_changed, l18_pv);
						return Module_vector.f_removeVector((l7_rec).f_polynomialValues, ((int)l13_i.value));
					} else {
						l13_i.value = ((Integer)(((int)l13_i.value)+1));
						return null;
					}
				};
				Module_loop.f_while(l16_$14, l17_$15);
				final Func1<Object,Struct_MizPolynomial> l19_$18 = (Func1<Object, Struct_MizPolynomial>)(Struct_MizPolynomial apv) -> {
					return Module_equalizer.f_mizPolyValInsert((l7_rec).f_polynomialValues, apv);
				};
				Module_vector.f_iterVector(l12_changed, ((Func1<Object,Object>)(Func1)l19_$18));
			} else {
			}
			l1_t.value = ((Integer)(((int)l1_t.value)+1));
			return null;
		};
		return Module_loop.f_while(l4_$2, l6_$3);
	}
	public static final Object f_mizSubstitutePendingVars(Struct_MizEqualizer aeq_0, Struct_MizNatFunc apend) {
		final Struct_MizNatFunc l3_pend = apend;
		final Func0<Boolean> l2_$0 = (Func0<Boolean>)() -> {
			return (Module_mobjects.f_mizNatCount(l3_pend)>0);
		};
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Struct_MizNatFunc l6_pend = apend;
		final Func0<Object> l4_$1 = (Func0<Object>)() -> {
			final int l5_k = Module_equalizer.f_mizClassOf(l5_eq_0, (Module_mobjects.f_mizNatItem(l6_pend, 0)).f_x);
			Module_mobjects.f_mizNatDeleteElem(l6_pend, (Module_mobjects.f_mizNatItem(l6_pend, 0)).f_x);
			final Struct_MizTrmSRec l6_rec = Module_equalizer.f_mizTrmSAt(l5_eq_0, l5_k);
			if ((Module_vector.f_sizeVector((l6_rec).f_polynomialValues)==1)) {
				final boolean l7___s = Module_equalizer.f_mizSubstituteVariableP(l5_eq_0, l5_k, ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l6_rec).f_polynomialValues, 0)), l6_pend);
				return null;
			} else {
				return null;
			}
		};
		Module_loop.f_while(l2_$0, l4_$1);
		final Reference<Boolean> l7_go = ((Reference<Boolean>)(new Reference(true)));
		final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
			return l7_go.value;
		};
		final Struct_MizEqualizer l12_eq_0 = aeq_0;
		final Struct_MizNatFunc l13_pend = apend;
		final Func0<Object> l11_$9 = (Func0<Object>)() -> {
			final Reference<Integer> l12_t = ((Reference<Integer>)(new Reference(1)));
			final Func0<Boolean> l15_$13 = (Func0<Boolean>)() -> {
				return (((int)l12_t.value)<=Module_equalizer.f_mizTrmNbr(l12_eq_0));
			};
			final Func0<Object> l16_$14 = (Func0<Object>)() -> {
				final Struct_MizTrmSRec l17_rec = Module_equalizer.f_mizTrmSAt(l12_eq_0, ((int)l12_t.value));
				final Struct l18_gsymswitch88 = (l17_rec).f_eqClass;
				boolean l19_live;
				Struct l20__tmp = l18_gsymswitch88;
				switch (l20__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l19_live=false;
					break;
				}
				default: {
					l19_live=true;
					break;
				}
				}
				if (((l19_live&&(l17_rec).f_numDetermined)&&(Module_vector.f_sizeVector((l17_rec).f_polynomialValues)==1))) {
					Module_mobjects.f_mizNatInsertElem(l13_pend, ((int)l12_t.value));
				} else {
				}
				l12_t.value = ((Integer)(((int)l12_t.value)+1));
				return null;
			};
			Module_loop.f_while(l15_$13, l16_$14);
			final Reference<Boolean> l17_subst = ((Reference<Boolean>)(new Reference(false)));
			final Func0<Boolean> l20_$18 = (Func0<Boolean>)() -> {
				return (Module_mobjects.f_mizNatCount(l13_pend)>0);
			};
			final Func0<Object> l21_$19 = (Func0<Object>)() -> {
				final int l22_k = Module_equalizer.f_mizClassOf(l12_eq_0, (Module_mobjects.f_mizNatItem(l13_pend, 0)).f_x);
				Module_mobjects.f_mizNatDeleteElem(l13_pend, (Module_mobjects.f_mizNatItem(l13_pend, 0)).f_x);
				final Struct_MizTrmSRec l23_rec = Module_equalizer.f_mizTrmSAt(l12_eq_0, l22_k);
				if ((Module_vector.f_sizeVector((l23_rec).f_polynomialValues)==1)) {
					if (Module_equalizer.f_mizSubstituteVariableP(l12_eq_0, l22_k, ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l23_rec).f_polynomialValues, 0)), l13_pend)) {
						l17_subst.value = ((Boolean)true);
						return null;
					} else {
						return null;
					}
				} else {
					return null;
				}
			};
			Module_loop.f_while(l20_$18, l21_$19);
			l7_go.value = l17_subst.value;
			return null;
		};
		return Module_loop.f_while(l10_$8, l11_$9);
	}
	public static final Object f_mizSubstituteSettings(Struct_MizEqualizer aeq_0) {
		final Struct_MizNatFunc l0_pend = Module_mobjects.f_mizInitNatSet(8);
		final Struct_Vector l3_$2 = (aeq_0).f_settings;
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func1<Object,Struct_MizIntPair> l4_$1 = (Func1<Object, Struct_MizIntPair>)(Struct_MizIntPair ap) -> {
			if (((l5_eq_0).f_contr==0)) {
				final Struct_MizTrmSRec l5_rx = Module_equalizer.f_mizTrmSAt(l5_eq_0, Module_equalizer.f_mizClassOf(l5_eq_0, (ap).f_x));
				final Struct_MizTrmSRec l6_ry = Module_equalizer.f_mizTrmSAt(l5_eq_0, Module_equalizer.f_mizClassOf(l5_eq_0, (ap).f_y));
				if (((Module_vector.f_sizeVector((l5_rx).f_polynomialValues)>0)&&(Module_vector.f_sizeVector((l6_ry).f_polynomialValues)>0))) {
					final boolean l7___s = Module_equalizer.f_mizSubstituteVariableP(l5_eq_0, (ap).f_x, ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l6_ry).f_polynomialValues, 0)), l0_pend);
				} else {
				}
				return Module_equalizer.f_mizUnionTrms(l5_eq_0, (ap).f_x, (ap).f_y);
			} else {
				return null;
			}
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		Module_vector.f_clearVector((aeq_0).f_settings);
		return Module_equalizer.f_mizSubstitutePendingVars(aeq_0, l0_pend);
	}
	public static final boolean f_mizSubstituteVariableP(Struct_MizEqualizer aeq_0, int aaVar, Struct_MizPolynomial aaTerm, Struct_MizNatFunc apend) {
		final Reference<Boolean> l0_res = ((Reference<Boolean>)(new Reference(false)));
		if (!Module_polynom.f_mizPolyHasTheVariable(aaTerm, aaVar)) {
			final int l1_lVar = Module_equalizer.f_mizClassOf(aeq_0, aaVar);
			final Reference<Integer> l2_t = ((Reference<Integer>)(new Reference(1)));
			final Struct_MizEqualizer l6_eq_0 = aeq_0;
			final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
				return (((int)l2_t.value)<=Module_equalizer.f_mizTrmNbr(l6_eq_0));
			};
			final Struct_MizEqualizer l8_eq_0 = aeq_0;
			final int l9_aVar = aaVar;
			final Struct_MizPolynomial l10_aTerm = aaTerm;
			final Struct_MizNatFunc l11_pend = apend;
			final Func0<Object> l7_$4 = (Func0<Object>)() -> {
				final Struct_MizTrmSRec l8_rec = Module_equalizer.f_mizTrmSAt(l8_eq_0, ((int)l2_t.value));
				final Struct l9_gsymswitch86 = (l8_rec).f_eqClass;
				boolean l10_live;
				Struct l11__tmp = l9_gsymswitch86;
				switch (l11__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l10_live=false;
					break;
				}
				default: {
					l10_live=true;
					break;
				}
				}
				if (((((int)l2_t.value)!=l1_lVar)&&l10_live)) {
					final Struct_Vector l13_changed = Module_vector.f_makeVector(2);
					final Reference<Integer> l14_i = ((Reference<Integer>)(new Reference(0)));
					final Func0<Boolean> l17_$15 = (Func0<Boolean>)() -> {
						return (((int)l14_i.value)<Module_vector.f_sizeVector((l8_rec).f_polynomialValues));
					};
					final Func0<Object> l18_$16 = (Func0<Object>)() -> {
						final Struct_MizPolynomial l19_pv = ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l8_rec).f_polynomialValues, ((int)l14_i.value)));
						if (Module_polynom.f_mizPolyHasTheVariable(l19_pv, l9_aVar)) {
							Module_polynom.f_mizPolyInsertValue(l19_pv, l9_aVar, l10_aTerm);
							if (Module_polynom.f_mizPolyIsNumeric(l19_pv)) {
								if (!(l8_rec).f_numDetermined) {
									Module_mobjects.f_mizNatInsertElem(l11_pend, ((int)l2_t.value));
									((Field_numDetermined)l8_rec).set_numDetermined(true);
									((Field_numValue)l8_rec).set_numValue(Module_polynom.f_mizPolyGetNumeric(l19_pv));
								} else {
									if (!Module_polynom.f_mizPolyIsNumericEqualWith(l19_pv, (l8_rec).f_numValue)) {
										Module_equalizer.f_mizSetContr(l8_eq_0, 4);
									} else {
									}
								}
							} else {
							}
							Module_vector.f_pushVector(l13_changed, l19_pv);
							Module_vector.f_removeVector((l8_rec).f_polynomialValues, ((int)l14_i.value));
							l0_res.value = ((Boolean)true);
							return null;
						} else {
							l14_i.value = ((Integer)(((int)l14_i.value)+1));
							return null;
						}
					};
					Module_loop.f_while(l17_$15, l18_$16);
					final Func1<Object,Struct_MizPolynomial> l20_$19 = (Func1<Object, Struct_MizPolynomial>)(Struct_MizPolynomial apv) -> {
						return Module_equalizer.f_mizPolyValInsert((l8_rec).f_polynomialValues, apv);
					};
					Module_vector.f_iterVector(l13_changed, ((Func1<Object,Object>)(Func1)l20_$19));
				} else {
				}
				l2_t.value = ((Integer)(((int)l2_t.value)+1));
				return null;
			};
			Module_loop.f_while(l5_$3, l7_$4);
		} else {
		}
		return ((boolean)l0_res.value);
	}
	public static final Object f_mizSuperClusterRoundLoop(Struct_MizEqualizer aeq_0) {
		Module_vector.f_clearVector((aeq_0).f_anDeps);
		final Reference<Integer> l0_i = ((Reference<Integer>)(new Reference(1)));
		final Struct_MizEqualizer l4_eq_0 = aeq_0;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return (((int)l0_i.value)<=Module_equalizer.f_mizTrmNbr(l4_eq_0));
		};
		final Struct_MizEqualizer l6_eq_0 = aeq_0;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			Module_vector.f_pushVector((l6_eq_0).f_anDeps, Module_equalizer.f_mizANDependentClasses(l6_eq_0, ((int)l0_i.value)));
			l0_i.value = ((Integer)(((int)l0_i.value)+1));
			return null;
		};
		Module_loop.f_while(l3_$1, l5_$2);
		final Reference<Integer> l7_j = ((Reference<Integer>)(new Reference(1)));
		final Struct_MizEqualizer l11_eq_0 = aeq_0;
		final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
			return (((int)l7_j.value)<=Module_equalizer.f_mizTrmNbr(l11_eq_0));
		};
		final Struct_MizEqualizer l13_eq_0 = aeq_0;
		final Func0<Object> l12_$9 = (Func0<Object>)() -> {
			final Struct l13_gsymswitch67 = (Module_equalizer.f_mizTrmSAt(l13_eq_0, ((int)l7_j.value))).f_eqClass;
			boolean l14_live;
			Struct l15__tmp = l13_gsymswitch67;
			switch (l15__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				l14_live=false;
				break;
			}
			default: {
				l14_live=true;
				break;
			}
			}
			if (l14_live) {
				Module_mobjects.f_mizNatInsertElem((l13_eq_0).f_anStack, ((int)l7_j.value));
			} else {
			}
			l7_j.value = ((Integer)(((int)l7_j.value)+1));
			return null;
		};
		Module_loop.f_while(l10_$8, l12_$9);
		final Struct_MizEqualizer l17_eq_0 = aeq_0;
		final Func0<Boolean> l16_$14 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l17_eq_0).f_contr==0)&&(Module_mobjects.f_mizNatCount((l17_eq_0).f_anStack)>0)));
		};
		final Struct_MizEqualizer l19_eq_0 = aeq_0;
		final Func0<Object> l18_$15 = (Func0<Object>)() -> {
			final int l19_ani = (Module_mobjects.f_mizNatItem((l19_eq_0).f_anStack, 0)).f_x;
			Module_mobjects.f_mizNatDeleteElem((l19_eq_0).f_anStack, l19_ani);
			if ((Module_equalizer.f_mizRoundUpSuperCluster(l19_eq_0, l19_ani)&&((l19_eq_0).f_contr==0))) {
				final Reference<Integer> l20_anj = ((Reference<Integer>)(new Reference(1)));
				final Func0<Boolean> l23_$21 = (Func0<Boolean>)() -> {
					return (((int)l20_anj.value)<=Module_equalizer.f_mizTrmNbr(l19_eq_0));
				};
				final Func0<Object> l24_$22 = (Func0<Object>)() -> {
					if (Module_equalizer.f_mizANDependsOn(l19_eq_0, l19_ani, ((int)l20_anj.value))) {
						final Struct l25_gsymswitch68 = ((Struct)Module_vector.f_getVectorUnsafe((l19_eq_0).f_anDeps, (((int)l20_anj.value)-1)));
						Struct l26__tmp = l25_gsymswitch68;
						switch (l26__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l27__tmp = (Struct_Some)l26__tmp;
							final Object l28_ns = l27__tmp.f_value;
							final Struct_MizNatFunc l29_ns = ((Struct_MizNatFunc)(l27__tmp).f_value);
							if (!Module_mobjects.f_mizNatHasInDom(l29_ns, l19_ani)) {
								Module_mobjects.f_mizNatInsertElem(l29_ns, l19_ani);
							} else {
							}
							break;
						}
						case 82/*None*/: {
							break;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l26__tmp.getTypeName());
						}
					} else {
					}
					l20_anj.value = ((Integer)(((int)l20_anj.value)+1));
					return null;
				};
				Module_loop.f_while(l23_$21, l24_$22);
				final Struct l25_gsymswitch69 = Module_equalizer.f_mizANDependentClasses(l19_eq_0, l19_ani);
				Struct l26__tmp = l25_gsymswitch69;
				switch (l26__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l27__tmp = (Struct_Some)l26__tmp;
					final Object l28_ns1 = l27__tmp.f_value;
					final Struct_MizNatFunc l29_ns1 = ((Struct_MizNatFunc)(l27__tmp).f_value);
					final Reference<Integer> l30_z = ((Reference<Integer>)(new Reference(0)));
					final Func0<Boolean> l33_$31 = (Func0<Boolean>)() -> {
						return (((int)l30_z.value)<Module_mobjects.f_mizNatCount(l29_ns1));
					};
					final Func0<Object> l34_$32 = (Func0<Object>)() -> {
						Module_mobjects.f_mizNatInsertElem((l19_eq_0).f_anStack, (Module_mobjects.f_mizNatItem(l29_ns1, ((int)l30_z.value))).f_x);
						l30_z.value = ((Integer)(((int)l30_z.value)+1));
						return null;
					};
					return Module_loop.f_while(l33_$31, l34_$32);
				}
				case 82/*None*/: {
					return null;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l26__tmp.getTypeName());
				}
			} else {
				return null;
			}
		};
		return Module_loop.f_while(l16_$14, l18_$15);
	}
	public static final int f_mizTrmInfoOf(Struct at) {
		Struct l0__tmp = at;
		switch (l0__tmp.getTypeId()) {
		case 77/*MizVarTrm*/: {
			final Struct_MizVarTrm l1__tmp = (Struct_MizVarTrm)l0__tmp;
			final int l2_trmInfo = l1__tmp.f_trmInfo;
			final int l3___ = (l1__tmp).f_sort;
			final int l4___ = (l1__tmp).f_varNr;
			final int l5___ = (l1__tmp).f_pattNr;
			final int l6_trmInfo = (l1__tmp).f_trmInfo;
			return l6_trmInfo;
		}
		case 45/*MizFuncTrm*/: {
			final Struct_MizFuncTrm l1__tmp = (Struct_MizFuncTrm)l0__tmp;
			final int l7_trmInfo = l1__tmp.f_trmInfo;
			final int l8___ = (l1__tmp).f_sort;
			final int l9___ = (l1__tmp).f_funcNr;
			final Struct l10___ = (l1__tmp).f_args;
			final int l11___ = (l1__tmp).f_pattNr;
			final int l12_trmInfo = (l1__tmp).f_trmInfo;
			return l12_trmInfo;
		}
		case 61/*MizPrivFuncTrm*/: {
			final Struct_MizPrivFuncTrm l1__tmp = (Struct_MizPrivFuncTrm)l0__tmp;
			final int l13_trmInfo = l1__tmp.f_trmInfo;
			final int l14___ = (l1__tmp).f_funcNr;
			final Struct l15___ = (l1__tmp).f_args;
			final Struct l16___ = (l1__tmp).f_funcExp;
			final int l17___ = (l1__tmp).f_pattNr;
			final int l18_trmInfo = (l1__tmp).f_trmInfo;
			return l18_trmInfo;
		}
		case 42/*MizFraenkelTrm*/: {
			final Struct_MizFraenkelTrm l1__tmp = (Struct_MizFraenkelTrm)l0__tmp;
			final int l19_trmInfo = l1__tmp.f_trmInfo;
			final Struct_Vector l20___ = (l1__tmp).f_lambdaArgs;
			final Struct l21___ = (l1__tmp).f_scope;
			final Struct l22___ = (l1__tmp).f_compr;
			final Struct_MizIntSeq l23___ = (l1__tmp).f_idents;
			final int l24___ = (l1__tmp).f_pattNr;
			final int l25_trmInfo = (l1__tmp).f_trmInfo;
			return l25_trmInfo;
		}
		case 62/*MizQuaTrm*/: {
			final Struct_MizQuaTrm l1__tmp = (Struct_MizQuaTrm)l0__tmp;
			final int l26_trmInfo = l1__tmp.f_trmInfo;
			final Struct l27___ = (l1__tmp).f_trmProper;
			final Struct_MizTyp l28___ = (l1__tmp).f_qua;
			final int l29___ = (l1__tmp).f_pattNr;
			final int l30_trmInfo = (l1__tmp).f_trmInfo;
			return l30_trmInfo;
		}
		case 21/*MizChoiceTrm*/: {
			final Struct_MizChoiceTrm l1__tmp = (Struct_MizChoiceTrm)l0__tmp;
			final int l31_trmInfo = l1__tmp.f_trmInfo;
			final Struct_MizTyp l32___ = (l1__tmp).f_choiceTyp;
			final int l33___ = (l1__tmp).f_pattNr;
			final int l34_trmInfo = (l1__tmp).f_trmInfo;
			return l34_trmInfo;
		}
		case 16/*MizBaseTrm*/: {
			final Struct_MizBaseTrm l1__tmp = (Struct_MizBaseTrm)l0__tmp;
			final int l35_trmInfo = l1__tmp.f_trmInfo;
			final int l36___ = (l1__tmp).f_sort;
			final int l37_trmInfo = (l1__tmp).f_trmInfo;
			return l37_trmInfo;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final int f_mizTrmNbr(Struct_MizEqualizer aeq_0) {
		return (Module_vector.f_sizeVector((aeq_0).f_trmS)-1);
	}
	public static final Struct_MizTrmSRec f_mizTrmSAt(Struct_MizEqualizer aeq_0, int ai) {
		return ((Struct_MizTrmSRec)Module_vector.f_getVectorUnsafe((aeq_0).f_trmS, ai));
	}
	public static final Object f_mizUnionEqualsForNonComplex(Struct_MizEqualizer aeq_0) {
		final int l0_before = Module_vector.f_sizeVector((aeq_0).f_eqPend);
		final Reference<Integer> l1_i = ((Reference<Integer>)(new Reference(0)));
		final Struct_MizEqualizer l5_eq_0 = aeq_0;
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l5_eq_0).f_contr==0)&&(((int)l1_i.value)<Module_vector.f_sizeVector((l5_eq_0).f_eqPend))));
		};
		final Struct_MizEqualizer l7_eq_0 = aeq_0;
		final Func0<Object> l6_$3 = (Func0<Object>)() -> {
			final Struct_MizIntPair l7_p = ((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((l7_eq_0).f_eqPend, ((int)l1_i.value)));
			final boolean l8_cx = (Module_equalizer.f_mizIsComplexNumberE(l7_eq_0, (Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, (l7_p).f_x))).f_superCluster)||Module_equalizer.f_mizIsComplexNumberE(l7_eq_0, (Module_equalizer.f_mizTrmSAt(l7_eq_0, Module_equalizer.f_mizClassOf(l7_eq_0, (l7_p).f_y))).f_superCluster));
			if (l8_cx) {
				Module_equalizer.f_mizInsertComplexE(l7_eq_0, (l7_p).f_x);
				if (((l7_eq_0).f_contr==0)) {
					Module_equalizer.f_mizInsertComplexE(l7_eq_0, (l7_p).f_y);
				} else {
				}
				l1_i.value = ((Integer)(((int)l1_i.value)+1));
				return null;
			} else {
				Module_equalizer.f_mizUnionTrms(l7_eq_0, (l7_p).f_x, (l7_p).f_y);
				return Module_vector.f_removeVector((l7_eq_0).f_eqPend, ((int)l1_i.value));
			}
		};
		Module_loop.f_while(l4_$2, l6_$3);
		if ((((aeq_0).f_contr==0)&&(l0_before!=Module_vector.f_sizeVector((aeq_0).f_eqPend)))) {
			return Module_equalizer.f_mizIdentities(aeq_0);
		} else {
			return null;
		}
	}
	public static final Object f_mizUnionTrms(Struct_MizEqualizer aeq_0, int ae1, int ae2) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_a0 = Module_equalizer.f_mizClassOf(aeq_0, ae1);
		final int l2_b0 = Module_equalizer.f_mizClassOf(aeq_0, ae2);
		if ((l1_a0!=l2_b0)) {
			final int l3_a = ((int)Native.fast_max(l1_a0, l2_b0));
			final int l4_b = ((int)Module_runtime.f_min(l1_a0, l2_b0));
			((Field_clash)aeq_0).set_clash(true);
			final Struct_MizTrmSRec l5_ra = Module_equalizer.f_mizTrmSAt(aeq_0, l3_a);
			final Struct_MizTrmSRec l6_rb = Module_equalizer.f_mizTrmSAt(aeq_0, l4_b);
			if ((l5_ra).f_numDetermined) {
				if ((l6_rb).f_numDetermined) {
					if (!((((l5_ra).f_numValue).f_re==((l6_rb).f_numValue).f_re)&&(((l5_ra).f_numValue).f_im==((l6_rb).f_numValue).f_im))) {
						Module_equalizer.f_mizSetContr(aeq_0, 5);
					} else {
					}
				} else {
					((Field_numDetermined)l6_rb).set_numDetermined(true);
					((Field_numValue)l6_rb).set_numValue((l5_ra).f_numValue);
				}
			} else {
			}
			if (((aeq_0).f_contr==0)) {
				final Reference<Struct> l7_cur = ((Reference<Struct>)(new Reference((l5_ra).f_eqClass)));
				final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
					final Struct l11_gsymswitch15 = l7_cur.value;
					Struct l12__tmp = l11_gsymswitch15;
					switch (l12__tmp.getTypeId()) {
					case 71/*MizTrmNil*/: {
						return ((Boolean)false);
					}
					default: {
						return ((Boolean)true);
					}
					}
				};
				final Struct_MizEqualizer l12_eq_0 = aeq_0;
				final Func0<Object> l11_$9 = (Func0<Object>)() -> {
					final Struct l12_gsymswitch16 = l7_cur.value;
					Struct l13__tmp = l12_gsymswitch16;
					switch (l13__tmp.getTypeId()) {
					case 70/*MizTrmElem*/: {
						final Struct_MizTrmElem l14__tmp = (Struct_MizTrmElem)l13__tmp;
						final Struct l15_trm = l14__tmp.f_trm;
						final Struct l16_next = l14__tmp.f_next;
						final Struct l17_trm = (l14__tmp).f_trm;
						final Struct l18_next = (l14__tmp).f_next;
						Module_equalizer.f_mizSetTrmInfo((Module_equalizer.f_mizTrmSAt(l12_eq_0, Module_equalizer.f_mizTrmInfoOf(l17_trm))).f_term, l4_b);
						l7_cur.value = l18_next;
						return null;
					}
					case 71/*MizTrmNil*/: {
						return null;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l13__tmp.getTypeName());
					}
				};
				Module_loop.f_while(l10_$8, l11_$9);
				((Field_eqClass)l6_rb).set_eqClass(Module_equalizer.f_mizConcatTrmLists((l6_rb).f_eqClass, (l5_ra).f_eqClass));
				((Field_eqClass)l5_ra).set_eqClass(SingletonStructs.str_MizTrmNil);
				final Struct_MizAttrColl l13_merged = Module_correl.f_mizNewEmptyCluster();
				final Struct_Vector l16_$15 = ((l6_rb).f_superCluster).f_items;
				final Func1<Object,Struct_MizAttr> l17_$14 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr ax) -> {
					return Module_correl.f_mizAttrCollInsert(l0_env, l13_merged, Wrappers.w_mizCopyAttr.invoke(ax));
				};
				Module_vector.f_iterVector(l16_$15, ((Func1<Object,Object>)(Func1)l17_$14));
				final Struct_Vector l20_$19 = ((l5_ra).f_superCluster).f_items;
				final Func1<Object,Struct_MizAttr> l21_$18 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr ax) -> {
					return Module_correl.f_mizAttrCollInsert(l0_env, l13_merged, Wrappers.w_mizCopyAttr.invoke(ax));
				};
				Module_vector.f_iterVector(l20_$19, ((Func1<Object,Object>)(Func1)l21_$18));
				((Field_superCluster)l6_rb).set_superCluster(l13_merged);
				if (!(l13_merged).f_consistent) {
					Module_equalizer.f_mizSetContr(aeq_0, 6);
				} else {
				}
				((Field_superCluster)l5_ra).set_superCluster(Module_correl.f_mizNewEmptyCluster());
				final Struct_Vector l24_$23 = (l5_ra).f_xTypClass;
				final Struct_MizEqualizer l26_eq_0 = aeq_0;
				final Func1<Object,Struct_MizTyp> l25_$22 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp atyp) -> {
					return Module_equalizer.f_mizInsertType(l26_eq_0, atyp, l4_b);
				};
				Module_vector.f_iterVector(l24_$23, ((Func1<Object,Object>)(Func1)l25_$22));
				Module_vector.f_clearVector((l5_ra).f_xTypClass);
				final Struct_Vector l29_$28 = (l5_ra).f_polynomialValues;
				final Func1<Object,Struct_MizPolynomial> l30_$27 = (Func1<Object, Struct_MizPolynomial>)(Struct_MizPolynomial apv) -> {
					return Module_equalizer.f_mizPolyValInsert((l6_rb).f_polynomialValues, apv);
				};
				Module_vector.f_iterVector(l29_$28, ((Func1<Object,Object>)(Func1)l30_$27));
				Module_vector.f_clearVector((l5_ra).f_polynomialValues);
				if ((Module_vector.f_sizeVector((l6_rb).f_polynomialValues)==2)) {
					final Struct_MizNatFunc l31_pend = Module_mobjects.f_mizInitNatSet(8);
					final int l32_iP = Module_polynom.f_mizPolyIsVariable(((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l6_rb).f_polynomialValues, 0)));
					final int l33_jP = Module_polynom.f_mizPolyIsVariable(((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l6_rb).f_polynomialValues, 1)));
					if ((l32_iP>0)) {
						if (((l33_jP>0)&&(l32_iP<l33_jP))) {
							Module_vector.f_removeVector((l6_rb).f_polynomialValues, 1);
							final boolean l34___sv1 = Module_equalizer.f_mizSubstituteVariableP(aeq_0, l33_jP, ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l6_rb).f_polynomialValues, 0)), l31_pend);
						} else {
							Module_vector.f_removeVector((l6_rb).f_polynomialValues, 0);
							final boolean l35___sv2 = Module_equalizer.f_mizSubstituteVariableP(aeq_0, l32_iP, ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l6_rb).f_polynomialValues, 0)), l31_pend);
						}
					} else {
						if ((l33_jP>0)) {
							Module_vector.f_removeVector((l6_rb).f_polynomialValues, 1);
							final boolean l36___sv3 = Module_equalizer.f_mizSubstituteVariableP(aeq_0, l33_jP, ((Struct_MizPolynomial)Module_vector.f_getVectorUnsafe((l6_rb).f_polynomialValues, 0)), l31_pend);
						} else {
						}
					}
					return Module_equalizer.f_mizSubstitutePendingVars(aeq_0, l31_pend);
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
	public static final boolean f_mizYArgList(Struct_MizEqualizer aeq_0, Struct al) {
		Module_equalizer.f_mizYTermList(aeq_0, al);
		final Reference<Boolean> l0_ok = ((Reference<Boolean>)(new Reference(true)));
		final Reference<Struct> l1_cur = ((Reference<Struct>)(new Reference(al)));
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			final Struct l5_gsymswitch1 = l1_cur.value;
			Struct l6__tmp = l5_gsymswitch1;
			switch (l6__tmp.getTypeId()) {
			case 71/*MizTrmNil*/: {
				return ((Boolean)false);
			}
			default: {
				return ((Boolean)true);
			}
			}
		};
		final Func0<Object> l5_$3 = (Func0<Object>)() -> {
			final Struct l6_gsymswitch2 = l1_cur.value;
			Struct l7__tmp = l6_gsymswitch2;
			switch (l7__tmp.getTypeId()) {
			case 70/*MizTrmElem*/: {
				final Struct_MizTrmElem l8__tmp = (Struct_MizTrmElem)l7__tmp;
				final Struct l9_trm = l8__tmp.f_trm;
				final Struct l10_next = l8__tmp.f_next;
				final Struct l11_trm = (l8__tmp).f_trm;
				final Struct l12_next = (l8__tmp).f_next;
				if ((Module_equalizer.f_mizTrmInfoOf(l11_trm)==0)) {
					l0_ok.value = ((Boolean)false);
				} else {
				}
				l1_cur.value = l12_next;
				return null;
			}
			case 71/*MizTrmNil*/: {
				return null;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l7__tmp.getTypeName());
			}
		};
		Module_loop.f_while(l4_$2, l5_$3);
		return ((boolean)l0_ok.value);
	}
	public static final Object f_mizYCluster(Struct_MizEqualizer aeq_0, Struct_MizAttrColl ac) {
		final Struct_Vector l2_$1 = (ac).f_items;
		final Struct_MizEqualizer l4_eq_0 = aeq_0;
		final Func1<Object,Struct_MizAttr> l3_$0 = (Func1<Object, Struct_MizAttr>)(Struct_MizAttr aa) -> {
			return Module_equalizer.f_mizYTermList(l4_eq_0, (aa).f_args);
		};
		return Module_vector.f_iterVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
	}
	public static final Object f_mizYEqClass(Struct_MizEqualizer aeq_0, Struct at) {
		if ((Module_equalizer.f_mizTrmNbr(aeq_0)>=Module_limits.g_MaxTrmNbr)) {
			((Field_trmOvfl)aeq_0).set_trmOvfl(true);
			return null;
		} else {
			final int l0_nr = (Module_equalizer.f_mizTrmNbr(aeq_0)+1);
			Module_equalizer.f_mizSetTrmInfo(at, l0_nr);
			((Field_eqClassNbr)aeq_0).set_eqClassNbr(((aeq_0).f_eqClassNbr+1));
			final Struct l1_rep = Module_correl.f_mizNewVarTrm(Module_lexicon.g_ikTrmEqConst, (aeq_0).f_eqClassNbr);
			Module_equalizer.f_mizSetTrmInfo(l1_rep, l0_nr);
			final Struct_Vector l2_xt = Module_vector.f_makeVector(4);
			Module_vector.f_pushVector(l2_xt, Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), Module_builtin.f_mizBuiltIn(((aeq_0).f_env).f_builtIn, Module_builtin.g_rqAny), ((Struct)SingletonStructs.str_MizTrmNil)));
			return Module_vector.f_pushVector((aeq_0).f_trmS, (new Struct_MizTrmSRec(l1_rep, false, (new Struct_MizComplex(0.0, 0.0)), (new Struct_MizTrmElem(at, ((Struct)SingletonStructs.str_MizTrmNil))), l2_xt, Module_correl.f_mizNewEmptyCluster(), Module_vector.f_makeVector(2))));
		}
	}
	public static final Object f_mizYFormula(Struct_MizEqualizer aeq_0, Struct af) {
		TAIL_CALL: for(;;) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		Struct l1__tmp = af;
		switch (l1__tmp.getTypeId()) {
		case 52/*MizNegFrm*/: {
			final Struct_MizNegFrm l2__tmp = (Struct_MizNegFrm)l1__tmp;
			final Struct l3_arg = l2__tmp.f_arg;
			final Struct l4_arg = (l2__tmp).f_arg;
			final int l5___ = (l2__tmp).f_pattNr;
			{
				final Struct l6___tmp = l4_arg;
				af = l6___tmp;
				continue TAIL_CALL;
			}
		}
		case 26/*MizConjFrm*/: {
			final Struct_MizConjFrm l2__tmp = (Struct_MizConjFrm)l1__tmp;
			final Struct_Vector l7_conjuncts = l2__tmp.f_conjuncts;
			final Struct_Vector l8_conjuncts = (l2__tmp).f_conjuncts;
			final int l9___ = (l2__tmp).f_pattNr;
			final Struct_MizEqualizer l12_eq_0 = aeq_0;
			final Func1<Object,Struct> l11_$10 = (Func1<Object, Struct>)(Struct ag) -> {
				return Module_equalizer.f_mizYFormula(l12_eq_0, ag);
			};
			return Module_vector.f_iterVector(l8_conjuncts, ((Func1<Object,Object>)(Func1)l11_$10));
		}
		case 76/*MizUnivFrm*/: {
			final Struct_MizUnivFrm l2__tmp = (Struct_MizUnivFrm)l1__tmp;
			final Struct_MizTyp l13_quantified = l2__tmp.f_quantified;
			final Struct l14_scope = l2__tmp.f_scope;
			final int l15___ = (l2__tmp).f_sort;
			final int l16___ = (l2__tmp).f_varId;
			final Struct_MizTyp l17_quantified = (l2__tmp).f_quantified;
			final Struct l18_scope = (l2__tmp).f_scope;
			final int l19___ = (l2__tmp).f_pattNr;
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
			Module_equalizer.f_mizYType(aeq_0, l17_quantified);
			Module_equalizer.f_mizYFormula(aeq_0, l18_scope);
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-1));
			return null;
		}
		case 58/*MizPredFrm*/: {
			final Struct_MizPredFrm l2__tmp = (Struct_MizPredFrm)l1__tmp;
			final Struct l20_args = l2__tmp.f_args;
			final int l21___ = (l2__tmp).f_sort;
			final int l22___ = (l2__tmp).f_predNr;
			final Struct l23_args = (l2__tmp).f_args;
			final int l24___ = (l2__tmp).f_pattNr;
			return Module_equalizer.f_mizYTermList(aeq_0, l23_args);
		}
		case 49/*MizLocPredFrm*/: {
			final Struct_MizLocPredFrm l2__tmp = (Struct_MizLocPredFrm)l1__tmp;
			final Struct l25_args = l2__tmp.f_args;
			final int l26___ = (l2__tmp).f_predNr;
			final Struct l27_args = (l2__tmp).f_args;
			final Struct l28___ = (l2__tmp).f_predExp;
			final int l29___ = (l2__tmp).f_pattNr;
			return Module_equalizer.f_mizYTermList(aeq_0, l27_args);
		}
		case 63/*MizQualFrm*/: {
			final Struct_MizQualFrm l2__tmp = (Struct_MizQualFrm)l1__tmp;
			final Struct l30_trm = l2__tmp.f_trm;
			final Struct_MizTyp l31_typ = l2__tmp.f_typ;
			final Struct l32_trm = (l2__tmp).f_trm;
			final Struct_MizTyp l33_typ = (l2__tmp).f_typ;
			final int l34___ = (l2__tmp).f_pattNr;
			Module_equalizer.f_mizYType(aeq_0, l33_typ);
			final Struct_MizQualFrm l35_qf = l2__tmp;
			((Field_trm)l35_qf).set_trm(Module_equalizer.f_mizYTerm(aeq_0, l32_trm));
			return null;
		}
		case 41/*MizFlexFrm*/: {
			final Struct_MizFlexFrm l2__tmp = (Struct_MizFlexFrm)l1__tmp;
			final Struct l36_lo = l2__tmp.f_leftOrig;
			final Struct l37_ro = l2__tmp.f_rightOrig;
			final int l38___ = (l2__tmp).f_sort;
			final Struct l39_lo = (l2__tmp).f_leftOrig;
			final Struct l40_ro = (l2__tmp).f_rightOrig;
			final Struct l41___ = (l2__tmp).f_leftTrm;
			final Struct l42___ = (l2__tmp).f_rightTrm;
			final Struct l43___ = (l2__tmp).f_expansion;
			final int l44___ = (l2__tmp).f_pattNr;
			Module_equalizer.f_mizYFormula(aeq_0, l39_lo);
			{
				final Struct l45___tmp = l40_ro;
				af = l45___tmp;
				continue TAIL_CALL;
			}
		}
		case 75/*MizUniqFrm*/: {
			final Struct_MizUniqFrm l2__tmp = (Struct_MizUniqFrm)l1__tmp;
			final int l46___ = (l2__tmp).f_sort;
			return null;
		}
		case 17/*MizBinFrm*/: {
			final Struct_MizBinFrm l2__tmp = (Struct_MizBinFrm)l1__tmp;
			final int l47___ = (l2__tmp).f_sort;
			final Struct l48___ = (l2__tmp).f_left;
			final Struct l49___ = (l2__tmp).f_right;
			final int l50___ = (l2__tmp).f_pattNr;
			return Module_errhan.f_mizRunTimeError(2023);
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l1__tmp.getTypeName());
		}
	}
	}
	public static final Struct f_mizYTerm(Struct_MizEqualizer aeq_0, Struct at0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final int l1_srt = Module_correl.f_mizTrmSort(at0);
		if ((((l1_srt==Module_lexicon.g_ikTrmBound)||(l1_srt==Module_lexicon.g_ikTrmEqConst))||(l1_srt==Module_lexicon.g_ikError))) {
			return at0;
		} else {
			if ((l1_srt==Module_lexicon.g_ikTrmInfConst)) {
				final Struct_MizVarTrm l2_vt = Module_equalizer.f_mizEqAsVar(at0);
				final Struct l3_cached = ((Struct)Module_vector.f_getVectorDef((aeq_0).f_dTrm, (l2_vt).f_varNr, SingletonStructs.str_None));
				Struct l4__tmp = l3_cached;
				switch (l4__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l5__tmp = (Struct_Some)l4__tmp;
					final Object l6_c = l5__tmp.f_value;
					final Struct l7_c = ((Struct)(l5__tmp).f_value);
					return l7_c;
				}
				case 82/*None*/: {
					Module_equalizer.f_mizYEqClass(aeq_0, at0);
					final int l8_info = Module_equalizer.f_mizTrmInfoOf(at0);
					final Struct l9_rep = (Module_equalizer.f_mizTrmSAt(aeq_0, l8_info)).f_term;
					final Struct_MizEqualizer l13_eq_0 = aeq_0;
					final Func0<Boolean> l12_$10 = (Func0<Boolean>)() -> {
						return (Module_vector.f_sizeVector((l13_eq_0).f_dTrm)<=(l2_vt).f_varNr);
					};
					final Struct_MizEqualizer l15_eq_0 = aeq_0;
					final Func0<Object> l14_$11 = (Func0<Object>)() -> {
						return Module_vector.f_pushVector((l15_eq_0).f_dTrm, SingletonStructs.str_None);
					};
					Module_loop.f_while(l12_$10, l14_$11);
					Module_vector.f_setVectorUnsafe((aeq_0).f_dTrm, (l2_vt).f_varNr, (new Struct_Some(l9_rep)));
					final Struct_MizConstDef l16_cd = ((Struct_MizConstDef)Module_vector.f_getVectorUnsafe((l0_env).f_inferConstDef, (l2_vt).f_varNr));
					final Struct l17_gsymswitch7 = Module_identify.f_mizNumericValue((aeq_0).f_idn, (l2_vt).f_varNr);
					Struct l18__tmp = l17_gsymswitch7;
					switch (l18__tmp.getTypeId()) {
					case 90/*Some*/: {
						final Struct_Some l19__tmp = (Struct_Some)l18__tmp;
						final Object l20_v = l19__tmp.f_value;
						final Struct_MizComplex l21_v = ((Struct_MizComplex)(l19__tmp).f_value);
						final Struct_MizTrmSRec l22_rec = Module_equalizer.f_mizTrmSAt(aeq_0, l8_info);
						((Field_numDetermined)l22_rec).set_numDetermined(true);
						((Field_numValue)l22_rec).set_numValue(l21_v);
						break;
					}
					case 82/*None*/: {
						break;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l18__tmp.getTypeName());
					}
					final Struct_MizTyp l23_aType = Wrappers.w_mizCopyExpTyp.invoke((l16_cd).f_typ);
					Module_equalizer.f_mizYType(aeq_0, l23_aType);
					Module_equalizer.f_mizInsertType(aeq_0, l23_aType, l8_info);
					return l9_rep;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l4__tmp.getTypeName());
				}
			} else {
				if ((l1_srt==Module_lexicon.g_ikTrmFunctor)) {
					final Struct_MizFuncTrm l24_ft = Module_equalizer.f_mizEqAsFunc(at0);
					final Struct l25_orgTrm = Module_correl.f_mizCopyTrm(at0);
					if (!Module_equalizer.f_mizYArgList(aeq_0, (l24_ft).f_args)) {
						return at0;
					} else {
						final Struct l26_lArgList1 = Module_correl.f_mizCopyTrmList((l24_ft).f_args);
						final Reference<Boolean> l27_eFree = ((Reference<Boolean>)(new Reference(true)));
						final Func2<Struct,Struct_MizWithin, Struct> l29_$28 = (Func2<Struct, Struct_MizWithin, Struct>)(Struct_MizWithin al30_0, Struct atrm) -> {
							if ((Module_correl.f_mizTrmSort(atrm)==Module_lexicon.g_ikTrmEqConst)) {
								l27_eFree.value = ((Boolean)false);
							} else {
							}
							return atrm;
						};
						final Struct_MizWithin l30_w = Module_correl.f_mizMakeWithin(l29_$28);
						final Struct l31___w1 = Module_correl.f_mizWithinTrm(l30_w, l25_orgTrm);
						Struct_MizTyp l32_aType;
						if (((boolean)l27_eFree.value)) {
							l32_aType=(l0_env).f_roundUpTrmType.invoke(l0_env, l25_orgTrm);
						} else {
							l32_aType=Module_correl_inst.f_mizCopyTrmType(l0_env, l25_orgTrm);
						}
						final Struct_Pair l33_pa = Module_correl.f_mizAdjustTrm(l0_env, l24_ft);
						final Struct l34_gsymswitch6 = Module_equalizer.f_mizFindEqArgsTerm(aeq_0, Module_equalizer.g_expFunctor, ((int)(l33_pa).f_first), ((Struct)(l33_pa).f_second));
						Struct l35__tmp = l34_gsymswitch6;
						switch (l35__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l36__tmp = (Struct_Some)l35__tmp;
							final Object l37_existing = l36__tmp.f_value;
							final Struct l38_existing = ((Struct)(l36__tmp).f_value);
							return (Module_equalizer.f_mizTrmSAt(aeq_0, Module_equalizer.f_mizTrmInfoOf(l38_existing))).f_term;
						}
						case 82/*None*/: {
							final Struct l39_nt = Module_correl.f_mizNewFuncTrm(((int)(l33_pa).f_first), Module_correl.f_mizCopyTrmList(((Struct)(l33_pa).f_second)));
							Module_equalizer.f_mizYEqClass(aeq_0, l39_nt);
							final int l40_info = Module_equalizer.f_mizTrmInfoOf(l39_nt);
							Module_equalizer.f_mizFuncIdxAllocT(aeq_0, Module_equalizer.g_expFunctor, l39_nt);
							Module_equalizer.f_mizYType(aeq_0, l32_aType);
							Module_equalizer.f_mizInsertType(aeq_0, l32_aType, l40_info);
							if ((Module_equalizer.f_mizRevReqPub((aeq_0).f_idn, Module_equalizer.f_mizAdjustedFuncNrE(l0_env, l39_nt))==Module_builtin.g_rqZeroNumber)) {
								final Struct_MizTrmSRec l41_rec0 = Module_equalizer.f_mizTrmSAt(aeq_0, l40_info);
								((Field_numDetermined)l41_rec0).set_numDetermined(true);
								((Field_numValue)l41_rec0).set_numValue((new Struct_MizComplex(0.0, 0.0)));
							} else {
							}
							final Struct_MizConstr l42_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coFunctor, (l24_ft).f_funcNr);
							if (Module_correl.f_mizHasProperty(l42_c, Module_correl.g_syCommutativity)) {
								final Struct l43_lCommTrm = Module_correl.f_mizNewFuncTrm((l24_ft).f_funcNr, Module_roundcl.f_mizSwapArguments(l26_lArgList1, (l42_c).f_firstArg, (l42_c).f_secondArg));
								final Struct_MizFuncTrm l44_swf = Module_equalizer.f_mizEqAsFunc(l43_lCommTrm);
								final Struct_Pair l45_pw = Module_correl.f_mizAdjustTrm(l0_env, l44_swf);
								final Struct l46_lCommTrm1 = Module_correl.f_mizNewFuncTrm(((int)(l45_pw).f_first), Module_correl.f_mizCopyTrmList(((Struct)(l45_pw).f_second)));
								Module_equalizer.f_mizSetTrmInfo(l46_lCommTrm1, l40_info);
								final Struct_MizTrmSRec l47_rec1 = Module_equalizer.f_mizTrmSAt(aeq_0, l40_info);
								((Field_eqClass)l47_rec1).set_eqClass(Module_equalizer.f_mizAppendTrm((l47_rec1).f_eqClass, l46_lCommTrm1));
								Module_equalizer.f_mizFuncIdxAllocT(aeq_0, Module_equalizer.g_expFunctor, l46_lCommTrm1);
							} else {
							}
							return (Module_equalizer.f_mizTrmSAt(aeq_0, l40_info)).f_term;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l35__tmp.getTypeName());
						}
					}
				} else {
					if (((((l1_srt==Module_lexicon.g_ikTrmSchFunc)||(l1_srt==Module_lexicon.g_ikTrmPrivFunc))||(l1_srt==Module_lexicon.g_ikTrmAggreg))||(l1_srt==Module_lexicon.g_ikTrmSelector))) {
						final Struct l48_args = Module_equalizer.f_mizArgsOfE(at0);
						if (!Module_equalizer.f_mizYArgList(aeq_0, l48_args)) {
							return at0;
						} else {
							int l49_kind;
							if ((l1_srt==Module_lexicon.g_ikTrmSchFunc)) {
								l49_kind=Module_equalizer.g_expSchFunc;
							} else {
								if ((l1_srt==Module_lexicon.g_ikTrmPrivFunc)) {
									l49_kind=Module_equalizer.g_expPrivFunc;
								} else {
									if ((l1_srt==Module_lexicon.g_ikTrmAggreg)) {
										l49_kind=Module_equalizer.g_expAggreg;
									} else {
										l49_kind=Module_equalizer.g_expSelector;
									}
								}
							}
							final int l50_fn = Module_equalizer.f_mizFuncNrOfE(at0);
							Struct l51_hit;
							if ((l1_srt==Module_lexicon.g_ikTrmSchFunc)) {
								l51_hit=((Struct)SingletonStructs.str_None);
							} else {
								l51_hit=Module_equalizer.f_mizFindEqArgsTerm(aeq_0, l49_kind, l50_fn, l48_args);
							}
							Struct l52__tmp = l51_hit;
							switch (l52__tmp.getTypeId()) {
							case 90/*Some*/: {
								final Struct_Some l53__tmp = (Struct_Some)l52__tmp;
								final Object l54_existing = l53__tmp.f_value;
								final Struct l55_existing = ((Struct)(l53__tmp).f_value);
								return (Module_equalizer.f_mizTrmSAt(aeq_0, Module_equalizer.f_mizTrmInfoOf(l55_existing))).f_term;
							}
							case 82/*None*/: {
								Module_equalizer.f_mizYEqClass(aeq_0, at0);
								final int l56_info2 = Module_equalizer.f_mizTrmInfoOf(at0);
								if ((l1_srt!=Module_lexicon.g_ikTrmSchFunc)) {
									Module_equalizer.f_mizFuncIdxAllocT(aeq_0, l49_kind, at0);
								} else {
								}
								final Struct_MizTyp l57_aType2 = Module_correl_inst.f_mizCopyTrmType(l0_env, at0);
								Module_equalizer.f_mizYType(aeq_0, l57_aType2);
								Module_equalizer.f_mizInsertType(aeq_0, l57_aType2, l56_info2);
								return (Module_equalizer.f_mizTrmSAt(aeq_0, l56_info2)).f_term;
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l52__tmp.getTypeName());
							}
						}
					} else {
						if ((l1_srt==Module_lexicon.g_ikTrmFraenkel)) {
							final Struct_MizFraenkelTrm l58_kt = Module_equalizer.f_mizEqAsFraenkel(at0);
							final int l59_lB = (l0_env).f_boundVarNbr;
							final Struct_Vector l62_$61 = (l58_kt).f_lambdaArgs;
							final Struct_MizEqualizer l64_eq_0 = aeq_0;
							final Func1<Object,Struct_MizTyp> l63_$60 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp atyp) -> {
								((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
								return Module_equalizer.f_mizYType(l64_eq_0, atyp);
							};
							Module_vector.f_iterVector(l62_$61, ((Func1<Object,Object>)(Func1)l63_$60));
							((Field_scope)l58_kt).set_scope(Module_equalizer.f_mizYTerm(aeq_0, (l58_kt).f_scope));
							Module_equalizer.f_mizYFormula(aeq_0, (l58_kt).f_compr);
							((Field_boundVarNbr)l0_env).set_boundVarNbr(l59_lB);
							return at0;
						} else {
							if ((l1_srt==Module_lexicon.g_ikTrmChoice)) {
								final Struct_MizChoiceTrm l65_ct = Module_equalizer.f_mizEqAsChoice(at0);
								Module_equalizer.f_mizYType(aeq_0, (l65_ct).f_choiceTyp);
								final boolean l66_bound = Module_equalizer.f_mizHasBoundE(aeq_0, at0);
								if (l66_bound) {
									return at0;
								} else {
									final Struct l67_gsymswitch5 = Module_equalizer.f_mizSortedFind(aeq_0, (aeq_0).f_choiceTerm, at0);
									Struct l68__tmp = l67_gsymswitch5;
									switch (l68__tmp.getTypeId()) {
									case 90/*Some*/: {
										final Struct_Some l69__tmp = (Struct_Some)l68__tmp;
										final Object l70_existing = l69__tmp.f_value;
										final Struct l71_existing = ((Struct)(l69__tmp).f_value);
										return (Module_equalizer.f_mizTrmSAt(aeq_0, Module_equalizer.f_mizTrmInfoOf(l71_existing))).f_term;
									}
									case 82/*None*/: {
										Module_equalizer.f_mizYEqClass(aeq_0, at0);
										final int l72_info3 = Module_equalizer.f_mizTrmInfoOf(at0);
										Module_vector.f_pushVector((aeq_0).f_choiceTerm, at0);
										final Struct_MizTyp l73_aType3 = Module_correl_inst.f_mizCopyTrmType(l0_env, at0);
										Module_equalizer.f_mizYType(aeq_0, l73_aType3);
										Module_equalizer.f_mizInsertType(aeq_0, l73_aType3, l72_info3);
										return (Module_equalizer.f_mizTrmSAt(aeq_0, l72_info3)).f_term;
									}
									default:
										throw new RuntimeException("Unexpected struct in switch: "+l68__tmp.getTypeName());
									}
								}
							} else {
								Module_errhan.f_mizRunTimeError(2024);
								return at0;
							}
						}
					}
				}
			}
		}
	}
	public static final Object f_mizYTermList(Struct_MizEqualizer aeq_0, Struct al) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = al;
		switch (l0__tmp.getTypeId()) {
		case 71/*MizTrmNil*/: {
			return null;
		}
		case 70/*MizTrmElem*/: {
			final Struct_MizTrmElem l1__tmp = (Struct_MizTrmElem)l0__tmp;
			final Struct l2_trm = l1__tmp.f_trm;
			final Struct l3_next = l1__tmp.f_next;
			final Struct l4_trm = (l1__tmp).f_trm;
			final Struct l5_next = (l1__tmp).f_next;
			final Struct_MizTrmElem l6_e = l1__tmp;
			((Field_trm)l6_e).set_trm(Module_equalizer.f_mizYTerm(aeq_0, l4_trm));
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
	public static final Object f_mizYType(Struct_MizEqualizer aeq_0, Struct_MizTyp at) {
		Module_equalizer.f_mizYCluster(aeq_0, (at).f_lower);
		Module_equalizer.f_mizYCluster(aeq_0, (at).f_upper);
		return Module_equalizer.f_mizYTermList(aeq_0, (at).f_modArgs);
	}
	public static final int f_mizYYTerm(Struct_MizEqualizer aeq_0, Struct at0, int afi0) {
		final Struct_MizCorrelEnv l0_env = (aeq_0).f_env;
		final Reference<Integer> l1_fi = ((Reference<Integer>)(new Reference(afi0)));
		final int l2_srt = Module_correl.f_mizTrmSort(at0);
		final Reference<Boolean> l3_handled = ((Reference<Boolean>)(new Reference(false)));
		if ((l2_srt==Module_lexicon.g_ikTrmNumeral)) {
			final Struct_MizVarTrm l4_vt = Module_equalizer.f_mizEqAsVar(at0);
			final Reference<Integer> l5_i = ((Reference<Integer>)(new Reference(1)));
			final Struct_MizEqualizer l9_eq_0 = aeq_0;
			final Func0<Boolean> l8_$6 = (Func0<Boolean>)() -> {
				return ((Boolean)(!((boolean)l3_handled.value)&&(((int)l5_i.value)<=Module_equalizer.f_mizTrmNbr(l9_eq_0))));
			};
			final Struct_MizEqualizer l11_eq_0 = aeq_0;
			final Func0<Object> l10_$7 = (Func0<Object>)() -> {
				final Struct_MizTrmSRec l11_rec = Module_equalizer.f_mizTrmSAt(l11_eq_0, ((int)l5_i.value));
				final Struct l12_gsymswitch14 = (l11_rec).f_eqClass;
				boolean l13_isNil;
				Struct l14__tmp = l12_gsymswitch14;
				switch (l14__tmp.getTypeId()) {
				case 71/*MizTrmNil*/: {
					l13_isNil=true;
					break;
				}
				default: {
					l13_isNil=false;
					break;
				}
				}
				if ((((!l13_isNil&&(l11_rec).f_numDetermined)&&(((l11_rec).f_numValue).f_re==Module_math.f_i2d((l4_vt).f_varNr)))&&(((l11_rec).f_numValue).f_im==0.0))) {
					l1_fi.value = ((Integer)Module_equalizer.f_mizTrmInfoOf((l11_rec).f_term));
					l3_handled.value = ((Boolean)true);
				} else {
				}
				l5_i.value = ((Integer)(((int)l5_i.value)+1));
				return null;
			};
			Module_loop.f_while(l8_$6, l10_$7);
		} else {
			if ((l2_srt==Module_lexicon.g_ikTrmFunctor)) {
				final Struct_MizFuncTrm l12_ft = Module_equalizer.f_mizEqAsFunc(at0);
				Module_equalizer.f_mizYTermList(aeq_0, (l12_ft).f_args);
				final Struct_Pair l13_pa = Module_correl.f_mizAdjustTrm(l0_env, l12_ft);
				final Struct l14_gsymswitch12 = Module_equalizer.f_mizFindEqArgsTerm(aeq_0, Module_equalizer.g_expFunctor, ((int)(l13_pa).f_first), ((Struct)(l13_pa).f_second));
				Struct l15__tmp = l14_gsymswitch12;
				switch (l15__tmp.getTypeId()) {
				case 90/*Some*/: {
					final Struct_Some l16__tmp = (Struct_Some)l15__tmp;
					final Object l17_existing = l16__tmp.f_value;
					final Struct l18_existing = ((Struct)(l16__tmp).f_value);
					l1_fi.value = ((Integer)Module_equalizer.f_mizTrmInfoOf(l18_existing));
					l3_handled.value = ((Boolean)true);
					break;
				}
				case 82/*None*/: {
					final Struct_MizConstr l19_c = Module_correl.f_mizConstr(l0_env, Module_correl.g_coFunctor, (l12_ft).f_funcNr);
					if (Module_correl.f_mizHasProperty(l19_c, Module_correl.g_syCommutativity)) {
						final Struct l20_lCommTrm = Module_correl.f_mizNewFuncTrm((l12_ft).f_funcNr, Module_roundcl.f_mizSwapArguments((l12_ft).f_args, (l19_c).f_firstArg, (l19_c).f_secondArg));
						final Struct_MizFuncTrm l21_cf = Module_equalizer.f_mizEqAsFunc(l20_lCommTrm);
						final Struct_Pair l22_pw = Module_correl.f_mizAdjustTrm(l0_env, l21_cf);
						final Struct l23_gsymswitch13 = Module_equalizer.f_mizFindEqArgsTerm(aeq_0, Module_equalizer.g_expFunctor, ((int)(l22_pw).f_first), ((Struct)(l22_pw).f_second));
						Struct l24__tmp = l23_gsymswitch13;
						switch (l24__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l25__tmp = (Struct_Some)l24__tmp;
							final Object l26_existing2 = l25__tmp.f_value;
							final Struct l27_existing2 = ((Struct)(l25__tmp).f_value);
							l1_fi.value = ((Integer)Module_equalizer.f_mizTrmInfoOf(l27_existing2));
							l3_handled.value = ((Boolean)true);
							break;
						}
						case 82/*None*/: {
							break;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l24__tmp.getTypeName());
						}
					} else {
					}
					break;
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l15__tmp.getTypeName());
				}
			} else {
				if ((((l2_srt==Module_lexicon.g_ikTrmSchFunc)||(l2_srt==Module_lexicon.g_ikTrmPrivFunc))||(l2_srt==Module_lexicon.g_ikTrmSelector))) {
					final Struct l28_args = Module_equalizer.f_mizArgsOfE(at0);
					Module_equalizer.f_mizYTermList(aeq_0, l28_args);
					int l29_kind;
					if ((l2_srt==Module_lexicon.g_ikTrmSchFunc)) {
						l29_kind=Module_equalizer.g_expSchFunc;
					} else {
						if ((l2_srt==Module_lexicon.g_ikTrmPrivFunc)) {
							l29_kind=Module_equalizer.g_expPrivFunc;
						} else {
							l29_kind=Module_equalizer.g_expSelector;
						}
					}
					final Struct l30_gsymswitch11 = Module_equalizer.f_mizFindEqArgsTerm(aeq_0, l29_kind, Module_equalizer.f_mizFuncNrOfE(at0), l28_args);
					Struct l31__tmp = l30_gsymswitch11;
					switch (l31__tmp.getTypeId()) {
					case 90/*Some*/: {
						final Struct_Some l32__tmp = (Struct_Some)l31__tmp;
						final Object l33_existing = l32__tmp.f_value;
						final Struct l34_existing = ((Struct)(l32__tmp).f_value);
						l1_fi.value = ((Integer)Module_equalizer.f_mizTrmInfoOf(l34_existing));
						l3_handled.value = ((Boolean)true);
						break;
					}
					case 82/*None*/: {
						break;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l31__tmp.getTypeName());
					}
				} else {
					if ((l2_srt==Module_lexicon.g_ikTrmAggreg)) {
						final Struct_MizFuncTrm l35_ft2 = Module_equalizer.f_mizEqAsFunc(at0);
						Module_equalizer.f_mizYTermList(aeq_0, (l35_ft2).f_args);
						final Struct_MizConstr l36_ca = Module_correl.f_mizConstr(l0_env, Module_correl.g_coAggregate, (l35_ft2).f_funcNr);
						final int l37_base = (l36_ca).f_aggregBase;
						final Struct l38_gsymswitch10 = Module_equalizer.f_mizFuncIdxFind(aeq_0, Module_equalizer.g_expAggreg, (l35_ft2).f_funcNr);
						Struct l39__tmp = l38_gsymswitch10;
						switch (l39__tmp.getTypeId()) {
						case 90/*Some*/: {
							final Struct_Some l40__tmp = (Struct_Some)l39__tmp;
							final Object l41_v = l40__tmp.f_value;
							final Struct_Vector l42_v = ((Struct_Vector)(l40__tmp).f_value);
							final Reference<Integer> l43_i = ((Reference<Integer>)(new Reference(0)));
							final Func0<Boolean> l46_$44 = (Func0<Boolean>)() -> {
								return ((Boolean)(!((boolean)l3_handled.value)&&(((int)l43_i.value)<Module_vector.f_sizeVector(l42_v))));
							};
							final Struct_MizEqualizer l48_eq_0 = aeq_0;
							final Func0<Object> l47_$45 = (Func0<Object>)() -> {
								final Struct l48_cand = ((Struct)Module_vector.f_getVectorUnsafe(l42_v, ((int)l43_i.value)));
								if (Module_equalizer.f_mizEqTrmListsE(l48_eq_0, Module_correl.f_mizTrmListDrop((l35_ft2).f_args, l37_base), Module_correl.f_mizTrmListDrop(Module_equalizer.f_mizArgsOfE(l48_cand), l37_base))) {
									l1_fi.value = ((Integer)Module_equalizer.f_mizTrmInfoOf(l48_cand));
									l3_handled.value = ((Boolean)true);
								} else {
								}
								l43_i.value = ((Integer)(((int)l43_i.value)+1));
								return null;
							};
							Module_loop.f_while(l46_$44, l47_$45);
							break;
						}
						case 82/*None*/: {
							break;
						}
						default:
							throw new RuntimeException("Unexpected struct in switch: "+l39__tmp.getTypeName());
						}
					} else {
						if ((l2_srt==Module_lexicon.g_ikTrmFraenkel)) {
							final Struct_MizFraenkelTrm l49_kt = Module_equalizer.f_mizEqAsFraenkel(at0);
							final Struct_Vector l52_$51 = (l49_kt).f_lambdaArgs;
							final Struct_MizEqualizer l54_eq_0 = aeq_0;
							final Func1<Object,Struct_MizTyp> l53_$50 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp atyp) -> {
								((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
								return Module_equalizer.f_mizYType(l54_eq_0, atyp);
							};
							Module_vector.f_iterVector(l52_$51, ((Func1<Object,Object>)(Func1)l53_$50));
							((Field_scope)l49_kt).set_scope(Module_equalizer.f_mizYTerm(aeq_0, (l49_kt).f_scope));
							Module_equalizer.f_mizYFormula(aeq_0, (l49_kt).f_compr);
							((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-Module_vector.f_sizeVector((l49_kt).f_lambdaArgs)));
							if (Module_equalizer.f_mizHasBoundE(aeq_0, at0)) {
								l3_handled.value = ((Boolean)true);
							} else {
								Module_equalizer.f_mizChChangeBoundE(aeq_0, at0);
								final Struct l55_gsymswitch9 = Module_equalizer.f_mizSortedFind(aeq_0, (aeq_0).f_frOper, at0);
								Struct l56__tmp = l55_gsymswitch9;
								switch (l56__tmp.getTypeId()) {
								case 90/*Some*/: {
									final Struct_Some l57__tmp = (Struct_Some)l56__tmp;
									final Object l58_existing = l57__tmp.f_value;
									final Struct l59_existing = ((Struct)(l57__tmp).f_value);
									l1_fi.value = ((Integer)Module_equalizer.f_mizTrmInfoOf(l59_existing));
									l3_handled.value = ((Boolean)true);
									break;
								}
								case 82/*None*/: {
									break;
								}
								default:
									throw new RuntimeException("Unexpected struct in switch: "+l56__tmp.getTypeName());
								}
							}
						} else {
							if ((l2_srt==Module_lexicon.g_ikTrmChoice)) {
								final Struct_MizChoiceTrm l60_ct = Module_equalizer.f_mizEqAsChoice(at0);
								Module_equalizer.f_mizYType(aeq_0, (l60_ct).f_choiceTyp);
								final Struct l61_gsymswitch8 = Module_equalizer.f_mizSortedFind(aeq_0, (aeq_0).f_choiceTerm, at0);
								Struct l62__tmp = l61_gsymswitch8;
								switch (l62__tmp.getTypeId()) {
								case 90/*Some*/: {
									final Struct_Some l63__tmp = (Struct_Some)l62__tmp;
									final Object l64_existing = l63__tmp.f_value;
									final Struct l65_existing = ((Struct)(l63__tmp).f_value);
									l1_fi.value = ((Integer)Module_equalizer.f_mizTrmInfoOf(l65_existing));
									l3_handled.value = ((Boolean)true);
									break;
								}
								case 82/*None*/: {
									break;
								}
								default:
									throw new RuntimeException("Unexpected struct in switch: "+l62__tmp.getTypeName());
								}
							} else {
								if (((l2_srt==Module_lexicon.g_ikTrmInfConst)||(l2_srt==Module_lexicon.g_ikTrmConstant))) {
									l3_handled.value = ((Boolean)true);
								} else {
									Module_errhan.f_mizRunTimeError(2026);
									l3_handled.value = ((Boolean)true);
								}
							}
						}
					}
				}
			}
		}
		if (!((boolean)l3_handled.value)) {
			final int l66_clNr = Module_equalizer.f_mizClassOf(aeq_0, ((int)l1_fi.value));
			final Struct_MizTrmSRec l67_rec = Module_equalizer.f_mizTrmSAt(aeq_0, l66_clNr);
			if ((l2_srt==Module_lexicon.g_ikTrmNumeral)) {
				final Struct_MizVarTrm l68_vt2 = Module_equalizer.f_mizEqAsVar(at0);
				if (!(l67_rec).f_numDetermined) {
					((Field_numDetermined)l67_rec).set_numDetermined(true);
					((Field_numValue)l67_rec).set_numValue((new Struct_MizComplex(Module_math.f_i2d((l68_vt2).f_varNr), 0.0)));
				} else {
					if (!((((l67_rec).f_numValue).f_re==Module_math.f_i2d((l68_vt2).f_varNr))&&(((l67_rec).f_numValue).f_im==0.0))) {
						Module_equalizer.f_mizSetContr(aeq_0, 3);
					} else {
					}
				}
				Module_equalizer.f_mizSetTrmInfo(at0, ((int)l1_fi.value));
			} else {
				if ((l2_srt==Module_lexicon.g_ikTrmFunctor)) {
					final Struct_MizFuncTrm l69_ft3 = Module_equalizer.f_mizEqAsFunc(at0);
					final int l70_iNr = Module_equalizer.f_mizAdjustedFuncNrE(l0_env, at0);
					if ((Module_equalizer.f_mizRevReqPub((aeq_0).f_idn, l70_iNr)==Module_builtin.g_rqImaginaryUnit)) {
						((Field_numDetermined)l67_rec).set_numDetermined(true);
						((Field_numValue)l67_rec).set_numValue((new Struct_MizComplex(0.0, 1.0)));
					} else {
					}
					if ((Module_equalizer.f_mizRevReqPub((aeq_0).f_idn, l70_iNr)==Module_builtin.g_rqZeroNumber)) {
						((Field_numDetermined)l67_rec).set_numDetermined(true);
						((Field_numValue)l67_rec).set_numValue((new Struct_MizComplex(0.0, 0.0)));
					} else {
					}
					final Struct_Pair l71_pa2 = Module_correl.f_mizAdjustTrm(l0_env, l69_ft3);
					final Struct l72_nt = Module_correl.f_mizNewFuncTrm(((int)(l71_pa2).f_first), Module_correl.f_mizCopyTrmList(((Struct)(l71_pa2).f_second)));
					Module_equalizer.f_mizSetTrmInfo(l72_nt, ((int)l1_fi.value));
					Module_equalizer.f_mizFuncIdxAllocT(aeq_0, Module_equalizer.g_expFunctor, l72_nt);
					((Field_eqClass)l67_rec).set_eqClass((new Struct_MizTrmElem(l72_nt, (l67_rec).f_eqClass)));
					final Struct_MizConstr l73_c3 = Module_correl.f_mizConstr(l0_env, Module_correl.g_coFunctor, (l69_ft3).f_funcNr);
					if (Module_correl.f_mizHasProperty(l73_c3, Module_correl.g_syCommutativity)) {
						final Struct l74_lc = Module_correl.f_mizNewFuncTrm((l69_ft3).f_funcNr, Module_roundcl.f_mizSwapArguments((l69_ft3).f_args, (l73_c3).f_firstArg, (l73_c3).f_secondArg));
						final Struct_MizFuncTrm l75_cf3 = Module_equalizer.f_mizEqAsFunc(l74_lc);
						final Struct_Pair l76_pw3 = Module_correl.f_mizAdjustTrm(l0_env, l75_cf3);
						final Struct l77_lc1 = Module_correl.f_mizNewFuncTrm(((int)(l76_pw3).f_first), Module_correl.f_mizCopyTrmList(((Struct)(l76_pw3).f_second)));
						Module_equalizer.f_mizSetTrmInfo(l77_lc1, ((int)l1_fi.value));
						((Field_eqClass)l67_rec).set_eqClass((new Struct_MizTrmElem(l77_lc1, (l67_rec).f_eqClass)));
						Module_equalizer.f_mizFuncIdxAllocT(aeq_0, Module_equalizer.g_expFunctor, l77_lc1);
					} else {
					}
				} else {
					Module_equalizer.f_mizSetTrmInfo(at0, ((int)l1_fi.value));
					((Field_eqClass)l67_rec).set_eqClass((new Struct_MizTrmElem(at0, (l67_rec).f_eqClass)));
					if ((l2_srt==Module_lexicon.g_ikTrmSchFunc)) {
						Module_equalizer.f_mizFuncIdxAllocT(aeq_0, Module_equalizer.g_expSchFunc, at0);
					} else {
						if ((l2_srt==Module_lexicon.g_ikTrmPrivFunc)) {
							Module_equalizer.f_mizFuncIdxAllocT(aeq_0, Module_equalizer.g_expPrivFunc, at0);
						} else {
							if ((l2_srt==Module_lexicon.g_ikTrmSelector)) {
								Module_equalizer.f_mizFuncIdxAllocT(aeq_0, Module_equalizer.g_expSelector, at0);
							} else {
								if ((l2_srt==Module_lexicon.g_ikTrmAggreg)) {
									Module_equalizer.f_mizFuncIdxAllocT(aeq_0, Module_equalizer.g_expAggreg, at0);
								} else {
									if ((l2_srt==Module_lexicon.g_ikTrmFraenkel)) {
										Module_vector.f_pushVector((aeq_0).f_frOper, at0);
									} else {
										if ((l2_srt==Module_lexicon.g_ikTrmChoice)) {
											Module_vector.f_pushVector((aeq_0).f_choiceTerm, at0);
										} else {
										}
									}
								}
							}
						}
					}
				}
			}
		} else {
		}
		return ((int)l1_fi.value);
	}
}
