// HASH COLLISIONS: YES
// timestamp: 1785512695551

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizNegFrm extends Struct implements Field_arg<Struct> {
	public Struct f_arg;
	public int f_pattNr;

	public Struct_MizNegFrm() {}
	public Struct_MizNegFrm(Struct a_arg, int a_pattNr) {
		f_arg = a_arg;
		f_pattNr = a_pattNr;
	}
	public Struct get_arg() { return f_arg; }
	public void set_arg(Struct value) { f_arg = value; }

	public int getTypeId() { return 51; }
	public String getTypeName() { return "MizNegFrm"; }

	private static final String[] field_names = new String[] {
		"arg", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_arg, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizNegFrm");
		f_arg = (Struct)values[0];
		f_pattNr = (Integer)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 51) return 51-tmp;
		Struct_MizNegFrm other = (Struct_MizNegFrm)other_gen;
		tmp = f_arg.compareTo(other.f_arg);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
