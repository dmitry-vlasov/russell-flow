// HASH COLLISIONS: YES
// timestamp: 1785512695550

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizFuncIdent extends Struct {
	public String f_article;
	public int f_absNr;
	public Struct_Vector f_primaries;
	public Struct f_pattern0;
	public Struct f_pattern1;
	public Object[] f_eqArgs;

	public Struct_MizFuncIdent() {}
	public Struct_MizFuncIdent(String a_article, int a_absNr, Struct_Vector a_primaries, Struct a_pattern0, Struct a_pattern1, Object[] a_eqArgs) {
		f_article = a_article;
		f_absNr = a_absNr;
		f_primaries = a_primaries;
		f_pattern0 = a_pattern0;
		f_pattern1 = a_pattern1;
		f_eqArgs = a_eqArgs;
	}

	public int getTypeId() { return 44; }
	public String getTypeName() { return "MizFuncIdent"; }

	private static final String[] field_names = new String[] {
		"article", "absNr", "primaries", "pattern0", "pattern1", "eqArgs"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRING, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.ARRAY
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_article, f_absNr, f_primaries, f_pattern0, f_pattern1, f_eqArgs
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 6)
			throw new IndexOutOfBoundsException("Invalid field count in MizFuncIdent");
		f_article = (String)values[0];
		f_absNr = (Integer)values[1];
		f_primaries = (Struct_Vector)values[2];
		f_pattern0 = (Struct)values[3];
		f_pattern1 = (Struct)values[4];
		f_eqArgs = (Object[])values[5];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 44) return 44-tmp;
		Struct_MizFuncIdent other = (Struct_MizFuncIdent)other_gen;
		tmp = f_article.compareTo(other.f_article);
		if (tmp != 0) return tmp;
		if (f_absNr != other.f_absNr)
			return (f_absNr > other.f_absNr) ? 1 : -1;
		tmp = f_primaries.compareTo(other.f_primaries);
		if (tmp != 0) return tmp;
		tmp = f_pattern0.compareTo(other.f_pattern0);
		if (tmp != 0) return tmp;
		tmp = f_pattern1.compareTo(other.f_pattern1);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_eqArgs, other.f_eqArgs);
		return tmp;
	}
}
