// HASH COLLISIONS: YES
// timestamp: 1785512695544

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_KeyValue extends Struct {
	public String f_key;
	public String f_value;

	public Struct_KeyValue() {}
	public Struct_KeyValue(String a_key, String a_value) {
		f_key = a_key;
		f_value = a_value;
	}

	public int getTypeId() { return 13; }
	public String getTypeName() { return "KeyValue"; }

	private static final String[] field_names = new String[] {
		"key", "value"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRING, RuntimeType.STRING
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_key, f_value
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in KeyValue");
		f_key = (String)values[0];
		f_value = (String)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 13) return 13-tmp;
		Struct_KeyValue other = (Struct_KeyValue)other_gen;
		tmp = f_key.compareTo(other.f_key);
		if (tmp != 0) return tmp;
		tmp = f_value.compareTo(other.f_value);
		return tmp;
	}
}
