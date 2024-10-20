package external

import java.util.Scanner
import kotlin.math.abs
import kotlin.math.max

class PolybenchOutputComparator {

    fun compare(output1: String, output2: String): Double {
        val n1 = getNumbers(output1) {it.nextDouble()}
        val n2 = getNumbers(output2) {it.nextDouble()}

        var difference = 0.0
        for (i in 0 until max(n1.size, n2.size)){
            val a1 = n1[i]
            val a2 = n2[i]
            difference += abs(a1 - a2)
        }

        return difference
    }

    private fun <T : Number> getNumbers(input: String, scan: (Scanner) -> T): List<T> {
        val lines = input.lines()
            .filterNot { it.startsWith("==") || it.startsWith("begin") || it.startsWith("end") }
            .joinToString("\n")
        val scanner = Scanner(lines)
        val results = mutableListOf<T>()
        while (scanner.hasNext()) {
            val v = scan(scanner)
            results.add(v)
        }
        return results
    }
}