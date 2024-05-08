package refinment

import ir.expression.Block
import ir.statement.Function
import ir.statement.Program

abstract class BlockRefiner : Refiner {
    override fun run(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .forEach(this::refineFunction)
    }

    open fun refineFunction(function: Function) {
        refineBlock(function.body)
    }

    open fun refineBlock(block: Block) {
        block.instructions
            .filterIsInstance<Block>()
            .forEach(this::refineBlock)
    }
}