package wasm

data class Index(val number: Int) {

    init {
        if(number < 0){
            throw Error("Invalid Index!")
        }
    }

    override fun toString(): String {
        return number.toString()
    }
    companion object {
        fun <T> next(items: List<T>) : Index {
            return Index(items.size)
        }
    }
}