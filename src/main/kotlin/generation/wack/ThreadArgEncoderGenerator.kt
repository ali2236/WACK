package generation.wack

import ir.expression.*
import ir.statement.Function
import ir.statement.Program
import wasm.Index
import wasm.WasmFunction
import wasm.WasmScope
import wasm.WasmValueType

object ThreadArgEncoderGenerator {

    fun generate(program: Program): ThreadArg {
        val module = program.module

        // types
        val argEncodeType = module.findOraddType(
            params = listOf(WasmValueType.i32, WasmValueType.i32),
            result = listOf(WasmValueType.i32)
        )

        val argDecodeType = module.findOraddType(
            params = listOf(WasmValueType.i32),
            result = listOf(WasmValueType.i32, WasmValueType.i32)
        )

        // function headers
        val wasmArgEncode = WasmFunction(Index.next(module.functions), "arg_encode", argEncodeType)
        module.functions.add(wasmArgEncode)
        val wasmArgDecode = WasmFunction(Index.next(module.functions), "arg_decode", argDecodeType)

        // functions
        val argEncode = Function(
            wasmArgEncode, instructions = mutableListOf(
                BinaryOP(
                    WasmValueType.i32, Operator.xor, Symbol(WasmScope.local, WasmValueType.i32, Index(0)), BinaryOP(
                        WasmValueType.i32,
                        Operator.shl,
                        Symbol(WasmScope.local, WasmValueType.i32, Index(1)),
                        Value(WasmValueType.i32, "16"),
                    )
                )
            )
        )
        program.statements.add(argEncode)

        val argDecode = Function(wasmArgDecode).also {
            val arg = Symbol(WasmScope.local, WasmValueType.i32, Index(0))
            // thread_id
            it.instructions.add(
                BinaryOP(
                    WasmValueType.i32, Operator.and, arg, Value(WasmValueType.i32, "0x0000FFFF")
                )
            )
            // kernel_id
            it.instructions.add(
                BinaryOP(
                    WasmValueType.i32, Operator.shr.copy(signed = BitSign.u), BinaryOP(
                        WasmValueType.i32, Operator.and, arg, Value(WasmValueType.i32, "0xFFFF0000")
                    ), Value(WasmValueType.i32, "16")
                ),
            )
        }
        program.statements.add(argDecode)

        return ThreadArg(
            wasmArgEncode,
            wasmArgDecode,
        )
    }
}