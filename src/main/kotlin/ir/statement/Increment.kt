package ir.statement

import generation.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

// symbol++
class Increment(var increment: AssignmentStore) : BasicStatement(), AssignmentStore {
    override fun assignedWith(): Expression {
        return increment.assignedWith()
    }

    override fun assignedTo(): SymbolLoad {
        return increment.assignedTo()
    }

    override fun replaceAssign(newValue: Expression) {
        return increment.replaceAssign(newValue)
    }

    override fun write(out: Appendable) {
        increment.write(out)
    }

    override fun visit(v: Visitor) {
        increment.visit(v)
    }

    override fun wat(wat: WatWriter) {
        increment.wat(wat)
    }
}

