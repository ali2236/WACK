package external

import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

fun runTimed(target: () -> Unit): Duration {
    val start = System.nanoTime()
    target()
    val end  = System.nanoTime()
    return (end - start).nanoseconds
}