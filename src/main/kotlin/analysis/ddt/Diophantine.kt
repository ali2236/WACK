package analysis.ddt

class Diophantine(p1: Polynomial, p2: Polynomial) : Polynomial() {

    init {
        addOffset(p1.getOffset())
        addOffset(p2.getOffset())
        for (symbol in p1.symbols()){

        }
        for (symbol in p2.symbols()){

        }
    }
}