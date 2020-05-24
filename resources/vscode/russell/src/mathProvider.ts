import * as vscode from 'vscode';
import * as path from 'path';

export interface MathEntity {
	location : vscode.Location;
	name : string;
	tooltip : string;
}

export class MathProvider implements vscode.TreeDataProvider<string> {

	private _onDidChangeTreeData: vscode.EventEmitter<string | null> = new vscode.EventEmitter<string | null>();
	readonly onDidChangeTreeData: vscode.Event<string | null> = this._onDidChangeTreeData.event;

	private entities: Map<string, MathEntity> = null;
	private updater : () => Thenable<MathEntity[]> | null;

	constructor(private context: vscode.ExtensionContext, updater: () => Thenable<MathEntity[]> | null) {
		this.updater = updater;
	}
	update(): void {
		let updated = this.updater();
		if (updated) {
			updated.then((entitites : MathEntity[]) => {
				if (entitites) {
					this.entities = entitites.reduce((acc, c) => acc.set(c.name, c), new Map())
				}
			});
		}
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
