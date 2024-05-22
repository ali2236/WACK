package generation.wack

import ir.annotations.For
import ir.expression.FunctionCall
import ir.expression.Symbol
import ir.finder.AnnotationFinder
import ir.finder.Finders
import ir.finder.ReplaceableFinder
import ir.statement.Block
import ir.statement.Function
import ir.statement.Program
import wasm.Index
import wasm.WasmFunction
import wasm.WasmValueType

object ThreadKernelGenerator {
    fun generate(program: Program) {
        val module = program.module
        program.statements.filterIsInstance<Function>().forEach { function ->
            AnnotationFinder(For::class.java).apply { visit(function) { } }.result().forEach { (forLoop, replace) ->
                    val kernelType = module.addType(listOf(WasmValueType.i32), listOf())
                    val parallelBlock = Block(annotations = forLoop.annotations.apply { removeIf { it is For } })

                    // find function locals used
                    val localsAccessed = Finders.symbols(forLoop.instructions).toSet()

                    // TODO: determine value for local using dfa+eval

                    // make function definition
                    val kernelFunction = WasmFunction(Index.next(module.functions), type = kernelType)

                    // TODO: Kernel init

                    // get thread_id
                    // calculate start and end
                    // start loop

                    // put loop as function body
                    val kernel = Function(kernelFunction, mutableListOf(
                        forLoop,
                    ))

                    // replace locals with new locals
                    ReplaceableFinder(Symbol::class.java).also { it.visit(kernel) {} }.result().forEach {
                        val smbl = it.statement
                        val newIndex = localsAccessed.indexOf(smbl)
                        val newSmbl = Symbol(smbl.scope, smbl.type, Index(newIndex))
                        it.replace(newSmbl)
                    }

                    // call kernel function with each thread portion
                    parallelBlock.push(FunctionCall(kernelFunction.index, localsAccessed.toList(), false))

                    // commit change
                    replace(parallelBlock)
                    module.functions.add(kernelFunction)
                    program.statements.add(kernel)
                }
        }
    }

}