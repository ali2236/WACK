package ir.annotations

import generation.c.CWriter
import generation.wat.WatWriter
import ir.finder.Visitor

class Kernel(val kernelId: Int) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@kernel $kernelId)")
    }

    override fun c(writer: CWriter) {
        writer.write("kernel($kernelId)")
    }

    override fun visit(v: Visitor) {

    }

    override fun toString(): String {
        return "@kernel($kernelId)"
    }
}