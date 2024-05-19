package ir.expression

import generation.WatWriter
import ir.statement.Assignable

class TeeSymbol(symbol: Symbol) : Symbol(symbol.scope, symbol.type, symbol.index) {

    override fun wat(wat: WatWriter) {
        // dont pass
    }

}