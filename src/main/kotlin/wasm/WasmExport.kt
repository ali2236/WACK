package wasm

data class WasmExport(val name: String, val kind: WasmExportKind, val index: Index)

enum class WasmExportKind {
    Function,
    Memory,
    Table,
    Global
}