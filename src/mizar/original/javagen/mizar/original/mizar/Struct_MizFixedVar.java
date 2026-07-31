// HASH COLLISIONS: YES
// timestamp: 1785512695549

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizFixedVar extends Struct implements Field_typ<Struct_MizTyp>, Field_def<Struct> {
	public int f_ident;
	public Struct_MizTyp f_typ;
	public boolean f_expandable;
	public Struct f_def;
	public int f_skelConstNr;

	public Struct_MizFixedVar() {}
	public Struct_MizFixedVar(int a_ident, Struct_MizTyp a_typ, boolean a_expandable, Struct a_def, int a_skelConstNr) {
		f_ident = a_ident;
		f_typ = a_typ;
		f_expandable = a_expandable;
		f_def = a_def;
		f_skelConstNr = a_skelConstNr;
	}
	public Struct_MizTyp get_typ() { return f_typ; }
	public void set_typ(Struct_MizTyp value) { f_typ = value; }
	public Struct get_def() { return f_def; }
	public void set_def(Struct value) { f_def = value; }

	public int getTypeId() { return 40; }
	public String getTypeName() { return "MizFixedVar"; }

	private static final String[] field_names = new String[] {
		"ident", "typ", "expandable", "def", "skelConstNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_ident, f_typ, f_expandable, f_def, f_skelConstNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizFixedVar");
		f_ident = (Integer)values[0];
		f_typ = (Struct_MizTyp)values[1];
		f_expandable = (Boolean)values[2];
		f_def = (Struct)values[3];
		f_skelConstNr = (Integer)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 40) return 40-tmp;
		Struct_MizFixedVar other = (Struct_MizFixedVar)other_gen;
		if (f_ident != other.f_ident)
			return (f_ident > other.f_ident) ? 1 : -1;
		tmp = f_typ.compareTo(other.f_typ);
		if (tmp != 0) return tmp;
		if (f_expandable != other.f_expandable)
			return f_expandable ? 1 : -1;
		tmp = f_def.compareTo(other.f_def);
		if (tmp != 0) return tmp;
		if (f_skelConstNr != other.f_skelConstNr)
			return (f_skelConstNr > other.f_skelConstNr) ? 1 : -1;
		return 0;
	}
}
