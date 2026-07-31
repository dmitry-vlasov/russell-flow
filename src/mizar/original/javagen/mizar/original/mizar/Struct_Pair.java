// HASH COLLISIONS: YES
// timestamp: 1785508391390

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_Pair extends Struct {
	public Object f_first;
	public Object f_second;

	public Struct_Pair() {}
	public Struct_Pair(Object a_first, Object a_second) {
		f_first = a_first;
		f_second = a_second;
	}

	public int getTypeId() { return 77; }
	public String getTypeName() { return "Pair"; }

	private static final String[] field_names = new String[] {
		"first", "second"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.UNKNOWN, RuntimeType.UNKNOWN
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_first, f_second
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in Pair");
		f_first = (Object)values[0];
		f_second = (Object)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 77) return 77-tmp;
		Struct_Pair other = (Struct_Pair)other_gen;
		tmp = FlowRuntime.compareByValue(f_first, other.f_first);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_second, other.f_second);
		return tmp;
	}
}
