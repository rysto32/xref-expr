/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xref_expr.parser.tree;

import xref_expr.scanner.AnnotatedValue;

/**
 *
 * @author rstone
 */
public class FullExpression extends Expression {
    private final Expression expr;
    private final AnnotatedValue trailingAnn;

    public FullExpression(Expression expr, AnnotatedValue trailingAnn) {
        this.expr = expr;
        this.trailingAnn = trailingAnn;
    }

    @Override
    public String toString() {
        return expr + trailingAnn.ann;
    }
}
