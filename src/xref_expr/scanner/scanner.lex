package xref_expr.scanner;

import java.util.*;
import java.util.regex.*;
import java.io.*;

import xref_expr.parser.*;
import lr_runtime.*;

%%

%public
%class Lexer
%implements lr_runtime.Scanner
%unicode
%function nextSymbol
%scanerror ScannerException
%type Token
%eofclose
%line
%column

%{
    private long serial = 0;

    private final StringBuilder string = new StringBuilder();
    private final StringBuilder macroName = new StringBuilder();
    
    private Token symbol(int type, String value) {
        Token t = new Symbol(type, yyline, yycolumn, value, serial);
        serial++;

        if(t == null) throw new NullPointerException();

        return t;
    }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

 /*comments */
Comment = {TraditionalComment} | {EndOfLineComment} 

TraditionalComment   = "/*" ~"*/" 
EndOfLineComment     = "//" ~{LineTerminator}

OctalDigit = [0-7];
HexDigit = [0-9a-fA-F];

OctalChar = ({OctalDigit}|{OctalDigit}{OctalDigit}|{OctalDigit}{OctalDigit}{OctalDigit})
HexChar = x({HexDigit}|{HexDigit}{HexDigit})

D = [0-9]
L = [a-zA-Z_$]

Identifier = {L}({L}|{D})*

NonNewlineWhitespace = [ \t]|{Comment}
Whitespace          = [ \t\n]|{Comment}

%xstate STRING, CHAR

%%

"*"         { return symbol(Sym.ASTERISK, yytext()); }
"&"         { return symbol(Sym.BITWISE_AND, yytext()); }
"-"         { return symbol(Sym.MINUS, yytext()); }
"!"         { return symbol(Sym.LOGICAL_NOT, yytext()); }
"~"         { return symbol(Sym.BITWISE_NOT, yytext()); }
"("         { return symbol(Sym.OPEN_P, yytext()); }
")"         { return symbol(Sym.CLOSE_P, yytext()); }
"?"         { return symbol(Sym.QUESTION_MARK, yytext()); }
":"         { return symbol(Sym.COLON, yytext()); }
","         { return symbol(Sym.COMMA, yytext()); }
"/"         { return symbol(Sym.SLASH, yytext()); }
"%"         { return symbol(Sym.MOD, yytext()); }
"<<"        { return symbol(Sym.SHIFT_LEFT, yytext()); }
">>"        { return symbol(Sym.SHIFT_RIGHT, yytext()); }
"<="        { return symbol(Sym.LESS_OR_EQUAL, yytext()); }
">="        { return symbol(Sym.GREATER_OR_EQUAL, yytext()); }
"<"         { return symbol(Sym.LESS_THAN, yytext()); }
">"         { return symbol(Sym.GREATER_THAN, yytext()); }
"=="        { return symbol(Sym.EQUALS, yytext()); }
"!="        { return symbol(Sym.NOT_EQUALS, yytext()); }
"^"         { return symbol(Sym.BITWISE_XOR, yytext()); }
"|"         { return symbol(Sym.BITWISE_OR, yytext()); }
"&&"        { return symbol(Sym.LOGICAL_AND, yytext()); }
"||"        { return symbol(Sym.LOGICAL_OR, yytext()); }
"+"         { return symbol(Sym.PLUS, yytext()); }
"..."       { return symbol(Sym.VARARGS, yytext()); }
";"         { return symbol(Sym.SEMICOLON, yytext()); }
"["|"<%"    { return symbol(Sym.OPEN_S, yytext()); }
"]"|"%>"    { return symbol(Sym.CLOSE_S, yytext()); }
"{"|"<:"    { return symbol(Sym.OPEN_B, yytext()); }
"}"|":>"    { return symbol(Sym.CLOSE_B, yytext()); }
"="         { return symbol(Sym.ASSIGN, yytext()); }
"*="        { return symbol(Sym.MULT_ASSIGN, yytext()); }
"/="        { return symbol(Sym.DIV_ASSIGN, yytext()); }
"%="        { return symbol(Sym.MOD_ASSIGN, yytext()); }
"+="        { return symbol(Sym.PLUS_ASSIGN, yytext()); }
"++"        { return symbol(Sym.INCREMENT, yytext()); }
"-="        { return symbol(Sym.SUB_ASSIGN, yytext()); }
"--"        { return symbol(Sym.DECREMENT, yytext()); }
"->"        { return symbol(Sym.POINTS_TO, yytext()); }
"&="        { return symbol(Sym.AND_ASSIGN, yytext()); }
"^="        { return symbol(Sym.XOR_ASSIGN, yytext()); }
"|="        { return symbol(Sym.OR_ASSIGN, yytext()); }
"."         { return symbol(Sym.DOT, yytext()); }
"<<="       { return symbol(Sym.LSHIFT_ASSIGN, yytext()); }
">>="       { return symbol(Sym.RSHIFT_ASSIGN, yytext()); }

L?\"      { string.setLength(0);  yybegin(STRING);  }

[1-9][0-9]*([uU]|[lL])*                       { return symbol(Sym.INT_CONSTANT, yytext()); }
0[xX][0-9a-fA-F]+([uU]|[lL])*                 { return symbol(Sym.INT_CONSTANT, yytext()); }
0[0-7]*([uU]|[lL])*                           { return symbol(Sym.INT_CONSTANT, yytext()); }
[0-9]+\\.[0-9]*([eE][-+]?[0-9]+)?             { return symbol(Sym.FLOAT_CONSTANT, yytext()); }
[0-9]*\\.[0-9]+([eE][-+]?[0-9]+)?             { return symbol(Sym.FLOAT_CONSTANT, yytext()); }
[0-9]+[eE][-+]?[0-9]+                         { return symbol(Sym.FLOAT_CONSTANT, yytext()); }
L?\'                                          { string.setLength(0); yybegin(CHAR); }


{Whitespace}+                        { } //ignore whitespace

"asm"       { return symbol(Sym.ASM, yytext()); }
"auto"      { return symbol(Sym.AUTO, yytext()); }
"break"     { return symbol(Sym.BREAK, yytext()); }
"case"      { return symbol(Sym.CASE, yytext()); }
"char"      { return symbol(Sym.CHAR, yytext()); }
"const"     { return symbol(Sym.CONST, yytext()); }
"continue"  { return symbol(Sym.CONTINUE, yytext()); }
"default"   { return symbol(Sym.DEFAULT, yytext()); }
"do"        { return symbol(Sym.DO, yytext()); }
"double"    { return symbol(Sym.DOUBLE, yytext()); }
"else"      { return symbol(Sym.ELSE, yytext()); }
"enum"      { return symbol(Sym.ENUM, yytext()); }
"extern"    { return symbol(Sym.EXTERN, yytext()); }
"float"     { return symbol(Sym.FLOAT, yytext()); }
"for"       { return symbol(Sym.FOR, yytext()); }
"goto"      { return symbol(Sym.GOTO, yytext()); }
"if"        { return symbol(Sym.IF, yytext()); }
"inline"|"__inline__"|"__inline"    { /*return symbol(Sym.INLINE, yytext());*/ }
"int"       { return symbol(Sym.INT, yytext()); }
"long"      { return symbol(Sym.LONG, yytext()); }
"register"  { return symbol(Sym.REGISTER, yytext()); }
"restrict"  { return symbol(Sym.RESTRICT, yytext()); }
"return"    { return symbol(Sym.RETURN, yytext()); }
"short"     { return symbol(Sym.SHORT, yytext()); }
"signed"|"__signed__"    { return symbol(Sym.SIGNED, yytext()); }
"sizeof"    { return symbol(Sym.SIZEOF, yytext()); }
"static"    { return symbol(Sym.STATIC, yytext()); }
"struct"    { return symbol(Sym.STRUCT, yytext()); }
"switch"    { return symbol(Sym.SWITCH, yytext()); }
"typedef"   { return symbol(Sym.TYPEDEF, yytext()); }
"union"     { return symbol(Sym.UNION, yytext()); }
"unsigned"  { return symbol(Sym.UNSIGNED, yytext()); }
"void"      { return symbol(Sym.VOID, yytext()); }
"volatile"  { return symbol(Sym.VOLATILE, yytext()); }
"while"     { return symbol(Sym.WHILE, yytext()); }


"_Bool"         { return symbol(Sym.BOOL, yytext()); }
"_Complex"      { return symbol(Sym.COMPLEX, yytext()); }
"_Imaginary"    { return symbol(Sym.IMAGINARY, yytext()); }

{Identifier}    { return symbol(Sym.IDENTIFIER, yytext()); }

<<EOF>>                         { return symbol(Sym.PARSER_EOF, null);  }
[^]           { throw new ScannerException("Can't match " + yytext() + " in " + stateNames.get(zzLexicalState)); }

<STRING> {
    "\\\""                          {   string.append( yytext() );  }
    "\""                            { 
                                        
                                        return symbol(Sym.STRING, string.toString()); 
                                    }
    [^\"\n\\]+                      {   string.append( yytext() );  }
    "\\"                            {   string.append( yytext() );  }
}

<CHAR> {
    \\\'                            {   string.append( yytext() );  }
    "\'"                            { 
                                        
                                        return symbol(Sym.CHAR_CONSTANT, string.toString()); 
                                    }
    [^\'\n\\]+                      {   string.append( yytext() );  }
    "\\"                            {   string.append( yytext() );  }
}


<<EOF>>   { throw new ScannerException("Unexpected EOF in state " + stateNames.get(zzLexicalState)); }

[^]           { throw new ScannerException("Can't match " + yytext() + " in " + stateNames.get(zzLexicalState)); }