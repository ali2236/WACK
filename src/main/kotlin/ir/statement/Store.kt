package ir.statement

import compiler.WAPC
import generation.wat.WatWriter
import ir.expression.*
import ir.finder.Visitor

class Store(
    var symbol: Load,
    var data: Expression,
) : BasicStatement(), AssignmentStore {

    override fun write(out: Appendable) {
        symbol.write(out)
        out.append(" = ")
        data.write(out)
        out.append(";\n")
    }

    override fun visit(v: Visitor) {
        v.visit(symbol.address) { symbol.address = it as Expression }
        v.visit(data) { data = it as Expression }
    }

    override fun wat(wat: WatWriter) {
        symbol.address.wat(wat)
        data.wat(wat)
        val ofst = if (symbol.offset != 0) " offset=${symbol.offset}" else ""
        val algn = if (symbol.align != 0) " align=${symbol.align}" else ""
        val memIndex = if(WAPC.params!!.multipleMemories) " ${symbol.memoryIndex}" else ""
        val memSize = if (symbol.memorySize != null) "${symbol.memorySize}" else ""
        val atomic = if(symbol.atomic) ".atomic" else ""
        wat.writeLine("${symbol.type}${atomic}.store${memSize}${memIndex}${ofst}${algn}", this)
    }

    override fun assignedWith(): Expression {
        return data
    }

    override fun assignedTo(): SymbolLoad {
        return symbol
    }

    override fun replaceAssign(newValue: Expression) {
        data = newValue
    }

    override fun toString(): String {
        val buffer = StringBuffer()
        write(buffer)
        return buffer.toString()
    }

}