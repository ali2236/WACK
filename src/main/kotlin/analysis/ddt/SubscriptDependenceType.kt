package analysis.ddt

enum class SubscriptDependenceType {
    ZIV, // no reference to loop symbols
    SIV, // single reference
    MIV; // multiple references

    companion object {
        fun fromSymbolsUsed(n: Int): SubscriptDependenceType {
            return when (n) {
                0 -> ZIV
                1 -> SIV
                else -> MIV
            }
        }
    }
}