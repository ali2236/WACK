package wasm

data class Index(var number: Int) {

    override fun toString(): String {
        return number.toString()
    }
    companion object {
        fun <T> next(items: List<T>) : Index {
            return Index(items.size)
        }
    }
}