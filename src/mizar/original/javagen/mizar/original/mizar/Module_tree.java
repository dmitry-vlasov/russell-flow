// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_tree {
	public static final Struct f_lookupTree(Struct aset, Object akey) {
		TAIL_CALL: for(;;) {
		Struct l0__tmp = aset;
		switch (l0__tmp.getTypeId()) {
		case 92/*TreeNode*/: {
			final Struct_TreeNode l1__tmp = (Struct_TreeNode)l0__tmp;
			final Object l2_k = l1__tmp.f_key;
			final Object l3_v = l1__tmp.f_value;
			final Struct l4_l = l1__tmp.f_left;
			final Struct l5_r = l1__tmp.f_right;
			final int l6_depth = l1__tmp.f_depth;
			final Object l7_k = (l1__tmp).f_key;
			final Object l8_v = (l1__tmp).f_value;
			final Struct l9_l = (l1__tmp).f_left;
			final Struct l10_r = (l1__tmp).f_right;
			final int l11_depth = (l1__tmp).f_depth;
			final int l12_c = Native.genericCompare(akey, l7_k);
			if ((l12_c<0)) {
				{
					final Struct l13___tmp = l9_l;
					aset = l13___tmp;
					continue TAIL_CALL;
				}
			} else {
				if ((l12_c==0)) {
					return (new Struct_Some(l8_v));
				} else {
					{
						final Struct l14___tmp = l10_r;
						aset = l14___tmp;
						continue TAIL_CALL;
					}
				}
			}
		}
		case 91/*TreeEmpty*/: {
			return ((Struct)SingletonStructs.str_None);
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	}
	public static final Object f_lookupTreeDef(Struct ax, Object akey, Object adefaultValue) {
		return Module_maybe.f_either(Module_tree.f_lookupTree(ax, akey), adefaultValue);
	}
	public static final Struct f_makeTree() {
		return ((Struct)SingletonStructs.str_TreeEmpty);
	}
	public static final Struct_TreeNode f_mkTreeNode(Object ak, Object av, Struct aleft, Struct aright) {
		return (new Struct_TreeNode(ak, av, aleft, aright, (((int)Native.fast_max(Module_tree.f_treeDepth(aleft), Module_tree.f_treeDepth(aright)))+1)));
	}
	public static final Struct f_pairs2tree(Object[] apairs) {
		final Struct l2_$1 = Module_tree.f_makeTree();
		final Func2<Struct,Struct, Struct_Pair> l3_$0 = (Func2<Struct, Struct, Struct_Pair>)(Struct at, Struct_Pair ap) -> {
			return Module_tree.f_setTree(at, (ap).f_first, (ap).f_second);
		};
		return ((Struct)Native.fold(apairs, l2_$1, ((Func2<Object,Object, Object>)(Func2)l3_$0)));
	}
	public static final Struct f_rebalancedTree(Object ak, Object av, Struct aleft, Struct aright) {
		final int l0_leftDepth = Module_tree.f_treeDepth(aleft);
		final int l1_rightDepth = Module_tree.f_treeDepth(aright);
		final int l2_balance = (l0_leftDepth-l1_rightDepth);
		final Struct_TreeNode l3_composed = (new Struct_TreeNode(ak, av, aleft, aright, (((int)Native.fast_max(l0_leftDepth, l1_rightDepth))+1)));
		if ((((l2_balance==(-1))||(l2_balance==0))||(l2_balance==1))) {
			return l3_composed;
		} else {
			if ((l2_balance<0)) {
				Struct l4__tmp = aright;
				switch (l4__tmp.getTypeId()) {
				case 91/*TreeEmpty*/: {
					return l3_composed;
				}
				case 92/*TreeNode*/: {
					final Struct_TreeNode l5__tmp = (Struct_TreeNode)l4__tmp;
					final Object l6_rk = l5__tmp.f_key;
					final Object l7_rv = l5__tmp.f_value;
					final Struct l8_rl = l5__tmp.f_left;
					final Struct l9_rr = l5__tmp.f_right;
					final int l10_rdepth = l5__tmp.f_depth;
					final Object l11_rk = (l5__tmp).f_key;
					final Object l12_rv = (l5__tmp).f_value;
					final Struct l13_rl = (l5__tmp).f_left;
					final Struct l14_rr = (l5__tmp).f_right;
					final int l15_rdepth = (l5__tmp).f_depth;
					return Module_tree.f_treeLeftRotation(((Module_tree.f_treeDepth(l13_rl)<Module_tree.f_treeDepth(l14_rr))?l3_composed:Module_tree.f_mkTreeNode(ak, av, aleft, Module_tree.f_treeRightRotation(l5__tmp))));
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l4__tmp.getTypeName());
				}
			} else {
				Struct l16__tmp = aleft;
				switch (l16__tmp.getTypeId()) {
				case 91/*TreeEmpty*/: {
					return l3_composed;
				}
				case 92/*TreeNode*/: {
					final Struct_TreeNode l17__tmp = (Struct_TreeNode)l16__tmp;
					final Object l18_lk = l17__tmp.f_key;
					final Object l19_lv = l17__tmp.f_value;
					final Struct l20_ll = l17__tmp.f_left;
					final Struct l21_lr = l17__tmp.f_right;
					final int l22_ldepth = l17__tmp.f_depth;
					final Object l23_lk = (l17__tmp).f_key;
					final Object l24_lv = (l17__tmp).f_value;
					final Struct l25_ll = (l17__tmp).f_left;
					final Struct l26_lr = (l17__tmp).f_right;
					final int l27_ldepth = (l17__tmp).f_depth;
					return Module_tree.f_treeRightRotation(((Module_tree.f_treeDepth(l25_ll)<Module_tree.f_treeDepth(l26_lr))?Module_tree.f_mkTreeNode(ak, av, Module_tree.f_treeLeftRotation(l17__tmp), aright):l3_composed));
				}
				default:
					throw new RuntimeException("Unexpected struct in switch: "+l16__tmp.getTypeName());
				}
			}
		}
	}
	public static final Struct f_setTree(Struct aset, Object akey, Object avalue) {
		Struct l0__tmp = aset;
		switch (l0__tmp.getTypeId()) {
		case 92/*TreeNode*/: {
			final Struct_TreeNode l1__tmp = (Struct_TreeNode)l0__tmp;
			final Object l2_k = l1__tmp.f_key;
			final Object l3_v = l1__tmp.f_value;
			final Struct l4_left = l1__tmp.f_left;
			final Struct l5_right = l1__tmp.f_right;
			final int l6_depth = l1__tmp.f_depth;
			final Object l7_k = (l1__tmp).f_key;
			final Object l8_v = (l1__tmp).f_value;
			final Struct l9_left = (l1__tmp).f_left;
			final Struct l10_right = (l1__tmp).f_right;
			final int l11_depth = (l1__tmp).f_depth;
			final int l12_c = Native.genericCompare(akey, l7_k);
			if ((l12_c<0)) {
				return Module_tree.f_rebalancedTree(l7_k, l8_v, Module_tree.f_setTree(l9_left, akey, avalue), l10_right);
			} else {
				if ((l12_c==0)) {
					return (new Struct_TreeNode(l7_k, avalue, l9_left, l10_right, l11_depth));
				} else {
					return Module_tree.f_rebalancedTree(l7_k, l8_v, l9_left, Module_tree.f_setTree(l10_right, akey, avalue));
				}
			}
		}
		case 91/*TreeEmpty*/: {
			return (new Struct_TreeNode(akey, avalue, ((Struct)SingletonStructs.str_TreeEmpty), ((Struct)SingletonStructs.str_TreeEmpty), 1));
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final int f_treeDepth(Struct atree) {
		Struct l0__tmp = atree;
		switch (l0__tmp.getTypeId()) {
		case 91/*TreeEmpty*/: {
			return 0;
		}
		case 92/*TreeNode*/: {
			final Struct_TreeNode l1__tmp = (Struct_TreeNode)l0__tmp;
			final int l2_depth = l1__tmp.f_depth;
			final Object l3___ = (l1__tmp).f_key;
			final Object l4___ = (l1__tmp).f_value;
			final Struct l5___ = (l1__tmp).f_left;
			final Struct l6___ = (l1__tmp).f_right;
			final int l7_depth = (l1__tmp).f_depth;
			return l7_depth;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_treeLeftRotation(Struct atree) {
		Struct l0__tmp = atree;
		switch (l0__tmp.getTypeId()) {
		case 91/*TreeEmpty*/: {
			return atree;
		}
		case 92/*TreeNode*/: {
			final Struct_TreeNode l1__tmp = (Struct_TreeNode)l0__tmp;
			final Object l2_k = l1__tmp.f_key;
			final Object l3_v = l1__tmp.f_value;
			final Struct l4_left = l1__tmp.f_left;
			final Struct l5_right = l1__tmp.f_right;
			final int l6_depth = l1__tmp.f_depth;
			final Object l7_k = (l1__tmp).f_key;
			final Object l8_v = (l1__tmp).f_value;
			final Struct l9_left = (l1__tmp).f_left;
			final Struct l10_right = (l1__tmp).f_right;
			final int l11_depth = (l1__tmp).f_depth;
			Struct l12__tmp = l10_right;
			switch (l12__tmp.getTypeId()) {
			case 91/*TreeEmpty*/: {
				return l1__tmp;
			}
			case 92/*TreeNode*/: {
				final Struct_TreeNode l13__tmp = (Struct_TreeNode)l12__tmp;
				final Object l14_ck = l13__tmp.f_key;
				final Object l15_cv = l13__tmp.f_value;
				final Struct l16_cleft = l13__tmp.f_left;
				final Struct l17_cright = l13__tmp.f_right;
				final int l18_cdepth = l13__tmp.f_depth;
				final Object l19_ck = (l13__tmp).f_key;
				final Object l20_cv = (l13__tmp).f_value;
				final Struct l21_cleft = (l13__tmp).f_left;
				final Struct l22_cright = (l13__tmp).f_right;
				final int l23_cdepth = (l13__tmp).f_depth;
				return Module_tree.f_mkTreeNode(l19_ck, l20_cv, Module_tree.f_mkTreeNode(l7_k, l8_v, l9_left, l21_cleft), l22_cright);
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l12__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
	public static final Struct f_treeRightRotation(Struct atree) {
		Struct l0__tmp = atree;
		switch (l0__tmp.getTypeId()) {
		case 91/*TreeEmpty*/: {
			return atree;
		}
		case 92/*TreeNode*/: {
			final Struct_TreeNode l1__tmp = (Struct_TreeNode)l0__tmp;
			final Object l2_k = l1__tmp.f_key;
			final Object l3_v = l1__tmp.f_value;
			final Struct l4_left = l1__tmp.f_left;
			final Struct l5_right = l1__tmp.f_right;
			final int l6_depth = l1__tmp.f_depth;
			final Object l7_k = (l1__tmp).f_key;
			final Object l8_v = (l1__tmp).f_value;
			final Struct l9_left = (l1__tmp).f_left;
			final Struct l10_right = (l1__tmp).f_right;
			final int l11_depth = (l1__tmp).f_depth;
			Struct l12__tmp = l9_left;
			switch (l12__tmp.getTypeId()) {
			case 91/*TreeEmpty*/: {
				return l1__tmp;
			}
			case 92/*TreeNode*/: {
				final Struct_TreeNode l13__tmp = (Struct_TreeNode)l12__tmp;
				final Object l14_ck = l13__tmp.f_key;
				final Object l15_cv = l13__tmp.f_value;
				final Struct l16_cleft = l13__tmp.f_left;
				final Struct l17_cright = l13__tmp.f_right;
				final int l18_cdepth = l13__tmp.f_depth;
				final Object l19_ck = (l13__tmp).f_key;
				final Object l20_cv = (l13__tmp).f_value;
				final Struct l21_cleft = (l13__tmp).f_left;
				final Struct l22_cright = (l13__tmp).f_right;
				final int l23_cdepth = (l13__tmp).f_depth;
				return Module_tree.f_mkTreeNode(l19_ck, l20_cv, l21_cleft, Module_tree.f_mkTreeNode(l7_k, l8_v, l22_cright, l10_right));
			}
			default:
				throw new RuntimeException("Unexpected struct in switch: "+l12__tmp.getTypeName());
			}
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l0__tmp.getTypeName());
		}
	}
}
