package ir.statement

import generation.WebAssemblyInstruction
import ir.finder.Visitable

interface Statement : Visitable, WebAssemblyInstruction {

    var id: Long?
    fun write(out: Appendable)

    companion object {

        private var statementId : Long = 0L

        fun newId() : Long {
            return statementId++
        }

        fun lastId(): Long = statementId - 1
    }
}