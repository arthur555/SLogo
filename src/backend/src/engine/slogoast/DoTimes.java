package engine.slogoast;

import engine.Lexer.Token;

import java.util.List;

/**
 * This class handles the concept of DoTimes.
 *
 * @author Haotian Wang
 */
public class DoTimes {
    private Token myToken;
    private Token startA;
    private Variable var;
    private Expression limit;
    private Token endA;
    private Token startB;
    private List<Expression> expressions;
    private Token EndB;

    public DoTimes(Token token, Token firstStart, Variable variable, Expression varLit, Token firstEnd, Token secondStart, List<Expression> exprList, Token secondEnd) {
        myToken = token;
        startA = firstStart;
        var = variable;
        limit = varLit;
        endA = firstEnd;
        startB = secondStart;
        expressions = exprList;
        EndB = secondEnd;
    }
}
