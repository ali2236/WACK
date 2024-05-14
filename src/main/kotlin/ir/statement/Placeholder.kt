package ir.statement

class Placeholder(val msg: String) : BasicStatement() {
    override fun write(out: Appendable) {
        Comment(msg).write(out)
    }

}