// HASH COLLISIONS: YES
// timestamp: 1749295327000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_math {
	public static double g_defaultMaxAbsoluteError;
	public static double g_defaultMaxRelativeError;
	public static double g_doubleMax;
	public static double g_doubleMin;
	public static void init() {
		g_defaultMaxAbsoluteError=0.000000000001;
		g_defaultMaxRelativeError=0.0000000001;
		g_doubleMax=17976931348623145000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0;
		g_doubleMin=(-Module_math.g_doubleMax);
	}
	public static final double f_abs(double ax) {
		if ((ax<0.0)) {
			return (-ax);
		} else {
			return ax;
		}
	}
	public static final int f_b2i(boolean ab) {
		if (ab) {
			return 1;
		} else {
			return 0;
		}
	}
	public static final double f_dpow(double ax, double am) {
		final Struct l2_$1 = Module_math.f_dpow2(ax, am);
		final double l4_m = am;
		final double l5_x = ax;
		final Func0<Double> l3_$0 = (Func0<Double>)() -> {
			return ((Double)(Native.exp((l4_m*Native.log(Module_math.f_abs(l5_x))))*(((l5_x<0.0)&&((Module_math.f_floor(Module_math.f_abs(l4_m))%2)==1))?(-1.0):1.0)));
		};
		return ((double)Module_maybe.f_eitherFn(l2_$1, Wrappers.w_idfn, ((Func0<Object>)(Func0)l3_$0)));
	}
	public static final Struct f_dpow2(double ax, double am) {
		final Struct l3_$2 = Module_math.f_tryDouble2Int(am);
		final double l5_x = ax;
		final Func1<Struct,Integer> l4_$0 = (Func1<Struct, Integer>)(Integer ai) -> {
			return Module_math.f_pow2(l5_x, ((int)ai));
		};
		final double l7_x = ax;
		final double l8_m = am;
		final Func0<Struct> l6_$1 = (Func0<Struct>)() -> {
			if ((l7_x<0.0)) {
				final double l7_powerRoot = (1.0/l8_m);
				final int l8_powerRootI = Module_string.f_trunc(l7_powerRoot);
				if (((Module_math.f_i2d(l8_powerRootI)==l7_powerRoot)&&((Module_math.f_iabs(l8_powerRootI)%2)==1))) {
					return (new Struct_Some((Native.exp((l8_m*Native.log(Module_math.f_abs(l7_x))))*(-1.0))));
				} else {
					return ((Struct)SingletonStructs.str_None);
				}
			} else {
				if (((l7_x==0.0)&&(l8_m>0.0))) {
					return (new Struct_Some(0.0));
				} else {
					if (((l7_x==0.0)&&(l8_m<=0.0))) {
						return ((Struct)SingletonStructs.str_None);
					} else {
						return (new Struct_Some(Native.exp((l8_m*Native.log(l7_x)))));
					}
				}
			}
		};
		return ((Struct)Module_maybe.f_eitherFn(l3_$2, ((Func1<Object,Object>)(Func1)l4_$0), ((Func0<Object>)(Func0)l6_$1)));
	}
	public static final boolean f_equalDoubles(double aa, double ab) {
		return Module_math.f_equalRelativeOrAbsolute(aa, ab, Module_math.g_defaultMaxRelativeError, Module_math.g_defaultMaxAbsoluteError);
	}
	public static final boolean f_equalRelative(double aa, double ab, double amaxRelativeError) {
		final double l0_x = Module_math.f_abs(aa);
		final double l1_y = Module_math.f_abs(ab);
		double l2_relativeError;
		if ((l1_y>l0_x)) {
			l2_relativeError=(Module_math.f_abs((ab-aa))/l1_y);
		} else {
			if ((l0_x!=0.0)) {
				l2_relativeError=(Module_math.f_abs((aa-ab))/l0_x);
			} else {
				l2_relativeError=l1_y;
			}
		}
		return (l2_relativeError<=amaxRelativeError);
	}
	public static final boolean f_equalRelativeOrAbsolute(double aa, double ab, double amaxRelativeError, double amaxAbsoluteError) {
		if (((aa==ab)||(Module_math.f_abs((aa-ab))<amaxAbsoluteError))) {
			return true;
		} else {
			return Module_math.f_equalRelative(aa, ab, amaxRelativeError);
		}
	}
	public static final int f_floor(double ad) {
		return Module_string.f_trunc(((ad>=0.0)?ad:((((-ad)-Module_math.f_i2d(Module_string.f_trunc((-ad))))>0.0)?(ad-1.0):ad)));
	}
	public static final int f_getDigitCode(int ac) {
		if (((48<=ac)&&(ac<=57))) {
			return (ac-48);
		} else {
			return (-1);
		}
	}
	public static final double f_i2d(int ai) {
		return ((double)ai);
	}
	public static final int f_iabs(int ax) {
		if ((ax<0)) {
			return (-ax);
		} else {
			return ax;
		}
	}
	public static final boolean f_isNanOrInf(double ad) {
		return (((ad==(2.0*ad))&&(ad!=0.0))||(ad!=ad));
	}
	public static final int f_pow(int ai, int an) {
		if ((an>0)) {
			final int l0_n2 = Module_math.f_pow(ai, (an/2));
			if (((an%2)==0)) {
				return (l0_n2*l0_n2);
			} else {
				return ((l0_n2*l0_n2)*ai);
			}
		} else {
			return 1;
		}
	}
	public static final Struct f_pow2(double ax, int an) {
		if ((ax==0.0)) {
			if ((an<=0)) {
				return ((Struct)SingletonStructs.str_None);
			} else {
				return (new Struct_Some(0.0));
			}
		} else {
			if ((an==0)) {
				return (new Struct_Some(1.0));
			} else {
				if ((an>0)) {
					return (new Struct_Some(Module_math.f_pow2helper(ax, an, true)));
				} else {
					return (new Struct_Some((1.0/Module_math.f_pow2helper(ax, (-an), false))));
				}
			}
		}
	}
	public static final double f_pow2helper(double ax, int an, boolean aorder) {
		if ((an==0)) {
			return 1.0;
		} else {
			final double l0_x2 = Module_math.f_pow2helper(ax, (an/2), aorder);
			if (((an%2)==0)) {
				return (l0_x2*l0_x2);
			} else {
				if (aorder) {
					return ((ax*l0_x2)*l0_x2);
				} else {
					return ((l0_x2*l0_x2)*ax);
				}
			}
		}
	}
	public static final double f_s2d(String as) {
		final int l0_n = Native.strlen(as);
		if ((l0_n==0)) {
			return 0.0;
		} else {
			final boolean l1_negative = (Native.getCharCodeAt(as, 0)==45);
			if (l1_negative) {
				return (-Module_math.f_s2dintWithError(as, 1, l0_n, 0.0, Module_math.g_doubleMin));
			} else {
				return Module_math.f_s2dintWithError(as, 0, l0_n, 0.0, Module_math.g_doubleMin);
			}
		}
	}
	public static final double f_s2dexp(String as, int ai, int an, double aacc) {
		if ((ai<an)) {
			final int l0_c = Native.getCharCodeAt(as, ai);
			final boolean l1_expNeg = (l0_c==45);
			final boolean l2_expPos = (l0_c==43);
			final int l3_exponent = Module_math.f_subStr2i(as, (ai+Module_math.f_b2i((l1_expNeg||l2_expPos))), an, 0);
			if (l1_expNeg) {
				return (aacc*Module_math.f_dpow(10.0, Module_math.f_i2d((-l3_exponent))));
			} else {
				return (aacc*Module_math.f_dpow(10.0, Module_math.f_i2d(l3_exponent)));
			}
		} else {
			return aacc;
		}
	}
	public static final Struct_Pair f_s2dfrac(String as, int ai, int an, double aacc, double afracacc) {
		if ((ai<an)) {
			final int l0_c = Native.getCharCodeAt(as, ai);
			final int l1_d = Module_math.f_getDigitCode(l0_c);
			if ((l1_d!=(-1))) {
				final Struct_Pair l2_fracres = Module_math.f_s2dfrac(as, (ai+1), an, ((aacc*10.0)+Module_math.f_i2d(l1_d)), (afracacc*10.0));
				return (new Struct_Pair((l2_fracres).f_first, (l2_fracres).f_second));
			} else {
				if (((l0_c==69)||(l0_c==101))) {
					return (new Struct_Pair(Module_math.f_s2dexp(as, (ai+1), an, (aacc/afracacc)), 1.0));
				} else {
					return (new Struct_Pair(aacc, afracacc));
				}
			}
		} else {
			return (new Struct_Pair(aacc, afracacc));
		}
	}
	public static final double f_s2dintWithError(String as, int ai, int an, double aacc, double aerrValue) {
		TAIL_CALL: for(;;) {
		if ((ai<an)) {
			final int l0_c = Native.getCharCodeAt(as, ai);
			final int l1_d = Module_math.f_getDigitCode(l0_c);
			if ((l1_d!=(-1))) {
				{
					final int l2___tmp = (ai+1);
					final double l3___tmp = ((10.0*aacc)+Module_math.f_i2d(l1_d));
					ai = l2___tmp;
					aacc = l3___tmp;
					continue TAIL_CALL;
				}
			} else {
				if ((l0_c==46)) {
					final Struct_Pair l4_fracres = Module_math.f_s2dfrac(as, (ai+1), an, aacc, 10.0);
					final double l5_mult = (10.0*((double)(l4_fracres).f_first));
					if (Module_math.f_isNanOrInf(l5_mult)) {
						return ((((double)(l4_fracres).f_first)/((double)(l4_fracres).f_second))*10.0);
					} else {
						return (l5_mult/((double)(l4_fracres).f_second));
					}
				} else {
					if (((l0_c==69)||(l0_c==101))) {
						return Module_math.f_s2dexp(as, (ai+1), an, aacc);
					} else {
						if (Module_math.f_equalDoubles(aerrValue, Module_math.g_doubleMin)) {
							return aacc;
						} else {
							return aerrValue;
						}
					}
				}
			}
		} else {
			return aacc;
		}
	}
	}
	public static final int f_s2i(String as) {
		return Module_math.f_floor(Module_math.f_s2d(as));
	}
	public static final int f_subStr2i(String as, int astart, int aend, int aacc) {
		TAIL_CALL: for(;;) {
		if ((astart<aend)) {
			final int l0_c = Native.getCharCodeAt(as, astart);
			final int l1_d = Module_math.f_getDigitCode(l0_c);
			if ((l1_d!=(-1))) {
				{
					final int l2___tmp = (astart+1);
					final int l3___tmp = ((10*aacc)+l1_d);
					astart = l2___tmp;
					aacc = l3___tmp;
					continue TAIL_CALL;
				}
			} else {
				return aacc;
			}
		} else {
			return aacc;
		}
	}
	}
	public static final Struct f_tryDouble2Int(double anumber) {
		final int l0_i = Module_math.f_floor(anumber);
		if ((Module_math.f_i2d(l0_i)==anumber)) {
			return (new Struct_Some(l0_i));
		} else {
			return ((Struct)SingletonStructs.str_None);
		}
	}
}
