// HASH COLLISIONS: YES
// timestamp: 1785512695546

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizComplex extends Struct {
	public double f_re;
	public double f_im;

	public Struct_MizComplex() {}
	public Struct_MizComplex(double a_re, double a_im) {
		f_re = a_re;
		f_im = a_im;
	}

	public int getTypeId() { return 25; }
	public String getTypeName() { return "MizComplex"; }

	private static final String[] field_names = new String[] {
		"re", "im"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.DOUBLE, RuntimeType.DOUBLE
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_re, f_im
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizComplex");
		f_re = (Double)values[0];
		f_im = (Double)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 25) return 25-tmp;
		Struct_MizComplex other = (Struct_MizComplex)other_gen;
		if (f_re != other.f_re)
			return (f_re > other.f_re) ? 1 : -1;
		if (f_im != other.f_im)
			return (f_im > other.f_im) ? 1 : -1;
		return 0;
	}
}
