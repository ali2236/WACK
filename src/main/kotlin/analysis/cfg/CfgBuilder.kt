package analysis.cfg

import ir.statement.*
import ir.statement.Function
import java.util.*

class CfgBuilder(val function: Function) {

    private var start = 0
    private val blocks = Stack<CfgNode>()

    private val currentBlock: CfgNode
        get() = blocks.peek()

    fun build() : CFG {
        val body = function.instructions
        pushBlock(null, body, mutableListOf())
        removeEmptyBlocks()
        return CFG(blocks)
    }

    private fun pushBlock(name: String?, instructions: MutableList<Statement>, next: MutableList<Statement>): CfgNode {
        val newBlock = CfgNode(blocks.size, name)
        if(blocks.isNotEmpty()){
            currentBlock.successors.add(newBlock.id)
        }
        blocks.add(newBlock)
        for ((i, stmt) in instructions.withIndex()) {
            // elements that changes control flow: Block, If, Br, Br_if
            when (stmt) {
                is Loop -> {
                    pushBlock("loop", stmt.instructions, instructions.after(i, next))

                }
                is BrIf -> {
                    val node = pushBlock("if(${stmt.condition})", mutableListOf(), instructions.after(i, next))
                    node.successors.add(blocks[node.id - stmt.depth - 1].id)
                }

                is Br -> {
                    val node = pushBlock("Br ${stmt.depth}", mutableListOf(), instructions.after(i, next))
                    node.successors.add(blocks[node.id - stmt.depth - 1].id)
                }

                is Block -> {
                    pushBlock(null, stmt.instructions, instructions.after(i, next))
                }

                else -> {
                    currentBlock.statements.add(stmt)
                }
            }
        }
        if(next.isNotEmpty()){
            pushBlock(null, next, mutableListOf())
        }
        return newBlock
    }

    private fun removeEmptyBlocks(){
        for (i in 0 until blocks.size){
            val block = blocks[i]
            val empty = block.statements.isEmpty()
            val singleOut = block.successors.size <= 1
            if(i == start && empty){
                start++
            } else if(empty && singleOut){
                for (otherBlock in blocks){
                    if(otherBlock.successors.contains(i)){
                        otherBlock.successors.remove(i)
                        otherBlock.successors.addAll(block.successors)
                    }
                }

            }
        }
    }
}

private fun MutableList<Statement>.after(i: Int, leftOver: List<Statement>): MutableList<Statement> {
    val next = this.subList(i + 1, this.size).toMutableList()
    this.removeAll(next)
    return (next + leftOver).toMutableList()
}
