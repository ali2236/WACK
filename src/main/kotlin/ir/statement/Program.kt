package ir.statement

import ir.ChildExpression
import ir.expression.Symbol
import ir.finder.Visitor

class Program(val statements: List<Statement>) : Statement {
    override fun write(out: Appendable) {
        for (statement in statements) {
            statement.write(out)
            out.append('\n')
        }
    }

    override fun expressions(): List<ChildExpression> {
        return listOf()
    }

    override fun visit(v: Visitor) {
        statements.forEach(v::visit)
    }
}