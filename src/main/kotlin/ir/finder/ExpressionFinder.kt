package ir.finder


class ExpressionFinder<T : Visitable>(
    private val clazz: Class<T>,
) :
    Visitor() {

    private val expr = mutableListOf<T>()

    fun visit(vs: Iterable<Visitable>) : ExpressionFinder<T>{
        vs.forEach(::visit)
        return this
    }

    override fun visit(v: Visitable) : ExpressionFinder<T> {
        if (v.javaClass == clazz) {
          expr.add(v as T)
        }
        super.visit(v)
        return this
    }

    fun result(): List<T>{
        return expr
    }

}