#!/bin/bash

java -jar -Xss16m -Xmx12g russell.jar verbose=1 set.mm ru=set.ru ru-stats=1 ru-fix-synt=1

java -jar -Xss16m -Xmx12g russell.jar verbose=1 set.ru mm=set-1.ru ru-stats=1

metamath 'read set-1.mm' 'verify proof *' 'exit'
