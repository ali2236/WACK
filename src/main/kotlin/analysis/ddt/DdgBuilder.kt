package analysis.ddt

import ir.statement.Function

class DdgBuilder(val function: Function){
    fun build(): Ddg{
        return Ddg()
    }
}