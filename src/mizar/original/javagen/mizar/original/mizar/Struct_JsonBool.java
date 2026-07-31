// HASH COLLISIONS: YES
// timestamp: 1785524849100

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_JsonBool extends Struct {
	public boolean f_v;

	public Struct_JsonBool() {}
	public Struct_JsonBool(boolean a_v) {
		f_v = a_v;
	}

	public int getTypeId() { return 5; }
	public String getTypeName() { return "JsonBool"; }

	private static final String[] field_names = new String[] {
		"v"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.BOOL
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_v
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 1)
			throw new IndexOutOfBoundsException("Invalid field count in JsonBool");
		f_v = (Boolean)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 5) return 5-tmp;
		Struct_JsonBool other = (Struct_JsonBool)other_gen;
		if (f_v != other.f_v)
			return f_v ? 1 : -1;
		return 0;
	}
}
