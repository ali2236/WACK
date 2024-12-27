package ir.finder

import analysis.ddt.AccessFinder
import analysis.dfa.DfaFact
import analysis.dfa.DfaValue
import analysis.dfa.StatementFactsFinder
import ir.annotations.Reduction
import ir.expression.BinaryOP
import ir.expression.Load
import ir.expression.Symbol
import ir.statement.*

// patterns:
// AssignmentStore(<symbolLoad>, binaryOP(<OP>,<expression>,<symbol>))
// AssignmentStore(<symbolLoad>, binaryOP(<OP>,<symbol>,<expression>))
class ReductionFinder(val finder: StatementFactsFinder, val loop: RangeLoop) : Visitor() {
    private val reductions = mutableListOf<Reduction>()

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when (v) {
            is Increment -> {
                return
            }

            is Assignment -> {
                val symbol = v.assignedTo()
                if (v.assignedWith() is BinaryOP) {
                    val binOp = v.assignedWith() as BinaryOP
                    if (binOp.left == symbol || binOp.right == symbol) {
                        val op = binOp.operator
                        val reduction = Reduction(symbol, op)
                        reductions.add(reduction)
                    }
                }
            }

            is Store -> {
                val symbol = v.assignedTo() as Load

                var reduction: Reduction? = null
                if (v.assignedWith() is BinaryOP) {
                    val binOp = v.assignedWith() as BinaryOP
                    if (binOp.left == symbol || binOp.right == symbol) {
                        val op = binOp.operator
                        reduction = Reduction(symbol, op)
                    }
                }

                // check if address is changing in each iteration
                if (reduction != null) {
                    val address = symbol.address
                    if (address is Symbol) {
                        val alias = finder.at(v)?.firstOrNull { it.symbol == address }?.value
                        if (alias is DfaValue.Alias && alias.expr != null) {
                            val access =
                                AccessFinder(finder).apply { visitExpression(alias.expr, loop, v) }.loopSymbolAccesses()
                            if (access.isNotEmpty()) {
                                reduction = null
                            }
                        }
                    }
                }

                reduction?.let {
                    reductions.add(reduction)
                }
            }
        }
        super.visit(v, replace)
    }

    fun result(): List<Reduction> {
        return reductions
    }
}