package ir.statement

import generation.WatWriter
import ir.Mode
import ir.Names
import ir.expression.Expression
import ir.expression.SingleResultFunction
import ir.finder.Visitor
import ir.wasm.Index
import ir.wasm.WasmValueType

class FunctionCall(
    val functionIndex: Index,
    val params: MutableList<Expression>,
    val returnType: List<WasmValueType>,
) : BasicStatement() {

    override fun write(out: Appendable) {
        out.append(functionIndex.access(Names.function, ""))
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

    override fun visit(v: Visitor) {
        v.visit(params) { i, stmt ->
            params[i] = stmt as Expression
        }
    }

    override fun wat(wat: WatWriter) {
        wat.writeAll(params.asReversed())
        val fi = functionIndex.section(Names.function)
        wat.writeLine("call $fi", this)
    }

    val result: SingleResultFunction
        get() {
            if(returnType.size != 1){
                throw Exception()
            }
            return SingleResultFunction(this, returnType.first())
        }

}