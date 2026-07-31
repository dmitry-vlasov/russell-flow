// HASH COLLISIONS: YES
// timestamp: 1776854068000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_string {
	public static final String f_cloneString(String as) {
		return as;
	}
	public static final String f_d2s(double ad) {
		return FlowRuntime.doubleToString(ad);
	}
	public static final Struct f_doStrSplit(String as, String asep, int astart, Struct aacc) {
		TAIL_CALL: for(;;) {
		final int l0_l = Native.strlen(as);
		final int l1_i = Native.strRangeIndexOf(as, asep, astart, l0_l);
		if ((l1_i<0)) {
			return (new Struct_Cons(Native.substring(as, astart, l0_l), aacc));
		} else {
			final String l2_part = Native.substring(as, astart, (l1_i-astart));
			final int l3_sepLen = Native.strlen(asep);
			{
				final int l4___tmp = (l1_i+l3_sepLen);
				final Struct l5___tmp = (new Struct_Cons(l2_part, aacc));
				astart = l4___tmp;
				aacc = l5___tmp;
				continue TAIL_CALL;
			}
		}
	}
	}
	public static final String f_i2s(int ai) {
		return Integer.toString((int)(ai));
	}
	public static final String f_ltrim(String as) {
		TAIL_CALL: for(;;) {
		final int l0_l = Native.strlen(as);
		if ((l0_l==0)) {
			return as;
		} else {
			final int l1_first = Native.getCharCodeAt(as, 0);
			if (((l1_first==32)||(l1_first==160))) {
				{
					final String l2___tmp = Native.substring(as, 1, (l0_l-1));
					as = l2___tmp;
					continue TAIL_CALL;
				}
			} else {
				return as;
			}
		}
	}
	}
	public static final int f_parseHex(String as) {
		final Func2<Integer,Integer, Integer> l1_$0 = (Func2<Integer, Integer, Integer>)(Integer aacc, Integer acode) -> {
			final Func1<Integer,Integer> l2_subtract = (Func1<Integer, Integer>)(Integer aoffset) -> {
				return ((Integer)((((int)aacc)*16)+(((int)acode)-((int)aoffset))));
			};
			if (((48<=((int)acode))&&(((int)acode)<=57))) {
				return l2_subtract.invoke(((Integer)48));
			} else {
				if (((65<=((int)acode))&&(((int)acode)<=70))) {
					return l2_subtract.invoke(((Integer)55));
				} else {
					if (((97<=((int)acode))&&(((int)acode)<=102))) {
						return l2_subtract.invoke(((Integer)87));
					} else {
						return aacc;
					}
				}
			}
		};
		return ((int)Module_string.f_stringFold(as, 0, ((Func2<Object,Object, Integer>)(Func2)l1_$0)));
	}
	public static final String f_rtrim(String as) {
		TAIL_CALL: for(;;) {
		final int l0_l = Native.strlen(as);
		if ((l0_l==0)) {
			return as;
		} else {
			final int l1_last = Native.getCharCodeAt(as, (l0_l-1));
			if (((l1_last==32)||(l1_last==160))) {
				{
					final String l2___tmp = Native.substring(as, 0, (l0_l-1));
					as = l2___tmp;
					continue TAIL_CALL;
				}
			} else {
				return as;
			}
		}
	}
	}
	public static final boolean f_startsWith(String as, String astart) {
		final int l0_sl = Native.strlen(astart);
		if ((l0_sl>Native.strlen(as))) {
			return false;
		} else {
			return (Native.substring(as, 0, l0_sl)).equals(astart);
		}
	}
	public static final boolean f_strContains(String astr, String asubstr) {
		return (Native.strIndexOf(astr, asubstr)>=0);
	}
	// The wrapper to a native: Native.strRangeIndexOf
	public static final int f_strRangeIndexOf(String astr, String asubstr, int astart, int aend) {
		return Native.strRangeIndexOf(astr, asubstr, astart, aend);
	}
	public static final String f_strRight(String as, int aindex) {
		final int l0_l = Native.strlen(as);
		if ((aindex>=l0_l)) {
			return "";
		} else {
			return Native.substring(as, aindex, (l0_l-aindex));
		}
	}
	public static final Object[] f_strSplit(String as, String asep) {
		if ((asep).equals("")) {
			return (new Object[] { as });
		} else {
			return Native.list2array(Module_string.f_doStrSplit(as, asep, 0, Module_list.f_makeList()));
		}
	}
	public static final Object f_stringFold(String as, Object aa, Func2<Object,Object, Integer> af) {
		final int l2_$1 = (Native.strlen(as)-1);
		final Func2<Object,Object, Integer> l4_f = af;
		final String l5_s = as;
		final Func2<Object,Object, Integer> l3_$0 = (Func2<Object, Object, Integer>)(Object aa2, Integer ai) -> {
			return l4_f.invoke(aa2, ((Integer)Native.getCharCodeAt(l5_s, ((int)ai))));
		};
		return Module_array.f_foldRange(0, l2_$1, aa, l3_$0);
	}
	public static final String f_trim(String as) {
		return Module_string.f_rtrim(Module_string.f_ltrim(as));
	}
	public static final int f_trunc(double ad) {
		return ((int)ad);
	}
}
