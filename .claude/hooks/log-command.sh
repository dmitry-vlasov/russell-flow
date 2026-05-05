#!/usr/bin/env bash
set -euo pipefail
mkdir -p .claude/logs
jq -r '[now | todateiso8601, .tool_input.command] | @tsv' >> .claude/logs/commands.tsv
