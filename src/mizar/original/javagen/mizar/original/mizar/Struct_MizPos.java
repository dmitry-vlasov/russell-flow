// HASH COLLISIONS: YES
// timestamp: 1785524849107

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizPos extends Struct {
	public int f_line;
	public int f_col;

	public Struct_MizPos() {}
	public Struct_MizPos(int a_line, int a_col) {
		f_line = a_line;
		f_col = a_col;
	}

	public int getTypeId() { return 55; }
	public String getTypeName() { return "MizPos"; }

	private static final String[] field_names = new String[] {
		"line", "col"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_line, f_col
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizPos");
		f_line = (Integer)values[0];
		f_col = (Integer)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 55) return 55-tmp;
		Struct_MizPos other = (Struct_MizPos)other_gen;
		if (f_line != other.f_line)
			return (f_line > other.f_line) ? 1 : -1;
		if (f_col != other.f_col)
			return (f_col > other.f_col) ? 1 : -1;
		return 0;
	}
}
