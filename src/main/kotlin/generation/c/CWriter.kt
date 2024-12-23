package generation.c

import compiler.WACK
import compiler.WAPC
import ir.Names
import ir.statement.Program
import ir.statement.Statement
import java.io.File


class CWriter(private val buffer: Appendable) {

    var cDebug: Boolean = true
    private var indent = 0
    val debugIndent = 36

    fun writeLine(statement: String, debug: Statement? = null) {
        startLine()
        write(statement)
        if (WACK.params.addCommentedIR) {
            debug?.let {
                for (i in 1..(debugIndent - (indent * Names.indent.length) - statement.length)) {
                    buffer.append(' ')
                }
                write(" // ")
                it.id?.let { id ->
                    write("${debug.javaClass.simpleName}($id): ")
                }
                write(debug.toString().replace("\n", ""))
            }
        }
        endLine()
    }

    fun indent(){
        indent++
    }

    fun deIndent(){
        indent--
    }

    fun indented(block: () -> Unit){
        indent()
        block()
        deIndent()
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
            it.c(this)
        }
    }

    fun inLine(block: () -> Unit) {
        startLine()
        block()
        endLine()
    }

    companion object {
        fun writeToFile(program: Program, cOut: File) : File {
            val outWriter = cOut.writer()
            val cWriter = CWriter(outWriter)
            program.c(cWriter)
            outWriter.flush()
            outWriter.close()
            return cOut
        }
    }
}