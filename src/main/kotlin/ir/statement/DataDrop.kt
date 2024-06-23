package ir.statement

import generation.WatWriter
import ir.wasm.Index

class DataDrop(val dataIndex: Index): BasicStatement() {
    override fun write(out: Appendable) {
        out.append("Data[$dataIndex].drop();\n")
    }

    override fun wat(wat: WatWriter) {
        wat.writeLine("data.drop $dataIndex")
    }
}