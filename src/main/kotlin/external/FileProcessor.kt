package external

import java.io.File

interface FileProcessor {
    fun process(input: File) : File
}