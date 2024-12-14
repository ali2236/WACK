package compiler

import java.nio.file.Path

interface WebAssemblyCompiler {
    fun run(input: Path, output: Path? = null): Path
}