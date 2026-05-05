---
description: Ask Codex and Gemini for a second opinion on a stuck problem
argument-hint: "brief description of the problem"
---

Run the bot council script with the current task context:

!bash .claude/commands/council.sh "$ARGUMENTS"

Then read both outputs, compare approaches, and propose a final synthesis.
Focus on what each model sees that the others miss.
