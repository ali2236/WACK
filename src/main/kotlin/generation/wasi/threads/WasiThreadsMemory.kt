package generation.wasi.threads

import ir.statement.Program
import ir.wasm.WasmExport
import ir.wasm.WasmExportKind
import ir.wasm.WasmImport

// Import & Export All memories - mark all memories as shared - declare memories in imports
class WasiThreadsMemory(val specialFirst: Boolean = true) {
    fun apply(program: Program) {
        val module = program.module

        // remove all exports
        module.exports.removeIf { it.kind == WasmExportKind.memory }

        // make all memories shared + imported + exported
        program.module.memories.forEach {

            // make shared
            it.shared = true
            if (it.max == null) {
                it.max = it.min
            }

            // add it to import
            if (it.import == null) {
                val name = if (it.index.name == "0" && specialFirst) "\"memory\"" else "\"memory-${it.index}\""
                it.import = WasmImport("\"env\"", name)
            }

            // add it to export
            module.exports.add(WasmExport(it.import!!.name, WasmExportKind.memory, it.index))
        }
    }
}