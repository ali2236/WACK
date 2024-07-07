package ir.wasm

data class Index(val name: String) {

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Index) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    fun asInt(): Int {
        return name.toInt()
    }

    fun section(sectionName : String): String {
        try {
            return "\$${sectionName}${name.toInt()}"
        } catch (e: Exception){
            return "\$${name}"
        }
    }

    fun access(type: String): String {
        try {
            return "\$${type}${name.toInt()}"
        } catch (e: Exception){
            return "\$${name}"
        }
    }


    companion object {

        fun number(number: Int): Index{
            if(number < 0){
                throw Error("Invalid Index!")
            }
            return Index(number.toString())
        }

        fun <T> next(items: List<T>) : Index {
            return Index.number(items.size)
        }

        fun parse(text: String?): Index {
            if(text.isNullOrBlank()){
                return Index.number(0)
            }
            try {
                return number(text.toInt())
            } catch (e: NumberFormatException) {}
            if(text.startsWith("$")){
                return Index(text.substring(1))
            } else {
                return Index(text)
            }
        }
    }
}