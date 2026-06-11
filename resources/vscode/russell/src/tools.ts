import * as vscode from 'vscode';
import { spawn, ChildProcess, spawnSync } from 'child_process';

function bashLoginCmd(cmd: string, args: string[]): string {
    return [cmd, ...args].join(' ');
}

export function run_cmd(cmd: string, wd: string, args: string[], on_out: (s: string) => void): ChildProcess {
    const options: any = { encoding: 'utf8' };
    if (wd && wd.length > 0) options.cwd = wd;
    let child = spawn('/bin/bash', ['-l', '-c', bashLoginCmd(cmd, args)], options);
    child.stdout.setEncoding('utf8');
    child.stdout.on("data", on_out);
    child.stderr.on("data", on_out);
    return child;
}

export function run_cmd_sync(cmd: string, wd: string, args: string[]) {
    const options: any = { encoding: 'utf8' as BufferEncoding };
    if (wd && wd.length > 0) options.cwd = wd;
    return spawnSync('/bin/bash', ['-l', '-c', bashLoginCmd(cmd, args)], options);
}

// One server per workspace: derive a stable port from the workspace path so different
// projects never share a server (no cross-project contamination, no stray server
// serving the wrong project). Base port from config, offset by a hash of the folder.
export function getWorkspacePort(): number {
    const base = vscode.workspace.getConfiguration("russell").get("portOfHttpServer") as number;
    const dir = getRussellDir();
    let h = 0;
    for (let i = 0; i < dir.length; i++) { h = (((h << 5) - h) + dir.charCodeAt(i)) | 0; }
    return base + (Math.abs(h) % 1000);
}

export function shutdownHttpServer() {
    const port = getWorkspacePort();
    return run_cmd("russellj", ".", ["server-shutdown", "server-port=" + port], (s) => { console.log(s); });
}

export function launchHttpServer(on_start: () => void, on_stop: () => void, serverChannel: vscode.OutputChannel) {
    on_start();
    const russell_dir = getRussellDir();
    const port = getWorkspacePort();
    const memory = vscode.workspace.getConfiguration("russell").get("memForHttpServer");
    serverChannel.appendLine(
        (new Date()).toString() + " Russell Http server is starting in '" + russell_dir + "' directory"
    );
    let httpServer = run_cmd(
        "russellj", russell_dir,
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
    return httpServer;
}

export function getRussellDir(): string {
    const folders = vscode.workspace.workspaceFolders;
    return folders && folders.length > 0 ? folders[0].uri.fsPath : ".";
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
    return Math.round(mem) + " Gb";
}
