package generation.runtime2

import ir.wasm.WasmFunction
import ir.wasm.WasmGlobal

data class WackPthreads(val threadCount : WasmGlobal, val parallel: WasmFunction)