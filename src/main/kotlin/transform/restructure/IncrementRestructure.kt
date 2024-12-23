package transform.restructure

import ir.annotations.Skip
import ir.expression.*
import ir.finder.ReplaceableFinder
import ir.statement.*
import ir.statement.Function
import transform.Transformer

// check for "<symbol> = <symbol> + 1" pattern
// replace with <symbol>++
class IncrementRestructure : Transformer {

    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>()
            .filterNot { it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                val assignmentStores = ReplaceableFinder(AssignmentStore::class.java).also { function.visit(it) }.result()
                assignmentStores.forEach { checkAssignmentStyleIncrement(it.statement, it.replace) }
            }
    }

    private fun checkAssignmentStyleIncrement(stmt: AssignmentStore, replace: (Statement) -> Unit) {
        try {
            if (stmt.assignedWith() is BinaryOP) {
                val opr = stmt.assignedWith() as BinaryOP
                val operatorIsAdd = opr.operator == BinaryOP.Operator.add
                val operatorIsSub = opr.operator == BinaryOP.Operator.sub
                if ((opr.right is Value && operatorIsAdd && (opr.right as Value).value == "1" && opr.left == stmt.assignedTo())) {
                    replace(Increment.plus(stmt))
                } else if ((opr.left is Value && operatorIsAdd && (opr.left as Value).value == "1" && opr.right == stmt.assignedTo())) {
                    replace(Increment.plus(stmt))
                } else if ((opr.right is Value && operatorIsSub && (opr.right as Value).value == "1" && opr.left == stmt.assignedTo())) {
                    replace(Increment.minus(stmt))
                } else if ((opr.left is Value && operatorIsSub && (opr.left as Value).value == "1" && opr.right == stmt.assignedTo())) {
                    replace(Increment.minus(stmt))
                }
            }
        } catch (e: Exception){}
    }

}