/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xref_expr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import lr_runtime.TokenFactory;
import xref_expr.parser.ExprParser;
import xref_expr.parser.tree.Expression;
import xref_expr.scanner.Lexer;
import xref_expr.scanner.SymbolFactory;

/**
 *
 * @author rstone
 */
public class Main {
    
    public static Lexer buildLexer(File f) throws IOException {
        Lexer l = new Lexer(new BufferedReader(new FileReader(f)));
        
        return l;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ExprParser p = new ExprParser();
            Lexer lex = buildLexer(new File("test.in"));
            TokenFactory factory = new SymbolFactory();
            
            Expression e = (Expression)p.parse(lex, factory);
            
            System.out.println("File parsed correctly.");
            System.out.println(e.toString());
            
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}
