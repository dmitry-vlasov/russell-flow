Implementation of Russell language on flow
------------------------------------------

All programming is done in the language  [Flow](https://github.com/area9innovation/flow9)

To use Russell just add 'bin' directory to PATH. You also need java runtime environment (JRE).
To compile Russell you'll need JDK 11 or higher.

To run translation from Metamath to Russell and back:
 - copy a fresh [Metamath](http://metamath.org/) theorem base set.mm to some directory and cd there.
 - cut an initial fragment of it with 'head -n 100000 set.mm > set-100000' (the whole set.mm translation may take too much memory)
 - be careful with the ending of the initial fragment: cut off the last theorem, if it is not complete
 - run 'russell mm2ru2mm set-100000'

Vscode extension: is located in 'resources/vscode'. Use it to explore the Russell theorem base.

Prerequisites: java runtime to run, java compiler (11 or higher) to compile.
