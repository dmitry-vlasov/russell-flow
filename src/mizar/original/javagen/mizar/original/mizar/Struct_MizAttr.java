// HASH COLLISIONS: YES
// timestamp: 1785512695544

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizAttr extends Struct {
	public int f_neg;
	public int f_attrNr;
	public Struct f_args;
	public boolean f_collected;
	public int f_pattNr;

	public Struct_MizAttr() {}
	public Struct_MizAttr(int a_neg, int a_attrNr, Struct a_args, boolean a_collected, int a_pattNr) {
		f_neg = a_neg;
		f_attrNr = a_attrNr;
		f_args = a_args;
		f_collected = a_collected;
		f_pattNr = a_pattNr;
	}

	public int getTypeId() { return 14; }
	public String getTypeName() { return "MizAttr"; }

	private static final String[] field_names = new String[] {
		"neg", "attrNr", "args", "collected", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_neg, f_attrNr, f_args, f_collected, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizAttr");
		f_neg = (Integer)values[0];
		f_attrNr = (Integer)values[1];
		f_args = (Struct)values[2];
		f_collected = (Boolean)values[3];
		f_pattNr = (Integer)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 14) return 14-tmp;
		Struct_MizAttr other = (Struct_MizAttr)other_gen;
		if (f_neg != other.f_neg)
			return (f_neg > other.f_neg) ? 1 : -1;
		if (f_attrNr != other.f_attrNr)
			return (f_attrNr > other.f_attrNr) ? 1 : -1;
		tmp = f_args.compareTo(other.f_args);
		if (tmp != 0) return tmp;
		if (f_collected != other.f_collected)
			return f_collected ? 1 : -1;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
