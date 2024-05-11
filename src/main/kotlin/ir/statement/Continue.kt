package ir.statement

class Continue : BasicStatement() {
    override fun c(out: Appendable) {
        out.append("continue;\n")
    }
}