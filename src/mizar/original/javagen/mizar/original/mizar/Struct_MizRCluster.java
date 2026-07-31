// HASH COLLISIONS: YES
// timestamp: 1785524849108

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizRCluster extends Struct {
	public String f_article;
	public int f_absNr;
	public Struct_Vector f_primaries;
	public Struct_MizClusterRec f_consequent;
	public Struct_MizTyp f_clusterType;

	public Struct_MizRCluster() {}
	public Struct_MizRCluster(String a_article, int a_absNr, Struct_Vector a_primaries, Struct_MizClusterRec a_consequent, Struct_MizTyp a_clusterType) {
		f_article = a_article;
		f_absNr = a_absNr;
		f_primaries = a_primaries;
		f_consequent = a_consequent;
		f_clusterType = a_clusterType;
	}

	public int getTypeId() { return 64; }
	public String getTypeName() { return "MizRCluster"; }

	private static final String[] field_names = new String[] {
		"article", "absNr", "primaries", "consequent", "clusterType"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRING, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_article, f_absNr, f_primaries, f_consequent, f_clusterType
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 5)
			throw new IndexOutOfBoundsException("Invalid field count in MizRCluster");
		f_article = (String)values[0];
		f_absNr = (Integer)values[1];
		f_primaries = (Struct_Vector)values[2];
		f_consequent = (Struct_MizClusterRec)values[3];
		f_clusterType = (Struct_MizTyp)values[4];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 64) return 64-tmp;
		Struct_MizRCluster other = (Struct_MizRCluster)other_gen;
		tmp = f_article.compareTo(other.f_article);
		if (tmp != 0) return tmp;
		if (f_absNr != other.f_absNr)
			return (f_absNr > other.f_absNr) ? 1 : -1;
		tmp = f_primaries.compareTo(other.f_primaries);
		if (tmp != 0) return tmp;
		tmp = f_consequent.compareTo(other.f_consequent);
		if (tmp != 0) return tmp;
		tmp = f_clusterType.compareTo(other.f_clusterType);
		return tmp;
	}
}
