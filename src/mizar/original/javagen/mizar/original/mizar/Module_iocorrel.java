// HASH COLLISIONS: YES
// timestamp: 1785496853000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_iocorrel {
	public static final int f_mizConstructorKind(int akindChar) {
		if ((akindChar==77)) {
			return Module_correl.g_coMode;
		} else {
			if ((akindChar==76)) {
				return Module_correl.g_coStructMode;
			} else {
				if ((akindChar==86)) {
					return Module_correl.g_coAttribute;
				} else {
					if ((akindChar==82)) {
						return Module_correl.g_coPredicate;
					} else {
						if ((akindChar==75)) {
							return Module_correl.g_coFunctor;
						} else {
							if ((akindChar==85)) {
								return Module_correl.g_coSelector;
							} else {
								if (((akindChar==71)||(akindChar==74))) {
									return Module_correl.g_coAggregate;
								} else {
									Module_errhan.f_mizRunTimeError(2211);
									return 0;
								}
							}
						}
					}
				}
			}
		}
	}
	public static final Struct_Vector f_mizInArgColl(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlAssert((((ar).f_x).f_nElName).equals("ArgTypes"));
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct_Vector l0_v = Module_iocorrel.f_mizInTypeColl(ar);
		Module_errhan.f_mizAssert(Module_xml_parser.g_errElRedundant, (((ar).f_x).f_nState==Module_xml_parser.g_MizEEnd));
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		return l0_v;
	}
	public static final Struct_MizAttr f_mizInAttr(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlAssert((((ar).f_x).f_nElName).equals("Adjective"));
		final int l0_nr = Module_xml_parser.f_mizXmlIntAttr((ar).f_x, "nr");
		int l1_neg;
		if (Module_xml_parser.f_mizXmlHasAttr((ar).f_x, "value")) {
			if ((Module_xml_parser.f_mizXmlAttr((ar).f_x, "value")).equals("true")) {
				l1_neg=1;
			} else {
				l1_neg=0;
			}
		} else {
			l1_neg=1;
		}
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct_MizAttr l2_res = (new Struct_MizAttr(l1_neg, l0_nr, Module_iocorrel.f_mizInTermList(ar), false, 0));
		Module_errhan.f_mizAssert(Module_xml_parser.g_errElRedundant, (((ar).f_x).f_nState==Module_xml_parser.g_MizEEnd));
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		return l2_res;
	}
	public static final Struct_MizAttrColl f_mizInAttrColl(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlAssert((((ar).f_x).f_nElName).equals("Cluster"));
		final Struct_MizAttrColl l0_coll = Module_correl.f_mizNewEmptyCluster();
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct_MizVrfReader l4_r = ar;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return (((l4_r).f_x).f_nState!=Module_xml_parser.g_MizEEnd);
		};
		final Struct_MizVrfReader l6_r = ar;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			final Struct_MizAttr l6_a = Module_iocorrel.f_mizInAttr(l6_r);
			if ((l6_r).f_mml) {
				return Module_vector.f_pushVector((l0_coll).f_items, l6_a);
			} else {
				return Module_correl.f_mizAttrCollInsert((l6_r).f_env, l0_coll, l6_a);
			}
		};
		Module_loop.f_while(l3_$1, l5_$2);
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		return l0_coll;
	}
	public static final Struct f_mizInChoiceTerm(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct l0_res = Module_correl.f_mizNewChoiceTrm(Module_iocorrel.f_mizInType(ar));
		Module_errhan.f_mizAssert(Module_xml_parser.g_errElRedundant, (((ar).f_x).f_nState==Module_xml_parser.g_MizEEnd));
		return l0_res;
	}
	public static final Struct f_mizInConjFormula(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct_Vector l0_conjuncts = Module_vector.f_makeVector(4);
		final Struct_MizVrfReader l4_r = ar;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return (((l4_r).f_x).f_nState!=Module_xml_parser.g_MizEEnd);
		};
		final Struct_MizVrfReader l6_r = ar;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			final Struct l6_f = Module_iocorrel.f_mizInFormula(l6_r);
			final int l7_srt = Module_correl.f_mizFrmSort(l6_f);
			if ((l7_srt==Module_lexicon.g_ikFrmVerum)) {
				return null;
			} else {
				if ((l7_srt==Module_lexicon.g_ikFrmConj)) {
					Struct l8__tmp = l6_f;
					switch (l8__tmp.getTypeId()) {
					case 26/*MizConjFrm*/: {
						final Struct_MizConjFrm l9__tmp = (Struct_MizConjFrm)l8__tmp;
						final Struct_Vector l10_inner = l9__tmp.f_conjuncts;
						final Struct_Vector l11_inner = (l9__tmp).f_conjuncts;
						final int l12___ = (l9__tmp).f_pattNr;
						final Func1<Object,Struct> l14_$13 = (Func1<Object, Struct>)(Struct ag) -> {
							return Module_vector.f_pushVector(l0_conjuncts, ag);
						};
						return Module_vector.f_iterVector(l11_inner, ((Func1<Object,Object>)(Func1)l14_$13));
					}
					default: {
						return null;
					}
					}
				} else {
					return Module_vector.f_pushVector(l0_conjuncts, l6_f);
				}
			}
		};
		Module_loop.f_while(l3_$1, l5_$2);
		return Module_correl.f_mizNewConjFrm(l0_conjuncts);
	}
	public static final Struct_MizConstr f_mizInConstructor1(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlAssert((((ar).f_x).f_nElName).equals("Constructor"));
		final String l0_kindS = Module_xml_parser.f_mizXmlAttr((ar).f_x, "kind");
		final int l1_kind = Module_iocorrel.f_mizConstructorKind(Native.getCharCodeAt(l0_kindS, 0));
		final int l2_nr = Module_xml_parser.f_mizXmlIntAttr((ar).f_x, "nr");
		final String l3_aid = Module_xml_parser.f_mizXmlAttr((ar).f_x, "aid");
		final Struct_MizConstr l4_c = Module_correl.f_mizMakeConstr(l1_kind, l3_aid, l2_nr);
		((Field_whichConstrNr)l4_c).set_whichConstrNr(Module_xml_parser.f_mizXmlIntAttrDef((ar).f_x, "redefnr", 0));
		((Field_superfluous)l4_c).set_superfluous(Module_xml_parser.f_mizXmlIntAttrDef((ar).f_x, "superfluous", 0));
		final int l5_structModeAggrNr = Module_xml_parser.f_mizXmlIntAttrDef((ar).f_x, "structmodeaggrnr", 0);
		final int l6_aggregBase = Module_xml_parser.f_mizXmlIntAttrDef((ar).f_x, "aggregbase", 0);
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		if (((((ar).f_x).f_nElName).equals("Properties")&&(((ar).f_x).f_nState==Module_xml_parser.g_MizEStart))) {
			((Field_firstArg)l4_c).set_firstArg(Module_xml_parser.f_mizXmlIntAttr((ar).f_x, "propertyarg1"));
			((Field_secondArg)l4_c).set_secondArg(Module_xml_parser.f_mizXmlIntAttr((ar).f_x, "propertyarg2"));
			Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
			final Struct_MizVrfReader l10_r = ar;
			final Func0<Boolean> l9_$7 = (Func0<Boolean>)() -> {
				return (((l10_r).f_x).f_nState!=Module_xml_parser.g_MizEEnd);
			};
			final Struct_MizVrfReader l12_r = ar;
			final Func0<Object> l11_$8 = (Func0<Object>)() -> {
				((Field_properties)l4_c).set_properties(Native.bitOr((l4_c).f_properties, Native.bitShl(1, Module_iocorrel.f_mizXmlElem2Prop(((l12_r).f_x).f_nElName))));
				Module_xml_parser.f_mizXmlAcceptEndState((l12_r).f_x);
				return Module_xml_parser.f_mizXmlNextElementState((l12_r).f_x);
			};
			Module_loop.f_while(l9_$7, l11_$8);
			Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		} else {
		}
		final Struct_Vector l13_primaries = Module_iocorrel.f_mizInArgColl(ar);
		final Func1<Object,Struct_MizTyp> l15_$14 = (Func1<Object, Struct_MizTyp>)(Struct_MizTyp atyp) -> {
			return Module_vector.f_pushVector((l4_c).f_primaries, atyp);
		};
		Module_vector.f_iterVector(l13_primaries, ((Func1<Object,Object>)(Func1)l15_$14));
		if ((((((l1_kind==Module_correl.g_coFunctor)||(l1_kind==Module_correl.g_coMode))||(l1_kind==Module_correl.g_coAttribute))||(l1_kind==Module_correl.g_coAggregate))||(l1_kind==Module_correl.g_coSelector))) {
			((Field_typ)l4_c).set_typ((new Struct_Some(Module_iocorrel.f_mizInType(ar))));
		} else {
		}
		if ((l1_kind==Module_correl.g_coStructMode)) {
			((Field_structModeAggrNr)l4_c).set_structModeAggrNr(l5_structModeAggrNr);
			final Struct_MizVrfReader l19_r = ar;
			final Func0<Boolean> l18_$16 = (Func0<Boolean>)() -> {
				return ((Boolean)((((l19_r).f_x).f_nElName).equals("Typ")&&(((l19_r).f_x).f_nState==Module_xml_parser.g_MizEStart)));
			};
			final Struct_MizVrfReader l21_r = ar;
			final Func0<Object> l20_$17 = (Func0<Object>)() -> {
				return Module_vector.f_pushVector((l4_c).f_prefixes, Module_iocorrel.f_mizInType(l21_r));
			};
			Module_loop.f_while(l18_$16, l20_$17);
			Module_xml_parser.f_mizXmlAssert((((ar).f_x).f_nElName).equals("Fields"));
			Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
			final Struct_MizVrfReader l25_r = ar;
			final Func0<Boolean> l24_$22 = (Func0<Boolean>)() -> {
				return (((l25_r).f_x).f_nState!=Module_xml_parser.g_MizEEnd);
			};
			final Struct_MizVrfReader l27_r = ar;
			final Func0<Object> l26_$23 = (Func0<Object>)() -> {
				Module_mobjects.f_mizNatInsertElem((l4_c).f_fields, Module_xml_parser.f_mizXmlIntAttr((l27_r).f_x, "nr"));
				Module_xml_parser.f_mizXmlAcceptEndState((l27_r).f_x);
				return Module_xml_parser.f_mizXmlNextElementState((l27_r).f_x);
			};
			Module_loop.f_while(l24_$22, l26_$23);
			Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		} else {
		}
		if ((l1_kind==Module_correl.g_coAggregate)) {
			((Field_aggregBase)l4_c).set_aggregBase(l6_aggregBase);
			Module_xml_parser.f_mizXmlAssert((((ar).f_x).f_nElName).equals("Fields"));
			Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
			final Struct_MizVrfReader l31_r = ar;
			final Func0<Boolean> l30_$28 = (Func0<Boolean>)() -> {
				return (((l31_r).f_x).f_nState!=Module_xml_parser.g_MizEEnd);
			};
			final Struct_MizVrfReader l33_r = ar;
			final Func0<Object> l32_$29 = (Func0<Object>)() -> {
				Module_mobjects.f_mizNatInsertElem((l4_c).f_fields, Module_xml_parser.f_mizXmlIntAttr((l33_r).f_x, "nr"));
				Module_xml_parser.f_mizXmlAcceptEndState((l33_r).f_x);
				return Module_xml_parser.f_mizXmlNextElementState((l33_r).f_x);
			};
			Module_loop.f_while(l30_$28, l32_$29);
			Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		} else {
		}
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		return l4_c;
	}
	public static final Struct f_mizInFlexFormula(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct l0_leftOrig = Module_iocorrel.f_mizInFormula(ar);
		final Struct l1_rightOrig = Module_iocorrel.f_mizInFormula(ar);
		final Struct l2_leftTrm = Module_iocorrel.f_mizInTerm(ar);
		final Struct l3_rightTrm = Module_iocorrel.f_mizInTerm(ar);
		final Struct l4_expansion = Module_iocorrel.f_mizInFormula(ar);
		return (new Struct_MizFlexFrm(Module_lexicon.g_ikFrmFlexConj, l0_leftOrig, l1_rightOrig, l2_leftTrm, l3_rightTrm, l4_expansion, 0));
	}
	public static final Struct f_mizInFormula(Struct_MizVrfReader ar) {
		final String l0_name = ((ar).f_x).f_nElName;
		Struct l1_res;
		if ((l0_name).equals("Not")) {
			l1_res=Module_iocorrel.f_mizInNegFormula(ar);
		} else {
			if ((l0_name).equals("And")) {
				l1_res=Module_iocorrel.f_mizInConjFormula(ar);
			} else {
				if ((l0_name).equals("For")) {
					l1_res=Module_iocorrel.f_mizInUnivFormula(ar);
				} else {
					if ((l0_name).equals("Pred")) {
						l1_res=Module_iocorrel.f_mizInPredFormula(ar);
					} else {
						if ((l0_name).equals("PrivPred")) {
							l1_res=Module_iocorrel.f_mizInLocPredFormula(ar);
						} else {
							if ((l0_name).equals("Is")) {
								l1_res=Module_iocorrel.f_mizInQualFormula(ar);
							} else {
								if ((l0_name).equals("FlexFrm")) {
									l1_res=Module_iocorrel.f_mizInFlexFormula(ar);
								} else {
									if (((l0_name).equals("Verum")||(l0_name).equals("ErrorFrm"))) {
										l1_res=Module_iocorrel.f_mizInUniqFormula(ar);
									} else {
										Module_errhan.f_mizRunTimeError(2037);
										l1_res=Module_correl.f_mizNewIncorFrm();
									}
								}
							}
						}
					}
				}
			}
		}
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		return l1_res;
	}
	public static final Struct f_mizInFraenkelTerm(Struct_MizVrfReader ar) {
		final Struct_MizCorrelEnv l0_env = (ar).f_env;
		final int l1_lBoundVarNbr = (l0_env).f_boundVarNbr;
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct_Vector l2_lambdaArgs = Module_vector.f_makeVector(4);
		final Struct_MizVrfReader l6_r = ar;
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return ((Boolean)((((l6_r).f_x).f_nState!=Module_xml_parser.g_MizEEnd)&&(((l6_r).f_x).f_nElName).equals("Typ")));
		};
		final Struct_MizVrfReader l8_r = ar;
		final Func0<Object> l7_$4 = (Func0<Object>)() -> {
			((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
			final Struct_MizTyp l8_typ = Module_iocorrel.f_mizInType(l8_r);
			Module_vector.f_pushVector(l2_lambdaArgs, l8_typ);
			return Module_correl.f_mizSetBoundVar(l0_env, (l0_env).f_boundVarNbr, l8_typ);
		};
		Module_loop.f_while(l5_$3, l7_$4);
		final Struct l9_scope = Module_iocorrel.f_mizInTerm(ar);
		final Struct l10_compr = Module_iocorrel.f_mizInFormula(ar);
		final Struct_MizIntSeq l11_idents = Module_mobjects.f_mizInitIntSeq(Module_vector.f_sizeVector(l2_lambdaArgs));
		final int l14_$13 = Module_vector.f_sizeVector(l2_lambdaArgs);
		final Func1<Object,Integer> l15_$12 = (Func1<Object, Integer>)(Integer al16_0) -> {
			Module_mobjects.f_mizSeqInsert(l11_idents, 0);
			return null;
		};
		Module_runtime.f_fori(1, l14_$13, l15_$12);
		final Struct_MizFraenkelTrm l16_res = (new Struct_MizFraenkelTrm(l2_lambdaArgs, l9_scope, l10_compr, l11_idents, 0, 0));
		((Field_boundVarNbr)l0_env).set_boundVarNbr(l1_lBoundVarNbr);
		Module_errhan.f_mizAssert(Module_xml_parser.g_errElRedundant, (((ar).f_x).f_nState==Module_xml_parser.g_MizEEnd));
		return l16_res;
	}
	public static final Struct f_mizInFuncTerm(Struct_MizVrfReader ar) {
		final Struct_Pair l0_lx = Module_iocorrel.f_mizInLexem(ar);
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct l1_res = Module_correl.f_mizNewLocFuncTrm(((int)(l0_lx).f_first), ((int)(l0_lx).f_second), Module_iocorrel.f_mizInTermList(ar));
		Module_errhan.f_mizAssert(Module_xml_parser.g_errElRedundant, (((ar).f_x).f_nState==Module_xml_parser.g_MizEEnd));
		return l1_res;
	}
	public static final Struct_Pair f_mizInLexem(Struct_MizVrfReader ar) {
		final String l0_kindS = Module_xml_parser.f_mizXmlAttr((ar).f_x, "kind");
		final int l1_nr = Module_xml_parser.f_mizXmlIntAttrDef((ar).f_x, "nr", 0);
		int l2_kind;
		if ((Native.strlen(l0_kindS)>=1)) {
			l2_kind=Native.getCharCodeAt(l0_kindS, 0);
		} else {
			l2_kind=Module_lexicon.g_ikError;
		}
		return (new Struct_Pair(l2_kind, l1_nr));
	}
	public static final Struct f_mizInLocPredFormula(Struct_MizVrfReader ar) {
		final int l0_nr = Module_xml_parser.f_mizXmlIntAttr((ar).f_x, "nr");
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct l1_args = Module_iocorrel.f_mizInTermList(ar);
		final Struct l2_f = Module_iocorrel.f_mizInFormula(ar);
		return Module_correl.f_mizNewLocPredFrm(l0_nr, l1_args, l2_f);
	}
	public static final Struct f_mizInNegFormula(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		return Module_correl.f_mizNewNeg(Module_iocorrel.f_mizInFormula(ar));
	}
	public static final Struct f_mizInPredFormula(Struct_MizVrfReader ar) {
		final Struct_Pair l0_lx = Module_iocorrel.f_mizInLexem(ar);
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		return Module_correl.f_mizNewPredFrm(((int)(l0_lx).f_first), ((int)(l0_lx).f_second), Module_iocorrel.f_mizInTermList(ar), 0);
	}
	public static final Struct f_mizInPrivFuncTerm(Struct_MizVrfReader ar) {
		final int l0_funcNr = Module_xml_parser.f_mizXmlIntAttr((ar).f_x, "nr");
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct l1_expn = Module_iocorrel.f_mizInTerm(ar);
		final Struct l2_args = Module_iocorrel.f_mizInTermList(ar);
		final Struct l3_res = Module_correl.f_mizNewPrivFuncTrm(l0_funcNr, l2_args, l1_expn);
		Module_errhan.f_mizAssert(Module_xml_parser.g_errElRedundant, (((ar).f_x).f_nState==Module_xml_parser.g_MizEEnd));
		return l3_res;
	}
	public static final Struct f_mizInQualFormula(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		final Struct l0_trm = Module_iocorrel.f_mizInTerm(ar);
		return Module_correl.f_mizNewQualFrm(l0_trm, Module_iocorrel.f_mizInType(ar));
	}
	public static final Struct f_mizInTerm(Struct_MizVrfReader ar) {
		final String l0_name = ((ar).f_x).f_nElName;
		Struct l1_res;
		if (Module_iocorrel.f_mizIsVarEl(l0_name)) {
			l1_res=Module_iocorrel.f_mizInVarTerm(ar);
		} else {
			if ((l0_name).equals("Func")) {
				l1_res=Module_iocorrel.f_mizInFuncTerm(ar);
			} else {
				if ((l0_name).equals("PrivFunc")) {
					l1_res=Module_iocorrel.f_mizInPrivFuncTerm(ar);
				} else {
					if ((l0_name).equals("Fraenkel")) {
						l1_res=Module_iocorrel.f_mizInFraenkelTerm(ar);
					} else {
						if ((l0_name).equals("Choice")) {
							l1_res=Module_iocorrel.f_mizInChoiceTerm(ar);
						} else {
							if (((l0_name).equals("It")||(l0_name).equals("ErrorTrm"))) {
								l1_res=Module_iocorrel.f_mizInUniqTerm(ar);
							} else {
								Module_errhan.f_mizRunTimeError(2038);
								l1_res=Module_correl.f_mizNewIncorTrm();
							}
						}
					}
				}
			}
		}
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		return l1_res;
	}
	public static final Struct f_mizInTermList(Struct_MizVrfReader ar) {
		final Reference<Struct> l0_head = ((Reference<Struct>)(new Reference(SingletonStructs.str_MizTrmNil)));
		final Reference<Struct> l1_tailEl = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
		final Struct_MizVrfReader l5_r = ar;
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((Boolean)((((l5_r).f_x).f_nState!=Module_xml_parser.g_MizEEnd)&&Module_iocorrel.f_mizIsTermEl(((l5_r).f_x).f_nElName)));
		};
		final Struct_MizVrfReader l7_r = ar;
		final Func0<Object> l6_$3 = (Func0<Object>)() -> {
			final Struct_MizTrmElem l7_e = (new Struct_MizTrmElem(Module_iocorrel.f_mizInTerm(l7_r), ((Struct)SingletonStructs.str_MizTrmNil)));
			final Struct l8_gsymswitch0 = l1_tailEl.value;
			Struct l9__tmp = l8_gsymswitch0;
			switch (l9__tmp.getTypeId()) {
			case 82/*None*/: {
				((Reference<Struct_MizTrmElem>)(Reference)l0_head).value = l7_e;
				break;
			}
			case 90/*Some*/: {
				final Struct_Some l10__tmp = (Struct_Some)l9__tmp;
				final Object l11_prev = l10__tmp.f_value;
				final Struct_MizTrmElem l12_prev = ((Struct_MizTrmElem)(l10__tmp).f_value);
				((Field_next)l12_prev).set_next(l7_e);
				break;
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l9__tmp.getTypeName());
			}
			((Reference<Struct_Some>)(Reference)l1_tailEl).value = (new Struct_Some(l7_e));
			return null;
		};
		Module_loop.f_while(l4_$2, l6_$3);
		return l0_head.value;
	}
	public static final Struct_MizTyp f_mizInType(Struct_MizVrfReader ar) {
		Module_xml_parser.f_mizXmlAssert((((ar).f_x).f_nElName).equals("Typ"));
		final Struct_Pair l0_lx = Module_iocorrel.f_mizInLexem(ar);
		final int l1_kind0 = ((int)(l0_lx).f_first);
		int l2_kind;
		if ((l1_kind0==76)) {
			l2_kind=Module_lexicon.g_ikTypStruct;
		} else {
			l2_kind=l1_kind0;
		}
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		Struct_MizTyp l3_res;
		if (((l2_kind==Module_lexicon.g_ikTypMode)||(l2_kind==Module_lexicon.g_ikTypStruct))) {
			if ((ar).f_mml) {
				final Struct_MizAttrColl l4_cl = Module_iocorrel.f_mizInAttrColl(ar);
				final Struct l5_args = Module_iocorrel.f_mizInTermList(ar);
				l3_res=(new Struct_MizTyp(l2_kind, l4_cl, Module_correl.f_mizCopyCluster(l4_cl), ((int)(l0_lx).f_second), l5_args, 0));
			} else {
				final Struct_MizAttrColl l6_lower = Module_iocorrel.f_mizInAttrColl(ar);
				final Struct_MizAttrColl l7_upper = Module_iocorrel.f_mizInAttrColl(ar);
				final Struct l8_args = Module_iocorrel.f_mizInTermList(ar);
				l3_res=(new Struct_MizTyp(l2_kind, l6_lower, l7_upper, ((int)(l0_lx).f_second), l8_args, 0));
			}
		} else {
			if ((l2_kind==101)) {
				l3_res=Module_correl.f_mizNewIncorTyp();
			} else {
				Module_errhan.f_mizRunTimeError(2036);
				l3_res=Module_correl.f_mizNewIncorTyp();
			}
		}
		Module_errhan.f_mizAssert(Module_xml_parser.g_errElRedundant, (((ar).f_x).f_nState==Module_xml_parser.g_MizEEnd));
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		return l3_res;
	}
	public static final Struct_Vector f_mizInTypeColl(Struct_MizVrfReader ar) {
		final Struct_Vector l0_v = Module_vector.f_makeVector(4);
		final Struct_MizVrfReader l4_r = ar;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return ((Boolean)((((l4_r).f_x).f_nState!=Module_xml_parser.g_MizEEnd)&&(((l4_r).f_x).f_nElName).equals("Typ")));
		};
		final Struct_MizVrfReader l6_r = ar;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			return Module_vector.f_pushVector(l0_v, Module_iocorrel.f_mizInType(l6_r));
		};
		Module_loop.f_while(l3_$1, l5_$2);
		return l0_v;
	}
	public static final Struct f_mizInUniqFormula(Struct_MizVrfReader ar) {
		Struct l0_res;
		if ((((ar).f_x).f_nElName).equals("Verum")) {
			l0_res=Module_correl.f_mizNewVerum();
		} else {
			l0_res=Module_correl.f_mizNewIncorFrm();
		}
		Module_xml_parser.f_mizXmlAcceptEndState((ar).f_x);
		return l0_res;
	}
	public static final Struct f_mizInUniqTerm(Struct_MizVrfReader ar) {
		Struct l0_res;
		if ((((ar).f_x).f_nElName).equals("It")) {
			l0_res=Module_correl.f_mizNewItTrm();
		} else {
			l0_res=Module_correl.f_mizNewIncorTrm();
		}
		Module_xml_parser.f_mizXmlAcceptEndState((ar).f_x);
		return l0_res;
	}
	public static final Struct f_mizInUnivFormula(Struct_MizVrfReader ar) {
		final Struct_MizCorrelEnv l0_env = (ar).f_env;
		Module_xml_parser.f_mizXmlNextElementState((ar).f_x);
		((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr+1));
		final Struct_MizTyp l1_typ = Module_iocorrel.f_mizInType(ar);
		Module_correl.f_mizSetBoundVar(l0_env, (l0_env).f_boundVarNbr, l1_typ);
		final Struct l2_res = Module_correl.f_mizNewUniv(l1_typ, Module_iocorrel.f_mizInFormula(ar));
		((Field_boundVarNbr)l0_env).set_boundVarNbr(((l0_env).f_boundVarNbr-1));
		return l2_res;
	}
	public static final Struct f_mizInVarTerm(Struct_MizVrfReader ar) {
		final Struct l0_res = Module_correl.f_mizNewVarTrm(Module_iocorrel.f_mizXVarEl2Kind(((ar).f_x).f_nElName), Module_xml_parser.f_mizXmlIntAttr((ar).f_x, "nr"));
		Module_xml_parser.f_mizXmlAcceptEndState((ar).f_x);
		return l0_res;
	}
	public static final boolean f_mizIsFrmEl(String aname) {
		return (((((((((aname).equals("Not")||(aname).equals("And"))||(aname).equals("For"))||(aname).equals("Pred"))||(aname).equals("PrivPred"))||(aname).equals("Is"))||(aname).equals("FlexFrm"))||(aname).equals("Verum"))||(aname).equals("ErrorFrm"));
	}
	public static final boolean f_mizIsTermEl(String aname) {
		return ((((((Module_iocorrel.f_mizIsVarEl(aname)||(aname).equals("Func"))||(aname).equals("PrivFunc"))||(aname).equals("Fraenkel"))||(aname).equals("Choice"))||(aname).equals("It"))||(aname).equals("ErrorTrm"));
	}
	public static final boolean f_mizIsVarEl(String aname) {
		return (((((((aname).equals("LocusVar")||(aname).equals("Var"))||(aname).equals("Const"))||(aname).equals("InfConst"))||(aname).equals("FreeVar"))||(aname).equals("LambdaVar"))||(aname).equals("Num"));
	}
	public static final Object f_mizLoadConstructors(Struct_MizVrfReader ar, String acontent) {
		final Struct_MizXmlParser l0_x = (ar).f_x;
		final Struct_MizVrfReader l1_r2 = (new Struct_MizVrfReader(Module_xml_parser.f_mizXmlInitParsing(acontent), (ar).f_env, true));
		Module_xml_parser.f_mizXmlNextElementState((l1_r2).f_x);
		Module_xml_parser.f_mizXmlAssert((((l1_r2).f_x).f_nElName).equals("Constructors"));
		Module_xml_parser.f_mizXmlNextElementState((l1_r2).f_x);
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((Boolean)((((l1_r2).f_x).f_nState==Module_xml_parser.g_MizEStart)&&(!(((l1_r2).f_x).f_nElName).equals("Constructors"))));
		};
		final Func0<Object> l5_$3 = (Func0<Object>)() -> {
			if ((((((l1_r2).f_x).f_nElName).equals("Signature")||(((l1_r2).f_x).f_nElName).equals("ArticleID"))||(((l1_r2).f_x).f_nElName).equals("Vocabularies"))) {
				return Module_xml_parser.f_mizXmlSkipElement((l1_r2).f_x);
			} else {
				final int l6_relnr = Module_xml_parser.f_mizXmlIntAttrDef((l1_r2).f_x, "relnr", (-1));
				final Struct_MizConstr l7_c = Module_iocorrel.f_mizInConstructor1(l1_r2);
				final int l8_slot = Module_correl.f_mizPushConstr((l1_r2).f_env, l7_c);
				if (((l6_relnr>=0)&&(l6_relnr!=l8_slot))) {
					return Module_errhan.f_mizRunTimeError(2803);
				} else {
					return null;
				}
			}
		};
		return Module_loop.f_while(l4_$2, l5_$3);
	}
	public static final Struct_MizVrfReader f_mizMakeVrfReader(Struct_MizXmlParser ax, Struct_MizCorrelEnv aenv) {
		return (new Struct_MizVrfReader(ax, aenv, false));
	}
	public static final int f_mizXVarEl2Kind(String aname) {
		if ((aname).equals("LocusVar")) {
			return Module_lexicon.g_ikTrmLocus;
		} else {
			if ((aname).equals("Var")) {
				return Module_lexicon.g_ikTrmBound;
			} else {
				if ((aname).equals("Const")) {
					return Module_lexicon.g_ikTrmConstant;
				} else {
					if ((aname).equals("InfConst")) {
						return Module_lexicon.g_ikTrmInfConst;
					} else {
						if ((aname).equals("FreeVar")) {
							return Module_lexicon.g_ikTrmFreeVar;
						} else {
							if ((aname).equals("LambdaVar")) {
								return Module_lexicon.g_ikTrmLambdaVar;
							} else {
								if ((aname).equals("Num")) {
									return Module_lexicon.g_ikTrmNumeral;
								} else {
									Module_errhan.f_mizRunTimeError(2038);
									return Module_lexicon.g_ikError;
								}
							}
						}
					}
				}
			}
		}
	}
	public static final int f_mizXmlElem2Prop(String aname) {
		if ((aname).equals("Symmetry")) {
			return Module_correl.g_sySymmetry;
		} else {
			if ((aname).equals("Reflexivity")) {
				return Module_correl.g_syReflexivity;
			} else {
				if ((aname).equals("Irreflexivity")) {
					return Module_correl.g_syIrreflexivity;
				} else {
					if ((aname).equals("Associativity")) {
						return Module_correl.g_syAssociativity;
					} else {
						if ((aname).equals("Transitivity")) {
							return Module_correl.g_syTransitivity;
						} else {
							if ((aname).equals("Commutativity")) {
								return Module_correl.g_syCommutativity;
							} else {
								if ((aname).equals("Connectedness")) {
									return Module_correl.g_syConnectedness;
								} else {
									if ((aname).equals("Asymmetry")) {
										return Module_correl.g_syAsymmetry;
									} else {
										if ((aname).equals("Idempotence")) {
											return Module_correl.g_syIdempotence;
										} else {
											if ((aname).equals("Involutiveness")) {
												return Module_correl.g_syInvolutiveness;
											} else {
												if ((aname).equals("Projectivity")) {
													return Module_correl.g_syProjectivity;
												} else {
													if ((aname).equals("Abstractness")) {
														return Module_correl.g_syAbstractness;
													} else {
														if ((aname).equals("Sethood")) {
															return Module_correl.g_sySethood;
														} else {
															Module_errhan.f_mizAssert(Module_xml_parser.g_errWrongXMLElement, false);
															return 0;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
