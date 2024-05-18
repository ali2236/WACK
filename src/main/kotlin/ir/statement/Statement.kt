package ir.statement

import generation.WebAssemblyInstruction
import ir.finder.Visitable

interface Statement : Visitable, WebAssemblyInstruction {
    fun write(out: Appendable)

}