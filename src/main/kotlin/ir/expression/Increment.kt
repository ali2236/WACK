package ir.expression

import generation.WatWriter
import ir.statement.*
import wasm.WasmValueType

class Increment(val operation: Assignee, val operator: Operator = Operator.add, val amount: Value) : BasicStatement() {
    override fun write(out: Appendable) {
        val op = when (operator) {
            Operator.add -> "increment ${operation.assignedTo()} by $amount"
            Operator.sub -> "decrement ${operation.assignedTo()} by $amount"
            else -> throw Error()
        }

        out.append(op)
        out.append(";\n")
    }

    override fun wat(wat: WatWriter) {
        operation.wat(wat)
    }
}