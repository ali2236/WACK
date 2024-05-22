package generation.wack

import wasm.WasmFunction

class ThreadArg(
    val encode : WasmFunction,
    val decode : WasmFunction,
)