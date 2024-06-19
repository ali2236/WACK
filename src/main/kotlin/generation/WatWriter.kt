package generation

import ir.Mode
import ir.Names
import ir.statement.Program
import ir.statement.Statement
import java.io.File


class WatWriter(private val buffer: Appendable) {

    var watDebug: Boolean = true
    var indent = 0
    val debugIndent = 36

    fun writeLine(instruction: String, debug: Statement? = null) {
        startLine()
        write(instruction)
        if (Mode.debug && watDebug) {
            debug?.let {
                for (i in 1..(debugIndent - (indent * Names.indent.length) - instruction.length)) {
                    buffer.append(' ')
                }
                write(" ;; ")
                it.id?.let { id ->
                    write("${debug.javaClass.simpleName}($id): ")
                }
                write(debug.toString().replace("\n", ""))
            }
        }
        endLine()
    }

    fun startLine() {
        for (i in 1..indent) {
            buffer.append(Names.indent) // two spaces
        }
    }

    fun endLine() {
        buffer.append('\n')
    }

    fun write(instruction: String) {
        buffer.append(instruction)
    }

    fun writeAll(stmts: List<Statement>) {
        stmts.forEach {
            it.wat(this)
        }
    }

    companion object {
        fun writeToFile(program: Program, watOut: File) {
            val outWriter = watOut.writer()
            val watWriter = WatWriter(outWriter)
            program.wat(watWriter)
            outWriter.flush()
            outWriter.close()
        }
    }
}