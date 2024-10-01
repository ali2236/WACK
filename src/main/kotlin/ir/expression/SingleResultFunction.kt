package ir.expression

import generation.WatWriter
import ir.finder.Visitor
import ir.statement.Statement
import ir.wasm.WasmValueType

class SingleResultFunction(var functionCall: Statement, val returnType: WasmValueType) : ImmutableExpression() {

    override fun exprType(): WasmValueType {
        return returnType
    }

    override fun write(out: Appendable) {
        functionCall.write(out)
    }

    override fun wat(wat: WatWriter) {
        functionCall.wat(wat)
    }

    override fun visit(v: Visitor) {
        v.visit(functionCall) { this.functionCall = it }
    }
}