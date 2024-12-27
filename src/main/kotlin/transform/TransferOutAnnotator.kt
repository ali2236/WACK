package transform

import analysis.duc.DefUseChain
import analysis.duc.StatementDefFinder
import ir.annotations.TransferOut
import ir.expression.Symbol
import ir.statement.Program

class TransferOutAnnotator : Transformer {
    override fun apply(program: Program) {
        program.allNonSkipFunctions().forEach { function ->
            val parallelLoops = function.allNonSkipRangeLoops().parallelFor()
            if (parallelLoops.isNotEmpty()) {
                val chain = DefUseChain.from(function)
                // check if any symbols are used after parallel loop
                for (loop in parallelLoops) {
                    // get all def statement in loop
                    val finder = StatementDefFinder(chain).apply { visit(loop) {} }
                    val defs = finder.defs()
                    val loopStatements = finder.statements()
                    val usesWithDefsFromLoop = defs.flatMap { def ->
                        def.definitions.filter { loopStatements.contains(it.statement) }
                    }
                    val usesOutsideOfLoop = usesWithDefsFromLoop.filterNot { loopStatements.contains(it.statement) }
                    if (usesOutsideOfLoop.isNotEmpty()) {
                        val symbols = usesOutsideOfLoop.map { it.symbol }.filterIsInstance<Symbol>().toSet()
                        symbols.forEachIndexed { index, symbol ->
                            loop.annotations.add(TransferOut(symbol, index))
                        }
                    }
                }
            }
        }
    }
}