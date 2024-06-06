package analysis.dfa

import ir.expression.Expression

interface DfaValue {
    fun join(other: DfaValue): DfaValue

    class Expr(val value: Expression) : DfaValue {
        override fun join(other: DfaValue): DfaValue {
            return when (other) {
                is Expr -> {
                    if (other.value == value) {
                        return this
                    } else {
                        return Unkown()
                    }
                }

                else -> other.join(this)
            }
        }



        override fun toString(): String {
            return value.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Expr) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }
    }

    class Undeclared : DfaValue {
        override fun join(other: DfaValue): DfaValue {
            return other
        }

        override fun equals(other: Any?): Boolean {
            return other is Undeclared
        }

        override fun toString(): String {
            return "T"
        }
    }

    class Unkown() : DfaValue {
        override fun join(other: DfaValue): DfaValue {
            return this
        }



        override fun toString(): String {
            return "?"
        }

        override fun equals(other: Any?): Boolean {
            return other is Unkown
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }
}

