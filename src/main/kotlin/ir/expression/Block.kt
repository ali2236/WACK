package ir.expression

open class Block(
    open val instructions: MutableList<Expression> = mutableListOf(),
    val hasReturn: Boolean = false,
    val brackets : Boolean = true,
) : Expression() {

    fun push(expr: Expression) {
        instructions.add(expr)
    }

    fun pop(): Expression {
        for(i in (instructions.size-1) downTo 0){
            if(instructions[i] is Assignment){
                continue
            }
            return instructions.removeAt(i)
        }
        throw Error()
    }

    override fun c(out: Appendable) {
        val len = instructions.size
        if (brackets) out.append("{\n")
        for (i in 0 until len) {
            val expr = instructions[i]
            if(i == len - 1 && hasReturn){
                out.append("return ")
                expr.c(out)
                out.append(";\n")
                continue
            }
            expr.c(out)
        }
        if (brackets) out.append("}\n")
    }

    open fun close() {}
}