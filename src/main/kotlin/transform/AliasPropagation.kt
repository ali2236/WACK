package transform

import ir.annotations.Skip
import ir.finder.BreadthFirstExpressionFinder
import ir.statement.Function
import ir.statement.Program
import ir.statement.RangeLoop

//
// Remove Aliasing from this:
// L0 = (M0[L11+28]+(M0[L11+8]*7200))+(M0[L11+4]*8);
// M0[L0+0] = ((M0[L11+40]*M0[(M0[L11+24]+(M0[L11+8]*8800))+(M0[L11+0]*8)+0])*M0[(M0[L11+20]+(M0[L11+0]*7200))+(M0[L11+4]*8)+0])+M0[L0+0];
//
// And make it like this:
// M0[M0[L11+28]+(M0[L11+8]*7200))+(M0[L11+4]*8)+0] = ((M0[L11+40]*M0[(M0[L11+24]+(M0[L11+8]*8800))+(M0[L11+0]*8)+0])*M0[(M0[L11+20]+(M0[L11+0]*7200))+(M0[L11+4]*8)+0])+M0[M0[L11+28]+(M0[L11+8]*7200))+(M0[L11+4]*8)+0];
//
@Deprecated("Not Implemented")
class AliasPropagation : Transformer {
    override fun apply(program: Program) {
        program.statements
            .filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach(this::applyToFunction)
    }

    private fun applyToFunction(function: Function){
        val topLevelRangeLoops = BreadthFirstExpressionFinder(RangeLoop::class.java, true).also { it.visit(function) {} }.result()
       for(topLevelLoop in topLevelRangeLoops){
           throw Error()
       }
    }
}