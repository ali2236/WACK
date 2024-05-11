package ir.statement

import ir.ChildExpression
import ir.expression.Break
import ir.expression.Expression
import ir.expression.Symbol
import ir.expression.Value

open class Loop(var condition: Expression = Value("1")) : Block() {

    fun breakConditions(): List<IndexedValue<BrIf>> {
        return instructions.withIndex()
            .filter { it.value is BrIf }
            .filterIsInstance<IndexedValue<BrIf>>()
    }
    override fun c(out: Appendable) {
        out.append("while(")
        condition.c(out)
        out.append(")")
        super.c(out)
    }

    override fun symbols(): List<Symbol> {
        return condition.symbols() + super.symbols()
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(condition){condition = it}
        )
    }

    override fun close() {
        push(Break())
    }
}