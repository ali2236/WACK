package ir.expression

class Loop(val body: Block) : Expression() {
    override fun c(out: Appendable) {
        out.append("for(;1;)")
        body.c(out)
    }
}