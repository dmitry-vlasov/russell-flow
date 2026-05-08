# Russell VSCode Extension

The Russell VSCode extension provides language support for `.ru` (Russell), `.rus` (Russell script), and `.mm` (Metamath) files. It communicates with the Russell backend via two channels: an **LSP server** for editor intelligence, and an optional **HTTP server** for heavier operations (verify, reprove, generalize).

The extension lives in [resources/vscode/russell/](../resources/vscode/russell/).

---

## Building and Installing

**Prerequisites:** Node.js, npm, and `vsce` (`npm install -g @vscode/vsce`).

```bash
cd resources/vscode/russell
npm install
npm run compile          # or: npx tsc -p .
bash build.sh            # produces ../russell.vsix
```

Install into VSCode:

```bash
code --install-extension resources/vscode/russell.vsix
```

Or via the UI: Extensions sidebar → `···` → *Install from VSIX…*

---

## Configuration Settings

| Setting | Default | Description |
|---|---|---|
| `russell.autostartHttpServer` | `true` | Start the HTTP server automatically on activation |
| `russell.portOfHttpServer` | `20001` | Port for the HTTP server |
| `russell.memForHttpServer` | `7` | Memory (GB) for the HTTP server |
| `russell.trace.server` | `off` | LSP traffic tracing: `off`, `messages`, `verbose` |

---

## Language Support

Three language IDs are registered:

| ID | Extension | Description |
|---|---|---|
| `russell` | `.ru` | Russell proof language |
| `russell-script` | `.rus` | Russell scripting language |
| `metamath` | `.mm` | Metamath format |

Each has a grammar file under [syntaxes/](../resources/vscode/russell/syntaxes/) and a language configuration (bracket matching, comment toggling, etc.) in the root of the extension directory.

---

## Commands

Commands are accessible from the command palette (`Ctrl+Shift+P`), the editor right-click menu (for `.ru` files), or via keyboard shortcuts.

| Command | Shortcut | Description |
|---|---|---|
| `russell.saveVerifyFile` | F7 | Save and verify the current file |
| `russell.verifyFile` | — | Verify a file (from explorer context) |
| `russell.reproveFile` | — | Re-prove all theorems in a file using the oracle prover |
| `russell.metamathFile` | F8 | Translate `.ru` → `.mm` and verify with Metamath |
| `russell.verifyTheorem` | — | Verify the theorem at cursor |
| `russell.reproveTheorem` | — | Re-prove the theorem at cursor |
| `russell.generalizeTheorem` | — | Generalize the theorem at cursor |
| `russell.findSymbol` | — | Search for a symbol by name across the math base |
| `russell.execCommand` | — | Run an arbitrary Russell script command |
| `russell.startHttpServer` | — | Start the background HTTP server |
| `russell.stopHttpServer` | — | Stop the HTTP server |
| `russell.restartLspServer` | — | Restart the LSP server |
| `russell.refreshMath` | — | Refresh the Mathematics Explorer tree views |
| `russell.refreshOntology` | — | Refresh the Notions (ontology) tree view |

---

## Mathematics Explorer

The extension contributes a **Mathematics Explorer** sidebar panel (view container `russell-math-container`) with the following tree views, populated automatically when the LSP server is ready:

| View ID | Name | Contents |
|---|---|---|
| `math-axioms` | Axioms | All axioms in the loaded math base |
| `math-defs` | Definitions | All definitions |
| `math-types` | Types | Type declarations |
| `math-rules` | Grammar | Syntax rules |
| `math-consts` | Constants | Constants/symbols |
| `math-ontology` | Notions | Ontology of mathematical notions (see below) |

All views are collapsed by default and have a **refresh** button in the title bar.

Clicking any entry in Axioms/Definitions/Types/Grammar/Constants opens the corresponding source file at the declaration location.

### Notions (Ontology)

