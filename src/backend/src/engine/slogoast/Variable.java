package engine.slogoast;

import engine.lexer.Token;

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

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s}", myToken.getString());
    }
}
