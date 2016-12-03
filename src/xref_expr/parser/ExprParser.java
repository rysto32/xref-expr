/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xref_expr.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import lr_runtime.Token;
import xref_expr.parser.tree.Expression;
import xref_expr.parser.tree.BinaryExpr;
import xref_expr.parser.tree.FullExpression;
import xref_expr.parser.tree.VarExpr;
import xref_expr.scanner.AnnotatedValue;

/**
 *
 * @author rstone
 */
public class ExprParser extends Parser {

    @Override
    protected Expression Expression(int line, int col, Expression arg0, Token<AnnotatedValue> arg1) {
        return new FullExpression(arg0, arg1.value);
    }

    @Override
    protected Expression AddExpression(int line, int col, Expression arg0, Token<AnnotatedValue> arg1, Expression arg2) {
        return new BinaryExpr(arg0, arg1.value, arg2);
    }

    @Override
    protected Expression AddExpression(int line, int col, Expression arg0) {
        return arg0;
    }

    @Override
    protected VarExpr VarExpression(int line, int col, Token<AnnotatedValue> arg0) {
        return new VarExpr(arg0.value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void suggestRepair(List<Token> list, NavigableSet<Integer> changes) {
        System.err.print("  Did you mean:\n  ");
        List<Integer> tokenWidth = new ArrayList<>(list.size());
        int start = changes.first();
        for(Token t : list.subList(Math.max(start - 3, 0), list.size())) {
            String s = t.toString();
            System.err.print(s);
            System.err.print(" ");
            
            tokenWidth.add(s.length());
        }
        System.err.print("\n  ");
        
        for(int i = 0; i < tokenWidth.size(); i++) {
           
            int width = tokenWidth.get(i);
            for(int j = 0; j < width; j++) {
                if(changes.contains(i)) {
                    System.err.print("^");
                } else {
                    System.err.print(" ");
                }
            }
            
            System.err.print(" ");
        }
        
        System.err.println("");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void reportError(Token t, int state, List<String> possible) {
        System.err.println("Error @ line " + t.getLine() + " column " + t.getColumn() + ": " +
                "caused by token " + t + "(" + t.getSym() + ") possible=" + possible);
    }


    @Override
    @SuppressWarnings("unchecked")
    public void reportGiveUp(Token t) {
        System.err.println("Error @ line " + t.getLine() + " column " + t.getColumn() + ": " +
                "Too many parse errors; bailing out");
        throw new CompileError();
    }
}
