# TODO
- [x] Multiple Memory Support
- [x] Lock Thread Mutex Before Kernel Lock
- [x] Parallel Block Generation
- [x] DFA
  - [ ] Constant Propagation
  - [x] Memory Aliasing
- [ ] Dependence Analysis P54
- [ ] GCD Test
- [ ] Data Dependency Test
- [ ] Block WasmBlockType
- [ ] Eval or Runtime Evaluation
- [ ] Runtime limit evaluation(omp if)
- [ ] Profitability Analysis
- [ ] Thread Local Storage (TLS)

Loop Analysis Steps:
if Pass GCD Test -> parallel
else test dependence
else false

### How to Run
```
wasmtime -S threads .\wat_seq.wasm
```
