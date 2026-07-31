// HASH COLLISIONS: YES
// timestamp: 1785508391388

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizReduction extends Struct {
	public String f_article;
	public int f_absNr;
	public Struct_Vector f_primaries;
	public Struct f_t0;
	public Struct f_t1;

	public Struct_MizReduction() {}
	public Struct_MizReduction(String a_article, int a_absNr, Struct_Vector a_primaries, Struct a_t0, Struct a_t1) {
		f_article = a_article;
		f_absNr = a_absNr;
		f_primaries = a_primaries;
		f_t0 = a_t0;
		f_t1 = a_t1;
	}

	public int getTypeId() { return 62; }
	public String getTypeName() { return "MizReduction"; }

	private static final String[] field_names = new String[] {
		"article", "absNr", "primaries", "t0", "t1"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRING, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_article, f_absNr, f_primaries, f_t0, f_t1
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizReduction");
		f_article = (String)values[0];
		f_absNr = (Integer)values[1];
		f_primaries = (Struct_Vector)values[2];
		f_t0 = (Struct)values[3];
		f_t1 = (Struct)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 62) return 62-tmp;
		Struct_MizReduction other = (Struct_MizReduction)other_gen;
		tmp = f_article.compareTo(other.f_article);
		if (tmp != 0) return tmp;
		if (f_absNr != other.f_absNr)
			return (f_absNr > other.f_absNr) ? 1 : -1;
		tmp = f_primaries.compareTo(other.f_primaries);
		if (tmp != 0) return tmp;
		tmp = f_t0.compareTo(other.f_t0);
		if (tmp != 0) return tmp;
		tmp = f_t1.compareTo(other.f_t1);
		return tmp;
	}
}
