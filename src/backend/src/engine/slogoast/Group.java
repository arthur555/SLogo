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

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{(%s)}", myExpr.toString());
    }
}
