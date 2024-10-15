package generation.wack

import ir.expression.*
import ir.statement.Nop
import ir.statement.Program
import ir.statement.Statement
import ir.statement.Store
import ir.wasm.Index
import ir.wasm.WasmFunction
import ir.wasm.WasmMemory
import ir.wasm.WasmValueType

class WackThread(
    private val getPropertyAddress: WasmFunction,
    private val getProperty: WasmFunction,
    private val setProperty: WasmFunction,
    val threadsMemory: WasmMemory,
) {

    fun getMutex1(threadId: Expression): Expression {
        return getPropertyAddress.call(threadId, Property.mutex1.value()).result
    }

    fun getMutex2(threadId: Expression): Expression {
        return getPropertyAddress.call(threadId, Property.mutex2.value()).result
    }

    companion object {
        fun generate(program: Program): WackThread {
            val module = program.module

            // memory
            val m = Index.next(module.memories)
            val threadsMemory = WasmMemory(m, 4, 4, true)
            module.memories.add(threadsMemory)

            // functions
            val getThreadPropertyAddress = program.addFunction(
                name = "get_wack_thread_property_address",
                params = listOf(WasmValueType.i32, WasmValueType.i32),
                result = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    BinaryOP(
                        WasmValueType.i32,
                        BinaryOP.Operator.add,
                        BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.mul,
                            Symbol.localI32(Index.number(0)),
                            Value.i32(Property.size()),
                        ),
                        BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.mul,
                            Symbol.localI32(Index.number(1)),
                            Value.i32(4)
                        ),
                    ),
                ),
            )

            val getThreadProperty = program.addFunction(
                name = "get_wack_thread_property",
                params = listOf(WasmValueType.i32, WasmValueType.i32),
                result = listOf(WasmValueType.i32),
                instructions = mutableListOf(
                    Load(
                        WasmValueType.i32,
                        getThreadPropertyAddress.functionData.call(
                            Symbol.localI32(Index.number(0)),
                            Symbol.localI32(Index.number(1)),
                        ).result,
                        threadsMemory.index,
                    ),
                ),
            )

            val setThreadProperty = program.addFunction(
                name = "set_wack_thread_property",
                params = listOf(WasmValueType.i32, WasmValueType.i32, WasmValueType.i32),
                instructions = mutableListOf(
                    Store(
                       Load(
                           WasmValueType.i32,
                           getThreadPropertyAddress.functionData.call(
                               Symbol.localI32(Index.number(0)),
                               Symbol.localI32(Index.number(1)),
                           ).result,
                           threadsMemory.index,
                       ),
                        Symbol.localI32(Index.number(2)),
                    ),
                ),
            )

            return WackThread(
                getThreadPropertyAddress.functionData,
                getThreadProperty.functionData,
                setThreadProperty.functionData,
                threadsMemory,
            )
        }
    }

    private enum class Property {
        mutex1, mutex2;

        fun value(): Expression {
            return Value.i32(this.ordinal)
        }

        companion object {
            fun size(): Int {
                return Property.values().size * 4
            }
        }
    }
}