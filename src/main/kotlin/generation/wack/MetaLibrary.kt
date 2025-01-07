package generation.wack

import compiler.WAPC
import generation.debug.PrintLibrary
import generation.wasm.threads.MutexLibrary
import ir.expression.*
import ir.statement.Program
import ir.statement.Store
import ir.wasm.*

// first 16kb
class MetaLibrary(
    val maxThreads: Property,
    //val stackBase: Property,
    val kernelId: Property,
    val threadPoolState: Property,
    val localTransfer: (WasmValueType) -> LocalTransfer,
    val metaMemory: WasmMemory
) {
    companion object {
        fun generate(program: Program): MetaLibrary {
            val module = program.module

            // memory import/export
            val metaMemory = if (!WAPC.params.multipleMemories) module.memories.first()
            else {
                val m = Index.next(module.memories)
                val mm = WasmMemory(m, 1, 1, true)
                module.memories.add(mm)
                mm
            }


            // functions
            val i32Bytes = 8
            val i64Bytes = 8
            val base = Value.i32(0)
            val maxThreads = Property.fromAddress(
                program,
                "max_threads",
                WasmValueType.i32,
                metaMemory,
                base.add(0L * i32Bytes),
            )
            /*val stackBase = Property.fromAddress(
                program,
                "stack_base",
                WasmValueType.i32,
                metaMemory,
                base.add(1L * i64Bytes),
            )*/
            val kernelId = Property.fromAddress(
                program,
                "kernel_id",
                WasmValueType.i32,
                metaMemory,
                base.add(1L * i32Bytes),
            )
            val threadPoolState = Property.fromAddress(
                program,
                "thread_pool_state",
                WasmValueType.i32,
                metaMemory,
                base.add(2L * i32Bytes),
            )

            // locals transfer
            val localsBase = base.add(3L * i32Bytes) // from this location forward
            val transferTypes = mutableMapOf<WasmValueType, LocalTransfer>()
            WasmValueType.values().forEachIndexed { i, type ->
                val localTransfer = LocalTransfer.from(
                    program,
                    type,
                    metaMemory,
                    localsBase,
                )
                transferTypes[type] = localTransfer
            }

            // export set_max_threads
            module.exports.add(
                WasmExport("\"wapc__set_max_threads\"", WasmExportKind.func, maxThreads.set.index)
            )

            return MetaLibrary(
                maxThreads,
                //stackBase,
                kernelId,
                threadPoolState,
                { type -> transferTypes[type] ?: throw Exception("Unknown Type $type") },
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
            ): Property {
                val getter = program.addFunction(
                    name = "wack__get_$name",
                    result = listOf(type),
                    instructions = mutableListOf(
                        Load(type, base, memory.index)
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

    class LocalTransfer(
        val save: WasmFunction,
        val load: WasmFunction,
    ) {
        companion object {
            fun from(
                program: Program,
                type: WasmValueType,
                memory: WasmMemory,
                base: Value,
            ): LocalTransfer {
                val index = Symbol(WasmScope.local, WasmValueType.i32, Index.number(0))
                val value = Symbol(WasmScope.local, type, Index.number(1))
                val address = BinaryOP.plus(BinaryOP.mul(index, Value.i32(8)), base)
                val saveLocal = program.addFunction(
                    name = "wack__save_local_${type.name}",
                    params = listOf(/*index*/ index.type, /*value*/ type),
                    instructions = mutableListOf(
                        Store(
                            Load(type, address, memory.index),
                            value,
                        ),
                    ),
                )
                val loadLocal = program.addFunction(
                    name = "wack__load_local_${type.name}",
                    params = listOf(/*index*/index.type),
                    result = listOf(/*value*/type),
                    instructions = mutableListOf(
                        Load(type, address, memory.index),
                    ),
                )
                return LocalTransfer(
                    save = saveLocal.functionData,
                    load = loadLocal.functionData
                )
            }
        }
    }


}