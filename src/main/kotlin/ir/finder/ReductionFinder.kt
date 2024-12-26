package ir.finder

import ir.annotations.Reduction
import ir.expression.BinaryOP
import ir.statement.AssignmentStore
import ir.statement.Increment
import ir.statement.Statement

// patterns:
// AssignmentStore(<symbolLoad>, binaryOP(<OP>,<expression>,<symbol>))
// AssignmentStore(<symbolLoad>, binaryOP(<OP>,<symbol>,<expression>))
class ReductionFinder : Visitor() {
    private val reductions = mutableListOf<Reduction>()

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when(v){
            is Increment -> {
                return
            }
            is AssignmentStore -> {
                val symbol = v.assignedTo()
                if (v.assignedWith() is BinaryOP){
                    val binOp = v.assignedWith() as BinaryOP
                    if (binOp.left == symbol || binOp.right == symbol){
                        val op = binOp.operator
                        val reduction = Reduction(symbol, op)
                        reductions.add(reduction)
                    }
                }
            }
        }
        super.visit(v, replace)
    }

    fun result() : List<Reduction> {
        return reductions
    }
}