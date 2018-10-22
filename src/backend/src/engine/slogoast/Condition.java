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
    private Token start;
    private Token end;
    private List<Expression> exprList;

    public Condition(Token a, Expression val, Token listStart, Token listEnd, List<Expression> list) {
        condition = a;
        expr = val;
        start = listStart;
        end = listEnd;
        exprList = list;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        Expression[] arr = exprList.toArray(new Expression[exprList.size()]);
        return String.format("{%s ; %s ; %s ; %s ; %s}", condition.toString(), expr.toString(), start.toString(), end.toString(), Arrays.toString(arr));
    }
}
