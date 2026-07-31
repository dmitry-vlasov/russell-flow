// HASH COLLISIONS: YES
// timestamp: 1785524849111

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizWithin extends Struct implements Field_boundVarNbr<Integer> {
	public Func2<Struct,Struct_MizWithin, Struct> f_p;
	public boolean f_incBound;
	public int f_boundVarNbr;

	public Struct_MizWithin() {}
	public Struct_MizWithin(Func2<Struct,Struct_MizWithin, Struct> a_p, boolean a_incBound, int a_boundVarNbr) {
		f_p = a_p;
		f_incBound = a_incBound;
		f_boundVarNbr = a_boundVarNbr;
	}
	public Integer get_boundVarNbr() { return f_boundVarNbr; }
	public void set_boundVarNbr(Integer value) { f_boundVarNbr = value; }

	public int getTypeId() { return 79; }
	public String getTypeName() { return "MizWithin"; }

	private static final String[] field_names = new String[] {
		"p", "incBound", "boundVarNbr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.UNKNOWN, RuntimeType.BOOL, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_p, f_incBound, f_boundVarNbr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizWithin");
		f_p = (Func2<Struct,Struct_MizWithin, Struct>)values[0];
		f_incBound = (Boolean)values[1];
		f_boundVarNbr = (Integer)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 79) return 79-tmp;
		Struct_MizWithin other = (Struct_MizWithin)other_gen;
		tmp = FlowRuntime.compareByValue(f_p, other.f_p);
		if (tmp != 0) return tmp;
		if (f_incBound != other.f_incBound)
			return f_incBound ? 1 : -1;
		if (f_boundVarNbr != other.f_boundVarNbr)
			return (f_boundVarNbr > other.f_boundVarNbr) ? 1 : -1;
		return 0;
	}
}
