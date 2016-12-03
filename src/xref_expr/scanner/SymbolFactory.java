/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xref_expr.scanner;

import lr_runtime.Token;
import lr_runtime.TokenFactory;

/**
 *
 * @author rstone
 */
public class SymbolFactory implements TokenFactory{

    @Override
    public Token makeToken(int s, Object v, int l, int c) {
        return new Symbol(s, v, l, c);
    }
    
}
