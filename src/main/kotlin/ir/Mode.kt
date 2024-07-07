package ir

import kotlin.reflect.KProperty

object Mode {
    fun insure(property: KProperty<Boolean>, expected: Boolean) {
        if(property.equals(expected)){
            throw Exception("Mode.${property.name} must be ${if(expected) "enabled" else "disabled"}!")
        }
    }

    val debug = true
    val multipleMemories = true
    val annotations = false
    val threadSpawnModule = "wasi" /*"wasi_snapshot_preview1"*/
}