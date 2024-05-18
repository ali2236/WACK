package ir.statement

import generation.WatWriter
import ir.finder.Visitor
import wasm.WasmModule

class Program(val module: WasmModule, val statements: MutableList<Statement>) : Statement {
    override fun write(out: Appendable) {
        for (statement in statements) {
            statement.write(out)
            out.append('\n')
        }
    }

    override fun visit(v: Visitor) {
        v.visit(statements, statements::set)
    }

    override fun wat(wat: WatWriter) {
        // module header
        wat.writeLine("(module")
        wat.indent++
        // types
        module.functionTypes.forEach { it.wat(wat) }
        // imports & functions
        wat.writeAll(statements)
        // tables
        module.tables.forEach { it.wat(wat) }
        // memories
        module.memories.forEach { it.wat(wat) }
        // globals
        module.globals.forEach { it.wat(wat) }
        // exports
        module.exports.forEach { it.wat(wat) }
        // elements
        module.elementSegments.forEach { it.wat(wat) }
        // TODO: data segment
        // close module
        wat.indent--
        wat.writeLine(")")
    }
}