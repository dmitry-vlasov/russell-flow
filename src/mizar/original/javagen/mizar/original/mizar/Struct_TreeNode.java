// HASH COLLISIONS: YES
// timestamp: 1785508391391

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_TreeNode extends Struct {
	public Object f_key;
	public Object f_value;
	public Struct f_left;
	public Struct f_right;
	public int f_depth;

	public Struct_TreeNode() {}
	public Struct_TreeNode(Object a_key, Object a_value, Struct a_left, Struct a_right, int a_depth) {
		f_key = a_key;
		f_value = a_value;
		f_left = a_left;
		f_right = a_right;
		f_depth = a_depth;
	}

	public int getTypeId() { return 85; }
	public String getTypeName() { return "TreeNode"; }

	private static final String[] field_names = new String[] {
		"key", "value", "left", "right", "depth"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.UNKNOWN, RuntimeType.UNKNOWN, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_key, f_value, f_left, f_right, f_depth
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in TreeNode");
		f_key = (Object)values[0];
		f_value = (Object)values[1];
		f_left = (Struct)values[2];
		f_right = (Struct)values[3];
		f_depth = (Integer)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 85) return 85-tmp;
		Struct_TreeNode other = (Struct_TreeNode)other_gen;
		tmp = FlowRuntime.compareByValue(f_key, other.f_key);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_value, other.f_value);
		if (tmp != 0) return tmp;
		tmp = f_left.compareTo(other.f_left);
		if (tmp != 0) return tmp;
		tmp = f_right.compareTo(other.f_right);
		if (tmp != 0) return tmp;
		if (f_depth != other.f_depth)
			return (f_depth > other.f_depth) ? 1 : -1;
		return 0;
	}
}
