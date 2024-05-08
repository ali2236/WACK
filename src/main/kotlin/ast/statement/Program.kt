package ast.statement

class Program(val statements: List<Statement>) : Statement {
    override fun c(out: Appendable) {
        for (statement in statements) {
            statement.c(out)
            out.append('\n')
        }
    }
}