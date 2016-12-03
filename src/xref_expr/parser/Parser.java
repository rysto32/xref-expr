package xref_expr.parser;

import lr_runtime.*;
import java.util.*;
import xref_expr.scanner.*;
import xref_expr.parser.tree.*;

public abstract class Parser extends lr_runtime.LrParser {
    private static final String [] ENCODED_TABLE = {
        "F1B88000000000000000B53F69185B8B8814437A82EB8A43FA42337355F2021B8A8353421392725517BC987CF04B659E1313039F0307726279466E7E1852A48140C72B21BC215FB4B423374FD723B8B4CAD781833D3FB42F1A2DCF842DEB98506D515004B4481426A70213D37C2A2A4CA409EBA86CB42B33F872EC5666064F460692ECCAA4DA8206060606F276101904D42880D4E1985C91043C859DF6D1C3C269071999189CD818B272F3135CD08E4BCF22F460EC29C82A4D2EC8CFC949A820B77060010E927E00292004CC8073C27213F2D5FC33FA4253D35B848E1D2852FDB1BDD289046F3B69526E496A65411380024D9F596E625A615BDA99A2BCD35E14733130308DD711043C40092CC2833233DA42343A57DC5771BC010A95E3C0C2929A5C5252C02C090800915AF05B21418158C057C0027813750899C681EA5628B216009BB19CAA0A4D492D4E4DEF32BF455EDDEE5A28DDDC350549F0414054F876A08DD7C807DE29026801E4B6042C200694148175814488219A3891EA34C984D1C006D106E273CD59C68B433324433A6018362727A614981DCE38F64049C4F9283E40FAE7E2011CB51448301352991EE6C8583B851ECA8783B01116170761B4508852D8FC96A2075CC85151006B9E9368D8300000"
    };

    public Parser() {
        super(ENCODED_TABLE);
    }

    @Override
    protected int getProductionLength(int prodId) {
        switch(prodId) {
            case 0: return 1;
            case 1: return 3;
            case 2: return 1;
            case 3: return 1;
            case 4: return 1;
        }

        throw new Error("Bad production id " + prodId);
    }

    @Override
    protected int getLhsSymbol(int prodId) {
        switch(prodId) {
            case 0: return Sym.expr;//expr --> add_expr 
            case 1: return Sym.add_expr;//add_expr --> add_expr PLUS var_expr 
            case 2: return Sym.add_expr;//add_expr --> var_expr 
            case 3: return Sym.var_expr;//var_expr --> IDENTIFIER 
        }

        throw new Error("Bad production id " + prodId);
    }

    @Override
    protected int getStartState() {
        return 0;
    }

    @Override
    protected int getNumSymbols() {
        return 95;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Object performAction(int prodId, java.util.List<lr_runtime.Token> rhs) {
        lr_runtime.Token first;
        switch(prodId) {
            case 0:
                first = findFirst(rhs);
                return Expression(first == null ? -1 : first.getLine(), first == null ? -1 : first.getColumn(), (Expression)rhs.get(0).value);
            case 1:
                first = findFirst(rhs);
                return AddExpression(first == null ? -1 : first.getLine(), first == null ? -1 : first.getColumn(), (Expression)rhs.get(0).value, (lr_runtime.Token<AnnotatedValue>)rhs.get(1), (Expression)rhs.get(2).value);
            case 2:
                first = findFirst(rhs);
                return AddExpression(first == null ? -1 : first.getLine(), first == null ? -1 : first.getColumn(), (Expression)rhs.get(0).value);
            case 3:
                first = findFirst(rhs);
                return VarExpression(first == null ? -1 : first.getLine(), first == null ? -1 : first.getColumn(), (lr_runtime.Token<AnnotatedValue>)rhs.get(0));
        }
        return null;
    }

    /** Method called when the production:
      *   expr --> add_expr 
      * is reduced.
      */
    protected abstract Expression Expression(int line, int col, Expression arg0);

    /** Method called when the production:
      *   add_expr --> add_expr PLUS var_expr 
      * is reduced.
      */
    protected abstract Expression AddExpression(int line, int col, Expression arg0, lr_runtime.Token<AnnotatedValue> arg1, Expression arg2);

    /** Method called when the production:
      *   add_expr --> var_expr 
      * is reduced.
      */
    protected abstract Expression AddExpression(int line, int col, Expression arg0);

    /** Method called when the production:
      *   var_expr --> IDENTIFIER 
      * is reduced.
      */
    protected abstract Expression VarExpression(int line, int col, lr_runtime.Token<AnnotatedValue> arg0);

}
