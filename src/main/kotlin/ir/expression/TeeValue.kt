package ir.expression

import generation.WatWriter

open class TeeValue(val expr: Expression) : Expression() {
    override fun clone(): Expression {
        return TeeValue(expr.clone())
    }

    override fun write(out: Appendable) {
        expr.write(out)
    }

    override fun wat(wat: WatWriter) {
       // dont pass
    }
}