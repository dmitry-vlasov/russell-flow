#!/bin/bash

git rev-parse --short HEAD > russell.commit

cd src

flowc1 jar=1 russell
