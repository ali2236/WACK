package analysis.duc

import ir.finder.Visitor
import ir.statement.AssignmentStore
import ir.statement.Statement

class StatementDefFinder(val defUse: DefUseChain) : Visitor() {

    private val symbolUseDef = mutableListOf<SymbolUseDefChain>()
    private val symbolDefStatement = mutableListOf<Statement>()

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        if (v is AssignmentStore){
            val sud = defUse.getSymbol(v.assignedTo())
            symbolUseDef.add(sud)
            symbolDefStatement.add(v)
        }
        super.visit(v, replace)
    }

    fun defs() : List<SymbolUseDefChain>{
        return symbolUseDef.filter { it.definitions.isNotEmpty() }
    }

    fun statements() : Set<Statement>{
        return symbolDefStatement.toSet()
    }
}