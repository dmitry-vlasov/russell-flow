// HASH COLLISIONS: YES
// timestamp: 1785496853000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_xml_parser {
	public static int g_MizEEnd;
	public static int g_MizEStart;
	public static int g_MizXTBI;
	public static int g_MizXTDT;
	public static int g_MizXTEE;
	public static int g_MizXTEI;
	public static int g_MizXTEOTX;
	public static int g_MizXTEQ;
	public static int g_MizXTET;
	public static int g_MizXTErr;
	public static int g_MizXTGT;
	public static int g_MizXTID;
	public static int g_MizXTLT;
	public static int g_MizXTQT;
	public static int g_errBadXMLToken;
	public static int g_errElRedundant;
	public static int g_errMissingXMLAttribute;
	public static int g_errWrongXMLElement;
	public static void init() {
		g_MizEEnd=1;
		g_MizEStart=0;
		g_MizXTBI=1;
		g_MizXTDT=3;
		g_MizXTEE=7;
		g_MizXTEI=2;
		g_MizXTEOTX=11;
		g_MizXTEQ=9;
		g_MizXTET=6;
		g_MizXTErr=0;
		g_MizXTGT=5;
		g_MizXTID=10;
		g_MizXTLT=4;
		g_MizXTQT=8;
		g_errBadXMLToken=7506;
		g_errElRedundant=7500;
		g_errMissingXMLAttribute=7502;
		g_errWrongXMLElement=7503;
	}
	public static final int f_mizCharCode1(String as, int ai) {
		if (((ai>=1)&&(ai<=Native.strlen(as)))) {
			return Native.getCharCodeAt(as, (ai-1));
		} else {
			return 0;
		}
	}
	public static final Object f_mizXmlAcceptEndState(Struct_MizXmlParser ap) {
		Module_xml_parser.f_mizXmlNextElementState(ap);
		return Module_errhan.f_mizAssert(Module_xml_parser.g_errElRedundant, ((ap).f_nState==Module_xml_parser.g_MizEEnd));
	}
	public static final Object f_mizXmlAssert(boolean acond) {
		return Module_errhan.f_mizAssert(Module_xml_parser.g_errWrongXMLElement, acond);
	}
	public static final String f_mizXmlAttr(Struct_MizXmlParser ap, String aname) {
		return Module_xml_parser.f_mizXmlAttrDef(ap, aname, "");
	}
	public static final String f_mizXmlAttrDef(Struct_MizXmlParser ap, String aname, String adef) {
		return ((String)Module_maybe.f_either(Module_xml_parser.f_mizXmlFindAttr(ap, aname), adef));
	}
	public static final int f_mizXmlCharKind(int ac) {
		if ((((ac>=97)&&(ac<=122))||((ac>=65)&&(ac<=90)))) {
			return 1;
		} else {
			if (((ac>=48)&&(ac<=57))) {
				return 2;
			} else {
				if ((((((ac==35)||(ac==38))||(ac==45))||(ac==46))||(ac==95))) {
					return 3;
				} else {
					return 0;
				}
			}
		}
	}
	public static final Object f_mizXmlErrorRecovery(int aerr) {
		return Module_errhan.f_mizAssert(Module_xml_parser.g_errBadXMLToken, false);
	}
	public static final Struct f_mizXmlFindAttr(Struct_MizXmlParser ap, String aname) {
		final int l0_n = Module_vector.f_sizeVector((ap).f_nAttrVals);
		final Reference<Struct> l1_res = ((Reference<Struct>)(new Reference(SingletonStructs.str_None)));
		final Reference<Integer> l2_i = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return ((Boolean)((((int)l2_i.value)<l0_n)&&Module_maybe.f_isNone(l1_res.value)));
		};
		final Struct_MizXmlParser l7_p = ap;
		final String l8_name = aname;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			final Struct_MizXmlAttr l7_a = ((Struct_MizXmlAttr)Module_vector.f_getVectorUnsafe((l7_p).f_nAttrVals, ((int)l2_i.value)));
			if (((l7_a).f_name).equals(l8_name)) {
				((Reference<Struct_Some>)(Reference)l1_res).value = (new Struct_Some((l7_a).f_value));
			} else {
			}
			l2_i.value = ((Integer)(((int)l2_i.value)+1));
			return null;
		};
		Module_loop.f_while(l5_$3, l6_$4);
		return l1_res.value;
	}
	public static final Object f_mizXmlGetAttrValue(Struct_MizXmlParser ap) {
		final int l0_lCol = (ap).f_nCurCol;
		final Struct_MizXmlParser l4_p = ap;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return ((Boolean)(((l4_p).f_nCurCol<Native.strlen((l4_p).f_nLine))&&(Module_xml_parser.f_mizCharCode1((l4_p).f_nLine, (l4_p).f_nCurCol)!=34)));
		};
		final Struct_MizXmlParser l6_p = ap;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			((Field_nCurCol)l6_p).set_nCurCol(((l6_p).f_nCurCol+1));
			return null;
		};
		Module_loop.f_while(l3_$1, l5_$2);
		((Field_nSpelling)ap).set_nSpelling(Native.substring((ap).f_nLine, (l0_lCol-1), ((ap).f_nCurCol-l0_lCol)));
		if ((Module_xml_parser.f_mizCharCode1((ap).f_nLine, (ap).f_nCurCol)==34)) {
			((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
		} else {
		}
		return Module_xml_parser.f_mizXmlSkipBlanks(ap);
	}
	public static final Object f_mizXmlGetToken(Struct_MizXmlParser ap) {
		final Reference<Boolean> l0_eotx = ((Reference<Boolean>)(new Reference(false)));
		final Struct_MizXmlParser l4_p = ap;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l0_eotx.value)&&((l4_p).f_nCurCol>=Native.strlen((l4_p).f_nLine))));
		};
		final Struct_MizXmlParser l6_p = ap;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			if ((((l6_p).f_lineIdx+1)>=Native.length((l6_p).f_lines))) {
				((Field_nCurTokenKind)l6_p).set_nCurTokenKind(Module_xml_parser.g_MizXTEOTX);
				((Field_nSpelling)l6_p).set_nSpelling("");
				l0_eotx.value = ((Boolean)true);
				return null;
			} else {
				((Field_lineIdx)l6_p).set_lineIdx(((l6_p).f_lineIdx+1));
				((Field_nLine)l6_p).set_nLine((((String)((l6_p).f_lines[(l6_p).f_lineIdx]))+" "));
				((Field_nPos)l6_p).set_nPos((new Struct_MizPos(((l6_p).f_lineIdx+1), ((l6_p).f_nPos).f_col)));
				((Field_nCurCol)l6_p).set_nCurCol(1);
				final Func0<Boolean> l8_$6 = (Func0<Boolean>)() -> {
					return ((Boolean)(((l6_p).f_nCurCol<Native.strlen((l6_p).f_nLine))&&(Module_xml_parser.f_mizCharCode1((l6_p).f_nLine, (l6_p).f_nCurCol)==32)));
				};
				final Func0<Object> l9_$7 = (Func0<Object>)() -> {
					((Field_nCurCol)l6_p).set_nCurCol(((l6_p).f_nCurCol+1));
					return null;
				};
				return Module_loop.f_while(l8_$6, l9_$7);
			}
		};
		Module_loop.f_while(l3_$1, l5_$2);
		if (!((boolean)l0_eotx.value)) {
			((Field_nPos)ap).set_nPos((new Struct_MizPos(((ap).f_lineIdx+1), (ap).f_nCurCol)));
			final int l7_startCol = (ap).f_nCurCol;
			final int l8_c = Module_xml_parser.f_mizCharCode1((ap).f_nLine, (ap).f_nCurCol);
			if (Module_xml_parser.f_mizXmlIsNameStart(l8_c)) {
				((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTID);
				((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
				final Struct_MizXmlParser l12_p = ap;
				final Func0<Boolean> l11_$9 = (Func0<Boolean>)() -> {
					return (Module_xml_parser.f_mizXmlCharKind(Module_xml_parser.f_mizCharCode1((l12_p).f_nLine, (l12_p).f_nCurCol))!=0);
				};
				final Struct_MizXmlParser l14_p = ap;
				final Func0<Object> l13_$10 = (Func0<Object>)() -> {
					((Field_nCurCol)l14_p).set_nCurCol(((l14_p).f_nCurCol+1));
					return null;
				};
				Module_loop.f_while(l11_$9, l13_$10);
			} else {
				if ((l8_c==34)) {
					((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTQT);
					((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
				} else {
					if ((l8_c==61)) {
						((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTEQ);
						((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
					} else {
						if ((l8_c==60)) {
							((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
							final int l15_c2 = Module_xml_parser.f_mizCharCode1((ap).f_nLine, (ap).f_nCurCol);
							if ((l15_c2==47)) {
								((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTET);
								((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
							} else {
								if ((l15_c2==63)) {
									((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTBI);
									((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
								} else {
									if ((l15_c2==33)) {
										((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTDT);
										((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
									} else {
										((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTLT);
									}
								}
							}
						} else {
							if ((l8_c==62)) {
								((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTGT);
								((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
							} else {
								if ((l8_c==47)) {
									((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
									if ((Module_xml_parser.f_mizCharCode1((ap).f_nLine, (ap).f_nCurCol)==62)) {
										((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTEE);
										((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
									} else {
										((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTErr);
									}
								} else {
									if ((l8_c==63)) {
										((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
										if ((Module_xml_parser.f_mizCharCode1((ap).f_nLine, (ap).f_nCurCol)==62)) {
											((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTEI);
											((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
										} else {
											((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTErr);
										}
									} else {
										((Field_nCurTokenKind)ap).set_nCurTokenKind(Module_xml_parser.g_MizXTErr);
										((Field_nCurCol)ap).set_nCurCol(((ap).f_nCurCol+1));
									}
								}
							}
						}
					}
				}
			}
			((Field_nSpelling)ap).set_nSpelling(Native.substring((ap).f_nLine, (l7_startCol-1), ((ap).f_nCurCol-l7_startCol)));
			return Module_xml_parser.f_mizXmlSkipBlanks(ap);
		} else {
			return null;
		}
	}
	public static final boolean f_mizXmlHasAttr(Struct_MizXmlParser ap, String aname) {
		return Module_maybe.f_isSome(Module_xml_parser.f_mizXmlFindAttr(ap, aname));
	}
	public static final Struct_MizXmlParser f_mizXmlInitParsing(String acontent) {
		final Struct_MizXmlParser l0_p = Module_xml_parser.f_mizXmlMakeParser(acontent);
		Module_xml_parser.f_mizXmlGetToken(l0_p);
		if (((l0_p).f_nCurTokenKind==Module_xml_parser.g_MizXTBI)) {
			Module_xml_parser.f_mizXmlGetToken(l0_p);
			if ((((l0_p).f_nCurTokenKind==Module_xml_parser.g_MizXTID)&&((l0_p).f_nSpelling).equals("xml"))) {
				Module_xml_parser.f_mizXmlGetToken(l0_p);
			} else {
				Module_xml_parser.f_mizXmlErrorRecovery(10);
			}
			final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
				return ((Boolean)(((l0_p).f_nCurTokenKind!=Module_xml_parser.g_MizXTEOTX)&&((l0_p).f_nCurTokenKind!=Module_xml_parser.g_MizXTEI)));
			};
			final Func0<Object> l4_$2 = (Func0<Object>)() -> {
				return Module_xml_parser.f_mizXmlGetToken(l0_p);
			};
			Module_loop.f_while(l3_$1, l4_$2);
			if (((l0_p).f_nCurTokenKind==Module_xml_parser.g_MizXTEI)) {
				Module_xml_parser.f_mizXmlGetToken(l0_p);
			} else {
			}
			final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
				return ((l0_p).f_nCurTokenKind==Module_xml_parser.g_MizXTBI);
			};
			final Func0<Object> l8_$6 = (Func0<Object>)() -> {
				Module_xml_parser.f_mizXmlGetToken(l0_p);
				final Func0<Boolean> l11_$9 = (Func0<Boolean>)() -> {
					return ((Boolean)(((l0_p).f_nCurTokenKind!=Module_xml_parser.g_MizXTEOTX)&&((l0_p).f_nCurTokenKind!=Module_xml_parser.g_MizXTEI)));
				};
				final Func0<Object> l12_$10 = (Func0<Object>)() -> {
					return Module_xml_parser.f_mizXmlGetToken(l0_p);
				};
				Module_loop.f_while(l11_$9, l12_$10);
				if (((l0_p).f_nCurTokenKind==Module_xml_parser.g_MizXTEI)) {
					return Module_xml_parser.f_mizXmlGetToken(l0_p);
				} else {
					return null;
				}
			};
			Module_loop.f_while(l7_$5, l8_$6);
		} else {
		}
		return l0_p;
	}
	public static final Struct_MizXmlParser f_mizXmlInitParsingFile(String apath) {
		return Module_xml_parser.f_mizXmlInitParsing(Native.getFileContent(apath));
	}
	public static final int f_mizXmlIntAttr(Struct_MizXmlParser ap, String aname) {
		final Struct l0_gsymswitch0 = Module_xml_parser.f_mizXmlFindAttr(ap, aname);
		Struct l1__tmp = l0_gsymswitch0;
		switch (l1__tmp.getTypeId()) {
		case 88/*Some*/: {
			final Struct_Some l2__tmp = (Struct_Some)l1__tmp;
			final Object l3_v = l2__tmp.f_value;
			final String l4_v = ((String)(l2__tmp).f_value);
			return Module_math.f_s2i(l4_v);
		}
		case 80/*None*/: {
			Module_errhan.f_mizAssert(Module_xml_parser.g_errMissingXMLAttribute, false);
			return 0;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l1__tmp.getTypeName());
		}
	}
	public static final int f_mizXmlIntAttrDef(Struct_MizXmlParser ap, String aname, int adef) {
		final Struct l0_gsymswitch1 = Module_xml_parser.f_mizXmlFindAttr(ap, aname);
		Struct l1__tmp = l0_gsymswitch1;
		switch (l1__tmp.getTypeId()) {
		case 88/*Some*/: {
			final Struct_Some l2__tmp = (Struct_Some)l1__tmp;
			final Object l3_v = l2__tmp.f_value;
			final String l4_v = ((String)(l2__tmp).f_value);
			return Module_math.f_s2i(l4_v);
		}
		case 80/*None*/: {
			return adef;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l1__tmp.getTypeName());
		}
	}
	public static final boolean f_mizXmlIsNameStart(int ac) {
		return (((((Module_xml_parser.f_mizXmlCharKind(ac)==1)||(Module_xml_parser.f_mizXmlCharKind(ac)==2))||(ac==95))||(ac==45))||(ac==38));
	}
	public static final Struct_MizXmlParser f_mizXmlMakeParser(String acontent) {
		final Object[] l4_$1 = Module_string.f_strSplit(acontent, "\n");
		final Struct_MizPos l5_$2 = (new Struct_MizPos(0, 0));
		final Struct_Vector l6_$3 = Module_vector.f_makeVector(8);
		final Func1<Object,String> l7_$0 = (Func1<Object, String>)(String al8_0) -> {
			return null;
		};
		return (new Struct_MizXmlParser(l4_$1, (-1), "", 1, Module_xml_parser.g_MizXTErr, "", l5_$2, "", Module_xml_parser.g_MizEEnd, l6_$3, l7_$0));
	}
	public static final Object f_mizXmlNextElementState(Struct_MizXmlParser ap) {
		final int l0_tk = (ap).f_nCurTokenKind;
		if ((l0_tk==Module_xml_parser.g_MizXTEOTX)) {
			((Field_nState)ap).set_nState(Module_xml_parser.g_MizEEnd);
			return null;
		} else {
			if ((l0_tk==Module_xml_parser.g_MizXTLT)) {
				((Field_nState)ap).set_nState(Module_xml_parser.g_MizEStart);
				Module_xml_parser.f_mizXmlGetToken(ap);
				if (((ap).f_nCurTokenKind==Module_xml_parser.g_MizXTID)) {
					Module_xml_parser.f_mizXmlOpenStartTag(ap);
					Module_xml_parser.f_mizXmlGetToken(ap);
					return Module_xml_parser.f_mizXmlParseAttrs(ap, true);
				} else {
					return Module_xml_parser.f_mizXmlErrorRecovery(6);
				}
			} else {
				if ((l0_tk==Module_xml_parser.g_MizXTEE)) {
					((Field_nState)ap).set_nState(Module_xml_parser.g_MizEEnd);
					return Module_xml_parser.f_mizXmlGetToken(ap);
				} else {
					if ((l0_tk==Module_xml_parser.g_MizXTET)) {
						((Field_nState)ap).set_nState(Module_xml_parser.g_MizEEnd);
						Module_xml_parser.f_mizXmlGetToken(ap);
						if (((ap).f_nCurTokenKind==Module_xml_parser.g_MizXTID)) {
							Module_xml_parser.f_mizXmlGetToken(ap);
							if (((ap).f_nCurTokenKind==Module_xml_parser.g_MizXTGT)) {
								return Module_xml_parser.f_mizXmlGetToken(ap);
							} else {
								return Module_xml_parser.f_mizXmlErrorRecovery(7);
							}
						} else {
							return Module_xml_parser.f_mizXmlErrorRecovery(8);
						}
					} else {
						return Module_xml_parser.f_mizXmlErrorRecovery(9);
					}
				}
			}
		}
	}
	public static final Object f_mizXmlNextTag(Struct_MizXmlParser ap) {
		final int l0_tk = (ap).f_nCurTokenKind;
		if ((l0_tk==Module_xml_parser.g_MizXTEOTX)) {
			((Field_nState)ap).set_nState(Module_xml_parser.g_MizEEnd);
			return null;
		} else {
			if ((l0_tk==Module_xml_parser.g_MizXTLT)) {
				((Field_nState)ap).set_nState(Module_xml_parser.g_MizEStart);
				Module_xml_parser.f_mizXmlGetToken(ap);
				if (((ap).f_nCurTokenKind==Module_xml_parser.g_MizXTID)) {
					Module_xml_parser.f_mizXmlOpenStartTag(ap);
					Module_xml_parser.f_mizXmlGetToken(ap);
					return Module_xml_parser.f_mizXmlParseAttrs(ap, false);
				} else {
					return Module_xml_parser.f_mizXmlErrorRecovery(6);
				}
			} else {
				if ((l0_tk==Module_xml_parser.g_MizXTEE)) {
					((Field_nState)ap).set_nState(Module_xml_parser.g_MizEEnd);
					return Module_xml_parser.f_mizXmlGetToken(ap);
				} else {
					if ((l0_tk==Module_xml_parser.g_MizXTET)) {
						((Field_nState)ap).set_nState(Module_xml_parser.g_MizEEnd);
						Module_xml_parser.f_mizXmlGetToken(ap);
						if (((ap).f_nCurTokenKind==Module_xml_parser.g_MizXTID)) {
							Module_xml_parser.f_mizXmlOpenStartTag(ap);
							Module_xml_parser.f_mizXmlGetToken(ap);
							if (((ap).f_nCurTokenKind==Module_xml_parser.g_MizXTGT)) {
								return Module_xml_parser.f_mizXmlGetToken(ap);
							} else {
								return Module_xml_parser.f_mizXmlErrorRecovery(7);
							}
						} else {
							return Module_xml_parser.f_mizXmlErrorRecovery(8);
						}
					} else {
						return Module_xml_parser.f_mizXmlErrorRecovery(9);
					}
				}
			}
		}
	}
	public static final Object f_mizXmlOpenStartTag(Struct_MizXmlParser ap) {
		((Field_nElName)ap).set_nElName((ap).f_nSpelling);
		Module_vector.f_clearVector((ap).f_nAttrVals);
		return (ap).f_onOpenTag.invoke((ap).f_nSpelling);
	}
	public static final Object f_mizXmlParseAttrs(Struct_MizXmlParser ap, boolean akeep) {
		final Reference<Boolean> l0_brk = ((Reference<Boolean>)(new Reference(false)));
		final Struct_MizXmlParser l4_p = ap;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l0_brk.value)&&((l4_p).f_nCurTokenKind!=Module_xml_parser.g_MizXTEOTX)));
		};
		final Struct_MizXmlParser l6_p = ap;
		final boolean l7_keep = akeep;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			final int l6_tk = (l6_p).f_nCurTokenKind;
			if ((l6_tk==Module_xml_parser.g_MizXTGT)) {
				Module_xml_parser.f_mizXmlGetToken(l6_p);
				l0_brk.value = ((Boolean)true);
				return null;
			} else {
				if ((l6_tk==Module_xml_parser.g_MizXTEE)) {
					l0_brk.value = ((Boolean)true);
					return null;
				} else {
					if ((l6_tk==Module_xml_parser.g_MizXTID)) {
						final String l7_attrName = (l6_p).f_nSpelling;
						Module_xml_parser.f_mizXmlGetToken(l6_p);
						if (((l6_p).f_nCurTokenKind==Module_xml_parser.g_MizXTEQ)) {
							Module_xml_parser.f_mizXmlGetToken(l6_p);
							if (((l6_p).f_nCurTokenKind==Module_xml_parser.g_MizXTQT)) {
								Module_xml_parser.f_mizXmlGetAttrValue(l6_p);
								if (l7_keep) {
									Module_vector.f_pushVector((l6_p).f_nAttrVals, (new Struct_MizXmlAttr(l7_attrName, (l6_p).f_nSpelling)));
								} else {
								}
								return Module_xml_parser.f_mizXmlGetToken(l6_p);
							} else {
								return Module_xml_parser.f_mizXmlErrorRecovery(3);
							}
						} else {
							return Module_xml_parser.f_mizXmlErrorRecovery(4);
						}
					} else {
						Module_xml_parser.f_mizXmlErrorRecovery(5);
						l0_brk.value = ((Boolean)true);
						return null;
					}
				}
			}
		};
		return Module_loop.f_while(l3_$1, l5_$2);
	}
	public static final Object f_mizXmlSkipBlanks(Struct_MizXmlParser ap) {
		final Struct_MizXmlParser l3_p = ap;
		final Func0<Boolean> l2_$0 = (Func0<Boolean>)() -> {
			boolean l5_$4;
			if (((l3_p).f_nCurCol<Native.strlen((l3_p).f_nLine))) {
				final int l6_ch = Module_xml_parser.f_mizCharCode1((l3_p).f_nLine, (l3_p).f_nCurCol);
				l5_$4=((l6_ch==32)||(l6_ch==9));
			} else {
				l5_$4=false;
			}
			return ((Boolean)l5_$4);
		};
		final Struct_MizXmlParser l5_p = ap;
		final Func0<Object> l4_$1 = (Func0<Object>)() -> {
			((Field_nCurCol)l5_p).set_nCurCol(((l5_p).f_nCurCol+1));
			return null;
		};
		return Module_loop.f_while(l2_$0, l4_$1);
	}
	public static final Object f_mizXmlSkipElement(Struct_MizXmlParser ap) {
		final Reference<Integer> l0_depth = ((Reference<Integer>)(new Reference(1)));
		final Struct_MizXmlParser l4_p = ap;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return ((Boolean)((((int)l0_depth.value)>0)&&((l4_p).f_nCurTokenKind!=Module_xml_parser.g_MizXTEOTX)));
		};
		final Struct_MizXmlParser l6_p = ap;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			Module_xml_parser.f_mizXmlNextTag(l6_p);
			if (((l6_p).f_nState==Module_xml_parser.g_MizEStart)) {
				l0_depth.value = ((Integer)(((int)l0_depth.value)+1));
				return null;
			} else {
				l0_depth.value = ((Integer)(((int)l0_depth.value)-1));
				return null;
			}
		};
		Module_loop.f_while(l3_$1, l5_$2);
		return Module_xml_parser.f_mizXmlNextElementState(ap);
	}
}
