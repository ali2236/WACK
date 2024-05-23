package generation.wack

import generation.wasm.threads.MutexLibrary
import ir.annotations.Parallel
import ir.expression.*
import ir.statement.*
import wasm.WasmGlobal
import wasm.WasmValueType

object ParallelBlockGenerator {
    fun generate(blocks: List<Block>, threadCount: WasmGlobal, mutex: MutexLibrary) {
        blocks.forEach { block ->
            val threadId = block.annotations.filterIsInstance<Parallel>().first().threadId!!
            val oldBody = block.instructions.toTypedArray()
            val zero = Value(WasmValueType.i32, "0")
            val one = Value(WasmValueType.i32, "1")
            block.instructions.clear()
            block.instructions.apply {
                // init: loop threadId from 0 until num_threads
                add(Assignment(threadId, zero))
                add(
                    Loop().also { loop ->
                        loop.parent = block
                        loop.indexInParent = 1
                        loop.instructions.addAll(
                            listOf(
                                // body: run block body
                                *oldBody,
                                Assignment(threadId, BinaryOP(WasmValueType.i32, Operator.add, threadId, one)),
                                BrIf(
                                    BinaryOP(
                                        WasmValueType.i32,
                                        Operator.lt.copy(signed = BitSign.s),
                                        threadId,
                                        threadCount.symbol
                                    ),
                                    block,
                                    1,
                                ),
                                Br(loop,0),
                            )
                        )
                    }
                )
                add(Assignment(threadId, zero))
                add(
                    Loop().also { loop ->
                        loop.parent = block
                        loop.indexInParent = 3
                        loop.instructions.addAll(
                            listOf(
                                // barrier: loop threadId from 0 until num_threads -> join(threadId)
                                mutex.join.call(threadId),
                                Assignment(threadId, BinaryOP(WasmValueType.i32, Operator.add, threadId, one)),
                                BrIf(
                                    BinaryOP(
                                        WasmValueType.i32,
                                        Operator.lt.copy(signed = BitSign.s),
                                        threadId,
                                        threadCount.symbol
                                    ),
                                    block,
                                    1,
                                ),
                                Br(loop,0),
                            )
                        )
                    }
                )
            }
        }
    }
}