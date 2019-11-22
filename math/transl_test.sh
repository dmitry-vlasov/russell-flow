#!/bin/bash

file=$1

rm ./$file.ru
rm ./$file-1.mm

java -jar -Xss16m -Xmx12g ../russell.jar verbose=1 $file.mm ru=$file.ru mm-2-ru=1 mm-stats=1 mm-fix=1

java -jar -Xss16m -Xmx12g ../russell.jar verbose=1 $file.ru mm=$file-1.mm ru-2-mm=1 ru-stats=1

../metamath "read $file-1.mm" 'verify proof *' 'exit'
