// HASH COLLISIONS: YES
// timestamp: 1785524849112

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_Vector extends Struct {
	public Object f_vect;
	public Struct f_defVal;

	public Struct_Vector() {}
	public Struct_Vector(Object a_vect, Struct a_defVal) {
		f_vect = a_vect;
		f_defVal = a_defVal;
	}

	public int getTypeId() { return 94; }
	public String getTypeName() { return "Vector"; }

	private static final String[] field_names = new String[] {
		"vect", "defVal"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.UNKNOWN, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_vect, f_defVal
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in Vector");
		f_vect = (Object)values[0];
		f_defVal = (Struct)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 94) return 94-tmp;
		Struct_Vector other = (Struct_Vector)other_gen;
		tmp = FlowRuntime.compareByValue(f_vect, other.f_vect);
		if (tmp != 0) return tmp;
		tmp = f_defVal.compareTo(other.f_defVal);
		return tmp;
	}
}
