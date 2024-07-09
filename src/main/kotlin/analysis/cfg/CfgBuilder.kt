package analysis.cfg

import ir.statement.FunctionCall
import ir.statement.IndirectFunctionCall
import ir.statement.*
import ir.statement.Function
import java.util.*

internal class CfgBuilder(val function: Function) {

    private val blocks = Stack<CfgBlock>()
    private val scope = Stack<CfgBlock>()

    private val startBlock = makeBlock("START")
    private val endBlock = makeBlock("END")
    private val previousBlock: CfgBlock
        get() = blocks.elementAt(blocks.size - 2)
    private val currentBlock: CfgBlock
        get() = blocks.last()
    private val currentScope: CfgBlock
        get() = scope.last()

    /*
    function f_xd() {
  var a:int;
  88860[0]:int = (a = (a = 88724[0]:int) - (a - 27089280) / 65);
  88868[0]:int = a - 27089344 >> 4;
}
     */
    fun build(): CFG {
        val body = function.instructions
        val function = makeBlock()
        startBlock.addSuccessor(function)
        pushScope(function, endBlock, endBlock)
        runOnBlock(function, body)
        popScope()
        removeEmptyBlocks()
        return CFG(blocks)
    }

    private fun makeBlock(label: String? = null): CfgBlock {
        val block = CfgBlock(blocks.size, label = label)
        blocks.add(block)
        return block
    }

    private fun pushScope(block: CfgBlock, br: CfgBlock?, next: CfgBlock?) {
        block.next = next
        block.br = br
        scope.push(block)
    }

    private fun popScope() {
        scope.pop()
    }

    private fun makeNext(i: Int, instructions: List<Statement>): CfgBlock {
        val after = instructions.after(i)
        if (after.isNotEmpty()) {
            val next = makeBlock()
            runOnBlock(next, after)
            return next
        } else if (currentScope.next != null) {
            return currentScope.next!!
        } else {
            throw Error()
        }
    }

    private fun runOnBlock(
        current: CfgBlock,
        instructions: List<Statement>,
    ) {
        if (instructions.isEmpty()) {
            current.addSuccessor(currentScope.next)
        }
        for ((i, stmt) in instructions.withIndex()) {
            when (stmt) {
                is BrTable -> {
                    val brBlock = makeBlock("br_table")
                    current.addSuccessor(brBlock)

                    stmt.depths.forEachIndexed { i, depth ->
                        if (i != stmt.depths.size - 1){
                        val case = makeBlock()
                        case.addSuccessor(scope[scope.size - depth - 1].br, "$i")
                        brBlock.addSuccessor(case)
                        } else {
                            val default = makeBlock()
                            val brTarget = scope[scope.size - depth - 1].br
                            default.addSuccessor(brTarget, "else")
                            brBlock.addSuccessor(default)
                        }
                    }

                    // no next
                }

                is Return, is Unreachable -> {
                    current.statements.add(stmt)
                    current.addSuccessor(endBlock)
                }

                is FunctionCall, is IndirectFunctionCall -> {
                    val next = makeNext(i, instructions)

                    current.statements.add(stmt)
                    current.addSuccessor(next)
                }

                is BrIf -> {
                    // next
                    val next = makeNext(i, instructions)

                    // body
                    val block = makeBlock(stmt.printHeader())
                    block.addSuccessor(scope[scope.size - stmt.depth - 1].br, "True")
                    current.addSuccessor(block)
                    block.statements.add(stmt)

                    // false
                    block.addSuccessor(next, "False")
                }

                is If -> {
                    // next
                    val next = makeNext(i, instructions)

                    val block = makeBlock(stmt.printHeader())
                    pushScope(block, next, next)
                    block.statements.add(stmt)

                    // true
                    val trueBody = makeBlock()
                    runOnBlock(trueBody, stmt.trueBody)
                    block.addSuccessor(trueBody, "True")

                    // false
                    if (stmt.elseBody != null) {
                        val falseBody = makeBlock()
                        runOnBlock(falseBody, stmt.elseBody!!)
                        block.addSuccessor(falseBody, "False")
                        popScope()
                    } else {
                        popScope()
                        // go to next
                        block.addSuccessor(next, "False")
                    }

                    current.addSuccessor(block)
                }

                is Br -> {
                    currentBlock.statements.add(stmt)
                    val target = scope[scope.size - stmt.depth - 1].br!!
                    current.addSuccessor(target, "Jump")
                }

                is Loop -> {
                    // next
                    val next = makeNext(i, instructions)

                    // loop
                    val headBlock = makeBlock(stmt.printHeader())
                    pushScope(headBlock, headBlock, next)
                    current.addSuccessor(headBlock)
                    headBlock.statements.add(stmt)

                    // body
                    val bodyBlock = makeBlock()
                    runOnBlock(bodyBlock, stmt.instructions)
                    headBlock.addSuccessor(bodyBlock)

                    // end
                    popScope()
                }

                is Block -> {
                    // next
                    val next = makeNext(i, instructions)

                    // current
                    val block = makeBlock(if(stmt.instructions.isEmpty()) "Block" else null)
                    pushScope(block, next, next)
                    runOnBlock(block, stmt.instructions)
                    current.addSuccessor(block)
                    //block.statements.add(stmt)

                    // end
                    popScope()
                }

                else -> {
                    current.statements.add(stmt)
                    continue
                }
            }
            return
        }
        // instructions ended with no next
        if(current.next == null){
            current.addSuccessor(currentScope.next)
        } else {
            current.addSuccessor(current.next)
        }
    }

    private fun removeEmptyBlocks() {
        for (i in 2 until blocks.size) {
            val block = blocks[i]
            if (!block.valid) {
                for (otherBlock in blocks) {
                    if (otherBlock.successors.map { it.target }.contains(i)) {
                        otherBlock.successors.removeIf { it.target == i }
                        otherBlock.successors.addAll(block.successors)
                    }
                }

            }
        }
    }
}

private fun List<Statement>.after(i: Int): List<Statement> {
    return this.subList(i + 1, this.size)
}
