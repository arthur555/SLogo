package engine.compiler.slogoast;

import engine.compiler.storage.StateMachine;
import engine.compiler.storage.StateMachineV2;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This is an abstract class that all AST nodes shall extend from.
 *
 * @author Haotian Wang
 */
public abstract class Expression {

    StateMachine myMemory;
    StateMachine myGlobal;


    public Expression(StateMachine glob){
        myGlobal = glob;
    }


    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    //String toString();

    /**
     * This method lets the AST act on a Turtle model.
     *
     * @param turtleManager : The TurtleManager that is affected by applying the abstract syntax tree.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    public abstract double interpret(TurtleManager turtleManager) throws InterpretationException;

    /**
     * This method evaluates the return value of the expression, without applying actual effects on the turtle.
     *
     *
     * @param turtleManager@return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    abstract double evaluate(TurtleManager turtleManager) throws InterpretationException;
}
