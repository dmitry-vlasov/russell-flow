// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_url {
	public static Reference<Func2<Object,String, Func1<Object,String>>> g_redirectAdditionalFn;
	public static void init() {
		final Func2<Object,String, Func1<Object,String>> l1_$0 = (Func2<Object, String, Func1<Object,String>>)(String aurl, Func1<Object,String> afn) -> {
			return afn.invoke(aurl);
		};
		g_redirectAdditionalFn=((Reference<Func2<Object,String, Func1<Object,String>>>)(new Reference(l1_$0)));
	}
}
