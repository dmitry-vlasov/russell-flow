// HASH COLLISIONS: YES
// timestamp: 1785512695558

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_VerboseOutput extends Struct {
	public Func0<Boolean> f_fn;

	public Struct_VerboseOutput() {}
	public Struct_VerboseOutput(Func0<Boolean> a_fn) {
		f_fn = a_fn;
	}

	public int getTypeId() { return 93; }
	public String getTypeName() { return "VerboseOutput"; }

	private static final String[] field_names = new String[] {
		"fn"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.UNKNOWN
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_fn
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 1)
			throw new IndexOutOfBoundsException("Invalid field count in VerboseOutput");
		f_fn = (Func0<Boolean>)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 93) return 93-tmp;
		Struct_VerboseOutput other = (Struct_VerboseOutput)other_gen;
		tmp = FlowRuntime.compareByValue(f_fn, other.f_fn);
		return tmp;
	}
}
