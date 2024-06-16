package optimization.restructure

import ir.expression.*
import ir.statement.Assignment
import ir.statement.AssignmentStore
import ir.statement.Increment
import ir.statement.Statement

// check for "<symbol> = <symbol> + 1" pattern
// replace with <symbol>++
class IncrementRestructure : Restructure() {

    override fun restructureInstruction(stmt: Statement) {
        if (stmt is AssignmentStore) {
            checkAssignmentStyleIncrement(stmt)
        }
    }

    private fun checkAssignmentStyleIncrement(stmt: AssignmentStore) {
        if (stmt.assignedWith() is BinaryOP) {
            val opr = stmt.assignedWith() as BinaryOP
            if ((opr.right is Value && opr.operator == BinaryOP.Operator.add && (opr.right as Value).value == "1" && opr.left == stmt.assignedTo())) {
                replaceCurrentInstruction(Increment(stmt))
            } else if ((opr.left is Value && opr.operator == BinaryOP.Operator.add && (opr.left as Value).value == "1" && opr.right == stmt.assignedTo())) {
                replaceCurrentInstruction(Increment(stmt))
            }
        }
    }

}