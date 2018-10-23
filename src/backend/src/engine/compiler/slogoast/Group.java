package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * This class provides the Grouping concept in AST. for example (5 * 8) = 5 * 8.
 *
 * @author Haotian Wang
 */
public class Group implements Expression {
    private Token groupStart;
    private Token groupEnd;
    private Expression myExpr;

    public Group(Token start, Expression a, Token end) {
        groupStart = start;
        groupEnd = end;
        myExpr = a;
    }

    /**
     * This method gives a String representation of the Expression node enclosed by curly braces.
     *
     * @return A String representation of the abstract syntax tree node.
     */
    @Override
    public String toString() {
        return String.format("{(%s)}", myExpr.toString());
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
