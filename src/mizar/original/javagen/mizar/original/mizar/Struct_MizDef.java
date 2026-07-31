// HASH COLLISIONS: YES
// timestamp: 1785512695547

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizDef extends Struct implements Field_sort<Integer> {
	public int f_sort;
	public Struct_Vector f_partials;
	public Struct f_otherwise;

	public Struct_MizDef() {}
	public Struct_MizDef(int a_sort, Struct_Vector a_partials, Struct a_otherwise) {
		f_sort = a_sort;
		f_partials = a_partials;
		f_otherwise = a_otherwise;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }

	public int getTypeId() { return 30; }
	public String getTypeName() { return "MizDef"; }

	private static final String[] field_names = new String[] {
		"sort", "partials", "otherwise"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort, f_partials, f_otherwise
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizDef");
		f_sort = (Integer)values[0];
		f_partials = (Struct_Vector)values[1];
		f_otherwise = (Struct)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 30) return 30-tmp;
		Struct_MizDef other = (Struct_MizDef)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		tmp = f_partials.compareTo(other.f_partials);
		if (tmp != 0) return tmp;
		tmp = f_otherwise.compareTo(other.f_otherwise);
		return tmp;
	}
}
