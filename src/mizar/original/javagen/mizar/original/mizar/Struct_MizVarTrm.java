// HASH COLLISIONS: YES
// timestamp: 1785512695556

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizVarTrm extends Struct implements Field_sort<Integer>, Field_varNr<Integer>, Field_trmInfo<Integer> {
	public int f_sort;
	public int f_varNr;
	public int f_pattNr;
	public int f_trmInfo;

	public Struct_MizVarTrm() {}
	public Struct_MizVarTrm(int a_sort, int a_varNr, int a_pattNr, int a_trmInfo) {
		f_sort = a_sort;
		f_varNr = a_varNr;
		f_pattNr = a_pattNr;
		f_trmInfo = a_trmInfo;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }
	public Integer get_varNr() { return f_varNr; }
	public void set_varNr(Integer value) { f_varNr = value; }
	public Integer get_trmInfo() { return f_trmInfo; }
	public void set_trmInfo(Integer value) { f_trmInfo = value; }

	public int getTypeId() { return 75; }
	public String getTypeName() { return "MizVarTrm"; }

	private static final String[] field_names = new String[] {
		"sort", "varNr", "pattNr", "trmInfo"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort, f_varNr, f_pattNr, f_trmInfo
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 4)
			throw new IndexOutOfBoundsException("Invalid field count in MizVarTrm");
		f_sort = (Integer)values[0];
		f_varNr = (Integer)values[1];
		f_pattNr = (Integer)values[2];
		f_trmInfo = (Integer)values[3];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 75) return 75-tmp;
		Struct_MizVarTrm other = (Struct_MizVarTrm)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		if (f_varNr != other.f_varNr)
			return (f_varNr > other.f_varNr) ? 1 : -1;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		if (f_trmInfo != other.f_trmInfo)
			return (f_trmInfo > other.f_trmInfo) ? 1 : -1;
		return 0;
	}
}
