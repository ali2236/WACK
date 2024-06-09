package analysis.ddt

import ir.expression.Expression
import ir.finder.Visitor
import ir.statement.AssignmentStore
import ir.statement.Statement
import ir.statement.SymbolLoad

class AccessFinder : Visitor() {

    private val accesses = mutableListOf<Access>()

    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when(v){
            is AssignmentStore -> {
                val access = Access(
                    v.assignedTo(),
                    AccessType.Write,
                )
                accesses.add(access)
                visit(v.assignedWith()){v.replaceAssign(it as Expression)}
                return
            }
            is SymbolLoad -> {
                val access = Access(v, AccessType.Read)
                accesses.add(access)
            }
        }
        super.visit(v, replace)
    }

    fun result() : List<Access>{
        return accesses
    }
}