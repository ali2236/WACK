// Generated from C:/Users/Aligator/Projects/WACK/src/main/java/parser/WatParser.g4 by ANTLR 4.13.1
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class WatParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAR=1, RPAR=2, NAT=3, INT=4, FLOAT=5, STRING_=6, VALUE_TYPE=7, CONST=8, 
		FUNCREF=9, MUT=10, NOP=11, UNREACHABLE=12, DROP=13, BLOCK=14, LOOP=15, 
		END=16, BR=17, BR_IF=18, BR_TABLE=19, RETURN=20, IF=21, THEN=22, ELSE=23, 
		SELECT=24, CALL=25, CALL_INDIRECT=26, LOCAL_GET=27, LOCAL_SET=28, LOCAL_TEE=29, 
		GLOBAL_GET=30, GLOBAL_SET=31, LOAD=32, STORE=33, OFFSET_EQ_NAT=34, ALIGN_EQ_NAT=35, 
		UNARY=36, BINARY=37, TEST=38, COMPARE=39, CONVERT=40, MEMORY_SIZE=41, 
		MEMORY_GROW=42, MEMORY_COPY=43, MEMORY_FILL=44, TYPE=45, FUNC=46, START_=47, 
		PARAM=48, RESULT=49, LOCAL=50, GLOBAL=51, TABLE=52, MEMORY=53, ELEM=54, 
		DATA=55, OFFSET=56, IMPORT=57, EXPORT=58, MODULE=59, BIN=60, QUOTE=61, 
		SCRIPT=62, REGISTER=63, INVOKE=64, GET=65, ASSERT_MALFORMED=66, ASSERT_INVALID=67, 
		ASSERT_UNLINKABLE=68, ASSERT_RETURN=69, ASSERT_RETURN_CANONICAL_NAN=70, 
		ASSERT_RETURN_ARITHMETIC_NAN=71, ASSERT_TRAP=72, ASSERT_EXHAUSTION=73, 
		INPUT=74, OUTPUT=75, VAR=76, SPACE=77, COMMENT=78;
	public static final int
		RULE_value = 0, RULE_name = 1, RULE_value_type = 2, RULE_elem_type = 3, 
		RULE_global_type = 4, RULE_def_type = 5, RULE_func_type = 6, RULE_table_type = 7, 
		RULE_memory_type = 8, RULE_type_use = 9, RULE_literal = 10, RULE_var_ = 11, 
		RULE_bind_var = 12, RULE_instr = 13, RULE_plain_instr = 14, RULE_select_type = 15, 
		RULE_call_instr_params = 16, RULE_block_instr = 17, RULE_block_type = 18, 
		RULE_block = 19, RULE_expr = 20, RULE_expr1 = 21, RULE_call_expr_type = 22, 
		RULE_call_expr_params = 23, RULE_call_expr_results = 24, RULE_if_block = 25, 
		RULE_instr_list = 26, RULE_const_expr = 27, RULE_func_ = 28, RULE_func_fields = 29, 
		RULE_func_fields_import = 30, RULE_func_fields_import_result = 31, RULE_func_fields_body = 32, 
		RULE_func_result_body = 33, RULE_func_body = 34, RULE_offset = 35, RULE_elem = 36, 
		RULE_table = 37, RULE_table_fields = 38, RULE_data = 39, RULE_memory = 40, 
		RULE_memory_fields = 41, RULE_sglobal = 42, RULE_global_fields = 43, RULE_import_desc = 44, 
		RULE_simport = 45, RULE_inline_import = 46, RULE_export_desc = 47, RULE_export_ = 48, 
		RULE_inline_export = 49, RULE_type_ = 50, RULE_type_def = 51, RULE_start_ = 52, 
		RULE_module_field = 53, RULE_module_ = 54, RULE_script_module = 55, RULE_action_ = 56, 
		RULE_assertion = 57, RULE_cmd = 58, RULE_meta = 59, RULE_wconst = 60, 
		RULE_const_list = 61, RULE_script = 62, RULE_module = 63;
	private static String[] makeRuleNames() {
		return new String[] {
			"value", "name", "value_type", "elem_type", "global_type", "def_type", 
			"func_type", "table_type", "memory_type", "type_use", "literal", "var_", 
			"bind_var", "instr", "plain_instr", "select_type", "call_instr_params", 
			"block_instr", "block_type", "block", "expr", "expr1", "call_expr_type", 
			"call_expr_params", "call_expr_results", "if_block", "instr_list", "const_expr", 
			"func_", "func_fields", "func_fields_import", "func_fields_import_result", 
			"func_fields_body", "func_result_body", "func_body", "offset", "elem", 
			"table", "table_fields", "data", "memory", "memory_fields", "sglobal", 
			"global_fields", "import_desc", "simport", "inline_import", "export_desc", 
			"export_", "inline_export", "type_", "type_def", "start_", "module_field", 
			"module_", "script_module", "action_", "assertion", "cmd", "meta", "wconst", 
			"const_list", "script", "module"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", null, null, null, null, null, null, "'funcref'", 
			"'mut'", "'nop'", "'unreachable'", "'drop'", "'block'", "'loop'", "'end'", 
			"'br'", "'br_if'", "'br_table'", "'return'", "'if'", "'then'", "'else'", 
			"'select'", "'call'", "'call_indirect'", "'local.get'", "'local.set'", 
			"'local.tee'", "'global.get'", "'global.set'", null, null, null, null, 
			null, null, null, null, null, "'memory.size'", "'memory.grow'", "'memory.copy'", 
			"'memory.fill'", "'type'", "'func'", "'start'", "'param'", "'result'", 
			"'local'", "'global'", "'table'", "'memory'", "'elem'", "'data'", "'offset'", 
			"'import'", "'export'", "'module'", "'binary'", "'quote'", "'script'", 
			"'register'", "'invoke'", "'get'", "'assert_malformed'", "'assert_invalid'", 
			"'assert_unlinkable'", "'assert_return'", "'assert_return_canonical_nan'", 
			"'assert_return_arithmetic_nan'", "'assert_trap'", "'assert_exhaustion'", 
			"'input'", "'output'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LPAR", "RPAR", "NAT", "INT", "FLOAT", "STRING_", "VALUE_TYPE", 
			"CONST", "FUNCREF", "MUT", "NOP", "UNREACHABLE", "DROP", "BLOCK", "LOOP", 
			"END", "BR", "BR_IF", "BR_TABLE", "RETURN", "IF", "THEN", "ELSE", "SELECT", 
			"CALL", "CALL_INDIRECT", "LOCAL_GET", "LOCAL_SET", "LOCAL_TEE", "GLOBAL_GET", 
			"GLOBAL_SET", "LOAD", "STORE", "OFFSET_EQ_NAT", "ALIGN_EQ_NAT", "UNARY", 
			"BINARY", "TEST", "COMPARE", "CONVERT", "MEMORY_SIZE", "MEMORY_GROW", 
			"MEMORY_COPY", "MEMORY_FILL", "TYPE", "FUNC", "START_", "PARAM", "RESULT", 
			"LOCAL", "GLOBAL", "TABLE", "MEMORY", "ELEM", "DATA", "OFFSET", "IMPORT", 
			"EXPORT", "MODULE", "BIN", "QUOTE", "SCRIPT", "REGISTER", "INVOKE", "GET", 
			"ASSERT_MALFORMED", "ASSERT_INVALID", "ASSERT_UNLINKABLE", "ASSERT_RETURN", 
			"ASSERT_RETURN_CANONICAL_NAN", "ASSERT_RETURN_ARITHMETIC_NAN", "ASSERT_TRAP", 
			"ASSERT_EXHAUSTION", "INPUT", "OUTPUT", "VAR", "SPACE", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "WatParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WatParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(WatParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(WatParser.FLOAT, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public TerminalNode STRING_() { return getToken(WatParser.STRING_, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(STRING_);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Value_typeContext extends ParserRuleContext {
		public TerminalNode VALUE_TYPE() { return getToken(WatParser.VALUE_TYPE, 0); }
		public Value_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterValue_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitValue_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitValue_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Value_typeContext value_type() throws RecognitionException {
		Value_typeContext _localctx = new Value_typeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_value_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(VALUE_TYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Elem_typeContext extends ParserRuleContext {
		public TerminalNode FUNCREF() { return getToken(WatParser.FUNCREF, 0); }
		public Elem_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elem_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterElem_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitElem_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitElem_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Elem_typeContext elem_type() throws RecognitionException {
		Elem_typeContext _localctx = new Elem_typeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_elem_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(FUNCREF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Global_typeContext extends ParserRuleContext {
		public Value_typeContext value_type() {
			return getRuleContext(Value_typeContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode MUT() { return getToken(WatParser.MUT, 0); }
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Global_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterGlobal_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitGlobal_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitGlobal_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Global_typeContext global_type() throws RecognitionException {
		Global_typeContext _localctx = new Global_typeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_global_type);
		try {
			setState(142);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VALUE_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				value_type();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(LPAR);
				setState(138);
				match(MUT);
				setState(139);
				value_type();
				setState(140);
				match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Def_typeContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode FUNC() { return getToken(WatParser.FUNC, 0); }
		public Func_typeContext func_type() {
			return getRuleContext(Func_typeContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Def_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterDef_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitDef_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitDef_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Def_typeContext def_type() throws RecognitionException {
		Def_typeContext _localctx = new Def_typeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_def_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(LPAR);
			setState(145);
			match(FUNC);
			setState(146);
			func_type();
			setState(147);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_typeContext extends ParserRuleContext {
		public List<TerminalNode> LPAR() { return getTokens(WatParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(WatParser.LPAR, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(WatParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(WatParser.RPAR, i);
		}
		public List<TerminalNode> RESULT() { return getTokens(WatParser.RESULT); }
		public TerminalNode RESULT(int i) {
			return getToken(WatParser.RESULT, i);
		}
		public List<TerminalNode> PARAM() { return getTokens(WatParser.PARAM); }
		public TerminalNode PARAM(int i) {
			return getToken(WatParser.PARAM, i);
		}
		public List<Bind_varContext> bind_var() {
			return getRuleContexts(Bind_varContext.class);
		}
		public Bind_varContext bind_var(int i) {
			return getRuleContext(Bind_varContext.class,i);
		}
		public List<Value_typeContext> value_type() {
			return getRuleContexts(Value_typeContext.class);
		}
		public Value_typeContext value_type(int i) {
			return getRuleContext(Value_typeContext.class,i);
		}
		public Func_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterFunc_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitFunc_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitFunc_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_typeContext func_type() throws RecognitionException {
		Func_typeContext _localctx = new Func_typeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_func_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(149);
				match(LPAR);
				setState(168);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(150);
					match(RESULT);
					setState(154);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(151);
						value_type();
						}
						}
						setState(156);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(157);
					match(PARAM);
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(158);
						value_type();
						}
						}
						setState(163);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 3:
					{
					setState(164);
					match(PARAM);
					setState(165);
					bind_var();
					setState(166);
					value_type();
					}
					break;
				}
				setState(170);
				match(RPAR);
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Table_typeContext extends ParserRuleContext {
		public List<TerminalNode> NAT() { return getTokens(WatParser.NAT); }
		public TerminalNode NAT(int i) {
			return getToken(WatParser.NAT, i);
		}
		public Elem_typeContext elem_type() {
			return getRuleContext(Elem_typeContext.class,0);
		}
		public Table_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterTable_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitTable_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitTable_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_typeContext table_type() throws RecognitionException {
		Table_typeContext _localctx = new Table_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_table_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(NAT);
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAT) {
				{
				setState(177);
				match(NAT);
				}
			}

			setState(180);
			elem_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Memory_typeContext extends ParserRuleContext {
		public List<TerminalNode> NAT() { return getTokens(WatParser.NAT); }
		public TerminalNode NAT(int i) {
			return getToken(WatParser.NAT, i);
		}
		public Memory_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memory_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterMemory_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitMemory_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitMemory_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Memory_typeContext memory_type() throws RecognitionException {
		Memory_typeContext _localctx = new Memory_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_memory_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(NAT);
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAT) {
				{
				setState(183);
				match(NAT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type_useContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode TYPE() { return getToken(WatParser.TYPE, 0); }
		public Var_Context var_() {
			return getRuleContext(Var_Context.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Type_useContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_use; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterType_use(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitType_use(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitType_use(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_useContext type_use() throws RecognitionException {
		Type_useContext _localctx = new Type_useContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type_use);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(LPAR);
			setState(187);
			match(TYPE);
			setState(188);
			var_();
			setState(189);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NAT() { return getToken(WatParser.NAT, 0); }
		public TerminalNode INT() { return getToken(WatParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(WatParser.FLOAT, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_Context extends ParserRuleContext {
		public TerminalNode NAT() { return getToken(WatParser.NAT, 0); }
		public TerminalNode VAR() { return getToken(WatParser.VAR, 0); }
		public Var_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterVar_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitVar_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitVar_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_Context var_() throws RecognitionException {
		Var_Context _localctx = new Var_Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_var_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			_la = _input.LA(1);
			if ( !(_la==NAT || _la==VAR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Bind_varContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(WatParser.VAR, 0); }
		public Bind_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bind_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterBind_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitBind_var(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitBind_var(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bind_varContext bind_var() throws RecognitionException {
		Bind_varContext _localctx = new Bind_varContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_bind_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(VAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstrContext extends ParserRuleContext {
		public Plain_instrContext plain_instr() {
			return getRuleContext(Plain_instrContext.class,0);
		}
		public Block_instrContext block_instr() {
			return getRuleContext(Block_instrContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterInstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitInstr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitInstr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstrContext instr() throws RecognitionException {
		InstrContext _localctx = new InstrContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_instr);
		try {
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONST:
			case NOP:
			case UNREACHABLE:
			case DROP:
			case BR:
			case BR_IF:
			case BR_TABLE:
			case RETURN:
			case SELECT:
			case CALL:
			case CALL_INDIRECT:
			case LOCAL_GET:
			case LOCAL_SET:
			case LOCAL_TEE:
			case GLOBAL_GET:
			case GLOBAL_SET:
			case LOAD:
			case STORE:
			case UNARY:
			case BINARY:
			case TEST:
			case COMPARE:
			case CONVERT:
			case MEMORY_SIZE:
			case MEMORY_GROW:
			case MEMORY_COPY:
			case MEMORY_FILL:
				enterOuterAlt(_localctx, 1);
				{
				setState(197);
				plain_instr();
				}
				break;
			case BLOCK:
			case LOOP:
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				block_instr();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(199);
				expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Plain_instrContext extends ParserRuleContext {
		public TerminalNode UNREACHABLE() { return getToken(WatParser.UNREACHABLE, 0); }
		public TerminalNode NOP() { return getToken(WatParser.NOP, 0); }
		public TerminalNode DROP() { return getToken(WatParser.DROP, 0); }
		public TerminalNode SELECT() { return getToken(WatParser.SELECT, 0); }
		public Select_typeContext select_type() {
			return getRuleContext(Select_typeContext.class,0);
		}
		public TerminalNode BR() { return getToken(WatParser.BR, 0); }
		public List<Var_Context> var_() {
			return getRuleContexts(Var_Context.class);
		}
		public Var_Context var_(int i) {
			return getRuleContext(Var_Context.class,i);
		}
		public TerminalNode BR_IF() { return getToken(WatParser.BR_IF, 0); }
		public TerminalNode BR_TABLE() { return getToken(WatParser.BR_TABLE, 0); }
		public TerminalNode RETURN() { return getToken(WatParser.RETURN, 0); }
		public TerminalNode CALL() { return getToken(WatParser.CALL, 0); }
		public TerminalNode CALL_INDIRECT() { return getToken(WatParser.CALL_INDIRECT, 0); }
		public Call_instr_paramsContext call_instr_params() {
			return getRuleContext(Call_instr_paramsContext.class,0);
		}
		public Type_useContext type_use() {
			return getRuleContext(Type_useContext.class,0);
		}
		public TerminalNode LOCAL_GET() { return getToken(WatParser.LOCAL_GET, 0); }
		public TerminalNode LOCAL_SET() { return getToken(WatParser.LOCAL_SET, 0); }
		public TerminalNode LOCAL_TEE() { return getToken(WatParser.LOCAL_TEE, 0); }
		public TerminalNode GLOBAL_GET() { return getToken(WatParser.GLOBAL_GET, 0); }
		public TerminalNode GLOBAL_SET() { return getToken(WatParser.GLOBAL_SET, 0); }
		public TerminalNode LOAD() { return getToken(WatParser.LOAD, 0); }
		public TerminalNode OFFSET_EQ_NAT() { return getToken(WatParser.OFFSET_EQ_NAT, 0); }
		public TerminalNode ALIGN_EQ_NAT() { return getToken(WatParser.ALIGN_EQ_NAT, 0); }
		public TerminalNode STORE() { return getToken(WatParser.STORE, 0); }
		public TerminalNode MEMORY_SIZE() { return getToken(WatParser.MEMORY_SIZE, 0); }
		public TerminalNode MEMORY_GROW() { return getToken(WatParser.MEMORY_GROW, 0); }
		public TerminalNode MEMORY_COPY() { return getToken(WatParser.MEMORY_COPY, 0); }
		public TerminalNode MEMORY_FILL() { return getToken(WatParser.MEMORY_FILL, 0); }
		public TerminalNode CONST() { return getToken(WatParser.CONST, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode TEST() { return getToken(WatParser.TEST, 0); }
		public TerminalNode COMPARE() { return getToken(WatParser.COMPARE, 0); }
		public TerminalNode UNARY() { return getToken(WatParser.UNARY, 0); }
		public TerminalNode BINARY() { return getToken(WatParser.BINARY, 0); }
		public TerminalNode CONVERT() { return getToken(WatParser.CONVERT, 0); }
		public Plain_instrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plain_instr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterPlain_instr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitPlain_instr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitPlain_instr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Plain_instrContext plain_instr() throws RecognitionException {
		Plain_instrContext _localctx = new Plain_instrContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_plain_instr);
		int _la;
		try {
			setState(286);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UNREACHABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				match(UNREACHABLE);
				}
				break;
			case NOP:
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				match(NOP);
				}
				break;
			case DROP:
				enterOuterAlt(_localctx, 3);
				{
				setState(204);
				match(DROP);
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 4);
				{
				setState(205);
				match(SELECT);
				setState(207);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(206);
					select_type();
					}
					break;
				}
				}
				break;
			case BR:
				enterOuterAlt(_localctx, 5);
				{
				setState(209);
				match(BR);
				setState(210);
				var_();
				}
				break;
			case BR_IF:
				enterOuterAlt(_localctx, 6);
				{
				setState(211);
				match(BR_IF);
				setState(212);
				var_();
				}
				break;
			case BR_TABLE:
				enterOuterAlt(_localctx, 7);
				{
				setState(213);
				match(BR_TABLE);
				setState(215); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(214);
					var_();
					}
					}
					setState(217); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NAT || _la==VAR );
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 8);
				{
				setState(219);
				match(RETURN);
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 9);
				{
				setState(220);
				match(CALL);
				setState(221);
				var_();
				}
				break;
			case CALL_INDIRECT:
				enterOuterAlt(_localctx, 10);
				{
				setState(222);
				match(CALL_INDIRECT);
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(223);
					var_();
					}
				}

				setState(227);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(226);
					type_use();
					}
					break;
				}
				setState(229);
				call_instr_params();
				}
				break;
			case LOCAL_GET:
				enterOuterAlt(_localctx, 11);
				{
				setState(230);
				match(LOCAL_GET);
				setState(231);
				var_();
				}
				break;
			case LOCAL_SET:
				enterOuterAlt(_localctx, 12);
				{
				setState(232);
				match(LOCAL_SET);
				setState(233);
				var_();
				}
				break;
			case LOCAL_TEE:
				enterOuterAlt(_localctx, 13);
				{
				setState(234);
				match(LOCAL_TEE);
				setState(235);
				var_();
				}
				break;
			case GLOBAL_GET:
				enterOuterAlt(_localctx, 14);
				{
				setState(236);
				match(GLOBAL_GET);
				setState(237);
				var_();
				}
				break;
			case GLOBAL_SET:
				enterOuterAlt(_localctx, 15);
				{
				setState(238);
				match(GLOBAL_SET);
				setState(239);
				var_();
				}
				break;
			case LOAD:
				enterOuterAlt(_localctx, 16);
				{
				setState(240);
				match(LOAD);
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(241);
					var_();
					}
				}

				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(244);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(247);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case STORE:
				enterOuterAlt(_localctx, 17);
				{
				setState(250);
				match(STORE);
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(251);
					var_();
					}
				}

				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(254);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(257);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case MEMORY_SIZE:
				enterOuterAlt(_localctx, 18);
				{
				setState(260);
				match(MEMORY_SIZE);
				setState(262);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(261);
					var_();
					}
				}

				}
				break;
			case MEMORY_GROW:
				enterOuterAlt(_localctx, 19);
				{
				setState(264);
				match(MEMORY_GROW);
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(265);
					var_();
					}
				}

				}
				break;
			case MEMORY_COPY:
				enterOuterAlt(_localctx, 20);
				{
				setState(268);
				match(MEMORY_COPY);
				setState(270);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(269);
					var_();
					}
					break;
				}
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(272);
					var_();
					}
				}

				}
				break;
			case MEMORY_FILL:
				enterOuterAlt(_localctx, 21);
				{
				setState(275);
				match(MEMORY_FILL);
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(276);
					var_();
					}
				}

				}
				break;
			case CONST:
				enterOuterAlt(_localctx, 22);
				{
				setState(279);
				match(CONST);
				setState(280);
				literal();
				}
				break;
			case TEST:
				enterOuterAlt(_localctx, 23);
				{
				setState(281);
				match(TEST);
				}
				break;
			case COMPARE:
				enterOuterAlt(_localctx, 24);
				{
				setState(282);
				match(COMPARE);
				}
				break;
			case UNARY:
				enterOuterAlt(_localctx, 25);
				{
				setState(283);
				match(UNARY);
				}
				break;
			case BINARY:
				enterOuterAlt(_localctx, 26);
				{
				setState(284);
				match(BINARY);
				}
				break;
			case CONVERT:
				enterOuterAlt(_localctx, 27);
				{
				setState(285);
				match(CONVERT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Select_typeContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode RETURN() { return getToken(WatParser.RETURN, 0); }
		public Value_typeContext value_type() {
			return getRuleContext(Value_typeContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Select_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterSelect_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitSelect_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitSelect_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_typeContext select_type() throws RecognitionException {
		Select_typeContext _localctx = new Select_typeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_select_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(LPAR);
			setState(289);
			match(RETURN);
			setState(290);
			value_type();
			setState(291);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Call_instr_paramsContext extends ParserRuleContext {
		public List<TerminalNode> LPAR() { return getTokens(WatParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(WatParser.LPAR, i);
		}
		public List<TerminalNode> PARAM() { return getTokens(WatParser.PARAM); }
		public TerminalNode PARAM(int i) {
			return getToken(WatParser.PARAM, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(WatParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(WatParser.RPAR, i);
		}
		public List<TerminalNode> RESULT() { return getTokens(WatParser.RESULT); }
		public TerminalNode RESULT(int i) {
			return getToken(WatParser.RESULT, i);
		}
		public List<Value_typeContext> value_type() {
			return getRuleContexts(Value_typeContext.class);
		}
		public Value_typeContext value_type(int i) {
			return getRuleContext(Value_typeContext.class,i);
		}
		public Call_instr_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_instr_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterCall_instr_params(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitCall_instr_params(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitCall_instr_params(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Call_instr_paramsContext call_instr_params() throws RecognitionException {
		Call_instr_paramsContext _localctx = new Call_instr_paramsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_call_instr_params);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(293);
					match(LPAR);
					setState(294);
					match(PARAM);
					setState(298);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(295);
						value_type();
						}
						}
						setState(300);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(301);
					match(RPAR);
					}
					} 
				}
				setState(306);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(318);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(307);
					match(LPAR);
					setState(308);
					match(RESULT);
					setState(312);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(309);
						value_type();
						}
						}
						setState(314);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(315);
					match(RPAR);
					}
					} 
				}
				setState(320);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Block_instrContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(WatParser.END, 0); }
		public TerminalNode BLOCK() { return getToken(WatParser.BLOCK, 0); }
		public TerminalNode LOOP() { return getToken(WatParser.LOOP, 0); }
		public List<Bind_varContext> bind_var() {
			return getRuleContexts(Bind_varContext.class);
		}
		public Bind_varContext bind_var(int i) {
			return getRuleContext(Bind_varContext.class,i);
		}
		public TerminalNode IF() { return getToken(WatParser.IF, 0); }
		public TerminalNode ELSE() { return getToken(WatParser.ELSE, 0); }
		public Instr_listContext instr_list() {
			return getRuleContext(Instr_listContext.class,0);
		}
		public Block_instrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_instr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterBlock_instr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitBlock_instr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitBlock_instr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Block_instrContext block_instr() throws RecognitionException {
		Block_instrContext _localctx = new Block_instrContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_block_instr);
		int _la;
		try {
			setState(346);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BLOCK:
			case LOOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				_la = _input.LA(1);
				if ( !(_la==BLOCK || _la==LOOP) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(322);
					bind_var();
					}
				}

				setState(325);
				block();
				setState(326);
				match(END);
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(327);
					bind_var();
					}
				}

				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
				match(IF);
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(331);
					bind_var();
					}
				}

				setState(334);
				block();
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(335);
					match(ELSE);
					setState(337);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VAR) {
						{
						setState(336);
						bind_var();
						}
					}

					setState(339);
					instr_list();
					}
				}

				setState(342);
				match(END);
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(343);
					bind_var();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Block_typeContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode RESULT() { return getToken(WatParser.RESULT, 0); }
		public Value_typeContext value_type() {
			return getRuleContext(Value_typeContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Block_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterBlock_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitBlock_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitBlock_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Block_typeContext block_type() throws RecognitionException {
		Block_typeContext _localctx = new Block_typeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_block_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			match(LPAR);
			setState(349);
			match(RESULT);
			setState(350);
			value_type();
			setState(351);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public Instr_listContext instr_list() {
			return getRuleContext(Instr_listContext.class,0);
		}
		public Block_typeContext block_type() {
			return getRuleContext(Block_typeContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(353);
				block_type();
				}
				break;
			}
			setState(356);
			instr_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public Expr1Context expr1() {
			return getRuleContext(Expr1Context.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			match(LPAR);
			setState(359);
			expr1();
			setState(360);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr1Context extends ParserRuleContext {
		public Plain_instrContext plain_instr() {
			return getRuleContext(Plain_instrContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode BLOCK() { return getToken(WatParser.BLOCK, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Bind_varContext bind_var() {
			return getRuleContext(Bind_varContext.class,0);
		}
		public TerminalNode LOOP() { return getToken(WatParser.LOOP, 0); }
		public TerminalNode IF() { return getToken(WatParser.IF, 0); }
		public If_blockContext if_block() {
			return getRuleContext(If_blockContext.class,0);
		}
		public Expr1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterExpr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitExpr1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitExpr1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr1Context expr1() throws RecognitionException {
		Expr1Context _localctx = new Expr1Context(_ctx, getState());
		enterRule(_localctx, 42, RULE_expr1);
		int _la;
		try {
			setState(384);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONST:
			case NOP:
			case UNREACHABLE:
			case DROP:
			case BR:
			case BR_IF:
			case BR_TABLE:
			case RETURN:
			case SELECT:
			case CALL:
			case CALL_INDIRECT:
			case LOCAL_GET:
			case LOCAL_SET:
			case LOCAL_TEE:
			case GLOBAL_GET:
			case GLOBAL_SET:
			case LOAD:
			case STORE:
			case UNARY:
			case BINARY:
			case TEST:
			case COMPARE:
			case CONVERT:
			case MEMORY_SIZE:
			case MEMORY_GROW:
			case MEMORY_COPY:
			case MEMORY_FILL:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				plain_instr();
				setState(366);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAR) {
					{
					{
					setState(363);
					expr();
					}
					}
					setState(368);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case BLOCK:
				enterOuterAlt(_localctx, 2);
				{
				setState(369);
				match(BLOCK);
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(370);
					bind_var();
					}
				}

				setState(373);
				block();
				}
				break;
			case LOOP:
				enterOuterAlt(_localctx, 3);
				{
				setState(374);
				match(LOOP);
				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(375);
					bind_var();
					}
				}

				setState(378);
				block();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(379);
				match(IF);
				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(380);
					bind_var();
					}
				}

				setState(383);
				if_block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Call_expr_typeContext extends ParserRuleContext {
		public Call_expr_paramsContext call_expr_params() {
			return getRuleContext(Call_expr_paramsContext.class,0);
		}
		public Type_useContext type_use() {
			return getRuleContext(Type_useContext.class,0);
		}
		public Call_expr_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_expr_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterCall_expr_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitCall_expr_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitCall_expr_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Call_expr_typeContext call_expr_type() throws RecognitionException {
		Call_expr_typeContext _localctx = new Call_expr_typeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_call_expr_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(386);
				type_use();
				}
				break;
			}
			setState(389);
			call_expr_params();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Call_expr_paramsContext extends ParserRuleContext {
		public Call_expr_resultsContext call_expr_results() {
			return getRuleContext(Call_expr_resultsContext.class,0);
		}
		public List<TerminalNode> LPAR() { return getTokens(WatParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(WatParser.LPAR, i);
		}
		public List<TerminalNode> PARAM() { return getTokens(WatParser.PARAM); }
		public TerminalNode PARAM(int i) {
			return getToken(WatParser.PARAM, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(WatParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(WatParser.RPAR, i);
		}
		public List<Value_typeContext> value_type() {
			return getRuleContexts(Value_typeContext.class);
		}
		public Value_typeContext value_type(int i) {
			return getRuleContext(Value_typeContext.class,i);
		}
		public Call_expr_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_expr_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterCall_expr_params(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitCall_expr_params(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitCall_expr_params(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Call_expr_paramsContext call_expr_params() throws RecognitionException {
		Call_expr_paramsContext _localctx = new Call_expr_paramsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_call_expr_params);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(391);
					match(LPAR);
					setState(392);
					match(PARAM);
					setState(396);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(393);
						value_type();
						}
						}
						setState(398);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(399);
					match(RPAR);
					}
					} 
				}
				setState(404);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			setState(405);
			call_expr_results();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Call_expr_resultsContext extends ParserRuleContext {
		public List<TerminalNode> LPAR() { return getTokens(WatParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(WatParser.LPAR, i);
		}
		public List<TerminalNode> RESULT() { return getTokens(WatParser.RESULT); }
		public TerminalNode RESULT(int i) {
			return getToken(WatParser.RESULT, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(WatParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(WatParser.RPAR, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<Value_typeContext> value_type() {
			return getRuleContexts(Value_typeContext.class);
		}
		public Value_typeContext value_type(int i) {
			return getRuleContext(Value_typeContext.class,i);
		}
		public Call_expr_resultsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_expr_results; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterCall_expr_results(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitCall_expr_results(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitCall_expr_results(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Call_expr_resultsContext call_expr_results() throws RecognitionException {
		Call_expr_resultsContext _localctx = new Call_expr_resultsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_call_expr_results);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(407);
					match(LPAR);
					setState(408);
					match(RESULT);
					setState(412);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(409);
						value_type();
						}
						}
						setState(414);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(415);
					match(RPAR);
					}
					} 
				}
				setState(420);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			}
			setState(424);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(421);
				expr();
				}
				}
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class If_blockContext extends ParserRuleContext {
		public Block_typeContext block_type() {
			return getRuleContext(Block_typeContext.class,0);
		}
		public If_blockContext if_block() {
			return getRuleContext(If_blockContext.class,0);
		}
		public List<TerminalNode> LPAR() { return getTokens(WatParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(WatParser.LPAR, i);
		}
		public TerminalNode THEN() { return getToken(WatParser.THEN, 0); }
		public List<Instr_listContext> instr_list() {
			return getRuleContexts(Instr_listContext.class);
		}
		public Instr_listContext instr_list(int i) {
			return getRuleContext(Instr_listContext.class,i);
		}
		public List<TerminalNode> RPAR() { return getTokens(WatParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(WatParser.RPAR, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(WatParser.ELSE, 0); }
		public If_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterIf_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitIf_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitIf_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_blockContext if_block() throws RecognitionException {
		If_blockContext _localctx = new If_blockContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_if_block);
		int _la;
		try {
			int _alt;
			setState(447);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(427);
				block_type();
				setState(428);
				if_block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(433);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(430);
						expr();
						}
						} 
					}
					setState(435);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				}
				setState(436);
				match(LPAR);
				setState(437);
				match(THEN);
				setState(438);
				instr_list();
				setState(439);
				match(RPAR);
				setState(445);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(440);
					match(LPAR);
					setState(441);
					match(ELSE);
					setState(442);
					instr_list();
					setState(443);
					match(RPAR);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Instr_listContext extends ParserRuleContext {
		public List<InstrContext> instr() {
			return getRuleContexts(InstrContext.class);
		}
		public InstrContext instr(int i) {
			return getRuleContext(InstrContext.class,i);
		}
		public Instr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instr_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterInstr_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitInstr_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitInstr_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instr_listContext instr_list() throws RecognitionException {
		Instr_listContext _localctx = new Instr_listContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_instr_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35132819831042L) != 0)) {
				{
				{
				setState(449);
				instr();
				}
				}
				setState(454);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Const_exprContext extends ParserRuleContext {
		public Instr_listContext instr_list() {
			return getRuleContext(Instr_listContext.class,0);
		}
		public Const_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterConst_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitConst_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitConst_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Const_exprContext const_expr() throws RecognitionException {
		Const_exprContext _localctx = new Const_exprContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_const_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			instr_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_Context extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode FUNC() { return getToken(WatParser.FUNC, 0); }
		public Func_fieldsContext func_fields() {
			return getRuleContext(Func_fieldsContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Bind_varContext bind_var() {
			return getRuleContext(Bind_varContext.class,0);
		}
		public Func_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterFunc_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitFunc_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitFunc_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_Context func_() throws RecognitionException {
		Func_Context _localctx = new Func_Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_func_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
			match(LPAR);
			setState(458);
			match(FUNC);
			setState(460);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(459);
				bind_var();
				}
			}

			setState(462);
			func_fields();
			setState(463);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_fieldsContext extends ParserRuleContext {
		public Func_fields_bodyContext func_fields_body() {
			return getRuleContext(Func_fields_bodyContext.class,0);
		}
		public Type_useContext type_use() {
			return getRuleContext(Type_useContext.class,0);
		}
		public Inline_importContext inline_import() {
			return getRuleContext(Inline_importContext.class,0);
		}
		public Func_fields_importContext func_fields_import() {
			return getRuleContext(Func_fields_importContext.class,0);
		}
		public Inline_exportContext inline_export() {
			return getRuleContext(Inline_exportContext.class,0);
		}
		public Func_fieldsContext func_fields() {
			return getRuleContext(Func_fieldsContext.class,0);
		}
		public Func_fieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterFunc_fields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitFunc_fields(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitFunc_fields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_fieldsContext func_fields() throws RecognitionException {
		Func_fieldsContext _localctx = new Func_fieldsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_func_fields);
		try {
			setState(478);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(466);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					{
					setState(465);
					type_use();
					}
					break;
				}
				setState(468);
				func_fields_body();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(469);
				inline_import();
				setState(471);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					{
					setState(470);
					type_use();
					}
					break;
				}
				setState(473);
				func_fields_import();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(475);
				inline_export();
				setState(476);
				func_fields();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_fields_importContext extends ParserRuleContext {
		public Func_fields_import_resultContext func_fields_import_result() {
			return getRuleContext(Func_fields_import_resultContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode PARAM() { return getToken(WatParser.PARAM, 0); }
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Bind_varContext bind_var() {
			return getRuleContext(Bind_varContext.class,0);
		}
		public List<Value_typeContext> value_type() {
			return getRuleContexts(Value_typeContext.class);
		}
		public Value_typeContext value_type(int i) {
			return getRuleContext(Value_typeContext.class,i);
		}
		public Func_fields_importContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_fields_import; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterFunc_fields_import(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitFunc_fields_import(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitFunc_fields_import(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_fields_importContext func_fields_import() throws RecognitionException {
		Func_fields_importContext _localctx = new Func_fields_importContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_func_fields_import);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(495);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(480);
				match(LPAR);
				setState(481);
				match(PARAM);
				setState(485);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VALUE_TYPE) {
					{
					{
					setState(482);
					value_type();
					}
					}
					setState(487);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(488);
				match(RPAR);
				}
				break;
			case 2:
				{
				setState(489);
				match(LPAR);
				setState(490);
				match(PARAM);
				setState(491);
				bind_var();
				setState(492);
				value_type();
				setState(493);
				match(RPAR);
				}
				break;
			}
			setState(497);
			func_fields_import_result();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_fields_import_resultContext extends ParserRuleContext {
		public List<TerminalNode> LPAR() { return getTokens(WatParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(WatParser.LPAR, i);
		}
		public List<TerminalNode> RESULT() { return getTokens(WatParser.RESULT); }
		public TerminalNode RESULT(int i) {
			return getToken(WatParser.RESULT, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(WatParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(WatParser.RPAR, i);
		}
		public List<Value_typeContext> value_type() {
			return getRuleContexts(Value_typeContext.class);
		}
		public Value_typeContext value_type(int i) {
			return getRuleContext(Value_typeContext.class,i);
		}
		public Func_fields_import_resultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_fields_import_result; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterFunc_fields_import_result(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitFunc_fields_import_result(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitFunc_fields_import_result(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_fields_import_resultContext func_fields_import_result() throws RecognitionException {
		Func_fields_import_resultContext _localctx = new Func_fields_import_resultContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_func_fields_import_result);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(499);
				match(LPAR);
				setState(500);
				match(RESULT);
				setState(504);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VALUE_TYPE) {
					{
					{
					setState(501);
					value_type();
					}
					}
					setState(506);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(507);
				match(RPAR);
				}
				}
				setState(512);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_fields_bodyContext extends ParserRuleContext {
		public Func_result_bodyContext func_result_body() {
			return getRuleContext(Func_result_bodyContext.class,0);
		}
		public List<TerminalNode> LPAR() { return getTokens(WatParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(WatParser.LPAR, i);
		}
		public List<TerminalNode> PARAM() { return getTokens(WatParser.PARAM); }
		public TerminalNode PARAM(int i) {
			return getToken(WatParser.PARAM, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(WatParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(WatParser.RPAR, i);
		}
		public List<Bind_varContext> bind_var() {
			return getRuleContexts(Bind_varContext.class);
		}
		public Bind_varContext bind_var(int i) {
			return getRuleContext(Bind_varContext.class,i);
		}
		public List<Value_typeContext> value_type() {
			return getRuleContexts(Value_typeContext.class);
		}
		public Value_typeContext value_type(int i) {
			return getRuleContext(Value_typeContext.class,i);
		}
		public Func_fields_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_fields_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterFunc_fields_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitFunc_fields_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitFunc_fields_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_fields_bodyContext func_fields_body() throws RecognitionException {
		Func_fields_bodyContext _localctx = new Func_fields_bodyContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_func_fields_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(528);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
					case 1:
						{
						setState(513);
						match(LPAR);
						setState(514);
						match(PARAM);
						setState(518);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VALUE_TYPE) {
							{
							{
							setState(515);
							value_type();
							}
							}
							setState(520);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(521);
						match(RPAR);
						}
						break;
					case 2:
						{
						setState(522);
						match(LPAR);
						setState(523);
						match(PARAM);
						setState(524);
						bind_var();
						setState(525);
						value_type();
						setState(526);
						match(RPAR);
						}
						break;
					}
					} 
				}
				setState(532);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
			}
			setState(533);
			func_result_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_result_bodyContext extends ParserRuleContext {
		public Func_bodyContext func_body() {
			return getRuleContext(Func_bodyContext.class,0);
		}
		public List<TerminalNode> LPAR() { return getTokens(WatParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(WatParser.LPAR, i);
		}
		public List<TerminalNode> RESULT() { return getTokens(WatParser.RESULT); }
		public TerminalNode RESULT(int i) {
			return getToken(WatParser.RESULT, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(WatParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(WatParser.RPAR, i);
		}
		public List<Value_typeContext> value_type() {
			return getRuleContexts(Value_typeContext.class);
		}
		public Value_typeContext value_type(int i) {
			return getRuleContext(Value_typeContext.class,i);
		}
		public Func_result_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_result_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterFunc_result_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitFunc_result_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitFunc_result_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_result_bodyContext func_result_body() throws RecognitionException {
		Func_result_bodyContext _localctx = new Func_result_bodyContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_func_result_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(535);
					match(LPAR);
					setState(536);
					match(RESULT);
					setState(540);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(537);
						value_type();
						}
						}
						setState(542);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(543);
					match(RPAR);
					}
					} 
				}
				setState(548);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			}
			setState(549);
			func_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_bodyContext extends ParserRuleContext {
		public Instr_listContext instr_list() {
			return getRuleContext(Instr_listContext.class,0);
		}
		public List<TerminalNode> LPAR() { return getTokens(WatParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(WatParser.LPAR, i);
		}
		public List<TerminalNode> LOCAL() { return getTokens(WatParser.LOCAL); }
		public TerminalNode LOCAL(int i) {
			return getToken(WatParser.LOCAL, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(WatParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(WatParser.RPAR, i);
		}
		public List<Bind_varContext> bind_var() {
			return getRuleContexts(Bind_varContext.class);
		}
		public Bind_varContext bind_var(int i) {
			return getRuleContext(Bind_varContext.class,i);
		}
		public List<Value_typeContext> value_type() {
			return getRuleContexts(Value_typeContext.class);
		}
		public Value_typeContext value_type(int i) {
			return getRuleContext(Value_typeContext.class,i);
		}
		public Func_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterFunc_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitFunc_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitFunc_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_bodyContext func_body() throws RecognitionException {
		Func_bodyContext _localctx = new Func_bodyContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_func_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(568);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(566);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
					case 1:
						{
						setState(551);
						match(LPAR);
						setState(552);
						match(LOCAL);
						setState(556);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VALUE_TYPE) {
							{
							{
							setState(553);
							value_type();
							}
							}
							setState(558);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(559);
						match(RPAR);
						}
						break;
					case 2:
						{
						setState(560);
						match(LPAR);
						setState(561);
						match(LOCAL);
						setState(562);
						bind_var();
						setState(563);
						value_type();
						setState(564);
						match(RPAR);
						}
						break;
					}
					} 
				}
				setState(570);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			}
			setState(571);
			instr_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OffsetContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode OFFSET() { return getToken(WatParser.OFFSET, 0); }
		public Const_exprContext const_expr() {
			return getRuleContext(Const_exprContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public OffsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_offset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterOffset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitOffset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitOffset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OffsetContext offset() throws RecognitionException {
		OffsetContext _localctx = new OffsetContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_offset);
		try {
			setState(579);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(573);
				match(LPAR);
				setState(574);
				match(OFFSET);
				setState(575);
				const_expr();
				setState(576);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(578);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElemContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode ELEM() { return getToken(WatParser.ELEM, 0); }
		public OffsetContext offset() {
			return getRuleContext(OffsetContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public List<Var_Context> var_() {
			return getRuleContexts(Var_Context.class);
		}
		public Var_Context var_(int i) {
			return getRuleContext(Var_Context.class,i);
		}
		public TerminalNode FUNC() { return getToken(WatParser.FUNC, 0); }
		public ElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterElem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitElem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElemContext elem() throws RecognitionException {
		ElemContext _localctx = new ElemContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_elem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			match(LPAR);
			setState(582);
			match(ELEM);
			setState(584);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAT || _la==VAR) {
				{
				setState(583);
				var_();
				}
			}

			setState(586);
			offset();
			setState(588);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FUNC) {
				{
				setState(587);
				match(FUNC);
				}
			}

			setState(593);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NAT || _la==VAR) {
				{
				{
				setState(590);
				var_();
				}
				}
				setState(595);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(596);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode TABLE() { return getToken(WatParser.TABLE, 0); }
		public Table_fieldsContext table_fields() {
			return getRuleContext(Table_fieldsContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Bind_varContext bind_var() {
			return getRuleContext(Bind_varContext.class,0);
		}
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(598);
			match(LPAR);
			setState(599);
			match(TABLE);
			setState(601);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(600);
				bind_var();
				}
			}

			setState(603);
			table_fields();
			setState(604);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Table_fieldsContext extends ParserRuleContext {
		public Table_typeContext table_type() {
			return getRuleContext(Table_typeContext.class,0);
		}
		public Inline_importContext inline_import() {
			return getRuleContext(Inline_importContext.class,0);
		}
		public Inline_exportContext inline_export() {
			return getRuleContext(Inline_exportContext.class,0);
		}
		public Table_fieldsContext table_fields() {
			return getRuleContext(Table_fieldsContext.class,0);
		}
		public Elem_typeContext elem_type() {
			return getRuleContext(Elem_typeContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode ELEM() { return getToken(WatParser.ELEM, 0); }
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public List<Var_Context> var_() {
			return getRuleContexts(Var_Context.class);
		}
		public Var_Context var_(int i) {
			return getRuleContext(Var_Context.class,i);
		}
		public Table_fieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterTable_fields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitTable_fields(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitTable_fields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_fieldsContext table_fields() throws RecognitionException {
		Table_fieldsContext _localctx = new Table_fieldsContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_table_fields);
		int _la;
		try {
			setState(624);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(606);
				table_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(607);
				inline_import();
				setState(608);
				table_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(610);
				inline_export();
				setState(611);
				table_fields();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(613);
				elem_type();
				setState(614);
				match(LPAR);
				setState(615);
				match(ELEM);
				setState(619);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NAT || _la==VAR) {
					{
					{
					setState(616);
					var_();
					}
					}
					setState(621);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(622);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode DATA() { return getToken(WatParser.DATA, 0); }
		public OffsetContext offset() {
			return getRuleContext(OffsetContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Var_Context var_() {
			return getRuleContext(Var_Context.class,0);
		}
		public List<TerminalNode> STRING_() { return getTokens(WatParser.STRING_); }
		public TerminalNode STRING_(int i) {
			return getToken(WatParser.STRING_, i);
		}
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitData(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(626);
			match(LPAR);
			setState(627);
			match(DATA);
			setState(629);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAT || _la==VAR) {
				{
				setState(628);
				var_();
				}
			}

			setState(631);
			offset();
			setState(635);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING_) {
				{
				{
				setState(632);
				match(STRING_);
				}
				}
				setState(637);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(638);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MemoryContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode MEMORY() { return getToken(WatParser.MEMORY, 0); }
		public Memory_fieldsContext memory_fields() {
			return getRuleContext(Memory_fieldsContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Bind_varContext bind_var() {
			return getRuleContext(Bind_varContext.class,0);
		}
		public MemoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterMemory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitMemory(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitMemory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemoryContext memory() throws RecognitionException {
		MemoryContext _localctx = new MemoryContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_memory);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(640);
			match(LPAR);
			setState(641);
			match(MEMORY);
			setState(643);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(642);
				bind_var();
				}
			}

			setState(645);
			memory_fields();
			setState(646);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Memory_fieldsContext extends ParserRuleContext {
		public Memory_typeContext memory_type() {
			return getRuleContext(Memory_typeContext.class,0);
		}
		public Inline_importContext inline_import() {
			return getRuleContext(Inline_importContext.class,0);
		}
		public Inline_exportContext inline_export() {
			return getRuleContext(Inline_exportContext.class,0);
		}
		public Memory_fieldsContext memory_fields() {
			return getRuleContext(Memory_fieldsContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode DATA() { return getToken(WatParser.DATA, 0); }
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public List<TerminalNode> STRING_() { return getTokens(WatParser.STRING_); }
		public TerminalNode STRING_(int i) {
			return getToken(WatParser.STRING_, i);
		}
		public Memory_fieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memory_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterMemory_fields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitMemory_fields(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitMemory_fields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Memory_fieldsContext memory_fields() throws RecognitionException {
		Memory_fieldsContext _localctx = new Memory_fieldsContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_memory_fields);
		int _la;
		try {
			setState(664);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(648);
				memory_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(649);
				inline_import();
				setState(650);
				memory_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(652);
				inline_export();
				setState(653);
				memory_fields();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(655);
				match(LPAR);
				setState(656);
				match(DATA);
				setState(660);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STRING_) {
					{
					{
					setState(657);
					match(STRING_);
					}
					}
					setState(662);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(663);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SglobalContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode GLOBAL() { return getToken(WatParser.GLOBAL, 0); }
		public Global_fieldsContext global_fields() {
			return getRuleContext(Global_fieldsContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Bind_varContext bind_var() {
			return getRuleContext(Bind_varContext.class,0);
		}
		public SglobalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sglobal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterSglobal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitSglobal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitSglobal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SglobalContext sglobal() throws RecognitionException {
		SglobalContext _localctx = new SglobalContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_sglobal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(666);
			match(LPAR);
			setState(667);
			match(GLOBAL);
			setState(669);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(668);
				bind_var();
				}
			}

			setState(671);
			global_fields();
			setState(672);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Global_fieldsContext extends ParserRuleContext {
		public Global_typeContext global_type() {
			return getRuleContext(Global_typeContext.class,0);
		}
		public Const_exprContext const_expr() {
			return getRuleContext(Const_exprContext.class,0);
		}
		public Inline_importContext inline_import() {
			return getRuleContext(Inline_importContext.class,0);
		}
		public Inline_exportContext inline_export() {
			return getRuleContext(Inline_exportContext.class,0);
		}
		public Global_fieldsContext global_fields() {
			return getRuleContext(Global_fieldsContext.class,0);
		}
		public Global_fieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterGlobal_fields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitGlobal_fields(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitGlobal_fields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Global_fieldsContext global_fields() throws RecognitionException {
		Global_fieldsContext _localctx = new Global_fieldsContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_global_fields);
		try {
			setState(683);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(674);
				global_type();
				setState(675);
				const_expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(677);
				inline_import();
				setState(678);
				global_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(680);
				inline_export();
				setState(681);
				global_fields();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_descContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode FUNC() { return getToken(WatParser.FUNC, 0); }
		public Type_useContext type_use() {
			return getRuleContext(Type_useContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Bind_varContext bind_var() {
			return getRuleContext(Bind_varContext.class,0);
		}
		public Func_typeContext func_type() {
			return getRuleContext(Func_typeContext.class,0);
		}
		public TerminalNode TABLE() { return getToken(WatParser.TABLE, 0); }
		public Table_typeContext table_type() {
			return getRuleContext(Table_typeContext.class,0);
		}
		public TerminalNode MEMORY() { return getToken(WatParser.MEMORY, 0); }
		public Memory_typeContext memory_type() {
			return getRuleContext(Memory_typeContext.class,0);
		}
		public TerminalNode GLOBAL() { return getToken(WatParser.GLOBAL, 0); }
		public Global_typeContext global_type() {
			return getRuleContext(Global_typeContext.class,0);
		}
		public Import_descContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_desc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterImport_desc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitImport_desc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitImport_desc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_descContext import_desc() throws RecognitionException {
		Import_descContext _localctx = new Import_descContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_import_desc);
		int _la;
		try {
			setState(725);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(685);
				match(LPAR);
				setState(686);
				match(FUNC);
				setState(688);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(687);
					bind_var();
					}
				}

				setState(690);
				type_use();
				setState(691);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(693);
				match(LPAR);
				setState(694);
				match(FUNC);
				setState(696);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(695);
					bind_var();
					}
				}

				setState(698);
				func_type();
				setState(699);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(701);
				match(LPAR);
				setState(702);
				match(TABLE);
				setState(704);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(703);
					bind_var();
					}
				}

				setState(706);
				table_type();
				setState(707);
				match(RPAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(709);
				match(LPAR);
				setState(710);
				match(MEMORY);
				setState(712);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(711);
					bind_var();
					}
				}

				setState(714);
				memory_type();
				setState(715);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(717);
				match(LPAR);
				setState(718);
				match(GLOBAL);
				setState(720);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(719);
					bind_var();
					}
				}

				setState(722);
				global_type();
				setState(723);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SimportContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode IMPORT() { return getToken(WatParser.IMPORT, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public Import_descContext import_desc() {
			return getRuleContext(Import_descContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public SimportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simport; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterSimport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitSimport(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitSimport(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimportContext simport() throws RecognitionException {
		SimportContext _localctx = new SimportContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_simport);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(727);
			match(LPAR);
			setState(728);
			match(IMPORT);
			setState(729);
			name();
			setState(730);
			name();
			setState(731);
			import_desc();
			setState(732);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Inline_importContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode IMPORT() { return getToken(WatParser.IMPORT, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Inline_importContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inline_import; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterInline_import(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitInline_import(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitInline_import(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Inline_importContext inline_import() throws RecognitionException {
		Inline_importContext _localctx = new Inline_importContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_inline_import);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(734);
			match(LPAR);
			setState(735);
			match(IMPORT);
			setState(736);
			name();
			setState(737);
			name();
			setState(738);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Export_descContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode FUNC() { return getToken(WatParser.FUNC, 0); }
		public Var_Context var_() {
			return getRuleContext(Var_Context.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public TerminalNode TABLE() { return getToken(WatParser.TABLE, 0); }
		public TerminalNode MEMORY() { return getToken(WatParser.MEMORY, 0); }
		public TerminalNode GLOBAL() { return getToken(WatParser.GLOBAL, 0); }
		public Export_descContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_export_desc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterExport_desc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitExport_desc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitExport_desc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Export_descContext export_desc() throws RecognitionException {
		Export_descContext _localctx = new Export_descContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_export_desc);
		try {
			setState(760);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(740);
				match(LPAR);
				setState(741);
				match(FUNC);
				setState(742);
				var_();
				setState(743);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(745);
				match(LPAR);
				setState(746);
				match(TABLE);
				setState(747);
				var_();
				setState(748);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(750);
				match(LPAR);
				setState(751);
				match(MEMORY);
				setState(752);
				var_();
				setState(753);
				match(RPAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(755);
				match(LPAR);
				setState(756);
				match(GLOBAL);
				setState(757);
				var_();
				setState(758);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Export_Context extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode EXPORT() { return getToken(WatParser.EXPORT, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Export_descContext export_desc() {
			return getRuleContext(Export_descContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Export_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_export_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterExport_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitExport_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitExport_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Export_Context export_() throws RecognitionException {
		Export_Context _localctx = new Export_Context(_ctx, getState());
		enterRule(_localctx, 96, RULE_export_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(762);
			match(LPAR);
			setState(763);
			match(EXPORT);
			setState(764);
			name();
			setState(765);
			export_desc();
			setState(766);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Inline_exportContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode EXPORT() { return getToken(WatParser.EXPORT, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Inline_exportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inline_export; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterInline_export(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitInline_export(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitInline_export(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Inline_exportContext inline_export() throws RecognitionException {
		Inline_exportContext _localctx = new Inline_exportContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_inline_export);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768);
			match(LPAR);
			setState(769);
			match(EXPORT);
			setState(770);
			name();
			setState(771);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type_Context extends ParserRuleContext {
		public Def_typeContext def_type() {
			return getRuleContext(Def_typeContext.class,0);
		}
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterType_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitType_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitType_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 100, RULE_type_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(773);
			def_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type_defContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode TYPE() { return getToken(WatParser.TYPE, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Bind_varContext bind_var() {
			return getRuleContext(Bind_varContext.class,0);
		}
		public Type_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterType_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitType_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitType_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_defContext type_def() throws RecognitionException {
		Type_defContext _localctx = new Type_defContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_type_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(775);
			match(LPAR);
			setState(776);
			match(TYPE);
			setState(778);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(777);
				bind_var();
				}
			}

			setState(780);
			type_();
			setState(781);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Start_Context extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode START_() { return getToken(WatParser.START_, 0); }
		public Var_Context var_() {
			return getRuleContext(Var_Context.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Start_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterStart_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitStart_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitStart_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Start_Context start_() throws RecognitionException {
		Start_Context _localctx = new Start_Context(_ctx, getState());
		enterRule(_localctx, 104, RULE_start_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(783);
			match(LPAR);
			setState(784);
			match(START_);
			setState(785);
			var_();
			setState(786);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Module_fieldContext extends ParserRuleContext {
		public Type_defContext type_def() {
			return getRuleContext(Type_defContext.class,0);
		}
		public SglobalContext sglobal() {
			return getRuleContext(SglobalContext.class,0);
		}
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public MemoryContext memory() {
			return getRuleContext(MemoryContext.class,0);
		}
		public Func_Context func_() {
			return getRuleContext(Func_Context.class,0);
		}
		public ElemContext elem() {
			return getRuleContext(ElemContext.class,0);
		}
		public DataContext data() {
			return getRuleContext(DataContext.class,0);
		}
		public Start_Context start_() {
			return getRuleContext(Start_Context.class,0);
		}
		public SimportContext simport() {
			return getRuleContext(SimportContext.class,0);
		}
		public Export_Context export_() {
			return getRuleContext(Export_Context.class,0);
		}
		public Module_fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterModule_field(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitModule_field(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitModule_field(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Module_fieldContext module_field() throws RecognitionException {
		Module_fieldContext _localctx = new Module_fieldContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_module_field);
		try {
			setState(798);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(788);
				type_def();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(789);
				sglobal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(790);
				table();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(791);
				memory();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(792);
				func_();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(793);
				elem();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(794);
				data();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(795);
				start_();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(796);
				simport();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(797);
				export_();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Module_Context extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode MODULE() { return getToken(WatParser.MODULE, 0); }
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public TerminalNode VAR() { return getToken(WatParser.VAR, 0); }
		public List<Module_fieldContext> module_field() {
			return getRuleContexts(Module_fieldContext.class);
		}
		public Module_fieldContext module_field(int i) {
			return getRuleContext(Module_fieldContext.class,i);
		}
		public Module_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterModule_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitModule_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitModule_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Module_Context module_() throws RecognitionException {
		Module_Context _localctx = new Module_Context(_ctx, getState());
		enterRule(_localctx, 108, RULE_module_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(800);
			match(LPAR);
			setState(801);
			match(MODULE);
			setState(803);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(802);
				match(VAR);
				}
			}

			setState(808);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(805);
				module_field();
				}
				}
				setState(810);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(811);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Script_moduleContext extends ParserRuleContext {
		public Module_Context module_() {
			return getRuleContext(Module_Context.class,0);
		}
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode MODULE() { return getToken(WatParser.MODULE, 0); }
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public TerminalNode BIN() { return getToken(WatParser.BIN, 0); }
		public TerminalNode QUOTE() { return getToken(WatParser.QUOTE, 0); }
		public TerminalNode VAR() { return getToken(WatParser.VAR, 0); }
		public List<TerminalNode> STRING_() { return getTokens(WatParser.STRING_); }
		public TerminalNode STRING_(int i) {
			return getToken(WatParser.STRING_, i);
		}
		public Script_moduleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterScript_module(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitScript_module(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitScript_module(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Script_moduleContext script_module() throws RecognitionException {
		Script_moduleContext _localctx = new Script_moduleContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_script_module);
		int _la;
		try {
			setState(827);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(813);
				module_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(814);
				match(LPAR);
				setState(815);
				match(MODULE);
				setState(817);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(816);
					match(VAR);
					}
				}

				setState(819);
				_la = _input.LA(1);
				if ( !(_la==BIN || _la==QUOTE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(823);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STRING_) {
					{
					{
					setState(820);
					match(STRING_);
					}
					}
					setState(825);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(826);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Action_Context extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode INVOKE() { return getToken(WatParser.INVOKE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Const_listContext const_list() {
			return getRuleContext(Const_listContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public TerminalNode VAR() { return getToken(WatParser.VAR, 0); }
		public TerminalNode GET() { return getToken(WatParser.GET, 0); }
		public Action_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterAction_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitAction_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitAction_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Action_Context action_() throws RecognitionException {
		Action_Context _localctx = new Action_Context(_ctx, getState());
		enterRule(_localctx, 112, RULE_action_);
		int _la;
		try {
			setState(846);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(829);
				match(LPAR);
				setState(830);
				match(INVOKE);
				setState(832);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(831);
					match(VAR);
					}
				}

				setState(834);
				name();
				setState(835);
				const_list();
				setState(836);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(838);
				match(LPAR);
				setState(839);
				match(GET);
				setState(841);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(840);
					match(VAR);
					}
				}

				setState(843);
				name();
				setState(844);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssertionContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode ASSERT_MALFORMED() { return getToken(WatParser.ASSERT_MALFORMED, 0); }
		public Script_moduleContext script_module() {
			return getRuleContext(Script_moduleContext.class,0);
		}
		public TerminalNode STRING_() { return getToken(WatParser.STRING_, 0); }
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public TerminalNode ASSERT_INVALID() { return getToken(WatParser.ASSERT_INVALID, 0); }
		public TerminalNode ASSERT_UNLINKABLE() { return getToken(WatParser.ASSERT_UNLINKABLE, 0); }
		public TerminalNode ASSERT_TRAP() { return getToken(WatParser.ASSERT_TRAP, 0); }
		public TerminalNode ASSERT_RETURN() { return getToken(WatParser.ASSERT_RETURN, 0); }
		public Action_Context action_() {
			return getRuleContext(Action_Context.class,0);
		}
		public Const_listContext const_list() {
			return getRuleContext(Const_listContext.class,0);
		}
		public TerminalNode ASSERT_RETURN_CANONICAL_NAN() { return getToken(WatParser.ASSERT_RETURN_CANONICAL_NAN, 0); }
		public TerminalNode ASSERT_RETURN_ARITHMETIC_NAN() { return getToken(WatParser.ASSERT_RETURN_ARITHMETIC_NAN, 0); }
		public TerminalNode ASSERT_EXHAUSTION() { return getToken(WatParser.ASSERT_EXHAUSTION, 0); }
		public AssertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assertion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterAssertion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitAssertion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitAssertion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssertionContext assertion() throws RecognitionException {
		AssertionContext _localctx = new AssertionContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_assertion);
		try {
			setState(900);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(848);
				match(LPAR);
				setState(849);
				match(ASSERT_MALFORMED);
				setState(850);
				script_module();
				setState(851);
				match(STRING_);
				setState(852);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(854);
				match(LPAR);
				setState(855);
				match(ASSERT_INVALID);
				setState(856);
				script_module();
				setState(857);
				match(STRING_);
				setState(858);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(860);
				match(LPAR);
				setState(861);
				match(ASSERT_UNLINKABLE);
				setState(862);
				script_module();
				setState(863);
				match(STRING_);
				setState(864);
				match(RPAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(866);
				match(LPAR);
				setState(867);
				match(ASSERT_TRAP);
				setState(868);
				script_module();
				setState(869);
				match(STRING_);
				setState(870);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(872);
				match(LPAR);
				setState(873);
				match(ASSERT_RETURN);
				setState(874);
				action_();
				setState(875);
				const_list();
				setState(876);
				match(RPAR);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(878);
				match(LPAR);
				setState(879);
				match(ASSERT_RETURN_CANONICAL_NAN);
				setState(880);
				action_();
				setState(881);
				match(RPAR);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(883);
				match(LPAR);
				setState(884);
				match(ASSERT_RETURN_ARITHMETIC_NAN);
				setState(885);
				action_();
				setState(886);
				match(RPAR);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(888);
				match(LPAR);
				setState(889);
				match(ASSERT_TRAP);
				setState(890);
				action_();
				setState(891);
				match(STRING_);
				setState(892);
				match(RPAR);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(894);
				match(LPAR);
				setState(895);
				match(ASSERT_EXHAUSTION);
				setState(896);
				action_();
				setState(897);
				match(STRING_);
				setState(898);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public Action_Context action_() {
			return getRuleContext(Action_Context.class,0);
		}
		public AssertionContext assertion() {
			return getRuleContext(AssertionContext.class,0);
		}
		public Script_moduleContext script_module() {
			return getRuleContext(Script_moduleContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode REGISTER() { return getToken(WatParser.REGISTER, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public TerminalNode VAR() { return getToken(WatParser.VAR, 0); }
		public MetaContext meta() {
			return getRuleContext(MetaContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitCmd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_cmd);
		int _la;
		try {
			setState(914);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(902);
				action_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(903);
				assertion();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(904);
				script_module();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(905);
				match(LPAR);
				setState(906);
				match(REGISTER);
				setState(907);
				name();
				setState(909);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(908);
					match(VAR);
					}
				}

				setState(911);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(913);
				meta();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MetaContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode SCRIPT() { return getToken(WatParser.SCRIPT, 0); }
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public TerminalNode VAR() { return getToken(WatParser.VAR, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode INPUT() { return getToken(WatParser.INPUT, 0); }
		public TerminalNode STRING_() { return getToken(WatParser.STRING_, 0); }
		public TerminalNode OUTPUT() { return getToken(WatParser.OUTPUT, 0); }
		public MetaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_meta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterMeta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitMeta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitMeta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MetaContext meta() throws RecognitionException {
		MetaContext _localctx = new MetaContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_meta);
		int _la;
		try {
			setState(948);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(916);
				match(LPAR);
				setState(917);
				match(SCRIPT);
				setState(919);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(918);
					match(VAR);
					}
				}

				setState(924);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAR) {
					{
					{
					setState(921);
					cmd();
					}
					}
					setState(926);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(927);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(928);
				match(LPAR);
				setState(929);
				match(INPUT);
				setState(931);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(930);
					match(VAR);
					}
				}

				setState(933);
				match(STRING_);
				setState(934);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(935);
				match(LPAR);
				setState(936);
				match(OUTPUT);
				setState(938);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(937);
					match(VAR);
					}
				}

				setState(940);
				match(STRING_);
				setState(941);
				match(RPAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(942);
				match(LPAR);
				setState(943);
				match(OUTPUT);
				setState(945);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(944);
					match(VAR);
					}
				}

				setState(947);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WconstContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode CONST() { return getToken(WatParser.CONST, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public WconstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wconst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterWconst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitWconst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitWconst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WconstContext wconst() throws RecognitionException {
		WconstContext _localctx = new WconstContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_wconst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(950);
			match(LPAR);
			setState(951);
			match(CONST);
			setState(952);
			literal();
			setState(953);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Const_listContext extends ParserRuleContext {
		public List<WconstContext> wconst() {
			return getRuleContexts(WconstContext.class);
		}
		public WconstContext wconst(int i) {
			return getRuleContext(WconstContext.class,i);
		}
		public Const_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterConst_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitConst_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitConst_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Const_listContext const_list() throws RecognitionException {
		Const_listContext _localctx = new Const_listContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_const_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(958);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(955);
				wconst();
				}
				}
				setState(960);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScriptContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(WatParser.EOF, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<Module_fieldContext> module_field() {
			return getRuleContexts(Module_fieldContext.class);
		}
		public Module_fieldContext module_field(int i) {
			return getRuleContext(Module_fieldContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_script);
		int _la;
		try {
			setState(975);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(964);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAR) {
					{
					{
					setState(961);
					cmd();
					}
					}
					setState(966);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(967);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(969); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(968);
					module_field();
					}
					}
					setState(971); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LPAR );
				setState(973);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModuleContext extends ParserRuleContext {
		public Module_Context module_() {
			return getRuleContext(Module_Context.class,0);
		}
		public TerminalNode EOF() { return getToken(WatParser.EOF, 0); }
		public List<Module_fieldContext> module_field() {
			return getRuleContexts(Module_fieldContext.class);
		}
		public Module_fieldContext module_field(int i) {
			return getRuleContext(Module_fieldContext.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitModule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_module);
		int _la;
		try {
			setState(987);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(977);
				module_();
				setState(978);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(983);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAR) {
					{
					{
					setState(980);
					module_field();
					}
					}
					setState(985);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(986);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001N\u03de\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004\u008f\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u0099\b\u0006"+
		"\n\u0006\f\u0006\u009c\t\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00a0"+
		"\b\u0006\n\u0006\f\u0006\u00a3\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u00a9\b\u0006\u0001\u0006\u0005\u0006\u00ac\b"+
		"\u0006\n\u0006\f\u0006\u00af\t\u0006\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u00b3\b\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0003\b\u00b9\b"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0003\r\u00c9\b\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e"+
		"\u00d0\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0004\u000e\u00d8\b\u000e\u000b\u000e\f\u000e\u00d9\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00e1"+
		"\b\u000e\u0001\u000e\u0003\u000e\u00e4\b\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e"+
		"\u00f3\b\u000e\u0001\u000e\u0003\u000e\u00f6\b\u000e\u0001\u000e\u0003"+
		"\u000e\u00f9\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00fd\b\u000e"+
		"\u0001\u000e\u0003\u000e\u0100\b\u000e\u0001\u000e\u0003\u000e\u0103\b"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0107\b\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u010b\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u010f"+
		"\b\u000e\u0001\u000e\u0003\u000e\u0112\b\u000e\u0001\u000e\u0001\u000e"+
		"\u0003\u000e\u0116\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u011f\b\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u0129\b\u0010\n\u0010\f\u0010\u012c\t\u0010\u0001"+
		"\u0010\u0005\u0010\u012f\b\u0010\n\u0010\f\u0010\u0132\t\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0005\u0010\u0137\b\u0010\n\u0010\f\u0010\u013a"+
		"\t\u0010\u0001\u0010\u0005\u0010\u013d\b\u0010\n\u0010\f\u0010\u0140\t"+
		"\u0010\u0001\u0011\u0001\u0011\u0003\u0011\u0144\b\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0003\u0011\u0149\b\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u014d\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0152"+
		"\b\u0011\u0001\u0011\u0003\u0011\u0155\b\u0011\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u0159\b\u0011\u0003\u0011\u015b\b\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0003\u0013\u0163"+
		"\b\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0005\u0015\u016d\b\u0015\n\u0015\f\u0015"+
		"\u0170\t\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0174\b\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0179\b\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0003\u0015\u017e\b\u0015\u0001\u0015\u0003\u0015\u0181"+
		"\b\u0015\u0001\u0016\u0003\u0016\u0184\b\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u018b\b\u0017\n\u0017"+
		"\f\u0017\u018e\t\u0017\u0001\u0017\u0005\u0017\u0191\b\u0017\n\u0017\f"+
		"\u0017\u0194\t\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0005\u0018\u019b\b\u0018\n\u0018\f\u0018\u019e\t\u0018\u0001\u0018"+
		"\u0005\u0018\u01a1\b\u0018\n\u0018\f\u0018\u01a4\t\u0018\u0001\u0018\u0005"+
		"\u0018\u01a7\b\u0018\n\u0018\f\u0018\u01aa\t\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0005\u0019\u01b0\b\u0019\n\u0019\f\u0019\u01b3"+
		"\t\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u01be\b\u0019\u0003"+
		"\u0019\u01c0\b\u0019\u0001\u001a\u0005\u001a\u01c3\b\u001a\n\u001a\f\u001a"+
		"\u01c6\t\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0003\u001c\u01cd\b\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0003\u001d\u01d3\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d"+
		"\u01d8\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0003\u001d\u01df\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e"+
		"\u01e4\b\u001e\n\u001e\f\u001e\u01e7\t\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u01f0"+
		"\b\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0005"+
		"\u001f\u01f7\b\u001f\n\u001f\f\u001f\u01fa\t\u001f\u0001\u001f\u0005\u001f"+
		"\u01fd\b\u001f\n\u001f\f\u001f\u0200\t\u001f\u0001 \u0001 \u0001 \u0005"+
		" \u0205\b \n \f \u0208\t \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0005 \u0211\b \n \f \u0214\t \u0001 \u0001 \u0001!\u0001!\u0001!\u0005"+
		"!\u021b\b!\n!\f!\u021e\t!\u0001!\u0005!\u0221\b!\n!\f!\u0224\t!\u0001"+
		"!\u0001!\u0001\"\u0001\"\u0001\"\u0005\"\u022b\b\"\n\"\f\"\u022e\t\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0005\"\u0237\b\"\n"+
		"\"\f\"\u023a\t\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0003#\u0244\b#\u0001$\u0001$\u0001$\u0003$\u0249\b$\u0001$\u0001$\u0003"+
		"$\u024d\b$\u0001$\u0005$\u0250\b$\n$\f$\u0253\t$\u0001$\u0001$\u0001%"+
		"\u0001%\u0001%\u0003%\u025a\b%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0005&\u026a"+
		"\b&\n&\f&\u026d\t&\u0001&\u0001&\u0003&\u0271\b&\u0001\'\u0001\'\u0001"+
		"\'\u0003\'\u0276\b\'\u0001\'\u0001\'\u0005\'\u027a\b\'\n\'\f\'\u027d\t"+
		"\'\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0003(\u0284\b(\u0001(\u0001("+
		"\u0001(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0005)\u0293\b)\n)\f)\u0296\t)\u0001)\u0003)\u0299\b)\u0001*"+
		"\u0001*\u0001*\u0003*\u029e\b*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u02ac\b+\u0001,\u0001"+
		",\u0001,\u0003,\u02b1\b,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0003"+
		",\u02b9\b,\u0001,\u0001,\u0001,\u0001,\u0001,\u0001,\u0003,\u02c1\b,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0003,\u02c9\b,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0003,\u02d1\b,\u0001,\u0001,\u0001,\u0003,\u02d6"+
		"\b,\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001"+
		".\u0001.\u0001.\u0001.\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001"+
		"/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001"+
		"/\u0001/\u0001/\u0001/\u0003/\u02f9\b/\u00010\u00010\u00010\u00010\u0001"+
		"0\u00010\u00011\u00011\u00011\u00011\u00011\u00012\u00012\u00013\u0001"+
		"3\u00013\u00033\u030b\b3\u00013\u00013\u00013\u00014\u00014\u00014\u0001"+
		"4\u00014\u00015\u00015\u00015\u00015\u00015\u00015\u00015\u00015\u0001"+
		"5\u00015\u00035\u031f\b5\u00016\u00016\u00016\u00036\u0324\b6\u00016\u0005"+
		"6\u0327\b6\n6\f6\u032a\t6\u00016\u00016\u00017\u00017\u00017\u00017\u0003"+
		"7\u0332\b7\u00017\u00017\u00057\u0336\b7\n7\f7\u0339\t7\u00017\u00037"+
		"\u033c\b7\u00018\u00018\u00018\u00038\u0341\b8\u00018\u00018\u00018\u0001"+
		"8\u00018\u00018\u00018\u00038\u034a\b8\u00018\u00018\u00018\u00038\u034f"+
		"\b8\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u0001"+
		"9\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u0001"+
		"9\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u0001"+
		"9\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u0001"+
		"9\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u00019\u0001"+
		"9\u00019\u00019\u00039\u0385\b9\u0001:\u0001:\u0001:\u0001:\u0001:\u0001"+
		":\u0001:\u0003:\u038e\b:\u0001:\u0001:\u0001:\u0003:\u0393\b:\u0001;\u0001"+
		";\u0001;\u0003;\u0398\b;\u0001;\u0005;\u039b\b;\n;\f;\u039e\t;\u0001;"+
		"\u0001;\u0001;\u0001;\u0003;\u03a4\b;\u0001;\u0001;\u0001;\u0001;\u0001"+
		";\u0003;\u03ab\b;\u0001;\u0001;\u0001;\u0001;\u0001;\u0003;\u03b2\b;\u0001"+
		";\u0003;\u03b5\b;\u0001<\u0001<\u0001<\u0001<\u0001<\u0001=\u0005=\u03bd"+
		"\b=\n=\f=\u03c0\t=\u0001>\u0005>\u03c3\b>\n>\f>\u03c6\t>\u0001>\u0001"+
		">\u0004>\u03ca\b>\u000b>\f>\u03cb\u0001>\u0001>\u0003>\u03d0\b>\u0001"+
		"?\u0001?\u0001?\u0001?\u0005?\u03d6\b?\n?\f?\u03d9\t?\u0001?\u0003?\u03dc"+
		"\b?\u0001?\u0000\u0000@\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\"+
		"^`bdfhjlnprtvxz|~\u0000\u0005\u0001\u0000\u0004\u0005\u0001\u0000\u0003"+
		"\u0005\u0002\u0000\u0003\u0003LL\u0001\u0000\u000e\u000f\u0001\u0000<"+
		"=\u044a\u0000\u0080\u0001\u0000\u0000\u0000\u0002\u0082\u0001\u0000\u0000"+
		"\u0000\u0004\u0084\u0001\u0000\u0000\u0000\u0006\u0086\u0001\u0000\u0000"+
		"\u0000\b\u008e\u0001\u0000\u0000\u0000\n\u0090\u0001\u0000\u0000\u0000"+
		"\f\u00ad\u0001\u0000\u0000\u0000\u000e\u00b0\u0001\u0000\u0000\u0000\u0010"+
		"\u00b6\u0001\u0000\u0000\u0000\u0012\u00ba\u0001\u0000\u0000\u0000\u0014"+
		"\u00bf\u0001\u0000\u0000\u0000\u0016\u00c1\u0001\u0000\u0000\u0000\u0018"+
		"\u00c3\u0001\u0000\u0000\u0000\u001a\u00c8\u0001\u0000\u0000\u0000\u001c"+
		"\u011e\u0001\u0000\u0000\u0000\u001e\u0120\u0001\u0000\u0000\u0000 \u0130"+
		"\u0001\u0000\u0000\u0000\"\u015a\u0001\u0000\u0000\u0000$\u015c\u0001"+
		"\u0000\u0000\u0000&\u0162\u0001\u0000\u0000\u0000(\u0166\u0001\u0000\u0000"+
		"\u0000*\u0180\u0001\u0000\u0000\u0000,\u0183\u0001\u0000\u0000\u0000."+
		"\u0192\u0001\u0000\u0000\u00000\u01a2\u0001\u0000\u0000\u00002\u01bf\u0001"+
		"\u0000\u0000\u00004\u01c4\u0001\u0000\u0000\u00006\u01c7\u0001\u0000\u0000"+
		"\u00008\u01c9\u0001\u0000\u0000\u0000:\u01de\u0001\u0000\u0000\u0000<"+
		"\u01ef\u0001\u0000\u0000\u0000>\u01fe\u0001\u0000\u0000\u0000@\u0212\u0001"+
		"\u0000\u0000\u0000B\u0222\u0001\u0000\u0000\u0000D\u0238\u0001\u0000\u0000"+
		"\u0000F\u0243\u0001\u0000\u0000\u0000H\u0245\u0001\u0000\u0000\u0000J"+
		"\u0256\u0001\u0000\u0000\u0000L\u0270\u0001\u0000\u0000\u0000N\u0272\u0001"+
		"\u0000\u0000\u0000P\u0280\u0001\u0000\u0000\u0000R\u0298\u0001\u0000\u0000"+
		"\u0000T\u029a\u0001\u0000\u0000\u0000V\u02ab\u0001\u0000\u0000\u0000X"+
		"\u02d5\u0001\u0000\u0000\u0000Z\u02d7\u0001\u0000\u0000\u0000\\\u02de"+
		"\u0001\u0000\u0000\u0000^\u02f8\u0001\u0000\u0000\u0000`\u02fa\u0001\u0000"+
		"\u0000\u0000b\u0300\u0001\u0000\u0000\u0000d\u0305\u0001\u0000\u0000\u0000"+
		"f\u0307\u0001\u0000\u0000\u0000h\u030f\u0001\u0000\u0000\u0000j\u031e"+
		"\u0001\u0000\u0000\u0000l\u0320\u0001\u0000\u0000\u0000n\u033b\u0001\u0000"+
		"\u0000\u0000p\u034e\u0001\u0000\u0000\u0000r\u0384\u0001\u0000\u0000\u0000"+
		"t\u0392\u0001\u0000\u0000\u0000v\u03b4\u0001\u0000\u0000\u0000x\u03b6"+
		"\u0001\u0000\u0000\u0000z\u03be\u0001\u0000\u0000\u0000|\u03cf\u0001\u0000"+
		"\u0000\u0000~\u03db\u0001\u0000\u0000\u0000\u0080\u0081\u0007\u0000\u0000"+
		"\u0000\u0081\u0001\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u0006\u0000"+
		"\u0000\u0083\u0003\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u0007\u0000"+
		"\u0000\u0085\u0005\u0001\u0000\u0000\u0000\u0086\u0087\u0005\t\u0000\u0000"+
		"\u0087\u0007\u0001\u0000\u0000\u0000\u0088\u008f\u0003\u0004\u0002\u0000"+
		"\u0089\u008a\u0005\u0001\u0000\u0000\u008a\u008b\u0005\n\u0000\u0000\u008b"+
		"\u008c\u0003\u0004\u0002\u0000\u008c\u008d\u0005\u0002\u0000\u0000\u008d"+
		"\u008f\u0001\u0000\u0000\u0000\u008e\u0088\u0001\u0000\u0000\u0000\u008e"+
		"\u0089\u0001\u0000\u0000\u0000\u008f\t\u0001\u0000\u0000\u0000\u0090\u0091"+
		"\u0005\u0001\u0000\u0000\u0091\u0092\u0005.\u0000\u0000\u0092\u0093\u0003"+
		"\f\u0006\u0000\u0093\u0094\u0005\u0002\u0000\u0000\u0094\u000b\u0001\u0000"+
		"\u0000\u0000\u0095\u00a8\u0005\u0001\u0000\u0000\u0096\u009a\u00051\u0000"+
		"\u0000\u0097\u0099\u0003\u0004\u0002\u0000\u0098\u0097\u0001\u0000\u0000"+
		"\u0000\u0099\u009c\u0001\u0000\u0000\u0000\u009a\u0098\u0001\u0000\u0000"+
		"\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u00a9\u0001\u0000\u0000"+
		"\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009d\u00a1\u00050\u0000\u0000"+
		"\u009e\u00a0\u0003\u0004\u0002\u0000\u009f\u009e\u0001\u0000\u0000\u0000"+
		"\u00a0\u00a3\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a9\u0001\u0000\u0000\u0000"+
		"\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a4\u00a5\u00050\u0000\u0000\u00a5"+
		"\u00a6\u0003\u0018\f\u0000\u00a6\u00a7\u0003\u0004\u0002\u0000\u00a7\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a8\u0096\u0001\u0000\u0000\u0000\u00a8\u009d"+
		"\u0001\u0000\u0000\u0000\u00a8\u00a4\u0001\u0000\u0000\u0000\u00a9\u00aa"+
		"\u0001\u0000\u0000\u0000\u00aa\u00ac\u0005\u0002\u0000\u0000\u00ab\u0095"+
		"\u0001\u0000\u0000\u0000\u00ac\u00af\u0001\u0000\u0000\u0000\u00ad\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\r\u0001"+
		"\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0\u00b2\u0005"+
		"\u0003\u0000\u0000\u00b1\u00b3\u0005\u0003\u0000\u0000\u00b2\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b5\u0003\u0006\u0003\u0000\u00b5\u000f\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b8\u0005\u0003\u0000\u0000\u00b7\u00b9\u0005"+
		"\u0003\u0000\u0000\u00b8\u00b7\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001"+
		"\u0000\u0000\u0000\u00b9\u0011\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005"+
		"\u0001\u0000\u0000\u00bb\u00bc\u0005-\u0000\u0000\u00bc\u00bd\u0003\u0016"+
		"\u000b\u0000\u00bd\u00be\u0005\u0002\u0000\u0000\u00be\u0013\u0001\u0000"+
		"\u0000\u0000\u00bf\u00c0\u0007\u0001\u0000\u0000\u00c0\u0015\u0001\u0000"+
		"\u0000\u0000\u00c1\u00c2\u0007\u0002\u0000\u0000\u00c2\u0017\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c4\u0005L\u0000\u0000\u00c4\u0019\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c9\u0003\u001c\u000e\u0000\u00c6\u00c9\u0003\"\u0011\u0000"+
		"\u00c7\u00c9\u0003(\u0014\u0000\u00c8\u00c5\u0001\u0000\u0000\u0000\u00c8"+
		"\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c9"+
		"\u001b\u0001\u0000\u0000\u0000\u00ca\u011f\u0005\f\u0000\u0000\u00cb\u011f"+
		"\u0005\u000b\u0000\u0000\u00cc\u011f\u0005\r\u0000\u0000\u00cd\u00cf\u0005"+
		"\u0018\u0000\u0000\u00ce\u00d0\u0003\u001e\u000f\u0000\u00cf\u00ce\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u011f\u0001"+
		"\u0000\u0000\u0000\u00d1\u00d2\u0005\u0011\u0000\u0000\u00d2\u011f\u0003"+
		"\u0016\u000b\u0000\u00d3\u00d4\u0005\u0012\u0000\u0000\u00d4\u011f\u0003"+
		"\u0016\u000b\u0000\u00d5\u00d7\u0005\u0013\u0000\u0000\u00d6\u00d8\u0003"+
		"\u0016\u000b\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001"+
		"\u0000\u0000\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00d9\u00da\u0001"+
		"\u0000\u0000\u0000\u00da\u011f\u0001\u0000\u0000\u0000\u00db\u011f\u0005"+
		"\u0014\u0000\u0000\u00dc\u00dd\u0005\u0019\u0000\u0000\u00dd\u011f\u0003"+
		"\u0016\u000b\u0000\u00de\u00e0\u0005\u001a\u0000\u0000\u00df\u00e1\u0003"+
		"\u0016\u000b\u0000\u00e0\u00df\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001"+
		"\u0000\u0000\u0000\u00e1\u00e3\u0001\u0000\u0000\u0000\u00e2\u00e4\u0003"+
		"\u0012\t\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000"+
		"\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u011f\u0003 \u0010"+
		"\u0000\u00e6\u00e7\u0005\u001b\u0000\u0000\u00e7\u011f\u0003\u0016\u000b"+
		"\u0000\u00e8\u00e9\u0005\u001c\u0000\u0000\u00e9\u011f\u0003\u0016\u000b"+
		"\u0000\u00ea\u00eb\u0005\u001d\u0000\u0000\u00eb\u011f\u0003\u0016\u000b"+
		"\u0000\u00ec\u00ed\u0005\u001e\u0000\u0000\u00ed\u011f\u0003\u0016\u000b"+
		"\u0000\u00ee\u00ef\u0005\u001f\u0000\u0000\u00ef\u011f\u0003\u0016\u000b"+
		"\u0000\u00f0\u00f2\u0005 \u0000\u0000\u00f1\u00f3\u0003\u0016\u000b\u0000"+
		"\u00f2\u00f1\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000"+
		"\u00f3\u00f5\u0001\u0000\u0000\u0000\u00f4\u00f6\u0005\"\u0000\u0000\u00f5"+
		"\u00f4\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f6"+
		"\u00f8\u0001\u0000\u0000\u0000\u00f7\u00f9\u0005#\u0000\u0000\u00f8\u00f7"+
		"\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000\u0000\u00f9\u011f"+
		"\u0001\u0000\u0000\u0000\u00fa\u00fc\u0005!\u0000\u0000\u00fb\u00fd\u0003"+
		"\u0016\u000b\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001"+
		"\u0000\u0000\u0000\u00fd\u00ff\u0001\u0000\u0000\u0000\u00fe\u0100\u0005"+
		"\"\u0000\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000"+
		"\u0000\u0000\u0100\u0102\u0001\u0000\u0000\u0000\u0101\u0103\u0005#\u0000"+
		"\u0000\u0102\u0101\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000"+
		"\u0000\u0103\u011f\u0001\u0000\u0000\u0000\u0104\u0106\u0005)\u0000\u0000"+
		"\u0105\u0107\u0003\u0016\u000b\u0000\u0106\u0105\u0001\u0000\u0000\u0000"+
		"\u0106\u0107\u0001\u0000\u0000\u0000\u0107\u011f\u0001\u0000\u0000\u0000"+
		"\u0108\u010a\u0005*\u0000\u0000\u0109\u010b\u0003\u0016\u000b\u0000\u010a"+
		"\u0109\u0001\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b"+
		"\u011f\u0001\u0000\u0000\u0000\u010c\u010e\u0005+\u0000\u0000\u010d\u010f"+
		"\u0003\u0016\u000b\u0000\u010e\u010d\u0001\u0000\u0000\u0000\u010e\u010f"+
		"\u0001\u0000\u0000\u0000\u010f\u0111\u0001\u0000\u0000\u0000\u0110\u0112"+
		"\u0003\u0016\u000b\u0000\u0111\u0110\u0001\u0000\u0000\u0000\u0111\u0112"+
		"\u0001\u0000\u0000\u0000\u0112\u011f\u0001\u0000\u0000\u0000\u0113\u0115"+
		"\u0005,\u0000\u0000\u0114\u0116\u0003\u0016\u000b\u0000\u0115\u0114\u0001"+
		"\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u011f\u0001"+
		"\u0000\u0000\u0000\u0117\u0118\u0005\b\u0000\u0000\u0118\u011f\u0003\u0014"+
		"\n\u0000\u0119\u011f\u0005&\u0000\u0000\u011a\u011f\u0005\'\u0000\u0000"+
		"\u011b\u011f\u0005$\u0000\u0000\u011c\u011f\u0005%\u0000\u0000\u011d\u011f"+
		"\u0005(\u0000\u0000\u011e\u00ca\u0001\u0000\u0000\u0000\u011e\u00cb\u0001"+
		"\u0000\u0000\u0000\u011e\u00cc\u0001\u0000\u0000\u0000\u011e\u00cd\u0001"+
		"\u0000\u0000\u0000\u011e\u00d1\u0001\u0000\u0000\u0000\u011e\u00d3\u0001"+
		"\u0000\u0000\u0000\u011e\u00d5\u0001\u0000\u0000\u0000\u011e\u00db\u0001"+
		"\u0000\u0000\u0000\u011e\u00dc\u0001\u0000\u0000\u0000\u011e\u00de\u0001"+
		"\u0000\u0000\u0000\u011e\u00e6\u0001\u0000\u0000\u0000\u011e\u00e8\u0001"+
		"\u0000\u0000\u0000\u011e\u00ea\u0001\u0000\u0000\u0000\u011e\u00ec\u0001"+
		"\u0000\u0000\u0000\u011e\u00ee\u0001\u0000\u0000\u0000\u011e\u00f0\u0001"+
		"\u0000\u0000\u0000\u011e\u00fa\u0001\u0000\u0000\u0000\u011e\u0104\u0001"+
		"\u0000\u0000\u0000\u011e\u0108\u0001\u0000\u0000\u0000\u011e\u010c\u0001"+
		"\u0000\u0000\u0000\u011e\u0113\u0001\u0000\u0000\u0000\u011e\u0117\u0001"+
		"\u0000\u0000\u0000\u011e\u0119\u0001\u0000\u0000\u0000\u011e\u011a\u0001"+
		"\u0000\u0000\u0000\u011e\u011b\u0001\u0000\u0000\u0000\u011e\u011c\u0001"+
		"\u0000\u0000\u0000\u011e\u011d\u0001\u0000\u0000\u0000\u011f\u001d\u0001"+
		"\u0000\u0000\u0000\u0120\u0121\u0005\u0001\u0000\u0000\u0121\u0122\u0005"+
		"\u0014\u0000\u0000\u0122\u0123\u0003\u0004\u0002\u0000\u0123\u0124\u0005"+
		"\u0002\u0000\u0000\u0124\u001f\u0001\u0000\u0000\u0000\u0125\u0126\u0005"+
		"\u0001\u0000\u0000\u0126\u012a\u00050\u0000\u0000\u0127\u0129\u0003\u0004"+
		"\u0002\u0000\u0128\u0127\u0001\u0000\u0000\u0000\u0129\u012c\u0001\u0000"+
		"\u0000\u0000\u012a\u0128\u0001\u0000\u0000\u0000\u012a\u012b\u0001\u0000"+
		"\u0000\u0000\u012b\u012d\u0001\u0000\u0000\u0000\u012c\u012a\u0001\u0000"+
		"\u0000\u0000\u012d\u012f\u0005\u0002\u0000\u0000\u012e\u0125\u0001\u0000"+
		"\u0000\u0000\u012f\u0132\u0001\u0000\u0000\u0000\u0130\u012e\u0001\u0000"+
		"\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000\u0131\u013e\u0001\u0000"+
		"\u0000\u0000\u0132\u0130\u0001\u0000\u0000\u0000\u0133\u0134\u0005\u0001"+
		"\u0000\u0000\u0134\u0138\u00051\u0000\u0000\u0135\u0137\u0003\u0004\u0002"+
		"\u0000\u0136\u0135\u0001\u0000\u0000\u0000\u0137\u013a\u0001\u0000\u0000"+
		"\u0000\u0138\u0136\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000"+
		"\u0000\u0139\u013b\u0001\u0000\u0000\u0000\u013a\u0138\u0001\u0000\u0000"+
		"\u0000\u013b\u013d\u0005\u0002\u0000\u0000\u013c\u0133\u0001\u0000\u0000"+
		"\u0000\u013d\u0140\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000"+
		"\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f!\u0001\u0000\u0000\u0000"+
		"\u0140\u013e\u0001\u0000\u0000\u0000\u0141\u0143\u0007\u0003\u0000\u0000"+
		"\u0142\u0144\u0003\u0018\f\u0000\u0143\u0142\u0001\u0000\u0000\u0000\u0143"+
		"\u0144\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000\u0000\u0000\u0145"+
		"\u0146\u0003&\u0013\u0000\u0146\u0148\u0005\u0010\u0000\u0000\u0147\u0149"+
		"\u0003\u0018\f\u0000\u0148\u0147\u0001\u0000\u0000\u0000\u0148\u0149\u0001"+
		"\u0000\u0000\u0000\u0149\u015b\u0001\u0000\u0000\u0000\u014a\u014c\u0005"+
		"\u0015\u0000\u0000\u014b\u014d\u0003\u0018\f\u0000\u014c\u014b\u0001\u0000"+
		"\u0000\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d\u014e\u0001\u0000"+
		"\u0000\u0000\u014e\u0154\u0003&\u0013\u0000\u014f\u0151\u0005\u0017\u0000"+
		"\u0000\u0150\u0152\u0003\u0018\f\u0000\u0151\u0150\u0001\u0000\u0000\u0000"+
		"\u0151\u0152\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000"+
		"\u0153\u0155\u00034\u001a\u0000\u0154\u014f\u0001\u0000\u0000\u0000\u0154"+
		"\u0155\u0001\u0000\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156"+
		"\u0158\u0005\u0010\u0000\u0000\u0157\u0159\u0003\u0018\f\u0000\u0158\u0157"+
		"\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u015b"+
		"\u0001\u0000\u0000\u0000\u015a\u0141\u0001\u0000\u0000\u0000\u015a\u014a"+
		"\u0001\u0000\u0000\u0000\u015b#\u0001\u0000\u0000\u0000\u015c\u015d\u0005"+
		"\u0001\u0000\u0000\u015d\u015e\u00051\u0000\u0000\u015e\u015f\u0003\u0004"+
		"\u0002\u0000\u015f\u0160\u0005\u0002\u0000\u0000\u0160%\u0001\u0000\u0000"+
		"\u0000\u0161\u0163\u0003$\u0012\u0000\u0162\u0161\u0001\u0000\u0000\u0000"+
		"\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000\u0000"+
		"\u0164\u0165\u00034\u001a\u0000\u0165\'\u0001\u0000\u0000\u0000\u0166"+
		"\u0167\u0005\u0001\u0000\u0000\u0167\u0168\u0003*\u0015\u0000\u0168\u0169"+
		"\u0005\u0002\u0000\u0000\u0169)\u0001\u0000\u0000\u0000\u016a\u016e\u0003"+
		"\u001c\u000e\u0000\u016b\u016d\u0003(\u0014\u0000\u016c\u016b\u0001\u0000"+
		"\u0000\u0000\u016d\u0170\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000"+
		"\u0000\u0000\u016e\u016f\u0001\u0000\u0000\u0000\u016f\u0181\u0001\u0000"+
		"\u0000\u0000\u0170\u016e\u0001\u0000\u0000\u0000\u0171\u0173\u0005\u000e"+
		"\u0000\u0000\u0172\u0174\u0003\u0018\f\u0000\u0173\u0172\u0001\u0000\u0000"+
		"\u0000\u0173\u0174\u0001\u0000\u0000\u0000\u0174\u0175\u0001\u0000\u0000"+
		"\u0000\u0175\u0181\u0003&\u0013\u0000\u0176\u0178\u0005\u000f\u0000\u0000"+
		"\u0177\u0179\u0003\u0018\f\u0000\u0178\u0177\u0001\u0000\u0000\u0000\u0178"+
		"\u0179\u0001\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a"+
		"\u0181\u0003&\u0013\u0000\u017b\u017d\u0005\u0015\u0000\u0000\u017c\u017e"+
		"\u0003\u0018\f\u0000\u017d\u017c\u0001\u0000\u0000\u0000\u017d\u017e\u0001"+
		"\u0000\u0000\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0181\u0003"+
		"2\u0019\u0000\u0180\u016a\u0001\u0000\u0000\u0000\u0180\u0171\u0001\u0000"+
		"\u0000\u0000\u0180\u0176\u0001\u0000\u0000\u0000\u0180\u017b\u0001\u0000"+
		"\u0000\u0000\u0181+\u0001\u0000\u0000\u0000\u0182\u0184\u0003\u0012\t"+
		"\u0000\u0183\u0182\u0001\u0000\u0000\u0000\u0183\u0184\u0001\u0000\u0000"+
		"\u0000\u0184\u0185\u0001\u0000\u0000\u0000\u0185\u0186\u0003.\u0017\u0000"+
		"\u0186-\u0001\u0000\u0000\u0000\u0187\u0188\u0005\u0001\u0000\u0000\u0188"+
		"\u018c\u00050\u0000\u0000\u0189\u018b\u0003\u0004\u0002\u0000\u018a\u0189"+
		"\u0001\u0000\u0000\u0000\u018b\u018e\u0001\u0000\u0000\u0000\u018c\u018a"+
		"\u0001\u0000\u0000\u0000\u018c\u018d\u0001\u0000\u0000\u0000\u018d\u018f"+
		"\u0001\u0000\u0000\u0000\u018e\u018c\u0001\u0000\u0000\u0000\u018f\u0191"+
		"\u0005\u0002\u0000\u0000\u0190\u0187\u0001\u0000\u0000\u0000\u0191\u0194"+
		"\u0001\u0000\u0000\u0000\u0192\u0190\u0001\u0000\u0000\u0000\u0192\u0193"+
		"\u0001\u0000\u0000\u0000\u0193\u0195\u0001\u0000\u0000\u0000\u0194\u0192"+
		"\u0001\u0000\u0000\u0000\u0195\u0196\u00030\u0018\u0000\u0196/\u0001\u0000"+
		"\u0000\u0000\u0197\u0198\u0005\u0001\u0000\u0000\u0198\u019c\u00051\u0000"+
		"\u0000\u0199\u019b\u0003\u0004\u0002\u0000\u019a\u0199\u0001\u0000\u0000"+
		"\u0000\u019b\u019e\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000"+
		"\u0000\u019c\u019d\u0001\u0000\u0000\u0000\u019d\u019f\u0001\u0000\u0000"+
		"\u0000\u019e\u019c\u0001\u0000\u0000\u0000\u019f\u01a1\u0005\u0002\u0000"+
		"\u0000\u01a0\u0197\u0001\u0000\u0000\u0000\u01a1\u01a4\u0001\u0000\u0000"+
		"\u0000\u01a2\u01a0\u0001\u0000\u0000\u0000\u01a2\u01a3\u0001\u0000\u0000"+
		"\u0000\u01a3\u01a8\u0001\u0000\u0000\u0000\u01a4\u01a2\u0001\u0000\u0000"+
		"\u0000\u01a5\u01a7\u0003(\u0014\u0000\u01a6\u01a5\u0001\u0000\u0000\u0000"+
		"\u01a7\u01aa\u0001\u0000\u0000\u0000\u01a8\u01a6\u0001\u0000\u0000\u0000"+
		"\u01a8\u01a9\u0001\u0000\u0000\u0000\u01a91\u0001\u0000\u0000\u0000\u01aa"+
		"\u01a8\u0001\u0000\u0000\u0000\u01ab\u01ac\u0003$\u0012\u0000\u01ac\u01ad"+
		"\u00032\u0019\u0000\u01ad\u01c0\u0001\u0000\u0000\u0000\u01ae\u01b0\u0003"+
		"(\u0014\u0000\u01af\u01ae\u0001\u0000\u0000\u0000\u01b0\u01b3\u0001\u0000"+
		"\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b1\u01b2\u0001\u0000"+
		"\u0000\u0000\u01b2\u01b4\u0001\u0000\u0000\u0000\u01b3\u01b1\u0001\u0000"+
		"\u0000\u0000\u01b4\u01b5\u0005\u0001\u0000\u0000\u01b5\u01b6\u0005\u0016"+
		"\u0000\u0000\u01b6\u01b7\u00034\u001a\u0000\u01b7\u01bd\u0005\u0002\u0000"+
		"\u0000\u01b8\u01b9\u0005\u0001\u0000\u0000\u01b9\u01ba\u0005\u0017\u0000"+
		"\u0000\u01ba\u01bb\u00034\u001a\u0000\u01bb\u01bc\u0005\u0002\u0000\u0000"+
		"\u01bc\u01be\u0001\u0000\u0000\u0000\u01bd\u01b8\u0001\u0000\u0000\u0000"+
		"\u01bd\u01be\u0001\u0000\u0000\u0000\u01be\u01c0\u0001\u0000\u0000\u0000"+
		"\u01bf\u01ab\u0001\u0000\u0000\u0000\u01bf\u01b1\u0001\u0000\u0000\u0000"+
		"\u01c03\u0001\u0000\u0000\u0000\u01c1\u01c3\u0003\u001a\r\u0000\u01c2"+
		"\u01c1\u0001\u0000\u0000\u0000\u01c3\u01c6\u0001\u0000\u0000\u0000\u01c4"+
		"\u01c2\u0001\u0000\u0000\u0000\u01c4\u01c5\u0001\u0000\u0000\u0000\u01c5"+
		"5\u0001\u0000\u0000\u0000\u01c6\u01c4\u0001\u0000\u0000\u0000\u01c7\u01c8"+
		"\u00034\u001a\u0000\u01c87\u0001\u0000\u0000\u0000\u01c9\u01ca\u0005\u0001"+
		"\u0000\u0000\u01ca\u01cc\u0005.\u0000\u0000\u01cb\u01cd\u0003\u0018\f"+
		"\u0000\u01cc\u01cb\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001\u0000\u0000"+
		"\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce\u01cf\u0003:\u001d\u0000"+
		"\u01cf\u01d0\u0005\u0002\u0000\u0000\u01d09\u0001\u0000\u0000\u0000\u01d1"+
		"\u01d3\u0003\u0012\t\u0000\u01d2\u01d1\u0001\u0000\u0000\u0000\u01d2\u01d3"+
		"\u0001\u0000\u0000\u0000\u01d3\u01d4\u0001\u0000\u0000\u0000\u01d4\u01df"+
		"\u0003@ \u0000\u01d5\u01d7\u0003\\.\u0000\u01d6\u01d8\u0003\u0012\t\u0000"+
		"\u01d7\u01d6\u0001\u0000\u0000\u0000\u01d7\u01d8\u0001\u0000\u0000\u0000"+
		"\u01d8\u01d9\u0001\u0000\u0000\u0000\u01d9\u01da\u0003<\u001e\u0000\u01da"+
		"\u01df\u0001\u0000\u0000\u0000\u01db\u01dc\u0003b1\u0000\u01dc\u01dd\u0003"+
		":\u001d\u0000\u01dd\u01df\u0001\u0000\u0000\u0000\u01de\u01d2\u0001\u0000"+
		"\u0000\u0000\u01de\u01d5\u0001\u0000\u0000\u0000\u01de\u01db\u0001\u0000"+
		"\u0000\u0000\u01df;\u0001\u0000\u0000\u0000\u01e0\u01e1\u0005\u0001\u0000"+
		"\u0000\u01e1\u01e5\u00050\u0000\u0000\u01e2\u01e4\u0003\u0004\u0002\u0000"+
		"\u01e3\u01e2\u0001\u0000\u0000\u0000\u01e4\u01e7\u0001\u0000\u0000\u0000"+
		"\u01e5\u01e3\u0001\u0000\u0000\u0000\u01e5\u01e6\u0001\u0000\u0000\u0000"+
		"\u01e6\u01e8\u0001\u0000\u0000\u0000\u01e7\u01e5\u0001\u0000\u0000\u0000"+
		"\u01e8\u01f0\u0005\u0002\u0000\u0000\u01e9\u01ea\u0005\u0001\u0000\u0000"+
		"\u01ea\u01eb\u00050\u0000\u0000\u01eb\u01ec\u0003\u0018\f\u0000\u01ec"+
		"\u01ed\u0003\u0004\u0002\u0000\u01ed\u01ee\u0005\u0002\u0000\u0000\u01ee"+
		"\u01f0\u0001\u0000\u0000\u0000\u01ef\u01e0\u0001\u0000\u0000\u0000\u01ef"+
		"\u01e9\u0001\u0000\u0000\u0000\u01f0\u01f1\u0001\u0000\u0000\u0000\u01f1"+
		"\u01f2\u0003>\u001f\u0000\u01f2=\u0001\u0000\u0000\u0000\u01f3\u01f4\u0005"+
		"\u0001\u0000\u0000\u01f4\u01f8\u00051\u0000\u0000\u01f5\u01f7\u0003\u0004"+
		"\u0002\u0000\u01f6\u01f5\u0001\u0000\u0000\u0000\u01f7\u01fa\u0001\u0000"+
		"\u0000\u0000\u01f8\u01f6\u0001\u0000\u0000\u0000\u01f8\u01f9\u0001\u0000"+
		"\u0000\u0000\u01f9\u01fb\u0001\u0000\u0000\u0000\u01fa\u01f8\u0001\u0000"+
		"\u0000\u0000\u01fb\u01fd\u0005\u0002\u0000\u0000\u01fc\u01f3\u0001\u0000"+
		"\u0000\u0000\u01fd\u0200\u0001\u0000\u0000\u0000\u01fe\u01fc\u0001\u0000"+
		"\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000\u01ff?\u0001\u0000\u0000"+
		"\u0000\u0200\u01fe\u0001\u0000\u0000\u0000\u0201\u0202\u0005\u0001\u0000"+
		"\u0000\u0202\u0206\u00050\u0000\u0000\u0203\u0205\u0003\u0004\u0002\u0000"+
		"\u0204\u0203\u0001\u0000\u0000\u0000\u0205\u0208\u0001\u0000\u0000\u0000"+
		"\u0206\u0204\u0001\u0000\u0000\u0000\u0206\u0207\u0001\u0000\u0000\u0000"+
		"\u0207\u0209\u0001\u0000\u0000\u0000\u0208\u0206\u0001\u0000\u0000\u0000"+
		"\u0209\u0211\u0005\u0002\u0000\u0000\u020a\u020b\u0005\u0001\u0000\u0000"+
		"\u020b\u020c\u00050\u0000\u0000\u020c\u020d\u0003\u0018\f\u0000\u020d"+
		"\u020e\u0003\u0004\u0002\u0000\u020e\u020f\u0005\u0002\u0000\u0000\u020f"+
		"\u0211\u0001\u0000\u0000\u0000\u0210\u0201\u0001\u0000\u0000\u0000\u0210"+
		"\u020a\u0001\u0000\u0000\u0000\u0211\u0214\u0001\u0000\u0000\u0000\u0212"+
		"\u0210\u0001\u0000\u0000\u0000\u0212\u0213\u0001\u0000\u0000\u0000\u0213"+
		"\u0215\u0001\u0000\u0000\u0000\u0214\u0212\u0001\u0000\u0000\u0000\u0215"+
		"\u0216\u0003B!\u0000\u0216A\u0001\u0000\u0000\u0000\u0217\u0218\u0005"+
		"\u0001\u0000\u0000\u0218\u021c\u00051\u0000\u0000\u0219\u021b\u0003\u0004"+
		"\u0002\u0000\u021a\u0219\u0001\u0000\u0000\u0000\u021b\u021e\u0001\u0000"+
		"\u0000\u0000\u021c\u021a\u0001\u0000\u0000\u0000\u021c\u021d\u0001\u0000"+
		"\u0000\u0000\u021d\u021f\u0001\u0000\u0000\u0000\u021e\u021c\u0001\u0000"+
		"\u0000\u0000\u021f\u0221\u0005\u0002\u0000\u0000\u0220\u0217\u0001\u0000"+
		"\u0000\u0000\u0221\u0224\u0001\u0000\u0000\u0000\u0222\u0220\u0001\u0000"+
		"\u0000\u0000\u0222\u0223\u0001\u0000\u0000\u0000\u0223\u0225\u0001\u0000"+
		"\u0000\u0000\u0224\u0222\u0001\u0000\u0000\u0000\u0225\u0226\u0003D\""+
		"\u0000\u0226C\u0001\u0000\u0000\u0000\u0227\u0228\u0005\u0001\u0000\u0000"+
		"\u0228\u022c\u00052\u0000\u0000\u0229\u022b\u0003\u0004\u0002\u0000\u022a"+
		"\u0229\u0001\u0000\u0000\u0000\u022b\u022e\u0001\u0000\u0000\u0000\u022c"+
		"\u022a\u0001\u0000\u0000\u0000\u022c\u022d\u0001\u0000\u0000\u0000\u022d"+
		"\u022f\u0001\u0000\u0000\u0000\u022e\u022c\u0001\u0000\u0000\u0000\u022f"+
		"\u0237\u0005\u0002\u0000\u0000\u0230\u0231\u0005\u0001\u0000\u0000\u0231"+
		"\u0232\u00052\u0000\u0000\u0232\u0233\u0003\u0018\f\u0000\u0233\u0234"+
		"\u0003\u0004\u0002\u0000\u0234\u0235\u0005\u0002\u0000\u0000\u0235\u0237"+
		"\u0001\u0000\u0000\u0000\u0236\u0227\u0001\u0000\u0000\u0000\u0236\u0230"+
		"\u0001\u0000\u0000\u0000\u0237\u023a\u0001\u0000\u0000\u0000\u0238\u0236"+
		"\u0001\u0000\u0000\u0000\u0238\u0239\u0001\u0000\u0000\u0000\u0239\u023b"+
		"\u0001\u0000\u0000\u0000\u023a\u0238\u0001\u0000\u0000\u0000\u023b\u023c"+
		"\u00034\u001a\u0000\u023cE\u0001\u0000\u0000\u0000\u023d\u023e\u0005\u0001"+
		"\u0000\u0000\u023e\u023f\u00058\u0000\u0000\u023f\u0240\u00036\u001b\u0000"+
		"\u0240\u0241\u0005\u0002\u0000\u0000\u0241\u0244\u0001\u0000\u0000\u0000"+
		"\u0242\u0244\u0003(\u0014\u0000\u0243\u023d\u0001\u0000\u0000\u0000\u0243"+
		"\u0242\u0001\u0000\u0000\u0000\u0244G\u0001\u0000\u0000\u0000\u0245\u0246"+
		"\u0005\u0001\u0000\u0000\u0246\u0248\u00056\u0000\u0000\u0247\u0249\u0003"+
		"\u0016\u000b\u0000\u0248\u0247\u0001\u0000\u0000\u0000\u0248\u0249\u0001"+
		"\u0000\u0000\u0000\u0249\u024a\u0001\u0000\u0000\u0000\u024a\u024c\u0003"+
		"F#\u0000\u024b\u024d\u0005.\u0000\u0000\u024c\u024b\u0001\u0000\u0000"+
		"\u0000\u024c\u024d\u0001\u0000\u0000\u0000\u024d\u0251\u0001\u0000\u0000"+
		"\u0000\u024e\u0250\u0003\u0016\u000b\u0000\u024f\u024e\u0001\u0000\u0000"+
		"\u0000\u0250\u0253\u0001\u0000\u0000\u0000\u0251\u024f\u0001\u0000\u0000"+
		"\u0000\u0251\u0252\u0001\u0000\u0000\u0000\u0252\u0254\u0001\u0000\u0000"+
		"\u0000\u0253\u0251\u0001\u0000\u0000\u0000\u0254\u0255\u0005\u0002\u0000"+
		"\u0000\u0255I\u0001\u0000\u0000\u0000\u0256\u0257\u0005\u0001\u0000\u0000"+
		"\u0257\u0259\u00054\u0000\u0000\u0258\u025a\u0003\u0018\f\u0000\u0259"+
		"\u0258\u0001\u0000\u0000\u0000\u0259\u025a\u0001\u0000\u0000\u0000\u025a"+
		"\u025b\u0001\u0000\u0000\u0000\u025b\u025c\u0003L&\u0000\u025c\u025d\u0005"+
		"\u0002\u0000\u0000\u025dK\u0001\u0000\u0000\u0000\u025e\u0271\u0003\u000e"+
		"\u0007\u0000\u025f\u0260\u0003\\.\u0000\u0260\u0261\u0003\u000e\u0007"+
		"\u0000\u0261\u0271\u0001\u0000\u0000\u0000\u0262\u0263\u0003b1\u0000\u0263"+
		"\u0264\u0003L&\u0000\u0264\u0271\u0001\u0000\u0000\u0000\u0265\u0266\u0003"+
		"\u0006\u0003\u0000\u0266\u0267\u0005\u0001\u0000\u0000\u0267\u026b\u0005"+
		"6\u0000\u0000\u0268\u026a\u0003\u0016\u000b\u0000\u0269\u0268\u0001\u0000"+
		"\u0000\u0000\u026a\u026d\u0001\u0000\u0000\u0000\u026b\u0269\u0001\u0000"+
		"\u0000\u0000\u026b\u026c\u0001\u0000\u0000\u0000\u026c\u026e\u0001\u0000"+
		"\u0000\u0000\u026d\u026b\u0001\u0000\u0000\u0000\u026e\u026f\u0005\u0002"+
		"\u0000\u0000\u026f\u0271\u0001\u0000\u0000\u0000\u0270\u025e\u0001\u0000"+
		"\u0000\u0000\u0270\u025f\u0001\u0000\u0000\u0000\u0270\u0262\u0001\u0000"+
		"\u0000\u0000\u0270\u0265\u0001\u0000\u0000\u0000\u0271M\u0001\u0000\u0000"+
		"\u0000\u0272\u0273\u0005\u0001\u0000\u0000\u0273\u0275\u00057\u0000\u0000"+
		"\u0274\u0276\u0003\u0016\u000b\u0000\u0275\u0274\u0001\u0000\u0000\u0000"+
		"\u0275\u0276\u0001\u0000\u0000\u0000\u0276\u0277\u0001\u0000\u0000\u0000"+
		"\u0277\u027b\u0003F#\u0000\u0278\u027a\u0005\u0006\u0000\u0000\u0279\u0278"+
		"\u0001\u0000\u0000\u0000\u027a\u027d\u0001\u0000\u0000\u0000\u027b\u0279"+
		"\u0001\u0000\u0000\u0000\u027b\u027c\u0001\u0000\u0000\u0000\u027c\u027e"+
		"\u0001\u0000\u0000\u0000\u027d\u027b\u0001\u0000\u0000\u0000\u027e\u027f"+
		"\u0005\u0002\u0000\u0000\u027fO\u0001\u0000\u0000\u0000\u0280\u0281\u0005"+
		"\u0001\u0000\u0000\u0281\u0283\u00055\u0000\u0000\u0282\u0284\u0003\u0018"+
		"\f\u0000\u0283\u0282\u0001\u0000\u0000\u0000\u0283\u0284\u0001\u0000\u0000"+
		"\u0000\u0284\u0285\u0001\u0000\u0000\u0000\u0285\u0286\u0003R)\u0000\u0286"+
		"\u0287\u0005\u0002\u0000\u0000\u0287Q\u0001\u0000\u0000\u0000\u0288\u0299"+
		"\u0003\u0010\b\u0000\u0289\u028a\u0003\\.\u0000\u028a\u028b\u0003\u0010"+
		"\b\u0000\u028b\u0299\u0001\u0000\u0000\u0000\u028c\u028d\u0003b1\u0000"+
		"\u028d\u028e\u0003R)\u0000\u028e\u0299\u0001\u0000\u0000\u0000\u028f\u0290"+
		"\u0005\u0001\u0000\u0000\u0290\u0294\u00057\u0000\u0000\u0291\u0293\u0005"+
		"\u0006\u0000\u0000\u0292\u0291\u0001\u0000\u0000\u0000\u0293\u0296\u0001"+
		"\u0000\u0000\u0000\u0294\u0292\u0001\u0000\u0000\u0000\u0294\u0295\u0001"+
		"\u0000\u0000\u0000\u0295\u0297\u0001\u0000\u0000\u0000\u0296\u0294\u0001"+
		"\u0000\u0000\u0000\u0297\u0299\u0005\u0002\u0000\u0000\u0298\u0288\u0001"+
		"\u0000\u0000\u0000\u0298\u0289\u0001\u0000\u0000\u0000\u0298\u028c\u0001"+
		"\u0000\u0000\u0000\u0298\u028f\u0001\u0000\u0000\u0000\u0299S\u0001\u0000"+
		"\u0000\u0000\u029a\u029b\u0005\u0001\u0000\u0000\u029b\u029d\u00053\u0000"+
		"\u0000\u029c\u029e\u0003\u0018\f\u0000\u029d\u029c\u0001\u0000\u0000\u0000"+
		"\u029d\u029e\u0001\u0000\u0000\u0000\u029e\u029f\u0001\u0000\u0000\u0000"+
		"\u029f\u02a0\u0003V+\u0000\u02a0\u02a1\u0005\u0002\u0000\u0000\u02a1U"+
		"\u0001\u0000\u0000\u0000\u02a2\u02a3\u0003\b\u0004\u0000\u02a3\u02a4\u0003"+
		"6\u001b\u0000\u02a4\u02ac\u0001\u0000\u0000\u0000\u02a5\u02a6\u0003\\"+
		".\u0000\u02a6\u02a7\u0003\b\u0004\u0000\u02a7\u02ac\u0001\u0000\u0000"+
		"\u0000\u02a8\u02a9\u0003b1\u0000\u02a9\u02aa\u0003V+\u0000\u02aa\u02ac"+
		"\u0001\u0000\u0000\u0000\u02ab\u02a2\u0001\u0000\u0000\u0000\u02ab\u02a5"+
		"\u0001\u0000\u0000\u0000\u02ab\u02a8\u0001\u0000\u0000\u0000\u02acW\u0001"+
		"\u0000\u0000\u0000\u02ad\u02ae\u0005\u0001\u0000\u0000\u02ae\u02b0\u0005"+
		".\u0000\u0000\u02af\u02b1\u0003\u0018\f\u0000\u02b0\u02af\u0001\u0000"+
		"\u0000\u0000\u02b0\u02b1\u0001\u0000\u0000\u0000\u02b1\u02b2\u0001\u0000"+
		"\u0000\u0000\u02b2\u02b3\u0003\u0012\t\u0000\u02b3\u02b4\u0005\u0002\u0000"+
		"\u0000\u02b4\u02d6\u0001\u0000\u0000\u0000\u02b5\u02b6\u0005\u0001\u0000"+
		"\u0000\u02b6\u02b8\u0005.\u0000\u0000\u02b7\u02b9\u0003\u0018\f\u0000"+
		"\u02b8\u02b7\u0001\u0000\u0000\u0000\u02b8\u02b9\u0001\u0000\u0000\u0000"+
		"\u02b9\u02ba\u0001\u0000\u0000\u0000\u02ba\u02bb\u0003\f\u0006\u0000\u02bb"+
		"\u02bc\u0005\u0002\u0000\u0000\u02bc\u02d6\u0001\u0000\u0000\u0000\u02bd"+
		"\u02be\u0005\u0001\u0000\u0000\u02be\u02c0\u00054\u0000\u0000\u02bf\u02c1"+
		"\u0003\u0018\f\u0000\u02c0\u02bf\u0001\u0000\u0000\u0000\u02c0\u02c1\u0001"+
		"\u0000\u0000\u0000\u02c1\u02c2\u0001\u0000\u0000\u0000\u02c2\u02c3\u0003"+
		"\u000e\u0007\u0000\u02c3\u02c4\u0005\u0002\u0000\u0000\u02c4\u02d6\u0001"+
		"\u0000\u0000\u0000\u02c5\u02c6\u0005\u0001\u0000\u0000\u02c6\u02c8\u0005"+
		"5\u0000\u0000\u02c7\u02c9\u0003\u0018\f\u0000\u02c8\u02c7\u0001\u0000"+
		"\u0000\u0000\u02c8\u02c9\u0001\u0000\u0000\u0000\u02c9\u02ca\u0001\u0000"+
		"\u0000\u0000\u02ca\u02cb\u0003\u0010\b\u0000\u02cb\u02cc\u0005\u0002\u0000"+
		"\u0000\u02cc\u02d6\u0001\u0000\u0000\u0000\u02cd\u02ce\u0005\u0001\u0000"+
		"\u0000\u02ce\u02d0\u00053\u0000\u0000\u02cf\u02d1\u0003\u0018\f\u0000"+
		"\u02d0\u02cf\u0001\u0000\u0000\u0000\u02d0\u02d1\u0001\u0000\u0000\u0000"+
		"\u02d1\u02d2\u0001\u0000\u0000\u0000\u02d2\u02d3\u0003\b\u0004\u0000\u02d3"+
		"\u02d4\u0005\u0002\u0000\u0000\u02d4\u02d6\u0001\u0000\u0000\u0000\u02d5"+
		"\u02ad\u0001\u0000\u0000\u0000\u02d5\u02b5\u0001\u0000\u0000\u0000\u02d5"+
		"\u02bd\u0001\u0000\u0000\u0000\u02d5\u02c5\u0001\u0000\u0000\u0000\u02d5"+
		"\u02cd\u0001\u0000\u0000\u0000\u02d6Y\u0001\u0000\u0000\u0000\u02d7\u02d8"+
		"\u0005\u0001\u0000\u0000\u02d8\u02d9\u00059\u0000\u0000\u02d9\u02da\u0003"+
		"\u0002\u0001\u0000\u02da\u02db\u0003\u0002\u0001\u0000\u02db\u02dc\u0003"+
		"X,\u0000\u02dc\u02dd\u0005\u0002\u0000\u0000\u02dd[\u0001\u0000\u0000"+
		"\u0000\u02de\u02df\u0005\u0001\u0000\u0000\u02df\u02e0\u00059\u0000\u0000"+
		"\u02e0\u02e1\u0003\u0002\u0001\u0000\u02e1\u02e2\u0003\u0002\u0001\u0000"+
		"\u02e2\u02e3\u0005\u0002\u0000\u0000\u02e3]\u0001\u0000\u0000\u0000\u02e4"+
		"\u02e5\u0005\u0001\u0000\u0000\u02e5\u02e6\u0005.\u0000\u0000\u02e6\u02e7"+
		"\u0003\u0016\u000b\u0000\u02e7\u02e8\u0005\u0002\u0000\u0000\u02e8\u02f9"+
		"\u0001\u0000\u0000\u0000\u02e9\u02ea\u0005\u0001\u0000\u0000\u02ea\u02eb"+
		"\u00054\u0000\u0000\u02eb\u02ec\u0003\u0016\u000b\u0000\u02ec\u02ed\u0005"+
		"\u0002\u0000\u0000\u02ed\u02f9\u0001\u0000\u0000\u0000\u02ee\u02ef\u0005"+
		"\u0001\u0000\u0000\u02ef\u02f0\u00055\u0000\u0000\u02f0\u02f1\u0003\u0016"+
		"\u000b\u0000\u02f1\u02f2\u0005\u0002\u0000\u0000\u02f2\u02f9\u0001\u0000"+
		"\u0000\u0000\u02f3\u02f4\u0005\u0001\u0000\u0000\u02f4\u02f5\u00053\u0000"+
		"\u0000\u02f5\u02f6\u0003\u0016\u000b\u0000\u02f6\u02f7\u0005\u0002\u0000"+
		"\u0000\u02f7\u02f9\u0001\u0000\u0000\u0000\u02f8\u02e4\u0001\u0000\u0000"+
		"\u0000\u02f8\u02e9\u0001\u0000\u0000\u0000\u02f8\u02ee\u0001\u0000\u0000"+
		"\u0000\u02f8\u02f3\u0001\u0000\u0000\u0000\u02f9_\u0001\u0000\u0000\u0000"+
		"\u02fa\u02fb\u0005\u0001\u0000\u0000\u02fb\u02fc\u0005:\u0000\u0000\u02fc"+
		"\u02fd\u0003\u0002\u0001\u0000\u02fd\u02fe\u0003^/\u0000\u02fe\u02ff\u0005"+
		"\u0002\u0000\u0000\u02ffa\u0001\u0000\u0000\u0000\u0300\u0301\u0005\u0001"+
		"\u0000\u0000\u0301\u0302\u0005:\u0000\u0000\u0302\u0303\u0003\u0002\u0001"+
		"\u0000\u0303\u0304\u0005\u0002\u0000\u0000\u0304c\u0001\u0000\u0000\u0000"+
		"\u0305\u0306\u0003\n\u0005\u0000\u0306e\u0001\u0000\u0000\u0000\u0307"+
		"\u0308\u0005\u0001\u0000\u0000\u0308\u030a\u0005-\u0000\u0000\u0309\u030b"+
		"\u0003\u0018\f\u0000\u030a\u0309\u0001\u0000\u0000\u0000\u030a\u030b\u0001"+
		"\u0000\u0000\u0000\u030b\u030c\u0001\u0000\u0000\u0000\u030c\u030d\u0003"+
		"d2\u0000\u030d\u030e\u0005\u0002\u0000\u0000\u030eg\u0001\u0000\u0000"+
		"\u0000\u030f\u0310\u0005\u0001\u0000\u0000\u0310\u0311\u0005/\u0000\u0000"+
		"\u0311\u0312\u0003\u0016\u000b\u0000\u0312\u0313\u0005\u0002\u0000\u0000"+
		"\u0313i\u0001\u0000\u0000\u0000\u0314\u031f\u0003f3\u0000\u0315\u031f"+
		"\u0003T*\u0000\u0316\u031f\u0003J%\u0000\u0317\u031f\u0003P(\u0000\u0318"+
		"\u031f\u00038\u001c\u0000\u0319\u031f\u0003H$\u0000\u031a\u031f\u0003"+
		"N\'\u0000\u031b\u031f\u0003h4\u0000\u031c\u031f\u0003Z-\u0000\u031d\u031f"+
		"\u0003`0\u0000\u031e\u0314\u0001\u0000\u0000\u0000\u031e\u0315\u0001\u0000"+
		"\u0000\u0000\u031e\u0316\u0001\u0000\u0000\u0000\u031e\u0317\u0001\u0000"+
		"\u0000\u0000\u031e\u0318\u0001\u0000\u0000\u0000\u031e\u0319\u0001\u0000"+
		"\u0000\u0000\u031e\u031a\u0001\u0000\u0000\u0000\u031e\u031b\u0001\u0000"+
		"\u0000\u0000\u031e\u031c\u0001\u0000\u0000\u0000\u031e\u031d\u0001\u0000"+
		"\u0000\u0000\u031fk\u0001\u0000\u0000\u0000\u0320\u0321\u0005\u0001\u0000"+
		"\u0000\u0321\u0323\u0005;\u0000\u0000\u0322\u0324\u0005L\u0000\u0000\u0323"+
		"\u0322\u0001\u0000\u0000\u0000\u0323\u0324\u0001\u0000\u0000\u0000\u0324"+
		"\u0328\u0001\u0000\u0000\u0000\u0325\u0327\u0003j5\u0000\u0326\u0325\u0001"+
		"\u0000\u0000\u0000\u0327\u032a\u0001\u0000\u0000\u0000\u0328\u0326\u0001"+
		"\u0000\u0000\u0000\u0328\u0329\u0001\u0000\u0000\u0000\u0329\u032b\u0001"+
		"\u0000\u0000\u0000\u032a\u0328\u0001\u0000\u0000\u0000\u032b\u032c\u0005"+
		"\u0002\u0000\u0000\u032cm\u0001\u0000\u0000\u0000\u032d\u033c\u0003l6"+
		"\u0000\u032e\u032f\u0005\u0001\u0000\u0000\u032f\u0331\u0005;\u0000\u0000"+
		"\u0330\u0332\u0005L\u0000\u0000\u0331\u0330\u0001\u0000\u0000\u0000\u0331"+
		"\u0332\u0001\u0000\u0000\u0000\u0332\u0333\u0001\u0000\u0000\u0000\u0333"+
		"\u0337\u0007\u0004\u0000\u0000\u0334\u0336\u0005\u0006\u0000\u0000\u0335"+
		"\u0334\u0001\u0000\u0000\u0000\u0336\u0339\u0001\u0000\u0000\u0000\u0337"+
		"\u0335\u0001\u0000\u0000\u0000\u0337\u0338\u0001\u0000\u0000\u0000\u0338"+
		"\u033a\u0001\u0000\u0000\u0000\u0339\u0337\u0001\u0000\u0000\u0000\u033a"+
		"\u033c\u0005\u0002\u0000\u0000\u033b\u032d\u0001\u0000\u0000\u0000\u033b"+
		"\u032e\u0001\u0000\u0000\u0000\u033co\u0001\u0000\u0000\u0000\u033d\u033e"+
		"\u0005\u0001\u0000\u0000\u033e\u0340\u0005@\u0000\u0000\u033f\u0341\u0005"+
		"L\u0000\u0000\u0340\u033f\u0001\u0000\u0000\u0000\u0340\u0341\u0001\u0000"+
		"\u0000\u0000\u0341\u0342\u0001\u0000\u0000\u0000\u0342\u0343\u0003\u0002"+
		"\u0001\u0000\u0343\u0344\u0003z=\u0000\u0344\u0345\u0005\u0002\u0000\u0000"+
		"\u0345\u034f\u0001\u0000\u0000\u0000\u0346\u0347\u0005\u0001\u0000\u0000"+
		"\u0347\u0349\u0005A\u0000\u0000\u0348\u034a\u0005L\u0000\u0000\u0349\u0348"+
		"\u0001\u0000\u0000\u0000\u0349\u034a\u0001\u0000\u0000\u0000\u034a\u034b"+
		"\u0001\u0000\u0000\u0000\u034b\u034c\u0003\u0002\u0001\u0000\u034c\u034d"+
		"\u0005\u0002\u0000\u0000\u034d\u034f\u0001\u0000\u0000\u0000\u034e\u033d"+
		"\u0001\u0000\u0000\u0000\u034e\u0346\u0001\u0000\u0000\u0000\u034fq\u0001"+
		"\u0000\u0000\u0000\u0350\u0351\u0005\u0001\u0000\u0000\u0351\u0352\u0005"+
		"B\u0000\u0000\u0352\u0353\u0003n7\u0000\u0353\u0354\u0005\u0006\u0000"+
		"\u0000\u0354\u0355\u0005\u0002\u0000\u0000\u0355\u0385\u0001\u0000\u0000"+
		"\u0000\u0356\u0357\u0005\u0001\u0000\u0000\u0357\u0358\u0005C\u0000\u0000"+
		"\u0358\u0359\u0003n7\u0000\u0359\u035a\u0005\u0006\u0000\u0000\u035a\u035b"+
		"\u0005\u0002\u0000\u0000\u035b\u0385\u0001\u0000\u0000\u0000\u035c\u035d"+
		"\u0005\u0001\u0000\u0000\u035d\u035e\u0005D\u0000\u0000\u035e\u035f\u0003"+
		"n7\u0000\u035f\u0360\u0005\u0006\u0000\u0000\u0360\u0361\u0005\u0002\u0000"+
		"\u0000\u0361\u0385\u0001\u0000\u0000\u0000\u0362\u0363\u0005\u0001\u0000"+
		"\u0000\u0363\u0364\u0005H\u0000\u0000\u0364\u0365\u0003n7\u0000\u0365"+
		"\u0366\u0005\u0006\u0000\u0000\u0366\u0367\u0005\u0002\u0000\u0000\u0367"+
		"\u0385\u0001\u0000\u0000\u0000\u0368\u0369\u0005\u0001\u0000\u0000\u0369"+
		"\u036a\u0005E\u0000\u0000\u036a\u036b\u0003p8\u0000\u036b\u036c\u0003"+
		"z=\u0000\u036c\u036d\u0005\u0002\u0000\u0000\u036d\u0385\u0001\u0000\u0000"+
		"\u0000\u036e\u036f\u0005\u0001\u0000\u0000\u036f\u0370\u0005F\u0000\u0000"+
		"\u0370\u0371\u0003p8\u0000\u0371\u0372\u0005\u0002\u0000\u0000\u0372\u0385"+
		"\u0001\u0000\u0000\u0000\u0373\u0374\u0005\u0001\u0000\u0000\u0374\u0375"+
		"\u0005G\u0000\u0000\u0375\u0376\u0003p8\u0000\u0376\u0377\u0005\u0002"+
		"\u0000\u0000\u0377\u0385\u0001\u0000\u0000\u0000\u0378\u0379\u0005\u0001"+
		"\u0000\u0000\u0379\u037a\u0005H\u0000\u0000\u037a\u037b\u0003p8\u0000"+
		"\u037b\u037c\u0005\u0006\u0000\u0000\u037c\u037d\u0005\u0002\u0000\u0000"+
		"\u037d\u0385\u0001\u0000\u0000\u0000\u037e\u037f\u0005\u0001\u0000\u0000"+
		"\u037f\u0380\u0005I\u0000\u0000\u0380\u0381\u0003p8\u0000\u0381\u0382"+
		"\u0005\u0006\u0000\u0000\u0382\u0383\u0005\u0002\u0000\u0000\u0383\u0385"+
		"\u0001\u0000\u0000\u0000\u0384\u0350\u0001\u0000\u0000\u0000\u0384\u0356"+
		"\u0001\u0000\u0000\u0000\u0384\u035c\u0001\u0000\u0000\u0000\u0384\u0362"+
		"\u0001\u0000\u0000\u0000\u0384\u0368\u0001\u0000\u0000\u0000\u0384\u036e"+
		"\u0001\u0000\u0000\u0000\u0384\u0373\u0001\u0000\u0000\u0000\u0384\u0378"+
		"\u0001\u0000\u0000\u0000\u0384\u037e\u0001\u0000\u0000\u0000\u0385s\u0001"+
		"\u0000\u0000\u0000\u0386\u0393\u0003p8\u0000\u0387\u0393\u0003r9\u0000"+
		"\u0388\u0393\u0003n7\u0000\u0389\u038a\u0005\u0001\u0000\u0000\u038a\u038b"+
		"\u0005?\u0000\u0000\u038b\u038d\u0003\u0002\u0001\u0000\u038c\u038e\u0005"+
		"L\u0000\u0000\u038d\u038c\u0001\u0000\u0000\u0000\u038d\u038e\u0001\u0000"+
		"\u0000\u0000\u038e\u038f\u0001\u0000\u0000\u0000\u038f\u0390\u0005\u0002"+
		"\u0000\u0000\u0390\u0393\u0001\u0000\u0000\u0000\u0391\u0393\u0003v;\u0000"+
		"\u0392\u0386\u0001\u0000\u0000\u0000\u0392\u0387\u0001\u0000\u0000\u0000"+
		"\u0392\u0388\u0001\u0000\u0000\u0000\u0392\u0389\u0001\u0000\u0000\u0000"+
		"\u0392\u0391\u0001\u0000\u0000\u0000\u0393u\u0001\u0000\u0000\u0000\u0394"+
		"\u0395\u0005\u0001\u0000\u0000\u0395\u0397\u0005>\u0000\u0000\u0396\u0398"+
		"\u0005L\u0000\u0000\u0397\u0396\u0001\u0000\u0000\u0000\u0397\u0398\u0001"+
		"\u0000\u0000\u0000\u0398\u039c\u0001\u0000\u0000\u0000\u0399\u039b\u0003"+
		"t:\u0000\u039a\u0399\u0001\u0000\u0000\u0000\u039b\u039e\u0001\u0000\u0000"+
		"\u0000\u039c\u039a\u0001\u0000\u0000\u0000\u039c\u039d\u0001\u0000\u0000"+
		"\u0000\u039d\u039f\u0001\u0000\u0000\u0000\u039e\u039c\u0001\u0000\u0000"+
		"\u0000\u039f\u03b5\u0005\u0002\u0000\u0000\u03a0\u03a1\u0005\u0001\u0000"+
		"\u0000\u03a1\u03a3\u0005J\u0000\u0000\u03a2\u03a4\u0005L\u0000\u0000\u03a3"+
		"\u03a2\u0001\u0000\u0000\u0000\u03a3\u03a4\u0001\u0000\u0000\u0000\u03a4"+
		"\u03a5\u0001\u0000\u0000\u0000\u03a5\u03a6\u0005\u0006\u0000\u0000\u03a6"+
		"\u03b5\u0005\u0002\u0000\u0000\u03a7\u03a8\u0005\u0001\u0000\u0000\u03a8"+
		"\u03aa\u0005K\u0000\u0000\u03a9\u03ab\u0005L\u0000\u0000\u03aa\u03a9\u0001"+
		"\u0000\u0000\u0000\u03aa\u03ab\u0001\u0000\u0000\u0000\u03ab\u03ac\u0001"+
		"\u0000\u0000\u0000\u03ac\u03ad\u0005\u0006\u0000\u0000\u03ad\u03b5\u0005"+
		"\u0002\u0000\u0000\u03ae\u03af\u0005\u0001\u0000\u0000\u03af\u03b1\u0005"+
		"K\u0000\u0000\u03b0\u03b2\u0005L\u0000\u0000\u03b1\u03b0\u0001\u0000\u0000"+
		"\u0000\u03b1\u03b2\u0001\u0000\u0000\u0000\u03b2\u03b3\u0001\u0000\u0000"+
		"\u0000\u03b3\u03b5\u0005\u0002\u0000\u0000\u03b4\u0394\u0001\u0000\u0000"+
		"\u0000\u03b4\u03a0\u0001\u0000\u0000\u0000\u03b4\u03a7\u0001\u0000\u0000"+
		"\u0000\u03b4\u03ae\u0001\u0000\u0000\u0000\u03b5w\u0001\u0000\u0000\u0000"+
		"\u03b6\u03b7\u0005\u0001\u0000\u0000\u03b7\u03b8\u0005\b\u0000\u0000\u03b8"+
		"\u03b9\u0003\u0014\n\u0000\u03b9\u03ba\u0005\u0002\u0000\u0000\u03bay"+
		"\u0001\u0000\u0000\u0000\u03bb\u03bd\u0003x<\u0000\u03bc\u03bb\u0001\u0000"+
		"\u0000\u0000\u03bd\u03c0\u0001\u0000\u0000\u0000\u03be\u03bc\u0001\u0000"+
		"\u0000\u0000\u03be\u03bf\u0001\u0000\u0000\u0000\u03bf{\u0001\u0000\u0000"+
		"\u0000\u03c0\u03be\u0001\u0000\u0000\u0000\u03c1\u03c3\u0003t:\u0000\u03c2"+
		"\u03c1\u0001\u0000\u0000\u0000\u03c3\u03c6\u0001\u0000\u0000\u0000\u03c4"+
		"\u03c2\u0001\u0000\u0000\u0000\u03c4\u03c5\u0001\u0000\u0000\u0000\u03c5"+
		"\u03c7\u0001\u0000\u0000\u0000\u03c6\u03c4\u0001\u0000\u0000\u0000\u03c7"+
		"\u03d0\u0005\u0000\u0000\u0001\u03c8\u03ca\u0003j5\u0000\u03c9\u03c8\u0001"+
		"\u0000\u0000\u0000\u03ca\u03cb\u0001\u0000\u0000\u0000\u03cb\u03c9\u0001"+
		"\u0000\u0000\u0000\u03cb\u03cc\u0001\u0000\u0000\u0000\u03cc\u03cd\u0001"+
		"\u0000\u0000\u0000\u03cd\u03ce\u0005\u0000\u0000\u0001\u03ce\u03d0\u0001"+
		"\u0000\u0000\u0000\u03cf\u03c4\u0001\u0000\u0000\u0000\u03cf\u03c9\u0001"+
		"\u0000\u0000\u0000\u03d0}\u0001\u0000\u0000\u0000\u03d1\u03d2\u0003l6"+
		"\u0000\u03d2\u03d3\u0005\u0000\u0000\u0001\u03d3\u03dc\u0001\u0000\u0000"+
		"\u0000\u03d4\u03d6\u0003j5\u0000\u03d5\u03d4\u0001\u0000\u0000\u0000\u03d6"+
		"\u03d9\u0001\u0000\u0000\u0000\u03d7\u03d5\u0001\u0000\u0000\u0000\u03d7"+
		"\u03d8\u0001\u0000\u0000\u0000\u03d8\u03da\u0001\u0000\u0000\u0000\u03d9"+
		"\u03d7\u0001\u0000\u0000\u0000\u03da\u03dc\u0005\u0000\u0000\u0001\u03db"+
		"\u03d1\u0001\u0000\u0000\u0000\u03db\u03d7\u0001\u0000\u0000\u0000\u03dc"+
		"\u007f\u0001\u0000\u0000\u0000q\u008e\u009a\u00a1\u00a8\u00ad\u00b2\u00b8"+
		"\u00c8\u00cf\u00d9\u00e0\u00e3\u00f2\u00f5\u00f8\u00fc\u00ff\u0102\u0106"+
		"\u010a\u010e\u0111\u0115\u011e\u012a\u0130\u0138\u013e\u0143\u0148\u014c"+
		"\u0151\u0154\u0158\u015a\u0162\u016e\u0173\u0178\u017d\u0180\u0183\u018c"+
		"\u0192\u019c\u01a2\u01a8\u01b1\u01bd\u01bf\u01c4\u01cc\u01d2\u01d7\u01de"+
		"\u01e5\u01ef\u01f8\u01fe\u0206\u0210\u0212\u021c\u0222\u022c\u0236\u0238"+
		"\u0243\u0248\u024c\u0251\u0259\u026b\u0270\u0275\u027b\u0283\u0294\u0298"+
		"\u029d\u02ab\u02b0\u02b8\u02c0\u02c8\u02d0\u02d5\u02f8\u030a\u031e\u0323"+
		"\u0328\u0331\u0337\u033b\u0340\u0349\u034e\u0384\u038d\u0392\u0397\u039c"+
		"\u03a3\u03aa\u03b1\u03b4\u03be\u03c4\u03cb\u03cf\u03d7\u03db";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}