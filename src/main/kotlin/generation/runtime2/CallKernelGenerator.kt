package generation.runtime2

import ir.expression.Symbol
import ir.statement.Function
import ir.statement.IndirectFunctionCall
import ir.statement.Program
import ir.wasm.*

object CallKernelGenerator {
    fun generate(program: Program, kernelTabel: WasmTable) : WasmFunction{
        val module = program.module
        val callKernelType = module.findOrAddType(listOf(WasmValueType.i32, WasmValueType.i32), listOf())
        val callKernelFunction = WasmFunction(
            Index.next(module.functions),
            callKernelType,
        )
        module.functions.add(callKernelFunction)
        val threadId =  Symbol(WasmScope.local, WasmValueType.i32, Index.number(0))
        val kernelIndex =  Symbol(WasmScope.local, WasmValueType.i32, Index.number(1))
        val kernelType = module.findOrAddType(listOf(WasmValueType.i32), listOf())
        val callKernel = Function(callKernelFunction, mutableListOf(
            IndirectFunctionCall(kernelTabel.index, kernelType.index, kernelIndex, listOf(threadId), listOf())
        ))
        program.statements.add(callKernel)
        module.exports.add(WasmExport("\"call_kernel\"", WasmExportKind.func, callKernelFunction.index))
        return callKernelFunction
    }
}