package analysis.ddt

enum class Direction {
    Any,
    Equal,
    Greater,
    Less,
    None;

    fun product(other: Direction): Direction {
        return when (this) {
            None -> None
            Any -> other
            Less -> when (other) {
                Any, Less -> Less
                else -> None
            }

            Equal -> when (other) {
                Any, Equal -> Equal
                else -> None
            }

            Greater -> when (other) {
                Any, Greater -> Greater
                else -> None
            }
        }
    }

    companion object {
        fun fromInt(n: Int? = null): Direction {
            if (n == null) {
                return Any
            } else if (n == 0) {
                return Equal
            } else if (n < 0) {
                return Less
            } else if (n > 0) {
                return Greater
            } else {
                return Any
            }
        }
    }
}