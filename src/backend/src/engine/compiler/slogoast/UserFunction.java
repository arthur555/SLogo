package engine.compiler.slogoast;

/**
 * The class handles the UserFunction AST node. For example, :drawSquare [4 5] is such a UserFunction.
 *
 * @author Haotian Wang
 */
public class UserFunction extends Expression {
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
}
