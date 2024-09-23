package generation.wack

import ir.expression.Load
import ir.expression.Symbol
import ir.expression.Value
import ir.statement.Program
import ir.statement.Store
import ir.wasm.*

class MetaLibrary(val getStackBase: WasmFunction, val setStackBase: WasmFunction, val metaMemory: WasmMemory) {
    companion object {
        fun generate(program: Program): MetaLibrary {
            val module = program.module

            // memory import/export
            val m = Index.next(module.memories)
            val metaMemory = WasmMemory(m, 1, 1, true)
            module.memories.add(metaMemory)

            // offsets
            val stackBase = Value.i32(0)

            // functions
            val getStackBase = program.addFunction(
                name = "wack__get_stack_base",
                result = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    Load(WasmValueType.i32, stackBase, metaMemory.index)
                ),
            )
            val setStackBase = program.addFunction(
                name = "wack__set_stack_base",
                params = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    Store(
                        Load(WasmValueType.i32, stackBase, metaMemory.index),
                        Symbol.localI32(Index.number(0))
                    )
                ),
            )

            return MetaLibrary(
                getStackBase.functionData,
                setStackBase.functionData,
                metaMemory,
            )
        }
    }
}