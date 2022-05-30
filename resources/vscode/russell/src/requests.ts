'use strict';
import * as vscode from 'vscode';

export function fileCommand(uri : vscode.Uri, target : string, action : string): object {
	return { 
		command : "command", 
		arguments: [
			"read file=" + uri.fsPath + ";\n" +
			"conf verbose=1;\n" + 
			action + " target=" + target + ";"
		] 
	};
}

export function filePositionCommand(action : string): object {
	let uri = vscode.window.activeTextEditor.document.uri;
	let pos = vscode.window.activeTextEditor.selection.active;
	return { 
		command : "command", 
		arguments: [
			"read file=" + uri.fsPath + ";\n" +
			"conf verbose=1;\n" + 
			action + " file=" + uri.fsPath + " line=" + pos.line + " col=" + pos.character + ";"
		] 
	};
}

export function verifyMetamath(uri : vscode.Uri): object {
	let ru_file = uri.fsPath;
	let mm_file = ru_file.substring(0, ru_file.lastIndexOf(".")) + ".mm";
	return { 
		command : "command", 
		arguments: [
			"read-ru   file=" + ru_file + ";\n" +
			"ru-to-mm  file=" + ru_file + ";\n" +
			//"conf-set  verb=2;\n" +
			"write-mm  file=" + mm_file + " monolithic=1 strip-comments=1;\n" +
			//"conf-set  verb=1;\n" +
			"verify-mm file=" + mm_file + ";"
		] 
	};
}
