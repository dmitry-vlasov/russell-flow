// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_maybe {
	public static final Object f_either(Struct am, Object aalternative) {
		Struct l0__tmp = am;
		switch (l0__tmp.getTypeId()) {
		case 82/*None*/: {
			return aalternative;
		}
		case 90/*Some*/: {
			final Struct_Some l1__tmp = (Struct_Some)l0__tmp;
			final Object l2_v = l1__tmp.f_value;
			final Object l3_v = (l1__tmp).f_value;
			return l3_v;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Object f_eitherFn(Struct am, Func1<Object,Object> afn, Func0<Object> aalternativeFn) {
		Struct l0__tmp = am;
		switch (l0__tmp.getTypeId()) {
		case 82/*None*/: {
			return aalternativeFn.invoke();
		}
		case 90/*Some*/: {
			final Struct_Some l1__tmp = (Struct_Some)l0__tmp;
			final Object l2_v = l1__tmp.f_value;
			final Object l3_v = (l1__tmp).f_value;
			return afn.invoke(l3_v);
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final boolean f_isNone(Struct am) {
		Struct l0__tmp = am;
		switch (l0__tmp.getTypeId()) {
		case 82/*None*/: {
			return true;
		}
		case 90/*Some*/: {
			final Struct_Some l1__tmp = (Struct_Some)l0__tmp;
			final Object l2___ = (l1__tmp).f_value;
			return false;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final boolean f_isSome(Struct am) {
		Struct l0__tmp = am;
		switch (l0__tmp.getTypeId()) {
		case 82/*None*/: {
			return false;
		}
		case 90/*Some*/: {
			final Struct_Some l1__tmp = (Struct_Some)l0__tmp;
			final Object l2___ = (l1__tmp).f_value;
			return true;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
}
