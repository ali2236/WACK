package ir.statement

import generation.WatWriter
import ir.expression.Expression
import ir.expression.Symbol
import ir.finder.Finders
import ir.finder.Visitor


open class Assignment(
    var symbol: Symbol,
    var value: Expression
) : BasicStatement(), AssignmentStore {

    init {
        // validate
        if (value.exprType() != symbol.exprType()) {
            throw Exception("Can't assign type ${value.exprType()} to ${symbol.exprType()}")
        }
    }


    override fun write(out: Appendable) {
        symbol.write(out)
        out.append(" = ")
        value.write(out)
        out.append(";\n")
    }

    override fun wat(wat: WatWriter) {
        value.wat(wat)
        wat.writeLine("${symbol.scope}.set ${symbol.index}", this)
    }

    override fun visit(v: Visitor) {
        v.visit(symbol) { symbol = it as Symbol }
        v.visit(value) { value = it as Expression }
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