The **Notions** view shows the ontology of mathematical notions derived from the loaded Russell sources. Each node represents a syntax rule (a grammatical construct), linked to its definition if one exists. The hierarchy reflects the parent–child relationships between constructs (a notion is a parent of another if its rule appears in the other's definition).

Hovering over a node shows:
- `Rule:` — the grammar rule string
- `Def:` — the definitional proposition (if a definition exists for this notion)

The ontology is built server-side by `ruMakeOntology` ([src/ru/util/ontology.flow](../src/ru/util/ontology.flow)) and returned as JSON via the `math-ontology` LSP command.

---

## LSP Server

The LSP server is the primary intelligence backend. It is launched automatically when VSCode opens a `.ru` file.

### Launch

On Linux, the server is started via a bash login shell so that `PATH` and environment variables from `~/.bashrc` / `~/.bash_profile` are available:

```
/bin/bash -l -c russell_lsp
```

`russell_lsp` is a wrapper script (typically on `PATH` after building the project) that runs:

```
bin/russellj server=lsp
```

The LSP server reads `russell.conf` by walking up from the working directory — the workspace root must contain or be inside a project tree with a `russell.conf`.

### LSP Methods

#### Lifecycle

| Method | Description |
|---|---|
| `initialize` | Negotiate capabilities; server advertises the capabilities below |
| `initialized` | Optionally registers dynamic text document change handlers |
| `shutdown` / `exit` | Clear caches and terminate |

Advertised server capabilities:

- `declarationProvider`, `definitionProvider`, `referencesProvider`
- `documentSymbolProvider`
- `hoverProvider`
- `renameProvider`
- `workspaceSymbolProvider`
- `completionProvider` (trigger: `\`)
- `executeCommandProvider` — commands: `verify`, `math-info`, `find-symbol`, `command`
- `textDocumentSync` — open/close + incremental changes + save notifications

#### Text Document

| Method | Triggered by | Action |
|---|---|---|
| `textDocument/didOpen` | Opening a `.ru` file | Load all caches, sync file text into server |
| `textDocument/didChange` | Editing | Sync updated text |
| `textDocument/didSave` | Save | Re-read and verify the file |
| `textDocument/hover` | Mouse hover | Return formatted statement for the name under cursor |
| `textDocument/definition` | Go to definition (F12) | Return source location of declaration |
| `textDocument/declaration` | Go to declaration | Return source location |
| `textDocument/references` | Find all references | Return all use sites |
| `textDocument/documentSymbol` | Outline view | Return all declarations in file |
| `textDocument/rename` | Rename symbol | Rename across the project |
| `textDocument/completion` | Typing `\` | Complete symbol names |

**Hover detail:** When the cursor is on an assertion name (theorem, axiom, definition) or a syntax constructor in a formula, the hover popup shows the full statement — hypotheses, proposition, and associated comments. For syntax constructors, the definition axiom linked to the construct is shown, including its defiendum, definiens, and definitional proposition.

#### Workspace

| Method | Description |
|---|---|
| `workspace/symbol` | Symbol search by query string |
| `workspace/executeCommand` | Dispatch one of the registered commands (see below) |

**Execute commands** dispatched by `workspace/executeCommand`:

| Command | LSP args | Server task | Description |
|---|---|---|---|
| `verify` | `file=…`, target | `verify` | Verify a single file or theorem |
| `math-info` | optional kind | `info-math` | Fetch math entity list for the Explorer |
| `math-ontology` | — | `info-ontology` | Fetch ontology for the Notions view |
| `find-symbol` | name string | `info-find` | Find a symbol by name |
| `command` | script string | script parser | Run an arbitrary Russell script |

### On Startup

After the LSP client connects, the extension:

1. Sends `cache-load` (as a `command`) to warm the server cache from disk
2. Sends `math-info` to populate the Mathematics Explorer tree views
3. Sends `math-ontology` to populate the Notions view

---

## HTTP Server

For operations that are too slow to run synchronously in the LSP pipeline (verify, reprove, generalize on large files), the extension can offload work to a persistent **HTTP server** running as a background process.

The HTTP server is `russellj mem=Ng server=http server-port=PORT`, started in the workspace root directory. It listens on the configured port (default 20001) and accepts the same Russell script commands as the console/LSP server.

The extension polls the HTTP server status every 3 seconds and shows a status bar item indicating whether it is online. The item is clickable to toggle the server on/off.

Memory and port are configurable via `russell.memForHttpServer` and `russell.portOfHttpServer`.

---

## Source Files

| File | Purpose |
|---|---|
| [src/extension.ts](../resources/vscode/russell/src/extension.ts) | Entry point — registers commands, starts LSP client and HTTP server |
| [src/math.ts](../resources/vscode/russell/src/math.ts) | `MathProvider` / `OntologyProvider` tree data providers |
| [src/prover.ts](../resources/vscode/russell/src/prover.ts) | Interactive prover panel tree provider |
| [src/tools.ts](../resources/vscode/russell/src/tools.ts) | Process launch helpers (`run_cmd`, HTTP server management) |
| [src/requests.ts](../resources/vscode/russell/src/requests.ts) | Helper functions for composing LSP command payloads |

### Server-side counterparts

| File | Purpose |
|---|---|
| [src/lsp/methods/lifecycle.flow](../src/lsp/methods/lifecycle.flow) | `initialize`, `initialized`, `shutdown`, capability negotiation |
| [src/lsp/methods/textDocument.flow](../src/lsp/methods/textDocument.flow) | All `textDocument/*` handlers |
| [src/lsp/methods/workspace.flow](../src/lsp/methods/workspace.flow) | `workspace/symbol`, `workspace/executeCommand` dispatch |
| [src/ru/info.flow](../src/ru/info.flow) | `info-math`, `info-ontology`, `info-symbol`, `info-find` implementations |
| [src/ru/util/ontology.flow](../src/ru/util/ontology.flow) | `ruMakeOntology`, `ruOntology2json` |
