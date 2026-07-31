// HASH COLLISIONS: YES
// timestamp: 1763142043000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_array {
	public static final Object[] f_arrayPush(Object[] aarray, Object avalue) {
		return Native.replace(aarray, Native.length(aarray), avalue);
	}
	public static final Object[] f_concat3(Object[] aa1, Object[] aa2, Object[] aa3) {
		if ((Native.length(aa1)<Native.length(aa3))) {
			return Native.concat(Native.concat(aa1, aa2), aa3);
		} else {
			return Native.concat(aa1, Native.concat(aa2, aa3));
		}
	}
	public static final boolean f_contains(Object[] aa, Object av) {
		return (Native.elemIndex(aa, av, (-1))!=(-1));
	}
	// The wrapper to a native: Native.elemIndex
	public static final int f_elemIndex(Object[] aa, Object aelem, int aillegal) {
		return Native.elemIndex(aa, aelem, aillegal);
	}
	// The wrapper to a native: Native.exists
	public static final boolean f_exists(Object[] aa, Func1<Boolean,Object> afn) {
		return Native.exists(aa, afn);
	}
	public static final boolean f_existsIndex(Object[] aa, int ai) {
		return ((ai>=0)&&(ai<Native.length(aa)));
	}
	public static final Struct f_find(Object[] aa, Func1<Boolean,Object> afn) {
		final Func1<Boolean,Object> l2_fn = afn;
		final Func2<Boolean,Integer, Object> l1_$0 = (Func2<Boolean, Integer, Object>)(Integer ai, Object ae) -> {
			return l2_fn.invoke(ae);
		};
		final int l3_foundIndex = Native.iteriUntil(aa, l1_$0);
		if ((l3_foundIndex==Native.length(aa))) {
			return ((Struct)SingletonStructs.str_None);
		} else {
			return (new Struct_Some(((Object)(aa[l3_foundIndex]))));
		}
	}
	public static final Object f_findDef(Object[] aa, Func1<Boolean,Object> afn, Object adef) {
		return Module_maybe.f_either(Module_array.f_find(aa, afn), adef);
	}
	public static final Object f_foldRange(int astart, int aend, Object aacc, Func2<Object,Object, Integer> afn) {
		TAIL_CALL: for(;;) {
		if ((astart<=aend)) {
			{
				final int l0___tmp = (astart+1);
				final Object l1___tmp = afn.invoke(aacc, ((Integer)astart));
				astart = l0___tmp;
				aacc = l1___tmp;
				continue TAIL_CALL;
			}
		} else {
			return aacc;
		}
	}
	}
	public static final Object[] f_insertArray(Object[] aa, int aindex, Object av) {
		return Module_array.f_insertArray2(aa, aindex, (new Object[] { av }));
	}
	public static final Object[] f_insertArray2(Object[] aa, int aindex, Object[] av) {
		return Module_array.f_concat3(Native.subrange(aa, 0, aindex), av, Native.subrange(aa, aindex, (Native.length(aa)-aindex)));
	}
	public static final Object f_refArrayPush(Reference<Object[]> arefArray, Object aelem) {
		arefArray.value = Module_array.f_arrayPush(arefArray.value, aelem);
		return null;
	}
	public static final Object[] f_removeIndex(Object[] aa, int aindex) {
		if (Module_array.f_existsIndex(aa, aindex)) {
			return Native.concat(Native.subrange(aa, 0, aindex), Native.subrange(aa, (aindex+1), ((Native.length(aa)-aindex)-1)));
		} else {
			return aa;
		}
	}
}
