package ir.statement

import generation.WatWriter
import ir.finder.Visitor

class RawWat(val instruction: String) : BasicStatement() {
    override fun write(out: Appendable) {
        out.append(instruction)
        out.append(";\n")
    }

    override fun visit(v: Visitor) {

    }

    override fun wat(wat: WatWriter) {
        wat.writeLine(instruction)
    }
}