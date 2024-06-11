package ir.wasm

import ir.expression.Expression
import ir.expression.FunctionCall
import ir.statement.Statement
import org.antlr.v4.runtime.tree.ParseTree

data class WasmFunction(
    val index: Index,
    val name: String? = null,
    val type : WasmFunctionType,
    val locals : MutableList<WasmValueType> = mutableListOf(), // only important when attached to a Function class
    val import: WasmImport? = null,
    val exportName: String? = null,
    val code: ParseTree? = null,
) {
    val allLocals : List<WasmValueType>
        get() = type.params + locals
    fun call(vararg params : Expression): FunctionCall {
        return FunctionCall(index, listOf(*params) ,type.result.isNotEmpty())
    }

    val prettyName: String
        get() {
            if(import != null){
                return import.name.replace("\"","")
            } else if(exportName != null){
                return exportName.replace("\"","")
            } else {
                return "f${index.number}"
            }
        }
}