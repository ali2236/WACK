package generation

import ir.Names
import ir.statement.Statement


class WatWriter(private val buffer: Appendable) {

    var indent = 0

    fun writeLine(instruction: String) {
        startLine()
        write(instruction)
        endLine()
    }

    fun startLine(){
        for (i in 1..indent){
            buffer.append(Names.indent) // two spaces
        }
    }

    fun endLine(){
        buffer.append('\n')
    }

    fun write(instruction: String){
        buffer.append(instruction)
    }

    fun writeAll(stmts: List<Statement>) {
        stmts.forEach {
            it.wat(this)
        }
    }
}