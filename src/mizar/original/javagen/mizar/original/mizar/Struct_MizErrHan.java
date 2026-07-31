// HASH COLLISIONS: YES
// timestamp: 1785508391386

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizErrHan extends Struct {
	public Struct_MizPos f_curPos;
	public int f_errorNbr;
	public Struct_Vector f_errors;

	public Struct_MizErrHan() {}
	public Struct_MizErrHan(Struct_MizPos a_curPos, int a_errorNbr, Struct_Vector a_errors) {
		f_curPos = a_curPos;
		f_errorNbr = a_errorNbr;
		f_errors = a_errors;
	}

	public int getTypeId() { return 38; }
	public String getTypeName() { return "MizErrHan"; }

	private static final String[] field_names = new String[] {
		"curPos", "errorNbr", "errors"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_curPos, f_errorNbr, f_errors
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizErrHan");
		f_curPos = (Struct_MizPos)values[0];
		f_errorNbr = (Integer)values[1];
		f_errors = (Struct_Vector)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 38) return 38-tmp;
		Struct_MizErrHan other = (Struct_MizErrHan)other_gen;
		tmp = f_curPos.compareTo(other.f_curPos);
		if (tmp != 0) return tmp;
		if (f_errorNbr != other.f_errorNbr)
			return (f_errorNbr > other.f_errorNbr) ? 1 : -1;
		tmp = f_errors.compareTo(other.f_errors);
		return tmp;
	}
}
