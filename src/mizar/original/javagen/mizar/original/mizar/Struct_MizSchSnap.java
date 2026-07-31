// HASH COLLISIONS: YES
// timestamp: 1785512695554

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizSchSnap extends Struct {
	public Object[] f_consts;
	public Struct f_funcs;
	public Struct f_preds;

	public Struct_MizSchSnap() {}
	public Struct_MizSchSnap(Object[] a_consts, Struct a_funcs, Struct a_preds) {
		f_consts = a_consts;
		f_funcs = a_funcs;
		f_preds = a_preds;
	}

	public int getTypeId() { return 64; }
	public String getTypeName() { return "MizSchSnap"; }

	private static final String[] field_names = new String[] {
		"consts", "funcs", "preds"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.ARRAY, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_consts, f_funcs, f_preds
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 3)
			throw new IndexOutOfBoundsException("Invalid field count in MizSchSnap");
		f_consts = (Object[])values[0];
		f_funcs = (Struct)values[1];
		f_preds = (Struct)values[2];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 64) return 64-tmp;
		Struct_MizSchSnap other = (Struct_MizSchSnap)other_gen;
		tmp = FlowRuntime.compareByValue(f_consts, other.f_consts);
		if (tmp != 0) return tmp;
		tmp = f_funcs.compareTo(other.f_funcs);
		if (tmp != 0) return tmp;
		tmp = f_preds.compareTo(other.f_preds);
		return tmp;
	}
}
