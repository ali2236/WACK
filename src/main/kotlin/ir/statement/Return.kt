package ir.statement

class Return : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("return;")
    }
}