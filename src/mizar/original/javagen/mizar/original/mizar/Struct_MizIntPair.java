// HASH COLLISIONS: YES
// timestamp: 1785524849106

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizIntPair extends Struct {
	public int f_x;
	public int f_y;

	public Struct_MizIntPair() {}
	public Struct_MizIntPair(int a_x, int a_y) {
		f_x = a_x;
		f_y = a_y;
	}

	public int getTypeId() { return 47; }
	public String getTypeName() { return "MizIntPair"; }

	private static final String[] field_names = new String[] {
		"x", "y"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_x, f_y
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizIntPair");
		f_x = (Integer)values[0];
		f_y = (Integer)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 47) return 47-tmp;
		Struct_MizIntPair other = (Struct_MizIntPair)other_gen;
		if (f_x != other.f_x)
			return (f_x > other.f_x) ? 1 : -1;
		if (f_y != other.f_y)
			return (f_y > other.f_y) ? 1 : -1;
		return 0;
	}
}
