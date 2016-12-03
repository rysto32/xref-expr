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
    public final Annotations ann;
    public final int symbol;
    public final long serial;
    
    public AnnotatedValue(String v, int s, long ser) {
        value = v;
        ann = new Annotations();
        symbol = s;
        serial = ser;
    }
    
    public AnnotatedValue(int s, long ser) {
        value = null;
        ann = new Annotations();
        symbol = s;
        serial = ser;
    }
}
