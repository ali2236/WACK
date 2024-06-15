package ir

import ir.expression.Assignment
import ir.expression.Symbol
import ir.expression.Value
import ir.statement.*
import ir.statement.Function
import ir.parser.WatVisitor
import ir.wasm.Index
import ir.wasm.WasmFunction
import ir.wasm.WasmModule
import ir.wasm.WasmScope

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

    private fun getFunctionInitSection(functionData: WasmFunction): List<Statement> {
        val inst = mutableListOf<Statement>()
        // Local Variables
        // declaration
        val paramCount = functionData.type.params.size
        val localCount = functionData.locals.size
        for (i in 0 until localCount) {
            val localType = functionData.locals[i]
            val symbol = Symbol(WasmScope.local, localType, Index(paramCount + i))
            val dec = Declaration(localType, symbol)
            inst.add(dec)
        }
        // assignment
        for (i in 0 until localCount) {
            val localType = functionData.locals[i]
            val symbol = Symbol(WasmScope.local, localType, Index(paramCount + i))
            val value = Value(localType, localType.defaultValue())
            val assignment = Assignment(symbol, value) // TODO: fake assignment
            inst.add(assignment)
        }

        return inst
    }
}