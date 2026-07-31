// HASH COLLISIONS: YES
// timestamp: 1785524849107

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizMonomial extends Struct implements Field_coeff<Struct_MizComplex> {
	public Struct_MizComplex f_coeff;
	public Struct_MizNatFunc f_powers;

	public Struct_MizMonomial() {}
	public Struct_MizMonomial(Struct_MizComplex a_coeff, Struct_MizNatFunc a_powers) {
		f_coeff = a_coeff;
		f_powers = a_powers;
	}
	public Struct_MizComplex get_coeff() { return f_coeff; }
	public void set_coeff(Struct_MizComplex value) { f_coeff = value; }

	public int getTypeId() { return 50; }
	public String getTypeName() { return "MizMonomial"; }

	private static final String[] field_names = new String[] {
		"coeff", "powers"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_coeff, f_powers
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizMonomial");
		f_coeff = (Struct_MizComplex)values[0];
		f_powers = (Struct_MizNatFunc)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 50) return 50-tmp;
		Struct_MizMonomial other = (Struct_MizMonomial)other_gen;
		tmp = f_coeff.compareTo(other.f_coeff);
		if (tmp != 0) return tmp;
		tmp = f_powers.compareTo(other.f_powers);
		return tmp;
	}
}
