import * as vscode from 'vscode';
import * as path from 'path';
import * as requests from './requests';
import { LanguageClient } from 'vscode-languageclient/node';

// Passed by a prover
export interface PropEntity {
	id: number;
	assertion: string;
	proofs: string[];
	children : HypEntity[];
	grand: number;
	expanded: boolean;
	tooltip : string;
}

export interface HypEntity {
	ind: number;
	expr_plain: string;
	expr_multyline: string;
	proofs: string[];
	children : number[];
	parent: number;
	tooltip : string;
}

export interface RootEntity {
	expr_plain: string;
	expr_multyline: string;
	proofs: string[];
	children : number[];
	tooltip : string;
}

export interface ProofVariantTree {
	nodes: PropEntity[],
	root?: RootEntity 
}

// Internal data structure for a tree node

export type NodeKind = 'prop' | 'hyp' | 'root';

export interface NodeEntity {
	id: number;
	kind: NodeKind;
	label: string;
	children : number[];
	parent: number;
	expanded: boolean;
	tooltip: string;
}

export class ProverProvider {
	private provider = new ProofVariantProvider();
	private client: LanguageClient = null;

	public constructor() {
		vscode.window.registerTreeDataProvider("russell-prover", this.provider);
	}
	public setClient(client: LanguageClient) {
		this.client = client;
		this.provider.setClient(client);
	}
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
			//vscode.window.showInformationMessage("A prover tree:\n" + JSON.stringify(tree));
			this.provider.update(tree);
		}
	}
	public clear(): void {
		this.provider.update({nodes: [], root: null});
	}
	public startProving(): void {
		this.clear();
		this.client.onNotification("prover/proved", (proved : any) => {
			if (proved) {
				vscode.workspace.openTextDocument({'language': 'russell', 'content': '\n' + proved});
			}
			this.client.onNotification("prover/proved", () => {});
		});
		this.client.sendRequest("workspace/executeCommand", requests.filePositionCommand("prove-start")).then(
			(data : any) => {
				if (data) {
					const proof = this.update(data);
					if (proof) {
						vscode.workspace.openTextDocument({'language': 'russell', 'content': '\n' + proof});
					}
				}
			},
			vscode.window.showErrorMessage
		)
	}
	public expandProp(node : NodeEntity): void {
		this.provider.expandProp(node);
	}
}

class ProofVariantProvider implements vscode.TreeDataProvider<NodeEntity> {

	private _onDidChangeTreeData: vscode.EventEmitter<NodeEntity | undefined | void> = new vscode.EventEmitter<NodeEntity>();
	readonly onDidChangeTreeData: vscode.Event<NodeEntity | undefined | void> = this._onDidChangeTreeData.event;

	private client: LanguageClient = null;
	private props: Map<number, PropEntity> = new Map();
	private root: RootEntity;

	constructor() { }
	public setClient(client: LanguageClient) {
		this.client = client;
	}
	public update(tree : ProofVariantTree): void {
		if (tree.root) {
			this.root = tree.root;
			this._onDidChangeTreeData.fire(rootEntity2Node(this.root));
		}
		tree.nodes.forEach(prop => this.props.set(prop.id, prop));
		this._onDidChangeTreeData.fire();
	}
	public async expandProp(node : NodeEntity): Promise<void> {
		const expand_command = { command: "command", arguments: ["conf verb=1; prove-expand nodes=" + node.id] };
		return this.client.sendRequest("workspace/executeCommand",expand_command).then((data: any) => {
			const update = <ProofVariantTree>data;
			if (!update) {
				vscode.window.showErrorMessage("update of a prover tree failed:\n" + JSON.stringify(data));
				return Promise.reject();
			} else {
				//vscode.window.showInformationMessage("A prover tree update:\n" + JSON.stringify(data));
				this.update(data);
				return Promise.resolve();
			}
		});
	}

	getChildren(item?: NodeEntity): Thenable<NodeEntity[]> {
		//vscode.window.showInformationMessage("getChildren of: " + item);
		if (!item) {
			if (this.root) {
				return Promise.resolve([rootEntity2Node(this.root)]);
			} else {
				return Promise.resolve([]);
			}
		} else if (item.kind == 'root') {
			return Promise.resolve(
				item.children.map((id) => propEntity2Node(this.props.get(id)))
			);
		} else if (item.kind == 'prop') {
			//vscode.window.showErrorMessage("getChildren of: prop");
			if (this.props.has(item.id)){
				if (!this.props.has(item.id)) {
					vscode.window.showErrorMessage("Prop " + JSON.stringify(item) + " with " + item.id + " is absent");
				}
				const prop = this.props.get(item.id);
				const prop_children = () => {
					return Promise.resolve(this.props.get(item.id).children.map(hypEntity2Node));
				}
				if (prop.expanded) {
					return prop_children();
				} else {
					return this.expandProp(item).then(prop_children);
				}
			} else {
				return Promise.resolve([]);
			}
		} else {
			//vscode.window.showInformationMessage("getChildren of: hyp");
			return Promise.resolve(
				item.children.map((id) => {
					if (!this.props.has(id)) {
						vscode.window.showErrorMessage("Hyp " + JSON.stringify(item) + " child " + id + " is absent");
					}
					return propEntity2Node(this.props.get(id));
				})
			);
		}
	}

	getParent(item: NodeEntity): Thenable<NodeEntity> {
		if (!item) {
			vscode.window.showErrorMessage("Null item (getParent)");
			return Promise.reject();
		} else {
			//vscode.window.showInformationMessage("getParent of: " + JSON.stringify(item));
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
			//vscode.window.showErrorMessage("Null item (getTreeItem)");
			return null;
		} else {
			//vscode.window.showInformationMessage("getTreeItem of: " + JSON.stringify(item));
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
		'expanded': prop.expanded,
		'tooltip': prop.tooltip + 
			(prop.proofs.length == 0 ? "" : "\nproved:\n" + prop.proofs.join('\n'))
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
		'expanded': true,
		'tooltip': hyp.tooltip + 
			(hyp.proofs.length == 0 ? "" : "\nproved:\n" + hyp.proofs.join('\n'))
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
		'expanded': true,
		'tooltip': root.tooltip + 
			(root.proofs.length == 0 ? "" : "\nproved:\n" + root.proofs.join('\n'))
	}
}
