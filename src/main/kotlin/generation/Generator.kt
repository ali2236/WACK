package generation

import ir.statement.Program

interface Generator {
    fun apply(program: Program)
}