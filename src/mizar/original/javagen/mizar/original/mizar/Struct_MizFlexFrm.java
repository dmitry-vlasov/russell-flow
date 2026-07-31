// HASH COLLISIONS: YES
// timestamp: 1785524849105

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizFlexFrm extends Struct implements Field_sort<Integer>, Field_leftTrm<Struct>, Field_rightTrm<Struct> {
	public int f_sort;
	public Struct f_leftOrig;
	public Struct f_rightOrig;
	public Struct f_leftTrm;
	public Struct f_rightTrm;
	public Struct f_expansion;
	public int f_pattNr;

	public Struct_MizFlexFrm() {}
	public Struct_MizFlexFrm(int a_sort, Struct a_leftOrig, Struct a_rightOrig, Struct a_leftTrm, Struct a_rightTrm, Struct a_expansion, int a_pattNr) {
		f_sort = a_sort;
		f_leftOrig = a_leftOrig;
		f_rightOrig = a_rightOrig;
		f_leftTrm = a_leftTrm;
		f_rightTrm = a_rightTrm;
		f_expansion = a_expansion;
		f_pattNr = a_pattNr;
	}
	public Integer get_sort() { return f_sort; }
	public void set_sort(Integer value) { f_sort = value; }
	public Struct get_leftTrm() { return f_leftTrm; }
	public void set_leftTrm(Struct value) { f_leftTrm = value; }
	public Struct get_rightTrm() { return f_rightTrm; }
	public void set_rightTrm(Struct value) { f_rightTrm = value; }

	public int getTypeId() { return 41; }
	public String getTypeName() { return "MizFlexFrm"; }

	private static final String[] field_names = new String[] {
		"sort", "leftOrig", "rightOrig", "leftTrm", "rightTrm", "expansion", "pattNr"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_sort, f_leftOrig, f_rightOrig, f_leftTrm, f_rightTrm, f_expansion, f_pattNr
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 7)
			throw new IndexOutOfBoundsException("Invalid field count in MizFlexFrm");
		f_sort = (Integer)values[0];
		f_leftOrig = (Struct)values[1];
		f_rightOrig = (Struct)values[2];
		f_leftTrm = (Struct)values[3];
		f_rightTrm = (Struct)values[4];
		f_expansion = (Struct)values[5];
		f_pattNr = (Integer)values[6];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 41) return 41-tmp;
		Struct_MizFlexFrm other = (Struct_MizFlexFrm)other_gen;
		if (f_sort != other.f_sort)
			return (f_sort > other.f_sort) ? 1 : -1;
		tmp = f_leftOrig.compareTo(other.f_leftOrig);
		if (tmp != 0) return tmp;
		tmp = f_rightOrig.compareTo(other.f_rightOrig);
		if (tmp != 0) return tmp;
		tmp = f_leftTrm.compareTo(other.f_leftTrm);
		if (tmp != 0) return tmp;
		tmp = f_rightTrm.compareTo(other.f_rightTrm);
		if (tmp != 0) return tmp;
		tmp = f_expansion.compareTo(other.f_expansion);
		if (tmp != 0) return tmp;
		if (f_pattNr != other.f_pattNr)
			return (f_pattNr > other.f_pattNr) ? 1 : -1;
		return 0;
	}
}
