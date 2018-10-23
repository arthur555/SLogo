package engine.compiler.slogoast;

import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * The class handles the UserFunction AST node. For example, :drawSquare [4 5] is such a UserFunction.
 *
 * @author Haotian Wang
 */
public class UserFunction implements Expression {
    private Variable myVariable;
    private ExpressionList parameters;

    public UserFunction(Variable var, ExpressionList list) {
        myVariable = var;
        parameters = list;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s}", myVariable.toString(), parameters.toString());
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
