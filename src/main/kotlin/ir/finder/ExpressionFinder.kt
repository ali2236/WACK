package ir.finder

import ir.statement.Statement


class ExpressionFinder<T : Visitable>(
    private val clazz: Class<T>,
) :
    Visitor() {

    private val expr = mutableListOf<T>()

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        if (clazz.isInstance(v)) {
            @Suppress("UNCHECKED_CAST")
            expr.add(v as T)
        }
        super.visit(v, replace)
    }

    fun result(): List<T>{
        return expr
    }

}