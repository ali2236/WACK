package optimization

import analysis.ddt.DdgBuilder
import ir.expression.FunctionCall
import ir.expression.Symbol
import ir.finder.Finders
import ir.finder.LoopFinder
import ir.finder.ReplaceableFinder
import ir.statement.*
import ir.statement.Function
import wasm.Index
import wasm.WasmFunction
import wasm.WasmFunctionType

class KernelExtraction : Optimizer {


    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .forEach { applyToFunction(program, it) }
    }

    private fun applyToFunction(program: Program, function: Function) {
        val module = program.module
        val ddt = DdgBuilder(function).build()

        // TODO: should only look for range loops
        val loops = LoopFinder(true).apply { visit(function){ } }.result()
        for ((loop, replace) in loops) {
            val loopIsParallelizable = true
            if (loopIsParallelizable) {
                val kernelType = WasmFunctionType(Index.next(module.functionTypes))
                val block = Block()

                // find function locals used
                val localsAccessed = Finders.symbols(loop.instructions).toSet()

                // make function type
                localsAccessed.forEach { kernelType.params.add(it.type) }

                // optimally determine value for local or just pass them as function parameters

                // make function definition
                val kernelFunction = WasmFunction(Index.next(module.functions), type = kernelType)

                // put loop as function body
                val kernel = Function(kernelFunction, mutableListOf(loop))

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

                // pre kernel call code(create threads)
                block.push(Comment("Pre Kernel Call Code"))

                // call kernel function with each thread portion
                block.push(FunctionCall(kernelFunction.index, localsAccessed.toList(),false))

                // commit side effects
                block.push(Comment("Revert Side Effects"))

                // post kernel code (thread sync)
                block.push(Comment("Post Kernel Call Code"))

                // commit change
                replace(block)
                module.functionTypes.add(kernelType)
                module.functions.add(kernelFunction)
                program.statements.add(kernel)
            }
        }
    }
}