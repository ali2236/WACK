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
- [x] Store 8/u8 types not support
- [ ] Move Increment Restructure to Optimization Passes
- [ ] Dependence Analysis P54
- [ ] GCD Test
- [ ] Data Dependency Test
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
