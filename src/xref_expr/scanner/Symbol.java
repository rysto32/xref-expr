/*
 * Token.java
 *
 * Created on June 13, 2006, 4:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xref_expr.scanner;

import lr_runtime.*;

import xref_expr.parser.*;
import static xref_expr.parser.Sym.*;

/**
 *
 * @author rstone
 */
public class Symbol extends lr_runtime.Token {
    
    public Symbol(int id, int l, int r, String s, String ann, long ser) {
        super(id, new AnnotatedValue(s, ann, id, ser), l, r);
    }

    public Symbol(int id, int l, int r, String s, long ser) {
        this(id, l, r, s, "", ser);
    }
    
    public Symbol(int id, int l, int r, long ser) {
        super(id, new AnnotatedValue(id, ser), l, r);
    }
    
    public Symbol(int id, Object v, int l, int r) {
        super(id, v, l, r);
    }
    
    public AnnotatedValue getValue() {
        return (AnnotatedValue)value;
    }
    
    public String toString() {
        if (value != null) {
            return toString(sym, ((AnnotatedValue)value).value);
        } else {
            return toString(sym, null);
        }
    }
    
    /*public Symbol makeCopy(int symbol) {
        return new Symbol(symbol, new AnnotatedValue(getValue().value, sym, getValue().serial), line, column);
    }
    
    public Symbol makeCopy() {
        return makeCopy(sym);
    }*/
    
    public static String toString(int sym, String value) {
        switch(sym) {            
            case ASTERISK: return "*";
            case BITWISE_AND: return "&";
            case MINUS: return "-";
            case LOGICAL_NOT: return "!";
            case BITWISE_NOT: return "~";
            case OPEN_P: return "(";
            case CLOSE_P: return ")";
            case QUESTION_MARK: return "?";
            case COLON: return ":";
            case COMMA: return ",";
            case SLASH: return "/";
            case MOD: return "%";
            case SHIFT_LEFT: return "<<";
            case SHIFT_RIGHT: return ">>";
            case LESS_OR_EQUAL: return "<=";
            case GREATER_OR_EQUAL: return ">=";
            case LESS_THAN: return "<";
            case GREATER_THAN: return ">";
            case EQUALS: return "==";
            case NOT_EQUALS: return "!=";
            case BITWISE_XOR: return "^";
            case BITWISE_OR: return "|";
            case LOGICAL_AND: return "&&";
            case LOGICAL_OR: return "||";
            case PLUS: return "+";
            case VARARGS: return "...";
            case SEMICOLON: return ";";
            case OPEN_S: return "[";
            case CLOSE_S: return "]";
            case OPEN_B: return "{";
            case CLOSE_B: return "}";
            case ASSIGN: return "=";
            case MULT_ASSIGN: return "*=";
            case DIV_ASSIGN: return "/=";
            case MOD_ASSIGN: return "%=";
            case PLUS_ASSIGN: return "+=";
            case INCREMENT: return "++";
            case SUB_ASSIGN: return "-=";
            case DECREMENT: return "--";
            case POINTS_TO: return "->";
            case AND_ASSIGN: return "&=";
            case XOR_ASSIGN: return "^=";
            case OR_ASSIGN: return "|=";
            case DOT: return ".";
            case LSHIFT_ASSIGN: return "<<=";
            case RSHIFT_ASSIGN: return ">>=";

            case AUTO: return "auto";
            case BREAK: return "break";
            case CASE: return "case";
            case CHAR: return "char";
            case CONST: return "const";
            case CONTINUE: return "continue";
            case DEFAULT: return "default";
            case DO: return "do";
            case DOUBLE: return "double";
            case ELSE: return "else";
            case ENUM: return "enum";
            case EXTERN: return "extern";
            case FLOAT: return "float";
            case FOR: return "for";
            case GOTO: return "goto";
            case IF: return "if";
            case INLINE: return "inline";
            case INT: return "int";
            case LONG: return "long";
            case REGISTER: return "register";
            case RESTRICT: return "restrict";
            case RETURN: return "return";
            case SHORT: return "short";
            case SIGNED: return "signed";
            case SIZEOF: return "sizeof";
            case STATIC: return "static";
            case STRUCT: return "struct";
            case SWITCH: return "switch";
            case TYPEDEF: return "typedef";
            case UNION: return "union";
            case UNSIGNED: return "unsigned";
            case VOID: return "void";
            case VOLATILE: return "volatile";
            case WHILE: return "while";


            case BOOL: return "_Bool";
            case COMPLEX: return "_Complex";
            case IMAGINARY: return "_Imaginary";
                
            case INT_CONSTANT:
                return "int_const(" + value + ")";
                
            case FLOAT_CONSTANT:
                return "float_const(" + value + ")";
                
            case CHAR_CONSTANT:
                return "char_const(" + value + ")";
                
            case STRING:
                return "\"" + value + "\"";
                
            case TYPE_NAME:
                return "TYPE(" + value +")";
                
            case IDENTIFIER:
                return "ID(" + value + ")";
                
            case PARSER_EOF:
                 return "EOF";
                 
            case ASM:
                 return "ASM";
                 
            case STRINGIZE:
                return "#";
                
            case TOKEN_PASTE:
                return "##";

            case END_OF_FILE:
                return ("END_OF_FILE");
             
            default:
                throw new Error("Unknown symbol type " + sym);
        }
        
    }
    
    public boolean isText() {
        switch(sym) {
            case TYPE_NAME: 
            case IDENTIFIER: 
            case ASM: 
            case AUTO: 
            case BREAK: 
            case CASE: 
            case CHAR: 
            case CONST: 
            case CONTINUE: 
            case DEFAULT: 
            case DO: 
            case DOUBLE: 
            case ELSE: 
            case ENUM: 
            case EXTERN: 
            case FLOAT: 
            case FOR: 
            case GOTO: 
            case IF: 
            case INLINE: 
            case INT: 
            case LONG: 
            case REGISTER: 
            case RESTRICT: 
            case RETURN: 
            case SHORT: 
            case SIGNED: 
            case SIZEOF: 
            case STATIC: 
            case STRUCT: 
            case SWITCH: 
            case TYPEDEF: 
            case UNION: 
            case UNSIGNED: 
            case VOID: 
            case VOLATILE: 
            case WHILE: 
            case BOOL: 
            case COMPLEX: 
            case IMAGINARY:
                return true;
            default:
                return false;
        }
    }
}
