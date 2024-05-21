package ir.statement

import generation.WatWriter
import ir.Names
import wasm.WasmFunction

class Function(
    val functionData: WasmFunction,
    instructions: MutableList<Statement> = mutableListOf()
) : Block(instructions, false, false) {

    init {
        instructions.forEachIndexed { i, stmt ->
            if (stmt is Block) {
                stmt.parent = this
                stmt.indexInParent = i
            }
        }
    }

    override fun write(out: Appendable) {

        // Return Type
        val results = functionData.type.result
        if (results.isEmpty()) {
            out.append("void")
        } else {
            out.append(results.first().name)
        }
        out.append(" ")

        // name
        out.append("f${functionData.index}")

        // params
        out.append("(")
        val paramCount = functionData.type.params.size
        for (i in 0 until paramCount) {
            val param = functionData.type.params[i]
            out.append(param.name)
            out.append(' ') // space
            out.append("l$i")
            if (i != functionData.type.params.size - 1) {
                out.append(',')
            }
        }
        out.append("){\n")

        // function body
        if (functionData.import != null) {
            val i = functionData.import

            out.append("// ")
            out.append(i.module)
            out.append(" ")
            out.append(i.name)
            out.append("\n")


        } else {
            super.write(out)
        }

        // Close
        out.append("}\n")
    }

    override fun watHeader(wat: WatWriter) {
        val funcStart =
            "(func (;${functionData.index};) (type ${functionData.type.index})${functionData.type.paramsWat()}${functionData.type.resultWat()}"
        if (functionData.import == null) {
            wat.writeLine(funcStart)
            if (functionData.locals.size > 0) {
                wat.writeLine("${Names.indent}(local ${functionData.locals.joinToString(" ") { it.name }})")
            }
        } else {
            // imported
            val import = functionData.import
            wat.writeLine("(import ${import.module} ${import.name} $funcStart))")
        }
    }

    override fun wat(wat: WatWriter) {
        watHeader(wat)
        if (functionData.import == null) {
            wat.indent++
            wat.writeAll(instructions)
            wat.writeLine(")")
            wat.indent--
        }
    }

}