package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.errors.InterpretationException;
import model.TurtleManager;
import model.TurtleModel;

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
        return 0;
    }

    /**
     * This method evaluates the return value of the expression, without applying actual effects on the turtle.
     *
     * @param state : The StateMachine that records the variables.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(StateMachine state) throws InterpretationException {
        return 0;
    }
}
