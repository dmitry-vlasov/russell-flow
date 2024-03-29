'use strict';
// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import { ChildProcess } from 'child_process';
import * as vscode from 'vscode';
import { 
	LanguageClient, LanguageClientOptions, RevealOutputChannelOn, ServerOptions
} from 'vscode-languageclient/node';
import { MathEntity, MathProvider, OntologyItem } from "./math";
import * as prover from "./prover";
import * as tools from "./tools";
import { num2memory } from './tools';
import * as requests from './requests';
import { doesNotReject } from 'assert';

//import isPortReachable from 'is-port-reachable'; // For 'is-port-reachable' 4.0.0. - doesn't work ...
const isPortReachable = require('is-port-reachable');

let client: LanguageClient = null;
let russellChannel: vscode.OutputChannel = null;
let lspChannel: vscode.OutputChannel = null;
let serverChannel: vscode.OutputChannel = null;
let serverStatusBarItem: vscode.StatusBarItem;
let httpServer: ChildProcess;
let httpServerOnline: boolean = false;
let mathProvider = new MathProvider();
let proverProvider = new prover.ProverProvider();

export function activate(context: vscode.ExtensionContext) {	
	serverStatusBarItem = vscode.window.createStatusBarItem(vscode.StatusBarAlignment.Right, 100);
	serverStatusBarItem.command = 'russell.toggleHttpServer';
	russellChannel = vscode.window.createOutputChannel("Russell output");
	updateLSPchannel();
	const reg_comm = (name: string, fn: any) => vscode.commands.registerCommand(name, fn);
	context.subscriptions.push(
		serverStatusBarItem,
		reg_comm('russell.saveVerifyFile', saveVerifyFile),
		reg_comm('russell.verifyFile', (uri: vscode.Uri) => processRussellFile(uri, "verify")),
		reg_comm('russell.verifyTheorem', () => processRussellTarget("verify")),
		reg_comm('russell.reproveFile', (uri: vscode.Uri) => processRussellFile(uri, "reprove-oracle")),
		reg_comm('russell.metamathFile', (uri: vscode.Uri) => verifyMetamath(uri)),
		reg_comm('russell.reproveTheorem', () => processRussellTarget("reprove-oracle")),
		reg_comm('russell.generalizeFile', generalizeFile),
		reg_comm('russell.generalizeTheorem', generalizeTheorem),
		reg_comm('russell.startHttpServer', startHttpServer),
		reg_comm('russell.stopHttpServer', stopHttpServer),
		reg_comm('russell.restartLspServer', startLspClient),
		reg_comm('russell.toggleHttpServer', toggleHttpServer),
		reg_comm('russell.findSymbol', findSymbol),
		reg_comm('russell.execCommand', execCommand),
		reg_comm('russell.gotoLocation', gotoLocation),
		reg_comm('russell.refreshMath', mathInfo),
		reg_comm('russell.refreshOntology', mathOntology),
		reg_comm('russell.prover-expand-prop', (node: prover.NodeEntity) => proverProvider.expandProp(node)),
		reg_comm('russell.prover-start', () => proverProvider.startProving()),
		vscode.workspace.onDidChangeConfiguration(handleConfigurationUpdates(context)),
	);
	checkHttpServerStatus(true);
	setInterval(checkHttpServerStatus, 3000, false);

	startLspClient();
	//addLatexHover();
	serverStatusBarItem.show();
}

