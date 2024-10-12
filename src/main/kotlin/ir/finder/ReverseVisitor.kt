package ir.finder

import ir.statement.Block

class ReverseVisitor {
    var stop = false
    fun searchInParentBlocks(block: Block, visitor: Visitor){
        if (block.parent == null || block.indexInParent == null || stop){
            return
        }
        val parent = block.parent
        for (i in block.indexInParent!! downTo 0){
            if (stop) return
            val stmt = parent!!.instructions[i]
            visitor.visit(stmt) { parent.instructions[i] = it }
        }
        searchInParentBlocks(parent!!, visitor)
    }
}