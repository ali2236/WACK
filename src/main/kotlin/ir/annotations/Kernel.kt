package ir.annotations

import generation.WatWriter
import ir.finder.Visitor

class Kernel(val kernelId: Int) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@kernel $kernelId)")
    }

    override fun visit(v: Visitor) {

    }
}