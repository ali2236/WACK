package ir

import ir.parser.WatVisitor
import ir.statement.Function
import ir.statement.Program
import ir.statement.Statement
import ir.wasm.Index
import ir.wasm.WasmModule

class IRConstructor(val module: WasmModule) {

    fun program(): Program {
        return Program(module, module.functions.map { function(it.index) }.toMutableList())
    }

    fun function(index: Index): Statement {
        val function = module.functions.first { it.index == index }
        val functionBlock: List<Statement> = if (function.code != null) {
            val visitor = WatVisitor(module)
            val instructions = visitor.visitFunction(function)
            /*getFunctionInitSection(function) +*/ instructions
        } else {
            listOf()
        }
        return Function(function, functionBlock.toMutableList())
    }
}