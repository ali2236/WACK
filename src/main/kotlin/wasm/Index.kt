package wasm

data class Index(var number: Int) {
    companion object {
        fun <T> next(items: List<T>) : Index {
            return Index(items.size)
        }
    }
}