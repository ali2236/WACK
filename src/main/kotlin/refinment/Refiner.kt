package refinment

import ast.expression.Block
import ast.expression.Expression
import ast.statement.Function
import ast.statement.Program
import java.util.*

abstract class Refiner {
    lateinit var currentProgram: Program
    lateinit var currentFunction : Function
    private var blocks = Stack<Block>()
    val parentBlock: Block
        get() = blocks.elementAt(blocks.size - 2)
    val currentBlock: Block
        get() = blocks.peek()

    var currentBlockIndex : Int? = null
    var currentInstrIndex : Int? = null
    open fun run(program: Program) {
        currentProgram = program
        program.statements
            .filterIsInstance<Function>()
            .forEach(this::refineFunction)
    }

    open fun refineFunction(function: Function) {
        currentFunction = function
        blocks.push(function.body)
        refineBlock(function.body)
    }

    open fun refineBlock(block: Block) {

        // call every instruction
        for (i in 0 until block.instructions.size) {
            currentInstrIndex = i
            val expr = block.instructions[i]
            refineInstruction(expr)
        }

        // call every sub-block
        val subBlocks = block.instructions
            .withIndex()
            .filter { it.value is Block }
            .filterIsInstance<IndexedValue<Block>>()

        for ((i, subBlock) in subBlocks) {
            blocks.push(subBlock)
            currentBlockIndex = i
            refineBlock(subBlock)
            blocks.pop()
        }
    }

    open fun refineInstruction(expr: Expression){}
    fun replaceCurrentBlock(block: Block){
        assert(currentBlockIndex != null)
        parentBlock.instructions[currentBlockIndex!!] = block
    }
    fun replaceCurrentInstruction(expr: Expression){
        assert(currentInstrIndex != null)
        currentBlock.instructions[currentInstrIndex!!] = expr
    }
}