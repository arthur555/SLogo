package engine.compiler.slogoast;

import engine.commands.Home;
import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.errors.InterpretationException;
import model.TurtleModel;

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
     * @param turtle : The TurtleModel that is affected by applying the abstract syntax tree.
     * @param state  : The StateMachine that records the variables.
     * @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     * @author Rahul Ramesh
     */
    @Override
    public double interpret(TurtleModel turtle, StateMachine state) throws InterpretationException {
        if (myToken.getString().equals("PenUp")) {
            turtle.setPenDown(false);
            return 0;
        } else if (myToken.getString().equals("PenDown")) {
            turtle.setPenDown(true);
            return 1;
        } else if (myToken.getString().equals("ShowTurtle")) {
            turtle.setVisible(true);
            return 1;
        } else if (myToken.getString().equals("HideTurtle")) {
            turtle.setVisible(false);
            return 0;
        } else if (myToken.getString().equals("Home")) {
            var prevPen = turtle.isPenDown().getValue();
            turtle.setPenDown(false);
            double myX = turtle.getX();
            double myY = turtle.getY();
            turtle.setX(0);
            turtle.move(true);
            turtle.setY(0);
            turtle.move(false);
            turtle.setPenDown(prevPen);
            return Math.sqrt(myX*myX + myY*myY);
        } else if (myToken.getString().equals("ClearScreen")) {
            Home hm = new Home();
            turtle.clean();
            turtle.setVisible(true);
            return hm.update(turtle);
        } 
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
