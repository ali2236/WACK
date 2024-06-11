package generation.wack

import ir.wasm.WasmFunction

class ThreadArg(
    val encode : WasmFunction,
    val decode : WasmFunction, // return kernel_id, thread_id
)