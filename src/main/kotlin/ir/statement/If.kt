package ir.statement

import generation.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

open class If(
    var condition: Expression,
    trueBody: MutableList<Statement> = mutableListOf(),
    var elseBody: MutableList<Statement>? = null,
    brackets: Boolean = true
) : Block(trueBody, brackets = brackets) {
    open val trueBody: List<Statement>
        get() = instructions

    override fun writeHeader(out: Appendable) {
        out.append("if(")
        condition.write(out)
        out.append(")")
    }

    override fun write(out: Appendable) {
        super.write(out)
        if (elseBody != null) {
            out.append("else {\n")
            elseBody?.forEach {
                it.write(out)
            }
            out.append("}\n")
        }
    }

    override fun watHeader(wat: WatWriter) {
        condition.wat(wat)
        wat.writeLine("if")
    }

    override fun wat(wat: WatWriter) {
        super.wat(wat)
        // TODO: Else
    }

    override fun visit(v: Visitor) {
        v.visit(condition){ condition = it as Expression}
        super.visit(v)
        if(elseBody != null){
            v.visit(elseBody!!, elseBody!!::set)
        }
    }

    override fun toString(): String {
        val b = StringBuffer()
        writeHeader(b)
        return b.toString()
    }
}

