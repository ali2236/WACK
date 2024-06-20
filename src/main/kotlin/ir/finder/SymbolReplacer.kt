package ir.finder

import ir.expression.Load
import ir.expression.Symbol
import ir.statement.Assignment
import ir.statement.Statement
import ir.statement.Store
import ir.statement.SymbolLoad

class SymbolReplacer(val replaceable : Map<SymbolLoad, Symbol>) : Visitor() {
    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        super.visit(v, replace)
        when(v){
            is SymbolLoad -> {
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