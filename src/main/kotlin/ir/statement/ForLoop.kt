package ir.statement

import ir.expression.Expression
import ir.expression.Symbol

class ForLoop(val init: Statement, val condition: Expression, val step: Statement) : Block() {
    override fun c(out: Appendable) {
        out.append("for(")
        init.c(out)
        out.append(';')
        condition.c(out)
        out.append(';')
        step.c(out)
        out.append(')')
        super.c(out)
    }

    override fun symbols(): List<Symbol> {
        return init.symbols() + condition.symbols() + step.symbols() + super.symbols()
    }
}