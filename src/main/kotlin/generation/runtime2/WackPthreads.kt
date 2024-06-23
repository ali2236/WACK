package generation.runtime2

import ir.wasm.WasmFunction

data class WackPthreads(val threadCount : WasmFunction, val parallel: WasmFunction)