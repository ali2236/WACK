package ir.finder

import ir.statement.Statement

class BreadthFirstExpressionFinder<T : Visitable>(
    private val clazz: Class<T>,
    val stopOnTopHit : Boolean = false,
) :
    Visitor() {

    private val expr = mutableSetOf<T>()

    private val q = mutableListOf<Iterable<Statement>>()

    override fun visit(vs: Iterable<Statement>, replace: (Int, Statement) -> Unit) {
        q.add(vs)
    }

    private fun _visit(vs: Iterable<Statement>){
        vs.forEach { stmt -> visit(stmt) {} }
    }

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        if (clazz.isInstance(v)) {
            @Suppress("UNCHECKED_CAST")
            expr.add(v as T)
            if(stopOnTopHit){
                return
            }
        }
        super.visit(v, replace)
    }

    fun result(): List<T>{
        while (q.isNotEmpty()){
            _visit(q.removeFirst())
        }
        return expr.toList()
    }

}