package ir.finder

import analysis.dfa.FactsFinder
import analysis.dfa.StatementFactsFinder
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.UnaryOP
import ir.expression.Value
import ir.statement.*
import java.util.*

class CostFinder(val finder: StatementFactsFinder) : Visitor() {

    private var multiplier = 1
    private var cost = 0L
    private var symbolicCost = Value.zero

    fun guesstimate(stmt: Statement): Pair<Long, Expression> {
        cost = 0L
        symbolicCost = Value.zero
        visit(stmt){}
        return Pair(cost, symbolicCost)
    }

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when (v) {
            is RangeLoop -> {
                if(v.range.to is Value){
                    increaseMultiplier((v.range.to as Value).toInt()) {
                        super.visit(v, replace)
                    }
                } else {
                    terminate()
                }
                return
            }

            is FunctionCall, is IndirectFunctionCall -> {
                add(5)
            }

            is Store -> {
                add(2)
            }

            is UnaryOP -> {
                if (v.operator == UnaryOP.Operator.sqrt) {
                    add(2)
                } else {
                    add(1)
                }
            }

            is BinaryOP -> {
                if (v.operator == BinaryOP.Operator.mul) {
                    add(2)
                } else if (v.operator == BinaryOP.Operator.div) {
                    add(5)
                } else if (v.operator == BinaryOP.Operator.rem) {
                    add(5)
                } else {
                    add(1)
                }
            }

            else -> {
                add(1)
            }
        }
        super.visit(v, replace)
    }

    private fun terminate(){
        cost = UNKNOWN
    }

    private fun add(n: Int) {
        cost += (n * multiplier)
    }

    private fun increaseMultiplier(increase: Int, block: () -> Unit) {
        multiplier += increase
        block()
        multiplier -= increase
    }

    companion object {
        val UNKNOWN: Long = -1L
    }
}