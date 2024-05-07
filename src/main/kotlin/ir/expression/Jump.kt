package ir.expression

class Jump(val depth: Int, val dest: Expression) : Expression() {
    override fun c(out: Appendable) {
        if (dest is Block && depth == 0) {
            out.append("break;\n")
        } else if (dest is Loop && depth == 0) {
            out.append("continue;\n")
        }
    }
}