package parser

import parser.WatParser.SimportContext
import parser.WatParser.Type_defContext
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
        // if Charsets.UTF_16LE -> Convert to UTF_8
        // 0x0a should be the last character

        // 2. Tokenize
        val lexer = WatLexer(CharStreams.fromReader(reader))
        val tokenStream = CommonTokenStream(lexer)

        // 3. Parse
        val parser = WatParser(tokenStream)
        return parser.module()
    }

    fun module(parseTree: ParseTree): WasmModule {
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
            locals = locals.toMutableList(),
            code = code
        )

        module.functions.add(function)
    }

    override fun enterSglobal(ctx: WatParser.SglobalContext) {
        val typeCtx = ctx.global_fields().global_type()
        val type = WasmValueType.parse(typeCtx.value_type().text)
        val mutable = typeCtx.MUT() != null
        val globalType = WasmGlobalType(type, mutable)
        val expr = ctx.global_fields().const_expr()
        val instructions = WatVisitor(module).visitArbitrary(expr).toMutableList()
        val global = WasmGlobal(Index.next(module.globals), globalType, instructions)
        module.globals.add(global)
    }

    override fun enterMemory(ctx: WatParser.MemoryContext) {
        val range = ctx.memory_fields().memory_type().NAT()
        val min = range.first().text.toInt()
        val max = if (range.size > 1) range.last().text.toInt() else null
        val shared = false
        val memory = WasmMemory(Index.next(module.memories), min, max, shared)
        module.memories.add(memory)
    }

    override fun enterExport_(ctx: WatParser.Export_Context) {
        val name = ctx.name().text
        val desc = ctx.export_desc()
        val kind = if (desc.FUNC() != null) WasmExportKind.func
        else if (desc.TABLE() != null) WasmExportKind.table
        else if (desc.MEMORY() != null) WasmExportKind.memory
        else if (desc.GLOBAL() != null) WasmExportKind.global
        else throw Error()
        val index = Index(desc.var_().text.toInt())
        val export = WasmExport(name, kind, index)
        module.exports.add(export)
    }

    override fun enterElem(ctx: WatParser.ElemContext?) {
        // TODO: Fix Element Grammar
    }

    override fun enterTable(ctx: WatParser.TableContext) {
        val range = ctx.table_fields().table_type().NAT()
        val min = range.first().text.toInt()
        val max = if (range.size > 1) range.last().text.toInt() else null
        val table = WasmTable(Index.next(module.tables),min, max, WasmRefType.funcref)
        module.tables.add(table)
    }

}


