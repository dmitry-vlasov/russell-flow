// HASH COLLISIONS: YES
// timestamp: 1785508391383

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizBuiltIn extends Struct {
	public Struct_Vector f_nrs;

	public Struct_MizBuiltIn() {}
	public Struct_MizBuiltIn(Struct_Vector a_nrs) {
		f_nrs = a_nrs;
	}

	public int getTypeId() { return 18; }
	public String getTypeName() { return "MizBuiltIn"; }

	private static final String[] field_names = new String[] {
		"nrs"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_nrs
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 1)
			throw new IndexOutOfBoundsException("Invalid field count in MizBuiltIn");
		f_nrs = (Struct_Vector)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 18) return 18-tmp;
		Struct_MizBuiltIn other = (Struct_MizBuiltIn)other_gen;
		tmp = f_nrs.compareTo(other.f_nrs);
		return tmp;
	}
}
