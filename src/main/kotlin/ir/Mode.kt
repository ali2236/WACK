package ir

import kotlin.reflect.KProperty

object Mode {
    fun insure(property: String, value: Boolean, expected: Boolean) {
        if(value != expected){
            throw Exception("Mode.${property} must be ${if(expected) "enabled" else "disabled"}!")
        }
    }
}