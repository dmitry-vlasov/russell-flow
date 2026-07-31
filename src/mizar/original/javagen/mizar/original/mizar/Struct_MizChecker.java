// HASH COLLISIONS: YES
// timestamp: 1785512695545

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizChecker extends Struct implements Field_unification<Func2<Integer,Struct_MizChecker, Struct_MizEqualizer>>, Field_atomicExpansions<Func1<Object[],Struct>>, Field_debug<Boolean> {
	public Struct_MizCorrelEnv f_env;
	public Struct_MizIdentify f_idn;
	public Struct_MizClusters f_clusters;
	public Struct_MizIntSeq f_chErrNr;
	public Func2<Integer,Struct_MizChecker, Struct_MizEqualizer> f_unification;
	public Func1<Object[],Struct> f_atomicExpansions;
	public boolean f_debug;

	public Struct_MizChecker() {}
	public Struct_MizChecker(Struct_MizCorrelEnv a_env, Struct_MizIdentify a_idn, Struct_MizClusters a_clusters, Struct_MizIntSeq a_chErrNr, Func2<Integer,Struct_MizChecker, Struct_MizEqualizer> a_unification, Func1<Object[],Struct> a_atomicExpansions, boolean a_debug) {
		f_env = a_env;
		f_idn = a_idn;
		f_clusters = a_clusters;
		f_chErrNr = a_chErrNr;
		f_unification = a_unification;
		f_atomicExpansions = a_atomicExpansions;
		f_debug = a_debug;
	}
	public Func2<Integer,Struct_MizChecker, Struct_MizEqualizer> get_unification() { return f_unification; }
	public void set_unification(Func2<Integer,Struct_MizChecker, Struct_MizEqualizer> value) { f_unification = value; }
	public Func1<Object[],Struct> get_atomicExpansions() { return f_atomicExpansions; }
	public void set_atomicExpansions(Func1<Object[],Struct> value) { f_atomicExpansions = value; }
	public Boolean get_debug() { return f_debug; }
	public void set_debug(Boolean value) { f_debug = value; }

	public int getTypeId() { return 20; }
	public String getTypeName() { return "MizChecker"; }

	private static final String[] field_names = new String[] {
		"env", "idn", "clusters", "chErrNr", "unification", "atomicExpansions", "debug"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.UNKNOWN, RuntimeType.UNKNOWN, RuntimeType.BOOL
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_env, f_idn, f_clusters, f_chErrNr, f_unification, f_atomicExpansions, f_debug
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 7)
			throw new IndexOutOfBoundsException("Invalid field count in MizChecker");
		f_env = (Struct_MizCorrelEnv)values[0];
		f_idn = (Struct_MizIdentify)values[1];
		f_clusters = (Struct_MizClusters)values[2];
		f_chErrNr = (Struct_MizIntSeq)values[3];
		f_unification = (Func2<Integer,Struct_MizChecker, Struct_MizEqualizer>)values[4];
		f_atomicExpansions = (Func1<Object[],Struct>)values[5];
		f_debug = (Boolean)values[6];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 20) return 20-tmp;
		Struct_MizChecker other = (Struct_MizChecker)other_gen;
		tmp = f_env.compareTo(other.f_env);
		if (tmp != 0) return tmp;
		tmp = f_idn.compareTo(other.f_idn);
		if (tmp != 0) return tmp;
		tmp = f_clusters.compareTo(other.f_clusters);
		if (tmp != 0) return tmp;
		tmp = f_chErrNr.compareTo(other.f_chErrNr);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_unification, other.f_unification);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_atomicExpansions, other.f_atomicExpansions);
		if (tmp != 0) return tmp;
		if (f_debug != other.f_debug)
			return f_debug ? 1 : -1;
		return 0;
	}
}
