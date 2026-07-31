// HASH COLLISIONS: YES
// timestamp: 1785508391387

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizPredFrm extends Struct implements Field_sort<Integer> {
	public int f_sort;
	public int f_predNr;
	public Struct f_args;
	public int f_pattNr;

	public Struct_MizPredFrm() {}
	public Struct_MizPredFrm(int a_sort, int a_predNr, Struct a_args, int a_pattNr) {
		f_sort = a_sort;
		f_predNr = a_predNr;
		f_args = a_args;
		f_pattNr = a_pattNr;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }

	public int getTypeId() { return 55; }
	public String getTypeName() { return "MizPredFrm"; }

	private static final String[] field_names = new String[] {
		"sort", "predNr", "args", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort, f_predNr, f_args, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 4)
			throw new IndexOutOfBoundsException("Invalid field count in MizPredFrm");
		f_sort = (Integer)values[0];
		f_predNr = (Integer)values[1];
		f_args = (Struct)values[2];
		f_pattNr = (Integer)values[3];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 55) return 55-tmp;
		Struct_MizPredFrm other = (Struct_MizPredFrm)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		if (f_predNr != other.f_predNr)
			return (f_predNr > other.f_predNr) ? 1 : -1;
		tmp = f_args.compareTo(other.f_args);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
