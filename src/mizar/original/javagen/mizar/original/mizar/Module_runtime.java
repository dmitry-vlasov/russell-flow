// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_runtime {
	public static Reference<Boolean> g_localStorageEnabled;
	public static void init() {
		g_localStorageEnabled=((Reference<Boolean>)(new Reference(true)));
	}
	public static final String f_b2s(boolean ab) {
		return Module_dynamic.f_toString(ab);
	}
	public static final int f_countUntil(int astart, int amaxcount, Func1<Boolean,Integer> apredicate) {
		TAIL_CALL: for(;;) {
		if ((astart>=amaxcount)) {
			return amaxcount;
		} else {
			if (((boolean)apredicate.invoke(((Integer)astart)))) {
				return astart;
			} else {
				{
					final int l0___tmp = (astart+1);
					astart = l0___tmp;
					continue TAIL_CALL;
				}
			}
		}
	}
	}
	public static final Object f_fori(int astart, int aend, Func1<Object,Integer> afn) {
		TAIL_CALL: for(;;) {
		if ((astart<=aend)) {
			afn.invoke(((Integer)astart));
			{
				final int l0___tmp = (astart+1);
				astart = l0___tmp;
				continue TAIL_CALL;
			}
		} else {
			return null;
		}
	}
	}
	// The wrapper to a native: Native.generate
	public static final Object[] f_generate(int astart, int aend, Func1<Object,Integer> afn) {
		return Native.generate(astart, aend, afn);
	}
	public static final String f_getKeyValue(String akey, String adefaultValue) {
		if (((boolean)Module_runtime.g_localStorageEnabled.value)) {
			return Native.getKeyValue(akey, adefaultValue);
		} else {
			return adefaultValue;
		}
	}
	public static final Object f_idfn(Object ax) {
		return ax;
	}
	// The wrapper to a native: Native.fast_max
	public static final Object f_max(Object al0_0, Object al1_1) {
		return Native.fast_max(al0_0, al1_1);
	}
	public static final Object f_min(Object aa, Object ab) {
		if ((FlowRuntime.compareByValue(aa,ab)<=0)) {
			return aa;
		} else {
			return ab;
		}
	}
	public static final Object f_println(Object astr) {
		if (Module_securitymode.f_isLoggingEnabled()) {
			return Native.println(((Object)astr));
		} else {
			return null;
		}
	}
}
