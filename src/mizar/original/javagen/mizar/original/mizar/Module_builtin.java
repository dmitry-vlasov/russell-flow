// HASH COLLISIONS: YES
// timestamp: 1785496853000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_builtin {
	public static int g_rqAny;
	public static int g_rqBelongsTo;
	public static int g_rqComplex;
	public static int g_rqDiv;
	public static int g_rqDivides;
	public static int g_rqElement;
	public static int g_rqEmpty;
	public static int g_rqEmptySet;
	public static int g_rqEqualsTo;
	public static int g_rqGCD;
	public static int g_rqImaginaryUnit;
	public static int g_rqInclusion;
	public static int g_rqIntersection;
	public static int g_rqLAST;
	public static int g_rqLCM;
	public static int g_rqLessOrEqual;
	public static int g_rqMod;
	public static int g_rqNegative;
	public static int g_rqNone;
	public static int g_rqOmega;
	public static int g_rqPositive;
	public static int g_rqPowerSet;
	public static int g_rqRealAdd;
	public static int g_rqRealDiff;
	public static int g_rqRealDiv;
	public static int g_rqRealInv;
	public static int g_rqRealMult;
	public static int g_rqRealNeg;
	public static int g_rqSetMode;
	public static int g_rqSubtraction;
	public static int g_rqSucc;
	public static int g_rqSymmetricDifference;
	public static int g_rqUnion;
	public static int g_rqZero;
	public static int g_rqZeroNumber;
	public static void init() {
		g_rqAny=1;
		g_rqBelongsTo=4;
		g_rqComplex=31;
		g_rqDiv=35;
		g_rqDivides=37;
		g_rqElement=7;
		g_rqEmpty=5;
		g_rqEmptySet=6;
		g_rqEqualsTo=3;
		g_rqGCD=39;
		g_rqImaginaryUnit=30;
		g_rqInclusion=9;
		g_rqIntersection=18;
		g_rqLAST=40;
		g_rqLCM=38;
		g_rqLessOrEqual=15;
		g_rqMod=36;
		g_rqNegative=28;
		g_rqNone=0;
		g_rqOmega=32;
		g_rqPositive=27;
		g_rqPowerSet=8;
		g_rqRealAdd=13;
		g_rqRealDiff=24;
		g_rqRealDiv=25;
		g_rqRealInv=23;
		g_rqRealMult=14;
		g_rqRealNeg=22;
		g_rqSetMode=2;
		g_rqSubtraction=19;
		g_rqSucc=16;
		g_rqSymmetricDifference=20;
		g_rqUnion=17;
		g_rqZero=34;
		g_rqZeroNumber=33;
	}
	public static final int f_mizBuiltIn(Struct_MizBuiltIn ab, int arq) {
		return ((int)Module_vector.f_getVectorDef((ab).f_nrs, arq, 0));
	}
	public static final Struct_MizBuiltIn f_mizMakeBuiltIn() {
		final Struct_Vector l0_v = Module_vector.f_makeVector((Module_builtin.g_rqLAST+1));
		final Func1<Object,Integer> l2_$1 = (Func1<Object, Integer>)(Integer al3_0) -> {
			return Module_vector.f_pushVector(l0_v, 0);
		};
		Module_runtime.f_fori(0, Module_builtin.g_rqLAST, l2_$1);
		return (new Struct_MizBuiltIn(l0_v));
	}
	public static final Object f_mizSetBuiltIn(Struct_MizBuiltIn ab, int arq, int anr) {
		return Module_vector.f_setVectorUnsafe((ab).f_nrs, arq, anr);
	}
}
