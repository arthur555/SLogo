package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

import java.util.Arrays;
import java.util.List;

/**
 * This class handles the list of Expression grammar.
 *
 * @author Haotian Wang
 */
public class ExpressionList implements Expression {
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

    /**
     * This method uses the Visitor pattern to let the Interpreter acts on the concrete types of the AST node.
     *
     * @param interpreter
     */
    @Override
    public void execute(Interpreter interpreter) throws InterpretationException {
        interpreter.interpret(this);
    }
}
