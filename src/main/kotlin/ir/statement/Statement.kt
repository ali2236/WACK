package ir.statement

import ir.finder.Visitable

interface Statement : Visitable {
    fun write(out: Appendable)

}