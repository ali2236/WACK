package ir.statement

class Comment(val text: String) : BasicStatement() {
    override fun write(out: Appendable) {
        out.append("/* $text */\n")
    }

}