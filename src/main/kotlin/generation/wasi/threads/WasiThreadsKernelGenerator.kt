package generation.wasi.threads

import ir.annotations.For
import ir.expression.FunctionCall
import ir.expression.Symbol
import ir.finder.AnnotationFinder
import ir.finder.Finders
import ir.finder.ReplaceableFinder
import ir.statement.*
import ir.statement.Function
import optimization.Optimizer
import wasm.Index
import wasm.WasmFunction
import wasm.WasmFunctionType

// kernel is called by every thread
// kernel should not care about creating threads and how it is called
// just call it once
class WasiThreadsKernelGenerator : Optimizer {

    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .forEach { applyToFunction(program, it) }
    }

    private fun applyToFunction(program: Program, function: Function) {
        val module = program.module
        val forLoops = AnnotationFinder(For::class.java).apply { visit(function) { } }.result()

        for ((forLoop, replace) in forLoops) {
            val kernelType = WasmFunctionType(Index.next(module.functionTypes))
            val block = Block(annotations = forLoop.annotations.apply { removeIf { it is For } })

            // find function locals used
            val localsAccessed = Finders.symbols(forLoop.instructions).toSet()

            // make function type
            localsAccessed.forEach { kernelType.params.add(it.type) }

            // optimally determine value for local or just pass them as function parameters

            // make function definition
            val kernelFunction = WasmFunction(Index.next(module.functions), type = kernelType)

            // TODO: Kernel init
            // get thread_id
            // calculate start and end
            // start loop

            // put loop as function body
            val kernel = Function(kernelFunction, mutableListOf(forLoop))

            // replace locals with new locals
            ReplaceableFinder(Symbol::class.java)
                .also { it.visit(kernel) {} }
                .result()
                .forEach {
                    val smbl = it.statement
                    val newIndex = localsAccessed.indexOf(smbl)
                    val newSmbl = Symbol(smbl.scope, smbl.type, Index(newIndex))
                    it.replace(newSmbl)
                }

            // call kernel function with each thread portion
            block.push(FunctionCall(kernelFunction.index, localsAccessed.toList(), false))

            // commit change
            replace(block)
            module.functionTypes.add(kernelType)
            module.functions.add(kernelFunction)
            program.statements.add(kernel)
        }
    }
}