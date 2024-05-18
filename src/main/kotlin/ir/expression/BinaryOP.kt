package ir.expression

import generation.WatWriter
import generation.WebAssemblyInstruction
import ir.finder.Visitor
import wasm.WasmValueType

class BinaryOP(val type: WasmValueType, var operator: Operator, var left: Expression, var right: Expression) :
    Expression() {

    override fun write(out: Appendable) {
        if (left is BinaryOP) out.append("(")
        left.write(out)
        if (left is BinaryOP) out.append(")")

        out.append(operator.sign)

        if (right is BinaryOP) out.append("(")
        right.write(out)
        if (right is BinaryOP) out.append(")")
    }

    override fun wat(wat: WatWriter) {
        if (right is Value && (right as Value).value == "0" && operator == Operator.eq) {
            left.wat(wat)
            wat.writeLine("${(right as Value).type}.eqz")
        } else {
            left.wat(wat)
            right.wat(wat)
            wat.writeLine("${type}.${operator.wat()}")
        }
    }

    override fun visit(v: Visitor) {
        v.visit(left) { this.left = it as Expression }
        v.visit(right) { this.right = it as Expression }
    }
}

data class Operator(val sign: String, val watName: String, var signed: BitSign? = null) {

    companion object {
        val eq = Operator("==", "eq")
        val neq = Operator("!=", "neq")
        val lt = Operator("<", "lt")
        val le = Operator("<=", "le")
        val gt = Operator(">", "gt")
        val ge = Operator(">=", "ge")
        val add = Operator("+", "add")
        val sub = Operator("-", "sub")
        val div = Operator("/", "div")
        val mul = Operator("*", "mul")
        val shl = Operator("<<", "shl")
        val shr = Operator(">>", "shr")
        val and = Operator("&", "and")
        val or = Operator("|", "or")
    }

    fun wat(): String {
        if (signed != null) {
            return "${watName}_$signed"
        } else {
            return watName
        }
    }
}

enum class BitSign {
    s, u
}