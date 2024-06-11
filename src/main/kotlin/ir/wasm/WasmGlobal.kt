package ir.wasm

import generation.WatWriter
import generation.WebAssemblyInstruction
import ir.expression.Symbol
import ir.statement.Statement

data class WasmGlobal(val index: Index, val type: WasmGlobalType, val constExpr: MutableList<Statement>) : WebAssemblyInstruction {

    val symbol: Symbol
        get() = Symbol(WasmScope.global, type.type, index)
    override fun wat(wat: WatWriter) {
        wat.writeLine("(global (;$index;) ($type) (")
        wat.indent++
        constExpr.map { it.wat(wat) }
        wat.indent--
        wat.writeLine("))")
    }

}



