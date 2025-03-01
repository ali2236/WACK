package ir.expression

import generation.c.CWriter
import generation.wat.WatWriter
import ir.finder.Visitor
import ir.statement.Increment
import ir.wasm.WasmBitSign
import ir.wasm.WasmValueType

class BinaryOP(val type: WasmValueType, var operator: Operator, var left: Expression, var right: Expression) :
    Expression() {

    override fun write(out: Appendable) {
        if (left is BinaryOP) out.append("(")
        left.write(out)
        if (left is BinaryOP) out.append(")")

        out.append(operator.sign)

        if (right is BinaryOP) out.append("(")
        right.write(out)
        if (right is BinaryOP) out.append(")")
    }

    override fun wat(wat: WatWriter) {
        if (right is Value && (right as Value).value == "0" && operator == Operator.eq) {
            left.wat(wat)
            wat.writeLine("${type}.eqz", this)
        } else {
            left.wat(wat)
            right.wat(wat)
            wat.writeLine("${type}.${operator.wat()}", this)
        }
    }

    override fun c(writer: CWriter) {
        if (left is BinaryOP) writer.write("(")
        left.c(writer)
        if (left is BinaryOP) writer.write(")")

        writer.write(operator.sign)

        if (right is BinaryOP) writer.write("(")
        right.c(writer)
        if (right is BinaryOP) writer.write(")")
    }

    override fun visit(v: Visitor) {
        v.visit(left) { this.left = it as Expression }
        v.visit(right) { this.right = it as Expression }
    }

    override fun clone(): Expression {
        return BinaryOP(type, operator.copy(), left.clone(), right.clone())
    }

    override fun exprType(): WasmValueType {
        if (operator.boolean) {
            return WasmValueType.i32
        }
        return type
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BinaryOP) return false

        if (type != other.type) return false
        if (operator != other.operator) return false
        if (left != other.left) return false
        if (right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + operator.hashCode()
        result = 31 * result + left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    // range-loop condition
    fun endInclusive(direction: Increment.Direction): Expression {
            if (right is Value) {
                return when (operator.sign) {
                    "<" -> (right as Value).add(-1) // i < 100  -> i++,99
                    "<=" -> (right as Value)        // i <= 100 -> i++,100
                    ">" -> (right as Value).add(1)  // i > 100  -> i--,101
                    ">=" -> (right as Value)        // i >= 100 -> i--,100
                    "!=" -> (right as Value)        // i != 100 -> (i++,99)|(i--,101)
                        .add(if (direction == Increment.Direction.plus) -1 else 1)
                    else -> throw Exception("operator ${operator.sign} is not supported")
                }
            } else {
                return when (operator.sign) {
                    "<" -> minus(right, Value.one)
                    "<=" -> right
                    ">" -> plus(right, Value.one)
                    ">=" -> right
                    "!=" -> plus(right, Value.i32(if (direction == Increment.Direction.plus) -1 else 1))
                    else -> throw Exception("operator ${operator.sign} is not supported")
                }
            }
        }

    val endExclusive: Expression
        get() {
            if (right is Value) {
                return when (operator.sign) {
                    "<" -> (right as Value)         // i < 100  -> i++
                    "<=" -> (right as Value).add(1) // i <= 100 -> i++
                    ">" -> (right as Value)         // i > 100  -> i--
                    ">=" -> (right as Value).add(-1)// i >= 100 -> i--
                    "!=" -> (right as Value)        // i != 100 -> i++|i--
                    else -> throw Exception("operator ${operator.sign} is not supported")
                }
            } else {
                return when (operator.sign) {
                    "<" -> right
                    "<=" -> plus(right, Value.one)
                    ">" -> right
                    ">=" -> plus(right, Value.i32(-1))
                    "!=" -> right
                    else -> throw Exception("operator ${operator.sign} is not supported")
                }
            }
        }



    data class Operator(val sign: String, val watName: String, var signed: WasmBitSign? = null) {

        val boolean: Boolean
            get() = when (sign) {
                "==", "!=", "<", "<=", ">", ">=" -> true
                else -> false
            }

        companion object {
            val eq = Operator("==", "eq")
            val neq = Operator("!=", "ne")
            val lt = Operator("<", "lt")
            val le = Operator("<=", "le")
            val gt = Operator(">", "gt")
            val ge = Operator(">=", "ge")
            val add = Operator("+", "add")
            val sub = Operator("-", "sub")
            val div = Operator("/", "div")
            val mul = Operator("*", "mul")
            val shl = Operator("<<", "shl")
            val shr = Operator(">>", "shr")
            val and = Operator("&", "and")
            val or = Operator("|", "or")
            val xor = Operator("^", "xor")
            val rem = Operator("%", "rem")
            val rotl = Operator("<<<", "rotl")
            val rotr = Operator(">>>", "rotr")
            val copysign = Operator("±", "copysign")
        }

        fun invert(): Operator {
            return when (sign) {
                "==" -> neq
                "!=" -> eq
                "<" -> ge
                "<=" -> gt
                ">" -> le
                ">=" -> lt
                "+" -> sub
                "-" -> add
                "/" -> mul
                "*" -> div
                "<<" -> shr
                ">>" -> shl
                "&" -> or
                "|" -> and
                else -> this
            }.copy(signed = signed)
        }

        fun reverse(): Operator {
            return when (sign) {
                "<" -> ge
                "<=" -> ge
                ">" -> le
                ">=" -> le
                else -> this
            }.copy(signed = signed)
        }

        fun wat(): String {
            if (signed != null) {
                return "${watName}_$signed"
            } else {
                return watName
            }
        }
    }

    companion object {
        fun increment(symbol: Symbol): BinaryOP {
            return BinaryOP(
                symbol.type,
                Operator.add,
                symbol,
                Value(symbol.type, "1"),
            )
        }

        fun mul(left: Expression, i: Value): BinaryOP {
            return BinaryOP(left.exprType(), Operator.mul, left, i)
        }

        fun plus(left: Expression, i: Value): BinaryOP {
            return BinaryOP(left.exprType(), Operator.add, left, i)
        }

        fun minus(left: Expression, i: Value): BinaryOP {
            return BinaryOP(left.exprType(), Operator.sub, left, i)
        }
    }

}