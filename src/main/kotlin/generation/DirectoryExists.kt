package generation

import java.io.File

fun insureDirectoryExists(path: String) {
    val dir = File(path)
    if (!dir.exists()) {
        dir.mkdir()
    }
}
