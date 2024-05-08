package ast.expression


open class Assignment(val symbol: Symbol, val value: Expression, var inline: Boolean = false) : Expression() {
    override fun c(out: Appendable) {
        symbol.c(out)
        out.append(" = ")
        value.c(out)
        if(!inline){
            out.append(";\n")
        }
    }
}