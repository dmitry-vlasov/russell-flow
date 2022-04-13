import * as vscode from 'vscode';
import * as fs from 'fs';
import { spawn, ChildProcess, spawnSync } from 'child_process';

export function run_cmd(cmd: string, wd: string, args: string[], on_out: (string) => void): ChildProcess {
    const options = wd && wd.length > 0 ? { cwd: wd, shell: true, encoding: 'utf8'} : { shell : true, encoding: 'utf8'};
    let child = spawn(cmd, args, options);
    child.stdout.setEncoding('utf8');
    child.stdout.on("data", on_out);
    child.stderr.on("data", on_out);
    //child.on("close", (code, msg) => on_exit(code, msg));
    return child;
}

export function run_cmd_sync(cmd: string, wd: string, args: string[]) {
	const options = wd && wd.length > 0 ? 
		{ cwd: wd, shell: true, encoding: "utf8" as BufferEncoding } :
        { shell: true, encoding: "utf8" as BufferEncoding };
    return spawnSync(cmd, args, options);
}

export function shutdownHttpServer() {
	const port = vscode.workspace.getConfiguration("russell").get("portOfHttpServer");
    return run_cmd("russell", ".", ["server-shutdown", "server-port=" + port], (s) => { console.log(s); } );
}

export function launchHttpServer(on_start : () => void, on_stop : () => void, serverChannel : vscode.OutputChannel) {
	on_start();
	const russell_dir = getRussellDir();
	const port = vscode.workspace.getConfiguration("russell").get("portOfHttpServer");
	const memory = vscode.workspace.getConfiguration("russell").get("memForHttpServer");
	serverChannel.appendLine(
		(new Date()).toString() + " Russell Http server is starting in '" + russell_dir + "' directory"
	); 
    let httpServer = run_cmd(
		"russell", russell_dir, 
		["mem=" + memory + "g", "server=http", "server-port=" + port], 
		(s) => serverChannel.append(s)
	);
    httpServer.addListener("close", (code: number, signal: string) => { 
		serverChannel.appendLine(
			(new Date()).toString() + " Russell Http server closed" + 
			(code == 0 ? "" : " code: " + code) + 
			(signal ? " signal: " + signal : "")
		);
		on_stop();
	});
    httpServer.addListener("disconnect", () => { 
		serverChannel.appendLine((new Date()).toString() + " Russell Http server disconnected"); 
		on_stop();
	});
	httpServer.addListener("exit", (code: number, signal: string) => { 
		serverChannel.appendLine(
			(new Date()).toString() + " Russell Http server exited" +
			(code == 0 ? "" : " code: " + code) + 
			(signal ? " signal: " + signal : "")
		); 
		on_stop();
	});
    return httpServer
}

export function getRussellDir(): string {
	return run_cmd_sync("russell", ".", ["russell-dir"]).stdout.toString().trim();
}

export function roundTo2Digits(num: number): number {
	return Math.round((num + Number.EPSILON) * 100) / 100;
}

export function num2memory(mem: number): string {
	if (mem < 1024) {
		return Math.round(mem) + " b";
	}
	mem /= 1024;
	if (mem < 1024) {
		return Math.round(mem) + " Kb";
	}
	mem /= 1024;
	if (mem < 1024) {
		return Math.round(mem) + " Mb";
	}
	mem /= 1024;
	if (mem < 1024) {
		return Math.round(mem) + " Gb";
	}
}

// Log file for low-level debugging
const hard_log_file = '/home/dmitry/dev/russell-flow/log/fuck.txt';

export function hardlog(msg : string): void {
	const log = fs.readFileSync(hard_log_file,'utf8');
	const new_log = log + "\n" + msg;
	fs.writeFileSync(hard_log_file, new_log);
}
