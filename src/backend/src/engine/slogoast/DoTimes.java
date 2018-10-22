package engine.slogoast;

import engine.Lexer.Token;

import java.util.Arrays;
import java.util.List;

/**
 * This class handles the concept of DoTimes.
 *
 * @author Haotian Wang
 */
public class DoTimes extends Expression {
    private Token myToken;
    private Token startA;
    private Variable var;
    private Expression limit;
    private Token endA;
    private Token startB;
    private List<Expression> expressions;
    private Token endB;

    public DoTimes(Token token, Token firstStart, Variable variable, Expression varLit, Token firstEnd, Token secondStart, List<Expression> exprList, Token secondEnd) {
        myToken = token;
        startA = firstStart;
        var = variable;
        limit = varLit;
        endA = firstEnd;
        startB = secondStart;
        expressions = exprList;
        endB = secondEnd;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        Expression[] arr = expressions.toArray(new Expression[expressions.size()]);
        return String.format("{%s ; %s ; %s ; %s ; %s ; %s ; %s ; %s}", myToken.toString(), startA.toString(), var.toString(), limit.toString(), endA.toString(), startB.toString(), Arrays.toString(arr), endB.toString());
    }
}
