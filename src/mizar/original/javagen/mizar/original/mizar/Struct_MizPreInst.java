// HASH COLLISIONS: YES
// timestamp: 1785508391387

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizPreInst extends Struct implements Field_top<Boolean>, Field_overflow<Boolean> {
	public Struct_Vector f_evals;
	public boolean f_top;
	public boolean f_overflow;

	public Struct_MizPreInst() {}
	public Struct_MizPreInst(Struct_Vector a_evals, boolean a_top, boolean a_overflow) {
		f_evals = a_evals;
		f_top = a_top;
		f_overflow = a_overflow;
	}
	public Boolean get_top() { return f_top; }
	public void set_top(Boolean value) { f_top = value; }
	public Boolean get_overflow() { return f_overflow; }
	public void set_overflow(Boolean value) { f_overflow = value; }

	public int getTypeId() { return 54; }
	public String getTypeName() { return "MizPreInst"; }

	private static final String[] field_names = new String[] {
		"evals", "top", "overflow"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.BOOL
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_evals, f_top, f_overflow
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizPreInst");
		f_evals = (Struct_Vector)values[0];
		f_top = (Boolean)values[1];
		f_overflow = (Boolean)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 54) return 54-tmp;
		Struct_MizPreInst other = (Struct_MizPreInst)other_gen;
		tmp = f_evals.compareTo(other.f_evals);
		if (tmp != 0) return tmp;
		if (f_top != other.f_top)
			return f_top ? 1 : -1;
		if (f_overflow != other.f_overflow)
			return f_overflow ? 1 : -1;
		return 0;
	}
}
