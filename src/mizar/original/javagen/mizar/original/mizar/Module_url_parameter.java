// HASH COLLISIONS: YES
// timestamp: 1751131951000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_url_parameter {
	public static Reference<Struct> g_allUrlParameters;
	public static void init() {
		g_allUrlParameters=((Reference<Struct>)(new Reference(Module_url_parameter.f_getAllUrlParameters())));
	}
	public static final Struct f_getAllUrlParameters() {
		final Struct l0_localStorage = Native.parseJson(Module_runtime.f_getKeyValue("local-url-parameters", "{}"));
		final Struct l1_localTree = ((Struct)Module_maybe.f_either(Module_json_utils.f_json2treeS(l0_localStorage, Wrappers.w_json2stringM), Module_tree.f_makeTree()));
		final Object[] l4_$3 = Native.getAllUrlParameters();
		final Func2<Struct,Struct, Object[]> l5_$2 = (Func2<Struct, Struct, Object[]>)(Struct aacc, Object[] apar) -> {
			return Module_tree.f_setTree(aacc, ((String)(apar[0])), ((String)(apar[1])));
		};
		return ((Struct)Native.fold(l4_$3, l1_localTree, ((Func2<Object,Object, Object>)(Func2)l5_$2)));
	}
	public static final String f_getUrlParameter(String aname) {
		return ((String)Module_tree.f_lookupTreeDef(Module_url_parameter.g_allUrlParameters.value, aname, ""));
	}
	public static final String f_getUrlParameterDef(String aname, String adef) {
		final String l0_param = Module_url_parameter.f_getUrlParameter(aname);
		if ((!(l0_param).equals(""))) {
			return l0_param;
		} else {
			return adef;
		}
	}
	public static final boolean f_isParameterFalse(String aparam) {
		return (((aparam).equals("false")||(aparam).equals("0"))||(aparam).equals("FALSE"));
	}
	public static final boolean f_isUrlParameterFalse(String aname) {
		final String l0_param = Module_url_parameter.f_getUrlParameter(aname);
		return Module_url_parameter.f_isParameterFalse(l0_param);
	}
	public static final boolean f_isUrlParameterTrue(String aname) {
		final Struct l3_$2 = Module_tree.f_lookupTree(Module_url_parameter.g_allUrlParameters.value, aname);
		final Func1<Boolean,String> l4_$0 = (Func1<Boolean, String>)(String aparam) -> {
			return ((Boolean)!Module_url_parameter.f_isParameterFalse(aparam));
		};
		final Func0<Boolean> l5_$1 = (Func0<Boolean>)() -> {
			return ((Boolean)false);
		};
		return ((boolean)Module_maybe.f_eitherFn(l3_$2, ((Func1<Object,Object>)(Func1)l4_$0), ((Func0<Object>)(Func0)l5_$1)));
	}
}
