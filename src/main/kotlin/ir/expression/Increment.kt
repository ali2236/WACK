package ir.expression

import generation.WatWriter
import ir.statement.*
import wasm.WasmValueType

class Increment(val operation: Assignee, val operator: Operator = Operator.add) : BasicStatement() {
    override fun write(out: Appendable) {
        operation.assignedTo().write(out)
        val op = when (operator) {
            Operator.add -> "++"
            Operator.sub -> "--"
            else -> throw Error()
        }
        out.append(op)
        out.append(";\n")
    }

    override fun wat(wat: WatWriter) {
        operation.wat(wat)
    }
}