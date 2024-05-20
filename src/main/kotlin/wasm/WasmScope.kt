package wasm

import ir.Names

enum class WasmScope {
    local,
    global;

    fun prefix(): String {
        return when(this){
            local -> Names.local
            global -> Names.global
        }
    }
}