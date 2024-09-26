package transform.restructure

import ir.expression.*
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
            val operatorIsAdd = opr.operator == BinaryOP.Operator.add
            val operatorIsSub = opr.operator == BinaryOP.Operator.sub
            if ((opr.right is Value && operatorIsAdd && (opr.right as Value).value == "1" && opr.left == stmt.assignedTo())) {
                replaceCurrentInstruction(Increment.plus(stmt))
            } else if ((opr.left is Value && operatorIsAdd && (opr.left as Value).value == "1" && opr.right == stmt.assignedTo())) {
                replaceCurrentInstruction(Increment.plus(stmt))
            } else  if ((opr.right is Value && operatorIsSub && (opr.right as Value).value == "1" && opr.left == stmt.assignedTo())) {
                replaceCurrentInstruction(Increment.minus(stmt))
            } else if ((opr.left is Value && operatorIsSub && (opr.left as Value).value == "1" && opr.right == stmt.assignedTo())) {
                replaceCurrentInstruction(Increment.minus(stmt))
            }
        }
    }

}