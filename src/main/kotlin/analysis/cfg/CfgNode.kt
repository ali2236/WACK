package analysis.cfg

import ir.statement.Statement

class CfgNode(
    val id : Int,
    val label: String?,
    val statements: MutableList<Statement> = mutableListOf(),
    val successors: MutableList<Int> = mutableListOf(),
){
    val valid : Boolean
        get() = statements.isNotEmpty() || successors.size > 1

    override fun toString(): String {
        return label ?: "$id"
    }
}