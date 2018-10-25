package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This class is a terminal node in the AST.
 *
 * @author Haotian Wang, Rahul Ramesh
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
     * This method lets the AST act on a Turtle model.
     *
     * @param turtleManager : The TurtleManager that is affected by applying the abstract syntax tree.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     * @author Rahul Ramesh
     */
    @Override
    public double interpret(TurtleManager turtleManager) throws InterpretationException {
        if (myToken.getString().equals("PenUp")) {
            return turtleManager.setPenDown(true);
        } else if (myToken.getString().equals("PenDown")) {
            return turtleManager.setPenDown(true);
        } else if (myToken.getString().equals("ShowTurtle")) {
            return turtleManager.setVisible(true);
        } else if (myToken.getString().equals("HideTurtle")) {
            return turtleManager.setVisible(false);
        } else if (myToken.getString().equals("Home")) {
            return turtleManager.moveTo(0,0,true);
        } else if (myToken.getString().equals("ClearScreen")) {
            turtleManager.clear();
            return turtleManager.moveTo(0,0,true);
        } else if (myToken.getString().equals("XCoordinate")) {

        } else if (myToken.getString().equals("YCoordinate")) {

        } else if (myToken.getString().equals("Heading")) {

        } else if (myToken.getString().equals("IsPenDown")) {

        } else if (myToken.getString().equals("IsShowing")) {

        } else if (myToken.getType().equals("Constant")) {
            return Double.parseDouble(myToken.getString());
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
}
