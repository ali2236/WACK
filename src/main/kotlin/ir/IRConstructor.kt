package ir

import ir.statement.Block
import ir.statement.Function
import ir.statement.Program
import ir.statement.Statement
import org.antlr.v4.runtime.tree.ParseTree
import wasm.Index
import wasm.WasmModule

class IRConstructor(val module: WasmModule) {

    fun program(): Program {
        return Program(
            module.functions.map { function(it.index) }
        )
    }

    fun function(index: Index): Statement {
        val function = module.functions.first { it.index == index }
        val functionBlock = if (function.code != null) {
            Block(
                functionBody(function.code).instructions,
                hasReturn = function.type.result.isNotEmpty(),
                brackets = false
            )
        } else {
            Block(hasReturn = false, brackets = false)
        }
        return Function(function, functionBlock)
    }

    private fun functionBody(parseTree: ParseTree): Block {
        val visitor = FunctionVisitor(module)
        visitor.visit(parseTree)
        return visitor.stack
    }
}