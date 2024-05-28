package ir.expression

import generation.WatWriter
import ir.finder.Visitor
import ir.statement.*
import wasm.WasmValueType

class Increment(val operation: Assignee, val operator: Operator = Operator.add) : Assignee {
    override fun assignedWith(): Expression {
       return operation.assignedWith()
    }

    override fun assignedTo(): Assignable {
        return operation.assignedTo()
    }

    override fun replaceAssign(newValue: Expression) {
        operation.replaceAssign(newValue)
    }

    override fun write(out: Appendable) {
        val op = when (operator) {
            Operator.add -> "${operation.assignedTo()}++"
            Operator.sub -> "${operation.assignedTo()}--"
            else -> throw Error()
        }

        out.append(op)
        out.append(";\n")
    }

    override fun visit(v: Visitor) {
        operation.visit(v)
    }

    override fun wat(wat: WatWriter) {
        operation.wat(wat)
    }
}

