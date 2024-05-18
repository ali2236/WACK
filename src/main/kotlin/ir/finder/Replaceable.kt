package ir.finder

import ir.statement.Statement


data class Replaceable<T : Statement>(val statement: T, val replace: (Statement) -> Unit)