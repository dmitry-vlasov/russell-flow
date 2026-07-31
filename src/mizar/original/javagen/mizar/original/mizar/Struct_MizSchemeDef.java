// HASH COLLISIONS: YES
// timestamp: 1785524849109

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizSchemeDef extends Struct {
	public int f_articleNr;
	public int f_nr;
	public Struct_Vector f_schTypes;
	public Object[] f_schProps;
	public boolean f_valid;

	public Struct_MizSchemeDef() {}
	public Struct_MizSchemeDef(int a_articleNr, int a_nr, Struct_Vector a_schTypes, Object[] a_schProps, boolean a_valid) {
		f_articleNr = a_articleNr;
		f_nr = a_nr;
		f_schTypes = a_schTypes;
		f_schProps = a_schProps;
		f_valid = a_valid;
	}

	public int getTypeId() { return 68; }
	public String getTypeName() { return "MizSchemeDef"; }

	private static final String[] field_names = new String[] {
		"articleNr", "nr", "schTypes", "schProps", "valid"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.ARRAY, RuntimeType.BOOL
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_articleNr, f_nr, f_schTypes, f_schProps, f_valid
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizSchemeDef");
		f_articleNr = (Integer)values[0];
		f_nr = (Integer)values[1];
		f_schTypes = (Struct_Vector)values[2];
		f_schProps = (Object[])values[3];
		f_valid = (Boolean)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 68) return 68-tmp;
		Struct_MizSchemeDef other = (Struct_MizSchemeDef)other_gen;
		if (f_articleNr != other.f_articleNr)
			return (f_articleNr > other.f_articleNr) ? 1 : -1;
		if (f_nr != other.f_nr)
			return (f_nr > other.f_nr) ? 1 : -1;
		tmp = f_schTypes.compareTo(other.f_schTypes);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_schProps, other.f_schProps);
		if (tmp != 0) return tmp;
		if (f_valid != other.f_valid)
			return f_valid ? 1 : -1;
		return 0;
	}
}
