package ir.finder

import ir.expression.Load
import ir.expression.Symbol
import ir.statement.Assignment
import ir.statement.Statement
import ir.statement.Store

class LoadReplacer(val replaceable : Map<Load, Symbol>) : Visitor() {
    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        super.visit(v, replace)
        when(v){
            is Load -> {
                if(replaceable.containsKey(v)){
                    replace(replaceable[v]!!)
                }
            }
            is Store -> {
                if(replaceable.containsKey(v.symbol)){
                    val assignment = Assignment(replaceable[v.symbol]!!, v.data)
                    replace(assignment)
                }
            }
        }
    }
}