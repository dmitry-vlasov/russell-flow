import * as vscode from 'vscode';
import * as path from 'path';
/*
RuProverProp(
			id        : int,
			assertion : RuAssertion,
			proofs    : ref [RuProverProofProp],
			sub       : Tree<RuVar, RuExp>,
			outer     : Tree<RuVar, RuExp>,
			children  : ref [RuProverExp],
			parent    : RuProverHyp,
		);
		RuProverExp(
			ind      : int,
			proofs   : ref [RuProverProofHyp],
			expr     : RuExp,
			children : ref [RuProverProp],
			parent   : RuProverProp,
		);
		RuProverRoot(
			proofs   : ref [RuProverProofHyp],
			expr     : RuExp,
			children : ref [RuProverProp]
		);

*/

// Passed by a prover

export interface PropEntity {
	id: number;
	assertion: string;
	proofs: string[];
	children : HypEntity[];
	grand: number;
}

export interface HypEntity {
	ind: number;
	expr_plain: string;
	expr_multyline: string;
	proofs: string[];
	children : number[];
	parent: number;
}

export interface RootEntity {
	expr_plain: string;
	expr_multyline: string;
	proofs: string[];
	children : number[];
}

export interface ProofVariantTree {
	nodes: PropEntity[],
	root: RootEntity 
}

// Internal data structure for a tree node

export type NodeKind = 'prop' | 'hyp' | 'root';

export interface NodeEntity {
	id: number;
	kind: NodeKind;
	label: string;
	children : number[];
	parent: number;
	tooltip: string;
}


export class ProverProvider {
	private provider: ProofVariantProvider;

	public constructor() {
		this.provider = new ProofVariantProvider();
		vscode.window.registerTreeDataProvider("russell-prover", this.provider);
	}
	//public destroy(): void {
	//	vscode.window. registerTreeDataProvider("russell-prover", this.provider);
	//}

	public update(data : any): string | null {
		var tree: ProofVariantTree = null;
		const json = (typeof data == 'object') ? <JSON>data : JSON.parse(data.toString())
		try {
			tree = <ProofVariantTree>json;
		} catch {
			vscode.window.showErrorMessage("error wile parsing JSON:\n" + JSON.stringify(data));
		}
		if (!tree) {
			vscode.window.showErrorMessage("update of a prover tree failed:\n" + JSON.stringify(data));
			return null;	
		} else {
			//vscode.window.showErrorMessage("A prover tree:\n" + JSON.stringify(tree));
			this.provider.update(tree);
			return (tree.root.proofs.length == 0) ? null : tree.root.proofs[0];
		}
	}
}

class ProofVariantProvider implements vscode.TreeDataProvider<NodeEntity> {

	private _onDidChangeTreeData: vscode.EventEmitter<NodeEntity> = new vscode.EventEmitter<NodeEntity>();
	readonly onDidChangeTreeData: vscode.Event<NodeEntity> = this._onDidChangeTreeData.event;

	private props: Map<number, PropEntity> = new Map();
	private root: RootEntity;

	constructor() { }
	update(tree : ProofVariantTree): void {
		/*const root = <NodeEntity> {
			'id': 0,
			'kind': 'hyp',
			'label': tree.root.expr,
			'proofs': tree.root.proofs.length,
			'children' : tree.root.children,
			'parent': -1,
			'tooltip': tree.root.tooltip
		};
		this.nodes.set(root.id, root);
		let cur_hyp_id = tree.nodes.length + 1;
		tree.nodes.forEach(prop => {
			const prop_children = prop.children.map(
				hyp => <NodeEntity> {
					'id': cur_hyp_id ++,
					'kind': 'hyp',
					'label': hyp.expr,
					'proofs': hyp.proofs,
					'children': hyp.children,
					'parent': prop.id,
					'tooltip': hyp.tooltip
				}
			);
			prop_children.forEach(child => this.nodes.set(child.id, child));
			this.nodes.set(prop.id, {
				'id': prop.id,
				'kind': 'prop',
				'label': prop.assertion,
				'proofs': prop.proofs,
				'children' : prop_children.map(child => child.id),
				'parent': prop.parent,
				'tooltip': prop.tooltip
			});
		});*/
		this.root = tree.root;
		tree.nodes.forEach(prop => this.props.set(prop.id, prop));
		const root = rootEntity2Node(tree.root);
		/*<NodeEntity> {
			'id': 0,
			'kind': 'root',
			'label': tree.root.expr_plain,
			'children' : tree.root.children,
			'parent': -1,
			'tooltip': tree.root.expr_multyline + 
				(tree.root.proofs.length == 0 ? "" : "\n" + tree.root.proofs.join('\n'))
		};*/
		this._onDidChangeTreeData.fire(root);
	}
	
/*
export type NodeKind = 'prop' | 'hyp';

export interface NodeEntity {
	id: number;
	kind: NodeKind;
	label: string;
	proofs: number;
	children : number[];
	parent: number;
	tooltip: string;
}

*/

