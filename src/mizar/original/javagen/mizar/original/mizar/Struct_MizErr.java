// HASH COLLISIONS: YES
// timestamp: 1785512695549

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizErr extends Struct {
	public Struct_MizPos f_pos;
	public int f_nr;

	public Struct_MizErr() {}
	public Struct_MizErr(Struct_MizPos a_pos, int a_nr) {
		f_pos = a_pos;
		f_nr = a_nr;
	}

	public int getTypeId() { return 37; }
	public String getTypeName() { return "MizErr"; }

	private static final String[] field_names = new String[] {
		"pos", "nr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_pos, f_nr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizErr");
		f_pos = (Struct_MizPos)values[0];
		f_nr = (Integer)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 37) return 37-tmp;
		Struct_MizErr other = (Struct_MizErr)other_gen;
		tmp = f_pos.compareTo(other.f_pos);
		if (tmp != 0) return tmp;
		if (f_nr != other.f_nr)
			return (f_nr > other.f_nr) ? 1 : -1;
		return 0;
	}
}
