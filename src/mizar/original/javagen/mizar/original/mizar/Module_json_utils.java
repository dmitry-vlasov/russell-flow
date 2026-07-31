// HASH COLLISIONS: YES
// timestamp: 1755953162000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_json_utils {
	public static final Struct f_json2stringM(Struct asrc) {
		Struct l0__tmp = asrc;
		switch (l0__tmp.getTypeId()) {
		case 5/*JsonBool*/: {
			final Struct_JsonBool l1__tmp = (Struct_JsonBool)l0__tmp;
			final boolean l2_b = l1__tmp.f_v;
			final boolean l3_b = (l1__tmp).f_v;
			return (new Struct_Some(Module_runtime.f_b2s(l3_b)));
		}
		case 6/*JsonDouble*/: {
			final Struct_JsonDouble l1__tmp = (Struct_JsonDouble)l0__tmp;
			final double l4_d = l1__tmp.f_value;
			final double l5_d = (l1__tmp).f_value;
			return (new Struct_Some(Module_string.f_d2s(l5_d)));
		}
		case 12/*JsonString*/: {
			final Struct_JsonString l1__tmp = (Struct_JsonString)l0__tmp;
			final String l6_s = l1__tmp.f_s;
			final String l7_s = (l1__tmp).f_s;
			return (new Struct_Some(l7_s));
		}
		default: {
			return ((Struct)SingletonStructs.str_None);
		}
		}
	}
	public static final Struct f_json2tree(Struct asrc, Func1<Struct,Struct> afnKey, Func1<Struct,Struct> afnValue) {
		TAIL_CALL: for(;;) {
		final Struct_Pair l0_err = (new Struct_Pair(false, Module_list.f_makeList()));
		Struct l1__tmp = asrc;
		switch (l1__tmp.getTypeId()) {
		case 4/*JsonArray*/: {
			final Struct_JsonArray l2__tmp = (Struct_JsonArray)l1__tmp;
			final Object[] l3_arr = l2__tmp.f_value;
			final Object[] l4_arr = (l2__tmp).f_value;
			final Func1<Struct,Struct> l6_fnValue = afnValue;
			final Func1<Struct,Struct> l7_fnKey = afnKey;
			final Func3<Struct_Pair,Struct_Pair, Struct, Struct> l5_trykv = (Func3<Struct_Pair, Struct_Pair, Struct, Struct>)(Struct_Pair ar, Struct ak, Struct av) -> {
				final Struct l6_gsymswitch0 = l6_fnValue.invoke(av);
				Struct l7__tmp = l6_gsymswitch0;
				switch (l7__tmp.getTypeId()) {
				case 80/*None*/: {
					return l0_err;
				}
				case 88/*Some*/: {
					final Struct_Some l8__tmp = (Struct_Some)l7__tmp;
					final Object l9_v2 = l8__tmp.f_value;
					final Object l10_v2 = (l8__tmp).f_value;
					final Struct l11_gsymswitch1 = l7_fnKey.invoke(ak);
					Struct l12__tmp = l11_gsymswitch1;
					switch (l12__tmp.getTypeId()) {
					case 80/*None*/: {
						return l0_err;
					}
					case 88/*Some*/: {
						final Struct_Some l13__tmp = (Struct_Some)l12__tmp;
						final Object l14_k2 = l13__tmp.f_value;
						final Object l15_k2 = (l13__tmp).f_value;
						return (new Struct_Pair(true, (new Struct_Cons((new Struct_Pair(l15_k2, l10_v2)), ((Struct)(ar).f_second)))));
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l12__tmp.getTypeName());
					}
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l7__tmp.getTypeName());
				}
			};
			final Struct_Pair l10_$9 = (new Struct_Pair(true, Module_list.f_makeList()));
			final Func2<Struct_Pair,Struct_Pair, Struct> l11_$8 = (Func2<Struct_Pair, Struct_Pair, Struct>)(Struct_Pair ar, Struct aa) -> {
				if (!((boolean)(ar).f_first)) {
					return ar;
				} else {
					Struct l12__tmp = aa;
					switch (l12__tmp.getTypeId()) {
					case 4/*JsonArray*/: {
						final Struct_JsonArray l13__tmp = (Struct_JsonArray)l12__tmp;
						final Object[] l14_arr2 = l13__tmp.f_value;
						final Object[] l15_arr2 = (l13__tmp).f_value;
						if ((Native.length(l15_arr2)<2)) {
							return l0_err;
						} else {
							return l5_trykv.invoke(ar, ((Struct)(l15_arr2[0])), ((Struct)(l15_arr2[1])));
						}
					}
					case 10/*JsonObject*/: {
						final Struct_JsonObject l13__tmp = (Struct_JsonObject)l12__tmp;
						final Object[] l16_arr2 = l13__tmp.f_members;
						final Object[] l17_arr2 = (l13__tmp).f_members;
						if ((Native.length(l17_arr2)<2)) {
							return l0_err;
						} else {
							final Struct l18_k = Module_json.f_getJsonFieldValue(l13__tmp, "key", ((Struct)SingletonStructs.str_JsonNull));
							final Struct l19_v = Module_json.f_getJsonFieldValue(l13__tmp, "value", ((Struct)SingletonStructs.str_JsonNull));
							if ((((Struct)l18_k).getTypeId() != 9&&((Struct)l19_v).getTypeId() != 9)) {
								return l5_trykv.invoke(ar, l18_k, l19_v);
							} else {
								return l5_trykv.invoke(ar, ((Struct)(((Struct_Pair)(l17_arr2[0]))).f_second), ((Struct)(((Struct_Pair)(l17_arr2[1]))).f_second));
							}
						}
					}
					default: {
						return l0_err;
					}
					}
				}
			};
			final Struct_Pair l12_r = ((Struct_Pair)Native.fold(l4_arr, l10_$9, ((Func2<Object,Object, Object>)(Func2)l11_$8)));
			if (!((boolean)(l12_r).f_first)) {
				return ((Struct)SingletonStructs.str_None);
			} else {
				return (new Struct_Some(Module_tree.f_pairs2tree(Native.list2array(((Struct)(l12_r).f_second)))));
			}
		}
		case 10/*JsonObject*/: {
			final Struct_JsonObject l2__tmp = (Struct_JsonObject)l1__tmp;
			final Object[] l13_arr = l2__tmp.f_members;
			final Object[] l14_arr = (l2__tmp).f_members;
			{
				final Struct l15___tmp = (new Struct_JsonArray(Native.map(l14_arr, ((Func1<Object,Object>)(Func1)Wrappers.w_secondOfPair))));
				asrc = l15___tmp;
				continue TAIL_CALL;
			}
		}
		default: {
			return ((Struct)SingletonStructs.str_None);
		}
		}
	}
	}
	public static final Struct f_json2treeS(Struct asrc, Func1<Struct,Struct> afnValue) {
		Struct l0__tmp = asrc;
		switch (l0__tmp.getTypeId()) {
		case 10/*JsonObject*/: {
			final Struct_JsonObject l1__tmp = (Struct_JsonObject)l0__tmp;
			final Object[] l2_arr = l1__tmp.f_members;
			final Object[] l3_arr = (l1__tmp).f_members;
			final Struct_Pair l6_$5 = (new Struct_Pair(true, Module_list.f_makeList()));
			final Func1<Struct,Struct> l8_fnValue = afnValue;
			final Func2<Struct_Pair,Struct_Pair, Struct_Pair> l7_$4 = (Func2<Struct_Pair, Struct_Pair, Struct_Pair>)(Struct_Pair ar, Struct_Pair aa) -> {
				if (!((boolean)(ar).f_first)) {
					return ar;
				} else {
					final Struct l8_gsymswitch2 = l8_fnValue.invoke(((Struct)(aa).f_second));
					Struct l9__tmp = l8_gsymswitch2;
					switch (l9__tmp.getTypeId()) {
					case 80/*None*/: {
						return (new Struct_Pair(false, Module_list.f_makeList()));
					}
					case 88/*Some*/: {
						final Struct_Some l10__tmp = (Struct_Some)l9__tmp;
						final Object l11_v = l10__tmp.f_value;
						final Object l12_v = (l10__tmp).f_value;
						return (new Struct_Pair(true, (new Struct_Cons((new Struct_Pair((aa).f_first, l12_v)), ((Struct)(ar).f_second)))));
					}
					default:
						throw new RuntimeException("Unexpected struct in switch: "+l9__tmp.getTypeName());
					}
				}
			};
			final Struct_Pair l9_r = ((Struct_Pair)Native.fold(l3_arr, l6_$5, ((Func2<Object,Object, Object>)(Func2)l7_$4)));
			if (!((boolean)(l9_r).f_first)) {
				return ((Struct)SingletonStructs.str_None);
			} else {
				return (new Struct_Some(Module_tree.f_pairs2tree(Native.list2array(((Struct)(l9_r).f_second)))));
			}
		}
		default: {
			return Module_json_utils.f_json2tree(asrc, Wrappers.w_json2stringM, afnValue);
		}
		}
	}
}
