#!/bin/bash

flowcpp --batch lingo/pegcode/pegcompiler.flow -- flowparser=ru/parse/file.flow flowparserast=ru/parse/ast.flow file=ru/parse/russell.lingo prefix_rules=ru
flowcpp --batch lingo/pegcode/pegcompiler.flow -- flowparser=mm/parse/file.flow flowparserast=mm/parse/ast.flow file=mm/parse/metamath.lingo prefix_rules=mm
