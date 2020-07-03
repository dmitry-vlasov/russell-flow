#!/bin/bash

file=$1

if [ -f "${file}-1.mm" ]; then
	rm ${file}-1.mm
fi

if [ -d "./${file}" ]; then
	rm -fr ./${file}
fi

mkdir ./${file}

echo "Translation: Metamath --> Russell"
echo "---------------------------------"
echo

dir=$(pwd)

echo import-roots=${dir}/${file} > ./${file}/russell.conf
echo project-roots=${dir}/${file}/${file}.ru >> ./${file}/russell.conf

../bin/russell \
	conf ru-root=./${file} project-roots=${dir}/${file}/${file}.ru ";" \
	mem-stats ";" \
	read-mm file=${file}.mm ";" \
	mm-to-ru file=${file}.mm ";" \
	conf import-roots=${dir}/${file} ";" \
	mem-stats ";" \
	fix-left-recursion ";" \
	split-math ";" \
	optimize-imports ";" \
	verify target=all ";" \
	mem-stats ";" \
	write-ru all=1 ";" \
	math-stats

echo
echo "Translation: Russell --> Metamath"
echo "---------------------------------"
echo

../bin/russell \
	load-conf dir=./${file} ";" \
	conf mm-root=. ";" \
	mem-stats ";" \
	read ";" \
	math-stats ";" \
	ru-to-mm ";" \
	mem-stats ";" \
	write-mm module=${file} monolithic=${file}-1.mm strip-comments=1 ";" \
	mem-stats

echo
echo "Verification of Metamath"
echo "------------------------"
echo

../bin/metamath "read $file-1.mm" "verify proof *" "exit"

