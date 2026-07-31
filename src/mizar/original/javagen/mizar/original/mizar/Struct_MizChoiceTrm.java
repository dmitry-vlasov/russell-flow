// HASH COLLISIONS: YES
// timestamp: 1785512695546

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizChoiceTrm extends Struct implements Field_trmInfo<Integer> {
	public Struct_MizTyp f_choiceTyp;
	public int f_pattNr;
	public int f_trmInfo;

	public Struct_MizChoiceTrm() {}
	public Struct_MizChoiceTrm(Struct_MizTyp a_choiceTyp, int a_pattNr, int a_trmInfo) {
		f_choiceTyp = a_choiceTyp;
		f_pattNr = a_pattNr;
		f_trmInfo = a_trmInfo;
	}
	public Integer get_trmInfo() { return f_trmInfo; }
	public void set_trmInfo(Integer value) { f_trmInfo = value; }

	public int getTypeId() { return 21; }
	public String getTypeName() { return "MizChoiceTrm"; }

	private static final String[] field_names = new String[] {
		"choiceTyp", "pattNr", "trmInfo"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_choiceTyp, f_pattNr, f_trmInfo
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizChoiceTrm");
		f_choiceTyp = (Struct_MizTyp)values[0];
		f_pattNr = (Integer)values[1];
		f_trmInfo = (Integer)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 21) return 21-tmp;
		Struct_MizChoiceTrm other = (Struct_MizChoiceTrm)other_gen;
		tmp = f_choiceTyp.compareTo(other.f_choiceTyp);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		if (f_trmInfo != other.f_trmInfo)
			return (f_trmInfo > other.f_trmInfo) ? 1 : -1;
		return 0;
	}
}
