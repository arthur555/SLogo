package engine.slogoast;

import engine.Lexer.Token;

/**
 * This class handles the variable class. It is terminal as well.
 *
 * @author Haotian Wang
 */
public class Variable extends Expression {
    private Token myToken;

    public Variable(Token token) {
        myToken = token;
    }
}
