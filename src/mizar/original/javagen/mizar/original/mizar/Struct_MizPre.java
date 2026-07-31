// HASH COLLISIONS: YES
// timestamp: 1785524849107

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizPre extends Struct implements Field_constBase<Integer>, Field_constOvfl<Boolean>, Field_latOvfl<Boolean>, Field_trivialError<Boolean>, Field_setVarInTrm<Func1<Struct,Struct>>, Field_newVariable<Func2<Object,Struct_MizPre, Struct_MizTyp>>, Field_atomicExpansions<Func1<Object[],Struct>>, Field_collectConst<Func1<Object,Struct>> {
	public Struct_MizCorrelEnv f_env;
	public Struct_Vector f_basic;
	public int f_constBase;
	public boolean f_constOvfl;
	public boolean f_latOvfl;
	public boolean f_trivialError;
	public Func1<Struct,Struct> f_setVarInTrm;
	public Func2<Object,Struct_MizPre, Struct_MizTyp> f_newVariable;
	public Func1<Object[],Struct> f_atomicExpansions;
	public Func1<Object,Struct> f_collectConst;

	public Struct_MizPre() {}
	public Struct_MizPre(Struct_MizCorrelEnv a_env, Struct_Vector a_basic, int a_constBase, boolean a_constOvfl, boolean a_latOvfl, boolean a_trivialError, Func1<Struct,Struct> a_setVarInTrm, Func2<Object,Struct_MizPre, Struct_MizTyp> a_newVariable, Func1<Object[],Struct> a_atomicExpansions, Func1<Object,Struct> a_collectConst) {
		f_env = a_env;
		f_basic = a_basic;
		f_constBase = a_constBase;
		f_constOvfl = a_constOvfl;
		f_latOvfl = a_latOvfl;
		f_trivialError = a_trivialError;
		f_setVarInTrm = a_setVarInTrm;
		f_newVariable = a_newVariable;
		f_atomicExpansions = a_atomicExpansions;
		f_collectConst = a_collectConst;
	}
	public Integer get_constBase() { return f_constBase; }
	public void set_constBase(Integer value) { f_constBase = value; }
	public Boolean get_constOvfl() { return f_constOvfl; }
	public void set_constOvfl(Boolean value) { f_constOvfl = value; }
	public Boolean get_latOvfl() { return f_latOvfl; }
	public void set_latOvfl(Boolean value) { f_latOvfl = value; }
	public Boolean get_trivialError() { return f_trivialError; }
	public void set_trivialError(Boolean value) { f_trivialError = value; }
	public Func1<Struct,Struct> get_setVarInTrm() { return f_setVarInTrm; }
	public void set_setVarInTrm(Func1<Struct,Struct> value) { f_setVarInTrm = value; }
	public Func2<Object,Struct_MizPre, Struct_MizTyp> get_newVariable() { return f_newVariable; }
	public void set_newVariable(Func2<Object,Struct_MizPre, Struct_MizTyp> value) { f_newVariable = value; }
	public Func1<Object[],Struct> get_atomicExpansions() { return f_atomicExpansions; }
	public void set_atomicExpansions(Func1<Object[],Struct> value) { f_atomicExpansions = value; }
	public Func1<Object,Struct> get_collectConst() { return f_collectConst; }
	public void set_collectConst(Func1<Object,Struct> value) { f_collectConst = value; }

	public int getTypeId() { return 56; }
	public String getTypeName() { return "MizPre"; }

	private static final String[] field_names = new String[] {
		"env", "basic", "constBase", "constOvfl", "latOvfl", "trivialError", "setVarInTrm", "newVariable", "atomicExpansions", "collectConst"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.BOOL, RuntimeType.BOOL, RuntimeType.BOOL, RuntimeType.UNKNOWN, RuntimeType.UNKNOWN, RuntimeType.UNKNOWN, RuntimeType.UNKNOWN
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_env, f_basic, f_constBase, f_constOvfl, f_latOvfl, f_trivialError, f_setVarInTrm, f_newVariable, f_atomicExpansions, f_collectConst
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 10)
			throw new IndexOutOfBoundsException("Invalid field count in MizPre");
		f_env = (Struct_MizCorrelEnv)values[0];
		f_basic = (Struct_Vector)values[1];
		f_constBase = (Integer)values[2];
		f_constOvfl = (Boolean)values[3];
		f_latOvfl = (Boolean)values[4];
		f_trivialError = (Boolean)values[5];
		f_setVarInTrm = (Func1<Struct,Struct>)values[6];
		f_newVariable = (Func2<Object,Struct_MizPre, Struct_MizTyp>)values[7];
		f_atomicExpansions = (Func1<Object[],Struct>)values[8];
		f_collectConst = (Func1<Object,Struct>)values[9];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 56) return 56-tmp;
		Struct_MizPre other = (Struct_MizPre)other_gen;
		tmp = f_env.compareTo(other.f_env);
		if (tmp != 0) return tmp;
		tmp = f_basic.compareTo(other.f_basic);
		if (tmp != 0) return tmp;
		if (f_constBase != other.f_constBase)
			return (f_constBase > other.f_constBase) ? 1 : -1;
		if (f_constOvfl != other.f_constOvfl)
			return f_constOvfl ? 1 : -1;
		if (f_latOvfl != other.f_latOvfl)
			return f_latOvfl ? 1 : -1;
		if (f_trivialError != other.f_trivialError)
			return f_trivialError ? 1 : -1;
		tmp = FlowRuntime.compareByValue(f_setVarInTrm, other.f_setVarInTrm);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_newVariable, other.f_newVariable);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_atomicExpansions, other.f_atomicExpansions);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_collectConst, other.f_collectConst);
		return tmp;
	}
}
