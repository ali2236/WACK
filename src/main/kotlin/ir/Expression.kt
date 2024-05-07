package ir

interface Expression : Statement {}

class Value(val value: String) : Expression {
    override fun c(out: Appendable) {
        out.append(value)
    }
}

class Assignment(val symbol: String, val value: Expression) : Expression {
    override fun c(out: Appendable) {
        out.append(symbol)
        out.append(" = ")
        value.c(out)
        out.append(';')
        out.append("\n")
    }
}

class Loop(val body: Block) : Expression {
    override fun c(out: Appendable) {
        out.append("for(;1;){\n")
        body.c(out)
        out.append("\n}\n")
    }
}

class Block(val instructions: List<Expression>) : Expression {
    override fun c(out: Appendable) {
        val len = instructions.size
        for (i in 0 until len) {
            val expr = instructions[i]
            if (i == len - 1 && expr is Value) {
                out.append("return ")
                expr.c(out)
                out.append(";\n")
            } else {
                expr.c(out)
            }
        }
    }
}

class BinaryOP(val operator: String, val first: Expression, val second: Expression) : Expression {
    override fun c(out: Appendable) {

        if (first is BinaryOP) out.append("(")
        first.c(out)
        if (first is BinaryOP) out.append(")")

        out.append(operator)
        if (second is BinaryOP) out.append("(")
        second.c(out)
        if (second is BinaryOP) out.append(")")
    }
}

class Store(val data: Expression, val address: Expression) : Expression {
    override fun c(out: Appendable) {
        out.append("Memory[")
        address.c(out)
        out.append("] = ")
        data.c(out)
        out.append(";\n")
    }

}

class FunctionCall(val name: String, val params : List<Expression>, val hasReturn : Boolean) : Expression {
    override fun c(out: Appendable) {
        out.append(name)
        out.append("(")

        val paramCount = params.size
        for (i in 0 until paramCount){
            val param = params[i]
            param.c(out)
            if(i != paramCount - 1){
                out.append(", ")
            }
        }

        out.append(")")
        if(!hasReturn){
            out.append(";\n")
        }
    }

}