// HASH COLLISIONS: YES
// timestamp: 1785512695552

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizPrep extends Struct implements Field_prev<Struct>, Field_lastBlockThesis<Struct>, Field_theorems<Struct>, Field_ownArticleNr<Integer>, Field_ownTheoremNr<Integer>, Field_ownDefNr<Integer> {
	public Struct_MizChecker f_chk;
	public Struct_MizVrfReader f_r;
	public Struct_Vector f_labeled;
	public Struct_Vector f_permLabeled;
	public Struct f_prev;
	public Struct f_lastBlockThesis;
	public Struct f_theorems;
	public Struct_MizDefinientia f_defs;
	public Struct_MizSchemes f_schemes;
	public int f_ownArticleNr;
	public int f_ownTheoremNr;
	public int f_ownDefNr;
	public Struct_MizPrepStats f_stats;

	public Struct_MizPrep() {}
	public Struct_MizPrep(Struct_MizChecker a_chk, Struct_MizVrfReader a_r, Struct_Vector a_labeled, Struct_Vector a_permLabeled, Struct a_prev, Struct a_lastBlockThesis, Struct a_theorems, Struct_MizDefinientia a_defs, Struct_MizSchemes a_schemes, int a_ownArticleNr, int a_ownTheoremNr, int a_ownDefNr, Struct_MizPrepStats a_stats) {
		f_chk = a_chk;
		f_r = a_r;
		f_labeled = a_labeled;
		f_permLabeled = a_permLabeled;
		f_prev = a_prev;
		f_lastBlockThesis = a_lastBlockThesis;
		f_theorems = a_theorems;
		f_defs = a_defs;
		f_schemes = a_schemes;
		f_ownArticleNr = a_ownArticleNr;
		f_ownTheoremNr = a_ownTheoremNr;
		f_ownDefNr = a_ownDefNr;
		f_stats = a_stats;
	}
	public Struct get_prev() { return f_prev; }
	public void set_prev(Struct value) { f_prev = value; }
	public Struct get_lastBlockThesis() { return f_lastBlockThesis; }
	public void set_lastBlockThesis(Struct value) { f_lastBlockThesis = value; }
	public Struct get_theorems() { return f_theorems; }
	public void set_theorems(Struct value) { f_theorems = value; }
	public Integer get_ownArticleNr() { return f_ownArticleNr; }
	public void set_ownArticleNr(Integer value) { f_ownArticleNr = value; }
	public Integer get_ownTheoremNr() { return f_ownTheoremNr; }
	public void set_ownTheoremNr(Integer value) { f_ownTheoremNr = value; }
	public Integer get_ownDefNr() { return f_ownDefNr; }
	public void set_ownDefNr(Integer value) { f_ownDefNr = value; }

	public int getTypeId() { return 57; }
	public String getTypeName() { return "MizPrep"; }

	private static final String[] field_names = new String[] {
		"chk", "r", "labeled", "permLabeled", "prev", "lastBlockThesis", "theorems", "defs", "schemes", "ownArticleNr", "ownTheoremNr", "ownDefNr", "stats"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_chk, f_r, f_labeled, f_permLabeled, f_prev, f_lastBlockThesis, f_theorems, f_defs, f_schemes, f_ownArticleNr, f_ownTheoremNr, f_ownDefNr, f_stats
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 13)
			throw new IndexOutOfBoundsException("Invalid field count in MizPrep");
		f_chk = (Struct_MizChecker)values[0];
		f_r = (Struct_MizVrfReader)values[1];
		f_labeled = (Struct_Vector)values[2];
		f_permLabeled = (Struct_Vector)values[3];
		f_prev = (Struct)values[4];
		f_lastBlockThesis = (Struct)values[5];
		f_theorems = (Struct)values[6];
		f_defs = (Struct_MizDefinientia)values[7];
		f_schemes = (Struct_MizSchemes)values[8];
		f_ownArticleNr = (Integer)values[9];
		f_ownTheoremNr = (Integer)values[10];
		f_ownDefNr = (Integer)values[11];
		f_stats = (Struct_MizPrepStats)values[12];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 57) return 57-tmp;
		Struct_MizPrep other = (Struct_MizPrep)other_gen;
		tmp = f_chk.compareTo(other.f_chk);
		if (tmp != 0) return tmp;
		tmp = f_r.compareTo(other.f_r);
		if (tmp != 0) return tmp;
		tmp = f_labeled.compareTo(other.f_labeled);
		if (tmp != 0) return tmp;
		tmp = f_permLabeled.compareTo(other.f_permLabeled);
		if (tmp != 0) return tmp;
		tmp = f_prev.compareTo(other.f_prev);
		if (tmp != 0) return tmp;
		tmp = f_lastBlockThesis.compareTo(other.f_lastBlockThesis);
		if (tmp != 0) return tmp;
		tmp = f_theorems.compareTo(other.f_theorems);
		if (tmp != 0) return tmp;
		tmp = f_defs.compareTo(other.f_defs);
		if (tmp != 0) return tmp;
		tmp = f_schemes.compareTo(other.f_schemes);
		if (tmp != 0) return tmp;
		if (f_ownArticleNr != other.f_ownArticleNr)
			return (f_ownArticleNr > other.f_ownArticleNr) ? 1 : -1;
		if (f_ownTheoremNr != other.f_ownTheoremNr)
			return (f_ownTheoremNr > other.f_ownTheoremNr) ? 1 : -1;
		if (f_ownDefNr != other.f_ownDefNr)
			return (f_ownDefNr > other.f_ownDefNr) ? 1 : -1;
		tmp = f_stats.compareTo(other.f_stats);
		return tmp;
	}
}
