package analysis.dfa

import ir.statement.Function
import ir.statement.Statement

class DfaBuilder(val function: Function) {

    lateinit var rootNode : DfaNode

    fun build(){

    }

    private fun makeDfaNode(stmts: List<Statement>) : DfaNode{
        TODO()
    }
}