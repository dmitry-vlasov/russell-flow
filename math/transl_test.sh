#!/bin/bash

file=$1

rm $file-1.mm

../bin/russell verbose=1 $file.mm mm-2-ru=1 ru-root=. ru-write-all=1 mm-stats=1 mm-fix=1 mm-split=1

../bin/russell verbose=1 $file.ru mm=$file-1.mm ru-2-mm=1 stats=1

../bin/metamath "read $file-1.mm" 'verify proof *' 'exit'
