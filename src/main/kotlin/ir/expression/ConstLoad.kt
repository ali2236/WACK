package ir.expression

class ConstLoad(val memory: Load, symbol: Symbol) : Symbol(symbol.scope, symbol.type , symbol.index)