package ir

import ir.expression.Expression
import ir.statement.Statement


class ChildExpression(val statement: Statement, val replace: (Expression) -> Unit)