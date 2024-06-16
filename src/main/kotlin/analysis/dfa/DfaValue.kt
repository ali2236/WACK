package analysis.dfa

import ir.expression.Expression
import ir.expression.Value

interface DfaValue {
    fun join(other: DfaValue): DfaValue
    fun asValue(): Value

    class Expr(val value: Expression) : DfaValue {
        override fun join(other: DfaValue): DfaValue {
            return when (other) {
                is Expr -> {
                    if (other.value == value) {
                        return this
                    } else {
                        return Unknown()
                    }
                }

                else -> other.join(this)
            }
        }

        override fun asValue(): Value {
            if (value is Value) {
                return value
            } else {
                throw Exception()
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

/*    class Undeclared : DfaValue {
        override fun join(other: DfaValue): DfaValue {
            return other
        }

        override fun equals(other: Any?): Boolean {
            return other is Undeclared
        }

        override fun toString(): String {
            return "T"
        }

        override fun asValue(): Value {
            throw Exception()
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }*/

    open class Unknown() : DfaValue {
        override fun join(other: DfaValue): DfaValue {
            return Unknown()
        }


        override fun toString(): String {
            return "?"
        }

        override fun equals(other: Any?): Boolean {
            return other is Unknown
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        override fun asValue(): Value {
            throw Exception()
        }
    }

    // to: exclusive in loop - inclusive outside
    class Range(val from: Value,val to: Value) : Unknown(){
        override fun toString(): String {
            return "[$from, $to]"
        }

        override fun equals(other: Any?): Boolean {
            if(other is Range){
                if (from != other.from) return false
                if (to != other.to) return false
                return true
            } else {
                return super.equals(other)
            }
        }


    }
}

