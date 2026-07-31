// HASH COLLISIONS: YES
// timestamp: 1785524849110

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizTrmSRec extends Struct implements Field_numDetermined<Boolean>, Field_numValue<Struct_MizComplex>, Field_eqClass<Struct>, Field_superCluster<Struct_MizAttrColl> {
	public Struct f_term;
	public boolean f_numDetermined;
	public Struct_MizComplex f_numValue;
	public Struct f_eqClass;
	public Struct_Vector f_xTypClass;
	public Struct_MizAttrColl f_superCluster;
	public Struct_Vector f_polynomialValues;

	public Struct_MizTrmSRec() {}
	public Struct_MizTrmSRec(Struct a_term, boolean a_numDetermined, Struct_MizComplex a_numValue, Struct a_eqClass, Struct_Vector a_xTypClass, Struct_MizAttrColl a_superCluster, Struct_Vector a_polynomialValues) {
		f_term = a_term;
		f_numDetermined = a_numDetermined;
		f_numValue = a_numValue;
		f_eqClass = a_eqClass;
		f_xTypClass = a_xTypClass;
		f_superCluster = a_superCluster;
		f_polynomialValues = a_polynomialValues;
	}
	public Boolean get_numDetermined() { return f_numDetermined; }
	public void set_numDetermined(Boolean value) { f_numDetermined = value; }
	public Struct_MizComplex get_numValue() { return f_numValue; }
	public void set_numValue(Struct_MizComplex value) { f_numValue = value; }
	public Struct get_eqClass() { return f_eqClass; }
	public void set_eqClass(Struct value) { f_eqClass = value; }
	public Struct_MizAttrColl get_superCluster() { return f_superCluster; }
	public void set_superCluster(Struct_MizAttrColl value) { f_superCluster = value; }

	public int getTypeId() { return 72; }
	public String getTypeName() { return "MizTrmSRec"; }

	private static final String[] field_names = new String[] {
		"term", "numDetermined", "numValue", "eqClass", "xTypClass", "superCluster", "polynomialValues"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.BOOL, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_term, f_numDetermined, f_numValue, f_eqClass, f_xTypClass, f_superCluster, f_polynomialValues
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 7)
			throw new IndexOutOfBoundsException("Invalid field count in MizTrmSRec");
		f_term = (Struct)values[0];
		f_numDetermined = (Boolean)values[1];
		f_numValue = (Struct_MizComplex)values[2];
		f_eqClass = (Struct)values[3];
		f_xTypClass = (Struct_Vector)values[4];
		f_superCluster = (Struct_MizAttrColl)values[5];
		f_polynomialValues = (Struct_Vector)values[6];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 72) return 72-tmp;
		Struct_MizTrmSRec other = (Struct_MizTrmSRec)other_gen;
		tmp = f_term.compareTo(other.f_term);
		if (tmp != 0) return tmp;
		if (f_numDetermined != other.f_numDetermined)
			return f_numDetermined ? 1 : -1;
		tmp = f_numValue.compareTo(other.f_numValue);
		if (tmp != 0) return tmp;
		tmp = f_eqClass.compareTo(other.f_eqClass);
		if (tmp != 0) return tmp;
		tmp = f_xTypClass.compareTo(other.f_xTypClass);
		if (tmp != 0) return tmp;
		tmp = f_superCluster.compareTo(other.f_superCluster);
		if (tmp != 0) return tmp;
		tmp = f_polynomialValues.compareTo(other.f_polynomialValues);
		return tmp;
	}
}
