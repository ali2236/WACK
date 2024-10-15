package generation.wack

import WAPC
import ir.expression.Expression
import ir.expression.Load
import ir.expression.Symbol
import ir.expression.Value
import ir.statement.Program
import ir.statement.Store
import ir.wasm.*

class MetaLibrary(
    val getMaxThreads: Expression,
    val maxThreads: Property,
    val stackBase: Property,
    val kernelId: Property,
    val threadPoolState: Property,
    val metaMemory: WasmMemory
) {
    companion object {
        fun generate(program: Program): MetaLibrary {
            val module = program.module

            // memory import/export
            val m = Index.next(module.memories)
            val metaMemory = WasmMemory(m, 1, 1, true)
            module.memories.add(metaMemory)


            // functions
            val i32Bytes = 4
            val maxThreads = Property.fromAddress(
                program,
                "max_threads",
                WasmValueType.i32,
                metaMemory,
                Value.i32(0 * i32Bytes),
            )
            val stackBase = Property.fromAddress(
                program,
                "stack_base",
                WasmValueType.i32,
                metaMemory,
                Value.i32(1 * i32Bytes)
            )
            val kernelId = Property.fromAddress(
                program,
                "kernel_id",
                WasmValueType.i32,
                metaMemory,
                Value.i32(2 * i32Bytes)
            )
            val threadPoolState = Property.fromAddress(
                program,
                "thread_pool_state",
                WasmValueType.i32,
                metaMemory,
                Value.i32(3 * i32Bytes)
            )

            return MetaLibrary(
                maxThreads.get.call().result,
                maxThreads,
                stackBase,
                kernelId,
                threadPoolState,
                metaMemory,
            )
        }
    }

    class Property(
        val get: WasmFunction,
        val set: WasmFunction,
    ) {
        companion object {
            fun fromAddress(
                program: Program,
                name: String,
                type: WasmValueType,
                memory: WasmMemory,
                base: Expression,
                offset: Int = 0,
            ): Property {
                val getter = program.addFunction(
                    name = "wack__get_$name",
                    result = listOf(type),
                    instructions = mutableListOf(
                        Load(type, base, memory.index, offset = offset)
                    ),
                )
                val setter = program.addFunction(
                    name = "wack__set_$name",
                    params = listOf(type),
                    instructions = mutableListOf(
                        Store(
                            Load(type, base, memory.index),
                            Symbol(WasmScope.local, type, Index.number(0)),
                        )
                    ),
                )
                return Property(
                    getter.functionData,
                    setter.functionData,
                )
            }
        }
    }
}