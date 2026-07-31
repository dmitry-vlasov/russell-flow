// HASH COLLISIONS: YES
// timestamp: 1785524849101

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_JsonParseResult extends Struct {
	public Struct f_value;
	public int f_errorPosition;
	public int f_lastProcessedPosition;

	public Struct_JsonParseResult() {}
	public Struct_JsonParseResult(Struct a_value, int a_errorPosition, int a_lastProcessedPosition) {
		f_value = a_value;
		f_errorPosition = a_errorPosition;
		f_lastProcessedPosition = a_lastProcessedPosition;
	}

	public int getTypeId() { return 11; }
	public String getTypeName() { return "JsonParseResult"; }

	private static final String[] field_names = new String[] {
		"value", "errorPosition", "lastProcessedPosition"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_value, f_errorPosition, f_lastProcessedPosition
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in JsonParseResult");
		f_value = (Struct)values[0];
		f_errorPosition = (Integer)values[1];
		f_lastProcessedPosition = (Integer)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 11) return 11-tmp;
		Struct_JsonParseResult other = (Struct_JsonParseResult)other_gen;
		tmp = f_value.compareTo(other.f_value);
		if (tmp != 0) return tmp;
		if (f_errorPosition != other.f_errorPosition)
			return (f_errorPosition > other.f_errorPosition) ? 1 : -1;
		if (f_lastProcessedPosition != other.f_lastProcessedPosition)
			return (f_lastProcessedPosition > other.f_lastProcessedPosition) ? 1 : -1;
		return 0;
	}
}
