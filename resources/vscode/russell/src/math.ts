import * as vscode from 'vscode';
import * as path from 'path';

export interface MathEntity {
	kind : string;
	location : vscode.Location;
	name : string;
	tooltip : string;
}

export interface OntologyItem {
	id : string;
	rule : any;
	rule_str : string;
	def : any | null;
	def_str : string | null;
	children : string[];
	parents : string[];
}
export class MathProvider {
	private providers : MathKindProvider[];
	private ontologyProvider : OntologyProvider;

	public constructor() {
		this.providers = [
			new MathKindProvider("const"),
			new MathKindProvider("type"),
			new MathKindProvider("rule"),
			new MathKindProvider("def"),
			new MathKindProvider("axiom")
		];
		this.ontologyProvider = new OntologyProvider();
		this.providers.forEach(provider => 
			vscode.window.registerTreeDataProvider("math-" + provider.kind + "s", provider)
		);
		vscode.window.registerTreeDataProvider("math-ontology", this.ontologyProvider);
	}

	public updateMath(data : MathEntity[]): void {
		const sorted = data.sort((a: MathEntity, b: MathEntity) => a.name.localeCompare(b.name));
		this.providers.forEach(provider => provider.update(sorted));
	}
	public updateOntology(data : OntologyItem[]): void {
		this.ontologyProvider.update(data);
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
		this._onDidChangeTreeData.fire(undefined);
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

/*
RuOntologyItem(
	rule : RuRule,
	def : Maybe<RuDef>,
	children : ref [int],
	parents : ref [int],
	deps : ref Set<int>
);
	JsonObject(filtermap([
		Some(Pair("id", JsonString(id2s(item.rule.info.id)))),
		Some(Pair("rule", ru2json(item.rule, math.decls.lang.rules))),
		maybeMap(item.def, \def -> Pair("def", ru2json(def, math.decls.lang.rules))),
		Some(Pair("rule_str", JsonString(ru2s(item.rule, math)))),
		maybeMap(item.def, \def -> Pair("def_str", JsonString(ru2s(def, math)))),
		Some(Pair("children", JsonArray(map(^(item.children), \ch -> JsonString(id2s(ch)))))),
		Some(Pair("parents", JsonArray(map(^(item.parents), \par -> JsonString(id2s(par)))))),
	], idfn));
*/

class OntologyProvider implements vscode.TreeDataProvider<OntologyItem> {

	private _onDidChangeTreeData: vscode.EventEmitter<OntologyItem | null> = new vscode.EventEmitter<OntologyItem | null>();
	readonly onDidChangeTreeData: vscode.Event<OntologyItem | null> = this._onDidChangeTreeData.event;

	private items: Map<string, OntologyItem> = null;
	private roots: OntologyItem[];

	update(data : OntologyItem[]): void {
		//vscode.window.showInformationMessage("ONTOLOGY: " + JSON.stringify(data));
		this.items = data.reduce((acc, c) => acc.set(c.id, c), new Map());
		this.roots = data.filter((i) => i.parents.length == 0);
		this._onDidChangeTreeData.fire(undefined);
	}

	getChildren(item?: OntologyItem): Thenable<OntologyItem[]> {
		if (!item) {
			return Promise.resolve(this.roots);
		} else {
			if (item.children) {
				return Promise.resolve(
					item.children.map((ch) => this.items.get(ch))
				);
			} else {
				return Promise.resolve([]);
			}
		}
	}

	getTreeItem(item: OntologyItem): vscode.TreeItem {
		let treeItem: vscode.TreeItem = new vscode.TreeItem(
			item.rule.term.string,
			vscode.TreeItemCollapsibleState.Collapsed
		);
		/*treeItem.command = {
			command: 'russell.gotoLocation', 
			title: 'open location',
			arguments: [c.location]
		};*/
		treeItem.tooltip = 
			"Rule: " + item.rule_str + 
			(item.def_str ? "\nDef: " + item.def_str : "");
		return treeItem;
	}
}