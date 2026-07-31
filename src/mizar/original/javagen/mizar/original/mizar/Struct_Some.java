// HASH COLLISIONS: YES
// timestamp: 1785512695557

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_Some extends Struct {
	public Object f_value;

	public Struct_Some() {}
	public Struct_Some(Object a_value) {
		f_value = a_value;
	}

	public int getTypeId() { return 88; }
	public String getTypeName() { return "Some"; }

	private static final String[] field_names = new String[] {
		"value"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.UNKNOWN
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_value
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 1)
			throw new IndexOutOfBoundsException("Invalid field count in Some");
		f_value = (Object)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 88) return 88-tmp;
		Struct_Some other = (Struct_Some)other_gen;
		tmp = FlowRuntime.compareByValue(f_value, other.f_value);
		return tmp;
	}
}
