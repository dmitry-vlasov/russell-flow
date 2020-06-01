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

../bin/russell \
	update-conf verbose=1 ru-root=./${file} import-roots=${dir} ";" \
	mem-stats ";" \
	read file=${file}.mm ";" \
	mm-to-ru file=${file}.mm ";" \
	mem-stats ";" \
	clear-conf ";" \
	update-conf verbose=1 ru-root=./${file} import-roots=${dir}/${file} ";" \
	fix-left-recursion file=${file}.ru ";" \
	split-math file=${file}.ru ";" \
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
	update-conf verbose=1 ru-root=./${file} mm-root=. ";" \
	mem-stats ";" \
	read file=./${file}/${file}.ru ";" \
	math-stats ";" \
	ru-to-mm file=${file}.ru ";" \
	mem-stats ";" \
	write-mm all-to-one=${file}-1.mm strip-comments=1 ";" \
	mem-stats

echo
echo "Verification of Metamath"
echo "------------------------"
echo

../bin/metamath "read $file-1.mm" "verify proof *" "exit"

