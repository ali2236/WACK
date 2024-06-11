package ir.parser

import parser.WatParser
import ir.wasm.WasmValueType

fun <E : WatParser.Value_typeContext> List<E>.toValueTypes(): MutableList<WasmValueType> {
    return this.map { it.VALUE_TYPE().text }
        .map { WasmValueType.parse(it) }.toMutableList()
}