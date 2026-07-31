// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_flowstructs {
	public static final Object f_extractStruct(Object[] aa, Object ae) {
		final Func2<Object,Object, Object> l1_$0 = (Func2<Object, Object, Object>)(Object aacc, Object ael) -> {
			if (Native.isSameStructType(aacc, ael)) {
				final Object l2_t = ((Object)Module_dynamic.f_flow(ael));
				return l2_t;
			} else {
				return aacc;
			}
		};
		return Native.fold(aa, ae, l1_$0);
	}
}
