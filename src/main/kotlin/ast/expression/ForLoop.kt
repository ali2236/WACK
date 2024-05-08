package ast.expression

class ForLoop(val init: Expression, val condition: Expression, val step: Expression) : Block() {
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