function addLatexHover(): void {
	vscode.languages.registerHoverProvider('russell', {
		provideHover(document, position, token) {
			
			// a markdown table, wrapping in a styled span did not work
			// had to style each "cell" separately
			// html entity &nbsp; works
	
			const markdown = new vscode.MarkdownString(`
	|    <span style="color:#ff0;background-color:#000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Table&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>|    Header     |
	|    :----:    |    :----:     |
	|first cell    |second cell  |
	|third cell    |<span style="color:#f00;background-color:#fff;">&nbsp;&nbsp;fourth cell&nbsp;&nbsp;</span>  |
			\n\n\n`);  // the newline is necessary for any following appends to work correctly, multiple newlines are reduced to one
			
			const styledString = `<span style="color:#fff;background-color:#666;">&nbsp;&nbsp;&nbsp;NASA code follows:&nbsp;&nbsp;&nbsp;</span>`;
	
			const codeBlock = `const a = 12;
	if (a) return;`;    // any preceding tabs will be rendered in a template literal, so flush left
	
			// const codeBlock2 = `const c = 12;\nif (c) return;`;  // works, alternate form with newline
	
			markdown.appendText("______________________________\n");  // a fake separator
			markdown.appendMarkdown("$ \\frac{x^{2} + y^{2}}{z - 1}$\n\n");
			markdown.appendMarkdown("```markdown\n$ \\frac{x^{2} + y^{2}}{z - 1}$\n```\n\n");
			markdown.appendMarkdown(styledString);
			markdown.appendCodeblock(codeBlock, "javascript");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "math");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "katex");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "latex");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "tex");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "markdown");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "markdown-latex");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "markdown-katex");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "markdown-tex");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "markdown.latex");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "markdown.katex");
			markdown.appendCodeblock("$ \\frac{x^{2} + y^{2}}{z - 1}$", "markdown.tex");
			markdown.appendMarkdown(
	`**Bold Text**
	* some note
	* another note
	* final note`
			);
	
			markdown.isTrusted = true;
	
			return new vscode.Hover(markdown, new vscode.Range(position, position));
		}
	});
}

function mathInfo(): Promise<void> {
	return client.sendRequest("workspace/executeCommand", { command: "math-info", arguments: [] }).then(
		(data : MathEntity[]) => mathProvider.updateMath(data)
	);
}

function mathOntology(): Promise<void> {
	return client.sendRequest("workspace/executeCommand", { command: "math-ontology", arguments: [] }).then(
		(data : OntologyItem[]) => mathProvider.updateOntology(data)
	);
}

function removeAllSpaces(str: string) : string { 
    return str.replace(/[\r\n\t ]+/gm, ''); 
} 

function execCommand() {
	russellChannel.show(true);
	let options: vscode.InputBoxOptions = { prompt: "Command and args: ", placeHolder: "" };
	vscode.window.showInputBox(options).then(value => {
		if (removeAllSpaces(value).length > 0) {
			let file = vscode.window.activeTextEditor.document.uri.fsPath;
			client.sendRequest("workspace/executeCommand", { command : "command", arguments: [value, file] }).then(
				(out : string) => { },
				(err : any) => {
					vscode.window.showErrorMessage(`command '${value}' failed: ${err}`);
				}
			);
		}
	});
}

function gotoLocation(location : vscode.Location) : void {
	let uri = vscode.Uri.parse(location.uri.toString());
	vscode.workspace.openTextDocument(uri).then((document) =>
		vscode.window.showTextDocument(document).then((edit) =>
			edit.revealRange(location.range)
		)
	);
}

function findSymbol() {
	let options: vscode.InputBoxOptions = { prompt: "Symbol: ", placeHolder: "" };
	vscode.window.showInputBox(options).then(value => {
		if (value) {
			client.sendRequest("workspace/executeCommand", { command : "find-symbol", arguments: [value] }).
			then(gotoLocation);
		}
	});
}

function stopLspClient() {
	if (client) {
		client.sendNotification("exit");
		client.stop();
	}
	client = null;
}

