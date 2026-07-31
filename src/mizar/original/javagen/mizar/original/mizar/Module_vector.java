// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_vector {
	// The wrapper to a native: Native.addVector
	public static final Object f_addNativeVector(Object avector, Object aval) {
		return Native.addVector(avector, aval);
	}
	// The wrapper to a native: Native.clearVector
	public static final Object f_clearNativeVector(Object avector, Object[] al0_1) {
		return Native.clearVector(avector, al0_1);
	}
	public static final Object f_clearVector(Struct_Vector avector) {
		final Object[] l0_dummy = SingletonStructs.arr_empty;
		return Native.clearVector((avector).f_vect, l0_dummy);
	}
	public static final Struct_Vector f_copyVector(Struct_Vector avector) {
		final Struct_Vector l0_copy = Module_vector.f_makeVector(Module_vector.f_sizeVector(avector));
		final Func1<Object,Object> l2_$1 = (Func1<Object, Object>)(Object ax) -> {
			return Module_vector.f_pushVector(l0_copy, ax);
		};
		Module_vector.f_iterVector(avector, l2_$1);
		return l0_copy;
	}
	public static final Object f_doFoldVector(Struct_Vector avector, Object aval, Func2<Object,Object, Object> afn, int ai) {
		TAIL_CALL: for(;;) {
		if ((ai==Module_vector.f_sizeVector(avector))) {
			return aval;
		} else {
			{
				final Object l0___tmp = afn.invoke(aval, Native.getVector((avector).f_vect, ai));
				final int l1___tmp = (ai+1);
				aval = l0___tmp;
				ai = l1___tmp;
				continue TAIL_CALL;
			}
		}
	}
	}
	public static final Object f_doIterVector(Struct_Vector avector, Func1<Object,Object> afn, int ai) {
		TAIL_CALL: for(;;) {
		if ((ai<Module_vector.f_sizeVector(avector))) {
			afn.invoke(Native.getVector((avector).f_vect, ai));
			{
				final int l0___tmp = (ai+1);
				ai = l0___tmp;
				continue TAIL_CALL;
			}
		} else {
			return null;
		}
	}
	}
	public static final Struct_Vector f_doMapVector(Struct_Vector avector, Func1<Object,Object> afn, Struct_Vector aret, int ai) {
		TAIL_CALL: for(;;) {
		if ((ai==Module_vector.f_sizeVector(avector))) {
			return aret;
		} else {
			Module_vector.f_pushVector(aret, afn.invoke(Native.getVector((avector).f_vect, ai)));
			{
				final int l0___tmp = (ai+1);
				ai = l0___tmp;
				continue TAIL_CALL;
			}
		}
	}
	}
	public static final Object f_foldVector(Struct_Vector avector, Object ainit, Func2<Object,Object, Object> afn) {
		return Module_vector.f_doFoldVector(avector, ainit, afn, 0);
	}
	// The wrapper to a native: Native.getVector
	public static final Object f_getNativeVector(Object avector, int ai) {
		return Native.getVector(avector, ai);
	}
	public static final Object f_getVectorDef(Struct_Vector avector, int ai, Object adef) {
		final Object[] l0_dummy = SingletonStructs.arr_empty;
		if (((0<=ai)&&(ai<Native.sizeVector((avector).f_vect, l0_dummy)))) {
			return Native.getVector((avector).f_vect, ai);
		} else {
			return adef;
		}
	}
	public static final Object f_getVectorUnsafe(Struct_Vector avector, int ai) {
		return Native.getVector((avector).f_vect, ai);
	}
	public static final Object f_iterVector(Struct_Vector avector, Func1<Object,Object> afn) {
		return Module_vector.f_doIterVector(avector, afn, 0);
	}
	// The wrapper to a native: Native.makeVector
	public static final Object f_makeNativeVector(int acapacity, Object[] al0_1) {
		return Native.makeVector(acapacity, al0_1);
	}
	public static final Struct_Vector f_makeVector(int acapacity) {
		final Object[] l0_dummy = SingletonStructs.arr_empty;
		return (new Struct_Vector(Native.makeVector(acapacity, l0_dummy), ((Struct)SingletonStructs.str_None)));
	}
	public static final Struct_Vector f_mapVector(Struct_Vector avector, Func1<Object,Object> afn) {
		final Struct_Vector l0_ret = Module_vector.f_makeVector(Module_vector.f_sizeVector(avector));
		return Module_vector.f_doMapVector(avector, afn, l0_ret, 0);
	}
	public static final Object f_pushVector(Struct_Vector avector, Object aval) {
		return Native.addVector((avector).f_vect, aval);
	}
	// The wrapper to a native: Native.removeVector
	public static final Object f_removeNativeVector(Object avector, int ai, Object[] al0_2) {
		return Native.removeVector(avector, ai, al0_2);
	}
	public static final Object f_removeVector(Struct_Vector avector, int ai) {
		final Object[] l0_dummy = SingletonStructs.arr_empty;
		if (((0<=ai)&&(ai<Native.sizeVector((avector).f_vect, l0_dummy)))) {
			return Native.removeVector((avector).f_vect, ai, l0_dummy);
		} else {
			return null;
		}
	}
	// The wrapper to a native: Native.setVector
	public static final Object f_setNativeVector(Object avector, int ai, Object aval) {
		return Native.setVector(avector, ai, aval);
	}
	public static final Object f_setVectorUnsafe(Struct_Vector avector, int ai, Object aval) {
		return Native.setVector((avector).f_vect, ai, aval);
	}
	// The wrapper to a native: Native.sizeVector
	public static final int f_sizeNativeVector(Object avector, Object[] al0_1) {
		return Native.sizeVector(avector, al0_1);
	}
	public static final int f_sizeVector(Struct_Vector avector) {
		final Object[] l0_dummy = SingletonStructs.arr_empty;
		return Native.sizeVector((avector).f_vect, l0_dummy);
	}
	public static final Object[] f_vector2array(Struct_Vector avector) {
		return Native.vector2array((avector).f_vect);
	}
	// The wrapper to a native: Native.vector2array
	public static final Object[] f_vector2arrayNative(Object avector) {
		return Native.vector2array(avector);
	}
}
