package ir.expression

class Increment(symbol: Symbol, operator: Operator = Operator.add) :
    Assignment(symbol, BinaryOP(operator, symbol, Value("1"))) {
    override fun c(out: Appendable) {
        symbol.c(out)
        val op = when ((value as BinaryOP).operator) {
            Operator.add -> "++"
            Operator.sub -> "--"
            else -> throw Error()
        }
        out.append("$op;\n")
    }
}