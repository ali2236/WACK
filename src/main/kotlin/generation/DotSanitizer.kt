package generation

class DotSanitizer(private val appendable: java.lang.Appendable) : java.lang.Appendable {

    override fun append(csq: CharSequence): java.lang.Appendable {
        return appendable.append(
            csq.replace(Regex("<"), "&lt;")
                .replace(Regex(">"), "&gt;")
        )
    }

    override fun append(csq: CharSequence, start: Int, end: Int): java.lang.Appendable {
        return appendable.append(
            csq.replace(Regex("<"), "&lt;")
                .replace(Regex(">"), "&gt;"), start, end
        )
    }

    override fun append(c: Char): java.lang.Appendable {
        return when (c) {
            '<' -> appendable.append("&lt;")
            '>' -> appendable.append("&gt;")
            else -> appendable.append(c)
        }
    }

}