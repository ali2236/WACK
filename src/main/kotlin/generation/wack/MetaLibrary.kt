package generation.wack

import compiler.WAPC
import ir.expression.*
import ir.statement.Assignment
import ir.statement.If
import ir.statement.Program
import ir.statement.Store
import ir.wasm.*

// first 16kb
class MetaLibrary(
    val maxThreads: Property,
    val tasksCount: Property,
    val kernelId: Property,
    val threadPoolState: Property,
    val localTransfer: (WasmValueType) -> LocalTransfer,
    val nonZeroMaxThreads: WasmFunction,
    val resetTasks: WasmFunction,
    val getTask: WasmFunction,
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
            val tasksCount = Property.fromAddress(
                program,
                "tasks_count",
                WasmValueType.i32,
                metaMemory,
                base.add(1L * i32Bytes),
            )
            val kernelId = Property.fromAddress(
                program,
                "kernel_id",
                WasmValueType.i32,
                metaMemory,
                base.add(2L * i32Bytes),
            )
            val threadPoolState = Property.fromAddress(
                program,
                "thread_pool_state",
                WasmValueType.i32,
                metaMemory,
                base.add(3L * i32Bytes),
            )
            val taskId = Property.fromAddress(
                program,
                "task_id",
                WasmValueType.i32,
                metaMemory,
                base.add(4L * i32Bytes),
            )

            val _tc = Symbol.localI32(Index.number(0))
            val nonZeroMaxThreads = program.addFunction(
                "wack__non_zero_max_threads",
                result = listOf(WasmValueType.i32),
                locals = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    Value.i32(WAPC.params.threads),
                    /*Assignment(_tc, maxThreads.get.call().result),
                    ResultBlock(
                        WasmValueType.i32,
                        If(
                            BinaryOP(WasmValueType.i32, BinaryOP.Operator.eq, _tc, Value.zero),
                            trueBody = mutableListOf(Value.i32(WAPC.params.threads)),
                            elseBody = mutableListOf(_tc),
                            type = WasmValueType.i32
                        ),
                    ),*/
                ),
            )

            val resetTasks = program.addFunction(
                "wack__reset_tasks",
                instructions = mutableListOf(
                    taskId.set.call(Value.zero),
                ),
            )

            val getTask = program.addFunction(
                "wack__get_task",
                locals = listOf(WasmValueType.i32),
                result = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    Assignment(_tc, taskId.get.call().result),
                    taskId.set.call(BinaryOP.plus(_tc, Value.one)),
                    _tc,
                ),
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
                tasksCount,
                kernelId,
                threadPoolState,
                { type -> transferTypes[type] ?: throw Exception("Unknown Type $type") },
                nonZeroMaxThreads.functionData,
                resetTasks.functionData,
                getTask.functionData,
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