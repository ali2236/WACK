package ir.annotations

import generation.c.CWriter
import generation.wat.WatWriter
import ir.finder.Visitor

class CallKernel(val kernelIndex: Int) :WackAnnotation{
    override fun wat(wat: WatWriter) {
        wat.write("(@call_kernel $kernelIndex)")
    }

    override fun c(writer: CWriter) {
        writer.write("kernel($kernelIndex)")
    }

    override fun visit(v: Visitor) {

    }

    override fun toString(): String {
        return "@call_kernel($kernelIndex)"
    }

}