function startLspClient() {
	stopLspClient();
	serverStatusBarItem.show();
	// If the extension is launched in debug mode then the debug server options are used
	// Otherwise the run options are used
	let serverOptions: ServerOptions = {
		command: process.platform == "win32" ? 'russell.bat' : 'russell_lsp',
		options:  { detached: false }
	}
	// Options to control the language client
	let clientOptions: LanguageClientOptions = {
		// Register the server for plain text documents
		documentSelector: [{scheme: 'file', language: 'russell'}],
		outputChannel: lspChannel,
		revealOutputChannelOn: RevealOutputChannelOn.Info,
		uriConverters: {
			// FIXME: by default the URI sent over the protocol will be percent encoded (see rfc3986#section-2.1)
			//        the "workaround" below disables temporarily the encoding until decoding
			//        is implemented properly in clangd
			code2Protocol: (uri: vscode.Uri) : string => uri.toString(true),
			protocol2Code: (uri: string) : vscode.Uri => vscode.Uri.parse(uri)
		},
		initializationOptions: {
			tabSize: vscode.workspace.getConfiguration("editor").get("tabSize")
		}
	}

	// Create the language client and start the client.
	client = new LanguageClient('russell', 'Russell Language Server', serverOptions, clientOptions);
	// Start the client. This will also launch the server
	client.start();
	client.onReady().then(
		() => {
			proverProvider.setClient(client);
			client.onNotification("console/message", (msg : string) => russellChannel.appendLine(msg));
			client.sendRequest("workspace/executeCommand", { command : "command", arguments: ["cache-load"] }).then(
				() => mathInfo().then(mathOntology),
				(err : any) => {
					vscode.window.showErrorMessage(`command 'cache-load' failed: ${err}`);
				}
			);
		}
	);
}

function updateLSPchannel() {
	const trace_server = vscode.workspace.getConfiguration("russell").get("trace.server");
	if (trace_server != "off") {
		lspChannel = vscode.window.createOutputChannel("Russell LSP");
	} else {
		if (lspChannel) {
			lspChannel.dispose();
			lspChannel = null;
		}
	}
}

function handleConfigurationUpdates(_context: vscode.ExtensionContext) {
	return (e: { affectsConfiguration: (arg0: string) => any; }) => {
		if (e.affectsConfiguration("russell.trace.server")) {
			updateLSPchannel();
		}
		if (e.affectsConfiguration("editor")) {
			const tabSize = vscode.workspace.getConfiguration("editor").get("tabSize");
			client.sendNotification("config/editor.tabSize", tabSize);
		}
	}
}

function checkHttpServerStatus(initial: boolean) {
	const port : number = vscode.workspace.getConfiguration("russell").get("portOfHttpServer");
	isPortReachable(port, {host: 'localhost'}).then(
		(reacheable : boolean) => {
			if (reacheable) {
				outputHttpServerMemStats();
				httpServerOnline = true;
			} else {
				httpServer = null;
				httpServerOnline = false;
				showHttpServerOffline();
				if (initial) {
					// launch russell server at startup
					let autostart = vscode.workspace.getConfiguration("russell").get("autostartHttpServer");
					if (autostart) {
						startHttpServer();
					}
				}
			}
		}
	);
}

function outputHttpServerMemStats() {
	client.sendRequest("workspace/executeCommand", { 
		command : "command", 
		arguments : ["stats-mem do_not_log_this=1"]
	}).then(
		(data : any) => {
			const mem_stats = num2memory(data.used) + " + " + num2memory(data.free) + " free = " + num2memory(data.total);
			showHttpServerOnline(mem_stats);
		},
		(err : any) => {
			russellChannel.appendLine("mem stats error: " + err);
			showHttpServerOffline();
		}
	);
}

function toggleHttpServer() {
    if (!httpServerOnline) {
		startHttpServer();
    } else {
		stopHttpServer();
	}
}

function startHttpServer() {
    if (!httpServerOnline) {
		if (!serverChannel) {
			serverChannel = vscode.window.createOutputChannel("Russell server");
			serverChannel.show();
		}
		httpServer = tools.launchHttpServer(
			showHttpServerIsLaunching, 
			showHttpServerOffline, 
			serverChannel
		);
		httpServerOnline = true;
    }
}

function stopHttpServer() {
	if (httpServerOnline) {
		tools.shutdownHttpServer().on("exit", (code, msg) => httpServer = null);
		httpServerOnline = false;
	}
}

