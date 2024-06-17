package ir.expression

import generation.WatWriter
import ir.finder.Finders
import ir.finder.Visitor
import ir.statement.AssignmentStore
import ir.statement.SymbolLoad
import ir.wasm.WasmValueType

class Tee(var symbol: Symbol, var value: Expression) : Expression(), AssignmentStore {

    init {
        if (symbol.exprType() != value.exprType()) {
            throw Exception("Type ${value.exprType()} can't be teed to ${symbol.exprType()}")
        }
    }

    override fun clone(): Expression {
        return Tee(
            symbol.clone() as Symbol,
            value.clone(),
        )
    }

    override fun exprType(): WasmValueType {
        return symbol.type
    }

    override fun assignedWith(): Expression {
        return value
    }

    override fun assignedTo(): SymbolLoad {
        return symbol
    }

    override fun replaceAssign(newValue: Expression) {
        value = newValue
    }

    override fun write(out: Appendable) {
        out.append("(${symbol}=")
        value.write(out)
        out.append(")")
    }

    override fun wat(wat: WatWriter) {
        value.wat(wat)
        wat.writeLine("${symbol.scope}.tee ${symbol.index}", this)
    }

    override fun visit(v: Visitor) {
        v.visit(symbol) { this.symbol = it as Symbol }
        v.visit(value) { this.value = it as Expression }
    }

    fun teeValue(): Expression {
        val dependant = Finders.symbols(value).any { it == symbol }
        if (dependant) {
            return symbol
        } else {
            return value
        }
    }

}