package ir.finder

import ir.statement.Statement

class ReplaceableFinder<T : Statement>(
    private val clazz: Class<T>,
) :
    Visitor() {

    private val expr = mutableListOf<Replaceable<T>>()

    override fun visit(v: Statement, replace: ((Statement) -> Unit)?) {
        if (v.javaClass == clazz && replace != null) {
            expr.add(Replaceable(v as T, replace))
        }
        super.visit(v, replace)
    }

    fun result(): List<Replaceable<T>>{
        return expr
    }

}