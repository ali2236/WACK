package transform

import analysis.dfa.DfaValue
import ir.annotations.For
import ir.annotations.Skip
import ir.expression.*
import ir.finder.BreadthFirstExpressionFinder
import ir.finder.ReverseVisitor
import ir.finder.Visitor
import ir.statement.*
import ir.statement.Function

class LoopNormalization : Transformer {
    override fun apply(program: Program) {
        program.statements.filterIsInstance<Function>()
            .filterNot { it.hasAnnotation(Skip::class.java) }
            .forEach { function ->
                // find all for-loops
                val forLoops = BreadthFirstExpressionFinder(RangeLoop::class.java, false)
                    .also { function.visit(it) }
                    .result()

                for (forLoop in forLoops) {
                    normalizeLoop(forLoop)
                }
            }
    }

    // if loop range doesn't start from 0:
    //  if L > 0 => 0 < i < U-L
    // TODO: or increment is not +1
    // then replace symbol with <increment*symbol+offset>
    private fun normalizeLoop(loop: RangeLoop) {
        if (loop.range.from is Value && loop.range.from != Value.zero) {
            val symbol = loop.symbol
            val from = loop.range.from
            val to = loop.range.to
            val symbolType = when (symbol) {
                is Symbol -> symbol.exprType()
                is Load -> symbol.exprType()
                else -> throw Error("Invalid Loop Symbol Type!")
            }
            // replacements
            lateinit var newFrom: Expression
            lateinit var newTo: Expression
            // replace loop range:
            // 1. before loop body: replace <symbol> = <from> with <symbol> = 0
            val rv = ReverseVisitor()
            rv.searchInParentBlocks(loop, object : Visitor() {
                override fun visit(v: Statement, replace: (Statement) -> Unit) {
                    when (v) {
                        is AssignmentStore -> {
                            if (v.assignedTo() == symbol) {
                                newFrom = Value(symbolType, "0")
                                v.replaceAssign(newFrom)
                                rv.stop = true
                            }
                        }
                    }
                }
            })

            // 2. in loop condition: replace <symbol> < <to> with <symbol> < <to> - <from>
            val bv = object : Visitor() {
                override fun visit(v: Statement, replace: (Statement) -> Unit) {
                    if (v == to) {
                        newTo = BinaryOP(
                            to.exprType(),
                            BinaryOP.Operator.sub,
                            to,
                            from,
                        )
                        replace(newTo)
                    }
                    super.visit(v, replace)
                }
            }
            bv.visit(loop) {}

            // 3. in loop body: replace <symbol> with <symbol>+<from>
            val cv = object : Visitor() {
                override fun visit(v: Statement, replace: (Statement) -> Unit) {
                    if (v is If && v.condition == loop.condition){
                        super.visit(v.instructions) {i, stmt -> v.instructions[i] = stmt}
                        return
                    } else if (v is Increment){
                        return
                    }
                    if (v == symbol) {
                        replace(
                            BinaryOP(
                                symbolType,
                                BinaryOP.Operator.add,
                                v as Expression,
                                from,
                            )
                        )
                    }
                    super.visit(v, replace)
                }
            }
            cv.visit(loop.instructions) { i, stmt -> loop.instructions[i] = stmt }

            // 4. in loop metadata: replace loop-range with new loop-range
            loop.range = DfaValue.Range(newFrom, newTo)
        }
    }
}