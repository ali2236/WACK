package ir.expression

import generation.WatWriter
import ir.finder.Visitor
import ir.wasm.WasmValueType

class StackExpression(var expr: Expression, private val replaceOriginal: (Expression) -> Unit) : Expression() {
    override fun clone(): Expression {
        return StackExpression(expr.clone()) {}
    }

    override fun write(out: Appendable) {
        out.append("S<$expr>")
    }

    override fun exprType(): WasmValueType {
        return expr.exprType()
    }

    override fun wat(wat: WatWriter) {
        // no wat
    }

    override fun visit(v: Visitor) {
        v.visit(expr){
            expr = it as Expression
            replaceOriginal(it)
        }
    }
}