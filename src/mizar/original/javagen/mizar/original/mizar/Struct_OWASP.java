// HASH COLLISIONS: YES
// timestamp: 1785524849111

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_OWASP extends Struct {
	public int f_level;

	public Struct_OWASP() {}
	public Struct_OWASP(int a_level) {
		f_level = a_level;
	}

	public int getTypeId() { return 83; }
	public String getTypeName() { return "OWASP"; }

	private static final String[] field_names = new String[] {
		"level"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_level
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 1)
			throw new IndexOutOfBoundsException("Invalid field count in OWASP");
		f_level = (Integer)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 83) return 83-tmp;
		Struct_OWASP other = (Struct_OWASP)other_gen;
		if (f_level != other.f_level)
			return (f_level > other.f_level) ? 1 : -1;
		return 0;
	}
}
