// HASH COLLISIONS: YES
// timestamp: 1785524849111

package mizar.original.mizar;

import com.area9innovation.flow.*;

public class Struct_MizXmlParser extends Struct implements Field_lineIdx<Integer>, Field_nLine<String>, Field_nCurCol<Integer>, Field_nCurTokenKind<Integer>, Field_nSpelling<String>, Field_nPos<Struct_MizPos>, Field_nElName<String>, Field_nState<Integer> {
	public Object[] f_lines;
	public int f_lineIdx;
	public String f_nLine;
	public int f_nCurCol;
	public int f_nCurTokenKind;
	public String f_nSpelling;
	public Struct_MizPos f_nPos;
	public String f_nElName;
	public int f_nState;
	public Struct_Vector f_nAttrVals;
	public Func1<Object,String> f_onOpenTag;

	public Struct_MizXmlParser() {}
	public Struct_MizXmlParser(Object[] a_lines, int a_lineIdx, String a_nLine, int a_nCurCol, int a_nCurTokenKind, String a_nSpelling, Struct_MizPos a_nPos, String a_nElName, int a_nState, Struct_Vector a_nAttrVals, Func1<Object,String> a_onOpenTag) {
		f_lines = a_lines;
		f_lineIdx = a_lineIdx;
		f_nLine = a_nLine;
		f_nCurCol = a_nCurCol;
		f_nCurTokenKind = a_nCurTokenKind;
		f_nSpelling = a_nSpelling;
		f_nPos = a_nPos;
		f_nElName = a_nElName;
		f_nState = a_nState;
		f_nAttrVals = a_nAttrVals;
		f_onOpenTag = a_onOpenTag;
	}
	public Integer get_lineIdx() { return f_lineIdx; }
	public void set_lineIdx(Integer value) { f_lineIdx = value; }
	public String get_nLine() { return f_nLine; }
	public void set_nLine(String value) { f_nLine = value; }
	public Integer get_nCurCol() { return f_nCurCol; }
	public void set_nCurCol(Integer value) { f_nCurCol = value; }
	public Integer get_nCurTokenKind() { return f_nCurTokenKind; }
	public void set_nCurTokenKind(Integer value) { f_nCurTokenKind = value; }
	public String get_nSpelling() { return f_nSpelling; }
	public void set_nSpelling(String value) { f_nSpelling = value; }
	public Struct_MizPos get_nPos() { return f_nPos; }
	public void set_nPos(Struct_MizPos value) { f_nPos = value; }
	public String get_nElName() { return f_nElName; }
	public void set_nElName(String value) { f_nElName = value; }
	public Integer get_nState() { return f_nState; }
	public void set_nState(Integer value) { f_nState = value; }

	public int getTypeId() { return 81; }
	public String getTypeName() { return "MizXmlParser"; }

	private static final String[] field_names = new String[] {
		"lines", "lineIdx", "nLine", "nCurCol", "nCurTokenKind", "nSpelling", "nPos", "nElName", "nState", "nAttrVals", "onOpenTag"
	};
	private static final RuntimeType[] field_types = new RuntimeType[] {
		RuntimeType.ARRAY, RuntimeType.INT, RuntimeType.STRING, RuntimeType.INT, RuntimeType.INT, RuntimeType.STRING, RuntimeType.STRUCT, RuntimeType.STRING, RuntimeType.INT, RuntimeType.STRUCT, RuntimeType.UNKNOWN
	};
	public String[] getFieldNames() { return field_names; }
	public RuntimeType[] getFieldTypes() { return field_types; }

	public Object[] getFields() {
		return new Object[] {
			f_lines, f_lineIdx, f_nLine, f_nCurCol, f_nCurTokenKind, f_nSpelling, f_nPos, f_nElName, f_nState, f_nAttrVals, f_onOpenTag
		};
	}
	@SuppressWarnings("unchecked")
	public void setFields(Object[] values) {
		if (values.length != 11)
			throw new IndexOutOfBoundsException("Invalid field count in MizXmlParser");
		f_lines = (Object[])values[0];
		f_lineIdx = (Integer)values[1];
		f_nLine = (String)values[2];
		f_nCurCol = (Integer)values[3];
		f_nCurTokenKind = (Integer)values[4];
		f_nSpelling = (String)values[5];
		f_nPos = (Struct_MizPos)values[6];
		f_nElName = (String)values[7];
		f_nState = (Integer)values[8];
		f_nAttrVals = (Struct_Vector)values[9];
		f_onOpenTag = (Func1<Object,String>)values[10];
	}

	public int compareTo(Struct other_gen) {
		if (other_gen == this) return 0;
		int tmp = other_gen.getTypeId();
		if (tmp != 81) return 81-tmp;
		Struct_MizXmlParser other = (Struct_MizXmlParser)other_gen;
		tmp = FlowRuntime.compareByValue(f_lines, other.f_lines);
		if (tmp != 0) return tmp;
		if (f_lineIdx != other.f_lineIdx)
			return (f_lineIdx > other.f_lineIdx) ? 1 : -1;
		tmp = f_nLine.compareTo(other.f_nLine);
		if (tmp != 0) return tmp;
		if (f_nCurCol != other.f_nCurCol)
			return (f_nCurCol > other.f_nCurCol) ? 1 : -1;
		if (f_nCurTokenKind != other.f_nCurTokenKind)
			return (f_nCurTokenKind > other.f_nCurTokenKind) ? 1 : -1;
		tmp = f_nSpelling.compareTo(other.f_nSpelling);
		if (tmp != 0) return tmp;
		tmp = f_nPos.compareTo(other.f_nPos);
		if (tmp != 0) return tmp;
		tmp = f_nElName.compareTo(other.f_nElName);
		if (tmp != 0) return tmp;
		if (f_nState != other.f_nState)
			return (f_nState > other.f_nState) ? 1 : -1;
		tmp = f_nAttrVals.compareTo(other.f_nAttrVals);
		if (tmp != 0) return tmp;
		tmp = FlowRuntime.compareByValue(f_onOpenTag, other.f_onOpenTag);
		return tmp;
	}
}
