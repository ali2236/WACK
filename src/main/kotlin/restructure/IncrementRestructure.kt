package restructure

import ir.expression.*
import ir.statement.Assignee
import ir.statement.Assignment
import ir.statement.Statement
import wasm.WasmValueType

// check for "<symbol> = <symbol> + 1" pattern
// replace with <symbol>++
class IncrementRestructure : Restructure() {

    override fun restructureInstruction(stmt: Statement) {
        if (stmt is Assignee) {
            checkAssignmentStyleIncrement(stmt)
        }
    }

    private fun checkAssignmentStyleIncrement(stmt: Assignee) {
        if (stmt.assignedWith() is BinaryOP) {
            val opr = stmt.assignedWith() as BinaryOP
            if (
                (opr.right is Value && (opr.right as Value).value == "1" && opr.left == stmt.assignedTo())
            ) {
                val v = opr.right as Value
                if (opr.operator == Operator.add) {
                    replaceCurrentInstruction(Increment(stmt, Operator.add))
                } else if (opr.operator == Operator.sub) {
                    replaceCurrentInstruction(Increment(stmt, Operator.sub))
                }
            }
        }
    }

}