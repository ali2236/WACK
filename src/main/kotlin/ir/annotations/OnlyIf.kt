package ir.annotations

import generation.c.CWriter
import generation.wat.WatWriter
import ir.finder.Visitor
import ir.statement.Statement

class OnlyIf(val condition: Statement) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@if (")
        wat.endLine()
        wat.watDebug = false
        condition.wat(wat)
        wat.write(")")
        wat.watDebug = true
        wat.write(")")
    }

    override fun c(writer: CWriter) {
        writer.write("if(")
        condition.c(writer)
        writer.write(")")
    }

    override fun visit(v: Visitor) {

    }
}