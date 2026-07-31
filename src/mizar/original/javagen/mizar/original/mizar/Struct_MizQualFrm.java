// HASH COLLISIONS: YES
// timestamp: 1785512695552

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizQualFrm extends Struct implements Field_trm<Struct>, Field_typ<Struct_MizTyp> {
	public Struct f_trm;
	public Struct_MizTyp f_typ;
	public int f_pattNr;

	public Struct_MizQualFrm() {}
	public Struct_MizQualFrm(Struct a_trm, Struct_MizTyp a_typ, int a_pattNr) {
		f_trm = a_trm;
		f_typ = a_typ;
		f_pattNr = a_pattNr;
	}
	public Struct get_trm() { return f_trm; }
	public void set_trm(Struct value) { f_trm = value; }
	public Struct_MizTyp get_typ() { return f_typ; }
	public void set_typ(Struct_MizTyp value) { f_typ = value; }

	public int getTypeId() { return 61; }
	public String getTypeName() { return "MizQualFrm"; }

	private static final String[] field_names = new String[] {
		"trm", "typ", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_trm, f_typ, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizQualFrm");
		f_trm = (Struct)values[0];
		f_typ = (Struct_MizTyp)values[1];
		f_pattNr = (Integer)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 61) return 61-tmp;
		Struct_MizQualFrm other = (Struct_MizQualFrm)other_gen;
		tmp = f_trm.compareTo(other.f_trm);
		if (tmp != 0) return tmp;
		tmp = f_typ.compareTo(other.f_typ);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
