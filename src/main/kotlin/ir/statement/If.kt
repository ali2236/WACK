package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol
import ir.finder.Visitor

open class If(
    var condition: Expression,
    trueBody: MutableList<Statement> = mutableListOf(),
    var elseBody: List<Statement>? = null,
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

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(condition) { condition = it }
        )
    }

    override fun visit(v: Visitor) {
        v.visit(condition)
        super.visit(v)
        elseBody.orEmpty().forEach(v::visit)
    }
}

