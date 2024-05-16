package ir.finder

abstract class Visitor {

    open fun visit(v: Visitable) : Visitor{
        v.visit(this)
        return this
    }
}

