#!/usr/bin/env python3
"""
PreToolUse hook for Bash: auto-approves the specific "write a commit
message via a quoted heredoc, then git add/commit/log" workflow, without
relying on Claude Code's built-in permission matcher (which splits Bash
commands on every newline -- including newlines inside a heredoc body --
so free-text commit messages can never satisfy a static allow rule).

Safety properties this script tries to hold:
  - Only a heredoc of the exact shape  cat > PATH <<'DELIM' ... DELIM
    (delimiter QUOTED, so the shell does not expand $(...) / `...` inside
    the body) is collapsed away. An unquoted delimiter, or any heredoc not
    immediately preceded by "cat >"/"cat >>", is left alone.
  - Every "<<" in the raw command must be accounted for by a recognized
    cat-heredoc block, or the whole command is left to the normal
    permission flow.
  - After collapsing recognized heredoc bodies, the remainder is split on
    shell operators (&&, ||, ;, |, newline) and every resulting fragment
    must match a short allow-list of safe prefixes (cat/git add/git
    commit/git log/tail). Fragments never see the original message text,
    so message content cannot influence the decision either way.
  - A blacklist scan for command substitution / dangerous flags on the
    collapsed command is an extra guard, applied even though the allow-
    list above already excludes those characters.
  - Any failure to positively confirm safety silently falls through
    (prints nothing, exits 0) -- Claude Code's normal permission flow
    (other rules, then interactive prompt) still applies.
"""
import json
import re
import sys

HEREDOC_BLOCK = re.compile(
    r"cat\s+>>?\s+([^\s;&|`$()<>]+)\s*<<-?(['\"])(\w+)\2\s*\n.*?\n\3\b",
    re.DOTALL,
)

SAFE_TOKEN = re.compile(
    r"^(?:"
    r"cat\s+>>?\s+[^\s;|&`$()<>]+"
    r"|git\s+add\s+[^;|&`$()]+"
    r"|git\s+commit\s+(?:-q\s+)?(?:-F\s+[^\s;|&`$()]+|-m\s+[^;|&`$()]+)"
    r"|git\s+log\s+[^;|&`$()]*"
    r"|tail\s+[^;|&`$()]*"
    r")$"
)

BLACKLIST = re.compile(
    r"\$\(|`|\brm\b|\bsudo\b|\bcurl\b|\bwget\b|--force|\bpush\b|\breset\b|"
    r"\bcheckout\b|\|\s*sh\b|\|\s*bash\b|\bexec\b|\beval\b"
)


def main():
    try:
        data = json.load(sys.stdin)
    except (json.JSONDecodeError, ValueError):
        return
    if data.get("tool_name") != "Bash":
        return
    cmd = data.get("tool_input", {}).get("command", "")
    if not cmd or "<<" not in cmd:
        return

    matches = list(HEREDOC_BLOCK.finditer(cmd))
    if cmd.count("<<") != len(matches):
        return  # some heredoc wasn't a recognized safe cat-block

    collapsed = cmd
    for m in reversed(matches):
        collapsed = collapsed[: m.start()] + f"cat > {m.group(1)}" + collapsed[m.end():]

    if BLACKLIST.search(collapsed):
        return

    parts = re.split(r"&&|\|\||;|\||\n", collapsed)
    parts = [p.strip() for p in parts if p.strip()]
    if not parts or not all(SAFE_TOKEN.match(p) for p in parts):
        return

    print(json.dumps({
        "hookSpecificOutput": {
            "hookEventName": "PreToolUse",
            "permissionDecision": "allow",
            "permissionDecisionReason": "recognized git-commit-via-heredoc workflow",
        }
    }))


if __name__ == "__main__":
    main()
