// HASH COLLISIONS: YES
// timestamp: 1785524849110

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizUnifier extends Struct implements Field_eqClassNbr<Integer>, Field_freeVarBase<Integer>, Field_unifBase<Integer>, Field_freeVarUnifs<Struct>, Field_debug<Boolean> {
	public Struct_MizCorrelEnv f_env;
	public Struct_MizIdentify f_idn;
	public Struct_MizEqualizer f_eq;
	public int f_eqClassNbr;
	public Struct_Vector f_eqList;
	public Struct_MizNatFunc f_dConstEqClass;
	public Struct_Vector f_eqClassVal;
	public Struct_Vector f_eqClassType;
	public Struct_Vector f_eqClassSuper;
	public Struct_Vector f_eTrm;
	public Struct_Vector f_freeVarType;
	public int f_freeVarBase;
	public int f_unifBase;
	public Struct f_freeVarUnifs;
	public Struct_MizPre f_pre;
	public boolean f_debug;

	public Struct_MizUnifier() {}
	public Struct_MizUnifier(Struct_MizCorrelEnv a_env, Struct_MizIdentify a_idn, Struct_MizEqualizer a_eq, int a_eqClassNbr, Struct_Vector a_eqList, Struct_MizNatFunc a_dConstEqClass, Struct_Vector a_eqClassVal, Struct_Vector a_eqClassType, Struct_Vector a_eqClassSuper, Struct_Vector a_eTrm, Struct_Vector a_freeVarType, int a_freeVarBase, int a_unifBase, Struct a_freeVarUnifs, Struct_MizPre a_pre, boolean a_debug) {
		f_env = a_env;
		f_idn = a_idn;
		f_eq = a_eq;
		f_eqClassNbr = a_eqClassNbr;
		f_eqList = a_eqList;
		f_dConstEqClass = a_dConstEqClass;
		f_eqClassVal = a_eqClassVal;
		f_eqClassType = a_eqClassType;
		f_eqClassSuper = a_eqClassSuper;
		f_eTrm = a_eTrm;
		f_freeVarType = a_freeVarType;
		f_freeVarBase = a_freeVarBase;
		f_unifBase = a_unifBase;
		f_freeVarUnifs = a_freeVarUnifs;
		f_pre = a_pre;
		f_debug = a_debug;
	}
	public Integer get_eqClassNbr() { return f_eqClassNbr; }
	public void set_eqClassNbr(Integer value) { f_eqClassNbr = value; }
	public Integer get_freeVarBase() { return f_freeVarBase; }
	public void set_freeVarBase(Integer value) { f_freeVarBase = value; }
	public Integer get_unifBase() { return f_unifBase; }
	public void set_unifBase(Integer value) { f_unifBase = value; }
	public Struct get_freeVarUnifs() { return f_freeVarUnifs; }
	public void set_freeVarUnifs(Struct value) { f_freeVarUnifs = value; }
	public Boolean get_debug() { return f_debug; }
	public void set_debug(Boolean value) { f_debug = value; }

	public int getTypeId() { return 74; }
	public String getTypeName() { return "MizUnifier"; }

	private static final String[] field_names = new String[] {
		"env", "idn", "eq", "eqClassNbr", "eqList", "dConstEqClass", "eqClassVal", "eqClassType", "eqClassSuper", "eTrm", "freeVarType", "freeVarBase", "unifBase", "freeVarUnifs", "pre", "debug"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.BOOL
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_env, f_idn, f_eq, f_eqClassNbr, f_eqList, f_dConstEqClass, f_eqClassVal, f_eqClassType, f_eqClassSuper, f_eTrm, f_freeVarType, f_freeVarBase, f_unifBase, f_freeVarUnifs, f_pre, f_debug
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 16)
			throw new IndexOutOfBoundsException("Invalid field count in MizUnifier");
		f_env = (Struct_MizCorrelEnv)values[0];
		f_idn = (Struct_MizIdentify)values[1];
		f_eq = (Struct_MizEqualizer)values[2];
		f_eqClassNbr = (Integer)values[3];
		f_eqList = (Struct_Vector)values[4];
		f_dConstEqClass = (Struct_MizNatFunc)values[5];
		f_eqClassVal = (Struct_Vector)values[6];
		f_eqClassType = (Struct_Vector)values[7];
		f_eqClassSuper = (Struct_Vector)values[8];
		f_eTrm = (Struct_Vector)values[9];
		f_freeVarType = (Struct_Vector)values[10];
		f_freeVarBase = (Integer)values[11];
		f_unifBase = (Integer)values[12];
		f_freeVarUnifs = (Struct)values[13];
		f_pre = (Struct_MizPre)values[14];
		f_debug = (Boolean)values[15];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 74) return 74-tmp;
		Struct_MizUnifier other = (Struct_MizUnifier)other_gen;
		tmp = f_env.compareTo(other.f_env);
		if (tmp != 0) return tmp;
		tmp = f_idn.compareTo(other.f_idn);
		if (tmp != 0) return tmp;
		tmp = f_eq.compareTo(other.f_eq);
		if (tmp != 0) return tmp;
		if (f_eqClassNbr != other.f_eqClassNbr)
			return (f_eqClassNbr > other.f_eqClassNbr) ? 1 : -1;
		tmp = f_eqList.compareTo(other.f_eqList);
		if (tmp != 0) return tmp;
		tmp = f_dConstEqClass.compareTo(other.f_dConstEqClass);
		if (tmp != 0) return tmp;
		tmp = f_eqClassVal.compareTo(other.f_eqClassVal);
		if (tmp != 0) return tmp;
		tmp = f_eqClassType.compareTo(other.f_eqClassType);
		if (tmp != 0) return tmp;
		tmp = f_eqClassSuper.compareTo(other.f_eqClassSuper);
		if (tmp != 0) return tmp;
		tmp = f_eTrm.compareTo(other.f_eTrm);
		if (tmp != 0) return tmp;
		tmp = f_freeVarType.compareTo(other.f_freeVarType);
		if (tmp != 0) return tmp;
		if (f_freeVarBase != other.f_freeVarBase)
			return (f_freeVarBase > other.f_freeVarBase) ? 1 : -1;
		if (f_unifBase != other.f_unifBase)
			return (f_unifBase > other.f_unifBase) ? 1 : -1;
		tmp = f_freeVarUnifs.compareTo(other.f_freeVarUnifs);
		if (tmp != 0) return tmp;
		tmp = f_pre.compareTo(other.f_pre);
		if (tmp != 0) return tmp;
		if (f_debug != other.f_debug)
			return f_debug ? 1 : -1;
		return 0;
	}
}
