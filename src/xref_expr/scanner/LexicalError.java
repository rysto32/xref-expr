/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xref_expr.scanner;

/**
 *
 * @author rstone
 */
public class LexicalError extends Exception {

    /**
     * Creates a new instance of <code>LexicalError</code> without detail
     * message.
     */
    public LexicalError() {
    }

    /**
     * Constructs an instance of <code>LexicalError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LexicalError(String msg) {
        super(msg);
    }
}
