package ir.expression

class BinaryOP(operator: Operator, val left: Expression, right: Expression) : Expression() {
    val operator: Operator
    val right: Expression

    init {
        val isArrayIndexCalculation = operator.sign == "<<" && right is Value && right.value == "2"
        this.operator = if (isArrayIndexCalculation) {
            Operator.mul
        } else {
            operator
        }
        this.right = if (isArrayIndexCalculation) {
            Value("4")
        } else {
            right
        }
    }

    override fun c(out: Appendable) {
        if (left is BinaryOP) out.append("(")
        left.c(out)
        if (left is BinaryOP) out.append(")")

        out.append(operator.sign)

        if (right is BinaryOP) out.append("(")
        right.c(out)
        if (right is BinaryOP) out.append(")")
    }

    fun invert(): Expression {
        val invertedOperator = Operator(operator.invertSign, operator.sign)
        return BinaryOP(invertedOperator, left, right)
    }

    override fun dependencies(): List<Symbol> {
        return left.dependencies() + right.dependencies()
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