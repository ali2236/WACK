package ir.wasm

import ir.expression.Expression
import ir.statement.FunctionCall
import org.antlr.v4.runtime.tree.ParseTree

data class WasmFunction(
    val index: Index,
    val type: WasmFunctionType,
    val locals: MutableList<WasmValueType> = mutableListOf(), // only important when attached to a Function class
    val import: WasmImport? = null,
    val code: ParseTree? = null,
) : IndexedSection {
    val allLocals: List<WasmValueType>
        get() = type.params + locals

    fun call(vararg params: Expression): FunctionCall {
        // TODO: fix inverse call param issue
        return FunctionCall(index, mutableListOf(*params), type.result)
    }

    override fun getSectionIndex(): Index {
        return index
    }
}