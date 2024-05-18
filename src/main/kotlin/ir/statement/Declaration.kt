package ir.statement

import generation.WatWriter
import ir.expression.Symbol
import ir.finder.Visitor
import wasm.WasmValueType

class Declaration(val type : WasmValueType, var symbol: Symbol) : Statement {
    override fun write(out: Appendable) {
        out.append(type.cType())
        out.append(' ')
        symbol.write(out)
        out.append(";\n")
    }


    override fun visit(v: Visitor) {
        v.visit(symbol){symbol = it as Symbol}
    }

    override fun wat(wat: WatWriter) {
        // none existent in wasm code
    }
}