// HASH COLLISIONS: YES
// timestamp: 1785524849112

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_Set extends Struct {
	public Struct f_tree;

	public Struct_Set() {}
	public Struct_Set(Struct a_tree) {
		f_tree = a_tree;
	}

	public int getTypeId() { return 89; }
	public String getTypeName() { return "Set"; }

	private static final String[] field_names = new String[] {
		"tree"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_tree
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 1)
			throw new IndexOutOfBoundsException("Invalid field count in Set");
		f_tree = (Struct)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 89) return 89-tmp;
		Struct_Set other = (Struct_Set)other_gen;
		tmp = f_tree.compareTo(other.f_tree);
		return tmp;
	}
}
