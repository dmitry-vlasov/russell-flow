// HASH COLLISIONS: YES
// timestamp: 1785508391383

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizBinFrm extends Struct implements Field_sort<Integer> {
	public int f_sort;
	public Struct f_left;
	public Struct f_right;
	public int f_pattNr;

	public Struct_MizBinFrm() {}
	public Struct_MizBinFrm(int a_sort, Struct a_left, Struct a_right, int a_pattNr) {
		f_sort = a_sort;
		f_left = a_left;
		f_right = a_right;
		f_pattNr = a_pattNr;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }

	public int getTypeId() { return 17; }
	public String getTypeName() { return "MizBinFrm"; }

	private static final String[] field_names = new String[] {
		"sort", "left", "right", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort, f_left, f_right, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 4)
			throw new IndexOutOfBoundsException("Invalid field count in MizBinFrm");
		f_sort = (Integer)values[0];
		f_left = (Struct)values[1];
		f_right = (Struct)values[2];
		f_pattNr = (Integer)values[3];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 17) return 17-tmp;
		Struct_MizBinFrm other = (Struct_MizBinFrm)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		tmp = f_left.compareTo(other.f_left);
		if (tmp != 0) return tmp;
		tmp = f_right.compareTo(other.f_right);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
