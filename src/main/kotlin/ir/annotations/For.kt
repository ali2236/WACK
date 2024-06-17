package ir.annotations

import generation.WatWriter

class For : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@for)")
    }
}