package ir.statement

import ir.expression.Expression

// Assignment, store
interface AssignmentStore : Statement{
    fun assignedWith() : Expression
    fun assignedTo() : SymbolLoad

    fun replaceAssign(newValue: Expression)
}