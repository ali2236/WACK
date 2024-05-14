package ir.statement

import ir.ChildExpression
import ir.expression.Symbol

class Program(val statements: List<Statement>) : Statement {
    override fun write(out: Appendable) {
        for (statement in statements) {
            statement.write(out)
            out.append('\n')
        }
    }

    override fun symbols(): List<Symbol> {
        return statements.map { it.symbols() }.reduce { a, b -> a + b }
    }

    override fun expressions(): List<ChildExpression> {
        return listOf()
    }
}