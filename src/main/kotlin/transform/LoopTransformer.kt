package transform

import compiler.WAPC
import ir.annotations.For
import ir.annotations.Parallel
import ir.annotations.Skip
import ir.finder.LoopFinder
import ir.statement.Function
import ir.statement.Program
import ir.statement.RangeLoop

fun Program.allNonSkipFunctions(): List<Function> {
    return statements
        .filterIsInstance<Function>()
        .filterNot { it.hasAnnotation(Skip::class.java) }
}

fun Function.allNonSkipRangeLoops(): List<RangeLoop> {
    return LoopFinder(RangeLoop::class.java, !WAPC.params.parallelizeInnerLoops)
        .also { it.visit(this) {} }
        .result()
        .map { it.statement }
}

fun List<RangeLoop>.parallelFor(): List<RangeLoop> {
    return filter { it.hasAnnotation(Parallel::class.java) && it.hasAnnotation(For::class.java) }
}