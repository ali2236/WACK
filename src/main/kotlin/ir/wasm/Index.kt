package ir.wasm

data class Index(val number: Int) {

    init {
        if(number < 0){
            throw Error("Invalid Index!")
        }
    }

    override fun toString(): String {
        return number.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Index) return false

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number
    }


    companion object {
        fun <T> next(items: List<T>) : Index {
            return Index(items.size)
        }
    }
}