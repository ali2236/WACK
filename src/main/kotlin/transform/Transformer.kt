package transform

import ir.statement.Program

interface Transformer {
    fun apply(program: Program)
}