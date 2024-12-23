package ir.statement

import generation.wat.WatWriter
import ir.expression.Expression
import ir.finder.Visitor
import ir.wasm.WasmValueType

open class If(
    var condition: Expression,
    trueBody: MutableList<Statement> = mutableListOf(),
    var elseBody: MutableList<Statement>? = null,
    brackets: Boolean = true,
    type: WasmValueType? = null,
) : Block(trueBody, brackets = brackets, type = type) {
    open val trueBody: List<Statement>
        get() = instructions

    override fun writeHeader(out: Appendable) {
        out.append("if(")
        condition.write(out)
        out.append(")")
    }

    override fun write(out: Appendable) {
        super.write(out)
        if (elseBody != null) {
            out.append("else {\n")
            elseBody?.forEach {
                it.write(out)
            }
            out.append("}\n")
        }
    }

    override val blockName: String
        get() = "if"

    override fun watEnd(wat: WatWriter) {
        // do nothing
    }

    override fun wat(wat: WatWriter) {
        condition.wat(wat)
        super.wat(wat)
        if (elseBody != null && elseBody!!.isNotEmpty()) {
            wat.writeLine("else")
            wat.indent++
            elseBody?.forEach { instr ->
                instr.wat(wat)
            }
            wat.indent--
        }
        wat.writeLine("end")
    }

    override fun visit(v: Visitor) {
        v.visit(condition) { condition = it as Expression }
        super.visit(v)
        if (elseBody != null) {
            v.visit(elseBody!!, elseBody!!::set)
        }
    }

    override fun toString(): String {
        val b = StringBuffer()
        writeHeader(b)
        return b.toString()
    }
}

