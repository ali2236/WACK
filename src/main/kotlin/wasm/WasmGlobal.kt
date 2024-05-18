package wasm

import generation.WatWriter
import generation.WebAssemblyInstruction
import ir.statement.Statement

data class WasmGlobal(val index: Index, val type: WasmGlobalType, val constExpr: MutableList<Statement>) : WebAssemblyInstruction {
    override fun wat(wat: WatWriter) {
        wat.writeLine("(global (;$index;) ($type) (")
        wat.indent++
        constExpr.map { it.wat(wat) }
        wat.indent--
        wat.writeLine("))")
    }

}

data class WasmGlobalType(val type: WasmValueType, val mutable: Boolean){
    override fun toString(): String {
        val mut = if(mutable) "mut " else ""
        return "$mut$type"
    }
}

