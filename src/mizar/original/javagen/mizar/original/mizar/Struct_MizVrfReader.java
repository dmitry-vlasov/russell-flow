// HASH COLLISIONS: YES
// timestamp: 1785524849110

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizVrfReader extends Struct implements Field_mml<Boolean> {
	public Struct_MizXmlParser f_x;
	public Struct_MizCorrelEnv f_env;
	public boolean f_mml;

	public Struct_MizVrfReader() {}
	public Struct_MizVrfReader(Struct_MizXmlParser a_x, Struct_MizCorrelEnv a_env, boolean a_mml) {
		f_x = a_x;
		f_env = a_env;
		f_mml = a_mml;
	}
	public Boolean get_mml() { return f_mml; }
	public void set_mml(Boolean value) { f_mml = value; }

	public int getTypeId() { return 78; }
	public String getTypeName() { return "MizVrfReader"; }

	private static final String[] field_names = new String[] {
		"x", "env", "mml"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.BOOL
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_x, f_env, f_mml
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizVrfReader");
		f_x = (Struct_MizXmlParser)values[0];
		f_env = (Struct_MizCorrelEnv)values[1];
		f_mml = (Boolean)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 78) return 78-tmp;
		Struct_MizVrfReader other = (Struct_MizVrfReader)other_gen;
		tmp = f_x.compareTo(other.f_x);
		if (tmp != 0) return tmp;
		tmp = f_env.compareTo(other.f_env);
		if (tmp != 0) return tmp;
		if (f_mml != other.f_mml)
			return f_mml ? 1 : -1;
		return 0;
	}
}