function showHttpServerOnline(mem_stats? : string) {
	if (mem_stats) {
		serverStatusBarItem.text = `$(vm-active) russell http: online (` + mem_stats + ")";
	} else {
		serverStatusBarItem.text = `$(vm-active) russell http: online`;
	}
}

function showHttpServerOffline() {
	serverStatusBarItem.text = `$(vm-outline) russell: http server: offline`;
}

function showHttpServerIsLaunching() {
	serverStatusBarItem.text = `$(vm-connect) russell: http server: starting...`;
}

// this method is called when your extension is deactivated
export function deactivate(): Thenable<void> | undefined {
	// First, shutdown Russell server, if it is owned by current vscode instance
	if (httpServer) {
		tools.shutdownHttpServer().on("exit", (code, msg) => {
			if (code != 0) {
				httpServer.kill("SIGKILL");
			}
			httpServer = null
		});
	}
    if (client) {
		return client.sendRequest("shutdown").then(
			(data : any) => {
				client.sendNotification("exit");
				return client.stop();
			},
			(err : any) => {
				vscode.window.showErrorMessage("error while shutting down: " + err);
				return client.stop();
			}
		);
    } else {
		return undefined;
	}
}

function saveVerifyFile(uri : vscode.Uri): void {
	if (!uri) {
		uri = vscode.window.activeTextEditor.document.uri;
	}
	if (uri) {
		let doc = vscode.workspace.textDocuments.find((doc) => doc.uri == uri);
		if (doc) {
			doc.save().then(() => processRussellFile(uri, "verify"));
		} else {
			vscode.window.showErrorMessage("can't fine document " + uri.fsPath);
		}
	}
}

function processRussellFile<T>(uri : vscode.Uri, action : string): Promise<T> {
	if (!uri) {
		uri = vscode.window.activeTextEditor.document.uri;
	}
	return processRussell(uri, uri.fsPath, action);
}

function processRussellTarget<T>(action : string): Promise<T> {
	let uri = vscode.window.activeTextEditor.document.uri;
	let pos = vscode.window.activeTextEditor.selection.active;
	let range = vscode.window.activeTextEditor.document.getWordRangeAtPosition(pos);
	let target = vscode.window.activeTextEditor.document.getText(range);
	return processRussell(uri, target, action);
}

function processRussellPosition<T>(action : string): Promise<T> {
	let uri = vscode.window.activeTextEditor.document.uri;
	let pos = vscode.window.activeTextEditor.selection.active;
	russellChannel.show(true);
	return client.sendRequest("workspace/executeCommand", requests.filePositionCommand(action));
}

function processRussell<T>(uri : vscode.Uri, target : string, action : string): Promise<T> {
	if (!uri) {
		uri = vscode.window.activeTextEditor.document.uri;
	}
	russellChannel.show(true);
	return client.sendRequest("workspace/executeCommand", requests.fileCommand(uri, target, action));
}

function verifyMetamath(uri : vscode.Uri): void {
	if (!uri) {
		uri = vscode.window.activeTextEditor.document.uri;
	}
	russellChannel.show(true);
	client.sendRequest("workspace/executeCommand", requests.verifyMetamath(uri));
}

function generalizeFile(uri : vscode.Uri): void {
	if (!uri) {
		uri = vscode.window.activeTextEditor.document.uri;
	}
	processRussellFile(uri, "generalize").then(
		(data: any): void => {
			if (data && data.theorems && data.theorems.length > 0) {
				vscode.workspace.openTextDocument({'language': 'russell', 'content': '\n' + data.theorems.join('\n\n')});
			}
		},
		vscode.window.showErrorMessage
	);
}

function generalizeTheorem(): void {
	processRussellTarget("generalize").then(
		(data : any) => {
			if (data && data.theorems && data.theorems.length > 0) {
				vscode.workspace.openTextDocument({'language': 'russell', 'content': '\n' + data.theorems.join('\n\n')});
			}
		},
		vscode.window.showErrorMessage
	);
}
