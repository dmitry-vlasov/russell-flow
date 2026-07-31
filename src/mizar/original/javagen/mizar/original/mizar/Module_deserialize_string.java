// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_deserialize_string {
	public static final Struct_Pair f_deserializeRestOfString2(String as, int astart) {
		return Module_deserialize_string.f_deserializeRestOfStringTail(as, astart);
	}
	public static final Struct_Pair f_deserializeRestOfStringTail(String as, int astart) {
		final Struct_Pair l0_rv = Module_deserialize_string.f_deserializeRestOfStringTail2(as, Module_list.f_makeList(), astart, (-1));
		return (new Struct_Pair(Module_string.f_cloneString(Native.list2string(((Struct)(l0_rv).f_first))), (l0_rv).f_second));
	}
	public static final Struct_Pair f_deserializeRestOfStringTail2(String as, Struct aacc, int astart, int aquote0) {
		TAIL_CALL: for(;;) {
		final int l0_strLength = Native.strlen(as);
		int l1_quote;
		if ((astart>aquote0)) {
			l1_quote=Native.strRangeIndexOf(as, "\"", astart, l0_strLength);
		} else {
			l1_quote=aquote0;
		}
		final int l2_escape = Native.strRangeIndexOf(as, "\\", astart, l1_quote);
		if (((l2_escape!=(-1))&&(l2_escape<l1_quote))) {
			final String l3_first = Native.substring(as, astart, (l2_escape-astart));
			final int l4_escapedCharCode = Native.getCharCodeAt(as, (l2_escape+1));
			if ((l4_escapedCharCode==34)) {
				{
					final Struct l5___tmp = (new Struct_Cons("\"", (new Struct_Cons(l3_first, aacc))));
					final int l6___tmp = (l2_escape+2);
					final int l7___tmp = l1_quote;
					aacc = l5___tmp;
					astart = l6___tmp;
					aquote0 = l7___tmp;
					continue TAIL_CALL;
				}
			} else {
				if ((l4_escapedCharCode==92)) {
					{
						final Struct l8___tmp = (new Struct_Cons("\\", (new Struct_Cons(l3_first, aacc))));
						final int l9___tmp = (l2_escape+2);
						final int l10___tmp = l1_quote;
						aacc = l8___tmp;
						astart = l9___tmp;
						aquote0 = l10___tmp;
						continue TAIL_CALL;
					}
				} else {
					if ((l4_escapedCharCode==110)) {
						{
							final Struct l11___tmp = (new Struct_Cons("\n", (new Struct_Cons(l3_first, aacc))));
							final int l12___tmp = (l2_escape+2);
							final int l13___tmp = l1_quote;
							aacc = l11___tmp;
							astart = l12___tmp;
							aquote0 = l13___tmp;
							continue TAIL_CALL;
						}
					} else {
						if ((l4_escapedCharCode==117)) {
							final String l14_hex = Native.substring(as, (l2_escape+2), 4);
							final int l15_code = Module_string.f_parseHex(l14_hex);
							{
								final Struct l16___tmp = (new Struct_Cons(Native.fromCharCode(l15_code), (new Struct_Cons(l3_first, aacc))));
								final int l17___tmp = (l2_escape+6);
								final int l18___tmp = l1_quote;
								aacc = l16___tmp;
								astart = l17___tmp;
								aquote0 = l18___tmp;
								continue TAIL_CALL;
							}
						} else {
							if ((l4_escapedCharCode==120)) {
								final String l19_hex = Native.substring(as, (l2_escape+2), 2);
								final int l20_code = Module_string.f_parseHex(l19_hex);
								{
									final Struct l21___tmp = (new Struct_Cons(Native.fromCharCode(l20_code), (new Struct_Cons(l3_first, aacc))));
									final int l22___tmp = (l2_escape+4);
									final int l23___tmp = l1_quote;
									aacc = l21___tmp;
									astart = l22___tmp;
									aquote0 = l23___tmp;
									continue TAIL_CALL;
								}
							} else {
								String l24_descaped;
								if ((l4_escapedCharCode==116)) {
									l24_descaped="\t";
								} else {
									if ((l4_escapedCharCode==114)) {
										l24_descaped="\r";
									} else {
										l24_descaped=Native.fromCharCode(l4_escapedCharCode);
									}
								}
								{
									final Struct l25___tmp = (new Struct_Cons(l24_descaped, (new Struct_Cons(l3_first, aacc))));
									final int l26___tmp = (l2_escape+2);
									final int l27___tmp = l1_quote;
									aacc = l25___tmp;
									astart = l26___tmp;
									aquote0 = l27___tmp;
									continue TAIL_CALL;
								}
							}
						}
					}
				}
			}
		} else {
			if ((l1_quote==(-1))) {
				return (new Struct_Pair((new Struct_Cons(((astart>=l0_strLength)?"":Native.substring(as, astart, (l0_strLength-astart))), aacc)), l0_strLength));
			} else {
				return (new Struct_Pair((new Struct_Cons(Native.substring(as, astart, (l1_quote-astart)), aacc)), (l1_quote+1)));
			}
		}
	}
	}
}
