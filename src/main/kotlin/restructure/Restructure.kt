package restructure

import ir.statement.Block
import ir.statement.Function
import ir.statement.Program
import ir.statement.Statement
import optimization.Optimizer
import java.util.*

abstract class Restructure : Optimizer {
    lateinit var currentProgram: Program
    lateinit var currentFunction: Function
    protected var blocks = Stack<Block>()
    val currentBlock: Block
        get() = blocks.peek()

    var currentInstrIndex: Int? = null
    override fun apply(program: Program) {
        currentProgram = program
        val functions = program.statements.filterIsInstance<Function>()
        for (function in functions) {
            currentFunction = function
            restructureFunction(function)
        }
    }

    open fun restructureFunction(function: Function) {
        blocks.push(function)
        restructureBlock(function)
        blocks.pop()
    }

    open fun restructureBlock(block: Block) {

        // call every instruction
        for (i in 0 until block.instructions.size) {
            currentInstrIndex = i
            val stmt = block.instructions[i]
            restructureInstruction(stmt)
        }

        // call every sub-block
        val subBlocks = block.instructions
            .withIndex()
            .filter { it.value is Block }
            .filterIsInstance<IndexedValue<Block>>()

        for ((i, subBlock) in subBlocks) {
            blocks.push(subBlock)
            restructureBlock(subBlock)
            blocks.pop()
        }
    }

    open fun restructureInstruction(stmt: Statement) {}
    fun replaceCurrentBlock(block: Block) {
        block.parent = currentBlock.parent
        block.indexInParent = currentBlock.indexInParent
        currentBlock.parent!!.instructions[currentBlock.indexInParent!!] = block
    }

    fun replaceCurrentInstruction(stmt: Statement) {
        assert(currentInstrIndex != null)
        currentBlock.instructions[currentInstrIndex!!] = stmt
    }
}