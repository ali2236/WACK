package restructure

import ir.expression.*
import ir.statement.AssignmentStore
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
            if (
                (opr.right is Value && (opr.right as Value).value == "1" && opr.left == stmt.assignedTo())
            ) {
                val v = opr.right as Value
                if (opr.operator == BinaryOP.Operator.add) {
                    replaceCurrentInstruction(Increment(stmt, BinaryOP.Operator.add))
                } else if (opr.operator == BinaryOP.Operator.sub) {
                    replaceCurrentInstruction(Increment(stmt, BinaryOP.Operator.sub))
                }
            }
        }
    }

}