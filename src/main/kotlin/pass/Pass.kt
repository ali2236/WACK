package pass

import ir.statement.Program

interface Pass {
    fun apply(program: Program)
}