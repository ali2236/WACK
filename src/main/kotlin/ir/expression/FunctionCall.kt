package ir.expression

import generation.WatWriter
import wasm.Index

class FunctionCall(
    val functionIndex: Index,
    val params: List<Expression>,
    val hasReturn: Boolean,
) : Expression() {
    override fun clone(): Expression {
        return FunctionCall(functionIndex, params.map { it.clone() }, hasReturn)
    }

    override fun write(out: Appendable) {
        out.append("f${functionIndex}")
        out.append("(")

        val paramCount = params.size
        for (i in 0 until paramCount) {
            val param = params[i]
            param.write(out)
            if (i != paramCount - 1) {
                out.append(", ")
            }
        }

        out.append(")")
        if (!hasReturn) {
            out.append(";\n")
        }
    }

    override fun wat(wat: WatWriter) {
        wat.writeAll(params)
        wat.writeLine("call \$f${functionIndex}")
    }

}