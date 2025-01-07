package transform

import ir.expression.Load
import ir.expression.Value
import ir.finder.ExpressionFinder
import ir.statement.Program
import ir.statement.Store

// offset all memory location by 128kb
class OffsetMemoryLocations : Transformer {
    override fun apply(program: Program) {
        val pages = 1
        val pageBytes = 65536
        val memory = program.module.memories.first()
        memory.min += pages

        // move data section forward by [pages]
        program.module.dataSegments.forEach { dataSegment ->
            val value = dataSegment.constExpr.first { it is Value } as Value
            value.value = value.add((pages.toLong() * pageBytes)).value
        }

        // move memory references forward by [pages]
        ExpressionFinder(Load::class.java).also { program.visit(it) }.result().forEach {
            it.offset += (pages * pageBytes)
        }
        ExpressionFinder(Store::class.java).also { program.visit(it) }.result().forEach {
            it.symbol.offset += (pages * pageBytes)
        }

    }

}