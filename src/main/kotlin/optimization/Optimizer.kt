package optimization

import ir.statement.Program

interface Optimizer {
    fun apply(program: Program)
}