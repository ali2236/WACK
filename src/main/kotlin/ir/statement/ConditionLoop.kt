package ir.statement

import ir.expression.BinaryOP
import ir.expression.Expression
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.ExpressionFinder
import ir.finder.Finders
import ir.finder.Visitor

open class ConditionLoop(var condition: Statement, instructions: MutableList<Statement>) : Loop(instructions) {

    override fun writeHeader(out: Appendable) {
        val symbols = findSymbols()
        out.append("condition-loop(${symbols.joinToString(", ")})")
    }

    fun findSymbols(): List<SymbolLoad> {
        return BreadthFirstExpressionFinder(SymbolLoad::class.java, true)
            .also { it.visit(condition) {} }.result()
    }
}