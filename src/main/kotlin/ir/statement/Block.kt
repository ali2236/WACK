package ir.statement

import ir.ChildExpression
import ir.expression.Expression
import ir.expression.Symbol

open class Block(
    open val instructions: MutableList<Statement> = mutableListOf(),
    val hasReturn: Boolean = false,
    val brackets : Boolean = true,
    var parent : Block? = null,
    var indexInParent : Int? = null,
) : Statement {

    open fun push(stmt: Statement) {
        if(stmt is Block){
            stmt.parent = this
            stmt.indexInParent = instructions.size
        }
        instructions.add(stmt)
    }

    open fun pop(): Expression {
        for(i in (instructions.size-1) downTo 0){
            if(instructions[i] !is Expression){
                continue
            }
            return instructions.removeAt(i) as Expression
        }
        throw Error()
    }

    open fun writeHeader(out: Appendable){

    }

    fun printHeader() : String?{
        val header = StringBuffer()
        writeHeader(header)
        if(header.isEmpty()){
            return null
        }
        return header.toString()
    }

    override fun write(out: Appendable) {
        val len = instructions.size
        writeHeader(out)
        if (brackets) out.append("{\n")
        for (i in 0 until len) {
            val expr = instructions[i]
            if(i == len - 1 && hasReturn){
                out.append("return ")
                expr.write(out)
                out.append(";\n")
                continue
            }
            expr.write(out)
        }
        if (brackets) out.append("}\n")
    }

    open fun close() {}

    override fun symbols(): List<Symbol> {
        return instructions.map { it.symbols() }.reduce { a, b -> a + b }
    }

    override fun expressions(): List<ChildExpression> {
        return listOf()
    }
}