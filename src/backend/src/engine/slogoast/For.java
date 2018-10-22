package engine.slogoast;

import engine.Lexer.Token;

import java.util.Arrays;
import java.util.List;

/**
 * This class emulates the For loop logic in the AST.
 *
 * @author Haotian Wang
 */
public class For extends Expression {
    private Token myToken;
    private Token startA;
    private Variable var;
    private Expression min;
    private Expression max;
    private Expression step;
    private Token endA;
    private Token startB;
    private List<Expression> expressions;
    private Token endB;

    public For(Token token, Token firstStart, Variable variable, Expression lower, Expression higher, Expression increment, Token firstEnd, Token secondStart, List<Expression> exprList, Token secondEnd) {
        myToken = token;
        startA = firstStart;
        var = variable;
        min = lower;
        max = higher;
        step = increment;
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
        return String.format("{%s ; %s ; %s ; %s ; %s ; %s ; %s ; %s ; %s ; %s}", myToken.toString(), startA.toString(), var.toString(), min.toString(), max.toString(), step.toString(), endA.toString(), startB.toString(), Arrays.toString(expressions.toArray(new Expression[expressions.size()])), endB.toString());
    }
}
