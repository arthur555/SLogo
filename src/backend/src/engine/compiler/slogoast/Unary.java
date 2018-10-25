package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This class handles the expression that takes in only one expression as parameter.
 *
 * @author Haotian Wang
 */
public class Unary implements Expression {
    private final Token myToken;
    private final Expression myExpr;

    public Unary(Token token, Expression a) {
        myToken = token;
        myExpr = a;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s}", myToken.getString(), myExpr.toString());
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
        double head = turtleManager.getAngle(); //in radians
        double x = turtleManager.getX();
        double y = turtleManager.getY();
        double value = myExpr.evaluate(turtleManager);
        if (myToken.getString().equals("Forward")){
            double stepX = value*Math.cos(Math.toRadians(head));
            double stepY = value*Math.sin(Math.toRadians(head));
            return turtleManager.moveTo(x+stepX, y+stepY, false);
        }
        else if (myToken.getString().equals("Backward")){
            return turtleManager.moveTo(x-value*Math.cos(Math.toRadians(head)), y-value*Math.sin(Math.toRadians(head)), false);
        }
        else if (myToken.getString().equals("Right")){
            return turtleManager.setAngle((head + value)%360);
        }
        else if (myToken.getString().equals("Left")){
            return turtleManager.setAngle((head - value)%360);
        }
        else if (myToken.getString().equals("SetHeading")){
            return turtleManager.setAngle(value);
        }
        else if (myToken.getString().equals("SetTowards")){

        }
        else if (myToken.getString().equals("Minus")){

        }
        else if (myToken.getString().equals("Sine")){

        }
        else if (myToken.getString().equals("Tangent")){

        }
        else if (myToken.getString().equals("ArcTangent")){

        }
        else if (myToken.getString().equals("NaturalLog")){

        }
        else if (myToken.getString().equals("Not")){

        }

        return turtleManager.moveTo(turtleManager.getX()+100, turtleManager.getY()+100, false);
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
