package parser

import dev.aligator.parser.WatParser
import wasm.WasmValueType

fun <E : WatParser.Value_typeContext> List<E>.toValueTypes(): MutableList<WasmValueType> {
    return this.map { it.VALUE_TYPE().text }
        .map { WasmValueType.parse(it) }.toMutableList()
}