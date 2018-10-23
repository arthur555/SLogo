package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * This class is an AST node representing binary operation that takes two expressions as commands.
 *
 * @author Haotian Wang
 */
public class Binary implements Expression {
    private Token myToken;
    private Expression myFirstExpr;
    private Expression mySecondExpr;

    public Binary(Token token, Expression a, Expression b) {
        myToken = token;
        myFirstExpr = a;
        mySecondExpr = b;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s}", myToken.getString(), myFirstExpr.toString(), mySecondExpr.toString());
    }

    /**
     * This method uses the Visitor pattern to let the Interpreter acts on the concrete types of the AST node.
     *
     * @param interpreter
     */

    @Override
    public void execute(Interpreter interpreter) throws InterpretationException {
        interpreter.interpret(this);
    }
}
