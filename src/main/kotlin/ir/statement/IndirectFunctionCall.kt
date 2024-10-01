package ir.statement

import generation.WatWriter
import ir.expression.Expression
import ir.finder.Visitor
import ir.wasm.Index
import ir.wasm.WasmValueType

class IndirectFunctionCall(
    val tableIndex: Index,
    val typeIndex: Index,
    var functionIndex: Expression,
    val params: MutableList<Expression>,
    val returnType: List<WasmValueType>
) : BasicStatement() {

    override fun write(out: Appendable) {
        out.append("T[$tableIndex][$functionIndex](")
        params.forEachIndexed { i, it ->
            it.write(out)
            if (i != params.size - 1) {
                out.append(",")
            }
        }
        out.append(")")
        if (returnType.isEmpty()) {
            out.append(";\n")
        }
    }

    override fun wat(wat: WatWriter) {
        wat.writeAll(params.asReversed())
        functionIndex.wat(wat)
        wat.writeLine("call_indirect $tableIndex (type $typeIndex)", this)
    }

    override fun visit(v: Visitor) {
        v.visit(functionIndex) {this.functionIndex = it as Expression}
        v.visit(params) {i, stmt ->
            params[i] = stmt as Expression
        }
    }
}