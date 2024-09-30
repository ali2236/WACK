package analysis.ddt

enum class Direction {
    any,
    equal,
    greater,
    less;

    companion object {
        fun fromInt(n : Int? = null) : Direction {
            if (n == null){
                return any
            } else if(n == 0){
                return equal
            } else if(n < 0){
                return less
            } else if (n > 0){
                return greater
            } else {
                return any
            }
        }
    }
}