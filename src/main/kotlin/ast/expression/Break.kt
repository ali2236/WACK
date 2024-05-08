package ast.expression

class Break : Expression() {
    override fun c(out: Appendable) {
        out.append("break;\n")
    }
}