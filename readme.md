Implementation of Russell language on flow
------------------------------------------

All programming is done in the language  [Flow](https://github.com/area9innovation/flow9)

To use Russell add 'bin' directory to PATH.

Test translation script 'transl_test.sh' is in math directory. 
To run it:
 - copy a fresh [Metamath](http://metamath.org/) theorem base set.mm to math directory
 - cut an initial fragment of it with 'head -n 100000 set.mm > set-100000' (the whole set.mm translation may cause out of memory)
 - run 'transl_test.sh set-100000'

Vscode extension: is located in 'resources/vscode'. Use it to explore the Russell theorem base.

Prerequisites: java runtime.