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
let serverStatusBarItem: vscode.StatusBarItem;
let httpServer : ChildProcess;
let httpServerOnline : boolean = false;
let mathProvider = new MathProvider();

export function activate(context: vscode.ExtensionContext) {	
	serverStatusBarItem = vscode.window.createStatusBarItem(vscode.StatusBarAlignment.Right, 100);
	serverStatusBarItem.command = 'russell.toggleHttpServer';
	context.subscriptions.push(serverStatusBarItem);
	context.subscriptions.push(vscode.commands.registerCommand('russell.verifyFile', (uri) => processRussellFile(uri, "verify")));
	context.subscriptions.push(vscode.commands.registerCommand('russell.verifyTheorem', () => processRussellTheorem("verify")));
	context.subscriptions.push(vscode.commands.registerCommand('russell.reproveFile', (uri) => processRussellFile(uri, "reprove")));
	context.subscriptions.push(vscode.commands.registerCommand('russell.metamathFile', (uri) => verifyMetamath(uri)));
	context.subscriptions.push(vscode.commands.registerCommand('russell.reproveTheorem', () => processRussellTheorem("reprove")));
	context.subscriptions.push(vscode.commands.registerCommand('russell.generalizeFile', (uri) => processRussellFile(uri, "generalize")));
	context.subscriptions.push(vscode.commands.registerCommand('russell.generalizeTheorem', () => processRussellTheorem("generalize")));
	context.subscriptions.push(vscode.commands.registerCommand('russell.startHttpServer', startHttpServer));
	context.subscriptions.push(vscode.commands.registerCommand('russell.stopHttpServer', stopHttpServer));
	context.subscriptions.push(vscode.commands.registerCommand('russell.restartLspServer', restartLspServer));
	context.subscriptions.push(vscode.commands.registerCommand('russell.toggleHttpServer', toggleHttpServer));
	context.subscriptions.push(vscode.commands.registerCommand('russell.findSymbol', findSymbol));
	context.subscriptions.push(vscode.commands.registerCommand('russell.execCommand', execCommand));
	context.subscriptions.push(vscode.commands.registerCommand('russell.gotoLocation', gotoLocation));
	context.subscriptions.push(vscode.commands.registerCommand('russell.refreshMath', mathInfo));

    russellChannel = vscode.window.createOutputChannel("Russell");
	russellChannel.show(true);

	vscode.workspace.onDidOpenTextDocument(mathInfo);

	checkHttpServerStatus(true);
	setInterval(checkHttpServerStatus, 3000, false);
	serverStatusBarItem.show();
	startLspServer();
}

function mathInfo(): void {
	client.sendRequest("workspace/executeCommand", { command: "math-info", arguments: [] }).then(
		(data : MathEntity[]) => mathProvider.update(data)
	);
}

function execCommand() {
	russellChannel.show(true);
	let options: vscode.InputBoxOptions = { prompt: "Command and args: ", placeHolder: "" };
	vscode.window.showInputBox(options).then(value => {
		let val_arr = value.split(" ");
		if (val_arr.length > 0) {
			let file_arg = Array("file=" + vscode.window.activeTextEditor.document.uri.fsPath);
			let args = file_arg.concat(val_arr);
			client.sendRequest("workspace/executeCommand", { command : "command", arguments: args }).then(
				(out : string) => {
					if (out != "null") {
						russellChannel.appendLine(out);
					}
				},
				(err : any) => {
					vscode.window.showErrorMessage(`command ${value} failed: ${err}`);
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
		() => client.onNotification("console/message", (msg : string) => russellChannel.appendLine(msg))
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

function checkHttpServerStatus(initial : boolean) {
	const port = vscode.workspace.getConfiguration("russell").get("portOfHttpServer");
	isPortReachable(port, {host: 'localhost'}).then(
		(reacheable : boolean) => {
			if (reacheable) {
				showHttpServerOnline();
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
		httpServer = tools.launchHttpServer(port, showHttpServerOnline, showHttpServerOffline);
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

function showHttpServerOnline() {
	serverStatusBarItem.text = `$(vm-active) russell http server: online`;
}

function showHttpServerOffline() {
	serverStatusBarItem.text = `$(vm-outline) russell: http server: offline`;
}

// this method is called when your extension is deactivated
export function deactivate() {
	// First, shutdown Russellc server, if it is owned by current vscode instance
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
			"file=" + uri.fsPath, "read",  ";",
			action, "target=" + target
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
			"file=" + ru_file, "read-ru",  ";",
			"ru-to-mm", "file=" + ru_file, ";",
			"write-mm", "file=" + mm_file, "monolithic=1", "strip-comments=1", ";",
			"verify-mm", "file=" + mm_file
		] 
	});
}
