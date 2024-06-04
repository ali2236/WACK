package ir.expression

import generation.WatWriter
import ir.finder.Visitor
import ir.statement.*

class Increment(val operation: AssignmentStore, val operator: BinaryOP.Operator = BinaryOP.Operator.add) : AssignmentStore {
    override var id: Long? = Statement.newId()
    override fun assignedWith(): Expression {
       return operation.assignedWith()
    }

    override fun assignedTo(): SymbolLoad {
        return operation.assignedTo()
    }

    override fun replaceAssign(newValue: Expression) {
        operation.replaceAssign(newValue)
    }

    override fun write(out: Appendable) {
        val op = when (operator) {
            BinaryOP.Operator.add -> "${operation.assignedTo()}++"
            BinaryOP.Operator.sub -> "${operation.assignedTo()}--"
            else -> throw Error()
        }

        out.append(op)
        out.append(";\n")
    }

    override fun isExpression(): Boolean = false

    override fun visit(v: Visitor) {
        operation.visit(v)
    }

    override fun wat(wat: WatWriter) {
        operation.wat(wat)
    }
}

