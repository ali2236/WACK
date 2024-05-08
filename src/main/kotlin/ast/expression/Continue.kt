package ast.expression

class Continue : Expression() {
    override fun c(out: Appendable) {
        out.append("continue;\n")
    }
}