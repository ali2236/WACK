package ir.expression

import generation.WatWriter
import ir.finder.Finders
import ir.finder.Visitor
import ir.statement.AssignmentStore
import ir.statement.SymbolLoad
import ir.wasm.WasmValueType


open class Assignment(
    var symbol: Symbol,
    var value: Expression,
    var inline: Boolean = false,
    var tee: Boolean = false
) : Expression(), AssignmentStore {

    init {
        // validate
        if (value.getType().first() != symbol.getType().first()) {
            throw Exception("Can't assign type ${value.getType()} to ${symbol.getType()}")
        }
    }


    override fun write(out: Appendable) {
        symbol.write(out)
        if (tee) {
            out.append(" := ")
        } else {
            out.append(" = ")
        }
        value.write(out)
        if (!inline) {
            out.append(";\n")
        }
    }

    override fun wat(wat: WatWriter) {
        value.wat(wat)
        if (tee) {
            wat.writeLine("${symbol.scope}.tee ${symbol.index}", this)
        } else {
            wat.writeLine("${symbol.scope}.set ${symbol.index}", this)

        }
    }

    override fun visit(v: Visitor) {
        v.visit(symbol) { symbol = it as Symbol }
        v.visit(value) { value = it as Expression }
    }

    override fun clone(): Expression {
        return Assignment(
            symbol.clone() as Symbol,
            value.clone(),
            inline, tee
        ).also { it.id = this.id }
    }

    override fun getType(): List<WasmValueType> {
        if (tee) {
            return listOf(symbol.type)
        } else {
            return listOf()
        }
    }

    fun teeValue(): Expression {
        val dependant = Finders.symbols(value).any { it == symbol }
        if (dependant) {
            return symbol
        } else {
            return value
        }
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

    override fun toString(): String {
        val buffer = StringBuffer()
        this.write(buffer)
        return buffer.toString()
    }

}