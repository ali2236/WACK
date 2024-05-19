package ir.finder

import ir.statement.Statement

abstract class Visitor {

    open fun visit(vs: Iterable<Statement>, replace: (Int, Statement) -> Unit) {
        vs.forEachIndexed { i, stmt -> visit(stmt) { replace(i,it) } }
    }

    open fun visit(v: Statement, replace: (Statement) -> Unit) {
        v.visit(this)
    }
}

