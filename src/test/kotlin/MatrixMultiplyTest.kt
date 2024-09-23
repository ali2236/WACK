import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.extension
import kotlin.test.Test

class MatrixMultiplyTest {

    @Test
    fun runAll(){
        val allWasmFilePaths = Files.list(Path("./src/test/resources/src"))
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "wasm" }

            println("test file\tinput run time\toutput run time")
        for (path in allWasmFilePaths){
            val inputRunTime = TODO()
            val output = WAPC.compile(path)
            val outputRunTime = TODO()
            println("${path.fileName}\t${inputRunTime}\t${outputRunTime}")
        }
    }

}