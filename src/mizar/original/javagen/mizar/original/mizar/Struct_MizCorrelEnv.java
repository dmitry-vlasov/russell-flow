// HASH COLLISIONS: YES
// timestamp: 1785508391385

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizCorrelEnv extends Struct implements Field_strictCompare<Boolean>, Field_boundVarNbr<Integer>, Field_instantiated<Boolean>, Field_boundBase<Integer>, Field_fixedVarNbr<Integer>, Field_itTyp<Struct_MizTyp>, Field_nonZeroTyp<Struct_MizTyp>, Field_itIsChecker<Boolean>, Field_roundUpTrmType<Func2<Struct_MizTyp,Struct_MizCorrelEnv, Struct>>, Field_roundUpCluster<Func3<Object,Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp>> {
	public Object[] f_constrs;
	public Struct_MizBuiltIn f_builtIn;
	public Struct_Vector f_inferConstDef;
	public Struct_MizIntSeq f_inferConsts;
	public boolean f_strictCompare;
	public int f_boundVarNbr;
	public Struct_Vector f_boundVar;
	public boolean f_instantiated;
	public Struct_Vector f_instantiation;
	public int f_boundBase;
	public Struct_Vector f_fixedVar;
	public int f_fixedVarNbr;
	public Struct_Vector f_locArgTyp;
	public Struct_Vector f_curSchFuncTyp;
	public Struct_Vector f_locFuncDef;
	public Struct_MizTyp f_itTyp;
	public Struct_MizTyp f_nonZeroTyp;
	public Struct_Vector f_substTrm;
	public boolean f_itIsChecker;
	public Func2<Struct_MizTyp,Struct_MizCorrelEnv, Struct> f_roundUpTrmType;
	public Func3<Object,Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp> f_roundUpCluster;

	public Struct_MizCorrelEnv() {}
	public Struct_MizCorrelEnv(Object[] a_constrs, Struct_MizBuiltIn a_builtIn, Struct_Vector a_inferConstDef, Struct_MizIntSeq a_inferConsts, boolean a_strictCompare, int a_boundVarNbr, Struct_Vector a_boundVar, boolean a_instantiated, Struct_Vector a_instantiation, int a_boundBase, Struct_Vector a_fixedVar, int a_fixedVarNbr, Struct_Vector a_locArgTyp, Struct_Vector a_curSchFuncTyp, Struct_Vector a_locFuncDef, Struct_MizTyp a_itTyp, Struct_MizTyp a_nonZeroTyp, Struct_Vector a_substTrm, boolean a_itIsChecker, Func2<Struct_MizTyp,Struct_MizCorrelEnv, Struct> a_roundUpTrmType, Func3<Object,Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp> a_roundUpCluster) {
		f_constrs = a_constrs;
		f_builtIn = a_builtIn;
		f_inferConstDef = a_inferConstDef;
		f_inferConsts = a_inferConsts;
		f_strictCompare = a_strictCompare;
		f_boundVarNbr = a_boundVarNbr;
		f_boundVar = a_boundVar;
		f_instantiated = a_instantiated;
		f_instantiation = a_instantiation;
		f_boundBase = a_boundBase;
		f_fixedVar = a_fixedVar;
		f_fixedVarNbr = a_fixedVarNbr;
		f_locArgTyp = a_locArgTyp;
		f_curSchFuncTyp = a_curSchFuncTyp;
		f_locFuncDef = a_locFuncDef;
		f_itTyp = a_itTyp;
		f_nonZeroTyp = a_nonZeroTyp;
		f_substTrm = a_substTrm;
		f_itIsChecker = a_itIsChecker;
		f_roundUpTrmType = a_roundUpTrmType;
		f_roundUpCluster = a_roundUpCluster;
	}
	public Boolean get_strictCompare() { return f_strictCompare; }
	public void set_strictCompare(Boolean value) { f_strictCompare = value; }
	public Integer get_boundVarNbr() { return f_boundVarNbr; }
	public void set_boundVarNbr(Integer value) { f_boundVarNbr = value; }
	public Boolean get_instantiated() { return f_instantiated; }
	public void set_instantiated(Boolean value) { f_instantiated = value; }
	public Integer get_boundBase() { return f_boundBase; }
	public void set_boundBase(Integer value) { f_boundBase = value; }
	public Integer get_fixedVarNbr() { return f_fixedVarNbr; }
	public void set_fixedVarNbr(Integer value) { f_fixedVarNbr = value; }
	public Struct_MizTyp get_itTyp() { return f_itTyp; }
	public void set_itTyp(Struct_MizTyp value) { f_itTyp = value; }
	public Struct_MizTyp get_nonZeroTyp() { return f_nonZeroTyp; }
	public void set_nonZeroTyp(Struct_MizTyp value) { f_nonZeroTyp = value; }
	public Boolean get_itIsChecker() { return f_itIsChecker; }
	public void set_itIsChecker(Boolean value) { f_itIsChecker = value; }
	public Func2<Struct_MizTyp,Struct_MizCorrelEnv, Struct> get_roundUpTrmType() { return f_roundUpTrmType; }
	public void set_roundUpTrmType(Func2<Struct_MizTyp,Struct_MizCorrelEnv, Struct> value) { f_roundUpTrmType = value; }
	public Func3<Object,Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp> get_roundUpCluster() { return f_roundUpCluster; }
	public void set_roundUpCluster(Func3<Object,Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp> value) { f_roundUpCluster = value; }

	public int getTypeId() { return 29; }
	public String getTypeName() { return "MizCorrelEnv"; }

	private static final String[] field_names = new String[] {
		"constrs", "builtIn", "inferConstDef", "inferConsts", "strictCompare", "boundVarNbr", "boundVar", "instantiated", "instantiation", "boundBase", "fixedVar", "fixedVarNbr", "locArgTyp", "curSchFuncTyp", "locFuncDef", "itTyp", "nonZeroTyp", "substTrm", "itIsChecker", "roundUpTrmType", "roundUpCluster"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.ARRAY, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.UNKNOWN, RuntimeType.UNKNOWN
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_constrs, f_builtIn, f_inferConstDef, f_inferConsts, f_strictCompare, f_boundVarNbr, f_boundVar, f_instantiated, f_instantiation, f_boundBase, f_fixedVar, f_fixedVarNbr, f_locArgTyp, f_curSchFuncTyp, f_locFuncDef, f_itTyp, f_nonZeroTyp, f_substTrm, f_itIsChecker, f_roundUpTrmType, f_roundUpCluster
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 21)
			throw new IndexOutOfBoundsException("Invalid field count in MizCorrelEnv");
		f_constrs = (Object[])values[0];
		f_builtIn = (Struct_MizBuiltIn)values[1];
		f_inferConstDef = (Struct_Vector)values[2];
		f_inferConsts = (Struct_MizIntSeq)values[3];
		f_strictCompare = (Boolean)values[4];
		f_boundVarNbr = (Integer)values[5];
		f_boundVar = (Struct_Vector)values[6];
		f_instantiated = (Boolean)values[7];
		f_instantiation = (Struct_Vector)values[8];
		f_boundBase = (Integer)values[9];
		f_fixedVar = (Struct_Vector)values[10];
		f_fixedVarNbr = (Integer)values[11];
		f_locArgTyp = (Struct_Vector)values[12];
		f_curSchFuncTyp = (Struct_Vector)values[13];
		f_locFuncDef = (Struct_Vector)values[14];
		f_itTyp = (Struct_MizTyp)values[15];
		f_nonZeroTyp = (Struct_MizTyp)values[16];
		f_substTrm = (Struct_Vector)values[17];
		f_itIsChecker = (Boolean)values[18];
		f_roundUpTrmType = (Func2<Struct_MizTyp,Struct_MizCorrelEnv, Struct>)values[19];
		f_roundUpCluster = (Func3<Object,Struct_MizCorrelEnv, Struct_MizAttrColl, Struct_MizTyp>)values[20];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 29) return 29-tmp;
		Struct_MizCorrelEnv other = (Struct_MizCorrelEnv)other_gen;
		tmp = FlowRuntime.compareByValue(f_constrs, other.f_constrs);
		if (tmp != 0) return tmp;
		tmp = f_builtIn.compareTo(other.f_builtIn);
		if (tmp != 0) return tmp;
		tmp = f_inferConstDef.compareTo(other.f_inferConstDef);
		if (tmp != 0) return tmp;
		tmp = f_inferConsts.compareTo(other.f_inferConsts);
		if (tmp != 0) return tmp;
		if (f_strictCompare != other.f_strictCompare)
			return f_strictCompare ? 1 : -1;
		if (f_boundVarNbr != other.f_boundVarNbr)
			return (f_boundVarNbr > other.f_boundVarNbr) ? 1 : -1;
		tmp = f_boundVar.compareTo(other.f_boundVar);
		if (tmp != 0) return tmp;
		if (f_instantiated != other.f_instantiated)
			return f_instantiated ? 1 : -1;
		tmp = f_instantiation.compareTo(other.f_instantiation);
		if (tmp != 0) return tmp;
		if (f_boundBase != other.f_boundBase)
			return (f_boundBase > other.f_boundBase) ? 1 : -1;
		tmp = f_fixedVar.compareTo(other.f_fixedVar);
		if (tmp != 0) return tmp;
		if (f_fixedVarNbr != other.f_fixedVarNbr)
			return (f_fixedVarNbr > other.f_fixedVarNbr) ? 1 : -1;
		tmp = f_locArgTyp.compareTo(other.f_locArgTyp);
		if (tmp != 0) return tmp;
		tmp = f_curSchFuncTyp.compareTo(other.f_curSchFuncTyp);
		if (tmp != 0) return tmp;
		tmp = f_locFuncDef.compareTo(other.f_locFuncDef);
		if (tmp != 0) return tmp;
		tmp = f_itTyp.compareTo(other.f_itTyp);
		if (tmp != 0) return tmp;
		tmp = f_nonZeroTyp.compareTo(other.f_nonZeroTyp);
		if (tmp != 0) return tmp;
		tmp = f_substTrm.compareTo(other.f_substTrm);
		if (tmp != 0) return tmp;
		if (f_itIsChecker != other.f_itIsChecker)
			return f_itIsChecker ? 1 : -1;
		tmp = FlowRuntime.compareByValue(f_roundUpTrmType, other.f_roundUpTrmType);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_roundUpCluster, other.f_roundUpCluster);
		return tmp;
	}
}
