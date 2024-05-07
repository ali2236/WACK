package ir.expression

class Store(val data: Expression, val address: Expression) : Expression() {
    override fun c(out: Appendable) {
        out.append("Memory[")
        address.c(out)
        out.append("] = ")
        data.c(out)
        out.append(";\n")
    }

}