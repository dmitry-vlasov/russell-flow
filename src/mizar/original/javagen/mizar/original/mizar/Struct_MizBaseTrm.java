// HASH COLLISIONS: YES
// timestamp: 1785524849102

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizBaseTrm extends Struct implements Field_sort<Integer>, Field_trmInfo<Integer> {
	public int f_sort;
	public int f_trmInfo;

	public Struct_MizBaseTrm() {}
	public Struct_MizBaseTrm(int a_sort, int a_trmInfo) {
		f_sort = a_sort;
		f_trmInfo = a_trmInfo;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }
	public Integer get_trmInfo() { return f_trmInfo; }
	public void set_trmInfo(Integer value) { f_trmInfo = value; }

	public int getTypeId() { return 16; }
	public String getTypeName() { return "MizBaseTrm"; }

	private static final String[] field_names = new String[] {
		"sort", "trmInfo"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort, f_trmInfo
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizBaseTrm");
		f_sort = (Integer)values[0];
		f_trmInfo = (Integer)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 16) return 16-tmp;
		Struct_MizBaseTrm other = (Struct_MizBaseTrm)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		if (f_trmInfo != other.f_trmInfo)
			return (f_trmInfo > other.f_trmInfo) ? 1 : -1;
		return 0;
	}
}
