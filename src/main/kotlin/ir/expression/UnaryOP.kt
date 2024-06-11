package ir.expression

import generation.WatWriter
import ir.finder.Visitor
import ir.wasm.WasmValueType

class UnaryOP(val type: WasmValueType, val operator: Operator, var value: Expression) : Expression() {
    override fun clone(): Expression {
        return UnaryOP(type, operator, value.clone())
    }

    override fun write(out: Appendable) {
        out.append(operator.sign)
        value.write(out)
    }

    override fun wat(wat: WatWriter) {
        value.wat(wat)
        wat.writeLine("${type}.${operator.wat()}")
    }

    override fun visit(v: Visitor) {
        v.visit(value) { this.value = it as Expression }
    }

    data class Operator(val sign: String, val watName: String) {

        companion object {
            val clz = Operator("clz:", "clz")
            val ctz = Operator("ctz:", "ctz")
            val popcnt = Operator("popcnt:", "popcnt")
            val neg = Operator("-", "neg")
            val abs = Operator("abs:", "abs")
            val sqrt = Operator("sqrt:", "sqrt")
            val ceil = Operator("ceil:", "ceil")
            val floor = Operator("floor:", "floor")
            val trunc = Operator("trunc:", "trunc")
            val nearest = Operator("nearest:", "nearest")
        }

        fun wat(): String {
            return watName
        }
    }
}