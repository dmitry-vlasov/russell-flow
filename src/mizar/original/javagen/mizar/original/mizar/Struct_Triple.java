// HASH COLLISIONS: YES
// timestamp: 1785508391391

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_Triple extends Struct {
	public Object f_first;
	public Object f_second;
	public Object f_third;

	public Struct_Triple() {}
	public Struct_Triple(Object a_first, Object a_second, Object a_third) {
		f_first = a_first;
		f_second = a_second;
		f_third = a_third;
	}

	public int getTypeId() { return 86; }
	public String getTypeName() { return "Triple"; }

	private static final String[] field_names = new String[] {
		"first", "second", "third"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.UNKNOWN, RuntimeType.UNKNOWN, RuntimeType.UNKNOWN
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_first, f_second, f_third
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in Triple");
		f_first = (Object)values[0];
		f_second = (Object)values[1];
		f_third = (Object)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 86) return 86-tmp;
		Struct_Triple other = (Struct_Triple)other_gen;
		tmp = FlowRuntime.compareByValue(f_first, other.f_first);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_second, other.f_second);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_third, other.f_third);
		return tmp;
	}
}
