// HASH COLLISIONS: YES
// timestamp: 1785508391384

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizConstr extends Struct implements Field_whichConstrNr<Integer>, Field_superfluous<Integer>, Field_properties<Integer>, Field_firstArg<Integer>, Field_secondArg<Integer>, Field_typ<Struct>, Field_structModeAggrNr<Integer>, Field_aggregBase<Integer> {
	public int f_kind;
	public String f_article;
	public int f_absNr;
	public Struct_Vector f_primaries;
	public int f_whichConstrNr;
	public int f_superfluous;
	public int f_properties;
	public int f_firstArg;
	public int f_secondArg;
	public Struct f_typ;
	public int f_structModeAggrNr;
	public int f_aggregBase;
	public Struct_MizNatFunc f_fields;
	public Struct_Vector f_prefixes;

	public Struct_MizConstr() {}
	public Struct_MizConstr(int a_kind, String a_article, int a_absNr, Struct_Vector a_primaries, int a_whichConstrNr, int a_superfluous, int a_properties, int a_firstArg, int a_secondArg, Struct a_typ, int a_structModeAggrNr, int a_aggregBase, Struct_MizNatFunc a_fields, Struct_Vector a_prefixes) {
		f_kind = a_kind;
		f_article = a_article;
		f_absNr = a_absNr;
		f_primaries = a_primaries;
		f_whichConstrNr = a_whichConstrNr;
		f_superfluous = a_superfluous;
		f_properties = a_properties;
		f_firstArg = a_firstArg;
		f_secondArg = a_secondArg;
		f_typ = a_typ;
		f_structModeAggrNr = a_structModeAggrNr;
		f_aggregBase = a_aggregBase;
		f_fields = a_fields;
		f_prefixes = a_prefixes;
	}
	public Integer get_whichConstrNr() { return f_whichConstrNr; }
	public void set_whichConstrNr(Integer value) { f_whichConstrNr = value; }
	public Integer get_superfluous() { return f_superfluous; }
	public void set_superfluous(Integer value) { f_superfluous = value; }
	public Integer get_properties() { return f_properties; }
	public void set_properties(Integer value) { f_properties = value; }
	public Integer get_firstArg() { return f_firstArg; }
	public void set_firstArg(Integer value) { f_firstArg = value; }
	public Integer get_secondArg() { return f_secondArg; }
	public void set_secondArg(Integer value) { f_secondArg = value; }
	public Struct get_typ() { return f_typ; }
	public void set_typ(Struct value) { f_typ = value; }
	public Integer get_structModeAggrNr() { return f_structModeAggrNr; }
	public void set_structModeAggrNr(Integer value) { f_structModeAggrNr = value; }
	public Integer get_aggregBase() { return f_aggregBase; }
	public void set_aggregBase(Integer value) { f_aggregBase = value; }

	public int getTypeId() { return 28; }
	public String getTypeName() { return "MizConstr"; }

	private static final String[] field_names = new String[] {
		"kind", "article", "absNr", "primaries", "whichConstrNr", "superfluous", "properties", "firstArg", "secondArg", "typ", "structModeAggrNr", "aggregBase", "fields", "prefixes"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.INT, RuntimeType.STRING, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.INT, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_kind, f_article, f_absNr, f_primaries, f_whichConstrNr, f_superfluous, f_properties, f_firstArg, f_secondArg, f_typ, f_structModeAggrNr, f_aggregBase, f_fields, f_prefixes
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 14)
			throw new IndexOutOfBoundsException("Invalid field count in MizConstr");
		f_kind = (Integer)values[0];
		f_article = (String)values[1];
		f_absNr = (Integer)values[2];
		f_primaries = (Struct_Vector)values[3];
		f_whichConstrNr = (Integer)values[4];
		f_superfluous = (Integer)values[5];
		f_properties = (Integer)values[6];
		f_firstArg = (Integer)values[7];
		f_secondArg = (Integer)values[8];
		f_typ = (Struct)values[9];
		f_structModeAggrNr = (Integer)values[10];
		f_aggregBase = (Integer)values[11];
		f_fields = (Struct_MizNatFunc)values[12];
		f_prefixes = (Struct_Vector)values[13];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 28) return 28-tmp;
		Struct_MizConstr other = (Struct_MizConstr)other_gen;
		if (f_kind != other.f_kind)
			return (f_kind > other.f_kind) ? 1 : -1;
		tmp = f_article.compareTo(other.f_article);
		if (tmp != 0) return tmp;
		if (f_absNr != other.f_absNr)
			return (f_absNr > other.f_absNr) ? 1 : -1;
		tmp = f_primaries.compareTo(other.f_primaries);
		if (tmp != 0) return tmp;
		if (f_whichConstrNr != other.f_whichConstrNr)
			return (f_whichConstrNr > other.f_whichConstrNr) ? 1 : -1;
		if (f_superfluous != other.f_superfluous)
			return (f_superfluous > other.f_superfluous) ? 1 : -1;
		if (f_properties != other.f_properties)
			return (f_properties > other.f_properties) ? 1 : -1;
		if (f_firstArg != other.f_firstArg)
			return (f_firstArg > other.f_firstArg) ? 1 : -1;
		if (f_secondArg != other.f_secondArg)
			return (f_secondArg > other.f_secondArg) ? 1 : -1;
		tmp = f_typ.compareTo(other.f_typ);
		if (tmp != 0) return tmp;
		if (f_structModeAggrNr != other.f_structModeAggrNr)
			return (f_structModeAggrNr > other.f_structModeAggrNr) ? 1 : -1;
		if (f_aggregBase != other.f_aggregBase)
			return (f_aggregBase > other.f_aggregBase) ? 1 : -1;
		tmp = f_fields.compareTo(other.f_fields);
		if (tmp != 0) return tmp;
		tmp = f_prefixes.compareTo(other.f_prefixes);
		return tmp;
	}
}
