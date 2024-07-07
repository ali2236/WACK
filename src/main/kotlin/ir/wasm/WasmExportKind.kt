package ir.wasm

import ir.Names

enum class WasmExportKind {
    func,
    memory,
    table,
    global;

    fun typeName():String{
        return when(this){
            func -> Names.function
            memory -> Names.memory
            table -> Names.table
            global -> Names.global
        }
    }
}