package transform.restructure

import analysis.dfa.Dfa
import analysis.dfa.DfaValue
import analysis.dfa.StatementFactsFinder
import analysis.dfa.whatIs
import ir.annotations.Skip
import ir.expression.BinaryOP
import ir.expression.Expression
import ir.expression.Value
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.LoopFinder
import ir.finder.ReplaceableFinder
import ir.statement.*
import ir.statement.Function
import transform.Transformer
import kotlin.Exception

class RangeLoopRestructure : Transformer {

    private lateinit var function: Function
    private lateinit var functionDfa: Dfa
    private lateinit var functionFacts: StatementFactsFinder

    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>()
            .filterNot { it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                this.function = function
                val loops = LoopFinder(ConditionLoop::class.java, topLevelOnly = false).also { function.visit(it) }.result()
                if (loops.isNotEmpty()) {
                    functionDfa = Dfa.from(function)
                    functionFacts = functionDfa.finder()
                    functionFacts.addFact(function, functionDfa.nodes.first().OUT.facts)

                    loops.forEach {
                        try {
                            transformIntoRangeLoop(it.statement, it.replace)
                        } catch (e: Exception) {
                            //println("range-loop-restructure: $e")
                        }
                    }
                }
            }
    }

    private fun transformIntoRangeLoop(loop: ConditionLoop, replace: (Statement) -> Unit) {
        val condition = loop.condition
        val conditionSymbols = loop.findSymbols()
        if (condition is BinaryOP) {
            val symbol = conditionSymbols.first()

            // symbol initial value
            val initialConstValue = initialValueOf(loop.parent, loop.indexInParent, symbol)
            val initialRuntimeValue = initialExpressionOf(loop.parent, loop.indexInParent, symbol)
            val initializedValue = functionFacts.at(function).whatIs(symbol)?.asValue()
            val initial = initialRuntimeValue ?: initialConstValue ?: initializedValue ?: return

            // validate has Increment
            val increment = BreadthFirstExpressionFinder(Increment::class.java).also { loop.visit(it) }.result()
                .firstOrNull { inc -> inc.stmt.assignedTo() == symbol }
            if (increment == null) {
                return
            }

            // symbol end value
            val end = condition.endInclusive(increment.direction)

            val rangeLoop = RangeLoop(symbol, DfaValue.Range(initial, end), condition, loop.instructions)
            rangeLoop.parent = loop.parent
            rangeLoop.indexInParent = loop.indexInParent
            replace(rangeLoop)
            rangeLoop.instructions.forEachIndexed { i, stmt ->
                if (stmt is Block){
                    stmt.parent = rangeLoop
                    stmt.indexInParent = i
                }
            }
        }
    }

    private fun initialValueOf(block: Block?, startIndex: Int?, symbol: SymbolLoad): Value? {
        if (block == null || startIndex == null) {
            return null
        }
        if (block is Function && startIndex == 0) {
            return functionFacts.at(block).whatIs(symbol)?.asValue()
        }
        for (i in (startIndex - 1) downTo 0) {
            val fact = functionFacts.at(block.instructions[i]).whatIs(symbol)
            if (fact != null) {
                return fact.asValue()
            }
        }
        return initialValueOf(block.parent, block.indexInParent, symbol)
    }

    private fun initialExpressionOf(block: Block?, startIndex: Int?, symbol: SymbolLoad): Expression? {
        if (block == null || startIndex == null) {
            return null
        }
        if (block is Function && startIndex == 0) {
            return null
        }
        for (i in (startIndex - 1) downTo 0) {
            val instruction = block.instructions[i]
            when (instruction) {
                is AssignmentStore -> {
                    if (instruction.assignedTo() == symbol) {
                        return instruction.assignedWith()
                    }
                }
            }
        }
        return initialExpressionOf(block.parent, block.indexInParent, symbol)
    }

}
