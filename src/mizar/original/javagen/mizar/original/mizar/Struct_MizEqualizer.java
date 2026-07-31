// HASH COLLISIONS: YES
// timestamp: 1785508391385

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizEqualizer extends Struct implements Field_trmOvfl<Boolean>, Field_contr<Integer>, Field_eqClassNbr<Integer>, Field_clash<Boolean>, Field_anInst<Struct_MizNatFunc> {
	public Struct_MizCorrelEnv f_env;
	public Struct_MizIdentify f_idn;
	public Struct_MizClusters f_clusters;
	public Struct_MizPre f_pre;
	public Struct_Vector f_trmS;
	public boolean f_trmOvfl;
	public int f_contr;
	public int f_eqClassNbr;
	public Struct_Vector f_posBas;
	public Struct_Vector f_negBas;
	public Struct_Vector f_dTrm;
	public Struct_Vector f_funcTrmIdx;
	public Struct_Vector f_frOper;
	public Struct_Vector f_choiceTerm;
	public boolean f_clash;
	public Struct_Vector f_eqPend;
	public Struct_Vector f_settings;
	public Struct_Vector f_allowedC;
	public Struct_Vector f_allowedF;
	public Struct_Vector f_anDeps;
	public Struct_MizNatFunc f_anStack;
	public Struct_MizNatFunc f_anInst;

	public Struct_MizEqualizer() {}
	public Struct_MizEqualizer(Struct_MizCorrelEnv a_env, Struct_MizIdentify a_idn, Struct_MizClusters a_clusters, Struct_MizPre a_pre, Struct_Vector a_trmS, boolean a_trmOvfl, int a_contr, int a_eqClassNbr, Struct_Vector a_posBas, Struct_Vector a_negBas, Struct_Vector a_dTrm, Struct_Vector a_funcTrmIdx, Struct_Vector a_frOper, Struct_Vector a_choiceTerm, boolean a_clash, Struct_Vector a_eqPend, Struct_Vector a_settings, Struct_Vector a_allowedC, Struct_Vector a_allowedF, Struct_Vector a_anDeps, Struct_MizNatFunc a_anStack, Struct_MizNatFunc a_anInst) {
		f_env = a_env;
		f_idn = a_idn;
		f_clusters = a_clusters;
		f_pre = a_pre;
		f_trmS = a_trmS;
		f_trmOvfl = a_trmOvfl;
		f_contr = a_contr;
		f_eqClassNbr = a_eqClassNbr;
		f_posBas = a_posBas;
		f_negBas = a_negBas;
		f_dTrm = a_dTrm;
		f_funcTrmIdx = a_funcTrmIdx;
		f_frOper = a_frOper;
		f_choiceTerm = a_choiceTerm;
		f_clash = a_clash;
		f_eqPend = a_eqPend;
		f_settings = a_settings;
		f_allowedC = a_allowedC;
		f_allowedF = a_allowedF;
		f_anDeps = a_anDeps;
		f_anStack = a_anStack;
		f_anInst = a_anInst;
	}
	public Boolean get_trmOvfl() { return f_trmOvfl; }
	public void set_trmOvfl(Boolean value) { f_trmOvfl = value; }
	public Integer get_contr() { return f_contr; }
	public void set_contr(Integer value) { f_contr = value; }
	public Integer get_eqClassNbr() { return f_eqClassNbr; }
	public void set_eqClassNbr(Integer value) { f_eqClassNbr = value; }
	public Boolean get_clash() { return f_clash; }
	public void set_clash(Boolean value) { f_clash = value; }
	public Struct_MizNatFunc get_anInst() { return f_anInst; }
	public void set_anInst(Struct_MizNatFunc value) { f_anInst = value; }

	public int getTypeId() { return 35; }
	public String getTypeName() { return "MizEqualizer"; }

	private static final String[] field_names = new String[] {
		"env", "idn", "clusters", "pre", "trmS", "trmOvfl", "contr", "eqClassNbr", "posBas", "negBas", "dTrm", "funcTrmIdx", "frOper", "choiceTerm", "clash", "eqPend", "settings", "allowedC", "allowedF", "anDeps", "anStack", "anInst"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_env, f_idn, f_clusters, f_pre, f_trmS, f_trmOvfl, f_contr, f_eqClassNbr, f_posBas, f_negBas, f_dTrm, f_funcTrmIdx, f_frOper, f_choiceTerm, f_clash, f_eqPend, f_settings, f_allowedC, f_allowedF, f_anDeps, f_anStack, f_anInst
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 22)
			throw new IndexOutOfBoundsException("Invalid field count in MizEqualizer");
		f_env = (Struct_MizCorrelEnv)values[0];
		f_idn = (Struct_MizIdentify)values[1];
		f_clusters = (Struct_MizClusters)values[2];
		f_pre = (Struct_MizPre)values[3];
		f_trmS = (Struct_Vector)values[4];
		f_trmOvfl = (Boolean)values[5];
		f_contr = (Integer)values[6];
		f_eqClassNbr = (Integer)values[7];
		f_posBas = (Struct_Vector)values[8];
		f_negBas = (Struct_Vector)values[9];
		f_dTrm = (Struct_Vector)values[10];
		f_funcTrmIdx = (Struct_Vector)values[11];
		f_frOper = (Struct_Vector)values[12];
		f_choiceTerm = (Struct_Vector)values[13];
		f_clash = (Boolean)values[14];
		f_eqPend = (Struct_Vector)values[15];
		f_settings = (Struct_Vector)values[16];
		f_allowedC = (Struct_Vector)values[17];
		f_allowedF = (Struct_Vector)values[18];
		f_anDeps = (Struct_Vector)values[19];
		f_anStack = (Struct_MizNatFunc)values[20];
		f_anInst = (Struct_MizNatFunc)values[21];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 35) return 35-tmp;
		Struct_MizEqualizer other = (Struct_MizEqualizer)other_gen;
		tmp = f_env.compareTo(other.f_env);
		if (tmp != 0) return tmp;
		tmp = f_idn.compareTo(other.f_idn);
		if (tmp != 0) return tmp;
		tmp = f_clusters.compareTo(other.f_clusters);
		if (tmp != 0) return tmp;
		tmp = f_pre.compareTo(other.f_pre);
		if (tmp != 0) return tmp;
		tmp = f_trmS.compareTo(other.f_trmS);
		if (tmp != 0) return tmp;
		if (f_trmOvfl != other.f_trmOvfl)
			return f_trmOvfl ? 1 : -1;
		if (f_contr != other.f_contr)
			return (f_contr > other.f_contr) ? 1 : -1;
		if (f_eqClassNbr != other.f_eqClassNbr)
			return (f_eqClassNbr > other.f_eqClassNbr) ? 1 : -1;
		tmp = f_posBas.compareTo(other.f_posBas);
		if (tmp != 0) return tmp;
		tmp = f_negBas.compareTo(other.f_negBas);
		if (tmp != 0) return tmp;
		tmp = f_dTrm.compareTo(other.f_dTrm);
		if (tmp != 0) return tmp;
		tmp = f_funcTrmIdx.compareTo(other.f_funcTrmIdx);
		if (tmp != 0) return tmp;
		tmp = f_frOper.compareTo(other.f_frOper);
		if (tmp != 0) return tmp;
		tmp = f_choiceTerm.compareTo(other.f_choiceTerm);
		if (tmp != 0) return tmp;
		if (f_clash != other.f_clash)
			return f_clash ? 1 : -1;
		tmp = f_eqPend.compareTo(other.f_eqPend);
		if (tmp != 0) return tmp;
		tmp = f_settings.compareTo(other.f_settings);
		if (tmp != 0) return tmp;
		tmp = f_allowedC.compareTo(other.f_allowedC);
		if (tmp != 0) return tmp;
		tmp = f_allowedF.compareTo(other.f_allowedF);
		if (tmp != 0) return tmp;
		tmp = f_anDeps.compareTo(other.f_anDeps);
		if (tmp != 0) return tmp;
		tmp = f_anStack.compareTo(other.f_anStack);
		if (tmp != 0) return tmp;
		tmp = f_anInst.compareTo(other.f_anInst);
		return tmp;
	}
}
