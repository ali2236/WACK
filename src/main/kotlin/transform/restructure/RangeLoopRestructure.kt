package transform.restructure

import analysis.dfa.Dfa
import analysis.dfa.DfaValue
import analysis.dfa.StatementFactsFinder
import analysis.dfa.whatIs
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Value
import ir.finder.BreadthFirstExpressionFinder
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
                //println("range loop exception: $e")
            }
        }
    }

    private fun transformIntoRangeLoop(loop: ConditionLoop) {
        val condition = loop.condition
        val conditionSymbols = loop.findSymbols()
        if (condition is BinaryOP) {
            val symbol = conditionSymbols.first()

            // symbol initial value
            val initialConstValue = initialValueOf(loop.parent, loop.indexInParent, symbol)
            val initialRuntimeValue = initialExpressionOf(loop.parent, loop.indexInParent, symbol)
            val initial = initialRuntimeValue ?: initialConstValue ?: return

            // validate has Increment
            val increment = BreadthFirstExpressionFinder(Increment::class.java).also { loop.visit(it) }.result().firstOrNull { inc -> inc.stmt.assignedTo() == symbol }
            if (increment == null) {
                return
            }

            // symbol end value
              val end = condition.endInclusive(increment.direction)

              val rangeLoop = RangeLoop(symbol, DfaValue.Range(initial, end), condition, loop.instructions)
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
            val fact = functionFacts.at(block.instructions[i]).whatIs(symbol)
            if(fact != null){
                return fact.asValue()
            }
        }
        return initialValueOf(block.parent, block.indexInParent, symbol)
    }

    private fun initialExpressionOf(block: Block?, startIndex: Int?, symbol: SymbolLoad) : Expression?{
        if (block == null || startIndex == null){
            return null
        }
        if(block is Function && startIndex == 0){
            return null
        }
        for (i in (startIndex - 1) downTo 0){
            val instruction = block.instructions[i]
            when(instruction){
                is AssignmentStore -> {
                    if(instruction.assignedTo() == symbol){
                        return instruction.assignedWith()
                    }
                }
            }
        }
        return initialExpressionOf(block.parent, block.indexInParent, symbol)
    }

}
