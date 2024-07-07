package transform.restructure

import analysis.dfa.Dfa
import analysis.dfa.DfaValue
import analysis.dfa.StatementFactsFinder
import analysis.dfa.whatIs
import ir.expression.BinaryOP
import ir.expression.Value
import ir.statement.*
import ir.statement.Function
import kotlin.Exception

class RangeLoopRestructure : Restructure() {

    private lateinit var functionDfa: Dfa
    private lateinit var functionFacts: StatementFactsFinder

    override fun restructureFunction(function: Function) {
        // dfa
        functionDfa = Dfa.from(function)
        functionFacts = functionDfa.finder()
        functionFacts.addFact(function, functionDfa.nodes.first().OUT.facts)
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
        val conditionSymbols = loop.findSymbols()
        if (condition is BinaryOP && conditionSymbols.size == 1) {
            val symbol = conditionSymbols.first()

            // symbol initial value
            val initial = initialValueOf(loop.parent, loop.indexInParent, symbol) ?: return

            // symbol end value
            val endExclusive = condition.endExclusive

            // TODO: validate has Increment
            // TODO: Normalize Increments
            /*val hasIncrement = loop.instructions.any { it is Increment }
            if (!hasIncrement) {
                return
            }*/

            val rangeLoop = RangeLoop(symbol, DfaValue.Range(initial, endExclusive), condition, loop.instructions)
            replaceCurrentBlock(rangeLoop)
        }
    }

    private fun initialValueOf(block: Block?, startIndex: Int?, symbol: SymbolLoad) : Value?{
        if (block == null || startIndex == null){
            return null
        }
        if(block is Function && startIndex == 0){
            return functionFacts.at(block).whatIs(symbol)?.asValue()
        }
        for (i in (startIndex - 1) downTo 0){
            val value = functionFacts.at(block.instructions[i]).whatIs(symbol)?.asValue()
            if(value != null){
                return value
            }
        }
        return initialValueOf(block.parent, block.indexInParent, symbol)
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
                "!=" -> (right as Value)
                else -> throw Exception()
            }
        }

}
