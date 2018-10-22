package engine.slogoast;

import engine.Lexer.Token;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class handles the Repeat and If logic. They are in the form of Condition Expression [ (Expression)* ].
 *
 * @author Haotian Wang
 */
public class Condition extends Expression {
    private Token condition;
    private Expression expr;
    private ExpressionList expressionList;

    public Condition(Token a, Expression val, ExpressionList list) {
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
}
