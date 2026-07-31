// HASH COLLISIONS: YES
// timestamp: 1785512695549

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizFraenkelTrm extends Struct implements Field_scope<Struct>, Field_trmInfo<Integer> {
	public Struct_Vector f_lambdaArgs;
	public Struct f_scope;
	public Struct f_compr;
	public Struct_MizIntSeq f_idents;
	public int f_pattNr;
	public int f_trmInfo;

	public Struct_MizFraenkelTrm() {}
	public Struct_MizFraenkelTrm(Struct_Vector a_lambdaArgs, Struct a_scope, Struct a_compr, Struct_MizIntSeq a_idents, int a_pattNr, int a_trmInfo) {
		f_lambdaArgs = a_lambdaArgs;
		f_scope = a_scope;
		f_compr = a_compr;
		f_idents = a_idents;
		f_pattNr = a_pattNr;
		f_trmInfo = a_trmInfo;
	}
	public Struct get_scope() { return f_scope; }
	public void set_scope(Struct value) { f_scope = value; }
	public Integer get_trmInfo() { return f_trmInfo; }
	public void set_trmInfo(Integer value) { f_trmInfo = value; }

	public int getTypeId() { return 42; }
	public String getTypeName() { return "MizFraenkelTrm"; }

	private static final String[] field_names = new String[] {
		"lambdaArgs", "scope", "compr", "idents", "pattNr", "trmInfo"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_lambdaArgs, f_scope, f_compr, f_idents, f_pattNr, f_trmInfo
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 6)
			throw new IndexOutOfBoundsException("Invalid field count in MizFraenkelTrm");
		f_lambdaArgs = (Struct_Vector)values[0];
		f_scope = (Struct)values[1];
		f_compr = (Struct)values[2];
		f_idents = (Struct_MizIntSeq)values[3];
		f_pattNr = (Integer)values[4];
		f_trmInfo = (Integer)values[5];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 42) return 42-tmp;
		Struct_MizFraenkelTrm other = (Struct_MizFraenkelTrm)other_gen;
		tmp = f_lambdaArgs.compareTo(other.f_lambdaArgs);
		if (tmp != 0) return tmp;
		tmp = f_scope.compareTo(other.f_scope);
		if (tmp != 0) return tmp;
		tmp = f_compr.compareTo(other.f_compr);
		if (tmp != 0) return tmp;
		tmp = f_idents.compareTo(other.f_idents);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		if (f_trmInfo != other.f_trmInfo)
			return (f_trmInfo > other.f_trmInfo) ? 1 : -1;
		return 0;
	}
}
