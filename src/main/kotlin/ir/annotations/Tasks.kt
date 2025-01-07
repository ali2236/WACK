package ir.annotations

import generation.wat.WatWriter
import ir.expression.Expression
import ir.finder.Visitor

class Tasks(val maxTasks: Expression) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("@tasks(")
        wat.endLine()
        maxTasks.wat(wat)
        wat.write(")")
    }

    override fun visit(v: Visitor) {

    }
}