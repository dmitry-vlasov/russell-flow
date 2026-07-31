// HASH COLLISIONS: YES
// timestamp: 1785524849109

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizSchemes extends Struct {
	public Struct_Vector f_items;

	public Struct_MizSchemes() {}
	public Struct_MizSchemes(Struct_Vector a_items) {
		f_items = a_items;
	}

	public int getTypeId() { return 69; }
	public String getTypeName() { return "MizSchemes"; }

	private static final String[] field_names = new String[] {
		"items"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_items
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 1)
			throw new IndexOutOfBoundsException("Invalid field count in MizSchemes");
		f_items = (Struct_Vector)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 69) return 69-tmp;
		Struct_MizSchemes other = (Struct_MizSchemes)other_gen;
		tmp = f_items.compareTo(other.f_items);
		return tmp;
	}
}
