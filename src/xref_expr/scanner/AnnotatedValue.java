/*
 * AnnotatedValue.java
 *
 * Created on June 27, 2006, 9:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xref_expr.scanner;

public class AnnotatedValue {
    public final String value;
    public final String ann;
    public final int symbol;
    public final long serial;
    
    public AnnotatedValue(String v, String a, int s, long ser) {
        value = v;
        ann = a;
        symbol = s;
        serial = ser;
    }
    
    public AnnotatedValue(int s, long ser) {
        value = null;
        ann = "";
        symbol = s;
        serial = ser;
    }
}
