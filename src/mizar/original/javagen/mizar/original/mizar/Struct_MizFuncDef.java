// HASH COLLISIONS: YES
// timestamp: 1785524849106

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizFuncDef extends Struct {
	public int f_id;
	public Struct_Vector f_primaries;
	public Struct f_funcDef;
	public Struct_MizTyp f_funcTyp;

	public Struct_MizFuncDef() {}
	public Struct_MizFuncDef(int a_id, Struct_Vector a_primaries, Struct a_funcDef, Struct_MizTyp a_funcTyp) {
		f_id = a_id;
		f_primaries = a_primaries;
		f_funcDef = a_funcDef;
		f_funcTyp = a_funcTyp;
	}

	public int getTypeId() { return 43; }
	public String getTypeName() { return "MizFuncDef"; }

	private static final String[] field_names = new String[] {
		"id", "primaries", "funcDef", "funcTyp"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_id, f_primaries, f_funcDef, f_funcTyp
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 4)
			throw new IndexOutOfBoundsException("Invalid field count in MizFuncDef");
		f_id = (Integer)values[0];
		f_primaries = (Struct_Vector)values[1];
		f_funcDef = (Struct)values[2];
		f_funcTyp = (Struct_MizTyp)values[3];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 43) return 43-tmp;
		Struct_MizFuncDef other = (Struct_MizFuncDef)other_gen;
		if (f_id != other.f_id)
			return (f_id > other.f_id) ? 1 : -1;
		tmp = f_primaries.compareTo(other.f_primaries);
		if (tmp != 0) return tmp;
		tmp = f_funcDef.compareTo(other.f_funcDef);
		if (tmp != 0) return tmp;
		tmp = f_funcTyp.compareTo(other.f_funcTyp);
		return tmp;
	}
}
