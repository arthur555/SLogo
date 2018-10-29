package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * Implements the Ternary operator in AST node.
 *
 * @author Haotian Wang
 */
public class Quaternary implements Expression {
    private Token myToken;
    private Expression firstArg;
    private Expression secondArg;
    private Expression thirdArg;
    private Expression fourthArg;

    public Quaternary(Token token, Expression expr1, Expression expr2, Expression expr3, Expression expr4) {
        myToken = token;
        firstArg = expr1;
        secondArg = expr2;
        thirdArg = expr3;
        fourthArg = expr4;
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
        if (myToken.getString().equals("SetPalette")) {
            double indexNum = firstArg.evaluate(turtleManager);
            String index = "colorIndex" + (int)indexNum;
            String hex = "#" + decimalToHex(secondArg.evaluate(turtleManager)) +
                    decimalToHex(thirdArg.evaluate(turtleManager)) +
                            decimalToHex(fourthArg.evaluate(turtleManager));
            turtleManager.memory().setString(firstArg.toString(), hex);
            return indexNum;
        }
        return 0;
    }

    private String decimalToHex(double num) throws InterpretationException {
        if (num < 0 || num > 255){
            throw new InterpretationException("RGB value not between range 0 and 255");
        }
        return Integer.toHexString( (int) num);
    }
}
