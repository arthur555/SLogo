package engine.compiler.slogoast;

import engine.compiler.Token;
import engine.compiler.interpreter.Interpreter;
import engine.errors.InterpretationException;

/**
 * This class handles the concept of DoTimes.
 *
 * @author Haotian Wang
 */
public class DoTimes implements Expression {
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
