---
name: add-command
description: Add a new Russell command or task to the system (RuComm registration pattern)
---

# Adding a New Russell Command

## The pattern
Every command is a `RuComm` value: metadata (`RuCommInfo`) + a run function.
Defined in [src/comm.flow](src/comm.flow):

```flow
RuComm(
    RuCommInfo(
        "my-command",       // name used in scripts and CLI
        "category",         // group for help output: "io", "prover", "translate", "conf", "misc", "info", "stats"
        "what it does",     // one-line help string
        "main-arg",         // name of the default positional arg (or "" if none)
        true,               // autofile: auto-resolve afile from conf
        false,              // autoindent: indent nested output
        [
            RuCommArg("main-arg", true, "description", "default-value"),
            RuCommArg("opt-arg",  true, "description", ""),
        ],
        []                  // return values (RuCommRet), usually []
    ),
    \unit -> {
        // unit.state  — current RuState (theorem base, conf)
        // unit.task   — parsed task with args
        // unit.out    — output handle (use unit.out.println)
        val = ruTaskStringArg(unit.task, "main-arg", "default");
        // ... do work ...
        Some(unit.state)    // return None() to signal failure
    }
);
```

## Step 1 — Implement and declare the command
Create a new `.flow` file or add to an existing one in the relevant subsystem:
- Prover commands → `src/ru/prover/comms.flow`
- MM commands → `src/mm/comms.flow`
- Base/misc → `src/base/comms.flow`
- Stats → `src/base/stats.flow`

Export the `RuComm` value.

## Step 2 — Register in the command tree
Open [src/process.flow](src/process.flow) and find `ruCommandsTree()`.
Add the new command to the appropriate list inside `commands_list`:

```flow
commands_list = [
    [...existing top-level commands...],
    ruBaseCommands,
    ruClearCommands,
    ruCommands(ruProcessScript),   // ← most ru/ commands live here
    mmCommands,                    // ← mm/ commands live here
    [myNewCommand],                // ← add a new list, or append to existing
];
```

Alternatively, add it to an existing array like `ruBaseCommands` in `src/base/comms.flow`
or to `ruCommands` in `src/ru/comms.flow`.

## Step 3 — Access task args in the run function
```flow
ruTaskStringArg(unit.task, "arg-name", "default")   // string arg
ruTaskIntArg(unit.task, "arg-name", 0)               // int arg
ruTaskArgs2Tree(unit.task)                            // all args as Tree<string,string>
```

## Step 4 — Build and test
```bash
./build_java.sh
bin/russellj my-command arg=value
bin/russellj help                  # verify it appears in help output
```

## Checklist
- [ ] `RuCommInfo` name is lowercase with hyphens (matches CLI usage)
- [ ] Run function returns `Some(unit.state)` on success, `None()` on failure
- [ ] Added to `ruCommandsTree()` in `process.flow`
- [ ] Appears in `bin/russellj help` output after rebuild
