package ast.statement

import ast.expression.Block
import wasm.WasmFunction

class Function(val functionData: WasmFunction, val body: Block) : Statement {
    override fun c(out: Appendable) {

        // Return Type
        val results = functionData.type.result
        if(results.isEmpty()){
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
        for (i in 0 until paramCount){
            val param = functionData.type.params[i]
            out.append(param.name)
            out.append(' ') // space
            out.append("l$i")
            if(i != functionData.type.params.size - 1){
                out.append(',')
            }
        }
        out.append("){\n")

        // Local Variables
        val localCount = functionData.locals.size
        for (i in 0 until localCount){
            val localType = functionData.locals[i]
            out.append(localType.name)
            out.append(' ')
            out.append("l${paramCount+i} = 0;\n")
        }

        // Rest of Body
        body.c(out)

        // Close
        out.append("}\n")
    }

}