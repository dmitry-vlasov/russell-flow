// HASH COLLISIONS: YES
// timestamp: 1785508391387

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizNatFunc extends Struct implements Field_consistent<Boolean> {
	public Struct_Vector f_items;
	public boolean f_consistent;

	public Struct_MizNatFunc() {}
	public Struct_MizNatFunc(Struct_Vector a_items, boolean a_consistent) {
		f_items = a_items;
		f_consistent = a_consistent;
	}
	public Boolean get_consistent() { return f_consistent; }
	public void set_consistent(Boolean value) { f_consistent = value; }

	public int getTypeId() { return 49; }
	public String getTypeName() { return "MizNatFunc"; }

	private static final String[] field_names = new String[] {
		"items", "consistent"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.BOOL
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_items, f_consistent
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizNatFunc");
		f_items = (Struct_Vector)values[0];
		f_consistent = (Boolean)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 49) return 49-tmp;
		Struct_MizNatFunc other = (Struct_MizNatFunc)other_gen;
		tmp = f_items.compareTo(other.f_items);
		if (tmp != 0) return tmp;
		if (f_consistent != other.f_consistent)
			return f_consistent ? 1 : -1;
		return 0;
	}
}
