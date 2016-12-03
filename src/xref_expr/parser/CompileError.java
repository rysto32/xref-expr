/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xref_expr.parser;

/**
 *
 * @author rstone
 */
public class CompileError extends Exception {

    /**
     * Creates a new instance of <code>CompileError</code> without detail
     * message.
     */
    public CompileError() {
    }

    /**
     * Constructs an instance of <code>CompileError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CompileError(String msg) {
        super(msg);
    }
}
