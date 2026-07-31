// HASH COLLISIONS: YES
// timestamp: 1785524849107

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizPolynomial extends Struct {
	public Struct_Vector f_monos;

	public Struct_MizPolynomial() {}
	public Struct_MizPolynomial(Struct_Vector a_monos) {
		f_monos = a_monos;
	}

	public int getTypeId() { return 54; }
	public String getTypeName() { return "MizPolynomial"; }

	private static final String[] field_names = new String[] {
		"monos"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_monos
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 1)
			throw new IndexOutOfBoundsException("Invalid field count in MizPolynomial");
		f_monos = (Struct_Vector)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 54) return 54-tmp;
		Struct_MizPolynomial other = (Struct_MizPolynomial)other_gen;
		tmp = f_monos.compareTo(other.f_monos);
		return tmp;
	}
}
