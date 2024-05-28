package ir.finder

import analysis.dfa.DfaFact
import ir.statement.Statement

class ConstantPropagationVisitor(val facts: Set<DfaFact>) : Visitor() {

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        super.visit(v, replace)
    }
}