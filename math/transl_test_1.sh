#!/bin/bash

file=$1

rm -fr ./ru
rm $file-1.mm

java -jar -Xss16m -Xmx12g ../russell.jar verbose=2 $file.mm mm-2-ru=1 ru-root=ru ru-write-all=1 mm-stats=1 mm-fix=1 mm-split=1

java -jar -Xss16m -Xmx12g ../russell.jar verbose=2 ru/$file.ru mm=$file-1.mm ru-2-mm=1 stats=1

../metamath "read $file-1.mm" 'verify proof *' 'exit'
