package analysis.dfa

import ir.statement.Statement

class DfaNode(
    val content: MutableList<Statement> = mutableListOf(),
    val IN: MutableList<DfaNode> = mutableListOf(),
    val OUT: MutableList<DfaNode> = mutableListOf(),
)