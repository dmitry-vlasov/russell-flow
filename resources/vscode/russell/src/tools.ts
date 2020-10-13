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
		{ cwd: wd, shell: true, encoding: 'utf8' } :
        { shell: true, encoding: 'utf8' };
    return spawnSync(cmd, args, options);
}

export function shutdownHttpServer(port : number) {
    return run_cmd("russell", "", ["server-shutdown", "server-port=" + port], (s) => { console.log(s); } );
}

export function launchHttpServer(port : number, on_start : () => void, on_stop : () => void) {
    on_start();
    let httpServer = run_cmd("russell", "", ["server=http", "server-port=" + port], (s) => { console.log(s); });
    httpServer.addListener("close", (code: number, signal: string) => { on_stop() });
    httpServer.addListener("disconnect", () => { on_stop() });
    httpServer.addListener("exit", (code: number, signal: string) => { on_stop() });
    return httpServer
}