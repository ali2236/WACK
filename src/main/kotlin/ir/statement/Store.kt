package ir.statement

import generation.WatWriter
import ir.Names
import ir.expression.*
import ir.finder.Visitor
import wasm.WasmValueType

class Store(
    val symbol: Load,
    var data: Expression,
) : Statement, Assignee {

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
        wat.writeLine("${symbol.type}.store ${symbol.memoryIndex}${ofst}${algn}")
    }

    override fun assignedWith(): Expression {
        return data
    }

    override fun assignedTo(): Assignable {
        return symbol
    }

    override fun replaceAssign(newValue: Expression) {
        data = newValue
    }

}