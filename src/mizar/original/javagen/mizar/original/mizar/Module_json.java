// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_json {
	public static final Struct_ParsingAcc f_doParseJson(String ajson, int ajsonLength, int astart) {
		TAIL_CALL: for(;;) {
		final int l0_charCode = Native.getCharCodeAt(ajson, astart);
		if (((((l0_charCode==32)||(l0_charCode==9))||(l0_charCode==10))||(l0_charCode==13))) {
			{
				final int l1___tmp = (astart+1);
				astart = l1___tmp;
				continue TAIL_CALL;
			}
		} else {
			if ((l0_charCode==91)) {
				return Module_json.f_parseJsonArray2(ajson, ajsonLength, (astart+1), Module_list.f_makeList());
			} else {
				if ((l0_charCode==123)) {
					return Module_json.f_parseJsonObject2(ajson, ajsonLength, (astart+1), Module_list.f_makeList());
				} else {
					if ((l0_charCode==34)) {
						final Struct_Pair l2_v = Module_deserialize_string.f_deserializeRestOfString2(ajson, (astart+1));
						return (new Struct_ParsingAcc((new Struct_JsonString(((String)(l2_v).f_first))), (astart+1), ((int)(l2_v).f_second), ((int)(l2_v).f_second)));
					} else {
						if (((l0_charCode==110)&&(Native.substring(ajson, astart, 4)).equals("null"))) {
							return (new Struct_ParsingAcc(SingletonStructs.str_JsonNull, astart, (astart+4), (astart+4)));
						} else {
							if (((l0_charCode==116)&&(Native.substring(ajson, astart, 4)).equals("true"))) {
								return (new Struct_ParsingAcc((new Struct_JsonBool(true)), astart, (astart+4), (astart+4)));
							} else {
								if (((l0_charCode==102)&&(Native.substring(ajson, astart, 5)).equals("false"))) {
									return (new Struct_ParsingAcc((new Struct_JsonBool(false)), astart, (astart+5), (astart+5)));
								} else {
									final Struct_Pair l3_v = Module_json.f_parseJsonDouble2(ajson, ajsonLength, astart);
									return (new Struct_ParsingAcc((l3_v).f_first, astart, ((int)(l3_v).f_second), ((int)(l3_v).f_second)));
								}
							}
						}
					}
				}
			}
		}
	}
	}
	public static final Struct f_findObjectByName(Object[] aobjects, String akey, boolean aignoreCase, Struct adefaultValue) {
		String l0_key2;
		if (aignoreCase) {
			l0_key2=Native.toLowerCase(akey);
		} else {
			l0_key2=akey;
		}
		final boolean l3_ignoreCase = aignoreCase;
		final String l4_key2 = l0_key2;
		final Func1<Boolean,Struct_Pair> l2_$1 = (Func1<Boolean, Struct_Pair>)(Struct_Pair ao) -> {
			String l3_k;
			if (l3_ignoreCase) {
				l3_k=Native.toLowerCase(((String)(ao).f_first));
			} else {
				l3_k=((String)(ao).f_first);
			}
			return (l3_k).equals(l4_key2);
		};
		return ((Struct)(((Struct_Pair)Module_array.f_findDef(aobjects, ((Func1<Boolean,Object>)(Func1)l2_$1), (new Struct_Pair(l0_key2, adefaultValue))))).f_second);
	}
	public static final Struct f_getJsonFieldValue(Struct ajson, String afield, Struct adef) {
		final Object[] l0_objects = Module_json.f_getJsonObjectValue(ajson, (new Object[] { (new Struct_Pair(afield, adef)) }));
		return Module_json.f_findObjectByName(l0_objects, afield, false, adef);
	}
	public static final Object[] f_getJsonObjectValue(Struct ajson, Object[] adef) {
		Struct l0__tmp = ajson;
		switch (l0__tmp.getTypeId()) {
		case 10/*JsonObject*/: {
			final Struct_JsonObject l1__tmp = (Struct_JsonObject)l0__tmp;
			final Object[] l2_members = l1__tmp.f_members;
			final Object[] l3_members = (l1__tmp).f_members;
			return l3_members;
		}
		default: {
			return adef;
		}
		}
	}
	// The wrapper to a native: Native.parseJson
	public static final Struct f_parseJson(String ajson) {
		return Native.parseJson(ajson);
	}
	public static final Struct_ParsingAcc f_parseJsonArray2(String ajson, int ajsonLength, int astart, Struct aacc) {
		TAIL_CALL: for(;;) {
		final int l0_fin = ((int)(Module_json.f_skipJson2(ajson, ajsonLength, astart, " ")).f_second);
		Struct_Pair l1_head;
		Struct l2__tmp = aacc;
		switch (l2__tmp.getTypeId()) {
		case 1/*EmptyList*/: {
			l1_head=(new Struct_Pair(l0_fin, l0_fin));
			break;
		}
		case 0/*Cons*/: {
			final Struct_Cons l3__tmp = (Struct_Cons)l2__tmp;
			final Struct l4___ = ((Struct)(l3__tmp).f_head);
			final Struct l5___ = (l3__tmp).f_tail;
			l1_head=Module_json.f_skipJson2(ajson, ajsonLength, astart, ",");
			break;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
		}
		if (((Native.getCharCodeAt(ajson, l0_fin)==93)||(Native.getCharCodeAt(ajson, ((int)(l1_head).f_second))==93))) {
			return (new Struct_ParsingAcc((new Struct_JsonArray(Native.list2array(aacc))), astart, (l0_fin+1), (l0_fin+1)));
		} else {
			if (((((int)(l1_head).f_second)>=ajsonLength)||(((int)(l1_head).f_first)<0))) {
				return (new Struct_ParsingAcc((new Struct_JsonArray(Native.list2array(aacc))), astart, astart, l0_fin));
			} else {
				final Struct_ParsingAcc l6_value = Module_json.f_doParseJson(ajson, ajsonLength, ((int)(l1_head).f_second));
				if (((l6_value).f_start==(l6_value).f_finish)) {
					return (new Struct_ParsingAcc((new Struct_JsonArray(Native.list2array(aacc))), astart, astart, (l6_value).f_finish));
				} else {
					{
						final int l7___tmp = (l6_value).f_finish;
						final Struct l8___tmp = (new Struct_Cons((l6_value).f_data, aacc));
						astart = l7___tmp;
						aacc = l8___tmp;
						continue TAIL_CALL;
					}
				}
			}
		}
	}
	}
	public static final Struct_Pair f_parseJsonDouble2(String as, int astringLength, int astart) {
		final String l2_s = as;
		final Func1<Boolean,Integer> l1_$0 = (Func1<Boolean, Integer>)(Integer ai) -> {
			final int l2_c = Native.getCharCodeAt(l2_s, ((int)ai));
			return ((Boolean)!(((((((48<=l2_c)&&(l2_c<=57))||(l2_c==46))||(l2_c==45))||(l2_c==101))||(l2_c==69))||(l2_c==43)));
		};
		final int l3_end = Module_runtime.f_countUntil(astart, astringLength, l1_$0);
		return (new Struct_Pair((new Struct_JsonDouble(Module_math.f_s2d(Native.substring(as, astart, (l3_end-astart))))), l3_end));
	}
	public static final Struct_ParsingAcc f_parseJsonObject2(String ajson, int ajsonLength, int astart, Struct aacc) {
		TAIL_CALL: for(;;) {
		final int l0_fin = ((int)(Module_json.f_skipJson2(ajson, ajsonLength, astart, " ")).f_second);
		Struct_Pair l1_head;
		Struct l2__tmp = aacc;
		switch (l2__tmp.getTypeId()) {
		case 1/*EmptyList*/: {
			l1_head=(new Struct_Pair(l0_fin, l0_fin));
			break;
		}
		case 0/*Cons*/: {
			final Struct_Cons l3__tmp = (Struct_Cons)l2__tmp;
			final Struct_Pair l4___ = ((Struct_Pair)(l3__tmp).f_head);
			final Struct l5___ = (l3__tmp).f_tail;
			l1_head=Module_json.f_skipJson2(ajson, ajsonLength, astart, ",");
			break;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l2__tmp.getTypeName());
		}
		final int l6_charCode = Native.getCharCodeAt(ajson, ((int)(l1_head).f_second));
		if ((Native.getCharCodeAt(ajson, l0_fin)==125)) {
			return (new Struct_ParsingAcc((new Struct_JsonObject(Native.list2array(aacc))), astart, (l0_fin+1), (l0_fin+1)));
		} else {
			if (((((int)(l1_head).f_second)>=ajsonLength)||(((int)(l1_head).f_first)<0))) {
				return (new Struct_ParsingAcc((new Struct_JsonObject(Native.list2array(aacc))), astart, astart, l0_fin));
			} else {
				Struct_Pair l7_key;
				if ((l6_charCode==34)) {
					final Struct_Pair l8_v = Module_deserialize_string.f_deserializeRestOfString2(ajson, (((int)(l1_head).f_second)+1));
					if ((((int)(l8_v).f_second)==(((int)(l1_head).f_second)+1))) {
						l7_key=(new Struct_Pair("", (((int)(l1_head).f_second)+1)));
					} else {
						l7_key=l8_v;
					}
				} else {
					l7_key=(new Struct_Pair("", (((int)(l1_head).f_second)+1)));
				}
				final Struct_Pair l9_afterColon = Module_json.f_skipJson2(ajson, ajsonLength, ((int)(l7_key).f_second), ":");
				final Struct_ParsingAcc l10_value = Module_json.f_doParseJson(ajson, ajsonLength, ((int)(l9_afterColon).f_second));
				final Struct_Cons l11_nacc = (new Struct_Cons((new Struct_Pair((l7_key).f_first, (l10_value).f_data)), aacc));
				if ((((((int)(l7_key).f_second)==(((int)(l1_head).f_second)+1))||(((int)(l9_afterColon).f_first)<0))||((l10_value).f_start==(l10_value).f_finish))) {
					return (new Struct_ParsingAcc((new Struct_JsonObject(Native.list2array(l11_nacc))), astart, astart, (l10_value).f_finish));
				} else {
					{
						final int l12___tmp = (l10_value).f_finish;
						final Struct l13___tmp = l11_nacc;
						astart = l12___tmp;
						aacc = l13___tmp;
						continue TAIL_CALL;
					}
				}
			}
		}
	}
	}
	public static final Struct f_parseJsonSafe(String ajson) {
		final Struct_ParsingAcc l0_v = Module_json.f_doParseJson(ajson, Native.strlen(ajson), 0);
		if (((l0_v).f_start==(l0_v).f_finish)) {
			return (new Struct_JsonDouble(0.0));
		} else {
			return ((Struct)(l0_v).f_data);
		}
	}
	public static final Struct_Pair f_skipJson2(String ajson, int ajsonLength, int astart, String achar) {
		TAIL_CALL: for(;;) {
		if ((astart>=ajsonLength)) {
			return (new Struct_Pair((-1), astart));
		} else {
			final int l0_headCharCode = Native.getCharCodeAt(ajson, astart);
			if ((Native.fromCharCode(l0_headCharCode)).equals(achar)) {
				final Struct_Pair l1_v = Module_json.f_skipJson2(ajson, ajsonLength, (astart+1), " ");
				return (new Struct_Pair(astart, (l1_v).f_second));
			} else {
				if (((((l0_headCharCode==32)||(l0_headCharCode==9))||(l0_headCharCode==10))||(l0_headCharCode==13))) {
					{
						final int l2___tmp = (astart+1);
						astart = l2___tmp;
						continue TAIL_CALL;
					}
				} else {
					return (new Struct_Pair((-1), astart));
				}
			}
		}
	}
	}
}
