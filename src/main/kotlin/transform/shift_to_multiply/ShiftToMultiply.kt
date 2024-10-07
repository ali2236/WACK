package transform.shift_to_multiply

import ir.annotations.Skip
import ir.finder.Replaceable
import ir.expression.*
import ir.finder.Finders
import ir.statement.Function
import ir.statement.Program
import ir.statement.Statement
import transform.Transformer
import transform.restructure.Restructure
import kotlin.math.pow

class ShiftToMultiply : Transformer {

    override fun apply(program: Program) {
        val replacer = ShiftLoadReplacer()
        program.statements
            .filterIsInstance<Function>()
            .filter { !it.hasAnnotation(Skip::class.java) }
            .forEach { it.visit(replacer) }
    }

}