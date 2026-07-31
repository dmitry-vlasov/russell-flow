// HASH COLLISIONS: YES
// timestamp: 1785524849108

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizPrepStats extends Struct implements Field_checked<Integer>, Field_accepted<Integer>, Field_failed<Integer>, Field_skippedBlocks<Integer> {
	public int f_checked;
	public int f_accepted;
	public int f_failed;
	public int f_skippedFrom;
	public int f_skippedBlocks;
	public Struct_Vector f_failures;

	public Struct_MizPrepStats() {}
	public Struct_MizPrepStats(int a_checked, int a_accepted, int a_failed, int a_skippedFrom, int a_skippedBlocks, Struct_Vector a_failures) {
		f_checked = a_checked;
		f_accepted = a_accepted;
		f_failed = a_failed;
		f_skippedFrom = a_skippedFrom;
		f_skippedBlocks = a_skippedBlocks;
		f_failures = a_failures;
	}
	public Integer get_checked() { return f_checked; }
	public void set_checked(Integer value) { f_checked = value; }
	public Integer get_accepted() { return f_accepted; }
	public void set_accepted(Integer value) { f_accepted = value; }
	public Integer get_failed() { return f_failed; }
	public void set_failed(Integer value) { f_failed = value; }
	public Integer get_skippedBlocks() { return f_skippedBlocks; }
	public void set_skippedBlocks(Integer value) { f_skippedBlocks = value; }

	public int getTypeId() { return 60; }
	public String getTypeName() { return "MizPrepStats"; }

	private static final String[] field_names = new String[] {
		"checked", "accepted", "failed", "skippedFrom", "skippedBlocks", "failures"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_checked, f_accepted, f_failed, f_skippedFrom, f_skippedBlocks, f_failures
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 6)
			throw new IndexOutOfBoundsException("Invalid field count in MizPrepStats");
		f_checked = (Integer)values[0];
		f_accepted = (Integer)values[1];
		f_failed = (Integer)values[2];
		f_skippedFrom = (Integer)values[3];
		f_skippedBlocks = (Integer)values[4];
		f_failures = (Struct_Vector)values[5];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 60) return 60-tmp;
		Struct_MizPrepStats other = (Struct_MizPrepStats)other_gen;
		if (f_checked != other.f_checked)
			return (f_checked > other.f_checked) ? 1 : -1;
		if (f_accepted != other.f_accepted)
			return (f_accepted > other.f_accepted) ? 1 : -1;
		if (f_failed != other.f_failed)
			return (f_failed > other.f_failed) ? 1 : -1;
		if (f_skippedFrom != other.f_skippedFrom)
			return (f_skippedFrom > other.f_skippedFrom) ? 1 : -1;
		if (f_skippedBlocks != other.f_skippedBlocks)
			return (f_skippedBlocks > other.f_skippedBlocks) ? 1 : -1;
		tmp = f_failures.compareTo(other.f_failures);
		return tmp;
	}
}
