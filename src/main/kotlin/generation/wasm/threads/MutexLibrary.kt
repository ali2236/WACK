package generation.wasm.threads

import ir.wasm.WasmFunction
import ir.wasm.WasmMemory

class MutexLibrary(
    val lock: WasmFunction,
    val unlock: WasmFunction,
    val join: WasmFunction,
    val memory: WasmMemory,
)