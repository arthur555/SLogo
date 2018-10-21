package engine.slogoast;

import engine.Lexer.Token;

/**
 * This class together with GroupEnd represents the Grouping concept in AST. for example (5 * 8) = 5 * 8.
 *
 * @author Haotian Wang
 */
public class GroupStart extends Expression {
    private Token myToken;
    private Expression myExpr;
    private GroupEnd myGroupEnd;

    public GroupStart(Token token, Expression a, GroupEnd ge) {
        myToken = token;
        myExpr = a;
        myGroupEnd = ge;
    }
}
