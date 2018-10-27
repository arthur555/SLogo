package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.compiler.storage.VariableType;
import engine.errors.InterpretationException;
import model.TurtleManager;

/**
 * This class handles the Repeat and If logic. They are in the form of Condition Expression [ (Expression)* ].
 *
 * @author Haotian Wang
 */
public class Condition extends Expression {
    private Token condition;
    private Expression expr;
    private ExpressionList expressionList;
    private static final String LOOP_COUNT = ":repcount";

    public Condition(Token a, Expression val, ExpressionList list, StateMachine glob) {
        super(glob);
        condition = a;
        expr = val;
        expressionList = list;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s}", condition.getString(), expr.toString(), expressionList.toString());
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
        if (condition.getString().equals("If")) {
            if (expr.evaluate(turtleManager) != 0) {
                return expressionList.interpret(turtleManager);
            } else {
                return 0;
            }
        } else if (condition.getString().equals("Repeat")) {
            int times = (int) expr.evaluate(turtleManager);
            if (times <= 0) {
                return 0;
            } else {
                double ret = 0;
                for (int i = 1 ; i <= times; i++) {
                    turtleManager.memory().setLocalInteger(LOOP_COUNT, i);
                    ret = expressionList.interpret(turtleManager);
                }
                turtleManager.memory().removeLocalVariable(LOOP_COUNT);
                return ret;
            }
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
        return evaluate(turtleManager);
    }


}
