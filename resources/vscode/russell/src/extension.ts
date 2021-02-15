'use strict';
// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import { ChildProcess } from 'child_process';
import * as vscode from 'vscode';
import { LanguageClient, LanguageClientOptions, RevealOutputChannelOn, ServerOptions, CancellationToken, Event, CancellationTokenSource } from 'vscode-languageclient';
import { MathEntity, MathProvider } from "./mathProvider";
import * as tools from "./tools";

const isPortReachable = require('is-port-reachable');

let client: LanguageClient = null;
let russellChannel : vscode.OutputChannel = null;
let serverChannel : vscode.OutputChannel = null;
let serverStatusBarItem: vscode.StatusBarItem;
let httpServer : ChildProcess;
let httpServerOnline : boolean = false;
let mathProvider = new MathProvider();

export function activate(context: vscode.ExtensionContext) {	
	serverStatusBarItem = vscode.window.createStatusBarItem(vscode.StatusBarAlignment.Right, 100);
	serverStatusBarItem.command = 'russell.toggleHttpServer';
	const reg_comm = (name : string, fn : any) => vscode.commands.registerCommand(name, fn);
	context.subscriptions.push(
		serverStatusBarItem,
		reg_comm('russell.verifyFile', (uri) => processRussellFile(uri, "verify")),
		reg_comm('russell.verifyTheorem', () => processRussellTheorem("verify")),
		reg_comm('russell.reproveFile', (uri) => processRussellFile(uri, "reprove")),
		reg_comm('russell.metamathFile', (uri) => verifyMetamath(uri)),
		reg_comm('russell.reproveTheorem', () => processRussellTheorem("reprove")),
		reg_comm('russell.generalizeFile', (uri) => processRussellFile(uri, "generalize")),
		reg_comm('russell.generalizeTheorem', () => processRussellTheorem("generalize")),
		reg_comm('russell.startHttpServer', startHttpServer),
		reg_comm('russell.stopHttpServer', stopHttpServer),
		reg_comm('russell.restartLspServer', restartLspServer),
		reg_comm('russell.toggleHttpServer', toggleHttpServer),
		reg_comm('russell.findSymbol', findSymbol),
		reg_comm('russell.execCommand', execCommand),
		reg_comm('russell.gotoLocation', gotoLocation),
		reg_comm('russell.refreshMath', mathInfo)
	);

	russellChannel = vscode.window.createOutputChannel("Russell output");
	serverChannel = vscode.window.createOutputChannel("Russell server");
	russellChannel.show(true);
	setInterval(checkHttpServerStatus, 3000, false);
	serverStatusBarItem.show();

	// launch russell server at startup
	if (vscode.workspace.getConfiguration("russell").get("autostartHttpServer")) {
		initHttpServer();
	} else {
		startLspServer();
	}
}

function mathInfo(): void {
	client.sendRequest("workspace/executeCommand", { command: "math-info", arguments: [] }).then(
		(data : MathEntity[]) => mathProvider.update(data)
	);
}

function removeAllSpaces(str : string) : string { 
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

function startLspServer() {
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
			client.onNotification("console/message", (msg : string) => russellChannel.appendLine(msg));
			client.sendRequest("workspace/executeCommand", { command : "command", arguments: ["cache-load"] }).then(
				mathInfo,
				(err : any) => {
					vscode.window.showErrorMessage(`command 'cache-load' failed: ${err}`);
				}
			);
		}
	);
}

function restartLspServer() {
	if (client) {
        client.stop().then(
			startLspServer,
			(reason) => { vscode.window.showInformationMessage(`Restart of LSP server failed: ${reason}.`) }
		);
	} else {
		startLspServer();
	}
}

function initHttpServer() {
	const port = vscode.workspace.getConfiguration("russell").get("portOfHttpServer");
	isPortReachable(port, {host: 'localhost'}).then(
		(reacheable : boolean) => {
			if (reacheable) {
				outputHttpServerMemStats();
				httpServerOnline = true;
			} else {
				showHttpServerIsLaunching();
				startHttpServer();
			}
			startLspServer();
		}
	);
}

function checkHttpServerStatus() {
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
			}
		}
	);
}

function outputHttpServerMemStats() {
	client.sendRequest("workspace/executeCommand", { 
		command : "command", 
		arguments : ["stats-mem show=1 do_not_log_this=1"]
	}).then(
		(out : string) => {
			const lines = out.split("\n");
			const mem_stats = lines.find((line) => line.indexOf("free") != -1);
			showHttpServerOnline(mem_stats);
		},
		(err : any) => {
			russellChannel.appendLine(err);
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
		const port : number = vscode.workspace.getConfiguration("russell").get("portOfHttpServer");
		httpServer = tools.launchHttpServer(port, showHttpServerOnline, showHttpServerOffline, serverChannel);
		httpServerOnline = true;
    }
}

function stopHttpServer() {
	if (httpServerOnline) {
		const port : number = vscode.workspace.getConfiguration("russell").get("portOfHttpServer");
		tools.shutdownHttpServer(port).on("exit", (code, msg) => httpServer = null);
		httpServerOnline = false;
	}
}

function showHttpServerOnline(mem_stats? : string) {
	if (mem_stats) {
		serverStatusBarItem.text = `$(vm-active) russell http server: online (` + mem_stats + ")";
	} else {
		serverStatusBarItem.text = `$(vm-active) russell http server: online`;
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
		const port : number = vscode.workspace.getConfiguration("russell").get("portOfHttpServer");
		tools.shutdownHttpServer(port).on("exit", (code, msg) => httpServer = null);
	}
    if (!client) {
        return undefined;
    } else {
		client.sendNotification("exit");
        return client.stop();
    }
}

function processRussellFile(uri : vscode.Uri, action : string): void {
	if (!uri) {
		uri = vscode.window.activeTextEditor.document.uri;
	}
	processRussell(uri, uri.fsPath, action);
}

function processRussellTheorem(action : string): void {
	let uri = vscode.window.activeTextEditor.document.uri;
	let pos = vscode.window.activeTextEditor.selection.active;
	if (pos instanceof vscode.Position) {
		let range = vscode.window.activeTextEditor.document.getWordRangeAtPosition(pos);
		let target = vscode.window.activeTextEditor.document.getText(range);
		processRussell(uri, target, action);
	}
}

function processRussell(uri : vscode.Uri, target : string, action : string): void {
	russellChannel.show(true);
	client.sendRequest("workspace/executeCommand", { 
		command : "command", 
		arguments: [
			"read file=" + uri.fsPath + ";\n" +
			"conf verbose=1;\n" + 
			action + " target=" + target + ";"
		] 
	});
}

function verifyMetamath(uri : vscode.Uri): void {
	russellChannel.show(true);
	let ru_file = uri.fsPath;
	let mm_file = ru_file.substr(0, ru_file.lastIndexOf(".")) + ".mm";
	client.sendRequest("workspace/executeCommand", { 
		command : "command", 
		arguments: [
			"read-ru   file=" + ru_file + ";\n" +
			"ru-to-mm  file=" + ru_file + ";\n" +
			"write-mm  target=" + mm_file + " monolithic=1 strip-comments=1;\n" +
			"verify-mm file=" + mm_file + ";"
		] 
	});
}
