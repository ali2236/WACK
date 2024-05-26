package analysis.cfg

import ir.statement.Statement

open class CfgBlock(
    val id : Int,
    val label: String?,
    val statements: MutableList<Statement> = mutableListOf(),
    val successors: MutableList<CfgEdge> = mutableListOf(),
    var next: CfgBlock? = null,
    var br: CfgBlock? = null
){
    val valid : Boolean
        get() = statements.isNotEmpty() || successors.size > 1 || label != null

    override fun toString(): String {
        return label ?: "$id"
    }

    fun addSuccessor(block: CfgBlock?, label: String? = null) {
        block?.let {
            successors.add(CfgEdge(label, block.id))
        }
    }
}