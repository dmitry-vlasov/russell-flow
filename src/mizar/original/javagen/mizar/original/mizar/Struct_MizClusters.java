// HASH COLLISIONS: YES
// timestamp: 1785524849103

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizClusters extends Struct {
	public Struct_Vector f_registered;
	public Struct_Vector f_functor;
	public Struct_Vector f_conditional;
	public Struct_Vector f_condIndexPos;
	public Struct_Vector f_condIndexNeg;
	public Struct_MizIntSeq f_initClFire;

	public Struct_MizClusters() {}
	public Struct_MizClusters(Struct_Vector a_registered, Struct_Vector a_functor, Struct_Vector a_conditional, Struct_Vector a_condIndexPos, Struct_Vector a_condIndexNeg, Struct_MizIntSeq a_initClFire) {
		f_registered = a_registered;
		f_functor = a_functor;
		f_conditional = a_conditional;
		f_condIndexPos = a_condIndexPos;
		f_condIndexNeg = a_condIndexNeg;
		f_initClFire = a_initClFire;
	}

	public int getTypeId() { return 23; }
	public String getTypeName() { return "MizClusters"; }

	private static final String[] field_names = new String[] {
		"registered", "functor", "conditional", "condIndexPos", "condIndexNeg", "initClFire"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT, RuntimeType.STRUCT
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_registered, f_functor, f_conditional, f_condIndexPos, f_condIndexNeg, f_initClFire
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 6)
			throw new IndexOutOfBoundsException("Invalid field count in MizClusters");
		f_registered = (Struct_Vector)values[0];
		f_functor = (Struct_Vector)values[1];
		f_conditional = (Struct_Vector)values[2];
		f_condIndexPos = (Struct_Vector)values[3];
		f_condIndexNeg = (Struct_Vector)values[4];
		f_initClFire = (Struct_MizIntSeq)values[5];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 23) return 23-tmp;
		Struct_MizClusters other = (Struct_MizClusters)other_gen;
		tmp = f_registered.compareTo(other.f_registered);
		if (tmp != 0) return tmp;
		tmp = f_functor.compareTo(other.f_functor);
		if (tmp != 0) return tmp;
		tmp = f_conditional.compareTo(other.f_conditional);
		if (tmp != 0) return tmp;
		tmp = f_condIndexPos.compareTo(other.f_condIndexPos);
		if (tmp != 0) return tmp;
		tmp = f_condIndexNeg.compareTo(other.f_condIndexNeg);
		if (tmp != 0) return tmp;
		tmp = f_initClFire.compareTo(other.f_initClFire);
		return tmp;
	}
}
