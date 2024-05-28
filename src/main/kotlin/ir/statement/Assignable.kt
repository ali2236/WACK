package ir.statement

import ir.expression.Expression


// symbol, load
interface Assignable : Statement {
    fun clone() : Expression
}