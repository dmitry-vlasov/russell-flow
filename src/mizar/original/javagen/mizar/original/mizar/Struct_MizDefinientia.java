// HASH COLLISIONS: YES
// timestamp: 1785512695548

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizDefinientia extends Struct implements Field_equalsBy<Struct> {
	public Struct_Vector f_all;
	public Struct_Vector f_expansions;
	public Struct f_equalsBy;

	public Struct_MizDefinientia() {}
	public Struct_MizDefinientia(Struct_Vector a_all, Struct_Vector a_expansions, Struct a_equalsBy) {
		f_all = a_all;
		f_expansions = a_expansions;
		f_equalsBy = a_equalsBy;
	}
	public Struct get_equalsBy() { return f_equalsBy; }
	public void set_equalsBy(Struct value) { f_equalsBy = value; }

	public int getTypeId() { return 34; }
	public String getTypeName() { return "MizDefinientia"; }

	private static final String[] field_names = new String[] {
		"all", "expansions", "equalsBy"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_all, f_expansions, f_equalsBy
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizDefinientia");
		f_all = (Struct_Vector)values[0];
		f_expansions = (Struct_Vector)values[1];
		f_equalsBy = (Struct)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 34) return 34-tmp;
		Struct_MizDefinientia other = (Struct_MizDefinientia)other_gen;
		tmp = f_all.compareTo(other.f_all);
		if (tmp != 0) return tmp;
		tmp = f_expansions.compareTo(other.f_expansions);
		if (tmp != 0) return tmp;
		tmp = f_equalsBy.compareTo(other.f_equalsBy);
		return tmp;
	}
}
