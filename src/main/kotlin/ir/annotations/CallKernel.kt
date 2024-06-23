package ir.annotations

import generation.WatWriter
import ir.finder.Visitor

class CallKernel(val kernelIndex: Int) :WackAnnotation{
    override fun wat(wat: WatWriter) {
        wat.write("(@call_kernel $kernelIndex)")
    }

    override fun visit(v: Visitor) {

    }

}