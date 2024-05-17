package ir.finder

import ir.expression.Expression
import ir.expression.Symbol
import ir.statement.Statement

object Finders {
    fun symbols(statement: Statement): List<Symbol> {
        return ExpressionFinder(Symbol::class.java).apply { visit(statement, null) }.result()
    }
    fun expressions(stmt: Statement): List<Replaceable<Expression>> {
        return ReplaceableFinder(Expression::class.java).apply { visit(stmt, null) }.result()
    }
}