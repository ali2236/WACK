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
		UNARY=36, BINARY=37, TEST=38, COMPARE=39, CONVERT=40, MEMORY_INIT=41, 
		MEMORY_SIZE=42, MEMORY_GROW=43, MEMORY_COPY=44, MEMORY_FILL=45, DATA_DROP=46, 
		SHARED=47, UNSHARED=48, ATOMICOP=49, ATOMIC_FENCE=50, ATOMIC_STORE=51, 
		ATOMIC_LOAD=52, ATOMIC_WAIT=53, ATOMIC_NOTIFY=54, ATOMIC_CMPXCHG=55, ATOMIC_OPR=56, 
		TYPE=57, FUNC=58, START_=59, PARAM=60, RESULT=61, LOCAL=62, GLOBAL=63, 
		TABLE=64, MEMORY=65, ELEM=66, DATA=67, OFFSET=68, IMPORT=69, EXPORT=70, 
		MODULE=71, BIN=72, QUOTE=73, SCRIPT=74, REGISTER=75, INVOKE=76, GET=77, 
		ASSERT_MALFORMED=78, ASSERT_INVALID=79, ASSERT_UNLINKABLE=80, ASSERT_RETURN=81, 
		ASSERT_RETURN_CANONICAL_NAN=82, ASSERT_RETURN_ARITHMETIC_NAN=83, ASSERT_TRAP=84, 
		ASSERT_EXHAUSTION=85, INPUT=86, OUTPUT=87, VAR=88, SPACE=89, COMMENT=90;
	public static final int
		RULE_value = 0, RULE_name = 1, RULE_value_type = 2, RULE_elem_type = 3, 
		RULE_global_type = 4, RULE_def_type = 5, RULE_func_type = 6, RULE_table_type = 7, 
		RULE_memory_type = 8, RULE_share = 9, RULE_type_use = 10, RULE_literal = 11, 
		RULE_var_ = 12, RULE_bind_var = 13, RULE_instr = 14, RULE_plain_instr = 15, 
		RULE_select_type = 16, RULE_call_instr_params = 17, RULE_call_instr_params_result = 18, 
		RULE_block_instr = 19, RULE_block_type = 20, RULE_block = 21, RULE_expr = 22, 
		RULE_expr1 = 23, RULE_call_expr_type = 24, RULE_call_expr_params = 25, 
		RULE_call_expr_results = 26, RULE_if_block = 27, RULE_instr_list = 28, 
		RULE_const_expr = 29, RULE_func_ = 30, RULE_func_fields = 31, RULE_func_fields_import = 32, 
		RULE_func_fields_import_result = 33, RULE_func_fields_body = 34, RULE_func_result_body = 35, 
		RULE_func_body = 36, RULE_offset = 37, RULE_elem = 38, RULE_function_list = 39, 
		RULE_table_bind = 40, RULE_table = 41, RULE_table_fields = 42, RULE_data = 43, 
		RULE_memory = 44, RULE_memory_fields = 45, RULE_sglobal = 46, RULE_global_fields = 47, 
		RULE_import_desc = 48, RULE_simport = 49, RULE_inline_import = 50, RULE_export_desc = 51, 
		RULE_export_ = 52, RULE_inline_export = 53, RULE_type_ = 54, RULE_type_def = 55, 
		RULE_start_ = 56, RULE_module_field = 57, RULE_module_ = 58, RULE_script_module = 59, 
		RULE_action_ = 60, RULE_assertion = 61, RULE_cmd = 62, RULE_meta = 63, 
		RULE_wconst = 64, RULE_const_list = 65, RULE_script = 66, RULE_module = 67;
	private static String[] makeRuleNames() {
		return new String[] {
			"value", "name", "value_type", "elem_type", "global_type", "def_type", 
			"func_type", "table_type", "memory_type", "share", "type_use", "literal", 
			"var_", "bind_var", "instr", "plain_instr", "select_type", "call_instr_params", 
			"call_instr_params_result", "block_instr", "block_type", "block", "expr", 
			"expr1", "call_expr_type", "call_expr_params", "call_expr_results", "if_block", 
			"instr_list", "const_expr", "func_", "func_fields", "func_fields_import", 
			"func_fields_import_result", "func_fields_body", "func_result_body", 
			"func_body", "offset", "elem", "function_list", "table_bind", "table", 
			"table_fields", "data", "memory", "memory_fields", "sglobal", "global_fields", 
			"import_desc", "simport", "inline_import", "export_desc", "export_", 
			"inline_export", "type_", "type_def", "start_", "module_field", "module_", 
			"script_module", "action_", "assertion", "cmd", "meta", "wconst", "const_list", 
			"script", "module"
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
			null, null, null, null, null, "'memory.init'", "'memory.size'", "'memory.grow'", 
			"'memory.copy'", "'memory.fill'", "'data.drop'", "'shared'", "'unshared'", 
			null, "'atomic.fence'", null, null, null, "'memory.atomic.notify'", null, 
			null, "'type'", "'func'", "'start'", "'param'", "'result'", "'local'", 
			"'global'", "'table'", "'memory'", "'elem'", "'data'", "'offset'", "'import'", 
			"'export'", "'module'", "'binary'", "'quote'", "'script'", "'register'", 
			"'invoke'", "'get'", "'assert_malformed'", "'assert_invalid'", "'assert_unlinkable'", 
			"'assert_return'", "'assert_return_canonical_nan'", "'assert_return_arithmetic_nan'", 
			"'assert_trap'", "'assert_exhaustion'", "'input'", "'output'"
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
			"BINARY", "TEST", "COMPARE", "CONVERT", "MEMORY_INIT", "MEMORY_SIZE", 
			"MEMORY_GROW", "MEMORY_COPY", "MEMORY_FILL", "DATA_DROP", "SHARED", "UNSHARED", 
			"ATOMICOP", "ATOMIC_FENCE", "ATOMIC_STORE", "ATOMIC_LOAD", "ATOMIC_WAIT", 
			"ATOMIC_NOTIFY", "ATOMIC_CMPXCHG", "ATOMIC_OPR", "TYPE", "FUNC", "START_", 
			"PARAM", "RESULT", "LOCAL", "GLOBAL", "TABLE", "MEMORY", "ELEM", "DATA", 
			"OFFSET", "IMPORT", "EXPORT", "MODULE", "BIN", "QUOTE", "SCRIPT", "REGISTER", 
			"INVOKE", "GET", "ASSERT_MALFORMED", "ASSERT_INVALID", "ASSERT_UNLINKABLE", 
			"ASSERT_RETURN", "ASSERT_RETURN_CANONICAL_NAN", "ASSERT_RETURN_ARITHMETIC_NAN", 
			"ASSERT_TRAP", "ASSERT_EXHAUSTION", "INPUT", "OUTPUT", "VAR", "SPACE", 
			"COMMENT"
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
			setState(136);
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
			setState(138);
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
			setState(140);
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
			setState(142);
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
			setState(150);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VALUE_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				value_type();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				match(LPAR);
				setState(146);
				match(MUT);
				setState(147);
				value_type();
				setState(148);
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
			setState(152);
			match(LPAR);
			setState(153);
			match(FUNC);
			setState(154);
			func_type();
			setState(155);
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
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(157);
				match(LPAR);
				setState(176);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(158);
					match(RESULT);
					setState(162);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(159);
						value_type();
						}
						}
						setState(164);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(165);
					match(PARAM);
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(166);
						value_type();
						}
						}
						setState(171);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 3:
					{
					setState(172);
					match(PARAM);
					setState(173);
					bind_var();
					setState(174);
					value_type();
					}
					break;
				}
				setState(178);
				match(RPAR);
				}
				}
				setState(183);
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
			setState(184);
			match(NAT);
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAT) {
				{
				setState(185);
				match(NAT);
				}
			}

			setState(188);
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
		public ShareContext share() {
			return getRuleContext(ShareContext.class,0);
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
			setState(190);
			match(NAT);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAT) {
				{
				setState(191);
				match(NAT);
				}
			}

			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARED || _la==UNSHARED) {
				{
				setState(194);
				share();
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
	public static class ShareContext extends ParserRuleContext {
		public TerminalNode SHARED() { return getToken(WatParser.SHARED, 0); }
		public TerminalNode UNSHARED() { return getToken(WatParser.UNSHARED, 0); }
		public ShareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_share; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterShare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitShare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitShare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShareContext share() throws RecognitionException {
		ShareContext _localctx = new ShareContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_share);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_la = _input.LA(1);
			if ( !(_la==SHARED || _la==UNSHARED) ) {
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
		enterRule(_localctx, 20, RULE_type_use);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(LPAR);
			setState(200);
			match(TYPE);
			setState(201);
			var_();
			setState(202);
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
		enterRule(_localctx, 22, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
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
		enterRule(_localctx, 24, RULE_var_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
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
		enterRule(_localctx, 26, RULE_bind_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
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
		enterRule(_localctx, 28, RULE_instr);
		try {
			setState(213);
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
			case MEMORY_INIT:
			case MEMORY_SIZE:
			case MEMORY_GROW:
			case MEMORY_COPY:
			case MEMORY_FILL:
			case DATA_DROP:
			case ATOMIC_FENCE:
			case ATOMIC_STORE:
			case ATOMIC_LOAD:
			case ATOMIC_WAIT:
			case ATOMIC_NOTIFY:
			case ATOMIC_CMPXCHG:
			case ATOMIC_OPR:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				plain_instr();
				}
				break;
			case BLOCK:
			case LOOP:
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(211);
				block_instr();
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
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
		public Type_useContext type_use() {
			return getRuleContext(Type_useContext.class,0);
		}
		public Call_instr_paramsContext call_instr_params() {
			return getRuleContext(Call_instr_paramsContext.class,0);
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
		public TerminalNode MEMORY_INIT() { return getToken(WatParser.MEMORY_INIT, 0); }
		public TerminalNode MEMORY_SIZE() { return getToken(WatParser.MEMORY_SIZE, 0); }
		public TerminalNode MEMORY_GROW() { return getToken(WatParser.MEMORY_GROW, 0); }
		public TerminalNode MEMORY_COPY() { return getToken(WatParser.MEMORY_COPY, 0); }
		public TerminalNode MEMORY_FILL() { return getToken(WatParser.MEMORY_FILL, 0); }
		public TerminalNode DATA_DROP() { return getToken(WatParser.DATA_DROP, 0); }
		public TerminalNode ATOMIC_FENCE() { return getToken(WatParser.ATOMIC_FENCE, 0); }
		public TerminalNode ATOMIC_LOAD() { return getToken(WatParser.ATOMIC_LOAD, 0); }
		public TerminalNode ATOMIC_STORE() { return getToken(WatParser.ATOMIC_STORE, 0); }
		public TerminalNode ATOMIC_WAIT() { return getToken(WatParser.ATOMIC_WAIT, 0); }
		public TerminalNode ATOMIC_NOTIFY() { return getToken(WatParser.ATOMIC_NOTIFY, 0); }
		public TerminalNode ATOMIC_CMPXCHG() { return getToken(WatParser.ATOMIC_CMPXCHG, 0); }
		public TerminalNode ATOMIC_OPR() { return getToken(WatParser.ATOMIC_OPR, 0); }
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
		enterRule(_localctx, 30, RULE_plain_instr);
		int _la;
		try {
			setState(370);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UNREACHABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				match(UNREACHABLE);
				}
				break;
			case NOP:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(NOP);
				}
				break;
			case DROP:
				enterOuterAlt(_localctx, 3);
				{
				setState(217);
				match(DROP);
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 4);
				{
				setState(218);
				match(SELECT);
				setState(220);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(219);
					select_type();
					}
					break;
				}
				}
				break;
			case BR:
				enterOuterAlt(_localctx, 5);
				{
				setState(222);
				match(BR);
				setState(223);
				var_();
				}
				break;
			case BR_IF:
				enterOuterAlt(_localctx, 6);
				{
				setState(224);
				match(BR_IF);
				setState(225);
				var_();
				}
				break;
			case BR_TABLE:
				enterOuterAlt(_localctx, 7);
				{
				setState(226);
				match(BR_TABLE);
				setState(228); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(227);
					var_();
					}
					}
					setState(230); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NAT || _la==VAR );
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 8);
				{
				setState(232);
				match(RETURN);
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 9);
				{
				setState(233);
				match(CALL);
				setState(234);
				var_();
				}
				break;
			case CALL_INDIRECT:
				enterOuterAlt(_localctx, 10);
				{
				setState(235);
				match(CALL_INDIRECT);
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(236);
					var_();
					}
				}

				setState(239);
				type_use();
				setState(240);
				call_instr_params();
				}
				break;
			case LOCAL_GET:
				enterOuterAlt(_localctx, 11);
				{
				setState(242);
				match(LOCAL_GET);
				setState(243);
				var_();
				}
				break;
			case LOCAL_SET:
				enterOuterAlt(_localctx, 12);
				{
				setState(244);
				match(LOCAL_SET);
				setState(245);
				var_();
				}
				break;
			case LOCAL_TEE:
				enterOuterAlt(_localctx, 13);
				{
				setState(246);
				match(LOCAL_TEE);
				setState(247);
				var_();
				}
				break;
			case GLOBAL_GET:
				enterOuterAlt(_localctx, 14);
				{
				setState(248);
				match(GLOBAL_GET);
				setState(249);
				var_();
				}
				break;
			case GLOBAL_SET:
				enterOuterAlt(_localctx, 15);
				{
				setState(250);
				match(GLOBAL_SET);
				setState(251);
				var_();
				}
				break;
			case LOAD:
				enterOuterAlt(_localctx, 16);
				{
				setState(252);
				match(LOAD);
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(253);
					var_();
					}
				}

				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(256);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(259);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case STORE:
				enterOuterAlt(_localctx, 17);
				{
				setState(262);
				match(STORE);
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(263);
					var_();
					}
				}

				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(266);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(269);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case MEMORY_INIT:
				enterOuterAlt(_localctx, 18);
				{
				setState(272);
				match(MEMORY_INIT);
				setState(274);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(273);
					var_();
					}
					break;
				}
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
			case MEMORY_SIZE:
				enterOuterAlt(_localctx, 19);
				{
				setState(279);
				match(MEMORY_SIZE);
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(280);
					var_();
					}
				}

				}
				break;
			case MEMORY_GROW:
				enterOuterAlt(_localctx, 20);
				{
				setState(283);
				match(MEMORY_GROW);
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(284);
					var_();
					}
				}

				}
				break;
			case MEMORY_COPY:
				enterOuterAlt(_localctx, 21);
				{
				setState(287);
				match(MEMORY_COPY);
				setState(289);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(288);
					var_();
					}
					break;
				}
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(291);
					var_();
					}
				}

				}
				break;
			case MEMORY_FILL:
				enterOuterAlt(_localctx, 22);
				{
				setState(294);
				match(MEMORY_FILL);
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(295);
					var_();
					}
				}

				}
				break;
			case DATA_DROP:
				enterOuterAlt(_localctx, 23);
				{
				setState(298);
				match(DATA_DROP);
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(299);
					var_();
					}
				}

				}
				break;
			case ATOMIC_FENCE:
				enterOuterAlt(_localctx, 24);
				{
				setState(302);
				match(ATOMIC_FENCE);
				}
				break;
			case ATOMIC_LOAD:
				enterOuterAlt(_localctx, 25);
				{
				setState(303);
				match(ATOMIC_LOAD);
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(304);
					var_();
					}
				}

				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(307);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(310);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case ATOMIC_STORE:
				enterOuterAlt(_localctx, 26);
				{
				setState(313);
				match(ATOMIC_STORE);
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(314);
					var_();
					}
				}

				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(317);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(320);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case ATOMIC_WAIT:
				enterOuterAlt(_localctx, 27);
				{
				setState(323);
				match(ATOMIC_WAIT);
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(324);
					var_();
					}
				}

				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(327);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(330);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case ATOMIC_NOTIFY:
				enterOuterAlt(_localctx, 28);
				{
				setState(333);
				match(ATOMIC_NOTIFY);
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(334);
					var_();
					}
				}

				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(337);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(341);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(340);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case ATOMIC_CMPXCHG:
				enterOuterAlt(_localctx, 29);
				{
				setState(343);
				match(ATOMIC_CMPXCHG);
				setState(345);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(344);
					var_();
					}
				}

				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(347);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(350);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case ATOMIC_OPR:
				enterOuterAlt(_localctx, 30);
				{
				setState(353);
				match(ATOMIC_OPR);
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAT || _la==VAR) {
					{
					setState(354);
					var_();
					}
				}

				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OFFSET_EQ_NAT) {
					{
					setState(357);
					match(OFFSET_EQ_NAT);
					}
				}

				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALIGN_EQ_NAT) {
					{
					setState(360);
					match(ALIGN_EQ_NAT);
					}
				}

				}
				break;
			case CONST:
				enterOuterAlt(_localctx, 31);
				{
				setState(363);
				match(CONST);
				setState(364);
				literal();
				}
				break;
			case TEST:
				enterOuterAlt(_localctx, 32);
				{
				setState(365);
				match(TEST);
				}
				break;
			case COMPARE:
				enterOuterAlt(_localctx, 33);
				{
				setState(366);
				match(COMPARE);
				}
				break;
			case UNARY:
				enterOuterAlt(_localctx, 34);
				{
				setState(367);
				match(UNARY);
				}
				break;
			case BINARY:
				enterOuterAlt(_localctx, 35);
				{
				setState(368);
				match(BINARY);
				}
				break;
			case CONVERT:
				enterOuterAlt(_localctx, 36);
				{
				setState(369);
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
		enterRule(_localctx, 32, RULE_select_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			match(LPAR);
			setState(373);
			match(RETURN);
			setState(374);
			value_type();
			setState(375);
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
		public Call_instr_params_resultContext call_instr_params_result() {
			return getRuleContext(Call_instr_params_resultContext.class,0);
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
		enterRule(_localctx, 34, RULE_call_instr_params);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(377);
					match(LPAR);
					setState(378);
					match(PARAM);
					setState(382);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(379);
						value_type();
						}
						}
						setState(384);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(385);
					match(RPAR);
					}
					} 
				}
				setState(390);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			}
			setState(391);
			call_instr_params_result();
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
	public static class Call_instr_params_resultContext extends ParserRuleContext {
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
		public Call_instr_params_resultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_instr_params_result; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterCall_instr_params_result(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitCall_instr_params_result(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitCall_instr_params_result(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Call_instr_params_resultContext call_instr_params_result() throws RecognitionException {
		Call_instr_params_resultContext _localctx = new Call_instr_params_resultContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_call_instr_params_result);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(393);
					match(LPAR);
					setState(394);
					match(RESULT);
					setState(398);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(395);
						value_type();
						}
						}
						setState(400);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(401);
					match(RPAR);
					}
					} 
				}
				setState(406);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
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
		enterRule(_localctx, 38, RULE_block_instr);
		int _la;
		try {
			setState(432);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BLOCK:
			case LOOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(407);
				_la = _input.LA(1);
				if ( !(_la==BLOCK || _la==LOOP) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(409);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(408);
					bind_var();
					}
				}

				setState(411);
				block();
				setState(412);
				match(END);
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(413);
					bind_var();
					}
				}

				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				match(IF);
				setState(418);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(417);
					bind_var();
					}
				}

				setState(420);
				block();
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(421);
					match(ELSE);
					setState(423);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VAR) {
						{
						setState(422);
						bind_var();
						}
					}

					setState(425);
					instr_list();
					}
				}

				setState(428);
				match(END);
				setState(430);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(429);
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
		enterRule(_localctx, 40, RULE_block_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			match(LPAR);
			setState(435);
			match(RESULT);
			setState(436);
			value_type();
			setState(437);
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
		enterRule(_localctx, 42, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(439);
				block_type();
				}
				break;
			}
			setState(442);
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
		enterRule(_localctx, 44, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			match(LPAR);
			setState(445);
			expr1();
			setState(446);
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
		enterRule(_localctx, 46, RULE_expr1);
		int _la;
		try {
			setState(470);
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
			case MEMORY_INIT:
			case MEMORY_SIZE:
			case MEMORY_GROW:
			case MEMORY_COPY:
			case MEMORY_FILL:
			case DATA_DROP:
			case ATOMIC_FENCE:
			case ATOMIC_STORE:
			case ATOMIC_LOAD:
			case ATOMIC_WAIT:
			case ATOMIC_NOTIFY:
			case ATOMIC_CMPXCHG:
			case ATOMIC_OPR:
				enterOuterAlt(_localctx, 1);
				{
				setState(448);
				plain_instr();
				setState(452);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAR) {
					{
					{
					setState(449);
					expr();
					}
					}
					setState(454);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case BLOCK:
				enterOuterAlt(_localctx, 2);
				{
				setState(455);
				match(BLOCK);
				setState(457);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(456);
					bind_var();
					}
				}

				setState(459);
				block();
				}
				break;
			case LOOP:
				enterOuterAlt(_localctx, 3);
				{
				setState(460);
				match(LOOP);
				setState(462);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(461);
					bind_var();
					}
				}

				setState(464);
				block();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(465);
				match(IF);
				setState(467);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(466);
					bind_var();
					}
				}

				setState(469);
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
		enterRule(_localctx, 48, RULE_call_expr_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				setState(472);
				type_use();
				}
				break;
			}
			setState(475);
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
		enterRule(_localctx, 50, RULE_call_expr_params);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(477);
					match(LPAR);
					setState(478);
					match(PARAM);
					setState(482);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(479);
						value_type();
						}
						}
						setState(484);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(485);
					match(RPAR);
					}
					} 
				}
				setState(490);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			}
			setState(491);
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
		enterRule(_localctx, 52, RULE_call_expr_results);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(504);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(493);
					match(LPAR);
					setState(494);
					match(RESULT);
					setState(498);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(495);
						value_type();
						}
						}
						setState(500);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(501);
					match(RPAR);
					}
					} 
				}
				setState(506);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			}
			setState(510);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(507);
				expr();
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
		enterRule(_localctx, 54, RULE_if_block);
		int _la;
		try {
			int _alt;
			setState(533);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(513);
				block_type();
				setState(514);
				if_block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(519);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(516);
						expr();
						}
						} 
					}
					setState(521);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
				}
				setState(522);
				match(LPAR);
				setState(523);
				match(THEN);
				setState(524);
				instr_list();
				setState(525);
				match(RPAR);
				setState(531);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(526);
					match(LPAR);
					setState(527);
					match(ELSE);
					setState(528);
					instr_list();
					setState(529);
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
		enterRule(_localctx, 56, RULE_instr_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 143129974105110786L) != 0)) {
				{
				{
				setState(535);
				instr();
				}
				}
				setState(540);
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
		enterRule(_localctx, 58, RULE_const_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
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
		enterRule(_localctx, 60, RULE_func_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			match(LPAR);
			setState(544);
			match(FUNC);
			setState(546);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(545);
				bind_var();
				}
			}

			setState(548);
			func_fields();
			setState(549);
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
		enterRule(_localctx, 62, RULE_func_fields);
		try {
			setState(564);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(552);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					{
					setState(551);
					type_use();
					}
					break;
				}
				setState(554);
				func_fields_body();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(555);
				inline_import();
				setState(557);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					{
					setState(556);
					type_use();
					}
					break;
				}
				setState(559);
				func_fields_import();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(561);
				inline_export();
				setState(562);
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
		enterRule(_localctx, 64, RULE_func_fields_import);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(566);
				match(LPAR);
				setState(567);
				match(PARAM);
				setState(571);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VALUE_TYPE) {
					{
					{
					setState(568);
					value_type();
					}
					}
					setState(573);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(574);
				match(RPAR);
				}
				break;
			case 2:
				{
				setState(575);
				match(LPAR);
				setState(576);
				match(PARAM);
				setState(577);
				bind_var();
				setState(578);
				value_type();
				setState(579);
				match(RPAR);
				}
				break;
			}
			setState(583);
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
		enterRule(_localctx, 66, RULE_func_fields_import_result);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(596);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(585);
				match(LPAR);
				setState(586);
				match(RESULT);
				setState(590);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VALUE_TYPE) {
					{
					{
					setState(587);
					value_type();
					}
					}
					setState(592);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(593);
				match(RPAR);
				}
				}
				setState(598);
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
		enterRule(_localctx, 68, RULE_func_fields_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(616);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(614);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
					case 1:
						{
						setState(599);
						match(LPAR);
						setState(600);
						match(PARAM);
						setState(604);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VALUE_TYPE) {
							{
							{
							setState(601);
							value_type();
							}
							}
							setState(606);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(607);
						match(RPAR);
						}
						break;
					case 2:
						{
						setState(608);
						match(LPAR);
						setState(609);
						match(PARAM);
						setState(610);
						bind_var();
						setState(611);
						value_type();
						setState(612);
						match(RPAR);
						}
						break;
					}
					} 
				}
				setState(618);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(619);
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
		enterRule(_localctx, 70, RULE_func_result_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(632);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(621);
					match(LPAR);
					setState(622);
					match(RESULT);
					setState(626);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==VALUE_TYPE) {
						{
						{
						setState(623);
						value_type();
						}
						}
						setState(628);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(629);
					match(RPAR);
					}
					} 
				}
				setState(634);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			}
			setState(635);
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
		enterRule(_localctx, 72, RULE_func_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(654);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(652);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
					case 1:
						{
						setState(637);
						match(LPAR);
						setState(638);
						match(LOCAL);
						setState(642);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VALUE_TYPE) {
							{
							{
							setState(639);
							value_type();
							}
							}
							setState(644);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(645);
						match(RPAR);
						}
						break;
					case 2:
						{
						setState(646);
						match(LPAR);
						setState(647);
						match(LOCAL);
						setState(648);
						bind_var();
						setState(649);
						value_type();
						setState(650);
						match(RPAR);
						}
						break;
					}
					} 
				}
				setState(656);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			}
			setState(657);
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
		enterRule(_localctx, 74, RULE_offset);
		try {
			setState(665);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(659);
				match(LPAR);
				setState(660);
				match(OFFSET);
				setState(661);
				const_expr();
				setState(662);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(664);
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
		public Var_Context var_() {
			return getRuleContext(Var_Context.class,0);
		}
		public Table_bindContext table_bind() {
			return getRuleContext(Table_bindContext.class,0);
		}
		public Function_listContext function_list() {
			return getRuleContext(Function_listContext.class,0);
		}
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
		enterRule(_localctx, 76, RULE_elem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(667);
			match(LPAR);
			setState(668);
			match(ELEM);
			setState(670);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAT || _la==VAR) {
				{
				setState(669);
				var_();
				}
			}

			setState(673);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				{
				setState(672);
				table_bind();
				}
				break;
			}
			setState(675);
			offset();
			setState(677);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FUNC) {
				{
				setState(676);
				function_list();
				}
			}

			setState(679);
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
	public static class Function_listContext extends ParserRuleContext {
		public TerminalNode FUNC() { return getToken(WatParser.FUNC, 0); }
		public List<Var_Context> var_() {
			return getRuleContexts(Var_Context.class);
		}
		public Var_Context var_(int i) {
			return getRuleContext(Var_Context.class,i);
		}
		public Function_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterFunction_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitFunction_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitFunction_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_listContext function_list() throws RecognitionException {
		Function_listContext _localctx = new Function_listContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_function_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(681);
			match(FUNC);
			setState(683); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(682);
				var_();
				}
				}
				setState(685); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAT || _la==VAR );
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
	public static class Table_bindContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(WatParser.LPAR, 0); }
		public TerminalNode TABLE() { return getToken(WatParser.TABLE, 0); }
		public Var_Context var_() {
			return getRuleContext(Var_Context.class,0);
		}
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Table_bindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_bind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).enterTable_bind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WatParserListener ) ((WatParserListener)listener).exitTable_bind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof WatParserVisitor ) return ((WatParserVisitor<? extends T>)visitor).visitTable_bind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_bindContext table_bind() throws RecognitionException {
		Table_bindContext _localctx = new Table_bindContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_table_bind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(687);
			match(LPAR);
			setState(688);
			match(TABLE);
			setState(689);
			var_();
			setState(690);
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
		enterRule(_localctx, 82, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(692);
			match(LPAR);
			setState(693);
			match(TABLE);
			setState(695);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(694);
				bind_var();
				}
			}

			setState(697);
			table_fields();
			setState(698);
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
		enterRule(_localctx, 84, RULE_table_fields);
		int _la;
		try {
			setState(718);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(700);
				table_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(701);
				inline_import();
				setState(702);
				table_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(704);
				inline_export();
				setState(705);
				table_fields();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(707);
				elem_type();
				setState(708);
				match(LPAR);
				setState(709);
				match(ELEM);
				setState(713);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NAT || _la==VAR) {
					{
					{
					setState(710);
					var_();
					}
					}
					setState(715);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(716);
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
		public TerminalNode RPAR() { return getToken(WatParser.RPAR, 0); }
		public Var_Context var_() {
			return getRuleContext(Var_Context.class,0);
		}
		public OffsetContext offset() {
			return getRuleContext(OffsetContext.class,0);
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
		enterRule(_localctx, 86, RULE_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(720);
			match(LPAR);
			setState(721);
			match(DATA);
			setState(723);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAT || _la==VAR) {
				{
				setState(722);
				var_();
				}
			}

			setState(726);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAR) {
				{
				setState(725);
				offset();
				}
			}

			setState(731);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING_) {
				{
				{
				setState(728);
				match(STRING_);
				}
				}
				setState(733);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(734);
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
		enterRule(_localctx, 88, RULE_memory);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(736);
			match(LPAR);
			setState(737);
			match(MEMORY);
			setState(739);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(738);
				bind_var();
				}
			}

			setState(741);
			memory_fields();
			setState(742);
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
		enterRule(_localctx, 90, RULE_memory_fields);
		int _la;
		try {
			setState(760);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(744);
				memory_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(745);
				inline_import();
				setState(746);
				memory_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(748);
				inline_export();
				setState(749);
				memory_fields();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(751);
				match(LPAR);
				setState(752);
				match(DATA);
				setState(756);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STRING_) {
					{
					{
					setState(753);
					match(STRING_);
					}
					}
					setState(758);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(759);
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
		enterRule(_localctx, 92, RULE_sglobal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(762);
			match(LPAR);
			setState(763);
			match(GLOBAL);
			setState(765);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(764);
				bind_var();
				}
			}

			setState(767);
			global_fields();
			setState(768);
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
		enterRule(_localctx, 94, RULE_global_fields);
		try {
			setState(779);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(770);
				global_type();
				setState(771);
				const_expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(773);
				inline_import();
				setState(774);
				global_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(776);
				inline_export();
				setState(777);
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
		enterRule(_localctx, 96, RULE_import_desc);
		int _la;
		try {
			setState(821);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(781);
				match(LPAR);
				setState(782);
				match(FUNC);
				setState(784);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(783);
					bind_var();
					}
				}

				setState(786);
				type_use();
				setState(787);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(789);
				match(LPAR);
				setState(790);
				match(FUNC);
				setState(792);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(791);
					bind_var();
					}
				}

				setState(794);
				func_type();
				setState(795);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(797);
				match(LPAR);
				setState(798);
				match(TABLE);
				setState(800);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(799);
					bind_var();
					}
				}

				setState(802);
				table_type();
				setState(803);
				match(RPAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(805);
				match(LPAR);
				setState(806);
				match(MEMORY);
				setState(808);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(807);
					bind_var();
					}
				}

				setState(810);
				memory_type();
				setState(811);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(813);
				match(LPAR);
				setState(814);
				match(GLOBAL);
				setState(816);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(815);
					bind_var();
					}
				}

				setState(818);
				global_type();
				setState(819);
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
		enterRule(_localctx, 98, RULE_simport);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(823);
			match(LPAR);
			setState(824);
			match(IMPORT);
			setState(825);
			name();
			setState(826);
			name();
			setState(827);
			import_desc();
			setState(828);
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
		enterRule(_localctx, 100, RULE_inline_import);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(830);
			match(LPAR);
			setState(831);
			match(IMPORT);
			setState(832);
			name();
			setState(833);
			name();
			setState(834);
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
		enterRule(_localctx, 102, RULE_export_desc);
		try {
			setState(856);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(836);
				match(LPAR);
				setState(837);
				match(FUNC);
				setState(838);
				var_();
				setState(839);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(841);
				match(LPAR);
				setState(842);
				match(TABLE);
				setState(843);
				var_();
				setState(844);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(846);
				match(LPAR);
				setState(847);
				match(MEMORY);
				setState(848);
				var_();
				setState(849);
				match(RPAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(851);
				match(LPAR);
				setState(852);
				match(GLOBAL);
				setState(853);
				var_();
				setState(854);
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
		enterRule(_localctx, 104, RULE_export_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(858);
			match(LPAR);
			setState(859);
			match(EXPORT);
			setState(860);
			name();
			setState(861);
			export_desc();
			setState(862);
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
		enterRule(_localctx, 106, RULE_inline_export);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(864);
			match(LPAR);
			setState(865);
			match(EXPORT);
			setState(866);
			name();
			setState(867);
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
		enterRule(_localctx, 108, RULE_type_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(869);
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
		enterRule(_localctx, 110, RULE_type_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(871);
			match(LPAR);
			setState(872);
			match(TYPE);
			setState(874);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(873);
				bind_var();
				}
			}

			setState(876);
			type_();
			setState(877);
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
		enterRule(_localctx, 112, RULE_start_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(879);
			match(LPAR);
			setState(880);
			match(START_);
			setState(881);
			var_();
			setState(882);
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
		enterRule(_localctx, 114, RULE_module_field);
		try {
			setState(894);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(884);
				type_def();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(885);
				sglobal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(886);
				table();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(887);
				memory();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(888);
				func_();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(889);
				elem();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(890);
				data();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(891);
				start_();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(892);
				simport();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(893);
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
		enterRule(_localctx, 116, RULE_module_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(896);
			match(LPAR);
			setState(897);
			match(MODULE);
			setState(899);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(898);
				match(VAR);
				}
			}

			setState(904);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(901);
				module_field();
				}
				}
				setState(906);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(907);
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
		enterRule(_localctx, 118, RULE_script_module);
		int _la;
		try {
			setState(923);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(909);
				module_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(910);
				match(LPAR);
				setState(911);
				match(MODULE);
				setState(913);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(912);
					match(VAR);
					}
				}

				setState(915);
				_la = _input.LA(1);
				if ( !(_la==BIN || _la==QUOTE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(919);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STRING_) {
					{
					{
					setState(916);
					match(STRING_);
					}
					}
					setState(921);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(922);
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
		enterRule(_localctx, 120, RULE_action_);
		int _la;
		try {
			setState(942);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,120,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(925);
				match(LPAR);
				setState(926);
				match(INVOKE);
				setState(928);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(927);
					match(VAR);
					}
				}

				setState(930);
				name();
				setState(931);
				const_list();
				setState(932);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(934);
				match(LPAR);
				setState(935);
				match(GET);
				setState(937);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(936);
					match(VAR);
					}
				}

				setState(939);
				name();
				setState(940);
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
		enterRule(_localctx, 122, RULE_assertion);
		try {
			setState(996);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(944);
				match(LPAR);
				setState(945);
				match(ASSERT_MALFORMED);
				setState(946);
				script_module();
				setState(947);
				match(STRING_);
				setState(948);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(950);
				match(LPAR);
				setState(951);
				match(ASSERT_INVALID);
				setState(952);
				script_module();
				setState(953);
				match(STRING_);
				setState(954);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(956);
				match(LPAR);
				setState(957);
				match(ASSERT_UNLINKABLE);
				setState(958);
				script_module();
				setState(959);
				match(STRING_);
				setState(960);
				match(RPAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(962);
				match(LPAR);
				setState(963);
				match(ASSERT_TRAP);
				setState(964);
				script_module();
				setState(965);
				match(STRING_);
				setState(966);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(968);
				match(LPAR);
				setState(969);
				match(ASSERT_RETURN);
				setState(970);
				action_();
				setState(971);
				const_list();
				setState(972);
				match(RPAR);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(974);
				match(LPAR);
				setState(975);
				match(ASSERT_RETURN_CANONICAL_NAN);
				setState(976);
				action_();
				setState(977);
				match(RPAR);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(979);
				match(LPAR);
				setState(980);
				match(ASSERT_RETURN_ARITHMETIC_NAN);
				setState(981);
				action_();
				setState(982);
				match(RPAR);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(984);
				match(LPAR);
				setState(985);
				match(ASSERT_TRAP);
				setState(986);
				action_();
				setState(987);
				match(STRING_);
				setState(988);
				match(RPAR);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(990);
				match(LPAR);
				setState(991);
				match(ASSERT_EXHAUSTION);
				setState(992);
				action_();
				setState(993);
				match(STRING_);
				setState(994);
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
		enterRule(_localctx, 124, RULE_cmd);
		int _la;
		try {
			setState(1010);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(998);
				action_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(999);
				assertion();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1000);
				script_module();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1001);
				match(LPAR);
				setState(1002);
				match(REGISTER);
				setState(1003);
				name();
				setState(1005);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(1004);
					match(VAR);
					}
				}

				setState(1007);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1009);
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
		enterRule(_localctx, 126, RULE_meta);
		int _la;
		try {
			setState(1044);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,129,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1012);
				match(LPAR);
				setState(1013);
				match(SCRIPT);
				setState(1015);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(1014);
					match(VAR);
					}
				}

				setState(1020);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAR) {
					{
					{
					setState(1017);
					cmd();
					}
					}
					setState(1022);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1023);
				match(RPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1024);
				match(LPAR);
				setState(1025);
				match(INPUT);
				setState(1027);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(1026);
					match(VAR);
					}
				}

				setState(1029);
				match(STRING_);
				setState(1030);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1031);
				match(LPAR);
				setState(1032);
				match(OUTPUT);
				setState(1034);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(1033);
					match(VAR);
					}
				}

				setState(1036);
				match(STRING_);
				setState(1037);
				match(RPAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1038);
				match(LPAR);
				setState(1039);
				match(OUTPUT);
				setState(1041);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR) {
					{
					setState(1040);
					match(VAR);
					}
				}

				setState(1043);
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
		enterRule(_localctx, 128, RULE_wconst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1046);
			match(LPAR);
			setState(1047);
			match(CONST);
			setState(1048);
			literal();
			setState(1049);
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
		enterRule(_localctx, 130, RULE_const_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1054);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAR) {
				{
				{
				setState(1051);
				wconst();
				}
				}
				setState(1056);
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
		enterRule(_localctx, 132, RULE_script);
		int _la;
		try {
			setState(1071);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,133,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1060);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAR) {
					{
					{
					setState(1057);
					cmd();
					}
					}
					setState(1062);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1063);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1065); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1064);
					module_field();
					}
					}
					setState(1067); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LPAR );
				setState(1069);
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
		enterRule(_localctx, 134, RULE_module);
		int _la;
		try {
			setState(1083);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,135,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1073);
				module_();
				setState(1074);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1079);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAR) {
					{
					{
					setState(1076);
					module_field();
					}
					}
					setState(1081);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1082);
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
		"\u0004\u0001Z\u043e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"\u0097\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00a1\b\u0006\n\u0006"+
		"\f\u0006\u00a4\t\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00a8\b\u0006"+
		"\n\u0006\f\u0006\u00ab\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u00b1\b\u0006\u0001\u0006\u0005\u0006\u00b4\b\u0006"+
		"\n\u0006\f\u0006\u00b7\t\u0006\u0001\u0007\u0001\u0007\u0003\u0007\u00bb"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0003\b\u00c1\b\b\u0001"+
		"\b\u0003\b\u00c4\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0003\u000e\u00d6\b\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00dd\b\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0004\u000f"+
		"\u00e5\b\u000f\u000b\u000f\f\u000f\u00e6\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00ee\b\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u00ff\b\u000f\u0001\u000f\u0003\u000f\u0102"+
		"\b\u000f\u0001\u000f\u0003\u000f\u0105\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u0109\b\u000f\u0001\u000f\u0003\u000f\u010c\b\u000f\u0001"+
		"\u000f\u0003\u000f\u010f\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0113"+
		"\b\u000f\u0001\u000f\u0003\u000f\u0116\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u011a\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u011e\b"+
		"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0122\b\u000f\u0001\u000f\u0003"+
		"\u000f\u0125\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0129\b\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u012d\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u0132\b\u000f\u0001\u000f\u0003\u000f\u0135\b"+
		"\u000f\u0001\u000f\u0003\u000f\u0138\b\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u013c\b\u000f\u0001\u000f\u0003\u000f\u013f\b\u000f\u0001\u000f"+
		"\u0003\u000f\u0142\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0146\b"+
		"\u000f\u0001\u000f\u0003\u000f\u0149\b\u000f\u0001\u000f\u0003\u000f\u014c"+
		"\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0150\b\u000f\u0001\u000f"+
		"\u0003\u000f\u0153\b\u000f\u0001\u000f\u0003\u000f\u0156\b\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u015a\b\u000f\u0001\u000f\u0003\u000f\u015d"+
		"\b\u000f\u0001\u000f\u0003\u000f\u0160\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u0164\b\u000f\u0001\u000f\u0003\u000f\u0167\b\u000f\u0001"+
		"\u000f\u0003\u000f\u016a\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0173\b\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0005\u0011\u017d\b\u0011\n\u0011\f\u0011\u0180\t\u0011"+
		"\u0001\u0011\u0005\u0011\u0183\b\u0011\n\u0011\f\u0011\u0186\t\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u018d"+
		"\b\u0012\n\u0012\f\u0012\u0190\t\u0012\u0001\u0012\u0005\u0012\u0193\b"+
		"\u0012\n\u0012\f\u0012\u0196\t\u0012\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u019a\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u019f\b"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01a3\b\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u01a8\b\u0013\u0001\u0013\u0003\u0013\u01ab"+
		"\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01af\b\u0013\u0003\u0013"+
		"\u01b1\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0003\u0015\u01b9\b\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0005\u0017"+
		"\u01c3\b\u0017\n\u0017\f\u0017\u01c6\t\u0017\u0001\u0017\u0001\u0017\u0003"+
		"\u0017\u01ca\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01cf"+
		"\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01d4\b\u0017"+
		"\u0001\u0017\u0003\u0017\u01d7\b\u0017\u0001\u0018\u0003\u0018\u01da\b"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0005"+
		"\u0019\u01e1\b\u0019\n\u0019\f\u0019\u01e4\t\u0019\u0001\u0019\u0005\u0019"+
		"\u01e7\b\u0019\n\u0019\f\u0019\u01ea\t\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u01f1\b\u001a\n\u001a\f\u001a"+
		"\u01f4\t\u001a\u0001\u001a\u0005\u001a\u01f7\b\u001a\n\u001a\f\u001a\u01fa"+
		"\t\u001a\u0001\u001a\u0005\u001a\u01fd\b\u001a\n\u001a\f\u001a\u0200\t"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u0206"+
		"\b\u001b\n\u001b\f\u001b\u0209\t\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0003\u001b\u0214\b\u001b\u0003\u001b\u0216\b\u001b\u0001\u001c\u0005"+
		"\u001c\u0219\b\u001c\n\u001c\f\u001c\u021c\t\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0223\b\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001f\u0003\u001f\u0229\b\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0003\u001f\u022e\b\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0235\b\u001f\u0001 "+
		"\u0001 \u0001 \u0005 \u023a\b \n \f \u023d\t \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0003 \u0246\b \u0001 \u0001 \u0001!\u0001!\u0001"+
		"!\u0005!\u024d\b!\n!\f!\u0250\t!\u0001!\u0005!\u0253\b!\n!\f!\u0256\t"+
		"!\u0001\"\u0001\"\u0001\"\u0005\"\u025b\b\"\n\"\f\"\u025e\t\"\u0001\""+
		"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0005\"\u0267\b\"\n\""+
		"\f\"\u026a\t\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0005#\u0271\b#\n"+
		"#\f#\u0274\t#\u0001#\u0005#\u0277\b#\n#\f#\u027a\t#\u0001#\u0001#\u0001"+
		"$\u0001$\u0001$\u0005$\u0281\b$\n$\f$\u0284\t$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0005$\u028d\b$\n$\f$\u0290\t$\u0001$\u0001$\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0003%\u029a\b%\u0001&\u0001&\u0001"+
		"&\u0003&\u029f\b&\u0001&\u0003&\u02a2\b&\u0001&\u0001&\u0003&\u02a6\b"+
		"&\u0001&\u0001&\u0001\'\u0001\'\u0004\'\u02ac\b\'\u000b\'\f\'\u02ad\u0001"+
		"(\u0001(\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0003)\u02b8\b)\u0001"+
		")\u0001)\u0001)\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001"+
		"*\u0001*\u0001*\u0001*\u0005*\u02c8\b*\n*\f*\u02cb\t*\u0001*\u0001*\u0003"+
		"*\u02cf\b*\u0001+\u0001+\u0001+\u0003+\u02d4\b+\u0001+\u0003+\u02d7\b"+
		"+\u0001+\u0005+\u02da\b+\n+\f+\u02dd\t+\u0001+\u0001+\u0001,\u0001,\u0001"+
		",\u0003,\u02e4\b,\u0001,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0001-\u0001-\u0001-\u0001-\u0005-\u02f3\b-\n-\f-\u02f6\t-\u0001"+
		"-\u0003-\u02f9\b-\u0001.\u0001.\u0001.\u0003.\u02fe\b.\u0001.\u0001.\u0001"+
		".\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0003"+
		"/\u030c\b/\u00010\u00010\u00010\u00030\u0311\b0\u00010\u00010\u00010\u0001"+
		"0\u00010\u00010\u00030\u0319\b0\u00010\u00010\u00010\u00010\u00010\u0001"+
		"0\u00030\u0321\b0\u00010\u00010\u00010\u00010\u00010\u00010\u00030\u0329"+
		"\b0\u00010\u00010\u00010\u00010\u00010\u00010\u00030\u0331\b0\u00010\u0001"+
		"0\u00010\u00030\u0336\b0\u00011\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00012\u00012\u00012\u00012\u00012\u00012\u00013\u00013\u00013\u0001"+
		"3\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u0001"+
		"3\u00013\u00013\u00013\u00013\u00013\u00013\u00033\u0359\b3\u00014\u0001"+
		"4\u00014\u00014\u00014\u00014\u00015\u00015\u00015\u00015\u00015\u0001"+
		"6\u00016\u00017\u00017\u00017\u00037\u036b\b7\u00017\u00017\u00017\u0001"+
		"8\u00018\u00018\u00018\u00018\u00019\u00019\u00019\u00019\u00019\u0001"+
		"9\u00019\u00019\u00019\u00019\u00039\u037f\b9\u0001:\u0001:\u0001:\u0003"+
		":\u0384\b:\u0001:\u0005:\u0387\b:\n:\f:\u038a\t:\u0001:\u0001:\u0001;"+
		"\u0001;\u0001;\u0001;\u0003;\u0392\b;\u0001;\u0001;\u0005;\u0396\b;\n"+
		";\f;\u0399\t;\u0001;\u0003;\u039c\b;\u0001<\u0001<\u0001<\u0003<\u03a1"+
		"\b<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0003<\u03aa\b<\u0001"+
		"<\u0001<\u0001<\u0003<\u03af\b<\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001=\u0001=\u0001=\u0001=\u0003=\u03e5\b=\u0001>\u0001"+
		">\u0001>\u0001>\u0001>\u0001>\u0001>\u0003>\u03ee\b>\u0001>\u0001>\u0001"+
		">\u0003>\u03f3\b>\u0001?\u0001?\u0001?\u0003?\u03f8\b?\u0001?\u0005?\u03fb"+
		"\b?\n?\f?\u03fe\t?\u0001?\u0001?\u0001?\u0001?\u0003?\u0404\b?\u0001?"+
		"\u0001?\u0001?\u0001?\u0001?\u0003?\u040b\b?\u0001?\u0001?\u0001?\u0001"+
		"?\u0001?\u0003?\u0412\b?\u0001?\u0003?\u0415\b?\u0001@\u0001@\u0001@\u0001"+
		"@\u0001@\u0001A\u0005A\u041d\bA\nA\fA\u0420\tA\u0001B\u0005B\u0423\bB"+
		"\nB\fB\u0426\tB\u0001B\u0001B\u0004B\u042a\bB\u000bB\fB\u042b\u0001B\u0001"+
		"B\u0003B\u0430\bB\u0001C\u0001C\u0001C\u0001C\u0005C\u0436\bC\nC\fC\u0439"+
		"\tC\u0001C\u0003C\u043c\bC\u0001C\u0000\u0000D\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0000"+
		"\u0006\u0001\u0000\u0004\u0005\u0001\u0000/0\u0001\u0000\u0003\u0005\u0002"+
		"\u0000\u0003\u0003XX\u0001\u0000\u000e\u000f\u0001\u0000HI\u04c6\u0000"+
		"\u0088\u0001\u0000\u0000\u0000\u0002\u008a\u0001\u0000\u0000\u0000\u0004"+
		"\u008c\u0001\u0000\u0000\u0000\u0006\u008e\u0001\u0000\u0000\u0000\b\u0096"+
		"\u0001\u0000\u0000\u0000\n\u0098\u0001\u0000\u0000\u0000\f\u00b5\u0001"+
		"\u0000\u0000\u0000\u000e\u00b8\u0001\u0000\u0000\u0000\u0010\u00be\u0001"+
		"\u0000\u0000\u0000\u0012\u00c5\u0001\u0000\u0000\u0000\u0014\u00c7\u0001"+
		"\u0000\u0000\u0000\u0016\u00cc\u0001\u0000\u0000\u0000\u0018\u00ce\u0001"+
		"\u0000\u0000\u0000\u001a\u00d0\u0001\u0000\u0000\u0000\u001c\u00d5\u0001"+
		"\u0000\u0000\u0000\u001e\u0172\u0001\u0000\u0000\u0000 \u0174\u0001\u0000"+
		"\u0000\u0000\"\u0184\u0001\u0000\u0000\u0000$\u0194\u0001\u0000\u0000"+
		"\u0000&\u01b0\u0001\u0000\u0000\u0000(\u01b2\u0001\u0000\u0000\u0000*"+
		"\u01b8\u0001\u0000\u0000\u0000,\u01bc\u0001\u0000\u0000\u0000.\u01d6\u0001"+
		"\u0000\u0000\u00000\u01d9\u0001\u0000\u0000\u00002\u01e8\u0001\u0000\u0000"+
		"\u00004\u01f8\u0001\u0000\u0000\u00006\u0215\u0001\u0000\u0000\u00008"+
		"\u021a\u0001\u0000\u0000\u0000:\u021d\u0001\u0000\u0000\u0000<\u021f\u0001"+
		"\u0000\u0000\u0000>\u0234\u0001\u0000\u0000\u0000@\u0245\u0001\u0000\u0000"+
		"\u0000B\u0254\u0001\u0000\u0000\u0000D\u0268\u0001\u0000\u0000\u0000F"+
		"\u0278\u0001\u0000\u0000\u0000H\u028e\u0001\u0000\u0000\u0000J\u0299\u0001"+
		"\u0000\u0000\u0000L\u029b\u0001\u0000\u0000\u0000N\u02a9\u0001\u0000\u0000"+
		"\u0000P\u02af\u0001\u0000\u0000\u0000R\u02b4\u0001\u0000\u0000\u0000T"+
		"\u02ce\u0001\u0000\u0000\u0000V\u02d0\u0001\u0000\u0000\u0000X\u02e0\u0001"+
		"\u0000\u0000\u0000Z\u02f8\u0001\u0000\u0000\u0000\\\u02fa\u0001\u0000"+
		"\u0000\u0000^\u030b\u0001\u0000\u0000\u0000`\u0335\u0001\u0000\u0000\u0000"+
		"b\u0337\u0001\u0000\u0000\u0000d\u033e\u0001\u0000\u0000\u0000f\u0358"+
		"\u0001\u0000\u0000\u0000h\u035a\u0001\u0000\u0000\u0000j\u0360\u0001\u0000"+
		"\u0000\u0000l\u0365\u0001\u0000\u0000\u0000n\u0367\u0001\u0000\u0000\u0000"+
		"p\u036f\u0001\u0000\u0000\u0000r\u037e\u0001\u0000\u0000\u0000t\u0380"+
		"\u0001\u0000\u0000\u0000v\u039b\u0001\u0000\u0000\u0000x\u03ae\u0001\u0000"+
		"\u0000\u0000z\u03e4\u0001\u0000\u0000\u0000|\u03f2\u0001\u0000\u0000\u0000"+
		"~\u0414\u0001\u0000\u0000\u0000\u0080\u0416\u0001\u0000\u0000\u0000\u0082"+
		"\u041e\u0001\u0000\u0000\u0000\u0084\u042f\u0001\u0000\u0000\u0000\u0086"+
		"\u043b\u0001\u0000\u0000\u0000\u0088\u0089\u0007\u0000\u0000\u0000\u0089"+
		"\u0001\u0001\u0000\u0000\u0000\u008a\u008b\u0005\u0006\u0000\u0000\u008b"+
		"\u0003\u0001\u0000\u0000\u0000\u008c\u008d\u0005\u0007\u0000\u0000\u008d"+
		"\u0005\u0001\u0000\u0000\u0000\u008e\u008f\u0005\t\u0000\u0000\u008f\u0007"+
		"\u0001\u0000\u0000\u0000\u0090\u0097\u0003\u0004\u0002\u0000\u0091\u0092"+
		"\u0005\u0001\u0000\u0000\u0092\u0093\u0005\n\u0000\u0000\u0093\u0094\u0003"+
		"\u0004\u0002\u0000\u0094\u0095\u0005\u0002\u0000\u0000\u0095\u0097\u0001"+
		"\u0000\u0000\u0000\u0096\u0090\u0001\u0000\u0000\u0000\u0096\u0091\u0001"+
		"\u0000\u0000\u0000\u0097\t\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0001"+
		"\u0000\u0000\u0099\u009a\u0005:\u0000\u0000\u009a\u009b\u0003\f\u0006"+
		"\u0000\u009b\u009c\u0005\u0002\u0000\u0000\u009c\u000b\u0001\u0000\u0000"+
		"\u0000\u009d\u00b0\u0005\u0001\u0000\u0000\u009e\u00a2\u0005=\u0000\u0000"+
		"\u009f\u00a1\u0003\u0004\u0002\u0000\u00a0\u009f\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a4\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00b1\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a9\u0005<\u0000\u0000\u00a6"+
		"\u00a8\u0003\u0004\u0002\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8"+
		"\u00ab\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9"+
		"\u00aa\u0001\u0000\u0000\u0000\u00aa\u00b1\u0001\u0000\u0000\u0000\u00ab"+
		"\u00a9\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005<\u0000\u0000\u00ad\u00ae"+
		"\u0003\u001a\r\u0000\u00ae\u00af\u0003\u0004\u0002\u0000\u00af\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b0\u009e\u0001\u0000\u0000\u0000\u00b0\u00a5\u0001"+
		"\u0000\u0000\u0000\u00b0\u00ac\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b4\u0005\u0002\u0000\u0000\u00b3\u009d\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b7\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\r\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b8\u00ba\u0005\u0003"+
		"\u0000\u0000\u00b9\u00bb\u0005\u0003\u0000\u0000\u00ba\u00b9\u0001\u0000"+
		"\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000"+
		"\u0000\u0000\u00bc\u00bd\u0003\u0006\u0003\u0000\u00bd\u000f\u0001\u0000"+
		"\u0000\u0000\u00be\u00c0\u0005\u0003\u0000\u0000\u00bf\u00c1\u0005\u0003"+
		"\u0000\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000"+
		"\u0000\u0000\u00c1\u00c3\u0001\u0000\u0000\u0000\u00c2\u00c4\u0003\u0012"+
		"\t\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c4\u0011\u0001\u0000\u0000\u0000\u00c5\u00c6\u0007\u0001\u0000"+
		"\u0000\u00c6\u0013\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005\u0001\u0000"+
		"\u0000\u00c8\u00c9\u00059\u0000\u0000\u00c9\u00ca\u0003\u0018\f\u0000"+
		"\u00ca\u00cb\u0005\u0002\u0000\u0000\u00cb\u0015\u0001\u0000\u0000\u0000"+
		"\u00cc\u00cd\u0007\u0002\u0000\u0000\u00cd\u0017\u0001\u0000\u0000\u0000"+
		"\u00ce\u00cf\u0007\u0003\u0000\u0000\u00cf\u0019\u0001\u0000\u0000\u0000"+
		"\u00d0\u00d1\u0005X\u0000\u0000\u00d1\u001b\u0001\u0000\u0000\u0000\u00d2"+
		"\u00d6\u0003\u001e\u000f\u0000\u00d3\u00d6\u0003&\u0013\u0000\u00d4\u00d6"+
		"\u0003,\u0016\u0000\u00d5\u00d2\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001"+
		"\u0000\u0000\u0000\u00d5\u00d4\u0001\u0000\u0000\u0000\u00d6\u001d\u0001"+
		"\u0000\u0000\u0000\u00d7\u0173\u0005\f\u0000\u0000\u00d8\u0173\u0005\u000b"+
		"\u0000\u0000\u00d9\u0173\u0005\r\u0000\u0000\u00da\u00dc\u0005\u0018\u0000"+
		"\u0000\u00db\u00dd\u0003 \u0010\u0000\u00dc\u00db\u0001\u0000\u0000\u0000"+
		"\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd\u0173\u0001\u0000\u0000\u0000"+
		"\u00de\u00df\u0005\u0011\u0000\u0000\u00df\u0173\u0003\u0018\f\u0000\u00e0"+
		"\u00e1\u0005\u0012\u0000\u0000\u00e1\u0173\u0003\u0018\f\u0000\u00e2\u00e4"+
		"\u0005\u0013\u0000\u0000\u00e3\u00e5\u0003\u0018\f\u0000\u00e4\u00e3\u0001"+
		"\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001"+
		"\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7\u0173\u0001"+
		"\u0000\u0000\u0000\u00e8\u0173\u0005\u0014\u0000\u0000\u00e9\u00ea\u0005"+
		"\u0019\u0000\u0000\u00ea\u0173\u0003\u0018\f\u0000\u00eb\u00ed\u0005\u001a"+
		"\u0000\u0000\u00ec\u00ee\u0003\u0018\f\u0000\u00ed\u00ec\u0001\u0000\u0000"+
		"\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000"+
		"\u0000\u00ef\u00f0\u0003\u0014\n\u0000\u00f0\u00f1\u0003\"\u0011\u0000"+
		"\u00f1\u0173\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005\u001b\u0000\u0000"+
		"\u00f3\u0173\u0003\u0018\f\u0000\u00f4\u00f5\u0005\u001c\u0000\u0000\u00f5"+
		"\u0173\u0003\u0018\f\u0000\u00f6\u00f7\u0005\u001d\u0000\u0000\u00f7\u0173"+
		"\u0003\u0018\f\u0000\u00f8\u00f9\u0005\u001e\u0000\u0000\u00f9\u0173\u0003"+
		"\u0018\f\u0000\u00fa\u00fb\u0005\u001f\u0000\u0000\u00fb\u0173\u0003\u0018"+
		"\f\u0000\u00fc\u00fe\u0005 \u0000\u0000\u00fd\u00ff\u0003\u0018\f\u0000"+
		"\u00fe\u00fd\u0001\u0000\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000"+
		"\u00ff\u0101\u0001\u0000\u0000\u0000\u0100\u0102\u0005\"\u0000\u0000\u0101"+
		"\u0100\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000\u0102"+
		"\u0104\u0001\u0000\u0000\u0000\u0103\u0105\u0005#\u0000\u0000\u0104\u0103"+
		"\u0001\u0000\u0000\u0000\u0104\u0105\u0001\u0000\u0000\u0000\u0105\u0173"+
		"\u0001\u0000\u0000\u0000\u0106\u0108\u0005!\u0000\u0000\u0107\u0109\u0003"+
		"\u0018\f\u0000\u0108\u0107\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000"+
		"\u0000\u0000\u0109\u010b\u0001\u0000\u0000\u0000\u010a\u010c\u0005\"\u0000"+
		"\u0000\u010b\u010a\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000"+
		"\u0000\u010c\u010e\u0001\u0000\u0000\u0000\u010d\u010f\u0005#\u0000\u0000"+
		"\u010e\u010d\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000"+
		"\u010f\u0173\u0001\u0000\u0000\u0000\u0110\u0112\u0005)\u0000\u0000\u0111"+
		"\u0113\u0003\u0018\f\u0000\u0112\u0111\u0001\u0000\u0000\u0000\u0112\u0113"+
		"\u0001\u0000\u0000\u0000\u0113\u0115\u0001\u0000\u0000\u0000\u0114\u0116"+
		"\u0003\u0018\f\u0000\u0115\u0114\u0001\u0000\u0000\u0000\u0115\u0116\u0001"+
		"\u0000\u0000\u0000\u0116\u0173\u0001\u0000\u0000\u0000\u0117\u0119\u0005"+
		"*\u0000\u0000\u0118\u011a\u0003\u0018\f\u0000\u0119\u0118\u0001\u0000"+
		"\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000\u011a\u0173\u0001\u0000"+
		"\u0000\u0000\u011b\u011d\u0005+\u0000\u0000\u011c\u011e\u0003\u0018\f"+
		"\u0000\u011d\u011c\u0001\u0000\u0000\u0000\u011d\u011e\u0001\u0000\u0000"+
		"\u0000\u011e\u0173\u0001\u0000\u0000\u0000\u011f\u0121\u0005,\u0000\u0000"+
		"\u0120\u0122\u0003\u0018\f\u0000\u0121\u0120\u0001\u0000\u0000\u0000\u0121"+
		"\u0122\u0001\u0000\u0000\u0000\u0122\u0124\u0001\u0000\u0000\u0000\u0123"+
		"\u0125\u0003\u0018\f\u0000\u0124\u0123\u0001\u0000\u0000\u0000\u0124\u0125"+
		"\u0001\u0000\u0000\u0000\u0125\u0173\u0001\u0000\u0000\u0000\u0126\u0128"+
		"\u0005-\u0000\u0000\u0127\u0129\u0003\u0018\f\u0000\u0128\u0127\u0001"+
		"\u0000\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000\u0129\u0173\u0001"+
		"\u0000\u0000\u0000\u012a\u012c\u0005.\u0000\u0000\u012b\u012d\u0003\u0018"+
		"\f\u0000\u012c\u012b\u0001\u0000\u0000\u0000\u012c\u012d\u0001\u0000\u0000"+
		"\u0000\u012d\u0173\u0001\u0000\u0000\u0000\u012e\u0173\u00052\u0000\u0000"+
		"\u012f\u0131\u00054\u0000\u0000\u0130\u0132\u0003\u0018\f\u0000\u0131"+
		"\u0130\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000\u0132"+
		"\u0134\u0001\u0000\u0000\u0000\u0133\u0135\u0005\"\u0000\u0000\u0134\u0133"+
		"\u0001\u0000\u0000\u0000\u0134\u0135\u0001\u0000\u0000\u0000\u0135\u0137"+
		"\u0001\u0000\u0000\u0000\u0136\u0138\u0005#\u0000\u0000\u0137\u0136\u0001"+
		"\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000\u0138\u0173\u0001"+
		"\u0000\u0000\u0000\u0139\u013b\u00053\u0000\u0000\u013a\u013c\u0003\u0018"+
		"\f\u0000\u013b\u013a\u0001\u0000\u0000\u0000\u013b\u013c\u0001\u0000\u0000"+
		"\u0000\u013c\u013e\u0001\u0000\u0000\u0000\u013d\u013f\u0005\"\u0000\u0000"+
		"\u013e\u013d\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000"+
		"\u013f\u0141\u0001\u0000\u0000\u0000\u0140\u0142\u0005#\u0000\u0000\u0141"+
		"\u0140\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000\u0142"+
		"\u0173\u0001\u0000\u0000\u0000\u0143\u0145\u00055\u0000\u0000\u0144\u0146"+
		"\u0003\u0018\f\u0000\u0145\u0144\u0001\u0000\u0000\u0000\u0145\u0146\u0001"+
		"\u0000\u0000\u0000\u0146\u0148\u0001\u0000\u0000\u0000\u0147\u0149\u0005"+
		"\"\u0000\u0000\u0148\u0147\u0001\u0000\u0000\u0000\u0148\u0149\u0001\u0000"+
		"\u0000\u0000\u0149\u014b\u0001\u0000\u0000\u0000\u014a\u014c\u0005#\u0000"+
		"\u0000\u014b\u014a\u0001\u0000\u0000\u0000\u014b\u014c\u0001\u0000\u0000"+
		"\u0000\u014c\u0173\u0001\u0000\u0000\u0000\u014d\u014f\u00056\u0000\u0000"+
		"\u014e\u0150\u0003\u0018\f\u0000\u014f\u014e\u0001\u0000\u0000\u0000\u014f"+
		"\u0150\u0001\u0000\u0000\u0000\u0150\u0152\u0001\u0000\u0000\u0000\u0151"+
		"\u0153\u0005\"\u0000\u0000\u0152\u0151\u0001\u0000\u0000\u0000\u0152\u0153"+
		"\u0001\u0000\u0000\u0000\u0153\u0155\u0001\u0000\u0000\u0000\u0154\u0156"+
		"\u0005#\u0000\u0000\u0155\u0154\u0001\u0000\u0000\u0000\u0155\u0156\u0001"+
		"\u0000\u0000\u0000\u0156\u0173\u0001\u0000\u0000\u0000\u0157\u0159\u0005"+
		"7\u0000\u0000\u0158\u015a\u0003\u0018\f\u0000\u0159\u0158\u0001\u0000"+
		"\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u015c\u0001\u0000"+
		"\u0000\u0000\u015b\u015d\u0005\"\u0000\u0000\u015c\u015b\u0001\u0000\u0000"+
		"\u0000\u015c\u015d\u0001\u0000\u0000\u0000\u015d\u015f\u0001\u0000\u0000"+
		"\u0000\u015e\u0160\u0005#\u0000\u0000\u015f\u015e\u0001\u0000\u0000\u0000"+
		"\u015f\u0160\u0001\u0000\u0000\u0000\u0160\u0173\u0001\u0000\u0000\u0000"+
		"\u0161\u0163\u00058\u0000\u0000\u0162\u0164\u0003\u0018\f\u0000\u0163"+
		"\u0162\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000\u0000\u0164"+
		"\u0166\u0001\u0000\u0000\u0000\u0165\u0167\u0005\"\u0000\u0000\u0166\u0165"+
		"\u0001\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167\u0169"+
		"\u0001\u0000\u0000\u0000\u0168\u016a\u0005#\u0000\u0000\u0169\u0168\u0001"+
		"\u0000\u0000\u0000\u0169\u016a\u0001\u0000\u0000\u0000\u016a\u0173\u0001"+
		"\u0000\u0000\u0000\u016b\u016c\u0005\b\u0000\u0000\u016c\u0173\u0003\u0016"+
		"\u000b\u0000\u016d\u0173\u0005&\u0000\u0000\u016e\u0173\u0005\'\u0000"+
		"\u0000\u016f\u0173\u0005$\u0000\u0000\u0170\u0173\u0005%\u0000\u0000\u0171"+
		"\u0173\u0005(\u0000\u0000\u0172\u00d7\u0001\u0000\u0000\u0000\u0172\u00d8"+
		"\u0001\u0000\u0000\u0000\u0172\u00d9\u0001\u0000\u0000\u0000\u0172\u00da"+
		"\u0001\u0000\u0000\u0000\u0172\u00de\u0001\u0000\u0000\u0000\u0172\u00e0"+
		"\u0001\u0000\u0000\u0000\u0172\u00e2\u0001\u0000\u0000\u0000\u0172\u00e8"+
		"\u0001\u0000\u0000\u0000\u0172\u00e9\u0001\u0000\u0000\u0000\u0172\u00eb"+
		"\u0001\u0000\u0000\u0000\u0172\u00f2\u0001\u0000\u0000\u0000\u0172\u00f4"+
		"\u0001\u0000\u0000\u0000\u0172\u00f6\u0001\u0000\u0000\u0000\u0172\u00f8"+
		"\u0001\u0000\u0000\u0000\u0172\u00fa\u0001\u0000\u0000\u0000\u0172\u00fc"+
		"\u0001\u0000\u0000\u0000\u0172\u0106\u0001\u0000\u0000\u0000\u0172\u0110"+
		"\u0001\u0000\u0000\u0000\u0172\u0117\u0001\u0000\u0000\u0000\u0172\u011b"+
		"\u0001\u0000\u0000\u0000\u0172\u011f\u0001\u0000\u0000\u0000\u0172\u0126"+
		"\u0001\u0000\u0000\u0000\u0172\u012a\u0001\u0000\u0000\u0000\u0172\u012e"+
		"\u0001\u0000\u0000\u0000\u0172\u012f\u0001\u0000\u0000\u0000\u0172\u0139"+
		"\u0001\u0000\u0000\u0000\u0172\u0143\u0001\u0000\u0000\u0000\u0172\u014d"+
		"\u0001\u0000\u0000\u0000\u0172\u0157\u0001\u0000\u0000\u0000\u0172\u0161"+
		"\u0001\u0000\u0000\u0000\u0172\u016b\u0001\u0000\u0000\u0000\u0172\u016d"+
		"\u0001\u0000\u0000\u0000\u0172\u016e\u0001\u0000\u0000\u0000\u0172\u016f"+
		"\u0001\u0000\u0000\u0000\u0172\u0170\u0001\u0000\u0000\u0000\u0172\u0171"+
		"\u0001\u0000\u0000\u0000\u0173\u001f\u0001\u0000\u0000\u0000\u0174\u0175"+
		"\u0005\u0001\u0000\u0000\u0175\u0176\u0005\u0014\u0000\u0000\u0176\u0177"+
		"\u0003\u0004\u0002\u0000\u0177\u0178\u0005\u0002\u0000\u0000\u0178!\u0001"+
		"\u0000\u0000\u0000\u0179\u017a\u0005\u0001\u0000\u0000\u017a\u017e\u0005"+
		"<\u0000\u0000\u017b\u017d\u0003\u0004\u0002\u0000\u017c\u017b\u0001\u0000"+
		"\u0000\u0000\u017d\u0180\u0001\u0000\u0000\u0000\u017e\u017c\u0001\u0000"+
		"\u0000\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0181\u0001\u0000"+
		"\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000\u0181\u0183\u0005\u0002"+
		"\u0000\u0000\u0182\u0179\u0001\u0000\u0000\u0000\u0183\u0186\u0001\u0000"+
		"\u0000\u0000\u0184\u0182\u0001\u0000\u0000\u0000\u0184\u0185\u0001\u0000"+
		"\u0000\u0000\u0185\u0187\u0001\u0000\u0000\u0000\u0186\u0184\u0001\u0000"+
		"\u0000\u0000\u0187\u0188\u0003$\u0012\u0000\u0188#\u0001\u0000\u0000\u0000"+
		"\u0189\u018a\u0005\u0001\u0000\u0000\u018a\u018e\u0005=\u0000\u0000\u018b"+
		"\u018d\u0003\u0004\u0002\u0000\u018c\u018b\u0001\u0000\u0000\u0000\u018d"+
		"\u0190\u0001\u0000\u0000\u0000\u018e\u018c\u0001\u0000\u0000\u0000\u018e"+
		"\u018f\u0001\u0000\u0000\u0000\u018f\u0191\u0001\u0000\u0000\u0000\u0190"+
		"\u018e\u0001\u0000\u0000\u0000\u0191\u0193\u0005\u0002\u0000\u0000\u0192"+
		"\u0189\u0001\u0000\u0000\u0000\u0193\u0196\u0001\u0000\u0000\u0000\u0194"+
		"\u0192\u0001\u0000\u0000\u0000\u0194\u0195\u0001\u0000\u0000\u0000\u0195"+
		"%\u0001\u0000\u0000\u0000\u0196\u0194\u0001\u0000\u0000\u0000\u0197\u0199"+
		"\u0007\u0004\u0000\u0000\u0198\u019a\u0003\u001a\r\u0000\u0199\u0198\u0001"+
		"\u0000\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019a\u019b\u0001"+
		"\u0000\u0000\u0000\u019b\u019c\u0003*\u0015\u0000\u019c\u019e\u0005\u0010"+
		"\u0000\u0000\u019d\u019f\u0003\u001a\r\u0000\u019e\u019d\u0001\u0000\u0000"+
		"\u0000\u019e\u019f\u0001\u0000\u0000\u0000\u019f\u01b1\u0001\u0000\u0000"+
		"\u0000\u01a0\u01a2\u0005\u0015\u0000\u0000\u01a1\u01a3\u0003\u001a\r\u0000"+
		"\u01a2\u01a1\u0001\u0000\u0000\u0000\u01a2\u01a3\u0001\u0000\u0000\u0000"+
		"\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4\u01aa\u0003*\u0015\u0000\u01a5"+
		"\u01a7\u0005\u0017\u0000\u0000\u01a6\u01a8\u0003\u001a\r\u0000\u01a7\u01a6"+
		"\u0001\u0000\u0000\u0000\u01a7\u01a8\u0001\u0000\u0000\u0000\u01a8\u01a9"+
		"\u0001\u0000\u0000\u0000\u01a9\u01ab\u00038\u001c\u0000\u01aa\u01a5\u0001"+
		"\u0000\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ac\u0001"+
		"\u0000\u0000\u0000\u01ac\u01ae\u0005\u0010\u0000\u0000\u01ad\u01af\u0003"+
		"\u001a\r\u0000\u01ae\u01ad\u0001\u0000\u0000\u0000\u01ae\u01af\u0001\u0000"+
		"\u0000\u0000\u01af\u01b1\u0001\u0000\u0000\u0000\u01b0\u0197\u0001\u0000"+
		"\u0000\u0000\u01b0\u01a0\u0001\u0000\u0000\u0000\u01b1\'\u0001\u0000\u0000"+
		"\u0000\u01b2\u01b3\u0005\u0001\u0000\u0000\u01b3\u01b4\u0005=\u0000\u0000"+
		"\u01b4\u01b5\u0003\u0004\u0002\u0000\u01b5\u01b6\u0005\u0002\u0000\u0000"+
		"\u01b6)\u0001\u0000\u0000\u0000\u01b7\u01b9\u0003(\u0014\u0000\u01b8\u01b7"+
		"\u0001\u0000\u0000\u0000\u01b8\u01b9\u0001\u0000\u0000\u0000\u01b9\u01ba"+
		"\u0001\u0000\u0000\u0000\u01ba\u01bb\u00038\u001c\u0000\u01bb+\u0001\u0000"+
		"\u0000\u0000\u01bc\u01bd\u0005\u0001\u0000\u0000\u01bd\u01be\u0003.\u0017"+
		"\u0000\u01be\u01bf\u0005\u0002\u0000\u0000\u01bf-\u0001\u0000\u0000\u0000"+
		"\u01c0\u01c4\u0003\u001e\u000f\u0000\u01c1\u01c3\u0003,\u0016\u0000\u01c2"+
		"\u01c1\u0001\u0000\u0000\u0000\u01c3\u01c6\u0001\u0000\u0000\u0000\u01c4"+
		"\u01c2\u0001\u0000\u0000\u0000\u01c4\u01c5\u0001\u0000\u0000\u0000\u01c5"+
		"\u01d7\u0001\u0000\u0000\u0000\u01c6\u01c4\u0001\u0000\u0000\u0000\u01c7"+
		"\u01c9\u0005\u000e\u0000\u0000\u01c8\u01ca\u0003\u001a\r\u0000\u01c9\u01c8"+
		"\u0001\u0000\u0000\u0000\u01c9\u01ca\u0001\u0000\u0000\u0000\u01ca\u01cb"+
		"\u0001\u0000\u0000\u0000\u01cb\u01d7\u0003*\u0015\u0000\u01cc\u01ce\u0005"+
		"\u000f\u0000\u0000\u01cd\u01cf\u0003\u001a\r\u0000\u01ce\u01cd\u0001\u0000"+
		"\u0000\u0000\u01ce\u01cf\u0001\u0000\u0000\u0000\u01cf\u01d0\u0001\u0000"+
		"\u0000\u0000\u01d0\u01d7\u0003*\u0015\u0000\u01d1\u01d3\u0005\u0015\u0000"+
		"\u0000\u01d2\u01d4\u0003\u001a\r\u0000\u01d3\u01d2\u0001\u0000\u0000\u0000"+
		"\u01d3\u01d4\u0001\u0000\u0000\u0000\u01d4\u01d5\u0001\u0000\u0000\u0000"+
		"\u01d5\u01d7\u00036\u001b\u0000\u01d6\u01c0\u0001\u0000\u0000\u0000\u01d6"+
		"\u01c7\u0001\u0000\u0000\u0000\u01d6\u01cc\u0001\u0000\u0000\u0000\u01d6"+
		"\u01d1\u0001\u0000\u0000\u0000\u01d7/\u0001\u0000\u0000\u0000\u01d8\u01da"+
		"\u0003\u0014\n\u0000\u01d9\u01d8\u0001\u0000\u0000\u0000\u01d9\u01da\u0001"+
		"\u0000\u0000\u0000\u01da\u01db\u0001\u0000\u0000\u0000\u01db\u01dc\u0003"+
		"2\u0019\u0000\u01dc1\u0001\u0000\u0000\u0000\u01dd\u01de\u0005\u0001\u0000"+
		"\u0000\u01de\u01e2\u0005<\u0000\u0000\u01df\u01e1\u0003\u0004\u0002\u0000"+
		"\u01e0\u01df\u0001\u0000\u0000\u0000\u01e1\u01e4\u0001\u0000\u0000\u0000"+
		"\u01e2\u01e0\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000\u0000\u0000"+
		"\u01e3\u01e5\u0001\u0000\u0000\u0000\u01e4\u01e2\u0001\u0000\u0000\u0000"+
		"\u01e5\u01e7\u0005\u0002\u0000\u0000\u01e6\u01dd\u0001\u0000\u0000\u0000"+
		"\u01e7\u01ea\u0001\u0000\u0000\u0000\u01e8\u01e6\u0001\u0000\u0000\u0000"+
		"\u01e8\u01e9\u0001\u0000\u0000\u0000\u01e9\u01eb\u0001\u0000\u0000\u0000"+
		"\u01ea\u01e8\u0001\u0000\u0000\u0000\u01eb\u01ec\u00034\u001a\u0000\u01ec"+
		"3\u0001\u0000\u0000\u0000\u01ed\u01ee\u0005\u0001\u0000\u0000\u01ee\u01f2"+
		"\u0005=\u0000\u0000\u01ef\u01f1\u0003\u0004\u0002\u0000\u01f0\u01ef\u0001"+
		"\u0000\u0000\u0000\u01f1\u01f4\u0001\u0000\u0000\u0000\u01f2\u01f0\u0001"+
		"\u0000\u0000\u0000\u01f2\u01f3\u0001\u0000\u0000\u0000\u01f3\u01f5\u0001"+
		"\u0000\u0000\u0000\u01f4\u01f2\u0001\u0000\u0000\u0000\u01f5\u01f7\u0005"+
		"\u0002\u0000\u0000\u01f6\u01ed\u0001\u0000\u0000\u0000\u01f7\u01fa\u0001"+
		"\u0000\u0000\u0000\u01f8\u01f6\u0001\u0000\u0000\u0000\u01f8\u01f9\u0001"+
		"\u0000\u0000\u0000\u01f9\u01fe\u0001\u0000\u0000\u0000\u01fa\u01f8\u0001"+
		"\u0000\u0000\u0000\u01fb\u01fd\u0003,\u0016\u0000\u01fc\u01fb\u0001\u0000"+
		"\u0000\u0000\u01fd\u0200\u0001\u0000\u0000\u0000\u01fe\u01fc\u0001\u0000"+
		"\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000\u01ff5\u0001\u0000\u0000"+
		"\u0000\u0200\u01fe\u0001\u0000\u0000\u0000\u0201\u0202\u0003(\u0014\u0000"+
		"\u0202\u0203\u00036\u001b\u0000\u0203\u0216\u0001\u0000\u0000\u0000\u0204"+
		"\u0206\u0003,\u0016\u0000\u0205\u0204\u0001\u0000\u0000\u0000\u0206\u0209"+
		"\u0001\u0000\u0000\u0000\u0207\u0205\u0001\u0000\u0000\u0000\u0207\u0208"+
		"\u0001\u0000\u0000\u0000\u0208\u020a\u0001\u0000\u0000\u0000\u0209\u0207"+
		"\u0001\u0000\u0000\u0000\u020a\u020b\u0005\u0001\u0000\u0000\u020b\u020c"+
		"\u0005\u0016\u0000\u0000\u020c\u020d\u00038\u001c\u0000\u020d\u0213\u0005"+
		"\u0002\u0000\u0000\u020e\u020f\u0005\u0001\u0000\u0000\u020f\u0210\u0005"+
		"\u0017\u0000\u0000\u0210\u0211\u00038\u001c\u0000\u0211\u0212\u0005\u0002"+
		"\u0000\u0000\u0212\u0214\u0001\u0000\u0000\u0000\u0213\u020e\u0001\u0000"+
		"\u0000\u0000\u0213\u0214\u0001\u0000\u0000\u0000\u0214\u0216\u0001\u0000"+
		"\u0000\u0000\u0215\u0201\u0001\u0000\u0000\u0000\u0215\u0207\u0001\u0000"+
		"\u0000\u0000\u02167\u0001\u0000\u0000\u0000\u0217\u0219\u0003\u001c\u000e"+
		"\u0000\u0218\u0217\u0001\u0000\u0000\u0000\u0219\u021c\u0001\u0000\u0000"+
		"\u0000\u021a\u0218\u0001\u0000\u0000\u0000\u021a\u021b\u0001\u0000\u0000"+
		"\u0000\u021b9\u0001\u0000\u0000\u0000\u021c\u021a\u0001\u0000\u0000\u0000"+
		"\u021d\u021e\u00038\u001c\u0000\u021e;\u0001\u0000\u0000\u0000\u021f\u0220"+
		"\u0005\u0001\u0000\u0000\u0220\u0222\u0005:\u0000\u0000\u0221\u0223\u0003"+
		"\u001a\r\u0000\u0222\u0221\u0001\u0000\u0000\u0000\u0222\u0223\u0001\u0000"+
		"\u0000\u0000\u0223\u0224\u0001\u0000\u0000\u0000\u0224\u0225\u0003>\u001f"+
		"\u0000\u0225\u0226\u0005\u0002\u0000\u0000\u0226=\u0001\u0000\u0000\u0000"+
		"\u0227\u0229\u0003\u0014\n\u0000\u0228\u0227\u0001\u0000\u0000\u0000\u0228"+
		"\u0229\u0001\u0000\u0000\u0000\u0229\u022a\u0001\u0000\u0000\u0000\u022a"+
		"\u0235\u0003D\"\u0000\u022b\u022d\u0003d2\u0000\u022c\u022e\u0003\u0014"+
		"\n\u0000\u022d\u022c\u0001\u0000\u0000\u0000\u022d\u022e\u0001\u0000\u0000"+
		"\u0000\u022e\u022f\u0001\u0000\u0000\u0000\u022f\u0230\u0003@ \u0000\u0230"+
		"\u0235\u0001\u0000\u0000\u0000\u0231\u0232\u0003j5\u0000\u0232\u0233\u0003"+
		">\u001f\u0000\u0233\u0235\u0001\u0000\u0000\u0000\u0234\u0228\u0001\u0000"+
		"\u0000\u0000\u0234\u022b\u0001\u0000\u0000\u0000\u0234\u0231\u0001\u0000"+
		"\u0000\u0000\u0235?\u0001\u0000\u0000\u0000\u0236\u0237\u0005\u0001\u0000"+
		"\u0000\u0237\u023b\u0005<\u0000\u0000\u0238\u023a\u0003\u0004\u0002\u0000"+
		"\u0239\u0238\u0001\u0000\u0000\u0000\u023a\u023d\u0001\u0000\u0000\u0000"+
		"\u023b\u0239\u0001\u0000\u0000\u0000\u023b\u023c\u0001\u0000\u0000\u0000"+
		"\u023c\u023e\u0001\u0000\u0000\u0000\u023d\u023b\u0001\u0000\u0000\u0000"+
		"\u023e\u0246\u0005\u0002\u0000\u0000\u023f\u0240\u0005\u0001\u0000\u0000"+
		"\u0240\u0241\u0005<\u0000\u0000\u0241\u0242\u0003\u001a\r\u0000\u0242"+
		"\u0243\u0003\u0004\u0002\u0000\u0243\u0244\u0005\u0002\u0000\u0000\u0244"+
		"\u0246\u0001\u0000\u0000\u0000\u0245\u0236\u0001\u0000\u0000\u0000\u0245"+
		"\u023f\u0001\u0000\u0000\u0000\u0246\u0247\u0001\u0000\u0000\u0000\u0247"+
		"\u0248\u0003B!\u0000\u0248A\u0001\u0000\u0000\u0000\u0249\u024a\u0005"+
		"\u0001\u0000\u0000\u024a\u024e\u0005=\u0000\u0000\u024b\u024d\u0003\u0004"+
		"\u0002\u0000\u024c\u024b\u0001\u0000\u0000\u0000\u024d\u0250\u0001\u0000"+
		"\u0000\u0000\u024e\u024c\u0001\u0000\u0000\u0000\u024e\u024f\u0001\u0000"+
		"\u0000\u0000\u024f\u0251\u0001\u0000\u0000\u0000\u0250\u024e\u0001\u0000"+
		"\u0000\u0000\u0251\u0253\u0005\u0002\u0000\u0000\u0252\u0249\u0001\u0000"+
		"\u0000\u0000\u0253\u0256\u0001\u0000\u0000\u0000\u0254\u0252\u0001\u0000"+
		"\u0000\u0000\u0254\u0255\u0001\u0000\u0000\u0000\u0255C\u0001\u0000\u0000"+
		"\u0000\u0256\u0254\u0001\u0000\u0000\u0000\u0257\u0258\u0005\u0001\u0000"+
		"\u0000\u0258\u025c\u0005<\u0000\u0000\u0259\u025b\u0003\u0004\u0002\u0000"+
		"\u025a\u0259\u0001\u0000\u0000\u0000\u025b\u025e\u0001\u0000\u0000\u0000"+
		"\u025c\u025a\u0001\u0000\u0000\u0000\u025c\u025d\u0001\u0000\u0000\u0000"+
		"\u025d\u025f\u0001\u0000\u0000\u0000\u025e\u025c\u0001\u0000\u0000\u0000"+
		"\u025f\u0267\u0005\u0002\u0000\u0000\u0260\u0261\u0005\u0001\u0000\u0000"+
		"\u0261\u0262\u0005<\u0000\u0000\u0262\u0263\u0003\u001a\r\u0000\u0263"+
		"\u0264\u0003\u0004\u0002\u0000\u0264\u0265\u0005\u0002\u0000\u0000\u0265"+
		"\u0267\u0001\u0000\u0000\u0000\u0266\u0257\u0001\u0000\u0000\u0000\u0266"+
		"\u0260\u0001\u0000\u0000\u0000\u0267\u026a\u0001\u0000\u0000\u0000\u0268"+
		"\u0266\u0001\u0000\u0000\u0000\u0268\u0269\u0001\u0000\u0000\u0000\u0269"+
		"\u026b\u0001\u0000\u0000\u0000\u026a\u0268\u0001\u0000\u0000\u0000\u026b"+
		"\u026c\u0003F#\u0000\u026cE\u0001\u0000\u0000\u0000\u026d\u026e\u0005"+
		"\u0001\u0000\u0000\u026e\u0272\u0005=\u0000\u0000\u026f\u0271\u0003\u0004"+
		"\u0002\u0000\u0270\u026f\u0001\u0000\u0000\u0000\u0271\u0274\u0001\u0000"+
		"\u0000\u0000\u0272\u0270\u0001\u0000\u0000\u0000\u0272\u0273\u0001\u0000"+
		"\u0000\u0000\u0273\u0275\u0001\u0000\u0000\u0000\u0274\u0272\u0001\u0000"+
		"\u0000\u0000\u0275\u0277\u0005\u0002\u0000\u0000\u0276\u026d\u0001\u0000"+
		"\u0000\u0000\u0277\u027a\u0001\u0000\u0000\u0000\u0278\u0276\u0001\u0000"+
		"\u0000\u0000\u0278\u0279\u0001\u0000\u0000\u0000\u0279\u027b\u0001\u0000"+
		"\u0000\u0000\u027a\u0278\u0001\u0000\u0000\u0000\u027b\u027c\u0003H$\u0000"+
		"\u027cG\u0001\u0000\u0000\u0000\u027d\u027e\u0005\u0001\u0000\u0000\u027e"+
		"\u0282\u0005>\u0000\u0000\u027f\u0281\u0003\u0004\u0002\u0000\u0280\u027f"+
		"\u0001\u0000\u0000\u0000\u0281\u0284\u0001\u0000\u0000\u0000\u0282\u0280"+
		"\u0001\u0000\u0000\u0000\u0282\u0283\u0001\u0000\u0000\u0000\u0283\u0285"+
		"\u0001\u0000\u0000\u0000\u0284\u0282\u0001\u0000\u0000\u0000\u0285\u028d"+
		"\u0005\u0002\u0000\u0000\u0286\u0287\u0005\u0001\u0000\u0000\u0287\u0288"+
		"\u0005>\u0000\u0000\u0288\u0289\u0003\u001a\r\u0000\u0289\u028a\u0003"+
		"\u0004\u0002\u0000\u028a\u028b\u0005\u0002\u0000\u0000\u028b\u028d\u0001"+
		"\u0000\u0000\u0000\u028c\u027d\u0001\u0000\u0000\u0000\u028c\u0286\u0001"+
		"\u0000\u0000\u0000\u028d\u0290\u0001\u0000\u0000\u0000\u028e\u028c\u0001"+
		"\u0000\u0000\u0000\u028e\u028f\u0001\u0000\u0000\u0000\u028f\u0291\u0001"+
		"\u0000\u0000\u0000\u0290\u028e\u0001\u0000\u0000\u0000\u0291\u0292\u0003"+
		"8\u001c\u0000\u0292I\u0001\u0000\u0000\u0000\u0293\u0294\u0005\u0001\u0000"+
		"\u0000\u0294\u0295\u0005D\u0000\u0000\u0295\u0296\u0003:\u001d\u0000\u0296"+
		"\u0297\u0005\u0002\u0000\u0000\u0297\u029a\u0001\u0000\u0000\u0000\u0298"+
		"\u029a\u0003,\u0016\u0000\u0299\u0293\u0001\u0000\u0000\u0000\u0299\u0298"+
		"\u0001\u0000\u0000\u0000\u029aK\u0001\u0000\u0000\u0000\u029b\u029c\u0005"+
		"\u0001\u0000\u0000\u029c\u029e\u0005B\u0000\u0000\u029d\u029f\u0003\u0018"+
		"\f\u0000\u029e\u029d\u0001\u0000\u0000\u0000\u029e\u029f\u0001\u0000\u0000"+
		"\u0000\u029f\u02a1\u0001\u0000\u0000\u0000\u02a0\u02a2\u0003P(\u0000\u02a1"+
		"\u02a0\u0001\u0000\u0000\u0000\u02a1\u02a2\u0001\u0000\u0000\u0000\u02a2"+
		"\u02a3\u0001\u0000\u0000\u0000\u02a3\u02a5\u0003J%\u0000\u02a4\u02a6\u0003"+
		"N\'\u0000\u02a5\u02a4\u0001\u0000\u0000\u0000\u02a5\u02a6\u0001\u0000"+
		"\u0000\u0000\u02a6\u02a7\u0001\u0000\u0000\u0000\u02a7\u02a8\u0005\u0002"+
		"\u0000\u0000\u02a8M\u0001\u0000\u0000\u0000\u02a9\u02ab\u0005:\u0000\u0000"+
		"\u02aa\u02ac\u0003\u0018\f\u0000\u02ab\u02aa\u0001\u0000\u0000\u0000\u02ac"+
		"\u02ad\u0001\u0000\u0000\u0000\u02ad\u02ab\u0001\u0000\u0000\u0000\u02ad"+
		"\u02ae\u0001\u0000\u0000\u0000\u02aeO\u0001\u0000\u0000\u0000\u02af\u02b0"+
		"\u0005\u0001\u0000\u0000\u02b0\u02b1\u0005@\u0000\u0000\u02b1\u02b2\u0003"+
		"\u0018\f\u0000\u02b2\u02b3\u0005\u0002\u0000\u0000\u02b3Q\u0001\u0000"+
		"\u0000\u0000\u02b4\u02b5\u0005\u0001\u0000\u0000\u02b5\u02b7\u0005@\u0000"+
		"\u0000\u02b6\u02b8\u0003\u001a\r\u0000\u02b7\u02b6\u0001\u0000\u0000\u0000"+
		"\u02b7\u02b8\u0001\u0000\u0000\u0000\u02b8\u02b9\u0001\u0000\u0000\u0000"+
		"\u02b9\u02ba\u0003T*\u0000\u02ba\u02bb\u0005\u0002\u0000\u0000\u02bbS"+
		"\u0001\u0000\u0000\u0000\u02bc\u02cf\u0003\u000e\u0007\u0000\u02bd\u02be"+
		"\u0003d2\u0000\u02be\u02bf\u0003\u000e\u0007\u0000\u02bf\u02cf\u0001\u0000"+
		"\u0000\u0000\u02c0\u02c1\u0003j5\u0000\u02c1\u02c2\u0003T*\u0000\u02c2"+
		"\u02cf\u0001\u0000\u0000\u0000\u02c3\u02c4\u0003\u0006\u0003\u0000\u02c4"+
		"\u02c5\u0005\u0001\u0000\u0000\u02c5\u02c9\u0005B\u0000\u0000\u02c6\u02c8"+
		"\u0003\u0018\f\u0000\u02c7\u02c6\u0001\u0000\u0000\u0000\u02c8\u02cb\u0001"+
		"\u0000\u0000\u0000\u02c9\u02c7\u0001\u0000\u0000\u0000\u02c9\u02ca\u0001"+
		"\u0000\u0000\u0000\u02ca\u02cc\u0001\u0000\u0000\u0000\u02cb\u02c9\u0001"+
		"\u0000\u0000\u0000\u02cc\u02cd\u0005\u0002\u0000\u0000\u02cd\u02cf\u0001"+
		"\u0000\u0000\u0000\u02ce\u02bc\u0001\u0000\u0000\u0000\u02ce\u02bd\u0001"+
		"\u0000\u0000\u0000\u02ce\u02c0\u0001\u0000\u0000\u0000\u02ce\u02c3\u0001"+
		"\u0000\u0000\u0000\u02cfU\u0001\u0000\u0000\u0000\u02d0\u02d1\u0005\u0001"+
		"\u0000\u0000\u02d1\u02d3\u0005C\u0000\u0000\u02d2\u02d4\u0003\u0018\f"+
		"\u0000\u02d3\u02d2\u0001\u0000\u0000\u0000\u02d3\u02d4\u0001\u0000\u0000"+
		"\u0000\u02d4\u02d6\u0001\u0000\u0000\u0000\u02d5\u02d7\u0003J%\u0000\u02d6"+
		"\u02d5\u0001\u0000\u0000\u0000\u02d6\u02d7\u0001\u0000\u0000\u0000\u02d7"+
		"\u02db\u0001\u0000\u0000\u0000\u02d8\u02da\u0005\u0006\u0000\u0000\u02d9"+
		"\u02d8\u0001\u0000\u0000\u0000\u02da\u02dd\u0001\u0000\u0000\u0000\u02db"+
		"\u02d9\u0001\u0000\u0000\u0000\u02db\u02dc\u0001\u0000\u0000\u0000\u02dc"+
		"\u02de\u0001\u0000\u0000\u0000\u02dd\u02db\u0001\u0000\u0000\u0000\u02de"+
		"\u02df\u0005\u0002\u0000\u0000\u02dfW\u0001\u0000\u0000\u0000\u02e0\u02e1"+
		"\u0005\u0001\u0000\u0000\u02e1\u02e3\u0005A\u0000\u0000\u02e2\u02e4\u0003"+
		"\u001a\r\u0000\u02e3\u02e2\u0001\u0000\u0000\u0000\u02e3\u02e4\u0001\u0000"+
		"\u0000\u0000\u02e4\u02e5\u0001\u0000\u0000\u0000\u02e5\u02e6\u0003Z-\u0000"+
		"\u02e6\u02e7\u0005\u0002\u0000\u0000\u02e7Y\u0001\u0000\u0000\u0000\u02e8"+
		"\u02f9\u0003\u0010\b\u0000\u02e9\u02ea\u0003d2\u0000\u02ea\u02eb\u0003"+
		"\u0010\b\u0000\u02eb\u02f9\u0001\u0000\u0000\u0000\u02ec\u02ed\u0003j"+
		"5\u0000\u02ed\u02ee\u0003Z-\u0000\u02ee\u02f9\u0001\u0000\u0000\u0000"+
		"\u02ef\u02f0\u0005\u0001\u0000\u0000\u02f0\u02f4\u0005C\u0000\u0000\u02f1"+
		"\u02f3\u0005\u0006\u0000\u0000\u02f2\u02f1\u0001\u0000\u0000\u0000\u02f3"+
		"\u02f6\u0001\u0000\u0000\u0000\u02f4\u02f2\u0001\u0000\u0000\u0000\u02f4"+
		"\u02f5\u0001\u0000\u0000\u0000\u02f5\u02f7\u0001\u0000\u0000\u0000\u02f6"+
		"\u02f4\u0001\u0000\u0000\u0000\u02f7\u02f9\u0005\u0002\u0000\u0000\u02f8"+
		"\u02e8\u0001\u0000\u0000\u0000\u02f8\u02e9\u0001\u0000\u0000\u0000\u02f8"+
		"\u02ec\u0001\u0000\u0000\u0000\u02f8\u02ef\u0001\u0000\u0000\u0000\u02f9"+
		"[\u0001\u0000\u0000\u0000\u02fa\u02fb\u0005\u0001\u0000\u0000\u02fb\u02fd"+
		"\u0005?\u0000\u0000\u02fc\u02fe\u0003\u001a\r\u0000\u02fd\u02fc\u0001"+
		"\u0000\u0000\u0000\u02fd\u02fe\u0001\u0000\u0000\u0000\u02fe\u02ff\u0001"+
		"\u0000\u0000\u0000\u02ff\u0300\u0003^/\u0000\u0300\u0301\u0005\u0002\u0000"+
		"\u0000\u0301]\u0001\u0000\u0000\u0000\u0302\u0303\u0003\b\u0004\u0000"+
		"\u0303\u0304\u0003:\u001d\u0000\u0304\u030c\u0001\u0000\u0000\u0000\u0305"+
		"\u0306\u0003d2\u0000\u0306\u0307\u0003\b\u0004\u0000\u0307\u030c\u0001"+
		"\u0000\u0000\u0000\u0308\u0309\u0003j5\u0000\u0309\u030a\u0003^/\u0000"+
		"\u030a\u030c\u0001\u0000\u0000\u0000\u030b\u0302\u0001\u0000\u0000\u0000"+
		"\u030b\u0305\u0001\u0000\u0000\u0000\u030b\u0308\u0001\u0000\u0000\u0000"+
		"\u030c_\u0001\u0000\u0000\u0000\u030d\u030e\u0005\u0001\u0000\u0000\u030e"+
		"\u0310\u0005:\u0000\u0000\u030f\u0311\u0003\u001a\r\u0000\u0310\u030f"+
		"\u0001\u0000\u0000\u0000\u0310\u0311\u0001\u0000\u0000\u0000\u0311\u0312"+
		"\u0001\u0000\u0000\u0000\u0312\u0313\u0003\u0014\n\u0000\u0313\u0314\u0005"+
		"\u0002\u0000\u0000\u0314\u0336\u0001\u0000\u0000\u0000\u0315\u0316\u0005"+
		"\u0001\u0000\u0000\u0316\u0318\u0005:\u0000\u0000\u0317\u0319\u0003\u001a"+
		"\r\u0000\u0318\u0317\u0001\u0000\u0000\u0000\u0318\u0319\u0001\u0000\u0000"+
		"\u0000\u0319\u031a\u0001\u0000\u0000\u0000\u031a\u031b\u0003\f\u0006\u0000"+
		"\u031b\u031c\u0005\u0002\u0000\u0000\u031c\u0336\u0001\u0000\u0000\u0000"+
		"\u031d\u031e\u0005\u0001\u0000\u0000\u031e\u0320\u0005@\u0000\u0000\u031f"+
		"\u0321\u0003\u001a\r\u0000\u0320\u031f\u0001\u0000\u0000\u0000\u0320\u0321"+
		"\u0001\u0000\u0000\u0000\u0321\u0322\u0001\u0000\u0000\u0000\u0322\u0323"+
		"\u0003\u000e\u0007\u0000\u0323\u0324\u0005\u0002\u0000\u0000\u0324\u0336"+
		"\u0001\u0000\u0000\u0000\u0325\u0326\u0005\u0001\u0000\u0000\u0326\u0328"+
		"\u0005A\u0000\u0000\u0327\u0329\u0003\u001a\r\u0000\u0328\u0327\u0001"+
		"\u0000\u0000\u0000\u0328\u0329\u0001\u0000\u0000\u0000\u0329\u032a\u0001"+
		"\u0000\u0000\u0000\u032a\u032b\u0003\u0010\b\u0000\u032b\u032c\u0005\u0002"+
		"\u0000\u0000\u032c\u0336\u0001\u0000\u0000\u0000\u032d\u032e\u0005\u0001"+
		"\u0000\u0000\u032e\u0330\u0005?\u0000\u0000\u032f\u0331\u0003\u001a\r"+
		"\u0000\u0330\u032f\u0001\u0000\u0000\u0000\u0330\u0331\u0001\u0000\u0000"+
		"\u0000\u0331\u0332\u0001\u0000\u0000\u0000\u0332\u0333\u0003\b\u0004\u0000"+
		"\u0333\u0334\u0005\u0002\u0000\u0000\u0334\u0336\u0001\u0000\u0000\u0000"+
		"\u0335\u030d\u0001\u0000\u0000\u0000\u0335\u0315\u0001\u0000\u0000\u0000"+
		"\u0335\u031d\u0001\u0000\u0000\u0000\u0335\u0325\u0001\u0000\u0000\u0000"+
		"\u0335\u032d\u0001\u0000\u0000\u0000\u0336a\u0001\u0000\u0000\u0000\u0337"+
		"\u0338\u0005\u0001\u0000\u0000\u0338\u0339\u0005E\u0000\u0000\u0339\u033a"+
		"\u0003\u0002\u0001\u0000\u033a\u033b\u0003\u0002\u0001\u0000\u033b\u033c"+
		"\u0003`0\u0000\u033c\u033d\u0005\u0002\u0000\u0000\u033dc\u0001\u0000"+
		"\u0000\u0000\u033e\u033f\u0005\u0001\u0000\u0000\u033f\u0340\u0005E\u0000"+
		"\u0000\u0340\u0341\u0003\u0002\u0001\u0000\u0341\u0342\u0003\u0002\u0001"+
		"\u0000\u0342\u0343\u0005\u0002\u0000\u0000\u0343e\u0001\u0000\u0000\u0000"+
		"\u0344\u0345\u0005\u0001\u0000\u0000\u0345\u0346\u0005:\u0000\u0000\u0346"+
		"\u0347\u0003\u0018\f\u0000\u0347\u0348\u0005\u0002\u0000\u0000\u0348\u0359"+
		"\u0001\u0000\u0000\u0000\u0349\u034a\u0005\u0001\u0000\u0000\u034a\u034b"+
		"\u0005@\u0000\u0000\u034b\u034c\u0003\u0018\f\u0000\u034c\u034d\u0005"+
		"\u0002\u0000\u0000\u034d\u0359\u0001\u0000\u0000\u0000\u034e\u034f\u0005"+
		"\u0001\u0000\u0000\u034f\u0350\u0005A\u0000\u0000\u0350\u0351\u0003\u0018"+
		"\f\u0000\u0351\u0352\u0005\u0002\u0000\u0000\u0352\u0359\u0001\u0000\u0000"+
		"\u0000\u0353\u0354\u0005\u0001\u0000\u0000\u0354\u0355\u0005?\u0000\u0000"+
		"\u0355\u0356\u0003\u0018\f\u0000\u0356\u0357\u0005\u0002\u0000\u0000\u0357"+
		"\u0359\u0001\u0000\u0000\u0000\u0358\u0344\u0001\u0000\u0000\u0000\u0358"+
		"\u0349\u0001\u0000\u0000\u0000\u0358\u034e\u0001\u0000\u0000\u0000\u0358"+
		"\u0353\u0001\u0000\u0000\u0000\u0359g\u0001\u0000\u0000\u0000\u035a\u035b"+
		"\u0005\u0001\u0000\u0000\u035b\u035c\u0005F\u0000\u0000\u035c\u035d\u0003"+
		"\u0002\u0001\u0000\u035d\u035e\u0003f3\u0000\u035e\u035f\u0005\u0002\u0000"+
		"\u0000\u035fi\u0001\u0000\u0000\u0000\u0360\u0361\u0005\u0001\u0000\u0000"+
		"\u0361\u0362\u0005F\u0000\u0000\u0362\u0363\u0003\u0002\u0001\u0000\u0363"+
		"\u0364\u0005\u0002\u0000\u0000\u0364k\u0001\u0000\u0000\u0000\u0365\u0366"+
		"\u0003\n\u0005\u0000\u0366m\u0001\u0000\u0000\u0000\u0367\u0368\u0005"+
		"\u0001\u0000\u0000\u0368\u036a\u00059\u0000\u0000\u0369\u036b\u0003\u001a"+
		"\r\u0000\u036a\u0369\u0001\u0000\u0000\u0000\u036a\u036b\u0001\u0000\u0000"+
		"\u0000\u036b\u036c\u0001\u0000\u0000\u0000\u036c\u036d\u0003l6\u0000\u036d"+
		"\u036e\u0005\u0002\u0000\u0000\u036eo\u0001\u0000\u0000\u0000\u036f\u0370"+
		"\u0005\u0001\u0000\u0000\u0370\u0371\u0005;\u0000\u0000\u0371\u0372\u0003"+
		"\u0018\f\u0000\u0372\u0373\u0005\u0002\u0000\u0000\u0373q\u0001\u0000"+
		"\u0000\u0000\u0374\u037f\u0003n7\u0000\u0375\u037f\u0003\\.\u0000\u0376"+
		"\u037f\u0003R)\u0000\u0377\u037f\u0003X,\u0000\u0378\u037f\u0003<\u001e"+
		"\u0000\u0379\u037f\u0003L&\u0000\u037a\u037f\u0003V+\u0000\u037b\u037f"+
		"\u0003p8\u0000\u037c\u037f\u0003b1\u0000\u037d\u037f\u0003h4\u0000\u037e"+
		"\u0374\u0001\u0000\u0000\u0000\u037e\u0375\u0001\u0000\u0000\u0000\u037e"+
		"\u0376\u0001\u0000\u0000\u0000\u037e\u0377\u0001\u0000\u0000\u0000\u037e"+
		"\u0378\u0001\u0000\u0000\u0000\u037e\u0379\u0001\u0000\u0000\u0000\u037e"+
		"\u037a\u0001\u0000\u0000\u0000\u037e\u037b\u0001\u0000\u0000\u0000\u037e"+
		"\u037c\u0001\u0000\u0000\u0000\u037e\u037d\u0001\u0000\u0000\u0000\u037f"+
		"s\u0001\u0000\u0000\u0000\u0380\u0381\u0005\u0001\u0000\u0000\u0381\u0383"+
		"\u0005G\u0000\u0000\u0382\u0384\u0005X\u0000\u0000\u0383\u0382\u0001\u0000"+
		"\u0000\u0000\u0383\u0384\u0001\u0000\u0000\u0000\u0384\u0388\u0001\u0000"+
		"\u0000\u0000\u0385\u0387\u0003r9\u0000\u0386\u0385\u0001\u0000\u0000\u0000"+
		"\u0387\u038a\u0001\u0000\u0000\u0000\u0388\u0386\u0001\u0000\u0000\u0000"+
		"\u0388\u0389\u0001\u0000\u0000\u0000\u0389\u038b\u0001\u0000\u0000\u0000"+
		"\u038a\u0388\u0001\u0000\u0000\u0000\u038b\u038c\u0005\u0002\u0000\u0000"+
		"\u038cu\u0001\u0000\u0000\u0000\u038d\u039c\u0003t:\u0000\u038e\u038f"+
		"\u0005\u0001\u0000\u0000\u038f\u0391\u0005G\u0000\u0000\u0390\u0392\u0005"+
		"X\u0000\u0000\u0391\u0390\u0001\u0000\u0000\u0000\u0391\u0392\u0001\u0000"+
		"\u0000\u0000\u0392\u0393\u0001\u0000\u0000\u0000\u0393\u0397\u0007\u0005"+
		"\u0000\u0000\u0394\u0396\u0005\u0006\u0000\u0000\u0395\u0394\u0001\u0000"+
		"\u0000\u0000\u0396\u0399\u0001\u0000\u0000\u0000\u0397\u0395\u0001\u0000"+
		"\u0000\u0000\u0397\u0398\u0001\u0000\u0000\u0000\u0398\u039a\u0001\u0000"+
		"\u0000\u0000\u0399\u0397\u0001\u0000\u0000\u0000\u039a\u039c\u0005\u0002"+
		"\u0000\u0000\u039b\u038d\u0001\u0000\u0000\u0000\u039b\u038e\u0001\u0000"+
		"\u0000\u0000\u039cw\u0001\u0000\u0000\u0000\u039d\u039e\u0005\u0001\u0000"+
		"\u0000\u039e\u03a0\u0005L\u0000\u0000\u039f\u03a1\u0005X\u0000\u0000\u03a0"+
		"\u039f\u0001\u0000\u0000\u0000\u03a0\u03a1\u0001\u0000\u0000\u0000\u03a1"+
		"\u03a2\u0001\u0000\u0000\u0000\u03a2\u03a3\u0003\u0002\u0001\u0000\u03a3"+
		"\u03a4\u0003\u0082A\u0000\u03a4\u03a5\u0005\u0002\u0000\u0000\u03a5\u03af"+
		"\u0001\u0000\u0000\u0000\u03a6\u03a7\u0005\u0001\u0000\u0000\u03a7\u03a9"+
		"\u0005M\u0000\u0000\u03a8\u03aa\u0005X\u0000\u0000\u03a9\u03a8\u0001\u0000"+
		"\u0000\u0000\u03a9\u03aa\u0001\u0000\u0000\u0000\u03aa\u03ab\u0001\u0000"+
		"\u0000\u0000\u03ab\u03ac\u0003\u0002\u0001\u0000\u03ac\u03ad\u0005\u0002"+
		"\u0000\u0000\u03ad\u03af\u0001\u0000\u0000\u0000\u03ae\u039d\u0001\u0000"+
		"\u0000\u0000\u03ae\u03a6\u0001\u0000\u0000\u0000\u03afy\u0001\u0000\u0000"+
		"\u0000\u03b0\u03b1\u0005\u0001\u0000\u0000\u03b1\u03b2\u0005N\u0000\u0000"+
		"\u03b2\u03b3\u0003v;\u0000\u03b3\u03b4\u0005\u0006\u0000\u0000\u03b4\u03b5"+
		"\u0005\u0002\u0000\u0000\u03b5\u03e5\u0001\u0000\u0000\u0000\u03b6\u03b7"+
		"\u0005\u0001\u0000\u0000\u03b7\u03b8\u0005O\u0000\u0000\u03b8\u03b9\u0003"+
		"v;\u0000\u03b9\u03ba\u0005\u0006\u0000\u0000\u03ba\u03bb\u0005\u0002\u0000"+
		"\u0000\u03bb\u03e5\u0001\u0000\u0000\u0000\u03bc\u03bd\u0005\u0001\u0000"+
		"\u0000\u03bd\u03be\u0005P\u0000\u0000\u03be\u03bf\u0003v;\u0000\u03bf"+
		"\u03c0\u0005\u0006\u0000\u0000\u03c0\u03c1\u0005\u0002\u0000\u0000\u03c1"+
		"\u03e5\u0001\u0000\u0000\u0000\u03c2\u03c3\u0005\u0001\u0000\u0000\u03c3"+
		"\u03c4\u0005T\u0000\u0000\u03c4\u03c5\u0003v;\u0000\u03c5\u03c6\u0005"+
		"\u0006\u0000\u0000\u03c6\u03c7\u0005\u0002\u0000\u0000\u03c7\u03e5\u0001"+
		"\u0000\u0000\u0000\u03c8\u03c9\u0005\u0001\u0000\u0000\u03c9\u03ca\u0005"+
		"Q\u0000\u0000\u03ca\u03cb\u0003x<\u0000\u03cb\u03cc\u0003\u0082A\u0000"+
		"\u03cc\u03cd\u0005\u0002\u0000\u0000\u03cd\u03e5\u0001\u0000\u0000\u0000"+
		"\u03ce\u03cf\u0005\u0001\u0000\u0000\u03cf\u03d0\u0005R\u0000\u0000\u03d0"+
		"\u03d1\u0003x<\u0000\u03d1\u03d2\u0005\u0002\u0000\u0000\u03d2\u03e5\u0001"+
		"\u0000\u0000\u0000\u03d3\u03d4\u0005\u0001\u0000\u0000\u03d4\u03d5\u0005"+
		"S\u0000\u0000\u03d5\u03d6\u0003x<\u0000\u03d6\u03d7\u0005\u0002\u0000"+
		"\u0000\u03d7\u03e5\u0001\u0000\u0000\u0000\u03d8\u03d9\u0005\u0001\u0000"+
		"\u0000\u03d9\u03da\u0005T\u0000\u0000\u03da\u03db\u0003x<\u0000\u03db"+
		"\u03dc\u0005\u0006\u0000\u0000\u03dc\u03dd\u0005\u0002\u0000\u0000\u03dd"+
		"\u03e5\u0001\u0000\u0000\u0000\u03de\u03df\u0005\u0001\u0000\u0000\u03df"+
		"\u03e0\u0005U\u0000\u0000\u03e0\u03e1\u0003x<\u0000\u03e1\u03e2\u0005"+
		"\u0006\u0000\u0000\u03e2\u03e3\u0005\u0002\u0000\u0000\u03e3\u03e5\u0001"+
		"\u0000\u0000\u0000\u03e4\u03b0\u0001\u0000\u0000\u0000\u03e4\u03b6\u0001"+
		"\u0000\u0000\u0000\u03e4\u03bc\u0001\u0000\u0000\u0000\u03e4\u03c2\u0001"+
		"\u0000\u0000\u0000\u03e4\u03c8\u0001\u0000\u0000\u0000\u03e4\u03ce\u0001"+
		"\u0000\u0000\u0000\u03e4\u03d3\u0001\u0000\u0000\u0000\u03e4\u03d8\u0001"+
		"\u0000\u0000\u0000\u03e4\u03de\u0001\u0000\u0000\u0000\u03e5{\u0001\u0000"+
		"\u0000\u0000\u03e6\u03f3\u0003x<\u0000\u03e7\u03f3\u0003z=\u0000\u03e8"+
		"\u03f3\u0003v;\u0000\u03e9\u03ea\u0005\u0001\u0000\u0000\u03ea\u03eb\u0005"+
		"K\u0000\u0000\u03eb\u03ed\u0003\u0002\u0001\u0000\u03ec\u03ee\u0005X\u0000"+
		"\u0000\u03ed\u03ec\u0001\u0000\u0000\u0000\u03ed\u03ee\u0001\u0000\u0000"+
		"\u0000\u03ee\u03ef\u0001\u0000\u0000\u0000\u03ef\u03f0\u0005\u0002\u0000"+
		"\u0000\u03f0\u03f3\u0001\u0000\u0000\u0000\u03f1\u03f3\u0003~?\u0000\u03f2"+
		"\u03e6\u0001\u0000\u0000\u0000\u03f2\u03e7\u0001\u0000\u0000\u0000\u03f2"+
		"\u03e8\u0001\u0000\u0000\u0000\u03f2\u03e9\u0001\u0000\u0000\u0000\u03f2"+
		"\u03f1\u0001\u0000\u0000\u0000\u03f3}\u0001\u0000\u0000\u0000\u03f4\u03f5"+
		"\u0005\u0001\u0000\u0000\u03f5\u03f7\u0005J\u0000\u0000\u03f6\u03f8\u0005"+
		"X\u0000\u0000\u03f7\u03f6\u0001\u0000\u0000\u0000\u03f7\u03f8\u0001\u0000"+
		"\u0000\u0000\u03f8\u03fc\u0001\u0000\u0000\u0000\u03f9\u03fb\u0003|>\u0000"+
		"\u03fa\u03f9\u0001\u0000\u0000\u0000\u03fb\u03fe\u0001\u0000\u0000\u0000"+
		"\u03fc\u03fa\u0001\u0000\u0000\u0000\u03fc\u03fd\u0001\u0000\u0000\u0000"+
		"\u03fd\u03ff\u0001\u0000\u0000\u0000\u03fe\u03fc\u0001\u0000\u0000\u0000"+
		"\u03ff\u0415\u0005\u0002\u0000\u0000\u0400\u0401\u0005\u0001\u0000\u0000"+
		"\u0401\u0403\u0005V\u0000\u0000\u0402\u0404\u0005X\u0000\u0000\u0403\u0402"+
		"\u0001\u0000\u0000\u0000\u0403\u0404\u0001\u0000\u0000\u0000\u0404\u0405"+
		"\u0001\u0000\u0000\u0000\u0405\u0406\u0005\u0006\u0000\u0000\u0406\u0415"+
		"\u0005\u0002\u0000\u0000\u0407\u0408\u0005\u0001\u0000\u0000\u0408\u040a"+
		"\u0005W\u0000\u0000\u0409\u040b\u0005X\u0000\u0000\u040a\u0409\u0001\u0000"+
		"\u0000\u0000\u040a\u040b\u0001\u0000\u0000\u0000\u040b\u040c\u0001\u0000"+
		"\u0000\u0000\u040c\u040d\u0005\u0006\u0000\u0000\u040d\u0415\u0005\u0002"+
		"\u0000\u0000\u040e\u040f\u0005\u0001\u0000\u0000\u040f\u0411\u0005W\u0000"+
		"\u0000\u0410\u0412\u0005X\u0000\u0000\u0411\u0410\u0001\u0000\u0000\u0000"+
		"\u0411\u0412\u0001\u0000\u0000\u0000\u0412\u0413\u0001\u0000\u0000\u0000"+
		"\u0413\u0415\u0005\u0002\u0000\u0000\u0414\u03f4\u0001\u0000\u0000\u0000"+
		"\u0414\u0400\u0001\u0000\u0000\u0000\u0414\u0407\u0001\u0000\u0000\u0000"+
		"\u0414\u040e\u0001\u0000\u0000\u0000\u0415\u007f\u0001\u0000\u0000\u0000"+
		"\u0416\u0417\u0005\u0001\u0000\u0000\u0417\u0418\u0005\b\u0000\u0000\u0418"+
		"\u0419\u0003\u0016\u000b\u0000\u0419\u041a\u0005\u0002\u0000\u0000\u041a"+
		"\u0081\u0001\u0000\u0000\u0000\u041b\u041d\u0003\u0080@\u0000\u041c\u041b"+
		"\u0001\u0000\u0000\u0000\u041d\u0420\u0001\u0000\u0000\u0000\u041e\u041c"+
		"\u0001\u0000\u0000\u0000\u041e\u041f\u0001\u0000\u0000\u0000\u041f\u0083"+
		"\u0001\u0000\u0000\u0000\u0420\u041e\u0001\u0000\u0000\u0000\u0421\u0423"+
		"\u0003|>\u0000\u0422\u0421\u0001\u0000\u0000\u0000\u0423\u0426\u0001\u0000"+
		"\u0000\u0000\u0424\u0422\u0001\u0000\u0000\u0000\u0424\u0425\u0001\u0000"+
		"\u0000\u0000\u0425\u0427\u0001\u0000\u0000\u0000\u0426\u0424\u0001\u0000"+
		"\u0000\u0000\u0427\u0430\u0005\u0000\u0000\u0001\u0428\u042a\u0003r9\u0000"+
		"\u0429\u0428\u0001\u0000\u0000\u0000\u042a\u042b\u0001\u0000\u0000\u0000"+
		"\u042b\u0429\u0001\u0000\u0000\u0000\u042b\u042c\u0001\u0000\u0000\u0000"+
		"\u042c\u042d\u0001\u0000\u0000\u0000\u042d\u042e\u0005\u0000\u0000\u0001"+
		"\u042e\u0430\u0001\u0000\u0000\u0000\u042f\u0424\u0001\u0000\u0000\u0000"+
		"\u042f\u0429\u0001\u0000\u0000\u0000\u0430\u0085\u0001\u0000\u0000\u0000"+
		"\u0431\u0432\u0003t:\u0000\u0432\u0433\u0005\u0000\u0000\u0001\u0433\u043c"+
		"\u0001\u0000\u0000\u0000\u0434\u0436\u0003r9\u0000\u0435\u0434\u0001\u0000"+
		"\u0000\u0000\u0436\u0439\u0001\u0000\u0000\u0000\u0437\u0435\u0001\u0000"+
		"\u0000\u0000\u0437\u0438\u0001\u0000\u0000\u0000\u0438\u043a\u0001\u0000"+
		"\u0000\u0000\u0439\u0437\u0001\u0000\u0000\u0000\u043a\u043c\u0005\u0000"+
		"\u0000\u0001\u043b\u0431\u0001\u0000\u0000\u0000\u043b\u0437\u0001\u0000"+
		"\u0000\u0000\u043c\u0087\u0001\u0000\u0000\u0000\u0088\u0096\u00a2\u00a9"+
		"\u00b0\u00b5\u00ba\u00c0\u00c3\u00d5\u00dc\u00e6\u00ed\u00fe\u0101\u0104"+
		"\u0108\u010b\u010e\u0112\u0115\u0119\u011d\u0121\u0124\u0128\u012c\u0131"+
		"\u0134\u0137\u013b\u013e\u0141\u0145\u0148\u014b\u014f\u0152\u0155\u0159"+
		"\u015c\u015f\u0163\u0166\u0169\u0172\u017e\u0184\u018e\u0194\u0199\u019e"+
		"\u01a2\u01a7\u01aa\u01ae\u01b0\u01b8\u01c4\u01c9\u01ce\u01d3\u01d6\u01d9"+
		"\u01e2\u01e8\u01f2\u01f8\u01fe\u0207\u0213\u0215\u021a\u0222\u0228\u022d"+
		"\u0234\u023b\u0245\u024e\u0254\u025c\u0266\u0268\u0272\u0278\u0282\u028c"+
		"\u028e\u0299\u029e\u02a1\u02a5\u02ad\u02b7\u02c9\u02ce\u02d3\u02d6\u02db"+
		"\u02e3\u02f4\u02f8\u02fd\u030b\u0310\u0318\u0320\u0328\u0330\u0335\u0358"+
		"\u036a\u037e\u0383\u0388\u0391\u0397\u039b\u03a0\u03a9\u03ae\u03e4\u03ed"+
		"\u03f2\u03f7\u03fc\u0403\u040a\u0411\u0414\u041e\u0424\u042b\u042f\u0437"+
		"\u043b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}