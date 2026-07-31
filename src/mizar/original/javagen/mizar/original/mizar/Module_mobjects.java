// HASH COLLISIONS: YES
// timestamp: 1785496853000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_mobjects {
	public static final Struct_MizIntSeq f_mizCopyIntSeq(Struct_MizIntSeq as) {
		return (new Struct_MizIntSeq(Module_vector.f_copyVector((as).f_items)));
	}
	public static final Struct_MizNatFunc f_mizCopyNatFunc(Struct_MizNatFunc af) {
		return (new Struct_MizNatFunc(Module_vector.f_copyVector((af).f_items), (af).f_consistent));
	}
	public static final Struct_MizIntSeq f_mizInitIntSeq(int acapacity) {
		return (new Struct_MizIntSeq(Module_vector.f_makeVector(((int)Native.fast_max(acapacity, 4)))));
	}
	public static final Struct_MizNatFunc f_mizInitNatFunc(int acapacity) {
		return (new Struct_MizNatFunc(Module_vector.f_makeVector(((int)Native.fast_max(acapacity, 4))), true));
	}
	public static final Struct_MizNatFunc f_mizInitNatSet(int acapacity) {
		return Module_mobjects.f_mizInitNatFunc(acapacity);
	}
	public static final Struct_Pair f_mizIntSetFind(Struct_MizIntSeq as, int av) {
		final Reference<Boolean> l0_found = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l1_l = ((Reference<Integer>)(new Reference(0)));
		final Reference<Integer> l2_h = ((Reference<Integer>)(new Reference((Module_vector.f_sizeVector((as).f_items)-1))));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return (((int)l1_l.value)<=((int)l2_h.value));
		};
		final Struct_MizIntSeq l7_s = as;
		final int l8_v = av;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			final int l7_i = ((((int)l1_l.value)+((int)l2_h.value))/2);
			final int l8_c = (((int)Module_vector.f_getVectorUnsafe((l7_s).f_items, l7_i))-l8_v);
			if ((l8_c<0)) {
				l1_l.value = ((Integer)(l7_i+1));
				return null;
			} else {
				l2_h.value = ((Integer)(l7_i-1));
				if ((l8_c==0)) {
					l0_found.value = ((Boolean)true);
					l1_l.value = ((Integer)l7_i);
					l2_h.value = ((Integer)(l7_i-1));
					return null;
				} else {
					return null;
				}
			}
		};
		Module_loop.f_while(l5_$3, l6_$4);
		return (new Struct_Pair(l0_found.value, l1_l.value));
	}
	public static final int f_mizIntSetInsert(Struct_MizIntSeq as, int av) {
		final Struct_Pair l0_p = Module_mobjects.f_mizIntSetFind(as, av);
		if (!((boolean)(l0_p).f_first)) {
			Module_mobjects.f_mizVectorAtInsert((as).f_items, ((int)(l0_p).f_second), av);
		} else {
		}
		return ((int)(l0_p).f_second);
	}
	public static final boolean f_mizIntSetIsInSet(Struct_MizIntSeq as, int av) {
		return ((boolean)(Module_mobjects.f_mizIntSetFind(as, av)).f_first);
	}
	public static final Object f_mizNatAssign(Struct_MizNatFunc af, int ax, int ay) {
		if ((af).f_consistent) {
			final Struct_Pair l0_p = Module_mobjects.f_mizNatSearchPair(af, ax);
			if (((boolean)(l0_p).f_first)) {
				if (((((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((af).f_items, ((int)(l0_p).f_second)))).f_y!=ay)) {
					return Module_mobjects.f_mizNatRefuted(af);
				} else {
					return null;
				}
			} else {
				return Module_mobjects.f_mizVectorAtInsert((af).f_items, ((int)(l0_p).f_second), (new Struct_MizIntPair(ax, ay)));
			}
		} else {
			return null;
		}
	}
	public static final int f_mizNatCompareWith(Struct_MizNatFunc af, Struct_MizNatFunc ag) {
		if (((Module_mobjects.f_mizNatCount(af)<=Module_mobjects.f_mizNatCount(ag))&&Module_mobjects.f_mizNatWeakerThan(af, ag))) {
			return (-1);
		} else {
			if (((Module_mobjects.f_mizNatCount(ag)<=Module_mobjects.f_mizNatCount(af))&&Module_mobjects.f_mizNatWeakerThan(ag, af))) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	public static final int f_mizNatCount(Struct_MizNatFunc af) {
		return Module_vector.f_sizeVector((af).f_items);
	}
	public static final Object f_mizNatDeleteElem(Struct_MizNatFunc af, int ax) {
		final Struct_Pair l0_p = Module_mobjects.f_mizNatSearchPair(af, ax);
		if (((boolean)(l0_p).f_first)) {
			return Module_mobjects.f_mizVectorAtDelete((af).f_items, ((int)(l0_p).f_second));
		} else {
			return null;
		}
	}
	public static final Object f_mizNatEnlargeBy(Struct_MizNatFunc af, Struct_MizNatFunc ag) {
		final Struct_Vector l2_$1 = (ag).f_items;
		final Struct_MizNatFunc l4_f = af;
		final Func1<Object,Struct_MizIntPair> l3_$0 = (Func1<Object, Struct_MizIntPair>)(Struct_MizIntPair ait) -> {
			return Module_mobjects.f_mizNatInsertElem(l4_f, (ait).f_x);
		};
		return Module_vector.f_iterVector(l2_$1, ((Func1<Object,Object>)(Func1)l3_$0));
	}
	public static final boolean f_mizNatHasInDom(Struct_MizNatFunc af, int ax) {
		return ((boolean)(Module_mobjects.f_mizNatSearchPair(af, ax)).f_first);
	}
	public static final Object f_mizNatInsertElem(Struct_MizNatFunc af, int ax) {
		return Module_mobjects.f_mizNatInsertPair(af, ax, 0);
	}
	public static final Object f_mizNatInsertPair(Struct_MizNatFunc af, int ax, int ay) {
		final Struct_Pair l0_p = Module_mobjects.f_mizNatSearchPair(af, ax);
		if (!((boolean)(l0_p).f_first)) {
			return Module_mobjects.f_mizVectorAtInsert((af).f_items, ((int)(l0_p).f_second), (new Struct_MizIntPair(ax, ay)));
		} else {
			return null;
		}
	}
	public static final boolean f_mizNatIsSubsetOf(Struct_MizNatFunc af, Struct_MizNatFunc ag) {
		final int l0_n = Module_vector.f_sizeVector((af).f_items);
		final int l1_c = Module_vector.f_sizeVector((ag).f_items);
		if ((l1_c<l0_n)) {
			return false;
		} else {
			final Reference<Boolean> l2_res = ((Reference<Boolean>)(new Reference(true)));
			final Reference<Integer> l3_j = ((Reference<Integer>)(new Reference(0)));
			final Reference<Integer> l4_i = ((Reference<Integer>)(new Reference(0)));
			final Func0<Boolean> l7_$5 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l4_i.value)<l0_n)&&((boolean)l2_res.value)));
			};
			final Struct_MizNatFunc l9_f = af;
			final Struct_MizNatFunc l10_g = ag;
			final Func0<Object> l8_$6 = (Func0<Object>)() -> {
				final Struct_MizIntPair l9_a = ((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((l9_f).f_items, ((int)l4_i.value)));
				final Func0<Boolean> l12_$10 = (Func0<Boolean>)() -> {
					return ((Boolean)((((int)l3_j.value)<l1_c)&&((((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((l10_g).f_items, ((int)l3_j.value)))).f_x<(l9_a).f_x)));
				};
				final Func0<Object> l13_$11 = (Func0<Object>)() -> {
					l3_j.value = ((Integer)(((int)l3_j.value)+1));
					return null;
				};
				Module_loop.f_while(l12_$10, l13_$11);
				if ((((int)l3_j.value)==l1_c)) {
					l2_res.value = ((Boolean)false);
				} else {
					final Struct_MizIntPair l14_b = ((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((l10_g).f_items, ((int)l3_j.value)));
					if ((((l9_a).f_x!=(l14_b).f_x)||((l9_a).f_y!=(l14_b).f_y))) {
						l2_res.value = ((Boolean)false);
					} else {
					}
				}
				l4_i.value = ((Integer)(((int)l4_i.value)+1));
				return null;
			};
			Module_loop.f_while(l7_$5, l8_$6);
			return ((boolean)l2_res.value);
		}
	}
	public static final Struct_MizIntPair f_mizNatItem(Struct_MizNatFunc af, int ai) {
		return ((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((af).f_items, ai));
	}
	public static final Object f_mizNatJoin(Struct_MizNatFunc af, Struct_MizNatFunc ag) {
		if ((af).f_consistent) {
			if (!(ag).f_consistent) {
				return Module_mobjects.f_mizNatRefuted(af);
			} else {
				final int l0_n = Module_vector.f_sizeVector((ag).f_items);
				final Reference<Integer> l1_k = ((Reference<Integer>)(new Reference(0)));
				final Struct_MizNatFunc l5_f = af;
				final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
					return ((Boolean)((((int)l1_k.value)<l0_n)&&(l5_f).f_consistent));
				};
				final Struct_MizNatFunc l7_g = ag;
				final Struct_MizNatFunc l8_f = af;
				final Func0<Object> l6_$3 = (Func0<Object>)() -> {
					final Struct_MizIntPair l7_it = ((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((l7_g).f_items, ((int)l1_k.value)));
					final Struct_Pair l8_p = Module_mobjects.f_mizNatSearchPair(l8_f, (l7_it).f_x);
					if (((boolean)(l8_p).f_first)) {
						final Struct_MizIntPair l9_mine = ((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((l8_f).f_items, ((int)(l8_p).f_second)));
						if (((l9_mine).f_y!=(l7_it).f_y)) {
							Module_mobjects.f_mizNatRefuted(l8_f);
						} else {
						}
					} else {
						Module_mobjects.f_mizVectorAtInsert((l8_f).f_items, ((int)(l8_p).f_second), l7_it);
					}
					l1_k.value = ((Integer)(((int)l1_k.value)+1));
					return null;
				};
				return Module_loop.f_while(l4_$2, l6_$3);
			}
		} else {
			return null;
		}
	}
	public static final Struct f_mizNatJoinAtom(Struct_MizNatFunc af, Struct_MizNatFunc aatom) {
		final Struct_MizNatFunc l0_eval = Module_mobjects.f_mizCopyNatFunc(af);
		Module_mobjects.f_mizNatJoin(l0_eval, aatom);
		if ((l0_eval).f_consistent) {
			return (new Struct_Some(l0_eval));
		} else {
			return ((Struct)SingletonStructs.str_None);
		}
	}
	public static final Object f_mizNatRefuted(Struct_MizNatFunc af) {
		Module_vector.f_clearVector((af).f_items);
		((Field_consistent)af).set_consistent(false);
		return null;
	}
	public static final Struct_Pair f_mizNatSearchPair(Struct_MizNatFunc af, int ax) {
		final Reference<Boolean> l0_found = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Integer> l1_l = ((Reference<Integer>)(new Reference(0)));
		final Reference<Integer> l2_h = ((Reference<Integer>)(new Reference((Module_vector.f_sizeVector((af).f_items)-1))));
		final Func0<Boolean> l5_$3 = (Func0<Boolean>)() -> {
			return (((int)l1_l.value)<=((int)l2_h.value));
		};
		final Struct_MizNatFunc l7_f = af;
		final int l8_x = ax;
		final Func0<Object> l6_$4 = (Func0<Object>)() -> {
			final int l7_i = ((((int)l1_l.value)+((int)l2_h.value))/2);
			final int l8_c = ((((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((l7_f).f_items, l7_i))).f_x-l8_x);
			if ((l8_c<0)) {
				l1_l.value = ((Integer)(l7_i+1));
				return null;
			} else {
				l2_h.value = ((Integer)(l7_i-1));
				if ((l8_c==0)) {
					l0_found.value = ((Boolean)true);
					l1_l.value = ((Integer)l7_i);
					l2_h.value = ((Integer)(l7_i-1));
					return null;
				} else {
					return null;
				}
			}
		};
		Module_loop.f_while(l5_$3, l6_$4);
		return (new Struct_Pair(l0_found.value, l1_l.value));
	}
	public static final Object f_mizNatUp(Struct_MizNatFunc af, int ax) {
		if ((af).f_consistent) {
			final Struct_Pair l0_p = Module_mobjects.f_mizNatSearchPair(af, ax);
			if (((boolean)(l0_p).f_first)) {
				final Struct_MizIntPair l1_it = ((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((af).f_items, ((int)(l0_p).f_second)));
				return Module_vector.f_setVectorUnsafe((af).f_items, ((int)(l0_p).f_second), (new Struct_MizIntPair((l1_it).f_x, ((l1_it).f_y+1))));
			} else {
				return Module_mobjects.f_mizVectorAtInsert((af).f_items, ((int)(l0_p).f_second), (new Struct_MizIntPair(ax, 1)));
			}
		} else {
			return null;
		}
	}
	public static final int f_mizNatValue(Struct_MizNatFunc af, int ax) {
		final Struct_Pair l0_p = Module_mobjects.f_mizNatSearchPair(af, ax);
		if (((boolean)(l0_p).f_first)) {
			return (((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((af).f_items, ((int)(l0_p).f_second)))).f_y;
		} else {
			Module_errhan.f_mizRunTimeError(2100);
			return 0;
		}
	}
	public static final boolean f_mizNatWeakerThan(Struct_MizNatFunc af, Struct_MizNatFunc ag) {
		if ((Module_mobjects.f_mizNatCount(af)>Module_mobjects.f_mizNatCount(ag))) {
			return false;
		} else {
			final Reference<Boolean> l0_res = ((Reference<Boolean>)(new Reference(true)));
			final Struct_Vector l3_$2 = (af).f_items;
			final Struct_MizNatFunc l5_g = ag;
			final Func1<Object,Struct_MizIntPair> l4_$1 = (Func1<Object, Struct_MizIntPair>)(Struct_MizIntPair ait) -> {
				if (((boolean)l0_res.value)) {
					final Struct_Pair l5_p = Module_mobjects.f_mizNatSearchPair(l5_g, (ait).f_x);
					if ((!((boolean)(l5_p).f_first)||((((Struct_MizIntPair)Module_vector.f_getVectorUnsafe((l5_g).f_items, ((int)(l5_p).f_second)))).f_y!=(ait).f_y))) {
						l0_res.value = ((Boolean)false);
						return null;
					} else {
						return null;
					}
				} else {
					return null;
				}
			};
			Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
			return ((boolean)l0_res.value);
		}
	}
	public static final Object f_mizSeqAtDelete(Struct_MizIntSeq as, int ai) {
		return Module_vector.f_removeVector((as).f_items, ai);
	}
	public static final Object f_mizSeqClear(Struct_MizIntSeq as) {
		return Module_vector.f_clearVector((as).f_items);
	}
	public static final int f_mizSeqCount(Struct_MizIntSeq as) {
		return Module_vector.f_sizeVector((as).f_items);
	}
	public static final int f_mizSeqInsert(Struct_MizIntSeq as, int av) {
		Module_vector.f_pushVector((as).f_items, av);
		return (Module_vector.f_sizeVector((as).f_items)-1);
	}
	public static final int f_mizSeqValue(Struct_MizIntSeq as, int ai) {
		return ((int)Module_vector.f_getVectorUnsafe((as).f_items, ai));
	}
	public static final Object f_mizVectorAtDelete(Struct_Vector av, int ai) {
		return Module_vector.f_removeVector(av, ai);
	}
	public static final Object f_mizVectorAtInsert(Struct_Vector av, int ai, Object aval) {
		final int l0_n = Module_vector.f_sizeVector(av);
		if ((ai>=l0_n)) {
			return Module_vector.f_pushVector(av, aval);
		} else {
			Module_vector.f_pushVector(av, Module_vector.f_getVectorUnsafe(av, (l0_n-1)));
			final Reference<Integer> l1_j = ((Reference<Integer>)(new Reference((l0_n-1))));
			final int l5_i = ai;
			final Func0<Boolean> l4_$2 = (Func0<Boolean>)() -> {
				return (((int)l1_j.value)>l5_i);
			};
			final Struct_Vector l7_v = av;
			final Func0<Object> l6_$3 = (Func0<Object>)() -> {
				Module_vector.f_setVectorUnsafe(l7_v, ((int)l1_j.value), Module_vector.f_getVectorUnsafe(l7_v, (((int)l1_j.value)-1)));
				l1_j.value = ((Integer)(((int)l1_j.value)-1));
				return null;
			};
			Module_loop.f_while(l4_$2, l6_$3);
			return Module_vector.f_setVectorUnsafe(av, ai, aval);
		}
	}
}
