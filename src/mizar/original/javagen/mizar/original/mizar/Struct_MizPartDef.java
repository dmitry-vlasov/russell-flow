// HASH COLLISIONS: YES
// timestamp: 1785512695551

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizPartDef extends Struct {
	public Struct f_body;
	public Struct f_guard;

	public Struct_MizPartDef() {}
	public Struct_MizPartDef(Struct a_body, Struct a_guard) {
		f_body = a_body;
		f_guard = a_guard;
	}

	public int getTypeId() { return 52; }
	public String getTypeName() { return "MizPartDef"; }

	private static final String[] field_names = new String[] {
		"body", "guard"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_body, f_guard
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizPartDef");
		f_body = (Struct)values[0];
		f_guard = (Struct)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 52) return 52-tmp;
		Struct_MizPartDef other = (Struct_MizPartDef)other_gen;
		tmp = f_body.compareTo(other.f_body);
		if (tmp != 0) return tmp;
		tmp = f_guard.compareTo(other.f_guard);
		return tmp;
	}
}
