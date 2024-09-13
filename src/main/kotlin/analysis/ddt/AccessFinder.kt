package analysis.ddt

import analysis.dfa.Dfa
import ir.expression.Expression
import ir.expression.Load
import ir.expression.Symbol
import ir.finder.Visitor
import ir.statement.AssignmentStore
import ir.statement.RangeLoop
import ir.statement.Statement
import ir.statement.SymbolLoad
import java.util.Stack

class AccessFinder(parentScope: RangeLoop, val dfa: Dfa) : Visitor() {

    private val accesses = mutableListOf<Access>()
    private val scope = Stack<RangeLoop>()
    private val subLoops = mutableListOf<RangeLoop>()
    private val finder = dfa.finder()
    private var currentStatement: Statement? = null

    init {
        visit(parentScope) {}
    }

    // depth first
    // push scope on range loop
    // ignore loop symbols
    // pop after visiting children
    override fun visit(v: Statement, replace: (Statement) -> Unit) {
        when (v) {
            is RangeLoop -> {
                scope.push(v)
                subLoops.add(v)
                super.visit(v, replace)
                scope.pop()
                return
            }

            is AssignmentStore -> {
                val access = Access(
                    v.assignedTo(),
                    AccessType.Write,
                    AccessScope(scope),
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
                        AccessScope(scope),
                        finder.at(currentStatement!!) ?: setOf(),
                    )
                    accesses.add(access)
                    return // dont break it down further
                } else {
                    val access = Access(
                        v,
                        AccessType.Read,
                        AccessScope(scope),
                        finder.at(currentStatement!!) ?: setOf(),
                    )
                    accesses.add(access)
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

    fun subLoops(): List<RangeLoop> {
        return subLoops
    }

    fun accesses(): List<Access> {
        return accesses
    }
}