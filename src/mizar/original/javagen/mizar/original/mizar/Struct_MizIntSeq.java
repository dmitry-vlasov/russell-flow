// HASH COLLISIONS: YES
// timestamp: 1785508391387

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizIntSeq extends Struct {
	public Struct_Vector f_items;

	public Struct_MizIntSeq() {}
	public Struct_MizIntSeq(Struct_Vector a_items) {
		f_items = a_items;
	}

	public int getTypeId() { return 47; }
	public String getTypeName() { return "MizIntSeq"; }

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
			throw new IndexOutOfBoundsException("Invalid field count in MizIntSeq");
		f_items = (Struct_Vector)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 47) return 47-tmp;
		Struct_MizIntSeq other = (Struct_MizIntSeq)other_gen;
		tmp = f_items.compareTo(other.f_items);
		return tmp;
	}
}
