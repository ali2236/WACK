package analysis.duc

import ir.finder.Visitor
import ir.statement.AssignmentStore
import ir.statement.Statement
import ir.statement.SymbolLoad

class DefUseFinder(val defUse: DefUseChain, val parentStatement: Statement) : Visitor() {

    init {
        when(parentStatement){
            is AssignmentStore -> {
                // def
                val chain = defUse.getSymbol(parentStatement.assignedTo())
                chain.addDef(parentStatement)

                // use
                visit(parentStatement.assignedWith()){}
            }
            else -> {
                // use
                visit(parentStatement){}
            }
        }
    }

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when(v){
            is SymbolLoad -> {
                // use
                val chain = defUse.getSymbol(v)
                chain.addUse(parentStatement)
            }
        }
        super.visit(v, replace)
    }
}