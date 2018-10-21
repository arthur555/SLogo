package engine.slogoast;

import engine.Lexer.Token;

/**
 * This class is an AST node representing binary operation that takes two expressions as commands.
 *
 * @author Haotian Wang
 */
public class Binary extends Expression {
    private Token myToken;
    private Expression myFirstExpr;
    private Expression mySecondExpr;

    public Binary(Token token, Expression a, Expression b) {
        myToken = token;
        myFirstExpr = a;
        mySecondExpr = b;
    }
}
