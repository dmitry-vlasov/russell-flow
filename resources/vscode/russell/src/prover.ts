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
			return this.provider.update(tree);
		}
	}
	public clear(): void {
		this.provider.clear();
	}
	public startProving(): void {
		this.clear();
		this.client.sendRequest("workspace/executeCommand", requests.proverPositionCommand("prove-start")).then(
			(data : any) => {
				if (data) {
					const proof = this.update(data);
					if (proof) {
						vscode.workspace.openTextDocument({'language': 'russell', 'content': '\n' + proof})
							.then(doc => vscode.window.showTextDocument(doc, {preview: false}));
					}
				}
			},
			vscode.window.showErrorMessage
		)
	}
	public expandProp(node : NodeEntity): void {
		this.provider.expandProp(node);
	}
	public forbidAssertion(node: NodeEntity): void {
		if (node.kind !== 'prop') return;
		const assertion = node.label;
		const cmd = { command: "command", arguments: ["prove-forbid assertions=" + assertion] };
		this.client.sendRequest("workspace/executeCommand", cmd).then(
			() => vscode.window.showInformationMessage("Blocked assertion: " + assertion),
			vscode.window.showErrorMessage
		);
	}
	public showCandidates(node: NodeEntity): void {
		const nodeId = node.kind === 'root' ? -1 : node.id;
		const cmd = { command: "command", arguments: ["prove-candidates node=" + nodeId] };
		this.client.sendRequest("workspace/executeCommand", cmd).then(
			(data: any) => {
				if (!data || !Array.isArray(data)) return;
				const items: vscode.QuickPickItem[] = data.map((c: any) => ({
					label: c.assertion,
					description: "arity " + c.arity
				}));
				vscode.window.showQuickPick(items, {
					placeHolder: "Choose an action",
					canPickMany: false,
				}).then((picked) => {
					if (!picked) return;
					vscode.window.showQuickPick(
						[
							{ label: "Block this assertion", action: "forbid" },
							{ label: "Expand with only this assertion", action: "expand" },
						],
						{ placeHolder: "What to do with '" + picked.label + "'?" }
					).then((action: any) => {
						if (!action) return;
						if (action.action === "forbid") {
							const forbidCmd = { command: "command", arguments: ["prove-forbid assertions=" + picked.label] };
							this.client.sendRequest("workspace/executeCommand", forbidCmd).then(
								() => vscode.window.showInformationMessage("Blocked: " + picked.label),
								vscode.window.showErrorMessage
							);
						} else {
							this.provider.expandPropWith(node, picked.label);
						}
					});
				});
			},
			vscode.window.showErrorMessage
		);
	}
}

class ProofVariantProvider implements vscode.TreeDataProvider<NodeEntity> {

	private _onDidChangeTreeData: vscode.EventEmitter<NodeEntity | undefined | void> = new vscode.EventEmitter<NodeEntity>();
	readonly onDidChangeTreeData: vscode.Event<NodeEntity | undefined | void> = this._onDidChangeTreeData.event;

	private client: LanguageClient = null;
	private props: Map<number, PropEntity> = new Map();
	private root: RootEntity;
	private expanding: Set<number> = new Set();
	private log: vscode.OutputChannel;

	constructor() {
		this.log = vscode.window.createOutputChannel("Russell Prover Debug");
		this.log.show(true);
		this.log.appendLine("[ProofVariantProvider] initialized");
	}
	public setClient(client: LanguageClient) {
		this.client = client;
	}
	public clear(): void {
		this.props.clear();
		this.expanding.clear();
		this.root = null;
		this._onDidChangeTreeData.fire();
	}
	public update(tree : ProofVariantTree): string | null {
		if (tree.root) {
			this.root = tree.root;
			this._onDidChangeTreeData.fire(rootEntity2Node(this.root));
		}
		tree.nodes.forEach(prop => this.props.set(prop.id, prop));
		this._onDidChangeTreeData.fire();
		// Only return a proof when this diff explicitly includes a root with proofs,
		// meaning a new proof was found in this expansion.
		if (tree.root && tree.root.proofs && tree.root.proofs.length > 0) {
			return tree.root.proofs[0];
		}
		return null;
	}
	public async expandProp(node : NodeEntity): Promise<void> {
		const expand_command = { command: "command", arguments: ["prove-expand nodes=" + node.id] };
		console.error("[prover] expandProp called for node " + node.id);
		this.log.appendLine("[expandProp] called for node " + node.id);
		return this.client.sendRequest("workspace/executeCommand", expand_command).then(
			(data: any) => {
				try {
					const dataStr = JSON.stringify(data);
					const hasRoot = data && data.root;
					const rootProofsLen = (hasRoot && data.root.proofs) ? data.root.proofs.length : -1;
					const msg = "expand: type=" + typeof data +
						" hasRoot=" + !!hasRoot +
						" rootProofs=" + rootProofsLen +
						" | " + dataStr.substring(0, 120);
					console.error("[prover] " + msg);
					this.log.appendLine("[expandProp] response: " + msg);
					const proof = this.update(data);
					if (proof) {
						this.log.appendLine("[expandProp] proof found (" + proof.length + " chars)");
						vscode.workspace.openTextDocument({'language': 'russell', 'content': '\n' + proof})
							.then(doc => vscode.window.showTextDocument(doc, {preview: false}));
					} else {
						this.log.appendLine("[expandProp] proof is null/empty after update");
					}
				} catch(e) {
					this.log.appendLine("[expandProp] ERROR: " + e);
					console.error("[prover] expand error: " + e);
				}
				return Promise.resolve();
			},
			(err: any) => {
				this.log.appendLine("[expandProp] request FAILED: " + JSON.stringify(err));
				console.error("[prover] expandProp request failed: " + JSON.stringify(err));
				return Promise.reject(err);
			}
		);
	}
	public async expandPropWith(node: NodeEntity, assertion: string): Promise<void> {
		const cmd = { command: "command", arguments: ["prove-expand nodes=" + node.id + " assertion=" + assertion] };
		return this.client.sendRequest("workspace/executeCommand", cmd).then((data: any) => {
			if (!data) {
				vscode.window.showErrorMessage("expand-with failed:\n" + JSON.stringify(data));
				return Promise.reject();
			} else {
				const proof = this.update(data);
				if (proof) {
					vscode.workspace.openTextDocument({'language': 'russell', 'content': '\n' + proof})
						.then(doc => vscode.window.showTextDocument(doc, {preview: false}));
				}
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
					// Fire expansion without awaiting it; return [] now.
					// When expandProp resolves it fires _onDidChangeTreeData and VSCode
					// re-calls getChildren, which will then see expanded=true.
					if (!this.expanding.has(item.id)) {
						this.expanding.add(item.id);
						this.expandProp(item).finally(() => this.expanding.delete(item.id));
					}
					return Promise.resolve([]);
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
				treeItem.contextValue = 'russell-prover-root';
				return treeItem;
			} else if (item.kind == 'prop') {
				if (this.props.has(item.id)) {
					const prop = this.props.get(item.id);
					let treeItem: vscode.TreeItem = new vscode.TreeItem(
						item.label, 
						vscode.TreeItemCollapsibleState.Collapsed
					);
					treeItem.command = {
						command: 'russell.prover-expand-prop',
						title: 'expand the node',
						arguments: [item]
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
				treeItem.contextValue = 'russell-prover-hyp';
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
