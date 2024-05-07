package ir

import org.antlr.v4.runtime.tree.ParseTree
import wasm.Index
import wasm.WasmFunction
import wasm.WasmModule
import java.util.*

class IRConstructor(val module: WasmModule) {

    fun function(index: Index): Statement {
        val function = module.functions.first { it.index == index }
        val functionBlock = if (function.code != null) Block(functionBody(function.code)) else Block(listOf())
        return Function(function, functionBlock)
    }

    private fun functionBody(parseTree: ParseTree): List<Expression> {
        val visitor = FunctionVisitor(module)
        visitor.visit(parseTree)
        return visitor.stack.toList()
    }
}