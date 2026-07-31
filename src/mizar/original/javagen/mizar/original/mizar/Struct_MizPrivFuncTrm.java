// HASH COLLISIONS: YES
// timestamp: 1785508391388

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizPrivFuncTrm extends Struct implements Field_funcExp<Struct>, Field_trmInfo<Integer> {
	public int f_funcNr;
	public Struct f_args;
	public Struct f_funcExp;
	public int f_pattNr;
	public int f_trmInfo;

	public Struct_MizPrivFuncTrm() {}
	public Struct_MizPrivFuncTrm(int a_funcNr, Struct a_args, Struct a_funcExp, int a_pattNr, int a_trmInfo) {
		f_funcNr = a_funcNr;
		f_args = a_args;
		f_funcExp = a_funcExp;
		f_pattNr = a_pattNr;
		f_trmInfo = a_trmInfo;
	}
	public Struct get_funcExp() { return f_funcExp; }
	public void set_funcExp(Struct value) { f_funcExp = value; }
	public Integer get_trmInfo() { return f_trmInfo; }
	public void set_trmInfo(Integer value) { f_trmInfo = value; }

	public int getTypeId() { return 58; }
	public String getTypeName() { return "MizPrivFuncTrm"; }

	private static final String[] field_names = new String[] {
		"funcNr", "args", "funcExp", "pattNr", "trmInfo"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_funcNr, f_args, f_funcExp, f_pattNr, f_trmInfo
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizPrivFuncTrm");
		f_funcNr = (Integer)values[0];
		f_args = (Struct)values[1];
		f_funcExp = (Struct)values[2];
		f_pattNr = (Integer)values[3];
		f_trmInfo = (Integer)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 58) return 58-tmp;
		Struct_MizPrivFuncTrm other = (Struct_MizPrivFuncTrm)other_gen;
		if (f_funcNr != other.f_funcNr)
			return (f_funcNr > other.f_funcNr) ? 1 : -1;
		tmp = f_args.compareTo(other.f_args);
		if (tmp != 0) return tmp;
		tmp = f_funcExp.compareTo(other.f_funcExp);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		if (f_trmInfo != other.f_trmInfo)
			return (f_trmInfo > other.f_trmInfo) ? 1 : -1;
		return 0;
	}
}
