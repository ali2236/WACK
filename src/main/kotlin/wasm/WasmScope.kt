package wasm

import ir.Names

enum class WasmScope {
    local,
    global;

    override fun toString(): String {
        return when(this){
            local -> Names.local
            global -> Names.global
        }
    }
}