// HASH COLLISIONS: YES
// timestamp: 1785524849105

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizEqualsDef extends Struct {
	public Struct_Vector f_primaries;
	public Struct_MizIntSeq f_essentials;
	public Struct f_expansion;

	public Struct_MizEqualsDef() {}
	public Struct_MizEqualsDef(Struct_Vector a_primaries, Struct_MizIntSeq a_essentials, Struct a_expansion) {
		f_primaries = a_primaries;
		f_essentials = a_essentials;
		f_expansion = a_expansion;
	}

	public int getTypeId() { return 36; }
	public String getTypeName() { return "MizEqualsDef"; }

	private static final String[] field_names = new String[] {
		"primaries", "essentials", "expansion"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_primaries, f_essentials, f_expansion
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizEqualsDef");
		f_primaries = (Struct_Vector)values[0];
		f_essentials = (Struct_MizIntSeq)values[1];
		f_expansion = (Struct)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 36) return 36-tmp;
		Struct_MizEqualsDef other = (Struct_MizEqualsDef)other_gen;
		tmp = f_primaries.compareTo(other.f_primaries);
		if (tmp != 0) return tmp;
		tmp = f_essentials.compareTo(other.f_essentials);
		if (tmp != 0) return tmp;
		tmp = f_expansion.compareTo(other.f_expansion);
		return tmp;
	}
}
