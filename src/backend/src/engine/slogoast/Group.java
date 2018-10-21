package engine.slogoast;

import engine.Lexer.Token;

/**
 * This class provides the Grouping concept in AST. for example (5 * 8) = 5 * 8.
 *
 * @author Haotian Wang
 */
public class Group extends Expression {
    private Token groupStart;
    private Token groupEnd;
    private Expression myExpr;

    public Group(Token start, Expression a, Token end) {
        groupStart = start;
        groupEnd = end;
        myExpr = a;
    }
}
