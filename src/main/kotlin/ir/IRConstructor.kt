package ir

import ir.expression.Symbol
import ir.expression.Value
import ir.statement.*
import ir.statement.Function
import wasm.Index
import wasm.WasmFunction
import wasm.WasmModule

class IRConstructor(val module: WasmModule) {

    fun program(): Program {
        return Program(module, module.functions.map { function(it.index) }.toMutableList())
    }

    fun function(index: Index): Statement {
        val function = module.functions.first { it.index == index }
        val functionBlock : List<Statement> = if (function.code != null) {
            val visitor = FunctionVisitor(
                module,
                function,
                Block(
                    hasReturn = function.type.result.isNotEmpty(),
                    brackets = false
                )
            )
            visitor.visit(function.code)
            getFunctionInitSection(function) + visitor.stack.instructions
        } else {
            listOf()
        }
        return Function(function, functionBlock.toMutableList())
    }

    private fun getFunctionInitSection(functionData: WasmFunction) : List<Statement>{
        val inst = mutableListOf<Statement>()
        // Local Variables
        // declaration
        val paramCount = functionData.type.params.size
        val localCount = functionData.locals.size
        for (i in paramCount until localCount) {
            val localType = functionData.locals[i]
            val symbol = Symbol(localType, Names.local + "${paramCount + i}")
            val dec = Declaration(localType, symbol)
            inst.add(dec)
        }
        // assignment
        for (i in paramCount until localCount) {
            val localType = functionData.locals[i]
            val symbol = Symbol(localType, Names.local + "${paramCount + i}")
            val value = Value(localType, localType.defaultValue())
            val assignment = Assignment(symbol, value)
            inst.add(assignment)
        }

        return inst
    }
}