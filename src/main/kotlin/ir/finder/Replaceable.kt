package ir.finder

import ir.statement.Statement


class Replaceable<T : Statement>(val statement: T, val replace: (T) -> Unit)