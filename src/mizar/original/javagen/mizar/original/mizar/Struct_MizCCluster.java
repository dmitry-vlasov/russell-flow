// HASH COLLISIONS: YES
// timestamp: 1785508391383

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizCCluster extends Struct {
	public String f_article;
	public int f_absNr;
	public Struct_Vector f_primaries;
	public Struct_MizAttrColl f_antecedent;
	public Struct_MizClusterRec f_consequent;
	public Struct_MizTyp f_clusterType;

	public Struct_MizCCluster() {}
	public Struct_MizCCluster(String a_article, int a_absNr, Struct_Vector a_primaries, Struct_MizAttrColl a_antecedent, Struct_MizClusterRec a_consequent, Struct_MizTyp a_clusterType) {
		f_article = a_article;
		f_absNr = a_absNr;
		f_primaries = a_primaries;
		f_antecedent = a_antecedent;
		f_consequent = a_consequent;
		f_clusterType = a_clusterType;
	}

	public int getTypeId() { return 19; }
	public String getTypeName() { return "MizCCluster"; }

	private static final String[] field_names = new String[] {
		"article", "absNr", "primaries", "antecedent", "consequent", "clusterType"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRING, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_article, f_absNr, f_primaries, f_antecedent, f_consequent, f_clusterType
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 6)
			throw new IndexOutOfBoundsException("Invalid field count in MizCCluster");
		f_article = (String)values[0];
		f_absNr = (Integer)values[1];
		f_primaries = (Struct_Vector)values[2];
		f_antecedent = (Struct_MizAttrColl)values[3];
		f_consequent = (Struct_MizClusterRec)values[4];
		f_clusterType = (Struct_MizTyp)values[5];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 19) return 19-tmp;
		Struct_MizCCluster other = (Struct_MizCCluster)other_gen;
		tmp = f_article.compareTo(other.f_article);
		if (tmp != 0) return tmp;
		if (f_absNr != other.f_absNr)
			return (f_absNr > other.f_absNr) ? 1 : -1;
		tmp = f_primaries.compareTo(other.f_primaries);
		if (tmp != 0) return tmp;
		tmp = f_antecedent.compareTo(other.f_antecedent);
		if (tmp != 0) return tmp;
		tmp = f_consequent.compareTo(other.f_consequent);
		if (tmp != 0) return tmp;
		tmp = f_clusterType.compareTo(other.f_clusterType);
		return tmp;
	}
}
