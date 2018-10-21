package engine.slogoast;

import engine.Lexer.Token;

/**
 * This class is a terminal node in the AST.
 *
 * @author Haotian Wang
 */
public class Direct extends Expression {
    private Token myToken;

    public Direct(Token token) {
        myToken = token;
    }
}
