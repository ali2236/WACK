package parser

import dev.aligator.parser.WatLexer
import dev.aligator.parser.WatParser
import dev.aligator.parser.WatParser.SimportContext
import dev.aligator.parser.WatParser.Type_defContext
import dev.aligator.parser.WatParserBaseListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import wasm.*
import java.io.File

object Wat {

    fun parse(path: String): ParseTree {
        // 1. Read
        val reader = File(path).reader(Charsets.UTF_8)

        // 2. Tokenize
        val lexer = WatLexer(CharStreams.fromReader(reader))
        val tokenStream = CommonTokenStream(lexer)

        // 3. Parse
        val parser = WatParser(tokenStream)
        return parser.module()
    }

    fun module(parseTree: ParseTree) : WasmModule {
        val module = WasmModule()
        ParseTreeWalker.DEFAULT.walk(WasmModuleRecorder(module), parseTree)
        return module
    }

}

class WasmModuleRecorder(val module: WasmModule) : WatParserBaseListener() {

    override fun enterType_def(ctx: Type_defContext) {
        val index = Index.next(module.functionTypes)
        val functionType = WasmFunctionType(index)
        val ft = ctx.type_().def_type().func_type()
        val (params, results) = ft.paramsAndResults()
        functionType.params.addAll(params)
        functionType.result.addAll(results)
        module.functionTypes.add(functionType)
    }

    override fun enterSimport(ctx: SimportContext) {
        val moduleName = ctx.name(0).STRING_().text
        val name = ctx.name(1).STRING_().text
        val import = WasmImport(moduleName, name)

        val desc = ctx.import_desc()
        if (desc.FUNC() != null) {
            val (params, results) = desc.func_type().paramsAndResults()
            val function = WasmFunction(
                Index.next(module.functions),
                name,
                WasmFunctionType(
                    index = Index(desc.type_use().var_().NAT().text.toInt()),
                    params = params,
                    result = results,
                ),
                import = import
            )
            module.functions.add(function)
        }

    }

    override fun enterFunc_(ctx: WatParser.Func_Context) {
        val fields = ctx.func_fields()
        val typeIndex = fields.type_use().var_().NAT().text.toInt()

        // Params -> Result Types
        val params = fields.func_fields_body().value_type().toValueTypes()
        val results = fields.func_fields_body().func_result_body().value_type().toValueTypes()

        // Body
        val body = fields.func_fields_body().func_result_body().func_body()
        val locals = body.value_type().toValueTypes()

        // Code
        val code = body.instr_list()

        // Build Function
        val function = WasmFunction(
            Index.next(module.functions),
            type = WasmFunctionType(Index(typeIndex), params, results),
            locals = locals,
            code = code
        )

        module.functions.add(function)
    }

}


