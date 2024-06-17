package optimization.restructure.archive

import ir.annotations.Skip
import ir.expression.Load
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Block
import ir.statement.ConditionLoop
import ir.statement.Function
import ir.statement.Program
import optimization.Optimizer

@Deprecated("doesn't work")
class AliasLoopLoadSymbol : Optimizer {
    override fun apply(program: Program) {
        program.statements
            .filter { it is Function && !it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                aliasTopLevelLoopSymbols(function as Function)
            }
    }

    fun aliasTopLevelLoopSymbols(block: Block) {
        val topLevelLoops = BreadthFirstExpressionFinder(ConditionLoop::class.java)
            .also { it.visit(block) {} }
            .result()
        topLevelLoops.forEach { aliasTopLevelLoopSymbols(it) }
        val loopAndSymbol = topLevelLoops
            .map { Pair(it, it.findSymbols()) }
            .filter { it.second.size == 1 }
            .map { Pair(it.first, it.second.single()) }
            .filter { it.second is Load && (it.second as Load).memorySize == null && (it.second as Load).sign == null }
        for ((loop, loadSymbol) in loopAndSymbol) {

        }
    }

}