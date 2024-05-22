package generation.wasm.threads

import wasm.WasmFunction
import wasm.WasmMemory

class MutexLibrary(
    val lock: WasmFunction,
    val unlock: WasmFunction,
    val join: WasmFunction,
    val memory: WasmMemory,
)