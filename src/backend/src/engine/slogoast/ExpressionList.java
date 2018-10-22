package engine.slogoast;

import engine.lexer.Token;

import java.util.Arrays;
import java.util.List;

/**
 * This class handles the list of Expression grammar.
 *
 * @author Haotian Wang
 */
public class ExpressionList extends Expression {
    private Token listStart;
    private List<Expression> expressionList;
    private Token listEnd;

    public ExpressionList(Token start, List<Expression> list, Token end) {
        listStart = start;
        expressionList = list;
        listEnd = end;
    }
    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return Arrays.toString(expressionList.toArray(new Expression[expressionList.size()]));
    }
}
