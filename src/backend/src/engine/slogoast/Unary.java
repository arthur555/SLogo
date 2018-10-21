package engine.slogoast;

import engine.Lexer.Token;

/**
 * This class handles the expression that takes in only one expression as parameter.
 *
 * @author Haotian Wang
 */
public class Unary extends Expression {
    private final Token myToken;
    private final Expression myFirstExpr;

    public Unary(Token token, Expression a) {
        myToken = token;
        myFirstExpr = a;
    }
}
