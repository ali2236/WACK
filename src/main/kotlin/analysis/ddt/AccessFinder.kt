package analysis.ddt

import analysis.ddg.Access
import analysis.ddg.AccessScope
import analysis.ddg.AccessType
import analysis.dfa.Dfa
import ir.expression.Expression
import ir.expression.Load
import ir.expression.Symbol
import ir.finder.Visitor
import ir.statement.*
import java.util.Stack

class AccessFinder(parentScope: RangeLoop, val dfa: Dfa) : Visitor() {

    private val accesses = mutableListOf<Access>()
    private val functionCalls = mutableListOf<Statement>() // <FunctionCall|IndirectFunctionCall>
    private val scope = Stack<RangeLoop>()
    private val subLoops = mutableListOf<RangeLoop>()
    private val finder = dfa.finder()
    private var currentStatement: Statement? = null
    private var dialias = DiAlias()

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
                val facts = finder.at(v)
                var assignedTo = v.assignedTo()
                if (assignedTo is Load){
                    // check for aliases in address
                     assignedTo = dialias.apply(assignedTo, facts) as SymbolLoad
                }
                val access = Access(
                    assignedTo,
                    AccessType.Write,
                    AccessScope(scope, currentStatement!!),
                    finder.at(v) ?: setOf()
                )
                accesses.add(access)
                currentStatement = v
                val unaliased = dialias.apply(v.assignedWith(), facts)
                visit(unaliased) {}
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

            is FunctionCall -> {
                functionCalls.add(v)
            }

            is IndirectFunctionCall -> {
                functionCalls.add(v)
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

    fun functionCalls(): List<Statement> {
        return functionCalls
    }
}