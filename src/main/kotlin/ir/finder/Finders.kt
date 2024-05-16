package ir.finder

import ir.expression.Symbol
import ir.statement.Statement

object Finders {
    fun symbols(statement: Statement): List<Symbol> {
        return ExpressionFinder(Symbol::class.java).visit(statement).result()
    }
    fun symbols(statement: List<Statement>): List<Symbol> {
        return ExpressionFinder(Symbol::class.java).visit(statement).result()
    }
}