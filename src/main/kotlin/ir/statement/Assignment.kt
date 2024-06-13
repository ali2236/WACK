package ir.statement

import generation.WatWriter
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Symbol
import ir.expression.Value
import ir.finder.Visitor


open class Assignment(
    var symbol: Symbol,
    var value: Expression,
    var inline: Boolean = false,
    var tee: Boolean = false
) : BasicStatement(), AssignmentStore {

    init {
        // validate
        if (value.getType().first() != symbol.getType().first()) {
            throw Exception("Can't assign type ${value.getType()} to ${symbol.getType()}")
        }
    }


    override fun write(out: Appendable) {
        symbol.write(out)
        out.append(" = ")
        value.write(out)
        if (!inline) {
            out.append(";\n")
        }
        // TODO: Tee
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