import * as vscode from 'vscode';
import * as path from 'path';

export interface MathEntity {
	kind : string;
	location : vscode.Location;
	name : string;
	tooltip : string;
}

export class MathProvider {
	private providers : MathKindProvider[];

	public constructor() {
		this.providers = [
			new MathKindProvider("const"),
			new MathKindProvider("type"),
			new MathKindProvider("rule"),
			new MathKindProvider("def"),
			new MathKindProvider("axiom")
		];
		this.providers.forEach(provider => 
			vscode.window.registerTreeDataProvider("math-" + provider.kind + "s", provider)
		);
	}

	public update(data : MathEntity[]): void {
		this.providers.forEach(provider => provider.update(data));
	}
}

class MathKindProvider implements vscode.TreeDataProvider<string> {

	private _onDidChangeTreeData: vscode.EventEmitter<string | null> = new vscode.EventEmitter<string | null>();
	readonly onDidChangeTreeData: vscode.Event<string | null> = this._onDidChangeTreeData.event;

	private entities: Map<string, MathEntity> = null;
	public kind : string;

	constructor(kind : string) {
		this.kind = kind;
	}
	update(data : MathEntity[]): void {
		this.entities = data.reduce(
			(acc, c) => (c.kind == this.kind) ? acc.set(c.name, c) : acc, 
			new Map()
		);
		this._onDidChangeTreeData.fire();
	}

	getChildren(name?: string): Thenable<string[]> {
		if (name) {
			return Promise.resolve([]);
		} else {
			return Promise.resolve(this.entities ? Array.from(this.entities.keys()): []);
		}
	}

	getTreeItem(name: string): vscode.TreeItem {
		if (this.entities.has(name)) {
			const c = this.entities.get(name);
			let treeItem: vscode.TreeItem = new vscode.TreeItem(
				c.name, 
				vscode.TreeItemCollapsibleState.None
			);
			treeItem.command = {
				command: 'russell.gotoLocation', 
				title: 'open location',
				arguments: [c.location]
			};
			treeItem.tooltip = c.tooltip;
			return treeItem;
		} else {
			return null;
		}
	}
}
