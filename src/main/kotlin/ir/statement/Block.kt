package ir.statement

import ir.annotations.WackAnnotation
import generation.WatWriter
import generation.WebAssemblyBlock
import ir.Mode
import ir.expression.Expression
import ir.expression.StackExpression
import ir.finder.Visitor
import ir.wasm.WasmValueType

open class Block(
    open val instructions: MutableList<Statement> = mutableListOf(),
    val hasReturn: Boolean = false,
    val brackets: Boolean = true,
    var parent: Block? = null,
    var indexInParent: Int? = null,
    val type: WasmValueType? = null,
    open val annotations: MutableList<WackAnnotation> = mutableListOf()
) : BasicStatement(), WebAssemblyBlock {

    open fun push(stmt: Statement) {
        if (stmt is Block) {
            stmt.parent = this
            stmt.indexInParent = instructions.size
        }
        instructions.add(stmt)
    }

    private val _poped = mutableSetOf<Long?>()
    open fun pop(): Expression {
        var foldable = true
        for (i in (instructions.size - 1) downTo 0) {
            val instr = instructions[i]
            if (instr !is Expression) { // checks if instruction is statement - don't change!
                foldable = false
                continue
            }
            if (foldable) {
                return instructions.removeAt(i) as Expression
            } else if (_poped.contains(instr.id)) {
                continue
            } else { // dont remove original expression - add pointer to stack location - cant be popped again
                _poped.add(instr.id)
                return StackExpression(instr){instructions[i] = it}
            }
        }
        throw Exception()
    }

    open fun writeHeader(out: Appendable) {}

    fun printHeader(): String? {
        val header = StringBuffer()
        writeHeader(header)
        if (header.isEmpty()) {
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
            if (i == len - 1 && hasReturn) {
                out.append("return ")
                expr.write(out)
                out.append(";\n")
                continue
            }
            expr.write(out)
        }
        if (brackets) out.append("}\n")
    }

    override fun visit(v: Visitor) {
        v.visit(instructions) { i, stmt ->
            instructions[i] = stmt
        }
    }

    fun watBlockAnnotations(wat: WatWriter) {
        if (WAPC.params!!.annotations) {
            for (annotation in annotations) {
                wat.write(" ")
                annotation.wat(wat)
            }
        }
    }

    fun watBlockType(wat: WatWriter) {
        if (type != null) {
            wat.write(" (result ${type.name})")
        }
    }

    open val blockName = "block"

    override fun watHeader(wat: WatWriter) {
        wat.startLine()
        wat.write(blockName)
        watBlockType(wat)
        watBlockAnnotations(wat)
        wat.endLine()
    }

    override fun watEnd(wat: WatWriter) {
        wat.writeLine("end")
    }

    override fun wat(wat: WatWriter) {
        watHeader(wat)
        wat.indent++
        wat.writeAll(instructions)
        wat.indent--
        watEnd(wat)
    }

    fun <T : WackAnnotation> hasAnnotation(clazz: Class<T>): Boolean {
        return annotations.any { clazz.isInstance(it) }
    }

    fun childOf(block: Block): Boolean {
        var current: Block? = parent
        while (current != null) {
            if (current == block) {
                return true
            }
            current = current.parent
        }
        return false
    }
}