package analysis.ddt

data class ArrayBounds(val low: Long, val high: Long) {
    fun intersect(range: ArrayBounds): Boolean {
        return range.low <= high && range.high >= low
    }
}