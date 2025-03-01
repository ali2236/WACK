package ir.wasm

import generation.wat.WatWriter
import generation.WebAssemblyInstruction
import ir.Names
import ir.expression.Symbol
import ir.statement.Statement

data class WasmGlobal(val index: Index, val type: WasmGlobalType, val constExpr: MutableList<Statement>) : WebAssemblyInstruction, IndexedSection {

    val symbol: Symbol
        get() = Symbol(WasmScope.global, type.type, index)

    override fun wat(wat: WatWriter) {
        val typ = if(type.mutable) "($type)" else "$type"
        wat.writeLine("(global ${index.section(Names.global)} $typ (")
        wat.indent++
        constExpr.map { it.wat(wat) }
        wat.indent--
        wat.writeLine("))")
    }

    override fun getSectionIndex(): Index = index

}



