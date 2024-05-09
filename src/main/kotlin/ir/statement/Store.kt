package ir.statement

import ir.expression.Expression
import ir.expression.Symbol
import wasm.WasmValueType

class Store(val type: WasmValueType, val data: Expression, val address: Expression) : Statement {
    override fun c(out: Appendable) {
        out.append("Memory_${type}[")
        address.c(out)
        out.append("] = ")
        data.c(out)
        out.append(";\n")
    }

    override fun symbols(): List<Symbol> {
        return address.symbols() + data.symbols()
    }

}