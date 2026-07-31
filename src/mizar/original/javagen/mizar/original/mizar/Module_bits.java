// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_bits {
	public static Object[] g_pots2;
	public static void init() {
		final Func1<Integer,Integer> l1_$0 = (Func1<Integer, Integer>)(Integer ai) -> {
			return ((Integer)Module_math.f_pow(2, ((int)ai)));
		};
		g_pots2=Native.generate(0, 32, ((Func1<Object,Integer>)(Func1)l1_$0));
	}
	// The wrapper to a native: Native.bitShl
	public static final int f_bitShl(int aa, int ab) {
		return Native.bitShl(aa, ab);
	}
	public static final int f_pot2(int an) {
		return ((int)(Module_bits.g_pots2[an]));
	}
}
