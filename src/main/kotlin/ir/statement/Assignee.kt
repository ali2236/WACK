package ir.statement

import ir.expression.Expression

// Assignment, store
interface Assignee : Statement{
    fun assignedWith() : Expression
    fun assignedTo() : Assignable

    fun replaceAssign(newValue: Expression)
}