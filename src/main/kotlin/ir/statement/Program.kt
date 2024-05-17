package ir.statement

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
}