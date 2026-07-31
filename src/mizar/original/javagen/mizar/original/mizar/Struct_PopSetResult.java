// HASH COLLISIONS: YES
// timestamp: 1785508391390

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_PopSetResult extends Struct {
	public Object f_value;
	public Struct_Set f_rest;

	public Struct_PopSetResult() {}
	public Struct_PopSetResult(Object a_value, Struct_Set a_rest) {
		f_value = a_value;
		f_rest = a_rest;
	}

	public int getTypeId() { return 80; }
	public String getTypeName() { return "PopSetResult"; }

	private static final String[] field_names = new String[] {
		"value", "rest"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.UNKNOWN, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_value, f_rest
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in PopSetResult");
		f_value = (Object)values[0];
		f_rest = (Struct_Set)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 80) return 80-tmp;
		Struct_PopSetResult other = (Struct_PopSetResult)other_gen;
		tmp = FlowRuntime.compareByValue(f_value, other.f_value);
		if (tmp != 0) return tmp;
		tmp = f_rest.compareTo(other.f_rest);
		return tmp;
	}
}
