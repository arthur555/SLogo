package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This class implements the Tell grammar.
 *
 * @author Haotian Wang
 */
public class Tell implements Expression {
    private Token myToken;
    private ExpressionList turtles;

    public Tell(Token token, ExpressionList list) {
        myToken = token;
        turtles = list;
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
        if (myToken.getString().equals("Tell")) {
            // TODO
        }
        return 0;
    }

    /**
     * This method evaluates the return value of the expression, without applying actual effects on the turtle.
     *
     * @param turtleManager @return A double value returned by evaluating the expression.
     * @throws InterpretationException
     */
    @Override
    public double evaluate(TurtleManager turtleManager) throws InterpretationException {
        return 0;
    }
}
