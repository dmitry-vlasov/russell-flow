// HASH COLLISIONS: YES
// timestamp: 1785512695557

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_PopResult extends Struct {
	public Object f_k;
	public Object f_v;
	public Struct f_rest;

	public Struct_PopResult() {}
	public Struct_PopResult(Object a_k, Object a_v, Struct a_rest) {
		f_k = a_k;
		f_v = a_v;
		f_rest = a_rest;
	}

	public int getTypeId() { return 84; }
	public String getTypeName() { return "PopResult"; }

	private static final String[] field_names = new String[] {
		"k", "v", "rest"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.UNKNOWN, RuntimeType.UNKNOWN, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_k, f_v, f_rest
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in PopResult");
		f_k = (Object)values[0];
		f_v = (Object)values[1];
		f_rest = (Struct)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 84) return 84-tmp;
		Struct_PopResult other = (Struct_PopResult)other_gen;
		tmp = FlowRuntime.compareByValue(f_k, other.f_k);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_v, other.f_v);
		if (tmp != 0) return tmp;
		tmp = f_rest.compareTo(other.f_rest);
		return tmp;
	}
}
