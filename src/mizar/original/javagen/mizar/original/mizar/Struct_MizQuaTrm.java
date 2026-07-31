// HASH COLLISIONS: YES
// timestamp: 1785508391388

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizQuaTrm extends Struct implements Field_trmProper<Struct>, Field_trmInfo<Integer> {
	public Struct f_trmProper;
	public Struct_MizTyp f_qua;
	public int f_pattNr;
	public int f_trmInfo;

	public Struct_MizQuaTrm() {}
	public Struct_MizQuaTrm(Struct a_trmProper, Struct_MizTyp a_qua, int a_pattNr, int a_trmInfo) {
		f_trmProper = a_trmProper;
		f_qua = a_qua;
		f_pattNr = a_pattNr;
		f_trmInfo = a_trmInfo;
	}
	public Struct get_trmProper() { return f_trmProper; }
	public void set_trmProper(Struct value) { f_trmProper = value; }
	public Integer get_trmInfo() { return f_trmInfo; }
	public void set_trmInfo(Integer value) { f_trmInfo = value; }

	public int getTypeId() { return 59; }
	public String getTypeName() { return "MizQuaTrm"; }

	private static final String[] field_names = new String[] {
		"trmProper", "qua", "pattNr", "trmInfo"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_trmProper, f_qua, f_pattNr, f_trmInfo
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 4)
			throw new IndexOutOfBoundsException("Invalid field count in MizQuaTrm");
		f_trmProper = (Struct)values[0];
		f_qua = (Struct_MizTyp)values[1];
		f_pattNr = (Integer)values[2];
		f_trmInfo = (Integer)values[3];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 59) return 59-tmp;
		Struct_MizQuaTrm other = (Struct_MizQuaTrm)other_gen;
		tmp = f_trmProper.compareTo(other.f_trmProper);
		if (tmp != 0) return tmp;
		tmp = f_qua.compareTo(other.f_qua);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		if (f_trmInfo != other.f_trmInfo)
			return (f_trmInfo > other.f_trmInfo) ? 1 : -1;
		return 0;
	}
}
