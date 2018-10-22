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
    private Token start;
    private Variable var;
    private Expression limit;
    private Token end;
    private ExpressionList expressionList;

    public DoTimes(Token token, Token firstStart, Variable variable, Expression varLit, Token firstEnd, ExpressionList list) {
        myToken = token;
        start = firstStart;
        var = variable;
        limit = varLit;
        end = firstEnd;
        expressionList = list;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{%s %s %s %s %s %s}", myToken.getString(), start.getString(), var.toString(), limit.toString(), end.getString(), expressionList.toString());
    }
}
