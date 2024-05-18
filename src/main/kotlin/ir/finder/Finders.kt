package ir.finder

import ir.expression.Expression
import ir.expression.Symbol
import ir.statement.Statement

object Finders {
    fun symbols(statement: Statement): List<Symbol> {
        return ExpressionFinder(Symbol::class.java).also { statement.visit(it) }.result()
    }
    fun symbols(statements: List<Statement>): List<Symbol> {
        return ExpressionFinder(Symbol::class.java).also { v -> statements.forEach{ s -> s.visit(v) } }.result()
    }
    fun expressions(statement: Statement): List<Replaceable<Expression>> {
        return ReplaceableFinder(Expression::class.java).also { statement.visit(it) }.result()
    }
}