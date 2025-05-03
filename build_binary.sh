#!/bin/bash

cd src

flowc1 \
    cpp3=1 \
    dce-types=1 \
    cpp-compiler=clang++ \
    cpp-mimalloc=1 \
    cpp-build-jobs=24 \
    cpp-verbose=1 \
    server=0 \
    \
    russell
