package analysis.ddt.tests

import analysis.ddg.Access
import analysis.ddt.DependenceResult
import analysis.ddt.DependenceTest
import ir.expression.Load
import ir.expression.Symbol

class TypeTest : DependenceTest() {
    override fun test(a1: Access, a2: Access): DependenceResult? {
        val oneIsLoad = a1.symbol is Load || a2.symbol is Load
        val oneIsSymbol = a1.symbol is Symbol || a2.symbol is Symbol
        if (oneIsSymbol && oneIsLoad){
            return DependenceResult.noCollision
        }
        return DependenceResult.inconclusive
    }
}