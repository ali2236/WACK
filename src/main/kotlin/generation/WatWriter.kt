package generation

import ir.Names
import ir.statement.Statement


class WatWriter(private val buffer: Appendable) {

    public var indent = 0

    fun writeLine(instruction: String) {
        for (i in 1..indent){
            buffer.append(Names.indent) // two spaces
        }
        buffer.append(instruction)
        buffer.append('\n')
    }

    fun writeAll(stmts: List<Statement>) {
        stmts.forEach {
            it.wat(this)
        }
    }
}