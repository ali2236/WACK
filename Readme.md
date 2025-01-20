# Automatic Parallelization of WebAssembly Code for Heterogeneous Edge Hardware

By: Ali Qanbari

Supervising Professor: Dr. Sedighian

## Abstract

Despite the years that have passed since the advent of multi-core processors, a significant portion of
existing programming code remains written sequentially and fails to fully leverage the capabilities of
existing hardware. Factors such as the difficulty of analyzing and rewriting code for parallel processing, lack of access to source code, and the high cost of manual rewriting are considered major obstacles
in this area. One key solution to overcome these challenges is the automatic parallelization of code
at the binary level, which allows for taking advantage of parallel processing even when the source
code is unavailable. This research aims to evaluate the effectiveness of automatic parallelization of
WebAssembly code on various heterogeneous hardware in edge environments. To achieve this, a tool
was designed to automatically convert sequential WebAssembly code into a parallel version using a
Single-Program Multiple-Data approach, with a special focus on loop parallelization. Evaluation of the
proposed method on a subset of Polybench tests showed that it is capable of effectively identifying and
parallelizing computational loops. Additionally, evaluations on heterogeneous edge hardware, including mobile devices and desktop systems, demonstrated that the proposed method can run and exploit
resources across different computing platforms.

---

### How to parallelize a wasm file using WAPC

Call `WAPC.Compile` and supply the the required arguments and compiler parameters in a java or kotlin file.

### How to run output binaries using wasmtime
```
wasmtime -W all-proposals=y -S threads .\wack_{file_name}.wasm
```
replace `{file_name}` with the input file name.
look at the `testUsingWAPC` function in `Main.kt` for a complete example.