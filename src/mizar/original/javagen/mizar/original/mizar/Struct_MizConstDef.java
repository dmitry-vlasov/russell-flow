// HASH COLLISIONS: YES
// timestamp: 1785508391384

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizConstDef extends Struct implements Field_def<Struct>, Field_setting<Integer>, Field_typ<Struct_MizTyp>, Field_determined<Boolean> {
	public Struct f_def;
	public Struct_MizNatFunc f_eqConst;
	public int f_setting;
	public Struct_MizTyp f_typ;
	public boolean f_determined;

	public Struct_MizConstDef() {}
	public Struct_MizConstDef(Struct a_def, Struct_MizNatFunc a_eqConst, int a_setting, Struct_MizTyp a_typ, boolean a_determined) {
		f_def = a_def;
		f_eqConst = a_eqConst;
		f_setting = a_setting;
		f_typ = a_typ;
		f_determined = a_determined;
	}
	public Struct get_def() { return f_def; }
	public void set_def(Struct value) { f_def = value; }
	public Integer get_setting() { return f_setting; }
	public void set_setting(Integer value) { f_setting = value; }
	public Struct_MizTyp get_typ() { return f_typ; }
	public void set_typ(Struct_MizTyp value) { f_typ = value; }
	public Boolean get_determined() { return f_determined; }
	public void set_determined(Boolean value) { f_determined = value; }

	public int getTypeId() { return 27; }
	public String getTypeName() { return "MizConstDef"; }

	private static final String[] field_names = new String[] {
		"def", "eqConst", "setting", "typ", "determined"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.BOOL
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_def, f_eqConst, f_setting, f_typ, f_determined
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizConstDef");
		f_def = (Struct)values[0];
		f_eqConst = (Struct_MizNatFunc)values[1];
		f_setting = (Integer)values[2];
		f_typ = (Struct_MizTyp)values[3];
		f_determined = (Boolean)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 27) return 27-tmp;
		Struct_MizConstDef other = (Struct_MizConstDef)other_gen;
		tmp = f_def.compareTo(other.f_def);
		if (tmp != 0) return tmp;
		tmp = f_eqConst.compareTo(other.f_eqConst);
		if (tmp != 0) return tmp;
		if (f_setting != other.f_setting)
			return (f_setting > other.f_setting) ? 1 : -1;
		tmp = f_typ.compareTo(other.f_typ);
		if (tmp != 0) return tmp;
		if (f_determined != other.f_determined)
			return f_determined ? 1 : -1;
		return 0;
	}
}
