package ir.statement

import generation.WatWriter
import ir.expression.Expression

class BrTable(val selector: Expression, val depths : List<Int>) : BasicStatement() {

    override fun write(out: Appendable) {
        out.append("switch(")
        selector.write(out)
        out.append("){\n")
        depths.subList(0, depths.size-1).forEachIndexed { index, i ->
            out.append("case $index => br $i,\n")
        }
        depths.last().also {
            out.append("else => br $it\n")
        }
        out.append("}\n")
    }

    override fun wat(wat: WatWriter) {
        selector.wat(wat)
        wat.writeLine("br_table ${depths.joinToString(" ")}")
    }
}