	getChildren(item?: NodeEntity): Thenable<NodeEntity[]> {
		/*if (!item) {
			vscode.window.showErrorMessage("Null item (getChildren)");
			return Promise.reject();
		} else {*/
			vscode.window.showErrorMessage("getChildren of: " + item);
			if (!item) {
				return Promise.resolve([rootEntity2Node(this.root)]);
			} else if (item.kind == 'root') {
				return Promise.resolve(
					item.children.map((id) => propEntity2Node(this.props.get(id)))
				);
			} else if (item.kind == 'prop') {
				vscode.window.showErrorMessage("getChildren of: prop");
				if (this.props.has(item.id)){
					if (!this.props.has(item.id)) {
						vscode.window.showErrorMessage("Prop " + JSON.stringify(item) + " with " + item.id + " is absent");
					}
					const prop = this.props.get(item.id);
					return Promise.resolve(prop.children.map(hypEntity2Node));
				} else {
					return Promise.resolve([]);
				}
			} else {
				vscode.window.showErrorMessage("getChildren of: hyp");
				return Promise.resolve(
					item.children.map((id) => {
						if (!this.props.has(id)) {
							vscode.window.showErrorMessage("Hyp " + JSON.stringify(item) + " child " + id + " is absent");
						}
						return propEntity2Node(this.props.get(id));
					})
				);
			}
		//}
	}

	getParent(item: NodeEntity): Thenable<NodeEntity> {
		if (!item) {
			vscode.window.showErrorMessage("Null item (getParent)");
			return Promise.reject();
		} else {
			vscode.window.showErrorMessage("getParent of: " + JSON.stringify(item));
			if (item.kind == 'root') {
				return Promise.resolve(null);
			} else if (item.kind == 'prop') {
				if (this.props.has(item.id)){
					const prop = this.props.get(item.id);
					if (prop.grand == -1) {
						return Promise.resolve(rootEntity2Node(this.root));
					} else {
						const grand =  this.props.get(prop.grand);
						return Promise.resolve(hypEntity2Node(grand.children[item.id]));
					}
				} else {
					return Promise.resolve(null);
				}
			} else {
				const parent = this.props.get(item.parent);
				return Promise.resolve(propEntity2Node(parent));
			}
		}
	}

	getTreeItem(item: NodeEntity): vscode.TreeItem {
		if (!item) {
			vscode.window.showErrorMessage("Null item (getTreeItem)");
			return null;
		} else {
			vscode.window.showErrorMessage("getTreeItem of: " + JSON.stringify(item));
			if (item.kind == 'root') {
				let treeItem: vscode.TreeItem = new vscode.TreeItem(
					item.label, 
					vscode.TreeItemCollapsibleState.Collapsed
				);
				treeItem.tooltip = item.tooltip;
				return treeItem;
			} else if (item.kind == 'prop') {
				if (this.props.has(item.id)) {
					const prop = this.props.get(item.id);
					let treeItem: vscode.TreeItem = new vscode.TreeItem(
						item.label, 
						vscode.TreeItemCollapsibleState.Collapsed
					);
					treeItem.command = {
						command: 'russell.proverExpand', 
						title: 'expand the node',
						arguments: [item.id]
					};
					treeItem.tooltip = item.tooltip;
					treeItem.contextValue = 'russell-prover-prop';
					return treeItem;
				} 
			} else {
				let treeItem: vscode.TreeItem = new vscode.TreeItem(
					item.label, 
					vscode.TreeItemCollapsibleState.Collapsed
				);
				treeItem.tooltip = item.tooltip;
				return treeItem;
			}
		}
	}
}

function propEntity2Node(prop : PropEntity): NodeEntity {
	return <NodeEntity> {
		'id': prop.id,
		'kind': 'prop',
		'label': prop.assertion,
		'proofs': prop.proofs,
		'children' : [],
		'parent': -1,
		'tooltip': prop.proofs.join('\n')
	}
}

function hypEntity2Node(hyp : HypEntity): NodeEntity {
	return <NodeEntity> {
		'id': hyp.ind,
		'kind': 'hyp',
		'label': hyp.expr_plain,
		'proofs': hyp.proofs,
		'children' : hyp.children,
		'parent': hyp.parent,
		'tooltip': hyp.expr_multyline + 
			(hyp.proofs.length == 0 ? "" : "\n" + hyp.proofs.join('\n'))
	}
}

function rootEntity2Node(root : RootEntity): NodeEntity {
	return <NodeEntity> {
		'id': -1,
		'kind': 'root',
		'label': root.expr_plain,
		'proofs': root.proofs,
		'children' : root.children,
		'parent': -1,
		'tooltip': root.expr_multyline + 
			(root.proofs.length == 0 ? "" : "\n" + root.proofs.join('\n'))
	}
}
