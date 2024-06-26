package generation.wack

import ir.expression.*
import ir.statement.Function
import ir.statement.Program
import ir.statement.RawWat
import ir.wasm.*

object ThreadArgEncoderGenerator {

    fun generate(program: Program): ThreadArg {
        val module = program.module

        val encoder = program.addFunction(
            params = listOf(WasmValueType.i32, WasmValueType.i32),
            result = listOf(WasmValueType.i32),
            instructions = mutableListOf(
                RawWat("local.get 0"),
                RawWat("local.get 1"),
                RawWat("i32.const 16"),
                RawWat("i32.shl"),
                RawWat("i32.xor"),
            )
        )

        val threadIdDecoder = program.addFunction(
            params = listOf(WasmValueType.i32),
            result = listOf(WasmValueType.i32),
            instructions = mutableListOf(
                RawWat("local.get 0"),
                RawWat("i32.const 0x0000FFFF"),
                RawWat("i32.and"),
            )
        )

        val kernelIdDecoder = program.addFunction(
            params = listOf(WasmValueType.i32),
            result = listOf(WasmValueType.i32),
            instructions = mutableListOf(
                RawWat("local.get 0"),
                RawWat("i32.const 0xFFFF0000"),
                RawWat("i32.and"),
                RawWat("i32.const 16"),
                RawWat("i32.shr_u"),
            )
        )

        return ThreadArg(
            encoder.functionData,
            threadIdDecoder.functionData,
            kernelIdDecoder.functionData,
        )
    }
}