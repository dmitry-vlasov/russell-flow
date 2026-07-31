// HASH COLLISIONS: YES
// timestamp: 1785524849106

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizIdentify extends Struct implements Field_onlyConstants<Boolean>, Field_equalsExpansionLevel<Integer>, Field_equalsExpand<Func3<Object[],Struct_MizIdentify, Integer, Struct>> {
	public Struct_MizCorrelEnv f_env;
	public Struct_Vector f_sortedIdx;
	public Struct_Vector f_numeric;
	public boolean f_onlyConstants;
	public int f_equalsExpansionLevel;
	public Struct_Vector f_revReq;
	public Func3<Object[],Struct_MizIdentify, Integer, Struct> f_equalsExpand;
	public Struct_Vector f_reductions;
	public Struct_Vector f_identifications;

	public Struct_MizIdentify() {}
	public Struct_MizIdentify(Struct_MizCorrelEnv a_env, Struct_Vector a_sortedIdx, Struct_Vector a_numeric, boolean a_onlyConstants, int a_equalsExpansionLevel, Struct_Vector a_revReq, Func3<Object[],Struct_MizIdentify, Integer, Struct> a_equalsExpand, Struct_Vector a_reductions, Struct_Vector a_identifications) {
		f_env = a_env;
		f_sortedIdx = a_sortedIdx;
		f_numeric = a_numeric;
		f_onlyConstants = a_onlyConstants;
		f_equalsExpansionLevel = a_equalsExpansionLevel;
		f_revReq = a_revReq;
		f_equalsExpand = a_equalsExpand;
		f_reductions = a_reductions;
		f_identifications = a_identifications;
	}
	public Boolean get_onlyConstants() { return f_onlyConstants; }
	public void set_onlyConstants(Boolean value) { f_onlyConstants = value; }
	public Integer get_equalsExpansionLevel() { return f_equalsExpansionLevel; }
	public void set_equalsExpansionLevel(Integer value) { f_equalsExpansionLevel = value; }
	public Func3<Object[],Struct_MizIdentify, Integer, Struct> get_equalsExpand() { return f_equalsExpand; }
	public void set_equalsExpand(Func3<Object[],Struct_MizIdentify, Integer, Struct> value) { f_equalsExpand = value; }

	public int getTypeId() { return 46; }
	public String getTypeName() { return "MizIdentify"; }

	private static final String[] field_names = new String[] {
		"env", "sortedIdx", "numeric", "onlyConstants", "equalsExpansionLevel", "revReq", "equalsExpand", "reductions", "identifications"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.UNKNOWN, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_env, f_sortedIdx, f_numeric, f_onlyConstants, f_equalsExpansionLevel, f_revReq, f_equalsExpand, f_reductions, f_identifications
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 9)
			throw new IndexOutOfBoundsException("Invalid field count in MizIdentify");
		f_env = (Struct_MizCorrelEnv)values[0];
		f_sortedIdx = (Struct_Vector)values[1];
		f_numeric = (Struct_Vector)values[2];
		f_onlyConstants = (Boolean)values[3];
		f_equalsExpansionLevel = (Integer)values[4];
		f_revReq = (Struct_Vector)values[5];
		f_equalsExpand = (Func3<Object[],Struct_MizIdentify, Integer, Struct>)values[6];
		f_reductions = (Struct_Vector)values[7];
		f_identifications = (Struct_Vector)values[8];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 46) return 46-tmp;
		Struct_MizIdentify other = (Struct_MizIdentify)other_gen;
		tmp = f_env.compareTo(other.f_env);
		if (tmp != 0) return tmp;
		tmp = f_sortedIdx.compareTo(other.f_sortedIdx);
		if (tmp != 0) return tmp;
		tmp = f_numeric.compareTo(other.f_numeric);
		if (tmp != 0) return tmp;
		if (f_onlyConstants != other.f_onlyConstants)
			return f_onlyConstants ? 1 : -1;
		if (f_equalsExpansionLevel != other.f_equalsExpansionLevel)
			return (f_equalsExpansionLevel > other.f_equalsExpansionLevel) ? 1 : -1;
		tmp = f_revReq.compareTo(other.f_revReq);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_equalsExpand, other.f_equalsExpand);
		if (tmp != 0) return tmp;
		tmp = f_reductions.compareTo(other.f_reductions);
		if (tmp != 0) return tmp;
		tmp = f_identifications.compareTo(other.f_identifications);
		return tmp;
	}
}
