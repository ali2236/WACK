package refinment

import ir.statement.Block
import ir.expression.Expression
import ir.statement.Function
import ir.statement.Program
import ir.statement.Statement
import java.util.*

abstract class Refiner {
    lateinit var currentProgram: Program
    lateinit var currentFunction: Function
    private var blocks = Stack<Block>()
    val currentBlock: Block
        get() = blocks.peek()

    var currentInstrIndex: Int? = null
    open fun run(program: Program) {
        currentProgram = program
        val functions = program.statements.filterIsInstance<Function>()
        for (function in functions) {
            currentFunction = function
            refineFunction(function)
        }
    }

    open fun refineFunction(function: Function) {
        blocks.push(function)
        refineBlock(function)
        blocks.pop()
    }

    open fun refineBlock(block: Block) {

        // call every instruction
        for (i in 0 until block.instructions.size) {
            currentInstrIndex = i
            val stmt = block.instructions[i]
            refineInstruction(stmt)
        }

        // call every sub-block
        val subBlocks = block.instructions
            .withIndex()
            .filter { it.value is Block }
            .filterIsInstance<IndexedValue<Block>>()

        for ((i, subBlock) in subBlocks) {
            blocks.push(subBlock)
            refineBlock(subBlock)
            blocks.pop()
        }
    }

    open fun refineInstruction(stmt: Statement) {}
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