package engine.compiler.slogoast;

import engine.compiler.Token;
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
public class TwoList implements Expression{
    private Token myToken;
    private ExpressionList listA;
    private ExpressionList listB;

    public TwoList(Token token, ExpressionList list1, ExpressionList list2) {
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

            List<Integer> oldSelected = turtleManager.selected();

            List<Integer> indices = new ArrayList<>();
            for (Expression index: listA.getListOfExpressions()){
                indices.add((int)index.evaluate(turtleManager));
            }

            turtleManager.tell(indices);

            for (Expression op: listB.getListOfExpressions()){
                ret = op.evaluate(turtleManager);
            }

            turtleManager.tell(oldSelected);
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
