package ir.statement

class Br(val target: Block, var depth: Int) : BasicStatement() {
    override fun write(out: Appendable) {
        if (target is Loop && depth == 0) {
            out.append("continue;\n")
        } else if (target is Block && depth == 0) {
            out.append("break;\n")
        } else {
            out.append("Br $depth")
        }
    }
}