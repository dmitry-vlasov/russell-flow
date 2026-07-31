// HASH COLLISIONS: YES
// timestamp: 1785524636000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_polynom {
	public static Struct_MizComplex g_mizCOneP;
	public static Struct_MizComplex g_mizCZeroP;
	public static void init() {
		g_mizCOneP=(new Struct_MizComplex(1.0, 0.0));
		g_mizCZeroP=(new Struct_MizComplex(0.0, 0.0));
	}
	public static final Struct_MizComplex f_cAdd(Struct_MizComplex aa, Struct_MizComplex ab) {
		return (new Struct_MizComplex(((aa).f_re+(ab).f_re), ((aa).f_im+(ab).f_im)));
	}
	public static final int f_cCmp(Struct_MizComplex aa, Struct_MizComplex ab) {
		if (((aa).f_re<(ab).f_re)) {
			return (-1);
		} else {
			if (((aa).f_re>(ab).f_re)) {
				return 1;
			} else {
				if (((aa).f_im<(ab).f_im)) {
					return (-1);
				} else {
					if (((aa).f_im>(ab).f_im)) {
						return 1;
					} else {
						return 0;
					}
				}
			}
		}
	}
	public static final boolean f_cEq0(Struct_MizComplex av) {
		return (((av).f_re==0.0)&&((av).f_im==0.0));
	}
	public static final boolean f_cEq1(Struct_MizComplex av) {
		return (((av).f_re==1.0)&&((av).f_im==0.0));
	}
	public static final Struct_MizComplex f_cMul(Struct_MizComplex aa, Struct_MizComplex ab) {
		return (new Struct_MizComplex((((aa).f_re*(ab).f_re)-((aa).f_im*(ab).f_im)), (((aa).f_re*(ab).f_im)+((aa).f_im*(ab).f_re))));
	}
	public static final Struct_MizComplex f_cNeg(Struct_MizComplex aa) {
		return (new Struct_MizComplex((-(aa).f_re), (-(aa).f_im)));
	}
	public static final Struct_MizPolynomial f_mizAddPolynomials(Struct_MizPolynomial aa, Struct_MizPolynomial ab) {
		final Struct_MizPolynomial l0_r = (new Struct_MizPolynomial(Module_vector.f_makeVector((Module_vector.f_sizeVector((aa).f_monos)+Module_vector.f_sizeVector((ab).f_monos)))));
		final Struct_Vector l3_$2 = (aa).f_monos;
		final Func1<Object,Struct_MizMonomial> l4_$1 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial am) -> {
			return Module_polynom.f_mizPolyInsert(l0_r, Module_polynom.f_mizCopyMonomial(am));
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		final Struct_Vector l7_$6 = (ab).f_monos;
		final Func1<Object,Struct_MizMonomial> l8_$5 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial am) -> {
			return Module_polynom.f_mizPolyInsert(l0_r, Module_polynom.f_mizCopyMonomial(am));
		};
		Module_vector.f_iterVector(l7_$6, ((Func1<Object,Object>)(Func1)l8_$5));
		if ((Module_vector.f_sizeVector((l0_r).f_monos)==0)) {
			return Module_polynom.f_mizPolyWithNumeric(Module_polynom.g_mizCZeroP);
		} else {
			return l0_r;
		}
	}
	public static final int f_mizCompareMonomials(Struct_MizMonomial aa, Struct_MizMonomial ab) {
		final int l0_na = Module_mobjects.f_mizNatCount((aa).f_powers);
		final int l1_nb = Module_mobjects.f_mizNatCount((ab).f_powers);
		final Reference<Integer> l2_res = ((Reference<Integer>)(new Reference(0)));
		final Reference<Integer> l3_i = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			return ((Boolean)((((int)l2_res.value)==0)&&(((int)l3_i.value)<l0_na)));
		};
		final Struct_MizMonomial l8_a = aa;
		final Struct_MizMonomial l9_b = ab;
		final Func0<Object> l7_$5 = (Func0<Object>)() -> {
			if ((((int)l3_i.value)>=l1_nb)) {
				l2_res.value = ((Integer)(-1));
			} else {
				final Struct_MizIntPair l8_pa = Module_mobjects.f_mizNatItem((l8_a).f_powers, ((int)l3_i.value));
				final Struct_MizIntPair l9_pb = Module_mobjects.f_mizNatItem((l9_b).f_powers, ((int)l3_i.value));
				int l10_c1;
				if (((l8_pa).f_x<(l9_pb).f_x)) {
					l10_c1=(-1);
				} else {
					if (((l8_pa).f_x>(l9_pb).f_x)) {
						l10_c1=1;
					} else {
						l10_c1=0;
					}
				}
				if ((l10_c1!=0)) {
					l2_res.value = ((Integer)l10_c1);
				} else {
					int l11_c2;
					if (((l8_pa).f_y<(l9_pb).f_y)) {
						l11_c2=1;
					} else {
						if (((l8_pa).f_y>(l9_pb).f_y)) {
							l11_c2=(-1);
						} else {
							l11_c2=0;
						}
					}
					if ((l11_c2!=0)) {
						l2_res.value = ((Integer)l11_c2);
					} else {
					}
				}
			}
			l3_i.value = ((Integer)(((int)l3_i.value)+1));
			return null;
		};
		Module_loop.f_while(l6_$4, l7_$5);
		if ((((int)l2_res.value)!=0)) {
			return ((int)l2_res.value);
		} else {
			if ((l0_na<l1_nb)) {
				return 1;
			} else {
				if ((l0_na>l1_nb)) {
					return (-1);
				} else {
					return 0;
				}
			}
		}
	}
	public static final int f_mizComparePolynomials(Struct_MizPolynomial aa, Struct_MizPolynomial ab) {
		final int l0_na = Module_vector.f_sizeVector((aa).f_monos);
		final int l1_nb = Module_vector.f_sizeVector((ab).f_monos);
		if ((l1_nb==0)) {
			return 1;
		} else {
			if ((l0_na==0)) {
				return (-1);
			} else {
				final Reference<Integer> l2_res = ((Reference<Integer>)(new Reference(0)));
				final Reference<Integer> l3_i = ((Reference<Integer>)(new Reference(0)));
				final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
					return ((Boolean)(((((int)l2_res.value)==0)&&(((int)l3_i.value)<l0_na))&&(((int)l3_i.value)<l1_nb)));
				};
				final Struct_MizPolynomial l8_a = aa;
				final Struct_MizPolynomial l9_b = ab;
				final Func0<Object> l7_$5 = (Func0<Object>)() -> {
					final Struct_MizMonomial l8_ma = ((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((l8_a).f_monos, ((int)l3_i.value)));
					final Struct_MizMonomial l9_mb = ((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((l9_b).f_monos, ((int)l3_i.value)));
					final int l10_c1 = Module_polynom.f_mizCompareMonomials(l8_ma, l9_mb);
					if ((l10_c1!=0)) {
						l2_res.value = ((Integer)l10_c1);
					} else {
						final int l11_c2 = Module_polynom.f_cCmp((l8_ma).f_coeff, (l9_mb).f_coeff);
						if ((l11_c2!=0)) {
							l2_res.value = ((Integer)l11_c2);
						} else {
						}
					}
					l3_i.value = ((Integer)(((int)l3_i.value)+1));
					return null;
				};
				Module_loop.f_while(l6_$4, l7_$5);
				if ((((int)l2_res.value)!=0)) {
					return ((int)l2_res.value);
				} else {
					if ((l0_na<l1_nb)) {
						return (-1);
					} else {
						if ((l0_na>l1_nb)) {
							return 1;
						} else {
							return 0;
						}
					}
				}
			}
		}
	}
	public static final Struct_MizMonomial f_mizCopyMonomial(Struct_MizMonomial am) {
		return (new Struct_MizMonomial((am).f_coeff, Module_mobjects.f_mizCopyNatFunc((am).f_powers)));
	}
	public static final Struct_MizPolynomial f_mizCopyPoly(Struct_MizPolynomial ap) {
		final Struct_MizPolynomial l0_r = (new Struct_MizPolynomial(Module_vector.f_makeVector(Module_vector.f_sizeVector((ap).f_monos))));
		final Struct_Vector l3_$2 = (ap).f_monos;
		final Func1<Object,Struct_MizMonomial> l4_$1 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial am) -> {
			return Module_vector.f_pushVector((l0_r).f_monos, Module_polynom.f_mizCopyMonomial(am));
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		return l0_r;
	}
	public static final Struct_MizPolynomial f_mizDiffPolynomials(Struct_MizPolynomial aa, Struct_MizPolynomial ab) {
		final Struct_MizPolynomial l0_r = (new Struct_MizPolynomial(Module_vector.f_makeVector((Module_vector.f_sizeVector((aa).f_monos)+Module_vector.f_sizeVector((ab).f_monos)))));
		final Struct_Vector l3_$2 = (aa).f_monos;
		final Func1<Object,Struct_MizMonomial> l4_$1 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial am) -> {
			return Module_polynom.f_mizPolyInsert(l0_r, Module_polynom.f_mizCopyMonomial(am));
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		final Struct_Vector l7_$6 = (ab).f_monos;
		final Func1<Object,Struct_MizMonomial> l8_$5 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial am) -> {
			final Struct_MizMonomial l9_nm = Module_polynom.f_mizCopyMonomial(am);
			((Field_coeff)l9_nm).set_coeff(Module_polynom.f_cNeg((am).f_coeff));
			return Module_polynom.f_mizPolyInsert(l0_r, l9_nm);
		};
		Module_vector.f_iterVector(l7_$6, ((Func1<Object,Object>)(Func1)l8_$5));
		if ((Module_vector.f_sizeVector((l0_r).f_monos)==0)) {
			return Module_polynom.f_mizPolyWithNumeric(Module_polynom.g_mizCZeroP);
		} else {
			return l0_r;
		}
	}
	public static final Struct_MizPolynomial f_mizMMultPolynomial(Struct_MizMonomial am, Struct_MizPolynomial ap) {
		final Struct_MizPolynomial l0_r = (new Struct_MizPolynomial(Module_vector.f_makeVector(Module_vector.f_sizeVector((ap).f_monos))));
		final Struct_Vector l3_$2 = (ap).f_monos;
		final Struct_MizMonomial l5_m = am;
		final Func1<Object,Struct_MizMonomial> l4_$1 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial apm) -> {
			final Struct_MizMonomial l5_nm = (new Struct_MizMonomial(Module_polynom.f_cMul((l5_m).f_coeff, (apm).f_coeff), Module_mobjects.f_mizCopyNatFunc((l5_m).f_powers)));
			Module_polynom.f_mizNatAddPointwise((l5_nm).f_powers, (apm).f_powers);
			return Module_polynom.f_mizPolyInsert(l0_r, l5_nm);
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		if ((Module_vector.f_sizeVector((l0_r).f_monos)==0)) {
			return Module_polynom.f_mizPolyWithNumeric(Module_polynom.g_mizCZeroP);
		} else {
			return l0_r;
		}
	}
	public static final int f_mizMonoIsVariable(Struct_MizMonomial am) {
		if ((((Module_mobjects.f_mizNatCount((am).f_powers)==1)&&((Module_mobjects.f_mizNatItem((am).f_powers, 0)).f_y==1))&&Module_polynom.f_cEq1((am).f_coeff))) {
			return (Module_mobjects.f_mizNatItem((am).f_powers, 0)).f_x;
		} else {
			return 0;
		}
	}
	public static final Struct_MizPolynomial f_mizMultPolynomials(Struct_MizPolynomial aa, Struct_MizPolynomial ab) {
		if ((Module_polynom.f_mizPolyIsNumericEqualWith(aa, Module_polynom.g_mizCZeroP)||Module_polynom.f_mizPolyIsNumericEqualWith(ab, Module_polynom.g_mizCZeroP))) {
			return Module_polynom.f_mizPolyWithNumeric(Module_polynom.g_mizCZeroP);
		} else {
			final Struct_MizPolynomial l0_r = (new Struct_MizPolynomial(Module_vector.f_makeVector((Module_vector.f_sizeVector((aa).f_monos)*Module_vector.f_sizeVector((ab).f_monos)))));
			final Struct_Vector l3_$2 = (aa).f_monos;
			final Struct_MizPolynomial l5_b = ab;
			final Func1<Object,Struct_MizMonomial> l4_$1 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial ama) -> {
				final Struct_Vector l7_$6 = (l5_b).f_monos;
				final Func1<Object,Struct_MizMonomial> l8_$5 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial amb) -> {
					final Struct_MizMonomial l9_nm = (new Struct_MizMonomial(Module_polynom.f_cMul((ama).f_coeff, (amb).f_coeff), Module_mobjects.f_mizCopyNatFunc((ama).f_powers)));
					Module_polynom.f_mizNatAddPointwise((l9_nm).f_powers, (amb).f_powers);
					return Module_polynom.f_mizPolyInsert(l0_r, l9_nm);
				};
				return Module_vector.f_iterVector(l7_$6, ((Func1<Object,Object>)(Func1)l8_$5));
			};
			Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
			if ((Module_vector.f_sizeVector((l0_r).f_monos)==0)) {
				return Module_polynom.f_mizPolyWithNumeric(Module_polynom.g_mizCZeroP);
			} else {
				return l0_r;
			}
		}
	}
	public static final Struct_MizPolynomial f_mizNMultPolynomial(Struct_MizComplex av, Struct_MizPolynomial ap) {
		if (Module_polynom.f_cEq0(av)) {
			return Module_polynom.f_mizPolyWithNumeric(av);
		} else {
			final Struct_MizPolynomial l0_r = Module_polynom.f_mizCopyPoly(ap);
			if (!Module_polynom.f_cEq1(av)) {
				final Struct_Vector l3_$2 = (l0_r).f_monos;
				final Struct_MizComplex l5_v = av;
				final Func1<Object,Struct_MizMonomial> l4_$1 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial am) -> {
					((Field_coeff)am).set_coeff(Module_polynom.f_cMul(l5_v, (am).f_coeff));
					return null;
				};
				Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
			} else {
			}
			return l0_r;
		}
	}
	public static final Object f_mizNatAddPointwise(Struct_MizNatFunc af, Struct_MizNatFunc ag) {
		final int l2_$1 = (Module_mobjects.f_mizNatCount(ag)-1);
		final Struct_MizNatFunc l4_g = ag;
		final Struct_MizNatFunc l5_f = af;
		final Func1<Object,Integer> l3_$0 = (Func1<Object, Integer>)(Integer ai) -> {
			final Struct_MizIntPair l4_it = Module_mobjects.f_mizNatItem(l4_g, ((int)ai));
			final int l7_$6 = (l4_it).f_y;
			final Func1<Object,Integer> l8_$5 = (Func1<Object, Integer>)(Integer al9_0) -> {
				return Module_mobjects.f_mizNatUp(l5_f, (l4_it).f_x);
			};
			return Module_runtime.f_fori(1, l7_$6, l8_$5);
		};
		return Module_runtime.f_fori(0, l2_$1, l3_$0);
	}
	public static final int f_mizNatCompareGraph(Struct_MizNatFunc af, Struct_MizNatFunc ag) {
		final int l0_nf = Module_mobjects.f_mizNatCount(af);
		final int l1_ng = Module_mobjects.f_mizNatCount(ag);
		final Reference<Integer> l2_res = ((Reference<Integer>)(new Reference(0)));
		final Reference<Integer> l3_i = ((Reference<Integer>)(new Reference(0)));
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			return ((Boolean)(((((int)l2_res.value)==0)&&(((int)l3_i.value)<l0_nf))&&(((int)l3_i.value)<l1_ng)));
		};
		final Struct_MizNatFunc l8_f = af;
		final Struct_MizNatFunc l9_g = ag;
		final Func0<Object> l7_$5 = (Func0<Object>)() -> {
			final Struct_MizIntPair l8_pf = Module_mobjects.f_mizNatItem(l8_f, ((int)l3_i.value));
			final Struct_MizIntPair l9_pg = Module_mobjects.f_mizNatItem(l9_g, ((int)l3_i.value));
			if (((l8_pf).f_x!=(l9_pg).f_x)) {
				l2_res.value = ((Integer)(((l8_pf).f_x<(l9_pg).f_x)?(-1):1));
			} else {
				if (((l8_pf).f_y!=(l9_pg).f_y)) {
					l2_res.value = ((Integer)(((l8_pf).f_y<(l9_pg).f_y)?(-1):1));
				} else {
				}
			}
			l3_i.value = ((Integer)(((int)l3_i.value)+1));
			return null;
		};
		Module_loop.f_while(l6_$4, l7_$5);
		if ((((int)l2_res.value)!=0)) {
			return ((int)l2_res.value);
		} else {
			if ((l0_nf<l1_ng)) {
				return (-1);
			} else {
				if ((l0_nf>l1_ng)) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
	public static final Struct_MizMonomial f_mizNewMonomial(Struct_MizComplex ac) {
		return (new Struct_MizMonomial(ac, Module_mobjects.f_mizInitNatFunc(4)));
	}
	public static final Struct_MizPolynomial f_mizNthPower(Struct_MizPolynomial ap, int an) {
		if ((an==0)) {
			return Module_polynom.f_mizPolyWithNumeric(Module_polynom.g_mizCOneP);
		} else {
			final Reference<Struct_MizPolynomial> l0_r = ((Reference<Struct_MizPolynomial>)(new Reference(Module_polynom.f_mizCopyPoly(ap))));
			final Struct_MizPolynomial l3_p = ap;
			final Func1<Object,Integer> l2_$1 = (Func1<Object, Integer>)(Integer al3_0) -> {
				l0_r.value = Module_polynom.f_mizMultPolynomials(l0_r.value, l3_p);
				return null;
			};
			Module_runtime.f_fori(2, an, l2_$1);
			return l0_r.value;
		}
	}
	public static final Struct_MizComplex f_mizPolyGetNumeric(Struct_MizPolynomial ap) {
		return (((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((ap).f_monos, 0))).f_coeff;
	}
	public static final boolean f_mizPolyHasTheVariable(Struct_MizPolynomial ap, int avarNr) {
		final Reference<Boolean> l0_r = ((Reference<Boolean>)(new Reference(false)));
		final Struct_Vector l3_$2 = (ap).f_monos;
		final int l5_varNr = avarNr;
		final Func1<Object,Struct_MizMonomial> l4_$1 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial am) -> {
			if (Module_mobjects.f_mizNatHasInDom((am).f_powers, l5_varNr)) {
				l0_r.value = ((Boolean)true);
				return null;
			} else {
				return null;
			}
		};
		Module_vector.f_iterVector(l3_$2, ((Func1<Object,Object>)(Func1)l4_$1));
		return ((boolean)l0_r.value);
	}
	public static final Object f_mizPolyInsert(Struct_MizPolynomial ap, Struct_MizMonomial am) {
		final int l0_n = Module_vector.f_sizeVector((ap).f_monos);
		final Reference<Integer> l1_pos = ((Reference<Integer>)(new Reference(0)));
		final Reference<Boolean> l2_found = ((Reference<Boolean>)(new Reference(false)));
		final Reference<Boolean> l3_brk = ((Reference<Boolean>)(new Reference(false)));
		final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
			return ((Boolean)(!((boolean)l3_brk.value)&&(((int)l1_pos.value)<l0_n)));
		};
		final Struct_MizPolynomial l8_p = ap;
		final Struct_MizMonomial l9_m = am;
		final Func0<Object> l7_$5 = (Func0<Object>)() -> {
			final int l8_c = Module_polynom.f_mizCompareMonomials(((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((l8_p).f_monos, ((int)l1_pos.value))), l9_m);
			if ((l8_c==0)) {
				l2_found.value = ((Boolean)true);
				l3_brk.value = ((Boolean)true);
				return null;
			} else {
				if ((l8_c>0)) {
					l3_brk.value = ((Boolean)true);
					return null;
				} else {
					l1_pos.value = ((Integer)(((int)l1_pos.value)+1));
					return null;
				}
			}
		};
		Module_loop.f_while(l6_$4, l7_$5);
		if (((boolean)l2_found.value)) {
			final Struct_MizMonomial l10_e = ((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((ap).f_monos, ((int)l1_pos.value)));
			final Struct_MizComplex l11_v = Module_polynom.f_cAdd((am).f_coeff, (l10_e).f_coeff);
			if (Module_polynom.f_cEq0(l11_v)) {
				if ((Module_vector.f_sizeVector((ap).f_monos)>1)) {
					return Module_vector.f_removeVector((ap).f_monos, ((int)l1_pos.value));
				} else {
					Module_mobjects.f_mizNatDeleteAll((l10_e).f_powers);
					((Field_coeff)l10_e).set_coeff(l11_v);
					return null;
				}
			} else {
				((Field_coeff)l10_e).set_coeff(l11_v);
				return null;
			}
		} else {
			if ((Module_polynom.f_cEq0((am).f_coeff)&&(Module_vector.f_sizeVector((ap).f_monos)>0))) {
				return null;
			} else {
				if (((Module_vector.f_sizeVector((ap).f_monos)>0)&&Module_polynom.f_cEq0((((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((ap).f_monos, 0))).f_coeff))) {
					Module_vector.f_removeVector((ap).f_monos, 0);
					l1_pos.value = ((Integer)Native.fast_max(0, (((int)l1_pos.value)-1)));
				} else {
				}
				return Module_polynom.f_mizVectorAtInsertM((ap).f_monos, ((int)l1_pos.value), am);
			}
		}
	}
	public static final Object f_mizPolyInsertValue(Struct_MizPolynomial ap, int avarNr, Struct_MizPolynomial aq) {
		final Struct_Vector l0_old = Module_vector.f_copyVector((ap).f_monos);
		Module_vector.f_clearVector((ap).f_monos);
		final int l3_varNr = avarNr;
		final Struct_MizPolynomial l4_q = aq;
		final Struct_MizPolynomial l5_p = ap;
		final Func1<Object,Struct_MizMonomial> l2_$1 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial am) -> {
			if (Module_mobjects.f_mizNatHasInDom((am).f_powers, l3_varNr)) {
				if (!Module_polynom.f_mizPolyIsNumericEqualWith(l4_q, Module_polynom.g_mizCZeroP)) {
					final int l3_power = Module_mobjects.f_mizNatValue((am).f_powers, l3_varNr);
					Module_mobjects.f_mizNatDeleteElem((am).f_powers, l3_varNr);
					final Struct_MizPolynomial l4_qp = Module_polynom.f_mizNthPower(l4_q, l3_power);
					final Struct_MizPolynomial l5_mp = Module_polynom.f_mizMMultPolynomial(am, l4_qp);
					final Struct_Vector l8_$7 = (l5_mp).f_monos;
					final Func1<Object,Struct_MizMonomial> l9_$6 = (Func1<Object, Struct_MizMonomial>)(Struct_MizMonomial anm) -> {
						return Module_polynom.f_mizPolyInsert(l5_p, anm);
					};
					return Module_vector.f_iterVector(l8_$7, ((Func1<Object,Object>)(Func1)l9_$6));
				} else {
					return null;
				}
			} else {
				return Module_polynom.f_mizPolyInsert(l5_p, am);
			}
		};
		Module_vector.f_iterVector(l0_old, ((Func1<Object,Object>)(Func1)l2_$1));
		if ((Module_vector.f_sizeVector((ap).f_monos)==0)) {
			return Module_vector.f_pushVector((ap).f_monos, Module_polynom.f_mizNewMonomial(Module_polynom.g_mizCZeroP));
		} else {
			return null;
		}
	}
	public static final boolean f_mizPolyIsNumeric(Struct_MizPolynomial ap) {
		return ((Module_vector.f_sizeVector((ap).f_monos)==1)&&(Module_mobjects.f_mizNatCount((((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((ap).f_monos, 0))).f_powers)==0));
	}
	public static final boolean f_mizPolyIsNumericEqualWith(Struct_MizPolynomial ap, Struct_MizComplex av) {
		return (Module_polynom.f_mizPolyIsNumeric(ap)&&(Module_polynom.f_cCmp((((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((ap).f_monos, 0))).f_coeff, av)==0));
	}
	public static final int f_mizPolyIsUniVariantVariable(Struct_MizPolynomial ap) {
		if ((Module_vector.f_sizeVector((ap).f_monos)==1)) {
			return Module_polynom.f_mizMonoIsVariable(((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((ap).f_monos, 0)));
		} else {
			return 0;
		}
	}
	public static final int f_mizPolyIsVariable(Struct_MizPolynomial ap) {
		if ((Module_vector.f_sizeVector((ap).f_monos)==1)) {
			return Module_polynom.f_mizMonoIsVariable(((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((ap).f_monos, 0)));
		} else {
			return 0;
		}
	}
	public static final Struct_MizPolynomial f_mizPolyWithMonomial(Struct_MizComplex ac, int avarNr) {
		final Struct_MizMonomial l0_m = Module_polynom.f_mizNewMonomial(ac);
		Module_mobjects.f_mizNatAssign((l0_m).f_powers, avarNr, 1);
		final Struct_MizPolynomial l1_p = (new Struct_MizPolynomial(Module_vector.f_makeVector(2)));
		Module_vector.f_pushVector((l1_p).f_monos, l0_m);
		return l1_p;
	}
	public static final Struct_MizPolynomial f_mizPolyWithNumeric(Struct_MizComplex av) {
		final Struct_MizPolynomial l0_p = (new Struct_MizPolynomial(Module_vector.f_makeVector(2)));
		Module_vector.f_pushVector((l0_p).f_monos, Module_polynom.f_mizNewMonomial(av));
		return l0_p;
	}
	public static final int f_mizStdCompareMonomials(Struct_MizMonomial aa, Struct_MizMonomial ab) {
		final int l0_ca = Module_mobjects.f_mizNatCountAll((aa).f_powers);
		final int l1_cb = Module_mobjects.f_mizNatCountAll((ab).f_powers);
		if ((l0_ca!=l1_cb)) {
			if ((l0_ca<l1_cb)) {
				return (-1);
			} else {
				return 1;
			}
		} else {
			return Module_polynom.f_mizNatCompareGraph((aa).f_powers, (ab).f_powers);
		}
	}
	public static final int f_mizStdComparePolynomials(Struct_MizPolynomial aa, Struct_MizPolynomial ab) {
		final int l0_na = Module_vector.f_sizeVector((aa).f_monos);
		final int l1_nb = Module_vector.f_sizeVector((ab).f_monos);
		if ((l0_na!=l1_nb)) {
			if ((l0_na<l1_nb)) {
				return (-1);
			} else {
				return 1;
			}
		} else {
			final Reference<Integer> l2_res = ((Reference<Integer>)(new Reference(0)));
			final Reference<Integer> l3_i = ((Reference<Integer>)(new Reference(0)));
			final Func0<Boolean> l6_$4 = (Func0<Boolean>)() -> {
				return ((Boolean)((((int)l2_res.value)==0)&&(((int)l3_i.value)<l0_na)));
			};
			final Struct_MizPolynomial l8_a = aa;
			final Struct_MizPolynomial l9_b = ab;
			final Func0<Object> l7_$5 = (Func0<Object>)() -> {
				final Struct_MizMonomial l8_ma = ((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((l8_a).f_monos, ((int)l3_i.value)));
				final Struct_MizMonomial l9_mb = ((Struct_MizMonomial)Module_vector.f_getVectorUnsafe((l9_b).f_monos, ((int)l3_i.value)));
				final int l10_c1 = Module_polynom.f_mizStdCompareMonomials(l8_ma, l9_mb);
				if ((l10_c1!=0)) {
					l2_res.value = ((Integer)l10_c1);
				} else {
					final int l11_c2 = Module_polynom.f_cCmp((l8_ma).f_coeff, (l9_mb).f_coeff);
					if ((l11_c2!=0)) {
						l2_res.value = ((Integer)l11_c2);
					} else {
					}
				}
				l3_i.value = ((Integer)(((int)l3_i.value)+1));
				return null;
			};
			Module_loop.f_while(l6_$4, l7_$5);
			return ((int)l2_res.value);
		}
	}
	public static final Object f_mizVectorAtInsertM(Struct_Vector av, int apos, Struct_MizMonomial am) {
		Module_vector.f_pushVector(av, am);
		final Reference<Integer> l0_i = ((Reference<Integer>)(new Reference((Module_vector.f_sizeVector(av)-1))));
		final int l4_pos = apos;
		final Func0<Boolean> l3_$1 = (Func0<Boolean>)() -> {
			return (((int)l0_i.value)>l4_pos);
		};
		final Struct_Vector l6_v = av;
		final Func0<Object> l5_$2 = (Func0<Object>)() -> {
			Module_vector.f_setVectorUnsafe(l6_v, ((int)l0_i.value), Module_vector.f_getVectorUnsafe(l6_v, (((int)l0_i.value)-1)));
			l0_i.value = ((Integer)(((int)l0_i.value)-1));
			return null;
		};
		Module_loop.f_while(l3_$1, l5_$2);
		return Module_vector.f_setVectorUnsafe(av, apos, am);
	}
}
