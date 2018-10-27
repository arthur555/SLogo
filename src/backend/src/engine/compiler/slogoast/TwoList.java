package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.storage.StateMachine;
import engine.errors.InterpretationException;
import model.TurtleManager;
import model.TurtleModel;
import model.TurtleOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the TwoList grammar.
 *
 * @author Haotian Wang
 */
public class TwoList extends Expression{
    private Token myToken;
    private ExpressionList listA;
    private ExpressionList listB;

    public TwoList(Token token, ExpressionList list1, ExpressionList list2, StateMachine glob) {
        super(glob);
        myToken = token;
        listA = list1;
        listB = list2;
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
        double ret = 0;
        if (myToken.getString().equals("Ask")) {
            TurtleOperations<ExpressionList> ops = (TurtleOperations<ExpressionList>) listB;
            List<Integer> indices = new ArrayList<>();
            for (Expression index: listA.getListOfExpressions()){
                indices.add((int)index.evaluate(turtleManager));
            }
            ret = turtleManager.ask(indices, ops).evaluate(turtleManager);
        } else if (myToken.getString().equals("AskWith")) {
            // TODO
        }
        return ret;
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
