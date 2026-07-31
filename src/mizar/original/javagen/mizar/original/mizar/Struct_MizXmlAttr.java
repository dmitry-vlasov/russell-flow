// HASH COLLISIONS: YES
// timestamp: 1785508391390

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizXmlAttr extends Struct {
	public String f_name;
	public String f_value;

	public Struct_MizXmlAttr() {}
	public Struct_MizXmlAttr(String a_name, String a_value) {
		f_name = a_name;
		f_value = a_value;
	}

	public int getTypeId() { return 73; }
	public String getTypeName() { return "MizXmlAttr"; }

	private static final String[] field_names = new String[] {
		"name", "value"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRING, RuntimeType.STRING
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_name, f_value
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizXmlAttr");
		f_name = (String)values[0];
		f_value = (String)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 73) return 73-tmp;
		Struct_MizXmlAttr other = (Struct_MizXmlAttr)other_gen;
		tmp = f_name.compareTo(other.f_name);
		if (tmp != 0) return tmp;
		tmp = f_value.compareTo(other.f_value);
		return tmp;
	}
}
