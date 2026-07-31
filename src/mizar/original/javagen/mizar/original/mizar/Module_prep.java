// HASH COLLISIONS: YES
// timestamp: 1785507806000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_prep {
	public static int g_kindD;
	public static int g_kindT;
	public static void init() {
		g_kindD=2;
		g_kindT=1;
	}
	public static final Struct_MizPrepStats f_mizCheckArticle(String ammlDir, String aarticle) {
		final Struct_MizCorrelEnv l0_env = Module_correl.f_mizMakeCorrelEnv();
		final Struct_MizClusters l1_cl = Module_roundcl.f_mizMakeClusters();
		Module_roundcl.f_mizWireRoundcl(l0_env, l1_cl);
		final Struct_MizIdentify l2_idn = Module_identify.f_mizMakeIdentify(l0_env);
		final Struct_MizChecker l3_chk = Module_checker.f_mizMakeChecker(l0_env, l2_idn, l1_cl);
		Module_unifier.f_mizWireUnifier(l3_chk);
		final Struct_MizVrfReader l4_r0 = Module_iocorrel.f_mizMakeVrfReader(Module_xml_parser.f_mizXmlInitParsing(""), l0_env);
		Module_iocorrel.f_mizLoadConstructors(l4_r0, Native.getFileContent((((ammlDir+"/")+aarticle)+".atr")));
		Module_prep.f_mizLoadRequirements(l0_env, (((ammlDir+"/")+aarticle)+".ere"));
		((Field_itTyp)l0_env).set_itTyp(Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqAny), Module_correl.g_mizNil));
		((Field_nonZeroTyp)l0_env).set_nonZeroTyp(Module_correl.f_mizNewStandardTyp(Module_lexicon.g_ikTypMode, Module_correl.f_mizNewEmptyCluster(), Module_correl.f_mizNewEmptyCluster(), Module_builtin.f_mizBuiltIn((l0_env).f_builtIn, Module_builtin.g_rqAny), Module_correl.g_mizNil));
		Module_identify.f_mizInitRevReq(l2_idn);
		final Struct_MizPrepStats l5_stats = (new Struct_MizPrepStats(0, 0, 0, 0, 0, Module_vector.f_makeVector(8)));
		final Struct_MizXmlParser l6_x = Module_xml_parser.f_mizXmlInitParsingFile((((ammlDir+"/")+aarticle)+".xml"));
		final Struct_MizVrfReader l7_r = Module_iocorrel.f_mizMakeVrfReader(l6_x, l0_env);
		final Struct_MizDefinientia l8_defs = Module_definientia.f_mizMakeDefinientia();
		final Struct_MizPrep l9_p = (new Struct_MizPrep(l3_chk, l7_r, Module_vector.f_makeVector(64), Module_vector.f_makeVector(64), ((Struct)SingletonStructs.str_None), ((Struct)SingletonStructs.str_None), Module_tree.f_makeTree(), l8_defs, 2, 0, 0, l5_stats));
		Module_prep.f_mizLoadTheorems(l9_p, Native.getFileContent((((ammlDir+"/")+aarticle)+".eth")));
		Module_prep.f_mizLoadClusters(l0_env, l1_cl, Native.getFileContent((((ammlDir+"/")+aarticle)+".ecl")));
		Module_prep.f_mizLoadReductions(l0_env, l2_idn, Native.getFileContent((((ammlDir+"/")+aarticle)+".erd")));
		Module_definientia.f_mizLoadDefinientia(l0_env, l8_defs, ammlDir, aarticle);
		final Func1<Object[],Struct> l11_$10 = (Func1<Object[], Struct>)(Struct af) -> {
			return Module_definientia.f_mizAtomicExpansions(l0_env, l8_defs, af);
		};
		((Field_atomicExpansions)l3_chk).set_atomicExpansions(l11_$10);
		final Func3<Object[],Struct_MizIdentify, Integer, Struct> l13_$12 = (Func3<Object[], Struct_MizIdentify, Integer, Struct>)(Struct_MizIdentify aidn2, Integer afuncNr, Struct aargs) -> {
			return Module_definientia.f_mizEqualsExpandHook(l8_defs, aidn2, ((int)afuncNr), aargs);
		};
		((Field_equalsExpand)l2_idn).set_equalsExpand(l13_$12);
		Module_xml_parser.f_mizXmlNextElementState(l6_x);
		Module_xml_parser.f_mizXmlAssert(((l6_x).f_nElName).equals("Article"));
		Module_xml_parser.f_mizXmlNextElementState(l6_x);
		Module_prep.f_mizWalkItems(l9_p);
		return l5_stats;
	}
	public static final Struct f_mizGetLabeled(Struct_MizPrep ap, int anr) {
		final Struct l0_gsymswitch0 = ((Struct)Module_vector.f_getVectorDef((ap).f_labeled, anr, SingletonStructs.str_None));
		Struct l1__tmp = l0_gsymswitch0;
		switch (l1__tmp.getTypeId()) {
		case 83/*Some*/: {
			final Struct_Some l2__tmp = (Struct_Some)l1__tmp;
			final Object l3_f = l2__tmp.f_value;
			final Struct l4_f = ((Struct)(l2__tmp).f_value);
			return (new Struct_Some(l4_f));
		}
		case 75/*None*/: {
			return ((Struct)Module_vector.f_getVectorDef((ap).f_permLabeled, anr, SingletonStructs.str_None));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l1__tmp.getTypeName());
		}
	}
	public static final Object f_mizJustify(Struct_MizPrep ap, Struct aclaim, int aposLine) {
		final Struct_MizCorrelEnv l0_env = ((ap).f_chk).f_env;
		final Struct_MizXmlParser l1_x = ((ap).f_r).f_x;
		final String l2_name = (l1_x).f_nElName;
		if (((l1_x).f_nState!=Module_xml_parser.g_MizEStart)) {
			return null;
		} else {
			if ((l2_name).equals("By")) {
				final boolean l3_linked = (Module_xml_parser.f_mizXmlAttr(l1_x, "linked")).equals("true");
				final int l4_line = Module_xml_parser.f_mizXmlIntAttrDef(l1_x, "line", aposLine);
				final int l5_col = Module_xml_parser.f_mizXmlIntAttrDef(l1_x, "col", 0);
				final Struct_Vector l6_premises = Module_vector.f_makeVector(8);
				Module_vector.f_pushVector(l6_premises, Module_correl.f_mizNewNeg(Wrappers.w_mizCopyFrm.invoke(aclaim)));
				if (l3_linked) {
					final Struct l7_gsymswitch1 = (ap).f_prev;
					Struct l8__tmp = l7_gsymswitch1;
					switch (l8__tmp.getTypeId()) {
					case 83/*Some*/: {
						final Struct_Some l9__tmp = (Struct_Some)l8__tmp;
						final Object l10_pf = l9__tmp.f_value;
						final Struct l11_pf = ((Struct)(l9__tmp).f_value);
						Module_vector.f_pushVector(l6_premises, Wrappers.w_mizCopyFrm.invoke(l11_pf));
						break;
					}
					case 75/*None*/: {
						break;
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l8__tmp.getTypeName());
					}
				} else {
				}
				Module_xml_parser.f_mizXmlNextElementState(l1_x);
				final Reference<Boolean> l12_ok = ((Reference<Boolean>)(new Reference(true)));
				final Func0<Boolean> l15_$13 = (Func0<Boolean>)() -> {
					return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
				};
				final Struct_MizPrep l17_p = ap;
				final Func0<Object> l16_$14 = (Func0<Object>)() -> {
					if (((l1_x).f_nElName).equals("Ref")) {
						final int l17_nr = Module_xml_parser.f_mizXmlIntAttrDef(l1_x, "nr", 0);
						if (Module_xml_parser.f_mizXmlHasAttr(l1_x, "articlenr")) {
							final int l18_art = Module_xml_parser.f_mizXmlIntAttr(l1_x, "articlenr");
							final String l19_kindS = Module_xml_parser.f_mizXmlAttr(l1_x, "kind");
							int l20_kind;
							if ((l19_kindS).equals("T")) {
								l20_kind=Module_prep.g_kindT;
							} else {
								l20_kind=Module_prep.g_kindD;
							}
							final Struct l21_gsymswitch3 = Module_tree.f_lookupTree((l17_p).f_theorems, Module_prep.f_thKey(l20_kind, l18_art, l17_nr));
							Struct l22__tmp = l21_gsymswitch3;
							switch (l22__tmp.getTypeId()) {
							case 83/*Some*/: {
								final Struct_Some l23__tmp = (Struct_Some)l22__tmp;
								final Object l24_f = l23__tmp.f_value;
								final Struct l25_f = ((Struct)(l23__tmp).f_value);
								Module_vector.f_pushVector(l6_premises, Wrappers.w_mizCopyFrm.invoke(l25_f));
								break;
							}
							case 75/*None*/: {
								l12_ok.value = ((Boolean)false);
								break;
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l22__tmp.getTypeName());
							}
						} else {
							final Struct l26_gsymswitch2 = Module_prep.f_mizGetLabeled(l17_p, l17_nr);
							Struct l27__tmp = l26_gsymswitch2;
							switch (l27__tmp.getTypeId()) {
							case 83/*Some*/: {
								final Struct_Some l28__tmp = (Struct_Some)l27__tmp;
								final Object l29_f = l28__tmp.f_value;
								final Struct l30_f = ((Struct)(l28__tmp).f_value);
								Module_vector.f_pushVector(l6_premises, Wrappers.w_mizCopyFrm.invoke(l30_f));
								break;
							}
							case 75/*None*/: {
								l12_ok.value = ((Boolean)false);
								break;
							}
							default:
								throw new RuntimeException("Unexpected struct in switch: "+l27__tmp.getTypeName());
							}
						}
						return Module_xml_parser.f_mizXmlSkipElement(l1_x);
					} else {
						return Module_xml_parser.f_mizXmlSkipElement(l1_x);
					}
				};
				Module_loop.f_while(l15_$13, l16_$14);
				Module_xml_parser.f_mizXmlNextElementState(l1_x);
				if ((Module_url_parameter.f_getUrlParameterDef("trace", "")).equals("1")) {
					Module_runtime.f_println(((("by at "+Module_string.f_i2s(l4_line))+":")+Module_string.f_i2s(l5_col)));
				} else {
				}
				final Struct_MizPrepStats l18_st1 = (ap).f_stats;
				((Field_checked)l18_st1).set_checked(((l18_st1).f_checked+1));
				if (((boolean)l12_ok.value)) {
					final int l19_dbgLine = Module_math.f_s2i(Module_url_parameter.f_getUrlParameterDef("debug-line", "-1"));
					final Struct_MizChecker l20_ck = (ap).f_chk;
					((Field_debug)l20_ck).set_debug((l4_line==l19_dbgLine));
					if ((l4_line==l19_dbgLine)) {
						Module_runtime.f_println(((("=== premises at "+Module_string.f_i2s(l4_line))+":")+Module_string.f_i2s(l5_col)));
						final Func1<Object,Struct> l22_$21 = (Func1<Object, Struct>)(Struct apf2) -> {
							return Module_runtime.f_println(("  "+Module_print.f_mizFrm2s(apf2)));
						};
						Module_vector.f_iterVector(l6_premises, ((Func1<Object,Object>)(Func1)l22_$21));
					} else {
					}
					Module_checker.f_mizInferenceChecker((ap).f_chk, l6_premises);
					((Field_debug)l20_ck).set_debug(false);
					if ((Module_mobjects.f_mizSeqCount(((ap).f_chk).f_chErrNr)==0)) {
						final Struct_MizPrepStats l23_st2 = (ap).f_stats;
						((Field_accepted)l23_st2).set_accepted(((l23_st2).f_accepted+1));
						return null;
					} else {
						final Struct_MizPrepStats l24_st3 = (ap).f_stats;
						((Field_failed)l24_st3).set_failed(((l24_st3).f_failed+1));
						final Object[] l27_$26 = Native.enumFromTo(0, (Module_mobjects.f_mizSeqCount(((ap).f_chk).f_chErrNr)-1));
						final Struct_MizPrep l29_p = ap;
						final Func2<String,String, Integer> l28_$25 = (Func2<String, String, Integer>)(String aacc, Integer ai) -> {
							return ((aacc+" ")+Module_string.f_i2s(Module_mobjects.f_mizSeqValue(((l29_p).f_chk).f_chErrNr, ((int)ai))));
						};
						final String l30_errs = ((String)Native.fold(l27_$26, "", ((Func2<Object,Object, Object>)(Func2)l28_$25)));
						return Module_vector.f_pushVector(((ap).f_stats).f_failures, (((Module_string.f_i2s(l4_line)+":")+Module_string.f_i2s(l5_col))+l30_errs));
					}
				} else {
					final Struct_MizPrepStats l31_st4 = (ap).f_stats;
					((Field_failed)l31_st4).set_failed(((l31_st4).f_failed+1));
					return Module_vector.f_pushVector(((ap).f_stats).f_failures, (((Module_string.f_i2s(l4_line)+":")+Module_string.f_i2s(l5_col))+" unresolved-ref"));
				}
			} else {
				if ((l2_name).equals("From")) {
					final Struct_MizPrepStats l32_st5 = (ap).f_stats;
					((Field_skippedFrom)l32_st5).set_skippedFrom(((l32_st5).f_skippedFrom+1));
					return Module_xml_parser.f_mizXmlSkipElement(l1_x);
				} else {
					if ((l2_name).equals("Proof")) {
						return Module_prep.f_mizWalkBlock(ap);
					} else {
						if ((l2_name).equals("SkippedProof")) {
							return Module_xml_parser.f_mizXmlSkipElement(l1_x);
						} else {
							final Struct_MizPrepStats l33_st6 = (ap).f_stats;
							((Field_skippedBlocks)l33_st6).set_skippedBlocks(((l33_st6).f_skippedBlocks+1));
							return Module_xml_parser.f_mizXmlSkipElement(l1_x);
						}
					}
				}
			}
		}
	}
	public static final Object f_mizLoadClusters(Struct_MizCorrelEnv aenv, Struct_MizClusters acl, String acontent) {
		final Struct_MizVrfReader l0_r2 = Module_prep.f_mizMakeVrfReaderMML(Module_xml_parser.f_mizXmlInitParsing(acontent), aenv);
		final Struct_MizXmlParser l1_x = (l0_r2).f_x;
		Module_xml_parser.f_mizXmlNextElementState(l1_x);
		Module_xml_parser.f_mizXmlAssert(((l1_x).f_nElName).equals("Registrations"));
		Module_xml_parser.f_mizXmlNextElementState(l1_x);
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
		};
		final Struct_MizCorrelEnv l6_env = aenv;
		final Struct_MizClusters l7_cl = acl;
		final Func0<Object> l5_$3 = (Func0<Object>)() -> {
			return Module_prep.f_mizReadOneCluster(l6_env, l7_cl, l0_r2);
		};
		return Module_loop.f_while(l4_$2, l5_$3);
	}
	public static final Object f_mizLoadReductions(Struct_MizCorrelEnv aenv, Struct_MizIdentify aidn, String acontent) {
		if ((!(acontent).equals(""))) {
			final Struct_MizVrfReader l0_r2 = Module_prep.f_mizMakeVrfReaderMML(Module_xml_parser.f_mizXmlInitParsing(acontent), aenv);
			final Struct_MizXmlParser l1_x = (l0_r2).f_x;
			Module_xml_parser.f_mizXmlNextElementState(l1_x);
			Module_xml_parser.f_mizXmlAssert(((l1_x).f_nElName).equals("ReductionRegistrations"));
			Module_xml_parser.f_mizXmlNextElementState(l1_x);
			final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
				return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
			};
			final Struct_MizIdentify l6_idn = aidn;
			final Func0<Object> l5_$3 = (Func0<Object>)() -> {
				if (((l1_x).f_nElName).equals("Reduction")) {
					return Module_prep.f_mizReadOneReduction(l6_idn, l0_r2);
				} else {
					return Module_xml_parser.f_mizXmlSkipElement(l1_x);
				}
			};
			return Module_loop.f_while(l4_$2, l5_$3);
		} else {
			return null;
		}
	}
	public static final Object f_mizLoadRequirements(Struct_MizCorrelEnv aenv, String apath) {
		final String l0_content = Native.getFileContent(apath);
		final Object[] l1_lines = Module_string.f_strSplit(l0_content, "\n");
		final int l4_$3 = ((int)Module_runtime.f_min((Native.length(l1_lines)-1), Module_builtin.g_rqLAST));
		final Struct_MizCorrelEnv l6_env = aenv;
		final Func1<Object,Integer> l5_$2 = (Func1<Object, Integer>)(Integer ai) -> {
			final int l6_v = Module_math.f_s2i(Module_string.f_trim(((String)(l1_lines[((int)ai)]))));
			return Module_builtin.f_mizSetBuiltIn((l6_env).f_builtIn, ((int)ai), l6_v);
		};
		return Module_runtime.f_fori(0, l4_$3, l5_$2);
	}
	public static final Object f_mizLoadTheorems(Struct_MizPrep ap, String acontent) {
		final Struct_MizVrfReader l0_r2 = Module_prep.f_mizMakeVrfReaderMML(Module_xml_parser.f_mizXmlInitParsing(acontent), ((ap).f_r).f_env);
		final Struct_MizXmlParser l1_x = (l0_r2).f_x;
		Module_xml_parser.f_mizXmlNextElementState(l1_x);
		Module_xml_parser.f_mizXmlAssert(((l1_x).f_nElName).equals("Theorems"));
		Module_xml_parser.f_mizXmlNextElementState(l1_x);
		final Reference<Integer> l2_maxArt = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
		};
		final Struct_MizPrep l7_p = ap;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			if (((l1_x).f_nElName).equals("Theorem")) {
				final int l7_articlenr = Module_xml_parser.f_mizXmlIntAttrDef(l1_x, "articlenr", 0);
				final int l8_nr = Module_xml_parser.f_mizXmlIntAttrDef(l1_x, "nr", 0);
				final String l9_kindS = Module_xml_parser.f_mizXmlAttr(l1_x, "kind");
				int l10_kind;
				if ((l9_kindS).equals("T")) {
					l10_kind=Module_prep.g_kindT;
				} else {
					l10_kind=Module_prep.g_kindD;
				}
				if ((l7_articlenr>((int)l2_maxArt.value))) {
					l2_maxArt.value = ((Integer)l7_articlenr);
				} else {
				}
				Module_xml_parser.f_mizXmlNextElementState(l1_x);
				final Struct l11_f = Module_iocorrel.f_mizInFormula(l0_r2);
				Module_xml_parser.f_mizXmlNextElementState(l1_x);
				((Field_theorems)l7_p).set_theorems(Module_tree.f_setTree((l7_p).f_theorems, Module_prep.f_thKey(l10_kind, l7_articlenr, l8_nr), l11_f));
				return null;
			} else {
				return Module_xml_parser.f_mizXmlSkipElement(l1_x);
			}
		};
		Module_loop.f_while(l5_$3, l6_$4);
		((Field_ownArticleNr)ap).set_ownArticleNr((((int)l2_maxArt.value)+1));
		return null;
	}
	public static final Struct_MizVrfReader f_mizMakeVrfReaderMML(Struct_MizXmlParser ax, Struct_MizCorrelEnv aenv) {
		final Struct_MizVrfReader l0_r = Module_iocorrel.f_mizMakeVrfReader(ax, aenv);
		((Field_mml)l0_r).set_mml(true);
		return l0_r;
	}
	public static final Object f_mizPushFixed(Struct_MizPrep ap, Struct_MizTyp atyp) {
		final Struct_MizCorrelEnv l0_env = ((ap).f_chk).f_env;
		((Field_fixedVarNbr)l0_env).set_fixedVarNbr(((l0_env).f_fixedVarNbr+1));
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector((l0_env).f_fixedVar)<=(l0_env).f_fixedVarNbr);
		};
		final Func0<Object> l4_$2 = (Func0<Object>)() -> {
			return Module_vector.f_pushVector((l0_env).f_fixedVar, (new Struct_MizFixedVar(0, Module_correl.f_mizNewIncorTyp(), false, ((Struct)SingletonStructs.str_None), 0)));
		};
		Module_loop.f_while(l3_$1, l4_$2);
		final Struct_MizFixedVar l5_fv = ((Struct_MizFixedVar)Module_vector.f_getVectorUnsafe((l0_env).f_fixedVar, (l0_env).f_fixedVarNbr));
		((Field_typ)l5_fv).set_typ(atyp);
		((Field_def)l5_fv).set_def(SingletonStructs.str_None);
		return null;
	}
	public static final Object f_mizReadOneCluster(Struct_MizCorrelEnv aenv, Struct_MizClusters acl, Struct_MizVrfReader ar2) {
		final Struct_MizXmlParser l0_x = (ar2).f_x;
		final String l1_name = (l0_x).f_nElName;
		final String l2_aid = Module_xml_parser.f_mizXmlAttr(l0_x, "aid");
		final int l3_nr = Module_xml_parser.f_mizXmlIntAttrDef(l0_x, "nr", 0);
		if ((l1_name).equals("RCluster")) {
			Module_xml_parser.f_mizXmlNextElementState(l0_x);
			final Struct_Vector l4_prims = Module_iocorrel.f_mizInArgColl(ar2);
			final Struct_MizTyp l5_typ = Module_iocorrel.f_mizInType(ar2);
			final Struct_MizAttrColl l6_lower = Module_iocorrel.f_mizInAttrColl(ar2);
			Struct_MizAttrColl l7_upper;
			if ((((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&((l0_x).f_nElName).equals("Cluster"))) {
				l7_upper=Module_iocorrel.f_mizInAttrColl(ar2);
			} else {
				l7_upper=Module_correl.f_mizCopyCluster(l6_lower);
			}
			Module_roundcl.f_mizInsertRCluster(acl, (new Struct_MizRCluster(l2_aid, l3_nr, l4_prims, (new Struct_MizClusterRec(l6_lower, l7_upper)), l5_typ)));
			return Module_xml_parser.f_mizXmlNextElementState(l0_x);
		} else {
			if ((l1_name).equals("CCluster")) {
				Module_xml_parser.f_mizXmlNextElementState(l0_x);
				final Struct_Vector l8_prims = Module_iocorrel.f_mizInArgColl(ar2);
				final Struct_MizAttrColl l9_ante = Module_iocorrel.f_mizInAttrColl(ar2);
				final Struct_MizTyp l10_typ = Module_iocorrel.f_mizInType(ar2);
				final Struct_MizAttrColl l11_lower = Module_iocorrel.f_mizInAttrColl(ar2);
				Struct_MizAttrColl l12_upper;
				if ((((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&((l0_x).f_nElName).equals("Cluster"))) {
					l12_upper=Module_iocorrel.f_mizInAttrColl(ar2);
				} else {
					l12_upper=Module_correl.f_mizCopyCluster(l11_lower);
				}
				Module_roundcl.f_mizInsertCCluster(aenv, acl, (new Struct_MizCCluster(l2_aid, l3_nr, l8_prims, l9_ante, (new Struct_MizClusterRec(l11_lower, l12_upper)), l10_typ)));
				return Module_xml_parser.f_mizXmlNextElementState(l0_x);
			} else {
				if ((l1_name).equals("FCluster")) {
					Module_xml_parser.f_mizXmlNextElementState(l0_x);
					final Struct_Vector l13_prims = Module_iocorrel.f_mizInArgColl(ar2);
					final Struct l14_trm = Module_iocorrel.f_mizInTerm(ar2);
					final Struct_MizAttrColl l15_lower = Module_iocorrel.f_mizInAttrColl(ar2);
					Struct_MizAttrColl l16_upper;
					if ((((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&((l0_x).f_nElName).equals("Cluster"))) {
						l16_upper=Module_iocorrel.f_mizInAttrColl(ar2);
					} else {
						l16_upper=Module_correl.f_mizCopyCluster(l15_lower);
					}
					Struct l17_typ;
					if ((((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&((l0_x).f_nElName).equals("Typ"))) {
						l17_typ=(new Struct_Some(Module_iocorrel.f_mizInType(ar2)));
					} else {
						l17_typ=((Struct)SingletonStructs.str_None);
					}
					Module_roundcl.f_mizInsertFCluster(aenv, acl, (new Struct_MizFCluster(l2_aid, l3_nr, l13_prims, (new Struct_MizClusterRec(l15_lower, l16_upper)), l14_trm, l17_typ)));
					return Module_xml_parser.f_mizXmlNextElementState(l0_x);
				} else {
					return Module_xml_parser.f_mizXmlSkipElement(l0_x);
				}
			}
		}
	}
	public static final Object f_mizReadOneReduction(Struct_MizIdentify aidn, Struct_MizVrfReader ar2) {
		final Struct_MizXmlParser l0_x = (ar2).f_x;
		final String l1_aid = Module_xml_parser.f_mizXmlAttr(l0_x, "aid");
		final int l2_nr = Module_xml_parser.f_mizXmlIntAttrDef(l0_x, "nr", 0);
		Module_xml_parser.f_mizXmlNextElementState(l0_x);
		if ((((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&((l0_x).f_nElName).equals("ErrorReduction"))) {
			final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
				return ((l0_x).f_nState==Module_xml_parser.g_MizEStart);
			};
			final Func0<Object> l6_$4 = (Func0<Object>)() -> {
				return Module_xml_parser.f_mizXmlSkipElement(l0_x);
			};
			Module_loop.f_while(l5_$3, l6_$4);
		} else {
			final Struct_Vector l7_prims = Module_vector.f_makeVector(4);
			final Func0<Boolean> l10_$8 = (Func0<Boolean>)() -> {
				return ((Boolean)(((l0_x).f_nState==Module_xml_parser.g_MizEStart)&&((l0_x).f_nElName).equals("Typ")));
			};
			final Struct_MizVrfReader l12_r2 = ar2;
			final Func0<Object> l11_$9 = (Func0<Object>)() -> {
				return Module_vector.f_pushVector(l7_prims, Module_iocorrel.f_mizInType(l12_r2));
			};
			Module_loop.f_while(l10_$8, l11_$9);
			final Struct l13_t0 = Module_iocorrel.f_mizInTerm(ar2);
			final Struct l14_t1 = Module_iocorrel.f_mizInTerm(ar2);
			Module_vector.f_pushVector((aidn).f_reductions, (new Struct_MizReduction(l1_aid, l2_nr, l7_prims, l13_t0, l14_t1)));
		}
		return Module_xml_parser.f_mizXmlNextElementState(l0_x);
	}
	public static final Struct_Pair f_mizReadProposition(Struct_MizPrep ap) {
		final Struct_MizXmlParser l0_x = ((ap).f_r).f_x;
		final int l1_nr = Module_xml_parser.f_mizXmlIntAttrDef(l0_x, "nr", 0);
		Module_xml_parser.f_mizXmlNextElementState(l0_x);
		final Struct l2_f = Module_iocorrel.f_mizInFormula((ap).f_r);
		Module_xml_parser.f_mizXmlNextElementState(l0_x);
		return (new Struct_Pair(l1_nr, l2_f));
	}
	public static final Object f_mizSetLabeled(Struct_MizPrep ap, int anr, Struct af) {
		final Struct_MizPrep l3_p = ap;
		final int l4_nr = anr;
		final Func0<Boolean> l2_$0 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector((l3_p).f_labeled)<=l4_nr);
		};
		final Struct_MizPrep l6_p = ap;
		final Func0<Object> l5_$1 = (Func0<Object>)() -> {
			return Module_vector.f_pushVector((l6_p).f_labeled, SingletonStructs.str_None);
		};
		Module_loop.f_while(l2_$0, l5_$1);
		return Module_vector.f_setVectorUnsafe((ap).f_labeled, anr, (new Struct_Some(af)));
	}
	public static final Object f_mizSetPermLabeled(Struct_MizPrep ap, int anr, Struct af) {
		final Struct_MizPrep l3_p = ap;
		final int l4_nr = anr;
		final Func0<Boolean> l2_$0 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector((l3_p).f_permLabeled)<=l4_nr);
		};
		final Struct_MizPrep l6_p = ap;
		final Func0<Object> l5_$1 = (Func0<Object>)() -> {
			return Module_vector.f_pushVector((l6_p).f_permLabeled, SingletonStructs.str_None);
		};
		Module_loop.f_while(l2_$0, l5_$1);
		return Module_vector.f_setVectorUnsafe((ap).f_permLabeled, anr, (new Struct_Some(af)));
	}
	public static final Object f_mizWalkBlock(Struct_MizPrep ap) {
		final Struct_MizCorrelEnv l0_env = ((ap).f_chk).f_env;
		final Struct_MizXmlParser l1_x = ((ap).f_r).f_x;
		final int l2_fixedMark = (l0_env).f_fixedVarNbr;
		final int l3_labelMark = Module_vector.f_sizeVector((ap).f_labeled);
		final Struct l4_prevMark = (ap).f_prev;
		Module_xml_parser.f_mizXmlNextElementState(l1_x);
		Module_prep.f_mizWalkItems(ap);
		Module_xml_parser.f_mizXmlNextElementState(l1_x);
		((Field_fixedVarNbr)l0_env).set_fixedVarNbr(l2_fixedMark);
		final Struct_MizPrep l8_p = ap;
		final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
			return (Module_vector.f_sizeVector((l8_p).f_labeled)>l3_labelMark);
		};
		final Struct_MizPrep l10_p = ap;
		final Func0<Object> l9_$6 = (Func0<Object>)() -> {
			return Module_vector.f_removeVector((l10_p).f_labeled, (Module_vector.f_sizeVector((l10_p).f_labeled)-1));
		};
		Module_loop.f_while(l7_$5, l9_$6);
		((Field_prev)ap).set_prev(l4_prevMark);
		return null;
	}
	public static final Object f_mizWalkItems(Struct_MizPrep ap) {
		final Struct_MizCorrelEnv l0_env = ((ap).f_chk).f_env;
		final Struct_MizXmlParser l1_x = ((ap).f_r).f_x;
		final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l1_x).f_nState==Module_xml_parser.g_MizEStart)&&((l1_x).f_nCurTokenKind!=Module_xml_parser.g_MizXTEOTX)));
		};
		final Struct_MizPrep l6_p = ap;
		final Func0<Object> l5_$3 = (Func0<Object>)() -> {
			final String l6_name = (l1_x).f_nElName;
			if ((l6_name).equals("Proposition")) {
				final Struct_Pair l7_pr = Module_prep.f_mizReadProposition(l6_p);
				Module_prep.f_mizJustify(l6_p, ((Struct)(l7_pr).f_second), 0);
				if ((((int)(l7_pr).f_first)>0)) {
					Module_prep.f_mizSetLabeled(l6_p, ((int)(l7_pr).f_first), ((Struct)(l7_pr).f_second));
				} else {
				}
				((Field_prev)l6_p).set_prev((new Struct_Some((l7_pr).f_second)));
				return null;
			} else {
				if ((l6_name).equals("JustifiedTheorem")) {
					Module_xml_parser.f_mizXmlNextElementState(l1_x);
					if ((((l1_x).f_nState==Module_xml_parser.g_MizEStart)&&((l1_x).f_nElName).equals("Proposition"))) {
						final Struct_Pair l8_pr = Module_prep.f_mizReadProposition(l6_p);
						Module_prep.f_mizJustify(l6_p, ((Struct)(l8_pr).f_second), 0);
						((Field_ownTheoremNr)l6_p).set_ownTheoremNr(((l6_p).f_ownTheoremNr+1));
						((Field_theorems)l6_p).set_theorems(Module_tree.f_setTree((l6_p).f_theorems, Module_prep.f_thKey(Module_prep.g_kindT, (l6_p).f_ownArticleNr, (l6_p).f_ownTheoremNr), (l8_pr).f_second));
						if ((((int)(l8_pr).f_first)>0)) {
							Module_prep.f_mizSetPermLabeled(l6_p, ((int)(l8_pr).f_first), ((Struct)(l8_pr).f_second));
						} else {
						}
					} else {
					}
					final Func0<Boolean> l11_$9 = (Func0<Boolean>)() -> {
						return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
					};
					final Func0<Object> l12_$10 = (Func0<Object>)() -> {
						return Module_xml_parser.f_mizXmlSkipElement(l1_x);
					};
					Module_loop.f_while(l11_$9, l12_$10);
					return Module_xml_parser.f_mizXmlNextElementState(l1_x);
				} else {
					if ((l6_name).equals("DefTheorem")) {
						((Field_ownDefNr)l6_p).set_ownDefNr(((l6_p).f_ownDefNr+1));
						Module_xml_parser.f_mizXmlNextElementState(l1_x);
						if ((((l1_x).f_nState==Module_xml_parser.g_MizEStart)&&((l1_x).f_nElName).equals("Proposition"))) {
							final Struct_Pair l13_pr = Module_prep.f_mizReadProposition(l6_p);
							((Field_theorems)l6_p).set_theorems(Module_tree.f_setTree((l6_p).f_theorems, Module_prep.f_thKey(Module_prep.g_kindD, (l6_p).f_ownArticleNr, (l6_p).f_ownDefNr), (l13_pr).f_second));
							if ((((int)(l13_pr).f_first)>0)) {
								Module_prep.f_mizSetPermLabeled(l6_p, ((int)(l13_pr).f_first), ((Struct)(l13_pr).f_second));
							} else {
							}
						} else {
						}
						final Func0<Boolean> l16_$14 = (Func0<Boolean>)() -> {
							return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
						};
						final Func0<Object> l17_$15 = (Func0<Object>)() -> {
							return Module_xml_parser.f_mizXmlSkipElement(l1_x);
						};
						Module_loop.f_while(l16_$14, l17_$15);
						return Module_xml_parser.f_mizXmlNextElementState(l1_x);
					} else {
						if ((l6_name).equals("Let")) {
							final int l18_n = Module_xml_parser.f_mizXmlIntAttrDef(l1_x, "nr", 1);
							Module_xml_parser.f_mizXmlNextElementState(l1_x);
							final Func0<Boolean> l21_$19 = (Func0<Boolean>)() -> {
								return ((Boolean)(((l1_x).f_nState==Module_xml_parser.g_MizEStart)&&((l1_x).f_nElName).equals("Typ")));
							};
							final Func0<Object> l22_$20 = (Func0<Object>)() -> {
								final Struct_MizTyp l23_typ = Module_iocorrel.f_mizInType((l6_p).f_r);
								return Module_prep.f_mizPushFixed(l6_p, l23_typ);
							};
							Module_loop.f_while(l21_$19, l22_$20);
							final Func0<Boolean> l25_$23 = (Func0<Boolean>)() -> {
								return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
							};
							final Func0<Object> l26_$24 = (Func0<Object>)() -> {
								return Module_xml_parser.f_mizXmlSkipElement(l1_x);
							};
							Module_loop.f_while(l25_$23, l26_$24);
							return Module_xml_parser.f_mizXmlNextElementState(l1_x);
						} else {
							if ((l6_name).equals("Assume")) {
								Module_xml_parser.f_mizXmlNextElementState(l1_x);
								final Func0<Boolean> l29_$27 = (Func0<Boolean>)() -> {
									return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
								};
								final Func0<Object> l30_$28 = (Func0<Object>)() -> {
									if (((l1_x).f_nElName).equals("Proposition")) {
										final Struct_Pair l31_pr = Module_prep.f_mizReadProposition(l6_p);
										if ((((int)(l31_pr).f_first)>0)) {
											Module_prep.f_mizSetLabeled(l6_p, ((int)(l31_pr).f_first), ((Struct)(l31_pr).f_second));
										} else {
										}
										((Field_prev)l6_p).set_prev((new Struct_Some((l31_pr).f_second)));
										return null;
									} else {
										return Module_xml_parser.f_mizXmlSkipElement(l1_x);
									}
								};
								Module_loop.f_while(l29_$27, l30_$28);
								return Module_xml_parser.f_mizXmlNextElementState(l1_x);
							} else {
								if (((l6_name).equals("Consider")||(l6_name).equals("Given"))) {
									final boolean l31_justified = (l6_name).equals("Consider");
									Module_xml_parser.f_mizXmlNextElementState(l1_x);
									if ((((l1_x).f_nState==Module_xml_parser.g_MizEStart)&&((l1_x).f_nElName).equals("Proposition"))) {
										final Struct_Pair l32_pr = Module_prep.f_mizReadProposition(l6_p);
										if (l31_justified) {
											Module_prep.f_mizJustify(l6_p, ((Struct)(l32_pr).f_second), 0);
										} else {
										}
									} else {
									}
									final Func0<Boolean> l35_$33 = (Func0<Boolean>)() -> {
										return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
									};
									final Func0<Object> l36_$34 = (Func0<Object>)() -> {
										if (((l1_x).f_nElName).equals("Typ")) {
											final Struct_MizTyp l37_typ = Module_iocorrel.f_mizInType((l6_p).f_r);
											return Module_prep.f_mizPushFixed(l6_p, l37_typ);
										} else {
											if (((l1_x).f_nElName).equals("Proposition")) {
												final Struct_Pair l38_pr2 = Module_prep.f_mizReadProposition(l6_p);
												if ((((int)(l38_pr2).f_first)>0)) {
													Module_prep.f_mizSetLabeled(l6_p, ((int)(l38_pr2).f_first), ((Struct)(l38_pr2).f_second));
												} else {
												}
												((Field_prev)l6_p).set_prev((new Struct_Some((l38_pr2).f_second)));
												return null;
											} else {
												return Module_xml_parser.f_mizXmlSkipElement(l1_x);
											}
										}
									};
									Module_loop.f_while(l35_$33, l36_$34);
									return Module_xml_parser.f_mizXmlNextElementState(l1_x);
								} else {
									if ((l6_name).equals("Reconsider")) {
										Module_xml_parser.f_mizXmlNextElementState(l1_x);
										final Func0<Boolean> l39_$37 = (Func0<Boolean>)() -> {
											return ((Boolean)(((l1_x).f_nState==Module_xml_parser.g_MizEStart)&&((l1_x).f_nElName).equals("Typ")));
										};
										final Func0<Object> l40_$38 = (Func0<Object>)() -> {
											final Struct_MizTyp l41_typ = Module_iocorrel.f_mizInType((l6_p).f_r);
											Module_prep.f_mizPushFixed(l6_p, l41_typ);
											final Struct l42_trm = Module_iocorrel.f_mizInTerm((l6_p).f_r);
											final Struct_MizFixedVar l43_fv = ((Struct_MizFixedVar)Module_vector.f_getVectorUnsafe((l0_env).f_fixedVar, (l0_env).f_fixedVarNbr));
											((Field_def)l43_fv).set_def((new Struct_Some(l42_trm)));
											return null;
										};
										Module_loop.f_while(l39_$37, l40_$38);
										if ((((l1_x).f_nState==Module_xml_parser.g_MizEStart)&&((l1_x).f_nElName).equals("Proposition"))) {
											final Struct_Pair l41_pr = Module_prep.f_mizReadProposition(l6_p);
											Module_prep.f_mizJustify(l6_p, ((Struct)(l41_pr).f_second), 0);
										} else {
										}
										final Func0<Boolean> l44_$42 = (Func0<Boolean>)() -> {
											return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
										};
										final Func0<Object> l45_$43 = (Func0<Object>)() -> {
											return Module_xml_parser.f_mizXmlSkipElement(l1_x);
										};
										Module_loop.f_while(l44_$42, l45_$43);
										return Module_xml_parser.f_mizXmlNextElementState(l1_x);
									} else {
										if ((l6_name).equals("TakeAsVar")) {
											Module_xml_parser.f_mizXmlNextElementState(l1_x);
											final Struct_MizTyp l46_typ3 = Module_iocorrel.f_mizInType((l6_p).f_r);
											Module_prep.f_mizPushFixed(l6_p, l46_typ3);
											final Struct l47_trm3 = Module_iocorrel.f_mizInTerm((l6_p).f_r);
											final Struct_MizFixedVar l48_fv3 = ((Struct_MizFixedVar)Module_vector.f_getVectorUnsafe((l0_env).f_fixedVar, (l0_env).f_fixedVarNbr));
											((Field_def)l48_fv3).set_def((new Struct_Some(l47_trm3)));
											final Func0<Boolean> l51_$49 = (Func0<Boolean>)() -> {
												return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
											};
											final Func0<Object> l52_$50 = (Func0<Object>)() -> {
												return Module_xml_parser.f_mizXmlSkipElement(l1_x);
											};
											Module_loop.f_while(l51_$49, l52_$50);
											return Module_xml_parser.f_mizXmlNextElementState(l1_x);
										} else {
											if ((l6_name).equals("Set")) {
												Module_xml_parser.f_mizXmlNextElementState(l1_x);
												final Struct l53___trm = Module_iocorrel.f_mizInTerm((l6_p).f_r);
												final Struct_MizTyp l54_typ2 = Module_iocorrel.f_mizInType((l6_p).f_r);
												Module_prep.f_mizPushFixed(l6_p, l54_typ2);
												final Func0<Boolean> l57_$55 = (Func0<Boolean>)() -> {
													return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
												};
												final Func0<Object> l58_$56 = (Func0<Object>)() -> {
													return Module_xml_parser.f_mizXmlSkipElement(l1_x);
												};
												Module_loop.f_while(l57_$55, l58_$56);
												return Module_xml_parser.f_mizXmlNextElementState(l1_x);
											} else {
												if ((l6_name).equals("Conclusion")) {
													Module_xml_parser.f_mizXmlNextElementState(l1_x);
													Module_prep.f_mizWalkItems(l6_p);
													return Module_xml_parser.f_mizXmlNextElementState(l1_x);
												} else {
													if ((l6_name).equals("Now")) {
														final int l59_labelNr = Module_xml_parser.f_mizXmlIntAttrDef(l1_x, "nr", 0);
														final Struct_MizCorrelEnv l60_env2 = ((l6_p).f_chk).f_env;
														final int l61_fixedMark = (l60_env2).f_fixedVarNbr;
														final int l62_labelMark = Module_vector.f_sizeVector((l6_p).f_labeled);
														final Struct l63_prevMark = (l6_p).f_prev;
														Module_xml_parser.f_mizXmlNextElementState(l1_x);
														((Field_lastBlockThesis)l6_p).set_lastBlockThesis(SingletonStructs.str_None);
														Module_prep.f_mizWalkItems(l6_p);
														Module_xml_parser.f_mizXmlNextElementState(l1_x);
														((Field_fixedVarNbr)l60_env2).set_fixedVarNbr(l61_fixedMark);
														final Func0<Boolean> l66_$64 = (Func0<Boolean>)() -> {
															return (Module_vector.f_sizeVector((l6_p).f_labeled)>l62_labelMark);
														};
														final Func0<Object> l67_$65 = (Func0<Object>)() -> {
															return Module_vector.f_removeVector((l6_p).f_labeled, (Module_vector.f_sizeVector((l6_p).f_labeled)-1));
														};
														Module_loop.f_while(l66_$64, l67_$65);
														((Field_prev)l6_p).set_prev(l63_prevMark);
														final Struct l68_gsymswitch5 = (l6_p).f_lastBlockThesis;
														Struct l69__tmp = l68_gsymswitch5;
														switch (l69__tmp.getTypeId()) {
														case 83/*Some*/: {
															final Struct_Some l70__tmp = (Struct_Some)l69__tmp;
															final Object l71_th = l70__tmp.f_value;
															final Struct l72_th = ((Struct)(l70__tmp).f_value);
															if ((l59_labelNr>0)) {
																Module_prep.f_mizSetLabeled(l6_p, l59_labelNr, l72_th);
															} else {
															}
															((Field_prev)l6_p).set_prev((new Struct_Some(l72_th)));
															break;
														}
														case 75/*None*/: {
															break;
														}
														default:
															throw new RuntimeException("Unexpected struct in switch: "+l69__tmp.getTypeName());
														}
														((Field_lastBlockThesis)l6_p).set_lastBlockThesis(SingletonStructs.str_None);
														return null;
													} else {
														if ((l6_name).equals("Proof")) {
															return Module_prep.f_mizWalkBlock(l6_p);
														} else {
															if ((l6_name).equals("BlockThesis")) {
																Module_xml_parser.f_mizXmlNextElementState(l1_x);
																final Func0<Boolean> l75_$73 = (Func0<Boolean>)() -> {
																	return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
																};
																final Func0<Object> l76_$74 = (Func0<Object>)() -> {
																	if (Module_iocorrel.f_mizIsFrmEl((l1_x).f_nElName)) {
																		((Field_lastBlockThesis)l6_p).set_lastBlockThesis((new Struct_Some(Module_iocorrel.f_mizInFormula((l6_p).f_r))));
																		return null;
																	} else {
																		return Module_xml_parser.f_mizXmlSkipElement(l1_x);
																	}
																};
																Module_loop.f_while(l75_$73, l76_$74);
																return Module_xml_parser.f_mizXmlNextElementState(l1_x);
															} else {
																if ((((((((((l6_name).equals("Thesis")||(l6_name).equals("ThesisExpansions"))||(l6_name).equals("EndPosition"))||(l6_name).equals("Take"))||(l6_name).equals("Reservation"))||(l6_name).equals("Section"))||(l6_name).equals("DefPred"))||(l6_name).equals("DefFunc"))||(l6_name).equals("SkippedProof"))) {
																	return Module_xml_parser.f_mizXmlSkipElement(l1_x);
																} else {
																	if ((l6_name).equals("Constructor")) {
																		final Struct_MizConstr l77_c = Module_iocorrel.f_mizInConstructor1((l6_p).f_r);
																		final int l78___nr = Module_correl.f_mizPushConstr(((l6_p).f_chk).f_env, l77_c);
																		return null;
																	} else {
																		if ((((l6_name).equals("RCluster")||(l6_name).equals("CCluster"))||(l6_name).equals("FCluster"))) {
																			return Module_prep.f_mizReadOneCluster(((l6_p).f_chk).f_env, ((l6_p).f_chk).f_clusters, (l6_p).f_r);
																		} else {
																			if ((l6_name).equals("Reduction")) {
																				return Module_prep.f_mizReadOneReduction(((l6_p).f_chk).f_idn, (l6_p).f_r);
																			} else {
																				if ((((((((((((((((l6_name).equals("DefinitionBlock")||(l6_name).equals("RegistrationBlock"))||(l6_name).equals("NotationBlock"))||(l6_name).equals("Registration"))||(l6_name).equals("Definition"))||(l6_name).equals("JustifiedProperty"))||(l6_name).equals("Coherence"))||(l6_name).equals("Existence"))||(l6_name).equals("Uniqueness"))||(l6_name).equals("Compatibility"))||(l6_name).equals("Correctness"))||(l6_name).equals("CaseBlock"))||(l6_name).equals("SupposeBlock"))||(l6_name).equals("ReductionRegistration"))||(l6_name).equals("Reducibility"))) {
																					return Module_prep.f_mizWalkBlock(l6_p);
																				} else {
																					if ((l6_name).equals("Definiens")) {
																						final Struct_MizDefiniens l79_d = Module_definientia.f_mizInDefiniens((l6_p).f_r);
																						Module_vector.f_pushVector(((l6_p).f_defs).f_all, l79_d);
																						Module_vector.f_pushVector(((l6_p).f_defs).f_expansions, l79_d);
																						final Struct l80_gsymswitch4 = Module_definientia.f_mizEqualsExpansionPub(((l6_p).f_chk).f_env, l79_d);
																						Struct l81__tmp = l80_gsymswitch4;
																						switch (l81__tmp.getTypeId()) {
																						case 83/*Some*/: {
																							final Struct_Some l82__tmp = (Struct_Some)l81__tmp;
																							final Object l83_ed = l82__tmp.f_value;
																							final Struct_MizEqualsDef l84_ed = ((Struct_MizEqualsDef)(l82__tmp).f_value);
																							final int l85_key = (l79_d).f_constrNr;
																							final Object[] l86_cur = ((Object[])Module_tree.f_lookupTreeDef(((l6_p).f_defs).f_equalsBy, l85_key, SingletonStructs.arr_empty));
																							final Struct_MizDefinientia l87_df = (l6_p).f_defs;
																							((Field_equalsBy)l87_df).set_equalsBy(Module_tree.f_setTree((l87_df).f_equalsBy, l85_key, Module_array.f_arrayPush(l86_cur, l84_ed)));
																							return null;
																						}
																						case 75/*None*/: {
																							return null;
																						}
																						default:
																							throw new RuntimeException("Unexpected struct in switch: "+l81__tmp.getTypeName());
																						}
																					} else {
																						if ((l6_name).equals("IterEquality")) {
																							final int l88_labelNr = Module_xml_parser.f_mizXmlIntAttrDef(l1_x, "nr", 0);
																							Module_xml_parser.f_mizXmlNextElementState(l1_x);
																							final Struct l89_t0 = Module_iocorrel.f_mizInTerm((l6_p).f_r);
																							final Reference<Struct> l90_cur = ((Reference<Struct>)(new Reference(l89_t0)));
																							final Func0<Boolean> l93_$91 = (Func0<Boolean>)() -> {
																								return ((Boolean)(((l1_x).f_nState==Module_xml_parser.g_MizEStart)&&((l1_x).f_nElName).equals("IterStep")));
																							};
																							final Func0<Object> l94_$92 = (Func0<Object>)() -> {
																								Module_xml_parser.f_mizXmlNextElementState(l1_x);
																								final Struct l95_ti = Module_iocorrel.f_mizInTerm((l6_p).f_r);
																								final Struct l96_stepClaim = Module_correl.f_mizNewEqFrm(((l6_p).f_chk).f_env, Module_correl.f_mizCopyTrm(l90_cur.value), Module_correl.f_mizCopyTrm(l95_ti));
																								Module_prep.f_mizJustify(l6_p, l96_stepClaim, 0);
																								l90_cur.value = l95_ti;
																								final Func0<Boolean> l99_$97 = (Func0<Boolean>)() -> {
																									return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
																								};
																								final Func0<Object> l100_$98 = (Func0<Object>)() -> {
																									return Module_xml_parser.f_mizXmlSkipElement(l1_x);
																								};
																								Module_loop.f_while(l99_$97, l100_$98);
																								return Module_xml_parser.f_mizXmlNextElementState(l1_x);
																							};
																							Module_loop.f_while(l93_$91, l94_$92);
																							final Func0<Boolean> l97_$95 = (Func0<Boolean>)() -> {
																								return ((l1_x).f_nState==Module_xml_parser.g_MizEStart);
																							};
																							final Func0<Object> l98_$96 = (Func0<Object>)() -> {
																								return Module_xml_parser.f_mizXmlSkipElement(l1_x);
																							};
																							Module_loop.f_while(l97_$95, l98_$96);
																							Module_xml_parser.f_mizXmlNextElementState(l1_x);
																							final Struct l99_resultF = Module_correl.f_mizNewEqFrm(((l6_p).f_chk).f_env, l89_t0, l90_cur.value);
																							if ((l88_labelNr>0)) {
																								Module_prep.f_mizSetLabeled(l6_p, l88_labelNr, l99_resultF);
																							} else {
																							}
																							((Field_prev)l6_p).set_prev((new Struct_Some(l99_resultF)));
																							return null;
																						} else {
																							if (((((((l6_name).equals("PerCasesReasoning")||(l6_name).equals("PerCases"))||(l6_name).equals("SchemeBlock"))||(l6_name).equals("Canceled"))||(l6_name).equals("Pattern"))||(l6_name).equals("Identify"))) {
																								final Struct_MizPrepStats l100_st7 = (l6_p).f_stats;
																								((Field_skippedBlocks)l100_st7).set_skippedBlocks(((l100_st7).f_skippedBlocks+1));
																								return Module_xml_parser.f_mizXmlSkipElement(l1_x);
																							} else {
																								final Struct_MizPrepStats l101_st8 = (l6_p).f_stats;
																								((Field_skippedBlocks)l101_st8).set_skippedBlocks(((l101_st8).f_skippedBlocks+1));
																								return Module_xml_parser.f_mizXmlSkipElement(l1_x);
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
								}
							}
						}
					}
				}
			}
		};
		return Module_loop.f_while(l4_$2, l5_$3);
	}
	public static final int f_thKey(int akind, int aarticlenr, int anr) {
		return (((akind*100000000)+(aarticlenr*10000))+anr);
	}
}
