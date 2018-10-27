package engine.compiler.slogoast;

import engine.compiler.storage.StateMachine;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * The class handles the UserFunction AST node. For example, :drawSquare [4 5] is such a UserFunction.
 *
 * @author Haotian Wang
 */
public class UserFunction extends Expression {
    private Variable myVariable;
    private ExpressionList parameters;

    public UserFunction(Variable var, ExpressionList list, StateMachine glob) {
        super(glob);
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
     * This method lets the AST act on a Turtle model.
     *
     * @param turtleManager : The TurtleManager that is affected by applying the abstract syntax tree.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double interpret(TurtleManager turtleManager) throws InterpretationException {
        MakeUserInstruction function = (MakeUserInstruction) turtleManager.memory().getGlobalValueInGeneralForm(myVariable.getVariableName());
        VariableList desiredParameters = function.getParameters();
        ExpressionList desiredExpressions = function.getExpressionList();
        if (desiredParameters.getListOfVariables().size() != parameters.getListOfExpressions().size()) {
            throw new InterpretationException(String.format("The number of expressions passed in, %d, does not match the number of desired parameters defined earlier, %d", parameters.getListOfExpressions().size(), desiredParameters.getListOfVariables().size()));
        }
        for (int i = 0; i < parameters.getListOfExpressions().size(); i++) {
            turtleManager.memory().setLocalDouble(desiredParameters.getListOfVariables().get(i).getVariableName(), parameters.getListOfExpressions().get(i).evaluate(turtleManager));
        }
        double ret = desiredExpressions.interpret(turtleManager);
        for (int i = 0; i < desiredParameters.getListOfVariables().size(); i++) {
            turtleManager.memory().removeLocalVariable(desiredParameters.getListOfVariables().get(i).getVariableName());
        }
        return ret;
    }

    /**
     * This method evaluates the return value of the expression, without applying actual effects on the turtle.
     *
     *
     * @param turtleManager@return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(TurtleManager turtleManager) throws InterpretationException {
        return interpret(turtleManager);
    }
}
