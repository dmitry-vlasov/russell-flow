// HASH COLLISIONS: YES
// timestamp: 1785508391389

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizUnivFrm extends Struct implements Field_sort<Integer>, Field_scope<Struct> {
	public int f_sort;
	public int f_varId;
	public Struct_MizTyp f_quantified;
	public Struct f_scope;
	public int f_pattNr;

	public Struct_MizUnivFrm() {}
	public Struct_MizUnivFrm(int a_sort, int a_varId, Struct_MizTyp a_quantified, Struct a_scope, int a_pattNr) {
		f_sort = a_sort;
		f_varId = a_varId;
		f_quantified = a_quantified;
		f_scope = a_scope;
		f_pattNr = a_pattNr;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }
	public Struct get_scope() { return f_scope; }
	public void set_scope(Struct value) { f_scope = value; }

	public int getTypeId() { return 69; }
	public String getTypeName() { return "MizUnivFrm"; }

	private static final String[] field_names = new String[] {
		"sort", "varId", "quantified", "scope", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort, f_varId, f_quantified, f_scope, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizUnivFrm");
		f_sort = (Integer)values[0];
		f_varId = (Integer)values[1];
		f_quantified = (Struct_MizTyp)values[2];
		f_scope = (Struct)values[3];
		f_pattNr = (Integer)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 69) return 69-tmp;
		Struct_MizUnivFrm other = (Struct_MizUnivFrm)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		if (f_varId != other.f_varId)
			return (f_varId > other.f_varId) ? 1 : -1;
		tmp = f_quantified.compareTo(other.f_quantified);
		if (tmp != 0) return tmp;
		tmp = f_scope.compareTo(other.f_scope);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
