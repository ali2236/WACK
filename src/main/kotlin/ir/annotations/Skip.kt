package ir.annotations

import generation.WatWriter

class Skip : WackAnnotation {
    override fun wat(wat: WatWriter) {
        wat.write("(@skip)")
    }
}