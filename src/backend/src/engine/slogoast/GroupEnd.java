package engine.slogoast;

import engine.Lexer.Token;

/**
 * This class together with GroupStart provides the Grouping concept in AST. for example (5 * 8) = 5 * 8.
 *
 * @author Haotian Wang
 */
public class GroupEnd {
    private Token myToken;
    private Expression myExpr;
    private GroupStart myGroupStart;

    public GroupEnd(Token token, Expression a, GroupStart gs) {
        myToken = token;
        myExpr = a;
        myGroupStart = gs;
    }
}
