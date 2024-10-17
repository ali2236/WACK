package generation.wack

import ir.expression.*
import ir.statement.Program
import ir.statement.Statement
import ir.statement.Store
import ir.wasm.Index
import ir.wasm.WasmFunction
import ir.wasm.WasmMemory
import ir.wasm.WasmValueType

class WackThread(
    private val getPropertyAddress: (Expression, Property) -> Expression,
    private val getProperty:  (Expression, Property) -> Expression,
    private val setProperty: (Expression, Property, Expression) -> Statement,
    val threadsMemory: WasmMemory,
) {

    fun getMutex1(threadId: Expression): Expression {
        return getPropertyAddress(threadId, Property.mutex1)
    }

    fun getMutex2(threadId: Expression): Expression {
        return getPropertyAddress(threadId, Property.mutex2)
    }

    fun readMutex1(threadId: Expression): Expression {
        return getProperty(threadId, Property.mutex1)
    }

    fun readMutex2(threadId: Expression): Expression {
        return getProperty(threadId, Property.mutex2)
    }

    fun setMutex1(threadId: Expression, value: Expression) : Statement {
        return setProperty(threadId, Property.mutex1, value)
    }

    fun setMutex2(threadId: Expression, value: Expression) : Statement {
        return setProperty(threadId, Property.mutex2, value)
    }

    companion object {
        fun generate(program: Program): WackThread {
            val module = program.module

            // memory
            val m = Index.next(module.memories)
            val threadsMemory = WasmMemory(m, 8, 8, true)
            module.memories.add(threadsMemory)

            // functions
            val offset = 4096
            val i32Bytes = 4
            val getThreadPropertyAddress = {tid: Expression, prop: Property ->
                BinaryOP.plus(
                    BinaryOP(
                        WasmValueType.i32,
                        BinaryOP.Operator.add,
                        BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.mul,
                            tid,
                            Value.i32(Property.size()),
                        ),
                        BinaryOP(
                            WasmValueType.i32,
                            BinaryOP.Operator.mul,
                            prop.index(),
                            Value.i32(i32Bytes)
                        ),
                    ),
                    Value.i32(offset)
                )
            }

            val getThreadProperty = { tid: Expression, prop: Property ->
                Load(
                    WasmValueType.i32,
                    getThreadPropertyAddress(tid, prop),
                    threadsMemory.index,
                )
            }

            val setThreadProperty = {  tid: Expression, prop: Property, value: Expression ->
                Store(getThreadProperty(tid, prop), value)
            }

            return WackThread(
                getThreadPropertyAddress,
                getThreadProperty,
                setThreadProperty,
                threadsMemory,
            )
        }
    }

    enum class Property {
        mutex1, mutex2;

        fun index(): Expression {
            return Value.i32(this.ordinal)
        }

        companion object {
            fun size(): Int {
                return Property.values().size * 4
            }
        }
    }
}