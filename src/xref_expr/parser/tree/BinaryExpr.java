/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xref_expr.parser.tree;

import xref_expr.scanner.*;

/**
 *
 * @author rstone
 */
public class BinaryExpr extends Expression {
    private final Expression left;
    private final AnnotatedValue op_ann;
    private final Expression right;

    public BinaryExpr(Expression left, AnnotatedValue op_ann, Expression right) {
        this.left = left;
        this.op_ann = op_ann;
        this.right = right;
    }

    @Override
    public String toString() {
        return left + op_ann.ann + op_ann.value + right;
    }
}
