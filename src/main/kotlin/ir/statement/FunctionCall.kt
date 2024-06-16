package ir.statement

import generation.WatWriter
import ir.Mode
import ir.expression.Expression
import ir.wasm.Index
import ir.wasm.WasmValueType

class FunctionCall(
    val functionIndex: Index,
    val params: List<Expression>,
    val returnType: List<WasmValueType>,
) : BasicStatement() {

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
        if (returnType.isEmpty()) {
            out.append(";\n")
        }
    }

    override fun wat(wat: WatWriter) {
        wat.writeAll(params.asReversed())
        val fi = if(Mode.callByIndex) "$functionIndex" else "\$f${functionIndex}"
        wat.writeLine("call $fi", this)
    }

}