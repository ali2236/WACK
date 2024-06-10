package restructure

import analysis.dfa.Dfa
import ir.expression.BinaryOP
import ir.expression.Value
import ir.finder.*
import ir.statement.*
import ir.statement.Function
import kotlin.Exception

class RangeLoopRestructure : Restructure() {

    private lateinit var functionDfa: Dfa
    private lateinit var functionFacts: StatementFactsFinder

    override fun restructureFunction(function: Function) {
        // dfa
        functionDfa = Dfa.from(function)
        functionFacts = StatementFactsFinder(functionDfa)
        super.restructureFunction(function)
    }

    override fun restructureBlock(block: Block) {
        super.restructureBlock(block)
        if (block is ConditionLoop) {
            try {
                transformIntoRangeLoop(block)
            } catch (e: Exception) {

            }
        }
    }

    private fun transformIntoRangeLoop(loop: ConditionLoop) {
        val condition = loop.condition
        val conditionSymbols = Finders.symbols(loop.condition)
        if (condition is BinaryOP && conditionSymbols.size == 1) {
            val symbol = conditionSymbols.first()

            // symbol initial value
            val initial = functionFacts.at(loop).whatIs(symbol)?.asValue() ?: return

            // symbol end value
            val endExclusive = condition.endExclusive

            // validate has Increment
            // TODO: Normalize Increments
            val hasIncrement = loop.instructions.any { it is Increment }
            if (!hasIncrement) {
                return
            }

            val rangeLoop = RangeLoop(symbol, initial, endExclusive, condition, loop.instructions)
            replaceCurrentBlock(rangeLoop)
        }
    }

    private val BinaryOP.endExclusive: Value
        get() {
            if (right !is Value) {
                throw Exception()
            }
            return when (operator.sign) {
                "<" -> right as Value
                "<=" -> (right as Value).add(1)
                ">" -> (right as Value).add(1)
                ">=" -> (right as Value)
                else -> throw Exception()
            }
        }

}
