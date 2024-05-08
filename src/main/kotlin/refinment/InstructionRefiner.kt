package refinment

import ir.expression.*

abstract class InstructionRefiner : BlockRefiner() {

    private lateinit var currentBlock : Block
    private var currentInstr : Int = 0

    override fun refineBlock(block: Block) {
        currentBlock = block
        super.refineBlock(block)
        currentBlock = block
        for (i in 0 until block.instructions.size) {
            currentInstr = i
            val expr = block.instructions[i]
            refineInstruction(expr)
        }
    }



    abstract fun refineInstruction(expr: Expression)

    fun replaceCurrent(expr: Expression){
        currentBlock.instructions[currentInstr] = expr
    }
}