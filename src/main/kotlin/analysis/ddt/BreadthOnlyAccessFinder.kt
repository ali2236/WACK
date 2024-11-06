package analysis.ddt

import analysis.ddg.Access
import analysis.ddg.AccessScope
import analysis.ddg.AccessType
import analysis.dfa.StatementFactsFinder
import ir.expression.Expression
import ir.expression.Load
import ir.expression.Symbol
import ir.finder.Visitor
import ir.statement.*
import java.util.*

class BreadthOnlyAccessFinder(val scope: Stack<RangeLoop>, val finder: StatementFactsFinder) : Visitor() {


    private val accesses = mutableListOf<Access>()
    private var currentStatement: Statement? = null

    init {
        visit(scope.peek()) {}
    }

    // breadth only
    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when (v) {
            is RangeLoop -> {
                if(v != scope.peek()){
                    return
                }
            }
            is Increment -> {
                return
            }
            is AssignmentStore -> {
                val access = Access(
                    v.assignedTo(),
                    AccessType.Write,
                    AccessScope(scope, currentStatement!!),
                    finder.at(v) ?: setOf()
                )
                accesses.add(access)
                currentStatement = v
                visit(v.assignedWith()) { v.replaceAssign(it as Expression) }
                return
            }

            is SymbolLoad -> {
                if (isLoopSymbol(v)) {
                    return
                } else if(isStackVariable(v)){
                    val access = Access(
                        v,
                        AccessType.Read,
                        AccessScope(scope, currentStatement!!),
                        finder.at(currentStatement!!) ?: setOf(),
                    )
                    accesses.add(access)
                    return // dont break it down further
                } else {
                    val access = Access(
                        v,
                        AccessType.Read,
                        AccessScope(scope, currentStatement!!),
                        finder.at(currentStatement!!) ?: setOf(),
                    )
                    accesses.add(access)
                    return // maybe dont go deeper
                }
            }
        }
        if (v !is Expression){
            currentStatement = v
        }
        super.visit(v, replace)
    }

    private fun isStackVariable(symbol: SymbolLoad): Boolean {
        if(symbol is Load && symbol.address is Symbol){
            return true
        } else {
            return false
        }
    }

    private fun isLoopSymbol(symbol: SymbolLoad): Boolean {
        for (loop in scope) {
            val symbols = loop.findSymbols()
            if (symbols.any { it == symbol }) {
                return true
            }
        }
        return false
    }

    fun accesses(): List<Access> {
        return accesses
    }
}