// HASH COLLISIONS: YES
// timestamp: 1785524849103

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizClusterRec extends Struct implements Field_lower<Struct_MizAttrColl>, Field_upper<Struct_MizAttrColl> {
	public Struct_MizAttrColl f_lower;
	public Struct_MizAttrColl f_upper;

	public Struct_MizClusterRec() {}
	public Struct_MizClusterRec(Struct_MizAttrColl a_lower, Struct_MizAttrColl a_upper) {
		f_lower = a_lower;
		f_upper = a_upper;
	}
	public Struct_MizAttrColl get_lower() { return f_lower; }
	public void set_lower(Struct_MizAttrColl value) { f_lower = value; }
	public Struct_MizAttrColl get_upper() { return f_upper; }
	public void set_upper(Struct_MizAttrColl value) { f_upper = value; }

	public int getTypeId() { return 22; }
	public String getTypeName() { return "MizClusterRec"; }

	private static final String[] field_names = new String[] {
		"lower", "upper"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_lower, f_upper
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 2)
			throw new IndexOutOfBoundsException("Invalid field count in MizClusterRec");
		f_lower = (Struct_MizAttrColl)values[0];
		f_upper = (Struct_MizAttrColl)values[1];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 22) return 22-tmp;
		Struct_MizClusterRec other = (Struct_MizClusterRec)other_gen;
		tmp = f_lower.compareTo(other.f_lower);
		if (tmp != 0) return tmp;
		tmp = f_upper.compareTo(other.f_upper);
		return tmp;
	}
}
