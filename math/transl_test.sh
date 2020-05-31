#!/bin/bash

file=$1

rm $file-1.mm

../bin/russell \
	update-conf verbose=1 ru-root=./${file} ';' \
	mem-stats ';' \
	read file=${file}.mm ';' \
	mm-to-ru file=${file}.mm ';' \
	mem-stats ';' \
	fix-left-recursion file=${file}.ru ';' \
	split-math file=${file}.ru ';' \
	mem-stats ';' \
	math-stats ';' \
	write-ru all=1 ';' \
	mem-stats

dir=$(pwd)

echo import-roots=${dir}/${file} > ./${file}/russell.conf

#verbose=1 caching=1 $file.ru mm=$file-1.mm ru-2-mm=1 ru-stats=1
../bin/russell \
	load-conf dir=./${file} \
	update-conf verbose=1 ru-root=./${file} ';' \
	mem-stats ';' \
	read file=./${file}/${file}.ru ';' \
	math-stats ';' \
	ru-to-mm file=${file}.ru ';' \
	mem-stats ';' \
	write-mm file=${file}-1.mm \
	mem-stats ';'

../bin/metamath "read $file-1.mm" 'verify proof *' 'exit'
