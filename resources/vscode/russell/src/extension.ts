'use strict';
// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import * as vscode from 'vscode';
import * as path from 'path';
import { ChildProcess } from 'child_process';
import * as fs from "fs";
import * as PropertiesReader from 'properties-reader';
import {
    LanguageClient, LanguageClientOptions, ServerOptions, RevealOutputChannelOn, Location,
} from 'vscode-languageclient';
import * as tools from "./tools";

const isPortReachable = require('is-port-reachable');

let client: LanguageClient = null;
let russellChannel : vscode.OutputChannel = null;
let serverStatusBarItem: vscode.StatusBarItem;
let httpServer : ChildProcess;
let httpServerOnline : boolean = false;

export function activate(context: vscode.ExtensionContext) {	
	serverStatusBarItem = vscode.window.createStatusBarItem(vscode.StatusBarAlignment.Right, 100);
	serverStatusBarItem.command = 'russell.toggleHttpServer';
    context.subscriptions.push(serverStatusBarItem);
    context.subscriptions.push(vscode.commands.registerCommand('russell.verify', verifyRussell));
    context.subscriptions.push(vscode.commands.registerCommand('russell.metamath', verifyMetamath));
    context.subscriptions.push(vscode.commands.registerCommand('russell.startHttpServer', startHttpServer));
	context.subscriptions.push(vscode.commands.registerCommand('russell.stopHttpServer', stopHttpServer));
	context.subscriptions.push(vscode.commands.registerCommand('russell.restartLspServer', restartLspServer));
	context.subscriptions.push(vscode.commands.registerCommand('russell.toggleHttpServer', toggleHttpServer));
	context.subscriptions.push(vscode.commands.registerCommand('russell.cacheInfo', cacheInfo));
	context.subscriptions.push(vscode.commands.registerCommand('russell.declInfo', declInfo));
	context.subscriptions.push(vscode.commands.registerCommand('russell.findSymbol', findSymbol));

    russellChannel = vscode.window.createOutputChannel("Russell");
	russellChannel.show();

	checkHttpServerStatus(true);
	setInterval(checkHttpServerStatus, 3000, false);
	serverStatusBarItem.show();
	startLspServer();
}

function toUTF8(text : string): string {
    /*var utf8Text = text;
    try {
        // Try to convert to utf-8
        utf8Text = decodeURIComponent(escape(text));
        // If the conversion succeeds, text is not utf-8
    } catch(e) {
        // console.log(e.message); // URI malformed
        // This exception means text is utf-8
    }   
	return utf8Text; // returned text is always utf-8*/
	
	return escape(text);
}

function cacheInfo() {
	let options: vscode.InputBoxOptions = { prompt: "Full: ", placeHolder: "" };
	vscode.window.showInputBox(options).then(value => {
		client.sendRequest("workspace/executeCommand", 
			value ? { command : "cache-info", arguments: [value] } : { command : "cache-info" }
		).then((out : string) => russellChannel.append(out));
	});
}

function declInfo() {
	let options: vscode.InputBoxOptions = { prompt: "Args: (consts, types, rules, axioms, defs, all) ", placeHolder: "" };
	vscode.window.showInputBox(options).then(value => {
		client.sendRequest("workspace/executeCommand", 
			value ? { command : "decl-info", arguments: [value] } : { command : "decl-info" }
		).then((out : string) => russellChannel.append(toUTF8(out)));
	});
}

function findSymbol() {
	let options: vscode.InputBoxOptions = { prompt: "Symbol: ", placeHolder: "" };
	vscode.window.showInputBox(options).then(value => {
		if (value) {
			client.sendRequest("workspace/executeCommand", { command : "find-symbol", arguments: [value] }).
			then((location : vscode.Location) => {
					let uri = vscode.Uri.parse(location.uri.toString());
					vscode.workspace.openTextDocument(uri).then((document) =>
						vscode.window.showTextDocument(document).then((edit) =>
							edit.revealRange(location.range)
						)
					);
			});
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
}

function restartLspServer() {
	if (client) {
		client.sendNotification("exit");
        client.stop().then(startLspServer);
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
        return client.stop()
    }
}

const homedir = process.env[(process.platform == "win32") ? "USERPROFILE" : "HOME"];

function expandHomeDir(p : string) : string {
	if (!p) return p;
	if (p == "~") return homedir;
	if (p.slice(0, 2) != "~/") return p;
	return path.join(homedir, p.slice(2));
}

function getPath(uri : string | vscode.Uri) : string {
	return expandHomeDir(uri instanceof vscode.Uri ? uri.fsPath : uri.startsWith("file://") ? vscode.Uri.parse(uri).fsPath : uri);
}

function resolveProjectRoot(uri : string | vscode.Uri) : string {
	const config = vscode.workspace.getConfiguration("russell");
	if (uri != null) {
		let dir = getPath(uri);
		while (dir != path.resolve(dir, "..")) {
			dir = path.resolve(dir, "..");
			if (fs.existsSync(path.resolve(dir, "russell.conf"))) {
				return dir;
			}
		}
	}
	return getPath(config.get("root"));
}

function verifyRussell() {
	let document = vscode.window.activeTextEditor.document;
	russellChannel.clear();
	russellChannel.show(true);
	//russellChannel.appendLine("Verifying file '" + document.uri + "'");
	client.sendRequest("workspace/executeCommand", { command : "verify", arguments: [document.uri.fsPath] }).
	then((out : string) => russellChannel.append(out));
}

function verifyMetamath() {
    verifyRussell();
}
