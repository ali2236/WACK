package ir.expression

import ir.ChildExpression

class BinaryOP(var operator: Operator, var left: Expression,var right: Expression) : Expression() {

    override fun write(out: Appendable) {
        if (left is BinaryOP) out.append("(")
        left.write(out)
        if (left is BinaryOP) out.append(")")

        out.append(operator.sign)

        if (right is BinaryOP) out.append("(")
        right.write(out)
        if (right is BinaryOP) out.append(")")
    }

    override fun expressions(): List<ChildExpression> {
        return listOf(
            ChildExpression(left){ this.left = it },
            ChildExpression(right){ this.right = it }
        )
    }

    fun invert(): Expression {
        val invertedOperator = Operator(operator.invertSign, operator.sign)
        return BinaryOP(invertedOperator, left, right)
    }

    override fun symbols(): List<Symbol> {
        return left.symbols() + right.symbols()
    }
}

data class Operator(val sign: String, val invertSign: String) {
    companion object {
        val eq = Operator("==", "!=")
        val neq = Operator("!=", "==")
        val lt = Operator("<", ">")
        val le = Operator("<=", ">=")
        val gt = Operator(">", "<")
        val ge = Operator(">=", "<=")
        val add = Operator("+", "+")
        val sub = Operator("-", "-")
        val div = Operator("/", "/")
        val mul = Operator("*", "*")
        val shl = Operator("<<", "<<")
        val shr = Operator(">>", ">>")
        val and = Operator("&", "&")
        val or = Operator("|", "|")
    }
}