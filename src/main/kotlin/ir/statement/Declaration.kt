package ir.statement

import ir.ChildExpression
import ir.expression.Symbol
import ir.finder.Visitor
import wasm.WasmValueType

class Declaration(val type : WasmValueType, val symbol: Symbol) : Statement {
    override fun write(out: Appendable) {
        out.append(type.cType())
        out.append(' ')
        symbol.write(out)
        out.append(";\n")
    }

    override fun expressions(): List<ChildExpression> {
        return listOf()
    }

    override fun visit(v: Visitor) {
        v.visit(symbol)
    }
}