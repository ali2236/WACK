package ir.statement

import ir.ChildExpression
import ir.expression.Symbol
import wasm.WasmValueType

class Declaration(val type : WasmValueType, val symbol: Symbol) : Statement {
    override fun c(out: Appendable) {
        out.append(type.cType())
        out.append(' ')
        symbol.c(out)
        out.append(";\n")
    }

    override fun symbols(): List<Symbol> {
        return symbol.symbols()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf()
    }
}