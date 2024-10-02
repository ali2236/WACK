package analysis.dfa

import ir.expression.Expression
import ir.expression.Value

interface DfaValue {
    fun join(other: DfaValue): DfaValue
    fun asValue(): Value?

    class Expr(val value: Expression) : DfaValue {
        override fun join(other: DfaValue): DfaValue {
            return when (other) {
                is Expr -> {
                    if (other.value == value) {
                        return this
                    } else {
                        return Alias()
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

    open class Alias(val expr: Expression? = null) : DfaValue {
        override fun join(other: DfaValue): DfaValue {
            return Alias()
        }


        override fun toString(): String {
            return expr?.toString() ?: "?"
        }

        override fun equals(other: Any?): Boolean {
            return other is Alias
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        override fun asValue(): Value? {
            return null
        }
    }

    // to: exclusive in loop - inclusive outside
    class Range(val from: Expression,val to: Expression) : Alias(){
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

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + from.hashCode()
            result = 31 * result + to.hashCode()
            return result
        }


    }
}

