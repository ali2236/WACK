# Automatic Parallelization of WebAssembly Code for Heterogeneous Edge Hardware

---

### How to parallelize a wasm file using WAPC

Call `WAPC.Compile` and supply the the required arguments and compiler parameters in a java or kotlin file.

### How to run output binaries using wasmtime
```
wasmtime -W all-proposals=y -S threads .\wack_{file_name}.wasm
```
replace `{file_name}` with the input file name.
look at the `testUsingWAPC` function in `Main.kt` for a complete example.
