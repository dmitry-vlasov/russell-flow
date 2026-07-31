// HASH COLLISIONS: YES
// timestamp: 1785512695554

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizSchState extends Struct implements Field_funcSubst<Struct>, Field_predSubst<Struct>, Field_thereAreBound<Boolean>, Field_err<Integer> {
	public Struct_MizCorrelEnv f_env;
	public Struct_Vector f_schTypes;
	public Struct_Vector f_constSubst;
	public Struct f_funcSubst;
	public Struct f_predSubst;
	public boolean f_thereAreBound;
	public int f_err;

	public Struct_MizSchState() {}
	public Struct_MizSchState(Struct_MizCorrelEnv a_env, Struct_Vector a_schTypes, Struct_Vector a_constSubst, Struct a_funcSubst, Struct a_predSubst, boolean a_thereAreBound, int a_err) {
		f_env = a_env;
		f_schTypes = a_schTypes;
		f_constSubst = a_constSubst;
		f_funcSubst = a_funcSubst;
		f_predSubst = a_predSubst;
		f_thereAreBound = a_thereAreBound;
		f_err = a_err;
	}
	public Struct get_funcSubst() { return f_funcSubst; }
	public void set_funcSubst(Struct value) { f_funcSubst = value; }
	public Struct get_predSubst() { return f_predSubst; }
	public void set_predSubst(Struct value) { f_predSubst = value; }
	public Boolean get_thereAreBound() { return f_thereAreBound; }
	public void set_thereAreBound(Boolean value) { f_thereAreBound = value; }
	public Integer get_err() { return f_err; }
	public void set_err(Integer value) { f_err = value; }

	public int getTypeId() { return 65; }
	public String getTypeName() { return "MizSchState"; }

	private static final String[] field_names = new String[] {
		"env", "schTypes", "constSubst", "funcSubst", "predSubst", "thereAreBound", "err"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_env, f_schTypes, f_constSubst, f_funcSubst, f_predSubst, f_thereAreBound, f_err
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 7)
			throw new IndexOutOfBoundsException("Invalid field count in MizSchState");
		f_env = (Struct_MizCorrelEnv)values[0];
		f_schTypes = (Struct_Vector)values[1];
		f_constSubst = (Struct_Vector)values[2];
		f_funcSubst = (Struct)values[3];
		f_predSubst = (Struct)values[4];
		f_thereAreBound = (Boolean)values[5];
		f_err = (Integer)values[6];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 65) return 65-tmp;
		Struct_MizSchState other = (Struct_MizSchState)other_gen;
		tmp = f_env.compareTo(other.f_env);
		if (tmp != 0) return tmp;
		tmp = f_schTypes.compareTo(other.f_schTypes);
		if (tmp != 0) return tmp;
		tmp = f_constSubst.compareTo(other.f_constSubst);
		if (tmp != 0) return tmp;
		tmp = f_funcSubst.compareTo(other.f_funcSubst);
		if (tmp != 0) return tmp;
		tmp = f_predSubst.compareTo(other.f_predSubst);
		if (tmp != 0) return tmp;
		if (f_thereAreBound != other.f_thereAreBound)
			return f_thereAreBound ? 1 : -1;
		if (f_err != other.f_err)
			return (f_err > other.f_err) ? 1 : -1;
		return 0;
	}
}
