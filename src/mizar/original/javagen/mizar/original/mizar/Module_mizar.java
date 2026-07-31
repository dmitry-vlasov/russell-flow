// HASH COLLISIONS: YES
// timestamp: 1785498592000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_mizar {
	public static final Object f_main() {
		final String l0_article = Module_url_parameter.f_getUrlParameterDef("article", "");
		final String l1_mml = Module_url_parameter.f_getUrlParameterDef("mml", "/home/dmitry/dev/math/MML-test");
		if ((l0_article).equals("")) {
			Module_runtime.f_println("mizarj: the Mizar verifier transcribed to Flow9");
			Module_runtime.f_println("usage: mizarj article=<name> [mml=<dir>]");
			return Native.quit(1);
		} else {
			final String l2_xmlPath = (((l1_mml+"/mml/")+l0_article)+".xml");
			if (!FlowFileSystem.fileExists(l2_xmlPath)) {
				Module_runtime.f_println(("mizarj: no analyzer report "+l2_xmlPath));
				return Native.quit(1);
			} else {
				final Struct_MizPrepStats l3_stats = Module_prep.f_mizCheckArticle((l1_mml+"/mml"), l0_article);
				Module_runtime.f_println(((((((((((l0_article+": checked=")+Module_string.f_i2s((l3_stats).f_checked))+" accepted=")+Module_string.f_i2s((l3_stats).f_accepted))+" failed=")+Module_string.f_i2s((l3_stats).f_failed))+" from-skipped=")+Module_string.f_i2s((l3_stats).f_skippedFrom))+" blocks-skipped=")+Module_string.f_i2s((l3_stats).f_skippedBlocks)));
				final Struct_Vector l6_$5 = (l3_stats).f_failures;
				final Func1<Object,String> l7_$4 = (Func1<Object, String>)(String af) -> {
					return Module_runtime.f_println(("  FAIL "+af));
				};
				Module_vector.f_iterVector(l6_$5, ((Func1<Object,Object>)(Func1)l7_$4));
				return Native.quit((((l3_stats).f_failed>0)?1:0));
			}
		}
	}
}
