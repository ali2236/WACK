package ir.annotations

import generation.WatWriter

class Kernel(val kernelId: Int) : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@kernel $kernelId)")
    }
}