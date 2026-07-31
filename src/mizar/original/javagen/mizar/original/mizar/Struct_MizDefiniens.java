// HASH COLLISIONS: YES
// timestamp: 1785524849104

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizDefiniens extends Struct implements Field_def<Struct> {
	public int f_constrKind;
	public int f_constrNr;
	public String f_article;
	public int f_defNr;
	public Struct_Vector f_primaries;
	public Struct_MizIntSeq f_essentials;
	public Struct f_assumptions;
	public Struct f_def;

	public Struct_MizDefiniens() {}
	public Struct_MizDefiniens(int a_constrKind, int a_constrNr, String a_article, int a_defNr, Struct_Vector a_primaries, Struct_MizIntSeq a_essentials, Struct a_assumptions, Struct a_def) {
		f_constrKind = a_constrKind;
		f_constrNr = a_constrNr;
		f_article = a_article;
		f_defNr = a_defNr;
		f_primaries = a_primaries;
		f_essentials = a_essentials;
		f_assumptions = a_assumptions;
		f_def = a_def;
	}
	public Struct get_def() { return f_def; }
	public void set_def(Struct value) { f_def = value; }

	public int getTypeId() { return 33; }
	public String getTypeName() { return "MizDefiniens"; }

	private static final String[] field_names = new String[] {
		"constrKind", "constrNr", "article", "defNr", "primaries", "essentials", "assumptions", "def"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT, RuntimeType.STRING, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_constrKind, f_constrNr, f_article, f_defNr, f_primaries, f_essentials, f_assumptions, f_def
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 8)
			throw new IndexOutOfBoundsException("Invalid field count in MizDefiniens");
		f_constrKind = (Integer)values[0];
		f_constrNr = (Integer)values[1];
		f_article = (String)values[2];
		f_defNr = (Integer)values[3];
		f_primaries = (Struct_Vector)values[4];
		f_essentials = (Struct_MizIntSeq)values[5];
		f_assumptions = (Struct)values[6];
		f_def = (Struct)values[7];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 33) return 33-tmp;
		Struct_MizDefiniens other = (Struct_MizDefiniens)other_gen;
		if (f_constrKind != other.f_constrKind)
			return (f_constrKind > other.f_constrKind) ? 1 : -1;
		if (f_constrNr != other.f_constrNr)
			return (f_constrNr > other.f_constrNr) ? 1 : -1;
		tmp = f_article.compareTo(other.f_article);
		if (tmp != 0) return tmp;
		if (f_defNr != other.f_defNr)
			return (f_defNr > other.f_defNr) ? 1 : -1;
		tmp = f_primaries.compareTo(other.f_primaries);
		if (tmp != 0) return tmp;
		tmp = f_essentials.compareTo(other.f_essentials);
		if (tmp != 0) return tmp;
		tmp = f_assumptions.compareTo(other.f_assumptions);
		if (tmp != 0) return tmp;
		tmp = f_def.compareTo(other.f_def);
		return tmp;
	}
}
