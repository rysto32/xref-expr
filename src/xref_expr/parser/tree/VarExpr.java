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
public class VarExpr extends Expression {
    private final AnnotatedValue var;

    public VarExpr(AnnotatedValue var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var.value;
    }
}
