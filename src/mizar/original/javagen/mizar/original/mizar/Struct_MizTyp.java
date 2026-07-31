// HASH COLLISIONS: YES
// timestamp: 1785512695555

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizTyp extends Struct implements Field_sort<Integer>, Field_lower<Struct_MizAttrColl>, Field_upper<Struct_MizAttrColl> {
	public int f_sort;
	public Struct_MizAttrColl f_lower;
	public Struct_MizAttrColl f_upper;
	public int f_modNr;
	public Struct f_modArgs;
	public int f_pattNr;

	public Struct_MizTyp() {}
	public Struct_MizTyp(int a_sort, Struct_MizAttrColl a_lower, Struct_MizAttrColl a_upper, int a_modNr, Struct a_modArgs, int a_pattNr) {
		f_sort = a_sort;
		f_lower = a_lower;
		f_upper = a_upper;
		f_modNr = a_modNr;
		f_modArgs = a_modArgs;
		f_pattNr = a_pattNr;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }
	public Struct_MizAttrColl get_lower() { return f_lower; }
	public void set_lower(Struct_MizAttrColl value) { f_lower = value; }
	public Struct_MizAttrColl get_upper() { return f_upper; }
	public void set_upper(Struct_MizAttrColl value) { f_upper = value; }

	public int getTypeId() { return 71; }
	public String getTypeName() { return "MizTyp"; }

	private static final String[] field_names = new String[] {
		"sort", "lower", "upper", "modNr", "modArgs", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort, f_lower, f_upper, f_modNr, f_modArgs, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 6)
			throw new IndexOutOfBoundsException("Invalid field count in MizTyp");
		f_sort = (Integer)values[0];
		f_lower = (Struct_MizAttrColl)values[1];
		f_upper = (Struct_MizAttrColl)values[2];
		f_modNr = (Integer)values[3];
		f_modArgs = (Struct)values[4];
		f_pattNr = (Integer)values[5];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 71) return 71-tmp;
		Struct_MizTyp other = (Struct_MizTyp)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		tmp = f_lower.compareTo(other.f_lower);
		if (tmp != 0) return tmp;
		tmp = f_upper.compareTo(other.f_upper);
		if (tmp != 0) return tmp;
		if (f_modNr != other.f_modNr)
			return (f_modNr > other.f_modNr) ? 1 : -1;
		tmp = f_modArgs.compareTo(other.f_modArgs);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
