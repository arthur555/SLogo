package engine.slogoast;

import engine.lexer.Token;

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
