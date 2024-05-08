package ir.expression

class Comment(val text: String) : Expression() {
    override fun c(out: Appendable) {
        out.append("/* $text */\n")
    }
}