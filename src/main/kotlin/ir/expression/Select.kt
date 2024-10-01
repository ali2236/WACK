package ir.expression

import generation.WatWriter
import ir.finder.Visitor
import ir.wasm.WasmValueType

// return selector == 0 ? val2 : val1
// TODO: implementation maybe incorrect
class Select(var val1: Expression, var val2: Expression, var selector: Expression, val resultType: WasmValueType?) :
    Expression() {
    override fun clone(): Expression {
        return Select(val1.clone(), val2.clone(), selector.clone(), resultType)
    }

    override fun exprType(): WasmValueType {
        return resultType ?: val1.exprType()
    }

    override fun write(out: Appendable) {
        selector.write(out)
        out.append(" ? ")
        val1.write(out)
        out.append(" : ")
        val2.write(out)
    }

    override fun wat(wat: WatWriter) {
        val1.wat(wat)
        val2.wat(wat)
        selector.wat(wat)
        val type = if(resultType != null) " (result $resultType)" else ""
        wat.writeLine("select$type", this)
    }

    override fun visit(v: Visitor) {
        v.visit(val1) {this.val1 = it as Expression}
        v.visit(val2) {this.val2 = it as Expression}
        v.visit(selector) {this.selector = it as Expression}
    }

}