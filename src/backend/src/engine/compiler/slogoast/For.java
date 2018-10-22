package engine.compiler.slogoast;

import engine.compiler.lexer.Token;

/**
 * This class emulates the For loop logic in the AST.
 *
 * @author Haotian Wang
 */
public class For extends Expression {
    private Token myToken;
    private Token start;
    private Variable var;
    private Expression min;
    private Expression max;
    private Expression step;
    private Token end;
    private ExpressionList expressionList;

    public For(Token token, Token firstStart, Variable variable, Expression lower, Expression higher, Expression increment, Token firstEnd, ExpressionList list) {
        myToken = token;
        start = firstStart;
        var = variable;
        min = lower;
        max = higher;
        step = increment;
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
        return String.format("{%s %s %s %s %s %s %s %s}", myToken.getString(), start.getString(), var.toString(), min.toString(), max.toString(), step.toString(), end.getString(), expressionList.toString());
    }
}
