package analysis.dfa

import ir.statement.Statement

class DfaNode(
    val content: Statement,
    val IN: MutableList<DfaNode> = mutableListOf(),
    val OUT: MutableList<DfaNode> = mutableListOf(),
    val successors : () -> List<DfaNode>
)