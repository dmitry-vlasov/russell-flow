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

const isPortReachable = require('is-port-reachable');

let client: LanguageClient = null;
let russellChannel: vscode.OutputChannel = null;
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
	const reg_comm = (name: string, fn: any) => vscode.commands.registerCommand(name, fn);
	context.subscriptions.push(
		serverStatusBarItem,
		reg_comm('russell.verifyFile', (uri) => processRussellFile(uri, "verify")),
		reg_comm('russell.verifyTheorem', () => processRussellTarget("verify")),
		reg_comm('russell.reproveFile', (uri) => processRussellFile(uri, "reprove-oracle")),
		reg_comm('russell.metamathFile', (uri) => verifyMetamath(uri)),
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
	);
	checkHttpServerStatus(true);
	setInterval(checkHttpServerStatus, 3000, false);

	startLspClient();
	serverStatusBarItem.show();
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
		command: process.platform == "win32" ? 'russell.bat' : 'russell',
		args: ['server=lsp'],
		options:  { detached: false }
	}
	// Options to control the language client
	let clientOptions: LanguageClientOptions = {
		// Register the server for plain text documents
		documentSelector: [{scheme: 'file', language: 'russell'}],
		outputChannel: russellChannel,
		revealOutputChannelOn: RevealOutputChannelOn.Info,
		uriConverters: {
			// FIXME: by default the URI sent over the protocol will be percent encoded (see rfc3986#section-2.1)
			//        the "workaround" below disables temporarily the encoding until decoding
			//        is implemented properly in clangd
			code2Protocol: (uri: vscode.Uri) : string => uri.toString(true),
			protocol2Code: (uri: string) : vscode.Uri => vscode.Uri.parse(uri)
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

function checkHttpServerStatus(initial: boolean) {
	const port = vscode.workspace.getConfiguration("russell").get("portOfHttpServer");
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
export function deactivate() {
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
		client.sendRequest("shutdown").then(
			(data : any) => {
				client.sendNotification("exit");
				client.stop();
			},
			(err : any) => {
				vscode.window.showErrorMessage("error while shutting down: " + err);
			}
		);
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
	russellChannel.show(true);
	return client.sendRequest("workspace/executeCommand", requests.fileCommand(uri, target, action));
}

function verifyMetamath(uri : vscode.Uri): void {
	russellChannel.show(true);
	let ru_file = uri.fsPath;
	let mm_file = ru_file.substr(0, ru_file.lastIndexOf(".")) + ".mm";
	client.sendRequest("workspace/executeCommand", verifyMetamath(uri));
}


function generalizeFile(uri : vscode.Uri): void {
	processRussellFile(uri, "generalize").then(
		(data: any) => {
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
