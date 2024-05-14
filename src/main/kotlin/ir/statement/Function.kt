package ir.statement

import ir.ChildExpression
import ir.Names
import ir.expression.Symbol
import ir.expression.Value
import wasm.WasmFunction

class Function(
    val functionData: WasmFunction,
    private val body: Block
) : Block(
    hasReturn = false,
    brackets = false,
) {

    override val instructions: MutableList<Statement>
        get() {
            val inst = mutableListOf<Statement>()
            // Local Variables
            // declaration
            val paramCount = functionData.type.params.size
            val localCount = functionData.locals.size
            for (i in paramCount until localCount) {
                val localType = functionData.locals[i]
                val symbol = Symbol(localType, Names.local + "${paramCount + i}")
                val dec = Declaration(localType, symbol)
                inst.add(dec)
            }
            // assignment
            for (i in paramCount until localCount) {
                val localType = functionData.locals[i]
                val symbol = Symbol(localType, Names.local + "${paramCount + i}")
                val value = Value(localType, localType.defaultValue())
                val assignment = Assignment(symbol, value)
                inst.add(assignment)
            }

            body.parent = this
            body.indexInParent = inst.size
            inst.add(body)
            return inst
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

    override fun symbols(): List<Symbol> {
        return body.symbols()
    }

    override fun expressions(): List<ChildExpression> {
        return body.expressions()
    }

}