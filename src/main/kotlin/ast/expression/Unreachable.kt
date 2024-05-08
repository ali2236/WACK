package ast.expression

class Unreachable : Expression() {
    override fun c(out: Appendable) {
        out.append("UNREACHABLE;\n")
    }
}