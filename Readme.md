# TODO
- [x] Multiple Memory Support
- [x] Lock Thread Mutex Before Kernel Lock
- [x] Parallel Block Generation
- [x] DFA
  - [x] Constant Propagation
  - [x] Memory Aliasing
- [x] Block WasmBlockType
- [x] Else for If block
- [x] Move RangeLoop Restructure to Optimization Passes
- [x] Element & Data Section Parsing
- [x] Support Store 8/16/32 types
- [x] Move Increment Restructure to Optimization Passes
- [x] Privatize Loop Symbol if needed
- [x] Generate Deep-Loop Kernels correctly
- [x] Dependence Analysis
- [x] GCD Test
- [x] Data Dependency Test
- [ ] Eval or Runtime Evaluation
- [ ] Runtime limit evaluation(omp if)
- [ ] Profitability Analysis
- [ ] Permit Pure Functions in loops
- [x]`+=` operator
- [ ] Thread Local Storage (TLS)

Currently not supported:
- O3

### How to Run
```
wasmtime -W all-proposals=y -S threads .\wack_matrix_multiply.wasm
```

```
wasm-opt -O3 --enable-threads --enable-bulk-memory --enable-reference-types --enable-multimemory .\wack_matrix_multiply.wasm -o opt_mat.wasm
```
