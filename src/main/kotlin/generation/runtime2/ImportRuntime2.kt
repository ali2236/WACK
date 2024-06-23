package generation.runtime2

import ir.expression.Symbol
import ir.finder.Replaceable
import ir.finder.ReplaceableFinder
import ir.statement.Function
import ir.statement.Program
import ir.wasm.Index
import ir.wasm.WasmScope
import java.io.File

//// things that will change when linking:
// 1. function index + function call
// 2. globals + global accesses
// 3. module function types + function types + indirect_call type
// 4. tabel index + element segment funcrefs
// 5. data segment index + memory.init
// 6. memory index + load/store
// 7. export index
object ImportRuntime2 {
    fun into(program: Program): WackPthreads {
        val runtimeWasm = File("./runtime/runtime2.wasm")
        val runtime = Program.from(runtimeWasm)

        // globals
        val globalsIndexMap = mutableMapOf<Index, Index>()
        program.module.globals.also { programGlobals ->
            runtime.module.globals.forEach {
                val newIndex = Index.next(runtime.module.globals)
                programGlobals.add(it.copy(index = newIndex))
                globalsIndexMap[it.index] = newIndex
            }
        }

        val symbolFinder = ReplaceableFinder(Symbol::class.java)
        runtime.statements.filterIsInstance<Function>().forEach {function ->
            function.visit(symbolFinder)
        }

        symbolFinder.result().filter { it.statement.scope == WasmScope.global }.forEach {
            val newIndex = globalsIndexMap.get(it.statement.index)
            if(newIndex != null){
                it.replace(Symbol(WasmScope.global, it.statement.type, newIndex))
            }
        }

        // memory


        return TODO()
    }
}