package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.VariableType;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This class handles the variable class. It is terminal as well.
 *
 * @author Haotian Wang
 */
public class Variable implements Expression {
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

    /**
     * This method lets the AST act on a Turtle model.
     *
     * @param turtleManager : The TurtleManager that is affected by applying the abstract syntax tree.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double interpret(TurtleManager turtleManager) throws InterpretationException {
        String variableName = myToken.getString();
        Object value = turtleManager.memory().getValue(variableName);
        VariableType type = turtleManager.memory().getVariableType(variableName);
        if (type == VariableType.STRING) {
            throw new InterpretationException(String.format("The variable \"%s\" defined as a String", variableName));
        } else if (type == VariableType.EXPRESSION) {
            Expression statement = (Expression) value;
            return statement.interpret(turtleManager);
        } else if (type == VariableType.DOUBLE) {
            Double temp = (Double) value;
            return temp.doubleValue();
        } else if (type == VariableType.INTEGER) {
            Integer temp = (Integer) value;
            return temp.intValue();
        }
        return 0;
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

    /**
     * This method returns the String representation of the variable name.
     *
     * @return The String representation of the name of the variable.
     */
    public String getVariableName() {
        return myToken.getString();
    }
}
