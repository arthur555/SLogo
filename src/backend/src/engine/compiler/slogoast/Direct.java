package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * This class is a terminal node in the AST.
 *
 * @author Haotian Wang
 */
public class Direct implements Expression {
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
