package optimization.restructure

import ir.expression.*
import ir.expression.Assignment
import ir.statement.Increment
import ir.statement.Statement

// check for "<symbol> = <symbol> + 1" pattern
// replace with <symbol>++
class IncrementRestructure : Restructure() {

    override fun restructureInstruction(stmt: Statement) {
        if (stmt is Assignment) {
            checkAssignmentStyleIncrement(stmt)
        }
    }

    private fun checkAssignmentStyleIncrement(stmt: Assignment) {
        if (stmt.assignedWith() is BinaryOP) {
            val opr = stmt.assignedWith() as BinaryOP
            if ((opr.right is Value && opr.operator == BinaryOP.Operator.add && (opr.right as Value).value == "1" && opr.left == stmt.assignedTo())) {
                replaceCurrentInstruction(Increment(stmt.symbol))
            }
        }
    }

}