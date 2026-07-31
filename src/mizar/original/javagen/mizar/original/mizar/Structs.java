// HASH COLLISIONS: YES
// timestamp: 1785508391393

package mizar.original.mizar;

import com.area9innovation.flow.*;

import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public final class Structs {

	// Structs array
	static final private Struct[] struct_list = {
		new Struct_Cons(),
		SingletonStructs.str_EmptyList,
		SingletonStructs.str_EmptyPopResult,
		SingletonStructs.str_IllegalStruct,
		new Struct_JsonArray(),
		new Struct_JsonBool(),
		new Struct_JsonDouble(),
		SingletonStructs.str_JsonFieldIgnoreCase,
		new Struct_JsonFieldTreatAsPath(),
		SingletonStructs.str_JsonNull,
		new Struct_JsonObject(),
		new Struct_JsonParseResult(),
		new Struct_JsonString(),
		new Struct_KeyValue(),
		new Struct_MizAttr(),
		new Struct_MizAttrColl(),
		new Struct_MizBaseTrm(),
		new Struct_MizBinFrm(),
		new Struct_MizBuiltIn(),
		new Struct_MizCCluster(),
		new Struct_MizChecker(),
		new Struct_MizChoiceTrm(),
		new Struct_MizClusterRec(),
		new Struct_MizClusters(),
		new Struct_MizComplPair(),
		new Struct_MizComplex(),
		new Struct_MizConjFrm(),
		new Struct_MizConstDef(),
		new Struct_MizConstr(),
		new Struct_MizCorrelEnv(),
		new Struct_MizDef(),
		new Struct_MizDefFrm(),
		new Struct_MizDefTrm(),
		new Struct_MizDefiniens(),
		new Struct_MizDefinientia(),
		new Struct_MizEqualizer(),
		new Struct_MizEqualsDef(),
		new Struct_MizErr(),
		new Struct_MizErrHan(),
		new Struct_MizFCluster(),
		new Struct_MizFixedVar(),
		new Struct_MizFlexFrm(),
		new Struct_MizFraenkelTrm(),
		new Struct_MizFuncDef(),
		new Struct_MizFuncTrm(),
		new Struct_MizIdentify(),
		new Struct_MizIntPair(),
		new Struct_MizIntSeq(),
		new Struct_MizLocPredFrm(),
		new Struct_MizNatFunc(),
		new Struct_MizNegFrm(),
		new Struct_MizPartDef(),
		new Struct_MizPos(),
		new Struct_MizPre(),
		new Struct_MizPreInst(),
		new Struct_MizPredFrm(),
		new Struct_MizPrep(),
		new Struct_MizPrepStats(),
		new Struct_MizPrivFuncTrm(),
		new Struct_MizQuaTrm(),
		new Struct_MizQualFrm(),
		new Struct_MizRCluster(),
		new Struct_MizReduction(),
		new Struct_MizTrmElem(),
		SingletonStructs.str_MizTrmNil,
		new Struct_MizTrmSRec(),
		new Struct_MizTyp(),
		new Struct_MizUnifier(),
		new Struct_MizUniqFrm(),
		new Struct_MizUnivFrm(),
		new Struct_MizVarTrm(),
		new Struct_MizVrfReader(),
		new Struct_MizWithin(),
		new Struct_MizXmlAttr(),
		new Struct_MizXmlParser(),
		SingletonStructs.str_None,
		new Struct_OWASP(),
		new Struct_Pair(),
		new Struct_ParsingAcc(),
		new Struct_PopResult(),
		new Struct_PopSetResult(),
		new Struct_Quadruple(),
		new Struct_Set(),
		new Struct_Some(),
		SingletonStructs.str_TreeEmpty,
		new Struct_TreeNode(),
		new Struct_Triple(),
		new Struct_Vector(),
		new Struct_VerboseOutput()
	};
	public static final void init() {
		FlowRuntime.struct_prototypes = struct_list;
		for (int i = 0; i < FlowRuntime.struct_prototypes.length; i++) {
			FlowRuntime.struct_ids.put(FlowRuntime.struct_prototypes[i].getTypeName(), i);
		}
	}
}
