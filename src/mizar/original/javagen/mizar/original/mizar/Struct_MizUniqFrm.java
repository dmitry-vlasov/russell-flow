// HASH COLLISIONS: YES
// timestamp: 1785508391389

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizUniqFrm extends Struct implements Field_sort<Integer> {
	public int f_sort;

	public Struct_MizUniqFrm() {}
	public Struct_MizUniqFrm(int a_sort) {
		f_sort = a_sort;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }

	public int getTypeId() { return 68; }
	public String getTypeName() { return "MizUniqFrm"; }

	private static final String[] field_names = new String[] {
		"sort"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 1)
			throw new IndexOutOfBoundsException("Invalid field count in MizUniqFrm");
		f_sort = (Integer)values[0];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 68) return 68-tmp;
		Struct_MizUniqFrm other = (Struct_MizUniqFrm)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		return 0;
	}
}
