// HASH COLLISIONS: YES
// timestamp: 1785508391384

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizConjFrm extends Struct {
	public Struct_Vector f_conjuncts;
	public int f_pattNr;

	public Struct_MizConjFrm() {}
	public Struct_MizConjFrm(Struct_Vector a_conjuncts, int a_pattNr) {
		f_conjuncts = a_conjuncts;
		f_pattNr = a_pattNr;
	}

	public int getTypeId() { return 26; }
	public String getTypeName() { return "MizConjFrm"; }

	private static final String[] field_names = new String[] {
		"conjuncts", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_conjuncts, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizConjFrm");
		f_conjuncts = (Struct_Vector)values[0];
		f_pattNr = (Integer)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 26) return 26-tmp;
		Struct_MizConjFrm other = (Struct_MizConjFrm)other_gen;
		tmp = f_conjuncts.compareTo(other.f_conjuncts);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
