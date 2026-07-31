// HASH COLLISIONS: YES
// timestamp: 1785524849106

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizFuncTrm extends Struct implements Field_sort<Integer>, Field_trmInfo<Integer> {
	public int f_sort;
	public int f_funcNr;
	public Struct f_args;
	public int f_pattNr;
	public int f_trmInfo;

	public Struct_MizFuncTrm() {}
	public Struct_MizFuncTrm(int a_sort, int a_funcNr, Struct a_args, int a_pattNr, int a_trmInfo) {
		f_sort = a_sort;
		f_funcNr = a_funcNr;
		f_args = a_args;
		f_pattNr = a_pattNr;
		f_trmInfo = a_trmInfo;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }
	public Integer get_trmInfo() { return f_trmInfo; }
	public void set_trmInfo(Integer value) { f_trmInfo = value; }

	public int getTypeId() { return 45; }
	public String getTypeName() { return "MizFuncTrm"; }

	private static final String[] field_names = new String[] {
		"sort", "funcNr", "args", "pattNr", "trmInfo"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort, f_funcNr, f_args, f_pattNr, f_trmInfo
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizFuncTrm");
		f_sort = (Integer)values[0];
		f_funcNr = (Integer)values[1];
		f_args = (Struct)values[2];
		f_pattNr = (Integer)values[3];
		f_trmInfo = (Integer)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 45) return 45-tmp;
		Struct_MizFuncTrm other = (Struct_MizFuncTrm)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		if (f_funcNr != other.f_funcNr)
			return (f_funcNr > other.f_funcNr) ? 1 : -1;
		tmp = f_args.compareTo(other.f_args);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		if (f_trmInfo != other.f_trmInfo)
			return (f_trmInfo > other.f_trmInfo) ? 1 : -1;
		return 0;
	}
}
