package ir.expression

import generation.WatWriter
import ir.statement.Assignable
import kotlin.math.exp

open class TeeValue(val expr: Expression) : Expression() {
    override fun write(out: Appendable) {
        expr.write(out)
    }

    override fun wat(wat: WatWriter) {
       // dont pass
    }
}