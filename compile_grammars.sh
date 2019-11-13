#!/bin/bash

pegcompile flowparser=parse/ru_parse_file.flow flowparserast=parse/ru_parse_ast.flow file=parse/russell.lingo
pegcompile flowparser=parse/mm_parse_file.flow flowparserast=parse/mm_parse_ast.flow file=parse/metamath.lingo
