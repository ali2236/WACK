package ir.statement

import generation.WatWriter
import ir.finder.Visitor

// Raw WAT statment
class RawWats(val instructions: List<String>) : Statement {
    override fun write(out: Appendable) {
        instructions.forEach {
            out.append(it)
            out.append(";\n")
        }
    }

    override fun visit(v: Visitor) {

    }

    override fun wat(wat: WatWriter) {
        instructions.forEach {
            wat.writeLine(it)
        }
    }
}