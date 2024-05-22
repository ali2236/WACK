package generation.wasi.threads

import wasm.WasmFunction
import wasm.WasmGlobal

class WasiThreadsRuntime(
    val numThreads : WasmGlobal,
    val encodeArg : WasmFunction,
)