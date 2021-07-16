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
	proofs: number;
	children : HypEntity[];
	parent: number;
	tooltip: string;
}

export interface HypEntity {
	id: number;
	expr: string;
	proofs: number;
	children : number[];
	parent: number;
	tooltip: string;
}

export interface RootEntity {
	expr: string;
	proofs: string[];
	children : number[];
	tooltip: string;
}

export interface ProofVariantTree {
	nodes: PropEntity[],
	root: RootEntity 
}

// Internal data structure for a tree node

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


export class ProverProvider {
	private provider: ProofVariantProvider;

	public constructor() {
		this.provider = new ProofVariantProvider();
		vscode.window.registerTreeDataProvider("russell-prover", this.provider);
	}
	//public destroy(): void {
	//	vscode.window. registerTreeDataProvider("russell-prover", this.provider);
	//}

	public update(data : string): string | null {
		var tree: ProofVariantTree;
		try {
			tree = <ProofVariantTree>JSON.parse(data);
		} catch {
			vscode.window.showErrorMessage("error wile parsing JSON:\n" + data);
		}
		this.provider.update(tree);
		return (tree.root.proofs.length == 0) ? undefined : tree.root.proofs[0];
	}
}

class ProofVariantProvider implements vscode.TreeDataProvider<NodeEntity> {

	private _onDidChangeTreeData: vscode.EventEmitter<NodeEntity> = new vscode.EventEmitter<NodeEntity>();
	readonly onDidChangeTreeData: vscode.Event<NodeEntity> = this._onDidChangeTreeData.event;

	private nodes: Map<number, NodeEntity> = new Map();

	constructor() { }
	update(tree : ProofVariantTree): void {
		const root = <NodeEntity> {
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
				'parent': -1,
				'tooltip': prop.tooltip
			});
		});
		this._onDidChangeTreeData.fire(root);
	}

	getChildren(item: NodeEntity): Thenable<NodeEntity[]> {
		if (this.nodes.has(item.id)){
			return Promise.resolve(item.children.map((id) => this.nodes.get(id)));
		} else {
			return Promise.resolve([]);
		}
	}

	getTreeItem(item: NodeEntity): vscode.TreeItem {
		if (this.nodes.has(item.id)) {
			const node = this.nodes.get(item.id);
			let treeItem: vscode.TreeItem = new vscode.TreeItem(
				node.label, 
				vscode.TreeItemCollapsibleState.Collapsed
			);
			if (item.kind == 'prop') {
				treeItem.command = {
					command: 'russell.proverExpand', 
					title: 'expand the node',
					arguments: [item.id]
				};
			}
			treeItem.tooltip = item.tooltip;
			return treeItem;
		} else {
			return null;
		}
	}
}
