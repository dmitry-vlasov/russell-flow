// HASH COLLISIONS: YES
// timestamp: 1785512695550

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizLocPredFrm extends Struct {
	public int f_predNr;
	public Struct f_args;
	public Struct f_predExp;
	public int f_pattNr;

	public Struct_MizLocPredFrm() {}
	public Struct_MizLocPredFrm(int a_predNr, Struct a_args, Struct a_predExp, int a_pattNr) {
		f_predNr = a_predNr;
		f_args = a_args;
		f_predExp = a_predExp;
		f_pattNr = a_pattNr;
	}

	public int getTypeId() { return 49; }
	public String getTypeName() { return "MizLocPredFrm"; }

	private static final String[] field_names = new String[] {
		"predNr", "args", "predExp", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_predNr, f_args, f_predExp, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 4)
			throw new IndexOutOfBoundsException("Invalid field count in MizLocPredFrm");
		f_predNr = (Integer)values[0];
		f_args = (Struct)values[1];
		f_predExp = (Struct)values[2];
		f_pattNr = (Integer)values[3];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 49) return 49-tmp;
		Struct_MizLocPredFrm other = (Struct_MizLocPredFrm)other_gen;
		if (f_predNr != other.f_predNr)
			return (f_predNr > other.f_predNr) ? 1 : -1;
		tmp = f_args.compareTo(other.f_args);
		if (tmp != 0) return tmp;
		tmp = f_predExp.compareTo(other.f_predExp);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
