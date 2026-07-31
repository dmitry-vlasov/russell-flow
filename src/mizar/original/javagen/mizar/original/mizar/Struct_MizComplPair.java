// HASH COLLISIONS: YES
// timestamp: 1785524849103

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizComplPair extends Struct {
	public int f_clause1;
	public int f_clause2;
	public int f_atom1;
	public int f_atom2;
	public Struct_MizPreInst f_insts;

	public Struct_MizComplPair() {}
	public Struct_MizComplPair(int a_clause1, int a_clause2, int a_atom1, int a_atom2, Struct_MizPreInst a_insts) {
		f_clause1 = a_clause1;
		f_clause2 = a_clause2;
		f_atom1 = a_atom1;
		f_atom2 = a_atom2;
		f_insts = a_insts;
	}

	public int getTypeId() { return 24; }
	public String getTypeName() { return "MizComplPair"; }

	private static final String[] field_names = new String[] {
		"clause1", "clause2", "atom1", "atom2", "insts"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_clause1, f_clause2, f_atom1, f_atom2, f_insts
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizComplPair");
		f_clause1 = (Integer)values[0];
		f_clause2 = (Integer)values[1];
		f_atom1 = (Integer)values[2];
		f_atom2 = (Integer)values[3];
		f_insts = (Struct_MizPreInst)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 24) return 24-tmp;
		Struct_MizComplPair other = (Struct_MizComplPair)other_gen;
		if (f_clause1 != other.f_clause1)
			return (f_clause1 > other.f_clause1) ? 1 : -1;
		if (f_clause2 != other.f_clause2)
			return (f_clause2 > other.f_clause2) ? 1 : -1;
		if (f_atom1 != other.f_atom1)
			return (f_atom1 > other.f_atom1) ? 1 : -1;
		if (f_atom2 != other.f_atom2)
			return (f_atom2 > other.f_atom2) ? 1 : -1;
		tmp = f_insts.compareTo(other.f_insts);
		return tmp;
	}
}
