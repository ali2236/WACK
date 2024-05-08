package refinment

import ir.statement.Program

interface Refiner {
    fun run(program: Program)

    companion object {
        fun refine(program: Program){
            val passes = listOf(
                IncrementRefiner(),
                WhileLoopRefiner(),
                ArrayRefiner(),
            )
            passes.forEach { it.run(program) }
        }
    }
}