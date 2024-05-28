package ir.expression

abstract class ImmutableExpression : Expression() {
    override fun clone(): Expression {
        return this
    }
}