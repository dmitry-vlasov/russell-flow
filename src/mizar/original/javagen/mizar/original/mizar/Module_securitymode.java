// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_securitymode {
	public static Reference<Boolean> g_loggingEnabled;
	public static Reference<Object[]> g_securityModes;
	public static void init() {
		g_loggingEnabled=((Reference<Boolean>)(new Reference(true)));
		g_securityModes=((Reference<Object[]>)(new Reference(SingletonStructs.arr_empty)));
	}
	public static final boolean f_isLoggingEnabled() {
		return ((!Module_securitymode.f_isOWASPLevel1()&&Module_securitymode.f_isVerboseOutput())&&((boolean)Module_securitymode.g_loggingEnabled.value));
	}
	public static final boolean f_isOWASPLevel1() {
		return ((((Struct_OWASP)Module_flowstructs.f_extractStruct(Module_securitymode.g_securityModes.value, (new Struct_OWASP(0))))).f_level>0);
	}
	public static final boolean f_isVerboseOutput() {
		final Object[] l2_$1 = Module_securitymode.g_securityModes.value;
		final Func0<Boolean> l3_$0 = (Func0<Boolean>)() -> {
			return ((Boolean)true);
		};
		return ((boolean)(((Struct_VerboseOutput)Module_flowstructs.f_extractStruct(l2_$1, (new Struct_VerboseOutput(l3_$0))))).f_fn.invoke());
	}
}
