package engine.slogoast;

import engine.Lexer.Token;

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
    private Token EndB;

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
        EndB = secondEnd;
    }
}
