package generation.wack

import ir.wasm.WasmFunction

@Deprecated("use struct wack_thread")
class ThreadArg(
    val encode : WasmFunction, // (thread_id, kernel_id) -> int32
    val decodeThreadId : WasmFunction,
    val decodeKernelId : WasmFunction,
)