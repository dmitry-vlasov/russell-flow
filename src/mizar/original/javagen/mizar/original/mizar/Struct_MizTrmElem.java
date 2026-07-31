// HASH COLLISIONS: YES
// timestamp: 1785508391388

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizTrmElem extends Struct implements Field_trm<Struct>, Field_next<Struct> {
	public Struct f_trm;
	public Struct f_next;

	public Struct_MizTrmElem() {}
	public Struct_MizTrmElem(Struct a_trm, Struct a_next) {
		f_trm = a_trm;
		f_next = a_next;
	}
	public Struct get_trm() { return f_trm; }
	public void set_trm(Struct value) { f_trm = value; }
	public Struct get_next() { return f_next; }
	public void set_next(Struct value) { f_next = value; }

	public int getTypeId() { return 63; }
	public String getTypeName() { return "MizTrmElem"; }

	private static final String[] field_names = new String[] {
		"trm", "next"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_trm, f_next
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizTrmElem");
		f_trm = (Struct)values[0];
		f_next = (Struct)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 63) return 63-tmp;
		Struct_MizTrmElem other = (Struct_MizTrmElem)other_gen;
		tmp = f_trm.compareTo(other.f_trm);
		if (tmp != 0) return tmp;
		tmp = f_next.compareTo(other.f_next);
		return tmp;
	}
}
