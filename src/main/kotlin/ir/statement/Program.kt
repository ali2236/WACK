package ir.statement

import external.Wasm2Wat
import generation.WatWriter
import ir.IRConstructor
import ir.finder.Visitor
import ir.parser.Wat
import ir.wasm.WasmModule
import java.io.File

class Program(val module: WasmModule, val statements: MutableList<Statement>) : BasicStatement() {
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
        // start
        module.start?.wat(wat)
        // elements
        module.elementSegments.forEach { it.wat(wat) }
        // data segment
        module.dataSegments.forEach { it.wat(wat) }
        // close module
        wat.indent--
        wat.writeLine(")")
    }

    companion object {
        fun from(wasmFile: File): Program {
            val watFile = Wasm2Wat.process(wasmFile)
            val parseTree = Wat.parse(watFile.path)
            val module = Wat.module(parseTree)
            val ir = IRConstructor(module)
            val program = ir.program()
            return program
        }
    }
}