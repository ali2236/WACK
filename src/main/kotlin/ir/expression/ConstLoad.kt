package ir.expression

class ConstLoad(val memory: Load, symbol: Symbol) : Symbol(memory.type , symbol.symbol)