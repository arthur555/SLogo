package engine.compiler.slogoast;

import engine.errors.InterpretationException;
import engine.compiler.storage.StateMachine;
import model.TurtleManager;
import model.TurtleModel;

/**
 * This is an abstract class that all AST nodes shall extend from.
 *
 * @author Haotian Wang
 */
public interface Expression {
    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    String toString();

    /**
     * This method lets the AST act on a Turtle model.
     *
     * @param turtleManager : The TurtleManager that is affected by applying the abstract syntax tree.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    double interpret(TurtleManager turtleManager) throws InterpretationException;

    /**
     * This method evaluates the return value of the expression, without applying actual effects on the turtle.
     *
     * @param state: The StateMachine that records the variables.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    double evaluate(StateMachine state) throws InterpretationException;
}
